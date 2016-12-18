package fr.afcepf.al29.groupem.controller;

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
import fr.afcepf.al29.groupem.entities.Order;
import fr.afcepf.al29.groupem.entities.OrderLine;
import fr.afcepf.al29.groupem.entities.OrderState;
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
	
	@Autowired
	UserBusApi userBusApi;
	@Autowired
	OrderBusApi orderBusApi;
	@Autowired	
	AddressBusApi addressBusApi;
	
	public void init(ComponentSystemEvent e){			
		int idUser = 3;
		userConnect =  userBusApi.getUserById(idUser);
		messageInfoPerson = "";
		System.out.println("********************userConnect******************"+userConnect.getfirstName() + " " + userConnect.getlastName());
		
		listOrder = new ArrayList<>();
		listOrder = orderBusApi.getOrderByUserId(idUser);
		for(Order order:listOrder){
			System.out.println(order.toString());
		}
		
		listOldOrder = new ArrayList<>();
		listOrdering = new ArrayList<>();
		for(Order order:listOrder){
			OrderState state = order.getState();
			if(state==OrderState.Livree ||state==OrderState.RemboursementClient){
				listOldOrder.add(order);				
			}else{
				listOrdering.add(order);
			}
		}
		/*
		Date referenceDate = new Date();			
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -2);
		referenceDate= c.getTime();
		System.out.println(referenceDate.toString());
		
		for(Order order:listOrder){
			Date dateOrder = order.getCreationDate();			
			if(dateOrder.before(referenceDate)){				
				listOldOrder.add(order);
			}else{				
				listOrdering.add(order);				
			}			
		}
		
		for(Order order:listOrdering){
			System.out.println("***************ordering****************" + order.toString());
		}
		for(Order order:listOldOrder){
			System.out.println("***********************oldorder**********************" + order.toString());
		}
		
		*/
		if(listOrdering.size()==0){
			messageOrdering = "Vous n'avez pas de commande en cours";
		}else{
			messageOrdering = "";
		}
		
		if(listOldOrder.size()==0){
			messageOldOrder = "Vous n'avez pas de commande historique";
		}else{
			messageOrdering = "";
		}
		
	}
	
	public String userModify(){
		return "/account.xhtml?faces-redirect = true";
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
