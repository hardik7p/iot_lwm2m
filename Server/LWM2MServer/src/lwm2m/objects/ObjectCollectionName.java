package lwm2m.objects;

public class ObjectCollectionName {
	
	private int objectId;
	private String collectionName;
	
	public ObjectCollectionName(int objectId, String collectionName){
		this.objectId=objectId;
		this.collectionName=collectionName;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	
}
