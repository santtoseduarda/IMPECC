package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.FuncionarioDAO;
import modelo.Funcionario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CadastroFuncionarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNomeCompleto;
    private JTextField txtCPF;
    private JTextField txtEmail;
    private JTextField txtLogin;
    private JTextField txtCelular;
    private JTextField txtSenha;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastroFuncionarios frame = new CadastroFuncionarios();
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
    public CadastroFuncionarios() {
    	setTitle("Cadastro Funcionário");
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
        contentPane.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]"));
        

		JLabel lblcadastroFunc = new JLabel("Cadastro de Funcionários");
		lblcadastroFunc.setForeground(new Color(255, 255, 255));
		lblcadastroFunc.setFont(fontBold.deriveFont(Font.PLAIN, 45));
		contentPane.add(lblcadastroFunc, "cell 3 1");
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(215, 106, Image.SCALE_DEFAULT)));
        contentPane.add(lblLogo, "cell 1 1 2 1");
        
        JLabel lblLinha1 = new JLabel("");
        lblLinha1.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
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
		panel.setLayout(new MigLayout("", "[grow][][][grow][grow 50][grow][][][grow]", "[][grow][][][][][grow 20][][][grow 20][][][grow 20][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT)));
		panel.add(lblNewLabel_6, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("Nome Completo:");
		panel.add(lblNewLabel_1, "cell 1 4");
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		panel.add(lblNewLabel_2, "cell 5 4");
		
		txtNomeCompleto = new JTextField();
		panel.add(txtNomeCompleto, "cell 1 5 3 1,growx");
		txtNomeCompleto.setColumns(10);
		
		txtEmail = new JTextField();
		panel.add(txtEmail, "cell 5 5 3 1,growx");
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Celular:");
		panel.add(lblNewLabel_3, "cell 1 7");
		
		JLabel lblCPF = new JLabel("CPF:");
		panel.add(lblCPF, "cell 5 7");
		
		txtCelular = new JTextField();
		panel.add(txtCelular, "cell 1 8 3 1,growx");
		txtCelular.setColumns(10);
		
		txtCPF = new JTextField();
		panel.add(txtCPF, "cell 5 8 3 1,growx");
		txtCPF.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Login:");
		panel.add(lblNewLabel_4, "cell 1 10");
		
		JLabel lblNewLabel_5 = new JLabel("Senha:");
		panel.add(lblNewLabel_5, "cell 5 10");
		
		txtLogin = new JTextField();
		panel.add(txtLogin, "cell 1 11 3 1,growx");
		txtLogin.setColumns(10);
		
		txtSenha = new JTextField();
		panel.add(txtSenha, "cell 5 11 3 1,growx");
		txtSenha.setColumns(10);
		
        JLabel lblLinha = new JLabel("");
        lblLinha.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
        contentPane.add(lblLinha, "cell 1 4 2 1");
        
        JLabel lblCaixa = new JLabel("");
        lblCaixa.setIcon(new ImageIcon(new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        contentPane.add(lblCaixa, "cell 1 5");
        
        JLabel lblProdutos = new JLabel("Produtos");
     		lblProdutos.setForeground(new Color(255, 255, 255));
     		lblProdutos.setFont(fontBold.deriveFont(Font.PLAIN, 20));
     		contentPane.add(lblProdutos, "cell 2 5,alignx left,aligny center");
             
        
        JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 1 6 2 1");
		
		JLabel lblCliente = new JLabel("");
		lblCliente.setIcon(new ImageIcon(new ImageIcon("src/img/cliente.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblCliente, "cell 1 7,alignx left,aligny center");
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblClientes, "cell 2 7");
		
		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 1 9 2 1");
		
		JLabel lblCaminhao = new JLabel("");
		lblCaminhao.setIcon(new ImageIcon(new ImageIcon("src/img/caminhao.png").getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		contentPane.add(lblCaminhao, "cell 1 10,alignx left,aligny center");
		
		JLabel lblFornecedor = new JLabel("Fornecedores");
		lblFornecedor.setForeground(new Color(255, 255, 255));
		lblFornecedor.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFornecedor, "cell 2 10");
		
		JLabel lblLinha4 = new JLabel("");
		lblLinha4.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha4, "cell 1 12 2 1");
		
		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setIcon(new ImageIcon(new ImageIcon("src/img/funcionario.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPane.add(lblFuncionario, "cell 1 13,alignx left,aligny center");
		
		JLabel lblFuncionarios = new JLabel("Funcionários");
		lblFuncionarios.setForeground(new Color(255, 255, 255));
		lblFuncionarios.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		contentPane.add(lblFuncionarios, "cell 2 13");
		
		JLabel lblLinha5 = new JLabel("");
		lblLinha5.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(215, 1, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha5, "cell 1 15 2 1");
		
		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(255, 0, 0));
		btnSair.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnSair.setBackground(new Color(255, 255, 255));
		contentPane.add(btnSair, "cell 2 83 1 4,aligny bottom");
	
		
		JButton btnAdicionar = new JButton("Adicionar Funcionário");
		btnAdicionar.setForeground(new Color(255, 0, 0));
		btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnAdicionar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAdicionar, "cell 28 85 1 4,aligny center");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				String cpf = txtCPF.getText();
				String email = txtEmail.getText();
				String nomeCompleto = txtNomeCompleto.getText();
				String celular = txtCelular.getText();

				if (login.isEmpty() || senha.isEmpty() || cpf.isEmpty() || email.isEmpty() || nomeCompleto.isEmpty()|| celular.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(null, 
						    "Todos os campos obrigatórios (*) devem ser preenchidos!", 
						    "Erro de cadastro", // Adicionei o título da janela
						    javax.swing.JOptionPane.ERROR_MESSAGE);
				} else {

					Funcionario cadastro = new Funcionario();

					cadastro.setLogin(login);
					cadastro.setSenha(senha);
					cadastro.setCelular(celular);
					cadastro.setCpf(cpf);
					cadastro.setEmail_Funcionario(email);
					cadastro.setNomeFuncionario(nomeCompleto);

					FuncionarioDAO novoFuncionario = new FuncionarioDAO();
					FuncionarioDAO.getInstancia();
					novoFuncionario.inserir(cadastro);
					
					ListagemFuncionarios janelaCadastro = new ListagemFuncionarios();
					janelaCadastro.setVisible(true);
					dispose();
					
					
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setForeground(Color.RED);
		btnCancelar.setBackground(Color.WHITE);
		contentPane.add(btnCancelar, "cell 21 85 1 4,aligny center");
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos .setFont(fontBold.deriveFont(Font.PLAIN, 25));
		btnLimparCampos.setForeground(Color.RED);
		btnLimparCampos.setBackground(Color.WHITE);
		contentPane.add(btnLimparCampos, "cell 25 85 1 4,aligny center");
		
		
		
		
		
    }
}
