package modelo;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    private double precoTotal;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularPrecoTotal();
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    private void calcularPrecoTotal() {
        this.precoTotal = quantidade * produto.getPreco();
    }
}
