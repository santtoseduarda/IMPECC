package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controle.FuncionarioDAO;
import modelo.Funcionario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListagemFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemFuncionarios frame = new ListagemFuncionarios();
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
	public ListagemFuncionarios() {
		
		ListagemFuncionarios janelaListagemFuncionarios = this;

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

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][][][][][][][][][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblListagemFunc = new JLabel("Listagem de Funcionários");
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

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setForeground(new Color(255, 255, 255));
		lblVendas.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblVendas, "cell 2 8,alignx left,aligny center");

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pesquisarPorCampo("ID", textField.getText());
			}
		});
		contentPane.add(panel, "cell 3 8 21 72,grow");
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][grow][][][][]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNewLabel_4, "cell 2 0");

		JLabel lblNewLabel_5 = new JLabel("Nome");
		lblNewLabel_5.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNewLabel_5, "cell 5 0");

		JLabel lblNewLabel_6 = new JLabel("CPF");
		lblNewLabel_6.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNewLabel_6, "cell 8 0");

		JLabel lblNewLabel_7 = new JLabel("Login");
		lblNewLabel_7.setFont(fontBold.deriveFont(Font.PLAIN, 14));
		panel.add(lblNewLabel_7, "cell 11 0");

		JLabel lblPesquisar = new JLabel("Pesquisar por : ");
		lblPesquisar.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		panel.add(lblPesquisar, "cell 1 1,alignx trailing");

		textField = new JTextField();
		panel.add(textField, "cell 2 1,growx");
		textField.setColumns(10);

		JLabel lupa1 = new JLabel("");
		lupa1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  pesquisarPorCampo("ID", textField.getText());
			}
		});
		lupa1.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa1, "cell 3 1,alignx trailing");

		textField_1 = new JTextField();
		panel.add(textField_1, "cell 5 1,growx");
		textField_1.setColumns(10);

		JLabel lupa2 = new JLabel("");
		lupa2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  pesquisarPorCampo("Nome", textField_1.getText());
			}
		});
		lupa2.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa2, "cell 6 1");

		textField_2 = new JTextField();
		panel.add(textField_2, "cell 8 1,growx");
		textField_2.setColumns(10);

		JLabel lupa3 = new JLabel("");
		lupa3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  pesquisarPorCampo("CPF", textField_2.getText());
			}
		});
		lupa3.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa3, "cell 9 1");

		textField_3 = new JTextField();
		panel.add(textField_3, "cell 11 1,growx");
		textField_3.setColumns(10);

		JLabel lupa4 = new JLabel("");
		lupa4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  pesquisarPorCampo("Login", textField_3.getText());
			}
				// TODO Auto-generated method stub
				
		});
		lupa4.setIcon(new ImageIcon(
				new ImageIcon("src/img/procurar.png").getImage().getScaledInstance(15, 16, Image.SCALE_DEFAULT)));
		panel.add(lupa4, "cell 12 1");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 1 3 13 14,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Email", "Celular", "CPF", "Login" }));
		scrollPane.setViewportView(table);
		atualizarTabela();

		JLabel lblLinha = new JLabel("");
		lblLinha.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha, "cell 1 9 2 1");

		JLabel lblCaixa = new JLabel("");
		lblCaixa.setIcon(new ImageIcon(
				new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaixa, "cell 1 10,alignx left,aligny center");

		JLabel lblProdutos = new JLabel("Produtos");
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

		JLabel lblClientes = new JLabel("Clientes");
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

		JLabel lblFornecedor = new JLabel("Fornecedores");
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

		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 2 17");

		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(
				new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 19 2 1");

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaListagemFuncionarios, 
			            "Você realmente deseja sair?", "Confirmação", 
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        
			        // Verifica a resposta
			        if (resposta == JOptionPane.YES_OPTION) {
			        	TelaLogin janelaListagemFuncionarios = new TelaLogin();
						janelaListagemFuncionarios.setVisible(true);
						dispose(); // Fecha a tela de login
			        }
				
				
			}
		});
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 78 1 4,aligny bottom");

		JButton btnAdicionar = new JButton("Adicionar Funcionário");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroFuncionarios janelaFuncionarios = new CadastroFuncionarios();
				janelaFuncionarios.setVisible(true);
				dispose(); // Fecha a tela de login

			}
		});
		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 21 80 1 2,aligny bottom");
	}

	private void pesquisarPorCampo(String campo, String valor) {
	    DefaultTableModel modeloTabela = (DefaultTableModel) table.getModel();
	    modeloTabela.setRowCount(0); // Limpa a tabela

	    FuncionarioDAO fdao = new FuncionarioDAO();
	    ArrayList<Funcionario> listaFuncionarios = fdao.buscarFuncionarios();
	   
	    for (Funcionario f : listaFuncionarios) {
	        boolean adiciona = false;

	        switch (campo) {
	            case "ID":
	                if (String.valueOf(f.getId_Funcionario()).contains(valor)) {
	                    adiciona = true;
	                }
	                break;
	            case "Nome":
	                if (f.getNomeFuncionario().toLowerCase().contains(valor.toLowerCase())) {
	                    adiciona = true;
	                }
	                break;
	            case "CPF":
	                if (f.getCpf().contains(valor)) {
	                    adiciona = true;
	                }
	                break;
	            case "Login":
	                if (f.getLogin().toLowerCase().contains(valor.toLowerCase())) {
	                    adiciona = true;
	                }
	                break;
	        }

	        if (adiciona) {
	            modeloTabela.addRow(new Object[] {
	                f.getId_Funcionario(),
	                f.getNomeFuncionario(),
	                f.getCpf(),
	                f.getLogin()
	            });
	        }
	    }
	}

	private void atualizarTabela() {

		DefaultTableModel modeloTabela = (DefaultTableModel) table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		FuncionarioDAO fdao = new FuncionarioDAO();
		ArrayList<Funcionario> listaFuncionarios = fdao.buscarFuncionarios();
		for (Funcionario f : listaFuncionarios) {
			modeloTabela.addRow(new Object[] { 
					f.getId_Funcionario(), 
					f.getNomeFuncionario(), 
					f.getEmail_Funcionario(),
					f.getCelular(), 
					f.getCpf(), 
					f.getLogin() });
		}

	}

}
