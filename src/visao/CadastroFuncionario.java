package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.FuncionarioController;
import net.miginfocom.swing.MigLayout;

public class CadastroFuncionario extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtNomeCompleto;
	public JTextField txtEmail;
	public JTextField txtCelular;
	public JTextField txtLogin;
	public JTextField txtSenha;
	public JTextField txtCPF;
	private JLabel lblCadFunc;
	public JButton btnCadastrar;
	private JButton btnVoltar;

	public CadastroFuncionario(FuncionarioController funcionarioController) {

		Font fontRegular = null;
		Font fontBold = null;

		BufferedInputStream fontRegulaFile = null;
		BufferedInputStream fontBoldFile = null;

		try {
			fontRegulaFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Regular.TTF"));
			fontBoldFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Bold.TTF"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
			fontBold = Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);


		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][85.00, grow][][grow][][grow]", "[][][grow][][][][][][][][][][][][][][][][][grow]"));

		lblCadFunc = new JLabel("Cadastro de Funcionário");
		lblCadFunc.setFont(fontBold.deriveFont(Font.PLAIN, 50));
		contentPane.add(lblCadFunc, "cell 0 0 7 4,alignx center,aligny center");

		JLabel lblNomeCompleto = new JLabel("Nome Completo:*");
		lblNomeCompleto.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblNomeCompleto, "cell 1 5");

		JLabel lblNewLabel_2 = new JLabel("E-mail:* email@exemplo.com");
		lblNewLabel_2.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblNewLabel_2, "cell 4 5");

		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setPreferredSize(new Dimension(90, 30));
		contentPane.add(txtNomeCompleto, "cell 1 6 2 1,growx");
		txtNomeCompleto.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(90, 30));
		contentPane.add(txtEmail, "cell 4 6 2 1,growx");
		txtEmail.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:* (00)00000-0000");
		lblCelular.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblCelular, "cell 1 8");

		JLabel lblCPF = new JLabel("CPF:* 000.000.000-00");
		lblCPF.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblCPF, "cell 4 8");

		txtCelular = new JTextField();
		txtCelular.setPreferredSize(new Dimension(90, 30));
		contentPane.add(txtCelular, "cell 1 9 2 1,growx");
		txtCelular.setColumns(10);

		txtCPF = new JTextField();
		txtCPF.setPreferredSize(new Dimension(90, 30));
		txtCPF.setColumns(10);
		contentPane.add(txtCPF, "cell 4 9 2 1,growx");

		JLabel lblLogin = new JLabel("Login:*");
		lblLogin.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblLogin, "cell 1 11");

		JLabel LlblSenha = new JLabel("Senha:*");
		LlblSenha.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(LlblSenha, "cell 4 11");

		txtLogin = new JTextField();
		txtLogin.setPreferredSize(new Dimension(90, 30));
		contentPane.add(txtLogin, "cell 1 12 2 1,growx");
		txtLogin.setColumns(10);

		txtSenha = new JTextField();
		txtSenha.setPreferredSize(new Dimension(90, 30));
		contentPane.add(txtSenha, "cell 4 12 2 1,growx");
		txtSenha.setColumns(10);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setActionCommand("voltar");
		btnVoltar.setForeground(new Color(225, 0, 0));
		btnVoltar.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(btnVoltar, "cell 1 17 1 2,alignx left,aligny bottom");
		

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setActionCommand("cadastrar");
		
		btnCadastrar.setForeground(new Color(225, 225, 225));
		btnCadastrar.setBackground(new Color(161, 0, 29));
		btnCadastrar.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(btnCadastrar, "flowx,cell 5 17 1 2,alignx right,aligny bottom");
	}

	public void addCadastroFuncListener(ActionListener listener) {
		btnCadastrar.addActionListener(listener);
		btnVoltar.addActionListener(listener);
	}

}
