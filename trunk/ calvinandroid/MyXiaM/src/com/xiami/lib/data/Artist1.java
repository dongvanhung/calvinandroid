package com.xiami.lib.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist1 implements Parcelable.Creator<Artist> {

	public Artist createFromParcel(Parcel paramParcel) {
		return (Artist) paramParcel.readSerializable();
	}

	public Artist[] newArray(int paramInt) {
		return new Artist[paramInt];
	}
}
