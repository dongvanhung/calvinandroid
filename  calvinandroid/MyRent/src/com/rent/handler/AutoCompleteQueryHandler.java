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

	public AutoCompleteQueryHandler(
			AutoCompleteQueryListener paramAutoCompleteQueryListener,
			Context paramContext) {
		this.mListener = paramAutoCompleteQueryListener;
	}

	public List<AutoCompleteItemData> getItemLists() {
		return this.mLists;
	}

	public void handleMessage(Message paramMessage) {
		super.handleMessage(paramMessage);
		JSONObject localObject1 = null;
		if (paramMessage.what == 1)
			this.mListener.autoCompleteQueryObtained("");
		else {
			ArrayList localArrayList = new ArrayList();
			this.mLists = localArrayList;
			String str = paramMessage.getData().getString("data");
			try {
				localObject1 = new JSONObject(str).getJSONObject("ResultSet");
				if ((localObject1.getString("status").equals("success"))) {
					if (!localObject1.isNull("Result")) {
						JSONArray localObject2 = localObject1
								.getJSONArray("Result");
						JSONObject localObject3;
						for (int i = 0; i < localObject2.length(); i++) {
							localObject3 = localObject2.getJSONObject(i);
							if (!localObject3.isNull("nQuantity")) {
								int k = localObject3.getInt("nQuantity");
								if (!(localObject3.isNull("sKey"))) {
									String str1 = localObject3.getString("sKey");
									AutoCompleteItemData localAutoCompleteItemData = new AutoCompleteItemData();
									localAutoCompleteItemData.number = k;
									localAutoCompleteItemData.suggestion = str1;
									boolean bool = this.mLists
											.add(localAutoCompleteItemData);
								}
							}
						}
						this.mListener.autoCompleteQueryObtained("success");
						
					}
				} else {
					this.mListener.autoCompleteQueryObtained("");
				}
			} catch (Exception localException) {
				this.mListener.autoCompleteQueryObtained("");
			}
		}
	}

}
