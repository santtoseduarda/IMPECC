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

import modelo.Funcionario;
import visao.AlterarFuncionario;
import visao.CadastroFuncionario;
import visao.CadastroFuncionarios;
import visao.ListagemFuncionarios;
import visao.MensagemView;
import visao.MensagemViewOp;

public class FuncionarioController {
	FuncionarioDAO fdao = new FuncionarioDAO();
	AlterarFuncionario janelaAlterar = new AlterarFuncionario(this);
	CadastroFuncionarios janelaCadastro = new CadastroFuncionarios(this);
	ListagemFuncionarios janelaListagem = new ListagemFuncionarios(this);
	CadastroFuncionario janelaLoginCadastro = new CadastroFuncionario(this);
	TelaInternaController telaInternaController = new TelaInternaController();

	public FuncionarioController() {
		telaInternaController.setTela(janelaListagem);
	}

	public void iniciarCadastroFunc() {
		limparCamposCadFuncionario();
		telaInternaController.setTela(janelaCadastro);
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}

	public void abrirListagem() {
		atualizarTabela("", "");
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

				try {
					if (validarCamposCadastroFuncionario()) {

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
							new MensagemView("Erro ao cadastrar funcionário.", "Erro de cadastro", 0);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

					MensagemViewOp mve = new MensagemViewOp("Você tem certeza que deseja excluir o funcionário?",
							"Exclusão de Funcionário");

					int confirmacao = mve.getResposta();

					if (confirmacao == 1) {

						FuncionarioDAO fdao = new FuncionarioDAO();
						boolean certo = fdao.excluirFuncionario(idFuncionario);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							new MensagemView("Funcionário excluído com sucesso.", "Exclusão", 1);
							atualizarTabela("", "");

						} else {
							new MensagemView("Erro ao excluir o funcionário", "Erro de exclusão", 0);
						}
					}
				} else {
					new MensagemView("Por favor, selecione um funcionário para excluir.", "Atenção", 0);
				}
			}

		};

	}

	//
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
						new MensagemView("Funcionário não encontrado", "Atenção", 0);
					}
				} else {
					new MensagemView("Por favor, selecione um funcionário para alterar.", "Atenção", 0);
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
					try {
						if (validarCamposEditarFuncionarios()) {
							Funcionario funcionario = new Funcionario();

							funcionario.setNomeFuncionario(janelaAlterar.txtNomeCompleto.getText());
							funcionario.setEmail_Funcionario(janelaAlterar.txtEmail.getText());
							funcionario.setCelular(janelaAlterar.txtCelular.getText());
							funcionario.setCpf(janelaAlterar.txtCPF.getText());
							funcionario.setLogin(janelaAlterar.txtLogin.getText());
							funcionario.setSenha(janelaAlterar.txtSenha.getText());

							try {
								boolean sucesso = fdao.alterarFuncionario(funcionario, idFuncionario);
								if (sucesso) {
									janelaListagem.setVisible(true);
									janelaAlterar.dispose();
									atualizarTabela("", "");
								} else {
									new MensagemView("Erro ao alterar funcionário: Nenhuma linha foi afetada.", "Erro",
											0);

								}
							} catch (Exception ex) {
								new MensagemView("Erro ao funcionário cliente.", "Erro", 0);

							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		};
	}

	public ActionListener sairSistema() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
				int resposta = mve.getResposta();

				// Verifica a resposta
				if (resposta == 1) {

					// botar o controller login para abrir a tela
					LoginController logController = new LoginController();
					logController.iniciarLogin();
					telaInternaController.fecharTela();
				}

			}

		};
	}

	public void limparCamposCadFuncionario() {
		// TODO Auto-generated method stub

		janelaCadastro.txtNomeCompleto.setText("");
		janelaCadastro.txtEmail.setText("");
		janelaCadastro.txtCelular.setText("");
		janelaCadastro.txtCPF.setText("");
		janelaCadastro.txtLogin.setText("");
		janelaCadastro.txtSenha.setText("");

	}

	public ActionListener limparCamposCadastroFuncionario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCamposCadFuncionario();
			}
		};
	}

	public ActionListener limparCamposEditarFuncionario() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAlterar.txtNomeCompleto.setText("");
				janelaAlterar.txtEmail.setText("");
				janelaAlterar.txtCelular.setText("");
				janelaAlterar.txtCPF.setText("");
				janelaAlterar.txtLogin.setText("");
				janelaAlterar.txtSenha.setText("");
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

	public MouseListener voltarLogin() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginController loginController = new LoginController();
				// FuncionarioController funcionarioController = new FuncionarioController();
				loginController.iniciarLogin();
				janelaLoginCadastro.dispose();
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

	public boolean validarCamposEditarFuncionarios() throws SQLException {
		String nomeFuncionario = janelaAlterar.txtNomeCompleto.getText();
		String email_Funcionario = janelaAlterar.txtEmail.getText();
		String celular = janelaAlterar.txtCelular.getText();
		String cpf = janelaAlterar.txtCPF.getText();
		String login = janelaAlterar.txtLogin.getText();
		String senha = janelaAlterar.txtSenha.getText();

		if (nomeFuncionario.isEmpty() || email_Funcionario.isEmpty() || celular.isEmpty() || cpf.isEmpty()
				|| login.isEmpty() || senha.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF no formato 000.000.000-00
			new MensagemView("CPF inválido. Deve estar no formato 000.000.000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!celular.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
			return false;
		}

		if (cpfJaCadastradoFuncionario(cpf)) {
			new MensagemView("CPF já cadastrado no sistema! Informe o CPF corretamente.", "Erro de cadastro", 0);
			return false;
		}
		return true;
	}
	

	private boolean cpfJaCadastradoFuncionario(String cpf) throws SQLException {
		Funcionario funcionario = fdao.buscarCPF(cpf);
		return funcionario != null;
	}

	public boolean validarCamposCadastroFuncionario() throws SQLException {
		String nomeFuncionario = janelaCadastro.txtNomeCompleto.getText();
		String email_Funcionario = janelaCadastro.txtEmail.getText();
		String celular = janelaCadastro.txtCelular.getText();
		String cpf = janelaCadastro.txtCPF.getText();
		String login = janelaCadastro.txtLogin.getText();
		String senha = janelaCadastro.txtSenha.getText();

		if (nomeFuncionario.isEmpty() || email_Funcionario.isEmpty() || celular.isEmpty() || cpf.isEmpty()
				|| login.isEmpty() || senha.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF no formato 000.000.000-00
			new MensagemView("CPF inválido. Deve estar no formato 000.000.000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!celular.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
			return false;
		}

		if (cpfJaCadastradoFuncionario(cpf)) {
			new MensagemView("CPF já cadastrado no sistema! Informe o CPF corretamente.", "Erro de cadastro", 0);
			return false;
		}

		if (loginJaExiste(login)) {
			new MensagemView("Login já cadastrado no sistema! Informe outro login.", "Erro de cadastro", 0);
			return false;
		}

		return true;
	}

	private boolean loginJaExiste(String login) throws SQLException {
		Funcionario funcionario = fdao.loginJaExiste(login);
		return funcionario != null;
	}

	public boolean validarCamposCadastroFuncionariosLogin() throws SQLException {
		String nomeFuncionario = janelaLoginCadastro.txtNomeCompleto.getText();
		String email_Funcionario = janelaLoginCadastro.txtEmail.getText();
		String celular = janelaLoginCadastro.txtCelular.getText();
		String cpf = janelaLoginCadastro.txtCPF.getText();
		String login = janelaLoginCadastro.txtLogin.getText();
		String senha = janelaLoginCadastro.txtSenha.getText();

		if (nomeFuncionario.isEmpty() || email_Funcionario.isEmpty() || celular.isEmpty() || cpf.isEmpty()
				|| login.isEmpty() || senha.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF no formato 000.000.000-00
			new MensagemView("CPF inválido. Deve estar no formato 000.000.000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email_Funcionario.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!celular.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
			return false;
		}
		
		if (cpfJaCadastradoFuncionario(cpf)) {
			new MensagemView("CPF já cadastrado no sistema! Informe o CPF corretamente.", "Erro de cadastro", 0);
			return false;
		}

		if (loginJaExiste(login)) {
			new MensagemView("Login já cadastrado no sistema! Informe outro Login.", "Erro de cadastro", 0);
			return false;
		}

		return true;
	}

	public ActionListener cadastrarFuncionarioLogin() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (validarCamposCadastroFuncionariosLogin()) {
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
							new MensagemView("Erro ao cadastrar funcionário.", "Erro", 0);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public void janelaCadastro() {
		// TODO Auto-generated method stub

	}

}