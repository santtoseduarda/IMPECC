package visao;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controle.FuncionarioDAO;
import controle.LoginController;
import modelo.Funcionario;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtLogin;
	public JTextField txtSenha;
	public boolean SenhaVisivel;
	public JPanel panel;
	public JTextField txttSenhaVisivel;
	LoginController loginController;
	
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 * @param loginController 
	 */
	public TelaLogin(LoginController loginController) {
		this.loginController = loginController;
		Font fontRegular = null;
		Font fontBold = null;
		
		BufferedInputStream fontRegulaFile = null;
		BufferedInputStream fontBoldFile = null;

		try {
			fontRegulaFile = new BufferedInputStream( new FileInputStream("src/fontes/Carlito-Regular.TTF"));
			fontBoldFile = new BufferedInputStream( new FileInputStream("src/fontes/Carlito-Bold.TTF"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
			fontBold= Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    TelaLogin janelaLogin = this;
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 737);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][200px,grow][][][][grow]", "[grow 50][grow][grow 50]"));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon("src/img/logo.png").getImage().getScaledInstance(230, 110, Image.SCALE_DEFAULT)));
		contentPane.add(lblNewLabel_3, "cell 4 0,alignx center,aligny center");
		
		panel = new JPanel();
		panel.setBackground(new Color(69, 69, 69, 40));
		contentPane.add(panel, "cell 3 1 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][][][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(fontBold.deriveFont(Font.PLAIN, 50));
		panel.add(lblNewLabel, "cell 0 0 4 2,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblNewLabel_1, "cell 1 3");
		
		txtLogin = new JTextField();
		panel.add(txtLogin, "cell 1 4 2 1,growx");
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblNewLabel_2, "cell 1 5");
		
		
		txttSenhaVisivel = new JTextField();
		txttSenhaVisivel.setColumns(10);

		
		txtSenha = new JPasswordField();
		panel.add(txtSenha, "cell 1 6 2 1,growx");
		txtSenha.setColumns(10);
		
		
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setForeground(new Color(255, 255, 255));
		btnAcessar.setBackground(new Color(255, 0, 0));
		btnAcessar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		
		// controller faz logar
		btnAcessar.addActionListener(loginController.logar());
		
		JLabel btnOlho = new JLabel("");
		btnOlho.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnOlho.setIcon(new ImageIcon(new ImageIcon("src/img/olho.png").getImage().getScaledInstance(17, 18, Image.SCALE_DEFAULT)));
		SenhaVisivel = false;
		btnOlho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//clique do olho
				if(SenhaVisivel == false) {
					System.out.print("mostrar senha");
					txttSenhaVisivel.setText(txtSenha.getText());

					panel.remove(txtSenha);

					panel.add(txttSenhaVisivel, "cell 1 6 2 1,growx");
					panel.repaint();
					SenhaVisivel = true;
				} else {
					System.out.print("ocular senha");
					txtSenha.setText(txttSenhaVisivel.getText());

					panel.remove(txttSenhaVisivel);

					panel.add(txtSenha, "cell 1 6 2 1,growx");
					panel.repaint();
					SenhaVisivel = false;
					
				}
				panel.validate();
			
			}
		});
		panel.add(btnOlho, "cell 3 6");
		
		JLabel btnEsqueceuSenha = new JLabel("Esqueceu sua senha?");
		btnEsqueceuSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//mostrar tela de esquecer a senha..
				System.out.print("esqueceu");
				
			}
		});
		btnEsqueceuSenha.setFont(fontBold.deriveFont(Font.PLAIN, 15));
		panel.add(btnEsqueceuSenha, "cell 1 7");
		panel.add(btnAcessar, "cell 1 9 2 1,alignx center");
		
		JButton btnCadastre = new JButton("Cadastre-se");
		btnCadastre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.iniciarCadastro();
			}
		});
		btnCadastre.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		panel.add(btnCadastre, "cell 1 11 2 1,alignx center");
	}
}