package lwm2m.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import lwm2m.server.Server;

import com.mongodb.BasicDBObject;

public class RegisterClient {

	private String registrationId;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String registerInDB(String epnendPointName, String lifeTime) {		
		
		int size = 1020;

		ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 1; i <= size; i++) {
			list.add(i);
		}
		Random rand = new Random();

		int tempId = rand.nextInt(list.size());
		String registrationId = Integer.toString(tempId);

		BasicDBObject document = new BasicDBObject();
		document.put("epnendPointName",epnendPointName);
		document.put("registrationId",registrationId);
		document.put("lifeTime",lifeTime);
		document.put("registrationDate",new Date());
		
		Server.registrationInfo.insert(document);
		
		return registrationId;
	}

}
