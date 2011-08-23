package com.rent.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.rent.SearchHandlerListener;
import com.rent.data.Community;
import com.rent.data.HouseSource;
import com.rent.lib.AppAlert;

public class SearchHandler extends ThreadHandler{

	private final String SUCCESS;
	  private String mCity;
	  private final ArrayList<Community> mCommList;
	  private final SearchHandler mHandler = this;
	  private final ArrayList<HouseSource> mHouseList;
	  private JsonHandler mJsonHandler;
	  private JSONObject mJsonObject;
	  private final SearchHandlerListener mListener;
	  public int mOffset;
	  private int mResultType;
	  private long mTime;
	  public int mTotalNumber = 0;

	  public SearchHandler(SearchHandlerListener paramSearchHandlerListener)
	  {
	    ArrayList localArrayList1 = new ArrayList();
	    this.mHouseList = localArrayList1;
	    ArrayList localArrayList2 = new ArrayList();
	    this.mCommList = localArrayList2;
	    this.SUCCESS = "success";
	    this.mListener = paramSearchHandlerListener;
	  }

	  public void clearAllList()
	  {
	    if (this.mHouseList != null)
	      this.mHouseList.clear();
	    if (this.mCommList != null)
	      this.mCommList.clear();
	  }

	  public ArrayList<Community> getCommList()
	  {
	    return this.mCommList;
	  }

	  public ArrayList<HouseSource> getHouseList()
	  {
	    return this.mHouseList;
	  }

	  public long getTime()
	  {
	    return this.mTime;
	  }

	  public int getTotalNumber()
	  {
	    return this.mTotalNumber;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    Bundle localBundle = paramMessage.getData();
	    String str1 = localBundle.getString("handler_data");
	    int i = localBundle.getInt("result");
	    this.mResultType = i;
	    long l = localBundle.getLong("low_time");
	    this.mTime = l;
	    String str2 = localBundle.getString("city");
	    this.mCity = str2;
	    JsonHandler localJsonHandler1 = new JsonHandler();
	    this.mJsonHandler = localJsonHandler1;
	    if (str1 != null)
	    {
	      JsonHandler localJsonHandler2 = this.mJsonHandler;
	      new JsonThread(localJsonHandler2, str1).start();
	    }
	      this.mListener.postSearch(false, this);
	  }

	  public void setTime(long paramLong)
	  {
	    this.mTime = paramLong;
	  }

	  class JsonHandler extends Handler
	  {
	    private JsonHandler()
	    {
	    }

	    public void handleMessage(Message paramMessage)
	    {
	      super.handleMessage(paramMessage);
	      boolean bool = paramMessage.getData().getBoolean("success");
	      SearchHandlerListener localSearchHandlerListener = SearchHandler.this.mListener;
	      SearchHandler localSearchHandler = SearchHandler.this.mHandler;
	      localSearchHandlerListener.postSearch(bool, localSearchHandler);
	    }
	  }

	  class JsonThread extends Thread
	  {
	    private final String mContent;
	    private final SearchHandler.JsonHandler mHandler;

	    public JsonThread(SearchHandler.JsonHandler paramString, String arg3)
	    {
	      this.mContent = arg3;
	      this.mHandler = paramString;
	    }

	    public void run()
	    {
	      Looper.prepare();
	      int i = 1;
	        try
	        {
	          SearchHandler localSearchHandler1 = SearchHandler.this;
	          String str1 = this.mContent;
	          JSONObject localJSONObject1 = new JSONObject(str1);
	         // JSONObject localJSONObject2 = SearchHandler.(localSearchHandler1, localJSONObject1);
	          JSONArray localJSONArray = SearchHandler.this.mJsonObject.getJSONArray("data");
	          int j = SearchHandler.this.mJsonObject.getInt("total_number");
	          SearchHandler localSearchHandler2 = SearchHandler.this;
	          int k = SearchHandler.this.mJsonObject.getInt("offset");
	          localSearchHandler2.mOffset = k;
	          AppAlert.checkData(SearchHandler.this.mJsonObject);
	          int m = localJSONArray.length();
	          int n = SearchHandler.this.mResultType;
	          if (1 != n)
	            return;
	          
	          SearchHandler.this.mHouseList.clear();
	          for (int l = 0; l < localJSONArray.length(); l++) {
	        	  HouseSource localObject1 = new HouseSource();
		          JSONObject localJSONObject3 = localJSONArray.getJSONObject(l);
		          if (localJSONObject3.isNull("id"))
		            continue;
		          long l1 = Long.parseLong(localJSONObject3.getString("id"));
		          localObject1.mOriginId = l1;
		          if (localJSONObject3.isNull("room"))
		            continue;
		          String str2 = localJSONObject3.getString("room");
		          localObject1.mRoom = str2;
		          if (localJSONObject3.isNull("title"))
		            continue;
		          String str3 = localJSONObject3.getString("title");
		          localObject1.mTitle = str3;
		          if (localJSONObject3.isNull("area"))
		            continue;
		          int i4 = localJSONObject3.getInt("area");
		          localObject1.mArea = i4;
		          if (localJSONObject3.isNull("rent_type"))
		            continue;
		          int i5 = Integer.parseInt(localJSONObject3.getString("rent_type"));
		          localObject1.mRentType = i5;
		          if (localJSONObject3.isNull("contact_person"))
		            continue;
		          String str4 = localJSONObject3.getString("contact_person");
		          localObject1.mContactPerson = str4;
		          if (localJSONObject3.isNull("phone"))
		            continue;
		          String str5 = localJSONObject3.getString("phone");
		          localObject1.mPhone = str5;
		          if (localJSONObject3.isNull("thumbnail"))
		            continue;
		          String str6 = localJSONObject3.getString("thumbnail");
		          localObject1.mImage = str6;
		          if (localJSONObject3.isNull("agency_status"))
		            continue;
		          String str7 = localJSONObject3.getString("agency_status");
		          localObject1.mAgency = str7;
		          if (localJSONObject3.isNull("address"))
		            continue;
		          String str8 = localJSONObject3.getString("address");
		          localObject1.mAddress = str8;
		          if (localJSONObject3.isNull("from_site"))
		            continue;
		          String str9 = localJSONObject3.getString("from_site");
		          localObject1.mFromUrl = str9;
		          if (localJSONObject3.isNull("price"))
		            continue;
		          int i6 = Integer.parseInt(localJSONObject3.getString("price"));
		          localObject1.mPrice = i6;
		          if (localJSONObject3.isNull("publish_time"))
		            continue;
		          String str10 = localJSONObject3.getString("publish_time");
		          localObject1.mPublishTime = str10;
		          boolean bool1 = SearchHandler.this.mHouseList.add(localObject1);
		          
		          Community community = new Community();
		          if (localJSONObject3.isNull("group_id"))
		            continue;
		          long l4 = localJSONObject3.getLong("group_id");
		          community.mGroupId = l4;
		          
		          if(SearchHandler.this.mCommList.size() == 0)
		        	  continue;
		          
		          long l5 = ((Community)SearchHandler.this.mCommList.get(l)).mGroupId;
		          long l6 = community.mGroupId;
		          if (l5 == l6)
		          {
		            if (localJSONObject3.isNull("source_count"))
		              continue;
		            int i7 = Integer.parseInt(localJSONObject3.getString("source_count"));
		            community.mSourceCount = i7;
		            if (localJSONObject3.isNull("name"))
		              continue;
		            String str11 = localJSONObject3.getString("name");
		            community.mName = str11;
		            if (localJSONObject3.isNull("address"))
		              continue;
		            String str12 = localJSONObject3.getString("address");
		            community.mAddress = str12;
		            if (localJSONObject3.isNull("rent_price"))
		              continue;
		            int i8 = Integer.parseInt(localJSONObject3.getString("rent_price"));
		            community.mPrice = i8;
		            if (localJSONObject3.isNull("thumbnail"))
		              continue;
		            String str13 = localJSONObject3.getString("thumbnail");
		            community.mImage = str13;
		            if (localJSONObject3.isNull("longitude"))
		              continue;
		            double d1 = Integer.parseInt(localJSONObject3.getString("longitude")) * 10.0D / 1000000.0D;
		            community.mLon = d1;
		            if (localJSONObject3.isNull("latitude"))
		              continue;
		            double d2 = Integer.parseInt(localJSONObject3.getString("latitude")) * 10.0D / 1000000.0D;
		            community.mLat = d2;
		            String str14 = SearchHandler.this.mCity;
		            community.mCity = str14;
		            boolean bool2 = SearchHandler.this.mCommList.add(community);
		            int i9 = SearchHandler.this.mCommList.size();
		          }
	          	}
	          
	          	SearchHandler.this.mTotalNumber = j;
	            Bundle localBundle1 = new Bundle();
	            localBundle1.putBoolean("success", true);
	            Message localMessage1 = new Message();
	            localMessage1.setData(localBundle1);
	            boolean bool3 = this.mHandler.sendMessage(localMessage1);
	            Looper.myLooper().quit();
	        }
	        catch (Exception localException2)
	        {
	          String str15 = localException2.toString();
	          int i10 = Log.e("searchhandlerexc", str15);
	          localException2.printStackTrace();
	          Bundle localBundle2 = new Bundle();
	          localBundle2.putBoolean("success", false);
	          Message localMessage2 = new Message();
	          localMessage2.setData(localBundle2);
	          boolean bool4 = this.mHandler.sendMessage(localMessage2);
	          Looper.myLooper().quit();
	        }
	        finally
	        {
	          Bundle localBundle3 = new Bundle();
	          localBundle3.putBoolean("success", false);
	          Message localMessage3 = new Message();
	          localMessage3.setData(localBundle3);
	          boolean bool5 = this.mHandler.sendMessage(localMessage3);
	          Looper.myLooper().quit();
	        }
	      }
	    }
	  
}
