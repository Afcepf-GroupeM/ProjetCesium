package net.sharkou.test;

import java.text.SimpleDateFormat;

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
		log.debug("\n\n");
		log.debug("   ----------   ");
		log.debug("Result for testSearchUserById #" + idToSearch);
		log.debug("User : " + user.getCivilite() + " " + user.getfirstName() + " " + user.getlastName());
		log.debug("Email : " + user.getEmail());
		log.debug("Phone # : " + user.getphone());
		log.debug("Date of bitrth : " + dateFormatter.format(user.getBirthDate()));
		log.debug("Password Hash : " + user.getpasswordHash());
		log.debug("   ----------   ");
		log.debug("\n\n");
		
		// Checks if the name retrieved is:
		String expectedLastName = "Laporte";
		Assert.assertTrue(user.getlastName().equals(expectedLastName));
	}
	
	

	

}
