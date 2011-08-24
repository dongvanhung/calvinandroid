package com.rent.handler;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rent.Rent;
import com.rent.data.CommunityDetail;
import com.rent.listener.CommunityDetailListener;
import com.rent.listener.InnerListener;

public class CommunityDetailHandler extends Handler {

	private CommunityDetailListener mListener;

	public CommunityDetailHandler(
			CommunityDetailListener paramCommunityDetailListener) {
		this.mListener = paramCommunityDetailListener;
	}

	public void handleMessage(Message paramMessage) {
		super.handleMessage(paramMessage);
		if (paramMessage.what == 1) {
			this.mListener.handleCommunityDetail(false, null);
		} else {
			String str = paramMessage.getData().getString("data");
			CommunityDetailHandler1 local1 = new CommunityDetailHandler1();
			InnerHandler localInnerHandler = new InnerHandler(local1);
			new InnerThread(localInnerHandler, str).start();
		}
	}

	final class CommunityDetailHandler1 implements InnerListener {
		public void innerListener(boolean paramBoolean,
				CommunityDetail paramCommunityDetail) {
			// CommunityDetailHandler.access$000(this.this$0).handleCommunityDetail(paramBoolean,
			// paramCommunityDetail);
		}
	}

	class InnerThread extends Thread {
		private String mContent;
		private CommunityDetailHandler.InnerHandler mHandler;

		public InnerThread(CommunityDetailHandler.InnerHandler paramString,
				String arg3) {
			this.mHandler = paramString;
			this.mContent = arg3;
		}

		public void run() {
			try {
				CommunityDetail localCommunityDetail = new CommunityDetail();
				String str1 = this.mContent;
				Object localObject1 = new JSONObject(str1)
						.getJSONObject("data");
				if (!((JSONObject) localObject1).isNull("price_chart_url")) {
					String str2 = ((JSONObject) localObject1)
							.getString("price_chart_url");
					localCommunityDetail.priceChaturl = str2;
				}
				if (!((JSONObject) localObject1).isNull("id")) {
					long l = ((JSONObject) localObject1).getLong("id");
					localCommunityDetail.id = l;
				}
				if (!((JSONObject) localObject1).isNull("name")) {
					String str3 = ((JSONObject) localObject1).getString("name");
					localCommunityDetail.name = str3;
				}
				if (!((JSONObject) localObject1).isNull("rent_price")) {
					int i = ((JSONObject) localObject1).getInt("rent_price");
					localCommunityDetail.rentPrice = i;
				}
				if (!((JSONObject) localObject1).isNull("house_price")) {
					int j = ((JSONObject) localObject1).getInt("house_price");
					localCommunityDetail.housePrice = j;
				}
				if (!((JSONObject) localObject1).isNull("rent_trend")) {
					double d1 = ((JSONObject) localObject1)
							.getDouble("rent_trend");
					localCommunityDetail.rentTrend = d1;
				}
				if (!((JSONObject) localObject1).isNull("house_trend")) {
					double d2 = ((JSONObject) localObject1)
							.getDouble("house_trend");
					localCommunityDetail.houseTrend = d2;
				}
				if (!((JSONObject) localObject1).isNull("latitude")) {
					double d3 = ((JSONObject) localObject1)
							.getDouble("latitude");
					localCommunityDetail.lat = d3;
				}
				if (!((JSONObject) localObject1).isNull("longitude")) {
					double d4 = ((JSONObject) localObject1)
							.getDouble("longitude");
					localCommunityDetail.lon = d4;
				}
				if (!((JSONObject) localObject1).isNull("address")) {
					String str4 = ((JSONObject) localObject1)
							.getString("address");
					localCommunityDetail.address = str4;
				}
				if (!((JSONObject) localObject1).isNull("thumbnail")) {
					String str5 = ((JSONObject) localObject1)
							.getString("thumbnail");
					localCommunityDetail.thumbnail = str5;
				}
				if (!((JSONObject) localObject1).isNull("intro")) {
					String str6 = ((JSONObject) localObject1)
							.getString("intro");
					localCommunityDetail.intro = str6;
					String str7 = localCommunityDetail.intro.replaceAll("\r\n",
							"\n");
					localCommunityDetail.intro = str7;
				}
				if (!((JSONObject) localObject1).isNull("bus")) {
					String str8 = ((JSONObject) localObject1).getString("bus");
					localCommunityDetail.bus = str8;
				}
				if (!((JSONObject) localObject1).isNull("subway")) {
					String str9 = ((JSONObject) localObject1)
							.getString("subway");
					localCommunityDetail.subway = str9;
				}
				if (!((JSONObject) localObject1).isNull("primary_school")) {
					String str10 = ((JSONObject) localObject1)
							.getString("primary_school");
					localCommunityDetail.primarySchool = str10;
				}
				if (!((JSONObject) localObject1).isNull("kindergarten")) {
					String str11 = ((JSONObject) localObject1)
							.getString("kindergarten");
					localCommunityDetail.kindergarten = str11;
				}
				if (!((JSONObject) localObject1).isNull("postoffice")) {
					String str12 = ((JSONObject) localObject1)
							.getString("postoffice");
					localCommunityDetail.postOffice = str12;
				}
				if (!((JSONObject) localObject1).isNull("bank")) {
					String str13 = ((JSONObject) localObject1)
							.getString("bank");
					localCommunityDetail.bank = str13;
				}
				if (!((JSONObject) localObject1).isNull("hospital")) {
					String str14 = ((JSONObject) localObject1)
							.getString("hospital");
					localCommunityDetail.hospital = str14;
				}
				if (!((JSONObject) localObject1).isNull("school")) {
					String str15 = ((JSONObject) localObject1)
							.getString("school");
					localCommunityDetail.school = str15;
				}
				if (!((JSONObject) localObject1).isNull("business_district")) {
					String str16 = ((JSONObject) localObject1)
							.getString("business_district");
					localCommunityDetail.businessDistrict = str16;
				}
				if (!((JSONObject) localObject1).isNull("medical_station")) {
					String str17 = ((JSONObject) localObject1)
							.getString("medical_station");
					localCommunityDetail.medicalStation = str17;
				}
				if (!((JSONObject) localObject1).isNull("restaurant")) {
					String str18 = ((JSONObject) localObject1)
							.getString("restaurant");
					localCommunityDetail.restaurant = str18;
				}
				if (!((JSONObject) localObject1).isNull("shopping")) {
					String str19 = ((JSONObject) localObject1)
							.getString("shopping");
					localCommunityDetail.shopping = str19;
				}
				if (!((JSONObject) localObject1).isNull("developer")) {
					String str20 = ((JSONObject) localObject1)
							.getString("developer");
					localCommunityDetail.developer = str20;
				}
				if (!((JSONObject) localObject1).isNull("property_company")) {
					String str21 = ((JSONObject) localObject1)
							.getString("property_company");
					localCommunityDetail.property_company = str21;
				}
				if (!((JSONObject) localObject1).isNull("property_fee")) {
					String str22 = ((JSONObject) localObject1)
							.getString("property_fee");
					localCommunityDetail.property_fee = str22;
				}
				if (!((JSONObject) localObject1).isNull("start_time")) {
					String str23 = ((JSONObject) localObject1)
							.getString("start_time");
					localCommunityDetail.start_time = str23;
				}
				if (!((JSONObject) localObject1).isNull("finish_time")) {
					String str24 = ((JSONObject) localObject1)
							.getString("finish_time");
					localCommunityDetail.finish_time = str24;
				}
				if (!((JSONObject) localObject1).isNull("decoration_info")) {
					String str25 = ((JSONObject) localObject1)
							.getString("decoration_info");
					localCommunityDetail.decoration_info = str25;
				}
				if (!((JSONObject) localObject1).isNull("arch_type")) {
					String str26 = ((JSONObject) localObject1)
							.getString("arch_type");
					localCommunityDetail.arch_type = str26;
				}
				if (!((JSONObject) localObject1).isNull("floors")) {
					String str27 = ((JSONObject) localObject1)
							.getString("floors");
					localCommunityDetail.floors = str27;
				}
				if (!((JSONObject) localObject1).isNull("property_type")) {
					String str28 = ((JSONObject) localObject1)
							.getString("property_type");
					localCommunityDetail.property_type = str28;
				}
				if (!((JSONObject) localObject1).isNull("building_area")) {
					String str29 = ((JSONObject) localObject1)
							.getString("building_area");
					localCommunityDetail.building_area = str29;
				}
				if (!((JSONObject) localObject1).isNull("cover_area")) {
					String str30 = ((JSONObject) localObject1)
							.getString("cover_area");
					localCommunityDetail.cover_area = str30;
				}
				if (!((JSONObject) localObject1).isNull("floor_area_ratio")) {
					String str31 = ((JSONObject) localObject1)
							.getString("floor_area_ratio");
					localCommunityDetail.floor_area_ratio = str31;
				}
				if (!((JSONObject) localObject1).isNull("images")) {
					localObject1 = ((JSONObject) localObject1)
							.getJSONObject("images");
					if (!((JSONObject) localObject1).isNull("pic_huxing")) {
						JSONArray localJSONArray = ((JSONObject) localObject1)
								.getJSONArray("pic_huxing");
						int m = 0;
						while (true) {
							int n = localJSONArray.length();
							if (m >= n)
								break;
							ArrayList localArrayList1 = localCommunityDetail.picHuxing;
							String str32 = localJSONArray.getString(m);
							boolean bool1 = localArrayList1.add(str32);
							m += 1;
						}
					}
					if (!((JSONObject) localObject1).isNull("pic_shijing")) {
						localObject1 = ((JSONObject) localObject1)
								.getJSONArray("pic_shijing");
						int k = 0;
						while (true) {
							int i1 = ((JSONArray) localObject1).length();
							if (k >= i1)
								break;
							ArrayList localArrayList2 = localCommunityDetail.picShijing;
							String str33 = ((JSONArray) localObject1)
									.getString(k);
							boolean bool2 = localArrayList2.add(str33);
							k += 1;
						}
					}
				}
				this.mHandler.setCommunityDetail(localCommunityDetail);
				Message localMessage1;
				Bundle localBundle1;
				boolean bool3;
				return;
			} catch (JSONException localJSONException) {
				while (true) {
					String str34 = localJSONException.toString();
					Rent.MyLog("CommunityDetailhanler.run()", str34);
					localJSONException.printStackTrace();
					Message localMessage2 = new Message();
					Bundle localBundle2 = new Bundle();
					localBundle2.putBoolean("data", false);
					localMessage2.setData(localBundle2);
					boolean bool4 = this.mHandler.sendMessage(localMessage2);
				}
			} finally {
				Message localMessage3 = new Message();
				Bundle localBundle3 = new Bundle();
				localBundle3.putBoolean("data", false);
				localMessage3.setData(localBundle3);
				boolean bool5 = this.mHandler.sendMessage(localMessage3);
			}
		}
	}

	class InnerHandler extends Handler {
		private CommunityDetail mDetail;
		private InnerListener mInnerListener;

		public InnerHandler(InnerListener arg2) {
			this.mInnerListener = arg2;
		}

		public void handleMessage(Message paramMessage) {
			super.handleMessage(paramMessage);
			if (paramMessage.what == 1) {
				this.mInnerListener.innerListener(false, null);
			} else {
				if (!paramMessage.getData().getBoolean("data")) {
					this.mInnerListener.innerListener(false, null);
				} else {
					InnerListener localInnerListener = this.mInnerListener;
					CommunityDetail localCommunityDetail = this.mDetail;
					localInnerListener
							.innerListener(true, localCommunityDetail);
				}
			}
		}

		public void setCommunityDetail(CommunityDetail paramCommunityDetail) {
			this.mDetail = paramCommunityDetail;
		}
	}
}
