package com.xiami.lib.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xiami.exception.URLArgNotFoundException;

public class URL {

	public static String parseUrl(String paramString, Map<String, String> paramMap)
    throws URLArgNotFoundException, UnsupportedEncodingException
  {
    Matcher localMatcher = Pattern.compile("\\{([^}]+)\\}").matcher(paramString);
    while (true)
    {
      if (!localMatcher.find())
        return paramString;
      String str1 = localMatcher.group(1);
      System.out.println(str1);
      String str2 = (String)paramMap.get(str1);
      if (str2 == null)
        throw new URLArgNotFoundException(str1);
      String str3 = localMatcher.group();
      String str4 = URLEncoder.encode(str2, "utf-8");
      paramString = paramString.replace(str3, str4);
    }
  }
}
