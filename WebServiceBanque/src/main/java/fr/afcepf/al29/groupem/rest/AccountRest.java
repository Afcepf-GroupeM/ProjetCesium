package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;

@Path("UserAccountService")
public class AccountRest {
	private AccountBusApi accountBusApi;
	@GET
	@Produces("text/plain")
	@Path("euroToFranc/{montant}")
	public void verifyAccount(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("crytogram") String crytogram,@PathParam("lastName") String lastName,@PathParam("balance") BigDecimal balance){
		accountBusApi.getIdByNumberCard(numberCard);
		
		
	}

}
