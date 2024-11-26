package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Fornecedor;
import modelo.Produto;

public class ProdutoDAO {
	Statement stm1 = null;
	int res1;
	PreparedStatement pst = null;
	Connection conn = ConexaoBanco.getConexaoMySQL();

	private static ArrayList<Produto> tabelaProduto;
	private static ProdutoDAO instancia;

	public static ProdutoDAO getInstancia() {

		if (instancia == null) {
			instancia = new ProdutoDAO();
			tabelaProduto = new ArrayList<>();
		}
		return instancia;
	}

	public boolean inserir(Produto p) {

		String inserir = "INSERT INTO produtos (nome_Produto, tamanho, genero, preco, qntd_Estoque, fornecedor) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			pst.setString(1, p.getNomeProduto());
			pst.setString(2, p.getTamanho());
			pst.setString(3, p.getGenero());
			pst.setDouble(4, p.getPreco());
			pst.setLong(5, p.getQtdEstoque());
			pst.setInt(6, p.getFornecedor().getID_fornecedor());
			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public Produto buscarProdutos(int id_Produto) {
		String sql = "SELECT p.*, f.nome_Fornecedor FROM produtos p JOIN fornecedores f ON p.fornecedor = f.id_Fornecedor WHERE p.id_Produto = ?";
		Produto p = null;

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_Produto);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				p = new Produto();
				p.setId_Produto(rs.getInt("id_Produto"));
				p.setNomeProduto(rs.getString("nome_Produto"));
				p.setTamanho(rs.getString("tamanho"));
				p.setGenero(rs.getString("genero"));
				p.setPreco(rs.getFloat("preco"));
				p.setQtdEstoque(rs.getInt("qntd_Estoque"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setID_fornecedor(rs.getInt("fornecedor"));
				fornecedor.setNome_Fornecedor(rs.getString("nome_Fornecedor"));
				p.setFornecedor(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	public ArrayList<Produto> buscarProdLupa(String campo, String valor) {
		ArrayList<Produto> listaProdutos = new ArrayList<>();

		try {
			String sql = "SELECT p.*, f.nome_Fornecedor FROM produtos p JOIN fornecedores f ON p.fornecedor = f.id_Fornecedor";

			if (!campo.isEmpty()) {
				sql += " WHERE " + campo + " LIKE ?";
			}

			PreparedStatement pst = conn.prepareStatement(sql);

			if (!campo.isEmpty()) {
				pst.setString(1, "%" + valor + "%");
			}

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Produto p = new Produto();
				p.setId_Produto(rs.getInt("id_Produto"));
				p.setNomeProduto(rs.getString("nome_Produto"));
				p.setTamanho(rs.getString("tamanho"));
				p.setGenero(rs.getString("genero"));
				p.setPreco(rs.getFloat("preco"));
				p.setQtdEstoque(rs.getInt("qntd_Estoque"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setID_fornecedor(rs.getInt("fornecedor"));
				fornecedor.setNome_Fornecedor(rs.getString("nome_Fornecedor"));
				p.setFornecedor(fornecedor);

				listaProdutos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	public boolean alterarProduto(Produto p) {
		String sql = "UPDATE produtos SET nome_Produto = ?, tamanho = ?, genero = ?, preco = ?, qntd_Estoque = ?, fornecedor = ? WHERE id_Produto = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getNomeProduto());
			pst.setString(2, p.getTamanho());
			pst.setString(3, p.getGenero());
			pst.setDouble(4, p.getPreco());
			pst.setLong(5, p.getQtdEstoque());
			pst.setInt(6, p.getFornecedor().getID_fornecedor());
			pst.setInt(7, p.getId_Produto());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Produto alterado com sucesso.");
				return true;
			} else {
				System.out.println("Nenhuma linha foi alterada. Verifique o ID ou os valores fornecidos.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirProdutos(int id_Produto) {

		String sql = "DELETE FROM produtos WHERE id_Produto = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, id_Produto); // pega o id
			int linhasAfetadas = pst.executeUpdate(); // faz o delete

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

			return false;
		}
	}

	public ArrayList<Produto> buscarTodosProdutos() throws SQLException {
		ArrayList<Produto> produtos = new ArrayList<>();
		String sql = "SELECT id_Produto, nome_Produto FROM produtos";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId_Produto(rs.getInt("id_Produto"));
				produto.setNomeProduto(rs.getString("nome_Produto"));
				produtos.add(produto);

			}
		}
		return produtos;
	}

}