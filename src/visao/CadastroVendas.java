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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ProdutoController;
import controle.VendaController;
import modelo.Fornecedor;
import modelo.Produto;
import net.miginfocom.swing.MigLayout;

public class CadastroVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCPFcliente;
	public JTable table;
	public JComboBox<Object> comboBoxProd;
	public JSpinner spinnerQntd;

	public CadastroVendas(VendaController vendaController) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 1607);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69, 35));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

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

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][grow][][][][][][][][][][][][][][grow][][][][grow][]",
				"[grow][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblvoltar = new JLabel("");
		lblvoltar.addMouseListener(vendaController.voltarListagem());
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT)));
		contentPane.add(lblvoltar, "cell 0 1");

		JLabel lblCadVenda = new JLabel("Cadastro de Vendas");
		lblCadVenda.setForeground(new Color(0, 0, 0));
		lblCadVenda.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblCadVenda, "cell 3 2");

		JLabel lblFunc = new JLabel("Funcionário: ");
		lblFunc.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblFunc, "cell 20 2,alignx center,growy");

		JLabel lblNomeFunc = new JLabel("Joao BlaBla");
		lblNomeFunc.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
		contentPane.add(lblNomeFunc, "cell 21 2,alignx left,aligny center");

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblProduto, "cell 2 9");

		comboBoxProd = new JComboBox<>();
		comboBoxProd.setPreferredSize(new Dimension(100, 30));
		contentPane.add(comboBoxProd, "cell 2 10 2 1,growx");
		ProdutoController produtoController = new ProdutoController();
		
		ArrayList<Produto> produtos = null;
		try {
			produtos = produtoController.buscarTodosProdutos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Produto produto : produtos) {
			comboBoxProd.addItem(produto);
		}
		
		JLabel lblQntd = new JLabel("Quantidade:");
		lblQntd.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblQntd, "cell 7 9");

		spinnerQntd = new JSpinner();
		spinnerQntd.setPreferredSize(new Dimension(100, 30));
		contentPane.add(spinnerQntd, "cell 7 10");

		JLabel cpfCliente = new JLabel("CPF Cliente:");
		cpfCliente.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(cpfCliente, "cell 17 9,alignx left");

		txtCPFcliente = new JTextField();
		txtCPFcliente.setPreferredSize(new Dimension(100, 30));
		contentPane.add(txtCPFcliente, "cell 17 10 3 1,growx");
		txtCPFcliente.setColumns(10);

		JLabel lblNomeCliente = new JLabel("nome cliente adicionado ao ok");
		lblNomeCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblNomeCliente, "cell 18 9,alignx left,growy");

		JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
		btnAdicionar.addActionListener(vendaController.adicionarCarrinho());
		btnAdicionar.setPreferredSize(new Dimension(100, 30));
		btnAdicionar.setForeground(Color.RED);
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 17));
		btnAdicionar.setBackground(Color.WHITE);
		contentPane.add(btnAdicionar, "cell 12 10,grow");

		JButton btnOK = new JButton("OK");
		btnOK.setPreferredSize(new Dimension(50, 30));
		btnOK.setForeground(Color.RED);
		btnOK.setFont(null);
		btnOK.setBackground(Color.WHITE);
		btnOK.setFont(fontBold.deriveFont(Font.PLAIN, 17));
		contentPane.add(btnOK, "cell 20 10,alignx left,growy");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 400)); // Tamanho
		contentPane.add(scrollPane, "cell 2 12 19 25, grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Venda", "Produto", "Quantidade", "Preço Un.", "Total" }));
		table.setPreferredScrollableViewportSize(new Dimension(800, 400));// Tamanho

		scrollPane.setViewportView(table);

		JButton btnExcluir = new JButton("Excluir Produto");
		btnExcluir.setForeground(Color.RED);
		btnExcluir.setFont(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(fontBold.deriveFont(Font.PLAIN, 22));
		contentPane.add(btnExcluir, "cell 2 37,grow");

		JLabel lblTotal = new JLabel("Total (R$): ");
		lblTotal.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		contentPane.add(lblTotal, "cell 2 79,alignx left");

		JLabel lblPreco = new JLabel("00.00");
		lblPreco.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		contentPane.add(lblPreco, "cell 3 79,alignx left");
		btnAdicionar.setPreferredSize(new Dimension(100, 30));

		JButton btnCancelar = new JButton("Cancelar");
		// btnExcluir.addActionListener(clienteController.excluirCliente());
		btnCancelar.setForeground(new Color(255, 0, 0));
		btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnCancelar, "cell 20 79,grow");

		// cadastrar funcionario
		JButton btnFinalizarVenda = new JButton("Finalizar Venda");
		btnFinalizarVenda.setForeground(new Color(255, 0, 0));
		btnFinalizarVenda.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnFinalizarVenda.setBackground(new Color(255, 255, 255));
		contentPane.add(btnFinalizarVenda, "cell 21 79,grow");

	}
	
	public JSpinner getSpinnerQntd() {
	    return spinnerQntd;
	}



}
