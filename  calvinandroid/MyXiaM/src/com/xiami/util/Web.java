package com.xiami.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.xiami.lib.util.BasicAuth;
import com.xiami.localdb.DbHelper;

public class Web {

	private static final String TAG = "xiami";
	  private static final String USER_AGENT = "Mozilla/4.5";

	  public static JSONArray getCachedJSONArray(String paramString, Context paramContext)
	  {
	    DbHelper localDbHelper = DbHelper.getInstance(paramContext);
	    int i = getHash(paramString);
	    String str1 = localDbHelper.getCache(i);
	    if (str1 == null)
	      Log.d("xiami", "cache miss");
	    while (true)
	    {
	      JSONArray localJSONArray2;
	      try
	      {
	        str1 = getString(paramString);
	        if (str1 == null)
	          continue;
	        JSONArray localJSONArray1 = new JSONArray(str1);
	        boolean bool = localDbHelper.setCache(i, str1, 86400);
	        localJSONArray2 = localJSONArray1;
	        return localJSONArray2;
	      }
	      catch (Exception localException)
	      {
	        localException.printStackTrace();
	        int k = Log.d("xiami", str1);
	        localJSONArray2 = new JSONArray();
	      }
	      int m = Log.d("xiami", "cache hint");
	      try
	      {
	        localJSONArray2 = new JSONArray(str1);
	      }
	      catch (JSONException localJSONException)
	      {
	        localJSONException.printStackTrace();
	        String str2 = String.valueOf(localJSONException.getMessage());
	        String str3 = str2 + str1;
	        int n = Log.d("xiami", str3);
	        localJSONArray2 = null;
	      }
	    }
	  }

	  public static JSONObject getCachedJSONObject(String paramString, Context paramContext)
	  {
	    return getCachedJSONObject(paramString, paramContext, 86400);
	  }

	  public static JSONObject getCachedJSONObject(String paramString, Context paramContext, int paramInt)
	  {
	    DbHelper localDbHelper = DbHelper.getInstance(paramContext);
	    int j = getHash(paramString);
	    String str1 = localDbHelper.getCache(j);
	    if (str1 == null);
	    try
	    {
	      int k = Log.d("xiami", "cache miss");
	      while (true)
	      {
	        JSONObject localJSONObject1;
	        JSONObject localJSONObject2;
	        try
	        {
	          str1 = getString(paramString);
	          if (str1 == null)
	            continue;
	          localJSONObject1 = new JSONObject(str1);
	          boolean bool = localDbHelper.setCache(j, str1, paramInt);
	          System.gc();
	          localJSONObject2 = localJSONObject1;
	          return localJSONObject2;
	        }
	        catch (Exception localException)
	        {
	          localException.printStackTrace();
	          int m = Log.d("xiami", str1);
	          localJSONObject2 = new JSONObject();
	          System.gc();
	        }
	        Log.d("xiami", "cache hint");
	        try
	        {
	          localJSONObject1 = new JSONObject(str1);
	          System.gc();
	          localJSONObject2 = localJSONObject1;
	        }
	        catch (JSONException localJSONException)
	        {
	          localJSONException.printStackTrace();
	          String str2 = String.valueOf(localJSONException.getMessage());
	          String str3 = str2 + str1;
	          int i1 = Log.d("xiami", str3);
	          System.gc();
	          localJSONObject2 = null;
	        }
	      }
	    }
	    finally
	    {
	      System.gc();
	    }
	  }

	  public static String getCachedText(String paramString, Context paramContext, int paramInt)
	  {
	    DbHelper localDbHelper = DbHelper.getInstance(paramContext);
	    int i = getHash(paramString);
	    String str1 = localDbHelper.getCache(i);
	    if (str1 == null)
	      Log.d("xiami", "cache miss");
	    else
	    {
	      try
	      {
	        str1 = getString(paramString);
	        if (str1 != null) {
		        boolean bool = localDbHelper.setCache(i, str1, paramInt);
		        return str1;
	        }
	      }
	      catch (Exception localException)
	      {
	        localException.printStackTrace();
	        int k = Log.d("xiami", str1);
	      }
	      int m = Log.d("xiami", "cache hint");
	    }
	    return str1;
	  }

	  public static int getHash(String paramString)
	  {
	    try
	    {
	      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
	      byte[] arrayOfByte1 = paramString.concat("_1").getBytes();
	      localMessageDigest.update(arrayOfByte1);
	      byte[] arrayOfByte2 = localMessageDigest.digest();
	      int i = new BigInteger(arrayOfByte2).intValue();
	      return i;
	    }
	    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
	    {
	    	localNoSuchAlgorithmException.printStackTrace();
	    }
	    return 0;
	  }

	  public static JSONArray getJSONArray(String paramString)
	    throws JSONException, Exception
	  {
	    String str = getString(paramString);
	    return new JSONArray(str);
	  }

	  public static JSONObject getJSONObject(String paramString)
	    throws ClientProtocolException, JSONException, IOException
	  {
	    String str = getString(paramString).trim();
	    return new JSONObject(str);
	  }

	  public static JSONObject getJSONObject(String paramString1, String paramString2, String paramString3)
	    throws ClientProtocolException, JSONException, IOException
	  {
	    String str = getString(paramString1, paramString2, paramString3);
	    return new JSONObject(str);
	  }

	  // ERROR //
	  protected static String getRequest(String paramString, DefaultHttpClient paramDefaultHttpClient)
	    throws ClientProtocolException, IOException
	  {
		  return "";
	    // Byte code:
	    //   0: ldc 160
	    //   2: invokestatic 166	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
	    //   5: invokevirtual 169	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
	    //   8: invokestatic 92	com/xiami/util/Log:d	(Ljava/lang/String;)I
	    //   11: istore_2
	    //   12: new 171	org/apache/http/client/methods/HttpGet
	    //   15: dup
	    //   16: aload_0
	    //   17: invokespecial 172	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
	    //   20: astore_3
	    //   21: new 77	java/lang/StringBuilder
	    //   24: dup
	    //   25: ldc 174
	    //   27: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
	    //   30: aload_0
	    //   31: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   34: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   37: astore 4
	    //   39: ldc 9
	    //   41: aload 4
	    //   43: invokestatic 44	com/xiami/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   46: istore 5
	    //   48: aload_3
	    //   49: ldc 176
	    //   51: ldc 12
	    //   53: invokevirtual 180	org/apache/http/client/methods/HttpGet:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
	    //   56: aload_1
	    //   57: aload_3
	    //   58: invokevirtual 186	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
	    //   61: astore 6
	    //   63: aload 6
	    //   65: invokeinterface 192 1 0
	    //   70: invokeinterface 197 1 0
	    //   75: istore 7
	    //   77: new 77	java/lang/StringBuilder
	    //   80: dup
	    //   81: ldc 199
	    //   83: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
	    //   86: iload 7
	    //   88: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
	    //   91: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   94: astore 8
	    //   96: ldc 9
	    //   98: aload 8
	    //   100: invokestatic 44	com/xiami/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   103: istore 9
	    //   105: aload 6
	    //   107: invokeinterface 206 1 0
	    //   112: invokestatic 210	com/xiami/util/Web:retrieveInputStream	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
	    //   115: astore 10
	    //   117: aload 10
	    //   119: astore 11
	    //   121: aload_3
	    //   122: invokevirtual 213	org/apache/http/client/methods/HttpGet:abort	()V
	    //   125: aload 11
	    //   127: areturn
	    //   128: invokevirtual 214	java/net/UnknownHostException:printStackTrace	()V
	    //   131: goto -119 -> 12
	    //   134: astore 12
	    //   136: aload_3
	    //   137: invokevirtual 213	org/apache/http/client/methods/HttpGet:abort	()V
	    //   140: aload 12
	    //   142: athrow
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   0	12	128	java/net/UnknownHostException
	    //   48	117	134	finally
	  }

	  protected static String getRequest(String paramString1, DefaultHttpClient paramDefaultHttpClient, String paramString2, String paramString3)
	    throws ClientProtocolException, IOException
	  {
	    HttpGet localHttpGet = new HttpGet(paramString1);
	    String str1 = "do the getRequest,url=" + paramString1;
	    int i = Log.d("xiami", str1);
	    try
	    {
	      localHttpGet.setHeader("User-Agent", "Mozilla/4.5");
	      String str2 = String.valueOf(paramString2);
	      String str3 = str2 + paramString3;
	      int j = Log.d("xiami", str3);
	      StringBuilder localStringBuilder = new StringBuilder("Basic ");
	      String str4 = BasicAuth.encode(paramString2, paramString3).trim();
	      String str5 = str4;
	      localHttpGet.addHeader("Authorization", str5);
	      HttpResponse localHttpResponse = paramDefaultHttpClient.execute(localHttpGet);
	      int k = localHttpResponse.getStatusLine().getStatusCode();
	      String str6 = "statuscode = " + k;
	      int m = Log.d("xiami", str6);
	      String str7 = retrieveInputStream(localHttpResponse.getEntity());
	      String str8 = str7;
	      localHttpGet.abort();
	      int n = Log.d("xiami", str8);
	      return str8;
	    }
	    finally
	    {
	      localHttpGet.abort();
	    }
	  }

	  public static String getString(String paramString)
	    throws ClientProtocolException, IOException
	  {
	    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	    return getRequest(paramString, localDefaultHttpClient);
	  }

	  public static String getString(String paramString1, String paramString2, String paramString3)
	    throws ClientProtocolException, IOException
	  {
	    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	    return getRequest(paramString1, localDefaultHttpClient, paramString2, paramString3);
	  }

	  protected static String retrieveInputStream(HttpEntity paramHttpEntity)
	  {
	    Long localLong = Long.valueOf(paramHttpEntity.getContentLength());
	    int i = (int)paramHttpEntity.getContentLength();
	    if (i < 0)
	      i = 10000;
	    StringBuffer localStringBuffer1 = new StringBuffer(i);
	    try
	    {
	      InputStream localInputStream = paramHttpEntity.getContent();
	      InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, "UTF-8");
	      char[] arrayOfChar = new char[i];
	      while (true)
	      {
	        int j = i - 1;
	        int k = localInputStreamReader.read(arrayOfChar, 0, j);
	        int m = k;
	        if (m <= 0)
	          return localStringBuffer1.toString();
	        int n = 0;
	        StringBuffer localStringBuffer2 = localStringBuffer1.append(arrayOfChar, n, m);
	      }
	    }
	    catch (UnsupportedEncodingException localUnsupportedEncodingException)
	    {
	      while (true)
	      {
	        String str1 = localUnsupportedEncodingException.getMessage();
	        int i1 = Log.e("xiami", str1);
	      }
	    }
	    catch (IllegalStateException localIllegalStateException)
	    {
	      while (true)
	      {
	        String str2 = localIllegalStateException.getMessage();
	        int i2 = Log.e("xiami", str2);
	      }
	    }
	    catch (IOException localIOException)
	    {
	      while (true)
	      {
	        String str3 = localIOException.getMessage();
	        int i3 = Log.e("xiami", str3);
	      }
	    }
	  }
}
