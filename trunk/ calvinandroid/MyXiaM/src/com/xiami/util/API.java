package com.xiami.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.xiami.Song;
import com.xiami.XiamiApp;
import com.xiami.exception.URLArgNotFoundException;
import com.xiami.lib.data.Album;
import com.xiami.lib.data.Artist;
import com.xiami.lib.data.Collect;
import com.xiami.lib.data.Radio;
import com.xiami.lib.data.RadioCategory;
import com.xiami.lib.data.SearchResultItem;
import com.xiami.lib.util.URL;

public class API {

	private static final String TAG = "WebUtil";
	  private static final String url_action_fav = "http://www.xiami.com/app/android/fav?id={id}&type={type}";
	  private static final String url_action_unfav = "http://www.xiami.com/app/android/unfav?id={id}&type={type}";
	  private static final String url_album = "http://www.xiami.com/app/android/album?id={id}&uid={uid}";
	  private static final String url_artist = "http://www.xiami.com/app/android/artist?id={id}";
	  private static final String url_artist_albums = "http://www.xiami.com/app/android/artist-albums?id={id}&page={page}";
	  private static final String url_artist_radio = "http://www.xiami.com/app/android/radio-artist?id={id}";
	  private static final String url_artist_top_song = "http://www.xiami.com/app/android/artist-topsongs?id={id}";
	  private static final String url_artsit_similars = "http://www.xiami.com/app/android/artist-similar?id={id}";
	  private static final String url_collect = "http://www.xiami.com/app/android/collect?id={id}&uid={uid}";
	  private static final String url_grade = "http://www.xiami.com/app/android/grade?id={id}&grade={grade}";
	  private static final String url_lib_albums = "http://www.xiami.com/app/android/lib-albums?uid={uid}&page={page}";
	  private static final String url_lib_artists = "http://www.xiami.com/app/android/lib-artists?uid={uid}&page={page}";
	  private static final String url_lib_collects = "http://www.xiami.com/app/android/lib-collects?uid={uid}&page={page}";
	  private static final String url_lib_songs = "http://www.xiami.com/app/android/lib-songs?uid={uid}&page={page}";
	  private static final String url_myplaylist = "http://www.xiami.com/app/android/myplaylist?uid={uid}";
	  private static final String url_myradiosongs = "http://www.xiami.com/app/android/lib-rnd?uid={uid}";
	  private static final String url_playlog = "http://www.xiami.com/app/android/playlog?id={id}&uid={uid}";
	  private static final String url_radio = "http://www.xiami.com/app/android/radio?id={id}&uid={uid}";
	  private static final String url_radio_categories = "http://www.xiami.com/app/android/radio-category";
	  private static final String url_rndsongs = "http://www.xiami.com/app/android/rnd?uid={uid}";
	  private static final String url_search_all = "http://www.xiami.com/app/android/searchv1?key={key}";
	  private static final String url_search_parts = "http://www.xiami.com/app/android/search-part?key={key}&type={type}&page={page}";
	  private XiamiApp app;
	  private Context mContext;

	  public API(XiamiApp paramXiamiApp)
	  {
	    this.app = paramXiamiApp;
	    Context localContext = this.app.getApplicationContext();
	    this.mContext = localContext;
	  }

	  private boolean fav(int paramInt, Favtype paramFavtype)
	  {
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return false;
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(paramInt);
	        Object localObject1 = localHashMap.put("id", str1);
	        String str2 = paramFavtype.toString();
	        Object localObject2 = localHashMap.put("type", str2);
	        String str3 = URL.parseUrl("http://www.xiami.com/app/android/fav?id={id}&type={type}", localHashMap);
	        String str4 = this.app.getMember().getEmail();
	        String str5 = this.app.getMember().getPassword();
	        String str6 = Web.getJSONObject(str3, str4, str5).getString("status");
	        boolean bool = "ok".equalsIgnoreCase(str6);
	        return bool;
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return false;
	  }

	  private boolean unfav(int paramInt, Favtype paramFavtype)
	  {
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return false;
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(paramInt);
	        Object localObject1 = localHashMap.put("id", str1);
	        String str2 = paramFavtype.toString();
	        Object localObject2 = localHashMap.put("type", str2);
	        String str3 = URL.parseUrl("http://www.xiami.com/app/android/unfav?id={id}&type={type}", localHashMap);
	        String str4 = this.app.getMember().getEmail();
	        String str5 = this.app.getMember().getPassword();
	        String str6 = Web.getJSONObject(str3, str4, str5).getString("status");
	        boolean bool = "ok".equalsIgnoreCase(str6);
	        return bool;
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return false;
	  }

	  public boolean favAlbum(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Album;
	    return fav(paramInt, localFavtype);
	  }

	  public boolean favArtist(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Artist;
	    return fav(paramInt, localFavtype);
	  }

	  public boolean favCollect(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Collect;
	    return fav(paramInt, localFavtype);
	  }

	  public Album getAlbum(int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    if (!this.app.checkNetwork())
	    	return null;
	    
	    Album localAlbum;
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(paramInt);
	      Object localObject2 = localHashMap.put("id", str1);
	      String str2 = String.valueOf(this.app.getMember().getUser_id());
	      Object localObject3 = localHashMap.put("uid", str2);
	      String str3 = URL.parseUrl("http://www.xiami.com/app/android/album?id={id}&uid={uid}", localHashMap);
	      Context localContext = this.mContext;
	      JSONObject localJSONObject = Web.getCachedJSONObject(str3, localContext).getJSONObject("album");
	      localAlbum = new Album();
	      int i = localJSONObject.getInt("album_id");
	      localAlbum.setId(i);
	      String str4 = localJSONObject.getString("title");
	      localAlbum.setTitle(str4);
	      String str5 = localJSONObject.getString("album_logo");
	      localAlbum.setLogo(str5);
	      String str6 = localJSONObject.getString("artist_name");
	      localAlbum.setArtist_name(str6);
	      String str7 = Html.fromHtml(localJSONObject.getString("description")).toString();
	      localAlbum.setDescription(str7);
	      JSONArray localJSONArray = localJSONObject.getJSONArray("songs");
	      List localList = getSongsFromJsonAry(localJSONArray);
	      localAlbum.setSongs(localList);
	      return localAlbum;
	  }

	  public Artist getArtist(int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
		  Artist localObject1 = null;
	    if (!this.app.checkNetwork())
	    {
	      return localObject1;
	    }
	    
	    HashMap localHashMap = new HashMap();
	    String str1 = String.valueOf(paramInt);
	    Object localObject2 = localHashMap.put("id", str1);
	    String str2 = URL.parseUrl("http://www.xiami.com/app/android/artist?id={id}", localHashMap);
	    Context localContext = this.mContext;
	    JSONObject localJSONObject1 = Web.getCachedJSONObject(str2, localContext);
	    JSONObject localJSONObject2 = localJSONObject1.getJSONObject("artist");
	    Artist localArtist = new Artist();
	    int i = localJSONObject2.getInt("artist_id");
	    localArtist.setId(i);
	    String str3 = localJSONObject2.getString("name");
	    localArtist.setName(str3);
	    String str4 = localJSONObject2.getString("logo");
	    localArtist.setLogo(str4);
	    String str5 = localJSONObject2.getString("description");
	    localArtist.setDesc(str5);
	    JSONArray localJSONArray = null;;
	    ArrayList localArrayList = new ArrayList();
	    int j;
	    int k;
	    if (!localJSONObject1.isNull("new_albums"))
	    {
	      localJSONArray = localJSONObject1.getJSONArray("new_albums");
	      localArrayList = new ArrayList();
	      j = localJSONArray.length();
	    }
	    	for (k = 0; k < localJSONArray.length(); k++) {
	    		JSONObject localJSONObject3 = localJSONArray.getJSONObject(k);
	  	      Album localAlbum = new Album();
	  	      int m = localJSONObject3.getInt("album_id");
	  	      localAlbum.setId(m);
	  	      String str6 = localJSONObject3.getString("title");
	  	      localAlbum.setTitle(str6);
	  	      String str7 = localJSONObject3.getString("album_logo");
	  	      localAlbum.setLogo(str7);
	  	      String str8 = localArtist.getName();
	  	      localAlbum.setArtist_name(str8);
	  	      boolean bool = localArrayList.add(localAlbum);
			}
	    	localArtist.setAlbums(localArrayList);
	        localObject1 = localArtist;
	        return localObject1;
	  }

	  public List<Album> getArtistAlbum(int paramInt1, int paramInt2)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    HashMap localHashMap = new HashMap();
	    String str1 = String.valueOf(paramInt1);
	    Object localObject1 = localHashMap.put("id", str1);
	    String str2 = String.valueOf(paramInt2);
	    Object localObject2 = localHashMap.put("page", str2);
	    String str3 = URL.parseUrl("http://www.xiami.com/app/android/artist-albums?id={id}&page={page}", localHashMap);
	    Context localContext = this.mContext;
	    JSONArray localJSONArray = Web.getCachedJSONObject(str3, localContext).getJSONArray("albums");
	    ArrayList localArrayList = new ArrayList();
	    int i = localJSONArray.length();
	    int j = 0;
	    while (true)
	    {
	      if (j >= i)
	        return localArrayList;
	      JSONObject localJSONObject = localJSONArray.getJSONObject(j);
	      Album localAlbum = new Album();
	      int k = localJSONObject.getInt("album_id");
	      localAlbum.setId(k);
	      String str4 = localJSONObject.getString("title");
	      localAlbum.setTitle(str4);
	      String str5 = localJSONObject.getString("album_logo");
	      localAlbum.setLogo(str5);
	      String str6 = localJSONObject.getString("gmt_publish");
	      localAlbum.setPublishTime(str6);
	      boolean bool = localArrayList.add(localAlbum);
	      j += 1;
	    }
	  }

	  public List<Song> getArtistRadioSongs(int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    if (!this.app.checkNetwork())
	    return null;
	    
	    JSONArray localJSONArray = null;
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(paramInt);
	      Object localObject = localHashMap.put("id", str1);
	      String str2 = URL.parseUrl("http://www.xiami.com/app/android/radio-artist?id={id}", localHashMap);
	      Context localContext = this.mContext;
	      localJSONArray = Web.getCachedJSONObject(str2, localContext).getJSONObject("radio").getJSONArray("songs");
	      List localList = getSongsFromJsonAry(localJSONArray);
	      return localList;
	  }

	  public List<Artist> getArtistSimilarity(int paramInt)
	    throws JSONException, UnsupportedEncodingException, URLArgNotFoundException
	  {
	    HashMap localHashMap = new HashMap();
	    String str1 = String.valueOf(paramInt);
	    Object localObject = localHashMap.put("id", str1);
	    String str2 = URL.parseUrl("http://www.xiami.com/app/android/artist-similar?id={id}", localHashMap);
	    Context localContext = this.mContext;
	    JSONArray localJSONArray = Web.getCachedJSONObject(str2, localContext).getJSONArray("artists");
	    ArrayList localArrayList = new ArrayList();
	    int i = localJSONArray.length();
	    int j = 0;
	    while (true)
	    {
	      if (j >= i)
	        return localArrayList;
	      JSONObject localJSONObject = localJSONArray.getJSONObject(j);
	      Artist localArtist = new Artist();
	      int k = localJSONObject.getInt("artist_id");
	      localArtist.setId(k);
	      String str3 = localJSONObject.getString("name");
	      localArtist.setName(str3);
	      String str4 = localJSONObject.getString("logo");
	      localArtist.setLogo(str4);
	      boolean bool = localArrayList.add(localArtist);
	      j += 1;
	    }
	  }

	  public List<Song> getArtistTopSongs(int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    HashMap localHashMap = new HashMap();
	    String str1 = String.valueOf(paramInt);
	    Object localObject = localHashMap.put("id", str1);
	    String str2 = URL.parseUrl("http://www.xiami.com/app/android/artist-topsongs?id={id}", localHashMap);
	    Context localContext1 = this.mContext;
	    String str3 = str2;
	    Context localContext2 = localContext1;
	    JSONArray localJSONArray1 = Web.getCachedJSONObject(str3, localContext2).getJSONArray("songs");
	    ArrayList localArrayList1 = new ArrayList();
	    int i = localJSONArray1.length();
	    int j = 0;
	    while (true)
	    {
	      int k = j;
	      int m = i;
	      if (k >= m)
	        return localArrayList1;
	      JSONArray localJSONArray2 = localJSONArray1;
	      int n = j;
	      JSONObject localJSONObject1 = localJSONArray2.getJSONObject(n);
	      JSONObject localJSONObject2 = localJSONObject1;
	      String str4 = "song_id";
	      int i1 = localJSONObject2.getInt(str4);
	      JSONObject localJSONObject3 = localJSONObject1;
	      String str5 = "album_id";
	      int i2 = localJSONObject3.getInt(str5);
	      JSONObject localJSONObject4 = localJSONObject1;
	      String str6 = "artist_id";
	      int i3 = localJSONObject4.getInt(str6);
	      JSONObject localJSONObject5 = localJSONObject1;
	      String str7 = "singers";
	      String str8 = localJSONObject5.getString(str7);
	      JSONObject localJSONObject6 = localJSONObject1;
	      String str9 = "name";
	      String str10 = localJSONObject6.getString(str9);
	      JSONObject localJSONObject7 = localJSONObject1;
	      String str11 = "album_logo";
	      String str12 = localJSONObject7.getString(str11);
	      JSONObject localJSONObject8 = localJSONObject1;
	      String str13 = "location";
	      String str14 = localJSONObject8.getString(str13);
	      JSONObject localJSONObject9 = localJSONObject1;
	      String str15 = "artist_name";
	      String str16 = localJSONObject9.getString(str15);
	      JSONObject localJSONObject10 = localJSONObject1;
	      String str17 = "lyric";
	      String str18 = localJSONObject10.getString(str17);
	      Song localSong1 = new Song(i1, i2, i3, str8, str10, str12, str14, "", str16, str18);
	      ArrayList localArrayList2 = localArrayList1;
	      Song localSong2 = localSong1;
	      boolean bool = localArrayList2.add(localSong2);
	      j += 1;
	    }
	  }

	  public Collect getCollect(int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    if (!this.app.checkNetwork())
	    return null;
	    
	    Collect localCollect;
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(paramInt);
	      Object localObject2 = localHashMap.put("id", str1);
	      String str2 = String.valueOf(this.app.getMember().getUser_id());
	      Object localObject3 = localHashMap.put("uid", str2);
	      String str3 = URL.parseUrl("http://www.xiami.com/app/android/collect?id={id}&uid={uid}", localHashMap);
	      Context localContext = this.mContext;
	      JSONObject localJSONObject1 = Web.getCachedJSONObject(str3, localContext);
	      localCollect = new Collect();
	      JSONObject localJSONObject2 = localJSONObject1.getJSONObject("collect");
	      int i = localJSONObject2.getInt("id");
	      localCollect.setId(i);
	      String str4 = localJSONObject2.getString("name");
	      localCollect.setTitle(str4);
	      String str5 = localJSONObject2.getString("logo");
	      localCollect.setCover(str5);
	      String str6 = localJSONObject2.getString("description");
	      localCollect.setDescription(str6);
	      int j = localJSONObject2.getInt("songs_count");
	      localCollect.setSongs_count(j);
	      String str7 = localJSONObject2.getString("nick_name");
	      localCollect.setAuthor(str7);
	      JSONArray localJSONArray = localJSONObject2.getJSONArray("songs");
	      List localList = getSongsFromJsonAry(localJSONArray);
	      localCollect.setSongs(localList);
	      return localCollect;
	  }

	  public Vector<Album> getLibAlbums(int paramInt)
	  {
		  Vector localVector = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	      {
	    	  return null;
	      }
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(this.app.getMember().getUser_id());
	      Object localObject2 = localHashMap.put("uid", str1);
	      String str2 = String.valueOf(paramInt);
	      Object localObject3 = localHashMap.put("page", str2);
	      JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/lib-albums?uid={uid}&page={page}", localHashMap)).getJSONArray("albums");
	      localVector = new Vector();
	      int i = 0;
	      for (int j = 0; j < localJSONArray.length(); j++) {
	    	  JSONObject localJSONObject = localJSONArray.getJSONObject(i);
		        Album localAlbum = new Album();
		        int k = localJSONObject.getInt("obj_id");
		        localAlbum.setId(k);
		        String str3 = localJSONObject.getString("title");
		        localAlbum.setTitle(str3);
		        String str4 = localJSONObject.getString("logo");
		        localAlbum.setLogo(str4);
		        int m = localJSONObject.getInt("songs_count");
		        localAlbum.setSongs_count(m);
		        String str5 = localJSONObject.getString("artist_name");
		        localAlbum.setArtist_name(str5);
		        boolean bool = localVector.add(localAlbum);
	    }
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localVector;
	  }

	  public Vector<Artist> getLibArtists(int paramInt)
	  {
		  Vector localVector = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	      {
	        return null;
	      }
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(this.app.getMember().getUser_id());
	      Object localObject2 = localHashMap.put("uid", str1);
	      String str2 = String.valueOf(paramInt);
	      Object localObject3 = localHashMap.put("page", str2);
	      JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/lib-artists?uid={uid}&page={page}", localHashMap)).getJSONArray("artists");
	      localVector = new Vector();
	      for (int j = 0; j < localJSONArray.length(); j++) {
	    	  JSONObject localJSONObject = localJSONArray.getJSONObject(j);
	          Artist localArtist = new Artist();
	          int m = localJSONObject.getInt("artist_id");
	          localArtist.setId(m);
	          int n = localJSONObject.getInt("obj_id");
	          localArtist.setRadioId(n);
	          String str3 = localJSONObject.getString("title");
	          localArtist.setName(str3);
	          String str4 = localJSONObject.getString("logo");
	          localArtist.setLogo(str4);
	          boolean bool = localVector.add(localArtist);
		}
	    }
	    catch (Exception localException2)
	    {
	        String str5 = localException2.getMessage();
	        int i1 = Log.e("WebUtil", str5);
	    }
	    return localVector;
	  }

	  public Vector<Collect> getLibCollects(int paramInt)
	  {
		  Vector localVector = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	      {
	        return null;
	      }
	      HashMap localHashMap = new HashMap();
	      String str1 = String.valueOf(this.app.getMember().getUser_id());
	      Object localObject2 = localHashMap.put("uid", str1);
	      String str2 = String.valueOf(paramInt);
	      Object localObject3 = localHashMap.put("page", str2);
	      JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/lib-collects?uid={uid}&page={page}", localHashMap)).getJSONArray("collects");
	      localVector = new Vector();
	      int i = 0;
	      for (int j = 0; j < localJSONArray.length(); j++) {
	    	  JSONObject localJSONObject = localJSONArray.getJSONObject(i);
	          Collect localCollect = new Collect();
	          int m = localJSONObject.getInt("obj_id");
	          localCollect.setId(m);
	          String str3 = localJSONObject.getString("title");
	          localCollect.setTitle(str3);
	          String str4 = localJSONObject.getString("logo");
	          localCollect.setCover(str4);
	          int n = localJSONObject.getInt("songs_count");
	          localCollect.setSongs_count(n);
	          String str5 = localJSONObject.getString("owner_nick_name");
	          localCollect.setAuthor(str5);
	          boolean bool = localVector.add(localCollect);
		}
	    }
	    catch (Exception localException2)
	    {
	        localException2.printStackTrace();
	        Object localObject1 = null;
	    }
	    return localVector;
	  }

	  public List<Song> getLibSongs(int paramInt)
	  {
		  List localList = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return null;
	     
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject2 = localHashMap.put("uid", str1);
	        String str2 = String.valueOf(paramInt);
	        Object localObject3 = localHashMap.put("page", str2);
	        JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/lib-songs?uid={uid}&page={page}", localHashMap)).getJSONArray("songs");
	        localList = getSongsFromJsonAry(localJSONArray);
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localList;
	  }

	  public List<Song> getMyPlaylist()
	  {
		  List localList = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return null;
	      
	        HashMap localHashMap = new HashMap();
	        String str = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject2 = localHashMap.put("uid", str);
	        JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/myplaylist?uid={uid}", localHashMap)).getJSONArray("songs");
	        localList = getSongsFromJsonAry(localJSONArray);
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localList;
	  }

	  public List<Song> getMyRadioSongs()
	  {
		  List localList = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return null;
	      
	        HashMap localHashMap = new HashMap();
	        String str = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject2 = localHashMap.put("uid", str);
	        JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/lib-rnd?uid={uid}", localHashMap)).getJSONArray("songs");
	        localList = getSongsFromJsonAry(localJSONArray);
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localList;
	  }

	  public Radio getRadio(int paramInt)
	  {
		  
		  Radio localRadio = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return null;
	      
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(paramInt);
	        Object localObject2 = localHashMap.put("id", str1);
	        String str2 = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject3 = localHashMap.put("uid", str2);
	        JSONObject localJSONObject = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/radio?id={id}&uid={uid}", localHashMap)).getJSONObject("radio");
	        localRadio = new Radio();
	        int i = localJSONObject.getInt("id");
	        localRadio.setRadio_id(i);
	        String str3 = localJSONObject.getString("name");
	        localRadio.setTitle(str3);
	        String str4 = localJSONObject.getString("logo");
	        localRadio.setCover(str4);
	        if (localJSONObject.has("desc"))
	        {
	          String str5 = localJSONObject.getString("desc");
	          localRadio.setDescription(str5);
	        }
	        JSONArray localJSONArray = localJSONObject.getJSONArray("songs");
	        List localList = getSongsFromJsonAry(localJSONArray);
	        localRadio.setSongs(localList);
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localRadio;
	  }

	  public List<RadioCategory> getRadioCategories()
	    throws ClientProtocolException, JSONException, IOException
	  {
	    Context localContext = this.mContext;
	    JSONArray localJSONArray1 = Web.getCachedJSONObject("http://www.xiami.com/app/android/radio-category", localContext).getJSONArray("categorys");
	    int i = localJSONArray1.length();
	    ArrayList localArrayList1 = new ArrayList();
	    for (int j2 = 0; j2 < localJSONArray1.length(); j2++) {
	    	 JSONObject localJSONObject1 = localJSONArray1.getJSONObject(j2);
	 	    RadioCategory localRadioCategory = new RadioCategory();
	 	    String str1 = localJSONObject1.getString("category_name");
	 	    localRadioCategory.setName(str1);
	 	    JSONArray localJSONArray2 = localJSONObject1.getJSONArray("radios");
	 	    ArrayList localArrayList2 = new ArrayList();
	 	    for (int l = 0; l < localJSONArray2.length(); l++) {
	 	    	 JSONObject localJSONObject2 = localJSONArray2.getJSONObject(l);
		 	      Radio localRadio = new Radio();
		 	      String str2 = localJSONObject2.getString("radio_name");
		 	      localRadio.setTitle(str2);
		 	      int n = localJSONObject2.getInt("radio_id");
		 	      localRadio.setRadio_id(n);
		 	      boolean bool2 = localArrayList2.add(localRadio);
			}
	 	   localRadioCategory.setRadios(localArrayList2);
	        boolean bool1 = localArrayList1.add(localRadioCategory);
		}
	    return localArrayList1;
	  }

	  public List<Song> getRndSongs()
	  {
		  List localList = null;
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return null;
	     
	        HashMap localHashMap = new HashMap();
	        String str = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject2 = localHashMap.put("uid", str);
	        JSONArray localJSONArray = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/rnd?uid={uid}", localHashMap)).getJSONArray("songs");
	        localList = getSongsFromJsonAry(localJSONArray);
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return localList;
	  }

	  public List<Song> getSongsFromJsonAry(JSONArray paramJSONArray)
	  {
	    if (!this.app.checkNetwork())
	    {
	    	return null;
	    }
	    ArrayList localArrayList2 = null;
		try {
			localArrayList2 = new ArrayList();
			int i = 0;
			for (int j = 0; j < paramJSONArray.length(); j++) {
				JSONArray localJSONArray = paramJSONArray;
			    int k = i;
			    JSONObject localJSONObject = localJSONArray.getJSONObject(k);
			    int m = localJSONObject.getInt("song_id");
			    int n = localJSONObject.getInt("album_id");
			    int i1 = localJSONObject.getInt("artist_id");
			    String str1 = localJSONObject.getString("singers");
			    String str2 = localJSONObject.getString("name");
			    String str3 = localJSONObject.getString("album_logo");
			    String str4 = localJSONObject.getString("location");
			    String str5 = localJSONObject.getString("title");
			    String str6 = localJSONObject.getString("artist_name");
			    String str7 = localJSONObject.getString("lyric");
			    Song localSong1 = new Song(m, n, i1, str1, str2, str3, str4, str5, str6, str7);
			    int i2 = localJSONObject.getInt("grade") + 1;
			    localSong1.setGrade(i2);
			    Song localSong2 = localSong1;
			    boolean bool = localArrayList2.add(localSong2);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	      return localArrayList2;
	  }

	  public boolean playlog(int paramInt)
	  {
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return false;
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(paramInt);
	        Object localObject1 = localHashMap.put("id", str1);
	        String str2 = String.valueOf(this.app.getMember().getUser_id());
	        Object localObject2 = localHashMap.put("uid", str2);
	        String str3 = Web.getJSONObject(URL.parseUrl("http://www.xiami.com/app/android/playlog?id={id}&uid={uid}", localHashMap)).getString("status");
	        boolean bool = "ok".equalsIgnoreCase(str3);
	        return bool;
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	      }
	    return false;
	  }

	  public Map<SearchResultItem.SEARCH_TYPE, List<SearchResultItem>> searchAll(String paramString)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    HashMap localHashMap1 = new HashMap();
	    Object localObject1 = localHashMap1.put("key", paramString);
	    String str1 = URL.parseUrl("http://www.xiami.com/app/android/searchv1?key={key}", localHashMap1);
	    Context localContext = this.mContext;
	    JSONObject localJSONObject = Web.getCachedJSONObject(str1, localContext);
	    HashMap localHashMap2 = new HashMap();
	    SearchResultItem.SEARCH_TYPE[] arrayOfSEARCH_TYPE = SearchResultItem.SEARCH_TYPE.values();
	    for (int i = 0; i < arrayOfSEARCH_TYPE.length; i++) {
	    	SearchResultItem.SEARCH_TYPE localSEARCH_TYPE = arrayOfSEARCH_TYPE[i];
	 	    String str2 = localSEARCH_TYPE.getName();
	 	    if (!localJSONObject.isNull(str2)) {
		 	   List<SearchResultItem> localObject2 = SearchResultItem.makeList(localJSONObject.getJSONArray(str2), localSEARCH_TYPE);
		 	   localHashMap2.put(localSEARCH_TYPE, localObject2);
	 	    }
		}
	      return localHashMap2;
	  }

	  public List<SearchResultItem> searchByType(String paramString, SearchResultItem.SEARCH_TYPE paramSEARCH_TYPE)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    return searchByType(paramString, paramSEARCH_TYPE, 0);
	  }

	  public List<SearchResultItem> searchByType(String paramString, SearchResultItem.SEARCH_TYPE paramSEARCH_TYPE, int paramInt)
	    throws UnsupportedEncodingException, URLArgNotFoundException, JSONException
	  {
	    HashMap localHashMap = new HashMap();
	    Object localObject1 = localHashMap.put("key", paramString);
	    String str1 = String.valueOf(paramInt);
	    Object localObject2 = localHashMap.put("page", str1);
	    String str2 = paramSEARCH_TYPE.getName();
	    Object localObject3 = localHashMap.put("type", str2);
	    String str3 = URL.parseUrl("http://www.xiami.com/app/android/search-part?key={key}&type={type}&page={page}", localHashMap);
	    Context localContext = this.mContext;
	    JSONObject localJSONObject = Web.getCachedJSONObject(str3, localContext);
	    if (localJSONObject.isNull("data"));
	    for (List localList = null; ; localList = SearchResultItem.makeList(localJSONObject.getJSONArray("data"), paramSEARCH_TYPE))
	      return localList;
	  }

	  public boolean unfavAlbum(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Album;
	    return unfav(paramInt, localFavtype);
	  }

	  public boolean unfavArtist(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Artist;
	    return unfav(paramInt, localFavtype);
	  }

	  public boolean unfavCollect(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Collect;
	    return unfav(paramInt, localFavtype);
	  }

	  public boolean unfavSong(int paramInt)
	  {
	    Favtype localFavtype = Favtype.Song;
	    return unfav(paramInt, localFavtype);
	  }

	  public boolean updateGrade(int paramInt1, int paramInt2)
	  {
	    try
	    {
	      if (!this.app.checkNetwork())
	    	  return false;
	        HashMap localHashMap = new HashMap();
	        String str1 = String.valueOf(paramInt1);
	        Object localObject1 = localHashMap.put("id", str1);
	        String str2 = String.valueOf(paramInt2);
	        Object localObject2 = localHashMap.put("grade", str2);
	        String str3 = URL.parseUrl("http://www.xiami.com/app/android/grade?id={id}&grade={grade}", localHashMap);
	        String str4 = this.app.getMember().getEmail();
	        String str5 = this.app.getMember().getPassword();
	        String str6 = Web.getJSONObject(str3, str4, str5).getString("status");
	        boolean bool = "ok".equalsIgnoreCase(str6);
	        return bool;
	    }
	    catch (Exception localException)
	    {
	        localException.printStackTrace();
	    }
	    return false;
	  }

	  enum Favtype
	  {
		  Album,
		  Artist,
		  Collect,
		  Radio,
		  Song;
	  }
}
