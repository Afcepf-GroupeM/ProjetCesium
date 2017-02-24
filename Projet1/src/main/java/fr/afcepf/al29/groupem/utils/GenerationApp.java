package fr.afcepf.al29.groupem.utils;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.controller.DataGeneration;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component 
public class GenerationApp {
	
	
	
	public static DataGeneration dataGen = new DataGeneration();

	public static void main(String[] args) {
		
		List<User> listUser = dataGen.generateUsers(10);
		for (User user : listUser) {
			System.out.println(user.toString());
		}

	}

}
