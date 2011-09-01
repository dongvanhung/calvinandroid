package com.rent.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rent.data.CalledData;
import com.rent.data.DetailData;
import com.rent.data.FavouriteData;

public class DetailDBManager {

	private static final String[] ALLCOLUMNS;
	  private static final String[] CALLEDCOLUMNS;
	  private static final String DATABASE_NAME = "detail.db";
	  public static final String DETAIL_ABSTRACT = "abstract";
	  public static final String DETAIL_ADDRESS = "address";
	  public static final String DETAIL_AGENCY_STATUS = "agency_status";
	  public static final String DETAIL_AREA = "area";
	  public static final String DETAIL_CALLEDTIMES = "called_times";
	  public static final String DETAIL_CONTACT_PATH = "contact_path";
	  public static final String DETAIL_CONTACT_PERSON = "contact_person";
	  public static final String DETAIL_EXT_NUMBER = "ext";
	  public static final String DETAIL_FAVOURITEORCALLED = "favourite_called";
	  public static final String DETAIL_FROM_SITE = "from_site";
	  public static final String DETAIL_IMAGES = "images";
	  public static final String DETAIL_LAST_CALLED_TIME = "last_called_time";
	  public static final String DETAIL_LOCAL_ID = "_id";
	  public static final String DETAIL_MASTER_NUMBER = "master";
	  public static final String DETAIL_ORIGIN_ID = "origin_id";
	  public static final String DETAIL_PHONE = "phone";
	  public static final String DETAIL_PRICE = "price";
	  public static final String DETAIL_PUBLISH_TIME = "public_time";
	  public static final String DETAIL_RENT_TYPE = "rent_type";
	  public static final String DETAIL_ROOM_TYPE = "room_type";
	  public static final String DETAIL_THUMBNAIL = "thumbnail";
	  public static final String DETAIL_TITLE = "title";
	  public static final String DETAIL_TOWARD = "toward";
	  private static final String[] FAVOURITECOLUMNS;
	  private static final String IMAGESARRAYSPLITEFLAG = "<<###>>";
	  private static final String RENT_TABLE_NAME = "rent_detail";
	  private SQLiteDatabase mDb;
	  private DatabaseHelper mOpenHelper;
	  private Context paramContext;

	  static
	  {
	    String[] arrayOfString1 = new String[1];
	    arrayOfString1[0] = "origin_id , room_type , toward , title , area , rent_type , contact_person , phone , thumbnail , agency_status , address , from_site , price , public_time , abstract , images , called_times , last_called_time, favourite_called, contact_path, master,ext";
	    ALLCOLUMNS = arrayOfString1;
	    String[] arrayOfString2 = new String[1];
	    arrayOfString2[0] = "origin_id , room_type , title , area , thumbnail , address , price , rent_type , agency_status";
	    FAVOURITECOLUMNS = arrayOfString2;
	    String[] arrayOfString3 = new String[1];
	    arrayOfString3[0] = "origin_id , room_type , title , area , price , last_called_time , rent_type , agency_status , address";
	    CALLEDCOLUMNS = arrayOfString3;
	  }

	  public DetailDBManager(Context paramContext)
	  {
		  this.paramContext = paramContext;
	    DatabaseHelper localDatabaseHelper = new DatabaseHelper();
	    this.mOpenHelper = localDatabaseHelper;
	    establishDb();
	  }

	  private void alertDB()
	  {
	    try
	    {
	      this.mDb.execSQL("ALTER TABLE rent_detail ADD master VARCHAR");
	      this.mDb.execSQL("ALTER TABLE rent_detail ADD ext VARCHAR");
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	    }
	  }

	  private String dealImagesToStr(DetailData paramDetailData)
	  {
	    String[] localObject = paramDetailData.getmImages();
	    StringBuilder localStringBuilder1 = new StringBuilder();
	    if ((localObject != null) && (localObject.length > 0))
	    {
	      localStringBuilder1 = new StringBuilder();
	      int i = 0;
	      int j = localObject.length;
	      if (i < j)
	      {
	        int k = localObject.length - 1;
	        if (i != k)
	        {
	          String str1 = localObject[i];
	          StringBuilder localStringBuilder2 = localStringBuilder1.append(str1);
	          StringBuilder localStringBuilder3 = localStringBuilder1.append("<<###>>");
	          i += 1;
	        } else 
	        {
	          String str2 = localObject[i];
	          StringBuilder localStringBuilder4 = localStringBuilder1.append(str2);
	        }
	      }
	    }
	    return localStringBuilder1.toString();
	  }

	  private String[] dealStrToImageArray(String paramString)
	  {
	    return paramString.split("<<###>>");
	  }

	  private ContentValues encapsulateDetailInContentValue(DetailData paramDetailData)
	  {
	    ContentValues localContentValues = new ContentValues();
	    Long localLong1 = Long.valueOf(paramDetailData.getmId());
	    localContentValues.put("origin_id", localLong1);
	    String str1 = paramDetailData.getmRoom();
	    localContentValues.put("room_type", str1);
	    String str2 = paramDetailData.getmToward();
	    localContentValues.put("toward", str2);
	    String str3 = paramDetailData.getmTitle();
	    localContentValues.put("title", str3);
	    Integer localInteger1 = Integer.valueOf(paramDetailData.getmArea());
	    localContentValues.put("area", localInteger1);
	    Integer localInteger2 = Integer.valueOf(paramDetailData.getmRentType());
	    localContentValues.put("rent_type", localInteger2);
	    String str4 = paramDetailData.getmContactPerson();
	    localContentValues.put("contact_person", str4);
	    String str5 = paramDetailData.getmPhone();
	    localContentValues.put("phone", str5);
	    String str6 = paramDetailData.getmThumbnail();
	    localContentValues.put("thumbnail", str6);
	    String str7 = paramDetailData.getmAgencyStatus();
	    localContentValues.put("agency_status", str7);
	    String str8 = paramDetailData.getmAddress();
	    localContentValues.put("address", str8);
	    String str9 = paramDetailData.getmFromSite();
	    localContentValues.put("from_site", str9);
	    Integer localInteger3 = Integer.valueOf(paramDetailData.getmPrice());
	    localContentValues.put("price", localInteger3);
	    String str10 = paramDetailData.getmPublishTime();
	    localContentValues.put("public_time", str10);
	    String str11 = paramDetailData.getmAbstract();
	    localContentValues.put("abstract", str11);
	    String str12 = dealImagesToStr(paramDetailData);
	    localContentValues.put("images", str12);
	    Integer localInteger4 = Integer.valueOf(paramDetailData.getmCalledTimes());
	    localContentValues.put("called_times", localInteger4);
	    Long localLong2 = Long.valueOf(paramDetailData.getmLastCalledTime());
	    localContentValues.put("last_called_time", localLong2);
	    Integer localInteger5 = Integer.valueOf(paramDetailData.getmFavouriteOrCalled());
	    localContentValues.put("favourite_called", localInteger5);
	    String str13 = paramDetailData.getmContactPath();
	    localContentValues.put("contact_path", str13);
	    String str14 = paramDetailData.mMasterNumber;
	    localContentValues.put("master", str14);
	    String str15 = paramDetailData.mExtNumber;
	    localContentValues.put("ext", str15);
	    return localContentValues;
	  }

	  private void establishDb()
	  {
	    if (this.mDb == null)
	    {
	      SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getWritableDatabase();
	      this.mDb = localSQLiteDatabase;
	    }
	    alertDB();
	  }

	  private boolean insertDetail(DetailData paramDetailData)
	  {
	    try
	    {
	      ContentValues localContentValues = encapsulateDetailInContentValue(paramDetailData);
	      long l = this.mDb.insert("rent_detail", null, localContentValues);
	      return true;
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	    }
	    return false;
	  }

	  private DetailData setAllColumnCursorToDetailData(Cursor paramCursor)
	  {
	    DetailData localDetailData = new DetailData();
	    long l1 = paramCursor.getLong(0);
	    localDetailData.setmId(l1);
	    String str1 = paramCursor.getString(1);
	    localDetailData.setmRoom(str1);
	    String str2 = paramCursor.getString(2);
	    localDetailData.setmToward(str2);
	    String str3 = paramCursor.getString(3);
	    localDetailData.setmTitle(str3);
	    int i = paramCursor.getInt(4);
	    localDetailData.setmArea(i);
	    int j = paramCursor.getInt(5);
	    localDetailData.setmRentType(j);
	    String str4 = paramCursor.getString(6);
	    localDetailData.setmContactPerson(str4);
	    String str5 = paramCursor.getString(7);
	    localDetailData.setmPhone(str5);
	    String str6 = paramCursor.getString(8);
	    localDetailData.setmThumbnail(str6);
	    String str7 = paramCursor.getString(9);
	    localDetailData.setmAgencyStatus(str7);
	    String str8 = paramCursor.getString(10);
	    localDetailData.setmAddress(str8);
	    String str9 = paramCursor.getString(11);
	    localDetailData.setmFromSite(str9);
	    int k = paramCursor.getInt(12);
	    localDetailData.setmPrice(k);
	    String str10 = paramCursor.getString(13);
	    localDetailData.setmPublishTime(str10);
	    String str11 = paramCursor.getString(14);
	    localDetailData.setmAbstract(str11);
	    String str12 = paramCursor.getString(15);
	    String[] arrayOfString = dealStrToImageArray(str12);
	    localDetailData.setmImages(arrayOfString);
	    int m = paramCursor.getInt(16);
	    localDetailData.setmCalledTimes(m);
	    long l2 = paramCursor.getLong(17);
	    localDetailData.setmLastCalledTime(l2);
	    int n = paramCursor.getInt(18);
	    localDetailData.setmFavouriteOrCalled(n);
	    String str13 = paramCursor.getString(19);
	    localDetailData.setmContactPath(str13);
	    int i1 = paramCursor.getColumnIndex("master");
	    String str14 = paramCursor.getString(i1);
	    localDetailData.mMasterNumber = str14;
	    int i2 = paramCursor.getColumnIndex("ext");
	    String str15 = paramCursor.getString(i2);
	    localDetailData.mExtNumber = str15;
	    return localDetailData;
	  }

	  private CalledData setCalledCursorToDetailData(Cursor paramCursor)
	  {
	    CalledData localCalledData = new CalledData();
	    long l1 = paramCursor.getLong(0);
	    localCalledData.setmId(l1);
	    String str1 = paramCursor.getString(1);
	    localCalledData.setmRoom(str1);
	    String str2 = paramCursor.getString(2);
	    localCalledData.setmTitle(str2);
	    int i = paramCursor.getInt(3);
	    localCalledData.setmArea(i);
	    int j = paramCursor.getInt(4);
	    localCalledData.setmPrice(j);
	    long l2 = paramCursor.getLong(5);
	    localCalledData.setmLastCalledTime(l2);
	    int k = paramCursor.getColumnIndex("agency_status");
	    String str3 = paramCursor.getString(k);
	    localCalledData.setmAgencyStatus(str3);
	    int m = paramCursor.getColumnIndex("rent_type");
	    int n = paramCursor.getInt(m);
	    localCalledData.setmRentType(n);
	    int i1 = paramCursor.getColumnIndex("address");
	    String str4 = paramCursor.getString(i1);
	    localCalledData.setmAddress(str4);
	    return localCalledData;
	  }

	  private FavouriteData setFavoutiteCursorToDetailData(Cursor paramCursor)
	  {
	    FavouriteData localFavouriteData = new FavouriteData();
	    long l = paramCursor.getLong(0);
	    localFavouriteData.setmId(l);
	    String str1 = paramCursor.getString(1);
	    localFavouriteData.setmRoom(str1);
	    String str2 = paramCursor.getString(2);
	    localFavouriteData.setmTitle(str2);
	    int i = paramCursor.getInt(3);
	    localFavouriteData.setmArea(i);
	    String str3 = paramCursor.getString(4);
	    localFavouriteData.setmThumbnail(str3);
	    String str4 = paramCursor.getString(5);
	    localFavouriteData.setmAddress(str4);
	    int j = paramCursor.getInt(6);
	    localFavouriteData.setmPrice(j);
	    int k = paramCursor.getColumnIndex("agency_status");
	    String str5 = paramCursor.getString(k);
	    localFavouriteData.setmAgencyStatus(str5);
	    int m = paramCursor.getColumnIndex("rent_type");
	    int n = paramCursor.getInt(m);
	    localFavouriteData.setmRentType(n);
	    return localFavouriteData;
	  }

	  private boolean updataDetailByOriginId(String paramString, long paramLong)
	  {
	    int i;
	    if (getDetailDataByOriginId(paramLong) == null)
	      return false;
	    else {
	      try
	      {
	        String str = "UPDATE rent_detail SET " + paramString + " WHERE " + "origin_id" + " = " + paramLong;
	        this.mDb.execSQL(str);
	        return true;
	      }
	      catch (Exception localException)
	      {
	    	  localException.printStackTrace();
	      }
	    }
	    return false;
	  }

	  public int addCalledStatusByOriginId(long paramLong)
	  {
	    DetailData localDetailData = getDetailDataByOriginId(paramLong);
	    int i;
	    if (localDetailData == null)
	      return 5;
	    else {
	      i = localDetailData.getmFavouriteOrCalled();
	      if ((4 == i) || (1 == i))
	      {
	        boolean bool1 = updataDetailDataFavouriteStatus(paramLong, 4);
	        StringBuilder localStringBuilder1 = new StringBuilder().append("last_called_time = ");
	        long l1 = System.currentTimeMillis();
	        String str1 = String.valueOf(l1);
	        boolean bool2 = updataDetailByOriginId(str1, paramLong);
	        i = 4;
	      } else {
		      StringBuilder localStringBuilder2 = new StringBuilder().append("last_called_time = ");
		      long l2 = System.currentTimeMillis();
		      String str2 = String.valueOf(l2);
		      boolean bool3 = updataDetailByOriginId(str2, paramLong);
		      boolean bool4 = updataDetailDataFavouriteStatus(paramLong, 2);
		      i = 2;
	      }
	    }
	    return i;
	  }

	  public int cancelCalledStatusByOriginId(long paramLong)
	  {
	    int i = getDetailDataByOriginId(paramLong).getmFavouriteOrCalled();
	    if (4 == i)
	    {
	      boolean bool1 = updataDetailDataFavouriteStatus(paramLong, 1);
	      i = 1;
	    } else {
	      if (2 == i)
	      {
	        boolean bool2 = updataDetailDataFavouriteStatus(paramLong, 3);
	        i = 3;
	      } else {
	    	  i = 0;
	      }
	    }
	    return i;
	  }

	  public int changeFavouriteStatusByOriginId(long paramLong)
	  {
	    int i = getDetailDataByOriginId(paramLong).getmFavouriteOrCalled();
	    if (1 == i)
	    {
	      boolean bool1 = updataDetailDataFavouriteStatus(paramLong, 0);
	      i = 0;
	    } else
	    {
	      if (2 == i)
	      {
	        boolean bool2 = updataDetailDataFavouriteStatus(paramLong, 4);
	        i = 4;
	      } else if (4 == i)
	      {
	        boolean bool3 = updataDetailDataFavouriteStatus(paramLong, 2);
	        i = 2;
	      } else {
	      boolean bool4 = updataDetailDataFavouriteStatus(paramLong, 1);
	      i = 1;
	      }
	    }
	    return i;
	  }

	  public void closeDb()
	  {
	    if (this.mDb != null)
	    {
	      this.mDb.close();
	      this.mDb = null;
	    }
	  }

	  // ERROR //
	  public java.util.List<Long> getCacheList()
	  {
		  return null;
	    // Byte code:
	    //   0: new 423	java/util/ArrayList
	    //   3: dup
	    //   4: invokespecial 424	java/util/ArrayList:<init>	()V
	    //   7: astore_1
	    //   8: aload_0
	    //   9: getfield 126	com/songshulin/android/rent/db/DetailDBManager:mDb	Landroid/database/sqlite/SQLiteDatabase;
	    //   12: ldc_w 426
	    //   15: aconst_null
	    //   16: invokevirtual 430	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
	    //   19: astore_2
	    //   20: aload_2
	    //   21: astore_3
	    //   22: aload_3
	    //   23: ifnull +100 -> 123
	    //   26: aload_3
	    //   27: invokeinterface 433 1 0
	    //   32: ifle +91 -> 123
	    //   35: aload_3
	    //   36: invokeinterface 437 1 0
	    //   41: istore 4
	    //   43: aload_3
	    //   44: invokeinterface 440 1 0
	    //   49: ifne +74 -> 123
	    //   52: aload_3
	    //   53: ldc 59
	    //   55: invokeinterface 355 2 0
	    //   60: istore 5
	    //   62: aload_3
	    //   63: iload 5
	    //   65: invokeinterface 278 2 0
	    //   70: lstore 6
	    //   72: aload_3
	    //   73: invokeinterface 443 1 0
	    //   78: istore 8
	    //   80: lload 6
	    //   82: invokestatic 177	java/lang/Long:valueOf	(J)Ljava/lang/Long;
	    //   85: astore 9
	    //   87: aload_1
	    //   88: aload 9
	    //   90: invokeinterface 449 2 0
	    //   95: istore 10
	    //   97: goto -54 -> 43
	    //   100: astore 11
	    //   102: aload_3
	    //   103: ifnull +18 -> 121
	    //   106: aload_3
	    //   107: invokeinterface 452 1 0
	    //   112: ifne +9 -> 121
	    //   115: aload_3
	    //   116: invokeinterface 453 1 0
	    //   121: aload_1
	    //   122: areturn
	    //   123: aload_3
	    //   124: ifnull -3 -> 121
	    //   127: aload_3
	    //   128: invokeinterface 452 1 0
	    //   133: ifne -12 -> 121
	    //   136: aload_3
	    //   137: invokeinterface 453 1 0
	    //   142: goto -21 -> 121
	    //   145: astore_1
	    //   146: aconst_null
	    //   147: astore_3
	    //   148: aload_3
	    //   149: ifnull +18 -> 167
	    //   152: aload_3
	    //   153: invokeinterface 452 1 0
	    //   158: ifne +9 -> 167
	    //   161: aload_3
	    //   162: invokeinterface 453 1 0
	    //   167: aload_1
	    //   168: athrow
	    //   169: astore_1
	    //   170: goto -22 -> 148
	    //   173: astore 12
	    //   175: aconst_null
	    //   176: astore_3
	    //   177: goto -75 -> 102
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   26	97	100	java/lang/Exception
	    //   8	20	145	finally
	    //   26	97	169	finally
	    //   8	20	173	java/lang/Exception
	  }

	  // ERROR //
	  public java.util.List<CalledData> getCalledListData()
	  {
		  return null;
	    // Byte code:
	    //   0: new 423	java/util/ArrayList
	    //   3: dup
	    //   4: invokespecial 424	java/util/ArrayList:<init>	()V
	    //   7: astore_1
	    //   8: new 146	java/lang/StringBuilder
	    //   11: dup
	    //   12: invokespecial 147	java/lang/StringBuilder:<init>	()V
	    //   15: ldc_w 456
	    //   18: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   21: astore_2
	    //   22: getstatic 110	com/songshulin/android/rent/db/DetailDBManager:CALLEDCOLUMNS	[Ljava/lang/String;
	    //   25: iconst_0
	    //   26: aaload
	    //   27: astore_3
	    //   28: aload_2
	    //   29: aload_3
	    //   30: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   33: ldc_w 458
	    //   36: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   39: ldc 90
	    //   41: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   44: ldc_w 393
	    //   47: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   50: ldc 41
	    //   52: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   55: ldc_w 395
	    //   58: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   61: iconst_2
	    //   62: invokevirtual 461	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
	    //   65: ldc_w 463
	    //   68: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   71: ldc 41
	    //   73: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   76: ldc_w 395
	    //   79: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   82: iconst_4
	    //   83: invokevirtual 461	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
	    //   86: ldc_w 465
	    //   89: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   92: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   95: astore 4
	    //   97: aload_0
	    //   98: getfield 126	com/songshulin/android/rent/db/DetailDBManager:mDb	Landroid/database/sqlite/SQLiteDatabase;
	    //   101: aload 4
	    //   103: aconst_null
	    //   104: invokevirtual 430	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
	    //   107: astore 5
	    //   109: aload 5
	    //   111: astore 4
	    //   113: aload 4
	    //   115: ifnull +127 -> 242
	    //   118: aload 4
	    //   120: invokeinterface 433 1 0
	    //   125: iflt +117 -> 242
	    //   128: aload 4
	    //   130: invokeinterface 433 1 0
	    //   135: istore 5
	    //   137: iload 5
	    //   139: ifne +27 -> 166
	    //   142: aload 4
	    //   144: ifnull +20 -> 164
	    //   147: aload 4
	    //   149: invokeinterface 452 1 0
	    //   154: ifne +10 -> 164
	    //   157: aload 4
	    //   159: invokeinterface 453 1 0
	    //   164: aload_1
	    //   165: areturn
	    //   166: aload 4
	    //   168: invokeinterface 437 1 0
	    //   173: istore 6
	    //   175: aload 4
	    //   177: invokeinterface 440 1 0
	    //   182: ifne +60 -> 242
	    //   185: aload_0
	    //   186: aload 4
	    //   188: invokespecial 467	com/songshulin/android/rent/db/DetailDBManager:setCalledCursorToDetailData	(Landroid/database/Cursor;)Lcom/songshulin/android/rent/data/CalledData;
	    //   191: astore 7
	    //   193: aload_1
	    //   194: aload 7
	    //   196: invokeinterface 449 2 0
	    //   201: istore 8
	    //   203: aload 4
	    //   205: invokeinterface 443 1 0
	    //   210: istore 9
	    //   212: goto -37 -> 175
	    //   215: astore 10
	    //   217: aload 4
	    //   219: ifnull -55 -> 164
	    //   222: aload 4
	    //   224: invokeinterface 452 1 0
	    //   229: ifne -65 -> 164
	    //   232: aload 4
	    //   234: invokeinterface 453 1 0
	    //   239: goto -75 -> 164
	    //   242: aload 4
	    //   244: ifnull -80 -> 164
	    //   247: aload 4
	    //   249: invokeinterface 452 1 0
	    //   254: ifne -90 -> 164
	    //   257: aload 4
	    //   259: invokeinterface 453 1 0
	    //   264: goto -100 -> 164
	    //   267: astore_1
	    //   268: aconst_null
	    //   269: astore 4
	    //   271: aload 4
	    //   273: ifnull +20 -> 293
	    //   276: aload 4
	    //   278: invokeinterface 452 1 0
	    //   283: ifne +10 -> 293
	    //   286: aload 4
	    //   288: invokeinterface 453 1 0
	    //   293: aload_1
	    //   294: athrow
	    //   295: astore_1
	    //   296: goto -25 -> 271
	    //   299: astore 11
	    //   301: aconst_null
	    //   302: astore 4
	    //   304: goto -87 -> 217
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   118	137	215	java/lang/Exception
	    //   166	212	215	java/lang/Exception
	    //   97	109	267	finally
	    //   118	137	295	finally
	    //   166	212	295	finally
	    //   97	109	299	java/lang/Exception
	  }

	  // ERROR //
	  public DetailData getDetailDataByOriginId(long paramLong)
	  {
		  return null;
	    // Byte code:
	    //   0: new 146	java/lang/StringBuilder
	    //   3: dup
	    //   4: invokespecial 147	java/lang/StringBuilder:<init>	()V
	    //   7: ldc_w 469
	    //   10: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   13: lload_1
	    //   14: invokevirtual 398	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
	    //   17: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   20: astore_3
	    //   21: aload_0
	    //   22: getfield 126	com/songshulin/android/rent/db/DetailDBManager:mDb	Landroid/database/sqlite/SQLiteDatabase;
	    //   25: astore 4
	    //   27: getstatic 102	com/songshulin/android/rent/db/DetailDBManager:ALLCOLUMNS	[Ljava/lang/String;
	    //   30: astore 5
	    //   32: aload 4
	    //   34: ldc 90
	    //   36: aload 5
	    //   38: aload_3
	    //   39: aconst_null
	    //   40: aconst_null
	    //   41: aconst_null
	    //   42: aconst_null
	    //   43: invokevirtual 473	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
	    //   46: astore 6
	    //   48: aload 6
	    //   50: astore 7
	    //   52: aload 7
	    //   54: ifnull +63 -> 117
	    //   57: aload 7
	    //   59: invokeinterface 433 1 0
	    //   64: ifle +53 -> 117
	    //   67: aload 7
	    //   69: invokeinterface 437 1 0
	    //   74: istore 8
	    //   76: aload_0
	    //   77: aload 7
	    //   79: invokespecial 475	com/songshulin/android/rent/db/DetailDBManager:setAllColumnCursorToDetailData	(Landroid/database/Cursor;)Lcom/songshulin/android/rent/data/DetailData;
	    //   82: astore 6
	    //   84: aload 6
	    //   86: astore 9
	    //   88: aload 7
	    //   90: ifnull +20 -> 110
	    //   93: aload 7
	    //   95: invokeinterface 452 1 0
	    //   100: ifne +10 -> 110
	    //   103: aload 7
	    //   105: invokeinterface 453 1 0
	    //   110: aload 9
	    //   112: astore 7
	    //   114: aload 7
	    //   116: areturn
	    //   117: aload 7
	    //   119: ifnull +20 -> 139
	    //   122: aload 7
	    //   124: invokeinterface 452 1 0
	    //   129: ifne +10 -> 139
	    //   132: aload 7
	    //   134: invokeinterface 453 1 0
	    //   139: aconst_null
	    //   140: astore 7
	    //   142: goto -28 -> 114
	    //   145: astore 10
	    //   147: aconst_null
	    //   148: astore 7
	    //   150: aload 7
	    //   152: ifnull -13 -> 139
	    //   155: aload 7
	    //   157: invokeinterface 452 1 0
	    //   162: ifne -23 -> 139
	    //   165: aload 7
	    //   167: invokeinterface 453 1 0
	    //   172: goto -33 -> 139
	    //   175: astore 7
	    //   177: aconst_null
	    //   178: astore 9
	    //   180: aload 9
	    //   182: ifnull +20 -> 202
	    //   185: aload 9
	    //   187: invokeinterface 452 1 0
	    //   192: ifne +10 -> 202
	    //   195: aload 9
	    //   197: invokeinterface 453 1 0
	    //   202: aload 7
	    //   204: athrow
	    //   205: astore 11
	    //   207: aload 7
	    //   209: astore 9
	    //   211: aload 11
	    //   213: astore 7
	    //   215: goto -35 -> 180
	    //   218: astore 12
	    //   220: goto -70 -> 150
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   0	48	145	java/lang/Exception
	    //   0	48	175	finally
	    //   57	84	205	finally
	    //   57	84	218	java/lang/Exception
	  }

	  // ERROR //
	  public java.util.List<FavouriteData> getFavouriteDetailData()
	  {
		  return null;
	    // Byte code:
	    //   0: new 423	java/util/ArrayList
	    //   3: dup
	    //   4: invokespecial 424	java/util/ArrayList:<init>	()V
	    //   7: astore_1
	    //   8: new 146	java/lang/StringBuilder
	    //   11: dup
	    //   12: invokespecial 147	java/lang/StringBuilder:<init>	()V
	    //   15: ldc_w 456
	    //   18: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   21: astore_2
	    //   22: getstatic 106	com/songshulin/android/rent/db/DetailDBManager:FAVOURITECOLUMNS	[Ljava/lang/String;
	    //   25: iconst_0
	    //   26: aaload
	    //   27: astore_3
	    //   28: aload_2
	    //   29: aload_3
	    //   30: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   33: ldc_w 458
	    //   36: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   39: ldc 90
	    //   41: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   44: ldc_w 393
	    //   47: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   50: ldc 41
	    //   52: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   55: ldc_w 395
	    //   58: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   61: iconst_1
	    //   62: invokevirtual 461	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
	    //   65: ldc_w 463
	    //   68: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   71: ldc 41
	    //   73: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   76: ldc_w 395
	    //   79: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   82: iconst_4
	    //   83: invokevirtual 461	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
	    //   86: ldc_w 478
	    //   89: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   92: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   95: astore 4
	    //   97: aload_0
	    //   98: getfield 126	com/songshulin/android/rent/db/DetailDBManager:mDb	Landroid/database/sqlite/SQLiteDatabase;
	    //   101: aload 4
	    //   103: aconst_null
	    //   104: invokevirtual 430	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
	    //   107: astore 5
	    //   109: aload 5
	    //   111: astore 4
	    //   113: aload 4
	    //   115: ifnull +127 -> 242
	    //   118: aload 4
	    //   120: invokeinterface 433 1 0
	    //   125: iflt +117 -> 242
	    //   128: aload 4
	    //   130: invokeinterface 433 1 0
	    //   135: istore 5
	    //   137: iload 5
	    //   139: ifne +27 -> 166
	    //   142: aload 4
	    //   144: ifnull +20 -> 164
	    //   147: aload 4
	    //   149: invokeinterface 452 1 0
	    //   154: ifne +10 -> 164
	    //   157: aload 4
	    //   159: invokeinterface 453 1 0
	    //   164: aload_1
	    //   165: areturn
	    //   166: aload 4
	    //   168: invokeinterface 437 1 0
	    //   173: istore 6
	    //   175: aload 4
	    //   177: invokeinterface 440 1 0
	    //   182: ifne +60 -> 242
	    //   185: aload_0
	    //   186: aload 4
	    //   188: invokespecial 480	com/songshulin/android/rent/db/DetailDBManager:setFavoutiteCursorToDetailData	(Landroid/database/Cursor;)Lcom/songshulin/android/rent/data/FavouriteData;
	    //   191: astore 7
	    //   193: aload_1
	    //   194: aload 7
	    //   196: invokeinterface 449 2 0
	    //   201: istore 8
	    //   203: aload 4
	    //   205: invokeinterface 443 1 0
	    //   210: istore 9
	    //   212: goto -37 -> 175
	    //   215: astore 10
	    //   217: aload 4
	    //   219: ifnull -55 -> 164
	    //   222: aload 4
	    //   224: invokeinterface 452 1 0
	    //   229: ifne -65 -> 164
	    //   232: aload 4
	    //   234: invokeinterface 453 1 0
	    //   239: goto -75 -> 164
	    //   242: aload 4
	    //   244: ifnull -80 -> 164
	    //   247: aload 4
	    //   249: invokeinterface 452 1 0
	    //   254: ifne -90 -> 164
	    //   257: aload 4
	    //   259: invokeinterface 453 1 0
	    //   264: goto -100 -> 164
	    //   267: astore_1
	    //   268: aconst_null
	    //   269: astore 4
	    //   271: aload 4
	    //   273: ifnull +20 -> 293
	    //   276: aload 4
	    //   278: invokeinterface 452 1 0
	    //   283: ifne +10 -> 293
	    //   286: aload 4
	    //   288: invokeinterface 453 1 0
	    //   293: aload_1
	    //   294: athrow
	    //   295: astore_1
	    //   296: goto -25 -> 271
	    //   299: astore 11
	    //   301: aconst_null
	    //   302: astore 4
	    //   304: goto -87 -> 217
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   118	137	215	java/lang/Exception
	    //   166	212	215	java/lang/Exception
	    //   97	109	267	finally
	    //   118	137	295	finally
	    //   166	212	295	finally
	    //   97	109	299	java/lang/Exception
	  }

	  public boolean insertDetailData(DetailData paramDetailData)
	  {
	    long l = paramDetailData.getmId();
	    try {
	    if (getDetailDataByOriginId(l) != null){
	      boolean bool1 = updataDetailData(paramDetailData);
	    } else {
	        boolean bool2 = insertDetail(paramDetailData);
	      }
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	    	return false;
	    }
	    return true;
	  }

	  // ERROR //
	  public int queryFavouriteStatusByOriginId(long paramLong)
	  {
		  return 0;
	    // Byte code:
	    //   0: new 146	java/lang/StringBuilder
	    //   3: dup
	    //   4: invokespecial 147	java/lang/StringBuilder:<init>	()V
	    //   7: ldc_w 489
	    //   10: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   13: lload_1
	    //   14: invokevirtual 398	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
	    //   17: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   20: astore_3
	    //   21: bipush 255
	    //   23: istore 4
	    //   25: aload_0
	    //   26: getfield 126	com/songshulin/android/rent/db/DetailDBManager:mDb	Landroid/database/sqlite/SQLiteDatabase;
	    //   29: aload_3
	    //   30: aconst_null
	    //   31: invokevirtual 430	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
	    //   34: astore 5
	    //   36: aload 5
	    //   38: astore_3
	    //   39: aload_3
	    //   40: ifnull +33 -> 73
	    //   43: aload_3
	    //   44: invokeinterface 433 1 0
	    //   49: ifle +24 -> 73
	    //   52: aload_3
	    //   53: invokeinterface 437 1 0
	    //   58: istore 6
	    //   60: aload_3
	    //   61: iconst_0
	    //   62: invokeinterface 299 2 0
	    //   67: istore 5
	    //   69: iload 5
	    //   71: istore 4
	    //   73: aload_3
	    //   74: ifnull +96 -> 170
	    //   77: aload_3
	    //   78: invokeinterface 452 1 0
	    //   83: ifne +87 -> 170
	    //   86: aload_3
	    //   87: invokeinterface 453 1 0
	    //   92: iload 4
	    //   94: istore_3
	    //   95: iload_3
	    //   96: ireturn
	    //   97: astore 7
	    //   99: aconst_null
	    //   100: astore_3
	    //   101: aload_3
	    //   102: ifnull +68 -> 170
	    //   105: aload_3
	    //   106: invokeinterface 452 1 0
	    //   111: ifne +59 -> 170
	    //   114: aload_3
	    //   115: invokeinterface 453 1 0
	    //   120: bipush 255
	    //   122: istore_3
	    //   123: goto -28 -> 95
	    //   126: astore_3
	    //   127: aconst_null
	    //   128: astore 4
	    //   130: aload 4
	    //   132: ifnull +20 -> 152
	    //   135: aload 4
	    //   137: invokeinterface 452 1 0
	    //   142: ifne +10 -> 152
	    //   145: aload 4
	    //   147: invokeinterface 453 1 0
	    //   152: aload_3
	    //   153: athrow
	    //   154: astore 8
	    //   156: aload_3
	    //   157: astore 4
	    //   159: aload 8
	    //   161: astore_3
	    //   162: goto -32 -> 130
	    //   165: astore 9
	    //   167: goto -66 -> 101
	    //   170: iload 4
	    //   172: istore_3
	    //   173: goto -78 -> 95
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   25	36	97	java/lang/Exception
	    //   25	36	126	finally
	    //   43	69	154	finally
	    //   43	69	165	java/lang/Exception
	  }

	  public boolean updataDetailData(DetailData paramDetailData)
	  {
		long l = paramDetailData.getmId();
		int i = 0;
		if (l == 0L)
			return false;
		else {
			if (getDetailDataByOriginId(i) == null) {
				i = 0;
			} else {
				try {
					ContentValues localContentValues = encapsulateDetailInContentValue(paramDetailData);
					String str = "origin_id = " + i;
					int j = this.mDb.update("rent_detail", localContentValues,
							str, null);
				} catch (Exception localException) {
					return false;
				}
			}
		}
		return true;
	  }

	  public boolean updataDetailDataFavouriteStatus(long paramLong, int paramInt)
	  {
	    int i;
	    if (getDetailDataByOriginId(paramLong) == null)
	      return false;
	    else
	    {
	      try
	      {
	        String str = "UPDATE rent_detail SET favourite_called = " + paramInt + " WHERE " + "origin_id" + " = " + paramLong;
	        this.mDb.execSQL(str);
	      }
	      catch (Exception localException)
	      {
	        localException.printStackTrace();
	        return false;
	      }
	    }
	    return true;
	  }

	  class DatabaseHelper extends SQLiteOpenHelper
	  {
	    public static final String createTableSql = " CREATE TABLE rent_detail ( _id INTEGER PRIMARY KEY AUTOINCREMENT, origin_id INTEGER , room_type VARCHAR , toward VARCHAR , title VARCHAR , area INTEGER , rent_type INTEGER , contact_person VARCHAR , phone VARCHAR , thumbnail VARCHAR , agency_status VARCHAR , address VARCHAR , from_site VARCHAR , price INTEGER , public_time VARCHAR , abstract VARCHAR , images VARCHAR , called_times INTEGER , last_called_time INTEGER , favourite_called INTEGER ,contact_path VARCHAR ,master VARCHAR, ext VARCHAR);";

	    public DatabaseHelper()
	    {
	      super(paramContext, "detail.db", null, 1);
	    }

	    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
	    {
	      try
	      {
	        paramSQLiteDatabase.execSQL(" CREATE TABLE rent_detail ( _id INTEGER PRIMARY KEY AUTOINCREMENT, origin_id INTEGER , room_type VARCHAR , toward VARCHAR , title VARCHAR , area INTEGER , rent_type INTEGER , contact_person VARCHAR , phone VARCHAR , thumbnail VARCHAR , agency_status VARCHAR , address VARCHAR , from_site VARCHAR , price INTEGER , public_time VARCHAR , abstract VARCHAR , images VARCHAR , called_times INTEGER , last_called_time INTEGER , favourite_called INTEGER ,contact_path VARCHAR ,master VARCHAR, ext VARCHAR);");
	      }
	      catch (Exception localException)
	      {
	    	  localException.printStackTrace();
	      }
	    }

	    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
	    {
	      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS rent_detail");
	      onCreate(paramSQLiteDatabase);
	    }
	  }
	  
}
