package controle;

import visao.AlterarFornecedor;
import modelo.Fornecedor;

public class FornecedorController {

	public void alterarFornecedor(int id_Fornecedor) {

		FornecedorDAO forDao = new FornecedorDAO();
		Fornecedor fornecedor = forDao.bucarFornecedor(id_Fornecedor);
		AlterarFornecedor janelaAlterar = new AlterarFornecedor(fornecedor);
		janelaAlterar.setVisible(true);

	}
}
