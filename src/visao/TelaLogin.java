package visao;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 580, 299);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][200px,grow][][][][grow]", "[grow 50][][][][grow][][][][grow 50]"));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Aluno\\Downloads\\logoromover-removebg-preview 1.png"));
		contentPane.add(lblNewLabel_4, "cell 4 1,alignx center");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(69, 69, 69, 40));
		contentPane.add(panel, "cell 3 4 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][][][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		panel.add(lblNewLabel, "cell 0 1 4 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		panel.add(lblNewLabel_1, "cell 1 3");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 4 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		panel.add(lblNewLabel_2, "cell 1 5");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 6 2 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		panel.add(lblNewLabel_3, "cell 1 7,alignx left,aligny baseline");
		
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton, "cell 1 9 2 1,alignx center");
		
		JButton btnNewButton_1 = new JButton("Cadastre-se");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnNewButton_1, "cell 1 11 2 1,alignx center");
	}
}