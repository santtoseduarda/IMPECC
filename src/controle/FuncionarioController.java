package controle;

import modelo.Funcionario;
import visao.AlterarFuncionario;

public class FuncionarioController {

	public void alterarFuncionario(int id_Funcionario) {
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario f = fdao.bucarFuncionario(id_Funcionario);
		AlterarFuncionario janelaAlterar = new AlterarFuncionario(f);
		janelaAlterar.setVisible(true);
		
	}

}
