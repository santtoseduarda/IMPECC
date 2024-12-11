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
import visao.ListagemVendas;
import visao.MensagemView;
import visao.MensagemViewOp;

public class VendaController {

	Venda venda = new Venda();
	VendaDAO novaVenda = new VendaDAO();
	ListagemVendas janelaListagem = new ListagemVendas(this);
	CadastroVendas janelaCadastro = new CadastroVendas(this);
	TelaInternaController telaInternaController = new TelaInternaController();

	public VendaController() {
		telaInternaController.setTela(janelaListagem);
		configurarListeners();
	}

	private class VendasListeners implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ("adicionarCarinhoAction".equals(e.getActionCommand())) {
				String produto = janelaCadastro.getCodProd();

				int codprod = Integer.parseInt(produto);
				ProdutoDAO produtobd = new ProdutoDAO();
				Produto p = produtobd.buscarProdutos(codprod);
				
				int quantidade = (int) janelaCadastro.spinnerQntd.getValue();
				
				double valorTotal = quantidade * p.getPreco();
				
				DefaultTableModel modeloTabela = (DefaultTableModel) janelaCadastro.table.getModel();
				
				modeloTabela.addRow(new Object[] { p.getNomeProduto(), quantidade,p.getPreco(),
						valorTotal});
				
			} else if ("okAction".equals(e.getActionCommand())) {
				String cpf = janelaCadastro.getCpfCliente();
				Cliente cliente = novaVenda.buscarCliente(cpf);
			}
		}

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

}
