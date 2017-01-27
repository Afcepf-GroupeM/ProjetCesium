package fr.afcepf.groupem.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.ILivraisonBus;
import fr.afcepf.groupem.business.api.ITrackingCodeService;
import fr.afcepf.groupem.business.api.ITransporteurBus;
import fr.afcepf.groupem.dto.DemandeLivraisonDto;
import fr.afcepf.groupem.dto.ReponseLivraisonDto;
import fr.afcepf.groupem.entities.Adresse;
import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Transporteur;




@Component

@Path("/PriseEnCharge")
public class PriseEnCharge {
	
	@Autowired
	ITrackingCodeService tcs;
	
	@Autowired
	ITransporteurBus transporteurBus;
	

	
	@Autowired
	ILivraisonBus livraisonBus;
	

	
	@GET
	@Path("/hello")
	@Produces("text/plain")
	public String hello(){		
		return "WS - Logistique Hello Lol";
	}
	
	
	@GET
	@Path("/testjson")
	@Produces("application/json")
	public Livraison testJson(){		
		Livraison liv = new Livraison();
		liv.setId(1);
		liv.setNbItems(42);
		liv.setAdresse(null);
		liv.setStatut(null);
		liv.setTrackingCode("UPS-FR75-4564646546");
		liv.setDatePriseEnCharge(new Date());
		
		return liv;
	}
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ReponseLivraisonDto addDemande(DemandeLivraisonDto demande){
		Adresse adresse = new Adresse();
		adresse.setLastname(demande.getLastName());
		adresse.setFirstname(demande.getFirstName());
		adresse.setNumero(demande.getNumero());
		adresse.setComplement(demande.getComplement());
		adresse.setTypeVoie(demande.getTypeVoie());
		adresse.setNomVoie(demande.getNomVoie());
		adresse.setCity(demande.getCity());
		adresse.setZipcode(demande.getZipcode());
		adresse.setCountry(demande.getCountry());
		
		System.out.println("Adresse: " + adresse);
		System.out.println("Nb items: " + demande.getNbItems());
		System.out.println("Delai max: " + demande.getDelaiMax());
		
		Transporteur transporteur = transporteurBus.chooseTransporteurByDelaiMax(demande.getDelaiMax());
		
		Livraison liv = new Livraison();
		liv.setAdresse(adresse);
		liv.setDateDemande(new Date());
		liv.setDatePriseEnCharge(new Date());
		liv.setNbItems(demande.getNbItems());
		liv.setTransporteur(transporteur);
		liv.setTrackingCode(tcs.generateTrackingCode(transporteur, adresse.getCountry(), adresse.getZipcode()));
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, demande.getDelaiMax());
				
		Date dateLivraisonPrevue = cal.getTime();
		liv.setDateLivraison(dateLivraisonPrevue);
		
		liv = livraisonBus.createLivraison(liv);
		
		ReponseLivraisonDto reponse = new ReponseLivraisonDto();
		reponse.setTrackingCode(liv.getTrackingCode());
		reponse.setNbItems(liv.getNbItems());
		reponse.setDateDemande(liv.getDateDemande());
		reponse.setDatePriseEnCharge(liv.getDatePriseEnCharge());
		reponse.setDateLivraison(liv.getDateLivraison());
		
		reponse.setLastName(adresse.getLastname());
		reponse.setFirstName(adresse.getFirstname());
		reponse.setNumero(adresse.getNumero());
		reponse.setComplement(adresse.getComplement());
		reponse.setTypeVoie(adresse.getTypeVoie());
		reponse.setNomVoie(adresse.getNomVoie());
		reponse.setCity(adresse.getCity());
		reponse.setZipcode(adresse.getZipcode());
		reponse.setCountry(adresse.getCountry());
		
		reponse.setTransporteurName(transporteur.getName());
		reponse.setUrl(transporteur.getUrl());
		
		return reponse;
	}
	

}
