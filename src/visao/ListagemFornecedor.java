package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.FornecedorController;
import net.miginfocom.swing.MigLayout;

public class ListagemFornecedor extends JFrame implements TelaInterna{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table;
	public JTextField txtId;
	public JTextField txtCnpj;
	public JTextField txtEmail;
	public JTextField txtNome;
	FornecedorController fornecedorController;
	private JLabel lblFornecedor;
	private JLabel lblProdutos;
	private JLabel lblClientes;
	private JLabel lblFuncionarios;
	private JLabel lblVendas;

	public ListagemFornecedor(FornecedorController fornecedorController) {
		this.fornecedorController = fornecedorController;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 853);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblListagemFunc = new JLabel("Listagem de Fornecedores");
		lblListagemFunc.setForeground(new Color(255, 255, 255));
		lblListagemFunc.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblListagemFunc, "cell 3 4");

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

		txtId = new JTextField();

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 3 8 21 72,grow");
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][grow][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblId = new JLabel("ID");
		lblId.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblId, "cell 2 0");

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNome, "cell 5 0");

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblCnpj, "cell 8 0");

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblEmail, "cell 11 0");

		JLabel lblPesquisar = new JLabel("Pesquisar por : ");
		lblPesquisar.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		panel.add(lblPesquisar, "cell 1 1,alignx trailing");

		JLabel lupa1 = new JLabel("");
		lupa1.addMouseListener(fornecedorController.pesquisaLupaFornecedor("id_Fornecedor", txtId));

		txtId.setColumns(10);
		panel.add(txtId, "cell 2 1,growx");
		lupa1.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa1, "cell 3 1,alignx trailing");

		txtNome = new JTextField();
		panel.add(txtNome, "cell 5 1,growx");
		txtNome.setColumns(10);

		JLabel lupa2 = new JLabel("");
		lupa2.addMouseListener(fornecedorController.pesquisaLupaFornecedor("nome_Fornecedor", txtNome));

		lupa2.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa2, "cell 6 1");

		txtCnpj = new JTextField();
		panel.add(txtCnpj, "cell 8 1,growx");
		txtCnpj.setColumns(10);

		JLabel lupa3 = new JLabel("");
		lupa3.addMouseListener(fornecedorController.pesquisaLupaFornecedor("cnpj", txtCnpj));
		lupa3.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa3, "cell 9 1");

		txtEmail = new JTextField();
		panel.add(txtEmail, "cell 11 1,growx");
		txtEmail.setColumns(10);

		JLabel lupa4 = new JLabel("");
		lupa3.addMouseListener(fornecedorController.pesquisaLupaFornecedor("email_Fornecedor", txtEmail));
		lupa4.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa4, "cell 12 1");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 1 3 14 14,grow");

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CNPJ", "Email", "Telefone" }));
		scrollPane.setViewportView(table);

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

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(fornecedorController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 78 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Adicionar Fornecedor");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedorController.cadastroFornecedor();
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(fornecedorController.buscaFornecedor());
		btnEditar.setForeground(new Color(255, 0, 0));
		btnEditar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnEditar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnEditar, "cell 19 80 1 2");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(fornecedorController.excluirFornecedor());
		btnExcluir.setForeground(new Color(255, 0, 0));
		btnExcluir.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnExcluir.setBackground(new Color(255, 255, 255));
		contentPane.add(btnExcluir, "cell 20 80 1 2");

		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 21 80 1 2,aligny bottom");
	}

	public JLabel getLabelFornecedor() {
		// TODO Auto-generated method stub
		return lblFornecedor;
	}

	public JLabel getLabelCliente() {
		// TODO Auto-generated method stub
		return lblClientes;
	}

	public JLabel getLabelFuncionario() {
		// TODO Auto-generated method stub
		return lblFuncionarios;
	}

	public JLabel getLabelVendas() {
		// TODO Auto-generated method stub
		return lblVendas;
	}

	public JLabel getLabelProduto() {
		// TODO Auto-generated method stub
		return lblProdutos;
	}
}
