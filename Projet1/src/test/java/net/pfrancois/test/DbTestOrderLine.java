
package net.pfrancois.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.AddressDaoApi;
import fr.afcepf.al29.groupem.dao.api.CarrierDaoApi;
import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.dao.api.OrderDaoApi;
import fr.afcepf.al29.groupem.dao.api.OrderLineDaoApi;
import fr.afcepf.al29.groupem.dao.api.UserDaoApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Carrier;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.ComplementAddress;
import fr.afcepf.al29.groupem.entities.Coupon;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
import fr.afcepf.al29.groupem.entities.RoadType;
import fr.afcepf.al29.groupem.entities.TypePayment;
import fr.afcepf.al29.groupem.entities.User;
import junit.framework.Assert;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestOrderLine {
	
	@Autowired
	private UserDaoApi userDao;
	@Autowired
	private ItemDaoApi itemDao;
	@Autowired
	private CategoryDaoApi categoryDao;
	@Autowired
	private OrderDaoApi orderDao;
	@Autowired
	private OrderLineDaoApi orderLineDao;
	@Autowired
	private AddressDaoApi addressDao;
	@Autowired
	private CarrierDaoApi carrierDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initOrderLineDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		userDao = (UserDaoApi) contextSpring.getBean(UserDaoApi.class);
		orderDao = (OrderDaoApi) contextSpring.getBean(OrderDaoApi.class);
		orderLineDao = (OrderLineDaoApi) contextSpring.getBean(OrderLineDaoApi.class);
		itemDao = (ItemDaoApi) contextSpring.getBean(ItemDaoApi.class);
		categoryDao = (CategoryDaoApi) contextSpring.getBean(CategoryDaoApi.class);
		addressDao = (AddressDaoApi) contextSpring.getBean(AddressDaoApi.class);
		carrierDao = (CarrierDaoApi) contextSpring.getBean(CarrierDaoApi.class);
	}
	
	@Test	//OK
	public void createOrderLine(){
		User user = new User();
		user.setlastName("Lol");
		user.setfirstName("PrenomTest");
		user.setCivilite(Civilite.Mme);
		user.setEmail("lole@lol.fr");
		user.setBirthDate(new Date());
		user.setpasswordHash("LolHash");
		user.setphone("0102030405");
		user = userDao.createUser(user);
		
		Address address = new Address();
		address.setName("Coffre");
		address.setNumber(13);
		address.setComplement(ComplementAddress.bis);
		address.setRoadType(RoadType.Rue);
		address.setRoadName(" du fric");
		address.setCity("PicsouVille");
		address.setZipcode("77777");
		address.setCountry("Dollars");
		address.setBilling(true);
		address.setValid(true);
		address.setUser(user);
		address = addressDao.createAddress(address);
		
		MetaCategory mc = new MetaCategory();
		mc.setName("Trésor");
		mc = categoryDao.createMetaCategory(mc);
		
		Category c = new Category();
		c.setName("Tech");
		c.setMetaCategory(mc);
		c = categoryDao.createCategory(c);
		
		Carrier carrier = new Carrier();
		carrier.setName("fedex");
		carrier.setTrackingUrl("trackidfghjk/tracking/");
		carrierDao.createCarrier(carrier);
		
		Order order = new Order();
		order.setCreationDate(new Date());
		order.setUser(user);
		order.setAmount(59.94f);
		order.setTypePayment(TypePayment.Visa);
		order.setState(OrderState.EnAttenteDePaiement);
		order.setTrackingNumber("098765432");
		order.setAdresseLivraison(address);
		order.setAdresseFacturation(address);
		order.setCarrier(carrier);
		order = orderDao.createOrder(order);
		
		Item item = new Item();
		item.setImagePath("pathImage");
		item.setReference("REF");
		item.setName("article en or");
		item.setDescription("Un super produit!");
		item.setPrice(19.98f);
		item.setStock(10);
		item.setCategory(c);
		item = itemDao.createItem(item);
		
		OrderLine ol1 = new OrderLine();
		ol1.setQuantity(2);
		ol1.setUnitPrice(19.98f);
		ol1.setOrder(order);
		ol1.setItem(item);
		
		OrderLine ol2 = new OrderLine();
		ol2.setQuantity(1);
		ol2.setUnitPrice(19.98f);
		ol2.setOrder(order);
		ol2.setItem(item);
		
		System.out.println("@@@@@@@@ Id orderLine avant: " + ol1.getId() + " et " + ol2.getId() + " @@@@@@@");
		
		ol1 = orderLineDao.createOrderLine(ol1);
		ol2 = orderLineDao.createOrderLine(ol2);
		
		
		System.out.println("@@@@@@@@ Id orderLine après: " + ol1.getId() + " et " + ol2.getId() + " @@@@@@@");
		Assert.assertTrue((ol1.getId() != 0) && (ol2.getId() != 0));
	}
	
	@Test	//OK
	public void getOrderLinesByOrderId(){
		List<OrderLine> orderLines = orderLineDao.getOrderLinesByOrderId(1);
		Assert.assertTrue(orderLines.size() > 0);
		
		for (OrderLine orderLine : orderLines){
			System.out.println("@@@@@@@@@ " + orderLine.toString() + " @@@@@@@@@@@@@");
		}
	}
	
	@Test	//OK
	public void getOrderLineById(){
		OrderLine orderLine = orderLineDao.getOrderLineById(2);
		Assert.assertTrue(orderLine.getId() == 2);
	}
	
	@Test	//OK
	public void updateOrderLine(){
		OrderLine ol = orderLineDao.getOrderLineById(1);

		System.out.println("@@@@@@@@@@@ orderline quantity avant update: " + ol.getQuantity() + " @@@@@@@@@@@");
		
		ol.setQuantity(5);
		orderLineDao.updateOrderLine(ol);
		
		ol = orderLineDao.getOrderLineById(1);
		
		System.out.println("@@@@@@@@@@@ orderLine quantity apres update: " + ol.getQuantity() + " @@@@@@@@@@@");
		Assert.assertTrue(ol.getQuantity() == 5);
	}
	
	@Test	//OK
	public void deleteOrderLineById(){
		boolean deletedControl = orderLineDao.deleteOrderLineById(2);
		Assert.assertTrue(deletedControl);
	}
}
