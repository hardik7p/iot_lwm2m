package lwm2m.objects;

public class SensorObject {
	
	private int objectId;
	private int objectInstanceId;

	private int predictedObjectCount;
	private boolean fenceBreached;
	private boolean weaponsDetected;
	private boolean gunShotsDetected;
	private boolean stopSensing;
	
	public SensorObject(){
		
		this.objectId=10;
		this.objectInstanceId=0;
	}
	public SensorObject(boolean preSet){
		
		this.objectId=10;
		this.objectInstanceId=0;
		this.predictedObjectCount=3;
		this.fenceBreached=true;
		this.weaponsDetected=true;
		this.gunShotsDetected=false;
	}
	public String getResourceDescription(int resourceId){
		
		switch (resourceId){
		case 0:
			return "predictedObjectCount";
		case 1:
			return "fenceBreached";
		case 2:
			return "weaponsDetected";
		case 3:
			return "gunShotsDetected";
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
	public int getPredictedObjectCount() {
		return predictedObjectCount;
	}
	public void setPredictedObjectCount(int predictedObjectCount) {
		this.predictedObjectCount = predictedObjectCount;
	}
	public boolean isFenceBreached() {
		return fenceBreached;
	}
	public void setFenceBreached(boolean fenceBreached) {
		this.fenceBreached = fenceBreached;
	}
	public boolean isWeaponsDetected() {
		return weaponsDetected;
	}
	public void setWeaponsDetected(boolean weaponsDetected) {
		this.weaponsDetected = weaponsDetected;
	}
	public boolean isGunShotsDetected() {
		return gunShotsDetected;
	}
	public void setGunShotsDetected(boolean gunShotsDetected) {
		this.gunShotsDetected = gunShotsDetected;
	}
	public boolean isStopSensing() {
		return stopSensing;
	}
	public void setStopSensing(boolean stopSensing) {
		this.stopSensing = stopSensing;
	}
}
