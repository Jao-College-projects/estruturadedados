package att01;

/**
 * Classe principal para testes e validação da Lista Encadeada
 * PARTE 4: Testes e Validação
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTES DA LISTA ENCADEADA ===\n");

        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        // Teste 1: Lista vazia
        System.out.println("1. Teste com lista vazia:");
        System.out.println("Tamanho: " + lista.tamanho());
        System.out.println("Está vazia: " + lista.estaVazia());
        lista.exibir();
        lista.removerDoInicio(); // Não deve fazer nada
        System.out.println("Após remover do início (lista vazia): " + lista.tamanho());

        // Teste 2: Inserção no início
        System.out.println("\n2. Inserindo elementos no início:");
        lista.inserirNoInicio(10);
        lista.inserirNoInicio(20);
        lista.inserirNoInicio(30);
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());
        System.out.println("Está vazia: " + lista.estaVazia());

        // Teste 3: Inserção no fim
        System.out.println("\n3. Inserindo elementos no fim:");
        lista.inserirNoFim(5);
        lista.inserirNoFim(1);
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        // Teste 4: Obter elemento por índice
        System.out.println("\n4. Obtendo elementos por índice:");
        try {
            for (int i = 0; i < lista.tamanho(); i++) {
                System.out.println("Índice " + i + ": " + lista.obterEm(i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Teste 5: Índice inválido
        System.out.println("\n5. Teste com índice inválido:");
        try {
            System.out.println("Tentando acessar índice 10...");
            lista.obterEm(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            System.out.println("Tentando acessar índice -1...");
            lista.obterEm(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        // Teste 6: Remoção do início
        System.out.println("\n6. Removendo do início:");
        System.out.println("Antes da remoção:");
        lista.exibir();
        lista.removerDoInicio();
        System.out.println("Após remoção:");
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        // Teste 7: Remoção por valor
        System.out.println("\n7. Removendo valor específico:");
        System.out.println("Lista atual:");
        lista.exibir();
        System.out.println("Removendo valor 10...");
        lista.removerValor(10);
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        System.out.println("Removendo valor 1 (último elemento)...");
        lista.removerValor(1);
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        // Teste 8: Busca
        System.out.println("\n8. Testando busca:");
        System.out.println("Lista atual:");
        lista.exibir();
        System.out.println("Buscar 20: " + lista.buscar(20));
        System.out.println("Buscar 5: " + lista.buscar(5));
        System.out.println("Buscar 100: " + lista.buscar(100));

        // Teste 9: Duplicatas
        System.out.println("\n9. Testando remoção de duplicatas:");
        lista.inserirNoInicio(20); // Adiciona duplicata do 20
        lista.inserirNoFim(5);     // Adiciona duplicata do 5
        lista.inserirNoFim(20);    // Adiciona outra duplicata do 20
        lista.inserirNoInicio(5);  // Adiciona outra duplicata do 5

        System.out.println("Lista com duplicatas:");
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        lista.removerDuplicatasSimples();
        System.out.println("Após remover duplicatas:");
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        // Teste 10: Teste de robustez - remover valor inexistente
        System.out.println("\n10. Removendo valor inexistente:");
        System.out.println("Lista atual:");
        lista.exibir();
        System.out.println("Tentando remover valor 999...");
        lista.removerValor(999);
        lista.exibir();
        System.out.println("Tamanho: " + lista.tamanho());

        // Teste 11: Esvaziar lista
        System.out.println("\n11. Esvaziando a lista:");
        while (!lista.estaVazia()) {
            System.out.println("Removendo do início... Tamanho atual: " + lista.tamanho());
            lista.removerDoInicio();
        }
        System.out.println("Lista final:");
        lista.exibir();
        System.out.println("Tamanho final: " + lista.tamanho());
        System.out.println("Está vazia: " + lista.estaVazia());

        // Teste 12: Teste com Strings
        System.out.println("\n12. Teste com Strings:");
        ListaEncadeada<String> listaStrings = new ListaEncadeada<>();
        listaStrings.inserirNoInicio("Mundo");
        listaStrings.inserirNoInicio("Olá");
        listaStrings.inserirNoFim("!");
        listaStrings.exibir();
        System.out.println("Elemento no índice 1: " + listaStrings.obterEm(1));
        System.out.println("Buscar 'Mundo': " + listaStrings.buscar("Mundo"));

        System.out.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
}
