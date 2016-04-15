package lwm2m.objects;

import java.util.Date;

public class LocationObject {

	private int objectId;
	private int objectInstanceId;
	private float latitude;
	private float longitude;
	private float altitude;
	private float velocity;
	private Date timeStamp;
	
	public LocationObject(){
		this.objectId=6;
		this.objectInstanceId=0;
	}
	public LocationObject(boolean preSet){
		
		this.objectId=6;
		this.objectInstanceId=0;
		this.latitude=(float) 23.02;
		this.longitude=(float) 72.57;
		this.altitude=(float) 48.77;
		this.velocity=(float) 0.0;
		this.timeStamp=new Date();
	}
	public String getResourceDescription(int resourceId){
		
		switch (resourceId){
		case 0:
			return "latitude";
		case 1:
			return "longitude";
		case 2:
			return "altitude";
		case 3:
			return "velocity";
		case 4:
			return "timeStamp";
		default :		
			return "error";			
		}
	}
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}
