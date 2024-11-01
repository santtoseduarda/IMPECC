package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import visao.ListagemFornecedor;
import visao.ListagemFuncionarios;
import visao.ListagemProdutos;
import visao.TelaInicial;

public class TelaInicialController {
	TelaInicial janelaTela = new TelaInicial(this); 

	public MouseAdapter abrirTelaProduto() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListagemProdutos janelaInicial = new ListagemProdutos();
				janelaInicial.setVisible(true);
				janelaInicial.dispose();
			}
		};
	}

	public MouseAdapter abrirTelaFornecedor() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListagemFornecedor janelaInicial = new ListagemFornecedor(null);
				janelaInicial.setVisible(true);
				janelaInicial.dispose();
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
}
