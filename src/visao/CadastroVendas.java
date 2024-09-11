package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroVendas extends JFrame {

    private JTable tabelaProdutos;
    private JTextField campoCPF;
    private JLabel labelTotal;

    public CadastroVendas() {
        setTitle("Cadastro de Vendas");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando os componentes
        campoCPF = new JTextField(20);
        labelTotal = new JLabel("Total (R$): 0.00");

        // Criando a tabela
        String[] colunas = {"ID", "Nome", "Quantidade", "Valor Unit.", "Subtotal"};
        Object[][] dados = {
                {15487, "Top Curve P - LIVE", 2, "R$89.96", "R$179.92"}
        };
        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas);
        tabelaProdutos = new JTable(new DefaultTableModel(
        	new Object[][] {
        		{new Integer(15487), "Top Curve P - LIVE", new Integer(2), "R$89.96", "R$179.92"},
        	},
        	new String[] {
        		"ID", "Nome", "Quantidade", "Valor Unit.", "Subtotal"
        	}
        ));

        // Criando o painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(campoCPF, BorderLayout.NORTH);
        painelPrincipal.add(new JScrollPane(tabelaProdutos), BorderLayout.EAST);
        painelPrincipal.add(labelTotal, BorderLayout.SOUTH);

        // Adicionando o painel ao frame
        getContentPane().add(painelPrincipal);

        // Botões
        JPanel painelBotoes = new JPanel();
        JButton botaoFinalizar = new JButton("Finalizar Venda");
        JButton botaoCancelar = new JButton("Cancelar");
        JButton botaoSair = new JButton("Sair");
        painelBotoes.add(botaoFinalizar);
        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoSair);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        // Adicionaar um listener para o botão finalizar (colocar a lógica aqui)
        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para finalizar a venda (salvar dados, gerar recibo, etc.)
                JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
            }
        });

        // Adicionar listeners para os outros botões de acordo com a necessidade

        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroVendas();
    }
}