package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Cliente;
import modelo.Fornecedor;

public class ClienteDAO {

	Statement stm1 = null; // permite fazer consultas no banco de dados
	int res1;
	PreparedStatement pst = null;
	Connection conn = ConexaoBanco.getConexaoMySQL(); // Fazer a conexão com o BD

	private static ArrayList<Cliente> tabelaCliente;
	private static ClienteDAO instancia;

	// instancia
	public static ClienteDAO getInstancia() {

		if (instancia == null) {
			instancia = new ClienteDAO();
			tabelaCliente = new ArrayList<>();

			// popularTabelaCliente();
		}

		return instancia;
	}

	public boolean inserir(Cliente c) {

		String inserir = "INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES (?, ?, ?, ?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			pst.setString(1, c.getNomeCliente()); // ajuste conforme os métodos get do seu modelo Cliente
			pst.setString(2, c.getDataNasc());
			pst.setString(3, c.getCpf_Cliente());
			pst.setString(4, c.getTelefone());
			pst.setString(5, c.getEmail());

			pst.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public ArrayList<Cliente> buscarClientes(String campo, String valor) {

		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		Statement stmt1 = null;

		Connection conn = ConexaoBanco.getConexaoMySQL();

		try {

			String sql = "SELECT * FROM clientes";
			PreparedStatement pst = conn.prepareStatement(sql);
			if (campo.isEmpty() == false) {
				sql = "SELECT * FROM clientes WHERE " + campo + " LIKE ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + valor + "%"); // Usando % para permitir busca parcial
			}
			System.out.println(pst);
			ResultSet rs = pst.executeQuery();

			// Iterando sobre o resultado e adicionando à lista de Clientes
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId_Cliente(rs.getInt("id_Cliente"));
				c.setNomeCliente(rs.getString("nome_Cliente"));
				c.setDataNasc(rs.getString("data_Nasc"));
				c.setCpf_Cliente(rs.getString("cpf_Cliente"));
				c.setTelefone(rs.getString("telefone_Cliente"));
				c.setEmail(rs.getString("email_Cliente"));
				listaClientes.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaClientes;

	}

	public boolean excluirCliente(int idCliente) {
		// TODO Auto-generated method stub

		Connection conn = ConexaoBanco.getConexaoMySQL(); // Estabelecer conexão com o banco
		String sql = "DELETE FROM clientes WHERE id_Cliente = ?"; // SQL para excluir pelo ID

		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, idCliente); // pega o id
			int linhasAfetadas = pst.executeUpdate(); // faz o delete

			return linhasAfetadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterarCliente(Cliente cliente) {
		String alterarCliente = "UPDATE clientes SET nome_Cliente = ?, data_Nasc = ?, cpf_Cliente = ?, telefone_Cliente = ?, email_Cliente = ?";

		try {
			pst = conn.prepareStatement(alterarCliente);

			pst.setString(1, cliente.getNomeCliente()); // ajuste conforme os métodos get do seu modelo Funcionario
			pst.setString(2, cliente.getDataNasc());
			pst.setString(3, cliente.getEmail());
			pst.setString(4, cliente.getTelefone());
			pst.setString(5, cliente.getCpf_Cliente());
			pst.setInt(6, cliente.getId_Cliente());
			System.out.println(pst);
			pst.executeUpdate();
			return true;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public Cliente buscarClientes(int idCliente) {

		String mostrarDados = "SELECT * FROM clientes  WHERE id_Cliente = ?";
		Cliente cliente = null;

		try {
			pst = conn.prepareStatement(mostrarDados);
			pst.setInt(1, idCliente);

			ResultSet rs = pst.executeQuery(); // Aqui você deve usar executeQuery()

			if (rs.next()) { // Se houver resultados, preencha o objeto Funcionario
				cliente = new Cliente();

				cliente.setId_Cliente(rs.getInt("idCliente"));
				cliente.setNomeCliente(rs.getString("nome_Cliente"));
				cliente.setEmail(rs.getString("email_Cliente"));
				cliente.setCpf_Cliente(rs.getString("cpf_Cliente"));
				cliente.setTelefone(rs.getString("telefone_Cliente"));
				cliente.setDataNasc(rs.getString("data_Nasc"));
				

				return cliente;
			}

			pst.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

}
