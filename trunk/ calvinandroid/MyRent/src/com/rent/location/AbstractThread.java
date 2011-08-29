package com.rent.location;

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

import com.rent.util.DefaultCookieStore;

public abstract class AbstractThread extends Thread{

	private final int TIMEOUT = 20000;
	  protected final Handler handler;
	  private String mAppName = null;
	  private Context mContext = null;
	  private String mVersionNmame = null;
	  private String url = null;

	  public AbstractThread(Context paramContext, Handler paramHandler, String paramString1, String paramString2)
	  {
	    this.handler = paramHandler;
	    this.mContext = paramContext;
	    this.mVersionNmame = paramString1;
	    this.mAppName = paramString2;
	  }

	  protected String executeGet()
	    throws ClientProtocolException, IOException
	  {
	    String localObject;
	    if (StringUtils.isEmpty(getUrl()))
	      localObject = null;
	    else
	    {
	      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
	      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
	      HttpClientParams.setRedirecting(localBasicHttpParams, false);
	      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	      Context localContext = this.mContext;
	      String str1 = this.mVersionNmame;
	      DefaultCookieStore localDefaultCookieStore = DefaultCookieStore.getSingleStore(localContext, str1, "rent");
	      localDefaultHttpClient.setCookieStore(localDefaultCookieStore);
	      String str2 = getUrl();
	      HttpGet localHttpGet = new HttpGet(str2);
	      HttpResponse hr = localDefaultHttpClient.execute(localHttpGet);
	      if (hr.getStatusLine().getStatusCode() == 200)
	      {
	        localObject = EntityUtils.toString(hr.getEntity(), "UTF-8");
	      } else {
	    	  localObject = null;
	      }
	    }
	    return localObject;
	  }

	  protected String executePost(List<BasicNameValuePair> paramList)
	    throws ClientProtocolException, IOException
	  {
	    Object localObject;
	    if (StringUtils.isEmpty(getUrl()))
	      localObject = null;
	    while (true)
	    {
	      String str1 = getUrl();
	      HttpPost localHttpPost = new HttpPost(str1);
	      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
	      localHttpPost.setEntity(localUrlEncodedFormEntity);
	      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
	      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
	      HttpClientParams.setRedirecting(localBasicHttpParams, false);
	      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	      Context localContext = this.mContext;
	      String str2 = this.mVersionNmame;
	      String str3 = this.mAppName;
	      DefaultCookieStore localDefaultCookieStore = DefaultCookieStore.getSingleStore(localContext, str2, str3);
	      localDefaultHttpClient.setCookieStore(localDefaultCookieStore);
	      localObject = localDefaultHttpClient.execute(localHttpPost);
	      if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
	      {
	        localObject = EntityUtils.toString(((HttpResponse)localObject).getEntity());
	        continue;
	      }
	      localObject = null;
	    }
	  }

	  protected abstract Message getMessageByContent(String paramString);

	  protected String getUrl()
	  {
	    return this.url;
	  }

	  protected abstract boolean isSuccessful(String paramString)
	    throws JSONException;

	  protected void setUrl(String paramString)
	  {
	    this.url = paramString;
	  }
	  
}
