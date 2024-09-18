package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Funcionario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CadastroFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeCompleto;
	private JTextField txtEmail;
	private JTextField txtCelular;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private JTextField txtCPF;

	/**
	 * Launch the application.
	 */
	//Lucas o Melhor 
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
	    
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow][][grow][][grow]", "[][][grow][][][][][][][][][][][][][][][][][grow]"));
		
		JLabel bntVoltar = new JLabel("");
		bntVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Voltar");
			}
		});
		bntVoltar.setIcon(new ImageIcon(new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));		
		contentPane.add(bntVoltar, "cell 0 0 2 2,alignx left,aligny center");
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setForeground(new Color(69, 69, 69, 0));
		contentPane.add(lblNewLabel_8, "cell 5 1 2 3");
		
		JLabel lblNewLabel = new JLabel("Cadastro de Funcionário");
		lblNewLabel.setFont(fontBold.deriveFont(Font.PLAIN, 50));
		contentPane.add(lblNewLabel, "cell 2 2 3 3,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Nome Completo:*");
		lblNewLabel_1.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblNewLabel_1, "cell 1 5");
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		lblNewLabel_2.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblNewLabel_2, "cell 4 5");
		
		txtNomeCompleto = new JTextField();
		contentPane.add(txtNomeCompleto, "cell 1 6 2 1,growx");
		txtNomeCompleto.setColumns(10);
		
		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 4 6 2 1,growx");
		txtEmail.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblCelular, "cell 1 8");
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblCPF, "cell 4 8");
		
		txtCelular = new JTextField();
		contentPane.add(txtCelular, "cell 1 9 2 1,growx");
		txtCelular.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		contentPane.add(txtCPF, "cell 4 9 2 1,growx");
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblLogin, "cell 1 11");
		
		JLabel LlblSenha = new JLabel("Senha:");
		LlblSenha.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(LlblSenha, "cell 4 11");
		
		txtLogin = new JTextField();
		contentPane.add(txtLogin, "cell 1 12 2 1,growx");
		txtLogin.setColumns(10);
		
		txtSenha = new JTextField();
		contentPane.add(txtSenha, "cell 4 12 2 1,growx");
		txtSenha.setColumns(10);
		
		JButton btnSair = new JButton("     Sair    ");
		btnSair.setForeground(new Color(225, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(btnSair, "cell 1 17 1 2,alignx left,aligny bottom");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = txtLogin.getText(); 
				String senha = txtSenha.getText(); 
				String cpf = txtCPF.getText(); 
				String email = txtEmail.getText(); 
				String nomeCompleto = txtNomeCompleto.getText(); 
				String celular = txtCelular.getText(); 
				
				// criar um objeto para usar o metodo aqui dentro da janela
				Funcionario cadastro = new Funcionario();
				
				
				// passar os valores para esse objeto
				cadastro.setLogin(login);
				cadastro.setSenha(senha);
				cadastro.setCelular(celular);
				cadastro.setCpf(cpf);
				cadastro.setEmail_Funcionario(email);
				cadastro.setNomeFuncionario(nomeCompleto);

				
				
			}
		});
		btnCadastrar.setForeground(new Color(225, 225, 225));
		btnCadastrar.setBackground(new Color(161, 0, 29));
		btnCadastrar.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(btnCadastrar, "flowx,cell 5 17 1 2,alignx right,aligny bottom");
	}

}
