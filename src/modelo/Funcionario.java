package modelo;

public class Funcionario extends Cliente{
	
	private int id_Funcionario;
	private String nomeFuncionario;
	private char login;
	private char senha;
	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public char getLogin() {
		return login;
	}
	public void setLogin(char login) {
		this.login = login;
	}
	public char getSenha() {
		return senha;
	}
	public void setSenha(char senha) {
		this.senha = senha;
	}
	
	

}
