/**
 * Classe que representa a lista encadeada genérica e seus métodos
 */
public class ListaEncadeada<T> {
    private No<T> inicio; // O início (start/head) da lista
    private int tamanhoTotal = 0; // Atributo para otimização do método tamanho()

    public ListaEncadeada() {
        this.inicio = null;
    }

    // PARTE 1: Implementações solicitadas

    /**
     * Remove o primeiro elemento da lista.
     * Complexidade: O(1) - Esta operação é O(1) porque apenas modifica a referência
     * do início da lista para o próximo nó, sem necessidade de percorrer a lista.
     */
    public void removerDoInicio() {
        if (this.inicio != null) {
            this.inicio = this.inicio.proximo;
            this.tamanhoTotal--;
        }
    }

    /**
     * Obtém o elemento na posição especificada pelo índice.
     * Complexidade: O(n) - No pior caso, é necessário percorrer toda a lista
     * até chegar ao índice desejado, resultando em complexidade linear.
     */
    public T obterEm(int indice) {
        if (indice < 0 || indice >= tamanhoTotal) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }

        No<T> atual = this.inicio;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual.dado;
    }

    /**
     * Remove a primeira ocorrência do valor especificado da lista.
     * Complexidade: O(n) - No pior caso, o elemento pode estar no final da lista
     * ou não existir, exigindo percorrer todos os nós.
     */
    public void removerValor(T dado) {
        if (this.inicio == null) {
            return;
        }

        // Caso especial: remover o primeiro nó
        if (this.inicio.dado.equals(dado)) {
            this.inicio = this.inicio.proximo;
            this.tamanhoTotal--;
            return;
        }

        No<T> anterior = this.inicio;
        while (anterior.proximo != null) {
            if (anterior.proximo.dado.equals(dado)) {
                anterior.proximo = anterior.proximo.proximo;
                this.tamanhoTotal--;
                return;
            }
            anterior = anterior.proximo;
        }
    }

    // PARTE 2: Operações de fim de lista e contagem

    /**
     * Adiciona um novo elemento no final da lista.
     * Complexidade: O(n) - É necessário percorrer toda a lista até encontrar
     * o último nó para então adicionar o novo elemento.
     */
    public void inserirNoFim(T dado) {
        No<T> novoNo = new No<>(dado);

        if (this.inicio == null) {
            this.inicio = novoNo;
        } else {
            No<T> atual = this.inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        this.tamanhoTotal++;
    }

    // PARTE 3: Método tamanho() otimizado

    /**
     * Retorna o número total de elementos na lista.
     * Complexidade: O(1) - Retorna diretamente o valor do atributo tamanhoTotal,
     * que é mantido atualizado pelos métodos de inserção e remoção.
     */
    public int tamanho() {
        return this.tamanhoTotal;
    }

    // Métodos existentes modificados para manter o tamanhoTotal atualizado

    /**
     * Adiciona um novo nó no início da lista.
     * Complexidade: O(1)
     */
    public void inserirNoInicio(T dado) {
        No<T> novoNo = new No<>(dado);
        novoNo.proximo = this.inicio;
        this.inicio = novoNo;
        this.tamanhoTotal++;
    }

    /**
     * Verifica se um elemento 'alvo' existe na lista.
     * Complexidade: O(n)
     */
    public boolean buscar(T alvo) {
        No<T> atual = this.inicio;
        while (atual != null) {
            if (alvo.equals(atual.dado)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    /**
     * Remove elementos duplicados da lista usando um método simples.
     * Complexidade: O(n^2)
     */
    public void removerDuplicatasSimples() {
        No<T> noExterno = this.inicio;
        while (noExterno != null) {
            No<T> corredor = noExterno;
            while (corredor.proximo != null) {
                if (corredor.proximo.dado.equals(noExterno.dado)) {
                    corredor.proximo = corredor.proximo.proximo;
                    this.tamanhoTotal--;
                } else {
                    corredor = corredor.proximo;
                }
            }
            noExterno = noExterno.proximo;
        }
    }

    /**
     * Método auxiliar para exibir a lista.
     * Complexidade: O(n)
     */
    public void exibir() {
        if (this.inicio == null) {
            System.out.println("Lista Vazia");
            return;
        }

        No<T> atual = this.inicio;
        StringBuilder sb = new StringBuilder();
        while (atual != null) {
            sb.append(String.valueOf(atual.dado)).append(" -> ");
            atual = atual.proximo;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Verifica se a lista está vazia.
     * Complexidade: O(1)
     */
    public boolean estaVazia() {
        return this.inicio == null;
    }
}
