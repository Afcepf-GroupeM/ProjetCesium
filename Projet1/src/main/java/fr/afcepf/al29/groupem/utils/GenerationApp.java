package fr.afcepf.al29.groupem.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component 
public class GenerationApp {
	
	
	
	public static DataGeneration dataGen = new DataGeneration();

	public static void main(String[] args) {
		
		dataGen.generateUsers(10);

	}

}
