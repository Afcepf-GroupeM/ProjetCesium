package fr.afcepf.groupem.rest;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;



@Component

@Path("/PriseEnCharge")
@Produces("text/plain")
public class PriseEnCharge {
	

	
	@GET
	public String sendPackage(){		
		return "WS - Logistique Lol";
	}
	
	

}
