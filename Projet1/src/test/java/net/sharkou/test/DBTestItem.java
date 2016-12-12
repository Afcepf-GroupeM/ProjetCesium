package net.sharkou.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;;

@ContextConfiguration(locations={"/springConf.xml"})
public class DBTestItem {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ItemDaoApi itemDao;
	
	
	// Context initialization - Need to be done at the very beginning of our app... 
	@Before
	public void initUserDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		itemDao = (ItemDaoApi) contextSpring.getBean(ItemDaoApi.class);
	}
	
	
	@Test
	public void testFindItemById(){
		
		Assert.assertTrue(false);
	}
	
//	@Test
	public void testUpdateItem(){
		Assert.assertTrue(false);
	}
	
//	@Test
	public void testTestAddItem(){
		Assert.assertTrue(false);
	}
	
//	@Test
	public void testDeleteItem(){
		Assert.assertTrue(false);
	}
		
//	@Test
	public void testGetItemsByCategory(){
		Assert.assertTrue(false);
	}
		
		
	
	
}
