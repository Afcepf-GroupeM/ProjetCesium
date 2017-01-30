package fr.afcepf.al29.groupem.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.Account;

public class Test {
	public static void main(String[] args) {		
		 AccountRest rest = new AccountRest();
		 Account account = null;
		 String numberCard = "123456789";
		 System.out.println("*************ici");
//		 account = rest.getAccountByNumberCard(numberCard);
		 System.out.println(account.toString());
	}

}
