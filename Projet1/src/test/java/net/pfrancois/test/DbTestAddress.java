package net.pfrancois.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.User;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestAddress {
	
	@Autowired
	private AddressDaoApi addressDao;
	@Autowired
	private UserDaoApi userDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initAddressDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		addressDao = (AddressDaoApi) contextSpring.getBean(AddressDaoApi.class);
		userDao = (UserDaoApi) contextSpring.getBean(UserDaoApi.class);
	}
	
	@Test	//OK
	public void addAddress(){
		User user = new User();
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("0102030405");
		userDao.createUser(user);
		
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
		
		System.out.println("Address id avant create: " + address.getId());
		
		address = addressDao.createAddress(address);
		System.out.println("Address id apres create: " + address.getId());
		Assert.assertTrue(address.getId() != 0);
	}
	
	@Test	//OK
	public void pickUpAddresses(){
		List<Address> addresses = addressDao.getAddressesByUserId(1);
		Assert.assertTrue(addresses.size() > 0);
		
		for (Address address : addresses){
			System.out.println("@@@@@@@@@ " + address.toString() + " @@@@@@@@@@@@@");
		}
	}
	
	@Test	//OK
	public void getAddress(){
		Address address = addressDao.getAddressById(1);
		Assert.assertTrue(address.getId() == 1);
	}
	
	@Test	//OK
	public void majAddress(){
		Address address = addressDao.getAddressById(1);
		
		System.out.println("@@@@@@@@@@@ Address country avant update: " + address.getCountry() + " @@@@@@@@@@@");
		
		address.setCountry("Euros");
		addressDao.updateAddress(address);
		
		address = addressDao.getAddressById(1);
		
		System.out.println("@@@@@@@@@@@ Address country apres update: " + address.getCountry() + " @@@@@@@@@@@");
		Assert.assertTrue(address.getCountry().equals("Euros"));
	}
	
	@Test	//OK
	public void oldAddress(){
		Address address = addressDao.getAddressById(1);
		System.out.println("@@@@@@@@@@@@@@ Validité addresse avant: " + address.isValide() + " @@@@@@@@@@@@");
		
		address = addressDao.disableAddress(address);
		
		address = addressDao.getAddressById(1);
		System.out.println("@@@@@@@@@@@@@@ Validité addresse après: " + address.isValide() + " @@@@@@@@@@@@");
		
		Assert.assertFalse(address.isValide());
	}
}
