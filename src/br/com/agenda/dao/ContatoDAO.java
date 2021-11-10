package br.com.agenda.dao;
import java.sql.Connection;
import java.sql.Date;
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
			// criamos uma preparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);			
				// adicinoar os valores esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//fechar as conexões 
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null)
				{
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
