package lwm2m.objects;

public class SecurityObject {

	private String LWM2MServerURI;
	private boolean BootStrapServer;
	private int ShortServerId;
	
	private String description[] = new String[3];
	
	public SecurityObject(){
		
		this.LWM2MServerURI = "";
		this.BootStrapServer = false;
		this.ShortServerId = -1;
	
		this.description[0]="ServerURI";
		this.description[0]="IsBootStrapServer";
		this.description[0]="ServerID";
	}
	public SecurityObject(String LWM2MServerURI, Boolean BootStrapServer, int ShortServerId){
		
		this.LWM2MServerURI = LWM2MServerURI;
		this.BootStrapServer = BootStrapServer;
		this.ShortServerId = ShortServerId;		
	}
		
	public String getLWM2MServerURI() {
		return LWM2MServerURI;
	}
	public void setLWM2MServerURI(String lWM2MServerURI) {
		LWM2MServerURI = lWM2MServerURI;
	}
	public boolean isBootStrapServer() {
		return BootStrapServer;
	}
	public void setBootStrapServer(boolean bootStrapServer) {
		BootStrapServer = bootStrapServer;
	}
	public int getShortServerId() {
		return ShortServerId;
	}
	public void setShortServerId(int shortServerId) {
		ShortServerId = shortServerId;
	}
}
