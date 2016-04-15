package lwm2m.downlinkClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DownlinkClient {

	static final String endPointName = "6d26bbc3-a66a-455e-b089-3bb2f8ac915e"; // Pre
																				// Installed
																				// EndPointName
																				// UUID.randomUUID()
	static String registrationId; // Fetched from server during bootstrap

	public static void main(String[] args) throws URISyntaxException, InterruptedException {

		// ---------------Discover---------------------//
		Client client = Client.create();
		String output = new String();

		URI uri1 = new URI("http://localhost:8017/LWM2MClient/lwm2m/3/0/discover");

		System.out.println("\nDiscover request : " + uri1);

		WebResource webResource = client.resource(uri1);

		ClientResponse response = webResource.type("text/plain").get(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Successfully Discovered : " + output);

		if (response.getStatus() == 200) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
		
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
/*
		// ---------------Read---------------------//

		ReadRequest r1 = new ReadRequest(10, 1, 2);
		ReadRequest r2 = new ReadRequest(6, 1);

		r1.sendReadRequest();
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		r2.sendReadRequest();

		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ---------------- Write Request --------------//
		new WriteRequest(1, 1, 0).sendWriteRequest();

		// ---------------Execute--------------//
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Client client1 = Client.create();
		String output1 = new String();

		URI uri11 = new URI("http://localhost:8017/LWM2MClient/lwm2m/10/0/5/execute");

		System.out.println("\nExecute request : " + uri11);

		WebResource webResource1 = client1.resource(uri11);

		ClientResponse response1 = webResource1.type("application/json").post(ClientResponse.class);

		output1 = response1.getEntity(String.class);
		System.out.println("Successfully Executed : " + output1);

		if (response1.getStatus() == 201) {
		} else {
			throw new RuntimeException("Error while Executing : HTTP error code : " + response.getStatus());
		}
		
		// ---------------create---------------------//

		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Thread.sleep(2000);

		CreateRequest cr1 = new CreateRequest();
		cr1.sendCreateRequest();

		// ---------------Create Completed---------------------//
		// ---------------Delete---------------------//
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	Thread.sleep(2000);
		
		DeleteRequest dr1 = new DeleteRequest();
		dr1.sendDeleteRequest();

		// ---------------Delete completed---------------------//


		// ------------------- Write Attribute --------------------//
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	Thread.sleep(5000);


		WriteAttributeRequest ob1 = new WriteAttributeRequest(10, 0, 0, 5); // resource:/10/0/0
																			// pmin=5
																			// seconds
		ob1.sendWriteAttributeRequest();
		
		
			// ------------------- Observation Request ------------------//
		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	Thread.sleep(2000);

		URI uri_observe = new URI("http://localhost:8017/LWM2MClient/lwm2m/10/0/0/observe?tokenNo=777");

		System.out.println("\nObservation Request at uri : " + uri_observe);

		Client client_observe = Client.create();
		WebResource webresource_observe = client_observe.resource(uri_observe);

		ClientResponse resp_observe = webresource_observe.type("application/json").get(ClientResponse.class);

		String output_observe = resp_observe.getEntity(String.class);
		System.out.println("Observe Request reply : " + output_observe);

		if (response.getStatus() == 201) {
			System.out.println("Observe Testing Successful");
		} else {
			throw new RuntimeException("Error while Observing : HTTP error code : " + response.getStatus());
		}

		
		// ------------------- Cancel Observation ------------------//

		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	Thread.sleep(10000);

		URI uri2 = new URI("http://localhost:8017/LWM2MClient/lwm2m/10/0/0/cancel");

		System.out.println("\nCancel request sent at uri : " + uri2);

		WebResource webResource2 = client.resource(uri2);

		ClientResponse response2 = webResource2.type("application/json").post(ClientResponse.class);

		output = response2.getEntity(String.class);
		System.out.println("Cancel Response : " + output);

		if (response.getStatus() == 201) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
*/
		// ------------------- Write Attribute Request On Object ------------------//

		
		String payload="/"+15+"/writeattributeObject"+"?pmin="+7+"&pmax="+10+"&cancel="+false;
		String clientURI="http://localhost:8017/LWM2MClient/lwm2m";
		String uri_temp=clientURI+payload;
		
		URI uri11 = new URI(uri_temp);

		System.out.println("\nWrite Attribute Object Request to Client: " + uri11);

		webResource = client.resource(uri11);

		response = webResource.type("application/json").put(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Write Attribute Object Response : " + output);
		
		// ------------------- Observation Request On Object ------------------//

		URI uri_observe = new URI("http://localhost:8017/LWM2MClient/lwm2m/15/observeObject?tokenNo=888");
		System.out.println("\nObservation Object Request at sent  uri : " + uri_observe);
		webResource = client.resource(uri_observe);
		response = webResource.type("application/json").get(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Observe Object Request reply : " + output);

		if (response.getStatus() == 201) {
			System.out.println("Observe Object Successful");
		} else {
			throw new RuntimeException("Error while Observing : HTTP error code : " + response.getStatus());
		}
		
		// ------------------- Cancel Observation ------------------//

		System.out.println("Continue?");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		URI uri2 = new URI("http://localhost:8017/LWM2MClient/lwm2m/15/cancelObjectObservation");
		System.out.println("\nCancel Object Observation Request sent at uri : " + uri2);
		webResource = client.resource(uri2);
		response = webResource.type("application/json").post(ClientResponse.class);
		output = response.getEntity(String.class);
		System.out.println("Cancel Response : " + output);

		if (response.getStatus() == 201) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
	}

}
