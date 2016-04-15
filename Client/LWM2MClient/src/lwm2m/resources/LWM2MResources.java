package lwm2m.resources;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class LWM2MResources {

	private int objectId;
	private int objectInstanceCount;
	private int resourceCount;
	private int pmin;
	private int pmax;
	private boolean cancel;

	public LWM2MResources(int objectId, int objectInstanceCount, int resourceCount, int pmin, int pmax) {
		this.objectId = objectId;
		this.objectInstanceCount = objectInstanceCount;
		this.resourceCount = resourceCount;
		this.pmin=pmin;
		this.pmax=pmax;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getObjectInstanceCount() {
		return objectInstanceCount;
	}

	public void setObjectInstanceCount(int objectInstanceCount) {
		this.objectInstanceCount = objectInstanceCount;
	}

	public int getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}

	public int getPmin() {
		return pmin;
	}

	public void setPmin(int pmin) {
		this.pmin = pmin;
	}

	public int getPmax() {
		return pmax;
	}

	public void setPmax(int pmax) {
		this.pmax = pmax;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myLWM2MResource = gson.toJson(this);

		// System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myLWM2MResource);
		if (objectId == 0) {
			a1.lwm2mResourceInfo.remove(new BasicDBObject());
		}
		a1.lwm2mResourceInfo.insert(dbo);
	}
}
