package fr.afcepf.al29.groupem.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.User;

@ManagedBean
@RequestScoped
public class UserController {

	
	private User user;
	private String message;
	
	
	@Autowired
	private UserBusApi userBus; 
	
	
	//methode pour s'inscrire liée à la page formulaireInscription.xhtml mais qui ne marche pas ?
	public String inscription() {
		try {
			user = userBus.createUser(user);
			message = "votre compte a été créé";
			user = new User();
			
		} catch (Exception e) {
			message = "erreur lors de la création de votre compte";
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
