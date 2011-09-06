package com.xiami.lib.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Collect1 implements Parcelable.Creator<Collect> {

	public Collect createFromParcel(Parcel paramParcel) {
		return new Collect(paramParcel);
	}

	public Collect[] newArray(int paramInt) {
		return new Collect[paramInt];
	}
}
