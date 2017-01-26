package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;
import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.ResponseBank;

@Component
@Path("/UserAccountService")
public class AccountRest {
	private int id;
	private Account account;
	private String name;
	private Boolean numberCardExiste=false;
	private ResponseBank responseBank;	
	
	public AccountRest() {
		super();
	}

	@Autowired
	private AccountBusApi accountBus;
	
	@GET
	@Produces("application/json")
	@Path("/receptionInfoReturnResponse")
	public ResponseBank receptionInfoReturnResponse(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("cryptogram") String cryptogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal amount){
		//get the account by numberCard
		account = getAccountByNumberCard(numberCard);
		//verify if the numberCard existe in the BDD
		/*
		if(account.equals(null)){
			numberCardExiste = false;			
		}else{					
			numberCardExiste = true;
		
			//verify the DateExpired is still valide	
			verifyDateExpiredCard(account);
			//verify the Crytogram is correct
			verifyCryptogram(account.getCryptogram());
			//verify the Name is correct
			verifyName(name);
			//verify the customer get enough money to pay the amount
			verifyAmount(amount);
		}

		*/
		//TODO: put the status and ... in the object responseBank, and send the response
		return responseBank;
		
	}	
	
	public Account getAccountByNumberCard(String numberCard){
		account = null;
		System.out.println("**********************ici3333333333333");
		System.out.println("numberCard" + numberCard);		
		System.out.println("dans AccountRest :accountBus ="+ accountBus);
		System.out.println("Accountbus2 : " + accountBus);
		account = accountBus.getAccountByNumberCard(numberCard);
		return account;
	}
	
	public boolean verifyDateExpiredCard(Account account){
		return false;
	}
	
	public boolean verifyCryptogram(String cryptogram){
		
		if(cryptogram==account.getCryptogram()){
			
		}
		return false;
	}
	
	public String verifyName(String name){
		return null;
	}
	
	public Boolean verifyAmount(BigDecimal amount){
		return false;
		
	}
	
	@GET
	@Produces("text/plain")
	@Path("/test")
	public Account test(@PathParam("numberCard")String numberCard){
		Account account = null;
		numberCard = "123456789";
		account = getAccountByNumberCard(numberCard);
		System.out.println(account.toString());
		return account;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getNumberCardExiste() {
		return numberCardExiste;
	}

	public void setNumberCardExiste(Boolean numberCardExiste) {
		this.numberCardExiste = numberCardExiste;
	}

	public ResponseBank getResponseBank() {
		return responseBank;
	}

	public void setResponseBank(ResponseBank responseBank) {
		this.responseBank = responseBank;
	}

	

	
}
