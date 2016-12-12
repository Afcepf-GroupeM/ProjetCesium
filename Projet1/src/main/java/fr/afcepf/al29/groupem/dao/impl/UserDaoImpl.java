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
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User getUserById(int userId) {
		User usr = entityManager.find(User.class, userId);
		return usr;
	}

	@Override
	public User updateUser(User user) {
		entityManager.merge(user);
		return user;
	}

	@Override
	public boolean deleteUser(User user) {
		
		// GetReference used to attach entity User
		User userToRemove = entityManager.getReference(User.class, user.getId());
		entityManager.remove(userToRemove);
		
		return (getUserById(user.getId()) == null);
	}

}
