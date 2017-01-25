package fr.afcepf.al29.groupem.rest;

import java.math.BigDecimal;
import java.util.Date;

import fr.afcepf.al29.groupem.business.AccountBusApi;
import fr.afcepf.al29.groupem.business.AccountBusImpl;

public class AccountRest {
	AccountBusApi accountBusApi;
	public void verifyAccount(String numberCard,Date dateExpiredCarte,String crytogram,String lastName,BigDecimal balance){
		accountBusApi.getIdByNumberCard(numberCard);
	}

}
