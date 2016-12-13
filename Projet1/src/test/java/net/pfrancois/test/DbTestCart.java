package net.pfrancois.test;

import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.CartDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.User;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestCart {
	
	@Autowired
	private CartDaoApi cartDao;
	@Autowired
	private UserDaoApi userDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initAddressDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		cartDao = (CartDaoApi) contextSpring.getBean(CartDaoApi.class);
		userDao = (UserDaoApi) contextSpring.getBean(UserDaoApi.class);
	}
	
	@Test	//OK
	public void createCart(){
		User user = new User();
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("0102030405");
		userDao.createUser(user);
		
		Cart cart = new Cart();
		cart.setCreationDate(new Date());
		cart.setUser(user);
		
		System.out.println("@@@@@@@@ Cart id avant create: " + cart.getId() + " @@@@@@@@");
		
		cart = cartDao.createCart(cart);
		System.out.println("@@@@@@@@ Cart id après create: " + cart.getId() + " @@@@@@@@");
		Assert.assertTrue(cart.getId() != 0);
	}
	
	@Test	//OK
	public void getCartById(){
		Cart cart = cartDao.getCartById(1);
		Assert.assertTrue(cart.getId() == 1);
	}
	
	@Test	//OK
	public void getLastCartUser(){
		User user = userDao.getUserById(1);
		
		Cart newcart = new Cart();
		newcart.setCreationDate(new Date());
		newcart.setUser(user);
		
		cartDao.createCart(newcart);
		
		Cart cart = cartDao.getCartByUserId(1);
		System.out.println("@@@@@@@@@ Dernier panier de l'utilisateur: " + cart.toString() + " @@@@@@@@@");
		Assert.assertTrue(cart.getId() == 2);
	}
	
	@Test	//OK
	public void destroyCart(){
		boolean cartDeleted = cartDao.destroyCartById(1);
		Assert.assertTrue(cartDeleted);
	}
}

