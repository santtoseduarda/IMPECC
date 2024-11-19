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

	private static VendaDAO instancia;

	public static VendaDAO getInstancia() {

		if (instancia == null) {
			instancia = new VendaDAO();
			new ArrayList<>();
		}
		return instancia;
	}

	public boolean inserir(Venda v) {

		String inserir = "INSERT INTO vendas (Total, Mtd_Pagamento, id_Cliente, id_Funcionario) VALUES (?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			pst.setDouble(1, v.getTotal());
			pst.setString(2, v.getMtd_Pagamento());
			//pst.setString(3, v.getCliente().getCpf_Cliente());
			//pst.setString(4, v.getFuncionario().getCpf());

			// nao sei se precisa
			// cpf da cliente que comrpu
			// cpd do funcionario que fez a compra

			pst.executeUpdate();
			return true;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return false;
	}

	public ArrayList<Venda> buscarVendasLupa(String campo, String valor) {
		ArrayList<Venda> listaVendas = new ArrayList<>();

		try {
			String sql = "SELECT * FROM vendas";
			PreparedStatement pst = conn.prepareStatement(sql);

			if (campo.isEmpty() == false) {
				sql = "SELECT * FROM vendas WHERE " + campo + " LIKE ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + valor + "%");
			}
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Venda v = new Venda();

				v.setId_Venda(rs.getInt("id_venda"));
				v.setTotal(rs.getFloat("Total"));
				v.setMtd_Pagamento(rs.getString("mtd_Pagamento"));

				ArrayList<ItemVenda> lista = buscarItensVendaPorIdVenda(rs.getInt("idVenda"));
				v.setItensVenda(lista);

				Cliente c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				c.setNomeCliente(rs.getString("nome_Cliente"));
				c.setDataNasc(rs.getString("data_Nasc"));
				c.setCpf_Cliente(rs.getString("cpf_Cliente"));
				c.setTelefone(rs.getString("telefone_Cliente"));
				c.setEmail(rs.getString("email_Cliente"));
				v.setCliente(c);

				Funcionario f = new Funcionario();
				f.setId_Funcionario(rs.getInt("id_Funcionario"));
				f.setNomeFuncionario(rs.getString("nome_Funcionario"));
				f.setEmail_Funcionario(rs.getString("email_Funcionario"));
				f.setCpf(rs.getString("cpf"));
				f.setCelular(rs.getString("celular"));
				f.setLogin(rs.getString("login"));
				v.setFuncionario(f);

				// fazer do prod
				Produto p = new Produto();

				p.setId_Produto(rs.getInt("id_Produto"));
				p.setNomeProduto(rs.getString("nome_Produto"));
				p.setTamanho(rs.getString("tamanho"));
				p.setGenero(rs.getString("genero"));
				p.setPreco(rs.getFloat("preco"));
				p.setQtdEstoque(rs.getInt("qntd_Estoque"));
				v.setProduto(p);

				listaVendas.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVendas;
	}

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

	public Venda buscarVendas(int idVenda) {
		String mostrarDados = "SELECT * FROM vendas JOIN clientes ON clientes.id_Cliente = vendas.id_Cliente JOIN funcionarios ON funcionarios.id_Funcionario = vendas.id_Funcionario WHERE id_venda = ?";
		Venda v = null;

		try {
			pst = conn.prepareStatement(mostrarDados);
			pst.setInt(1, idVenda);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				v = new Venda();

				v.setId_Venda(rs.getInt("idVenda"));
				v.setMtd_Pagamento(rs.getString("mtd_Pagamento"));
				v.setTotal(rs.getFloat("Total"));

				ArrayList<ItemVenda> lista = buscarItensVendaPorIdVenda(rs.getInt("idVenda"));
				v.setItensVenda(lista);

				Cliente c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				c.setNomeCliente(rs.getString("nome_Cliente"));
				c.setDataNasc(rs.getString("data_Nasc"));
				c.setCpf_Cliente(rs.getString("cpf_Cliente"));
				c.setTelefone(rs.getString("telefone_Cliente"));
				c.setEmail(rs.getString("email_Cliente"));
				v.setCliente(c);

				Funcionario f = new Funcionario();
				f.setId_Funcionario(rs.getInt("id_Funcionario"));
				f.setNomeFuncionario(rs.getString("nome_Funcionario"));
				f.setEmail_Funcionario(rs.getString("email_Funcionario"));
				f.setCpf(rs.getString("cpf"));
				f.setCelular(rs.getString("celular"));
				f.setLogin(rs.getString("login"));
				v.setFuncionario(f);

				return v;
			}

			pst.executeQuery();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}

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
				p.setFornecedor(rs.getString("fornecedor"));
				p.setQtdEstoque(rs.getInt("qntd_Estoque"));

				v.setPreco(rs.getInt("preco_venda"));
				v.setQntd(rs.getInt("qntd"));
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