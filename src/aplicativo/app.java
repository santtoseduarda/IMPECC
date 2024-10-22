package aplicativo;

import java.awt.EventQueue;

import controle.LoginController;

public class app {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginController controller = new LoginController();
					controller.iniciarLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
