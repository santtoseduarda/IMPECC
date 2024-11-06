package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import modelo.Cliente;
import modelo.Funcionario;
import visao.AlterarCliente;
import visao.CadastroCliente;

public class ClienteController {
	
	//instanciar as classes que vão ser usadas
	
	Cliente cliente = new Cliente();
	ClienteDAO clidao = new ClienteDAO();
	ListagemCliente janelaListagem = new ListagemCliente(this);
	CadastroCliente janelaCadastro = new CadastroCliente(this);
	AlterarCliente janelaAlterar = new AlterarCliente(cliente, this);
	
	
	public void abrirListagemCLientes() {
		atualizarTabela("", "");
		janelaListagem.setVisible(true);
	}
	
	public void iniciarCadastroCliente() {
		limparCamposCadCliente();
		janelaCadastro.setVisible(true);
		janelaListagem.dispose();
	}
	
	public MouseListener voltarListagem() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteController clienteController = new ClienteController();
				clienteController.abrirListagemCLientes();
				janelaCadastro.dispose();
			}
		};
	}
	
	public ActionListener cadastrarCliente() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (limparCamposCadClientes()) {
					
					Cliente cadastro = new Cliente();
					
					cadastro.setNomeCliente(janelaCadastro.txtNome.getText());
					cadastro.setDataNasc(janelaCadastro.txtDataNsc.getText());
					cadastro.setCpf_Cliente(janelaCadastro.txtCPF.getText());
					cadastro.setTelefone(janelaCadastro.txtTelefone.getText());
					cadastro.setEmail(janelaCadastro.txtEmail.getText());
					
					ClienteDAO novoCliente = new ClienteDAO();
					try {
						novoCliente.inserir(cadastro);
						janelaListagem.setVisible(true);
						atualizarTabela("", "");
						janelaCadastro.dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		};
	}
	
	public void limparCamposCadClientes() {
		// TODO Auto-generated method stub
		
				janelaCadastro.txtNome.setText("");
				janelaCadastro.txtDataNsc.setText("");
				janelaCadastro.txtCPF.setText("");
				janelaCadastro.txtTelefone.setText("");
				janelaCadastro.txtEmail.setText("");
	}
	
	
}
