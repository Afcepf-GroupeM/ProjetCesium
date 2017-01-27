package fr.afcepf.al29.groupem.business;

import java.math.BigDecimal;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Operation;

public interface OperationBusApi {

	Operation createOperation(BigDecimal amount, Account account, String label);
	
	Operation updateOperation(Operation operation);
	
}
