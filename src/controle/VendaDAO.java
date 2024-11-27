package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BancodeDados.ConexaoBanco;
import modelo.Venda;
import modelo.ItemVenda;

public class VendaDAO {
    private Connection conn = ConexaoBanco.getConexaoMySQL();

    public boolean salvarVenda(Venda venda) {
        String sqlVenda = "INSERT INTO vendas (valor_total) VALUES (?)";
        String sqlItemVenda = "INSERT INTO itens_venda (id_venda, id_produto, quantidade, preco_total) VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            // Inserir a venda
            PreparedStatement pstVenda = conn.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            pstVenda.setDouble(1, venda.getValorTotal());
            pstVenda.executeUpdate();

            // Recuperar o ID da venda gerada
            var rs = pstVenda.getGeneratedKeys();
            rs.next();
            int idVenda = rs.getInt(1);

            // Inserir os itens da venda
            PreparedStatement pstItemVenda = conn.prepareStatement(sqlItemVenda);
            for (ItemVenda item : venda.getItensVenda()) {
                pstItemVenda.setInt(1, idVenda);
                pstItemVenda.setInt(2, item.getProduto().getId_Produto());
                pstItemVenda.setInt(3, item.getQuantidade());
                pstItemVenda.setDouble(4, item.getPrecoTotal());
                pstItemVenda.addBatch();
            }
            pstItemVenda.executeBatch();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
