package com.rent.handler;

import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.content.Context;
import android.os.Handler;

import com.rent.PreferenceUtils;
import com.rent.Rent;
import com.rent.listener.AddressFromLocationListener;

public class AddressFromLocationHandler extends Handler{

	private Context mContext;
	  private AddressFromLocationListener mListener;

	  public AddressFromLocationHandler(AddressFromLocationListener paramAddressFromLocationListener, Context paramContext)
	  {
	    this.mListener = paramAddressFromLocationListener;
	    this.mContext = paramContext;
	  }

	  public void handleMessage(android.os.Message paramMessage)
	  {
		  super.handleMessage(paramMessage);
	        try {
				if(paramMessage.what != 1) {
					mListener.addressObtained("");
				} 
				String data = paramMessage.getData().getString("data");
				JSONObject obj = (new JSONObject(data)).getJSONArray("Placemark").getJSONObject(0).getJSONObject("AddressDetails").getJSONObject("Country").getJSONObject("AdministrativeArea").getJSONObject("Locality");
				StringBuilder stringbuilder = new StringBuilder();
				JSONObject jsonobject = ((JSONObject) (obj)).getJSONObject("DependentLocality");
				String s1 = jsonobject.getString("DependentLocalityName");
				StringBuilder stringbuilder1 = stringbuilder.append(s1);
				String s2 = jsonobject.getJSONObject("Thoroughfare").getString("ThoroughfareName");
				StringBuilder stringbuilder2 = stringbuilder.append(s2);
				String city = obj.getString("LocalityName");
				if(city != null && city.length() > 0)
				{
				    int i = city.length() - 1;
				    city = city.substring(0, i);
				} else {
					
				}
				PreferenceUtils.saveCityName(mContext, city);
				android.content.SharedPreferences.Editor editor = mContext.getSharedPreferences("location_point", 0).edit();
				String s3 = stringbuilder.toString();
				boolean flag = editor.putString("name", s3).commit();
				String s = PreferenceUtils.getCurrentCityName(mContext);
				if(s == null || s.trim().length() == 0)
				{
				    Context context = mContext;
				    String s8 = mContext.getString(com.rent.R.string.defalut_city);
				    PreferenceUtils.saveCityName(context, s8);
				}
				
				AddressFromLocationListener addressfromlocationlistener = mListener;
				String s4 = stringbuilder.toString();
				addressfromlocationlistener.addressObtained(s4);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	  }
}
