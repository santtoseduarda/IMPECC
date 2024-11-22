package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.FuncionarioController;
import net.miginfocom.swing.MigLayout;

public class AlterarFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtNomeCompleto;
	public JTextField txtEmail;
	public JTextField txtCelular;
	public JTextField txtCPF;
	public JTextField txtLogin;
	public JTextField txtSenha;
	private JComponent lblVendas;
	private JComponent lblProdutos;
	private JLabel lblClientes;
	private JComponent lblFornecedor;
	private JComponent lblFuncionarios;

	public AlterarFuncionario(FuncionarioController funcionarioController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("Alterar Funcionário");
		Font fontRegular = null;
		Font fontBold = null;

		BufferedInputStream fontRegulaFile = null;
		BufferedInputStream fontBoldFile = null;

		try {
			fontRegulaFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Regular.TTF"));
			fontBoldFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Bold.TTF"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
			fontBold = Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 853);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblAlterarFunc = new JLabel("Editar Dados do Funcionário");
		lblAlterarFunc.setForeground(new Color(255, 255, 255));
		lblAlterarFunc.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblAlterarFunc, "cell 3 4");

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 1 0 2 6,alignx center,aligny center");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha1, "cell 1 7 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon(
				new ImageIcon("src/img/carrinho.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCarrinho, "cell 1 8,alignx left,aligny center");

		lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 2 8,alignx left,aligny center");

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha, "cell 1 9 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaixa, "cell 1 10,alignx left,aligny center");

		lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblProdutos, "cell 2 10,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 1 11 2 1");

		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCliente, "cell 1 12,alignx left,aligny center");

		lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblClientes, "cell 2 12");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 1 13 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaminhao, "cell 1 15,alignx left,aligny center");

		lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFornecedor, "cell 2 15");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha4, "cell 1 16 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblFuncionario, "cell 1 17,alignx left,aligny center");

		lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 2 17");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 19 2 1");

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 3 7 27 78,grow");
		panel.setLayout(new MigLayout("", "[grow][][][grow][grow 50][grow][][][grow]",
				"[][][grow][][][][][grow 20][][][grow 20][][][grow 20][][][][][][][][][][grow]"));

		JLabel lblvoltar = new JLabel("");
		// volta para a listagem
		lblvoltar.addMouseListener(funcionarioController.voltarListagem());
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblNomeCompleto, "cell 1 5");

		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setPreferredSize(new Dimension(90, 30));
		panel.add(txtNomeCompleto, "cell 1 6 3 1,growx");
		txtNomeCompleto.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblEmail, "cell 5 5");
		
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(90, 30));
		panel.add(txtEmail, "cell 5 6 3 1,growx");
		txtEmail.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblCelular, "cell 1 8");

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblCPF, "cell 5 8");

		txtCelular = new JTextField();
		txtCelular.setPreferredSize(new Dimension(90, 30));
		panel.add(txtCelular, "cell 1 9 3 1,growx");
		txtCelular.setColumns(10);

		txtCPF = new JTextField();
		panel.add(txtCPF, "cell 5 9 3 1,growx");
		txtCPF.setPreferredSize(new Dimension(90, 30));
		txtCPF.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblLogin, "cell 1 11");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblSenha, "cell 5 11");

		txtLogin = new JTextField();
		txtLogin.setPreferredSize(new Dimension(90, 30));
		panel.add(txtLogin, "cell 1 12 3 1,growx");
		txtLogin.setColumns(10);

		txtSenha = new JTextField();
		txtSenha.setPreferredSize(new Dimension(90, 30));
		panel.add(txtSenha, "cell 5 12 3 1,growx");
		txtSenha.setColumns(10);

		JButton btnSair = new JButton("Sair");
		// chamar o controller para sair do sistema
		btnSair.addActionListener(funcionarioController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Salvar Edições");
		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 28 85 1 4,aligny center");
		btnAdicionar.addActionListener(funcionarioController.salvarEdicoes());

		JButton btnLimparCampos = new JButton("Limpar Campos");
		// controller limpa os campos
		btnLimparCampos.addActionListener(funcionarioController.limparCamposEditarFuncionario());
		btnLimparCampos.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimparCampos.setForeground(Color.RED);
		btnLimparCampos.setBackground(Color.WHITE);
		contentPane.add(btnLimparCampos, "cell 25 85 1 4,aligny center");

	}

}
