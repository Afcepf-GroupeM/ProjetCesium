package net.pfrancois.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestAddress {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private AddressDaoApi addressDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initAddressDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		addressDao = (AddressDaoApi) contextSpring.getBean(AddressDaoApi.class);
	}
	
	@Test
	public void addAddress(){
		User user = new User();
		user.setId(1);
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("010203040506");
		
		Address address = new Address();
		address.setName("Coffre");
		address.setNumber(13);
		address.setRoadType(RoadType.Rue);
		address.setRoadName(" du fric");
		address.setCity("PicsouVille");
		address.setZipcode("77777");
		address.setCountry("Dollars");
		address.setBilling(false);
		address.setValide(true);
		address.setUser(user);
		
		log.debug("Address id avant create: " + address.getId());
		
		address = addressDao.createAddress(address);
		log.debug("Address id apres create: " + address.getId());
		Assert.assertTrue(address.getId() != 0);
	}
}
