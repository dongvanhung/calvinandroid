package com.xiami.exception;

public class URLArgNotFoundException extends Exception {

	String argName;

	  public URLArgNotFoundException(String paramString)
	  {
	    this.argName = paramString;
	  }

	  public String getMessage()
	  {
	    StringBuilder localStringBuilder = new StringBuilder("arg [");
	    String str = this.argName;
	    return str;
	  }
}
