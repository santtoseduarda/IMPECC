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
import modelo.Fornecedor;
import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import visao.CadastroVendas;
import visao.ListagemVendas;

public class VendaController {
	
	Venda venda = new Venda();
	VendaDAO vdao = new VendaDAO();
	ListagemVendas janelaListagem = new ListagemVendas(this);
	CadastroVendas janelaCadastro = new CadastroVendas(this);
	TelaInternaController telaInternaController = new TelaInternaController();

	
	public VendaController() {
		telaInternaController.setTela(janelaListagem);
	}
	
	public void abrirListagemVenda() {
		// TODO Auto-generated method stub
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}
	
	public void inserirVenda() {
		// inserir
		janelaCadastro.setVisible(true);
	}
	
	public void iniciarCadastroVenda() {
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}
	
	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirListagemVenda();
				janelaCadastro.dispose();
			}
		};
	}
	
	public ActionListener sairSistema() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {

					// botar o controller login para abrir a tela
					LoginController logController = new LoginController();
					logController.iniciarLogin();
				}

			}

		};
	}
	
	private void pesquisarPorCampo(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0);

		
		ArrayList<Venda> listaVenda = vdao.buscarVendasLupa(campo, valor);

		for (Venda v : listaVenda) {

			modeloTabela.addRow(new Object[] { v.getId_Venda(), v.getTotal(), v.getMtd_Pagamento(), v.getCliente().getCpf_Cliente(), v.getFuncionario().getNomeFuncionario() });
		}
	}

	public MouseListener pesquisa(String campo, JTextField txtID) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String valor = txtID.getText(); // Obter o valor atualizado do campo de texto no momento do clique
				pesquisarPorCampo(campo, valor);
			}
		};
	}
	//
	
	
	public void atualizarTabela(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		ArrayList<Venda> listaVendas = vdao.buscarVendasLupa(campo, valor);

		if (listaVendas != null && !listaVendas.isEmpty()) {
			for (Venda v : listaVendas) {
				// Adiciona os dados do cliente na tabela
				modeloTabela.addRow(new Object[] { v.getId_Venda(),  v.getTotal(), v.getMtd_Pagamento(), v.getCliente().getCpf_Cliente(), v.getFuncionario().getNomeFuncionario()});
			}
		}
	}

	public ActionListener adicionarCarrinho() {
	    return new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            try {
	                // Obter o produto selecionado e a quantidade do spinner
	                Produto produtoSelecionado = (Produto) janelaCadastro.comboBoxProd.getSelectedItem();
	                int quantidade = (Integer) janelaCadastro.getSpinnerQntd().getValue();

	                // Verificar se os valores são válidos
	                if (produtoSelecionado == null || quantidade <= 0) {
	                    JOptionPane.showMessageDialog(janelaCadastro, "Selecione um produto e insira uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Criar um novo ItemVenda
	                ItemVenda itemVenda = new ItemVenda();
	                itemVenda.setProduto(produtoSelecionado);
	                itemVenda.setQntd(quantidade);
	                itemVenda.setPreco(produtoSelecionado.getPreco());
	                itemVenda.setPrecoTotal(quantidade * produtoSelecionado.getPreco());

	                // Adicionar o item à lista de itens da venda
	                venda.getItensVenda().add(itemVenda);

	                // Atualizar a tabela
	                DefaultTableModel model = (DefaultTableModel) janelaCadastro.table.getModel();
	                model.addRow(new Object[]{
	                    produtoSelecionado.getId_Produto(),
	                    produtoSelecionado.getNomeProduto(),
	                    quantidade,
	                    produtoSelecionado.getPreco(),
	                    itemVenda.getPrecoTotal()
	                });

	                // Atualizar o total geral
	                float totalGeral = venda.getTotal() + itemVenda.getPrecoTotal();
	                venda.setTotal(totalGeral);
	                janelaCadastro.lblPreco.setText(String.format("%.2f", totalGeral));

	                JOptionPane.showMessageDialog(janelaCadastro, "Produto adicionado ao carrinho com sucesso!");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(janelaCadastro, "Erro ao adicionar o produto ao carrinho: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    };
	}

	public void abrirTelaAdicionarVenda() {
		// TODO Auto-generated method stub
		
	}

	

}
