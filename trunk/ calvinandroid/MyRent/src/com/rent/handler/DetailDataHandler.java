package com.rent.handler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Message;

import com.rent.data.DetailData;
import com.rent.listener.HandlerListener;

public class DetailDataHandler extends ThreadHandler {

	private List<DetailData> mDetailDataList;
	  private HandlerListener mListener;

	  public DetailDataHandler(HandlerListener paramHandlerListener)
	  {
	    ArrayList localArrayList = new ArrayList();
	    this.mDetailDataList = localArrayList;
	    this.mListener = paramHandlerListener;
	  }

	  public void clearDetailList()
	  {
	    if (this.mDetailDataList != null)
	      this.mDetailDataList.clear();
	  }

	  public List<DetailData> getDetailDataList()
	  {
	    return this.mDetailDataList;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    Bundle localBundle = paramMessage.getData();
	    String localObject1 = localBundle.getString("handler_data");
	    DetailData dd = new DetailData();
	    long l = localBundle.getLong("mId");
	    try
	    {
	      JSONObject localJSONObject = new JSONObject((String)localObject1);
	      Object localObject2 = localJSONObject.getJSONObject("data");
	      dd.setmId(l);
	      if (!((JSONObject)localObject2).isNull("room"))
	      {
	        String str1 = ((JSONObject)localObject2).getString("room");
	        dd.setmRoom(str1);
	      }
	      if (!((JSONObject)localObject2).isNull("title"))
	      {
	        String str2 = ((JSONObject)localObject2).getString("title");
	        dd.setmTitle(str2);
	      }
	      if (!((JSONObject)localObject2).isNull("area"))
	      {
	        int j = ((JSONObject)localObject2).getInt("area");
	        dd.setmArea(j);
	      }
	      if (!((JSONObject)localObject2).isNull("rent_type"))
	      {
	        int k = ((JSONObject)localObject2).getInt("rent_type");
	        dd.setmRentType(k);
	      }
	      if (!((JSONObject)localObject2).isNull("contact_person"))
	      {
	        String str3 = ((JSONObject)localObject2).getString("contact_person");
	        dd.setmContactPerson(str3);
	      }
	      if (!((JSONObject)localObject2).isNull("phone"))
	      {
	        String str4 = ((JSONObject)localObject2).getString("phone");
	        dd.setmPhone(str4);
	      }
	      if (!((JSONObject)localObject2).isNull("thumbnail"))
	      {
	        String str5 = ((JSONObject)localObject2).getString("thumbnail");
	        dd.setmThumbnail(str5);
	      }
	      if (!((JSONObject)localObject2).isNull("agency_status"))
	      {
	        String str6 = ((JSONObject)localObject2).getString("agency_status");
	        dd.setmAgencyStatus(str6);
	      }
	      if (!((JSONObject)localObject2).isNull("address"))
	      {
	        String str7 = ((JSONObject)localObject2).getString("address");
	        dd.setmAddress(str7);
	      }
	      if (!((JSONObject)localObject2).isNull("from_site"))
	      {
	        String str8 = ((JSONObject)localObject2).getString("from_site");
	        dd.setmFromSite(str8);
	      }
	      if (!((JSONObject)localObject2).isNull("price"))
	      {
	        int m = ((JSONObject)localObject2).getInt("price");
	        dd.setmPrice(m);
	      }
	      if (!((JSONObject)localObject2).isNull("publish_time"))
	      {
	        String str9 = ((JSONObject)localObject2).getString("publish_time");
	        dd.setmPublishTime(str9);
	      }
	      if (!((JSONObject)localObject2).isNull("abstract"))
	      {
	        String str10 = ((JSONObject)localObject2).getString("abstract");
	        dd.setmAbstract(str10);
	      }
	      if (!((JSONObject)localObject2).isNull("contact_path"))
	      {
	        String str11 = ((JSONObject)localObject2).getString("contact_path");
	        dd.setmContactPath(str11);
	      }
	      if (!((JSONObject)localObject2).isNull("master_number"))
	      {
	        String str12 = ((JSONObject)localObject2).getString("master_number");
	        dd.mMasterNumber = str12;
	      }
	      if (!((JSONObject)localObject2).isNull("ext_number"))
	      {
	        String str13 = ((JSONObject)localObject2).getString("ext_number");
	        dd.mExtNumber = str13;
	      }
	      if (!((JSONObject)localObject2).isNull("images"))
	      {
	        localObject2 = ((JSONObject)localObject2).getJSONArray("images");
	        int i = ((JSONArray)localObject2).length();
	        String[] arrayOfString = new String[i];
	        int n = 0;
	        while (n < i)
	        {
	          String str14 = ((JSONArray)localObject2).getString(n);
	          arrayOfString[n] = str14;
	          n += 1;
	        }
	        dd.setmImages(arrayOfString);
	      }
	      boolean bool = this.mDetailDataList.add(dd);
	      this.mListener.statusChanged(true);
	      return;
	    }
	    catch (Exception localException)
	    {
	        this.mListener.statusChanged(false);
	        localException.printStackTrace();
	      }
	    }
}
