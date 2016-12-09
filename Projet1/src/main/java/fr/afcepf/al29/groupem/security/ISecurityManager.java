package fr.afcepf.al29.groupem.security;

public interface ISecurityManager {
	
	String hashPassword(String password);
	boolean verifyPassword(String password,String passwordHash, String salt);
	boolean verifyPassword(String password,String passwordHash);

}
