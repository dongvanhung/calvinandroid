package com.rent.thread;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

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

	public void run() {
		try {
			StringBuilder stringbuilder = new StringBuilder();
			String s = mUrl;
			String s1 = stringbuilder.append(s).append("").toString();
//			int i = Log.e("url", s1);
			String s2 = mUrl;
//			super.setUrl(s2);
//			String s3 = executeGet();
			
			String s3 = "{\"message\": \"success\", \"data\": [{\"source_count\": 13, \"name\": \"\u51e4\u5357\u4e00\u6751\", \"house_price\": 0, \"longitude\": 12152620, \"rent_price\": 2320, \"address\": \"\u5468\u5bb6\u5634\u8def\u51e4\u57ce\u8def\", \"latitude\": 3127505, \"group_id\": 2822325660, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/971/158/813714.jpg\"}, {\"source_count\": 12, \"name\": \"\u51ef\u5174\u82d1\", \"house_price\": 23363, \"longitude\": 12152780, \"rent_price\": 3740, \"address\": \"\u6768\u6d66\u533a \u9ec4\u5174\u8def97\u5f04\u3001\u7709\u5dde\u652f\u8def85\u53f7\u7b49\", \"latitude\": 3127580, \"group_id\": 320680719, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/396/499/178183.jpg\"}, {\"source_count\": 9, \"name\": \"\u4e1c\u7530\u516c\u5bd3\", \"house_price\": 24020, \"longitude\": 12152960, \"rent_price\": 3500, \"address\": \"\u6768\u6d66\u533a\u4e0a\u6d77\u6768\u6d66\u7709\u5dde\u8def888\u5f041\u53f7\", \"latitude\": 3127059, \"group_id\": 4231787560, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/318/17/178151.jpg\"}, {\"source_count\": 27, \"name\": \"\u534e\u5347\u65b0\u82d1\", \"house_price\": 25937, \"longitude\": 12152232, \"rent_price\": 4333, \"address\": \"\u6768\u6d66\u533a \u63a7\u6c5f\u8def1505\u5f0435\u300140\u300141\u53f7\u7b49\", \"latitude\": 3127596, \"group_id\": 648140830, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/265/944/5301604.jpg\"}, {\"source_count\": 20, \"name\": \"\u63a7\u6c5f\u8def1197\u5f04\", \"house_price\": 21872, \"longitude\": 12152723, \"rent_price\": 2625, \"address\": \"\u63a7\u6c5f\u8def1197\u5f04\", \"latitude\": 3127789, \"group_id\": 3487803141, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/27/172/153637.jpg\"}, {\"source_count\": 18, \"name\": \"\u4fe1\u901a\u6d66\u7693\u56ed\", \"house_price\": 28959, \"longitude\": 12152245, \"rent_price\": 3600, \"address\": \"\u5468\u5bb6\u5634\u8def2408\u53f7 \u5170\u5dde\u8def1333\u5f04\", \"latitude\": 3127086, \"group_id\": 2402226633, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/295/829/780351.jpg\"}, {\"source_count\": 43, \"name\": \"\u65b0\u51e4\u57ce\", \"house_price\": 28547, \"longitude\": 12152189, \"rent_price\": 4775, \"address\": \"\u63a7\u6c5f\u8def1500\u5f04\", \"latitude\": 3127573, \"group_id\": 178146357, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/638/85/155166.jpg\"}, {\"source_count\": 32, \"name\": \"\u63a7\u6c5f\u8def1200\u5f04\", \"house_price\": 21738, \"longitude\": 12152749, \"rent_price\": 2433, \"address\": \"\u63a7\u6c5f\u8def1200\u5f04\", \"latitude\": 3127861, \"group_id\": 2965467071, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/980/296/5351786.jpg\"}, {\"source_count\": 19, \"name\": \"\u534e\u5347\u516c\u5bd3\", \"house_price\": 25085, \"longitude\": 12152115, \"rent_price\": 4000, \"address\": \"\u63a7\u6c5f\u8def1545\u5f041-2\u53f7\", \"latitude\": 3127392, \"group_id\": 1209089041, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/321/814/177407.jpg\"}, {\"source_count\": 6, \"name\": \"\u4e2d\u738b\u5c0f\u533a\", \"house_price\": 24050, \"longitude\": 12153063, \"rent_price\": 3200, \"address\": \"\u7709\u5dde\u8def938\u5f04\", \"latitude\": 3126928, \"group_id\": 4064885956, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/471/651/754829.jpg\"}, {\"source_count\": 6, \"name\": \"\u9633\u660e\u82d1\", \"house_price\": 27957, \"longitude\": 12152212, \"rent_price\": 0, \"address\": \"\u6768\u6d66\u533a \u6c5f\u6d66\u8def1178\u53f7\", \"latitude\": 3126948, \"group_id\": 1158810336, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/607/871/161424.jpg\"}, {\"source_count\": 11, \"name\": \"\u73b0\u4ee3\u661f\u6d32\u57ce\u4e09\u671f(\u683c\u8c03\u661f\u6d32)\", \"house_price\": 29285, \"longitude\": 12152220, \"rent_price\": 4583, \"address\": \"\u683c\u8c03\u661f\u6d32\u4f4d\u4e8e\u6768\u6d66\u533a\u6c5f\u6d66\u8def1199\u53f7\", \"latitude\": 3126797, \"group_id\": 137294465, \"thumbnail\": \"/image/no_image.jpg\"}, {\"source_count\": 25, \"name\": \"\u957f\u7709\u5c0f\u533a\", \"house_price\": 23906, \"longitude\": 12152927, \"rent_price\": 2100, \"address\": \"\u957f\u9633\u8def\u8fd1\u7709\u5dde\u8def\", \"latitude\": 3126750, \"group_id\": 4223049799, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/480/378/175223.jpg\"}, {\"source_count\": 20, \"name\": \"\u4e1c\u65b9\u661f\u5ea7\u9152\u5e97\u516c\u5bd3\", \"house_price\": 19034, \"longitude\": 12151908, \"rent_price\": 0, \"address\": \"\u6768\u6d66\u533a \u6c5f\u6d66\u8def1508\u53f7\", \"latitude\": 3127418, \"group_id\": 925264042, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/80/920/175369.jpg\"}, {\"source_count\": 5, \"name\": \"\u65b0\u51e4\u57ce\u94f6\u5ea7\", \"house_price\": 34312, \"longitude\": 12152129, \"rent_price\": 0, \"address\": \"\u65b0\u51e4\u57ce\uff08\u94f6\u5ea7\u516c\u5bd3\uff09\u4f4d\u4e8e\u6768\u6d66\u533a\u63a7\u6c5f\u8def1498\u53f7\", \"latitude\": 3127752, \"group_id\": 1487267506, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/452/570/5340677.jpg\"}, {\"source_count\": 33, \"name\": \"\u91d1\u9e4f\u82b1\u56ed\", \"house_price\": 23173, \"longitude\": 12152659, \"rent_price\": 3550, \"address\": \"\u5170\u5dde\u8def1100\u5f04 \u5170\u5dde\u8def1101\u5f04\", \"latitude\": 3126674, \"group_id\": 1166648431, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/306/377/156782.jpg\"}, {\"source_count\": 11, \"name\": \"\u7533\u901a\u516c\u5bd3\", \"house_price\": 24390, \"longitude\": 12152832, \"rent_price\": 2800, \"address\": \"\u957f\u9633\u8def1315\u5f04\", \"latitude\": 3126690, \"group_id\": 3514399758, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/61/896/171391.jpg\"}, {\"source_count\": 14, \"name\": \"\u4e2d\u901a\u5927\u53a6\", \"house_price\": 18147, \"longitude\": 12153217, \"rent_price\": 3500, \"address\": \"\u6768\u6d66\u533a \u9ec4\u5174\u8def1\u53f7\uff08\u8fd1\u957f\u9633\u8def\u53e3\uff09\", \"latitude\": 3126968, \"group_id\": 3875757336, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/672/754/160321.jpg\"}, {\"source_count\": 20, \"name\": \"\u63a7\u6c5f\u4e8c\u6751\", \"house_price\": 22560, \"longitude\": 12152494, \"rent_price\": 2600, \"address\": \"\u63a7\u6c5f\u8def\u9756\u5b87\u5357\u8def\", \"latitude\": 3128003, \"group_id\": 2718586879, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/866/271/150175.jpg\"}, {\"source_count\": 30, \"name\": \"\u957f\u9633\u65b0\u82d1\", \"house_price\": 28694, \"longitude\": 12152847, \"rent_price\": 3650, \"address\": \"\u5146\u4e30\u8679\u6865\u516c\u5bd3\u4f4d\u4e8e\u5f90\u6c47\u4e2d\u5c71\u897f\u8def1790\u53f7\", \"latitude\": 3126679, \"group_id\": 771861864, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/612/330/177532.jpg\"}], \"total_number\": 1276, \"offset\": 22}";
			boolean flag1;
			if(isSuccessful(s3))
			{
			    ThreadHandler threadhandler = handler;
			    Message message = getMessageByContent(s3);
			    boolean flag = threadhandler.sendMessage(message);
			} else
			{
			    flag1 = handler.sendEmptyMessage(3);
			}
		} catch (Exception e) {
			boolean flag2 = handler.sendEmptyMessage(3);
			e.printStackTrace();
		}
        
	}
}
