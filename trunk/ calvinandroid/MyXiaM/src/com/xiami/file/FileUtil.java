package com.xiami.file;


public class FileUtil {

	/*public static String TAG = "xiami";
	  private Context mContext;

	  public FileUtil(Context paramContext)
	  {
	    this.mContext = paramContext;
	    int i = Log.d(TAG, "New FileUtil");
	  }

	  private static int computeInitialSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
	  {
	    double d1 = paramOptions.outWidth;
	    double d2 = paramOptions.outHeight;
	    int i;
	    int j;
	    label33: int k;
	    if (paramInt2 == -1)
	    {
	      i = 1;
	      if (paramInt1 != -1)
	        break label74;
	      j = 128;
	      if (j >= i)
	        break label114;
	      k = i;
	    }
	    while (true)
	    {
	      return k;
	      float f = d1 * d2;
	      double d3 = paramInt2;
	      i = (int)Math.ceil(Math.sqrt(f / d3));
	      break;
	      label74: double d4 = paramInt1;
	      double d5 = Math.floor(d1 / d4);
	      double d6 = paramInt1;
	      double d7 = Math.floor(d2 / d6);
	      j = (int)Math.min(d5, d7);
	      break label33;
	      label114: if ((paramInt2 == -1) && (paramInt1 == -1))
	      {
	        k = 1;
	        continue;
	      }
	      if (paramInt1 == -1)
	      {
	        k = i;
	        continue;
	      }
	      k = j;
	    }
	  }

	  public static int computeSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
	  {
	    int i = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
	    int j;
	    if (i <= 8)
	    {
	      j = 1;
	      if (j < i);
	    }
	    while (true)
	    {
	      return j;
	      j <<= 1;
	      break;
	      j = (i + 7) / 8 * 8;
	    }
	  }

	  public static InputStream getContent(String paramString)
	  {
	    Object localObject1 = null;
	    Object localObject2;
	    if ((paramString.length() > 4) && (paramString.substring(0, 4).equals("http")))
	      localObject2 = null;
	    while (true)
	    {
	      try
	      {
	        URL localURL = new URL(paramString);
	        localObject2 = localURL;
	      }
	      catch (MalformedURLException localMalformedURLException)
	      {
	        try
	        {
	          HttpURLConnection localHttpURLConnection = (HttpURLConnection)localObject2.openConnection();
	          localHttpURLConnection.setDoInput(1);
	          localHttpURLConnection.setReadTimeout(20000);
	          localHttpURLConnection.connect();
	          InputStream localInputStream = localHttpURLConnection.getInputStream();
	          localObject1 = localInputStream;
	          return localObject1;
	          localMalformedURLException.printStackTrace();
	          continue;
	        }
	        catch (IOException localIOException)
	        {
	          localIOException.printStackTrace();
	          continue;
	        }
	      }
	      try
	      {
	        FileInputStream localFileInputStream = new FileInputStream(paramString);
	        localObject1 = localFileInputStream;
	      }
	      catch (FileNotFoundException localFileNotFoundException)
	      {
	        localFileNotFoundException.printStackTrace();
	      }
	    }
	  }

	  public static String getImgFolder()
	  {
	    String str1 = Environment.getExternalStorageDirectory().getPath();
	    String str2 = File.separator;
	    String str3 = str1.concat(str2).concat("xiami");
	    String str4 = File.separator;
	    return str3.concat(str4).concat("img");
	  }

	  public static String getImgPath(String paramString)
	  {
	    String str1 = getImgFolder();
	    boolean bool = new File(str1).mkdirs();
	    String str2 = File.separator;
	    return str1.concat(str2).concat(paramString);
	  }

	  public static String getLrcFolder()
	  {
	    String str1 = Environment.getExternalStorageDirectory().getPath();
	    String str2 = File.separator;
	    String str3 = str1.concat(str2).concat("xiami");
	    String str4 = File.separator;
	    return str3.concat(str4).concat("lrc");
	  }

	  public static String getLrcPath(String paramString)
	  {
	    String str1 = getLrcFolder();
	    boolean bool = new File(str1).mkdirs();
	    String str2 = File.separator;
	    return str1.concat(str2).concat(paramString);
	  }

	  public static String getMp3Folder()
	  {
	    String str1 = Environment.getExternalStorageDirectory().getPath();
	    String str2 = File.separator;
	    String str3 = str1.concat(str2).concat("xiami");
	    String str4 = File.separator;
	    return str3.concat(str4).concat("file");
	  }

	  public static String getMp3Path(String paramString)
	  {
	    String str1 = getMp3Folder();
	    boolean bool = new File(str1).mkdirs();
	    String str2 = File.separator;
	    return str1.concat(str2).concat(paramString);
	  }

	  public static String getOfflineFolder()
	  {
	    String str1 = Environment.getExternalStorageDirectory().getPath();
	    String str2 = File.separator;
	    return str1.concat(str2).concat("xiami");
	  }

	  public byte[] InputStreamToByte(InputStream paramInputStream)
	    throws IOException
	  {
	    Object localObject;
	    if (paramInputStream == null)
	    {
	      localObject = null;
	      return localObject;
	    }
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
	    while (true)
	    {
	      int i = paramInputStream.read();
	      if (i == -1)
	      {
	        byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
	        localByteArrayOutputStream.close();
	        localObject = arrayOfByte;
	        break;
	      }
	      localByteArrayOutputStream.write(i);
	    }
	  }

	  public boolean checkImg(String paramString)
	  {
	    String str = getImgPath(paramString);
	    if (paramString != null);
	    for (boolean bool = new File(str).exists(); ; bool = false)
	      return bool;
	  }

	  public boolean copyFile(String paramString1, String paramString2)
	  {
	    try
	    {
	      byte[] arrayOfByte = readFile(paramString1);
	      writeDataToFile(arrayOfByte, paramString2);
	      i = 1;
	      return i;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        String str1 = TAG;
	        String str2 = localException.toString();
	        int j = Log.e(str1, str2);
	        int i = 0;
	      }
	    }
	  }

	  public boolean createDir(String paramString)
	  {
	    int i = 0;
	    try
	    {
	      File localFile = new File(paramString);
	      if (!localFile.isDirectory())
	      {
	        boolean bool = localFile.mkdirs();
	        i = bool;
	      }
	      return i;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        String str1 = TAG;
	        StringBuilder localStringBuilder = new StringBuilder("createDir__");
	        String str2 = localException.toString();
	        String str3 = str2;
	        int j = Log.e(str1, str3);
	      }
	    }
	  }

	  public void deleteAllOfflineData()
	  {
	    String str = getOfflineFolder();
	    File localFile = new File(str);
	    File[] arrayOfFile1;
	    int m;
	    if (localFile.exists())
	    {
	      FileUtil.1 local1 = new FileUtil.1(this);
	      arrayOfFile1 = localFile.listFiles(local1);
	      StringBuilder localStringBuilder = new StringBuilder("delete folders:");
	      int i = arrayOfFile1.length;
	      int j = Log.d(i);
	      int k = arrayOfFile1.length;
	      m = 0;
	      if (m < k);
	    }
	    else
	    {
	      return;
	    }
	    File[] arrayOfFile2 = arrayOfFile1[m].listFiles();
	    int n = arrayOfFile2.length;
	    int i1 = 0;
	    while (true)
	    {
	      if (i1 >= n)
	      {
	        m += 1;
	        break;
	      }
	      boolean bool = arrayOfFile2[i1].delete();
	      i1 += 1;
	    }
	  }

	  public boolean deleteFile(String paramString)
	  {
	    return this.mContext.deleteFile(paramString);
	  }

	  public void deleteOfflineData(int paramInt)
	  {
	    String str1 = getMp3Path(String.valueOf(paramInt));
	    File localFile1 = new File(str1);
	    if (localFile1.exists())
	      boolean bool1 = localFile1.delete();
	    String str2 = getImgPath(String.valueOf(paramInt));
	    if (new File(str2).exists())
	      boolean bool2 = localFile1.delete();
	    String str3 = getLrcPath(String.valueOf(paramInt));
	    File localFile2 = new File(str3);
	    if (localFile2.exists())
	      boolean bool3 = localFile2.delete();
	  }

	  public String getFileLrc(int paramInt)
	  {
	    String str1 = getLrcPath("lrc_" + paramInt);
	    if (new File(str1).exists());
	    while (true)
	    {
	      try
	      {
	        byte[] arrayOfByte = readFile(str1);
	        str2 = new String(arrayOfByte);
	        return str2;
	      }
	      catch (IOException localIOException)
	      {
	        localIOException.printStackTrace();
	        String str3 = TAG;
	        StringBuilder localStringBuilder = new StringBuilder("FileUtil getImg1");
	        String str4 = localIOException.toString();
	        String str5 = str4;
	        int i = Log.e(str3, str5);
	      }
	      String str2 = null;
	    }
	  }

	  // ERROR //
	  public Bitmap getImg(String paramString1, String paramString2)
	  {
	    // Byte code:
	    //   0: aload_2
	    //   1: invokestatic 185	com/xiami/file/FileUtil:getImgPath	(Ljava/lang/String;)Ljava/lang/String;
	    //   4: astore_3
	    //   5: aload_3
	    //   6: invokestatic 240	com/xiami/util/Log:d	(Ljava/lang/String;)I
	    //   9: istore 4
	    //   11: new 33	android/graphics/BitmapFactory$Options
	    //   14: dup
	    //   15: invokespecial 272	android/graphics/BitmapFactory$Options:<init>	()V
	    //   18: astore 5
	    //   20: aload 5
	    //   22: ldc 95
	    //   24: putfield 276	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
	    //   27: aload_2
	    //   28: ifnull +179 -> 207
	    //   31: new 128	java/io/File
	    //   34: dup
	    //   35: aload_3
	    //   36: invokespecial 144	java/io/File:<init>	(Ljava/lang/String;)V
	    //   39: invokevirtual 188	java/io/File:exists	()Z
	    //   42: ifeq +172 -> 214
	    //   45: aload_0
	    //   46: aload_3
	    //   47: invokevirtual 196	com/xiami/file/FileUtil:readFile	(Ljava/lang/String;)[B
	    //   50: astore 6
	    //   52: new 278	java/io/ByteArrayInputStream
	    //   55: dup
	    //   56: aload 6
	    //   58: invokespecial 279	java/io/ByteArrayInputStream:<init>	([B)V
	    //   61: astore 7
	    //   63: aload 7
	    //   65: aconst_null
	    //   66: aload 5
	    //   68: invokestatic 285	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
	    //   71: astore 8
	    //   73: aload 5
	    //   75: bipush 255
	    //   77: ldc_w 286
	    //   80: invokestatic 288	com/xiami/file/FileUtil:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
	    //   83: istore 9
	    //   85: aload 5
	    //   87: ldc_w 289
	    //   90: putfield 276	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
	    //   93: aload 5
	    //   95: iload 9
	    //   97: putfield 292	android/graphics/BitmapFactory$Options:inSampleSize	I
	    //   100: aload 7
	    //   102: aconst_null
	    //   103: aload 5
	    //   105: invokestatic 285	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
	    //   108: astore 10
	    //   110: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   113: astore 11
	    //   115: new 212	java/lang/StringBuilder
	    //   118: dup
	    //   119: ldc_w 294
	    //   122: invokespecial 215	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
	    //   125: aload_3
	    //   126: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   129: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   132: astore 12
	    //   134: aload 11
	    //   136: aload 12
	    //   138: invokestatic 29	com/xiami/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   141: istore 13
	    //   143: aload 7
	    //   145: invokevirtual 295	java/io/InputStream:close	()V
	    //   148: aload 10
	    //   150: astore 14
	    //   152: aload 14
	    //   154: areturn
	    //   155: astore 15
	    //   157: aload 15
	    //   159: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   162: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   165: astore 16
	    //   167: new 212	java/lang/StringBuilder
	    //   170: dup
	    //   171: ldc_w 268
	    //   174: invokespecial 215	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
	    //   177: astore 17
	    //   179: aload 15
	    //   181: invokevirtual 269	java/io/IOException:toString	()Ljava/lang/String;
	    //   184: astore 18
	    //   186: aload 17
	    //   188: aload 18
	    //   190: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   193: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   196: astore 19
	    //   198: aload 16
	    //   200: aload 19
	    //   202: invokestatic 206	com/xiami/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
	    //   205: istore 20
	    //   207: aconst_null
	    //   208: astore 14
	    //   210: goto -58 -> 152
	    //   213: athrow
	    //   214: aload_1
	    //   215: invokestatic 297	com/xiami/file/FileUtil:getContent	(Ljava/lang/String;)Ljava/io/InputStream;
	    //   218: astore 21
	    //   220: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   223: astore 22
	    //   225: new 212	java/lang/StringBuilder
	    //   228: dup
	    //   229: ldc_w 299
	    //   232: invokespecial 215	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
	    //   235: aload_1
	    //   236: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   239: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   242: astore 23
	    //   244: aload 22
	    //   246: aload 23
	    //   248: invokestatic 29	com/xiami/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   251: istore 24
	    //   253: aload 21
	    //   255: ifnonnull +20 -> 275
	    //   258: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   261: ldc_w 301
	    //   264: invokestatic 29	com/xiami/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   267: istore 25
	    //   269: aconst_null
	    //   270: astore 14
	    //   272: goto -120 -> 152
	    //   275: aload_0
	    //   276: aload 21
	    //   278: aload_3
	    //   279: invokevirtual 305	com/xiami/file/FileUtil:saveStreamToFile	(Ljava/io/InputStream;Ljava/lang/String;)V
	    //   282: aload_0
	    //   283: aload_3
	    //   284: invokevirtual 196	com/xiami/file/FileUtil:readFile	(Ljava/lang/String;)[B
	    //   287: astore 26
	    //   289: new 278	java/io/ByteArrayInputStream
	    //   292: dup
	    //   293: aload 26
	    //   295: invokespecial 279	java/io/ByteArrayInputStream:<init>	([B)V
	    //   298: aconst_null
	    //   299: aload 5
	    //   301: invokestatic 285	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
	    //   304: astore 27
	    //   306: aload 5
	    //   308: bipush 255
	    //   310: ldc_w 286
	    //   313: invokestatic 288	com/xiami/file/FileUtil:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
	    //   316: istore 28
	    //   318: aload 5
	    //   320: ldc_w 289
	    //   323: putfield 276	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
	    //   326: aload 5
	    //   328: iload 28
	    //   330: putfield 292	android/graphics/BitmapFactory$Options:inSampleSize	I
	    //   333: aload_3
	    //   334: aload 5
	    //   336: invokestatic 309	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
	    //   339: astore 29
	    //   341: aload 29
	    //   343: astore 10
	    //   345: aload 21
	    //   347: ifnull +8 -> 355
	    //   350: aload 21
	    //   352: invokevirtual 295	java/io/InputStream:close	()V
	    //   355: aload 10
	    //   357: astore 14
	    //   359: goto -207 -> 152
	    //   362: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   365: goto -10 -> 355
	    //   368: astore 15
	    //   370: aload 15
	    //   372: invokevirtual 118	java/io/FileNotFoundException:printStackTrace	()V
	    //   375: aload 21
	    //   377: ifnull -170 -> 207
	    //   380: aload 21
	    //   382: invokevirtual 295	java/io/InputStream:close	()V
	    //   385: goto -178 -> 207
	    //   388: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   391: goto -184 -> 207
	    //   394: astore 15
	    //   396: aload 15
	    //   398: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   401: aload 21
	    //   403: ifnull -196 -> 207
	    //   406: aload 21
	    //   408: invokevirtual 295	java/io/InputStream:close	()V
	    //   411: goto -204 -> 207
	    //   414: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   417: goto -210 -> 207
	    //   420: astore 14
	    //   422: aload 21
	    //   424: ifnull +8 -> 432
	    //   427: aload 21
	    //   429: invokevirtual 295	java/io/InputStream:close	()V
	    //   432: aload 14
	    //   434: athrow
	    //   435: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   438: goto -6 -> 432
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   45	148	155	java/io/IOException
	    //   45	148	213	finally
	    //   157	207	213	finally
	    //   350	355	362	java/io/IOException
	    //   275	341	368	java/io/FileNotFoundException
	    //   380	385	388	java/io/IOException
	    //   275	341	394	java/io/IOException
	    //   406	411	414	java/io/IOException
	    //   275	341	420	finally
	    //   370	375	420	finally
	    //   396	401	420	finally
	    //   427	432	435	java/io/IOException
	  }

	  public Bitmap getLocalImage(String paramString)
	  {
	    String str1 = getImgPath(paramString);
	    if ((paramString != null) && (new File(str1).exists()));
	    while (true)
	    {
	      try
	      {
	        byte[] arrayOfByte = readFile(str1);
	        Bitmap localBitmap1 = BitmapFactory.decodeStream(new ByteArrayInputStream(arrayOfByte));
	        String str2 = TAG;
	        String str3 = "FileUtil getImg from sdcard path=" + str1;
	        int i = Log.d(str2, str3);
	        localBitmap2 = localBitmap1;
	        return localBitmap2;
	      }
	      catch (Exception localException)
	      {
	        String str4 = TAG;
	        StringBuilder localStringBuilder = new StringBuilder("FileUtil getImg1");
	        String str5 = localException.toString();
	        String str6 = str5;
	        int j = Log.e(str4, str6);
	      }
	      Bitmap localBitmap2 = null;
	    }
	  }

	  public String getMp3(String paramString1, String paramString2)
	  {
	    String str1 = getMp3Path(paramString2);
	    String str2;
	    if (paramString2 != null)
	      if (new File(str1).exists())
	        str2 = str1.replace("\\", "/");
	    while (true)
	    {
	      return str2;
	      str2 = paramString1;
	      continue;
	      str2 = null;
	    }
	  }

	  public String getWebLrc(String paramString, int paramInt)
	  {
	    String str1 = getLrcPath("lrc_" + paramInt);
	    InputStream localInputStream = getContent(paramString);
	    try
	    {
	      FileOutputStream localFileOutputStream = new FileOutputStream(str1);
	      byte[] arrayOfByte1 = InputStreamToByte(localInputStream);
	      byte[] arrayOfByte2 = arrayOfByte1;
	      if (arrayOfByte2 == null)
	        if (localInputStream == null);
	      while (true)
	      {
	        try
	        {
	          localInputStream.close();
	          str2 = null;
	          return str2;
	        }
	        catch (IOException localIOException2)
	        {
	          localIOException2.printStackTrace();
	          continue;
	        }
	        localFileOutputStream.write(arrayOfByte2);
	        localFileOutputStream.close();
	        str2 = new String(arrayOfByte2);
	        if (localInputStream == null)
	          continue;
	        try
	        {
	          localInputStream.close();
	        }
	        catch (IOException localIOException3)
	        {
	          localIOException3.printStackTrace();
	        }
	      }
	    }
	    catch (FileNotFoundException localFileNotFoundException)
	    {
	      while (true)
	      {
	        String str2;
	        localFileNotFoundException.printStackTrace();
	        if (localInputStream != null);
	        try
	        {
	          localInputStream.close();
	          str2 = null;
	        }
	        catch (IOException localIOException4)
	        {
	          while (true)
	            localIOException4.printStackTrace();
	        }
	      }
	    }
	    catch (IOException localIOException1)
	    {
	      while (true)
	      {
	        localIOException1.printStackTrace();
	        if (localInputStream == null)
	          continue;
	        try
	        {
	          localInputStream.close();
	        }
	        catch (IOException localIOException5)
	        {
	          localIOException5.printStackTrace();
	        }
	      }
	    }
	    finally
	    {
	      if (localInputStream == null);
	    }
	    try
	    {
	      localInputStream.close();
	      throw localObject;
	    }
	    catch (IOException localIOException6)
	    {
	      while (true)
	        localIOException6.printStackTrace();
	    }
	  }

	  // ERROR //
	  public byte[] readFile(String paramString)
	    throws IOException
	  {
	    // Byte code:
	    //   0: iconst_0
	    //   1: checkcast 336	[B
	    //   4: astore_2
	    //   5: aconst_null
	    //   6: astore_3
	    //   7: new 128	java/io/File
	    //   10: dup
	    //   11: aload_1
	    //   12: invokespecial 144	java/io/File:<init>	(Ljava/lang/String;)V
	    //   15: astore 4
	    //   17: new 116	java/io/FileInputStream
	    //   20: dup
	    //   21: aload 4
	    //   23: invokespecial 339	java/io/FileInputStream:<init>	(Ljava/io/File;)V
	    //   26: astore 5
	    //   28: aload 5
	    //   30: invokevirtual 342	java/io/FileInputStream:available	()I
	    //   33: newarray byte
	    //   35: astore_2
	    //   36: aload 5
	    //   38: aload_2
	    //   39: invokevirtual 345	java/io/FileInputStream:read	([B)I
	    //   42: istore 6
	    //   44: aload 5
	    //   46: ifnull +8 -> 54
	    //   49: aload 5
	    //   51: invokevirtual 346	java/io/FileInputStream:close	()V
	    //   54: aload 5
	    //   56: astore 7
	    //   58: aload_2
	    //   59: areturn
	    //   60: astore 8
	    //   62: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   65: astore 9
	    //   67: aload 8
	    //   69: invokevirtual 347	java/io/FileNotFoundException:toString	()Ljava/lang/String;
	    //   72: astore 10
	    //   74: aload 9
	    //   76: aload 10
	    //   78: invokestatic 206	com/xiami/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
	    //   81: istore 11
	    //   83: aload_3
	    //   84: ifnull -26 -> 58
	    //   87: aload_3
	    //   88: invokevirtual 346	java/io/FileInputStream:close	()V
	    //   91: goto -33 -> 58
	    //   94: astore 8
	    //   96: aload 8
	    //   98: invokevirtual 114	java/io/IOException:printStackTrace	()V
	    //   101: aload_3
	    //   102: ifnull -44 -> 58
	    //   105: aload_3
	    //   106: invokevirtual 346	java/io/FileInputStream:close	()V
	    //   109: goto -51 -> 58
	    //   112: astore 12
	    //   114: aload_3
	    //   115: ifnull +7 -> 122
	    //   118: aload_3
	    //   119: invokevirtual 346	java/io/FileInputStream:close	()V
	    //   122: aload 12
	    //   124: athrow
	    //   125: astore 12
	    //   127: aload 5
	    //   129: astore_3
	    //   130: goto -16 -> 114
	    //   133: astore 8
	    //   135: aload 5
	    //   137: astore_3
	    //   138: goto -42 -> 96
	    //   141: astore 8
	    //   143: aload 5
	    //   145: astore_3
	    //   146: goto -84 -> 62
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   7	28	60	java/io/FileNotFoundException
	    //   7	28	94	java/io/IOException
	    //   7	28	112	finally
	    //   62	83	112	finally
	    //   96	101	112	finally
	    //   28	44	125	finally
	    //   28	44	133	java/io/IOException
	    //   28	44	141	java/io/FileNotFoundException
	  }

	  public void saveStreamToFile(InputStream paramInputStream, String paramString)
	    throws IOException
	  {
	    FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
	    while (true)
	    {
	      int i = paramInputStream.read();
	      if (i <= -1)
	      {
	        localFileOutputStream.close();
	        return;
	      }
	      localFileOutputStream.write(i);
	    }
	  }

	  // ERROR //
	  public void writeDataToFile(byte[] paramArrayOfByte, String paramString)
	    throws IOException
	  {
	    // Byte code:
	    //   0: aconst_null
	    //   1: astore_3
	    //   2: new 328	java/io/FileOutputStream
	    //   5: dup
	    //   6: aload_2
	    //   7: invokespecial 329	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
	    //   10: astore 4
	    //   12: aload 4
	    //   14: aload_1
	    //   15: invokevirtual 333	java/io/FileOutputStream:write	([B)V
	    //   18: aload 4
	    //   20: invokevirtual 351	java/io/FileOutputStream:flush	()V
	    //   23: aload 4
	    //   25: ifnull +8 -> 33
	    //   28: aload 4
	    //   30: invokevirtual 334	java/io/FileOutputStream:close	()V
	    //   33: aload 4
	    //   35: astore 5
	    //   37: return
	    //   38: astore 6
	    //   40: getstatic 15	com/xiami/file/FileUtil:TAG	Ljava/lang/String;
	    //   43: astore 7
	    //   45: aload 6
	    //   47: invokevirtual 269	java/io/IOException:toString	()Ljava/lang/String;
	    //   50: astore 8
	    //   52: aload 7
	    //   54: aload 8
	    //   56: invokestatic 206	com/xiami/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
	    //   59: istore 9
	    //   61: aload_3
	    //   62: ifnull -25 -> 37
	    //   65: aload_3
	    //   66: invokevirtual 334	java/io/FileOutputStream:close	()V
	    //   69: goto -32 -> 37
	    //   72: astore 10
	    //   74: aload_3
	    //   75: ifnull +7 -> 82
	    //   78: aload_3
	    //   79: invokevirtual 334	java/io/FileOutputStream:close	()V
	    //   82: aload 10
	    //   84: athrow
	    //   85: astore 10
	    //   87: aload 4
	    //   89: astore_3
	    //   90: goto -16 -> 74
	    //   93: astore 6
	    //   95: aload 4
	    //   97: astore_3
	    //   98: goto -58 -> 40
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   2	12	38	java/io/IOException
	    //   2	12	72	finally
	    //   40	61	72	finally
	    //   12	23	85	finally
	    //   12	23	93	java/io/IOException
	  }*/
}
