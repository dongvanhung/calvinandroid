package com.xiami.lib.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xiami.Song;
import com.xiami.lib.util.WithImage;

public class SearchResultItem implements WithImage {

	String extr;
	  int id1;
	  int id2;
	  String imageUrl;
	  boolean isImgLoaded = false;
	  String name1;
	  String name2;

	  public static List<SearchResultItem> makeList(JSONArray paramJSONArray, SEARCH_TYPE paramSEARCH_TYPE)
	    throws JSONException
	  {
	    int i = paramJSONArray.length();
	    ArrayList localArrayList = new ArrayList();
	    int j = paramSEARCH_TYPE.ordinal();
	    switch (j + 1)
	    {
	    default:
	    case 1:
	    	for (int j2 = 0; j2 < paramJSONArray.length(); j2++) {
	    		JSONObject localJSONObject1 = paramJSONArray.getJSONObject(j2);
		        SearchResultItem localSearchResultItem1 = new SearchResultItem();
		        String str1 = localJSONObject1.getString("name");
		        localSearchResultItem1.name1 = str1;
		        int m = localJSONObject1.getInt("id");
		        localSearchResultItem1.id1 = m;
		        String str2 = localJSONObject1.getString("artist_name");
		        localSearchResultItem1.name2 = str2;
		        int n = localJSONObject1.getInt("artist_id");
		        localSearchResultItem1.id2 = n;
		        String str3 = localJSONObject1.getString("logo");
		        localSearchResultItem1.imageUrl = str3;
		        boolean bool1 = localArrayList.add(localSearchResultItem1);
			}
	    	break;
	    case 2:
	    	for (int j2 = 0; j2 < paramJSONArray.length(); j2++) {
		    	JSONObject localJSONObject2 = paramJSONArray.getJSONObject(j2);
		        SearchResultItem localSearchResultItem2 = new SearchResultItem();
		        String str4 = localJSONObject2.getString("name");
		        localSearchResultItem2.name1 = str4;
		        int i1 = localJSONObject2.getInt("id");
		        localSearchResultItem2.id1 = i1;
		        String str5 = localJSONObject2.getString("logo");
		        localSearchResultItem2.imageUrl = str5;
		        boolean bool2 = localArrayList.add(localSearchResultItem2);
	    	}
	    	break;
	    case 4:
	    	for (int j2 = 0; j2 < paramJSONArray.length(); j2++) {
	    		JSONObject localJSONObject3 = paramJSONArray.getJSONObject(j2);
		        SearchResultItem localSearchResultItem3 = new SearchResultItem();
		        String str6 = localJSONObject3.getString("name");
		        localSearchResultItem3.name1 = str6;
		        int i2 = localJSONObject3.getInt("id");
		        localSearchResultItem3.id1 = i2;
		        String str7 = localJSONObject3.getString("artist_name");
		        localSearchResultItem3.name2 = str7;
		        int i3 = localJSONObject3.getInt("artist_id");
		        localSearchResultItem3.id2 = i3;
		        String str8 = localJSONObject3.getString("logo");
		        localSearchResultItem3.imageUrl = str8;
		        String str9 = localJSONObject3.getString("location");
		        localSearchResultItem3.extr = str9;
		        boolean bool3 = localArrayList.add(localSearchResultItem3);
			}
	    	break;
	    case 3:
	    	for (int j2 = 0; j2 < paramJSONArray.length(); j2++) {
	    		JSONObject localJSONObject4 = paramJSONArray.getJSONObject(j2);
		        SearchResultItem localSearchResultItem4 = new SearchResultItem();
		        String str10 = localJSONObject4.getString("name");
		        localSearchResultItem4.name1 = str10;
		        int i4 = localJSONObject4.getInt("id");
		        localSearchResultItem4.id1 = i4;
		        String str11 = localJSONObject4.getString("logo");
		        localSearchResultItem4.imageUrl = str11;
		        boolean bool4 = localArrayList.add(localSearchResultItem4);
			}
	    	break;
	    }
	    return localArrayList;
	  }

	  public String getExtr()
	  {
	    return this.extr;
	  }

	  public int getId1()
	  {
	    return this.id1;
	  }

	  public int getId2()
	  {
	    return this.id2;
	  }

	  public String getImageUrl()
	  {
	    return this.imageUrl;
	  }

	  public String getName1()
	  {
	    return this.name1;
	  }

	  public String getName2()
	  {
	    return this.name2;
	  }

	  public boolean isImageLoaded()
	  {
	    return this.isImgLoaded;
	  }

	  public void setExtr(String paramString)
	  {
	    this.extr = paramString;
	  }

	  public void setId1(int paramInt)
	  {
	    this.id1 = paramInt;
	  }

	  public void setId2(int paramInt)
	  {
	    this.id2 = paramInt;
	  }

	  public void setImageLoaded(boolean paramBoolean)
	  {
	    this.isImgLoaded = true;
	  }

	  public void setImageUrl(String paramString)
	  {
	    this.imageUrl = paramString;
	  }

	  public void setName1(String paramString)
	  {
	    this.name1 = paramString;
	  }

	  public void setName2(String paramString)
	  {
	    this.name2 = paramString;
	  }

	  public Album toAlbum()
	  {
	    Album localAlbum = new Album();
	    String str1 = this.name1;
	    localAlbum.setTitle(str1);
	    String str2 = this.name2;
	    localAlbum.setArtist_name(str2);
	    int i = this.id1;
	    localAlbum.setId(i);
	    return localAlbum;
	  }

	  public Artist toArtist()
	  {
	    Artist localArtist = new Artist();
	    String str = this.name1;
	    localArtist.setName(str);
	    int i = this.id1;
	    localArtist.setId(i);
	    return localArtist;
	  }

	  public Collect toCollect()
	  {
	    Collect localCollect = new Collect();
	    int i = this.id1;
	    localCollect.setId(i);
	    String str = this.name1;
	    localCollect.setTitle(str);
	    return localCollect;
	  }

	  public Song toSong()
	  {
	    Song localSong = new Song();
	    int i = this.id1;
	    localSong.setId(i);
	    String str1 = this.name1;
	    localSong.setName(str1);
	    int j = this.id2;
	    localSong.setArtistId(j);
	    String str2 = this.name2;
	    localSong.setArtistName(str2);
	    String str3 = this.name2;
	    localSong.setSingers(str3);
	    String str4 = this.extr;
	    localSong.setFileUrl(str4);
	    return localSong;
	  }

	  public static enum SEARCH_TYPE
	  {
		  ALBUM,
	    	ARTIST,
	    	COLLECT,
	    	SONG;
		  
	    public String getName()
	    {
	      int i = ordinal();
	      String str = null;
	      switch (i + 1)
	      {
	      default:
	      case 1:
	    	  str = "albums";
	    	  break;
	      case 2:
	    	  str = "artists";
	    	  break;
	      case 4:
	    	  str = "songs";
	    	  break;
	      case 3:
	    	  str = "collects";
	    	  break;
	      }
	      return str;
	    }

	    public String getTitle()
	    {
	      int i = ordinal();
	      String str;
	      switch (i + 1)
	      {
	      default:
	        str = null;
	      case 1:
	    	  str = "专辑";
	    	  break;
	      case 2:
	    	  str = "歌手";
	    	  break;
	      case 4:
	    	  str = "歌曲";
	    	  break;
	      case 3:
	    	  str = "收集";
	    	  break;
	      }
	      return str;
	    }
	  }
}
