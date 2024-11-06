package modelo;

import java.time.LocalDate;

public class Cliente {

	
	
	private int id_Cliente;
	private String nomeCliente;
	private String cpf_Cliente;
	private String email;
	private String telefone;
	private String dataNasc;
	
	public int getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpf_Cliente() {
		return cpf_Cliente;
	}
	public void setCpf_Cliente(String cpf_Cliente) {
		this.cpf_Cliente = cpf_Cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	
}
