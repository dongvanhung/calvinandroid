package com.rent.exchange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.common.LocationAgent;
import com.rent.exchange.common.Network;
import com.rent.exchange.common.UmengHelper;
import com.rent.exchange.model.AdvertiserConfig;
import com.rent.listener.ExchangeDataRequestListener;

public class ExchangeDataService {

	public ExchangeDataService() {
	}

	private static String[] NetworkAccessMode(Context context1) {
		String as[];
		PackageManager packagemanager;
		String s;
		as = new String[2];
		as[0] = "Unknown";
		as[1] = "Unknown";
		packagemanager = context1.getPackageManager();
		s = context1.getPackageName();
		if (packagemanager.checkPermission(
				"android.permission.ACCESS_NETWORK_STATE", s) == 0) {
			ConnectivityManager connectivitymanager = (ConnectivityManager) context1
					.getSystemService("connectivity");
			if (connectivitymanager == null) {
				as[0] = "Unknown";
			} else {
				State i = connectivitymanager.getNetworkInfo(1).getState();
				State j = android.net.NetworkInfo.State.CONNECTED;
				if (i == j) {
					as[0] = "Wi-Fi";
				} else {
					NetworkInfo networkinfo = connectivitymanager
							.getNetworkInfo(0);
					State k = networkinfo.getState();
					State l = android.net.NetworkInfo.State.CONNECTED;
					if (k == l) {
						as[0] = "2G/3G";
						String s1 = networkinfo.getSubtypeName();
						as[1] = s1;
					}
				}
			}
		} else {
			as[0] = "Unknown";
		}
		return as;
	}

	private static ArrayList convertToAdvertisers(JSONObject jsonobject) {
		ArrayList arraylist = new ArrayList();
		try {
			if (jsonobject != null) {
				String s = jsonobject.getString("status");
				if (jsonobject.has("filter")) {
					boolean flag;
					String s1;
					JSONArray jsonarray;
					int j;
					int k;
					AdvertiserConfig advertiserconfig;
					boolean flag1;
					if (jsonobject.getString("filter").equals("1"))
						flag = true;
					else
						flag = false;
					ExchangeConstants.filterInstalledApp = flag;
				}
				if (jsonobject.has("show_size")) {
					String s1 = jsonobject.getString("show_size");
					if (s1 != null)
						if ("1".equals(s1))
							ExchangeConstants.show_size = true;
						else
							ExchangeConstants.show_size = false;
				}
				if (!"1".equals(s)) {
				} else {
					JSONArray jsonarray = jsonobject.getJSONArray("promoters");
					int j = 0;
					int k = jsonarray.length();
					if (j < k) {
						AdvertiserConfig advertiserconfig = getAdvertiserFromJson((JSONObject) jsonarray
								.get(j));
						arraylist.add(advertiserconfig);
						j++;
					}
				}
			} else {
				int i = Log.e(ExchangeConstants.LOG_TAG, "failed requesting");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arraylist;
	}

	public static void filter()
	    {
	       /* int i;
	        int j;
	        i = ExchangeConstants.REQUEST_NUMBER;
	        j = minimal_ad_count;
	        if(i <= j || mAdvertisers == null) {} else {
	        int k;
	        int l;
	        k = mAdvertisers.size();
	        l = minimal_ad_count;
	        if(k > l) {
	        	 Set set;
	 	        StringBuilder stringbuilder;
	 	        int i1;
	 	        int j1;
	 	        set = DeviceManager.getInstalledPackages(context);
	 	        stringbuilder = new StringBuilder("");
	 	        i1 = 0;
	 	        j1 = mAdvertisers.size() - 1;
	 	     }
	        return;
	       
	_L7:
	        if(j1 >= 0) {
	        	int l1;
		        int i2;
		        l1 = mAdvertisers.size();
		        i2 = minimal_ad_count;	
		        if(l1 <= i2) {
		        	String s = ExchangeConstants.LOG_TAG;
			        String s1 = String.valueOf(i1);
			        StringBuilder stringbuilder1 = (new StringBuilder(s1)).append(" requested apps are filtered, including:");
			        String s2 = stringbuilder.toString();
			        String s3 = stringbuilder1.append(s2).toString();
			        int k1 = Log.i(s, s3);
		        }
	        } else 
	_L4:
	        
	          goto _L2
	_L5:
	        
	         goto _L4; else goto _L6
	_L6:
	        AdvertiserConfig advertiserconfig = (AdvertiserConfig)mAdvertisers.get(j1);
	        String s4 = advertiserconfig.packageName;
	        if(set.contains(s4))
	        {
	            String s5 = String.valueOf(advertiserconfig.packageName);
	            String s6 = (new StringBuilder(s5)).append(",").toString();
	            StringBuilder stringbuilder2 = stringbuilder.append(s6);
	            i1++;
	            Object obj = mAdvertisers.remove(j1);
	        }
	        j1--;
	          goto _L7*/
	    }

	public static AdvertiserConfig getAd(int i) {
		AdvertiserConfig advertiserconfig;
		if (hasData())
			advertiserconfig = (AdvertiserConfig) mAdvertisers.get(i);
		else
			advertiserconfig = null;
		return advertiserconfig;
	}

	static AdvertiserConfig getAdvertiserFromJson(JSONObject jsonobject)
	    {
		AdvertiserConfig advertiserconfig = new AdvertiserConfig();
		return advertiserconfig;
	        /*AdvertiserConfig advertiserconfig = new AdvertiserConfig();
	        if(jsonobject.has("title"))
	        {
	            String s = jsonobject.getString("title");
	            advertiserconfig.adName = s;
	        }
	        if(jsonobject.has("ad_words"))
	        {
	            String s1 = jsonobject.getString("ad_words");
	            advertiserconfig.adDescription = s1;
	        }
	        if(jsonobject.has("description"))
	        {
	            String s2 = jsonobject.getString("description");
	            advertiserconfig.adDetail = s2;
	        }
	        if(jsonobject.has("promoter"))
	        {
	            String s3 = jsonobject.getString("promoter");
	            advertiserconfig.appkey = s3;
	        }
	        if(jsonobject.has("category"))
	        {
	            String s4 = jsonobject.getString("category");
	            advertiserconfig.category = s4;
	        }
	        if(jsonobject.has("size"))
	        {
	            long l = jsonobject.getLong("size");
	            advertiserconfig.fileSize = l;
	        }
	        if(!jsonobject.has("url")) goto _L2; else goto _L1
	_L1:
	        String s5 = jsonobject.getString("url");
	        if(!s5.toLowerCase().contains("http")) goto _L4; else goto _L3
	_L3:
	        String s6 = s5;
	_L5:
	        advertiserconfig.apk = s6;
	_L2:
	        if(jsonobject.has("provider"))
	        {
	            String s7 = jsonobject.getString("provider");
	            advertiserconfig.provider = s7;
	        }
	        if(jsonobject.has("app_package_name"))
	        {
	            String s8 = jsonobject.getString("app_package_name");
	            advertiserconfig.packageName = s8;
	        }
	        if(jsonobject.has("landing_type"))
	        {
	            int i = jsonobject.getInt("landing_type");
	            advertiserconfig.landingType = i;
	        }
	        if(jsonobject.has("icon"))
	        {
	            String s9 = String.valueOf(ExchangeConstants.BASE_URL);
	            StringBuilder stringbuilder = new StringBuilder(s9);
	            String s10 = jsonobject.getString("icon");
	            String s11 = stringbuilder.append(s10).toString();
	            advertiserconfig.adIconUrl = s11;
	        }
	_L6:
	        return advertiserconfig;
	_L4:
	        String s13;
	        String s12 = String.valueOf(ExchangeConstants.BASE_URL);
	        s13 = (new StringBuilder(s12)).append(s5).toString();
	        s6 = s13;
	          goto _L5
	        printStackTrace();
	          goto _L6*/
	    }

	public static AdvertiserConfig getCurAd() {
		AdvertiserConfig advertiserconfig;
		if (hasData()) {
			List list = mAdvertisers;
			int i = curIndex;
			advertiserconfig = (AdvertiserConfig) list.get(i);
		} else {
			advertiserconfig = null;
		}
		return advertiserconfig;
	}

	private static String getDateTime() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return simpledateformat.format(date);
	}

	public static List getExampleAds(int i)
	    {
		ArrayList arraylist = new ArrayList();
		return arraylist;
	       /* ArrayList arraylist = new ArrayList();
	        i;
	        JVM INSTR tableswitch 0 7: default 56
	    //                   0 62
	    //                   1 557
	    //                   2 1056
	    //                   3 1555
	    //                   4 2054
	    //                   5 2553
	    //                   6 3176
	    //                   7 3851;
	           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
	_L1:
	        mAdvertisers = arraylist;
	        return arraylist;
	_L2:
	        String s = ExchangeConstants.title[0];
	        String s1 = ExchangeConstants.des[0];
	        String s2 = ExchangeConstants.detailDes[0];
	        int j = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig = new AdvertiserConfig(s, s1, s2, 0x27100L, "1.1", j);
	        boolean flag = arraylist.add(advertiserconfig);
	        String s3 = ExchangeConstants.title[1];
	        String s4 = ExchangeConstants.des[1];
	        String s5 = ExchangeConstants.detailDes[1];
	        int k = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig1 = new AdvertiserConfig(s3, s4, s5, 0x27100L, "1.1", k);
	        boolean flag1 = arraylist.add(advertiserconfig1);
	        String s6 = ExchangeConstants.title[2];
	        String s7 = ExchangeConstants.des[2];
	        String s8 = ExchangeConstants.detailDes[2];
	        int l = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig2 = new AdvertiserConfig(s6, s7, s8, 0x27100L, "1.1", l);
	        boolean flag2 = arraylist.add(advertiserconfig2);
	        String s9 = ExchangeConstants.title[3];
	        String s10 = ExchangeConstants.des[3];
	        String s11 = ExchangeConstants.detailDes[3];
	        int i1 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig3 = new AdvertiserConfig(s9, s10, s11, 0x27100L, "1.1", i1);
	        boolean flag3 = arraylist.add(advertiserconfig3);
	        String s12 = ExchangeConstants.title[4];
	        String s13 = ExchangeConstants.des[4];
	        String s14 = ExchangeConstants.detailDes[4];
	        int j1 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig4 = new AdvertiserConfig(s12, s13, s14, 0x27100L, "1.1", j1);
	        boolean flag4 = arraylist.add(advertiserconfig4);
	        String s15 = ExchangeConstants.title[5];
	        String s16 = ExchangeConstants.des[5];
	        String s17 = ExchangeConstants.detailDes[5];
	        int k1 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig5 = new AdvertiserConfig(s15, s16, s17, 0x27100L, "1.1", k1);
	        boolean flag5 = arraylist.add(advertiserconfig5);
	        String s18 = ExchangeConstants.title[6];
	        String s19 = ExchangeConstants.des[6];
	        String s20 = ExchangeConstants.detailDes[6];
	        int l1 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig6 = new AdvertiserConfig(s18, s19, s20, 0x27100L, "1.1", l1);
	        boolean flag6 = arraylist.add(advertiserconfig6);
	        String s21 = ExchangeConstants.title[7];
	        String s22 = ExchangeConstants.des[7];
	        String s23 = ExchangeConstants.detailDes[7];
	        int i2 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig7 = new AdvertiserConfig(s21, s22, s23, 0x27100L, "1.1", i2);
	        boolean flag7 = arraylist.add(advertiserconfig7);
	        continue;  Loop/switch isn't completed 
	_L3:
	        String s24 = ExchangeConstants.title[1];
	        String s25 = ExchangeConstants.des[1];
	        String s26 = ExchangeConstants.detailDes[1];
	        int j2 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig8 = new AdvertiserConfig(s24, s25, s26, 0x27100L, "1.1", j2);
	        boolean flag8 = arraylist.add(advertiserconfig8);
	        String s27 = ExchangeConstants.title[2];
	        String s28 = ExchangeConstants.des[2];
	        String s29 = ExchangeConstants.detailDes[2];
	        int k2 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig9 = new AdvertiserConfig(s27, s28, s29, 0x27100L, "1.1", k2);
	        boolean flag9 = arraylist.add(advertiserconfig9);
	        String s30 = ExchangeConstants.title[0];
	        String s31 = ExchangeConstants.des[0];
	        String s32 = ExchangeConstants.detailDes[0];
	        int l2 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig10 = new AdvertiserConfig(s30, s31, s32, 0x27100L, "1.1", l2);
	        boolean flag10 = arraylist.add(advertiserconfig10);
	        String s33 = ExchangeConstants.title[7];
	        String s34 = ExchangeConstants.des[7];
	        String s35 = ExchangeConstants.detailDes[7];
	        int i3 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig11 = new AdvertiserConfig(s33, s34, s35, 0x27100L, "1.1", i3);
	        boolean flag11 = arraylist.add(advertiserconfig11);
	        String s36 = ExchangeConstants.title[6];
	        String s37 = ExchangeConstants.des[6];
	        String s38 = ExchangeConstants.detailDes[6];
	        int j3 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig12 = new AdvertiserConfig(s36, s37, s38, 0x27100L, "1.1", j3);
	        boolean flag12 = arraylist.add(advertiserconfig12);
	        String s39 = ExchangeConstants.title[5];
	        String s40 = ExchangeConstants.des[5];
	        String s41 = ExchangeConstants.detailDes[5];
	        int k3 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig13 = new AdvertiserConfig(s39, s40, s41, 0x27100L, "1.1", k3);
	        boolean flag13 = arraylist.add(advertiserconfig13);
	        String s42 = ExchangeConstants.title[4];
	        String s43 = ExchangeConstants.des[4];
	        String s44 = ExchangeConstants.detailDes[4];
	        int l3 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig14 = new AdvertiserConfig(s42, s43, s44, 0x27100L, "1.1", l3);
	        boolean flag14 = arraylist.add(advertiserconfig14);
	        String s45 = ExchangeConstants.title[3];
	        String s46 = ExchangeConstants.des[3];
	        String s47 = ExchangeConstants.detailDes[3];
	        int i4 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig15 = new AdvertiserConfig(s45, s46, s47, 0x27100L, "1.1", i4);
	        boolean flag15 = arraylist.add(advertiserconfig15);
	        continue;  Loop/switch isn't completed 
	_L4:
	        String s48 = ExchangeConstants.title[2];
	        String s49 = ExchangeConstants.des[2];
	        String s50 = ExchangeConstants.detailDes[2];
	        int j4 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig16 = new AdvertiserConfig(s48, s49, s50, 0x27100L, "1.1", j4);
	        boolean flag16 = arraylist.add(advertiserconfig16);
	        String s51 = ExchangeConstants.title[4];
	        String s52 = ExchangeConstants.des[4];
	        String s53 = ExchangeConstants.detailDes[4];
	        int k4 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig17 = new AdvertiserConfig(s51, s52, s53, 0x27100L, "1.1", k4);
	        boolean flag17 = arraylist.add(advertiserconfig17);
	        String s54 = ExchangeConstants.title[1];
	        String s55 = ExchangeConstants.des[1];
	        String s56 = ExchangeConstants.detailDes[1];
	        int l4 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig18 = new AdvertiserConfig(s54, s55, s56, 0x27100L, "1.1", l4);
	        boolean flag18 = arraylist.add(advertiserconfig18);
	        String s57 = ExchangeConstants.title[3];
	        String s58 = ExchangeConstants.des[3];
	        String s59 = ExchangeConstants.detailDes[3];
	        int i5 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig19 = new AdvertiserConfig(s57, s58, s59, 0x27100L, "1.1", i5);
	        boolean flag19 = arraylist.add(advertiserconfig19);
	        String s60 = ExchangeConstants.title[6];
	        String s61 = ExchangeConstants.des[6];
	        String s62 = ExchangeConstants.detailDes[6];
	        int j5 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig20 = new AdvertiserConfig(s60, s61, s62, 0x27100L, "1.1", j5);
	        boolean flag20 = arraylist.add(advertiserconfig20);
	        String s63 = ExchangeConstants.title[5];
	        String s64 = ExchangeConstants.des[5];
	        String s65 = ExchangeConstants.detailDes[5];
	        int k5 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig21 = new AdvertiserConfig(s63, s64, s65, 0x27100L, "1.1", k5);
	        boolean flag21 = arraylist.add(advertiserconfig21);
	        String s66 = ExchangeConstants.title[7];
	        String s67 = ExchangeConstants.des[7];
	        String s68 = ExchangeConstants.detailDes[7];
	        int l5 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig22 = new AdvertiserConfig(s66, s67, s68, 0x27100L, "1.1", l5);
	        boolean flag22 = arraylist.add(advertiserconfig22);
	        String s69 = ExchangeConstants.title[0];
	        String s70 = ExchangeConstants.des[0];
	        String s71 = ExchangeConstants.detailDes[0];
	        int i6 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig23 = new AdvertiserConfig(s69, s70, s71, 0x27100L, "1.1", i6);
	        boolean flag23 = arraylist.add(advertiserconfig23);
	        continue;  Loop/switch isn't completed 
	_L5:
	        String s72 = ExchangeConstants.title[3];
	        String s73 = ExchangeConstants.des[3];
	        String s74 = ExchangeConstants.detailDes[3];
	        int j6 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig24 = new AdvertiserConfig(s72, s73, s74, 0x27100L, "1.1", j6);
	        boolean flag24 = arraylist.add(advertiserconfig24);
	        String s75 = ExchangeConstants.title[1];
	        String s76 = ExchangeConstants.des[1];
	        String s77 = ExchangeConstants.detailDes[1];
	        int k6 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig25 = new AdvertiserConfig(s75, s76, s77, 0x27100L, "1.1", k6);
	        boolean flag25 = arraylist.add(advertiserconfig25);
	        String s78 = ExchangeConstants.title[0];
	        String s79 = ExchangeConstants.des[0];
	        String s80 = ExchangeConstants.detailDes[0];
	        int l6 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig26 = new AdvertiserConfig(s78, s79, s80, 0x27100L, "1.1", l6);
	        boolean flag26 = arraylist.add(advertiserconfig26);
	        String s81 = ExchangeConstants.title[5];
	        String s82 = ExchangeConstants.des[5];
	        String s83 = ExchangeConstants.detailDes[5];
	        int i7 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig27 = new AdvertiserConfig(s81, s82, s83, 0x27100L, "1.1", i7);
	        boolean flag27 = arraylist.add(advertiserconfig27);
	        String s84 = ExchangeConstants.title[4];
	        String s85 = ExchangeConstants.des[4];
	        String s86 = ExchangeConstants.detailDes[4];
	        int j7 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig28 = new AdvertiserConfig(s84, s85, s86, 0x27100L, "1.1", j7);
	        boolean flag28 = arraylist.add(advertiserconfig28);
	        String s87 = ExchangeConstants.title[2];
	        String s88 = ExchangeConstants.des[2];
	        String s89 = ExchangeConstants.detailDes[2];
	        int k7 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig29 = new AdvertiserConfig(s87, s88, s89, 0x27100L, "1.1", k7);
	        boolean flag29 = arraylist.add(advertiserconfig29);
	        String s90 = ExchangeConstants.title[6];
	        String s91 = ExchangeConstants.des[6];
	        String s92 = ExchangeConstants.detailDes[6];
	        int l7 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig30 = new AdvertiserConfig(s90, s91, s92, 0x27100L, "1.1", l7);
	        boolean flag30 = arraylist.add(advertiserconfig30);
	        String s93 = ExchangeConstants.title[7];
	        String s94 = ExchangeConstants.des[7];
	        String s95 = ExchangeConstants.detailDes[7];
	        int i8 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig31 = new AdvertiserConfig(s93, s94, s95, 0x27100L, "1.1", i8);
	        boolean flag31 = arraylist.add(advertiserconfig31);
	        continue;  Loop/switch isn't completed 
	_L6:
	        String s96 = ExchangeConstants.title[4];
	        String s97 = ExchangeConstants.des[4];
	        String s98 = ExchangeConstants.detailDes[4];
	        int j8 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig32 = new AdvertiserConfig(s96, s97, s98, 0x27100L, "1.1", j8);
	        boolean flag32 = arraylist.add(advertiserconfig32);
	        String s99 = ExchangeConstants.title[3];
	        String s100 = ExchangeConstants.des[3];
	        String s101 = ExchangeConstants.detailDes[3];
	        int k8 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig33 = new AdvertiserConfig(s99, s100, s101, 0x27100L, "1.1", k8);
	        boolean flag33 = arraylist.add(advertiserconfig33);
	        String s102 = ExchangeConstants.title[2];
	        String s103 = ExchangeConstants.des[2];
	        String s104 = ExchangeConstants.detailDes[2];
	        int l8 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig34 = new AdvertiserConfig(s102, s103, s104, 0x27100L, "1.1", l8);
	        boolean flag34 = arraylist.add(advertiserconfig34);
	        String s105 = ExchangeConstants.title[1];
	        String s106 = ExchangeConstants.des[1];
	        String s107 = ExchangeConstants.detailDes[1];
	        int i9 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig35 = new AdvertiserConfig(s105, s106, s107, 0x27100L, "1.1", i9);
	        boolean flag35 = arraylist.add(advertiserconfig35);
	        String s108 = ExchangeConstants.title[5];
	        String s109 = ExchangeConstants.des[5];
	        String s110 = ExchangeConstants.detailDes[5];
	        int j9 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig36 = new AdvertiserConfig(s108, s109, s110, 0x27100L, "1.1", j9);
	        boolean flag36 = arraylist.add(advertiserconfig36);
	        String s111 = ExchangeConstants.title[0];
	        String s112 = ExchangeConstants.des[0];
	        String s113 = ExchangeConstants.detailDes[0];
	        int k9 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig37 = new AdvertiserConfig(s111, s112, s113, 0x27100L, "1.1", k9);
	        boolean flag37 = arraylist.add(advertiserconfig37);
	        String s114 = ExchangeConstants.title[7];
	        String s115 = ExchangeConstants.des[7];
	        String s116 = ExchangeConstants.detailDes[7];
	        int l9 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig38 = new AdvertiserConfig(s114, s115, s116, 0x27100L, "1.1", l9);
	        boolean flag38 = arraylist.add(advertiserconfig38);
	        String s117 = ExchangeConstants.title[6];
	        String s118 = ExchangeConstants.des[6];
	        String s119 = ExchangeConstants.detailDes[6];
	        int i10 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig39 = new AdvertiserConfig(s117, s118, s119, 0x27100L, "1.1", i10);
	        boolean flag39 = arraylist.add(advertiserconfig39);
	        continue;  Loop/switch isn't completed 
	_L7:
	        String s120 = ExchangeConstants.title[5];
	        String s121 = ExchangeConstants.des[5];
	        String s122 = ExchangeConstants.detailDes[5];
	        int j10 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig40 = new AdvertiserConfig(s120, s121, s122, 0x27100L, "1.1", j10);
	        boolean flag40 = arraylist.add(advertiserconfig40);
	        String s123 = ExchangeConstants.title[3];
	        String s124 = ExchangeConstants.des[3];
	        String s125 = ExchangeConstants.detailDes[3];
	        int k10 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig41 = new AdvertiserConfig(s123, s124, s125, 0x27100L, "1.1", k10);
	        boolean flag41 = arraylist.add(advertiserconfig41);
	        String s126 = ExchangeConstants.title[2];
	        String s127 = ExchangeConstants.des[2];
	        String s128 = ExchangeConstants.detailDes[2];
	        int l10 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig42 = new AdvertiserConfig(s126, s127, s128, 0x27100L, "1.1", l10);
	        boolean flag42 = arraylist.add(advertiserconfig42);
	        String s129 = ExchangeConstants.title[1];
	        String s130 = ExchangeConstants.des[1];
	        String s131 = ExchangeConstants.detailDes[1];
	        int i11 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig43 = new AdvertiserConfig(s129, s130, s131, 0x27100L, "1.1", i11);
	        boolean flag43 = arraylist.add(advertiserconfig43);
	        String s132 = ExchangeConstants.title[0];
	        String s133 = ExchangeConstants.des[0];
	        String s134 = ExchangeConstants.detailDes[0];
	        int j11 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig44 = new AdvertiserConfig(s132, s133, s134, 0x27100L, "1.1", j11);
	        boolean flag44 = arraylist.add(advertiserconfig44);
	        String s135 = ExchangeConstants.title[6];
	        String s136 = ExchangeConstants.des[6];
	        String s137 = ExchangeConstants.detailDes[6];
	        int k11 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig45 = new AdvertiserConfig(s135, s136, s137, 0x27100L, "1.1", k11);
	        boolean flag45 = arraylist.add(advertiserconfig45);
	        String s138 = ExchangeConstants.title[7];
	        String s139 = ExchangeConstants.des[7];
	        String s140 = ExchangeConstants.detailDes[7];
	        int l11 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig46 = new AdvertiserConfig(s138, s139, s140, 0x27100L, "1.1", l11);
	        boolean flag46 = arraylist.add(advertiserconfig46);
	        String s141 = ExchangeConstants.title[4];
	        String s142 = ExchangeConstants.des[4];
	        String s143 = ExchangeConstants.detailDes[4];
	        int i12 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig47 = new AdvertiserConfig(s141, s142, s143, 0x27100L, "1.1", i12);
	        boolean flag47 = arraylist.add(advertiserconfig47);
	        continue;  Loop/switch isn't completed 
	_L8:
	        String s144 = ExchangeConstants.title[6];
	        String s145 = ExchangeConstants.des[6];
	        String s146 = ExchangeConstants.detailDes[6];
	        int j12 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig48 = new AdvertiserConfig(s144, s145, s146, 0x27100L, "1.1", j12);
	        boolean flag48 = arraylist.add(advertiserconfig48);
	        String s147 = ExchangeConstants.title[3];
	        String s148 = ExchangeConstants.des[3];
	        String s149 = ExchangeConstants.detailDes[3];
	        int k12 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig49 = new AdvertiserConfig(s147, s148, s149, 0x27100L, "1.1", k12);
	        boolean flag49 = arraylist.add(advertiserconfig49);
	        String s150 = ExchangeConstants.title[2];
	        String s151 = ExchangeConstants.des[2];
	        String s152 = ExchangeConstants.detailDes[2];
	        int l12 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig50 = new AdvertiserConfig(s150, s151, s152, 0x27100L, "1.1", l12);
	        boolean flag50 = arraylist.add(advertiserconfig50);
	        String s153 = ExchangeConstants.title[1];
	        String s154 = ExchangeConstants.des[1];
	        String s155 = ExchangeConstants.detailDes[1];
	        int i13 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig51 = new AdvertiserConfig(s153, s154, s155, 0x27100L, "1.1", i13);
	        boolean flag51 = arraylist.add(advertiserconfig51);
	        String s156 = ExchangeConstants.title[0];
	        String s157 = ExchangeConstants.des[0];
	        String s158 = ExchangeConstants.detailDes[0];
	        int j13 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig52 = new AdvertiserConfig(s156, s157, s158, 0x27100L, "1.1", j13);
	        boolean flag52 = arraylist.add(advertiserconfig52);
	        String s159 = ExchangeConstants.title[7];
	        String s160 = ExchangeConstants.des[7];
	        String s161 = ExchangeConstants.detailDes[7];
	        int k13 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig53 = new AdvertiserConfig(s159, s160, s161, 0x27100L, "1.1", k13);
	        boolean flag53 = arraylist.add(advertiserconfig53);
	        String s162 = ExchangeConstants.title[4];
	        String s163 = ExchangeConstants.des[4];
	        String s164 = ExchangeConstants.detailDes[4];
	        int l13 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig54 = new AdvertiserConfig(s162, s163, s164, 0x27100L, "1.1", l13);
	        boolean flag54 = arraylist.add(advertiserconfig54);
	        String s165 = ExchangeConstants.title[5];
	        String s166 = ExchangeConstants.des[5];
	        String s167 = ExchangeConstants.detailDes[5];
	        int i14 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig55 = new AdvertiserConfig(s165, s166, s167, 0x27100L, "1.1", i14);
	        boolean flag55 = arraylist.add(advertiserconfig55);
	        continue;  Loop/switch isn't completed 
	_L9:
	        String s168 = ExchangeConstants.title[7];
	        String s169 = ExchangeConstants.des[7];
	        String s170 = ExchangeConstants.detailDes[7];
	        int j14 = ExchangeConstants.appIcon[7];
	        AdvertiserConfig advertiserconfig56 = new AdvertiserConfig(s168, s169, s170, 0x27100L, "1.1", j14);
	        boolean flag56 = arraylist.add(advertiserconfig56);
	        String s171 = ExchangeConstants.title[3];
	        String s172 = ExchangeConstants.des[3];
	        String s173 = ExchangeConstants.detailDes[3];
	        int k14 = ExchangeConstants.appIcon[3];
	        AdvertiserConfig advertiserconfig57 = new AdvertiserConfig(s171, s172, s173, 0x27100L, "1.1", k14);
	        boolean flag57 = arraylist.add(advertiserconfig57);
	        String s174 = ExchangeConstants.title[4];
	        String s175 = ExchangeConstants.des[4];
	        String s176 = ExchangeConstants.detailDes[4];
	        int l14 = ExchangeConstants.appIcon[4];
	        AdvertiserConfig advertiserconfig58 = new AdvertiserConfig(s174, s175, s176, 0x27100L, "1.1", l14);
	        boolean flag58 = arraylist.add(advertiserconfig58);
	        String s177 = ExchangeConstants.title[2];
	        String s178 = ExchangeConstants.des[2];
	        String s179 = ExchangeConstants.detailDes[2];
	        int i15 = ExchangeConstants.appIcon[2];
	        AdvertiserConfig advertiserconfig59 = new AdvertiserConfig(s177, s178, s179, 0x27100L, "1.1", i15);
	        boolean flag59 = arraylist.add(advertiserconfig59);
	        String s180 = ExchangeConstants.title[1];
	        String s181 = ExchangeConstants.des[1];
	        String s182 = ExchangeConstants.detailDes[1];
	        int j15 = ExchangeConstants.appIcon[1];
	        AdvertiserConfig advertiserconfig60 = new AdvertiserConfig(s180, s181, s182, 0x27100L, "1.1", j15);
	        boolean flag60 = arraylist.add(advertiserconfig60);
	        String s183 = ExchangeConstants.title[0];
	        String s184 = ExchangeConstants.des[0];
	        String s185 = ExchangeConstants.detailDes[0];
	        int k15 = ExchangeConstants.appIcon[0];
	        AdvertiserConfig advertiserconfig61 = new AdvertiserConfig(s183, s184, s185, 0x27100L, "1.1", k15);
	        boolean flag61 = arraylist.add(advertiserconfig61);
	        String s186 = ExchangeConstants.title[5];
	        String s187 = ExchangeConstants.des[5];
	        String s188 = ExchangeConstants.detailDes[5];
	        int l15 = ExchangeConstants.appIcon[5];
	        AdvertiserConfig advertiserconfig62 = new AdvertiserConfig(s186, s187, s188, 0x27100L, "1.1", l15);
	        boolean flag62 = arraylist.add(advertiserconfig62);
	        String s189 = ExchangeConstants.title[6];
	        String s190 = ExchangeConstants.des[6];
	        String s191 = ExchangeConstants.detailDes[6];
	        int i16 = ExchangeConstants.appIcon[6];
	        AdvertiserConfig advertiserconfig63 = new AdvertiserConfig(s189, s190, s191, 0x27100L, "1.1", i16);
	        boolean flag63 = arraylist.add(advertiserconfig63);
	        if(true) goto _L1; else goto _L10
	_L10:*/
	    }

	private static Location getLocationOnce(Context context1) {
		return (new LocationAgent(context1)).getLocation();
	}

	private static JSONObject getReportInput(int i, int j, int k, List list)
	    {
		JSONObject jsonobject = new JSONObject();
		return jsonobject;
	        /*JSONObject jsonobject = new JSONObject();
	        JSONObject jsonobject1 = jsonobject.put("page_level", j);
	        JSONObject jsonobject2 = jsonobject.put("promotion_type", k);
	        j = (TelephonyManager)context.getSystemService("phone");
	        if(j != null) goto _L2; else goto _L1
	_L1:
	        int l = Log.w(ExchangeConstants.LOG_TAG, "No IMEI.");
	        i = null;
	_L5:
	        return i;
	_L2:
	        String s = j.getDeviceId();
	        JSONObject jsonobject3 = jsonobject.put("device_id", s);
	        String s1 = ExchangeConstants.APPKEY;
	        JSONObject jsonobject4 = jsonobject.put("publisher", s1);
	        String s2 = getDateTime();
	        String s3 = s2.split(" ")[0];
	        String s4 = s2.split(" ")[1];
	        JSONObject jsonobject5 = jsonobject.put("date", s3);
	        JSONObject jsonobject6 = jsonobject.put("time", s4);
	        j = Calendar.getInstance((new Configuration()).locale);
	        if(j == null) goto _L4; else goto _L3
	_L3:
	        JSONArray jsonarray;
	        int j1;
	        j = j.getTimeZone();
	        JSONObject jsonobject8;
	        JSONObject jsonobject9;
	        if(j != null)
	        {
	            int i1 = j.getRawOffset() / 0x36ee80;
	            JSONObject jsonobject7 = jsonobject.put("timezone", i1);
	        } else
	        {
	            jsonobject9 = jsonobject.put("timezone", 8);
	        }
	_L7:
	        jsonarray = new JSONArray();
	        j1 = list.size();
	        k = 0;
	_L8:
	        if(k < j1)
	            break MISSING_BLOCK_LABEL_278;
	        jsonobject8 = jsonobject.put("promoters", jsonarray);
	_L6:
	        i = jsonobject;
	          goto _L5
	        printStackTrace();
	          goto _L6
	_L4:
	        JSONObject jsonobject10 = jsonobject.put("timezone", 8);
	          goto _L7
	        AdvertiserConfig advertiserconfig = (AdvertiserConfig)list.get(k);
	        JSONObject jsonobject11 = new JSONObject();
	        String s5 = advertiserconfig.appkey;
	        JSONObject jsonobject12 = jsonobject11.put("promoter", s5);
	        String s6 = advertiserconfig.category;
	        JSONObject jsonobject13 = jsonobject11.put("category", s6);
	        int k1 = advertiserconfig.action;
	        JSONObject jsonobject14 = jsonobject11.put("action", k1);
	        JSONObject jsonobject15;
	        JSONArray jsonarray1;
	        JSONObject jsonobject16;
	        if(j1 > 1)
	            jsonobject15 = jsonobject11.put("action_index", k);
	        else
	            jsonobject16 = jsonobject11.put("action_index", i);
	        jsonarray1 = jsonarray.put(jsonobject11);
	        k++;
	          goto _L8*/
	    }

	private static JSONObject getRequestInput()
	    {
		JSONObject jsonobject = new JSONObject();
		return jsonobject;
	        /*JSONObject jsonobject = new JSONObject();
	        TelephonyManager telephonymanager;
	        String s = ExchangeConstants.keywords;
	        JSONObject jsonobject1 = jsonobject.put("keywords", s);
	        int i = ExchangeConstants.sdk_version;
	        JSONObject jsonobject2 = jsonobject.put("sdk_version", i);
	        telephonymanager = (TelephonyManager)context.getSystemService("phone");
	        if(telephonymanager != null) goto _L2; else goto _L1
	_L1:
	        Object obj;
	        int j = Log.w(ExchangeConstants.LOG_TAG, "No IMEI.");
	        obj = null;
	_L9:
	        return ((JSONObject) (obj));
	_L2:
	        String s1 = telephonymanager.getDeviceId();
	        JSONObject jsonobject3 = jsonobject.put("device_id", s1);
	        String s2 = Build.MODEL;
	        JSONObject jsonobject4 = jsonobject.put("device_model", s2);
	        ExchangeConstants.APPKEY = DeviceManager.getAppkey(context);
	        String s3 = ExchangeConstants.APPKEY;
	        JSONObject jsonobject5 = jsonobject.put("app_key", s3);
	        JSONObject jsonobject7;
	        try
	        {
	            PackageManager packagemanager = context.getPackageManager();
	            String s4 = context.getPackageName();
	            int k = packagemanager.getPackageInfo(s4, 0).versionCode;
	            JSONObject jsonobject6 = jsonobject.put("app_version", k);
	        }
	        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
	        {
	            jsonobject20 = jsonobject.put("app_version", "unknown");
	        }
	        jsonobject7 = jsonobject.put("os", "android");
	        obj = new Configuration();
	        android.provider.Settings.System.getConfiguration(context.getContentResolver(), ((Configuration) (obj)));
	        if(obj == null || ((Configuration) (obj)).locale == null) goto _L4; else goto _L3
	_L3:
	        String s5 = ((Configuration) (obj)).locale.getDisplayName();
	        JSONObject jsonobject8 = jsonobject.put("locale", s5);
	_L11:
	        obj = Calendar.getInstance(((Configuration) (obj)).locale);
	        if(obj == null) goto _L6; else goto _L5
	_L5:
	        obj = ((Calendar) (obj)).getTimeZone();
	        if(obj == null) goto _L8; else goto _L7
	_L7:
	        int l = ((TimeZone) (obj)).getRawOffset() / 0x36ee80;
	        JSONObject jsonobject9 = jsonobject.put("timezone", l);
	_L12:
	        String s12;
	        JSONObject jsonobject14;
	        String s13;
	        JSONObject jsonobject15;
	        String s14;
	        JSONObject jsonobject16;
	        String s15;
	        JSONObject jsonobject17;
	        JSONObject jsonobject18;
	        int j1;
	        JSONObject jsonobject19;
	        JSONObject jsonobject20;
	        Exception exception;
	        int k1;
	        JSONObject jsonobject21;
	        JSONObject jsonobject22;
	        JSONObject jsonobject23;
	        JSONObject jsonobject24;
	        try
	        {
	            DisplayMetrics displaymetrics = new DisplayMetrics();
	            ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
	            int i1 = displaymetrics.widthPixels;
	            String s6 = String.valueOf(String.valueOf(displaymetrics.heightPixels));
	            StringBuilder stringbuilder = (new StringBuilder(s6)).append("*");
	            String s7 = String.valueOf(i1);
	            String s8 = stringbuilder.append(s7).toString();
	            JSONObject jsonobject10 = jsonobject.put("resolution", s8);
	        }
	        catch(Exception exception1)
	        {
	            jsonobject24 = jsonobject.put("resolution", "Unknown");
	        }
	        JSONObject jsonobject25;
	        try
	        {
	            obj = NetworkAccessMode(context);
	            String s9 = obj[0];
	            JSONObject jsonobject11 = jsonobject.put("access", s9);
	            if(obj[0].equals("2G/3G"))
	            {
	                String s10 = obj[1];
	                JSONObject jsonobject12 = jsonobject.put("access_subtype", s10);
	            }
	        }
	        catch(Exception exception2)
	        {
	            jsonobject25 = jsonobject.put("access", "Unknown");
	        }
	        JSONObject jsonobject26;
	        try
	        {
	            String s11 = telephonymanager.getNetworkOperatorName();
	            JSONObject jsonobject13 = jsonobject.put("carrier", s11);
	        }
	        catch(Exception exception3)
	        {
	            jsonobject26 = jsonobject.put("carrier", "Unknown");
	        }
	        obj = getLocationOnce(context);
	        if(obj == null)
	            break MISSING_BLOCK_LABEL_707;
	        s12 = String.valueOf(((Location) (obj)).getLatitude());
	        jsonobject14 = jsonobject.put("lat", s12);
	        s13 = String.valueOf(((Location) (obj)).getLongitude());
	        jsonobject15 = jsonobject.put("lng", s13);
	_L13:
	        s14 = UmengHelper.getCPU();
	        jsonobject16 = jsonobject.put("cpu", s14);
	        s15 = (new Date()).toString();
	        jsonobject17 = jsonobject.put("time", s15);
	        jsonobject18 = jsonobject.put("is_banner", "true");
	        j1 = ExchangeConstants.REQUEST_NUMBER;
	        jsonobject19 = jsonobject.put("request_count", j1);
	_L10:
	        obj = jsonobject;
	          goto _L9
	        exception;
	        k1 = Log.w(ExchangeConstants.LOG_TAG, "ERROR GET INPUT.");
	          goto _L10
	_L4:
	        jsonobject21 = jsonobject.put("locale", "null");
	          goto _L11
	_L8:
	        jsonobject22 = jsonobject.put("timezone", 8);
	          goto _L12
	_L6:
	        jsonobject23 = jsonobject.put("timezone", 8);
	          goto _L12
	        JSONObject jsonobject27 = jsonobject.put("lat", 0D);
	        JSONObject jsonobject28 = jsonobject.put("lng", 0D);
	          goto _L13*/
	    }

	public static boolean hasData() {
		boolean flag;
		if (mAdvertisers == null || mAdvertisers.size() == 0)
			flag = false;
		else
			flag = true;
		return flag;
	}

	static int localLenth(String s)
	    {
		return 0;
	        /*int i;
	        int j;
	        i = 0;
	        j = s.length() - 1;
	_L2:
	        char c;
	        if(j < 0)
	            return i / 2;
	        c = s.charAt(j);
	        if((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
	            break;  Loop/switch isn't completed 
	        i++;
	_L3:
	        j--;
	        if(true) goto _L2; else goto _L1
	_L1:
	        if(Character.isLetter(c))
	            i += 2;
	        else
	            i++;
	          goto _L3
	        if(true) goto _L2; else goto _L4
	_L4:*/
	    }

	public static int report(int i, int j, int k, List list)
	    {
		return 0;
	        /*String s1;
	        JSONObject jsonobject = getReportInput(i, j, k, list);
	        String s = ExchangeConstants.REPORT_URL;
	        s1 = Network.sendMessage(jsonobject, s);
	        String s2 = (new StringBuilder("report result:")).append(s1).toString();
	        int l = Log.i("exchange", s2);
	        if(s1 != null) goto _L2; else goto _L1
	_L1:
	        byte byte0 = -1;
	_L4:
	        return byte0;
	_L2:
	        boolean flag;
	        String s3 = (new JSONObject(s1)).getString("success");
	        flag = "ok".equals(s3);
	        if(flag)
	            byte0 = 1;
	        else
	            byte0 = 0;
	        continue;  Loop/switch isn't completed 
	        printStackTrace();
	        byte0 = -1;
	        if(true) goto _L4; else goto _L3
	_L3:*/
	    }

	public static void request()
	    {
	        /*String s1;
	        JSONObject jsonobject1;
	        JSONObject jsonobject = getRequestInput();
	        String s = ExchangeConstants.REQUEST_URL;
	        s1 = Network.sendMessage(jsonobject, s);
	        String s2 = (new StringBuilder("request result:")).append(s1).toString();
	        int i = Log.i("exchange", s2);
	        jsonobject1 = null;
	        JSONObject jsonobject2 = new JSONObject(s1);
	        jsonobject1 = jsonobject2;
	_L2:
	        mAdvertisers = convertToAdvertisers(jsonobject1);
	        if(ExchangeConstants.filterInstalledApp)
	            filter();
	        return;
	        printStackTrace();
	        if(true) goto _L2; else goto _L1
	_L1:*/
	    }

	public static void requestDataAsyn(Context context1,
			ExchangeDataRequestListener exchangedatarequestlistener) {
		if (ExchangeConstants.ONLY_CHINESE
				&& !DeviceManager.isChinese(context1)) {
			int i = Log.e(ExchangeConstants.LOG_TAG,
					"English os can not show ads");
			exchangedatarequestlistener.dataReceived(0);
		} else {
			(new GetDataThread(context1, exchangedatarequestlistener)).start();
		}
	}

	public static boolean rotate()
	    {
		return true;
	        /*int k;
	        if(!hasData())
	            break MISSING_BLOCK_LABEL_97;
	        int i = curIndex + 1;
	        int j = mAdvertisers.size();
	        k = i % j;
	_L3:
	        int l = curIndex;
	        if(k != l && getAd(k).landingType != 0) goto _L2; else goto _L1
	_L1:
	        boolean flag;
	        int i1 = curIndex;
	        int j1;
	        int k1;
	        if(k == i1)
	        {
	            flag = false;
	        } else
	        {
	            curIndex = k;
	            flag = true;
	        }
	_L4:
	        return flag;
	_L2:
	        j1 = curIndex + 1;
	        k1 = mAdvertisers.size();
	        k = j1 % k1;
	          goto _L3
	        flag = false;
	          goto _L4*/
	    }

	public static void setKeywords(String s) {
		ExchangeConstants.keywords = s;
	}

	public static void trimDescription(List list, int i, int j) {
		Iterator iterator = list.iterator();
		do {
			AdvertiserConfig advertiserconfig;
			String s2;
			do {
				if (!iterator.hasNext())
					return;
				advertiserconfig = (AdvertiserConfig) iterator.next();
				String s = advertiserconfig.adDescription;
				if (s.length() > j) {
					String s1 = s.substring(0, j);
					advertiserconfig.adDescription = s1;
				}
				s2 = advertiserconfig.adName;
			} while (s2.length() <= i);
			String s3 = s2.substring(0, i);
			advertiserconfig.adName = s3;
		} while (true);
	}

	public static Context context;
	public static int curIndex = 0;
	public static List mAdvertisers = null;
	static int minimal_ad_count = 6;
}
