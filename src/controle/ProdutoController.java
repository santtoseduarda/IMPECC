package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;

import modelo.Fornecedor;
import modelo.Funcionario;
import modelo.Produto;
import visao.AlterarProduto;
import visao.CadastroFuncionario;
import visao.CadastroProduto;
import visao.ListagemFuncionarios;
import visao.ListagemProdutos;

public class ProdutoController {

	ListagemProdutos viewL = new ListagemProdutos(this);
	CadastroProduto viewC = new CadastroProduto(this);
	AlterarProduto viewA = new AlterarProduto(this);
	Produto produto = new Produto();
	ProdutoDAO novoProduto = new ProdutoDAO();

	public void abrirListagemProdutos() {
		atualizarTabela("", "");
		viewL.setVisible(true);
	}

	public ActionListener sairSistema() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					LoginController logController = new LoginController();
					logController.iniciarLogin();
				}

			}

		};
	}

	public ActionListener cadastroProduto() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// limparCamposCad();
				viewC.setVisible(true);
				viewL.dispose();
			}
		};
	}

	protected void limparCamposCad() {

	}

	public ActionListener excluirProduto() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int posicaoSelecionada = viewL.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
					int idProduto = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Você tem certeza que deseja excluir o produto?", "Confirmação de Exclusão",
							JOptionPane.YES_NO_OPTION);

					if (confirmacao == JOptionPane.YES_OPTION) {

						boolean certo = novoProduto.excluirProdutos(idProduto);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.");

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir o produto.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para excluir.");
				}
			}
		};

	}

	protected void pesquisarPorCampo(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
		modeloTabela.setRowCount(0);

		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produto> listaProdutos = pdao.buscarProdLupa(campo, valor);

		for (Produto p : listaProdutos) {
			modeloTabela.addRow(new Object[] { p.getId_Produto(), p.getNomeProduto(), p.getTamanho(), p.getGenero(),
					p.getPreco(), p.getFornecedor().getNome_Fornecedor(), p.getQtdEstoque() });
		}
	}

	public MouseListener pesquisa(String campo, JTextField textField) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String valor = textField.getText();
				pesquisarPorCampo(campo, valor);
			}
		};
	}

	private void atualizarTabela(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
		modeloTabela.setRowCount(0);

		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produto> listaProdutos = pdao.buscarProdLupa(campo, valor);

		if (listaProdutos != null && !listaProdutos.isEmpty()) {
			for (Produto p : listaProdutos) {
				modeloTabela.addRow(new Object[] { p.getId_Produto(), p.getNomeProduto(), p.getTamanho(), p.getGenero(),
						p.getPreco(), p.getFornecedor().getNome_Fornecedor(), p.getQtdEstoque() });
			}
		}
	}

	public ActionListener adicionarProduto() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCamposProd()) {

					produto.setNomeProduto(viewC.txtNomeProduto.getText());
					produto.setTamanho(viewC.comboBoxTamanho.getSelectedItem().toString());
					produto.setGenero(viewC.comboBoxGenero.getSelectedItem().toString());
					produto.setFornecedor((Fornecedor) viewC.comboBoxFornecedor.getSelectedItem());

					String preco = viewC.txtPreco.getText();
					String qntEstoque = viewC.txtQntdEstoque.getText();

					float precoConvert = Float.parseFloat(preco);
					int qntEstoqueConvert = Integer.parseInt(qntEstoque);

					produto.setPreco(precoConvert);
					produto.setQtdEstoque(qntEstoqueConvert);

					try {

						novoProduto.inserir(produto);
						viewL.setVisible(true);
						atualizarTabela("", "");
						viewC.dispose();

					} catch (NumberFormatException ex) {

						javax.swing.JOptionPane.showMessageDialog(null,
								"Preencha os campos de Preço e Quantidade corretamente (somente números).",
								"Erro de cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	protected boolean validarCamposProd() {
		String nomeProduto = viewC.txtNomeProduto.getText();
		String tamanho = viewC.comboBoxTamanho.getSelectedItem().toString();
		String genero = viewC.comboBoxGenero.getSelectedItem().toString();
		String preco = viewC.txtPreco.getText();
		String fornecedor = (viewC.comboBoxFornecedor.getSelectedItem().toString());
		String qntEstoque = viewC.txtQntdEstoque.getText();

		if (nomeProduto.isEmpty() || tamanho == null || genero == null || preco.isEmpty() || fornecedor == null
				|| qntEstoque.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewL.setVisible(true);
				viewC.dispose();
			}
		};
	}

	public ActionListener limparCampos() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewC.txtNomeProduto.setText("");
				viewC.txtPreco.setText("");
				viewC.txtQntdEstoque.setText("");

				viewC.comboBoxTamanho.setSelectedItem(null);
				viewC.comboBoxGenero.setSelectedItem(null);
				viewC.comboBoxFornecedor.setSelectedItem(null);
			}
		};
	}

	private void mostrarDados(Produto p) {
		viewA.txtNome.setText(p.getNomeProduto());
		viewA.comboBoxTamanho.setSelectedItem(p.getTamanho());
		viewA.comboBoxGenero.setSelectedItem(p.getGenero());
		viewA.txtPreço.setText(String.valueOf(p.getPreco()));

		viewA.comboBoxFornecedor.setSelectedItem(p.getFornecedor().getNome_Fornecedor());

		viewA.txtQuantidadeEstoque.setText(String.valueOf(p.getQtdEstoque()));
	}

	public ActionListener buscaProduto() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = viewL.table.getSelectedRow();

				if (selectedRow != -1) {
					int idProduto = (int) viewL.table.getValueAt(selectedRow, 0);

					produto = novoProduto.buscarProdutos(idProduto);

					if (produto != null) {
						mostrarDados(produto);

						viewA.setVisible(true);
						viewL.dispose();
					} else {
						System.out.println("Produto não encontrado.");
						JOptionPane.showMessageDialog(viewL, "Produto não encontrado.");
					}
				} else {
					System.out.println("Nenhuma linha selecionada.");
					JOptionPane.showMessageDialog(viewL, "Por favor, selecione um produto para alterar.");
				}
			}
		};
	}

	public ActionListener limparCamposEditarProduto() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewA.txtNome.setText("");
				viewA.txtPreço.setText("");
				viewA.txtQuantidadeEstoque.setText("");
				viewC.comboBoxTamanho.setSelectedItem(null);
				viewC.comboBoxGenero.setSelectedItem(null);
				viewC.comboBoxFornecedor.setSelectedItem(null);
			}
		};
	}

	public ActionListener salvarEdicoes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = viewL.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
					int idProduto = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					if (validarCamposEditarProduto()) {
						Produto produto = new Produto();

						produto.setNomeProduto(viewA.txtNome.getText());
						produto.setTamanho(viewA.comboBoxTamanho.getSelectedItem().toString());
						produto.setGenero(viewA.comboBoxGenero.getSelectedItem().toString());
						produto.setPreco(Float.parseFloat(viewA.txtPreço.getText()));

						Fornecedor fornecedorSelecionado = (Fornecedor) viewA.comboBoxFornecedor.getSelectedItem();
						produto.setFornecedor(fornecedorSelecionado);
						produto.setQtdEstoque(Integer.parseInt(viewA.txtQuantidadeEstoque.getText()));
						;

						try {
							boolean sucesso = novoProduto.alterarProduto(produto);
							if (sucesso) {
								viewL.setVisible(true);
								viewA.dispose();
								atualizarTabela("", "");
							} else {
								JOptionPane.showMessageDialog(null,
										"Erro ao alterar funcionário: Nenhuma linha foi afetada.", "Erro",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Erro ao alterar funcionário: " + ex.getMessage(),
									"Erro", JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			}
		};
	}

	protected boolean validarCamposEditarProduto() {
		// TODO Auto-generated method stub
		return false;
	}
}
