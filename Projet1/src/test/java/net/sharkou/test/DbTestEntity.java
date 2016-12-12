package net.sharkou.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;


//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.User;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestEntity {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserDaoApi userDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initUserDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		userDao = (UserDaoApi) contextSpring.getBean(UserDaoApi.class);
		
	}
	
	
	@Test
	public void testSearchUserById(){
		
		// Date formatter to show birthDate in a nice way
		String datePattern = "dd MMMMM yyyy";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		
		// User id to search trough the DAO
		int idToSearch = 1;
		User user = userDao.getUserById(idToSearch);
		
		// Printing the result in console
		log.info("\n\n");
		log.info("   ----------   ");
		log.info("Result for testSearchUserById #" + idToSearch);
		log.info("User : " + user.getCivilite() + " " + user.getfirstName() + " " + user.getlastName());
		log.info("Email : " + user.getEmail());
		log.info("Phone # : " + user.getphone());
		log.info("Date of bitrth : " + dateFormatter.format(user.getBirthDate()));
		log.info("Password Hash : " + user.getpasswordHash());
		log.info("   ----------   ");
		log.info("\n\n");
		
		// Checks if the name retrieved is:
		String expectedLastName = "Laporte";
		Assert.assertTrue(user.getlastName().equals(expectedLastName));
	}
	
	
	@Test
	public void testAddNewUser(){
		//Creating User to add to DB
		User user = new User();
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("0102030405");
		
		log.info("User id avant create: " + user.getId());
		
		user = userDao.createUser(user);
		log.info("\n\n");
		log.info("   ----------   ");
		log.info("User id apres create: " + user.getId());
		log.info("   ----------   ");
		log.info("\n\n");
		Assert.assertTrue(user.getId() != 0);	
		
	}
	
//	@Test
	public void testDeleteUser(){
		// Specify an Id existing in the DB is mandatory to pass the test /!\ 
		int idToDelete = 5;
		User user = userDao.getUserById(idToDelete);
		boolean result = userDao.deleteUser(user);
		Assert.assertTrue(result);
	}
	
	
//	@Test
	public void testUpdateUser(){
		
		// Id of the user we want to update
		int idToUpdate = 6;
		User user = userDao.getUserById(idToUpdate);
		user.setfirstName("NewFirstName");
		user.setfirstName("NewName");
		
		// Updating user in DB
		user = userDao.updateUser(user);
		
		// Reading the User in DB
		user = userDao.getUserById(idToUpdate);
		
		// Checks if the update is succesful
		Assert.assertTrue(user.getlastName().equals("NewName"));
		
		
	}
	

	

}
