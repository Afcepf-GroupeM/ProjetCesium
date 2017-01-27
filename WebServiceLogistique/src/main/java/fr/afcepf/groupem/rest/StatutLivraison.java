package fr.afcepf.groupem.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.ILivraisonBus;
import fr.afcepf.groupem.business.api.IStatutBus;
import fr.afcepf.groupem.dto.DemandeStatutDto;
import fr.afcepf.groupem.dto.EnvoiStatutDto;
import fr.afcepf.groupem.entities.Adresse;
import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;
import fr.afcepf.groupem.entities.StatutLine;
import fr.afcepf.groupem.entities.Transporteur;

@Component
@Path("/StatutLivraison")
public class StatutLivraison {

	@Autowired
	ILivraisonBus livraisonBus;
	@Autowired
	IStatutBus statutBus;
	
	@POST
	@Path("/send")
	@Consumes("application/json")
	@Produces("application/json")
	public EnvoiStatutDto sendStatut(DemandeStatutDto demande){
		String trackingCode = demande.getTrackingCode();
		
		System.out.println("Code de tracking à recherher: " + trackingCode);
		
		Livraison livraison = livraisonBus.getLivraisonByTrackingCode(trackingCode);
		Adresse adresse = livraison.getAdresse();
		Statut statut = livraison.getStatut();
		Transporteur transporteur = livraison.getTransporteur();
		List<StatutLine> statutLines = statutBus.getStatutLinesByStatutId(statut.getId());
		
		int sLine = 1;
		Map<Integer, List<String>> sLines = new HashMap<Integer, List<String>>();
		
		for (StatutLine statutLine : statutLines){
			List<String> slContent = new ArrayList<>();
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateUpdateSl = statutLine.getDateUpdate();
			String dateUpdate = df.format(dateUpdateSl);
			
			slContent.add(dateUpdate);
			slContent.add(statutLine.getLocationUpdate());
			slContent.add(statutLine.getDetailsUpdate());
			
			sLines.put(sLine, slContent);
			sLine++;
		}
		
		EnvoiStatutDto etatLivraison = new EnvoiStatutDto();
		etatLivraison.setTrackingCode(livraison.getTrackingCode());
		etatLivraison.setNbItems(livraison.getNbItems());
		etatLivraison.setDateDemande(livraison.getDateDemande());
		etatLivraison.setDatePriseEnCharge(livraison.getDatePriseEnCharge());
		etatLivraison.setDateLivraison(livraison.getDateLivraison());
		
		etatLivraison.setLastName(adresse.getLastname());
		etatLivraison.setFirstName(adresse.getFirstname());
		etatLivraison.setNumero(adresse.getNumero());
		etatLivraison.setComplement(adresse.getComplement());
		etatLivraison.setTypeVoie(adresse.getTypeVoie());
		etatLivraison.setNomVoie(adresse.getNomVoie());
		etatLivraison.setCity(adresse.getCity());
		etatLivraison.setZipcode(adresse.getZipcode());
		etatLivraison.setCountry(adresse.getCountry());
		
		etatLivraison.setTransporteurName(transporteur.getName());
		etatLivraison.setUrl(transporteur.getUrl());
		
		etatLivraison.setStatutLines(sLines);
		
		return etatLivraison;
	}

}