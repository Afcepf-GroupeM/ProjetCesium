package fr.afcepf.al29.groupem.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.AccountDaoApi;
import fr.afcepf.al29.groupem.entities.Account;

@Transactional
@Component
public class AccountBusImpl implements AccountBusApi{
	@Autowired
	private AccountDaoApi accoundDao;
	
	@Override
	public Account getAccountByNumberCard(String numberCard) {		
		return accoundDao.getAccountByNumberCard(numberCard);
	}
	
}
