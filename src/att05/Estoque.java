package att05;

import java.util.HashMap;
import java.util.Map;
public class Estoque {
    private final Map<String, Produto> produtos = new HashMap<>();

    // adiciona um produto novo se o SKU não existir
    public void adicionarProduto(Produto produto) {
        String sku = produto.getSku();
        if (produtos.containsKey(sku)) {
            System.out.println("Erro: SKU já cadastrado: " + sku);
            return;
        }
        produtos.put(sku, produto);
        System.out.println("Produto adicionado: " + produto);
    }

    // busca por SKU e retorna o Produto (ou null)
    public Produto buscarProduto(String sku) {
        return produtos.get(sku);
    }

    // atualiza a quantidade absoluta (não aceita negativa)
    public void atualizarQuantidade(String sku, int novaQuantidade) {
        if (novaQuantidade < 0) {
            System.out.println("Erro: quantidade não pode ser negativa.");
            return;
        }
        Produto p = produtos.get(sku);
        if (p == null) {
            System.out.println("Erro: produto não encontrado para SKU: " + sku);
            return;
        }
        p.setQuantidadeEmEstoque(novaQuantidade);
        System.out.println("Quantidade atualizada: " + p);
    }

    // remove o produto por SKU
    public void removerProduto(String sku) {
        Produto removido = produtos.remove(sku);
        if (removido != null) {
            System.out.println("Removido: " + removido);
        } else {
            System.out.println("Erro: SKU não encontrado: " + sku);
        }
    }

    // lista os produtos
    public void listarTodosOsProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        System.out.println("SKU        | Nome                 | Preço     | Qtd");
        System.out.println("-----------+----------------------+-----------+-----");
        for (Produto p : produtos.values()) {
            System.out.printf("%-10s | %-20s | R$%8.2f | %3d%n",
                    p.getSku(), abreviar(p.getNome(), 20), p.getPreco(), p.getQuantidadeEmEstoque());
        }
    }

    // lista produtos com estoque <= limite
    public void listarProdutosComEstoqueBaixo(int limite) {
        boolean encontrou = false;
        for (Produto p : produtos.values()) {
            if (p.getQuantidadeEmEstoque() <= limite) {
                if (!encontrou) {
                    System.out.println("Itens com estoque <= " + limite + ":");
                    System.out.println("SKU        | Nome                 | Preço     | Qtd");
                    System.out.println("-----------+----------------------+-----------+-----");
                }
                System.out.printf("%-10s | %-20s | R$%8.2f | %3d%n",
                        p.getSku(), abreviar(p.getNome(), 20), p.getPreco(), p.getQuantidadeEmEstoque());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto com estoque <= " + limite + ".");
    }

    // soma total (preco * quantidade)
    public double calcularValorTotalEstoque() {
        double total = 0.0;
        for (Produto p : produtos.values()) {
            total += p.getPreco() * p.getQuantidadeEmEstoque();
        }
        return total;
    }

    // utilitário para não quebrar o layout ao listar
    private String abreviar(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 1) + "…";
    }
}
