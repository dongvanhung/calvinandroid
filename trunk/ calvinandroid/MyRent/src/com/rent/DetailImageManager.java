package com.rent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Environment;

public class DetailImageManager {

	public static String FILE_FOLDER_PATH;
	  public static final String IMAGES_FILE_HEAD = "images";
	  public static final String THUMBNAIL_FILE_HEAD = "thumbnails";

	  static
	  {
	    StringBuilder localStringBuilder = new StringBuilder();
	    String str = Rent.APP_SDCARD_FOLDER;
	    FILE_FOLDER_PATH = str + "/detail/pic/";
	  }

	  public static void clearCache(List<Long> paramList)
	  {
	    if (!Environment.getExternalStorageState().equals("mounted"));
	      String str1 = FILE_FOLDER_PATH;
	      File localObject = new File(str1);
	      if(localObject.exists()) {
	    	  String[] files = localObject.list();
			try {
				for (int j = 0; j < files.length; j++) {
					StringBuilder localStringBuilder1 = new StringBuilder();
					String str2 = FILE_FOLDER_PATH;
					StringBuilder localStringBuilder2 = localStringBuilder1
							.append(str2);
					String str3 = files[j];
					String str4 = str3;
					File localFile = new File(str4);
					boolean bool;
					if (localFile != null) {
						bool = localFile.isDirectory();
						if (!bool)
							;
					}
					try {
						Long localLong = Long.valueOf(files[j]);
						bool = paramList.contains(localLong);
						if (bool) {
							continue;
						}
					} catch (Exception localException1) {
						while (true) {
							localException1.printStackTrace();
							StringBuilder localStringBuilder3 = new StringBuilder();
							String str5 = FILE_FOLDER_PATH;
							StringBuilder localStringBuilder4 = localStringBuilder3
									.append(str5);
							String str6 = files[j];
							delDir(str6);
						}
					}
				}
			} catch (Exception localException2) {
					localException2.printStackTrace();
			}
	    }
	  }

	  public static void delDir(String paramString)
	    throws Exception
	  {
	    File localFile = new File(paramString);
	    if ((localFile.exists()) && (localFile.isDirectory()))
	    {
	      if (localFile.listFiles().length != 0) {
	    	  File[] arrayOfFile = localFile.listFiles();
		      int i = localFile.listFiles().length;
		      int j = 0;
		      while (j < i)
		      {
		        if (arrayOfFile[j].isDirectory())
		          delDir(arrayOfFile[j].getAbsolutePath());
		        boolean bool2 = arrayOfFile[j].delete();
		        j += 1;
		      }
	      } else {
	    	  boolean bool1 = localFile.delete();
	      }
	    }
	  }

	  // ERROR //
	  private Bitmap getBitmap(String paramString)
	  {
		  return null;
	    // Byte code:
	    //   0: invokestatic 46	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
	    //   3: astore_2
	    //   4: ldc 48
	    //   6: aload_2
	    //   7: invokevirtual 54	java/lang/String:equals	(Ljava/lang/Object;)Z
	    //   10: ifeq +98 -> 108
	    //   13: new 56	java/io/File
	    //   16: dup
	    //   17: aload_1
	    //   18: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
	    //   21: astore_3
	    //   22: aload_3
	    //   23: invokevirtual 63	java/io/File:exists	()Z
	    //   26: ifne +7 -> 33
	    //   29: aconst_null
	    //   30: astore_3
	    //   31: aload_3
	    //   32: areturn
	    //   33: new 101	java/io/FileInputStream
	    //   36: dup
	    //   37: aload_3
	    //   38: invokespecial 104	java/io/FileInputStream:<init>	(Ljava/io/File;)V
	    //   41: astore 4
	    //   43: aload 4
	    //   45: invokestatic 110	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
	    //   48: astore 5
	    //   50: aload 5
	    //   52: astore_3
	    //   53: aload 4
	    //   55: invokevirtual 113	java/io/FileInputStream:close	()V
	    //   58: goto -27 -> 31
	    //   61: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   64: goto -33 -> 31
	    //   67: astore_3
	    //   68: aconst_null
	    //   69: astore 4
	    //   71: aload_3
	    //   72: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   75: aload 4
	    //   77: invokevirtual 113	java/io/FileInputStream:close	()V
	    //   80: aconst_null
	    //   81: astore_3
	    //   82: goto -51 -> 31
	    //   85: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   88: goto -8 -> 80
	    //   91: astore_3
	    //   92: aconst_null
	    //   93: astore 4
	    //   95: aload 4
	    //   97: invokevirtual 113	java/io/FileInputStream:close	()V
	    //   100: aload_3
	    //   101: athrow
	    //   102: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   105: goto -5 -> 100
	    //   108: aconst_null
	    //   109: astore_3
	    //   110: goto -79 -> 31
	    //   113: astore_3
	    //   114: goto -19 -> 95
	    //   117: astore_3
	    //   118: goto -47 -> 71
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   53	58	61	java/lang/Exception
	    //   33	43	67	java/lang/Exception
	    //   75	80	85	java/lang/Exception
	    //   33	43	91	finally
	    //   95	100	102	java/lang/Exception
	    //   43	50	113	finally
	    //   71	75	113	finally
	    //   43	50	117	java/lang/Exception
	  }

	  private List<File> getFolderBitmap(String paramString)
	  {
	    ArrayList localArrayList = new ArrayList();
	    String str1 = Environment.getExternalStorageState();
	    Object localObject1;
	    if ("mounted".equals(str1))
	    {
	      StringBuilder localStringBuilder = new StringBuilder();
	      String str2 = FILE_FOLDER_PATH;
	      String str3 = str2 + paramString;
	      localObject1 = new File(str3);
	      if (((File)localObject1).exists()) {
	    	  File[] files = ((File)localObject1).listFiles();
		      for (int i = 0; i < files.length; i++) {
		    	  Object localObject2 = files[i];
			       localArrayList.add(localObject2);
		      }
	      }
	    }
	    return localArrayList;
	  }

	  private boolean isFolderEmpty(String paramString1, String paramString2)
	  {
	    ArrayList localArrayList = new ArrayList();
	    String str1 = Environment.getExternalStorageState();
	    File localObject1 = null;
	    int i;
	    if ("mounted".equals(str1))
	    {
	      StringBuilder localStringBuilder = new StringBuilder();
	      String str2 = FILE_FOLDER_PATH;
	      String str3 = str2 + paramString1;
	      localObject1 = new File(str3);
	      if (!localObject1.exists())
	        return true;
	    } else
	    {
	      File[] files  = localObject1.listFiles();
	     for (int j = 0; j < files.length; j++) {
	    	 File localObject2 = files[j];
		     if (localObject2.getName().startsWith(paramString2))
		          localArrayList.add(localObject2);
		}
	     if (localArrayList.size() > 0)
	      {
	    	 return false;
	      }
	    }
	    return true;
	  }

	  // ERROR //
	  private boolean saveBitmap(Bitmap paramBitmap, String paramString1, String paramString2)
	  {
		  return true;
	    // Byte code:
	    //   0: invokestatic 46	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
	    //   3: astore 4
	    //   5: ldc 48
	    //   7: aload 4
	    //   9: invokevirtual 54	java/lang/String:equals	(Ljava/lang/Object;)Z
	    //   12: ifeq +229 -> 241
	    //   15: new 16	java/lang/StringBuilder
	    //   18: dup
	    //   19: invokespecial 19	java/lang/StringBuilder:<init>	()V
	    //   22: astore 5
	    //   24: getstatic 36	com/songshulin/android/rent/DetailImageManager:FILE_FOLDER_PATH	Ljava/lang/String;
	    //   27: astore 6
	    //   29: aload 5
	    //   31: aload 6
	    //   33: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   36: aload_3
	    //   37: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   40: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   43: astore 7
	    //   45: new 56	java/io/File
	    //   48: dup
	    //   49: aload 7
	    //   51: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
	    //   54: astore 8
	    //   56: aload 8
	    //   58: invokevirtual 63	java/io/File:exists	()Z
	    //   61: ifne +10 -> 71
	    //   64: aload 8
	    //   66: invokevirtual 139	java/io/File:mkdirs	()Z
	    //   69: istore 9
	    //   71: new 16	java/lang/StringBuilder
	    //   74: dup
	    //   75: invokespecial 19	java/lang/StringBuilder:<init>	()V
	    //   78: aload 8
	    //   80: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
	    //   83: ldc 144
	    //   85: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   88: aload_2
	    //   89: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   92: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   95: astore 10
	    //   97: new 56	java/io/File
	    //   100: dup
	    //   101: aload 10
	    //   103: invokespecial 59	java/io/File:<init>	(Ljava/lang/String;)V
	    //   106: astore 11
	    //   108: aload 11
	    //   110: invokevirtual 63	java/io/File:exists	()Z
	    //   113: ifne +10 -> 123
	    //   116: aload 11
	    //   118: invokevirtual 147	java/io/File:createNewFile	()Z
	    //   121: istore 12
	    //   123: aconst_null
	    //   124: astore 8
	    //   126: new 149	java/io/FileOutputStream
	    //   129: dup
	    //   130: aload 11
	    //   132: invokespecial 150	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
	    //   135: astore 13
	    //   137: getstatic 156	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
	    //   140: astore 14
	    //   142: aload_1
	    //   143: aload 14
	    //   145: bipush 100
	    //   147: aload 13
	    //   149: invokevirtual 162	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
	    //   152: istore 15
	    //   154: aload 13
	    //   156: invokevirtual 165	java/io/FileOutputStream:flush	()V
	    //   159: aload 13
	    //   161: invokevirtual 166	java/io/FileOutputStream:close	()V
	    //   164: iconst_1
	    //   165: istore 8
	    //   167: iload 8
	    //   169: ireturn
	    //   170: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   173: iconst_0
	    //   174: istore 8
	    //   176: goto -9 -> 167
	    //   179: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   182: goto -18 -> 164
	    //   185: astore 16
	    //   187: aload 8
	    //   189: astore 11
	    //   191: aload 16
	    //   193: astore 8
	    //   195: aload 8
	    //   197: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   200: aload 11
	    //   202: invokevirtual 166	java/io/FileOutputStream:close	()V
	    //   205: iconst_0
	    //   206: istore 8
	    //   208: goto -41 -> 167
	    //   211: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   214: goto -9 -> 205
	    //   217: astore 17
	    //   219: aload 8
	    //   221: astore 11
	    //   223: aload 17
	    //   225: astore 8
	    //   227: aload 11
	    //   229: invokevirtual 166	java/io/FileOutputStream:close	()V
	    //   232: aload 8
	    //   234: athrow
	    //   235: invokevirtual 84	java/lang/Exception:printStackTrace	()V
	    //   238: goto -6 -> 232
	    //   241: iconst_0
	    //   242: istore 8
	    //   244: goto -77 -> 167
	    //   247: astore 8
	    //   249: aload 13
	    //   251: astore 11
	    //   253: goto -26 -> 227
	    //   256: astore 8
	    //   258: goto -31 -> 227
	    //   261: astore 8
	    //   263: aload 13
	    //   265: astore 11
	    //   267: goto -72 -> 195
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   116	123	170	java/lang/Exception
	    //   159	164	179	java/lang/Exception
	    //   126	137	185	java/lang/Exception
	    //   200	205	211	java/lang/Exception
	    //   126	137	217	finally
	    //   227	232	235	java/lang/Exception
	    //   137	159	247	finally
	    //   195	200	256	finally
	    //   137	159	261	java/lang/Exception
	  }

	  public List<Bitmap> getImagesBitmap(String paramString)
	  {
	    ArrayList localArrayList = new ArrayList();
	    Object localObject = getFolderBitmap(paramString);
	    if (localObject == null)
	    	return null;
	    else {
	      Collections.sort((List)localObject);
	      Iterator localIterator = ((List)localObject).iterator();
	      while (localIterator.hasNext())
	      {
	        localObject = (File)localIterator.next();
	        if (!((File)localObject).getName().startsWith("images"))
	          continue;
	        String str = ((File)localObject).toString();
	        Bitmap localBitmap = getBitmap(str);
	        boolean bool = localArrayList.add(localBitmap);
	      }
	    }
	    return localArrayList;
	  }

	  public List<Bitmap> getThumbnailBitmaps(String paramString)
	  {
	    ArrayList localArrayList = new ArrayList();
	    Iterator localIterator = getFolderBitmap(paramString).iterator();
	    while (localIterator.hasNext())
	    {
	      File localFile = (File)localIterator.next();
	      if (!localFile.getName().startsWith("thumbnails"))
	        continue;
	      String str = localFile.toString();
	      Bitmap localBitmap = getBitmap(str);
	      boolean bool = localArrayList.add(localBitmap);
	    }
	    return localArrayList;
	  }

	  public boolean isFolderNoImages(String paramString)
	  {
	    return isFolderEmpty(paramString, "images");
	  }

	  public boolean saveImagesBitmap(Bitmap paramBitmap, String paramString1, String paramString2)
	  {
	    String str = "images" + paramString1;
	    return saveBitmap(paramBitmap, str, paramString2);
	  }

	  public boolean saveThumbnailBitmap(Bitmap paramBitmap, String paramString1, String paramString2)
	  {
	    String str = "thumbnails" + paramString1;
	    return saveBitmap(paramBitmap, str, paramString2);
	  }
}
