package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import visao.CadastroFuncionario;
import visao.MensagemView;
import visao.TelaInicial;
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
				char[] senhaCharArray = view.txtSenha.getPassword(); // Alterado para getPassword
				String senha = new String(senhaCharArray); // Convertendo char[] para String

				if (fdao.verificarLogin(login, senha)) {

					telaInicial();
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
}
