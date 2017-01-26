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
	private BigDecimal balance;
	private BigDecimal amount;
	
	@GET
	@Produces("application/json")
	@Path("verifyAccount/{numberCard,dateExpiredCarte,crytogram,lastName,balance}")
	public ResponseBank receptionInfoReturnResponse(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("crytogram") String crytogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal balance){
		id = verifyAccount(numberCard, dateExpiredCarte, crytogram, lastName, balance);
		account = getAccountByID(id);
		verifyDateExpiredCard(account);
		verifyCryptogram(account);
		verifyName(name);
		verifyAmount(amount);
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
	
	public boolean verifyCryptogram(Account account){
		
		
		
		
		
		return false;
	}
	
	public String verifyName(String name){
		return null;
	}
	
	public Boolean verifyAmount(BigDecimal amount){
		if (amount.compareTo(account.getBalance())< 0) 
			System.out.println();
		
		
		return false;
	}
}
