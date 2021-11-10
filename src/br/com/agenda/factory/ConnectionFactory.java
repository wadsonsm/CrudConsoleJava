package br.com.agenda.factory;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	//senha do banco
	private static final String PASSWORD = "051282";
	//caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	//conexão com o banco de dados
	public static Connection createConnectionToMySql() throws Exception
	{
		// Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		// cria conexao com banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		//recuperar conexão com o banco de dados
		Connection con  = createConnectionToMySql();
		if(con != null)
		{
			System.out.println("Conexão obtida com sucesso !");
			con.close();
		}
	}
}
