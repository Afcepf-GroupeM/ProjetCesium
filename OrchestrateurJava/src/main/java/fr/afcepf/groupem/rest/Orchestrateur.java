package fr.afcepf.groupem.rest;


import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.IWSBankBusApi;
import fr.afcepf.groupem.business.api.IWSShippingBusApi;
import fr.afcepf.groupem.entities.BankRequest;
import fr.afcepf.groupem.entities.BankResponse;
import fr.afcepf.groupem.entities.OrderValidationReponse;
import fr.afcepf.groupem.entities.OrderValidationRequest;
import fr.afcepf.groupem.entities.ShippingRequest;
import fr.afcepf.groupem.entities.ShippingResponse;




@Component

@Path("/Orchestrateur")
public class Orchestrateur {
	
	@Autowired
	IWSBankBusApi wsBankBus;
	
	@Autowired
	IWSShippingBusApi wsShippingBus;
	

	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String hello(){		
		return "Orchestrateur test : WS OK";
	}
	
	
	
	
	
	
//	
//	Return code:
//	0 : OK 
//	1 : Failed -> Error with WSbank
//	2 : Failed -> Error with WSShipping
//	4 : Failed -> Payement denied
//	5 : Failed -> 
//	
//		
	@POST
	@Path("/validation")
	@Produces("application/json")
	@Consumes("application/json")
	public OrderValidationReponse validationCommande(OrderValidationRequest orchRequest) {
		
		OrderValidationReponse orchResponse = new OrderValidationReponse();
		orchResponse.setErrorCode(0);
		orchResponse.setMessage("");
		
		
		BankRequest bankRequest = new BankRequest();
		bankRequest.setAmount(orchRequest.getMontant());
		bankRequest.setNumberCard(orchRequest.getCardNumber());
		bankRequest.setCryptogram(orchRequest.getCryptogram());
		bankRequest.setMonthExpiredCard(orchRequest.getMonthValidity());
		bankRequest.setYearExpiredCard(orchRequest.getYearValidity());
		bankRequest.setLastName(orchRequest.getLastname());
		
		BankResponse bankResponse = wsBankBus.checkPayement(bankRequest);
		
		if(bankResponse.getReturnCode() != 0) {
			orchResponse.setErrorCode(1);
			orchResponse.setMessage("Error with WS Bank : \n\tError code: "+ bankResponse.getReturnCode() + "\n\tMessage: " + bankResponse.getMessage());
		} else {
			
			if(bankResponse.getStatut().equals("OK")){
				
			
			
			orchResponse.setTransactionId(bankResponse.getTransactionId());
			
			ShippingRequest shippingRequest = new ShippingRequest();
			shippingRequest.setFirstName(orchRequest.getFirstname());
			shippingRequest.setLastName(orchRequest.getLastname());
			shippingRequest.setNumero(orchRequest.getRoadNumber());
			shippingRequest.setComplement(orchRequest.getComplement());
			shippingRequest.setTypeVoie(orchRequest.getRoadType());
			shippingRequest.setNomVoie(orchRequest.getRoadName());
			shippingRequest.setCity(orchRequest.getCity());
			shippingRequest.setZipcode(orchRequest.getZipcode());
			shippingRequest.setCountry(orchRequest.getCountry());
			shippingRequest.setDelaiMax(orchRequest.getNbDaysMaxToDeliver());
			shippingRequest.setNbItems(orchRequest.getNbOfItems());
			ShippingResponse shippingResponse = wsShippingBus.requestShipping(shippingRequest);
			
			if(shippingResponse.getReturnCode() != 0){
				orchResponse.setErrorCode(2);
				orchResponse.setMessage("Error with WS Shipping: \n\tError code: " + shippingResponse.getReturnCode()+"\n\tMessage: " + shippingResponse.getMessage());
			} else {
				
				orchResponse.setDateLivraison(shippingResponse.getDateLivraison());
				orchResponse.setTrackingCode(shippingResponse.getTrackingCode());
			}
			} else {
				orchResponse.setErrorCode(4);
				orchResponse.setMessage("Paiement refusé - Verifier les informations.");
			}
		
		}
		return orchResponse;
	}
	
	
	
	

}
