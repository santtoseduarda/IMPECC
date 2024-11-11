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

import modelo.Cliente;
import visao.AlterarCliente;
import visao.CadastroCliente;
import visao.ListagemClientes;

public class ClienteController {

	// instanciar as classes que vão ser usadas

	Cliente cliente = new Cliente();
	ClienteDAO cdao = new ClienteDAO();
	ListagemClientes janelaListagem = new ListagemClientes(this);
	CadastroCliente janelaCadastro = new CadastroCliente(this);
	AlterarCliente janelaAlterar = new AlterarCliente(cliente, this);

	public void abrirListagemCLientes() {
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}

	public void inserirCliente() {
		// inserir
		janelaCadastro.setVisible(true);
	}

	public void iniciarCadastroCliente() {
		limparCamposCadClientes();
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}

	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirListagemCLientes();
				janelaCadastro.dispose();
			}
		};
	}

	public ActionListener cadastrarCliente() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (validarCamposCadastroClientes()) {

					Cliente cadastro = new Cliente();

					cadastro.setNomeCliente(janelaCadastro.txtNome.getText());
					cadastro.setDataNasc(janelaCadastro.txtDataNsc.getText());
					cadastro.setCpf_Cliente(janelaCadastro.txtCPF.getText());
					cadastro.setTelefone(janelaCadastro.txtTelefone.getText());
					cadastro.setEmail(janelaCadastro.txtEmail.getText());

					ClienteDAO novoCliente = new ClienteDAO();

					try {
						novoCliente.inserir(cadastro);
						janelaListagem.setVisible(true);
						atualizarTabela("", "");
						janelaCadastro.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		};
	}

	/*
	 * public String validaçaoData(String dateStr) { try { DateTimeFormatter
	 * inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy"); DateTimeFormatter
	 * outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); return
	 * LocalDate.parse(dateStr, inputFormat).format(outputFormat); } catch
	 * (DateTimeParseException e) { return null; // Indica data inválida } }
	 */

	public boolean validarCamposCadastroClientes() {
		String nome = janelaCadastro.txtNome.getText();
		String dataNasc = janelaCadastro.txtDataNsc.getText();
		String cpf = janelaCadastro.txtCPF.getText();
		String telefone = janelaCadastro.txtTelefone.getText();
		String email = janelaCadastro.txtEmail.getText();

		if (nome.isEmpty() || dataNasc.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve estar no formato 000.000.000-00",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			JOptionPane.showMessageDialog(null, "Celular inválido. Deve estar no formato (00)00000-0000.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;

		}
		return true;
	}

	public void limparCamposCadClientes() {
		// TODO Auto-generated method stub
		janelaCadastro.txtNome.setText("");
		janelaCadastro.txtDataNsc.setText("");
		janelaCadastro.txtCPF.setText("");
		janelaCadastro.txtTelefone.setText("");
		janelaCadastro.txtEmail.setText("");
	}

	public ActionListener excluirCliente() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int posicaoSelecionada = janelaListagem.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
					int idCliente = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Você tem certeza que deseja excluir o cliente?", "Confirmação de Exclusão",
							JOptionPane.YES_NO_OPTION);

					if (confirmacao == JOptionPane.YES_OPTION) {

						ClienteDAO cdao = new ClienteDAO();
						boolean certo = cdao.excluirCliente(idCliente);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
							atualizarTabela("", "");

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para excluir.");
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

	public void atualizarTabela(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		// ClienteDAO cdao = new ClienteDAO();
		ArrayList<Cliente> listaClientes = cdao.buscarClientesLupa(campo, valor);

		if (listaClientes != null && !listaClientes.isEmpty()) {
			for (Cliente c : listaClientes) {
				// Adiciona os dados do cliente na tabela
				modeloTabela.addRow(new Object[] { c.getId_Cliente(), c.getNomeCliente(), c.getDataNasc(),
						c.getCpf_Cliente(), c.getTelefone(), c.getEmail() });
			}
		}
	}

	public ActionListener buscaCliente() {
		// busca cliente para editar
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = janelaListagem.table.getSelectedRow();

				if (selectedRow != -1) {
					int id_Cliente = (int) janelaListagem.table.getValueAt(selectedRow, 0);

					// Busca o funcionário no banco de dados usando o DAO
					cliente = cdao.buscarClientes(id_Cliente);

					if (cliente != null) {
						// Preenche os campos da janela de alteração com os dados do funcionário
						mostrarDados(cliente);

						// Exibe a janela de alteração
						janelaAlterar.setVisible(true);
						janelaListagem.dispose();
					} else {
						System.out.println("cliente não encontrado.");
						JOptionPane.showMessageDialog(janelaListagem, "cliente não encontrado.");
					}
				} else {
					System.out.println("Nenhuma linha selecionada.");
					JOptionPane.showMessageDialog(janelaListagem, "Por favor, selecione um cliente para alterar.");
				}
			}
		};
	}

	protected void mostrarDados(Cliente cliente) {
		// TODO Auto-generated method stub
		janelaAlterar.txtNomeCliente.setText(cliente.getNomeCliente());
		janelaAlterar.txtDataNascCliente.setText(cliente.getDataNasc());
		janelaAlterar.txtCpfCliente.setText(cliente.getCpf_Cliente());
		janelaAlterar.txtEmailCliente.setText(cliente.getEmail());
		janelaAlterar.txtTelefoneCliente.setText(cliente.getTelefone());

	}

	public ActionListener salvarEdicoesEditarCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = janelaListagem.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
					int id_Cliente = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					if (validarCamposEditarCliente()) {
						Cliente alterar = new Cliente();

						alterar.setId_Cliente(id_Cliente);
						alterar.setNomeCliente(janelaAlterar.txtNomeCliente.getText());
						alterar.setDataNasc(janelaAlterar.txtDataNascCliente.getText());
						alterar.setCpf_Cliente(janelaAlterar.txtCpfCliente.getText());
						alterar.setTelefone(janelaAlterar.txtTelefoneCliente.getText());
						alterar.setEmail(janelaAlterar.txtEmailCliente.getText());

						try {
							boolean sucesso = cdao.alterarCliente(alterar);
							if (sucesso) {
								janelaListagem.setVisible(true);
								janelaAlterar.dispose();
								atualizarTabela("", "");
							} else {
								JOptionPane.showMessageDialog(null,
										"Erro ao alterar cliente: Nenhuma linha foi afetada.", "Erro",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Erro ao alterar cliente: " + ex.getMessage(), "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		};
	}

	public boolean validarCamposEditarCliente() {
		String nome = janelaAlterar.txtNomeCliente.getText();
		String dataNasc = janelaAlterar.txtDataNascCliente.getText();
		String cpf = janelaAlterar.txtCpfCliente.getText();
		String telefone = janelaAlterar.txtTelefoneCliente.getText();
		String email = janelaAlterar.txtEmailCliente.getText();

		if (nome.isEmpty() || dataNasc.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve estar no formato 000.000.000-00",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			JOptionPane.showMessageDialog(null, "Celular inválido. Deve estar no formato (00)00000-0000.",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;

		}
		return true;
	}

	public ActionListener limparCamposEditarClientes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaAlterar.txtNomeCliente.setText("");
				janelaAlterar.txtDataNascCliente.setText("");
				janelaAlterar.txtCpfCliente.setText("");
				janelaAlterar.txtTelefoneCliente.setText("");
				janelaAlterar.txtEmailCliente.setText("");
			}
		};
	}
	
	private void pesquisarPorCampo(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0);

		ClienteDAO cdao = new ClienteDAO();
		ArrayList<Cliente> listaCliente = cdao.buscarClientesLupa(campo, valor);

		for (Cliente c : listaCliente) {

			modeloTabela.addRow(new Object[] { c.getId_Cliente(), c.getNomeCliente(), c.getDataNasc(),
					c.getCpf_Cliente(), c.getTelefone(), c.getEmail() });
		}
	}

	public MouseListener pesquisa(String campo, JTextField txtID) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String valor = txtID.getText(); // Obter o valor atualizado do campo de texto no momento do clique
				pesquisarPorCampo(campo, valor);
			}
		};
	}

	

}
