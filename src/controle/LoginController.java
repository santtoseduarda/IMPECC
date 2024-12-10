package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import modelo.Funcionario;
import visao.CadastroVendas;
import visao.MensagemView;
import visao.TelaLogin;

public class LoginController {
	TelaLogin view = new TelaLogin(this);
	FuncionarioDAO fdao = new FuncionarioDAO();
	FuncionarioController fcont = new FuncionarioController();
	char caractereSenha = 's';

	public void iniciarCadastro() {
		fcont.janelaLoginCadastro.setVisible(true);
		view.dispose();
	}

	public void telaInicial() {
		TelaInicialController telaInicialController = new TelaInicialController();
		telaInicialController.abrirTelaInicial();
	}

	public void iniciarLogin() {
		view.setVisible(true);
	}

	public ActionListener logar() {
	    return new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String login = view.txtLogin.getText();
	            char[] senhaCharArray = view.txtSenha.getPassword();
	            String senha = new String(senhaCharArray);

	            if (fdao.verificarLogin(login, senha)) {

	                Funcionario funcionarioLogado = fcont.buscarFuncionarioPorLogin(login);

	                SessaoUsuario.setFuncionarioLogado(funcionarioLogado);

	                VendaController vendaController = new VendaController(); // Certifique-se de que a instância de VendaController esteja correta
	                CadastroVendas telaCadastroVendas = new CadastroVendas(vendaController);
	                telaCadastroVendas.setVisible(true);

	                view.dispose();

	            } else {
	                new MensagemView("Login ou senha incorretos!", "Erro de login", 0);
	            }

	            java.util.Arrays.fill(senhaCharArray, '\0');
	        }
	    };
	}

	public MouseAdapter mostrarSenha() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (caractereSenha == 's') {
					caractereSenha = view.txtSenha.getEchoChar();
				}
				// clique do olho
				if (view.SenhaVisivel == false) {
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
	
	if (loginValido) { 
	    Funcionario funcionarioLogado = funcionarioController.buscarFuncionarioPorLogin(login);
	    SessaoUsuario.setFuncionarioLogado(funcionarioLogado);
	    
	    
	    CadastroVendas telaCadastroVendas = new CadastroVendas(vendaController);
	    telaCadastroVendas.setVisible(true);
	}
}
