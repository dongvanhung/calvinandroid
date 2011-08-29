package com.rent.thread;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class AddressFromLocationThread extends AbstractThread{

	private Context mContext;
	private String mUrl = "http://maps.google.cn/maps/geo?q=%s,%s&hl=zh-CN&key=0RSTRVxpCVRGfIJLY5rlZ5gpQz7bvaLAyK7y_0w";

	public AddressFromLocationThread(Handler paramHandler, Context paramContext) {
		super(paramHandler);
		this.mContext = paramContext;
	}

	protected Message getMessageByContent(String paramString) {
		Bundle localBundle = new Bundle();
		localBundle.putString("data", paramString);
		Message localMessage = new Message();
		localMessage.setData(localBundle);
		return localMessage;
	}

	protected boolean isSuccessful(String paramString) throws JSONException {
		if (paramString != null)
			return true;
		return false;
	}

	public void run() {
		SharedPreferences localSharedPreferences = this.mContext
				.getSharedPreferences("location_point", 0);
		double d1 = Double.parseDouble(localSharedPreferences.getString("lat",
				"39.920591"));
		double d2 = Double.parseDouble(localSharedPreferences.getString("lon",
				"116.432791"));
		String str1 = this.mUrl;
		Object[] arrayOfObject = new Object[2];
		String str2 = String.valueOf(d1);
		arrayOfObject[0] = str2;
		String str3 = String.valueOf(d2);
		arrayOfObject[1] = str3;
		String str4 = String.format(str1, arrayOfObject);
		this.mUrl = str4;
		String str5 = this.mUrl;
		super.setUrl(str5);
		try {
			String str6 = executeGet();
			if (isSuccessful(str6)) {
				Handler localHandler = this.handler;
				Message localMessage = getMessageByContent(str6);
				boolean bool1 = localHandler.sendMessage(localMessage);
			} else {
				this.handler.sendEmptyMessage(1);
			}
		} catch (Exception localException) {
			this.handler.sendEmptyMessage(1);
		}
	}
	
}
