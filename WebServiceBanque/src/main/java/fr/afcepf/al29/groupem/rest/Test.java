package fr.afcepf.al29.groupem.rest;

import fr.afcepf.al29.groupem.entities.Account;

public class Test {
	public static void main(String[] args) {
		 AccountRest rest = new AccountRest();
		 Account account = new Account();
		 String numberCard = "123456789";
		 account = rest.getAccountByNumberCard(numberCard);
		 System.out.println(account.toString());
	}

}
