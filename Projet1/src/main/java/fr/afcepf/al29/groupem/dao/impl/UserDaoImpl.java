package fr.afcepf.al29.groupem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component 
public class UserDaoImpl implements UserDaoApi {
	
	Logger log = Logger.getLogger(this.getClass());

	@PersistenceContext(unitName="Projet1") 
	private EntityManager entityManager;
	
	
	@Override
	public boolean createAddress(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(int userId) {
		log.debug("Dans: UserDaoImpl - getUserById");
		User usr = entityManager.find(User.class, userId);
		return usr;
	}

	@Override
	public User updateUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserById(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
