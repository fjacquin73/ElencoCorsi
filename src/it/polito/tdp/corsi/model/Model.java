package it.polito.tdp.corsi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {

	private List<Corso> corsi;
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
		
	}
	
	public List<Corso> listaCorsiSemestre (int pd) {
		
		List<Corso> risultato2 = corsoDAO.listByPD(pd);
		return risultato2;
		
	}

	public String getNomeCognomeByMatricola(int matricola) {
		
		Studente studente = studenteDAO.getStudenteByMatricola(matricola);
		
		if (studente == null) {
			return " Non ho trovato nessuno studente associato alla magtricola";
		}
		
		return studente.getNome() + " " + studente.getCognome();

	}

	public String getStatisticheFromCorsi() {
		
		this.corsi = corsoDAO.listAll();
		
		StringBuilder sb = new StringBuilder();
		
		for (Corso c : this.corsi) {
			Statistiche stat = corsoDAO.getStatisticheByCodins(c.getCodIns());
			sb.append("codins: " + c.getCodIns() + " " + stat.toString()+ "\n");
		}
		
		return sb.toString();
	}
	
}
