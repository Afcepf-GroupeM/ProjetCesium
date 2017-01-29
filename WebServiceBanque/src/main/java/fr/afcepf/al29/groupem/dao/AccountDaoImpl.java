package fr.afcepf.al29.groupem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;

@Transactional
@Component
public class AccountDaoImpl implements AccountDaoApi{
	
	@PersistenceContext(unitName="WebServiceBanque")
	private EntityManager entityManager;
	
	@Override
	public List<Account> getAccountByNumberCard(String numberCard) {
		System.out.println("***********dans Dao " + numberCard +"**************** 3333");
		System.out.println(" *******************4444***********numberCard = "+numberCard);
		List<Account> accounts = null; 
	    try {
	    	accounts = entityManager.createQuery("SELECT a FROM Account a WHERE a.numberCard = :numberCard", Account.class).setParameter("numberCard", numberCard).getResultList(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return accounts;
	}

	@Override
	public Account debitAccount(Account account) {
		account = entityManager.merge(account);
		return account;
	}

	
	

}
