package com.xiami.lib.data;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.xiami.Song;
import com.xiami.lib.util.WithImage;

public class Collect implements Parcelable, Serializable, WithImage {

	public static final Parcelable.Creator<Collect> CREATOR = new Collect1();
	  private static final long serialVersionUID = -3259233968883659568L;
	  private String author;
	  private Bitmap bitmap = null;
	  private String cover;
	  private String description;
	  private int downloads;
	  private boolean isImageLoaded = false;
	  private int is_local = 0;
	  private int list_id;
	  private List<Song> songs;
	  private int songs_count;
	  private String title;

	  public Collect()
	  {
	  }

	  public Collect(Parcel paramParcel)
	  {
	    Collect localCollect = (Collect)paramParcel.readSerializable();
	    int i = localCollect.getList_id();
	    this.list_id = i;
	    String str1 = localCollect.getTitle();
	    this.title = str1;
	    String str2 = localCollect.getAuthor();
	    this.author = str2;
	    String str3 = localCollect.getCover();
	    this.cover = str3;
	    String str4 = localCollect.getDescription();
	    this.description = str4;
	    int j = localCollect.getSongs_count();
	    this.songs_count = j;
	    int k = localCollect.getDownloads();
	    this.downloads = k;
	    List localList = localCollect.getSongs();
	    this.songs = localList;
	  }

	  public int describeContents()
	  {
	    return 0;
	  }

	  public String getAuthor()
	  {
	    return this.author;
	  }

	  public Bitmap getBitmap()
	  {
	    return this.bitmap;
	  }

	  public String getCover()
	  {
	    return this.cover;
	  }

	  public String getDescription()
	  {
	    return this.description;
	  }

	  public int getDownloads()
	  {
	    return this.downloads;
	  }

	  public String getImageUrl()
	  {
	    return this.cover;
	  }

	  public int getIs_local()
	  {
	    return this.is_local;
	  }

	  public int getList_id()
	  {
	    return this.list_id;
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

	  public void setAuthor(String paramString)
	  {
	    this.author = paramString;
	  }

	  public void setBitmap(Bitmap paramBitmap)
	  {
	    this.bitmap = paramBitmap;
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

	  public void setDownloads(int paramInt)
	  {
	    this.downloads = paramInt;
	  }

	  public void setId(int paramInt)
	  {
	    this.list_id = paramInt;
	  }

	  public void setImageLoaded(boolean paramBoolean)
	  {
	    this.isImageLoaded = paramBoolean;
	  }

	  public void setIs_local(int paramInt)
	  {
	    this.is_local = paramInt;
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
