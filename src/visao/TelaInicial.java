package visao;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Funcionario;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {

		
		
		// fonte
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
		setBounds(100, 100, 905, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][300][grow]", "[grow][][][][][][][][][][][][][][][grow]"));

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(459, 237, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 1 0 2 1,alignx center");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha1, "cell 1 2 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon(
				new ImageIcon("src/img/carrinho.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
		contentPane.add(lblCarrinho, "cell 1 3");

		TelaInicial janelaInicial = this;
		
		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setFont(fontRegular.deriveFont(Font.PLAIN, 24));
		lblVendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// tela inicial para abrir em vendas
			}
		});
		lblVendas.setForeground(new Color(255, 255, 255));
		contentPane.add(lblVendas, "cell 2 3,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 1 4 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaixa, "cell 1 5");

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(fontRegular.deriveFont(Font.PLAIN, 24));
		lblProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListagemProdutos janelaInicial = new ListagemProdutos();
				janelaInicial.setVisible(true);
				dispose();
			}
		});
		lblProdutos.setForeground(new Color(255, 255, 255));
		contentPane.add(lblProdutos, "cell 2 5,alignx left,aligny center");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 1 6 2 1");

		JLabel lblClientes = new JLabel(" ");
		lblClientes.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
		contentPane.add(lblClientes, "cell 1 7");

		JLabel lblCliente = new JLabel("Clientes");
		lblCliente.setFont(fontRegular.deriveFont(Font.PLAIN, 24));
		lblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// tela inicial para abrir em clientes
			}
		});
		lblCliente.setForeground(new Color(255, 255, 255));
		contentPane.add(lblCliente, "cell 2 7,alignx left,aligny center");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha4, "cell 1 8 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaminhao, "cell 1 9");

		JLabel lblFornecedores = new JLabel("Fornecedores");
		lblFornecedores.setFont(fontRegular.deriveFont(Font.PLAIN, 24));
		lblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// tela inicial para abrir em fornecedores
			}
		});
		lblFornecedores.setForeground(new Color(255, 255, 255));
		contentPane.add(lblFornecedores, "cell 2 9,alignx left,aligny center");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 10 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		contentPane.add(lblFuncionario, "cell 1 11");


		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setFont(fontRegular.deriveFont(Font.PLAIN, 24));
		lblFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ListagemFuncionarios janelaInicial = new ListagemFuncionarios();
				janelaInicial.setVisible(true);
				dispose();

				// tela inicial para abrir em Funcionarios
			}
		});
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		contentPane.add(lblFuncionarios, "cell 2 11,alignx left,aligny center");

		JLabel lblLinha6 = new JLabel("");
		lblLinha6.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(500, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha6, "cell 1 12 2 1");
	}

}
