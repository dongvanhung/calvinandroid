package com.rent.location;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.rent.MapPoint;
import com.rent.handler.GoogleJsonLocationHandler;
import com.rent.handler.TimerHandler;
import com.rent.listener.GoogleJSonLocationListener;
import com.rent.listener.MyLocationListener;
import com.rent.listener.TimerListener;
import com.rent.thread.GoogleJSonLocationThread;
import com.rent.thread.TimerThread;

public class MyLocationManager implements GoogleJSonLocationListener,
		LocationListener {

	private static final int OVERTIME = 120;
	private static String TAG = "MyLocationManager2";
	private static MyLocationManager instance;
	private static Context mContext;
	private boolean mCellLocated;
	private Set<MyLocationListener> mListeners;
	private LocationManager mLocationManager;
	private boolean mProviderLocated;
	TimerThread mTimer;

	private MyLocationManager() {
		HashSet localHashSet = new HashSet();
		this.mListeners = localHashSet;
		this.mProviderLocated = true;
		this.mCellLocated = true;
	}

	private JSONObject createJSONObject(String paramString,
			JSONArray paramJSONArray) {
		JSONObject localJSONObject1 = new JSONObject();
		try {
			JSONObject localJSONObject2 = localJSONObject1.put("version",
					"1.1.0");
			JSONObject localJSONObject3 = localJSONObject1.put("host",
					"maps.google.com");
			JSONObject localJSONObject4 = localJSONObject1.put(paramString,
					paramJSONArray);
			return localJSONObject1;
		} catch (JSONException localJSONException) {
			Log.e(TAG, "call JSONObject's put failed", localJSONException);
		}
		return null;
	}

	private JSONArray getCellJSon() {
		JSONObject localJSONObject1 = new JSONObject();
		String str3 = "";
		String str4 = "";
		int k = 0;
		int j = 0;
		JSONArray localJSONArray1 = new JSONArray();
		TelephonyManager tm = (TelephonyManager) mContext
				.getSystemService("phone");
		try {
			int i = tm.getNetworkType();
			if ((i == 4) || (i == 7)) {
				if ((CdmaCellLocation) tm.getCellLocation() == null) {
					return null;
				}
				String str1 = tm.getSimOperator().substring(0, 3);
				if (!"460".equals(str1))
					return null;
			}
			String str2 = tm.getNetworkOperator();
			str3 = str2.substring(0, 3);
			str4 = str2.substring(3);
			GsmCellLocation localGsmCellLocation = (GsmCellLocation) tm
					.getCellLocation();
			k = localGsmCellLocation.getCid();
			j = localGsmCellLocation.getLac();

			JSONObject localJSONObject2 = localJSONObject1.put("cell_id", k);
			JSONObject localJSONObject3 = localJSONObject1.put(
					"location_area_code", j);
			JSONObject localJSONObject4 = localJSONObject1.put(
					"mobile_country_code", str3);
			JSONObject localJSONObject5 = localJSONObject1.put(
					"mobile_network_code", str4);
			localJSONArray1.put(localJSONObject1);
			Iterator localIterator = tm.getNeighboringCellInfo().iterator();
			NeighboringCellInfo nc = (NeighboringCellInfo) localIterator.next();
		} catch (JSONException localObject2) {

		}
		return localJSONArray1;
	}

	public static MyLocationManager getInstance(Context paramContext) {
		if (instance == null)
			instance = new MyLocationManager();
		mContext = paramContext;
		return instance;
	}

	private JSONArray getWIFIJSon() {
		JSONArray localJSONArray1 = new JSONArray();
		try {

			WifiManager wm = (WifiManager) mContext.getSystemService("wifi");
			if (!wm.isWifiEnabled()) {
				localJSONArray1 = null;
			} else {
				WifiInfo localWifiInfo = wm.getConnectionInfo();
				String str1 = localWifiInfo.getMacAddress();
				String str2 = localWifiInfo.getSSID();
				JSONObject localJSONObject1 = new JSONObject();
				JSONObject localJSONObject2 = localJSONObject1.put(
						"mac_address", str1);
				JSONObject localJSONObject3 = localJSONObject1
						.put("ssid", str2);
				JSONArray localJSONArray2 = localJSONArray1
						.put(localJSONObject1);
			}
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return localJSONArray1;
	}

	void fireListeners(double paramDouble1, double paramDouble2,
			boolean paramBoolean) {
		Iterator localIterator = this.mListeners.iterator();
		if (localIterator.hasNext()) {
			MyLocationListener localMyLocationListener = (MyLocationListener) localIterator
					.next();
			double d1 = paramDouble1;
			double d2 = paramDouble2;
			boolean bool = paramBoolean;
			localMyLocationListener.onLocationChanged(d1, d2, bool);
		}
	}

	public void locationObtained(double paramDouble1, double paramDouble2,
			double paramDouble3, boolean paramBoolean, int paramInt) {
		if (paramInt == 0)
			if (!this.mProviderLocated)
				return;

		if ((paramBoolean) && (paramDouble3 < 1000.0D)) {
			MyLocationManager localMyLocationManager1 = this;
			double d1 = paramDouble1;
			double d2 = paramDouble2;
			boolean bool1 = paramBoolean;
			localMyLocationManager1.fireListeners(d1, d2, bool1);
		} else {
			if ((paramInt == 4.9E-324D) || (paramInt == 0)) { //TODO: paramInt == -1 is not sure 
				if (paramBoolean) {
					MyLocationManager localMyLocationManager2 = this;
					double d3 = paramDouble1;
					double d4 = paramDouble2;
					boolean bool2 = paramBoolean;
					localMyLocationManager2.fireListeners(d3, d4, bool2);
				}
				stopLocation();
			}
		}
	}

	public void onLocationChanged(Location paramLocation) {
		double d1 = paramLocation.getLatitude();
		double d2 = paramLocation.getLongitude();
		MyLocationManager localMyLocationManager = this;
		int i = 1;
		localMyLocationManager.locationObtained(d1, d2, 0.0D, true, i);
	}

	public void onProviderDisabled(String paramString) {
	}

	public void onProviderEnabled(String paramString) {
	}

	public void onStatusChanged(String paramString, int paramInt,
			Bundle paramBundle) {
	}

	public void registerListener(MyLocationListener paramMyLocationListener) {
		if (this.mListeners.contains(paramMyLocationListener)) {
			return;
		} else {
			if ((this.mProviderLocated) && (this.mCellLocated)) {
				boolean bool = this.mListeners.add(paramMyLocationListener);
				startCellLocation();
				startProviderLocation();
				MyLocationManager1 local1 = new MyLocationManager1();
				TimerHandler localTimerHandler = new TimerHandler(local1);
				TimerThread localTimerThread = new TimerThread(120,
						localTimerHandler);
				this.mTimer = localTimerThread;
				this.mTimer.start();
			}
		}
	}

	final class MyLocationManager1 implements TimerListener {
		public void timeOut() {
			MapPoint localMapPoint = new MapPoint();
			double d1 = Double.parseDouble("39.920591");
			localMapPoint.lat = d1;
			double d2 = Double.parseDouble("116.432791");
			localMapPoint.lon = d2;
			/*
			 * if ((!MyLocationManager.access$000(this.this$0)) &&
			 * (!MyLocationManager.access$100(this.this$0))) //TODO:: {
			 */
			double d3 = localMapPoint.lat;
			double d4 = localMapPoint.lon;
			locationObtained(d3, d4, 0.0D, true, -1);
			return;
			// }
			// stopLocation();
		}
	}

	void startCellLocation() {
		JSONArray localJSONArray = null;
		this.mCellLocated = false;
		Object localObject = getCellJSon();
		if (localObject == null) {
			MyLocationManager localMyLocationManager = this;
			double d1 = 0.0D;
			double d2 = 0.0D;
			int i = 0;
			localMyLocationManager.locationObtained(0.0D, d1, d2, false, i);
		} else {
			localObject = createJSONObject("cell_towers",
					(JSONArray) localObject);
			localJSONArray = getWIFIJSon();
			if (localJSONArray != null)
				;
			try {
				JSONObject localJSONObject = ((JSONObject) localObject).put(
						"wifi_towers", localJSONArray);
				GoogleJsonLocationHandler localGoogleJsonLocationHandler = new GoogleJsonLocationHandler(
						this);
				new GoogleJSonLocationThread(localGoogleJsonLocationHandler,
						(JSONObject) localObject).start();
			} catch (JSONException localJSONException) {
				localJSONException.printStackTrace();
			}
		}
	}

	void startProviderLocation() {
		this.mProviderLocated = false;
		LocationManager localLocationManager1 = (LocationManager) mContext
				.getSystemService("location");
		this.mLocationManager = localLocationManager1;
		List localList = this.mLocationManager.getAllProviders();
		if (localList != null) {
			for (int j = 0; j < localList.size(); j++) {
				Object localObject = localList.get(j);
				if ("network".equals(localObject)) {
					LocationManager localLocationManager2 = this.mLocationManager;
					String str1 = (String) localList.get(j);
					MyLocationManager localMyLocationManager1 = this;
					localLocationManager2.requestLocationUpdates(str1, 1L,
							1.0F, localMyLocationManager1);
				}
			}
		}
	}

	public void stopLocation() {
		try {
			if (this.mLocationManager == null) {
				this.mLocationManager = (LocationManager) mContext
						.getSystemService("location");
			}
			this.mLocationManager.removeUpdates(this);
			if (this.mTimer != null) {
				this.mTimer.interrupt();
				this.mTimer = null;
			}
			this.mListeners.clear();
			this.mProviderLocated = true;
			this.mCellLocated = true;
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void stopLocationManager() {
	}

}
