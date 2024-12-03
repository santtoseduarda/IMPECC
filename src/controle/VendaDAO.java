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
	
	// busca pela lupa

	public ArrayList<Venda> buscarVendasLupa(String campo, String valor) {
	    ArrayList<Venda> listaVendas = new ArrayList<>();

	    try {
	    	String sql = "SELECT * FROM vendas WHERE " + campo + " LIKE ?";
	        
	        if (!campo.isEmpty()) {
	            sql += " WHERE " + campo + " LIKE ?";
	        } else {
		    	sql = "SELECT * FROM vendas";

	        }

	        PreparedStatement pst = conn.prepareStatement(sql);


	        ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Venda v = new Venda();
				v.setIdVenda(rs.getInt("id_venda"));
				v.setValorTotal(rs.getFloat("Total"));
				v.setMtd_Pagamento(rs.getString("mtd_Pagamento"));

				Cliente c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				//v.setCliente(c);

				Funcionario f = new Funcionario();
				f.setId_Funcionario(rs.getInt("id_Funcionario"));
				//v.setIdFuncionario(f);

				// Aqui você pode buscar os itens da venda com base no ID da venda
				ArrayList<ItemVenda> listaItens = buscarItensVendaPorIdVenda(v.getIdVenda());
				v.setItensVenda(listaItens);

				listaVendas.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVendas;
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

	// ADICIONA NO CARRINHO
	public boolean adicionarCarrinho(ItemVenda item, ArrayList<ItemVenda> carrinho) {
		for (ItemVenda i : carrinho) {
			if (i.getProduto().getId_Produto() == item.getProduto().getId_Produto()) {
				i.setQuantidade(i.getQuantidade() + item.getQuantidade());
				i.setPrecoTotal(i.getPrecoTotal() + item.getProduto().getPreco() * item.getQuantidade());
				return true;
			}
		}
		
		carrinho.add(item);
		return true;
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

	// BUSCA VENDAS
	public Venda buscarVendas(int idVenda) {
		String sql = "SELECT v.*,  FROM vendas v JOIN clientes ON clientes.id_Cliente = vendas.id_Cliente JOIN funcionarios ON funcionarios.id_Funcionario = vendas.id_Funcionario WHERE id_venda = ?";
		Venda v = null;

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idVenda);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				v = new Venda();

				v.setIdVenda(idVenda);
				v.setMtd_Pagamento(rs.getString("mtd_Pagamento"));
				v.setValorTotal(idVenda);

				ArrayList<ItemVenda> lista = buscarItensVendaPorIdVenda(rs.getInt("idVenda"));
				v.setItensVenda(lista);

				Cliente c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				c.setNomeCliente(rs.getString("nome_Cliente"));
				c.setDataNasc(rs.getString("data_Nasc"));
				c.setCpf_Cliente(rs.getString("cpf_Cliente"));
				c.setTelefone(rs.getString("telefone_Cliente"));
				c.setEmail(rs.getString("email_Cliente"));
				//v.setCliente(c);

				Funcionario f = new Funcionario();
				f.setId_Funcionario(rs.getInt("id_Funcionario"));
				f.setNomeFuncionario(rs.getString("nome_Funcionario"));
				f.setEmail_Funcionario(rs.getString("email_Funcionario"));
				f.setCpf(rs.getString("cpf"));
				f.setCelular(rs.getString("celular"));
				f.setLogin(rs.getString("login"));
				//v.setFuncionario(f);

				return v;
			}

			pst.executeQuery();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	// BUSCA ITEM DA VENDA PELO ID DA VENDA
	private ArrayList<ItemVenda> buscarItensVendaPorIdVenda(int idVenda) {
		String mostrarDados = "SELECT *, venda_produtos.preco as preco_venda, produtos.preco as preco_produto FROM venda_produtos JOIN produtos ON venda_produtos.id_Produto = produtos.id_Produto WHERE id_venda = ?";
		ArrayList<ItemVenda> lista = new ArrayList<ItemVenda>();

		try {
			pst = conn.prepareStatement(mostrarDados);
			pst.setInt(1, idVenda);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				ItemVenda v = new ItemVenda();

				Produto p = new Produto();
				p.setId_Produto(rs.getInt("id_Produto"));
				p.setNomeProduto(rs.getString("nome_Produto"));
				p.setTamanho(rs.getString("tamanho"));
				p.setGenero(rs.getString("genero"));
				p.setPreco(rs.getFloat("preco_produto"));
				// p.setFornecedor(rs.getFornecedor("fornecedor"));
				p.setQtdEstoque(rs.getInt("qntd_Estoque"));
				
				v.setPrecoTotal(rs.getFloat("preco_venda"));
				v.setQuantidade(rs.getInt("qntd"));
				v.setProduto(p);

				lista.add(v);
			}

			return lista;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}

}