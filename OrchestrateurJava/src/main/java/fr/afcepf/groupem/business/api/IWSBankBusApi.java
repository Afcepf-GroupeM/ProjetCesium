package fr.afcepf.groupem.business.api;

import fr.afcepf.groupem.entities.BankRequest;
import fr.afcepf.groupem.entities.BankResponse;

public interface IWSBankBusApi {
	
	BankResponse checkPayement(BankRequest bankRequest);
	

}
