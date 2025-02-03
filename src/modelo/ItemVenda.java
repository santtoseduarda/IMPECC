package modelo;

public class ItemVenda {
    private Produto produto;
    private int quantidade;
    

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
        getPrecoTotal();
    }

	public float getPrecoTotal() {
		return produto.getPreco()*quantidade;
	}

public float getprecoProduto() {
	return produto.getPreco();
}

public void setPrecoTotal(double valorTotal) {
	// TODO Auto-generated method stub
	
}
    


	
}
