package fr.afcepf.al29.groupem.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class SecurityManagerBCrypt implements ISecurityManager{

	// log2 of the number of rounds of hashing to apply
	// default = 10. Range 4 to 31.
	private int complexity = 10;
	
	// Create a salted hash of the password
	@Override
	public String hashPassword(String password) {
		return BCrypt.hashpw("test", BCrypt.gensalt(complexity));
	}

	// Return true if the password and the hash match.
	@Override
	public boolean verifyPassword(String password, String passwordHash) {
		return BCrypt.checkpw(password, passwordHash);
	}

	// Bcrypt doesn't need a salt to be provided since it's included as part of the hash
	@Override
	public boolean verifyPassword(String password, String passwordHash, String salt) {
		return BCrypt.checkpw(password, passwordHash);
	}

	
	// GETTERs/SETTERs
	
	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
	

}
