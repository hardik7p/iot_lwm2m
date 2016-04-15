package lwm2m.objects;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import lwm2m.client.ClientA;

public class WeaponSensorObject {
	
	private int objectId;
	private String epn;
	private String timestamp;
	private Boolean WeaponDetected;
	private Boolean SmallArms;
	private int ObservationId;
	private boolean Explosives;
	
	public WeaponSensorObject(){
		this.objectId=12;
		this.WeaponDetected=false;
		this.SmallArms=false;
		this.ObservationId=0;
		this.Explosives=false;
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

	public Boolean getWeaponDetected() {
		return WeaponDetected;
	}

	public void setWeaponDetected(Boolean weaponDetected) {
		WeaponDetected = weaponDetected;
	}

	public Boolean getSmallArms() {
		return SmallArms;
	}

	public void setSmallArms(Boolean smallArms) {
		SmallArms = smallArms;
	}

	public int getObservationId() {
		return ObservationId;
	}

	public void setObservationId(int observationId) {
		ObservationId = observationId;
	}

	public boolean isExplosives() {
		return Explosives;
	}

	public void setExplosives(boolean explosives) {
		Explosives = explosives;
	}
	
	public void pushToDB(ClientA a1) throws UnknownHostException {

		Gson gson = new Gson();
		String myServer = gson.toJson(this);

		//System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myServer);
			a1.WeaponInfo.remove(new BasicDBObject());
		a1.WeaponInfo.insert(dbo);
	}


}