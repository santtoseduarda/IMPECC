 package modelo;

public class Funcionario extends Cliente {

	private int id_Funcionario;
	private String nomeFuncionario;
	private String login;
	private String senha;
	private String cpf;
	private String celular;
	private String email_Funcionario;
	public int getId_Funcionario() {
		return id_Funcionario;
	}
	public void setId_Funcionario(int id_Funcionario) {
		this.id_Funcionario = id_Funcionario;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail_Funcionario() {
		return email_Funcionario;
	}
	public void setEmail_Funcionario(String email_Funcionario) {
		this.email_Funcionario = email_Funcionario;
	}

}
