package com.xiami.localdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xiami.Song;
import com.xiami.lib.data.Bill;
import com.xiami.lib.data.BillCategory;
import com.xiami.lib.data.Member;
import com.xiami.util.FmSetting;

public class DbHelper {

	public static final String DATABASE_NAME = "xiami.db";
	  public static final String TAG = "Db";
	  private static DbHelper mDbInstance;
	  private Context mContext;
	  private SQLiteDatabase mDb;

	  public DbHelper(Context paramContext)
	  {
	    this.mContext = paramContext;
	    SQLiteDatabase localSQLiteDatabase = this.mContext.openOrCreateDatabase("xiami.db", 0, null);
	    this.mDb = localSQLiteDatabase;
	    CreateTable();
	    StringBuilder localStringBuilder = new StringBuilder("db path=");
	    String str1 = this.mDb.getPath();
	    String str2 = str1;
	    int i = Log.i("Db", str2);
	  }

	  private void CreateTable()
	  {
	    try
	    {
	      this.mDb.execSQL("create table if not exists mylib_albums(id integer auto_increment,user_id integer,title text,logo text,obj_id integer,songs_count integer,artist_name text);");
	      this.mDb.execSQL("create table if not exists mylib_artists(id integer auto_increment,user_id integer,title text,logo text,obj_id integer)");
	      this.mDb.execSQL("create table if not exists mylib_radios(id integer auto_increment,user_id integer,title text,logo text,obj_id integer)");
	      this.mDb.execSQL("create table if not exists mylib_collects(id integer auto_increment,user_id integer,title text,logo text,obj_id integer,songs_count integer,owner_user_id integer,owner_nick_name text)");
	      this.mDb.execSQL("create table if not exists serv_bill_categories(id integer,name text)");
	      this.mDb.execSQL("create table if not exists serv_bills(id integer,name text,logo text,category integer)");
	      this.mDb.execSQL("create table if not exists str_cache(id integer,content text,timeout integer)");
	      this.mDb.execSQL("create table if not exists members(user_id integer,nick_name text,password text,email text)");
	      this.mDb.execSQL("create table if not exists fmsetting(offline integer,wifi integer,net3g integer,showmsg_nowifi integer default 1)");
	      this.mDb.execSQL("create table if not exists download_songs(song_id integer,album_id integer,artist_id integer,artist_name text,singer text,name text,cover text,file_url text,album_name text,status integer,download_status integer,path text,grade integer)");
	      int i = Log.d("Db", "Create table success");
	      return;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        String str = localException.getMessage();
	        int j = Log.d("Db", str);
	      }
	    }
	  }

	  public static void closeInstance()
	  {
	    if (mDbInstance != null)
	      mDbInstance.close();
	    mDbInstance = null;
	  }

	  public static DbHelper getInstance(Context paramContext)
	  {
	    if (mDbInstance == null)
	    {
	      mDbInstance = new DbHelper(paramContext);
	      int i = Log.i("Db", "new dbhelper instance");
	    }
	    return mDbInstance;
	  }

	  public void addOfflineSong(Song paramSong)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    ContentValues localContentValues = new ContentValues();
	    Integer localInteger1 = Integer.valueOf(paramSong.getSongId());
	    localContentValues.put("song_id", localInteger1);
	    Integer localInteger2 = Integer.valueOf(paramSong.getAlbumId());
	    localContentValues.put("album_id", localInteger2);
	    Integer localInteger3 = Integer.valueOf(paramSong.getArtistId());
	    localContentValues.put("artist_id", localInteger3);
	    String str1 = paramSong.getArtistName();
	    localContentValues.put("artist_name", str1);
	    String str2 = paramSong.getSingers();
	    localContentValues.put("singer", str2);
	    String str3 = paramSong.getName();
	    localContentValues.put("name", str3);
	    String str4 = paramSong.getCover();
	    localContentValues.put("cover", str4);
	    String str5 = paramSong.getFileUrl();
	    localContentValues.put("file_url", str5);
	    String str6 = paramSong.getAlbumName();
	    localContentValues.put("album_name", str6);
	    Integer localInteger4 = Integer.valueOf(paramSong.getStatus());
	    localContentValues.put("status", localInteger4);
	    Integer localInteger5 = Integer.valueOf(paramSong.getDownloadStatus());
	    localContentValues.put("download_status", localInteger5);
	    String str7 = paramSong.getPath();
	    localContentValues.put("path", str7);
	    Integer localInteger6 = Integer.valueOf(paramSong.getGrade());
	    localContentValues.put("grade", localInteger6);
	    long l = this.mDb.insert("download_songs", "", localContentValues);
	  }

	  public void addOfflineSongs(List<Song> paramList)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    Iterator localIterator = paramList.iterator();
	    while (true)
	    {
	      if (!localIterator.hasNext())
	        return;
	      Song localSong = (Song)localIterator.next();
	      ContentValues localContentValues = new ContentValues();
	      Integer localInteger1 = Integer.valueOf(localSong.getSongId());
	      localContentValues.put("song_id", localInteger1);
	      Integer localInteger2 = Integer.valueOf(localSong.getAlbumId());
	      localContentValues.put("album_id", localInteger2);
	      Integer localInteger3 = Integer.valueOf(localSong.getArtistId());
	      localContentValues.put("artist_id", localInteger3);
	      String str1 = localSong.getArtistName();
	      localContentValues.put("artist_name", str1);
	      String str2 = localSong.getSingers();
	      localContentValues.put("singer", str2);
	      String str3 = localSong.getName();
	      localContentValues.put("name", str3);
	      String str4 = localSong.getCover();
	      localContentValues.put("cover", str4);
	      String str5 = localSong.getFileUrl();
	      localContentValues.put("file_url", str5);
	      String str6 = localSong.getAlbumName();
	      localContentValues.put("album_name", str6);
	      Integer localInteger4 = Integer.valueOf(localSong.getStatus());
	      localContentValues.put("status", localInteger4);
	      Integer localInteger5 = Integer.valueOf(localSong.getDownloadStatus());
	      localContentValues.put("download_status", localInteger5);
	      String str7 = localSong.getPath();
	      localContentValues.put("path", str7);
	      Integer localInteger6 = Integer.valueOf(localSong.getGrade());
	      localContentValues.put("grade", localInteger6);
	      long l = this.mDb.insert("download_songs", "", localContentValues);
	    }
	  }

	  public void cleanBillCategories()
	  {
	    int i = this.mDb.delete("serv_bill_categories", null, null);
	    cleanBills();
	  }

	  public void cleanBills()
	  {
	    int i = this.mDb.delete("serv_bills", null, null);
	  }

	  public void clearOfflineSongs()
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    int i = this.mDb.delete("download_songs", null, null);
	  }

	  public void close()
	  {
	    this.mDb.close();
	  }

	  public void deleteCache(int paramInt)
	  {
	    String[] arrayOfString = new String[1];
	    String str = String.valueOf(paramInt);
	    arrayOfString[0] = str;
	    int i = this.mDb.delete("str_cache", "id = ?", arrayOfString);
	  }

	  public void deleteOfflineSong(Song paramSong)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    String[] arrayOfString = new String[1];
	    String str = String.valueOf(paramSong.getSongId());
	    arrayOfString[0] = str;
	    int i = this.mDb.delete("download_songs", "song_id = ?", arrayOfString);
	  }

	  public String getCache(int paramInt)
	  {
	    String[] arrayOfString1 = new String[2];
	    arrayOfString1[0] = "content";
	    arrayOfString1[1] = "timeout";
	    String[] arrayOfString2 = new String[1];
	    String str1 = String.valueOf(paramInt);
	    arrayOfString2[0] = str1;
	    Cursor localCursor = this.mDb.query("str_cache", arrayOfString1, "id = ?", arrayOfString2, null, null, null);
	    String localObject = null;
	    if (localCursor.getCount() <= 0)
	    {
	      String str2 = "not exist:" + paramInt;
	      int i = Log.d("Db", str2);
	      localCursor.close();
	    }
	    else
	    {
	      boolean bool = localCursor.moveToFirst();
	      if (!localCursor.isAfterLast())
	      {
	        String str3 = localCursor.getString(0);
	        int j = localCursor.getInt(1);
	        localCursor.close();
	        long l1 = j;
	        long l2 = System.currentTimeMillis() / 1000L;
	        if (l1 > l2)
	        {
	          localObject = str3;
	        } else {
		        StringBuilder localStringBuilder = new StringBuilder("timeout:").append(j).append(" ");
		        long l3 = System.currentTimeMillis() / 1000L;
		        String str4 = String.valueOf(l3);
		        int k = Log.d("Db", str4);
		        int m = this.mDb.delete("str_cache", "id = ?", arrayOfString2);
		        localObject = null;
	        }
	      }
	    }
	    return localObject;
	  }

	  public FmSetting getFmSetting()
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    FmSetting localFmSetting1 = new FmSetting();
	    SQLiteDatabase localSQLiteDatabase1 = this.mDb;
	    String str1 = "PRAGMA table_info(`fmsetting`)";
	    String[] arrayOfString1 = null;
	    Cursor localCursor1 = localSQLiteDatabase1.rawQuery(str1, arrayOfString1);
	    if (localCursor1.getCount() < 4)
	    {
	      SQLiteDatabase localSQLiteDatabase2 = this.mDb;
	      String str2 = "alter table fmsetting add showmsg_nowifi integer default 1";
	      localSQLiteDatabase2.execSQL(str2);
	    }
	    localCursor1.close();
	    String[] arrayOfString2 = new String[4];
	    arrayOfString2[0] = "offline";
	    arrayOfString2[1] = "wifi";
	    arrayOfString2[2] = "net3g";
	    arrayOfString2[3] = "showmsg_nowifi";
	    Cursor localCursor2 = this.mDb.query("fmsetting", arrayOfString2, null, null, null, null, null);
	    if (localCursor2.getCount() <= 0)
	    {
	      int i = Log.d("Db", "not exist: FmSetting");
	      localCursor2.close();
	    }
	    else
	    {
	     
	      boolean bool = localCursor2.moveToFirst();
	      if (!localCursor2.isAfterLast())
	      {
	        int j = localCursor2.getInt(0);
	        int k = localCursor2.getInt(1);
	        int m = localCursor2.getInt(2);
	        int n = localCursor2.getInt(3);
	        localCursor2.close();
	        localFmSetting1.setNet3g(m);
	        localFmSetting1.setForceOffline(j);
	        FmSetting localFmSetting3 = localFmSetting1;
	        int i1 = k;
	        localFmSetting3.setWifi(i1);
	        FmSetting localFmSetting4 = localFmSetting1;
	        int i2 = n;
	        localFmSetting4.setShowMessageWhenNoWifi(i2);
	      }
	      localCursor2.close();
	    }
	    return localFmSetting1;
	  }

	  public Member getLoginMember()
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    Member localMember1 = new Member();
	    String[] arrayOfString = new String[4];
	    arrayOfString[0] = "user_id";
	    arrayOfString[1] = "email";
	    arrayOfString[2] = "nick_name";
	    arrayOfString[3] = "password";
	    Cursor localCursor = this.mDb.query("members", arrayOfString, null, null, null, null, null);
	    if (localCursor.getCount() <= 0)
	    {
	      int i = Log.d("Db", "not exist: member");
	      localCursor.close();
	    }
	    else
	    {
	      
	      boolean bool = localCursor.moveToFirst();
	      if (!localCursor.isAfterLast())
	      {
	        int j = localCursor.getInt(0);
	        String str1 = localCursor.getString(1);
	        String str2 = localCursor.getString(2);
	        String str3 = localCursor.getString(3);
	        localCursor.close();
	        localMember1.setEmail(str1);
	        localMember1.setNick_name(str2);
	        localMember1.setUser_id(j);
	        localMember1.setPassword(str3);
	      }
	      localCursor.close();
	    }
	    return localMember1;
	  }

	  public Song getOfflineSong(int paramInt)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    Song localSong = null;
	    String[] arrayOfString1 = new String[13];
	    arrayOfString1[0] = "song_id";
	    arrayOfString1[1] = "album_id";
	    arrayOfString1[2] = "artist_id";
	    arrayOfString1[3] = "artist_name";
	    arrayOfString1[4] = "singer";
	    arrayOfString1[5] = "name";
	    arrayOfString1[6] = "cover";
	    arrayOfString1[7] = "file_url";
	    arrayOfString1[8] = "album_name";
	    arrayOfString1[9] = "status";
	    arrayOfString1[10] = "download_status";
	    arrayOfString1[11] = "path";
	    arrayOfString1[12] = "grade";
	    String[] arrayOfString2 = new String[1];
	    String str1 = String.valueOf(paramInt);
	    arrayOfString2[0] = str1;
	    SQLiteDatabase localSQLiteDatabase = this.mDb;
	    String str2 = null;
	    String str3 = null;
	    Cursor localCursor = localSQLiteDatabase.query("download_songs", arrayOfString1, "song_id = ?", arrayOfString2, null, str2, str3);
	    if (localCursor.getCount() <= 0)
	      localCursor.close();
	    else
	    {
	      boolean bool = localCursor.moveToFirst();
	      if (!localCursor.isAfterLast())
	      {
	        localSong = new Song();
	        int i = localCursor.getInt(0);
	        localSong.setId(i);
	        int j = localCursor.getInt(1);
	        localSong.setAlbumId(j);
	        int k = localCursor.getInt(2);
	        localSong.setArtistId(k);
	        String str4 = localCursor.getString(3);
	        localSong.setArtistName(str4);
	        String str5 = localCursor.getString(4);
	        localSong.setSingers(str5);
	        String str6 = localCursor.getString(5);
	        localSong.setName(str6);
	        String str7 = localCursor.getString(6);
	        localSong.setCover(str7);
	        String str8 = localCursor.getString(7);
	        localSong.setFileUrl(str8);
	        String str9 = localCursor.getString(8);
	        localSong.setAlbumName(str9);
	        int m = localCursor.getInt(9);
	        localSong.setStatus(m);
	        int n = localCursor.getInt(10);
	        localSong.setDownloadStatus(n);
	        String str10 = localCursor.getString(11);
	        localSong.setPath(str10);
	        int i1 = localCursor.getInt(12);
	        localSong.setGrade(i1);
	      }
	      localCursor.close();
	    }
	    return localSong;
	  }

	  public List<Song> getOfflineSongs()
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    List<Song> localArrayList = new ArrayList<Song>();
	    String[] arrayOfString1 = new String[13];
	    arrayOfString1[0] = "song_id";
	    arrayOfString1[1] = "album_id";
	    arrayOfString1[2] = "artist_id";
	    arrayOfString1[3] = "artist_name";
	    arrayOfString1[4] = "singer";
	    arrayOfString1[5] = "name";
	    arrayOfString1[6] = "cover";
	    arrayOfString1[7] = "file_url";
	    arrayOfString1[8] = "album_name";
	    arrayOfString1[9] = "status";
	    arrayOfString1[10] = "download_status";
	    arrayOfString1[11] = "path";
	    arrayOfString1[12] = "grade";
	    SQLiteDatabase localSQLiteDatabase = this.mDb;
	    String[] arrayOfString2 = null;
	    String str1 = null;
	    String str2 = null;
	    String str3 = null;
	    Cursor localCursor = localSQLiteDatabase.query("download_songs", arrayOfString1, null, arrayOfString2, str1, str2, str3);
	    if (localCursor.getCount() <= 0)
	    {
	      localCursor.close();
	    }
	    else
	    {
	    	boolean bool1 = localCursor.moveToFirst();
	      if (localCursor.isAfterLast())
	      {
	        localCursor.close();
	      } else {
		      Song localSong = new Song();
		      int i = localCursor.getInt(0);
		      localSong.setId(i);
		      int j = localCursor.getInt(1);
		      localSong.setAlbumId(j);
		      int k = localCursor.getInt(2);
		      localSong.setArtistId(k);
		      String str4 = localCursor.getString(3);
		      localSong.setArtistName(str4);
		      String str5 = localCursor.getString(4);
		      localSong.setSingers(str5);
		      String str6 = localCursor.getString(5);
		      localSong.setName(str6);
		      String str7 = localCursor.getString(6);
		      localSong.setCover(str7);
		      String str8 = localCursor.getString(7);
		      localSong.setFileUrl(str8);
		      String str9 = localCursor.getString(8);
		      localSong.setAlbumName(str9);
		      int m = localCursor.getInt(9);
		      localSong.setStatus(m);
		      int n = localCursor.getInt(10);
		      localSong.setDownloadStatus(n);
		      String str10 = localCursor.getString(11);
		      localSong.setPath(str10);
		      int i1 = localCursor.getInt(12);
		      localSong.setGrade(i1);
		      boolean bool2 = localArrayList.add(localSong);
		      boolean bool3 = localCursor.moveToNext();
	      }
	    }
	    return localArrayList;
	  }

	  public List<BillCategory> getServBillCategories()
	  {
	    ArrayList<BillCategory> localArrayList1 = new ArrayList<BillCategory>();
	    String[] arrayOfString1 = new String[2];
	    arrayOfString1[0] = "id";
	    arrayOfString1[1] = "name";
	    SQLiteDatabase localSQLiteDatabase = this.mDb;
	    String[] arrayOfString2 = null;
	    String str1 = null;
	    String str2 = null;
	    Cursor localCursor = localSQLiteDatabase.query("serv_bill_categories", arrayOfString1, null, arrayOfString2, str1, str2, "id asc");
	    if (localCursor.getCount() <= 0)
	    {
	      localCursor.close();
	    }
	    else
	    {
	    	boolean bool1 = localCursor.moveToFirst();
	      if (localCursor.isAfterLast())
	      {
	        localCursor.close();
	        
	      } else {
		      ArrayList localArrayList2 = new ArrayList();
		      int i = localCursor.getInt(0);
		      String str3 = localCursor.getString(1);
		      BillCategory localBillCategory = new BillCategory(i, str3, localArrayList2);
		      boolean bool2 = localArrayList1.add(localBillCategory);
		      boolean bool3 = localCursor.moveToNext();
	      }
	    }
	    return localArrayList1;
	  }

	  public List<BillCategory> getServBillCategoriesWithBills()
	  {
	    List<BillCategory> localList1 = getServBillCategories();
	    HashMap localHashMap = new HashMap();
	    String[] arrayOfString = new String[4];
	    arrayOfString[0] = "id";
	    arrayOfString[1] = "name";
	    arrayOfString[2] = "logo";
	    arrayOfString[3] = "category";
	    Cursor localCursor = this.mDb.query("serv_bills", arrayOfString, null, null, null, null, "category asc");
	    if (localCursor.getCount() <= 0)
	      localCursor.close();
	    else
	    {
	      boolean bool1 = localCursor.moveToFirst();
	      if (localCursor.isAfterLast()) {
		      localCursor.close();
		      Iterator localIterator = localList1.iterator();
		      while (localIterator.hasNext())
		      {
		        BillCategory localBillCategory = (BillCategory)localIterator.next();
		        Integer localInteger1 = Integer.valueOf(localBillCategory.getId());
		        if (!localHashMap.containsKey(localInteger1))
		          continue;
		        Integer localInteger2 = Integer.valueOf(localBillCategory.getId());
		        List localList2 = (List)localHashMap.get(localInteger2);
		        localBillCategory.setBills(localList2);
		      }
	      }
	    }
	    int i = localCursor.getInt(3);
	    Integer localInteger3 = Integer.valueOf(i);
	    Object localObject1 = new ArrayList();
	    if (!localHashMap.containsKey(localInteger3))
	    {
	      Integer localInteger4 = Integer.valueOf(i);
	      Object localObject2 = localHashMap.put(localInteger4, localObject1);
	    }
	    else
	    {
	      int j = localCursor.getInt(0);
	      String str1 = localCursor.getString(1);
	      String str2 = localCursor.getString(2);
	      Bill localBill = new Bill(j, str1, str2, i, "");
	      boolean bool2 = ((List)localObject1).add(localBill);
	      boolean bool3 = localCursor.moveToNext();
	      Integer localInteger5 = Integer.valueOf(i);
	      localObject1 = (List)localHashMap.get(localInteger5);
	    }
	    return localList1;
	  }

	  public void openDb()
	  {
	    SQLiteDatabase localSQLiteDatabase = this.mContext.openOrCreateDatabase("xiami.db", 0, null);
	    this.mDb = localSQLiteDatabase;
	  }

	  public String quote(String paramString)
	  {
	    if (paramString != null)
	      paramString = paramString.replace("'", "''");
	    return paramString;
	  }

	  public void refleshBillCategories(List<BillCategory> paramList)
	  {
	    cleanBillCategories();
	    saveBillCategories(paramList);
	  }

	  public void saveBillCategories(List<BillCategory> paramList)
	  {
	    Iterator localIterator = paramList.iterator();
	    while (true)
	    {
	      if (!localIterator.hasNext())
	        return;
	      BillCategory localBillCategory = (BillCategory)localIterator.next();
	      ContentValues localContentValues = new ContentValues();
	      Integer localInteger = Integer.valueOf(localBillCategory.getId());
	      localContentValues.put("id", localInteger);
	      String str = localBillCategory.getName();
	      localContentValues.put("name", str);
	      long l = this.mDb.insert("serv_bill_categories", "", localContentValues);
	      List localList = localBillCategory.getBills();
	      saveBills(localList);
	    }
	  }

	  public void saveBills(List<Bill> paramList)
	  {
	    Iterator localIterator = paramList.iterator();
	    while (true)
	    {
	      if (!localIterator.hasNext())
	        return;
	      Bill localBill = (Bill)localIterator.next();
	      ContentValues localContentValues = new ContentValues();
	      Integer localInteger1 = Integer.valueOf(localBill.getId());
	      localContentValues.put("id", localInteger1);
	      String str1 = localBill.getName();
	      localContentValues.put("name", str1);
	      String str2 = localBill.getLogo();
	      localContentValues.put("logo", str2);
	      Integer localInteger2 = Integer.valueOf(localBill.getCategory());
	      localContentValues.put("category", localInteger2);
	      long l = this.mDb.insert("serv_bills", "", localContentValues);
	    }
	  }

	  public boolean setCache(int paramInt1, String paramString, int paramInt2)
	  {
	    ContentValues localContentValues = new ContentValues();
	    Integer localInteger = Integer.valueOf(paramInt1);
	    localContentValues.put("id", localInteger);
	    localContentValues.put("content", paramString);
	    long l1 = System.currentTimeMillis() / 1000L;
	    long l2 = paramInt2;
	    Long localLong = Long.valueOf(l1 + l2);
	    localContentValues.put("timeout", localLong);
	    if (this.mDb.insert("str_cache", "", localContentValues) == 65535L) 
	    	return false;
	    return true;
	  }

	  public void setFmSetting(FmSetting paramFmSetting)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    this.mDb.execSQL("delete from fmsetting");
	    if (paramFmSetting != null)
	    {
	      ContentValues localContentValues = new ContentValues();
	      Integer localInteger1 = Integer.valueOf(paramFmSetting.isForceOffline());
	      localContentValues.put("offline", localInteger1);
	      Integer localInteger2 = Integer.valueOf(paramFmSetting.getWifi());
	      localContentValues.put("wifi", localInteger2);
	      Integer localInteger3 = Integer.valueOf(paramFmSetting.getNet3g());
	      localContentValues.put("net3g", localInteger3);
	      Integer localInteger4 = Integer.valueOf(paramFmSetting.getShowMessageWhenNoWifi());
	      localContentValues.put("showmsg_nowifi", localInteger4);
	      long l = this.mDb.insert("fmsetting", "", localContentValues);
	    }
	  }

	  public void setLoginMmeber(Member paramMember)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    this.mDb.execSQL("delete from members");
	    if (paramMember != null)
	    {
	      ContentValues localContentValues = new ContentValues();
	      Integer localInteger = Integer.valueOf(paramMember.getUser_id());
	      localContentValues.put("user_id", localInteger);
	      String str1 = paramMember.getNick_name();
	      localContentValues.put("nick_name", str1);
	      String str2 = paramMember.getPassword();
	      localContentValues.put("password", str2);
	      String str3 = paramMember.getEmail();
	      localContentValues.put("email", str3);
	      long l = this.mDb.insert("members", "", localContentValues);
	    }
	  }

	  public void updateOfflineSong(Song paramSong)
	  {
	    if (!this.mDb.isOpen())
	      openDb();
	    ContentValues localContentValues = new ContentValues();
	    Integer localInteger1 = Integer.valueOf(paramSong.getAlbumId());
	    localContentValues.put("album_id", localInteger1);
	    Integer localInteger2 = Integer.valueOf(paramSong.getArtistId());
	    localContentValues.put("artist_id", localInteger2);
	    String str1 = paramSong.getArtistName();
	    localContentValues.put("artist_name", str1);
	    String str2 = paramSong.getSingers();
	    localContentValues.put("singer", str2);
	    String str3 = paramSong.getName();
	    localContentValues.put("name", str3);
	    String str4 = paramSong.getCover();
	    localContentValues.put("cover", str4);
	    String str5 = paramSong.getFileUrl();
	    localContentValues.put("file_url", str5);
	    String str6 = paramSong.getAlbumName();
	    localContentValues.put("album_name", str6);
	    Integer localInteger3 = Integer.valueOf(paramSong.getStatus());
	    localContentValues.put("status", localInteger3);
	    Integer localInteger4 = Integer.valueOf(paramSong.getDownloadStatus());
	    localContentValues.put("download_status", localInteger4);
	    String str7 = paramSong.getPath();
	    localContentValues.put("path", str7);
	    Integer localInteger5 = Integer.valueOf(paramSong.getGrade());
	    localContentValues.put("grade", localInteger5);
	    String[] arrayOfString = new String[1];
	    String str8 = String.valueOf(paramSong.getSongId());
	    arrayOfString[0] = str8;
	    int i = this.mDb.update("download_songs", localContentValues, "song_id = ?", arrayOfString);
	  }
}
