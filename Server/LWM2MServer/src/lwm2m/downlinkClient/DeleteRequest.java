package lwm2m.downlinkClient;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DeleteRequest {
	private int objectId;
	private int ACLobjectId;

	public DeleteRequest(){
		this.objectId=10;
		this.ACLobjectId=10;
	}
	
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getACLObjectId() {
		return ACLobjectId;
	}
	public void setACLObjectId(int ACLobjectId) {
		this.ACLobjectId = ACLobjectId;
	}
	
	
	void sendDeleteRequest() throws URISyntaxException{

		Client client = Client.create();
		String output = new String();

		String payload="/"+objectId+"/"+ACLobjectId+"/delete";
		String clientURI="http://localhost:8017/LWM2MClient/lwm2m";
		String uri_temp=clientURI+payload;
		
		URI uri1 = new URI(uri_temp);

		System.out.println("\nDelete Request to Client: " + uri1);

		WebResource webResource = client.resource(uri1);
		
		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Delete Response : " + output);
		
		if (response.getStatus() == 201) {
		} else {
			throw new RuntimeException("Error while Discovering : HTTP error code : " + response.getStatus());
		}
	}
}