package lwm2m.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class AccessControlObject {

	private int objectId;
	private int ACLobjectId;
	private int objectInstanceId;
	private int ACL;
	private int owner;
	
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getObjectInstanceId() {
		return objectInstanceId;
	}
	public void setObjectInstanceId(int objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}
	public int getACLobjectId() {
		return ACLobjectId;
	}
	public void setACLobjectId(int ACLobjectId) {
		this.ACLobjectId = ACLobjectId;
	}
	public int getACL() {
		return ACL;
	}
	public void setACL(int aCL) {
		this.ACL = aCL;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		//System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myServer);
		a1.accessControlInfo.insert(dbo);
	}
	
	public void deleteFromDB(ClientA a1) throws UnknownHostException {
		
		
		BasicDBObject query = new BasicDBObject();
		query.put("objectId", objectId);
		query.put("ACLobjectId", ACLobjectId);
		
		a1.accessControlInfo.remove(query);

		
	}
}