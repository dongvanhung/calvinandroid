package com.xiami.util;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

	public JSONObject join(String paramString1, String paramString2, String paramString3)
    throws JSONException, Exception
  {
    String str1 = String.valueOf("http://www.xiami.com/app/android/reg");
    StringBuilder localStringBuilder1 = new StringBuilder(str1).append("?email=");
    String str2 = URLEncoder.encode(paramString1);
    StringBuilder localStringBuilder2 = localStringBuilder1.append(str2).append("&pwd=");
    String str3 = URLEncoder.encode(paramString2);
    StringBuilder localStringBuilder3 = localStringBuilder2.append(str3).append("&nickname=");
    String str4 = URLEncoder.encode(paramString3);
    return Web.getJSONObject(str4);
  }

  public JSONObject login(String paramString1, String paramString2)
    throws JSONException, ClientProtocolException, IOException
  {
    String str1 = String.valueOf("http://www.xiami.com/app/android/login");
    StringBuilder localStringBuilder1 = new StringBuilder(str1).append("?email=");
    String str2 = URLEncoder.encode(paramString1);
    StringBuilder localStringBuilder2 = localStringBuilder1.append(str2).append("&pwd=");
    String str3 = URLEncoder.encode(paramString2);
    return Web.getJSONObject(str3);
  }
}
