package fr.afcepf.al29.groupem.business.api;

import java.util.List;

import fr.afcepf.al29.groupem.entities.Order;

public interface OrderBusApi {

	List<Order> getOrderByUserId(int idUser);

}
