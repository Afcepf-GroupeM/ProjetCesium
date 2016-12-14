package net.pfrancois.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.CartDaoApi;
import fr.afcepf.al29.groupem.dao.api.CartLineDaoApi;
import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Cart;
import fr.afcepf.al29.groupem.entities.CartLine;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;
import fr.afcepf.al29.groupem.entities.User;
import junit.framework.Assert;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestCartLine {
	
	@Autowired
	private UserDaoApi userDao;
	@Autowired
	private ItemDaoApi itemDao;
	@Autowired
	private CategoryDaoApi categoryDao;
	@Autowired
	private CartDaoApi cartDao;
	@Autowired
	private CartLineDaoApi cartLineDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initCartLineDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		userDao = (UserDaoApi) contextSpring.getBean(UserDaoApi.class);
		cartDao = (CartDaoApi) contextSpring.getBean(CartDaoApi.class);
		cartLineDao = (CartLineDaoApi) contextSpring.getBean(CartLineDaoApi.class);
		itemDao = (ItemDaoApi) contextSpring.getBean(ItemDaoApi.class);
		categoryDao = (CategoryDaoApi) contextSpring.getBean(CategoryDaoApi.class);
	}
	
	@Test	//OK
	public void createCartLine(){
		User user = new User();
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("0102030405");
		userDao.createUser(user);
		
		MetaCategory mc = new MetaCategory();
		mc.setName("Trésor");
		categoryDao.createMetaCategory(mc);
		
		Category c = new Category();
		c.setName("Tech");
		c.setMetaCategory(mc);
		categoryDao.createCategory(c);
		
		Cart cart = new Cart();
		cart.setCreationDate(new Date());
		cart.setUser(user);
		cart = cartDao.createCart(cart);
		
		Item item = new Item();
		item.setImagePath("pathImage");
		item.setReference("REF");
		item.setName("article en or");
		item.setDescription("Un super produit!");
		item.setPrice(19.98f);
		item.setStock(10);
		item.setCategory(c);
		itemDao.createItem(item);
		
		CartLine cl1 = new CartLine();
		cl1.setQuantity(2);
		cl1.setUnitPrice(19.98f);
		cl1.setCart(cart);
		cl1.setItem(item);
		
		CartLine cl2 = new CartLine();
		cl2.setQuantity(1);
		cl2.setUnitPrice(19.98f);
		cl2.setCart(cart);
		cl2.setItem(item);
		
		System.out.println("@@@@@@@@ Id cartLine avant: " + cl1.getId() + " et " + cl2.getId() + " @@@@@@@");
		
		cl1 = cartLineDao.createCartLine(cl1);
		cl2 = cartLineDao.createCartLine(cl2);
		
		
		System.out.println("@@@@@@@@ Id cartLine après: " + cl1.getId() + " et " + cl2.getId() + " @@@@@@@");
		Assert.assertTrue((cl1.getId() != 0) && (cl2.getId() != 0));
	}
	
	@Test	//OK
	public void getCartLinesByCartId(){
		List<CartLine> cartLines = cartLineDao.getCartLinesByCartId(1);
		Assert.assertTrue(cartLines.size() > 0);
		
		for (CartLine cartLine : cartLines){
			System.out.println("@@@@@@@@@ " + cartLine.toString() + " @@@@@@@@@@@@@");
		}
	}
	
	@Test	//OK
	public void getCartLineById(){
		CartLine cartLine = cartLineDao.getCartLineById(2);
		Assert.assertTrue(cartLine.getId() == 2);
	}
	
	@Test	//OK
	public void updateCartLine(){
		CartLine cl = cartLineDao.getCartLineById(1);

		System.out.println("@@@@@@@@@@@ cartline quantity avant update: " + cl.getQuantity() + " @@@@@@@@@@@");
		
		cl.setQuantity(5);
		cartLineDao.updateCartLine(cl);
		
		cl = cartLineDao.getCartLineById(1);
		
		System.out.println("@@@@@@@@@@@ cartLine quantity apres update: " + cl.getQuantity() + " @@@@@@@@@@@");
		Assert.assertTrue(cl.getQuantity() == 5);
	}
	
	@Test	//OK
	public void deleteCartLineById(){
		boolean deletedControl = cartLineDao.deleteCartLineById(2);
		Assert.assertTrue(deletedControl);
	}
}
