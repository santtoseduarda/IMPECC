package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import visao.TelaInterna;

public class TelaInternaController {

	TelaInterna tela;

	public void setTela(TelaInterna tela) {
		this.tela = tela;
		adicionarListenerFornecedor();
		adicionarListenerCliente();
		adicionarListenerFuncionario();
		adicionarListenerVenda();
		adicionarListenerProdutos();
		

	}

	private void adicionarListenerFuncionario() {
		// TODO Auto-generated method stub
		tela.getLabelFuncionario().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				tela.dispose();
				FuncionarioController funcController = new FuncionarioController();
				funcController.abrirListagem();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void adicionarListenerFornecedor() {
		// TODO Auto-generated method stub
		tela.getLabelFornecedor().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				tela.dispose();
				FornecedorController fornecedorController = new FornecedorController();
				fornecedorController.abrirListagemFornecedor();

			}

			@Override
			public void mousePressed(MouseEvent e) {
			
			}

			@Override
			public void mouseExited(MouseEvent e) {
			
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void adicionarListenerCliente() {
		// TODO Auto-generated method stub
		tela.getLabelCliente().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				tela.dispose();
				ClienteController clienteController = new ClienteController();
				clienteController.abrirListagemCLientes();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	private void adicionarListenerVenda() {
		// TODO Auto-generated method stub
		tela.getLabelVendas().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				tela.dispose();
				VendaController vendaController = new VendaController();
				vendaController.abrirListagemVenda();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	private void adicionarListenerProdutos() {
		// TODO Auto-generated method stub
		tela.getLabelProduto().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				tela.dispose();
				ProdutoController produtoController = new ProdutoController();
				produtoController.abrirListagemProdutos();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
