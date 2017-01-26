package fr.afcepf.groupem.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.ILivraisonBus;
import fr.afcepf.groupem.entities.Livraison;
import fr.afcepf.groupem.entities.Statut;

@Component
@Path("/StatutLivraison")
public class StatutLivraison {

	@Autowired
	ILivraisonBus livraisonBus;
	
	@POST
	@Path("/send")
	@Produces("application/json")
	public Statut sendStatut(String trackingCode){
		Livraison livraison = livraisonBus.getLivraisonByTrackingCode(trackingCode);
		livraison.toString();
		Statut statut = livraison.getStatut();
		return statut;
	}

}