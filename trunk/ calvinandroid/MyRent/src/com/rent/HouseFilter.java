package com.rent;

import java.io.Serializable;

import android.content.Context;

public class HouseFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	  private int mDistanceLength = 3;
	  private boolean mIsAgency;
	  private boolean mIsPersonal;
	  private boolean mIsRentAll;
	  private boolean mIsRentPart;
	  private int mPriceHight;
	  private int mPriceLow;
	  private int mRoomNumber;

	  public HouseFilter()
	  {
	  }

	  public HouseFilter(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
	  {
	    this.mPriceHight = paramInt1;
	    this.mPriceLow = paramInt2;
	    this.mRoomNumber = paramInt3;
	    this.mDistanceLength = paramInt4;
	    this.mIsPersonal = paramBoolean1;
	    this.mIsAgency = paramBoolean2;
	    this.mIsRentPart = paramBoolean3;
	    this.mIsRentAll = paramBoolean4;
	  }

	  public int getmDistanceLength()
	  {
	    return this.mDistanceLength;
	  }

	  public int getmPriceHight()
	  {
	    return this.mPriceHight;
	  }

	  public int getmPriceLow()
	  {
	    return this.mPriceLow;
	  }

	  public int getmRoomNumber()
	  {
	    return this.mRoomNumber;
	  }

	  public boolean ismIsAgency()
	  {
	    return this.mIsAgency;
	  }

	  public boolean ismIsPersonal()
	  {
	    return this.mIsPersonal;
	  }

	  public boolean ismIsRentAll()
	  {
	    return this.mIsRentAll;
	  }

	  public boolean ismIsRentPart()
	  {
	    return this.mIsRentPart;
	  }

	  public void setmDistanceLength(int paramInt)
	  {
	    this.mDistanceLength = paramInt;
	  }

	  public void setmIsAgency(boolean paramBoolean)
	  {
	    this.mIsAgency = paramBoolean;
	  }

	  public void setmIsPersonal(boolean paramBoolean)
	  {
	    this.mIsPersonal = paramBoolean;
	  }

	  public void setmIsRentAll(boolean paramBoolean)
	  {
	    this.mIsRentAll = paramBoolean;
	  }

	  public void setmIsRentPart(boolean paramBoolean)
	  {
	    this.mIsRentPart = paramBoolean;
	  }

	  public void setmPriceHight(int paramInt)
	  {
	    this.mPriceHight = paramInt;
	  }

	  public void setmPriceLow(int paramInt)
	  {
	    this.mPriceLow = paramInt;
	  }

	  public void setmRoomNumber(int paramInt)
	  {
	    this.mRoomNumber = paramInt;
	  }

	  public String toDisplayedString(Context paramContext)
	  {
		  return "";
	   /* int i = 0;
	    StringBuilder localStringBuilder1 = new StringBuilder();
	    StringBuilder localStringBuilder2 = localStringBuilder1.append("  ");
	    if ((getmPriceLow() == -1) && (getmPriceHight() == -1))
	    {
	      i = 0 + 1;
	      if ((getmRoomNumber() != -1) && (getmRoomNumber() != 0))
	        break label310;
	      i += 1;
	      label60: if ((!ismIsRentPart()) || (ismIsRentAll()))
	        break label373;
	      String str1 = paramContext.getString(2131361937);
	      StringBuilder localStringBuilder3 = localStringBuilder1.append(str1);
	      StringBuilder localStringBuilder4 = localStringBuilder1.append("  ");
	      label98: if ((!ismIsAgency()) || (ismIsPersonal()))
	        break label421;
	      String str2 = paramContext.getString(2131361934);
	      StringBuilder localStringBuilder5 = localStringBuilder1.append(str2);
	      StringBuilder localStringBuilder6 = localStringBuilder1.append("  ");
	      label136: if (i != 4)
	        break label469;
	      String str3 = paramContext.getString(2131361879);
	      StringBuilder localStringBuilder7 = localStringBuilder1.append(str3);
	      StringBuilder localStringBuilder8 = localStringBuilder1.append(" ");
	    }
	    while (true)
	    {
	      return localStringBuilder1.toString();
	      if ((getmPriceLow() != -1) && (getmPriceHight() == -1))
	      {
	        StringBuilder localStringBuilder9 = new StringBuilder();
	        int j = getmPriceLow();
	        String str4 = j + "以上";
	        StringBuilder localStringBuilder10 = localStringBuilder1.append(str4);
	        StringBuilder localStringBuilder11 = localStringBuilder1.append("  ");
	        break;
	      }
	      StringBuilder localStringBuilder12 = new StringBuilder();
	      int k = getmPriceLow();
	      StringBuilder localStringBuilder13 = localStringBuilder12.append(k).append("-");
	      int m = getmPriceHight();
	      String str5 = m + "元";
	      StringBuilder localStringBuilder14 = localStringBuilder1.append(str5);
	      StringBuilder localStringBuilder15 = localStringBuilder1.append("  ");
	      break;
	      StringBuilder localStringBuilder16 = new StringBuilder();
	      int n = getmRoomNumber();
	      StringBuilder localStringBuilder17 = localStringBuilder16.append(n);
	      String str6 = paramContext.getString(2131361868);
	      String str7 = str6;
	      StringBuilder localStringBuilder18 = localStringBuilder1.append(str7);
	      StringBuilder localStringBuilder19 = localStringBuilder1.append("  ");
	      break label60;
	      label373: if ((!ismIsRentPart()) && (ismIsRentAll()))
	      {
	        String str8 = paramContext.getString(2131361936);
	        StringBuilder localStringBuilder20 = localStringBuilder1.append(str8);
	        StringBuilder localStringBuilder21 = localStringBuilder1.append("  ");
	        break label98;
	      }
	      i += 1;
	      break label98;
	      label421: if ((!ismIsAgency()) && (ismIsPersonal()))
	      {
	        String str9 = paramContext.getString(2131361935);
	        StringBuilder localStringBuilder22 = localStringBuilder1.append(str9);
	        StringBuilder localStringBuilder23 = localStringBuilder1.append("  ");
	        break label136;
	      }
	      i += 1;
	      break label136;
	      StringBuilder localStringBuilder24 = localStringBuilder1.append(" ");
	    }*/
	  }

	  public String toString()
	  {
	    StringBuilder localStringBuilder1 = new StringBuilder().append("HouseFilter [mDistanceLength=");
	    int i = this.mDistanceLength;
	    StringBuilder localStringBuilder2 = localStringBuilder1.append(i).append(", mIsAgency=");
	    boolean bool1 = this.mIsAgency;
	    StringBuilder localStringBuilder3 = localStringBuilder2.append(bool1).append(", mIsPersonal=");
	    boolean bool2 = this.mIsPersonal;
	    StringBuilder localStringBuilder4 = localStringBuilder3.append(bool2).append(", mIsRentAll=");
	    boolean bool3 = this.mIsRentAll;
	    StringBuilder localStringBuilder5 = localStringBuilder4.append(bool3).append(", mIsRentPart=");
	    boolean bool4 = this.mIsRentPart;
	    StringBuilder localStringBuilder6 = localStringBuilder5.append(bool4).append(", mPriceHight=");
	    int j = this.mPriceHight;
	    StringBuilder localStringBuilder7 = localStringBuilder6.append(j).append(", mPriceLow=");
	    int k = this.mPriceLow;
	    StringBuilder localStringBuilder8 = localStringBuilder7.append(k).append(", mRoomNumber=");
	    int m = this.mRoomNumber;
	    return m + "]";
	  }
	  
}
