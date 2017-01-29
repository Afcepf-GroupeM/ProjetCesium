package fr.afcepf.al29.groupem.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.AccountDaoApi;
import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Customer;

@Transactional
@Component
public class AccountBusImpl implements AccountBusApi{
	@Autowired
	private AccountDaoApi accountDao;
	
	@Override
	public List<Account> getAccountByNumberCard(String numberCard) {	
		System.out.println("*******dans bus***********2222");
		return accountDao.getAccountByNumberCard(numberCard);
	}
	
	
	
}
