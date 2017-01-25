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
import fr.afcepf.groupem.business.api.ITranspoteurBus;
import fr.afcepf.groupem.entities.DemandeLivraison;
import fr.afcepf.groupem.entities.Livraison;




@Component

@Path("/PriseEnCharge")
public class PriseEnCharge {
	
	@Autowired
	ITrackingCodeService tcs;
	
	@Autowired
	ITranspoteurBus transporteurBus;
	

	
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
	public Livraison addDemande(DemandeLivraison demande){
		
		Livraison liv = new Livraison();
		liv.setAdresse(demande.getAdresse());
		liv.setDateDemande(new Date());
		liv.setDatePriseEnCharge(new Date());
		liv.setNbItems(demande.getNbItem());
		liv.setTrackingCode(tcs.generateTrackingCode(transporteurBus.chooseTransporteurByDelaiMax(demande.getDelaiMax()), 
													 demande.getAdresse().getCountry(), 
													 demande.getAdresse().getZipcode()));
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, demande.getDelaiMax());
				
		Date dateLivraisonPrevue = cal.getTime();
		liv.setDateLivraison(dateLivraisonPrevue);
		
		liv = livraisonBus.createLivraison(liv);
		
		return liv;
	}
	

}
