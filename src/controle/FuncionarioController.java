package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Funcionario;
import visao.AlterarFuncionario;
import visao.CadastroFuncionario;
import visao.CadastroFuncionarios;
import visao.ListagemFuncionarios;
import visao.TelaLogin;

public class FuncionarioController {
	FuncionarioDAO fdao = new FuncionarioDAO();
	AlterarFuncionario janelaAlterar = new AlterarFuncionario(this);
	CadastroFuncionarios janelaCadastro = new CadastroFuncionarios(this);
	ListagemFuncionarios janelaListagem = new ListagemFuncionarios(this);
	CadastroFuncionario janelaLoginCadastro = new CadastroFuncionario(this);

	public void iniciarCadastroFunc() {
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}

	public void abrirListagem() {
		janelaListagem.setVisible(true);
	}

	public void inserirFuncionario() {
		// inserir
		janelaCadastro.setVisible(true);
	}

	public ActionListener cadastrarFuncionario() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (validarCamposCadastroFuncionarios()) {
					
					Funcionario cadastro = new Funcionario();
					cadastro.setLogin(janelaCadastro.txtLogin.getText());
					cadastro.setSenha(janelaCadastro.txtSenha.getText());
					cadastro.setCelular(janelaCadastro.txtCelular.getText());
					cadastro.setCpf(janelaCadastro.txtCPF.getText());
					cadastro.setEmail_Funcionario(janelaCadastro.txtEmail.getText());
					cadastro.setNomeFuncionario(janelaCadastro.txtNomeCompleto.getText());

					FuncionarioDAO novoFuncionario = new FuncionarioDAO();
					try {
						novoFuncionario.inserir(cadastro);
						janelaListagem.setVisible(true);
						atualizarTabela("", "");
						janelaCadastro.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		};
	}

	public ActionListener excluirFuncionario() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int posicaoSelecionada = janelaListagem.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
					int idFuncionario = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Você tem certeza que deseja excluir o funcionário?", "Confirmação de Exclusão",
							JOptionPane.YES_NO_OPTION);

					if (confirmacao == JOptionPane.YES_OPTION) {

						// vai excluir o funcionario
						FuncionarioDAO fdao = new FuncionarioDAO();
						boolean certo = fdao.excluirFuncionario(idFuncionario);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso.");

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionário.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um funcionário para excluir.");
				}
			}

		};

	}

	public ActionListener buscaFuncionario() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Supondo que 'janelaListagem' é um JFrame que contém uma JTable chamada
				// 'tabelaFuncionarios'
				int selectedRow = janelaListagem.table.getSelectedRow();

				if (selectedRow != -1) { // Verifica se alguma linha foi selecionada
					// Supondo que a primeira coluna da tabela contém o ID do funcionário
					int id_Funcionario = (int) janelaListagem.table.getValueAt(selectedRow, 0);

					// Busca o funcionário no banco de dados usando o DAO
					Funcionario f = fdao.buscarFuncionario(id_Funcionario);

					if (f != null) {
						// Preenche os campos da janela de alteração com os dados do funcionário
						mostrarDados(f);

						// Exibe a janela de alteração
						janelaAlterar.setVisible(true);
						janelaListagem.dispose();
					} else {
						System.out.println("Funcionário não encontrado.");
						JOptionPane.showMessageDialog(janelaListagem, "Funcionário não encontrado.");
					}
				} else {
					System.out.println("Nenhuma linha selecionada.");
					JOptionPane.showMessageDialog(janelaListagem, "Por favor, selecione um funcionário para alterar.");
				}
			}
		};
	}

	public ActionListener salvarEdicoes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = janelaListagem.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
					int idFuncionario = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					
					Funcionario funcionario = new Funcionario();
					funcionario.setLogin(janelaAlterar.txtLogin.getText());
					funcionario.setSenha(janelaAlterar.txtSenha.getText());
					funcionario.setCelular(janelaAlterar.txtCelular.getText());
					funcionario.setCpf(janelaAlterar.txtCPF.getText());
					funcionario.setEmail_Funcionario(janelaAlterar.txtEmail.getText());
					funcionario.setNomeFuncionario(janelaAlterar.txtNomeCompleto.getText());

					try {
						boolean sucesso = fdao.alterarFuncionario(funcionario, idFuncionario);
						if (sucesso) {
							janelaListagem.setVisible(true);
							janelaAlterar.dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Erro ao alterar funcionário: Nenhuma linha foi afetada.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao alterar funcionário: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public ActionListener sairSistema() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {

					// botar o controller login para abrir a tela
					LoginController logController = new LoginController();
					logController.iniciarLogin();
				}

			}

		};
	}

	public ActionListener limparCamposCadastroFuncionario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaCadastro.txtNomeCompleto.setText("");
				janelaCadastro.txtEmail.setText("");
				janelaCadastro.txtCelular.setText("");
				janelaCadastro.txtCPF.setText("");
				janelaCadastro.txtLogin.setText("");
				janelaCadastro.txtSenha.setText("");
			}
		};
	}

	public void mostrarDados(Funcionario f) {
		janelaAlterar.txtNomeCompleto.setText(f.getNomeFuncionario());
		janelaAlterar.txtEmail.setText(f.getEmail_Funcionario());
		janelaAlterar.txtCelular.setText(f.getCelular());
		janelaAlterar.txtCPF.setText(f.getCpf());
		janelaAlterar.txtLogin.setText(f.getLogin());
		janelaAlterar.txtSenha.setText(f.getSenha());
	}

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FuncionarioController funcionarioController = new FuncionarioController();
				funcionarioController.abrirListagem();
				janelaCadastro.dispose();
			}
		};
	}

	private void pesquisarPorCampo(String campo, String valor) {
	
		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0);

		FuncionarioDAO fdao = new FuncionarioDAO();
		ArrayList<Funcionario> listaFuncionarios = fdao.buscarFuncLupa(campo, valor);

		for (Funcionario f : listaFuncionarios) {

			modeloTabela.addRow(new Object[] { f.getId_Funcionario(), f.getNomeFuncionario(), f.getEmail_Funcionario(),
					f.getCelular(), f.getCpf(), f.getLogin(), });
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


	public void atualizarTabela(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0);

		FuncionarioDAO fdao = new FuncionarioDAO();
		ArrayList<Funcionario> listaFuncionarios = fdao.buscarFuncLupa(campo, valor);

		if (listaFuncionarios != null && !listaFuncionarios.isEmpty()) {
			for (Funcionario f : listaFuncionarios) {
				// Adiciona os dados do funcionário na tabela
				modeloTabela.addRow(new Object[] { f.getId_Funcionario(), f.getNomeFuncionario(),
						f.getEmail_Funcionario(), f.getCelular(), f.getCpf(), f.getLogin() });
			}
		}
	}

	public boolean validarCampos() {
		String nomeFuncionario = janelaLoginCadastro.txtNomeCompleto.getText();
		String email_Funcionario = janelaLoginCadastro.txtEmail.getText();
		String celular = janelaLoginCadastro.txtCelular.getText();
		String cpf = janelaLoginCadastro.txtCPF.getText();
		String login = janelaLoginCadastro.txtLogin.getText();
		String senha = janelaLoginCadastro.txtSenha.getText();

		if (nomeFuncionario.isEmpty() || email_Funcionario.isEmpty() || celular.isEmpty() || cpf.isEmpty()
				|| login.isEmpty() || senha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF no formato 000.000.000-00
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve estar no formato 000.000.000-00.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!celular.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			JOptionPane.showMessageDialog(null, "Celular inválido. Deve estar no formato (00)00000-0000.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validarCamposCadastroFuncionarios() {
		String nomeFuncionario = janelaCadastro.txtNomeCompleto.getText();
		String email_Funcionario = janelaCadastro.txtEmail.getText();
		String celular = janelaCadastro.txtCelular.getText();
		String cpf = janelaCadastro.txtCPF.getText();
		String login = janelaCadastro.txtLogin.getText();
		String senha = janelaCadastro.txtSenha.getText();

		if (nomeFuncionario.isEmpty() || email_Funcionario.isEmpty() || celular.isEmpty() || cpf.isEmpty()
				|| login.isEmpty() || senha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF no formato 000.000.000-00
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve estar no formato 000.000.000-00.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!celular.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			JOptionPane.showMessageDialog(null, "Celular inválido. Deve estar no formato (00)00000-0000.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public ActionListener cadastrarFuncionarioLogin() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entrou aqui");
				if (validarCampos()) {
					Funcionario cadastro = new Funcionario();

					// Capturando os dados dos campos de texto
					cadastro.setNomeFuncionario(janelaLoginCadastro.txtNomeCompleto.getText());
					cadastro.setEmail_Funcionario(janelaLoginCadastro.txtEmail.getText());
					cadastro.setCelular(janelaLoginCadastro.txtCelular.getText());
					cadastro.setCpf(janelaLoginCadastro.txtCPF.getText());
					cadastro.setLogin(janelaLoginCadastro.txtLogin.getText());
					cadastro.setSenha(janelaLoginCadastro.txtSenha.getText());

					FuncionarioDAO novoFuncionario = new FuncionarioDAO();
					try {
						novoFuncionario.inserir(cadastro);
						LoginController loginController = new LoginController();
						loginController.iniciarLogin();

						janelaLoginCadastro.dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
	}

}