package fr.afcepf.al29.groupem.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.entities.Account;

public class Test {
	public static void main(String[] args) {		
		 AccountRest rest = new AccountRest();
		 List<Account> accounts = null;
		 String numberCard = "123456789";
		 System.out.println("*************ici");
		 accounts = rest.getAccountByNumberCard(numberCard);
		 //System.out.println(accounts.toString());
	}

}
