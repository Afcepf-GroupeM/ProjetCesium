package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.CategoryBusApi;
import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

@Transactional
@Component
public class CategoryBusImpl implements CategoryBusApi {
	
	@Autowired
	CategoryDaoApi catDao;
	

	@Override
	public List<MetaCategory> getAllMetaCategory() {
		return catDao.getAllMetaCategory();
	}

	@Override
	public MetaCategory getMetaCategoryById(int metaCategoryId) {
		
		return null;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		//TODO Add check if null = category doesn't exist.
		return catDao.getCategoryById(categoryId);
	}

	

}
