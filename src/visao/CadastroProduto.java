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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.FornecedorDAO;
import controle.FuncionarioDAO;
import controle.ProdutoDAO;
import modelo.Fornecedor;
import modelo.Funcionario;
import modelo.Produto;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtPreco;
	private JTextField txtQntdEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
		CadastroProduto janelaCadastroProduto = this;
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
		lblCarrinho.setIcon(new ImageIcon("C:\\Users\\Aluno\\Downloads\\IMPECC\\src\\img\\carrinho.png"));
		contentPane.add(lblCarrinho, "cell 1 3");

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 2 3,alignx left,aligny center");

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 3 2 27 83,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][][grow][grow 50][grow][][][grow]",
				"[][grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][grow][grow]"));

		JLabel lblvoltar = new JLabel("");
		lblvoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListagemProdutos novaJanela = new ListagemProdutos();
				novaJanela.setVisible(true);
				dispose();
			}
		});
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");

		JLabel lblNome = new JLabel("Nome Produto:*");
		panel.add(lblNome, "cell 1 3,growx,aligny bottom");

		JTextField txtNomeProduto = new JTextField();
		panel.add(txtNomeProduto, "cell 1 5 7 1,growx,aligny bottom");
		txtNomeProduto.setColumns(10);

		JLabel lblCodBarra = new JLabel("Codigo de Barra: *");
		panel.add(lblCodBarra, "cell 1 7,growx,aligny bottom");

		JTextField txtCodBarra = new JTextField();
		panel.add(txtCodBarra, "cell 1 9 7 1,growx,aligny bottom");
		txtCodBarra.setColumns(10);

		JLabel lblTamanho = new JLabel("Tamanho:*");
		panel.add(lblTamanho, "cell 1 11,growx,aligny bottom");

		JComboBox comboBoxTamanho = new JComboBox();
		comboBoxTamanho.setModel(new DefaultComboBoxModel(new String[] {"Selecione um Item", "PP", "P", "M", "G", "GG"}));
		panel.add(comboBoxTamanho, "cell 1 13 7 1,growx");

		JLabel lblGenero = new JLabel("Gênero:*");
		panel.add(lblGenero, "cell 1 15,growx,aligny bottom");

		JComboBox comboBoxGenero = new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"Selecione um Item", "Feminino", "Masculino", "Unissex"}));
		panel.add(comboBoxGenero, "cell 1 17 7 1,growx");

		JLabel lblPreco = new JLabel("Preço:*");
		panel.add(lblPreco, "cell 1 19,growx,aligny bottom");

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		panel.add(txtPreco, "cell 1 21 7 1,growx,aligny bottom");

		JLabel lblForn = new JLabel("Fornecedor:*");
		panel.add(lblForn, "cell 1 23,growx,aligny bottom");

		
		JComboBox comboBoxFornecedor = new JComboBox();
		comboBoxFornecedor.setModel(new DefaultComboBoxModel(new String[] {"tem", "que", "adicionar", "os", "fornecedores"}));
		panel.add(comboBoxFornecedor, "cell 1 25 7 1,growx");

		JLabel lblQntdEstoque = new JLabel("Quantidade em estoque:*");
		panel.add(lblQntdEstoque, "cell 1 27,growx,aligny bottom");

		txtQntdEstoque = new JTextField();
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

		JLabel lblProdutos = new JLabel("Produtos");
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

		JLabel lblClientes = new JLabel("Clientes");
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

		JLabel lblFornecedor = new JLabel("Fornecedores");
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

		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 2 13");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 15 2 1");

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaCadastroProduto, "Você realmente deseja sair?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					TelaLogin CadastroProduto = new TelaLogin(null);
					CadastroProduto.setVisible(true);
					dispose(); // Fecha a tela de login
				}

			}
		});
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 28 85 1 4,aligny center");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeProduto = txtNomeProduto.getText();
				String codBarra = txtCodBarra.getText();
				String tamanho = (String)comboBoxTamanho.getSelectedItem();
				String genero = (String)comboBoxGenero.getSelectedItem();
				String preco = txtPreco.getText();
				String fornecedor = (String)comboBoxFornecedor.getSelectedItem();
				String qntEstoque = txtQntdEstoque.getText();
				
				if (nomeProduto.isEmpty() || codBarra.isEmpty() || tamanho==null || genero==null || preco.isEmpty()
						|| fornecedor==null || qntEstoque.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Todos os campos obrigatórios (*) devem ser preenchidos!", "Erro de cadastro", 
							javax.swing.JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						//conversao dos valores
						float precoConvert = Float.parseFloat(preco);
			            int qntEstoqueConvert = Integer.parseInt(qntEstoque);
			            int codBarraConvert = Integer.parseInt(codBarra);
						
						Produto cadastro = new Produto();

						cadastro.setNomeProduto(nomeProduto);
						cadastro.setCodBarra(codBarraConvert);
						cadastro.setTamanho(tamanho);
						cadastro.setGenero(genero);
						cadastro.setPreco(precoConvert);
						cadastro.setFornecedor(fornecedor);
						cadastro.setQtdEstoque(qntEstoqueConvert); 

						ProdutoDAO novoProduto = new ProdutoDAO();
						ProdutoDAO.getInstancia();
						novoProduto.inserir(cadastro);
						
						ListagemProdutos janelaCadastro = new ListagemProdutos();
						janelaCadastro.setVisible(true);
						dispose();
					}catch (NumberFormatException ex) {
		                
		                javax.swing.JOptionPane.showMessageDialog(null,
		                        "Preencha os campos de Preço e Quantidade corretamente (somente números).", 
		                        "Erro de cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
		            }
				}
			}
		});

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        txtNomeProduto.setText("");
		        txtCodBarra.setText("");
		        txtPreco.setText("");
		        txtQntdEstoque.setText("");
		        
		        //limpar do combobox
		        comboBoxTamanho.setSelectedItem(null);
		        comboBoxGenero.setSelectedItem(null);
		        comboBoxFornecedor.setSelectedItem(null);
			}
		});
		btnLimparCampos.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimparCampos.setForeground(Color.RED);
		btnLimparCampos.setBackground(Color.WHITE);
		contentPane.add(btnLimparCampos, "cell 25 85 1 4,aligny center");

	}
}
