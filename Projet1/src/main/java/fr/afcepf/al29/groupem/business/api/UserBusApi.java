package fr.afcepf.al29.groupem.business.api;

import fr.afcepf.al29.groupem.entities.User;

public interface UserBusApi {
	
	public boolean checkUserCredential(String login, String password);
	
	User createUser(User user);
	
	
}
