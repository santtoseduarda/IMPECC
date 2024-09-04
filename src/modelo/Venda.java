package modelo;

public class Venda {
	
	// id automático
	// chave estrangeira: id_Funcionário, id_Cliente
	// id_produto deveria ser uma lista de produtos??

	private Float total;
	private String mtd_Pagamento;
	
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
	

}
