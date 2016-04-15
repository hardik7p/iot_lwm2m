package lwm2m.observe;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lwm2m.client.ClientA;
import lwm2m.objects.DeviceObject;
import lwm2m.objects.LocationObject;
import lwm2m.objects.SensorObject;

public class NotifyObject implements Runnable {

	int objectId;
	int objectInstanceId;
	int resourceId;
	int tokenNo;

	public NotifyObject(int objectId, int tokenNo) {
		this.objectId = objectId;
		this.tokenNo = tokenNo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		System.out.println("Notify Initiated on Object");

		{
			ClientA a1 = new ClientA();
			try {
				a1.connectDb1();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}

			BasicDBObject query = new BasicDBObject();
			query.put("objectId", objectId);

			BasicDBObject fields = new BasicDBObject().append("_id", false);
			fields.put("pmin", true);
			fields.put("pmax", true);
			fields.put("cancel", true);
			DBCursor curs = a1.lwm2mResourceInfo.find(query, fields);

			int pmin = 0, pmax = 0;
			boolean cancel_field = true;
			while (curs.hasNext()) {
				DBObject dbo = curs.next();
				pmin = Integer.parseInt(dbo.get("pmin").toString());
				pmax = Integer.parseInt(dbo.get("pmax").toString());
				cancel_field = Boolean.parseBoolean(dbo.get("pmax").toString());
			}

			System.out.println("Notify Initiated for every " + pmin + " seconds with tokenNo: " + tokenNo);

			while (!cancel_field) {
				try {
					
					BasicDBObject queryForValue = new BasicDBObject();
					BasicDBObject fieldsForValue = new BasicDBObject().append("_id", false);

					DBCursor cursorForValue = a1.getCollection(objectId).find(queryForValue, fieldsForValue);
					String value = "";
					while (cursorForValue.hasNext()) {
						DBObject dbo = cursorForValue.next();
						value = dbo.toString();
					}
					System.out.println("Notifying the value: " + value);

					// ---------------------------Notify Started--------------------------//

					String new_uri = a1.sec0.getLWM2MServerURI().replaceAll("bs", "");					
					String s1 = URLEncoder.encode(value);
					new_uri = new_uri + "notify?tokenNo="+tokenNo+"&value="+s1;

					System.out.println("uri for notify "+new_uri);
					
					DefaultHttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(new_uri);

					client.execute(get);

					// ---------------------------Notify Ended--------------------------//

					// ----------check for the cancel flag---------//
		        	 BasicDBObject fieldsForCancelFlag = new BasicDBObject().append("_id", false);
		 			fieldsForCancelFlag.put("cancel", true);
		 			
					DBCursor cursorForCancelFlag = a1.lwm2mResourceInfo.find(query, fieldsForCancelFlag);
					while (cursorForCancelFlag.hasNext()) {
						DBObject dbo = cursorForCancelFlag.next();
						cancel_field = Boolean.parseBoolean(dbo.get("cancel").toString());
					}

					Thread.sleep(pmin * 1000);
			//			break;

				} catch (InterruptedException e) {
					System.out.println("going into exception");
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Cancel Request Received: Notify Thread Ended.");
	}

}
