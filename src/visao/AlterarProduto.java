package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ProdutoController;
import modelo.Fornecedor;
import net.miginfocom.swing.MigLayout;

public class AlterarProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	public JTextField txtQuantidadeEstoque;
	public JTextField txtNome;
	public JTextField txtPreço;
	public JComboBox<Fornecedor> comboBoxFornecedor;
	public JComboBox comboBoxGenero;
	public JComboBox comboBoxTamanho;
	private JComponent lblFuncionarios;
	private JComponent lblFornecedor;
	private JLabel lblClientes;
	private JLabel lblProdutos;

	public AlterarProduto(ProdutoController produtoController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		setTitle("Alterar Produto ");
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
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(161, 0, 29));
		contentPane_1.setForeground(new Color(255, 0, 0));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblEditarProdutos = new JLabel("Editar Dados do Produto");
		lblEditarProdutos.setForeground(new Color(255, 255, 255));
		lblEditarProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane_1.add(lblEditarProdutos, "cell 3 4");
		

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLogo, "cell 1 0 2 6,alignx center,aligny center");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha1,"cell 1 7 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon("src/img/carrinho.png"));
		contentPane_1.add(lblCarrinho,  "cell 1 8,alignx left,aligny center");

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblVendas, "cell 2 8,alignx left,aligny center");

		JPanel panel = new JPanel();
		contentPane_1.add(panel, "cell 3 7 29 78,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][][grow][grow 50][grow][][][grow]",
				"[][][][][][grow][][][][][][grow][][][][][][grow][][][][grow][][][][][][grow][][][][][grow][][][][grow][][][][]"));

		JLabel lblvoltar = new JLabel("");
		lblvoltar.addMouseListener(produtoController.voltarListagem());
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblNome, "cell 1 3");

		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(90, 30));
		panel.add(txtNome, "cell 1 4 7 1,growx");
		txtNome.setColumns(10);

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblTamanho, "cell 1 8");

		comboBoxTamanho = new JComboBox();
		comboBoxTamanho.setPreferredSize(new Dimension(90, 30));
		panel.add(comboBoxTamanho, "cell 1 9 7 1,growx");
		comboBoxTamanho
				.setModel(new DefaultComboBoxModel(new String[] { "Selecione um Item", "PP", "P", "M", "G", "GG" }));

		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblGenero, "cell 1 13");

		comboBoxGenero = new JComboBox();
		comboBoxGenero.setPreferredSize(new Dimension(90, 30));
		panel.add(comboBoxGenero, "cell 1 14 7 1,growx");
		comboBoxGenero.setModel(
				new DefaultComboBoxModel(new String[] { "Selecione um Item", "Feminino", "Masculino", "Unissex" }));

		JLabel lblPreço = new JLabel("Preço");
		lblPreço.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblPreço, "cell 1 19");

		txtPreço = new JTextField();
		txtPreço.setPreferredSize(new Dimension(90, 30));
		panel.add(txtPreço, "cell 1 20 7 1,growx");
		txtPreço.setColumns(10);

		JLabel lblfornecedor = new JLabel("Fornecedor");
		lblfornecedor.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblfornecedor, "cell 1 24");

		comboBoxFornecedor = new JComboBox();
		comboBoxFornecedor.setPreferredSize(new Dimension(90, 30));
		panel.add(comboBoxFornecedor, "cell 1 25 7 1,growx");

		JLabel lblQuantidadeEstoque = new JLabel("Quantida em Estoque");
		lblQuantidadeEstoque.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblQuantidadeEstoque, "cell 1 29");

		txtQuantidadeEstoque = new JTextField();
		txtQuantidadeEstoque.setPreferredSize(new Dimension(90, 30));
		panel.add(txtQuantidadeEstoque, "cell 1 30 7 1,growx");
		txtQuantidadeEstoque.setColumns(10);

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha,"cell 1 9 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCaixa, "cell 1 10,alignx left,aligny center");

		lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblProdutos,  "cell 2 10,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha2,  "cell 1 11 2 1");

		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCliente, "cell 1 12,alignx left,aligny center");

		lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblClientes, "cell 2 12");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha3, "cell 1 13 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCaminhao,"cell 1 15,alignx left,aligny center");

		lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblFornecedor, "cell 2 15");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha4, "cell 1 16 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblFuncionario, "cell 1 17,alignx left,aligny center");

		lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblFuncionarios, "cell 2 17");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha5, "cell 1 19 2 1");

		JButton btnSair = new JButton("  Sair  ");
		btnSair.addActionListener(produtoController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnSair, "cell 2 83 1 4,aligny bottom");

		JButton btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setForeground(Color.RED);
		btnLimpar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimpar.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnLimpar, "cell 25 85 1 4,aligny center");

		JButton btnSalvarEdicoes = new JButton("Salvar Edições");
		btnSalvarEdicoes.addActionListener(produtoController.salvarEdicoes());
		btnSalvarEdicoes.setForeground(Color.RED);
		btnSalvarEdicoes.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSalvarEdicoes.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnSalvarEdicoes, "cell 28 85 1 4,aligny center");

	}

}
