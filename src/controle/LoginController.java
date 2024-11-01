package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import visao.CadastroFuncionario;
import visao.TelaInicial;
import visao.TelaLogin;

public class LoginController {
	TelaLogin view = new TelaLogin(this);
	FuncionarioDAO fdao = new FuncionarioDAO();
	FuncionarioController fcont = new FuncionarioController();


	public void iniciarCadastro(){
	
		fcont.janelaLoginCadastro.setVisible(true);
		view.dispose();
	}
	
	public void telaInicial(){
		TelaInicialController telaInicialController = new TelaInicialController();
		TelaInicial novaJanela = new TelaInicial(telaInicialController);
        novaJanela.setVisible(true);
	}

	public void iniciarLogin() {
		view.setVisible(true);
	}

	public ActionListener logar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = view.txtLogin.getText();
		        String senha = view.txtSenha.getText();
		        				
				 if (fdao.verificarLogin(login, senha)) {
					 
			            // Se a verificação for bem-sucedida, abre a tela inicial
			            telaInicial();
			            view.dispose(); // Fecha a tela de login
			            
			        } else {
			            // Se o login ou a senha estiverem errados, mostra uma mensagem de erro
			            javax.swing.JOptionPane.showMessageDialog(null,
			                    "Login ou senha incorretos!",
			                    "Erro de login",
			                    javax.swing.JOptionPane.ERROR_MESSAGE);
			        }
			}
		};
	}
	
	public MouseAdapter mostrarSenha() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//clique do olho
				if(view.SenhaVisivel == false) {
					System.out.print("mostrar senha");
					view.txttSenhaVisivel.setText(view.txtSenha.getText());

					view.panel.remove(view.txtSenha);

					view.panel.add(view.txttSenhaVisivel, "cell 1 6 2 1,growx");
					view.panel.repaint();
					view.SenhaVisivel = true;
				} else {
					System.out.print("ocular senha");
					view.txtSenha.setText(view.txttSenhaVisivel.getText());

					view.panel.remove(view.txttSenhaVisivel);

					view.panel.add(view.txtSenha, "cell 1 6 2 1,growx");
					view.panel.repaint();
					view.SenhaVisivel = false;
					
				}
				view.panel.validate();
			
			}
		};
	}
}
