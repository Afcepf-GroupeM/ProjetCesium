package fr.afcepf.groupem.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import org.springframework.stereotype.Component;




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
	public String validationCommande() {
		
		return null;
	}
	
	

}
