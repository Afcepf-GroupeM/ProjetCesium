package fr.afcepf.al29.groupem.business;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;

public interface AccountBusApi {
<<<<<<< HEAD
	public Account getAccountByNumberCard(String numberCard);
	
=======
	public List<Account> getAccountByNumberCard(String numberCard);
	public Customer getCustomerByAccount(Account account);
>>>>>>> branch 'master' of ssh://git@github.com/Afcepf-GroupeM/ProjetCesium.git
	
}
