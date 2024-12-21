package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;

public class VendaDAO {

	Statement stm1 = null;
	int res1;
	PreparedStatement pst = null;
	Connection conn = ConexaoBanco.getConexaoMySQL();

	private static ArrayList<Venda> tabelaVenda;
	private static VendaDAO instancia;

	public static VendaDAO getInstancia() {

		if (instancia == null) {
			instancia = new VendaDAO();
			tabelaVenda = new ArrayList<>();
		}
		return instancia;
	}

	public boolean inserir(Venda v) {

		String inserir = "INSERT INTO vendas (Total, Mtd_Pagamento, id_Cliente, id_Funcionario) VALUES (?, ?)";


		try {
			pst = conn.prepareStatement(inserir);

			pst.setDouble(1, v.getValorTotal());
			pst.setString(2, v.getMtd_Pagamento());
			pst.setInt(3, v.getIdCliente());
			pst.setInt(4, v.getIdFuncionario());
			// pst.setString(3, v.getCliente().getCpf_Cliente());
			// pst.setString(4, v.getFuncionario().getCpf());

			// nao sei se precisa
			// cpf do cliente que comrpu
			// cpd do funcionario que fez a compra

			pst.executeUpdate();
			return true;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return false;
	}
	

	// ALTERA UMA VENDA
	public boolean alterarVenda(Venda v) {
		String sql = "UPDATE vendas SET total = ?, mtd_pagamento = ?, id_cliente = ?, id_funcionario = ?, id_produto = ? WHERE id_venda = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, v.getValorTotal());
			pst.setString(2, v.getMtd_Pagamento());
			pst.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}


	// CALCULA O TOTAL
	
	public double calcularTotal(ArrayList<ItemVenda> carrinho) {
	    // Validar se o carrinho está vazio
	    if (carrinho == null || carrinho.isEmpty()) {
	        return 0.0; // Retorna 0 caso não existam itens no carrinho
	    }

	    double total = 0.0;

	    // Itera por cada item e soma os valores totais
	    for (ItemVenda item : carrinho) {
	        if (item != null && item.getProduto() != null) {
	            double precoUnitario = item.getProduto().getPreco(); // Obtém o preço do produto
	            int quantidade = item.getQuantidade(); // Obtém a quantidade do item
	            total += precoUnitario * quantidade; // Soma o total (preço * quantidade)
	        }
	    }

	    return total; // Retorna o total calculado
	}


	// EXCLUIR VENDA
	public boolean excluirVendas(int idVenda) {
		String sql = "DELETE FROM vendas WHERE id_venda = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, idVenda); // para pegar o id
			int linhasAfetadas = pst.executeUpdate(); // faz o delete

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

			return false;
		}
	}
	
	public boolean finalizarVenda(Venda v, ArrayList<ItemVenda> carrinho) {
	    Connection conn = null;
	    PreparedStatement pstVenda = null;
	    PreparedStatement pstItemVenda = null;
	    PreparedStatement pstAtualizarEstoque = null;

	    try {
	        conn = ConexaoBanco.getConexaoMySQL();
	        conn.setAutoCommit(false); // Iniciar transação

	        // Inserir venda
	        String inserirVenda = "INSERT INTO vendas (Total, Mtd_Pagamento, id_Cliente, id_Funcionario) VALUES (?, ?, ?, ?)";
	        pstVenda = conn.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS);
	        pstVenda.setDouble(1, calcularTotal(carrinho));
	        pstVenda.setString(2, v.getMtd_Pagamento());
	        pstVenda.setInt(3, v.getIdCliente());
	        pstVenda.setInt(4, v.getIdFuncionario());
	        pstVenda.executeUpdate();

	        // Obter o ID da venda gerado
	        ResultSet rs = pstVenda.getGeneratedKeys();
	        int idVenda = 0;
	        if (rs.next()) {
	            idVenda = rs.getInt(1);
	        }

	        // Inserir itens da venda
	        String inserirItemVenda = "INSERT INTO venda_produtos (id_venda, id_produto, qntd, preco) VALUES (?, ?, ?, ?)";
	        pstItemVenda = conn.prepareStatement(inserirItemVenda);

	        String atualizarEstoque = "UPDATE produtos SET qntd_Estoque = qntd_Estoque - ? WHERE id_Produto = ?";
	        pstAtualizarEstoque = conn.prepareStatement(atualizarEstoque);

	        for (ItemVenda item : carrinho) {
	            // Inserir item
	            pstItemVenda.setInt(1, idVenda);
	            pstItemVenda.setInt(2, item.getProduto().getId_Produto());
	            pstItemVenda.setInt(3, item.getQuantidade());
	            pstItemVenda.setDouble(4, item.getPrecoTotal());
	            pstItemVenda.addBatch();

	            // Atualizar estoque
	            pstAtualizarEstoque.setInt(1, item.getQuantidade());
	            pstAtualizarEstoque.setInt(2, item.getProduto().getId_Produto());
	            pstAtualizarEstoque.addBatch();
	        }

	        pstItemVenda.executeBatch();
	        pstAtualizarEstoque.executeBatch();

	        conn.commit(); // Confirmar transação
	        return true;

	    } catch (SQLException e) {
	        try {
	            if (conn != null) {
	                conn.rollback(); // Reverter alterações em caso de erro
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pstVenda != null) pstVenda.close();
	            if (pstItemVenda != null) pstItemVenda.close();
	            if (pstAtualizarEstoque != null) pstAtualizarEstoque.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public void salvarVenda(int idCliente, ArrayList<ItemVenda> itens, double totalVenda) throws SQLException {
	    String sqlVenda = "INSERT INTO vendas (id_cliente, total) VALUES (?, ?)";
	    String sqlItens = "INSERT INTO itens_venda (id_venda, id_produto, quantidade, preco_total) VALUES (?, ?, ?, ?)";

	    try (Connection conn = ConexaoBanco.getConexaoMySQL();
	         PreparedStatement psVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
	         PreparedStatement psItens = conn.prepareStatement(sqlItens)) {

	        // Inserir a venda
	        psVenda.setInt(1, idCliente);
	        psVenda.setDouble(2, totalVenda);
	        psVenda.executeUpdate();

	        ResultSet rs = psVenda.getGeneratedKeys();
	        if (rs.next()) {
	            int idVenda = rs.getInt(1);

	            // Inserir os itens da venda
	            for (ItemVenda item : itens) {
	                psItens.setInt(1, idVenda);
	                psItens.setInt(2, item.getProduto().getId_Produto());
	                psItens.setInt(3, item.getQuantidade());
	                psItens.setDouble(4, item.getPrecoTotal());
	                psItens.addBatch();
	            }
	            psItens.executeBatch();
	        }
	    }
	}

	
	public Cliente buscarCliente(String cpf) {
		String sql = "SELECT c.*  FROM clientes c WHERE cpf_Cliente = ?";
		Cliente c =null;
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cpf);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				
				c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				c.setNomeCliente(rs.getString("nome_Cliente"));
				c.setDataNasc(rs.getString("data_Nasc"));
				c.setCpf_Cliente(rs.getString("cpf_Cliente"));
				c.setTelefone(rs.getString("telefone_Cliente"));
				c.setEmail(rs.getString("email_Cliente"));
			}
				
		
		
	}catch (SQLException e1) {
		e1.printStackTrace();
	}
		return c;
	}
}