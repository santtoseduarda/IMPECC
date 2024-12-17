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

	// FINALIZAR VENDA
	public boolean finalizarVenda(Venda v, ArrayList<ItemVenda> carrinho) {
		try {
			// Inserir venda na tabela 'vendas'
			String inserirVenda = "INSERT INTO vendas (Total, Mtd_Pagamento, id_Cliente, id_Funcionario) VALUES (?, ?, ?, ?)";
			pst = conn.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, calcularTotal(carrinho));
			pst.setString(2, v.getMtd_Pagamento());
			pst.setInt(3, v.getIdCliente());
			pst.setInt(4, v.getIdFuncionario());
			pst.executeUpdate();

			// Obter o ID da venda gerado automaticamente
			ResultSet rs = pst.getGeneratedKeys();
			int idVenda = 0;
			if (rs.next()) {
				idVenda = rs.getInt(1);
			}
			
			String inserirItemVenda = "INSERT INTO venda_produtos (id_venda, id_produto, qntd, preco) VALUES (?, ?, ?, ?)";
	        for (ItemVenda item : carrinho) {
	            pst = conn.prepareStatement(inserirItemVenda);
	            pst.setInt(1, idVenda);
	            pst.setInt(2, item.getProduto().getId_Produto());
	            pst.setInt(3, item.getQuantidade());
	            pst.setDouble(4, item.getPrecoTotal());
	            pst.executeUpdate();
	   

			String atualizarEstoque = "UPDATE produtos SET qntd_Estoque = qntd_Estoque - ? WHERE id_Produto = ?";
			pst = conn.prepareStatement(atualizarEstoque);
			pst.setInt(1, item.getQuantidade());
			pst.setInt(2, item.getProduto().getId_Produto());
			pst.executeUpdate();
	        }
	        

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}


	// CALCULA O TOTAL
	public double calcularTotal(ArrayList<ItemVenda> carrinho) {
		double total = 0.0;
		for (ItemVenda item : carrinho) {
			total += item.getPrecoTotal();
		}
		return total;
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