package fr.afcepf.groupem.business.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import fr.afcepf.groupem.business.api.IWSShippingBusApi;
import fr.afcepf.groupem.entities.ShippingRequest;
import fr.afcepf.groupem.entities.ShippingResponse;

public class WSShippingBusImpl implements IWSShippingBusApi {

	

	private String urlWSLogistique = "http://localhost:8080/WebServiceLogistique/PriseEnCharge/add";

	

//	
//	Return code:
//	0 : OK 
//	1 : Failed -> JSONException on AddresseJson creation 
//	2 : Failed -> JSONException on shippingRequestJson creation
//	4 : Failed -> MalformedURLException on response from WS Logistique
//	5 : Failed -> IOException
//	
//	
	@Override
	public ShippingResponse requestShipping(ShippingRequest shippingRequest) {
		
		ShippingResponse shippingResponse = new ShippingResponse();
		shippingResponse.setReturnCode(0);
		shippingResponse.setMessage("");
		
		JSONObject adresseJson = new JSONObject();
		try {
			adresseJson.put("lastname", shippingRequest.getLastname());
			adresseJson.put("firstname", shippingRequest.getFirstname());
			adresseJson.put("numero", shippingRequest.getRoadNumber());
			adresseJson.put("complement", shippingRequest.getComplement());
			adresseJson.put("typeVoie", shippingRequest.getRoadType());
			adresseJson.put("nomVoie", shippingRequest.getRoadName());
			adresseJson.put("city", shippingRequest.getCity());
			adresseJson.put("zipcode", shippingRequest.getZipcode());
			adresseJson.put("country", shippingRequest.getCountry());			
		} catch (JSONException e) {
			shippingResponse.setReturnCode(1);
			shippingResponse.setMessage(e.getMessage());
		}
		
		JSONObject shippingRequestJson = new JSONObject();
		try {
			shippingRequestJson.put("nbItem", shippingRequest.getNbItem());
			shippingRequestJson.put("delaiMax", shippingRequest.getDelaiMax());
			shippingRequestJson.put("adresse", adresseJson);
		} catch (JSONException e) {
			shippingResponse.setReturnCode(2);
			shippingResponse.setMessage(e.getMessage());
		}
		
		JSONObject returnLogistique = null;
		System.out.println("\n----------\n.: Call: WS Logistique - PriseEnCharge :.");
		try {
			URL url = new URL(urlWSLogistique);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			System.out.println("Request sent to WS Logistique : " + shippingRequestJson.toString());
			out.write(shippingRequestJson.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String sortie = "";
			while ((line = in.readLine()) != null) {
				sortie += line;
			}
			System.out.println("Response from WS Logistique : " + sortie );
			try {
				returnLogistique = new JSONObject(sortie);			
				shippingResponse.setTrackingCode(returnLogistique.getString("trackingCode"));
				shippingResponse.setDateLivraison((Date) returnLogistique.get("dateLivraison"));
				shippingResponse.setDatePriseEnCharge((Date) returnLogistique.get("datePriseEnCharge"));				
								
			} catch (JSONException e) {
				shippingResponse.setReturnCode(3);
				shippingResponse.setMessage(e.getMessage());
			}
						
		} catch (MalformedURLException e) {
			shippingResponse.setReturnCode(4);
			shippingResponse.setMessage(e.getMessage());
		} catch (IOException e) {
			shippingResponse.setReturnCode(5);
			shippingResponse.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		
		System.out.println("Response after calling WS Logistique : \n" + returnLogistique.toString() );
	
		return shippingResponse;
	}

}
