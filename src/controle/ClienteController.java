package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import visao.AlterarCliente;
import visao.CadastroCliente;
import visao.ListagemClientes;
import visao.MensagemView;
import visao.MensagemViewOp;

public class ClienteController {

	// instanciar as classes que vão ser usadas

	Cliente cliente = new Cliente();
	ClienteDAO cdao = new ClienteDAO();
	ListagemClientes janelaListagem = new ListagemClientes(this);
	CadastroCliente janelaCadastro = new CadastroCliente(this);
	AlterarCliente janelaAlterar = new AlterarCliente(cliente, this);
	TelaInternaController telaInternaController = new TelaInternaController();

	public ClienteController() {
		telaInternaController.setTela(janelaListagem);
	}

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
						new MensagemView("Erro ao cadastrar cliente.", "Erro de cadastro", 0);
	
					}
				}
			}

		};
	}

	public boolean validarDataNascimento(String dataNasc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			LocalDate data = LocalDate.parse(dataNasc, formatter);

			// Verifica se a data é maior que a data atual
			if (data.isAfter(LocalDate.now())) {
				new MensagemView("Data de nascimento não pode ser no futuro.", "Erro de cadastro", 0);
				return false;
			}

			// Verifica a idade (mínimo de 18 anos e máximo de 120 anos)
			long idade = ChronoUnit.YEARS.between(data, LocalDate.now());
			if (idade < 18 || idade > 120) {
				new MensagemView("A idade deve ser entre 18 e 120 anos.", "Erro de cadastro", 0);
				return false;
			}

			return true; // Data é válida e dentro do intervalo
		} catch (DateTimeParseException e) {
			new MensagemView("Data inválida. O formato correto é dd/MM/yyyy.", "Erro de cadastro", 0);
			return false;
		}
	}

	public boolean validarCamposCadastroClientes() {
		String nome = janelaCadastro.txtNome.getText();
		String dataNasc = janelaCadastro.txtDataNsc.getText();
		String cpf = janelaCadastro.txtCPF.getText();
		String telefone = janelaCadastro.txtTelefone.getText();
		String email = janelaCadastro.txtEmail.getText();

		if (nome.isEmpty() || dataNasc.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!validarDataNascimento(dataNasc)) {
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			new MensagemView("CPF inválido. Deve estar no formato 000.000.000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
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
					
					

					MensagemViewOp mve = new MensagemViewOp("Você tem certeza que deseja excluir o cliente?", "Exclusão de Cliente");
					
					int confirmacao = mve.getResposta();

					if (confirmacao == 1) {

						ClienteDAO cdao = new ClienteDAO();
						boolean certo = cdao.excluirCliente(idCliente);

						if (certo) {

							modeloTabela.removeRow(posicaoSelecionada);
							new MensagemView("Cliente excluído com sucesso.", "Exclusão", 1);
							atualizarTabela("", "");

						} else {
							new MensagemView("Erro ao excluir o cliente", "Erro de exclusão", 0);
						}
					}
				} else {
					new MensagemView("Por favor, selecione um cliente para excluir.", "Atenção", 0);
				}
			}

		};

	}

	public ActionListener sairSistema() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
				int resposta =  mve.getResposta();
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
						new MensagemView("Cliente não encontrado", "Atenção", 0);
	
					}
				} else {
					new MensagemView("Por favor, selecione um cliente para alterar.", "Atenção", 0);
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
								new MensagemView("Erro ao alterar cliente: Nenhuma linha foi afetada.", "Erro", 0);
							}
						} catch (Exception ex) {
							new MensagemView("Erro ao alterar cliente.", "Erro", 0);
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
			new MensagemView("Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 0);
			return false;
		}

		if (!validarDataNascimento(dataNasc)) {
			return false;
		}

		if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			new MensagemView("CPF inválido. Deve estar no formato 000.000.000-00", "Erro de cadastro", 0);
			return false;
		}

		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			new MensagemView("E-mail inválido. Deve conter '@' e um domínio válido.", "Erro de cadastro", 0);
			return false;
		}

		if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) { // Celular no formato (00)00000-0000
			new MensagemView("Celular inválido. Deve estar no formato (00)00000-0000.", "Erro de cadastro", 0);
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
