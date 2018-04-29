package it.polito.tdp.corsi.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		List<Corso> corsi = m.listaCorsiSemestre(2);
		
		for(Corso c : corsi) {
			System.out.println(c.toString());
		}
		
		int matricola = 146102;
		String result = m.getNomeCognomeByMatricola(matricola);
		System.out.println("Matricola "+ matricola + " nome cognome:" + result);
		
		result = m.getStatisticheFromCorsi();
		System.out.println(result);
		
	}

}
