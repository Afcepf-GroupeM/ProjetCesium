package fr.afcepf.groupem.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.groupem.business.api.IStatutBus;
import fr.afcepf.groupem.dao.api.IStatutDao;
import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;

@Transactional
@Component
public class StatutBus implements IStatutBus {
	
	@Autowired
	IStatutDao statutDao;

	
	@Override
	public Statut getStatutByLivraisonId(int idLivraison) {
		return statutDao.getStatutByLivraisonId(idLivraison);
	}

	@Override
	public List<StatutLine> getStatutLinesByStatutId(int idStatut) {
		return statutDao.getStatutLinesByStatutId(idStatut);
	}

	@Override
	public Statut createNewStatut(Livraison livraison) {
		Statut statut = statutDao.createStatut(livraison);
		
		StatutLine statutLine = new StatutLine();
		statutLine.setStatut(statut);
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
		List<StatutLine> statutLines = statut.getStatutLines();
		statutLines.add(statutLine);
		statut.setStatutLines(statutLines);
		
		statutDao.updateStatut(statut);
		statutDao.createStatutLine(statutLine);
		
		return statut;
	}

}
