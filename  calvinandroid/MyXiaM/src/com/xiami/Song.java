package com.xiami;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import com.xiami.lib.util.WithImage;

public class Song implements Serializable, Parcelable, WithImage {
	
	public static final Parcelable.Creator<Song> CREATOR = new Song1();
	  public static final int SONG_STATUS_DOWNLOAD = 1;
	  public static final int SONG_STATUS_EMPTY = 0;
	  private static final long serialVersionUID = -2800591639959450653L;
	  private int albumId;
	  private String albumName;
	  private int artistId;
	  private String artistName;
	  private String cover;
	  private int downloadStatus = 0;
	  private String fileUrl = null;
	  private int grade;
	  private boolean isImageLoaded = false;
	  private boolean isLocal = false;
	  private String localId;
	  private String lrcFile;
	  private String name;
	  private String path = null;
	  private String singers;
	  private int songId;
	  private int status = 0;
	  
	  public Song()
	  {
	  }

	  public Song(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
	  {
	    this.songId = paramInt1;
	    this.albumId = paramInt2;
	    this.artistId = paramInt3;
	    this.singers = paramString1;
	    this.name = paramString2;
	    this.cover = paramString3;
	    this.fileUrl = paramString4;
	    this.albumName = paramString5;
	    this.artistName = paramString6;
	    this.lrcFile = paramString7;
	  }

	  public int describeContents()
	  {
	    return 0;
	  }

	  public boolean equals(Object paramObject)
	  {
	    int i;
	    if (paramObject == null)
	      return false;
	    else
	    {
	      int j = ((Song)paramObject).songId;
	      int k = this.songId;
	      if (j == k)
	      {
	        return true;
	      }
	      return false;
	    }
	  }

	  public int getAlbumId()
	  {
	    return this.albumId;
	  }

	  public String getAlbumName()
	  {
	    return this.albumName;
	  }

	  public int getArtistId()
	  {
	    return this.artistId;
	  }

	  public String getArtistName()
	  {
	    return this.artistName;
	  }

	  public String getCover()
	  {
	    return this.cover;
	  }

	  public int getDownloadStatus()
	  {
	    return this.downloadStatus;
	  }

	  public String getFileUrl()
	  {
	    return this.fileUrl;
	  }

	  public int getGrade()
	  {
	    return this.grade;
	  }

	  public String getImageUrl()
	  {
	    return this.cover;
	  }

	  public String getLocalId()
	  {
	    return this.localId;
	  }

	  public String getLrcFile()
	  {
	    if ((this.lrcFile == null) || (this.lrcFile.trim().equals("")) || (this.lrcFile.trim().equals("null")));
	    for (String str = null; ; str = this.lrcFile)
	      return str;
	  }

	  public String getName()
	  {
	    return this.name;
	  }

	  public String getPath()
	  {
	    return this.path;
	  }

	  public String getSingers()
	  {
	    return this.singers;
	  }

	  public int getSongId()
	  {
	    return this.songId;
	  }

	  public int getStatus()
	  {
	    return this.status;
	  }

	  public boolean isImageLoaded()
	  {
	    return this.isImageLoaded;
	  }

	  public boolean isLocal()
	  {
	    return this.isLocal;
	  }

	  public void setAlbumId(int paramInt)
	  {
	    this.albumId = paramInt;
	  }

	  public void setAlbumName(String paramString)
	  {
	    this.albumName = paramString;
	  }

	  public void setArtistId(int paramInt)
	  {
	    this.artistId = paramInt;
	  }

	  public void setArtistName(String paramString)
	  {
	    this.artistName = paramString;
	  }

	  public void setCover(String paramString)
	  {
	    this.cover = paramString;
	  }

	  public void setDownloadStatus(int paramInt)
	  {
	    this.downloadStatus = paramInt;
	  }

	  public void setFileUrl(String paramString)
	  {
	    this.fileUrl = paramString;
	  }

	  public void setGrade(int paramInt)
	  {
	    this.grade = paramInt;
	  }

	  public void setId(int paramInt)
	  {
	    this.songId = paramInt;
	  }

	  public void setImageLoaded(boolean paramBoolean)
	  {
	    this.isImageLoaded = paramBoolean;
	  }

	  public void setLocal(boolean paramBoolean)
	  {
	    this.isLocal = paramBoolean;
	  }

	  public void setLocalId(String paramString)
	  {
	    this.localId = paramString;
	  }

	  public void setLrcFile(String paramString)
	  {
	    this.lrcFile = paramString;
	  }

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }

	  public void setPath(String paramString)
	  {
	    this.path = paramString;
	  }

	  public void setSingers(String paramString)
	  {
	    this.singers = paramString;
	  }

	  public void setStatus(int paramInt)
	  {
		  this.status = paramInt;
	  }

	  public void writeToParcel(Parcel paramParcel, int paramInt)
	  {
	    paramParcel.writeSerializable(this);
	  }
}
