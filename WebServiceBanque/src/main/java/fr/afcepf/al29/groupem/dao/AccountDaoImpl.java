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
	public List<Account> getAccountByNumberCard(String numberCard) {
		System.out.println("***********dans Dao " + numberCard +"**************** 3333");
		List<Account> accounts = null; 
	    try {
	    	accounts = entityManager.createQuery("SELECT a FROM Account a WHERE a.numberCard = :numberCard", Account.class).setParameter("numberCard", numberCard).getResultList(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return accounts;
	}

	

	
	//methode pour debiter le compte, il faut: creer un objet "compte", creer un objet "montant"
	@Override
	public Account debitAccount(BigDecimal Amount, Account account) {
		Account account1 = new Account();
		Operation operation1 = new Operation();
		BigDecimal balance = account1.getBalance().subtract(Amount);
		
		entityManager.createQuery("UPDATE Account set balance = :balance", Account.class).setParameter("balance", balance).executeUpdate();
		return account1;
	}

}
