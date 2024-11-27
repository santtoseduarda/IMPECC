package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import visao.CadastroVendas;

public class VendaController {
	private CadastroVendas view;
	private ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();
	private Venda vendaAtual = new Venda();

	public VendaController(CadastroVendas view) {
		this.view = view;
		carregarProdutos();
	}

	public void carregarProdutos() {
		try {
			view.getcomboBoxProd().removeAllItems();
			for (Produto produto : produtoDAO.buscarTodosProdutos()) {
				view.getcomboBoxProdutos().addItem(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ActionListener adicionarAoCarrinho() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Produto produtoSelecionado = (Produto) view.getComboBoxProdutos().getSelectedItem();
				int quantidade = (int) view.getSpinnerQuantidade().getValue();

				if (produtoSelecionado != null && quantidade > 0) {
					ItemVenda item = new ItemVenda();
					item.setProduto(produtoSelecionado);
					item.setQuantidade(quantidade);

					vendaAtual.adicionarItem(item);

					DefaultTableModel modeloTabela = (DefaultTableModel) view.getTabelaCarrinho().getModel();
					modeloTabela.addRow(
							new Object[] { produtoSelecionado.getId_Produto(), produtoSelecionado.getNomeProduto(),
									quantidade, produtoSelecionado.getPreco(), item.getPrecoTotal() });

					view.getLabelValorTotal().setText(String.format("R$ %.2f", vendaAtual.getValorTotal()));
				}
			}
		};
	}

	public ActionListener finalizarVenda() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VendaDAO vendaDAO = new VendaDAO();
				boolean sucesso = vendaDAO.salvarVenda(vendaAtual);

				if (sucesso) {
					view.mostrarMensagem("Venda finalizada com sucesso!");
					limparCarrinho();
				} else {
					view.mostrarMensagem("Erro ao finalizar a venda.");
				}
			}
		};
	}

	private void limparCarrinho() {
		vendaAtual = new Venda();
		DefaultTableModel modeloTabela = (DefaultTableModel) view.getTabelaCarrinho().getModel();
		modeloTabela.setRowCount(0);
		view.getLabelValorTotal().setText("R$ 0,00");
	}
}
