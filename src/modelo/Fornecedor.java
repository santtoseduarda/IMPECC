package modelo;

public class Fornecedor extends Cliente{
	
	private int ID;
	private String nome_Fornecedor;
	private String CNPJ;
	private String telefone_Fornecedor;
	private String email_Fornecedor;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome_Fornecedor() {
		return nome_Fornecedor;
	}
	public void setNome_Fornecedor(String nome_Fornecedor) {
		this.nome_Fornecedor = nome_Fornecedor;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getTelefone_Fornecedor() {
		return telefone_Fornecedor;
	}
	public void setTelefone_Fornecedor(String telefone_Fornecedor) {
		this.telefone_Fornecedor = telefone_Fornecedor;
	}
	public String getEmail_Fornecedor() {
		return email_Fornecedor;
	}
	public void setEmail_Fornecedor(String email_Fornecedor) {
		this.email_Fornecedor = email_Fornecedor;
	}
	
	
	
}