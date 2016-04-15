package lwm2m.downlinkServer;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import lwm2m.client.ClientA;
import lwm2m.objects.AccessControlObject;
import lwm2m.objects.DeviceObject;
import lwm2m.objects.LocationObject;
import lwm2m.objects.SensorObject;
import lwm2m.observe.NotifyObject;
import lwm2m.observe.NotifyResource;
import lwm2m.observe.WriteAttribute;

@Path("/lwm2m")
public class DownlinkServer {

	static Map<String, List<String>> map = new HashMap<String, List<String>>();

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello() throws UnknownHostException, JSONException {

		String temp1 = new String("Test Successful");
		return temp1;
	}

	// -----------------Discover---------------//
	@GET
	@Path("{objectId}/{objectInstanceId}/discover")
	@Produces(MediaType.TEXT_PLAIN)
	public String discoverRequest(@PathParam("objectId") int objectId,
			@PathParam("objectInstanceId") int objectInstanceId) throws UnknownHostException, JSONException {

		System.out.println("\n----------------Discover-------------");

		System.out.println("Discover Reuqest Received for: " + objectId + "/" + objectInstanceId);

		ClientA a1 = new ClientA();
		a1.connectDb1();

		BasicDBObject query = new BasicDBObject();
		query.put("objectId", objectId);
		BasicDBObject fields = new BasicDBObject().append("_id", false);
		fields.put("resourceCount", true);
		fields.put("pmin", true);
		fields.put("pmax", true);
		DBCursor curs = a1.lwm2mResourceInfo.find(query, fields);
		int resourceCount = 0, pmin = 0, pmax = 0;
		while (curs.hasNext()) {
			DBObject dbo = curs.next();
			resourceCount = Integer.parseInt(dbo.get("resourceCount").toString());
			pmin = Integer.parseInt(dbo.get("pmin").toString());
			pmax = Integer.parseInt(dbo.get("pmax").toString());
		}
		String temp1 = "";
		for (int i = 0; i < resourceCount; i++) {
			String temp;
			if (i != resourceCount - 1) {
				temp = "</" + objectId + "/" + objectInstanceId + "/" + i + ">, ";
			} else {
				temp = "</" + objectId + "/" + objectInstanceId + "/" + i + ">";
			}
			temp1 = temp1 + temp;
		}
		String jReply = "</" + objectId + "/" + objectInstanceId + ">, pmin=" + pmin + ", pmax=" + pmax + ", " + temp1;
		return jReply;
	}

	// -----------------Read---------------//

	@GET
	@Path("{objectId}/{objectInstanceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readObject(@PathParam("objectId") int objectId, @PathParam("objectInstanceId") int objectInstanceId)
			throws UnknownHostException, JSONException {

		System.out.println("\n----------------Read-------------");

		System.out.println("Read Reuqest Received for: " + objectId + "/" + objectInstanceId);

		ClientA a1 = new ClientA();
		a1.connectDb1();

		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject().append("_id", false);
		DBCursor curs = a1.getCollection(objectId).find(query, fields);
		String jReply = null;
		while (curs.hasNext()) {
			DBObject dbo = curs.next();
			jReply = dbo.toString();
		}
		return jReply;
	}

	@GET
	@Path("{objectId}/{objectInstanceId}/{resourceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readResource(@PathParam("objectId") int objectId, @PathParam("objectInstanceId") int objectInstanceId,
			@PathParam("resourceId") int resourceId) throws UnknownHostException, JSONException {

		System.out.println("\n----------------Read-------------");

		System.out.println("Read Reuqest Received for: " + objectId + "/" + objectInstanceId + "/" + resourceId);

		ClientA a1 = new ClientA();
		a1.connectDb1();

		String resource;
		switch (objectId) {
		case 3:
			resource = new DeviceObject().getResourceDescription(resourceId);
			break;
		case 6:
			resource = new LocationObject().getResourceDescription(resourceId);
			break;
		case 10:
			resource = new SensorObject().getResourceDescription(resourceId);
			break;
		default:
			resource = null;
			break;
		}
		String jReply = null;
		if (resource == null) {
			return "error";
		}
		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject(resource, true).append("_id", false);
		DBCursor curs = a1.getCollection(objectId).find(query, fields);
		while (curs.hasNext()) {
			DBObject dbo = curs.next();
			jReply = dbo.toString();
		}
		return jReply;
	}
	// -----------------Write---------------//

	@PUT
	@Path("/{objectId}/{objectInstanceId}/{resourceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRegistration(String jString) throws JSONException, UnknownHostException {

		System.out.println("\n----------------Write-------------");

		JSONObject j1 = new JSONObject(jString);
		int serv_id = Integer.parseInt(j1.get("value").toString());
		BasicDBObject query = new BasicDBObject();
		BasicDBObject document = new BasicDBObject().append("$set",
				new BasicDBObject().append("ShortServerId", serv_id));
		ClientA a1 = new ClientA();
		a1.connectDb1();
		a1.serverInfo.update(query, document);

		System.out.println("Value Changed to: " + serv_id);

		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}

	// -----------------WriteAttribute---------------//
	@PUT
	@Path("/{objectId}/{objectInstanceId}/{resourceId}/writeattribute/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response writeAttributeRequest(@PathParam("objectId") int objectId,
			@PathParam("objectInstanceId") int objectInstanceId, @PathParam("resourceId") int resourceId,
			@QueryParam("pmin") int pmin, @QueryParam("pmax") int pmax, @QueryParam("gt") int gt,
			@QueryParam("lt") int lt, @QueryParam("st") int st, @QueryParam("cancel") boolean cancel)
					throws JSONException, UnknownHostException {

		System.out.println("\n----------------Write Attribute-------------");
		WriteAttribute ob1 = new WriteAttribute();

		System.out.println("Write Attribute Request Received for: " + objectId + "/" + objectInstanceId + "/" + resourceId);

		ob1.setObjectId(objectId);
		ob1.setObjectInstanceId(objectInstanceId);
		ob1.setResourceId(resourceId);
		ob1.setPmin(pmin);
		ob1.setPmax(pmax);
		ob1.setGreaterThan(gt);
		ob1.setLessThan(lt);
		ob1.setStep(st);
		ob1.setCancel(cancel);

		ClientA a1 = new ClientA();
		try {
			a1.connectDb1();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String myObservation = gson.toJson(ob1);
		DBObject dbo = (DBObject) JSON.parse(myObservation);
		a1.observationInfo.remove(new BasicDBObject());
		a1.observationInfo.insert(dbo);
		
		System.out.println("Attributes Set Successfully");
		System.out.println("pmin: "+pmin);
		System.out.println("pmax: "+pmax);
		System.out.println("greater than: "+gt);
		System.out.println("less than: "+lt);
		System.out.println("step: "+st);
		System.out.println("cancel: "+cancel);



		String response = "Attributes set on : /" + objectId + "/" + objectInstanceId + "/" + resourceId;
		return Response.status(201).entity(response).build();
	}
	// -----------------WriteAttribute on Object---------------//
		@PUT
		@Path("/{objectId}/writeattributeObject/")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response writeAttributeRequestOnObject(@PathParam("objectId") int objectId,
				@QueryParam("pmin") int pmin, @QueryParam("pmax") int pmax, @QueryParam("cancel") boolean cancel)
						throws JSONException, UnknownHostException {

			System.out.println("\n----------------Write Attribute on Object-------------");
			System.out.println("Write Attribute Request Received for Object: " + objectId);

			ClientA a1 = new ClientA();
			try {
				a1.connectDb1();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			BasicDBObject query1 = new BasicDBObject();
			query1.put("objectId", objectId);

			BasicDBObject document1 = new BasicDBObject();
			document1.append("pmin", pmin);
			document1.append("pmax", pmax);
			document1.append("cancel",cancel);
			DBObject update = new BasicDBObject("$set", document1);

			ClientA a2 = new ClientA();
			a2.connectDb1();
			a2.lwm2mResourceInfo.update(query1, update);
		
			System.out.println("Attributes Set Successfully");
			System.out.println("pmin: "+pmin);
			System.out.println("pmax: "+pmax);
			System.out.println("cancel: "+cancel);
			String response = "Attributes set on Object : /" + objectId;
			return Response.status(201).entity(response).build();
		}

	// -----------------Observe on Resource---------------//
	@GET
	@Path("{objectId}/{objectInstanceId}/{resourceId}/observe")
	@Produces(MediaType.APPLICATION_JSON)
	public Response observeRequestOnResource(@PathParam("objectId") int objectId,
			@PathParam("objectInstanceId") int objectInstanceId, @PathParam("resourceId") int resourceId,
			@QueryParam("tokenNo") int tokenNo) throws UnknownHostException, JSONException {

		System.out.println("\n----------------Observation-------------");

		System.out.println("Observation Reuqest Received for Resource: " + objectId + "/" + objectInstanceId + "/" + resourceId);

		// ------------------New Thread Started -----------------------//
		Runnable r1 = new NotifyResource(objectId, objectInstanceId, resourceId, tokenNo);
		new Thread(r1).start();

		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}
	// -----------------Observe whole Object---------------//
	@GET
	@Path("{objectId}/observeObject")
	@Produces(MediaType.APPLICATION_JSON)
	public Response observeRequestOnObject(@PathParam("objectId") int objectId,
			@QueryParam("tokenNo") int tokenNo) throws UnknownHostException, JSONException {

		System.out.println("\n----------------Observation-------------");

		System.out.println("Observation Reuqest Received for Object: /" + objectId);

		// ------------------New Thread Started -----------------------//
		Runnable r1 = new NotifyObject(objectId, tokenNo);
		new Thread(r1).start();

		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}


	// --------------------------------- Cancel Observation
	// -------------------//
	@POST
	@Path("{objectId}/{objectInstanceId}/{resourceId}/cancel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelRequest(@PathParam("objectId") int objectId,
			@PathParam("objectInstanceId") int objectInstanceId, @PathParam("resourceId") int resourceId)
					throws JSONException, UnknownHostException, JSONException {

		System.out.println("\n----------------Cancel-------------");

		System.out.println("Cancel Reuqest Received for Resource: " + objectId + "/" + objectInstanceId + "/" + resourceId);

		BasicDBObject query1 = new BasicDBObject();
		query1.put("objectId", objectId);
		query1.put("objectInstanceId", objectInstanceId);
		query1.put("resourceId", resourceId);
		BasicDBObject document1 = new BasicDBObject().append("$set", new BasicDBObject().append("cancel", true));
		ClientA a2 = new ClientA();
		a2.connectDb1();
		a2.observationInfo.update(query1, document1);
		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}
	// --------------------------------- Cancel Observation on Object
	// -------------------//
	@POST
	@Path("{objectId}/cancelObjectObservation")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelRequestOnObject(@PathParam("objectId") int objectId)
					throws JSONException, UnknownHostException, JSONException {

		System.out.println("\n----------------Cancel-------------");

		System.out.println("Cancel Reuqest Received for Object: /" + objectId);

		BasicDBObject query1 = new BasicDBObject();
		query1.put("objectId", objectId);

		BasicDBObject document1 = new BasicDBObject().append("$set", new BasicDBObject().append("cancel", true));
		ClientA a2 = new ClientA();
		a2.connectDb1();
		a2.lwm2mResourceInfo.update(query1, document1);
		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}

	// -----------------Execute---------------//
	@POST
	@Path("/{objectId}/{objectInstanceId}/{resourceId}/execute")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response executeRequest(String arguments, @PathParam("objectId") int objectId,
			@PathParam("objectInstanceId") int objectInstanceId, @PathParam("resourceId") int resourceId)
					throws JSONException, UnknownHostException {

		System.out.println("\n----------------Execute-------------");

		String response = "Execute Request On /" + objectId + "/" + objectInstanceId + "/" + resourceId;
		System.out.println("Execute: " + response);
		BasicDBObject query = new BasicDBObject();
		BasicDBObject document = new BasicDBObject().append("$set", new BasicDBObject().append("stopSensing", true));
		ClientA a1 = new ClientA();
		a1.connectDb1();
		a1.sensorInfo.update(query, document);
		System.out.println("Stopping all Sensing Activity....");

		JSONObject status = new JSONObject("{ \"status\" : \"changed\" }");
		return Response.status(201).entity(status).build();
	}

	// ----------------------------------create-------------------//
	@POST
	@Path("{objectId}/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateRequest(@PathParam("objectId") int objectId, @QueryParam("ACLobjectId") int ACLobjectId,
			@QueryParam("objectInstanceId") int objectInstanceId, @QueryParam("ACL") int ACL,
			@QueryParam("owner") int owner) throws JSONException, UnknownHostException, JSONException {

		System.out.println("\n----------------Create-------------");

		System.out.println("Create Reuqest Received for: " + objectId);

		BasicDBObject query2 = new BasicDBObject();
		query2.put("objectId", objectId);
		BasicDBObject document2 = new BasicDBObject().append("_id", false);
		document2.put("objectInstanceCount", true);

		ClientA a2 = new ClientA();
		a2.connectDb1();
		DBObject doc = a2.lwm2mResourceInfo.findOne(query2, document2);
		String temp1 = doc.get("objectInstanceCount").toString();
		int b = Integer.parseInt(temp1);
		AccessControlObject ACL1 = new AccessControlObject();
		int c = b + 1;

		ACL1.setObjectId(objectId);
		ACL1.setACLobjectId(ACLobjectId);
		ACL1.setObjectInstanceId(objectInstanceId);
		ACL1.setACL(ACL);
		ACL1.setOwner(owner);
		ACL1.pushToDB(a2);

		BasicDBObject query3 = new BasicDBObject();
		query3.put("objectId", objectId);
		BasicDBObject document3 = new BasicDBObject().append("$set",
				new BasicDBObject().append("objectInstanceCount", c));
		a2.lwm2mResourceInfo.update(query3, document3);

	//	System.out.println("max instance id for object " + objectId + " was " + b);
	//	System.out.println("new instance created at :" + c);

		JSONObject status = new JSONObject("{ \"status\" : \"created\" }");
		return Response.status(201).entity(status).build();
	}

	// ----------------create ended----------------------//

	// -----------------Delete---------------//

	@DELETE
	@Path("/{objectId}/{ACLobjectId}/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRequest(@PathParam("objectId") int objectId, @PathParam("ACLobjectId") int ACLobjectId)
			throws JSONException, UnknownHostException {
		BasicDBObject query4 = new BasicDBObject();
		query4.put("objectId", objectId);
		BasicDBObject document4 = new BasicDBObject().append("_id", false);
		document4.put("objectInstanceCount", true);
		
		System.out.println("\n----------------Delete-------------");

		ClientA a2 = new ClientA();
		a2.connectDb1();
		DBObject doc = a2.lwm2mResourceInfo.findOne(query4, document4);
		String temp1 = doc.get("objectInstanceCount").toString();
		int b = Integer.parseInt(temp1);
		AccessControlObject ACL1 = new AccessControlObject();
		int c =b-1;
		
		ACL1.setObjectId(objectId);
		ACL1.setACLobjectId(ACLobjectId);
		ACL1.deleteFromDB(a2);
		
		
		BasicDBObject query5 = new BasicDBObject();
		query5.put("objectId", objectId);
		BasicDBObject document5 = new BasicDBObject().append("$set", new BasicDBObject().append("objectInstanceCount", c));
		a2.lwm2mResourceInfo.update(query5, document5);
		
		
		System.out.println("deleted the object having objectid " +objectId+ " and instanceid " +b);
		System.out.println("new instance count :"+c);

		JSONObject status = new JSONObject("{ \"status\" : \"deleted \"  }");
		
		System.out.println(status.toString());
		return Response.status(201).entity(status.toString()).build();

	}

}
