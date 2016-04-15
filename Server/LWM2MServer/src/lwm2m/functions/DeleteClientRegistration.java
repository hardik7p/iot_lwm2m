package lwm2m.functions;

import lwm2m.server.Server;

import com.mongodb.BasicDBObject;

public class DeleteClientRegistration {

	public String deleteRegistrationInDB(String registrationId) {		
		
		
		BasicDBObject query = new BasicDBObject();
		query.append("registrationId", registrationId);
		
		Server.registrationInfo.remove(query);
		
		return "Success";
	}

	


}

