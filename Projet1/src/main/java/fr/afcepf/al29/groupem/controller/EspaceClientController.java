package fr.afcepf.al29.groupem.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al29.groupem.business.api.AddressBusApi;
import fr.afcepf.al29.groupem.business.api.OrderBusApi;
import fr.afcepf.al29.groupem.business.api.UserBusApi;
import fr.afcepf.al29.groupem.entities.Address;
import fr.afcepf.al29.groupem.entities.Civilite;
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.User;

@Component
@ManagedBean
public class EspaceClientController {
	private String messageInfoPerson;
	private User userConnect;
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private List<Order> listOrder;
	
	private String messageOrdering;		
	private List<Order> listOrdering;
	private Order ordering;
	private OrderLine orderingLine;
	
	private String messageOldOrder;
	private List<Order> listOldOrder;
	private Order oldOrder;
	private OrderLine oldOrderLine;
	
	private String messageAddress;
	private Address address;
	private List<Address> listAddress;
	
	UserBusApi userBusApi;
	@Autowired
	OrderBusApi orderBusApi;	
	AddressBusApi addressBusApi;
	
	public void init(ComponentSystemEvent e){		
		Date date= new Date();
		try {
			date = simpleDateFormat.parse("02/10/1956");
		} catch (ParseException e1) {			
			e1.printStackTrace();
		}
		User userConnect = new User(Civilite.Mr,"Lagaffe","Gaston","toto@franquin.com","0163458950",date,"$2a$10$ql6IkE4MblaPsc5FTwzulOyYD49HKv65AIbMrdAru.MBU4uhHx18q");
		int idUser = 8;
		listOrder = new ArrayList<>();
		listOrder = orderBusApi.getOrderByUserId(idUser);
		
		for(Order order:listOrder){
			Date dateOrder = order.getCreationDate();
			
			Date referenceDate = new Date();
			referenceDate = dateOrder;
			Calendar c = Calendar.getInstance(); 
			c.setTime(referenceDate); 
			c.add(Calendar.MONTH, -2);
			referenceDate= c.getTime();
			System.out.println(referenceDate.toString());				
			
			if(dateOrder.before(referenceDate)){
				listOldOrder = new ArrayList<>();
				listOldOrder.add(order);
			}else{
				listOrdering = new ArrayList<>();
				listOrdering.add(order);				
			}			
		}
		
		if(listOrdering.isEmpty()){
			messageOrdering = "Vous n'avez pas de commande en cours";
		}else{
			messageOrdering = "";
		}
		
		if(listOldOrder.isEmpty()){
			messageOldOrder = "Vous n'avez pas de commande historique";
		}else{
			messageOrdering = "";
		}
	}
	
	
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
	public OrderLine getOldOrderLine() {
		return oldOrderLine;
	}

	public void setOldOrderLine(OrderLine oldOrderLine) {
		this.oldOrderLine = oldOrderLine;
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


	public String getMessageOldOrder() {
		return messageOldOrder;
	}


	public void setMessageOldOrder(String messageOldOrder) {
		this.messageOldOrder = messageOldOrder;
	}
	
	
}
