package lwm2m.observe;

public class WriteAttribute {

	private int objectId;
	private int objectInstanceId;
	private int resourceId;
	private int pmin;
	private int pmax;
	private int greaterThan;
	private int lessThan;
	private int step;
	private boolean cancel;
	
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
	public int getPmin() {
		return pmin;
	}
	public void setPmin(int minimumPeriod) {
		this.pmin = minimumPeriod;
	}
	public int getPmax() {
		return pmax;
	}
	public void setPmax(int maximumPeriod) {
		this.pmax = maximumPeriod;
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
}
