package net.pfrancois.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.CarrierDaoApi;
import fr.afcepf.al29.groupem.entities.Carrier;

@ContextConfiguration(locations={"/springConf.xml"})
public class DbTestCarrier {

	@Autowired
	private CarrierDaoApi carrierDao;
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initCarrierDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		carrierDao = (CarrierDaoApi) contextSpring.getBean(CarrierDaoApi.class);
	}
	
	@Test	//OK
	public void createCarrier(){
		Carrier carrier = new Carrier();
		carrier.setName("fedex");
		carrier.setTrackingUrl("trackidfghjk/tracking/");
		
		System.out.println("@@@@@@@ carrierId avant ajout: " + carrier.getId() + " @@@@@@@");
		
		carrierDao.createCarrier(carrier);
		
		System.out.println("@@@@@@@ carrierId après ajout: " + carrier.getId() + " @@@@@@@");
		Assert.assertTrue(carrier.getId() != 0);
	}
	
	@Test	//OK
	public void getCarrierById(){
		Carrier carrier = carrierDao.getCarrierById(1);
		Assert.assertTrue(carrier.getId() == 1);
	}
	
	@Test	//OK
	public void updateCarrier(){
		Carrier carrier = carrierDao.getCarrierById(1);
		System.out.println("@@@@@@@ trackingurl avant update: " + carrier.getTrackingUrl() + " @@@@@@@");
		carrier.setTrackingUrl("jetracemonurl/tracking/");
		carrier = carrierDao.updateCarrier(carrier);
		System.out.println("@@@@@@@ trackingurl après update: " + carrier.getTrackingUrl() + " @@@@@@@");
		Assert.assertTrue(carrier.getTrackingUrl().equals("jetracemonurl/tracking/"));
	}
	
	@Test	//OK
	public void deleteCarrier(){
		boolean deleteControl = carrierDao.deleteCarrier(1);
		Assert.assertTrue(deleteControl);
	}
}
