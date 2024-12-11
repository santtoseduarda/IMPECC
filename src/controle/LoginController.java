package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import modelo.Cliente;
import visao.CadastroFuncionario;
import visao.MensagemView;
import visao.TelaInicial;
import visao.TelaLogin;

public class LoginController {
	TelaLogin view = new TelaLogin(this);
	FuncionarioDAO fdao = new FuncionarioDAO();
	FuncionarioController fcont = new FuncionarioController();
	char caractereSenha = 's';

	public LoginController() {
		configurarListeners();
  	}
	
	public void iniciarCadastro(){
		fcont.janelaLoginCadastro.setVisible(true);
		view.dispose();
	}
	
	public void telaInicial(){
		TelaInicialController telaInicialController = new TelaInicialController();
		telaInicialController.abrirTelaInicial();
	}

	public void iniciarLogin() {
		view.setVisible(true);
	}
	
	public class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ("login".equals(e.getActionCommand())) {
				logar();
			} else if ("cadastro".equals(e.getActionCommand())){
				iniciarCadastro();
			}
		}
	}
	
	private void configurarListeners() {
		view.addTelaLoginListener(new LoginListener());		
	}
	
	
	public void logar() {
				
				String login = view.txtLogin.getText();
		        String senha = view.txtSenha.getText();
		        				
				 if (fdao.verificarLogin(login, senha)) {
					 
			            // Se a verificação for bem-sucedida, abre a tela inicial
			            telaInicial();
			            view.dispose(); // Fecha a tela de login
			            
			        } else {
			            // Se o login ou a senha estiverem errados, mostra uma mensagem de erro
			        	new MensagemView("Login ou senha incorretos!", "Erro de login", 0);
			        };
	}
	
	public MouseAdapter mostrarSenha() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(caractereSenha == 's'){
					caractereSenha = 	view.txtSenha.getEchoChar();
				}
				//clique do olho
				if(view.SenhaVisivel == false) {
					System.out.print("mostrar senha");
					

					view.txtSenha.setEchoChar('\0');
					view.SenhaVisivel = true;
				} else {
					System.out.print("ocular senha");
					
					view.txtSenha.setEchoChar(caractereSenha);
					
					view.SenhaVisivel = false;
					
				}
				view.panel.validate();
			
			}
		};
	}
}
