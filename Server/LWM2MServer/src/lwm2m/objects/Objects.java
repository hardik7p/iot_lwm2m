package lwm2m.objects;

import java.util.ArrayList;

public class Objects {
	
	ArrayList<ObjectCollectionName> lwm2mObjects = new ArrayList<ObjectCollectionName>();

	public Objects(){
		ObjectCollectionName o1 = new ObjectCollectionName(3, "serverDeviceInfo");
		ObjectCollectionName o2 = new ObjectCollectionName(6, "serverLocationInfo");
		ObjectCollectionName o3 = new ObjectCollectionName(10, "serverPressureInfo");
		ObjectCollectionName o4= new ObjectCollectionName(11, "serverUGSVehicleInfo");
		ObjectCollectionName o5= new ObjectCollectionName(12, "serverWeaponInfo");
		ObjectCollectionName o6= new ObjectCollectionName(13, "serverUGSPersonalInfo");
		ObjectCollectionName o7= new ObjectCollectionName(14, "serverIRInfo");
		ObjectCollectionName o8= new ObjectCollectionName(15, "serverVisualInfo");
		lwm2mObjects.add(o1);
		lwm2mObjects.add(o2);
		lwm2mObjects.add(o3);
		lwm2mObjects.add(o4);
		lwm2mObjects.add(o5);
		lwm2mObjects.add(o6);
		lwm2mObjects.add(o7);		
		lwm2mObjects.add(o8);
	}
	public String getObjectDBName(int objectId){
		return lwm2mObjects.get(objectId).toString();
	}
}
