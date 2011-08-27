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
			
			String s3 = "{\"message\": \"success\", \"data\": [{\"source_count\": 76, \"name\": \"\u5bcc\u71d5\u65b0\u6751\", \"house_price\": 7500, \"longitude\": 11597916, \"rent_price\": 1287, \"address\": \"\u623f\u5c71\u533a\u57ce\u5173\u4e07\u5b81\u6865\u73af\u5c9b\u5317\", \"latitude\": 3971615, \"group_id\": 1094027140, \"thumbnail\": \"/image/no_image.jpg\"}, {\"source_count\": 164, \"name\": \"\u661f\u6cb3\u82d1\", \"house_price\": 26767, \"longitude\": 11636783, \"rent_price\": 4079, \"address\": \"\u623f\u5c71\u533a\u57ce\u5173\u9547\", \"latitude\": 3983773, \"group_id\": 2542661804, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/16/655/13030489118232318037.jpg\"}, {\"source_count\": 65, \"name\": \"\u6a21\u5f0f\u53e3\u5317\u91cc\", \"house_price\": 18133, \"longitude\": 11616850, \"rent_price\": 2475, \"address\": \"\u82f9\u679c\u56ed\u5730\u94c1\u897f\u6a21\u5f0f\u53e3\u5927\u8857\", \"latitude\": 3993559, \"group_id\": 1829901001, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/554/746/9448400364580418563.jpg\"}, {\"source_count\": 544, \"name\": \"\u6d77\u7279\u82b1\u56ed\", \"house_price\": 19333, \"longitude\": 11618357, \"rent_price\": 2841, \"address\": \"\u5b9e\u5174\u897f\u8857\u897f\u4e95\u8def\u4ea4\u53c9\u53e3\u5f80\u4e1c200\u7c73\", \"latitude\": 3993313, \"group_id\": 2310278235, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/266/899/5548732478509440348.jpg\"}, {\"source_count\": 378, \"name\": \"\u516b\u89d2\u5357\u91cc\", \"house_price\": 20508, \"longitude\": 11620148, \"rent_price\": 2697, \"address\": \"\u516b\u89d2\u5357\u8def\u4e0e\u516b\u89d2\u897f\u8857\u4ea4\u53e3\u4e1c\u5357\u89d2\", \"latitude\": 3990997, \"group_id\": 1879536813, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/759/599/6633168069949324124.jpg\"}, {\"source_count\": 81, \"name\": \"\u77f3\u666f\u5c71\u6768\u5e84\u5c0f\u533a\", \"house_price\": 19274, \"longitude\": 11619847, \"rent_price\": 2483, \"address\": \"\u6768\u5e84\u9996\u94a2\", \"latitude\": 3992116, \"group_id\": 1620467382, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/553/146/17434497632943261054.jpg\"}, {\"source_count\": 403, \"name\": \"\u516b\u89d2\u4e2d\u91cc\", \"house_price\": 20780, \"longitude\": 11620221, \"rent_price\": 2613, \"address\": \"\u516b\u89d2\u4e1c\u8857\u8def\u53e3\u4ee5\u897f50\u7c73\u8def\u5317\", \"latitude\": 3991355, \"group_id\": 3643825431, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/882/771/12009835744458012685.jpg\"}, {\"source_count\": 476, \"name\": \"\u4e03\u661f\u56ed\", \"house_price\": 21158, \"longitude\": 11621350, \"rent_price\": 3070, \"address\": \"\u9c81\u8c37\u5357\u8def\u4e0e\u7fe0\u56ed\u897f\u8857\u8def\u53e3\u4ee5\u5357\u7ea650\u7c73\", \"latitude\": 3989983, \"group_id\": 4077108482, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/229/618/3847884624594327544.jpg\"}, {\"source_count\": 320, \"name\": \"\u96cd\u666f\u56db\u5b63\", \"house_price\": 23437, \"longitude\": 11620402, \"rent_price\": 3980, \"address\": \"\u77f3\u666f\u5c71\u533a\u897f\u4e94\u73af\u8fb9\u7530\u6751\u8def\u897f\u9ec4\u6751\", \"latitude\": 3993373, \"group_id\": 175928179, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/280/874/4797816913622980148.jpg\"}, {\"source_count\": 434, \"name\": \"\u4f9d\u7fe0\u56ed\", \"house_price\": 21221, \"longitude\": 11621598, \"rent_price\": 3256, \"address\": \"\u5317\u4eac\u5e02\u77f3\u666f\u5c71\u533a\u4f9d\u7fe0\u56ed14\u53f7\u697c\", \"latitude\": 3990071, \"group_id\": 343669318, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/410/488/15467906535224713961.jpg\"}, {\"source_count\": 331, \"name\": \"\u8001\u5c71\u897f\u91cc\", \"house_price\": 22602, \"longitude\": 11621842, \"rent_price\": 2503, \"address\": \"\u77f3\u666f\u5c71\u8def\u77f3\u666f\u5c71\u534e\u8054\u5546\u53a6\u5bf9\u8fc7\", \"latitude\": 3990951, \"group_id\": 704227579, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/867/74/5324677475049104744.jpg\"}, {\"source_count\": 287, \"name\": \"\u4e94\u82b3\u56ed\", \"house_price\": 21183, \"longitude\": 11622601, \"rent_price\": 2550, \"address\": \"\u9c81\u8c37\u5927\u8857\u7126\u5bb6\u575f\u8def\u53e3\u5357\u8f66\u7ad9\", \"latitude\": 3989870, \"group_id\": 2966852953, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/362/176/16213564912638774923.jpg\"}, {\"source_count\": 240, \"name\": \"\u77f3\u666f\u5c71\u4e07\u8fbe\u5e7f\u573a\", \"house_price\": 22822, \"longitude\": 11622546, \"rent_price\": 5046, \"address\": \"\u77f3\u666f\u5c71\u8def\u4e0e\u9c81\u8c37\u5927\u8857\u4ea4\u6c47\u5904\u897f\u5357\u89d2\", \"latitude\": 3990573, \"group_id\": 1320658215, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/634/552/1216099967839170178.jpg\"}, {\"source_count\": 1281, \"name\": \"\u8fdc\u6d0b\u5c71\u6c34\", \"house_price\": 27451, \"longitude\": 11623758, \"rent_price\": 4246, \"address\": \"\u77f3\u666f\u5c71\u533a\u516b\u5b9d\u5c71\u5730\u94c1\u5357200\u7c73\u7389\u6cc9\u897f\u91cc\", \"latitude\": 3990209, \"group_id\": 3957315980, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/547/50/15832311526915851514.jpg\"}, {\"source_count\": 119, \"name\": \"\u5929\u9e3f\u7f8e\u57df\", \"house_price\": 26669, \"longitude\": 11625108, \"rent_price\": 3840, \"address\": \"\u4e30\u53f0\u533a \u5c0f\u5c6f\u8def149\u53f7\uff08\u7389\u6cc9\u8def\u8def\u53e3\u5411\u53573500\u7c73\uff09\", \"latitude\": 3987468, \"group_id\": 332271060, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/341/715/7650392859192593113.jpg\"}, {\"source_count\": 259, \"name\": \"\u73e0\u6c5f\u5cf0\u666f\", \"house_price\": 24872, \"longitude\": 11626616, \"rent_price\": 4064, \"address\": \"\u4e30\u53f0\u533a \u9752\u5854\u897f\u8def58\u53f7\", \"latitude\": 3987963, \"group_id\": 2421843009, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/829/467/10403273518948344866.jpg\"}, {\"source_count\": 352, \"name\": \"\u9752\u5854\u851a\u56ed\", \"house_price\": 20103, \"longitude\": 11626439, \"rent_price\": 2605, \"address\": \"\u9752\u5854\u897f\u8def\u548c\u83b2\u82b1\u6c60\u897f\u8def\u4ea4\u53e3\", \"latitude\": 3989430, \"group_id\": 3395936233, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/634/696/12389496318858663673.jpg\"}, {\"source_count\": 287, \"name\": \"\u4fdd\u5229\u897f\u5c71\u6797\u8bed\", \"house_price\": 25725, \"longitude\": 11621519, \"rent_price\": 2930, \"address\": \"\u6d77\u6dc0\u533a \u897f\u5317\u65fa\u9547\u4e0a\u5e84\u8def\u4e0e\u9ed1\u9f99\u6f6d\u8def\u4ea4\u53c9\u53e3\u897f\u884c\u7ea6400\u7c73\", \"latitude\": 4003863, \"group_id\": 1163576367, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/797/103/13433429535839927256.jpg\"}, {\"source_count\": 5, \"name\": \"\u65f6\u4ee3\u8d22\u5bcc\u5929\u5730\", \"house_price\": 21831, \"longitude\": 11628735, \"rent_price\": 0, \"address\": \"\u4e30\u53f0\u822a\u4e30\u8def\u4e00\u53f7(\u90ae\u7f16\uff1a100070)\", \"latitude\": 3983197, \"group_id\": 1439010248, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/866/506/16831757113106699727.jpg\"}, {\"source_count\": 322, \"name\": \"\u9752\u5854\u4e1c\u91cc\", \"house_price\": 22673, \"longitude\": 11626942, \"rent_price\": 3056, \"address\": \"\u9752\u5854\u897f\u8def\u4e1c\u9762\uff0c\u4e94\u68f5\u677e\u5730\u94c1\u53e3\u5f80\u5357200\u7c73\", \"latitude\": 3989478, \"group_id\": 871147770, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/385/336/721493112658304858.jpg\"}], \"total_number\": 0, \"offset\": 24}";
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
