package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;
import fr.afcepf.al29.groupem.business.OperationBusApi;
import fr.afcepf.al29.groupem.dto.RequestData;
import fr.afcepf.al29.groupem.dto.ResponseBank;
import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;
import fr.afcepf.al29.groupem.entities.Operation;

@Component
@Path("/UserAccountService")
public class AccountRest {
	private int id;
	private List<Account> accounts;
	private Account account;
	private String name;
	private Boolean numberCardExiste = false;
	private Boolean isExpired = true;
	private Boolean cryptogramCorrect = false;
	private Boolean nameCorrect = false;
	private Boolean balanceEnough = false;
	private ResponseBank responseBank = new ResponseBank();	
	//in response, return un status de prélèvement "OK" ou "NOT-OK"
	private String statusResponse;
	//in response, return un referenceNumber = operation.id
	private Integer referenceNumberResponse;
	private Operation operation;
	
	public AccountRest() {
		super();
	}

	@Autowired
	private AccountBusApi accountBus;
	@Autowired
	private OperationBusApi opeBus;
	
	/*
	@GET
	@Produces("application/json")
	@Path("/receptionInfoReturnResponse")
	public ResponseBank receptionInfoReturnResponse(@PathParam("nameCompany")String nameCompany, @PathParam("numberCard")String numberCard,@PathParam("dateExpiredCarte") Date dateExpiredCarte,@PathParam("cryptogram") String cryptogram,@PathParam("lastName") String lastName,@PathParam("amount") BigDecimal amount){	
		//get the account by numberCard
		accounts = getAccountByNumberCard(numberCard);
		System.out.println("***************in AccountRest***in method  line 61" + numberCard);
		//1.verify if the numberCard existe in the BDD		
		if(accounts.size()==0){
			numberCardExiste = false;	
			setResponseBankNegative(statusResponse,referenceNumberResponse);
		}else{					
			numberCardExiste = true;
			//take the object account
			account = accounts.get(0);
			//2.verify the DateExpired is still valide	
			isExpired = verifyDateExpiredCard(account);
			if(isExpired = false){
				//3.verify the Crytogram is correct
				verifyCryptogram(account.getCryptogram());
				if(cryptogramCorrect = true ){				
					//4.verify the Name is correct
					verifyName(account, lastName);
					if(nameCorrect = true){
						//5.verify the customer get enough money to pay the amount
						verifyAmount(account,amount);
						if(balanceEnough = true){
							//construire la réponse
							statusResponse = "OK";
							//créer un opération de prélèvement
							operation = opeBus.createOperation(amount, account,nameCompany);
							//response positive: OK
							referenceNumberResponse = operation.getId();
							responseBank.setStatus(statusResponse);
							responseBank.setReferenceNumber(referenceNumberResponse);							
							//TODO: Debit balance of account
							debitAccount(amount,account);
						}else{
							setResponseBankNegative(statusResponse,referenceNumberResponse);
							  }
					}else{
						setResponseBankNegative(statusResponse,referenceNumberResponse);
						  }
				}else{
					setResponseBankNegative(statusResponse,referenceNumberResponse);
					  }
				
			}else{
				setResponseBankNegative(statusResponse,referenceNumberResponse);
				  }
		}
		return responseBank;		
	}	
	*/
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/receptionInfoReturnResponse")
	public ResponseBank receptionInfoReturnResponse(RequestData requestData){
				//get the account by numberCard
				String numberCard = requestData.getNumberCard();
				String lastName = requestData.getLastName();
				Integer expiredYear = requestData.getYearExpiredCard();
				Integer expiredMonth = requestData.getMonthExpiredCard();
				BigDecimal amount = requestData.getAmount();
				String nameCompany = requestData.getNameCompany();	
				System.out.println(numberCard);
				
				accounts = getAccountByNumberCard(requestData.getNumberCard());
				System.out.println("***************in AccountRest***in method  line 61" + numberCard);
				//1.verify if the numberCard existe in the BDD		
				if(accounts.size()==0){
					numberCardExiste = false;	
					setResponseBankNegative(statusResponse,referenceNumberResponse);
				}else{					
					numberCardExiste = true;
					//take the object account
					account = accounts.get(0);
					System.out.println("**********in method line 138, account récupéré =  " + account.toString());
					//2.verify the DateExpired is still valide						
					isExpired = verifyDateExpiredCard(expiredYear,expiredMonth,account);
					System.out.println("***************isExpired = " + isExpired);
					System.out.println("isExpired == false : " +(isExpired == false));
					if(isExpired == false){
						//3.verify the Crytogram is correct
						verifyCryptogram(account.getCryptogram());
						System.out.println("******************cryptogramCorrect = " + cryptogramCorrect);
						if(cryptogramCorrect == true ){				
							//4.verify the Name is correct
							verifyName(account, lastName);
							System.out.println("******************** nameCorrect = " + nameCorrect);
							if(nameCorrect == true){
								//5.verify the customer get enough money to pay the amount
								verifyAmount(account,amount);
								System.out.println("*****************balanceEnough = " + balanceEnough);
								if(balanceEnough == true){
									//construire la réponse
									statusResponse = "OK";
									//créer un opération de prélèvement
									operation = opeBus.createOperation(amount, account,nameCompany);
									//response positive: OK
									referenceNumberResponse = operation.getId();
									responseBank.setStatus(statusResponse);
									responseBank.setReferenceNumber(referenceNumberResponse);							
									//TODO: Debit balance of account
									debitAccount(amount,account);
								}else{
									setResponseBankNegative(statusResponse,referenceNumberResponse);
									  }
							}else{
								setResponseBankNegative(statusResponse,referenceNumberResponse);
								  }
						}else{
							setResponseBankNegative(statusResponse,referenceNumberResponse);
							  }
						
					}else{
						setResponseBankNegative(statusResponse,referenceNumberResponse);
						  }
				}
				return responseBank;				
	}
	
	public void setResponseBankNegative(String status,Integer reference){
		statusResponse = "NOT-OK";
		referenceNumberResponse = 0;
		responseBank.setStatus(statusResponse);
		responseBank.setReferenceNumber(referenceNumberResponse);
	}
	
	public List<Account> getAccountByNumberCard(String numberCard){
		accounts = null;
		System.out.println("**********************ici3333333333333");
		System.out.println("numberCard : " + numberCard);		
		System.out.println("dans AccountRest :accountBus ="+ accountBus);
		System.out.println("Accountbus2 : " + accountBus);
		accounts = accountBus.getAccountByNumberCard(numberCard);
		return accounts;
	}
	/*
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
	*/
	public boolean verifyDateExpiredCard(Integer yearInput, Integer monthInput, Account account){
		Boolean expired = true;		
		//year and month of today:
		Calendar cToday = Calendar.getInstance();
		int yearToday = cToday.get(Calendar.YEAR);
		System.out.println("****************in verifyDateExpiredCard, yearToday = " + yearToday);
		int monthToday = cToday.get(Calendar.MONTH) + 1;
		System.out.println("****************in verifyDateExpiredCard, monthToday = " + monthToday);
			
		//year and month of expiredDay of bankCard:		
		Calendar cExpiredDay = new GregorianCalendar();
		cExpiredDay.setTime(account.getDateExpiredCarte());
		int yearExpiredDay = cExpiredDay.get(Calendar.YEAR);
		System.out.println("****************in verifyDateExpiredCard, yearExpiredDay = " + yearExpiredDay);
		//Add one to month {0 - 11}
		int monthExpiredDay = cExpiredDay.get(Calendar.MONTH) + 1;
		System.out.println("****************in verifyDateExpiredCard, monthExpiredDay = " + monthExpiredDay);
		//int day = cExpiredDay.get(Calendar.DAY_OF_MONTH);
		System.out.println("yearExpiredDay==yearInput :" + (yearExpiredDay==yearInput));
		System.out.println("yearExpiredDay >= yearToday : " + (yearExpiredDay >= yearToday));
		if(yearExpiredDay == yearInput && yearExpiredDay >= yearToday){
			expired = false;
			System.out.println("monthExpiredDay == monthInput :" + (monthExpiredDay == monthInput));
			if(monthExpiredDay == monthInput){
				expired = false;
				System.out.println("yearExpiredDay == yearToday: " + (yearExpiredDay == yearToday));
				if(yearExpiredDay == yearToday){
					System.out.println("monthExpiredDay >= monthToday: " + (monthExpiredDay >= monthToday));
					if(monthExpiredDay >= monthToday){
						expired = false;
					}else{
						expired = true;
					}
				}
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
	
	public Boolean verifyName(Account account,String lastNameInput){		
		Customer customer = account.getCustomer();
		String lastNameOfAccount = customer.getLastName();
		if(lastNameOfAccount.equals(lastNameInput)){
			nameCorrect = true;
		}else{
			nameCorrect = false;
		}
		return nameCorrect;
	}
	
	public Boolean verifyAmount(Account account,BigDecimal amount){
		if((account.getBalance().compareTo(amount))==1){
			balanceEnough = true;
		}else{
			balanceEnough = false;
		}
		return balanceEnough;
		
	}
	
	public void debitAccount(BigDecimal amount,Account account){
		//accountBus.debitAccount(amount,account);
	}
	
	@GET
	@Produces("text/plain")
	@Path("/test")
	public List<Account> test(@PathParam("numberCard")String numberCard){
		List<Account> accounts = null;
		numberCard = "123456789";
		accounts = getAccountByNumberCard(numberCard);
		if(accounts.size()!=0){
			for(Account a:accounts){
				System.out.println(a.toString());
			}
		}else
		{System.out.println(accounts);}
			
		return accounts;
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
