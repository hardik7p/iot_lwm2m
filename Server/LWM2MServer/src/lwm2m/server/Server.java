package lwm2m.server;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import lwm2m.functions.BootStrapClient;
import lwm2m.functions.DeleteClientRegistration;
import lwm2m.functions.RegisterClient;
import lwm2m.functions.UpdateClientRegistration;

@Path("/lwm2m")
public class Server {

	public static MongoClient mongo = null;
	public static DB db;
	public static DBCollection bootStrapInfo;
	public static DBCollection registrationInfo;
	public static DBCollection server_lwm2mResourceInfo;

	public static void connectDb1() throws UnknownHostException {

//		if (mongo == null) {
//			mongo = new MongoClient("localhost", 27017);
//			db = mongo.getDB("myDatabase");

		String textUri = "mongodb://cmpe273:cmpe273@ds047672.mongolab.com:47672/cmpe273";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
			bootStrapInfo = db.getCollection("bootStrapInfo");
			registrationInfo = db.getCollection("registrationInfo");
			server_lwm2mResourceInfo = db.getCollection("server_lwm2mResourceInfo");

//		}
	}

	static Map<String, List<String>> map = new HashMap<String, List<String>>();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello() throws UnknownHostException, JSONException {

		String temp1 = new String("Hello");
		return temp1;
	}

	@POST
	@Path("/bs")
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String bootStrap(@QueryParam("ep") String endPointName) throws JSONException, UnknownHostException {

		System.out.println("---------------------------------------------");
		connectDb1();

		System.out.println("BootStraping Client : EndPointName = " + endPointName);
		BootStrapClient bs = new BootStrapClient();

		String temp1 = new String();
		temp1 = bs.bootStrapInDB(endPointName).replace("\\", "");

		return temp1;
	}

	@POST
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(String myParameters, @QueryParam("ep") String endPointName,
			@QueryParam("lt") String lifeTime) throws JSONException, UnknownHostException {

		connectDb1();

		System.out.println("Registering Client : EndPointName = " + endPointName + " LifeTime = " + lifeTime);

		System.out.println("\nRegistration Payload Received: " + myParameters);

		RegisterClient rd = new RegisterClient();

		String registrationId = new String();
		registrationId = rd.registerInDB(endPointName, lifeTime);

		return Response.status(201).entity(registrationId).build();
	}

	@POST
	@Path("/ns")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response registerNS(String myParameters) throws JSONException, UnknownHostException {

		connectDb1();
		// System.out.println("\nSecret" + myParameters);

		String[] str1 = myParameters.split("_");

		for (int i = 0; i < str1.length; i++) {

			DBObject dbo = (DBObject) JSON.parse(str1[i]);
			if (i == 0) {
				server_lwm2mResourceInfo.remove(new BasicDBObject());
			}
			server_lwm2mResourceInfo.insert(dbo);
		}
		String registrationId = "hello";

		return Response.status(201).entity(registrationId).build();
	}

	@PUT
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRegistration(@QueryParam("registrationId") String registrationId,
			@QueryParam("lt") String lifeTime) throws JSONException, UnknownHostException {

		connectDb1();

		UpdateClientRegistration ud = new UpdateClientRegistration();

		System.out.println(
				"Updating Client Registration : Registration Id = " + registrationId + " LifeTime = " + lifeTime);

		ud.updateRegistrationInDB(registrationId, lifeTime);

		return Response.status(201).entity(registrationId).build();

	}

	@DELETE
	@Path("/rd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRegistration(@QueryParam("registrationId") String registrationId)
			throws JSONException, UnknownHostException {

		connectDb1();

		DeleteClientRegistration dd = new DeleteClientRegistration();

		System.out.println("Deleting Client Registration : Registration Id = " + registrationId);

		dd.deleteRegistrationInDB(registrationId);

		return Response.status(201).entity(registrationId).build();

	}

	@GET
	@Path("/notify")
	// @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String notify(@QueryParam("tokenNo") String tokenNo, @QueryParam("objectId") int objectId,
			@QueryParam("value") String value) throws JSONException, UnknownHostException, InterruptedException {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		System.out.println("Notification Received: tokenNo: " + randomInt + " value: " + value);

		System.out.println("Performing Write Attribute to Nearby Servers");

		Thread.sleep(2000);

		System.out.println("Performing Observe Request to Nearby UGS-V Sensors");
		
		Thread.sleep(2000);

		System.out.println("Performing Observe Request to Nearby Pressure Sensors");
		System.out.println("--------------------------------------------------");


		return "OK";
	}
}
