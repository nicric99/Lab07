package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Evento;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	public List<Evento> getEventi(Nerc nerc){
	 final String sql="SELECT TIMESTAMPDIFF(MINUTE,date_event_began,date_event_finished) AS tempo,date_event_finished,date_event_began,nerc_id,n.value,n.id,customers_affected "+
			 "FROM poweroutages,nerc AS n "+
			 "WHERE nerc_id=? AND poweroutages.nerc_id=n.id "+
			 "ORDER BY poweroutages.date_event_began ";
	
	List<Evento> eventi= new ArrayList<Evento>();
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1,nerc.getId());
		ResultSet res = st.executeQuery();
		

		while (res.next()) {
			Timestamp ts1= res.getTimestamp("date_event_began");
			Timestamp ts2= res.getTimestamp("date_event_finished");
			LocalDateTime d11=ts1.toLocalDateTime();
			LocalDateTime d22=ts2.toLocalDateTime();
			LocalDate dateb=d11.toLocalDate();
			LocalDate datef=d22.toLocalDate();
			Evento e= new Evento(res.getInt("id"),res.getInt("customers_affected"),res.getInt("nerc_id"),dateb,datef,res.getInt("tempo"));
			eventi.add(e);
		}

		conn.close();
		return eventi;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}	
	
	
	}
	

}
