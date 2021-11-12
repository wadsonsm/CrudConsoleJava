package br.com.agenda.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO
{

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
			System.out.println("Contato salvo com sucesso !");
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
	
	@SuppressWarnings("finally")
	public List<Contato> getContatos()

	{
		String sql = "SELECT * FROM CONTATOS";
		
		List<Contato> contatos = new ArrayList<Contato>();
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//classe que vai recuperar os dados do banco ** SELECT **
		ResultSet rst = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rst = pstm.executeQuery();
			
				while (rst.next())
				{
					Contato contato = new Contato();
					//recuperar id
					contato.setId(rst.getInt("id"));
					//recupear nome
					contato.setNome(rst.getString("nome"));
					// recuperar a idade
					contato.setIdade(rst.getInt("idade"));
					//recuperar a data de cadastro
					contato.setDataCadastro(rst.getDate("dataCadastro"));
					
					contatos.add(contato);			
				}
			
			}catch (Exception e){
				e.printStackTrace();
			}finally{	
				try {
					if(pstm != null) {
						pstm.close();
					}
					
					if(conn != null){
						conn.close();
					}
				}catch (Exception e2) {
						e2.printStackTrace();
					}	
				return contatos;
	}}
	
	public void update(Contato contato)
	{
		String sql = "UPDATE CONTATOS SET NOME = ?, IDADE = ?, DATACADASTRO = ? WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
			
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally {
				try {
					if(pstm!= null) {
						pstm.close();
					}
					
					if(conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		
	}
	
	public void deleteById(int id)
	{
		String sql = "DELETE FROM CONTATOS WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.execute();
			
			System.out.println("Excluído com sucesso !");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
	
	
}

	


