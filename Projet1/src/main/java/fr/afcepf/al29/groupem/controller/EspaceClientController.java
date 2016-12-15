package fr.afcepf.al29.groupem.controller;

import java.util.List;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.User;

public class EspaceClientController {
	private String messageInfoPerson;
	private User userConnect;
	
	private List<Order> listOrder;
	
	private String messageOrdering;		
	private List<Order> listOrdering;
	private Order ordering;
	private OrderLine orderingLine;
	
	private List<Order> listOldOrder;
	private Order oldOrder;
	private OrderLine oleOrderLine;
	
	private String messageAddress;
	private Address address;
	private List<Address> listAddress;
	
	UserBusApi userBusApi;
	OrderBusApi orderBusApi;	
	AddressBusApi addressBusApi;
	
	
	public String getMessageInfoPerson() {
		return messageInfoPerson;
	}
	public void setMessageInfoPerson(String messageInfoPerson) {
		this.messageInfoPerson = messageInfoPerson;
	}
	public User getUserConnect() {
		return userConnect;
	}
	public void setUserConnect(User userConnect) {
		this.userConnect = userConnect;
	}
	public List<Order> getListOrder() {
		return listOrder;
	}
	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}
	public String getMessageOrdering() {
		return messageOrdering;
	}
	public void setMessageOrdering(String messageOrdering) {
		this.messageOrdering = messageOrdering;
	}
	public List<Order> getListOrdering() {
		return listOrdering;
	}
	public void setListOrdering(List<Order> listOrdering) {
		this.listOrdering = listOrdering;
	}
	public Order getOrdering() {
		return ordering;
	}
	public void setOrdering(Order ordering) {
		this.ordering = ordering;
	}
	public OrderLine getOrderingLine() {
		return orderingLine;
	}
	public void setOrderingLine(OrderLine orderingLine) {
		this.orderingLine = orderingLine;
	}
	public List<Order> getListOldOrder() {
		return listOldOrder;
	}
	public void setListOldOrder(List<Order> listOldOrder) {
		this.listOldOrder = listOldOrder;
	}
	public Order getOldOrder() {
		return oldOrder;
	}
	public void setOldOrder(Order oldOrder) {
		this.oldOrder = oldOrder;
	}
	public OrderLine getOleOrderLine() {
		return oleOrderLine;
	}
	public void setOleOrderLine(OrderLine oleOrderLine) {
		this.oleOrderLine = oleOrderLine;
	}
	public String getMessageAddress() {
		return messageAddress;
	}
	public void setMessageAddress(String messageAddress) {
		this.messageAddress = messageAddress;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Address> getListAddress() {
		return listAddress;
	}
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}
	
	
}
