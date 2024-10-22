package controle;

import javax.swing.JOptionPane;

import modelo.Funcionario;
import visao.AlterarFuncionario;
import visao.CadastroFuncionarios;
import visao.ListagemFuncionarios;

public class FuncionarioController {
	FuncionarioDAO fdao = new FuncionarioDAO();
	Funcionario f = new Funcionario();

	public void inserirFuncionario() {
	// inserir
		CadastroFuncionarios janelaFuncionarios = new CadastroFuncionarios();
		janelaFuncionarios.setVisible(true);		
	}
	
	public void excluirFuncionario() {
	// excluir
		
	}

	public void selecionaBusca(int id_Funcionario) {
		
		// Alterar
		f = fdao.bucarFuncionario(id_Funcionario);
		AlterarFuncionario janelaAlterar = new AlterarFuncionario(f);
		janelaAlterar.setVisible(true);
		
	}
	
	public void alterarFuncionarioBanco() {

}
}