package att05;

public class Produto {
    private final String sku;
    private final String nome;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String sku, String nome, double preco, int quantidadeEmEstoque) {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("SKU inválido.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido.");
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        if (quantidadeEmEstoque < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.sku = sku.trim();
        this.nome = nome.trim();
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getSku() { return sku; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeEmEstoque() { return quantidadeEmEstoque; }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.preco = preco;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    @Override
    public String toString() {
        return String.format("Produto [SKU=%s, Nome=%s, Preço=R$%.2f, Qtd=%d]",
                sku, nome, preco, quantidadeEmEstoque);
    }
}
