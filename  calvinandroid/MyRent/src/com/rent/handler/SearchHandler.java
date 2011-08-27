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

public class SearchHandler extends ThreadHandler {

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

	public SearchHandler(SearchHandlerListener paramSearchHandlerListener) {
		ArrayList localArrayList1 = new ArrayList();
		this.mHouseList = localArrayList1;
		ArrayList localArrayList2 = new ArrayList();
		this.mCommList = localArrayList2;
		this.SUCCESS = "success";
		this.mListener = paramSearchHandlerListener;
	}

	public void clearAllList() {
		if (this.mHouseList != null)
			this.mHouseList.clear();
		if (this.mCommList != null)
			this.mCommList.clear();
	}

	public ArrayList<Community> getCommList() {
		return this.mCommList;
	}

	public ArrayList<HouseSource> getHouseList() {
		return this.mHouseList;
	}

	public long getTime() {
		return this.mTime;
	}

	public int getTotalNumber() {
		return this.mTotalNumber;
	}

	public void handleMessage(Message paramMessage) {
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
		if (str1 != null) {
			JsonHandler localJsonHandler2 = this.mJsonHandler;
			new JsonThread(localJsonHandler2, str1).start();
		} else {
			this.mListener.postSearch(false, this);
		}
	}

	public void setTime(long paramLong) {
		this.mTime = paramLong;
	}

	class JsonHandler extends Handler {
		private JsonHandler() {
		}

		public void handleMessage(Message paramMessage) {
			super.handleMessage(paramMessage);
			boolean bool = paramMessage.getData().getBoolean("success");
			SearchHandlerListener localSearchHandlerListener = SearchHandler.this.mListener;
			SearchHandler localSearchHandler = SearchHandler.this.mHandler;
			localSearchHandlerListener.postSearch(bool, localSearchHandler);
		}
	}

	class JsonThread extends Thread {
		private final String mContent;
		private final SearchHandler.JsonHandler mHandler;

		public JsonThread(SearchHandler.JsonHandler paramString, String arg3) {
			this.mContent = arg3;
			this.mHandler = paramString;
		}

		public void run() {
			Looper.prepare();
			int i = 1;
			try {
				SearchHandler localSearchHandler1 = SearchHandler.this;
				String str1 = this.mContent;
				JSONObject localJSONObject1 = new JSONObject(str1);
				// JSONObject localJSONObject2 =
				// SearchHandler.(localSearchHandler1, localJSONObject1);
				JSONArray localJSONArray = localJSONObject1
						.getJSONArray("data");
				int j = localJSONObject1.getInt("total_number");
				SearchHandler localSearchHandler2 = SearchHandler.this;
				int k = localJSONObject1.getInt("offset");
				localSearchHandler2.mOffset = k;
				AppAlert.checkData(localJSONObject1);
				int m = localJSONArray.length();
				int n = SearchHandler.this.mResultType;
				/*
				 * if (1 != n) return;
				 */

				SearchHandler.this.mHouseList.clear();
				for (int l = 0; l < localJSONArray.length(); l++) {
					HouseSource localObject1 = new HouseSource();
					JSONObject localJSONObject3 = localJSONArray
							.getJSONObject(l);

					if (!localJSONObject3.isNull("id")) {
						localObject1.mOriginId = Long.parseLong(localJSONObject3.getString("id"));
					}

					if (!localJSONObject3.isNull("room")) {
						localObject1.mRoom = localJSONObject3.getString("room");
					}

					if (!localJSONObject3.isNull("title")) {
						localObject1.mTitle = localJSONObject3.getString("title");
					}

					if (!localJSONObject3.isNull("area")) {
						localObject1.mArea = localJSONObject3.getInt("area");
					}

					if (!localJSONObject3.isNull("rent_type")) {
						localObject1.mRentType = Integer.parseInt(localJSONObject3.getString("rent_type"));
					}

					if (!localJSONObject3.isNull("contact_person")) {
						localObject1.mContactPerson = localJSONObject3.getString("contact_person");
					}

					if (!localJSONObject3.isNull("phone")) {
						localObject1.mPhone = localJSONObject3.getString("phone");
					}

					if (!localJSONObject3.isNull("thumbnail")) {
						localObject1.mImage = localJSONObject3.getString("thumbnail");
					}

					if (!localJSONObject3.isNull("agency_status")) {
						localObject1.mAgency = localJSONObject3.getString("agency_status");
					}

					if (!localJSONObject3.isNull("address")) {
						localObject1.mAddress = localJSONObject3.getString("address");
					}

					if (!localJSONObject3.isNull("from_site")) {
						localObject1.mFromUrl = localJSONObject3.getString("from_site");
					}

					if (!localJSONObject3.isNull("price")) {
						localObject1.mPrice = Integer.parseInt(localJSONObject3.getString("price"));
					}

					if (!localJSONObject3.isNull("publish_time")) {
						localObject1.mPublishTime = localJSONObject3.getString("publish_time");
					}
					boolean bool1 = SearchHandler.this.mHouseList
							.add(localObject1);

					Community community = new Community();

					if (!localJSONObject3.isNull("group_id")) {
						community.mGroupId = localJSONObject3.getLong("group_id");
					}

					/*
					 * if(SearchHandler.this.mCommList.size() == 0) continue;
					 */

					/*
					 * long l5 =
					 * ((Community)SearchHandler.this.mCommList.get(l))
					 * .mGroupId; long l6 = community.mGroupId; if (l5 == l6) {
					 */

					if (!localJSONObject3.isNull("source_count")) {
						community.mSourceCount = Integer.parseInt(localJSONObject3.getString("source_count"));
					}

					if (!localJSONObject3.isNull("name")) {
						community.mName = localJSONObject3.getString("name");
					}

					if (!localJSONObject3.isNull("address")) {
						community.mAddress = localJSONObject3.getString("address");
					}

					if (!localJSONObject3.isNull("rent_price")) {
						community.mPrice = Integer.parseInt(localJSONObject3.getString("rent_price"));
					}

					if (!localJSONObject3.isNull("thumbnail")) {
						community.mImage = localJSONObject3.getString("thumbnail");
					}

					if (!localJSONObject3.isNull("longitude")) {
						community.mLon = Integer.parseInt(localJSONObject3.getString("longitude")) * 10.0D / 1000000.0D;
					}

					if (!localJSONObject3.isNull("latitude")) {
						community.mLat = Integer.parseInt(localJSONObject3.getString("latitude")) * 10.0D / 1000000.0D;
					}
					// String str14 = SearchHandler.this.mCity;
					community.mCity = "";
					boolean bool2 = SearchHandler.this.mCommList.add(community);
					int i9 = SearchHandler.this.mCommList.size();
					// }
				}

				SearchHandler.this.mTotalNumber = j;
				Bundle localBundle1 = new Bundle();
				localBundle1.putBoolean("success", true);
				Message localMessage1 = new Message();
				localMessage1.setData(localBundle1);
				boolean bool3 = this.mHandler.sendMessage(localMessage1);
				Looper.myLooper().quit();
			} catch (Exception localException2) {
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
		}
	}

}
