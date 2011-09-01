package com.rent.data;

public class FavouriteData {

	private String mAddress;
	  private String mAgencyStatus;
	  private int mArea;
	  private long mId;
	  private int mPrice;
	  private int mRentType;
	  private String mRoom;
	  private String mThumbnail;
	  private String mTitle;

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

	  public long getmId()
	  {
	    return this.mId;
	  }

	  public int getmPrice()
	  {
	    return this.mPrice;
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

	  public void setmId(long paramLong)
	  {
	    this.mId = paramLong;
	  }

	  public void setmPrice(int paramInt)
	  {
	    this.mPrice = paramInt;
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

	  public String toString()
	  {
	    StringBuilder localStringBuilder1 = new StringBuilder().append("FavouriteData [mAddress=");
	    String str1 = this.mAddress;
	    StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(", mAgencyStatus=");
	    String str2 = this.mAgencyStatus;
	    StringBuilder localStringBuilder3 = localStringBuilder2.append(str2).append(", mArea=");
	    int i = this.mArea;
	    StringBuilder localStringBuilder4 = localStringBuilder3.append(i).append(", mId=");
	    long l = this.mId;
	    StringBuilder localStringBuilder5 = localStringBuilder4.append(l).append(", mPrice=");
	    int j = this.mPrice;
	    StringBuilder localStringBuilder6 = localStringBuilder5.append(j).append(", mRentType=");
	    int k = this.mRentType;
	    StringBuilder localStringBuilder7 = localStringBuilder6.append(k).append(", mRoom=");
	    String str3 = this.mRoom;
	    StringBuilder localStringBuilder8 = localStringBuilder7.append(str3).append(", mThumbnail=");
	    String str4 = this.mThumbnail;
	    StringBuilder localStringBuilder9 = localStringBuilder8.append(str4).append(", mTitle=");
	    String str5 = this.mTitle;
	    return str5 + "]";
	  }
	  
}
