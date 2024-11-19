package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BancodeDados.ConexaoBanco;
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

		String inserir = "INSERT INTO vendas (Total, Mtd_Pagamento) VALUES (?, ?)";

		try {
			pst = conn.prepareStatement(inserir);

			pst.setDouble(1, v.getTotal());
			pst.setString(2, v.getMtd_Pagamento());

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
				v.setId_Cliente(rs.getInt("id_cliente"));
				v.setIdFuncionario(rs.getInt("id_funcionario"));
				v.setIdProduto(rs.getInt("id_produto"));

				listaVendas.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVendas;
	}

	public boolean alterarVenda(Venda v) {
		String sql = "UPDATE vendas SET total = ?, mtd_pagamento = ? WHERE id_venda = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, v.getTotal());
			pst.setString(2, v.getMtd_Pagamento());
			pst.setInt(3, v.getId_Venda());
			pst.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
		String mostrarDados = "SELECT * FROM vendas WHERE id_venda = ?";
		Venda v = null;

		try {
			pst = conn.prepareStatement(mostrarDados);
			pst.setInt(1, idVenda);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				v = new Venda();
				v.setId_Venda(rs.getInt("idVenda"));
				v.setTotal(rs.getFloat("Total"));
				v.setMtd_Pagamento(rs.getString("mtd_Pagamento"));
				v.setIdCliente(rs.getInt("id_cliente"));
				v.setIdFuncionario(rs.getInt("id_funcionario"));
				v.setIdProduto(rs.getInt("id_produto"));

				return v;
			}

			pst.executeQuery();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;
	}
}