package modelo;

public class Funcionario extends Cliente {

	private int id_Funcionario;
	private String nomeFuncionario;
	private String login;
	private String senha;
	private String cpf;
	private String celular;
	private String email_Funcionario;

	public String getEmail_Funcionario() {
		return email_Funcionario;
	}

	public void setEmail_Funcionario(String email_Funcionario) {
		if (email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {

			this.email_Funcionario = email_Funcionario;

		} else {

			javax.swing.JOptionPane.showMessageDialog(null,

					"E-mail inválido. Deve conter '@' e um domínio válido.",

					"Erro de cadastro",

					javax.swing.JOptionPane.ERROR_MESSAGE);

			throw new IllegalArgumentException("E-mail inválido.");

		}

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf.matches("\\d{11}")) {

			this.cpf = cpf;

		} else {

			javax.swing.JOptionPane.showMessageDialog(null,

					"CPF inválido. Deve ter 11 dígitos numéricos.",

					"Erro de cadastro",

					javax.swing.JOptionPane.ERROR_MESSAGE);

			throw new IllegalArgumentException("CPF inválido.");

		}
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		if (celular.matches("\\d{11}")) {

			this.celular = celular;

		} else {

			javax.swing.JOptionPane.showMessageDialog(null,

					"Celular inválido. Deve ter 9 dígitos numéricos.",

					"Erro de cadastro",

					javax.swing.JOptionPane.ERROR_MESSAGE);

			throw new IllegalArgumentException("Celular inválido.");

		}
	}

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

}
