package com.rent.data;

import java.io.Serializable;
import java.util.Arrays;

public class DetailData implements Serializable {

	private static final long serialVersionUID = 1L;
	  private String mAbstract;
	  private String mAddress;
	  private String mAgencyStatus;
	  private int mArea;
	  private int mCalledTimes;
	  private String mContactPath;
	  private String mContactPerson;
	  public String mExtNumber;
	  private int mFavouriteOrCalled;
	  private String mFromSite;
	  private long mId;
	  private String[] mImages;
	  private long mLastCalledTime;
	  public String mMasterNumber;
	  private String mPhone;
	  private int mPrice;
	  private String mPublishTime;
	  private int mRentType;
	  private String mRoom;
	  private String mThumbnail;
	  private String mTitle;
	  private String mToward;

	  public String getmAbstract()
	  {
	    return this.mAbstract;
	  }

	  public String getmAddress()
	  {
	    return this.mAddress;
	  }

	  public String getmAgencyStatus()
	  {
	    return this.mAgencyStatus;
	  }

	  public int getmArea()
	  {
	    return this.mArea;
	  }

	  public int getmCalledTimes()
	  {
	    return this.mCalledTimes;
	  }

	  public String getmContactPath()
	  {
	    return this.mContactPath;
	  }

	  public String getmContactPerson()
	  {
	    return this.mContactPerson;
	  }

	  public int getmFavouriteOrCalled()
	  {
	    return this.mFavouriteOrCalled;
	  }

	  public String getmFromSite()
	  {
	    return this.mFromSite;
	  }

	  public long getmId()
	  {
	    return this.mId;
	  }

	  public String[] getmImages()
	  {
	    return this.mImages;
	  }

	  public long getmLastCalledTime()
	  {
	    return this.mLastCalledTime;
	  }

	  public String getmPhone()
	  {
	    return this.mPhone;
	  }

	  public int getmPrice()
	  {
	    return this.mPrice;
	  }

	  public String getmPublishTime()
	  {
	    return this.mPublishTime;
	  }

	  public int getmRentType()
	  {
	    return this.mRentType;
	  }

	  public String getmRoom()
	  {
	    return this.mRoom;
	  }

	  public String getmThumbnail()
	  {
	    return this.mThumbnail;
	  }

	  public String getmTitle()
	  {
	    return this.mTitle;
	  }

	  public String getmToward()
	  {
	    return this.mToward;
	  }

	  public void setmAbstract(String paramString)
	  {
	    this.mAbstract = paramString;
	  }

	  public void setmAddress(String paramString)
	  {
	    this.mAddress = paramString;
	  }

	  public void setmAgencyStatus(String paramString)
	  {
	    this.mAgencyStatus = paramString;
	  }

	  public void setmArea(int paramInt)
	  {
	    this.mArea = paramInt;
	  }

	  public void setmCalledTimes(int paramInt)
	  {
	    this.mCalledTimes = paramInt;
	  }

	  public void setmContactPath(String paramString)
	  {
	    this.mContactPath = paramString;
	  }

	  public void setmContactPerson(String paramString)
	  {
	    this.mContactPerson = paramString;
	  }

	  public void setmFavouriteOrCalled(int paramInt)
	  {
	    this.mFavouriteOrCalled = paramInt;
	  }

	  public void setmFromSite(String paramString)
	  {
	    this.mFromSite = paramString;
	  }

	  public void setmId(long paramLong)
	  {
	    this.mId = paramLong;
	  }

	  public void setmImages(String[] paramArrayOfString)
	  {
	    this.mImages = paramArrayOfString;
	  }

	  public void setmLastCalledTime(long paramLong)
	  {
	    this.mLastCalledTime = paramLong;
	  }

	  public void setmPhone(String paramString)
	  {
	    this.mPhone = paramString;
	  }

	  public void setmPrice(int paramInt)
	  {
	    this.mPrice = paramInt;
	  }

	  public void setmPublishTime(String paramString)
	  {
	    this.mPublishTime = paramString;
	  }

	  public void setmRentType(int paramInt)
	  {
	    this.mRentType = paramInt;
	  }

	  public void setmRoom(String paramString)
	  {
	    this.mRoom = paramString;
	  }

	  public void setmThumbnail(String paramString)
	  {
	    this.mThumbnail = paramString;
	  }

	  public void setmTitle(String paramString)
	  {
	    this.mTitle = paramString;
	  }

	  public void setmToward(String paramString)
	  {
	    this.mToward = paramString;
	  }

	  public String toString()
	  {
	    StringBuilder localStringBuilder1 = new StringBuilder().append("DetailData [mAbstract=");
	    String str1 = this.mAbstract;
	    StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(", mAddress=");
	    String str2 = this.mAddress;
	    StringBuilder localStringBuilder3 = localStringBuilder2.append(str2).append(", mAgencyStatus=");
	    String str3 = this.mAgencyStatus;
	    StringBuilder localStringBuilder4 = localStringBuilder3.append(str3).append(", mArea=");
	    int i = this.mArea;
	    StringBuilder localStringBuilder5 = localStringBuilder4.append(i).append(", mCalledTimes=");
	    int j = this.mCalledTimes;
	    StringBuilder localStringBuilder6 = localStringBuilder5.append(j).append(", mContactPerson=");
	    String str4 = this.mContactPerson;
	    StringBuilder localStringBuilder7 = localStringBuilder6.append(str4).append(", mFavouriteOrCalled=");
	    int k = this.mFavouriteOrCalled;
	    StringBuilder localStringBuilder8 = localStringBuilder7.append(k).append(", mFromSite=");
	    String str5 = this.mFromSite;
	    StringBuilder localStringBuilder9 = localStringBuilder8.append(str5).append(", mId=");
	    long l1 = this.mId;
	    StringBuilder localStringBuilder10 = localStringBuilder9.append(l1).append(", mImages=");
	    String str6 = Arrays.toString(this.mImages);
	    StringBuilder localStringBuilder11 = localStringBuilder10.append(str6).append(", mLastCalledTime=");
	    long l2 = this.mLastCalledTime;
	    StringBuilder localStringBuilder12 = localStringBuilder11.append(l2).append(", mPhone=");
	    String str7 = this.mPhone;
	    StringBuilder localStringBuilder13 = localStringBuilder12.append(str7).append(", mPrice=");
	    int m = this.mPrice;
	    StringBuilder localStringBuilder14 = localStringBuilder13.append(m).append(", mPublishTime=");
	    String str8 = this.mPublishTime;
	    StringBuilder localStringBuilder15 = localStringBuilder14.append(str8).append(", mRentType=");
	    int n = this.mRentType;
	    StringBuilder localStringBuilder16 = localStringBuilder15.append(n).append(", mRoom=");
	    String str9 = this.mRoom;
	    StringBuilder localStringBuilder17 = localStringBuilder16.append(str9).append(", mThumbnail=");
	    String str10 = this.mThumbnail;
	    StringBuilder localStringBuilder18 = localStringBuilder17.append(str10).append(", mTitle=");
	    String str11 = this.mTitle;
	    StringBuilder localStringBuilder19 = localStringBuilder18.append(str11).append(", mToward=");
	    String str12 = this.mToward;
	    return str12 + "]";
	  }
	  
}
