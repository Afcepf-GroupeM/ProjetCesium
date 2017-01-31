package fr.afcepf.groupem.business.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.IWSBankBusApi;
import fr.afcepf.groupem.entities.BankRequest;
import fr.afcepf.groupem.entities.BankResponse;

@Component
public class WSBankBusImpl implements IWSBankBusApi {
	
	private String urlWSBank = "http://localhost:8080/WebServiceBanque/UserAccountService/receptionInfoReturnResponse";

	
//	
//	Return code:
//	0 : OK 
//	1 : Failed -> MalformedURLException
//	2 : Failed -> IOException 
//	3 : Failed -> JSONException on response from WS Bank
//	
	
	@Override
	public BankResponse checkPayement(BankRequest bankRequest) {
		
		BankResponse bankResponse = new BankResponse();
		bankResponse.setMessage("");
		bankResponse.setReturnCode(0);
		
		JSONObject bankRequestJson = new JSONObject(bankRequest);
		JSONObject returnBank = null;
		System.out.println("\n----------\n.: Call: WS Bank - CheckPayement :.");
		try {
			URL url = new URL(urlWSBank);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			System.out.println("Request sent to WS Bank : " + bankRequestJson.toString());
			out.write(bankRequestJson.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String sortie = "";
			while ((line = in.readLine()) != null) {
				sortie += line;
			}
			System.out.println("Response from WS Bank : " + sortie );
			try {
				returnBank = new JSONObject(sortie);			
				bankResponse.setTransactionId(returnBank.getInt("referenceNumber"));
				bankResponse.setStatut(returnBank.getString("status"));
								
			} catch (JSONException e) {
				bankResponse.setReturnCode(3);
				bankResponse.setMessage(e.getMessage());
			}
						
		} catch (MalformedURLException e) {
			bankResponse.setReturnCode(1);
			bankResponse.setMessage(e.getMessage());
		} catch (IOException e) {
			bankResponse.setReturnCode(2);
			bankResponse.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		
		System.out.println("Response after calling WS Bank : \n" + bankResponse.toString() );
		return bankResponse;
	}

	
	
	public String getUrlWSBank() {
		return urlWSBank;
	}

	public void setUrlWSBank(String urlWSBank) {
		this.urlWSBank = urlWSBank;
	}
	
	

}
