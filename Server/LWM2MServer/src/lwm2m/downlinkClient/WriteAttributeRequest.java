package lwm2m.downlinkClient;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WriteAttributeRequest {

	private int objectId;	
	private int objectInstanceId;
	private int resourceId;
	private int minimumPeriod;
	private int maximumPeriod;
	private int greaterThan;
	private int lessThan;
	private int step;
	private boolean cancel;
	
	public WriteAttributeRequest(int objectId, int objectInstanceId, int resourceId, int minimumPeriod){
		this.objectId=objectId;
		this.objectInstanceId=objectInstanceId;
		this.resourceId=resourceId;
		this.minimumPeriod=minimumPeriod;
		this.maximumPeriod=10;
		this.greaterThan=5;
		this.lessThan=0;
		this.step=0;
		this.cancel=false;
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
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public int getMinimumPeriod() {
		return minimumPeriod;
	}
	public void setMinimumPeriod(int minimumPeriod) {
		this.minimumPeriod = minimumPeriod;
	}
	public int getMaximumPeriod() {
		return maximumPeriod;
	}
	public void setMaximumPeriod(int maximumPeriod) {
		this.maximumPeriod = maximumPeriod;
	}
	public int getGreaterThan() {
		return greaterThan;
	}
	public void setGreaterThan(int greaterThan) {
		this.greaterThan = greaterThan;
	}
	public int getLessThan() {
		return lessThan;
	}
	public void setLessThan(int lessThan) {
		this.lessThan = lessThan;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public boolean getCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	void sendWriteAttributeRequest() throws URISyntaxException{

		Client client = Client.create();
		String output = new String();

		String payload="/"+objectId+"/"+objectInstanceId+"/"+resourceId+"/writeattribute"+"?pmin="+minimumPeriod+
				"&pmax="+maximumPeriod+"&gt="+greaterThan+"&lt="+lessThan+"&st="+step+"&cancel="+cancel;
		String clientURI="http://localhost:8017/LWM2MClient/lwm2m";
		String uri_temp=clientURI+payload;
		
		URI uri1 = new URI(uri_temp);

		System.out.println("\nWrite Attribute Request to Client: " + uri1);

		WebResource webResource = client.resource(uri1);

		ClientResponse response = webResource.type("application/json").put(ClientResponse.class);

		output = response.getEntity(String.class);
		System.out.println("Write Attribute Response : " + output);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Error in Observe Request to Client : HTTP error code : "	+ response.getStatus());
		}
	}
}
	