package lwm2m.objects;

public class DeviceObject {
	
	private int objectId;
	private int objectInstanceId;

	private String Manufacturer;
	private String DeviceType;
	private String ModelNumber;
	private String SerialNumber;
	private String FirmwareVersion;
	private float BatteryLevel;
	
	public DeviceObject(){
		this.objectId=3;
		this.objectInstanceId=0;
	}
	public DeviceObject(boolean preSet){
		
		this.objectId=3;
		this.objectInstanceId=0;
		this.Manufacturer="TATA Advance Systems";
		this.DeviceType="BorderDefenseSensor";
		this.ModelNumber="bds01";
		this.SerialNumber="001";
		this.FirmwareVersion="bds 1.0";
		this.BatteryLevel=(float) 0.95;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public String getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}
	public String getModelNumber() {
		return ModelNumber;
	}
	public void setModelNumber(String modelNumber) {
		ModelNumber = modelNumber;
	}
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getFirmwareVersion() {
		return FirmwareVersion;
	}
	public void setFirmwareVersion(String firmwareVersion) {
		FirmwareVersion = firmwareVersion;
	}
	public float getBatteryLevel() {
		return BatteryLevel;
	}
	public void setBatteryLevel(float batteryLevel) {
		BatteryLevel = batteryLevel;
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

	public String getResourceDescription(int resourceId){
		
		switch (resourceId){
		case 0:
			return "Manufacturer";
		case 1:
			return "DeviceType";
		case 2:
			return "ModelNumber";
		case 3:
			return "SerialNumber";
		case 4:
			return "FirmwareVersion";
		case 5:
			return "BatteryLevel";
		default :		
			return "error";			
		}
	}
	
	@Override
	public String toString() {
	   return "DeviceObject [ObjectId=" + objectId +
	   		   ", ObjectInstanceId=" + objectInstanceId + ", Manufacturer=" + Manufacturer + 
			   ", DeviceType=" + DeviceType + ", ModelNumber=" 	+ ModelNumber +
			   ", SerialNumber=" + SerialNumber + ", FirmwareVersion=" + FirmwareVersion + 
			   ", BatteryLevel=" + BatteryLevel + "]";
	}
}
