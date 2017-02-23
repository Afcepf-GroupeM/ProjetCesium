package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component 
public class UserDaoImpl implements UserDaoApi {

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

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			user = entityManager.createQuery("SELECT usr FROM User usr WHERE usr.email = :useremail",User.class).setParameter("useremail", email).getSingleResult();
		} catch (Exception e) {} // Catches exception if email doesn't exists. Mandatory with getSingleResult. Return null
		return user;
	}
	
	@Override
	public List<User> getUsersByLastname(String name){
	    List<User> listUsers = new ArrayList<>();
	    listUsers = entityManager.createQuery("SELECT usr FROM User usr WHERE usr.lastName LIKE :lastname",User.class).setParameter("lastname", '%'+name+'%').getResultList();
        return listUsers;
	}

    @Override
    public List<User> getUsersByFirstname(String firstname) {
        List<User> listUsers = new ArrayList<>();
        listUsers = entityManager.createQuery("SELECT usr FROM User usr WHERE usr.firstName LIKE :firstname",User.class).setParameter("firstname", '%'+firstname+'%').getResultList();
        return listUsers;
    }

	@Override
	public List<User> getAllUsers() {
		List<User> listUsers = new ArrayList<>();
		listUsers = entityManager.createQuery("SELECT usr FROM User usr",User.class).getResultList();
		return listUsers;
	}
	
	

}
