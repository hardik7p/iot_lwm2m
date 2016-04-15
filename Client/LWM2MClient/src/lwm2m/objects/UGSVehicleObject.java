package lwm2m.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class UGSVehicleObject {
	
	private int objectId;
	private String epn;
	private String timestamp;
	private Boolean VehicleDetected;
	private int VehicleCount;
	private int ObservationId;
	
	public UGSVehicleObject(){
		this.objectId=11;
		this.VehicleDetected=false;
		this.VehicleCount=0;
		this.ObservationId=0;
	}
	public String getEpn() {
		return epn;
	}

	public void setEpn(String epn) {
		this.epn = epn;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public Boolean getVehicleDetected() {
		return VehicleDetected;
	}

	public void setVehicleDetected(Boolean vehicleDetected) {
		VehicleDetected = vehicleDetected;
	}

	public int getVehicleCount() {
		return VehicleCount;
	}

	public void setVehicleCount(int vehicleCount) {
		VehicleCount = vehicleCount;
	}

	public int getObservationId() {
		return ObservationId;
	}

	public void setObservationId(int observationId) {
		ObservationId = observationId;
	}

	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		//System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myServer);
			a1.UGSVehicleInfo.remove(new BasicDBObject());
		a1.UGSVehicleInfo.insert(dbo);
	}

}