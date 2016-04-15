package lwm2m.resources;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class ObjectDefinition {

	private int objectId;
	private String description;
	
	public ObjectDefinition(int objectId, String description){
		this.objectId=objectId;
		this.description=description;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void pushToDB(ClientA a1) throws UnknownHostException{
		
		Gson gson = new Gson();
		String myObjectDefinition = gson.toJson(this);
		
		DBObject dbo = (DBObject)JSON.parse(myObjectDefinition);
		if(objectId==0){		
			a1.objectDefinitionInfo.remove(new BasicDBObject());
		}
		a1.objectDefinitionInfo.insert(dbo);
	}
}

