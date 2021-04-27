package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	List<Evento> soluzioneMigliore= new ArrayList<Evento>();
	int personeCoinvolte;
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public List<Evento> getOptimalSolution(Nerc nerc,int maxYears, int maxHours){
		
		List<Evento> parziale= new ArrayList<Evento>();
		this.personeCoinvolte=0;
		cerca(parziale,0,maxYears,maxHours,nerc);
		return soluzioneMigliore;
		
		
		
	}

	private void cerca(List<Evento> parziale, int l, int maxYears, int maxHours,Nerc nerc) {
		
		
		
		
		
		
		
	}
	
	
	private boolean isValid(List<Evento> parziale,int maxYears,int maxHours) {
		
		
		long daysBetween=ChronoUnit.DAYS.between(parziale.get(0).getDate_event_began(),parziale.get(parziale.size()-1).getDate_event_finished());
		double years=(double) daysBetween/365;
		if(years>maxYears) {
			return false;
		}
		
		
		
		double sum=0;
		for(Evento e:parziale) {
			sum=sum+ e.getTimeMinute();
		}
		double tempo=(double)sum/60;
		if(tempo> maxHours) {
			return false;
		}
		return true;
		
	}
	private int getPersoneCoinvolte(List<Evento> parziale) {
		int sum=0;
		for(Evento e:parziale) {
			sum+=e.getCustomerAffected();
		}
		return sum;
	}
	
	
}