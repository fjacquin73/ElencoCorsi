package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Statistiche;

public class CorsoDAO {

	/**
	 * Ritorna tutti i risultati della tabella corso
	 * @return
	 */
	public List<Corso> listAll(){
		
		String sql = "SELECT codins, crediti, nome, pd FROM corso";
		
		List<Corso> result = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), 
						res.getInt("pd"));
				
				result.add(c);
				
			}
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
			
		}
		
		return result;
		
	}
	
	/**
	 * Ritorna i corsi che hanno il periodo didattico passato come parametro {@code pd}
	 * @param pd
	 * @return
	 */
	public List<Corso> listByPD(int pd) {
		
		String sql = "SELECT codins, crediti, nome, pd FROM corso WHERE pd = ?";
		
		List<Corso> result = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), 
						res.getInt("pd"));
				
				result.add(c);
				
			}
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
			
		}
		
		return result;


	}

	public Statistiche getStatisticheByCodins(String codIns) {

		String sql ="SELECT cds, COUNT(cds) as count FROM studente as s, iscrizione as i WHERE s.matricola = i.matricola AND i.codins = ? GROUP BY cds";
		
		Statistiche stat = new Statistiche();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				stat.getMappaCDS().put(res.getString("cds"), res.getInt("count"));
				
			}
			
			conn.close();
			
		} catch  (SQLException e) {
			throw new RuntimeException (e);
		}
		
		return stat;
		
	}
	
}
