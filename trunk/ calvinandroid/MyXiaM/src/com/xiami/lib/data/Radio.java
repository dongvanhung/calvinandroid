package com.xiami.lib.data;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.xiami.Song;
import com.xiami.lib.util.WithImage;

public class Radio implements Serializable, Parcelable, WithImage {

	public static final Parcelable.Creator<Radio> CREATOR = new Radio1();
	  private static final long serialVersionUID = -5885358621958774797L;
	  private Bitmap bitmap = null;
	  private int category;
	  private String cover;
	  private String description;
	  private boolean isImageLoaded = false;
	  private int radio_id;
	  private List<Song> songs;
	  private String title;

	  public int describeContents()
	  {
	    return 0;
	  }

	  public Bitmap getBitmap()
	  {
	    return this.bitmap;
	  }

	  public int getCategory()
	  {
	    return this.category;
	  }

	  public String getCover()
	  {
	    return this.cover;
	  }

	  public String getDescription()
	  {
	    return this.description;
	  }

	  public String getImageUrl()
	  {
	    return this.cover;
	  }

	  public int getRadio_id()
	  {
	    return this.radio_id;
	  }

	  public List<Song> getSongs()
	  {
	    return this.songs;
	  }

	  public String getTitle()
	  {
	    return this.title;
	  }

	  public boolean isImageLoaded()
	  {
	    return this.isImageLoaded;
	  }

	  public void setBitmap(Bitmap paramBitmap)
	  {
	    this.bitmap = paramBitmap;
	  }

	  public void setCategory(int paramInt)
	  {
	    this.category = paramInt;
	  }

	  public void setCover(String paramString)
	  {
	    this.cover = paramString;
	  }

	  public void setDescription(String paramString)
	  {
	    if (paramString.trim().equals("null"));
	    for (this.description = ""; ; this.description = paramString)
	      return;
	  }

	  public void setImageLoaded(boolean paramBoolean)
	  {
	    this.isImageLoaded = paramBoolean;
	  }

	  public void setRadio_id(int paramInt)
	  {
	    this.radio_id = paramInt;
	  }

	  public void setSongs(List<Song> paramList)
	  {
	    this.songs = paramList;
	  }

	  public void setTitle(String paramString)
	  {
	    this.title = paramString;
	  }

	  public void writeToParcel(Parcel paramParcel, int paramInt)
	  {
	    paramParcel.writeSerializable(this);
	  }
}
