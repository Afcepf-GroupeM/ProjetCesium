package fr.afcepf.groupem.rest;


import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import org.springframework.stereotype.Component;

import fr.afcepf.groupem.entities.BankRequest;
import fr.afcepf.groupem.entities.OrderValidationRequest;




@Component

@Path("/Orchestrateur")
public class Orchestrateur {

	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String hello(){		
		return "Orchestrateur test : WS OK";
	}
	
	
	
	@POST
	@Path("/validation")
	@Produces("application/json")
	@Consumes("application/json")
	public String validationCommande(OrderValidationRequest request) {
		
		BankRequest bankRequest = new BankRequest();
		bankRequest.setAmount(request.getMontant());
		bankRequest.setCardNumber(request.getCardNumber());
		bankRequest.setCryptogram(request.getCryptogram());
		Date dateValidite = new Date();
		
		
		
		return null;
	}
	
	
	
	

}
