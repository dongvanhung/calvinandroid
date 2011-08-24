package com.rent.thread;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;

import com.rent.HouseFilter;
import com.rent.handler.ThreadHandler;

public class SearchThread extends AbstractThread {

	public static final int SORT_PRICE = 1;
	public static final int SORT_TIME = 0;
	private final ThreadHandler handler;
	private String mCity;
	private final int mResultType;
	private final long mTime;
	private final String mUrl;

	public SearchThread(ThreadHandler paramThreadHandler, double paramDouble1,
			double paramDouble2, double paramDouble3, double paramDouble4,
			String paramString1, int paramInt, HouseFilter paramHouseFilter,
			long paramLong, Context paramContext, boolean paramBoolean,
			String paramString2) {
		super(paramThreadHandler);
		ThreadHandler localThreadHandler = paramThreadHandler;
		this.handler = localThreadHandler;
		this.mResultType = 0;
		long l1 = paramLong;
		this.mTime = l1;
		this.mCity = paramString1;
		StringBuilder localStringBuilder1 = new StringBuilder()
				.append("&time_upper=");
		long l2 = System.currentTimeMillis() / 1000L;
		String str1 = l2 + "&time_lower=%s";
		StringBuilder localStringBuilder2 = new StringBuilder(
				"http://api.99fang.com/core/1/search?channel=rent&latitude_lower=%d&longitude_lower=%d&latitude_upper=%d&longitude_upper=%d&offset=%d&count=20&city=%s");
		if (paramHouseFilter.getmPriceLow() != -1) {
			Object[] arrayOfObject1 = new Object[1];
			Integer localInteger1 = Integer.valueOf(paramHouseFilter
					.getmPriceLow());
			arrayOfObject1[0] = localInteger1;
			String str2 = String.format("&price_lower=%d", arrayOfObject1);
			StringBuilder localStringBuilder3 = localStringBuilder2
					.append(str2);
		}
		if (paramHouseFilter.getmPriceHight() != -1) {
			Object[] arrayOfObject2 = new Object[1];
			Integer localInteger2 = Integer.valueOf(paramHouseFilter
					.getmPriceHight());
			arrayOfObject2[0] = localInteger2;
			String str3 = String.format("&price_upper=%d", arrayOfObject2);
			StringBuilder localStringBuilder4 = localStringBuilder2
					.append(str3);
		}
		if ((paramHouseFilter.ismIsAgency())
				&& (!paramHouseFilter.ismIsPersonal())) {
			Object[] arrayOfObject3 = new Object[1];
			Integer localInteger3 = Integer.valueOf(1);
			arrayOfObject3[0] = localInteger3;
			String str4 = String.format("&agency_type=%d", arrayOfObject3);
			StringBuilder localStringBuilder5 = localStringBuilder2
					.append(str4);
		}
		if ((!paramHouseFilter.ismIsAgency())
				&& (paramHouseFilter.ismIsPersonal())) {
			Object[] arrayOfObject4 = new Object[1];
			Integer localInteger4 = Integer.valueOf(2);
			arrayOfObject4[0] = localInteger4;
			String str5 = String.format("&agency_type=%d", arrayOfObject4);
			StringBuilder localStringBuilder6 = localStringBuilder2
					.append(str5);
		}
		if ((paramHouseFilter.ismIsRentPart())
				&& (!paramHouseFilter.ismIsRentAll())) {
			Object[] arrayOfObject5 = new Object[1];
			Integer localInteger5 = Integer.valueOf(1);
			arrayOfObject5[0] = localInteger5;
			String str6 = String.format("&rent_type=%d", arrayOfObject5);
			StringBuilder localStringBuilder7 = localStringBuilder2
					.append(str6);
		}
		if ((!paramHouseFilter.ismIsRentPart())
				&& (paramHouseFilter.ismIsRentAll())) {
			Object[] arrayOfObject6 = new Object[1];
			Integer localInteger6 = Integer.valueOf(0);
			arrayOfObject6[0] = localInteger6;
			String str7 = String.format("&rent_type=%d", arrayOfObject6);
			StringBuilder localStringBuilder8 = localStringBuilder2
					.append(str7);
		}
		if (paramHouseFilter.getmRoomNumber() != -1) {
			Object[] arrayOfObject7 = new Object[1];
			Integer localInteger7 = Integer.valueOf(paramHouseFilter
					.getmRoomNumber());
			arrayOfObject7[0] = localInteger7;
			String str8 = String.format("&room_number=%d", arrayOfObject7);
			StringBuilder localStringBuilder9 = localStringBuilder2
					.append(str8);
		}
		if (65535L != paramLong) {
			Object[] arrayOfObject8 = new Object[1];
			StringBuilder localStringBuilder10 = new StringBuilder();
			long l3 = paramLong;
			String str9 = l3 + "";
			arrayOfObject8[0] = str9;
			String str10 = String.format(str1, arrayOfObject8);
			StringBuilder localStringBuilder11 = localStringBuilder2
					.append(str10);
		}
		if (paramBoolean)
			localStringBuilder2.append("&total_number_only=true");
		if (paramContext != null) {
			Context localContext = paramContext;
			String str11 = "phone";
			String str12 = ((TelephonyManager) localContext
					.getSystemService(str11)).getDeviceId();
			String str13 = "&uuid=" + str12;
			StringBuilder localStringBuilder13 = localStringBuilder2
					.append(str13);
			StringBuilder localStringBuilder14 = localStringBuilder2
					.append("&app_name=rent");
			StringBuilder localStringBuilder15 = localStringBuilder2
					.append("&platform=0");
		}
		if ((paramString2 != null) && (paramString2.length() > 0)) {
			StringBuilder localStringBuilder16 = new StringBuilder()
					.append("&q=");
			String str14 = paramString2;
			String str15 = str14;
			StringBuilder localStringBuilder17 = localStringBuilder2
					.append(str15);
		}
		int i = (int) (0.0F * paramDouble1);
		int j = (int) (0.0F * paramDouble2);
		int k = (int) (0.0F * paramDouble3);
		int m = (int) (0.0F * paramDouble4);
		//if (i > k && j > m) {
			Object[] arrayOfObject9 = new Object[6];
			Integer localInteger8 = Integer.valueOf(k);
			arrayOfObject9[0] = localInteger8;
			Integer localInteger9 = Integer.valueOf(m);
			arrayOfObject9[1] = localInteger9;
			Integer localInteger10 = Integer.valueOf(i);
			arrayOfObject9[2] = localInteger10;
			Integer localInteger11 = Integer.valueOf(j);
			arrayOfObject9[3] = localInteger11;
			Integer localInteger12 = Integer.valueOf(paramInt);
			arrayOfObject9[4] = localInteger12;
			arrayOfObject9[5] = paramString1;
			String str16 = String.format(localStringBuilder2.toString(),
					arrayOfObject9);
			this.mUrl = str16;
		//}
	}

	public SearchThread(ThreadHandler paramThreadHandler, long paramLong,
			int paramInt1, int paramInt2, HouseFilter paramHouseFilter,
			String paramString, int paramInt3) {
		super(paramThreadHandler);
		this.handler = paramThreadHandler;
		this.mResultType = 1;
		this.mTime = 65535L;
		StringBuilder localStringBuilder1 = new StringBuilder(
				"http://api.99fang.com/core/1/search?channel=rent&group_id=%d&offset=%d&city=%s");
		if (paramHouseFilter.getmPriceLow() != -1) {
			Object[] arrayOfObject1 = new Object[1];
			Integer localInteger1 = Integer.valueOf(paramHouseFilter
					.getmPriceLow());
			arrayOfObject1[0] = localInteger1;
			String str1 = String.format("&price_lower=%d", arrayOfObject1);
			StringBuilder localStringBuilder2 = localStringBuilder1
					.append(str1);
		}
		if (paramHouseFilter.getmPriceHight() != -1) {
			Object[] arrayOfObject2 = new Object[1];
			Integer localInteger2 = Integer.valueOf(paramHouseFilter
					.getmPriceHight());
			arrayOfObject2[0] = localInteger2;
			String str2 = String.format("&price_upper=%d", arrayOfObject2);
			StringBuilder localStringBuilder3 = localStringBuilder1
					.append(str2);
		}
		if ((paramHouseFilter.ismIsAgency())
				&& (!paramHouseFilter.ismIsPersonal())) {
			Object[] arrayOfObject3 = new Object[1];
			Integer localInteger3 = Integer.valueOf(1);
			arrayOfObject3[0] = localInteger3;
			String str3 = String.format("&agency_type=%d", arrayOfObject3);
			StringBuilder localStringBuilder4 = localStringBuilder1
					.append(str3);
		}
		if ((!paramHouseFilter.ismIsAgency())
				&& (paramHouseFilter.ismIsPersonal())) {
			Object[] arrayOfObject4 = new Object[1];
			Integer localInteger4 = Integer.valueOf(2);
			arrayOfObject4[0] = localInteger4;
			String str4 = String.format("&agency_type=%d", arrayOfObject4);
			StringBuilder localStringBuilder5 = localStringBuilder1
					.append(str4);
		}
		if ((paramHouseFilter.ismIsRentPart())
				&& (!paramHouseFilter.ismIsRentAll())) {
			Object[] arrayOfObject5 = new Object[1];
			Integer localInteger5 = Integer.valueOf(1);
			arrayOfObject5[0] = localInteger5;
			String str5 = String.format("&rent_type=%d", arrayOfObject5);
			StringBuilder localStringBuilder6 = localStringBuilder1
					.append(str5);
		}
		if ((!paramHouseFilter.ismIsRentPart())
				&& (paramHouseFilter.ismIsRentAll())) {
			Object[] arrayOfObject6 = new Object[1];
			Integer localInteger6 = Integer.valueOf(0);
			arrayOfObject6[0] = localInteger6;
			String str6 = String.format("&rent_type=%d", arrayOfObject6);
			StringBuilder localStringBuilder7 = localStringBuilder1
					.append(str6);
		}
		if (paramHouseFilter.getmRoomNumber() != -1) {
			Object[] arrayOfObject7 = new Object[1];
			Integer localInteger7 = Integer.valueOf(paramHouseFilter
					.getmRoomNumber());
			arrayOfObject7[0] = localInteger7;
			String str7 = String.format("&room_number=%d", arrayOfObject7);
			StringBuilder localStringBuilder8 = localStringBuilder1
					.append(str7);
		}
		if (paramInt3 == 0)
			localStringBuilder1.append("&sort=0");

		Object[] arrayOfObject8 = new Object[3];
		Long localLong = Long.valueOf(paramLong);
		arrayOfObject8[0] = localLong;
		Integer localInteger8 = Integer.valueOf(paramInt1);
		arrayOfObject8[1] = localInteger8;
		arrayOfObject8[2] = paramString;
		String str8 = String.format(localStringBuilder1.toString(),
				arrayOfObject8);
		this.mUrl = str8;
		localStringBuilder1.append("&sort=1");
	}

	public ThreadHandler getHandler() {
		return this.handler;
	}

	protected Message getMessageByContent(String paramString) {
		Bundle localBundle = new Bundle();
		localBundle.putString("handler_data", paramString);
		int i = this.mResultType;
		localBundle.putInt("result", i);
		long l = this.mTime;
		localBundle.putLong("low_time", l);
		String str = this.mCity;
		localBundle.putString("city", str);
		Message localMessage = new Message();
		localMessage.setData(localBundle);
		return localMessage;
	}

	protected boolean isSuccessful(String paramString) throws JSONException {
		if (paramString != null) {
			String str = new JSONObject(paramString).getString("message");
			if (!"success".equals(str))
				return false;
			return true;
		}
		return false;
	}

	// ERROR //
	public void run() {
		// Byte code:
		// 0: new 31 java/lang/StringBuilder
		// 3: dup
		// 4: invokespecial 34 java/lang/StringBuilder:<init> ()V
		// 7: astore_1
		// 8: aload_0
		// 9: getfield 144 com/songshulin/android/rent/thread/SearchThread:mUrl
		// Ljava/lang/String;
		// 12: astore_2
		// 13: aload_1
		// 14: aload_2
		// 15: invokevirtual 40 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 18: ldc 115
		// 20: invokevirtual 40 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 23: invokevirtual 57 java/lang/StringBuilder:toString
		// ()Ljava/lang/String;
		// 26: astore_3
		// 27: ldc 214
		// 29: aload_3
		// 30: invokestatic 220 android/util/Log:e
		// (Ljava/lang/String;Ljava/lang/String;)I
		// 33: istore 4
		// 35: aload_0
		// 36: getfield 144 com/songshulin/android/rent/thread/SearchThread:mUrl
		// Ljava/lang/String;
		// 39: astore 5
		// 41: aload_0
		// 42: aload 5
		// 44: invokespecial 223
		// com/songshulin/android/rent/thread/AbstractThread:setUrl
		// (Ljava/lang/String;)V
		// 47: aload_0
		// 48: invokevirtual 226
		// com/songshulin/android/rent/thread/SearchThread:executeGet
		// ()Ljava/lang/String;
		// 51: astore 6
		// 53: aload_0
		// 54: aload 6
		// 56: invokevirtual 228
		// com/songshulin/android/rent/thread/SearchThread:isSuccessful
		// (Ljava/lang/String;)Z
		// 59: ifeq +27 -> 86
		// 62: aload_0
		// 63: getfield 23
		// com/songshulin/android/rent/thread/SearchThread:handler
		// Lcom/songshulin/android/rent/handler/ThreadHandler;
		// 66: astore 7
		// 68: aload_0
		// 69: aload 6
		// 71: invokevirtual 230
		// com/songshulin/android/rent/thread/SearchThread:getMessageByContent
		// (Ljava/lang/String;)Landroid/os/Message;
		// 74: astore 8
		// 76: aload 7
		// 78: aload 8
		// 80: invokevirtual 236
		// com/songshulin/android/rent/handler/ThreadHandler:sendMessage
		// (Landroid/os/Message;)Z
		// 83: istore 9
		// 85: return
		// 86: aload_0
		// 87: getfield 23
		// com/songshulin/android/rent/thread/SearchThread:handler
		// Lcom/songshulin/android/rent/handler/ThreadHandler;
		// 90: iconst_3
		// 91: invokevirtual 240
		// com/songshulin/android/rent/handler/ThreadHandler:sendEmptyMessage
		// (I)Z
		// 94: istore 10
		// 96: goto -11 -> 85
		// 99: astore 11
		// 101: aload_0
		// 102: getfield 23
		// com/songshulin/android/rent/thread/SearchThread:handler
		// Lcom/songshulin/android/rent/handler/ThreadHandler;
		// 105: iconst_3
		// 106: invokevirtual 240
		// com/songshulin/android/rent/handler/ThreadHandler:sendEmptyMessage
		// (I)Z
		// 109: istore 12
		// 111: goto -26 -> 85
		// 114: athrow
		//
		// Exception table:
		// from to target type
		// 47 96 99 java/lang/Exception
		// 47 96 114 finally
		// 101 111 114 finally
	}
}
