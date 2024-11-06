package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Produto;
import visao.AlterarProduto;
import visao.CadastroFuncionario;
import visao.CadastroProduto;
import visao.ListagemProdutos;

public class ProdutoController {

	ListagemProdutos viewL = new ListagemProdutos(this);
	CadastroProduto viewC = new CadastroProduto(this);
	Produto cadastro = new Produto();
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
				limparCamposCad();
				viewL.setVisible(true);
			}
		};
	}

	protected void limparCamposCad() {
		// TODO Auto-generated method stub

	}

	public ActionListener buscaProduto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void alterarProdutos(int id_Produto) {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarProdutos(id_Produto);
		AlterarProduto janelaAlterar = new AlterarProduto(p);
		janelaAlterar.setVisible(true);
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
		
		return true;
	}

	public ActionListener adicionarProduto() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCamposProd()) {

					float precoConvert = Float.parseFloat(preco);
					int qntEstoqueConvert = Integer.parseInt(qntEstoque);
					int codBarraConvert = Integer.parseInt(codBarra);

					cadastro.setNomeProduto(nomeProduto);
					cadastro.setCodBarra(codBarraConvert);
					cadastro.setTamanho(tamanho);
					cadastro.setGenero(genero);
					cadastro.setPreco(precoConvert);
					cadastro.setFornecedor(fornecedor);
					cadastro.setQtdEstoque(qntEstoqueConvert);

					try {

						novoProduto.inserir(cadastro);
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

	

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewL.setVisible(true);
				viewC.dispose();
			}
		};
	}

}
