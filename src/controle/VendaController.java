package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import visao.MensagemViewOp;

public class VendaController {
	
	Venda venda = new Venda();
	VendaDAO novaVenda = new VendaDAO();
	ListagemVendas janelaListagem = new ListagemVendas(this);
	CadastroVendas janelaCadastro = new CadastroVendas(this);
	TelaInternaController telaInternaController = new TelaInternaController();
	
	public VendaController() {
		telaInternaController.setTela(janelaListagem);
	}
	
	public void abrirListagemVenda() {
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}
	
	public ActionListener sairSistema() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MensagemViewOp mve = new MensagemViewOp("Você realmente deseja sair?", "Confirmação");
				int resposta =  mve.getResposta();
				
				// Verifica a resposta
				if (resposta == 1) {
					LoginController logController = new LoginController();
					logController.iniciarLogin();
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
	
	
	
	public void atualizarTabela(String campo, String valor) {
		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela
		
		VendaDAO vdao = new VendaDAO();
		ArrayList<Venda> listaVendas = vdao.buscarVendasLupa(campo, valor);

		if (listaVendas != null && !listaVendas.isEmpty()) {
			for (Venda v : listaVendas) {
				modeloTabela.addRow(new Object[] { v.getIdVenda(),  v.getValorTotal(),
						v.getIdCliente(), v.getIdFuncionario()});
			}
		}
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
