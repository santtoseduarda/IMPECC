package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.Conexao;
import BancodeDados.ConexaoBanco;
import modelo.Funcionario;

public class FuncionarioDAO {
	
	/*public Connection conexao = null;
	private PreparedStatement pst =null;
	private ResultSet rs = null;*/
	
	// conexao = ConexaoBanco.getConexaoMySQL();
	
	Statement stm1 = null; // permite fazer consultas no banco de dados
	int res1;
	
	
	// incluir, listar

	private static ArrayList<Funcionario> tabelaFuncionario;
	private static FuncionarioDAO instancia;

	// instancia
	public static FuncionarioDAO getInstancia() {

		if (instancia == null) {
			instancia = new FuncionarioDAO();
			tabelaFuncionario = new ArrayList<>();

			// popularTabelaFuncionario();
		}

		return instancia;
	}

	public boolean inserir(Funcionario f) {
		String inserir = "INSERT INTO funcionarios ('nome_Funcionario', 'email_Funcionario', 'login', 'senha', 'celular', 'cpf') VALUES (?, ?, ?, ?, ?, ?";
		pst = conexao.prepareStatement(inserir);
		
		// pst.setString(1, nomeCompleto);
		
		pst.executeUpdate();
		
		Connection conn = ConexaoBanco.getConexaoMySQL(); // fazer a conexao com o BD

		try {
			pst = conexao.prepareStatement(inserir);
			stm1 = (Statement) conn.createStatement(); // traz o resultados da consulta SQL
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static void main(String[] args) {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		// Exemplo de como inserir um funcionário
		Funcionario novoFuncionario = new Funcionario();
		if (funcionarioDAO.inserir(novoFuncionario)) {
			System.out.println("Funcionário inserido com sucesso.");
		} else {
			System.out.println("Falha ao inserir funcionário.");
		}

	}
}
