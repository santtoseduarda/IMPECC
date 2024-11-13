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

	public void abrirListagem() {
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
				//limparCamposCad();
				viewC.setVisible(true);
				viewL.dispose();
			}
		};
	}

	protected void limparCamposCad() {

	}

	public ActionListener buscaProduto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void alterarProdutos(int id_Produto) {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarProdutos(id_Produto);
		viewA.setVisible(true);
	}

	public ActionListener excluirProduto() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void pesquisarPorCampo(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
		modeloTabela.setRowCount(0);

		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produto> listaProdutos = pdao.buscarProdLupa(campo, valor);

		for (Produto p : listaProdutos) {

			modeloTabela.addRow(new Object[] { p.getId_Produto(), p.getNomeProduto(), p.getCodBarra(), p.getTamanho(),
					p.getGenero(), p.getPreco(), p.getFornecedor(), p.getQtdEstoque()

			});
		}

	}

	public MouseListener pesquisa(String campo, JTextField textField) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String valor = textField.getText(); // Obter o valor atualizado do campo de texto no momento do clique
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
				modeloTabela.addRow(new Object[] { p.getId_Produto(), p.getNomeProduto(), p.getCodBarra(),
						p.getTamanho(), p.getGenero(), p.getPreco(), p.getFornecedor(), p.getQtdEstoque() });
			}
		}
	}

	public ActionListener adicionarProduto() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCamposProd()) {

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
		String codBarra = viewC.txtCodBarra.getText();
		String tamanho = (String) viewC.comboBoxTamanho.getSelectedItem();
		String genero = (String) viewC.comboBoxGenero.getSelectedItem();
		String preco = viewC.txtPreco.getText();
		String fornecedor = (String) viewC.comboBoxFornecedor.getSelectedItem();
		String qntEstoque = viewC.txtQntdEstoque.getText();

		if (nomeProduto.isEmpty() || codBarra.isEmpty() || tamanho == null || genero == null || preco.isEmpty()
				|| fornecedor == null || qntEstoque.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}

		float precoConvert = Float.parseFloat(preco);
		int qntEstoqueConvert = Integer.parseInt(qntEstoque);
		int codBarraConvert = Integer.parseInt(codBarra);

		produto.setNomeProduto(viewC.txtNomeProduto.getText());
		produto.setCodBarra(codBarraConvert);
		produto.setTamanho(tamanho);
		produto.setGenero(genero);
		produto.setPreco(precoConvert);
		produto.setFornecedor(fornecedor);
		produto.setQtdEstoque(qntEstoqueConvert);

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
				viewC.txtCodBarra.setText("");
				viewC.txtPreco.setText("");
				viewC.txtQntdEstoque.setText("");
		        
		        //limpar do combobox
				viewC.comboBoxTamanho.setSelectedItem(null);
				viewC.comboBoxGenero.setSelectedItem(null);
				viewC.comboBoxFornecedor.setSelectedItem(null);
			}
		};
	}

	/*
	public ActionListener salvarEdicoes() {
		return null;
		return new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = viewL.table.getSelectedRow();
				if (posicaoSelecionada >= 0) {
					DefaultTableModel modeloTabela = (DefaultTableModel) viewL.table.getModel();
					int idProduto = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					
					produto.setNomeProduto(nomeProduto);
					produto.setCodBarra(codBarraConvert);
					produto.setTamanho(tamanho);
					produto.setGenero(genero);
					produto.setPreco(precoConvert);
					produto.setFornecedor(fornecedor);
					produto.setQtdEstoque(qntEstoqueConvert);
					
				}
			 
				
			  
			  ProdutoDAO funcionarioAlterado = new ProdutoDAO(); try {
			  funcionarioAlterado.alterarFuncionario(produto); ListagemFuncionarios
			  janelaListagem = new ListagemFuncionarios(); janelaListagem.setVisible(true);
			  dispose();
			  
			  } catch (Exception ex) { JOptionPane.showMessageDialog(null,
			  "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
			  JOptionPane.ERROR_MESSAGE); }
			  
			  }

		}	
	}

	public ActionListener limparCamposEditarFuncionarioProduto() {
		public ActionListener limparCamposEditarFuncionarioProduto() {
			// TODO Auto-generated method stub
			return null;
		});
	}

	private void mostrarDados(Produto p) {
		

	}*/
}
