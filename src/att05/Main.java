package att05;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Estoque estoque = new Estoque();

    public static void main(String[] args) {
        // dados iniciais para teste rápido
        estoque.adicionarProduto(new Produto("CEL-001", "Smartphone X", 1500.00, 20));
        estoque.adicionarProduto(new Produto("TV-050", "TV 50\" 4K", 2799.90, 8));
        estoque.adicionarProduto(new Produto("NTB-015", "Notebook i5", 3499.00, 12));
        estoque.adicionarProduto(new Produto("FON-200", "Fone Bluetooth", 199.99, 45));

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Escolha uma opção: ");
            System.out.println();

            switch (opcao) {
                case 1 -> adicionar();                 // adiciona produto
                case 2 -> buscar();                    // busca por SKU
                case 3 -> atualizarQuantidade();       // define quantidade absoluta
                case 4 -> remover();                   // remove por SKU (com confirmação)
                case 5 -> { estoque.listarTodosOsProdutos(); pausar(); }  // lista os produtos
                case 6 -> listarBaixoEstoque();        // lista com estoque baixo
                case 7 -> mostrarValorTotal();         // mostra R$ total
                case 8 -> atualizarPreco();            // atualiza preço
                case 9 -> reporQuantidade();           // soma quantidade (+N)
                case 10 -> darBaixaQuantidade();       // subtrai quantidade (-N)
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("==================================");
        System.out.println("           MENU ESTOQUE           ");
        System.out.println("==================================");
        System.out.println("1) Adicionar produto");
        System.out.println("2) Buscar produto por SKU");
        System.out.println("3) Atualizar quantidade (valor absoluto)");
        System.out.println("4) Remover produto");
        System.out.println("5) Listar todos os produtos");
        System.out.println("6) Listar produtos com estoque baixo");
        System.out.println("7) Mostrar valor total do estoque");
        System.out.println("8) Atualizar preço de um produto");
        System.out.println("9) Repor quantidade (+N)");
        System.out.println("10) Dar baixa na quantidade (-N)");
        System.out.println("0) Sair");
        System.out.println("==================================");
    }

    // adiciona produto
    private static void adicionar() {
        String sku = lerString("SKU: ");
        String nome = lerString("Nome: ");
        double preco = lerDoubleNaoNegativo("Preço (R$): ");
        int qtd = lerIntNaoNegativo("Quantidade: ");
        try {
            estoque.adicionarProduto(new Produto(sku, nome, preco, qtd));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar: " + e.getMessage());
        }
        pausar();
    }

    // busca e mostra um produto por SKU
    private static void buscar() {
        String sku = lerString("Informe o SKU: ");
        Produto p = estoque.buscarProduto(sku);
        if (p == null) System.out.println("Produto não encontrado.");
        else System.out.println(p);
        pausar();
    }

    // define a quantidade absoluta (substitui pela nova)
    private static void atualizarQuantidade() {
        String sku = lerString("SKU: ");
        int novaQtd = lerIntNaoNegativo("Nova quantidade (valor absoluto): ");
        estoque.atualizarQuantidade(sku, novaQtd);
        pausar();
    }

    // confirma e remove produto por SKU
    private static void remover() {
        String sku = lerString("SKU: ");
        Produto p = estoque.buscarProduto(sku);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            pausar();
            return;
        }
        System.out.println("Remover este produto? " + p);
        String conf = lerString("Digite S para confirmar: ");
        if (conf.equalsIgnoreCase("S")) {
            estoque.removerProduto(sku);
        } else {
            System.out.println("Remoção cancelada.");
        }
        pausar();
    }

    // lista itens com estoque <= limite
    private static void listarBaixoEstoque() {
        int limite = lerIntNaoNegativo("Exibir com quantidade <= (limite): ");
        estoque.listarProdutosComEstoqueBaixo(limite);
        pausar();
    }

    // mostra o valor total do estoque
    private static void mostrarValorTotal() {
        double total = estoque.calcularValorTotalEstoque();
        System.out.printf("Valor total do estoque: R$%.2f%n", total);
        pausar();
    }

    // atualiza o preço de um produto
    private static void atualizarPreco() {
        String sku = lerString("SKU: ");
        Produto p = estoque.buscarProduto(sku);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            pausar();
            return;
        }
        double novoPreco = lerDoubleNaoNegativo("Novo preço (R$): ");
        try {
            p.setPreco(novoPreco);
            System.out.println("Preço atualizado: " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }

    // repõe quantidade (+N)
    private static void reporQuantidade() {
        String sku = lerString("SKU: ");
        Produto p = estoque.buscarProduto(sku);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            pausar();
            return;
        }
        int acrescimo = lerIntNaoNegativo("Acrescentar quantas unidades (+N): ");
        int nova = p.getQuantidadeEmEstoque() + acrescimo;
        estoque.atualizarQuantidade(sku, nova);
        pausar();
    }

    // dá baixa na quantidade (-N), sem ficar negativo
    private static void darBaixaQuantidade() {
        String sku = lerString("SKU: ");
        Produto p = estoque.buscarProduto(sku);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            pausar();
            return;
        }
        int baixa = lerIntNaoNegativo("Dar baixa de quantas unidades (-N): ");
        int atual = p.getQuantidadeEmEstoque();
        if (baixa > atual) {
            System.out.println("Erro: não há quantidade suficiente. Qtd atual = " + atual);
        } else {
            estoque.atualizarQuantidade(sku, atual - baixa);
        }
        pausar();
    }

    // utilitários de leitura
    private static String lerString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static int lerIntNaoNegativo(String msg) {
        while (true) {
            int v = lerInt(msg);
            if (v < 0) System.out.println("Não pode ser negativo.");
            else return v;
        }
    }

    private static double lerDoubleNaoNegativo(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String s = sc.nextLine().trim().replace(",", ".");
                double v = Double.parseDouble(s);
                if (v < 0) System.out.println("Não pode ser negativo.");
                else return v;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (use ponto ou vírgula).");
            }
        }
    }

    // pausa simples para o usuário ler a saída
    private static void pausar() {
        System.out.print("Pressione Enter para continuar...");
        sc.nextLine();
    }
}
