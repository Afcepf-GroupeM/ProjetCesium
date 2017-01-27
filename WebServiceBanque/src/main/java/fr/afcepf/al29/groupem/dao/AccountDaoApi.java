package fr.afcepf.al29.groupem.dao;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;

public interface AccountDaoApi {
	public List<Account> getAccountByNumberCard(String numberCard);
	
}
