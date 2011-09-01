package com.rent.data;

public class CalledData {

	private String mAddress;
	  private String mAgencyStatus;
	  private int mArea;
	  private long mId;
	  private long mLastCalledTime;
	  private int mPrice;
	  private int mRentType;
	  private String mRoom;
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

	  public long getmLastCalledTime()
	  {
	    return this.mLastCalledTime;
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

	  public void setmLastCalledTime(long paramLong)
	  {
	    this.mLastCalledTime = paramLong;
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

	  public void setmTitle(String paramString)
	  {
	    this.mTitle = paramString;
	  }

	  public String toString()
	  {
	    StringBuilder localStringBuilder1 = new StringBuilder().append("CalledData [mAgencyStatus=");
	    String str1 = this.mAgencyStatus;
	    StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(", mArea=");
	    int i = this.mArea;
	    StringBuilder localStringBuilder3 = localStringBuilder2.append(i).append(", mId=");
	    long l1 = this.mId;
	    StringBuilder localStringBuilder4 = localStringBuilder3.append(l1).append(", mLastCalledTime=");
	    long l2 = this.mLastCalledTime;
	    StringBuilder localStringBuilder5 = localStringBuilder4.append(l2).append(", mPrice=");
	    int j = this.mPrice;
	    StringBuilder localStringBuilder6 = localStringBuilder5.append(j).append(", mRentType=");
	    int k = this.mRentType;
	    StringBuilder localStringBuilder7 = localStringBuilder6.append(k).append(", mRoom=");
	    String str2 = this.mRoom;
	    StringBuilder localStringBuilder8 = localStringBuilder7.append(str2).append(", mTitle=");
	    String str3 = this.mTitle;
	    return str3 + "]";
	  }
	  
}
