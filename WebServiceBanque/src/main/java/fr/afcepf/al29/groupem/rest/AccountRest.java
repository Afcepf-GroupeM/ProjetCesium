package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import fr.afcepf.al29.groupem.entities.Customer;
import fr.afcepf.al29.groupem.entities.ResponseBank;

@Component
@Path("UserAccountService")
public class AccountRest {
	private int id;
	private Account account;
	private String name;
<<<<<<< HEAD
	private Boolean numberCardExiste=false;
	private ResponseBank responseBank;
	
	
=======
	private Boolean numberCardExiste = false;
	private Boolean isExpired = true;
	private Boolean cryptogramCorrect = false;
	private Boolean nameCorrect = false;
	private ResponseBank responseBank;	
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git
	
	public AccountRest() {
		super();
	}

	@Autowired
	private AccountBusApi accountBus;
	
	@GET
<<<<<<< HEAD
	@Produces("application/json")
	@Path("receptionInfoReturnResponse/")
	public ResponseBank receptionInfoReturnResponse(@PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("cryptogram") String cryptogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal amount){
		//get the account by numberCard
		account = getAccountByNumberCard(numberCard);
=======
	@Produces("application/json")
	@Path("/receptionInfoReturnResponse")
	public ResponseBank receptionInfoReturnResponse(@PathParam("nameCompany")String nameCompany, @PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("cryptogram") String cryptogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal amount){
		//get the account by numberCard
		accounts = getAccountByNumberCard(numberCard);
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git
		//verify if the numberCard existe in the BDD
		
		if(accounts.size()==0){
			numberCardExiste = false;			
		}else{					
			numberCardExiste = true;
			//take the object account
			account = accounts.get(0);
			//verify the DateExpired is still valide	
			isExpired = verifyDateExpiredCard(account);
			if(isExpired = false){
				//verify the Crytogram is correct
				verifyCryptogram(account.getCryptogram());
				if(cryptogramCorrect = true ){				
					//verify the Name is correct
					verifyName(account);
					//verify the customer get enough money to pay the amount
					verifyAmount(amount);
					//TODO: construire la réponse
					//TODO: Debit balance of account
					debitAccount(amount);
				}
			}
		}

		
		//TODO: put the status and ... in the object responseBank, and send the response
		return responseBank;
		
	}	
	
	public Account getAccountByNumberCard(String numberCard){
		account = null;
		System.out.println("**********************ici3333333333333");
		System.out.println(numberCard);
		Boolean a = false;
		System.out.println("dans AccountRest :accountBus ="+ accountBus);
		AccountBusImpl accountbus2 = new AccountBusImpl();
		System.out.println("Accountbus2 : " + accountbus2);
		account = accountbus2.getAccountByNumberCard(numberCard);
		return account;
	}
	
	public boolean verifyDateExpiredCard(Account account){
		Boolean expired = true;
		//year and month of today:
		Calendar cToday = Calendar.getInstance();
		int yearToday = cToday.get(Calendar.YEAR);
		int monthToday = cToday.get(Calendar.MONTH) + 1;
		
		//year and month of expiredDay of bankCard:		
		Calendar cExpiredDay = new GregorianCalendar();
		cExpiredDay.setTime(account.getDateExpiredCarte());
		int yearExpiredDay = cExpiredDay.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int monthExpiredDay = cExpiredDay.get(Calendar.MONTH) + 1;
		//int day = cExpiredDay.get(Calendar.DAY_OF_MONTH);
		if(yearExpiredDay < yearToday){
			expired = false;
			if(monthExpiredDay < monthToday){
				expired = false;
			}else{
				expired = true;
			}
		}else{
			expired = true;
		}
		return expired;
	}
	
	public boolean verifyCryptogram(String cryptogram){
		
		if(cryptogram.equals(account.getCryptogram())){
			cryptogramCorrect = true;
		} else {
			cryptogramCorrect = false;
		}
		return cryptogramCorrect;
	}
	
	public String verifyName(Account account){
		Customer customer = new Customer();
		customer = accountBus.getCustomerByAccount(account);
		return null;
	}
	
	public Boolean verifyAmount(BigDecimal amount){
		return false;
		
	}
	
	public void debitAccount(BigDecimal amount){
		accountBus.debitAccount(amount);
	}
	
	@GET
	@Produces("text/plain")
	@Path("/test")
	public Account test(@PathParam("numberCard")String numberCard){
		Account account = null;
		numberCard = "123456789";
		account = getAccountByNumberCard(numberCard);
		return account;
	}
	
	
	@GET
	@Produces("text/plain")
	@Path("/test2")
	public String test2(){
		
		return "lol";
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
