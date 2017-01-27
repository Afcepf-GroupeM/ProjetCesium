package fr.afcepf.al29.groupem.business;

import java.math.BigDecimal;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;

public interface AccountBusApi {
	public List<Account> getAccountByNumberCard(String numberCard);
	public Customer getCustomerByAccount(Account account);
	
	//methode pour débiter le compte qui a besoin en parametre du compte et du montant
	public Account debitAccount(BigDecimal Amount, Account account);
	
}
