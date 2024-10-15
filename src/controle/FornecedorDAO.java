package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Fornecedor;
import modelo.Funcionario;

public class FornecedorDAO {

	Statement stm1 = null; // permite fazer consultas no banco de dados
	int res1;
	PreparedStatement pst = null;
	Connection conn = ConexaoBanco.getConexaoMySQL(); // Fazer a conexão com o BD

	// incluir, listar

	private static ArrayList<Fornecedor> tabelaFornecedor;
	private static FornecedorDAO instancia;

	// instancia
	public static FornecedorDAO getInstancia() {

		if (instancia == null) {
			instancia = new FornecedorDAO();
			tabelaFornecedor = new ArrayList<>();

			// popularTabelaFornecedores();
		}

		return instancia;
	}

	public boolean inserir(Fornecedor fr) {

		Connection conn = ConexaoBanco.getConexaoMySQL(); // Fazer a conexão com o BD

		String inserir = "INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES (?, ?, ?, ?)";

		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(inserir);

			// Setar os valores nos placeholders "?"
			pst.setString(1, fr.getNome_Fornecedor()); // ajuste conforme os métodos get do seu modelo Fornecedor
			pst.setString(2, fr.getCNPJ());
			pst.setString(3, fr.getTelefone_Fornecedor());
			pst.setString(4, fr.getEmail_Fornecedor());

			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public ArrayList<Fornecedor> buscarFornecedores(String campo, String valor) {

		ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
		Statement stmt1 = null;

		Connection conn = ConexaoBanco.getConexaoMySQL();

		try {

			String sql = "SELECT * FROM fornecedor";
			PreparedStatement pst = conn.prepareStatement(sql);
			if (campo.isEmpty() == false) {
				sql = "SELECT * FROM fornecedores WHERE " + campo + " LIKE ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + valor + "%"); // Usando % para permitir busca parcial
			}
			System.out.println(pst);
			ResultSet rs = pst.executeQuery();

			// Iterando sobre o resultado e adicionando à lista de fornecedores

			while (rs.next()) {
				Fornecedor fr = new Fornecedor();
				fr.setID_fornecedor(rs.getInt("id_Fornecedor"));
				fr.setNome_Fornecedor(rs.getString("nome_Fornecedor"));
				fr.setCNPJ(rs.getString("cnpj"));
				fr.setTelefone_Fornecedor(rs.getString("telefone_Fornecedor"));
				fr.setEmail_Fornecedor(rs.getString("email_Fornecedor"));
				listaFornecedores.add(fr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFornecedores;

	}

	public boolean excluirFornecedor(int idFornecedor) {
		// TODO Auto-generated method stub

		Connection conn = ConexaoBanco.getConexaoMySQL(); // Estabelecer conexão com o banco
		String excluirFornecedor = "DELETE FROM fornecedor WHERE id_Fornecedor = ?"; // SQL para excluir pelo ID

		try {
			PreparedStatement pst = conn.prepareStatement(excluirFornecedor);

			pst.setInt(1, idFornecedor); // pega o id
			int linhasAfetadas = pst.executeUpdate(); // faz o delete

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterarFornecedor(Fornecedor fornecedor) {
		String alterarFornecedor = "UPDATE fornecedor SET nome_Fornecedor = ?, email_fornecedor = ?, telefone_fornecedor = ? WHERE cnpj = ?";

		try {
			pst = conn.prepareStatement(alterarFornecedor);
			pst.setString(1, fornecedor.getNome_Fornecedor()); // ajuste conforme os métodos get do seu modelo Funcionario
			pst.setString(2, fornecedor.getEmail_Fornecedor());
			pst.setString(3, fornecedor.getTelefone_Fornecedor());
			pst.setString(4, fornecedor.getCNPJ());
			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public Fornecedor bucarFornecedor(int id_Fornecedor) {

		String mostrarDados = "SELECT * FROM fornecedor WHERE id_Fornecedor = ?";
		Fornecedor fornecedor = null;

		try {
			pst = conn.prepareStatement(mostrarDados);
			pst.setInt(1, id_Fornecedor);

			ResultSet rs = pst.executeQuery(); // Aqui você deve usar executeQuery()

			if (rs.next()) { // Se houver resultados, preencha o objeto Funcionario
				fornecedor = new Fornecedor();
				fornecedor.setID_fornecedor(rs.getInt("id_Fornecedor"));
				fornecedor.setNome_Fornecedor(rs.getString("nome_Fornecedor"));
				fornecedor.setEmail_Fornecedor(rs.getString("email_fornecedor"));
				fornecedor.setTelefone_Fornecedor(rs.getString("telefone_fornecedor"));
				fornecedor.setCNPJ(rs.getString("cnpj"));
				return fornecedor;
			}

			pst.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

}