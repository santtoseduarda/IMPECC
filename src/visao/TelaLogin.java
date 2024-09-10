package visao;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField tctSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(69, 69, 69, 40));
		contentPane.add(panel, "cell 3 1 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][][][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		panel.add(lblNewLabel, "cell 0 0 4 2,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		panel.add(lblNewLabel_1, "cell 1 3");
		
		txtLogin = new JTextField();
		panel.add(txtLogin, "cell 1 4 2 1,growx");
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		panel.add(lblNewLabel_2, "cell 1 5");
		
		tctSenha = new JTextField();
		panel.add(tctSenha, "cell 1 6 2 1,growx");
		tctSenha.setColumns(10);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.setForeground(new Color(255, 255, 255));
		btnAcessar.setBackground(new Color(255, 0, 0));
		btnAcessar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel btnOlho = new JLabel("");
		btnOlho.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnOlho.setIcon(new ImageIcon(new ImageIcon("src/img/olho.png").getImage().getScaledInstance(17, 18, Image.SCALE_DEFAULT)));

		btnOlho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//clique do olho
				System.out.print("mostrar senha");
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
		btnEsqueceuSenha.setFont(new Font("Tahoma", Font.PLAIN, 9));
		panel.add(btnEsqueceuSenha, "cell 1 7");
		panel.add(btnAcessar, "cell 1 9 2 1,alignx center");
		
		JButton btnCadastre = new JButton("Cadastre-se");
		btnCadastre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnCadastre, "cell 1 11 2 1,alignx center");
	}
}