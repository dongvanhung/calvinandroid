package com.rent.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Checksum;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

public class DefaultCookieStore implements CookieStore{

	private static DefaultCookieStore singleStore;
	  private List<Cookie> cookieList;

	  private DefaultCookieStore()
	  {
	    ArrayList localArrayList = new ArrayList();
	    this.cookieList = localArrayList;
	  }

	  public static DefaultCookieStore getSingleStore(Context paramContext, String paramString1, String paramString2)
	  {
	    if (singleStore == null)
	    {
	      singleStore = new DefaultCookieStore();
//	      String str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
	      String str1 = "Test";
	      BasicClientCookie localBasicClientCookie1 = new BasicClientCookie("uuid", str1);
	      localBasicClientCookie1.setVersion(0);
	      localBasicClientCookie1.setDomain(".99fang.com");
	      localBasicClientCookie1.setPath("/");
	      singleStore.addCookie(localBasicClientCookie1);
	      BasicClientCookie localBasicClientCookie2 = new BasicClientCookie("app_version", paramString1);
	      localBasicClientCookie2.setVersion(0);
	      localBasicClientCookie2.setDomain(".99fang.com");
	      localBasicClientCookie2.setPath("/");
	      singleStore.addCookie(localBasicClientCookie2);
	      BasicClientCookie localBasicClientCookie3 = new BasicClientCookie("app_name", paramString2);
	      localBasicClientCookie3.setVersion(0);
	      localBasicClientCookie3.setDomain(".99fang.com");
	      localBasicClientCookie3.setPath("/");
	      singleStore.addCookie(localBasicClientCookie3);
	      String str2 = Build.VERSION.RELEASE;
	      BasicClientCookie localBasicClientCookie4 = new BasicClientCookie("os_version", str2);
	      localBasicClientCookie4.setVersion(0);
	      localBasicClientCookie4.setDomain(".99fang.com");
	      localBasicClientCookie4.setPath("/");
	      singleStore.addCookie(localBasicClientCookie4);
	      String str3 = Build.MODEL;
	      BasicClientCookie localBasicClientCookie5 = new BasicClientCookie("hardware", str3);
	      localBasicClientCookie5.setVersion(0);
	      localBasicClientCookie5.setDomain(".99fang.com");
	      localBasicClientCookie5.setPath("/");
	      singleStore.addCookie(localBasicClientCookie5);
	      /*String str4 = ManifestData.getString(paramContext, "UMENG_CHANNEL");*/
	      String str4 = "";
	      String str5 = "channel";
	      if (StringUtils.isEmpty(str4))
	        str4 = "default";
	      BasicClientCookie localBasicClientCookie6 = new BasicClientCookie(str5, str4);
	      localBasicClientCookie6.setVersion(0);
	      localBasicClientCookie6.setDomain(".99fang.com");
	      localBasicClientCookie6.setPath("/");
	      singleStore.addCookie(localBasicClientCookie6);
	      StringBuilder localStringBuilder = new StringBuilder().append("");
	      long l = System.currentTimeMillis() / 1000L;
	      String str6 = l + "";
	      BasicClientCookie localBasicClientCookie7 = new BasicClientCookie("start_time", str6);
	      localBasicClientCookie7.setVersion(0);
	      localBasicClientCookie7.setDomain(".99fang.com");
	      localBasicClientCookie7.setPath("/");
	      singleStore.addCookie(localBasicClientCookie7);
	      String str7 = paramString2;
	      String str8 = paramString1;
	      //String str9 = Checksum.getChecksum(str1, str7, str8, str6, str3, str2); //TODO
	      String str9 = "";
	      BasicClientCookie localBasicClientCookie8 = new BasicClientCookie("checksum", str9);
	      localBasicClientCookie8.setVersion(0);
	      localBasicClientCookie8.setDomain(".99fang.com");
	      localBasicClientCookie8.setPath("/");
	      singleStore.addCookie(localBasicClientCookie8);
	    }
	    return singleStore;
	  }

	  public void addCookie(Cookie paramCookie)
	  {
	    boolean bool = this.cookieList.add(paramCookie);
	  }

	  public void clear()
	  {
	    this.cookieList.clear();
	  }

	  public boolean clearExpired(Date paramDate)
	  {
	    return false;
	  }

	  public List<Cookie> getCookies()
	  {
	    return this.cookieList;
	  }
	  
}
