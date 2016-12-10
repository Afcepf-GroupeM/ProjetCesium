package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

public interface CategoryDaoApi {
	
	boolean createCategory(Category category);
	Category getCategoryById(int categoryId);
	Category updateCategoryById(int categoryId);
	boolean destroyCategoryById(int categoryId);
	
	List<Category> getCategoryByMetaId(int metaCategoryId);
	
	boolean createMetaCategory(MetaCategory metaCategory);
	MetaCategory getMetaCategoryById(int metaCategoryId);
	List<MetaCategory> getAllMetaCategory();
	

}
