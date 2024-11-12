package visao;

import javax.swing.JLabel;

public interface TelaInterna {

	public JLabel getLabelFornecedor();
	public JLabel getLabelCliente();
	public JLabel getLabelFuncionario();
	public JLabel getLabelVendas();
	public JLabel getLabelProduto();
	public void dispose();
}
