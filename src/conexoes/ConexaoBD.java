package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	private static String url =  "jdbc:mysql://localhost:3306/vendas";
	private static String user = "root";
	private static String password = "teste123";
	
	public  Connection getConnection() {
		try {
			//System.out.println("Conexão realizada com sucesso");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println("Falha na conexão");
			throw new RuntimeException(ex);
		}
	}

}
