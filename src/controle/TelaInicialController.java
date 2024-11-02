package controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import visao.ListagemFornecedor;
import visao.ListagemProdutos;
import visao.TelaInicial;

public class TelaInicialController {
	TelaInicial janelaTela = new TelaInicial(this); 

	public MouseAdapter abrirTelaProduto() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				janelaTela.dispose();
			}
		};
	}

	public MouseAdapter abrirTelaFornecedor() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				janelaTela.dispose();
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
