package lwm2m.client;

import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import lwm2m.objects.DeviceObject;
import lwm2m.objects.LocationObject;
import lwm2m.objects.SecurityObject;
import lwm2m.objects.SensorObject;
import lwm2m.objects.ServerObject;
import lwm2m.resources.LWM2MResources;
import lwm2m.resources.ObjectDefinition;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientA {

	public MongoClient mongo = null;
	public DB db;
	public DBCollection bootStrapInfo;
	public DBCollection registrationInfo;
	public DBCollection serverInfo;
	public DBCollection deviceInfo;
	public DBCollection locationInfo;
	public DBCollection sensorInfo;
	public DBCollection observationInfo;
	public DBCollection objectDefinitionInfo;
	public DBCollection lwm2mResourceInfo;
	public DBCollection resourceDefinitionInfo;
	public DBCollection accessControlInfo;
	public DBCollection PressureInfo;
	public DBCollection UGSVehicleInfo;
	public DBCollection WeaponInfo;
	public DBCollection UGSPersonInfo;
	public DBCollection InfraRedInfo;
	public DBCollection VisualInfo;

	public void initialize() throws UnknownHostException {

		ObjectDefinition def1 = new ObjectDefinition(0, "Security");
		def1.pushToDB(this);
		def1.setObjectId(1);
		def1.setDescription("Server");
		def1.pushToDB(this);
		def1.setObjectId(3);
		def1.setDescription("Device");
		def1.pushToDB(this);
		def1.setObjectId(6);
		def1.setDescription("Location");
		def1.pushToDB(this);
		def1.setObjectId(10);
		def1.setDescription("Pressure");
		def1.pushToDB(this);
		def1.setDescription("UGSVehicle");
		def1.pushToDB(this);
		def1.setDescription("Weapon");
		def1.pushToDB(this);
		def1.setDescription("UGSPerson");
		def1.pushToDB(this);
		def1.setDescription("InfaRed");
		def1.pushToDB(this);
		def1.setDescription("Visual");
		def1.pushToDB(this);

		LWM2MResources myResources = new LWM2MResources(0, 2, 2, 3, 6);
		myResources.pushToDB(this);
		myResources.setObjectId(1);
		myResources.setObjectInstanceCount(2);
		myResources.setResourceCount(2);
		myResources.pushToDB(this);
		myResources.setObjectId(3);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(6);
		myResources.pushToDB(this);
		myResources.setObjectId(6);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(10);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(11);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(12);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(13);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(14);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
		myResources.setObjectId(15);
		myResources.setObjectInstanceCount(1);
		myResources.setResourceCount(5);
		myResources.pushToDB(this);
	}

	public void connectDb1() throws UnknownHostException {

		// if (mongo == null) {
		// mongo = new MongoClient("localhost", 27017);
		// db = mongo.getDB("myDatabase");

		String textUri = "mongodb://cmpe273:cmpe273@ds047672.mongolab.com:47672/cmpe273";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());

		bootStrapInfo = db.getCollection("bootStrapInfo");
		registrationInfo = db.getCollection("registrationInfo");
		serverInfo = db.getCollection("serverInfo");
		deviceInfo = db.getCollection("deviceInfo");
		sensorInfo = db.getCollection("sensorInfo");
		locationInfo = db.getCollection("locationInfo");
		observationInfo = db.getCollection("observationInfo");
		objectDefinitionInfo = db.getCollection("objectDefinitionInfo");
		lwm2mResourceInfo = db.getCollection("lwm2mResourceInfo");
		resourceDefinitionInfo = db.getCollection("resourceDefinitionInfo");
		accessControlInfo = db.getCollection("accessControlInfo");
		UGSPersonInfo = db.getCollection("UGSPersonInfo");
		InfraRedInfo = db.getCollection("InfraRedInfo");
		VisualInfo = db.getCollection("VisualInfo");
		PressureInfo = db.getCollection("PressureInfo");
		UGSVehicleInfo = db.getCollection("UGSVehicleInfo");
		WeaponInfo = db.getCollection("WeaponInfo");
		// }
	}

	public static final String endPointName = "6d26bbc3-a66a-455e-b089-3bb2f8ac915e"; // Pre
																						// Indtalled
																						// EndPointName
																						// UUID.randomUUID()
	public static String registrationId; // Fetched from server during bootstrap
	public SecurityObject sec0; // 1st Security Object /0 containing
								// BootStrapServer URI
	public ServerObject serv0; // 1st Server Object /1 containing
								// BootStrapServer Info
	public DeviceObject dev0; // Device Object /3 containing manufacturer,
								// devicetype, batterLevel etc
	public LocationObject loc0; // Location Object /6 containing Location Info
	public SensorObject sens0;
	static String[] resourceArray = new String[3]; // Resource Array to store
													// Server Response
	static String[] valueArray = new String[3]; // Resource Corresponding Value
												// Array for Server Response

	public ClientA() {

		sec0 = new SecurityObject("http://localhost:8007/LWM2MServer/lwm2m/bs", true, 0);
		serv0 = new ServerObject(true);
		dev0 = new DeviceObject(true);
		loc0 = new LocationObject(true);
		sens0 = new SensorObject(true);
	}

	public static void main(String[] args) throws UnknownHostException {

		ClientA c1 = new ClientA();

		c1.connectDb1();

		c1.pushObjectToDatabase();
		SecurityObject sec1 = new SecurityObject();
		ServerObject serv1 = new ServerObject();
		serv1.setShortServerId(101);
		serv1.setLifeTime(100);
		serv1.pushToDB(c1);
		c1.initialize();

		try {
			c1.requestBootStrap(); // BootStrap
		} catch (JSONException e) {
			e.printStackTrace();
		}
		c1.consumeBootStrapResponse(sec1, serv1);
		c1.requestRegistration(sec1, serv1); // Register
		try {
			Thread.sleep(2000); // Sleep for 10 seconds
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		// c1.updateRegistration(sec1, serv1); // Update Registration
		try {
			Thread.sleep(2000); // Again Sleep for 10 seconds
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		// c1.deleteRegistration(sec1, serv1); // Update Registration

		System.out.println("Waiting...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Ended.");

	}

	public int requestBootStrap() throws JSONException {

		Client client = Client.create();
		String output = new String();

		URI uri1 = UriBuilder.fromUri(sec0.getLWM2MServerURI()).queryParam("ep", endPointName).build("segment",
				"value");

		System.out.println("\nBootStraping with BootStrap Server : " + uri1);

		WebResource webResource = client.resource(uri1);

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Successfully BootStraped : " + output);

		if (response.getStatus() == 200) {

			JSONArray arr = new JSONArray(output);
			List<String> resource = new ArrayList<String>();
			List<String> value = new ArrayList<String>();

			for (int j = 0; j < arr.length(); ++j) {
				resource.add(arr.getJSONObject(j).getString("resource"));
				value.add(arr.getJSONObject(j).getString("value"));
			}

			for (int j = 0; j < arr.length(); ++j) {
				resourceArray[j] = (resource.get(j));
				valueArray[j] = (value.get(j));
			}
		} else {
			throw new RuntimeException("Error while BootStraping : HTTP error code : " + response.getStatus());
		}
		return 0;

	}

	public int consumeBootStrapResponse(SecurityObject sec1, ServerObject serv1) {

		// Handling for identifying Resource Response
		// New Object/Resource Instances Created upon request
		// Existing Object/Resource Instances Modified

		System.out.println("Resources Received :");
		for (int i = 0; i < 3; i++) {
			System.out.println(resourceArray[i] + " : " + valueArray[i]);
		}
		sec1.setLWM2MServerURI(valueArray[0]);
		serv1.setShortServerId(Integer.parseInt(valueArray[1]));
		serv1.setLifeTime(Integer.parseInt(valueArray[2]));

		return 0;
	}

	public int requestRegistration(SecurityObject sec1, ServerObject serv1) {

		Client client = Client.create();

		URI uri2 = UriBuilder.fromUri(sec1.getLWM2MServerURI()).queryParam("ep", endPointName)
				.queryParam("lt", serv1.getLifeTime()).build("segment", "value");

		System.out.println("\nRegistering with Server : " + uri2);

		String resources = "";

		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject().append("_id", false);
		fields.put("objectId", 1);
		fields.put("objectInstanceCount", 1);
		DBCursor curs = this.lwm2mResourceInfo.find(query, fields);
		int objectId, instanceCount;
		while (curs.hasNext()) {
			DBObject dbo = curs.next();
			objectId = Integer.parseInt(dbo.get("objectId").toString());
			instanceCount = Integer.parseInt(dbo.get("objectInstanceCount").toString());
			for (int i = 0; i < instanceCount; i++) {
				String temp;
				if (curs.hasNext()) {
					temp = "</" + objectId + "/" + i + ">, ";
				} else {
					temp = "</" + objectId + "/" + i + ">";
				}
				resources = resources + temp;
			}
		}
		// System.out.println("\nABCD" + resources);

		WebResource webResource = client.resource(uri2);

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, resources);

		registrationId = response.getEntity(String.class);

		System.out.println("Successfully Registered at Location = " + registrationId);
		String temp;
		String myString = "";
		BasicDBObject fields1 = new BasicDBObject().append("_id", false);
		DBCursor curs1 = this.lwm2mResourceInfo.find(query, fields1);
		while (curs1.hasNext()) {
			DBObject dbo = curs1.next();
			temp = dbo.toString();
			if (curs1.hasNext()) {
				myString = myString + temp + "_";
			} else {
				myString = myString + temp;
			}
		}
		Client client1 = Client.create();
		String uri_temp = sec1.getLWM2MServerURI();
		String newURI = uri_temp.replaceAll("rd", "ns");
		URI uri3 = UriBuilder.fromUri(newURI).build("segment", "value");

		// System.out.println("\nSecret Resource : " + uri3);
		WebResource webResource1 = client1.resource(uri3);

		myString = myString.toString();

		webResource1.type("text/plain").post(ClientResponse.class, myString);

		return 0;

	}

	public int updateRegistration(SecurityObject sec1, ServerObject serv1) {

		Client client = Client.create();

		URI uri2 = UriBuilder.fromUri(sec1.getLWM2MServerURI()).queryParam("registrationId", registrationId)
				.queryParam("lt", serv1.getLifeTime() + "000").build("segment", "value");

		System.out.println("\nUpdating Registration with Server : " + uri2);

		WebResource webResource = client.resource(uri2);

		ClientResponse response = webResource.type("application/json").put(ClientResponse.class);

		registrationId = response.getEntity(String.class);
		System.out.println("Registeration Updated Successfully at Location = " + registrationId);
		return 0;

	}

	public int deleteRegistration(SecurityObject sec1, ServerObject serv1) {

		Client client = Client.create();

		URI uri2 = UriBuilder.fromUri(sec1.getLWM2MServerURI()).queryParam("registrationId", registrationId)
				.build("segment", "value");

		System.out.println("\nDeleting Registration with Server : " + uri2);

		WebResource webResource = client.resource(uri2);

		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);

		registrationId = response.getEntity(String.class);
		System.out.println("Registeration Deleted Successfully from Location = " + registrationId);
		return 0;

	}

	public void pushObjectToDatabase() {
		Gson gson = new Gson();
		String myDevice = gson.toJson(this.dev0);
		String myLocation = gson.toJson(loc0);
		String mySensor = gson.toJson(sens0);

		// System.out.println("\n"+myJson);
		DBObject dbo = (DBObject) JSON.parse(myDevice);

		this.deviceInfo.remove(new BasicDBObject());
		this.deviceInfo.insert(dbo);

		dbo = (DBObject) JSON.parse(myLocation);
		this.locationInfo.remove(new BasicDBObject());
		this.locationInfo.insert(dbo);

		dbo = (DBObject) JSON.parse(mySensor);
		this.sensorInfo.remove(new BasicDBObject());
		this.sensorInfo.insert(dbo);
	}

	public DBCollection getCollection(int objectId) {

		switch (objectId) {
		case 3:
			return deviceInfo;
		case 6:
			return locationInfo;
		case 10:
			return PressureInfo;
		case 11:
			return UGSVehicleInfo;
		case 12:
			return WeaponInfo;
		case 13:
			return UGSPersonInfo;
		case 14:
			return InfraRedInfo;
		case 15:
			return VisualInfo;
		default:
			return null;
		}
	}
}
