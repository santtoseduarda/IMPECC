package BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
	public static Connection getConexaoMySQL () {
		Connection connection = null; // criando um metodo zerada
		
		// driver de conexão
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("O Dirver especificado não foi encontrado!");
		}
		
		//nome do servidor
		String serverName = "localhost:3306";
		
		// nome do banco de dados
		String mydatabase = "impecc";
		
		// url de conexao
		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
		
		String username = "root";
		
		String password = "aluno";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível conextar ao banco de dados");
		}
		
		return connection;
	}

}
