package fr.afcepf.al29.groupem.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.SecurityManagerApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;

@Transactional
@Component
public class UserBusImpl implements UserBusApi{
	
	@Autowired
	private UserDaoApi userDao;
	
	@Autowired
	private AddressDaoApi addressDao;
	
	@Autowired
	private SecurityManagerApi secMan;
	
	
	
	public boolean checkUserCredential(String login, String password){
		boolean result = false;
		User userToCheck = null;
			userToCheck = userDao.getUserByEmail(login);
			if(userToCheck != null){
				result = secMan.verifyPassword(password, userToCheck.getpasswordHash());
			}
		
		return result;
	}


	//methode pour s'inscrire
	@Override
	public User createUser(Civilite civilite, String lastName, String firstName, String email, String phone, String passwordPlaintext, Date birthDate) {
		User user = new User();
		user.setlastName(lastName);
		user.setfirstName(firstName);
		user.setCivilite(civilite);
		user.setBirthDate(birthDate);
		user.setEmail(email);
		user.setphone(phone);
		user.setpasswordHash(secMan.hashPassword(passwordPlaintext));
		
		User userCreated = userDao.createUser(user);
		return userCreated;
	}

	@Override
	public User getUserById(int userId){
		return userDao.getUserById(userId);
	}

	@Override
	public User getUserByLogin(String login) {
		User user = userDao.getUserByEmail(login);
		return user;
	}
	
	@Override
	public List<User> getAllUsers(){
		List<User> listAllUsers = userDao.getAllUsers();
		return listAllUsers;
	}

	@Override
	public User updateUser(int idUser, String lastName, Civilite civilite, String firstName, String email, String phone,
			Date formattedDate, String passwordPlaintext) {
		User userConnect = new User();
		userConnect.setId(idUser);
		userConnect.setlastName(lastName);
		userConnect.setfirstName(firstName);
		userConnect.setCivilite(civilite);
		userConnect.setBirthDate(formattedDate);
		userConnect.setEmail(email);
		userConnect.setphone(phone);
		userConnect.setpasswordHash(secMan.hashPassword(passwordPlaintext));
		
		User usermodified =userDao.updateUser(userConnect);
		System.out.println("**************dans userbus*******after update*********"+usermodified.toString());
		return usermodified;
	}	
	
	@Override
	public List<User> searchUsers(String input, String searchType){
	    List<User> listUsers = new ArrayList<>();	    
	    switch (searchType) {
        case "1": //search By ID
            User user = userDao.getUserById(Integer.valueOf(input));
            if(user != null){
                listUsers.add(user);
            }
            break;
        case "2": //search By email
            User user2 = userDao.getUserByEmail(input);
            if(user2 != null){
                listUsers.add(user2);
            }
            break;
        case "3": //search By lastName
            listUsers = userDao.getUsersByLastname(input);
            break;
        case "4": //search By firtsName
            listUsers = userDao.getUsersByFirstname(input);
            break;
        default:
            break;
        }
	    return listUsers;
	}
	
	@Override
	public List<User> generateUsers(List<User> listUsers){
		for (User user : listUsers) {
			user = userDao.createUser(user);
			Address address = new Address();
			address.setBilling(true);
			address.setCity("VilleParDefaut");
			address.setCountry("France");
			address.setName("NomParDefaut");
			address.setNumber(1);
			address.setRoadName("par Defaut");
			address.setRoadType(RoadType.Rue);
			address.setUser(user);
			address.setValid(true);
			address.setZipcode("99999");
			addressDao.createAddress(address);
		}
		return listUsers;
	}


	
	

}
