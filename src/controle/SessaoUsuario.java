package controle;

import modelo.Funcionario;

public class SessaoUsuario {
	
	 private static Funcionario funcionarioLogado;

	    public static void setFuncionarioLogado(Funcionario funcionario) {
	        funcionarioLogado = funcionario;
	    }

	    public static Funcionario getFuncionarioLogado() {
	        return funcionarioLogado;
	    }
	    
}
