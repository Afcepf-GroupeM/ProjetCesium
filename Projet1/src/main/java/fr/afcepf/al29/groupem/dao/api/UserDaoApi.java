package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.User;

public interface UserDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	User createUser(User user);
	User getUserById(int userId);
	User updateUser(User user);
	boolean deleteUser(User user);

}
