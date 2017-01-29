package fr.afcepf.al29.groupem.business;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.OperationDaoApi;
import fr.afcepf.al29.groupem.entities.Account;
import fr.afcepf.al29.groupem.entities.Operation;

@Transactional
@Component
public class OperationBusImpl implements OperationBusApi {

	@Autowired
	private OperationDaoApi operationDao;
	
	@Override
	public Operation createOperation (BigDecimal amount, Account account, String label) {
		Operation operation = new Operation();
		operation.setAmount(amount);
		operation.setAccount(account);
		operation.setLabel(label);
		Date date = new Date();
		operation.setDateOp(date);
		
		Operation operationCreated = operationDao.createOperation(operation);
		
		return operationCreated;
	}

	@Override
	public Operation updateOperation(Operation operation) {
		
		
		Operation operationUpdated = operationDao.updateOperation(operation);
		return operationUpdated;
	}

	
	
	
	
	
	
}
