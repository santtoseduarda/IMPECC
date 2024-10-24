package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Funcionario;
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

		String inserir = "INSERT INTO produtos (codBarra, nome_Produto, tamanho, genero, preco, qntd_Estoque, fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			pst.setInt(1, p.getCodBarra());
			pst.setString(2, p.getNomeProduto());
			pst.setString(3, p.getTamanho());
			pst.setString(4, p.getGenero());
			pst.setDouble(5, p.getPreco());
			pst.setLong(6, p.getQtdEstoque());
			pst.setString(7, p.getFornecedor());
			pst.executeUpdate();
			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public ArrayList<Produto> buscarProdLupa(String campo, String valor) {

		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

		try {

			String sql = "SELECT * FROM produtos";
			PreparedStatement pst = conn.prepareStatement(sql);

			if (campo.isEmpty() == false) {
				sql = "SELECT * FROM produtos WHERE " + campo + " LIKE ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + valor + "%");
			}
			ResultSet rs = pst.executeQuery();

			// Iterando sobre o resultado e adicionando à lista de funcionários
			while (rs.next()) {

				Produto p = new Produto();
				
                p.setId_Produto(rs.getInt("id_Produto"));
                p.setNomeProduto(rs.getString("nome_Produto"));
                p.setTamanho(rs.getString("tamanho"));
                p.setGenero(rs.getString("genero"));
                p.setPreco(rs.getFloat("preco"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setQtdEstoque(rs.getInt("qntd_Estoque"));

				listaProdutos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProdutos;

	}
	
	public boolean alterarProduto(Produto p) {
        String sql = "UPDATE produtos SET nome_Produto = ?, tamanho = ?, genero = ?, preco = ?, qntd_Estoque = ?, fornecedor = ? WHERE id_Produto = ?";
        
        try  {
        	
        	PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getNomeProduto());
            pst.setString(2, p.getTamanho());
            pst.setString(3, p.getGenero());
            pst.setDouble(4, p.getPreco());
            pst.setLong(5, p.getQtdEstoque());
            pst.setString(6, p.getFornecedor());
            pst.setInt(7, p.getId_Produto());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean excluirProdutos(int id_Produto) {
		// TODO Auto-generated method stub

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

	public Produto buscarProdutos(int id_Produto) {
		String mostrarDados = "SELECT * FROM produtos WHERE id_Produto = ?";
		Produto p = null;
		
		try {
			pst = conn.prepareStatement(mostrarDados);
	        pst.setInt(1, id_Produto); 

	        ResultSet rs = pst.executeQuery(); 

	        if (rs.next()) { 
	        	
	            p = new Produto();
	            p.setId_Produto(rs.getInt("id_Produto"));
                p.setNomeProduto(rs.getString("nome_Produto"));
                p.setTamanho(rs.getString("tamanho"));
                p.setGenero(rs.getString("genero"));
                p.setPreco(rs.getFloat("preco"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setQtdEstoque(rs.getInt("qntd_Estoque"));
                
	            return p;
	        }

			pst.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}



}
