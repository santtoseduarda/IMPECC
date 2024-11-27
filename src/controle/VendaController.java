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

	public ActionListener adicionarCarrinho() {
	    return new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            try {
	                // Obter o produto selecionado e a quantidade do spinner
	                Produto produtoSelecionado = (Produto) janelaCadastro.comboBoxProd.getSelectedItem();
	                int quantidade = (Integer) janelaCadastro.getSpinnerQntd().getValue();

	                // Verificar se os valores são válidos
	                if (produtoSelecionado == null || quantidade <= 0) {
	                    JOptionPane.showMessageDialog(janelaCadastro, "Selecione um produto e insira uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Criar um novo ItemVenda
	                ItemVenda itemVenda = new ItemVenda();
	                itemVenda.setProduto(produtoSelecionado);
	                itemVenda.setQntd(quantidade);
	                itemVenda.setPreco(produtoSelecionado.getPreco());
	                itemVenda.setPrecoTotal(quantidade * produtoSelecionado.getPreco());

	                // Adicionar o item à lista de itens da venda
	                venda.getItensVenda().add(itemVenda);

	                // Atualizar a tabela
	                DefaultTableModel model = (DefaultTableModel) janelaCadastro.table.getModel();
	                model.addRow(new Object[]{
	                    produtoSelecionado.getId_Produto(),
	                    produtoSelecionado.getNomeProduto(),
	                    quantidade,
	                    produtoSelecionado.getPreco(),
	                    itemVenda.getPrecoTotal()
	                });

	                // Atualizar o total geral
	                float totalGeral = venda.getTotal() + itemVenda.getPrecoTotal();
	                venda.setTotal(totalGeral);
	                janelaCadastro.lblPreco.setText(String.format("%.2f", totalGeral));

	                JOptionPane.showMessageDialog(janelaCadastro, "Produto adicionado ao carrinho com sucesso!");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(janelaCadastro, "Erro ao adicionar o produto ao carrinho: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    };
	}

	public void abrirTelaAdicionarVenda() {
		// TODO Auto-generated method stub
		
	}

	

}
