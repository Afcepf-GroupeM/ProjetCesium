package fr.afcepf.al29.groupem.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import fr.afcepf.al29.groupem.entities.Operation;


@Transactional
@Component
public class OperationDaoImpl implements OperationDaoApi {

	@PersistenceContext(unitName="WebServiceBanque")
	private EntityManager entityManager;
	

	@Override
	public Operation createOperation(Operation operation) {
		entityManager.persist(operation);
		return operation;
	}


	@Override
	public Operation updateOperation(Operation operation) {
		entityManager.merge(operation);
		return operation;
	}

}
