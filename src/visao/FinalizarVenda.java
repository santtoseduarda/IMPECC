package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.VendaController;
import net.miginfocom.swing.MigLayout;

public class FinalizarVenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;
	private JLabel lblNome;
	private JLabel lblCpfValor;
	private JLabel lblTotalValor;
	private JComboBox<String> comboBox; // Adicionando referência ao JComboBox
    private JButton btnFinalizarVenda;

	/**
	 * Create the frame.
	 */
	public FinalizarVenda(VendaController vendaController) {
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
		lblCarrinho.setIcon(new ImageIcon("src/img/carrinho.png"));
		contentPane.add(lblCarrinho, "cell 1 3");

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 2 3,alignx left,aligny center");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255)); // Fundo branco para o painel
		panel.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		// Título do painel
		JLabel lblTitulo = new JLabel("Finalizar Venda");
		lblTitulo.setFont(fontBold.deriveFont(Font.PLAIN, 30));
		panel.add(lblTitulo, "cell 0 0 3 1,alignx center, gapy 20");

		// Adicionar o painel ao contentPane
		contentPane.add(panel, "cell 3 2 30 83,grow");

		// Informações do Cliente
		JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
		lblNomeCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblNomeCliente, "cell 0 14,alignx left,gapx 10");

		lblNome = new JLabel(""); // Campo para exibir o nome do cliente
		lblNome.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblNome, "cell 1 14,width 200!,alignx left");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblCpf, "cell 0 19,alignx left,gapx 10");

		lblCpfValor = new JLabel(""); // Campo para exibir o CPF do cliente
		lblCpfValor.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblCpfValor, "cell 1 19,width 200!,alignx left");

		// Total da Venda
		JLabel lblTotal = new JLabel("Total (R$):");
		lblTotal.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblTotal, "cell 0 23,alignx left,gapx 10");

		lblTotalValor = new JLabel(""); // Campo para exibir o total da venda
		lblTotalValor.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblTotalValor, "cell 1 23,width 200!,alignx left");

		// Método de Pagamento
		JLabel lblMetPag = new JLabel("Método de Pagamento:");
		lblMetPag.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(lblMetPag, "cell 0 27,alignx left,gapx 10");

		comboBox = new JComboBox<>(); // Inicializando o JComboBox
		comboBox.setModel(new DefaultComboBoxModel<>(
				new String[] { "Cartão de Crédito", "Cartão de Débito", "Dinheiro", "Pix" }));
		comboBox.setFont(fontRegular.deriveFont(Font.PLAIN, 20));
		panel.add(comboBox, "cell 1 27,width 200!,alignx left");

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
		btnSair.addActionListener(vendaController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(vendaController.cancelar());
		btnCancelar.setForeground(Color.RED);
		btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnCancelar, "cell 22 85 1 4,aligny center");

		btnFinalizarVenda = new JButton("Finalizar");
		btnFinalizarVenda.addActionListener(vendaController.finalizarVenda());
		btnFinalizarVenda.setForeground(Color.RED);
		btnFinalizarVenda.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnFinalizarVenda.setBackground(new Color(255, 255, 255));
		contentPane.add(btnFinalizarVenda, "cell 28 85 1 4,aligny center");

	}

	 public void setClienteInfo(String nomeCliente, String cpfCliente) {
	        lblNome.setText(nomeCliente);
	        lblCpfValor.setText(cpfCliente);
	    }

	    public void setTotalVenda(String totalVenda) {
	        lblTotalValor.setText(totalVenda);
	    }

	    public String getFormaPagamento() {
	        return (String) comboBox.getSelectedItem();
	    }

	    public void addFinalizarVendaListener(ActionListener listener) {
	        btnFinalizarVenda.addActionListener(listener);
	    }
}