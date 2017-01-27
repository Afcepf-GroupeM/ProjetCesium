package fr.afcepf.al29.groupem.dao;

import java.math.BigDecimal;

import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Operation;

public interface OperationDaoApi {

	Operation createOperation(Operation operation);
	
	Operation updateOperation(Operation operation);
}
