package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Transactional
@Component
public class CategoryBusImpl implements CategoryBusApi {
	
	@Autowired
	CategoryDaoApi catDao;
	
	@Autowired
	ItemDaoApi itemDao;
	

	@Override
	public List<MetaCategory> getAllMetaCategory() {
		return catDao.getAllMetaCategory();
	}

	@Override
	public MetaCategory getMetaCategoryById(int metaCategoryId) {
		
		return catDao.getMetaCategoryById(metaCategoryId);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		//TODO Add check if null = category doesn't exist.
		return catDao.getCategoryById(categoryId);
	}

	@Override
	public Category createCategory(Category category) {
		Category cat = catDao.createCategory(category);
		return cat;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroyCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> getCategoryByMetaId(int metaCategoryId) {
		
		return catDao.getCategoryByMetaId(metaCategoryId);
	}

	@Override
	public MetaCategory createMetaCategory(MetaCategory metaCategory) {
		MetaCategory metaCat = catDao.createMetaCategory(metaCategory);
		return metaCat;
	}

	@Override
	public MetaCategory updateMetaCategory(MetaCategory metaCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroyMetaCategory(MetaCategory metaCategory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category findCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int numberOfCategoriesInMeta(int idMetaCat){
		List<Category> listCat = catDao.getCategoryByMetaId(idMetaCat);
		return listCat.size();
	}
	
	@Override
	public int numberOfItemsInCat(int idCategory){
		List<Item> itemList = itemDao.getItemsByCategory(idCategory);
		return itemList.size();
	}

	

}
