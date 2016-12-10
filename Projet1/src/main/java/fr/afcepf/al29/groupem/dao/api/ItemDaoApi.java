package fr.afcepf.al29.groupem.dao.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Item;

public interface ItemDaoApi {
	
	// CRUD (Create - Read - Update - Delete)
	boolean createItem(Item item);
	Item getItemById(int itemId);
	Item updateItemById(int itemId);
	boolean deleteItemById(int itemId);
	
	List<Item> getItemsByCategory (int categoryId);
	

}
