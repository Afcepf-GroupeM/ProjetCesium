package fr.afcepf.al29.groupem.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.ItemBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.entities.Item;
import fr.afcepf.al29.groupem.entities.Order;

@Component
@ManagedBean
public class AdministrationController {
	
	
	private double caToday;
	private double caMonth;
	private double caTotal;
	private int ordersToday;
	private int ordersMonth;
	private int ordersTotal;
	
	private List<Order> ordersToPrepare; // First 5 found
	private List<Item> itemsLowStock; // First 5 found
	
	@Autowired
	private OrderBusApi orderBus;
	
	@Autowired
	private ItemBusApi itemBus;
	
	
	
	public void initAdmin(ComponentSystemEvent c){
		itemsLowStock = new ArrayList<>();
		List<Item> itemsStockLessThanSix = itemBus.getItemsByStockLessthan(6);
		int i = 0;
		Iterator<Item> it = itemsStockLessThanSix.iterator();
		while (it.hasNext() && i < 5) {
			itemsLowStock.add(it.next());
			i++;
		}
		
	}



	public double getCaToday() {
		return caToday;
	}



	public void setCaToday(double caToday) {
		this.caToday = caToday;
	}



	public double getCaMonth() {
		return caMonth;
	}



	public void setCaMonth(double caMonth) {
		this.caMonth = caMonth;
	}



	public double getCaTotal() {
		return caTotal;
	}



	public void setCaTotal(double caTotal) {
		this.caTotal = caTotal;
	}



	public int getOrdersToday() {
		return ordersToday;
	}



	public void setOrdersToday(int ordersToday) {
		this.ordersToday = ordersToday;
	}



	public int getOrdersMonth() {
		return ordersMonth;
	}



	public void setOrdersMonth(int ordersMonth) {
		this.ordersMonth = ordersMonth;
	}



	public int getOrdersTotal() {
		return ordersTotal;
	}



	public void setOrdersTotal(int ordersTotal) {
		this.ordersTotal = ordersTotal;
	}



	public List<Order> getOrdersToPrepare() {
		return ordersToPrepare;
	}



	public void setOrdersToPrepare(List<Order> ordersToPrepare) {
		this.ordersToPrepare = ordersToPrepare;
	}



	public List<Item> getItemsLowStock() {
		return itemsLowStock;
	}



	public void setItemsLowStock(List<Item> itemsLowStock) {
		this.itemsLowStock = itemsLowStock;
	}



	public OrderBusApi getOrderBus() {
		return orderBus;
	}



	public void setOrderBus(OrderBusApi orderBus) {
		this.orderBus = orderBus;
	}



	public ItemBusApi getItemBus() {
		return itemBus;
	}



	public void setItemBus(ItemBusApi itemBus) {
		this.itemBus = itemBus;
	}

	
	
}
