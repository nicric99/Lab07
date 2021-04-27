package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Evento> parziale= new ArrayList<Evento>();
		LocalDate d1=LocalDate.of(1999,03,03);
		LocalDate d2=LocalDate.of(1999,04,04);
		LocalDate d3=LocalDate.of(2000,03,03);
		LocalDate d4=LocalDate.of(2018,04,04);
		
		Evento a= new Evento(1,50,11,d1,d2,1);
		Evento b=new Evento(2,50,11,d3,d4,1);
		parziale.add(a);
		parziale.add(b);
//		System.out.println(model.isValid(parziale, 25, 3));
		

	}

}
