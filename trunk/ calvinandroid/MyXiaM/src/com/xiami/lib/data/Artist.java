package com.xiami.lib.data;

import java.io.Serializable;
import java.util.List;

import com.xiami.lib.util.WithImage;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements WithImage, Parcelable, Serializable {

	public static final Parcelable.Creator<Artist> CREATOR = new Artist1();
	  private static final long serialVersionUID = 32872698739949973L;
	  private List<Album> albums;
	  private String desc;
	  protected int id;
	  private boolean isImageLoaded = false;
	  protected String logo;
	  protected String name;
	  protected int radioId;

	  public int describeContents()
	  {
	    return 0;
	  }

	  public List<Album> getAlbums()
	  {
	    return this.albums;
	  }

	  public String getDesc()
	  {
	    return this.desc;
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

	  public String getName()
	  {
	    return this.name;
	  }

	  public int getRadioId()
	  {
	    return this.radioId;
	  }

	  public boolean isImageLoaded()
	  {
	    return this.isImageLoaded;
	  }

	  public void setAlbums(List<Album> paramList)
	  {
	    this.albums = paramList;
	  }

	  public void setDesc(String paramString)
	  {
	    this.desc = paramString;
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

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }

	  public void setRadioId(int paramInt)
	  {
	    this.radioId = paramInt;
	  }

	  public void writeToParcel(Parcel paramParcel, int paramInt)
	  {
	    paramParcel.writeSerializable(this);
	  }
}
