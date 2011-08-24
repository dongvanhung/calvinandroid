package com.rent.exchange.model;

import android.graphics.drawable.Drawable;

public class AdvertiserConfig {

	public int action;
	  public String adDescription;
	  public String adDetail;
	  public Drawable adIcon = null;
	  public int adIconIndex = -1;
	  public String adIconUrl = null;
	  public String adName = null;
	  public String apk;
	  public String appkey;
	  public String category;
	  public int displayType;
	  public long fileSize;
	  public Drawable fullSizeBanner;
	  public long id = 65535L;
	  public boolean installed = false;
	  public int landingType;
	  public String packageName;
	  public String provider = "Test";
	  public Drawable umengIcon;
	  public String version;

	  public AdvertiserConfig()
	  {
	  }

	  public AdvertiserConfig(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, int paramInt)
	  {
	    this.adName = paramString1;
	    this.adDescription = paramString2;
	    this.adDetail = paramString3;
	    this.fileSize = paramLong;
	    this.version = paramString4;
	    this.adIconIndex = paramInt;
	  }

	  public long getId()
	  {
	    return this.id;
	  }
}
