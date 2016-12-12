package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.entities.Item;


@Transactional
@Component 
public class ItemDaoImpl implements ItemDaoApi {

	@PersistenceContext(unitName="Projet1") 
	private EntityManager entityManager;
	
	@Override
	public Item createItem(Item item) {
		entityManager.persist(item);
		return item;
	}

	@Override
	public Item getItemById(int itemId) {
		Item item = entityManager.find(Item.class, itemId);
		return item;
	}

	@Override
	public Item updateItem(Item item) {
		entityManager.merge(item);
		return item;
	}

	@Override
	public boolean deleteItem(Item item) {
		// GetReference used to attach entity User
				Item itemToRemove = entityManager.getReference(Item.class, item.getId());
				entityManager.remove(itemToRemove);
				
				return (getItemById(item.getId()) == null);
	}

	@Override
	public List<Item> getItemsByCategory(int categoryId) {
		List<Item> listItems = new ArrayList<>();
		listItems = entityManager.createQuery("SELECT itm FROM item itm WHERE itm.categoryid = :catid", Item.class)
								 .setParameter("catid", categoryId)
								 .getResultList();
		return listItems;
	}

}
