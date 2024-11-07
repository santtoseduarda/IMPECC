package controle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import visao.TelaInicial;

public class TelaInicialController {
	TelaInicial janelaTela = new TelaInicial(this); 

	
	public MouseListener abrirTelaFornecedor() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FornecedorController fornecedorController = new FornecedorController();
				fornecedorController.abrirListagemFornecedor();		
				janelaTela.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public MouseListener abrirTelaFuncionario() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FuncionarioController funcionarioController = new FuncionarioController();
				funcionarioController.abrirListagem();		
				janelaTela.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public MouseListener abrirTelaProduto() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProdutoController prodC = new ProdutoController();
				prodC.abrirListagem();
				janelaTela.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public MouseListener abrirTelaCliente() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteController clienteController = new ClienteController();
				clienteController.abrirListagemCLientes();		
				janelaTela.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
