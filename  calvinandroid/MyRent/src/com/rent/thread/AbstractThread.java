package com.rent.thread;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.rent.Rent;
import com.rent.util.DefaultCookieStore;

public abstract class AbstractThread extends Thread {

	private final int TIMEOUT = 20000;
	protected final Handler handler;
	protected Context mContext = null;
	private String url = null;

	public AbstractThread(Handler paramHandler) {
		this.handler = paramHandler;
	}

	protected String executeGet() throws ClientProtocolException, IOException {
		String localObject = "";
		if (!StringUtils.isEmpty(getUrl())) {
			{
				BasicHttpParams localBasicHttpParams = new BasicHttpParams();
				HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
				HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
						20000);
				HttpClientParams.setRedirecting(localBasicHttpParams, false);
				DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(
						localBasicHttpParams);
				Context localContext = Rent.getAppContext();
//				String str1 = Rent.getVersionName(Rent.getAppContext());
				String str1 = "1.0";
				DefaultCookieStore localDefaultCookieStore = DefaultCookieStore
						.getSingleStore(localContext, str1, "rent");
				localDefaultHttpClient.setCookieStore(localDefaultCookieStore);
				String str2 = getUrl();
				HttpGet localHttpGet = new HttpGet(str2);
				HttpResponse response = localDefaultHttpClient
						.execute(localHttpGet);
				if (response.getStatusLine().getStatusCode() == 200) {
					localObject = EntityUtils.toString(response.getEntity(),
							"UTF-8");
				}
			}
		}
		return localObject;
	}

	protected String executePost(List<BasicNameValuePair> paramList)
			throws ClientProtocolException, IOException {
		Object localObject;
		if (StringUtils.isEmpty(getUrl()))
			localObject = null;
		while (true) {
			String str1 = getUrl();
			HttpPost localHttpPost = new HttpPost(str1);
			UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(
					paramList, "UTF-8");
			localHttpPost.setEntity(localUrlEncodedFormEntity);
			BasicHttpParams localBasicHttpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
			HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
					20000);
			HttpClientParams.setRedirecting(localBasicHttpParams, false);
			DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(
					localBasicHttpParams);
			Context localContext = Rent.getAppContext();
			String str2 = Rent.getVersionName(Rent.getAppContext());
			DefaultCookieStore localDefaultCookieStore = DefaultCookieStore
					.getSingleStore(localContext, str2, "rent");
			localDefaultHttpClient.setCookieStore(localDefaultCookieStore);
			localObject = localDefaultHttpClient.execute(localHttpPost);
			if (((HttpResponse) localObject).getStatusLine().getStatusCode() == 200) {
				localObject = EntityUtils.toString(((HttpResponse) localObject)
						.getEntity());
				continue;
			}
			localObject = null;
		}
	}

	protected abstract Message getMessageByContent(String paramString);

	protected String getUrl() {
		return this.url;
	}

	protected abstract boolean isSuccessful(String paramString)
			throws JSONException;

	protected void setUrl(String paramString) {
		this.url = paramString;
	}
}
