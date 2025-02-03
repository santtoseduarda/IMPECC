package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import visao.CadastroVendas;
import visao.FinalizarVenda;
import visao.ListagemVendas;
import visao.MensagemView;
import visao.MensagemViewOp;

public class VendaController {

    public static ActionListener finalizarVenda;
    private double totalCarrinho = 0.0;
    Venda venda = new Venda();
    VendaDAO novaVenda = new VendaDAO();
    ListagemVendas janelaListagem = new ListagemVendas(this);
    CadastroVendas janelaCadastro = new CadastroVendas(this);
    TelaInternaController telaInternaController = new TelaInternaController();
    FinalizarVenda janelaFinalizarVenda = new FinalizarVenda(this);
    private ArrayList<ItemVenda> carrinhoItens = new ArrayList<>();

    public VendaController() {
        telaInternaController.setTela(janelaListagem);
        configurarListeners();
    }

    private class VendasListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("adicionarCarinhoAction".equals(e.getActionCommand())) {
                adicionarProdutoAoCarrinho();
            } else if ("okAction".equals(e.getActionCommand())) {
                buscarClientePorCPF();
            } else if ("excluirProdutoAction".equals(e.getActionCommand())) {
                excluirProdutoDaTabela();
            }
        }

        /*
        private void adicionarProdutoAoCarrinho() {
            try {
                String produto = janelaCadastro.getCodProd();
                int codprod = Integer.parseInt(produto);

                ProdutoDAO produtobd = new ProdutoDAO();
                Produto p = produtobd.buscarProdutos(codprod);

                int quantidade = (int) janelaCadastro.getSpinnerQntd().getValue();
                double valorTotal = quantidade * p.getPreco();

                // Formatar os valores para 2 casas decimais
                String precoFormatado = String.format("%.2f", p.getPreco());
                String valorTotalFormatado = String.format("%.2f", valorTotal);

                DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
                modeloTabela.addRow(new Object[]{p.getNomeProduto(), quantidade, precoFormatado, valorTotalFormatado});

                atualizarTotalCarrinho(); // Atualiza o total do carrinho
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janelaCadastro, "Erro ao adicionar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

*/
        private void adicionarProdutoAoCarrinho() {
            try {
                String produto = janelaCadastro.getCodProd();
                int codprod = Integer.parseInt(produto);

                ProdutoDAO produtobd = new ProdutoDAO();
                Produto p = produtobd.buscarProdutos(codprod);

                int quantidade = (int) janelaCadastro.getSpinnerQntd().getValue();
                double valorTotal = quantidade * p.getPreco();

                // Criar item de venda
                ItemVenda item = new ItemVenda();
                item.setProduto(p);
                item.setQuantidade(quantidade);
                item.setPrecoTotal(valorTotal);
                carrinhoItens.add(item);

                // Formatar os valores para 2 casas decimais
                String precoFormatado = String.format("%.2f", p.getPreco());
                String valorTotalFormatado = String.format("%.2f", valorTotal);

                DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
                modeloTabela.addRow(new Object[]{p.getNomeProduto(), quantidade, precoFormatado, valorTotalFormatado});

                atualizarTotalCarrinho();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(janelaCadastro, "Erro ao adicionar produto: " + ex.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void buscarClientePorCPF() {
            String cpf = janelaCadastro.getCpfCliente().trim(); // Remove espaços extras
            
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(janelaCadastro, "Digite o CPF do cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Cliente cliente = novaVenda.buscarCliente(cpf);

            if (cliente != null && cliente.getNomeCliente() != null && !cliente.getNomeCliente().isEmpty()) {
                janelaCadastro.setNomeCliente(cliente.getNomeCliente()); // Atualiza o nome do cliente na tela
            } else {
                JOptionPane.showMessageDialog(janelaCadastro, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                janelaCadastro.setNomeCliente(""); // Garante que não mostre nome errado na tela
            }
        }



        private void excluirProdutoDaTabela() {
            int selectedRow = janelaCadastro.table.getSelectedRow();
            if (selectedRow == -1) {
                new MensagemView("Por favor, selecione um produto para realizar a exclusão.", "Atenção", 0);
            } else {
                MensagemViewOp mve = new MensagemViewOp("Você tem certeza que deseja remover este produto do carrinho?", "Confirmação de Exclusão");
                int confirmacao = mve.getResposta();

                if (confirmacao == 1) {
                    DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
                    modeloTabela.removeRow(selectedRow);
                    atualizarTotalCarrinho(); 
                    new MensagemView("Produto removido com sucesso!", "Sucesso", 1);
                }
            }
        }


        private void atualizarTotalCarrinho() {
            totalCarrinho = 0.0;
            DefaultTableModel modelo = (DefaultTableModel) janelaCadastro.table.getModel();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                String valorStr = modelo.getValueAt(i, 3).toString();
                valorStr = valorStr.replace(",", ".");
                totalCarrinho += Double.parseDouble(valorStr);
            }

            String totalFormatado = String.format("%.2f", totalCarrinho);
            janelaCadastro.setTotalVenda(totalFormatado);
        }
    }

    private void configurarListeners() {
        janelaCadastro.addcadastroVendasListener(new VendasListeners());

    }

    public ActionListener sairSistema() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
                int resposta = mve.getResposta();

                if (resposta == 1) {
                    LoginController logController = new LoginController();
                    logController.iniciarLogin();
                    telaInternaController.fecharTela();
                }
            }
        };
    }

    public ActionListener iniciarCadastroVenda() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaCadastro.setVisible(true);
                janelaListagem.dispose();
            }
        };
    }

    public MouseListener voltarListagem() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                janelaListagem.setVisible(true);
                carregarVendas();
                janelaCadastro.dispose();
            }
        };
    }
    
    public ActionListener cancelar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MensagemViewOp mve = new MensagemViewOp("Você tem certeza que deseja cancelar a venda?", "Cancelamento de Venda");
                
                int confirmacao = mve.getResposta();

                if (confirmacao == 1) {
                    venda = null;
                    limparCarrinho();
                    
                    new MensagemView("Venda cancelada com sucesso.", "Cancelamento", 1);
                    
                    janelaFinalizarVenda.dispose();
                    janelaListagem.setVisible(true);
                    carregarVendas();
                } else {
                    new MensagemView("Cancelamento da venda não realizado.", "Atenção", 0);
                }
            }
        };
    }


    public void abrirListagemVenda() {
        janelaListagem.setVisible(true);
        carregarVendas();
    }

    public ActionListener abrirFinalizarVenda() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = janelaCadastro.lblcliente.getText();
                String cpfCliente = janelaCadastro.getCpfCliente();
                String totalVenda = janelaCadastro.lblPreco.getText();

                janelaFinalizarVenda.setClienteInfo(nomeCliente, cpfCliente);
                janelaFinalizarVenda.setTotalVenda(totalVenda);

                janelaFinalizarVenda.setVisible(true);
                janelaCadastro.dispose();
            }
        };
    }

    public ActionListener finalizarVenda() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = janelaCadastro.getCpfCliente();
                    Cliente cliente = novaVenda.buscarCliente(cpf);

                    if (cliente == null) {
                        new MensagemView("Cliente não encontrado.", "Erro", 0);
                        return;
                    }

                    venda.setIdCliente(cliente.getId_Cliente());
                    venda.setValorTotal(totalCarrinho);
                    venda.setMtd_Pagamento(janelaFinalizarVenda.getFormaPagamento());
                    Date dataAtual = new Date(System.currentTimeMillis()); // Obtendo a data atual
                    venda.setDataCompra(dataAtual);

                    boolean sucesso = novaVenda.finalizarVenda(venda, carrinhoItens);

                    if (sucesso) {
                        new MensagemView("Venda finalizada com sucesso!", "Sucesso", 1);
                        limparCarrinho();
                        janelaFinalizarVenda.dispose();
                        abrirListagemVenda();
                    } else {
                        new MensagemView("Erro ao finalizar a venda.", "Erro", 0);
                    }
                } catch (Exception ex) {
                    new MensagemView("Erro ao finalizar venda: " + ex.getMessage(), "Erro", 0);
                }
            }
        };
    }


	protected void limparCarrinho() {
		carrinhoItens.clear();
        DefaultTableModel modelo = (DefaultTableModel) janelaCadastro.table.getModel();
        modelo.setRowCount(0);
        totalCarrinho = 0.0;
        janelaCadastro.setTotalVenda("0,00");
		
	}
	
	public void carregarVendas() {
	    if (janelaListagem == null || janelaListagem.getTable() == null) {
	        System.err.println("Erro: janelaListagem ou a tabela não foi inicializada.");
	        return;
	    }

	    List<Venda> vendas = novaVenda.listarVendas();
	    DefaultTableModel model = (DefaultTableModel) janelaListagem.getTable().getModel();
	    model.setRowCount(0); // Limpa a tabela antes de carregar os dados

	    for (Venda venda : vendas) {
	        model.addRow(new Object[]{
	            venda.getIdVenda(),
	            String.format("R$ %.2f", venda.getValorTotal()),
	            venda.getDataCompra(),
	            venda.getNomeCliente(),
	            "" // Coluna de Funcionário vazia
	        });
	    }
	}
	
	// Método para pesquisar vendas por campo e valor
	private void pesquisarPorCampo(String campo, String valor) {
	    DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.getTable().getModel();
	    modeloTabela.setRowCount(0); // Limpa a tabela antes de carregar os novos dados

	    ArrayList<Venda> listaVendas = novaVenda.buscarVendaLupa(campo, valor); // Chama o DAO para buscar as vendas

	    // Preenche a tabela com os resultados
	    for (Venda v : listaVendas) {
	        modeloTabela.addRow(new Object[] {
	            v.getIdVenda(),
	            String.format("R$ %.2f", v.getValorTotal()), // Formata o valor total
	            v.getDataCompra(),
	            v.getNomeCliente()
	        });
	    }
	}

	// Método para criar o MouseListener da lupa
	public MouseListener pesquisa(String campo, JTextField textField) {
	    return new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            String valor = textField.getText(); // Obtém o valor do campo de texto
	            pesquisarPorCampo(campo, valor); // Chama o método de pesquisa
	        }
	    };
	}


}