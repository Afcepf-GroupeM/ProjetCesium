import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class Pg {

	public static void main(String[] args) throws JSONException {
		
	
		
		
		JSONObject jsonObject = new JSONObject();
		Adresse adresse = new Adresse();
		adresse.setFirstname("Bernard");
		adresse.setLastname("Laporte");
		adresse.setNumero(42);
		adresse.setComplement("");
		adresse.setTypeVoie("rue");
		adresse.setNomVoie("des papillons");
		adresse.setZipcode("91210");
		adresse.setCity("Draveil");
		adresse.setCountry("France");
		
		JSONObject jsonadresse = new JSONObject(adresse);
		
		jsonObject.put("adresse", jsonadresse);
		jsonObject.put("nbItem", 50);
		jsonObject.put("delaiMax", 5);
		
		
		System.out.println(jsonObject);
		
		
		try {
			URL url = new URL("http://localhost:8080/WebServiceLogistique/PriseEnCharge/add");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonObject.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (in.readLine() != null) {
			}
			System.out.println("\nReussite");
			in.close();
		} catch (Exception e) {
			System.out.println("\nErreur");
			System.out.println(e);
		}

		
	} 
		

	}


