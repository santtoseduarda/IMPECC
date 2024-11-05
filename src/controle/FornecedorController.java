package controle;

import visao.AlterarFornecedor;
import visao.CadastroFornecedores;
import visao.ListagemFornecedor;
import visao.TelaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Fornecedor;
import modelo.Funcionario;

public class FornecedorController {

	Fornecedor fornecedor = new Fornecedor();
	FornecedorDAO fordao = new FornecedorDAO();
	ListagemFornecedor viewl = new ListagemFornecedor(this);
	CadastroFornecedores viewc = new CadastroFornecedores(this);
	//AlterarFornecedor viewa = new AlterarFornecedor(fornecedor, null);

	// listagemFornecedor

	public void abrirListagemFornecedor() {
		atualizarTabela("", "");
		viewl.setVisible(true);
	}
	

	public void cadastroFornecedor() {
		viewc.setVisible(true);
		viewl.dispose();
	}

	public void editarFornecedor(int id_Fornecedor) {

/*		FornecedorDAO forDao = new FornecedorDAO();
		fornecedor = forDao.bucarFornecedor(id_Fornecedor);
		viewa.setVisible(true);
*/
	}

	public ActionListener excluirFornecedor() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int posicaoSelecionada = viewl.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
					int idFornecedor = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Você tem certeza que deseja excluir o fornecedor?", "Confirmação de Exclusão",
							JOptionPane.YES_NO_OPTION);

					if (confirmacao == JOptionPane.YES_OPTION) {

						// vai excluir o fornecedor
						FornecedorDAO frdao = new FornecedorDAO();
						boolean certo = frdao.excluirFornecedor(idFornecedor);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso.");

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir o fornecedor.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um fornecedor para excluir.");
				}
			}
		};
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

	private void pesquisarPorCampo(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		FornecedorDAO frdao = new FornecedorDAO();
		ArrayList<Fornecedor> listaFornecedores = frdao.buscarFornecedores(campo, valor); // Passando o campo e o valor

		for (Fornecedor fr : listaFornecedores) {

			modeloTabela.addRow(new Object[] { fr.getID_fornecedor(), fr.getNome_Fornecedor(), fr.getCNPJ(),
					fr.getEmail_Fornecedor(), fr.getTelefone_Fornecedor(), });
		}
	}

	public void atualizarTabela(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		FornecedorDAO frdao = new FornecedorDAO();
		ArrayList<Fornecedor> listaFornecedores = frdao.buscarFornecedores(campo, valor);

		if (listaFornecedores != null && !listaFornecedores.isEmpty()) {
			for (Fornecedor fr : listaFornecedores) {
				// Adiciona os dados do fornecedor na tabela
				modeloTabela.addRow(new Object[] { fr.getID_fornecedor(), fr.getNome_Fornecedor(), fr.getCNPJ(),
						fr.getEmail_Fornecedor(), fr.getTelefone_Fornecedor(), });
			}
		}
	}

	public MouseListener pesquisaLupaFornecedor(String campo, JTextField txtID) {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            String valor = txtID.getText(); // Obter o valor atualizado do campo de texto no momento do clique
				pesquisarPorCampo(campo, valor);
			}
		};
	}

	// cadastro de funcionario

	public void inserirFornecedor() {
		viewc.setVisible(true);
	}

	public ActionListener cadastrarFornecedor() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCamposCadastroFornecedores()) {

					Fornecedor cadastro = new Fornecedor();
					
					cadastro.setNome_Fornecedor(viewc.txtNome.getText());
					cadastro.setCNPJ(viewc.txtCnpj.getText());
					cadastro.setEmail_Fornecedor(viewc.txtEmail.getText());
					cadastro.setTelefone_Fornecedor(viewc.txtTelefone.getText());

					FornecedorDAO novoFornecedor = new FornecedorDAO();

					try {
						novoFornecedor.inserir(cadastro);
						viewl.setVisible(true);
						atualizarTabela("", "");
						viewc.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		};
	}

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FornecedorController fornecedorController = new FornecedorController();
				fornecedorController.abrirListagemFornecedor();
				viewc.dispose();
			}
		};
	}

	//arrumar essas validações
	public boolean validarCamposCadastroFornecedores() {
		String nome = viewc.txtNome.getText();
		String cnpj = viewc.txtCnpj.getText();
		String email = viewc.txtEmail.getText();
		String telefone = viewc.txtTelefone.getText();

		if (nome.isEmpty() || cnpj.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cnpj.matches("\\d{14}")) {
			JOptionPane.showMessageDialog(null, "CNPJ inválido. Deve ter 14 dígitos numéricos.", "Erro de cadastro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!telefone.matches("\\d{11}")) {
			JOptionPane.showMessageDialog(null, "Celular inválido. Deve ter 11 dígitos numéricos.", "Erro de cadastro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public ActionListener limparCamposCadastroFornecedor() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewc.txtNome.setText("");
				viewc.txtEmail.setText("");
				viewc.txtTelefone.setText("");
				viewc.txtCnpj.setText("");
			}
		};
	}

	public ActionListener limparCamposAlterar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/*				viewa.txtCnpj.setText("");
				viewa.txtEmailFornecedor.setText("");
				viewa.txtNomeFornecedor.setText("");
				viewa.txtTelefoneFornecedor.setText("");
*/			}
		};
	}

/*	public ActionListener salvarEdicoes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedor.setTelefone_Fornecedor(viewa.txtTelefoneFornecedor.getText());
				fornecedor.setCNPJ(viewa.txtCnpj.getText());
				fornecedor.setEmail_Fornecedor(viewa.txtEmailFornecedor.getText());
				fornecedor.setNome_Fornecedor(viewa.txtNomeFornecedor.getText());

				try {
					fordao.alterarFornecedor(fornecedor);
					viewl.setVisible(true);
					viewa.dispose();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
*/
	public void mostrarDados(Fornecedor fornecedor) {
/*		viewa.txtNomeFornecedor.setText(fornecedor.getNome_Fornecedor());
		viewa.txtEmailFornecedor.setText(fornecedor.getEmail_Fornecedor());
		viewa.txtCnpj.setText(fornecedor.getCNPJ());
		viewa.txtTelefoneFornecedor.setText(fornecedor.getTelefone_Fornecedor());
	*/}

}
