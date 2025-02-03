package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import controle.VendaController;
import net.miginfocom.swing.MigLayout;

public class CadastroVendas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCPFcliente;
    public JTable table;
    public JComboBox<Object> comboBoxProd;
    public JSpinner spinnerQntd;
    private JButton btnAdicionar;
    private JButton btnOK;
    private JTextField txtCodProd;
    public JLabel lblPreco;
    public JLabel lblcliente;
    static VendaController vendaController;

    public CadastroVendas(VendaController vendaController) {
    	this.vendaController = vendaController;

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
            e.printStackTrace();
        }
        try {
            fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
            fontBold = Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][][grow][grow][][grow][][grow]", "[][30px][grow][30px][grow][]"));

        JLabel lblvoltar = new JLabel("");
        lblvoltar.setIcon(new ImageIcon(
                new ImageIcon("src/img/voltar1.png").getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT)));
        contentPane.add(lblvoltar, "cell 0 0");
        lblvoltar.addMouseListener(vendaController.voltarListagem());


        JLabel lblCadVenda = new JLabel("Cadastro de Vendas");
        lblCadVenda.setForeground(new Color(0, 0, 0));
        lblCadVenda.setFont(fontBold.deriveFont(Font.PLAIN, 45));
        contentPane.add(lblCadVenda, "cell 2 0");

        JPanel inputPanel = new JPanel(new MigLayout("", "[][grow][][grow][][grow]", "[][]"));

        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setFont(fontBold.deriveFont(Font.PLAIN, 20));
        inputPanel.add(lblProduto, "cell 0 0");

        txtCodProd = new JTextField();
        txtCodProd.setPreferredSize(new Dimension(100, 30));
        inputPanel.add(txtCodProd, "cell 1 0,growx");

        JLabel lblQntd = new JLabel("Quantidade:");
        lblQntd.setFont(fontBold.deriveFont(Font.PLAIN, 20));
        inputPanel.add(lblQntd, "cell 2 0");

        spinnerQntd = new JSpinner();
        spinnerQntd.setPreferredSize(new Dimension(100, 30));
        inputPanel.add(spinnerQntd, "cell 3 0");

        JLabel cpfCliente = new JLabel("CPF Cliente:");
        cpfCliente.setFont(fontBold.deriveFont(Font.PLAIN, 20));
        inputPanel.add(cpfCliente, "cell 4 0");

        txtCPFcliente = new JTextField();
        txtCPFcliente.setPreferredSize(new Dimension(150, 30));
        inputPanel.add(txtCPFcliente, "flowx,cell 5 0,growx");

        btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnAdicionar.setActionCommand("adicionarCarinhoAction");
        btnAdicionar.setForeground(Color.RED);
        btnAdicionar.setFont(fontBold.deriveFont(Font.PLAIN, 17));
        btnAdicionar.setBackground(Color.WHITE);
        inputPanel.add(btnAdicionar, "cell 1 1");

        contentPane.add(inputPanel, "cell 0 1 8 1,growx");

        JLabel lblCliente = new JLabel("Cliente:");
        inputPanel.add(lblCliente, "cell 4 1");

        lblcliente = new JLabel("");
        inputPanel.add(lblcliente, "cell 5 1");

        btnOK = new JButton("OK");
        btnOK.setActionCommand("okAction");
        btnOK.setForeground(Color.RED);
        btnOK.setFont(fontBold.deriveFont(Font.PLAIN, 17));
        btnOK.setBackground(Color.WHITE);
        inputPanel.add(btnOK, "cell 5 0");

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "Produto", "Quantidade", "Preço Un.", "Total" }));
        scrollPane.setViewportView(table);
        contentPane.add(scrollPane, "cell 0 2 8 1,grow");

        JButton btnExcluir = new JButton("Excluir Produto");
        btnExcluir.setForeground(Color.RED);
        btnExcluir.setFont(fontBold.deriveFont(Font.PLAIN, 22));
        btnExcluir.setBackground(Color.WHITE);
        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);
                    atualizarTotalVenda();
                }
            }
        });
        contentPane.add(btnExcluir, "cell 0 3,growx");

        JPanel bottomPanel = new JPanel(new MigLayout("", "[grow][][]", "[]"));

        JLabel lblTotal = new JLabel("Total (R$): ");
        lblTotal.setFont(fontBold.deriveFont(Font.PLAIN, 25));
        bottomPanel.add(lblTotal, "cell 0 0");

        lblPreco = new JLabel("0,00");
        lblPreco.setFont(fontBold.deriveFont(Font.PLAIN, 25));
        bottomPanel.add(lblPreco, "cell 0 0");

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(vendaController.cancelar());
        btnCancelar.setForeground(Color.RED);
        btnCancelar.setFont(fontBold.deriveFont(Font.PLAIN, 25));
        btnCancelar.setBackground(Color.WHITE);
        bottomPanel.add(btnCancelar, "cell 1 0");

        JButton btnFinalizarVenda = new JButton("Finalizar Venda");
        btnFinalizarVenda.addActionListener(vendaController.abrirFinalizarVenda());
        btnFinalizarVenda.setForeground(Color.RED);
        btnFinalizarVenda.setFont(fontBold.deriveFont(Font.PLAIN, 25));
        btnFinalizarVenda.setBackground(Color.WHITE);
        bottomPanel.add(btnFinalizarVenda, "cell 2 0");

        contentPane.add(bottomPanel, "cell 0 4 8 1,growx");

        btnOK.addActionListener(e -> {
            String cpf = txtCPFcliente.getText().trim();
            
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o CPF do cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Remove formatação do CPF
            cpf = cpf.replace(".", "").replace("-", "");

            // Se o nome já estiver preenchido, evita busca desnecessária
            if (!lblcliente.getText().isEmpty()) {
                return;
            }

            String nomeCliente = buscarNomeClientePorCPF(cpf);

            if (nomeCliente != null) {
                lblcliente.setText(nomeCliente);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private String buscarNomeClientePorCPF(String cpf) {
        // Simulação de busca do nome do cliente (substitua por uma chamada real ao banco de dados)
        if (cpf.equals("123.456.789-00")) {
            return "João Silva";
        } else if (cpf.equals("987.654.321-00")) {
            return "Maria Oliveira";
        } else {
            return null;
        }
    }

    private void atualizarTotalVenda() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double precoUn = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", "."));
            int quantidade = Integer.parseInt(model.getValueAt(i, 1).toString());
            total += precoUn * quantidade;
        }
        setTotalVenda(String.format("%.2f", total));
    }

    public void setTotalVenda(String total) {
        String valorFormatado = total.replace(".", ",");
        lblPreco.setText(valorFormatado);
    }

    public JSpinner getSpinnerQntd() {
        return spinnerQntd;
    }

    public void addcadastroVendasListener(ActionListener listener) {
        btnAdicionar.addActionListener(listener);
        btnOK.addActionListener(listener);
    }

    public String getCpfCliente() {
        return txtCPFcliente.getText();
    }

    public String getCodProd() {
        return txtCodProd.getText();
    }

    public void setNomeCliente(String nomeCliente) {
        lblcliente.setText(nomeCliente);
    }

    public static void main(String[] args) {
        CadastroVendas frame = new CadastroVendas(vendaController);
        frame.setVisible(true);
    }
}