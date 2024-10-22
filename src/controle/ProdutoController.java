package controle;


import modelo.Produto;
import visao.AlterarProduto;
import visao.CadastroFuncionario;
import visao.CadastroProduto;

public class ProdutoController {

	ProdutoController janelaControllerProd = this;
	
	ProdutoController listagemProduto = new ProdutoController();
	listagemProduto.setVisible(true);
	dispose();
	
	
	public void alterarProdutos(int id_Produto) {
		
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarProdutos(id_Produto);
		AlterarProduto janelaAlterar = new AlterarProduto(p);
		janelaAlterar.setVisible(true);
		
	}

}
