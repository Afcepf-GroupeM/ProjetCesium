package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;
import fr.afcepf.al29.groupem.entities.ResponseBank;

@Path("UserAccountService")
public class AccountRest {
	private AccountBusApi accountBusApi;
	
	@GET
	@Produces("text/plain")
	@Path("verifyAccount/{numberCard,dateExpiredCarte,crytogram,lastName,balance}")
	public ResponseBank receptionInfoReturnResponse(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("crytogram") String crytogram,@PathParam("lastName") String lastName,@PathParam("balance") BigDecimal balance){
		return null;
		
	}
		
	public Integer verifyAccount(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("crytogram") String crytogram,@PathParam("lastName") String lastName,@PathParam("balance") BigDecimal balance){
		return accountBusApi.getIdByNumberCard(numberCard);
	}

}
