package lwm2m.resources;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class ResourceDefinition {

	private int objectId;

	Map<Integer, String> resourceDescriptionMap = new HashMap<Integer, String>();

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public Map<Integer, String> getResourceDescriptionMap() {
		return resourceDescriptionMap;
	}

	public void setResourceDescriptionMap(Map<Integer, String> resourceDescriptionMap) {
		this.resourceDescriptionMap = resourceDescriptionMap;
	}

	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myLWM2MResource = gson.toJson(this);

		// System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myLWM2MResource);

		a1.resourceDefinitionInfo.remove(new BasicDBObject());
		a1.resourceDefinitionInfo.insert(dbo);
	}
}
