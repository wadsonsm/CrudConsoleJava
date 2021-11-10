package br.com.agenda.dao;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void save(Contato contato)
	{
		String sql = "INSERT INTO contatos (NOME,IDADE,DATACADASTRO)VALUES(?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// cria conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
