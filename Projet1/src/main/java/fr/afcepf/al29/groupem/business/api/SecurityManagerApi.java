package fr.afcepf.al29.groupem.business.api;

public interface SecurityManagerApi {
	
	String hashPassword(String password);
	boolean verifyPassword(String password,String passwordHash, String salt);
	boolean verifyPassword(String password,String passwordHash);

}
