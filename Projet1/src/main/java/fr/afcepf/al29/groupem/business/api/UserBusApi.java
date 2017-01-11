package fr.afcepf.al29.groupem.business.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.User;

public interface UserBusApi {
	
	public boolean checkUserCredential(String login, String password);
	

	User createUser(Civilite civilite, String lastName, String firstName, String email, String phone,
			String passwordPlaintext, Date birthDate);
	
	User getUserByLogin(String login);
	public User getUserById(int userID);

	public User updateUser(int idUser, String lastName, Civilite civilite, String firstName, String email, String phone,
			Date formattedDate, String password1);


    List<User> searchUsers(String paramInput, String paramSearchType);
}
