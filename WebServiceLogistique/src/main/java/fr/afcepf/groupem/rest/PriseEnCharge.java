package fr.afcepf.groupem.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.ILivraisonBus;

@Component

@Path("/PriseEnCharge")
@Produces("application/json")
@Consumes("application/json")
public class PriseEnCharge {
	
	@Autowired
	private ILivraisonBus livraisonBus;
	
	
	@Path("/sendPackage")
	@PUT
	public String sendPackage(){
		
		return null;
	}
	
	

}
