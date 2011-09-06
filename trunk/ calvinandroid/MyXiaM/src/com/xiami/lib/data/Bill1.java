package com.xiami.lib.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Bill1 implements Parcelable.Creator<Bill> {

	public Bill createFromParcel(Parcel paramParcel)
	  {
	    return (Bill)paramParcel.readSerializable();
	  }

	  public Bill[] newArray(int paramInt)
	  {
	    return new Bill[paramInt];
	  }
}
