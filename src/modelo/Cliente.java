package modelo;

import java.time.LocalDate;

public class Cliente {

	//coloca o id 
	
	
	private String nomeCliente;
	private String cpf_Cliente;
	private String email;
	private char telefone;
	private LocalDate dataNasc;
	
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
	public char getTelefone() {
		return telefone;
	}
	public void setTelefone(char telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	
}
