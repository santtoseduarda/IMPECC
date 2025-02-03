package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BancodeDados.Conexao;
import BancodeDados.ConexaoBanco;
import modelo.Cliente;
import modelo.ItemVenda;
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

	        // Inserir venda - removido id_Funcionario
	        String inserirVenda = "INSERT INTO vendas (Total, Mtd_Pagamento, id_Cliente, DataCompra) VALUES (?, ?, ?, ?)";
	        pstVenda = conn.prepareStatement(inserirVenda, Statement.RETURN_GENERATED_KEYS);
	        pstVenda.setDouble(1, calcularTotal(carrinho));
	        pstVenda.setString(2, v.getMtd_Pagamento());
	        pstVenda.setInt(3, v.getIdCliente());
	        pstVenda.setDate(4, v.getDataCompra());
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
	
	public ArrayList<Venda> buscarVendaLupa(String campo, String valor) {

	    ArrayList<Venda> listaVendas = new ArrayList<>();
	    Connection conn = ConexaoBanco.getConexaoMySQL();

	    try {
	        // 1. Se o campo for "nome_Cliente", buscamos primeiro o id_Cliente
	        String sql;
	        if (campo.equals("nome_Cliente") && !valor.isEmpty()) {
	            // 2. Primeiro, buscamos o id_Cliente pelo nome
	            sql = "SELECT id_Cliente FROM clientes WHERE nome_Cliente LIKE ?";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setString(1, "%" + valor + "%");
	            ResultSet rs = pst.executeQuery();

	            // 3. Se houver resultados (clientes encontrados), pegamos os ids e fazemos a busca na tabela vendas
	            while (rs.next()) {
	                int idCliente = rs.getInt("id_Cliente");

	                // 4. Agora, buscamos as vendas associadas a esse id_Cliente
	                String sqlVendas = "SELECT v.id_Venda, v.id_Cliente, v.dataCompra, c.nome_Cliente "
	                                  + "FROM vendas v "
	                                  + "JOIN clientes c ON v.id_Cliente = c.id_Cliente "
	                                  + "WHERE v.id_Cliente = ?";
	                PreparedStatement pstVendas = conn.prepareStatement(sqlVendas);
	                pstVendas.setInt(1, idCliente);
	                ResultSet rsVendas = pstVendas.executeQuery();

	                // 5. Iterando sobre os resultados e adicionando à lista de Vendas
	                while (rsVendas.next()) {
	                    Venda v = new Venda();
	                    v.setIdVenda(rsVendas.getInt("id_Venda"));
	                    v.setIdCliente(rsVendas.getInt("id_Cliente"));
	                    v.setDataCompra(rsVendas.getDate("dataCompra"));
	                    v.setNomeCliente(rsVendas.getString("nome_Cliente"));  // O nome do cliente vem da tabela clientes
	                    listaVendas.add(v);
	                }
	            }

	        } else {
	            // 6. Caso o filtro seja por id_Venda, id_Cliente ou dataCompra
	            sql = "SELECT v.id_Venda, v.id_Cliente, v.dataCompra, c.nome_Cliente "
	                 + "FROM vendas v "
	                 + "JOIN clientes c ON v.id_Cliente = c.id_Cliente "
	                 + "WHERE v." + campo + " LIKE ?";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setString(1, "%" + valor + "%");
	            ResultSet rs = pst.executeQuery();

	            // 7. Iterando sobre o resultado e adicionando à lista de Vendas
	            while (rs.next()) {
	                Venda v = new Venda();
	                v.setIdVenda(rs.getInt("id_Venda"));
	                v.setIdCliente(rs.getInt("id_Cliente"));
	                v.setDataCompra(rs.getDate("dataCompra"));
	                v.setNomeCliente(rs.getString("nome_Cliente"));
	                listaVendas.add(v);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaVendas;
	}




	
	public Cliente buscarCliente(String cpf) {
	    String sql = "SELECT * FROM clientes WHERE cpf_Cliente = ?"; // Consulta SQL para buscar o cliente pelo CPF
	    Cliente cliente = null; // Inicializa o objeto Cliente como null

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, cpf); // Define o parâmetro CPF na consulta

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) { // Verifica se há resultados
	                cliente = new Cliente(); // Cria uma nova instância de Cliente
	                cliente.setId_Cliente(rs.getInt("id_Cliente")); // Define o ID do cliente
	                cliente.setNomeCliente(rs.getString("nome_Cliente")); // Define o nome do cliente
	                cliente.setDataNasc(rs.getString("data_Nasc")); // Define a data de nascimento
	                cliente.setCpf_Cliente(rs.getString("cpf_Cliente")); // Define o CPF do cliente
	                cliente.setTelefone(rs.getString("telefone_Cliente")); // Define o telefone do cliente
	                cliente.setEmail(rs.getString("email_Cliente")); // Define o e-mail do cliente
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Trata exceções de SQL
	        System.out.println("Erro ao buscar cliente: " + e.getMessage());
	    }

	    return cliente; // Retorna o cliente encontrado (ou null se não encontrado)
	}
	
	
	public List<Venda> listarVendas() {
	    List<Venda> vendas = new ArrayList<>();
	    String sql = "SELECT v.id_venda, v.total, v.DataCompra, c.nome_Cliente " +
	                 "FROM vendas v " +
	                 "JOIN clientes c ON v.id_cliente = c.id_Cliente"; // Fazendo a junção com clientes

	    try (Connection conn = Conexao.getConexao();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Venda venda = new Venda();
	            venda.setIdVenda(rs.getInt("id_venda"));
	            venda.setValorTotal(rs.getDouble("total"));
	            
	            venda.setDataCompra(rs.getDate("DataCompra")); // Obtendo a data da compra
	            venda.setNomeCliente(rs.getString("nome_Cliente")); // Obtendo o nome do cliente
	            vendas.add(venda);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return vendas;
	}

	
}