package controle;

import visao.AlterarFornecedor;
import visao.CadastroFornecedores;
import visao.ListagemFornecedor;
import visao.TelaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Fornecedor;

public class FornecedorController {
	
	FornecedorDAO fordao = new FornecedorDAO();
	ListagemFornecedor viewl = new ListagemFornecedor(this);
	CadastroFornecedores viewc = new CadastroFornecedores(this);
	
	
	//listagemFornecedor
	
	
	public void iniciarListagem() {
		viewl.setVisible(true);
	}
		

	public void	cadastroFornecedor() {
		CadastroFornecedores  janelaCadastroFornecedores = new CadastroFornecedores(this);
		janelaCadastroFornecedores.setVisible(true);
		viewl.dispose();
	}
	
	public void editarFornecedor(int id_Fornecedor) {

		FornecedorDAO forDao = new FornecedorDAO();
		Fornecedor fornecedor = forDao.bucarFornecedor(id_Fornecedor);
		AlterarFornecedor janelaAlterar = new AlterarFornecedor(fornecedor);
		janelaAlterar.setVisible(true);

	}
	
	public ActionListener excluirFornecedor() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int posicaoSelecionada = viewl.table.getSelectedRow();
		        
		        if (posicaoSelecionada >= 0) {
		        	
		            DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
		            int idFornecedor = (int) modeloTabela.getValueAt(posicaoSelecionada, 0);

		            int confirmacao = JOptionPane.showConfirmDialog(null, 
		                    "Você tem certeza que deseja excluir o fornecedor?", "Confirmação de Exclusão", 
		                    JOptionPane.YES_NO_OPTION);
		            
		            if (confirmacao == JOptionPane.YES_OPTION) {
		            	
		            	//vai excluir o fornecedor
		            	FornecedorDAO frdao = new FornecedorDAO();
		                boolean certo = frdao.excluirFornecedor(idFornecedor); 

		                if (certo) {
		                	
		                    modeloTabela.removeRow(posicaoSelecionada);
		                    JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso.");
		                    
		                } else {
		                    JOptionPane.showMessageDialog(null, "Erro ao excluir o fornecedor.");
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, selecione um fornecedor para excluir.");
		        }
		    }
		};
	}
	
	public MouseListener sairSistema() {
		return new MouseListener(){
			public void mouseClicked1(MouseEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				// Verifica a resposta
				if (resposta == JOptionPane.YES_OPTION) {
					TelaLogin viewLogin = new TelaLogin(null);

					viewLogin.setVisible(true);
					viewLogin.dispose(); // Fecha a tela de login
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
	
	private void pesquisarPorCampo(String campo, String valor) {
	    DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
	    modeloTabela.setRowCount(0); // Limpa a tabela

	    FornecedorDAO frdao = new FornecedorDAO();
	    ArrayList<Fornecedor> listaFornecedores = frdao.buscarFornecedores(campo, valor); // Passando o campo e o valor
	   
	    for (Fornecedor fr : listaFornecedores) {

	        modeloTabela.addRow(new Object[]{
	                fr.getID_fornecedor(),
	                fr.getNome_Fornecedor(),
	                fr.getCNPJ(),
	                fr.getEmail_Fornecedor(),
	                fr.getTelefone_Fornecedor(),
	        });
	    }
	}
	
	public void atualizarTabela(String campo, String valor) {

		DefaultTableModel modeloTabela = (DefaultTableModel) viewl.table.getModel();
		modeloTabela.setRowCount(0); // Limpa a tabela

		FornecedorDAO frdao = new FornecedorDAO();
		ArrayList<Fornecedor> listaFornecedores = frdao.buscarFornecedores(campo, valor);
		
		
		 if (listaFornecedores != null && !listaFornecedores.isEmpty()) {
		        for (Fornecedor fr : listaFornecedores) {
		            // Adiciona os dados do fornecedor na tabela
		            modeloTabela.addRow(new Object[] {
		            		fr.getID_fornecedor(),
			                fr.getNome_Fornecedor(),
			                fr.getCNPJ(),
			                fr.getEmail_Fornecedor(),
			                fr.getTelefone_Fornecedor(),
		            });
		        }
		    }
	}


	public MouseListener pesquisa(String campo, String valor) {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getSource());
				pesquisarPorCampo(campo, valor);
			}
		};
	}
	
}

