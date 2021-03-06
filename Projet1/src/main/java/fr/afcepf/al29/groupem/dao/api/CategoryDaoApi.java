package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

public interface CategoryDaoApi {
	
	Category createCategory(Category category);
	Category getCategoryById(int categoryId);
	Category updateCategory(Category category);
	boolean destroyCategory(Category category);
	
	List<Category> getCategoryByMetaId(int metaCategoryId);
	
	MetaCategory createMetaCategory(MetaCategory metaCategory);
	MetaCategory getMetaCategoryById(int metaCategoryId);
	MetaCategory updateMetaCategory(MetaCategory metaCategory);
	boolean destroyMetaCategory(MetaCategory metaCategory);
	List<MetaCategory> getAllMetaCategory();
	Category findCategory(int categoryId);
	

}
