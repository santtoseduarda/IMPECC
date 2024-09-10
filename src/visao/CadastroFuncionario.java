package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][grow][][grow]", "[][][grow][][][][][][][][][][][][][][][][][grow]"));
		
		JLabel bntVoltar = new JLabel("");
		bntVoltar.setIcon(new ImageIcon(new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));		
		contentPane.add(bntVoltar, "cell 0 0 2 2,alignx left,aligny center");
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setForeground(new Color(69, 69, 69, 0));
		contentPane.add(lblNewLabel_8, "cell 5 1 2 3");
		
		JLabel lblNewLabel = new JLabel("Cadastro de Funcionário");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 2 2 3 3,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Nome Completo:*");
		contentPane.add(lblNewLabel_1, "cell 1 5");
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		contentPane.add(lblNewLabel_2, "cell 4 5");
		
		txtNomeCompleto = new JTextField();
		contentPane.add(txtNomeCompleto, "cell 1 6 2 1,growx");
		txtNomeCompleto.setColumns(10);
		
		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 4 6 2 1,growx");
		txtEmail.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		contentPane.add(lblCPF, "cell 1 8");
		
		JLabel lblNewLabel_4 = new JLabel("Cargo:");
		contentPane.add(lblNewLabel_4, "cell 4 8");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 9 2 1,growx");
		textField.setColumns(10);
		
		txtCargo = new JTextField();
		contentPane.add(txtCargo, "cell 4 9 2 1,growx");
		txtCargo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone:");
		contentPane.add(lblNewLabel_3, "cell 1 11");
		
		JLabel lblNewLabel_6 = new JLabel("Login:");
		contentPane.add(lblNewLabel_6, "cell 4 11");
		
		txtTelefone = new JTextField();
		contentPane.add(txtTelefone, "cell 1 12 2 1,growx");
		txtTelefone.setColumns(10);
		
		txtLogin = new JTextField();
		contentPane.add(txtLogin, "cell 4 12 2 1,growx");
		txtLogin.setColumns(10);
		
		JLabel lbl = new JLabel("Celular:");
		contentPane.add(lbl, "cell 1 14");
		
		JLabel lblNewLabel_7 = new JLabel("Senha:");
		contentPane.add(lblNewLabel_7, "cell 4 14");
		
		txtCelular = new JTextField();
		contentPane.add(txtCelular, "cell 1 15 2 1,growx");
		txtCelular.setColumns(10);
		
		txtSenha = new JTextField();
		contentPane.add(txtSenha, "cell 4 15 2 1,growx");
		txtSenha.setColumns(10);
		
		JButton btnSair = new JButton("     Sair    ");
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnSair, "cell 1 18,alignx left");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(161, 0, 29));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCadastrar, "flowx,cell 5 18,alignx right");
	}

}
