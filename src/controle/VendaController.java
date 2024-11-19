package controle;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Venda;
import visao.CadastroVendas;
import visao.ListagemVendas;

public class VendaController {
	
	Venda venda = new Venda();
	VendaDAO vdao = new VendaDAO();
	ListagemVendas janelaListagem = new ListagemVendas(this);
	CadastroVendas janelaCadastro = CadastroVendas();
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
	
	
	public void atualizarTabela(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) janelaListagem.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		ArrayList<Venda> listaVendas = vdao.buscarClientesLupa(campo, valor);

		if (listaVendas != null && !listaVendas.isEmpty()) {
			for (Venda c : listaVendas) {
				// Adiciona os dados do cliente na tabela
				modeloTabela.addRow(new Object[] { c.getId_Cliente(), c.getNomeCliente(), c.getDataNasc(),
						c.getCpf_Cliente(), c.getTelefone(), c.getEmail() });
			}
		}
	}
	

}
