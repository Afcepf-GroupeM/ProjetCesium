package fr.afcepf.al29.groupem.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;
import fr.afcepf.al29.groupem.entities.Operation;

@Transactional
@Component
public class AccountDaoImpl implements AccountDaoApi{
	
	@PersistenceContext(unitName="WebServiceBanque")
	private EntityManager entityManager;
	
	@Override
	public Account getAccountByNumberCard(String numberCard) {
		System.out.println("***********dans Dao**************** 3333");
		Account account = null; 
	    try {
	    	account = entityManager.createQuery("SELECT a FROM Account a WHERE a.numberCard = :numberCard", Account.class).setParameter("numberCard", numberCard).getSingleResult(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return account;
	}

	

	
	//methode pour debiter le compte, il faut creer un objet "compte", creer un objet "montant"
	@Override
	public Account debitAccount(BigDecimal amount, Account account) {
		
		
		account.setBalance(account.getBalance().subtract(amount));
		
		entityManager.merge(account);
		return account;
	}

}
