package com.xiami;

import android.os.Parcel;
import android.os.Parcelable;

public class Song1 implements Parcelable.Creator<Song> {

	public Song createFromParcel(Parcel paramParcel)
	  {
	    return (Song)paramParcel.readSerializable();
	  }

	  public Song[] newArray(int paramInt)
	  {
	    return new Song[paramInt];
	  }
}
