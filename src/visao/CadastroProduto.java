package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.FornecedorController;
import controle.FornecedorDAO;
import controle.FuncionarioDAO;
import controle.ProdutoController;
import controle.ProdutoDAO;
import modelo.Fornecedor;
import modelo.Funcionario;
import modelo.Produto;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

public class CadastroProduto extends JFrame implements TelaInterna {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtPreco;
	public JTextField txtQntdEstoque;
	public JComboBox<Fornecedor> comboBoxFornecedor;
	public JComboBox comboBoxGenero;
	public JComboBox comboBoxTamanho;
	public JTextField txtNomeProduto;
	private JLabel lblProdutos;
	private JLabel lblClientes;
	private JLabel lblFuncionarios;
	private JLabel lblFornecedor;
	private JLabel lblVendas;

	public CadastroProduto(ProdutoController produtoController) {
		
		
		setTitle("Cadastro Produto");
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblCadastroProduto = new JLabel("Cadastro de Produtos");
		lblCadastroProduto.setForeground(new Color(255, 255, 255));
		lblCadastroProduto.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblCadastroProduto, "cell 3 1");

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 1 1 2 1");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha1, "cell 1 2 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon("src/img/carrinho.png"));
		contentPane.add(lblCarrinho, "cell 1 3");

		lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 2 3,alignx left,aligny center");

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 3 2 27 83,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][][grow][grow 50][grow][][][grow]",
				"[][][][][][grow][][][][][][grow][][][][][][grow][][][][grow][][][][][][grow][][][][][grow][][][][grow][][][][]"));

		JLabel lblvoltar = new JLabel("");
		lblvoltar.addMouseListener(produtoController.voltarListagem());

		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");

		JLabel lblNome = new JLabel("Nome Produto:*");
		lblNome.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblNome, "cell 1 3,growx,aligny bottom");

		txtNomeProduto = new JTextField();
		txtNomeProduto.setPreferredSize(new Dimension(90, 30));
		panel.add(txtNomeProduto, "cell 1 5 7 1,growx,aligny bottom");
		txtNomeProduto.setColumns(10);

		JLabel lblTamanho = new JLabel("Tamanho:*");
		lblTamanho.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblTamanho, "cell 1 11,growx,aligny bottom");

		comboBoxTamanho = new JComboBox();
		comboBoxTamanho.setPreferredSize(new Dimension(90, 30));
		comboBoxTamanho.setModel(new DefaultComboBoxModel(new String[] { "Selecione um Item", "PP", "P", "M", "G", "GG" }));
		panel.add(comboBoxTamanho, "cell 1 13 7 1,growx");

		JLabel lblGenero = new JLabel("Gênero:*");
		lblGenero.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblGenero, "cell 1 15,growx,aligny bottom");

		comboBoxGenero = new JComboBox();
		comboBoxGenero.setPreferredSize(new Dimension(90, 30));
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] { "Selecione um Item", "Feminino", "Masculino", "Unissex" }));
		panel.add(comboBoxGenero, "cell 1 17 7 1,growx");

		JLabel lblPreco = new JLabel("Preço:*");
		lblPreco.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblPreco, "cell 1 19,growx,aligny bottom");

		txtPreco = new JTextField();
		txtPreco.setPreferredSize(new Dimension(90, 30));
		txtPreco.setColumns(10);
		panel.add(txtPreco, "cell 1 21 7 1,growx,aligny bottom");

		JLabel lblForn = new JLabel("Fornecedor:*");
		lblForn.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblForn, "cell 1 23,growx,aligny bottom");

		comboBoxFornecedor = new JComboBox<>();
		comboBoxFornecedor.setPreferredSize(new Dimension(90, 30));
		FornecedorController fornecedorController = new FornecedorController();

		ArrayList<Fornecedor> fornecedores = null;
		try {
			fornecedores = fornecedorController.buscarTodosFornecedores();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Fornecedor fornecedor : fornecedores) {
			comboBoxFornecedor.addItem(fornecedor);
		}

		panel.add(comboBoxFornecedor, "cell 1 25 7 1,growx");

		JLabel lblQntdEstoque = new JLabel("Quantidade em estoque:*");
		lblQntdEstoque.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		panel.add(lblQntdEstoque, "cell 1 27,growx,aligny bottom");

		txtQntdEstoque = new JTextField();
		txtQntdEstoque.setPreferredSize(new Dimension(90, 30));
		txtQntdEstoque.setColumns(10);
		panel.add(txtQntdEstoque, "cell 1 29 7 1,growx,aligny bottom");

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha, "cell 1 4 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaixa, "cell 1 5");

		lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblProdutos, "cell 2 5,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 1 6 2 1");

		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCliente, "cell 1 7,alignx left,aligny center");

		lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblClientes, "cell 2 7");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 1 9 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaminhao, "cell 1 10,alignx left,aligny center");

		lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFornecedor, "cell 2 10");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha4, "cell 1 12 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblFuncionario, "cell 1 13,alignx left,aligny center");

		lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 2 13");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 15 2 1");

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(produtoController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 28 85 1 4,aligny center");
		btnAdicionar.addActionListener(produtoController.adicionarProduto());

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(produtoController.limparCampos());
		btnLimparCampos.addActionListener(produtoController.limparCampos());
		btnLimparCampos.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimparCampos.setForeground(Color.RED);
		btnLimparCampos.setBackground(Color.WHITE);
		contentPane.add(btnLimparCampos, "cell 25 85 1 4,aligny center");

	}

	@Override
	public JLabel getLabelFornecedor() {
		// TODO Auto-generated method stub
		return lblFornecedor;
		
	}

	@Override
	public JLabel getLabelCliente() {
		// TODO Auto-generated method stub
		return lblClientes;
	}

	@Override
	public JLabel getLabelFuncionario() {
		// TODO Auto-generated method stub
		return lblFuncionarios;
	}

	@Override
	public JLabel getLabelVendas() {
		// TODO Auto-generated method stub
		return lblVendas;
	}

	@Override
	public JLabel getLabelProduto() {
		// TODO Auto-generated method stub
		return lblProdutos;
	}
}
