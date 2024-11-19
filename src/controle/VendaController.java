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
	

}
