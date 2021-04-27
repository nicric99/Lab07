package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Evento> parziale= new ArrayList<Evento>();
		List<Evento> parziale1= new ArrayList<Evento>();
		LocalDate d1=LocalDate.of(1999,03,03);
		LocalDate d2=LocalDate.of(1999,04,04);
		LocalDate d3=LocalDate.of(2000,03,03);
		LocalDate d4=LocalDate.of(2018,04,04);
		
		Evento a= new Evento(1,50,11,d1,d2,1);
		Evento b=new Evento(2,50,11,d3,d4,1);
//		parziale.add(a);
//		parziale.add(b);
		Nerc nerc= new Nerc(3,"MAAC");
		parziale= model.getOptimalSolution(nerc, 25, 10000);
		
		System.out.println(parziale.size());
		for(Evento e:parziale) {
		System.out.println(e.toString());
		}
		System.out.println(model.personeCoinvolte+"\n"+model.oretotali);
		

	}

}
