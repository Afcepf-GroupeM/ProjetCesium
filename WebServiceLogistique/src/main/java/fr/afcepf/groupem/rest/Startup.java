package fr.afcepf.groupem.rest;

import java.net.InetAddress;

import javax.xml.ws.Endpoint;

public class Startup {

	public static void main(String[] args) {
		try {
			PriseEnCharge conv  = new PriseEnCharge();
			//String soapUrl = "http://localhost:8080/conv"; 
			//avec restriction de securité
			String hostName= InetAddress.getLocalHost().getHostName();
			String url = "http://"+ hostName+ ":8080/";
			//pour accepter requete d'autre machine
			//http://....:8080/conv?wsdl
			Endpoint.publish(url, conv);
			System.out.println("serveur rest démarré, en attente des requêtes "
					          + "url=" + url);
		} catch (Exception e) {
						e.printStackTrace();
		}

	}

}
