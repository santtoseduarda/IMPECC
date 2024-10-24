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

import controle.FuncionarioDAO;
import controle.ProdutoDAO;
import modelo.Funcionario;
import modelo.Produto;
import net.miginfocom.swing.MigLayout;

public class AlterarProduto extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField txtFornecedor;
	private JTextField txtQuantidadeEstoque;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarProduto frame = new AlterarProduto(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlterarProduto(Produto p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		AlterarProduto janelaAlterarProduto = this;

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
		contentPane_1.setLayout(new MigLayout("",
				"[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));

		JLabel lblEditarProdutos = new JLabel("Editar Dados do Produto");
		lblEditarProdutos.setForeground(new Color(255, 255, 255));
		lblEditarProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblEditarProdutos, "cell 3 1");

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLogo, "cell 1 1 2 1");

		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha1, "cell 1 2 2 1");

		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon("C:\\Users\\Aluno\\Downloads\\IMPECC\\src\\img\\carrinho.png"));
		contentPane_1.add(lblCarrinho, "cell 1 3");

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblVendas, "cell 2 3,alignx left,aligny center");

		JPanel panel = new JPanel();
		contentPane_1.add(panel, "cell 3 2 29 83,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][][grow][grow 50][grow][][][grow]",
				"[][][][][][grow][][][][][][grow][][][][][grow][][][][grow][][][][][][grow][][][][][grow][][][][grow][][][][]"));

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

		JLabel lblNome = new JLabel("Nome");
		panel.add(lblNome, "cell 1 3");

		JTextField txtNome = new JTextField();
		panel.add(txtNome, "cell 1 4 7 1,growx");
		txtNome.setColumns(10);

		JLabel lblTamanho = new JLabel("Tamanho");
		panel.add(lblTamanho, "cell 1 8");

		JTextField txtTamanho = new JTextField();
		panel.add(txtTamanho, "cell 1 9 7 1,growx");
		txtTamanho.setColumns(10);

		JLabel lblGenero = new JLabel("Gênero");
		panel.add(lblGenero, "cell 1 13");

		JTextField txtGenero = new JTextField();
		panel.add(txtGenero, "cell 1 14 7 1,growx");
		txtGenero.setColumns(10);

		JLabel lblPreço = new JLabel("Preço");
		panel.add(lblPreço, "cell 1 18");

		JTextField txtPreço = new JTextField();
		panel.add(txtPreço, "cell 1 19 7 1,growx");
		txtPreço.setColumns(10);

		JLabel lblfornecedor = new JLabel("Fornecedor");
		panel.add(lblfornecedor, "cell 1 23");

		txtFornecedor = new JTextField();
		panel.add(txtFornecedor, "cell 1 24 7 1,growx");
		txtFornecedor.setColumns(10);

		JLabel lblQuantidadeEstoque = new JLabel("Quantida em Estoque");
		panel.add(lblQuantidadeEstoque, "cell 1 28");

		txtQuantidadeEstoque = new JTextField();
		panel.add(txtQuantidadeEstoque, "cell 1 29 7 1,growx");
		txtQuantidadeEstoque.setColumns(10);

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha, "cell 1 4 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCaixa, "cell 1 5");

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setForeground(new Color(255, 255, 255));
		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblProdutos, "cell 2 5,alignx left,aligny center");

		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha2, "cell 1 6 2 1");

		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(
				new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCliente, "cell 1 7,alignx left,aligny center");

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblClientes, "cell 2 7");

		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha3, "cell 1 9 2 1");

		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(
				new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblCaminhao, "cell 1 10,alignx left,aligny center");

		JLabel lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblFornecedor, "cell 2 10");

		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha4, "cell 1 12 2 1");

		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(
				new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblFuncionario, "cell 1 13,alignx left,aligny center");

		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane_1.add(lblFuncionarios, "cell 2 13");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane_1.add(lblLinha5, "cell 1 15 2 1");

		JButton btnSair = new JButton("  Sair  ");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaAlterarProduto, "Você realmente deseja sair?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					TelaLogin janelaAlterarProduto = new TelaLogin(null);
					janelaAlterarProduto.setVisible(true);
					dispose(); // volta para tela de login
				}

			}
		});
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnSair, "cell 2 88,aligny bottom");

		JButton btnCancelar = new JButton("   Cancelar   ");
		btnCancelar.setForeground(Color.RED);
		btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnCancelar, "cell 18 88,aligny bottom");

		JButton btnSalvarEdicoes = new JButton("Salvar Edições");
		btnSalvarEdicoes.setForeground(Color.RED);
		btnSalvarEdicoes.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSalvarEdicoes.setBackground(new Color(255, 255, 255));
		contentPane_1.add(btnSalvarEdicoes, "cell 23 88,aligny bottom");

		/*
		 * 
		 * //AINDA FALTA ARRUMAR AS VARIAVEIS DOS PRODUTOS EEDITAR A TELA
		 * 
		 * 
		 * 
		 * 
		 * JButton btnAdicionar = new JButton("Salvar Edições");
		 * btnAdicionar.setForeground(new Color(255, 0, 0));
		 * btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		 * btnAdicionar.setBackground(new Color(255, 255, 255));
		 * contentPane.add(btnAdicionar, "cell 28 85 1 4,aligny center");
		 * btnAdicionar.addActionListener(new ActionListener() {
		 * 
		 * public void actionPerformed(ActionEvent e) {
		 * 
		 * Produto produto = new Produto(); produto.setLogin(txtLogin.getText());
		 * produto.setSenha(txtSenha.getText());
		 * produto.setCelular(txtCelular.getText()); produto.setCpf(txtCPF.getText());
		 * produto.setEmail_Funcionario(txtEmail.getText());
		 * produto.setNomeFuncionario(txtNomeCompleto.getText());
		 * 
		 * ProdutoDAO funcionarioAlterado = new ProdutoDAO(); try {
		 * funcionarioAlterado.alterarFuncionario(produto); ListagemFuncionarios
		 * janelaListagem = new ListagemFuncionarios(); janelaListagem.setVisible(true);
		 * dispose();
		 * 
		 * } catch (Exception ex) { JOptionPane.showMessageDialog(null,
		 * "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro",
		 * JOptionPane.ERROR_MESSAGE); }
		 * 
		 * }
		 * 
		 * });
		 * 
		 * JButton btnLimparCampos = new JButton("Limpar Campos");
		 * btnLimparCampos.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * txtNomeCompleto.setText(""); txtEmail.setText(""); txtCelular.setText("");
		 * txtCPF.setText(""); txtLogin.setText(""); txtSenha.setText("");
		 * 
		 * } }); btnLimparCampos.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		 * btnLimparCampos.setForeground(Color.RED);
		 * btnLimparCampos.setBackground(Color.WHITE); contentPane.add(btnLimparCampos,
		 * "cell 25 85 1 4,aligny center"); mostrarDados(p);
		 * 
		 * }
		 * 
		 * private void mostrarDados(Produto p) {
		 * txtNomeCompleto.setText(f.getNomeFuncionario());
		 * txtEmail.setText(f.getEmail_Funcionario());
		 * txtCelular.setText(f.getCelular()); txtCPF.setText(f.getCpf());
		 * txtLogin.setText(f.getLogin()); txtSenha.setText(f.getSenha());
		 * 
		 * }
		 */
	}
}
