package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.poweroutages.model.*;
import it.polito.tdp.poweroutages.model.Nerc;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		PowerOutageDAO dao= new PowerOutageDAO();
		/*try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}*/
		Nerc nerc= new Nerc(2,"ERCOT");
		List<Evento> eventi= new ArrayList<Evento>(dao.getEventi(nerc));
		for(Evento e:eventi) {
			System.out.println(e.getDate_event_began().toString()+"\n");
		}
		
	}

}
