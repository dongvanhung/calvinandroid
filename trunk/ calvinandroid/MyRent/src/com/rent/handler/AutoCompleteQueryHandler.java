package com.rent.handler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.rent.data.AutoCompleteItemData;
import com.rent.listener.AutoCompleteQueryListener;

public class AutoCompleteQueryHandler extends Handler {

	private AutoCompleteQueryListener mListener;
	  private List<AutoCompleteItemData> mLists;

	  public AutoCompleteQueryHandler(AutoCompleteQueryListener paramAutoCompleteQueryListener, Context paramContext)
	  {
	    this.mListener = paramAutoCompleteQueryListener;
	  }

	  public List<AutoCompleteItemData> getItemLists()
	  {
	    return this.mLists;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    if (paramMessage.what == 1)
	      this.mListener.autoCompleteQueryObtained("");
	    JSONObject localObject1 = null;
	      while (true)
	      {
	        ArrayList localArrayList = new ArrayList();
	        this.mLists = localArrayList;
	        String str = paramMessage.getData().getString("data");
	        try
	        {
	        	localObject1 = new JSONObject(str).getJSONObject("ResultSet");
	          if ((localObject1.getString("status").equals("success")))
	            break;
	          this.mListener.autoCompleteQueryObtained("");
	        }
	        catch (Exception localException)
	        {
	          this.mListener.autoCompleteQueryObtained("");
	        }
	      }
	    //while ((localObject1.isNull("Result")); //TODO
	    try {
	    	JSONArray localObject2 = localObject1.getJSONArray("Result");
			int i = 0;
			int j = localObject2.length();
			JSONObject localObject3;
			localObject3 = localObject2.getJSONObject(0);
			if (!localObject3.isNull("nQuantity")) {
				for (int k = localObject3.getInt("nQuantity"); ; k = 0)
				{
				  if (!(localObject3.isNull("sKey"))) {
					 String str = localObject3.getString("sKey");
					    AutoCompleteItemData localAutoCompleteItemData = new AutoCompleteItemData();
					    localAutoCompleteItemData.number = k;
					    localAutoCompleteItemData.suggestion = str;
					    boolean bool = this.mLists.add(localAutoCompleteItemData);
					    this.mListener.autoCompleteQueryObtained("success");
				  }
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	  }
	  
}
