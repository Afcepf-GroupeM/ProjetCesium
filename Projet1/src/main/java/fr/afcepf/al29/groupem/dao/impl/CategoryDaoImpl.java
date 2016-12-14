package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.dao.api.CategoryDaoApi;
import fr.afcepf.al29.groupem.entities.Category;
import fr.afcepf.al29.groupem.entities.MetaCategory;


@Transactional
@Component
public class CategoryDaoImpl implements CategoryDaoApi{
	
	@PersistenceContext(unitName="Projet1") 
	private EntityManager entityManager;
	

	@Override
	public Category createCategory(Category category) {
		entityManager.persist(category);
		return category;
	}


	@Override
	public Category getCategoryById(int categoryId) {
		Category category = entityManager.find(Category.class, categoryId);
		return category;
	}


	@Override
	public Category updateCategory(Category category) {
		entityManager.merge(category);
		return category;
	}


	@Override
	public boolean destroyCategory(Category category) {
		Category catToRemove = entityManager.getReference(Category.class, category.getId());
		entityManager.remove(catToRemove);
		return (getCategoryById(category.getId()) == null);
	}


	@Override
	public List<Category> getCategoryByMetaId(int metaCategoryId) {
		List<Category> listCategories = new ArrayList<>();
		listCategories = entityManager.createQuery("SELECT cat FROM Category cat WHERE cat.metaCategory = :metacatid",Category.class)
									  .setParameter("metacatid", metaCategoryId)
									  .getResultList();
		return listCategories;
	}


	@Override
	public MetaCategory createMetaCategory(MetaCategory metaCategory) {
		entityManager.persist(metaCategory);
		return metaCategory;
	}


	@Override
	public MetaCategory getMetaCategoryById(int metaCategoryId) {
		MetaCategory metacat = entityManager.find(MetaCategory.class, metaCategoryId);
		return metacat;
	}


	@Override
	public MetaCategory updateMetaCategory(MetaCategory metaCategory) {
		entityManager.merge(metaCategory);
		return metaCategory;
	}


	@Override
	public boolean destroyMetaCategory(MetaCategory metaCategory) {
		MetaCategory metaCatToRemove = entityManager.getReference(MetaCategory.class, metaCategory.getId());
		entityManager.remove(metaCatToRemove);
		return (getCategoryById(metaCategory.getId()) == null);
	}


	@Override
	public List<MetaCategory> getAllMetaCategory() {
		List<MetaCategory> listMetaCat = entityManager.createQuery("SELECT metcat FROM MetaCategory metcat",MetaCategory.class).getResultList();
		return listMetaCat;
	}


}
