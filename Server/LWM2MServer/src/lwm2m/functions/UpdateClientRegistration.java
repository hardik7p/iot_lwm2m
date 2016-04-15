package lwm2m.functions;

import lwm2m.server.Server;

import com.mongodb.BasicDBObject;

public class UpdateClientRegistration {

	public String updateRegistrationInDB(String registrationId, String lifeTime) {		
		
		

		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put("registrationId", registrationId);
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("lifeTime", lifeTime));

		Server.registrationInfo.update(searchQuery, newDocument);
	
		return "Success";
	}

}
