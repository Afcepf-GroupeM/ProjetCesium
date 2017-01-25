package fr.afcepf.al29.groupem.business;

import fr.afcepf.al29.groupem.dao.AccountDaoApi;

public class AccountBusImpl implements AccountBusApi{
	private AccountDaoApi accoundDaoApi;
	@Override
	public Integer getIdByNumberCard(String numberCard) {		
		return accoundDaoApi.getIdByNumberCard(numberCard);
	}
	
}
