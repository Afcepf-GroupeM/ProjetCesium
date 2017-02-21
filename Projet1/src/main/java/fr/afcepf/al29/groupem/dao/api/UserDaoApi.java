package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.User;

public interface UserDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	User createUser(User user);
	User getUserById(int userId);
	User getUserByEmail(String email);
	User updateUser(User user);
	boolean deleteUser(User user);
    List<User> getUsersByFirstname(String paramName);
    List<User> getUsersByLastname(String paramName);
	List<User> getAllUsers();

}
