package fr.afcepf.al29.groupem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		listItems = entityManager.createQuery("SELECT itm FROM Item itm WHERE itm.category.id = :catid", Item.class)
								 .setParameter("catid", categoryId)
								 .getResultList();
		return listItems;
	}

	@Override
	public List<Item> searchItems(String keyword) {
		
		Query query;
		List<Item> items;
		query = entityManager.createQuery("SELECT i FROM Item i WHERE UPPER(i.name) LIKE:keyword OR UPPER (i.category.name) LIKE :keyword ORDER BY i.category.metaCategory.name, i.category.name");
		query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		items = query.getResultList();
		
		return items;
		
	}

	@Override
	public Item findItem(int itemId) {
		Item item = entityManager.find(Item.class, itemId);
		return item;
	}

	@Override
	public Item updateItemQuantity(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItemsByStockLessThan(int stock) {
		List<Item> listItems = new ArrayList<>();
		listItems = entityManager.createQuery("SELECT itm FROM Item itm WHERE itm.stock < :stock", Item.class)
								 .setParameter("stock", stock)
								 .getResultList();
		return listItems;
		
	}

	@Override
	public List<Item> getAllItems() {
		List<Item> listItems = new ArrayList<>();
		listItems = entityManager.createQuery("SELECT itm FROM Item itm",Item.class).getResultList();
		return listItems;
	}

}
