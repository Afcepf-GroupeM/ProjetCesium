package net.sharkou.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;
import junit.framework.Assert;


public class CategoriesTest {
	
	private Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private CategoryDaoApi catDao;
	
	@Before
	public void initUserDao(){
		ApplicationContext contextSpring = new ClassPathXmlApplicationContext("springConf.xml");
		catDao = (CategoryDaoApi) contextSpring.getBean(CategoryDaoApi.class);
		
	}
	
	
//	@Test
	public void testGetAllMeta(){
		
		List<MetaCategory> listMeta = null;
		listMeta = catDao.getAllMetaCategory();
		for (MetaCategory metaCategory : listMeta) {
			log.info("\n\n");
			log.info(metaCategory.getId() + " : " + metaCategory.getName());
			log.info("-------------- ");
			List<Category> listCat = metaCategory.getCategories();
			
			for (Category category : listCat) {
				log.info("\t" + category.getId() + " : " + category.getName());
			}
			log.info("\n\n");
		}
		
		
		Assert.assertTrue(!(listMeta==null));
		
	}
	
	
//	@Test
	public void testPrintCategory(){
		
		Category category = catDao.getCategoryById(1);
		log.info("Id: " + category.getId());
		log.info("Nom: " + category.getName());
		log.info("Meta-category: " + category.getMetaCategory().getName());
		log.info("Items:");
		for (Item item : category.getItems()) {
			log.info("\t\t- Nom: " + item.getName());
			log.info("\t\t\tReference: " + item.getReference());
			log.info("\t\t\tPrix: " + item.getPrice()+ "€");
			log.info("\n");
		}

		
		Assert.assertTrue(!(category == null));
		
		
	}
	
}
