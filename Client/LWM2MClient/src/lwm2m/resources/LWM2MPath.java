package lwm2m.resources;

public class LWM2MPath {

    private final int objectId;
    private final Integer objectInstanceId;
    private final Integer resourceId;

    public LWM2MPath(int objectId) {
        this.objectId = objectId;
        this.objectInstanceId = null;
        this.resourceId = null;
    }
    public LWM2MPath(int objectId, int objectInstanceId) {
        this.objectId = objectId;
        this.objectInstanceId = objectInstanceId;
        this.resourceId = null;
    }
    public LWM2MPath(int objectId, int objectInstanceId, int resourceId) {
        this.objectId = objectId;
        this.objectInstanceId = objectInstanceId;
        this.resourceId = resourceId;
    }

    public LWM2MPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        String[] p = path.split("/");
        if (p.length < 1 || p.length > 3) {
            throw new IllegalArgumentException("Invalid length for path: " + path);
        }
        try {
            this.objectId = Integer.valueOf(p[0]);
            this.objectInstanceId = (p.length >= 2) ? Integer.valueOf(p[1]) : null;
            this.resourceId = (p.length == 3) ? Integer.valueOf(p[2]) : null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid elements in path: " + path, e);
        }
    }

    public int getObjectId() {
        return objectId;
    }

    public Integer getObjectInstanceId() {
        return objectInstanceId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public boolean isObject() {
        return objectInstanceId == null && resourceId == null;
    }

    public boolean isObjectInstance() {
        return objectInstanceId != null && resourceId == null;
    }

    public boolean isResource() {
        return objectInstanceId != null && resourceId != null;
    }

    /**
     * The string representation of the path: /{Object ID}/{ObjectInstance ID}/{Resource ID}
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("/");
        b.append(getObjectId());
        if (getObjectInstanceId() != null) {
            b.append("/").append(getObjectInstanceId());
            if (getResourceId() != null) {
                b.append("/").append(getResourceId());
            }
        }
        return b.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LWM2MPath other = (LWM2MPath) obj;
        if (objectId != other.objectId) {
            return false;
        }
        if (objectInstanceId == null) {
            if (other.objectInstanceId != null) {
                return false;
            }
        } else if (!objectInstanceId.equals(other.objectInstanceId)) {
            return false;
        }
        if (resourceId == null) {
            if (other.resourceId != null) {
                return false;
            }
        } else if (!resourceId.equals(other.resourceId)) {
            return false;
        }
        return true;
    }

}