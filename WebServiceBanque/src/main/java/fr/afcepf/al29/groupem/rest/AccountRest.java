package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;
import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.ResponseBank;

@Path("UserAccountService")
public class AccountRest {
	private int id;
	private Account account;
	private String name;
	private AccountBusApi accountBusApi;
	
	@GET
	@Produces("application/json")
	@Path("verifyAccount/{numberCard,dateExpiredCarte,crytogram,lastName,balance}")
	public ResponseBank receptionInfoReturnResponse(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("crytogram") String crytogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal balance){
		id = verifyAccount(numberCard, dateExpiredCarte, crytogram, lastName, balance);
		account = getAccountByID(id);
		verifyDateExpiredCard(account);
		verifyCrytogram(account);
		verifyName(name);
		verifyAmount(account);
		return null;
		
	}
		
	public Integer verifyAccount(String numberCard, Date dateExpiredCarte,String crytogram,String lastName, BigDecimal balance){

		return accountBusApi.getIdByNumberCard(numberCard);


		

	}
	
	public Account getAccountByID(Integer id){
		return null;
	}
	
	public boolean verifyDateExpiredCard(Account account){
		return false;
	}
	
	public boolean verifyCrytogram(Account account){
		return false;
	}
	
	public String verifyName(String name){
		return null;
	}
	
	public Boolean verifyAmount(Account account){
		return false;
	}
}
