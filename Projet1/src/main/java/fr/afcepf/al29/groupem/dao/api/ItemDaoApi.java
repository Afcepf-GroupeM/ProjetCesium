package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Item;

public interface ItemDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	Item createItem(Item item);
	Item getItemById(int itemId);
	Item updateItem(Item item);
	boolean deleteItem(Item item);
	
	List<Item> getItemsByCategory (int categoryId);
	
	List<Item> searchItems(String keyword);
	
	Item findItem(int itemId);

}
