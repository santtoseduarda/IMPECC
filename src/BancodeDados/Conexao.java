package BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/impecc"; // formato do conctor que irá utilizar
		// localhost: 3306 é o local da máquina = porta de comunicação
		
		String user = "root";
		String passoword = "Berbelle77-";
		
		// try = tentar
		try (Connection connection = DriverManager.getConnection(url, user, passoword)) {
			System.out.println("Conexão bem sucedida");
		} catch (SQLException e) {
			e.printStackTrace(); // imprimi erro
			System.out.println("Falha na conexão com o banco de dados");
		}
	}

}
