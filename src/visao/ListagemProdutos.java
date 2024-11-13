package visao;

import java.awt.Color;
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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.FuncionarioController;
import controle.FuncionarioDAO;
import controle.ProdutoController;
import controle.ProdutoDAO;
import modelo.Produto;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;

public class ListagemProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCodBarra;
	private JTextField txtTamanho;
	private JTextField txtGenero;
	private JTextField txtFornecedor;
	public JTable table;

	public ListagemProdutos(ProdutoController produtoController) {

		ListagemProdutos janelaListagemProdutos = this;

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
		setBounds(100, 100, 1054, 853);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);


		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblListagemProduto = new JLabel("Listagem de Produtos");
		lblListagemProduto.setForeground(new Color(255, 255, 255));
		lblListagemProduto.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblListagemProduto, "cell 4 4");

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 2 0 2 6,alignx center,aligny center");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha1, "cell 2 7 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon(
				new ImageIcon("src/img/carrinho.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCarrinho, "cell 2 8,alignx left,aligny center");

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 3 8,alignx left,aligny center");

		JPanel panel = new JPanel();
		/*panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pesquisarPorCampo("id_Produto", txtId.getText());
			}
		});*/
		contentPane.add(panel, "cell 4 8 21 73,grow");
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][grow]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblId = new JLabel("ID");
		lblId.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblId, "cell 1 0");

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNome, "cell 4 0");

		JLabel lbCodBarra = new JLabel("Cod. Barra");
		lbCodBarra.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lbCodBarra, "cell 7 0");

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblTamanho, "cell 10 0");

		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblGenero, "cell 13 0");

		JLabel lblFornecedores = new JLabel("Fornecedor");
		lblFornecedores.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblFornecedores, "cell 16 0");

		JLabel lblPesquisar = new JLabel("Pesquisar por : ");
		lblPesquisar.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		panel.add(lblPesquisar, "cell 0 1,alignx trailing");

		txtId = new JTextField();
		panel.add(txtId, "cell 1 1,growx");
		txtId.setColumns(10);

		JLabel lupaId = new JLabel("");
		lupaId.addMouseListener(produtoController.pesquisa("id_Fornecedor", txtId));
		lupaId.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupaId, "cell 2 1,alignx trailing");

		txtNome = new JTextField();
		panel.add(txtNome, "cell 4 1,growx");
		txtNome.setColumns(10);

		JLabel lupaNome = new JLabel("");
		lupaNome.addMouseListener(produtoController.pesquisa("nome_Produto", txtNome));
		lupaNome.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupaNome, "cell 5 1");

		txtCodBarra = new JTextField();
		panel.add(txtCodBarra, "cell 7 1,growx");
		txtCodBarra.setColumns(10);

		JLabel lupaCod = new JLabel("");
		lupaCod.addMouseListener(produtoController.pesquisa("codBarra", txtCodBarra));
		
		lupaCod.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupaCod, "cell 8 1");

		JTextField txtTamanho = new JTextField();
		panel.add(txtTamanho, "cell 10 1,growx");
		txtTamanho.setColumns(10);

		JLabel lupaTamanho = new JLabel("");
		lupaTamanho.addMouseListener(produtoController.pesquisa("tamanho", txtTamanho));
		
		lupaTamanho.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupaTamanho, "cell 11 1,alignx trailing");

		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		panel.add(txtGenero, "cell 13 1,growx");

		JLabel lupaGenero = new JLabel("");
		lupaGenero.addMouseListener(produtoController.pesquisa("genero", txtGenero));
		
		lupaGenero.setIcon(new ImageIcon(ListagemProdutos.class.getResource("/img/procurar.png")));
		panel.add(lupaGenero, "cell 14 1");

		txtFornecedor = new JTextField();
		txtFornecedor.setColumns(10);
		panel.add(txtFornecedor, "cell 16 1,growx");

		JLabel lupaFornecedor = new JLabel("");
		lupaFornecedor.addMouseListener(produtoController.pesquisa("fornecedor", txtFornecedor));

		lupaFornecedor.setIcon(new ImageIcon(ListagemProdutos.class.getResource("/img/procurar.png")));
		panel.add(lupaFornecedor, "cell 17 1");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 3 20 19,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(
		    new Object[][] {},
		    new String[] {
		        "ID", "Nome", "Cod. Barra", "Marca", "Tamanho", "Genero", "Preco", "Fornecedor", "Qnt. Estoque"
		    }
		));
		scrollPane.setViewportView(table);

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha, "cell 2 9 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaixa, "cell 2 10,alignx left,aligny center");

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblProdutos, "cell 3 10,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 2 11 2 1");

		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCliente, "cell 2 12,alignx left,aligny center");

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblClientes, "cell 3 12");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 2 13 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaminhao, "cell 2 15,alignx left,aligny center");

		JLabel lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFornecedor, "cell 3 15");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha4, "cell 2 16 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblFuncionario, "cell 2 17,alignx left,aligny center");

		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 3 17");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 2 19 2 1");

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(produtoController.sairSistema());
		// sair

		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 3 79 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.addActionListener(produtoController.cadastroProduto());

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(produtoController.buscaProduto());

		btnEditar.setForeground(new Color(255, 0, 0));
		btnEditar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnEditar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnEditar, "cell 20 81 1 2");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(produtoController.excluirProduto());
		btnExcluir.setForeground(new Color(255, 0, 0));
		btnExcluir.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnExcluir.setBackground(new Color(255, 255, 255));
		contentPane.add(btnExcluir, "cell 21 81 1 2");

		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 22 81 1 2,aligny bottom");

	}


}
