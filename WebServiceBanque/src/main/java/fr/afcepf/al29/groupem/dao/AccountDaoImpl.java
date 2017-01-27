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

	@Override
	public Customer getCustomerByAccount(Account account) {
		Customer customer = new Customer();
		//customer = entityManager.createQuery("SELECT c FROM Customer c WHERE Account.customerid = :customerid")
		return customer;
	}

}
