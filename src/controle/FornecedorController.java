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
import visao.AlterarFornecedor;
import visao.CadastroFornecedores;
import visao.ListagemFornecedor;
import visao.MensagemView;
import visao.MensagemViewOp;

public class FornecedorController {

	Fornecedor fornecedor = new Fornecedor();
	FornecedorDAO fordao = new FornecedorDAO();
	ListagemFornecedor viewl = new ListagemFornecedor(this);
	CadastroFornecedores viewc = new CadastroFornecedores(this);
	AlterarFornecedor viewa = new AlterarFornecedor(fornecedor, this);
	TelaInternaController telaInternaController = new TelaInternaController();

	public FornecedorController() {
		telaInternaController.setTela(viewl);
	}

	public void abrirListagemFornecedor() {
		atualizarTabela("", "");
		viewl.setVisible(true);
	}

	public void cadastroFornecedor() {
		limparCamposCadFornecedor();
		viewc.setVisible(true);
		viewl.dispose();
	}

	public ActionListener buscaFornecedor() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = viewl.table.getSelectedRow();

				if (selectedRow != -1) {
					int id_Fornecedor = (int) viewl.table.getValueAt(selectedRow, 0);

					// Busca o funcionário no banco de dados usando o DAO
					fornecedor = fordao.buscarFornecedor(id_Fornecedor);

					if (fornecedor != null) {
						// Preenche os campos da janela de alteração com os dados do funcionário
						mostrarDados(fornecedor);

						// Exibe a janela de alteração
						viewa.setVisible(true);
						viewl.dispose();
					} else {
						new MensagemView("Fornecedor não encontrado.", "Atenção", 0);
					}
				} else {
					new MensagemView("Por favor, selecione um fornecedor para alterar.", "Atenção", 0);
				}
			}
		};
	}

	public ActionListener excluirFornecedor() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int posicaoSelecionada = viewl.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
					int idFornecedor = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

					MensagemViewOp mve = new MensagemViewOp("Você tem certeza que deseja excluir o fornecedor?", "Exclusão de Fornecedor");

					int confirmacao = mve.getResposta();

					if (confirmacao == 1) {

						// vai excluir o fornecedor
						FornecedorDAO frdao = new FornecedorDAO();
						boolean certo = frdao.excluirFornecedor(idFornecedor);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							new MensagemView("Fornecedor excluído com sucesso", "Exclusão", 1);

						} else {
							new MensagemView("Erro ao excluir o fornecedor", "Erro de exclusão", 0);
						}
					}
				} else {
					new MensagemView("Por favor, selecione um fornecedor para excluir.", "Atenção", 0);
				}
			}
		};
	}

	public ActionListener sairSistema() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
				int resposta =  mve.getResposta();
				// Verifica a resposta
				if (resposta == 1) {
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
						new MensagemView("Erro ao cadastrar funcionário", "Erro", 0);
					}

				}

			}
		};
	}

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewl.setVisible(true);
				viewc.dispose();
			}
		};
	}

	// arrumar essas validações
	public boolean validarCamposCadastroFornecedores() {
		String nome = viewc.txtNome.getText();
		String cnpj = viewc.txtCnpj.getText();
		String email = viewc.txtEmail.getText();
		String telefone = viewc.txtTelefone.getText();

		if (nome.isEmpty() || cnpj.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")) {
			new MensagemView("CNPJ inválido. Deve estar no formato 00.000.000/0000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
			return false;

		}
		return true;
	}

	public void limparCamposCadFornecedor() {

		viewc.txtNome.setText("");
		viewc.txtEmail.setText("");
		viewc.txtTelefone.setText("");
		viewc.txtCnpj.setText("");

	}

	public ActionListener limparCamposCadastroFornecedor() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCamposCadFornecedor();
			}
		};
	}

	public ActionListener limparCamposAlterarFornecedor() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewa.txtCnpj.setText("");
				viewa.txtEmailFornecedor.setText("");
				viewa.txtNomeFornecedor.setText("");
				viewa.txtTelefoneFornecedor.setText("");
			}
		};
	}

	public ActionListener salvarEdicoes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = viewl.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
					int idFornecedor = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					if (validarCamposEditarForn()) {
						Fornecedor fornecedor = new Fornecedor();

						fornecedor.setID_fornecedor(idFornecedor);
						fornecedor.setTelefone_Fornecedor(viewa.txtTelefoneFornecedor.getText());
						fornecedor.setCNPJ(viewa.txtCnpj.getText());
						fornecedor.setEmail_Fornecedor(viewa.txtEmailFornecedor.getText());
						fornecedor.setNome_Fornecedor(viewa.txtNomeFornecedor.getText());

						try {
							boolean sucesso = fordao.alterarFornecedor(fornecedor);
							if (sucesso) {
								atualizarTabela("", "");
								viewl.setVisible(true);
								viewa.dispose();
							} else {
								new MensagemView("Erro ao alterar fornecedor: Nenhuma linha foi afetada.", "Erro", 0);
							}
						} catch (Exception ex) {
							new MensagemView("Erro ao alterar fornecedor.", "Erro", 0);
						}
					}

				}
			}
		};
	}

	protected boolean validarCamposEditarForn() {
		String nome = viewa.txtNomeFornecedor.getText();
		String cnpj = viewa.txtCnpj.getText();
		String email = viewa.txtEmailFornecedor.getText();
		String telefone = viewa.txtTelefoneFornecedor.getText();

		if (nome.isEmpty() || cnpj.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")) {
			new MensagemView("CNPJ inválido. Deve estar no formato 00.000.000/0000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
			return false;

		}
		return true;
	}

	public void mostrarDados(Fornecedor fornecedor) {
		viewa.txtNomeFornecedor.setText(fornecedor.getNome_Fornecedor());
		viewa.txtEmailFornecedor.setText(fornecedor.getEmail_Fornecedor());
		viewa.txtCnpj.setText(fornecedor.getCNPJ());
		viewa.txtTelefoneFornecedor.setText(fornecedor.getTelefone_Fornecedor());
	}

	public ArrayList<Fornecedor> buscarTodosFornecedores() throws SQLException {
		return fordao.buscarTodosFornecedores();
	}

}
