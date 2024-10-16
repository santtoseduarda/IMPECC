package controle;


import modelo.Produto;
import visao.AlterarProduto;

public class ProdutoController {

	public void alterarProdutos(int id_Produto) {
		
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = pdao.buscarProdutos(id_Produto);
		AlterarProduto janelaAlterar = new AlterarProduto(p);
		janelaAlterar.setVisible(true);
		
	}

}
