package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import modelo.Funcionario;
import visao.AlterarFuncionario;
import visao.CadastroFuncionarios;
import visao.ListagemFuncionarios;
import visao.TelaLogin;

public class FuncionarioController {
	FuncionarioDAO fdao = new FuncionarioDAO();
	Funcionario f = new Funcionario();
	TelaLogin viewLogin = new TelaLogin(null);
	AlterarFuncionario janelaAlterar = new AlterarFuncionario(f, this);
	CadastroFuncionarios janelaCadastro = new CadastroFuncionarios(this);
	ListagemFuncionarios janelaListagem = new ListagemFuncionarios(this);

	public void inserirFuncionario() {
	// inserir
		janelaCadastro.setVisible(true);		
	}
	
	public ActionListener cadastrarFuncionario() {
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validarCampos()) {
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
						TelaLogin janelaLogin = new TelaLogin(null);
						janelaLogin.setVisible(true);
						janelaCadastro.dispose();
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}				
			}

		}
	}
	
	public void excluirFuncionario() {
	// excluir
		
	}

	public void selecionaBusca(int id_Funcionario) {
		// seleciona o usuario e apresenta na tela
		f = fdao.bucarFuncionario(id_Funcionario);
		janelaAlterar.setVisible(true);
		
	}
	
	public ActionListener salvarEdicoes() {
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario();
				funcionario.setLogin(janelaAlterar.txtLogin.getText());
				funcionario.setSenha(janelaAlterar.txtSenha.getText());
				funcionario.setCelular(janelaAlterar.txtCelular.getText());
				funcionario.setCpf(janelaAlterar.txtCPF.getText());
				funcionario.setEmail_Funcionario(janelaAlterar.txtEmail.getText());
				funcionario.setNomeFuncionario(janelaAlterar.txtNomeCompleto.getText());
	
				try {
					fdao.alterarFuncionario(funcionario);
					janelaListagem.setVisible(true);
					janelaAlterar.dispose();
	
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}



	public MouseListener sairSistema() {
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					
					viewLogin.setVisible(true);
					viewLogin.dispose(); // Fecha a tela de login
				}

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

	public ActionListener limparCampos() {
		// TODO Auto-generated method stub
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
				janelaListagem.setVisible(true);
				janelaListagem.dispose();
			}
		};
	}
}