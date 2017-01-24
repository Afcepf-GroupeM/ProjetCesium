import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.groupem.business.api.ITrackingCodeService;
import fr.afcepf.groupem.business.impl.TrackingCodeService;
import fr.afcepf.groupem.entities.Transporteur;

@Component
public class Pg {
	
	
	
	

	public static void main(String[] args) {
		
		
		TrackingCodeService tcs = new TrackingCodeService();
		
		Transporteur transp = new Transporteur();
		transp.setId(1);
		transp.setName("Lol Transport");
		transp.setTrackingPrefix("UPS");
		transp.setUrl("http://www.loltransport.net/");
		
		for (int i = 0; i < 10; i++) {
			String trackingCode = tcs.generateTrackingCode(transp, "france", "03450");
			System.out.println(trackingCode);			
		}
		
		
		
		

	}

	
}
