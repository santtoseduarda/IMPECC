package controle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import visao.TelaInicial;

public class TelaInicialController {
    private TelaInicial janelaTela;

    public TelaInicialController() {
        // Aqui, a TelaInicial é criada corretamente no momento da inicialização do controlador
        janelaTela = new TelaInicial(this);
    }

    public MouseListener abrirTelaFornecedor() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharTelaInicial(); // Fecha a tela inicial
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.abrirListagemFornecedor();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    public MouseListener abrirTelaFuncionario() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharTelaInicial(); // Fecha a tela inicial
                FuncionarioController funcionarioController = new FuncionarioController();
                funcionarioController.abrirListagem();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    public MouseListener abrirTelaProduto() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharTelaInicial(); // Fecha a tela inicial
                ProdutoController produtoController = new ProdutoController();
                produtoController.abrirListagemProdutos();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    public MouseListener abrirTelaCliente() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharTelaInicial(); // Fecha a tela inicial
                ClienteController clienteController = new ClienteController();
                clienteController.abrirListagemCLientes();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    public MouseListener abrirTelaVendas() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharTelaInicial(); // Fecha a tela inicial
                VendaController vendaController = new VendaController();
                vendaController.abrirListagemVenda();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    // Método responsável por fechar a tela inicial corretamente
    private void fecharTelaInicial() {
        if (janelaTela != null) {
            janelaTela.dispose(); // Fecha a janela e libera os recursos
            janelaTela = null; // Garantir que a referência à janela seja removida
        }
    }
}
