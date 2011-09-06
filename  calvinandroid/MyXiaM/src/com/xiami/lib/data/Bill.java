package com.xiami.lib.data;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Bill implements Serializable, Parcelable {

	public static final Parcelable.Creator<Bill> CREATOR = new Bill1();
	  private static final long serialVersionUID = -1757928186927030712L;
	  private int category;
	  private String categoryName;
	  private int id;
	  private String logo;
	  private String name;

	  public Bill(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3)
	  {
	    this.id = paramInt1;
	    this.name = paramString1;
	    this.logo = paramString2;
	    this.category = paramInt2;
	    this.categoryName = paramString3;
	  }

	  public int describeContents()
	  {
	    return 0;
	  }

	  public int getCategory()
	  {
	    return this.category;
	  }

	  public String getCategoryName()
	  {
	    return this.categoryName;
	  }

	  public int getId()
	  {
	    return this.id;
	  }

	  public String getLogo()
	  {
	    return this.logo;
	  }

	  public String getName()
	  {
	    return this.name;
	  }

	  public void setCategory(int paramInt)
	  {
	    this.category = paramInt;
	  }

	  public void setCategoryName(String paramString)
	  {
	    this.categoryName = paramString;
	  }

	  public void setId(int paramInt)
	  {
	    this.id = paramInt;
	  }

	  public void setLogo(String paramString)
	  {
	    this.logo = paramString;
	  }

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }

	  public void writeToParcel(Parcel paramParcel, int paramInt)
	  {
	    paramParcel.writeSerializable(this);
	  }
}
