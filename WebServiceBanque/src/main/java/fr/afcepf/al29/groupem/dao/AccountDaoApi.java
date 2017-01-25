package fr.afcepf.al29.groupem.dao;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Account;

public interface AccountDaoApi {
	public Account getAccountByNumberCard(String numberCard);
}
