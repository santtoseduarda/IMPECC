package modelo;

import java.util.ArrayList;

public class Venda {
    private int idVenda;
    private ArrayList<ItemVenda> carrinho;
    private double valorTotal;
    private String Mtd_Pagamento;
    private int idCliente;
    private int idFuncionario;

    public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getMtd_Pagamento() {
		return Mtd_Pagamento;
	}

	public void setMtd_Pagamento(String mtd_Pagamento) {
		Mtd_Pagamento = mtd_Pagamento;
	}

	public Venda() {
		carrinho = new ArrayList<>();
        valorTotal = 0.0;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public ArrayList<ItemVenda> getItensVenda() {
        return carrinho;
    }

    public void setItensVenda(ArrayList<ItemVenda> carrinho) {
        this.carrinho = carrinho;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarItem(ItemVenda item) {
    	carrinho.add(item);
        valorTotal += item.getPrecoTotal();
    }

	public String getCodProduto() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getQuantidade() {
		// TODO Auto-generated method stub
		return 0;
	}
}
