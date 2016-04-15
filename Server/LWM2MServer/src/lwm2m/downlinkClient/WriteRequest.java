package lwm2m.downlinkClient;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WriteRequest{

    private final LWM2MPath path;
    
    public LWM2MPath getPath() {
        return this.path;
    }
    public WriteRequest(int objectId, int objectInstanceId) {
        this(new LWM2MPath(objectId, objectInstanceId));
    }
    public WriteRequest(int objectId, int objectInstanceId, int resourceId) {
        this(new LWM2MPath(objectId, objectInstanceId, resourceId));
    }
       private WriteRequest(LWM2MPath target) {
    	this.path=target;
    }

    @Override
    public final String toString() {
        return String.format("WriteRequest [%s]", getPath());
    }

	public int sendWriteRequest() throws URISyntaxException {
		String payload;

		if(path.isObject()){
			System.out.println("Must be an Object Instance or a Resource");
//			return -1;
		}
		else{
			payload=path.toString();
		}
		payload=path.toString();

		Client client = Client.create();
		String output = new String();

		String clientURI="http://localhost:8017/LWM2MClient/lwm2m";
		String uri_temp=clientURI+payload;
		
		URI uri1 = new URI(uri_temp);

		System.out.println("\nWrite to Client: " + uri1);

		WebResource webResource = client.resource(uri1);
		
		String resource = "{\"value\":\"555\"}";

		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, resource);

		output = response.getEntity(String.class);
		System.out.println("Write Response : " + output);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Error while Write to Client : HTTP error code : "	+ response.getStatus());
		}
		
		return 0;
	}

}