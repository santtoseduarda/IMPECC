package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import BancodeDados.Conexao;
import BancodeDados.ConexaoBanco;
import modelo.Funcionario;
import visao.AlterarFuncionario;

public class FuncionarioDAO {

	Statement stm1 = null; // permite fazer consultas no banco de dados
	int res1;
	PreparedStatement pst = null;
	Connection conn = ConexaoBanco.getConexaoMySQL(); // Fazer a conexão com o BD

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

		String inserir = "INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, login, senha, celular, cpf) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			// Setar os valores nos placeholders "?"
			pst.setString(1, f.getNomeFuncionario()); // ajuste conforme os métodos get do seu modelo Funcionario
			pst.setString(2, f.getEmail_Funcionario());
			pst.setString(3, f.getLogin());
			pst.setString(4, f.getSenha());
			pst.setString(5, f.getCelular());
			pst.setString(6, f.getCpf());

			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public boolean verificarLogin(String login, String senha) {

		String verificacao = "SELECT * FROM funcionarios WHERE login = ? AND senha = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(verificacao);

			pst.setString(1, login);
			pst.setString(2, senha);
			ResultSet res = pst.executeQuery();

			// Se houver um resultado, o login é válido
			return res.next(); // Se houver pelo menos uma linha, o login está correto
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retorna falso em caso de erro
		}
	}

	public ArrayList<Funcionario> buscarFuncLupa(String campo, String valor) {

		ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		try {

			String sql = "SELECT * FROM funcionarios";
			PreparedStatement pst = conn.prepareStatement(sql);
			if (campo.isEmpty() == false) {
				sql = "SELECT * FROM funcionarios WHERE " + campo + " LIKE ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + valor + "%"); // Usando % para permitir busca parcial
			}
			ResultSet rs = pst.executeQuery();

			// Iterando sobre o resultado e adicionando à lista de funcionários
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setId_Funcionario(rs.getInt("id_Funcionario"));
				f.setNomeFuncionario(rs.getString("nome_Funcionario"));
				f.setEmail_Funcionario(rs.getString("email_Funcionario"));
				f.setCpf(rs.getString("cpf"));
				f.setCelular(rs.getString("celular"));
				f.setLogin(rs.getString("login"));
				listaFuncionarios.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFuncionarios;

	}

	public boolean excluirFuncionario(int idFuncionario) {
		// TODO Auto-generated method stub

		String sql = "DELETE FROM funcionarios WHERE id_Funcionario = ?"; // SQL para excluir pelo ID

		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, idFuncionario); // pega o id
			int linhasAfetadas = pst.executeUpdate(); // faz o delete

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

			return false;
		}
	}


	public boolean alterarFuncionario(Funcionario f) {
		String alterar = "UPDATE funcionarios SET nome_Funcionario = ?, email_Funcionario = ?, login = ?, senha = ?, celular = ? WHERE cpf = ?";

		try {
			pst = conn.prepareStatement(alterar);
			pst.setString(1, f.getNomeFuncionario()); // ajuste conforme os métodos get do seu modelo Funcionario
			pst.setString(2, f.getEmail_Funcionario());
			pst.setString(3, f.getLogin());
			pst.setString(4, f.getSenha());
			pst.setString(5, f.getCelular());
			pst.setString(6, f.getCpf());
			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public Funcionario buscarFuncionario(int id_Funcionario) {
		
		String mostrarDados = "SELECT * FROM funcionarios WHERE id_Funcionario = ?";
		Funcionario f = null;
		
		try {
			pst = conn.prepareStatement(mostrarDados);
	        pst.setInt(1, id_Funcionario); // Use o id_Funcionario passado como parâmetro

	        ResultSet rs = pst.executeQuery(); // Aqui você deve usar executeQuery()

	        if (rs.next()) { // Se houver resultados, preencha o objeto Funcionario
	            f = new Funcionario();
	            f.setId_Funcionario(rs.getInt("id_Funcionario")); // Adicione isso se necessário
	            f.setNomeFuncionario(rs.getString("nome_Funcionario"));
	            f.setEmail_Funcionario(rs.getString("email_Funcionario"));
	            f.setLogin(rs.getString("login"));
	            f.setSenha(rs.getString("senha"));
	            f.setCelular(rs.getString("celular"));
	            f.setCpf(rs.getString("cpf"));
	            return f;
	        }

			pst.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

		
	}
}
