package com.xiami.lib.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Radio1 implements Parcelable.Creator<Radio> {

	public Radio createFromParcel(Parcel paramParcel) {
		return (Radio) paramParcel.readSerializable();
	}

	public Radio[] newArray(int paramInt) {
		return new Radio[paramInt];
	}
}
