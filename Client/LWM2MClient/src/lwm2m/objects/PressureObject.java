package lwm2m.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class PressureObject {

	private int objectId;
	private String epn;
	private String timestamp;
	private Boolean TypeOfVehicle;
	private double Pressure;
	private Boolean PressureDetected;
	private int observationId;

	public PressureObject() {
		this.objectId = 10;
		this.Pressure = 0.0;
		this.PressureDetected = false;
		this.TypeOfVehicle = false;
		this.observationId = 0;
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

	public int getObservationId() {
		return observationId;
	}

	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public Boolean getTypeOfVehicle() {
		return TypeOfVehicle;
	}

	public void setTypeOfVehicle(Boolean typeOfVehicle) {
		TypeOfVehicle = typeOfVehicle;
	}

	public double getPressure() {
		return Pressure;
	}

	public void setPressure(double pressure) {
		Pressure = pressure;
	}

	public Boolean getPressureDetected() {
		return PressureDetected;
	}

	public void setPressureDetected(Boolean pressureDetected) {
		PressureDetected = pressureDetected;
	}

	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		// System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myServer);
		a1.PressureInfo.remove(new BasicDBObject());
		a1.PressureInfo.insert(dbo);
	}

}