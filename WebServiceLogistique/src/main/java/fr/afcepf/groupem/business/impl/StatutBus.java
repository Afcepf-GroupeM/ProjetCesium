package fr.afcepf.groupem.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afcepf.groupem.business.api.IStatutBus;
import fr.afcepf.groupem.dao.api.IStatutDao;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

public class StatutBus implements IStatutBus {
	
	@Autowired
	IStatutDao statutDao;

	
	@Override
	public Statut getStatutByLivraisonId(int idLivraison) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatutLine> getStatutLinesByStatutId(int idStatut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statut createNewStatut() {
		Statut statut = statutDao.createStatut();
		
		StatutLine statutLine = new StatutLine();
		statutLine.setIdstatut(statut.getId());
		statutLine.setDateUpdate(new Date());
		statutLine.setLocationUpdate("PARIS");
		statutLine.setDetailsUpdate("Demande de prise en charge par le marchand.");
		statutLine = statutDao.createStatutLine(statutLine);
		List<StatutLine> statutLines = new ArrayList<>();
		statutLines.add(statutLine);
		statut.setStatutLines(statutLines);
		
		statut = statutDao.updateStatut(statut);
		
		return statut;
	}

	@Override
	public Statut addStatutLine(Statut statut, StatutLine statutLine) {
		// TODO Auto-generated method stub
		return null;
	}

}
