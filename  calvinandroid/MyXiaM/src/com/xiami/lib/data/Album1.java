package com.xiami.lib.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Album1 implements Parcelable.Creator<Album> {

	public Album createFromParcel(Parcel paramParcel) {
		return (Album) paramParcel.readSerializable();
	}

	public Album[] newArray(int paramInt) {
		return new Album[paramInt];
	}
}
