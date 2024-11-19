package modelo;

public class Venda {
	
	// chave estrangeira: id_Funcionário, id_Cliente
	// id_produto deveria ser uma lista de produtos??
	
	private int id_Venda; 	// id automático
	private Float total;
	private String mtd_Pagamento;
	private Cliente cliente;
	private Funcionario funcionario;

	
	public int getId_Venda() {
		return id_Venda;
	}
	public void setId_Venda(int id_Venda) {
		this.id_Venda = id_Venda;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getMtd_Pagamento() {
		return mtd_Pagamento;
	}
	public void setMtd_Pagamento(String mtd_Pagamento) {
		this.mtd_Pagamento = mtd_Pagamento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	
}
