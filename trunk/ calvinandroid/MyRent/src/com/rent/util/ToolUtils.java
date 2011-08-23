package com.rent.util;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;

public class ToolUtils {

	public static boolean checkAndUpdate(Context paramContext,
			String paramString, int paramInt) {
		SharedPreferences localSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(paramContext);
		long l1 = localSharedPreferences.getLong(paramString, 0L);
		long l2 = System.currentTimeMillis();
		long l3 = paramInt;
		long l4 = l1 + l3;
		if (l2 > l4) {
			boolean bool = localSharedPreferences.edit()
					.putLong(paramString, l2).commit();
			return true;
		} else {
			return false;
		}
	}

	public static String getVersionName(Context paramContext) {
		String str2 = "";
		try {
			PackageManager localPackageManager = paramContext
					.getPackageManager();
			String str1 = paramContext.getPackageName();
			str2 = localPackageManager.getPackageInfo(str1, 0).versionName;
		} catch (Exception localException) {
		}
		return str2;
	}

	public static boolean hasNewVersion(String paramString1, String paramString2) {
		boolean result = false;
		try {
			float f1 = Float.parseFloat(paramString1);
			float f2 = Float.parseFloat(paramString2);
			float f3 = f2;
			if (f1 > f3) {
				result = true;
			}
		} catch (Exception localException) {
		}
		return result;
	}

	public static void installShortcut(Activity paramActivity,
			String paramString, int paramInt) {
		Intent localIntent1 = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		Intent localIntent2 = localIntent1.putExtra(
				"android.intent.extra.shortcut.NAME", paramString);
		Intent.ShortcutIconResource localShortcutIconResource = Intent.ShortcutIconResource
				.fromContext(paramActivity, paramInt);
		Intent localIntent3 = localIntent1.putExtra(
				"android.intent.extra.shortcut.ICON_RESOURCE",
				localShortcutIconResource);
		Intent localIntent4 = new Intent();
		String str = paramActivity.getPackageName();
		ComponentName localComponentName = new ComponentName(str,
				".activity.SplashActivity");
		Intent localIntent5 = localIntent4.setComponent(localComponentName);
		Intent localIntent6 = localIntent1.putExtra(
				"android.intent.extra.shortcut.INTENT", localIntent4);
		paramActivity.sendBroadcast(localIntent1);
	}

	public static boolean isInstallShortcut(Activity paramActivity,
			String paramString) {
		boolean result = false;
		Uri localUri = Uri.parse("content://" + "com.android.launcher.settings"
				+ "/favorites?notify=true");
		ContentResolver localContentResolver = paramActivity
				.getContentResolver();
		String[] arrayOfString1 = new String[1];
		arrayOfString1[0] = "title";
		String[] arrayOfString2 = new String[1];
		arrayOfString2[0] = paramString;
		Cursor localCursor = localContentResolver.query(localUri,
				arrayOfString1, "title=?", arrayOfString2, null);
		if ((localCursor != null) && (localCursor.getCount() > 0)) {
			result = true;
		}
		if (localCursor != null)
			localCursor.close();
		return result;
	}

	public static void runApplication(Context paramContext,
			String paramString1, String paramString2) {
		Iterator localIterator = paramContext.getPackageManager()
				.getInstalledPackages(0).iterator();
		String str;
		do {
			if (!localIterator.hasNext())
				break;
			str = ((PackageInfo) localIterator.next()).packageName;
		} while (!paramString1.equalsIgnoreCase(str));
		for (int i = 1;; i = 0) {
			if (i != 0) {
				Intent localIntent1 = paramContext.getPackageManager()
						.getLaunchIntentForPackage(paramString1);
				paramContext.startActivity(localIntent1);
			}
			try {
				Uri localUri1 = Uri.parse("market://search?q=pname:"
						+ paramString1);
				Intent localIntent2 = new Intent("android.intent.action.VIEW",
						localUri1);
				paramContext.startActivity(localIntent2);
			} catch (Exception localException1) {
				try {
					if (StringUtils.isEmpty(paramString2))
						continue;
					Uri localUri2 = Uri.parse(paramString2);
					Intent localIntent3 = new Intent(
							"android.intent.action.VIEW", localUri2);
					paramContext.startActivity(localIntent3);
				} catch (Exception localException2) {
				}
			}
		}
	}
}
