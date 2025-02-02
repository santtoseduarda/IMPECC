package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
	FinalizarVenda janelaFinalizarVenda = new FinalizarVenda();

	public VendaController() {
		telaInternaController.setTela(janelaListagem);
		configurarListeners();
	}

	private class VendasListeners implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if ("adicionarCarinhoAction".equals(e.getActionCommand())) {
		        try {
		            String produto = janelaCadastro.getCodProd();
		            int codprod = Integer.parseInt(produto);
		            
		            ProdutoDAO produtobd = new ProdutoDAO();
		            Produto p = produtobd.buscarProdutos(codprod);
		            
		            int quantidade = (int) janelaCadastro.spinnerQntd.getValue();
		            double valorTotal = quantidade * p.getPreco();
		         // Format the values to 2 decimal places
	                String precoFormatado = String.format("%.2f", p.getPreco());
	                String valorTotalFormatado = String.format("%.2f", valorTotal);

		            DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
		            modeloTabela.addRow(new Object[] { p.getNomeProduto(), quantidade, precoFormatado, valorTotalFormatado });

		            atualizarTotalCarrinho(); // Atualiza o total do carrinho
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(janelaCadastro, "Erro ao adicionar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } else if ("okAction".equals(e.getActionCommand())) {
		    	String cpf = janelaCadastro.getCpfCliente();
	            Cliente cliente = novaVenda.buscarCliente(cpf);
	            if (cliente != null) {
	                janelaCadastro.setNomeCliente(cliente.getNomeCliente());
	            } else {
	                JOptionPane.showMessageDialog(janelaCadastro, 
	                    "Cliente não encontrado!", 
	                    "Erro", 
	                    JOptionPane.ERROR_MESSAGE);
	            }
	            /*
		        String cpf = janelaCadastro.getCpfCliente();
		        Cliente cliente = novaVenda.buscarCliente(cpf);
		        if (cliente != null) {
		            janelaCadastro.setNomeCliente(cliente.getNomeCliente());
		        } else {
		            JOptionPane.showMessageDialog(janelaCadastro, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		        */
		    }
		}

		private void atualizarTotalCarrinho() {
			totalCarrinho = 0.0;
	        DefaultTableModel modelo = (DefaultTableModel) janelaCadastro.table.getModel();
	        
	        for (int i = 0; i < modelo.getRowCount(); i++) {
	            String valorStr = modelo.getValueAt(i, 3).toString();
	            // Remove any existing formatting and parse the value
	            valorStr = valorStr.replace(",", ".");
	            totalCarrinho += Double.parseDouble(valorStr);
	        }
		
	        // Format the total to 2 decimal places
	        String totalFormatado = String.format("%.2f", totalCarrinho);
	        janelaCadastro.setTotalVenda(totalFormatado);
	        
	}
		
	        
	        /*
			DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
		    double total = 0.0;

		    for (int i = 0; i < modeloTabela.getRowCount(); i++) {
		        double valor = (double) modeloTabela.getValueAt(i, 3); // Coluna "Total"
		        total += valor;
		    }

		    janelaCadastro.setTotalVenda(String.format("%.2f", total));
		}
		*/


	}
	


	private void configurarListeners() {
		janelaCadastro.addcadastroVendasListener(new VendasListeners());
	}

	/*public void abrirListagemVenda() {
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}*/

	public ActionListener sairSistema() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
				int resposta = mve.getResposta();

				// Verifica a resposta
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
				janelaCadastro.dispose();
			}
		};
	}

	public void abrirListagemVenda() {
		janelaListagem.setVisible(true);
		
	}
	
	public void abrirFinalizarVenda() {
		janelaFinalizarVenda.setVisible(true);
	}

	

}
