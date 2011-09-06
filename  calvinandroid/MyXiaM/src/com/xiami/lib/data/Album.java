package com.xiami.lib.data;

import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.xiami.Song;
import com.xiami.lib.util.WithImage;

public class Album implements Serializable, Parcelable, WithImage {

	public static final Parcelable.Creator<Album> CREATOR = new Album1();
	  private static final long serialVersionUID = 8383605080265580289L;
	  private String artist_name;
	  private String description;
	  protected int id;
	  private boolean isImageLoaded = false;
	  protected String logo;
	  protected String publishTime;
	  private List<Song> songs;
	  private int songs_count;
	  protected String title;

	  public int describeContents()
	  {
	    return 0;
	  }

	  public String getArtist_name()
	  {
	    return this.artist_name;
	  }

	  public String getDescription()
	  {
	    return this.description;
	  }

	  public int getId()
	  {
	    return this.id;
	  }

	  public String getImageUrl()
	  {
	    return this.logo;
	  }

	  public String getLogo()
	  {
	    return this.logo;
	  }

	  public String getPublishTime()
	  {
	    return this.publishTime;
	  }

	  public List<Song> getSongs()
	  {
	    return this.songs;
	  }

	  public int getSongs_count()
	  {
	    return this.songs_count;
	  }

	  public String getTitle()
	  {
	    return this.title;
	  }

	  public boolean isImageLoaded()
	  {
	    return this.isImageLoaded;
	  }

	  public void setArtist_name(String paramString)
	  {
	    this.artist_name = paramString;
	  }

	  public void setDescription(String paramString)
	  {
	    this.description = paramString;
	  }

	  public void setId(int paramInt)
	  {
	    this.id = paramInt;
	  }

	  public void setImageLoaded(boolean paramBoolean)
	  {
	    this.isImageLoaded = paramBoolean;
	  }

	  public void setLogo(String paramString)
	  {
	    this.logo = paramString;
	  }

	  public void setPublishTime(String paramString)
	  {
	    this.publishTime = paramString;
	  }

	  public void setSongs(List<Song> paramList)
	  {
	    this.songs = paramList;
	  }

	  public void setSongs_count(int paramInt)
	  {
	    this.songs_count = paramInt;
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
