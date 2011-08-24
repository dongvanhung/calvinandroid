package com.rent.exchange.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

public class DeviceManager {

	public static Set<String> installedApps = new HashSet();

	public static int dpToPix(int paramInt, Context paramContext) {
		float f = paramContext.getResources().getDisplayMetrics().density;
		return (int) (paramInt * f + 0.5F);
	}

	public static String getAppkey(Context paramContext) {
		Object localObject = null;
		try {
			PackageManager localPackageManager = paramContext
					.getPackageManager();
			String str1 = paramContext.getPackageName();
			ApplicationInfo localApplicationInfo = localPackageManager
					.getApplicationInfo(str1, 128);
			if (localApplicationInfo != null) {
				String str2 = localApplicationInfo.metaData
						.getString("UMENG_APPKEY");
				if (str2 == null) {
				} else {
					return str2;
				}
			} else {
				int i = Log
						.i(ExchangeConstants.LOG_TAG,
								"Could not read EXCHANGE_APPKEY meta-data from AndroidManifest.xml.");
			}
		} catch (Exception localException) {
			int j = Log
					.i(ExchangeConstants.LOG_TAG,
							"Could not read EXCHANGE_APPKEY meta-data from AndroidManifest.xml.",
							localException);
		}
		return null;
	}

	public static String getFileVersionDes(String paramString) {
		String str = String.valueOf(ExchangeConstants.banben);
		return str + paramString;
	}

	public static int getImageHeight(Context paramContext, float paramFloat) {
		return Math
				.round(paramContext.getResources().getDisplayMetrics().heightPixels
						* paramFloat);
	}

	public static Set<String> getInstalledPackages(Context paramContext) {
		HashSet localHashSet = new HashSet();
		List localList = paramContext.getPackageManager().getInstalledPackages(
				0);
		int i = 0;
		while (true) {
			int j = localList.size();
			if (i >= j) {
				installedApps = localHashSet;
				return localHashSet;
			}
			String str = ((PackageInfo) localList.get(i)).packageName;
			boolean bool = localHashSet.add(str);
			i += 1;
		}
	}

	public static JSONObject getMessage(Context paramContext) {
		JSONObject localJSONObject1 = new JSONObject();
		try {
			String str1 = ((TelephonyManager) paramContext
					.getSystemService("phone")).getDeviceId();
			JSONObject localJSONObject2 = localJSONObject1.put("device_key",
					str1);
			try {
				PackageManager localPackageManager = paramContext
						.getPackageManager();
				String str2 = paramContext.getPackageName();
				String str3 = localPackageManager.getPackageInfo(str2, 0).versionName;
				JSONObject localJSONObject3 = localJSONObject1.put(
						"app_version", str3);
				String str4 = Build.VERSION.RELEASE;
				JSONObject localJSONObject4 = localJSONObject1.put(
						"os_version", str4);
				Configuration localConfiguration = new Configuration();
				Settings.System.getConfiguration(
						paramContext.getContentResolver(), localConfiguration);
				if (localConfiguration.locale != null) {
					String str5 = localConfiguration.locale.getCountry();
					JSONObject localJSONObject5 = localJSONObject1.put(
							"country", str5);
					String str6 = localConfiguration.locale.toString();
					JSONObject localJSONObject6 = localJSONObject1.put(
							"language", str6);
					int i = Calendar.getInstance(localConfiguration.locale)
							.getTimeZone().getRawOffset() / 3600000;
					JSONObject localJSONObject7 = localJSONObject1.put(
							"timezone", i);
				}
				JSONObject localJSONObject8 = localJSONObject1.put("lat", 0.0D);
				JSONObject localJSONObject9 = localJSONObject1.put("lng", 0.0D);
				SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date localDate = new Date();
				String str7 = localSimpleDateFormat.format(localDate);
				String str8 = str7.split(" ")[0];
				String str9 = str7.split(" ")[1];
				JSONObject localJSONObject10 = localJSONObject1.put("date",
						str8);
				JSONObject localJSONObject11 = localJSONObject1.put("time",
						str9);
				return localJSONObject1;
			} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
				JSONObject localJSONObject12 = localJSONObject1.put(
						"app_version", "unknown");
			}
		} catch (Exception localException) {
				String str10 = localException.getMessage();
				int j = Log.e("error", str10);
		}
		return null;
	}

	public static boolean isChinese(Context paramContext) {
		return paramContext.getResources().getConfiguration().locale.toString()
				.equals("zh_CN");
	}

	public static boolean isScreenPortrait(Context paramContext) {
		if (paramContext.getResources().getConfiguration().orientation == 1)
			return true;
		return false;
	}
}
