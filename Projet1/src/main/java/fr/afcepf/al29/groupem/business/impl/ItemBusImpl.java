package fr.afcepf.al29.groupem.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.dao.api.ItemDaoApi;
import fr.afcepf.al29.groupem.entities.Item;



@Transactional
@Component
public class ItemBusImpl implements ItemBusApi {

	@Autowired
	private ItemDaoApi itemDao;
	
	
	@Override
	public Item createItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemById(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Item> getItemsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
