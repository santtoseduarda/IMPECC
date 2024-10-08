/*package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
import modelo.Cliente;
/*
public class ClienteDAO {
	
	/*
	 * public Connection conexao = null; private PreparedStatement pst =null;
	 * private ResultSet rs = null;
	 */

	// conexao = ConexaoBanco.getConexaoMySQL();

	/* Statement stm1 = null; // permite fazer consultas no banco de dados
	int res1;

	// incluir, listar

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

		Connection conn = ConexaoBanco.getConexaoMySQL(); // Fazer a conexão com o BD

		String inserir = "INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Clinte, telefone_Cliente, email_Cliente) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(inserir);

			// Setar os valores nos placeholders "?"
			pst.setString(1, c.getNomeCliente());
			pst.setLocalDate(2, c.getDataNasc());
			pst.setString(3, c.getCpf_Cliente());
			pst.setString(4, c.getTelefone());
			pst.setString(5, c.getEmail());
			
			pst.setString(1, c.getNomeCliente()); // ajuste conforme os métodos get do seu modelo Cliente
			pst.setLocalDate(2, c.getDataNasc());
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
				c.setdataNasc(rs.getDate("data_Nasc"));
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
	    	
	    	pst.setInt(1, idCliente); //pega o id
	        int linhasAfetadas = pst.executeUpdate();  //faz o delete

	        return linhasAfetadas > 0;

	         
	    }catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			return false;
		}
	}

}*/
