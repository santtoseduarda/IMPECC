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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FinalizarVenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarVenda frame = new FinalizarVenda();
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
	public FinalizarVenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1261, 2499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("FinalizarVenda");
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
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblFinalizarVenda = new JLabel("FinalizarVenda");
		lblFinalizarVenda.setForeground(new Color(255, 255, 255));
		lblFinalizarVenda.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblFinalizarVenda, "cell 3 1");

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
		contentPane.add(panel, "cell 3 2 30 83,grow");
		panel.setLayout(new MigLayout("", "[grow][][][][][][][][][][][][grow][grow][grow]", "[grow][][][][][][][][][][][][][][][][][][grow 25][][][][][][][][][][][][][][][][][][][grow][][][][]"));

		JLabel lblvoltar = new JLabel("");
	//	lblvoltar.addMouseListener(clienteController.voltarListagem());
		lblvoltar.setIcon(new ImageIcon(
				new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblvoltar, "cell 0 0");
																				
																						JLabel lblNomeCliente = new JLabel("NomeCliente");
																						lblNomeCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
																						panel.add(lblNomeCliente, "cell 5 2 6 1,alignx left,aligny center");
																		
																				JLabel lblNome = new JLabel("");
																				lblNome.setPreferredSize(new Dimension(90, 30));
																				panel.add(lblNome, "cell 5 4 6 1");
																
																		JLabel lblCpf1 = new JLabel("CPF");
																		lblCpf1.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
																		panel.add(lblCpf1, "cell 5 7 7 1,alignx left,aligny center");
														
																JLabel lblCpf = new JLabel("");
																lblCpf.setPreferredSize(new Dimension(90, 30));
																panel.add(lblCpf, "cell 5 9 7 1");
												
														JLabel lblTotal = new JLabel("Total(R$):");
														lblTotal.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
														panel.add(lblTotal, "cell 5 12 7 1,alignx left,aligny center");
										
												JLabel lblTotalPreco = new JLabel("");
												lblTotalPreco.setPreferredSize(new Dimension(90, 30));
												panel.add(lblTotalPreco, "cell 5 14 7 1");
								
										JLabel lblMetPag = new JLabel("Métodos De Pagamento");
										lblMetPag.setFont(fontRegular.deriveFont(Font.PLAIN, 22));
										panel.add(lblMetPag, "cell 5 17 7 1,alignx left,aligny center");
										
												JComboBox comboBox = new JComboBox();
												comboBox.setPreferredSize(new Dimension(90, 30));
												comboBox.setModel(new DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Dinheiro\t", "Pix" }));
												panel.add(comboBox, "cell 5 19 7 1,alignx left,aligny center");

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
		//btnSair.addActionListener(ClienteController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		btnCancelar = new JButton("Cancelar");
		// btnLimparCampos.addActionListener(clienteController.limparCamposCadClientes());
		btnCancelar.setForeground(Color.RED);
		btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnCancelar, "cell 25 85 1 4,aligny center");

		JButton btnFinalizarVenda = new JButton("Finalizar");
	//	btnFinalizarVenda.addActionListener(clienteController.cadastrarCliente());
		btnFinalizarVenda.setForeground(Color.RED);
		btnFinalizarVenda.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnFinalizarVenda.setBackground(new Color(255, 255, 255));
		contentPane.add(btnFinalizarVenda, "cell 28 85 1 4,aligny center");

	}
}
