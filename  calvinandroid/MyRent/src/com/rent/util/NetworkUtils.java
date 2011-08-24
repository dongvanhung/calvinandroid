package com.rent.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	public static byte[] downLoadImage(String paramString) throws Exception {
		HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(
				paramString).openConnection();
		localHttpURLConnection.setRequestMethod("GET");
		localHttpURLConnection.setConnectTimeout(5000);
		InputStream localInputStream = localHttpURLConnection.getInputStream();
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayOfByte = new byte[102400];
		while (true) {
			int i = localInputStream.read(arrayOfByte);
			if (i == -1)
				break;
			localByteArrayOutputStream.write(arrayOfByte, 0, i);
		}
		localByteArrayOutputStream.close();
		localInputStream.close();
		return localByteArrayOutputStream.toByteArray();
	}

	public static boolean isNetworkAvaiable(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		if (localNetworkInfo != null)
			;
		for (boolean bool = localNetworkInfo.isAvailable();; bool = false)
			return bool;
	}

	public static boolean isWifi(Context paramContext) {
		ConnectivityManager cm = (ConnectivityManager) paramContext
				.getSystemService("connectivity");
		NetworkInfo localNetworkInfo = cm.getActiveNetworkInfo();
		int i;
		if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()))
			return false;

		int j = cm.getActiveNetworkInfo().getType();
		if (1 == j) {
			return true;
		} 
		return false;
	}
}
