package com.rent.exchange.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class Network {

	// ERROR //
	private static String convertStreamToString(InputStream paramInputStream) {
		return  "";
	}

	public static Drawable fetchImage(String paramString) {
		try {
			String str1 = paramString.replace("/", "").replace(":", "");
			String str2 = ExchangeConstants.IMAGE_ROOT;
			boolean bool1 = new File(str2).mkdirs();
			String str3 = String.valueOf(ExchangeConstants.IMAGE_ROOT);
			String str4 = str3 + str1;
			File localFile = new File(str4);
			InputStream localInputStream = null;
			FileOutputStream localFileOutputStream = null;
			byte[] arrayOfByte = null;
			if (!localFile.exists()) {
				boolean bool2 = localFile.createNewFile();
				localInputStream = (InputStream) new URL(paramString)
						.openConnection().getContent();
				localFileOutputStream = new FileOutputStream(localFile);
				arrayOfByte = new byte[4096];
			}
			while (true) {
				int i = localInputStream.read(arrayOfByte);
				if (i == -1) {
					localFileOutputStream.flush();
					localInputStream.close();
					localFileOutputStream.close();
					Drawable localDrawable = Drawable.createFromPath(str4);
					return localDrawable;
				}
				localFileOutputStream.write(arrayOfByte, 0, i);
			}
		} catch (Exception localException) {
			String str5 = ExchangeConstants.LOG_TAG;
			String str6 = localException.getStackTrace().toString();
			int j = Log.i(str5, str6);
			Drawable localDrawable = null;
		}
		return null;
	}

	public static String sendMessage(JSONObject paramJSONObject,
			String paramString) {
		String str1 = ExchangeConstants.LOG_TAG;
		String str2 = paramJSONObject.toString();
		int i = Log.i(str1, str2);
		HttpPost localHttpPost = new HttpPost(paramString);
		BasicHttpParams localBasicHttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
		HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
		Object localObject = new DefaultHttpClient(localBasicHttpParams);
		try {
			ArrayList localArrayList = new ArrayList(1);
			String str3 = paramJSONObject.toString();
			BasicNameValuePair localBasicNameValuePair = new BasicNameValuePair(
					"content", str3);
			boolean bool = localArrayList.add(localBasicNameValuePair);
			UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(
					localArrayList, "UTF-8");
			localHttpPost.setEntity(localUrlEncodedFormEntity);
			localHttpPost.addHeader("Accept-Encoding", "gzip");
			localObject = ((HttpClient) localObject).execute(localHttpPost);
			if (((HttpResponse) localObject).getStatusLine().getStatusCode() == 200) {
				String str4 = ExchangeConstants.LOG_TAG;
				String str5 = "Sent message to " + paramString;
				int j = Log.i(str4, str5);
				/*
				 * HttpResponse response =
				 * ((HttpResponse)localObject).getEntity(); //TODO if (response
				 * != null) { paramString = response.getContent();
				 * paramJSONObject =
				 * response.getFirstHeader("Content-Encoding"); if
				 * ((paramJSONObject == null) ||
				 * (!paramJSONObject.getValue().equalsIgnoreCase("gzip"))) break
				 * label315; paramJSONObject = new GZIPInputStream(paramString);
				 * paramJSONObject = convertStreamToString(paramJSONObject); }
				 */
				return paramJSONObject.toString();
			} else {
				paramJSONObject = null;
				int k = Log.i(ExchangeConstants.LOG_TAG,
						"Failed to send message.");
				paramJSONObject = null;
			}
		} catch (ClientProtocolException localClientProtocolException) {
			while (true) {
				int m = Log.i(ExchangeConstants.LOG_TAG,
						"ClientProtocolException,Failed to send message.",
						localClientProtocolException);
				paramJSONObject = null;
			}
		} catch (IOException localIOException) {
			int n = Log.i(ExchangeConstants.LOG_TAG,
					"IOException,Failed to send message.", localIOException);
			paramJSONObject = null;
		}
		return "";
	}
}
