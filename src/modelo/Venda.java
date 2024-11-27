package modelo;

import java.util.ArrayList;

public class Venda {
    private int idVenda;
    private ArrayList<ItemVenda> itensVenda;
    private double valorTotal;

    public Venda() {
        itensVenda = new ArrayList<>();
        valorTotal = 0.0;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public ArrayList<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ArrayList<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionarItem(ItemVenda item) {
        itensVenda.add(item);
        valorTotal += item.getPrecoTotal();
    }
}
