package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Fornecedor;
import modelo.Produto;
import visao.AlterarProduto;
import visao.CadastroProduto;
import visao.ListagemProdutos;

public class ProdutoController {

	ListagemProdutos viewL = new ListagemProdutos(this);
	CadastroProduto viewC = new CadastroProduto(this);
	AlterarProduto viewA = new AlterarProduto(this);
	Produto produto = new Produto();
	ProdutoDAO novoProduto = new ProdutoDAO();
	TelaInternaController telaInternaController = new TelaInternaController();

	public ProdutoController() {
		telaInternaController.setTela(viewL);
	}

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
				limparCamposCad();
				viewC.setVisible(true);
				viewL.dispose();

			}
		};
	}

	protected void limparCamposCad() {
		viewC.txtNomeProduto.setText("");
		viewC.txtPreco.setText("");
		viewC.txtQntdEstoque.setText("");
		viewC.comboBoxTamanho.setSelectedItem(null);
		viewC.comboBoxGenero.setSelectedItem(null);
		viewC.comboBoxFornecedor.setSelectedItem(null);
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
					try {
						produto.setNomeProduto(viewC.txtNomeProduto.getText());
						produto.setTamanho(viewC.comboBoxTamanho.getSelectedItem().toString());
						produto.setGenero(viewC.comboBoxGenero.getSelectedItem().toString());
						produto.setFornecedor((Fornecedor) viewC.comboBoxFornecedor.getSelectedItem());

						float precoConvert = Float.parseFloat(viewC.txtPreco.getText());
						int qntEstoqueConvert = Integer.parseInt(viewC.txtQntdEstoque.getText());

						produto.setPreco(precoConvert);
						produto.setQtdEstoque(qntEstoqueConvert);

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
		String tamanho = (viewC.comboBoxTamanho.getSelectedItem() != null)
				? viewC.comboBoxTamanho.getSelectedItem().toString()
				: "";
		String genero = (viewC.comboBoxGenero.getSelectedItem() != null)
				? viewC.comboBoxGenero.getSelectedItem().toString()
				: "";
		String preco = viewC.txtPreco.getText();
		String fornecedor = (viewC.comboBoxFornecedor.getSelectedItem() != null)
				? viewC.comboBoxFornecedor.getSelectedItem().toString()
				: "";
		String qntEstoque = viewC.txtQntdEstoque.getText();

		try {
			Float.parseFloat(preco);
			Integer.parseInt(qntEstoque);
		} catch (NumberFormatException e) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Preencha os campos de Preço e Quantidade corretamente (somente números).", "Erro de cadastro",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (nomeProduto.isEmpty() || tamanho.isEmpty() || genero.isEmpty() || preco.isEmpty() || fornecedor.isEmpty()
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
		viewA.txtQuantidadeEstoque.setText(String.valueOf(p.getQtdEstoque()));

		FornecedorController fornecedorController = new FornecedorController();
		ArrayList<Fornecedor> fornecedores;
		try {
			fornecedores = fornecedorController.buscarTodosFornecedores();
			viewA.comboBoxFornecedor.removeAllItems();

			for (Fornecedor fornecedor : fornecedores) {
				viewA.comboBoxFornecedor.addItem(fornecedor);
			}

			Fornecedor fornecedorProduto = produto.getFornecedor();
			if (fornecedorProduto != null) {
				viewA.comboBoxFornecedor.setSelectedItem(fornecedorProduto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(viewA, "Erro ao carregar fornecedores.");
		}

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

						produto.setId_Produto(idProduto);

						produto.setNomeProduto(viewA.txtNome.getText());
						produto.setTamanho(viewA.comboBoxTamanho.getSelectedItem().toString());
						produto.setGenero(viewA.comboBoxGenero.getSelectedItem().toString());
						produto.setPreco(Float.parseFloat(viewA.txtPreço.getText()));

						Fornecedor fornecedorSelecionado = (Fornecedor) viewA.comboBoxFornecedor.getSelectedItem();
						produto.setFornecedor(fornecedorSelecionado);
						produto.setQtdEstoque(Integer.parseInt(viewA.txtQuantidadeEstoque.getText()));

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
		String nomeProduto = viewA.txtNome.getText();
		String tamanho = (viewA.comboBoxTamanho.getSelectedItem() != null)
				? viewA.comboBoxTamanho.getSelectedItem().toString()
				: "";
		String genero = (viewA.comboBoxGenero.getSelectedItem() != null)
				? viewA.comboBoxGenero.getSelectedItem().toString()
				: "";
		String preco = viewA.txtPreço.getText();
		String fornecedor = (viewA.comboBoxFornecedor.getSelectedItem() != null)
				? viewA.comboBoxFornecedor.getSelectedItem().toString()
				: "";
		String qntEstoque = viewA.txtQuantidadeEstoque.getText();

		try {
			Float.parseFloat(preco);
			Integer.parseInt(qntEstoque);
		} catch (NumberFormatException e) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Preencha os campos de Preço e Quantidade corretamente (somente números).", "Erro de cadastro",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (nomeProduto.isEmpty() || tamanho.isEmpty() || genero.isEmpty() || preco.isEmpty() || fornecedor.isEmpty()
				|| qntEstoque.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public ArrayList<Produto> buscarTodosProdutos() throws SQLException {
		return novoProduto.buscarTodosProdutos();
	}

}
