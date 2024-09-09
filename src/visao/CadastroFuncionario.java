package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeCompleto;
	private JTextField txtEmail;
	private JTextField textField;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtCargo;
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionario frame = new CadastroFuncionario();
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
	public CadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][grow][][grow]", "[grow][][][][][][][][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Cadastro de Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 0 0 7 3,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Nome Completo:*");
		contentPane.add(lblNewLabel_1, "cell 1 3");
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		contentPane.add(lblNewLabel_2, "cell 4 3");
		
		txtNomeCompleto = new JTextField();
		contentPane.add(txtNomeCompleto, "cell 1 4 2 1,growx");
		txtNomeCompleto.setColumns(10);
		
		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 4 4 2 1,growx");
		txtEmail.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		contentPane.add(lblCPF, "cell 1 6");
		
		JLabel lblNewLabel_4 = new JLabel("Cargo:");
		contentPane.add(lblNewLabel_4, "cell 4 6");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 7 2 1,growx");
		textField.setColumns(10);
		
		txtCargo = new JTextField();
		contentPane.add(txtCargo, "cell 4 7 2 1,growx");
		txtCargo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone:");
		contentPane.add(lblNewLabel_3, "cell 1 9");
		
		JLabel lblNewLabel_6 = new JLabel("Login:");
		contentPane.add(lblNewLabel_6, "cell 4 9");
		
		txtTelefone = new JTextField();
		contentPane.add(txtTelefone, "cell 1 10 2 1,growx");
		txtTelefone.setColumns(10);
		
		txtLogin = new JTextField();
		contentPane.add(txtLogin, "cell 4 10 2 1,growx");
		txtLogin.setColumns(10);
		
		JLabel lbl = new JLabel("Celular:");
		contentPane.add(lbl, "cell 1 12");
		
		JLabel lblNewLabel_7 = new JLabel("Senha:");
		contentPane.add(lblNewLabel_7, "cell 4 12");
		
		txtCelular = new JTextField();
		contentPane.add(txtCelular, "cell 1 13 2 1,growx");
		txtCelular.setColumns(10);
		
		txtSenha = new JTextField();
		contentPane.add(txtSenha, "cell 4 13 2 1,growx");
		txtSenha.setColumns(10);
		
		JButton btnSair = new JButton("     Sair    ");
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnSair, "cell 1 16,alignx left");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(161, 0, 29));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCadastrar, "flowx,cell 5 16,alignx right");
	}

}
