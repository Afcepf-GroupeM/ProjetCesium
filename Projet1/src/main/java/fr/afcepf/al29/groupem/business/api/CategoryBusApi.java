package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;

public interface CategoryBusApi {
	
	List<MetaCategory> getAllMetaCategory();
	MetaCategory getMetaCategoryById(int metaCategoryId);
	Category getCategoryById(int categoryByDefault);

}
