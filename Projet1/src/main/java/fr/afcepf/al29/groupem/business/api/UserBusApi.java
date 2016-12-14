package fr.afcepf.al29.groupem.business.api;

import java.util.Date;

import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.User;

public interface UserBusApi {
	
	public boolean checkUserCredential(String login, String password);
	
	User createUser(User user);

	User createUser(Civilite civilite, String lastName, String firstName, String email, String phone,
			String passwordPlaintext, Date birthDate);
	
	
}
