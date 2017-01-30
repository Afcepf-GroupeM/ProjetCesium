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
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/receptionInfoReturnResponse")
	public ResponseBank receptionInfoReturnResponse(RequestData requestData){
				System.out.println("************************requestData:" + requestData.toString());
				//get the account by numberCard
				String numberCard = requestData.getNumberCard();
				String lastName = requestData.getLastName();
				Integer expiredYear = requestData.getYearExpiredCard();
				Integer expiredMonth = requestData.getMonthExpiredCard();
				BigDecimal amount = requestData.getAmount();
				String nameCompany = requestData.getNameCompany();					
				
				accounts = getAccountByNumberCard(requestData.getNumberCard());				
				//1.verify if the numberCard existe in the BDD		
				if(accounts.size()==0){
					numberCardExiste = false;	
					System.out.println("1. number Card n'existe pas.");
					setResponseBankNegative(statusResponse,referenceNumberResponse);
				}else{					
					numberCardExiste = true;
					//take the object account
					account = accounts.get(0);	
					System.out.println("********************account:" + account.toString());
					//2.verify the DateExpired is still valide						
					isExpired = verifyDateExpiredCard(expiredYear,expiredMonth,account);					
					if(isExpired == false){
						//3.verify the Crytogram is correct
						verifyCryptogram(requestData.getCryptogram(),account.getCryptogram());						
						if(cryptogramCorrect == true ){				
							//4.verify the Name is correct
							verifyName(account, lastName);							
							if(nameCorrect == true){
								//5.verify the customer get enough money to pay the amount
								verifyAmount(account,amount);								
								if(balanceEnough == true){
									//construire la réponse
									statusResponse = "OK";
									//créer un opération de prélèvement
									operation = opeBus.createOperation(amount, account,nameCompany);
									//response positive: OK
									referenceNumberResponse = operation.getId();
									responseBank.setStatus(statusResponse);
									responseBank.setReferenceNumber(referenceNumberResponse);							
									//Debit balance of account
									account = debitAccount(amount,account);									
								}else{
									System.out.println("5. customer don't have enough money to pay.");
									setResponseBankNegative(statusResponse,referenceNumberResponse);
									  }
							}else{
								System.out.println("4. family name is not correct. ");
								setResponseBankNegative(statusResponse,referenceNumberResponse);
								  }
						}else{
							System.out.println("3. cryptogram n'est pas correct.");
							setResponseBankNegative(statusResponse,referenceNumberResponse);
							  }
						
					}else{
						System.out.println("2. card is expired.");
						setResponseBankNegative(statusResponse,referenceNumberResponse);
						  }
				}
				System.out.println("********************************responseBank:" + responseBank.toString());
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
		accounts = accountBus.getAccountByNumberCard(numberCard);
		return accounts;
	}
	
	public boolean verifyDateExpiredCard(Integer yearInput, Integer monthInput, Account account){
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
		if(yearExpiredDay == yearInput && yearExpiredDay >= yearToday){
			expired = false;			
			if(monthExpiredDay == monthInput){
				expired = false;				
				if(yearExpiredDay == yearToday){					
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
	
	public boolean verifyCryptogram(String cryptogramInput,String cryptogramAccount){
		
		if(cryptogramInput.equals(cryptogramAccount)){
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
		int i = account.getBalance().compareTo(amount);
		if((i==1 || i==0)){
			balanceEnough = true;
		}else{
			balanceEnough = false;
		}
		return balanceEnough;
		
	}
	
	public Account debitAccount(BigDecimal amount,Account account){
		return accountBus.debitAccount(amount,account);
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
