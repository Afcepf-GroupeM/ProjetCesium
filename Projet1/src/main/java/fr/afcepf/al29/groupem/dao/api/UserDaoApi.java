package fr.afcepf.al29.groupem.dao.api;

import fr.afcepf.al29.groupem.entities.User;

public interface UserDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	boolean createAddress(User user);
	User getUserById(int userId);
	User updateUserById(int userId);
	boolean deleteUserById(int userId);

}
