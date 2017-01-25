package fr.afcepf.al29.groupem.business;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Account;

public interface AccountBusApi {
	public Account getAccountByNumberCard(String numberCard);
}
