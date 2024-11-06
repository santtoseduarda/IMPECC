package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Funcionario;
import visao.AlterarCliente;
import visao.CadastroCliente;
import visao.ListagemCliente;

public class ClienteController {
	
	//instanciar as classes que vão ser usadas
	
	Cliente cliente = new Cliente();
	ClienteDAO clidao = new ClienteDAO();
	ListagemCliente janelaListagem = new ListagemCliente(this);
	CadastroCliente janelaCadastro = new CadastroCliente();
	AlterarCliente janelaAlterar = new AlterarCliente(cliente, this);
	
	
	public void abrirListagemCLientes() {
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}
	
	public ActionListener iniciarCadastroCliente() {
		limparCamposCadCliente();
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}
	
	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteController clienteController = new ClienteController();
				clienteController.abrirListagemCLientes();
				janelaCadastro.dispose();
			}
		};
	}
	
	public ActionListener cadastrarCliente() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (validarCamposCadClientes()) {
					
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
	
	public boolean validarCamposCadastroFornecedores() {
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

		if (!cpf.matches("\\d{3}\\\\.\\\\d{3}\\\\.\\\\d{3}-\\\\d{2}")) { 
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve estar no formato 00.000.000/0000-00",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!dataNasc.matches("")) {
	        JOptionPane.showMessageDialog(null, "E-mail inválido. Deve conter '@' e um domínio válido.",
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
	/*
	public void limparCamposCadClientes() {
		// TODO Auto-generated method stub
		
				janelaCadastro.txtNome.setText("");
				janelaCadastro.txtDataNsc.setText("");
				janelaCadastro.txtCPF.setText("");
				janelaCadastro.txtTelefone.setText("");
				janelaCadastro.txtEmail.setText("");
	}
	*/
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
	/*
	public ActionListener salvarEdicoes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = janelaListagem.table.getSelectedRow();

				if (posicaoSelecionada >= 0) {

					DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
					int idCliente = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);
					Cliente cadastro = new Cliente();
					
					cadastro.setNomeCliente(janelaAlterar.txtNomeCliente.getText());
					cadastro.setDataNasc(janelaAlterar.txtDataNascCliente.getText());
					cadastro.setCpf_Cliente(janelaAlterar.txtCpfCliente.getText());
					cadastro.setTelefone(janelaAlterar.txtTelefoneCliente.getText());
					cadastro.setEmail(janelaAlterar.txtEmailCliente.getText());
					

					try {
						boolean sucesso = clidao.alterarCliente(cliente, idCliente);
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
		};
	}
	
	public ActionListener buscaCliente() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = janelaListagem.table.getSelectedRow();

				if (selectedRow != -1) { // Verifica se alguma linha foi selecionada
					// Supondo que a primeira coluna da tabela contém o ID do funcionário
					int id_Cliente = (int) janelaListagem.table.getValueAt(selectedRow, 0);

					// Busca o funcionário no banco de dados usando o DAO
					Cliente c = clidao.buscarFuncionario(id_Cliente);

					if (f != null) {
						// Preenche os campos da janela de alteração com os dados do funcionário
						mostrarDados(c);

						// Exibe a janela de alteração
						janelaAlterar.setVisible(true);
						janelaListagem.dispose();
					} else {
						System.out.println("Cliente não encontrado.");
						JOptionPane.showMessageDialog(janelaListagem, "Cliente não encontrado.");
					}
				} else {
					System.out.println("Nenhuma linha selecionada.");
					JOptionPane.showMessageDialog(janelaListagem, "Por favor, selecione um cliente para alterar.");
				}
			}
		};
	}
	*/
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
	
	
}
