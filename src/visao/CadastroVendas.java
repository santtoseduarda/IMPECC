package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.VendaController;
import net.miginfocom.swing.MigLayout;

public class CadastroVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

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
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][][][][grow][][][][grow][][grow][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblCadVenda = new JLabel("Cadastro de Vendas");
		lblCadVenda.setForeground(new Color(0, 0, 0));
		lblCadVenda.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblCadVenda, "cell 3 2");

		JLabel lblFunc = new JLabel("Funcionário: ---");
		lblFunc.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFunc, "cell 20 3,alignx center,growy");

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblProduto, "cell 2 7");

		JLabel lblQntd = new JLabel("Quantidade:");
		lblQntd.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblQntd, "cell 6 7");

		JLabel cpfCliente = new JLabel("CPF Cliente:");
		cpfCliente.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(cpfCliente, "cell 15 7,alignx left");

		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 2 8 2 1,growx");

		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(100, 30));
		contentPane.add(spinner, "cell 6 8");

		JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
		btnAdicionar.setPreferredSize(new Dimension(100, 30));
		btnAdicionar.setForeground(Color.RED);
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 17));
		btnAdicionar.setBackground(Color.WHITE);
		contentPane.add(btnAdicionar, "cell 9 8");

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 30));
		contentPane.add(textField, "cell 15 8 4 1,growx");
		textField.setColumns(10);

		// sair
		JButton btnSair = new JButton("Sair");
		// btnSair.addActionListener(clienteController.sairSistema());
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 78 1 4,aligny bottom");

		// cadastrar funcionario
		JButton btnAdicionarss = new JButton("Adicionar Cliente");
		btnAdicionarss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clienteController.iniciarCadastroCliente();
			}
		});
		btnAdicionarss.setForeground(new Color(255, 0, 0));
		btnAdicionarss.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionarss.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionarss, "cell 21 80 1 2,aligny bottom");

		// editar
		JButton btnEditar = new JButton("Editar");
		// btnEditar.addActionListener(clienteController.buscaCliente());

		btnEditar.setForeground(new Color(255, 0, 0));
		btnEditar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnEditar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnEditar, "cell 19 80 1 2");

		// excluir
		JButton btnExcluir = new JButton("Excluir");
		// btnExcluir.addActionListener(clienteController.excluirCliente());
		btnExcluir.setForeground(new Color(255, 0, 0));
		btnExcluir.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnExcluir.setBackground(new Color(255, 255, 255));
		contentPane.add(btnExcluir, "cell 20 80 1 2");

	}

}
