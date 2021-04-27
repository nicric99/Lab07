package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	ArrayList<Evento> soluzioneMigliore= new ArrayList<Evento>();
	int personeCoinvolte=0;
	List<Evento> partenza= new ArrayList<Evento>();
	double oretotali=0;
	
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
		partenza=podao.getEventi(nerc);
	cerca(parziale,0,maxYears,maxHours,nerc);
	return soluzioneMigliore;
		
		
		
		
	}

	private void cerca(List<Evento> parziale, int l, int maxYears, int maxHours,Nerc nerc) {
		
		if(l!=0 && parziale.size()>1) {
			if(!this.isValid(parziale, maxYears, maxHours)) {
			return;
			}
			else{
			int personeCoinvolteTmp= this.getPersoneCoinvolte(parziale);
			if(personeCoinvolteTmp> this.personeCoinvolte) {
				soluzioneMigliore= new ArrayList(parziale);
				this.personeCoinvolte=personeCoinvolteTmp;
				this.oretotali= this.oretot(parziale);
			}
		}
		}
		// questo è utilizzato per gestire i casi di un solo caso avvenuto
		if(parziale.size()==1) {
			if(this.isValid(parziale, maxYears, maxHours)) {
			int personeCoinvolteTmp= this.getPersoneCoinvolte(parziale);
			if(personeCoinvolteTmp> this.personeCoinvolte) {
				soluzioneMigliore= new ArrayList(parziale);
				this.personeCoinvolte=personeCoinvolteTmp;
				this.oretotali= this.oretot(parziale);
			}}
		}
		if(l==partenza.size()) {
			return;
		}
		parziale.add(partenza.get(l));
		if(this.presente(parziale)) {
			return;
		}
		cerca(parziale,l+1,maxYears,maxHours,nerc);
		
		parziale.remove(partenza.get(l));
		cerca(parziale,l+1,maxYears,maxHours,nerc);
	}
	
	
	private boolean isValid(List<Evento> parziale,int maxYears,int maxHours) {
		
		if(parziale.size()>1) {
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
		}else {
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
		
	}
	public double oretot(List<Evento> parziale) {
		double sum=0;
		for(Evento e:parziale) {
			sum=sum+ e.getTimeMinute();
		}
		double tempo=(double)sum/60;
		return tempo;
	}
	private boolean presente(List<Evento> parziale) {
		Set<Evento> confronto= new HashSet<Evento>();
		for(Evento e:parziale) {
			confronto.add(e);
		}
		if(parziale.size()>confronto.size()) {
			return true;
		}else {
			return false;
		}
		
	}
	private int getPersoneCoinvolte(List<Evento> parziale) {
		int sum=0;
		for(Evento e:parziale) {
			sum+=e.getCustomerAffected();
		}
		return sum;
	}
	
	
}