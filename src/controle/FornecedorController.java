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
import javax.swing.table.DefaultTableModel;

import modelo.Fornecedor;
import modelo.Funcionario;

public class FornecedorController {

	Fornecedor fornecedor = new Fornecedor();
	FornecedorDAO fordao = new FornecedorDAO();
	ListagemFornecedor viewl = new ListagemFornecedor(this);
	CadastroFornecedores viewc = new CadastroFornecedores(this);
	AlterarFornecedor viewa = new AlterarFornecedor(fornecedor, null);

	// listagemFornecedor

	public void iniciarListagem() {
		viewl.setVisible(true);
	}

	public void cadastroFornecedor() {
		CadastroFornecedores janelaCadastroFornecedores = new CadastroFornecedores(this);
		janelaCadastroFornecedores.setVisible(true);
		viewl.dispose();
	}

	public void editarFornecedor(int id_Fornecedor) {

		FornecedorDAO forDao = new FornecedorDAO();
		fornecedor = forDao.bucarFornecedor(id_Fornecedor);
		viewa.setVisible(true);

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

	public MouseListener sairSistema() {
		return new MouseListener() {
			public void mouseClicked1(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					TelaLogin viewLogin = new TelaLogin(null);

					viewLogin.setVisible(true);
					viewLogin.dispose(); // Fecha a tela de login
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

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

	public MouseListener pesquisa(String campo, String valor) {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getSource());
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

				if (validarCampos()) {

					fornecedor.setCNPJ(viewc.txtCnpj.getText());
					fornecedor.setEmail_Fornecedor(viewc.txtEmail.getText());
					fornecedor.setNome_Fornecedor(viewc.txtNome.getText());
					fornecedor.setTelefone_Fornecedor(viewc.txtTelefone.getText());

					fordao.getInstancia();
					fordao.inserir(fornecedor);

					FornecedorDAO novoFornecedor = new FornecedorDAO();

					try {
						novoFornecedor.inserir(fornecedor);
						viewl.setVisible(true);
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
				viewl.setVisible(true);
				viewl.dispose();
			}
		};
	}

	protected boolean validarCampos() {
		String cnpj = viewc.txtCnpj.getText();
		String email = viewc.txtEmail.getText();
		String nome = viewc.txtNome.getText();
		String telefone = viewc.txtTelefone.getText();

		if (cnpj.isEmpty() || email.isEmpty() || nome.isEmpty() || telefone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios (*) devem ser preenchidos!",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!cnpj.matches("\\d{11}")) {
			JOptionPane.showMessageDialog(null, "CPF inválido. Deve ter 11 dígitos numéricos.", "Erro de cadastro",
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
	
	public ActionListener limparCamposCadastro() {
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
	
	public void mostrarDados(Fornecedor fornecedor) {
		viewa.txtNomeFornecedor.setText(fornecedor.getNome_Fornecedor());
		viewa.txtEmailFornecedor.setText(fornecedor.getEmail_Fornecedor());
		viewa.txtCnpj.setText(fornecedor.getCNPJ());
		viewa.txtTelefoneFornecedor.setText(fornecedor.getTelefone_Fornecedor());
	}


}
