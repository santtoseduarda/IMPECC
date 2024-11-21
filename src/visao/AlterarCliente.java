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

import controle.ClienteController;
import modelo.Cliente;
import net.miginfocom.swing.MigLayout;

public class AlterarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtDataNascCliente;
	public JTextField txtNomeCliente;
	public JTextField txtCpfCliente;
	public JTextField txtTelefoneCliente;
	public JTextField txtEmailCliente;
	private JComponent lblVendas;
	private JComponent lblProdutos;
	private JComponent lblClientes;
	private JComponent lblFornecedor;
	private JComponent lblFuncionarios;

	public AlterarCliente(Cliente cliente, ClienteController clienteController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("Alterar Cliente ");
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
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][grow][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 1 0 2 6,alignx center,aligny center");
		
				JLabel lblcadastroFunc = new JLabel("Editar Dados do Cliente");
				lblcadastroFunc.setForeground(new Color(255, 255, 255));
				lblcadastroFunc.setFont(fontBold.deriveFont(Font.PLAIN, 45));
				contentPane.add(lblcadastroFunc, "cell 3 4");

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
		panel.setLayout(new MigLayout("", "[grow][grow][grow][][grow][grow 50][grow][][][grow]",
				"[][][][][grow][][][][][grow][][][][][grow][][][][][grow][][][][][grow][][][][][grow][][][][][grow][][][][]"));

		JLabel lblvoltar = new JLabel("");
		lblvoltar.addMouseListener(clienteController.voltarListagem());
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");

		JLabel lblNomeCliente = new JLabel("Nome");
		lblNomeCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblNomeCliente, "cell 1 5");

		txtNomeCliente = new JTextField();
		txtNomeCliente.setPreferredSize(new Dimension(90, 30));
		panel.add(txtNomeCliente, "cell 1 6 8 1,growx");
		txtNomeCliente.setColumns(10);

		JLabel lblDataNascCliente = new JLabel("Data de Nascimento:");
		lblDataNascCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblDataNascCliente, "cell 1 10");

		txtDataNascCliente = new JTextField();
		txtDataNascCliente.setPreferredSize(new Dimension(90, 30));
		panel.add(txtDataNascCliente, "cell 1 11 6 1,growx");
		txtDataNascCliente.setColumns(10);

		JLabel lblCpfClinte = new JLabel("CPF");
		lblCpfClinte.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblCpfClinte, "cell 1 13");

		txtCpfCliente = new JTextField();
		txtCpfCliente.setPreferredSize(new Dimension(90, 30));
		panel.add(txtCpfCliente, "cell 1 16 6 1,growx");
		txtCpfCliente.setColumns(10);

		JLabel lblTelefoneCliente = new JLabel("Telefone");
		lblTelefoneCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblTelefoneCliente, "cell 1 20");

		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setPreferredSize(new Dimension(90, 30));
		panel.add(txtTelefoneCliente, "cell 1 21 6 1,growx");
		txtTelefoneCliente.setColumns(10);

		JLabel lblEmailCliente = new JLabel("Email");
		lblEmailCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblEmailCliente, "cell 1 25");

		txtEmailCliente = new JTextField();
		txtEmailCliente.setPreferredSize(new Dimension(90, 30));
		panel.add(txtEmailCliente, "cell 1 26 6 1,growx");
		txtEmailCliente.setColumns(10);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(clienteController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		JButton btnSalvar = new JButton("Salvar Edições");
		btnSalvar.addActionListener(clienteController.salvarEdicoesEditarCliente());
		btnSalvar.setForeground(new Color(255, 0, 0));
		btnSalvar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSalvar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSalvar, "cell 28 85 1 4,aligny center");

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(clienteController.limparCamposEditarClientes());
		btnLimparCampos.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimparCampos.setForeground(Color.RED);
		btnLimparCampos.setBackground(Color.WHITE);
		contentPane.add(btnLimparCampos, "cell 25 85 1 4,aligny center");

	}

}
