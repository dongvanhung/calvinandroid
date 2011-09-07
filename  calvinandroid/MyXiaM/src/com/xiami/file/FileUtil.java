package com.xiami.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;



public class FileUtil {

	public static String TAG = "xiami";
	  private Context mContext;

	  public FileUtil(Context paramContext)
	  {
	    this.mContext = paramContext;
	    int i = Log.d(TAG, "New FileUtil");
	  }

	  private static int computeInitialSampleSize(BitmapFactory.Options options, int i, int j)
	  {
		  double d = options.outWidth;
	        double d1 = options.outHeight;
	        int k;
	        int l;
	        int i1;
	        if(j == -1)
	        {
	            k = 1;
	        } else
	        {
	            double d2 = d * d1;
	            double d3 = j;
	            k = (int)Math.ceil(Math.sqrt(d2 / d3));
	        }
	        if(i == -1)
	        {
	            l = 128;
	        } else
	        {
	            double d4 = i;
	            double d5 = Math.floor(d / d4);
	            double d6 = i;
	            double d7 = Math.floor(d1 / d6);
	            l = (int)Math.min(d5, d7);
	        }
	        if(l < k)
	            i1 = k;
	        else
	        if(j == -1 && i == -1)
	            i1 = 1;
	        else
	        if(i == -1)
	            i1 = k;
	        else
	            i1 = l;
	        return i1;
	  }

	  public static int computeSampleSize(BitmapFactory.Options paramOptions, int i, int j)
	  {
		int k;
		int l = 0;
		k = computeInitialSampleSize(paramOptions, i, j);
		if (k <= 8) {
			l = 1;
			l <<= 1;
			if (l >= k) {
				l = ((k + 7) / 8) * 8;
			}
		}
		return l;
	  }

	  public static InputStream getContent(String paramString)
	  {
	    InputStream localObject2 = null;
	    if ((paramString.length() > 4) && (paramString.substring(0, 4).equals("http")))
	    	try
	      {
	        FileInputStream localFileInputStream = new FileInputStream(paramString);
	        localObject2 = localFileInputStream;
	      }
	      catch (FileNotFoundException localFileNotFoundException)
	      {
	        localFileNotFoundException.printStackTrace();
	      }
	    else
	    {
	      try
	      {
	        URL localURL = new URL(paramString);
	          HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
	          localHttpURLConnection.setDoInput(true);
	          localHttpURLConnection.setReadTimeout(20000);
	          localHttpURLConnection.connect();
	          InputStream localInputStream = localHttpURLConnection.getInputStream();
	          localObject2 = localInputStream;
	        }
	        catch (IOException localIOException)
	        {
	          localIOException.printStackTrace();
	        }
	    }
	    return localObject2;
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
			throws IOException {
		byte[] localObject = null;
		if (paramInputStream == null) {
			localObject = null;
		} else {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			int i = paramInputStream.read();
			while (i == -1) {
				localByteArrayOutputStream.write(i);
			}
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			localByteArrayOutputStream.close();
			localObject = arrayOfByte;
		}
		return localObject;
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
	     return true;
	    }
	    catch (Exception localException)
	    {
	        String str2 = localException.toString();
	    }
	    return false;
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
	        return bool;
	      }
	    }
	    catch (Exception localException)
	    {
	        String str1 = TAG;
	        StringBuilder localStringBuilder = new StringBuilder("createDir__");
	        String str2 = localException.toString();
	        String str3 = str2;
	        int j = Log.e(str1, str3);
	      }
	    return false;
	  }
	  
	  class FileUtil1 implements FileFilter
	{
	  public boolean accept(File paramFile)
	  {
			if ((paramFile.isDirectory())
					&& ((paramFile.getName().equalsIgnoreCase("img"))
							|| (paramFile.getName().equalsIgnoreCase("file")) || (paramFile
							.getName().equalsIgnoreCase("lrc"))))
				return true;
			return false;
	  }
	}
	  
	  public void deleteAllOfflineData()
	  {
		String str = getOfflineFolder();
		File localFile = new File(str);
		File[] arrayOfFile1;
		int m;
		if (localFile.exists()) {
			FileUtil1 local1 = new FileUtil1();
			arrayOfFile1 = localFile.listFiles(local1);
			StringBuilder localStringBuilder = new StringBuilder(
					"delete folders:");
			for (int i = 0; i < arrayOfFile1.length; i++) {
				File[] arrayOfFile2 = arrayOfFile1[i].listFiles();
				for (int j = 0; j < arrayOfFile2.length; j++) {
					boolean bool = arrayOfFile2[j].delete();
				}
			}
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
	      localFile1.delete();
	    String str2 = getImgPath(String.valueOf(paramInt));
	    if (new File(str2).exists())
	      localFile1.delete();
	    String str3 = getLrcPath(String.valueOf(paramInt));
	    File localFile2 = new File(str3);
	    if (localFile2.exists())
	      localFile2.delete();
	  }

	  public String getFileLrc(int paramInt)
	  {
		  String str2 = null;
	    String str1 = getLrcPath("lrc_" + paramInt);
	    if (new File(str1).exists()) {
	      try
	      {
	        byte[] arrayOfByte = readFile(str1);
	        str2 = new String(arrayOfByte);
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
	    }
	    return str2;
	  }

	  // ERROR //
	  public Bitmap getImg(String s, String s1)
	  {
		String s2 = null;
		android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
		s2 = getImgPath(s1);
		options.inJustDecodeBounds = true;
		Bitmap bitmap2 = null;
		try {
			if (s1 != null) {
				if (!(new File(s2)).exists()) {
					InputStream inputstream = getContent(s);
					if (inputstream != null) {
						saveStreamToFile(inputstream, s2);
						byte abyte1[] = readFile(s2);
						Bitmap bitmap3 = BitmapFactory
								.decodeStream(new ByteArrayInputStream(abyte1),
										null, options);
						int k1 = computeSampleSize(options, -1, 0x57e40);
						options.inJustDecodeBounds = false;
						options.inSampleSize = k1;
						bitmap2 = BitmapFactory.decodeFile(s2, options);
						inputstream.close();
					}
				} else {
					Bitmap bitmap1;
					byte abyte0[] = readFile(s2);
					ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
							abyte0);
					Bitmap bitmap = BitmapFactory.decodeStream(
							bytearrayinputstream, null, options);
					int j = computeSampleSize(options, -1, 0x57e40);
					options.inJustDecodeBounds = false;
					options.inSampleSize = j;
					bitmap1 = BitmapFactory.decodeStream(bytearrayinputstream,
							null, options);
					String s3 = TAG;
					String s4 = (new StringBuilder(
							"FileUtil getImg from sdcard path=")).append(s2)
							.toString();
					int k = Log.d(s3, s4);
					bytearrayinputstream.close();
					bitmap2 = bitmap1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap2;
	  }

	  public Bitmap getLocalImage(String paramString)
	  {
	    String str1 = getImgPath(paramString);
	    Bitmap localBitmap1 = null;
	    if ((paramString != null) && (new File(str1).exists())){
	    	try
	      {
	        byte[] arrayOfByte = readFile(str1);
	        localBitmap1 = BitmapFactory.decodeStream(new ByteArrayInputStream(arrayOfByte));
	        String str2 = TAG;
	        String str3 = "FileUtil getImg from sdcard path=" + str1;
	        int i = Log.d(str2, str3);
	      }
	      catch (Exception localException)
	      {
	        String str4 = TAG;
	        StringBuilder localStringBuilder = new StringBuilder("FileUtil getImg1");
	        String str5 = localException.toString();
	        String str6 = str5;
	        int j = Log.e(str4, str6);
	      }
	    }
	    return localBitmap1;
	  }

	  public String getMp3(String paramString1, String paramString2)
	  {
	    String str1 = getMp3Path(paramString2);
	    String str2 = "";
	    if (paramString2 != null)
	      if (new File(str1).exists())
	        str2 = str1.replace("\\", "/");
	      else
	    {
	      str2 = paramString1;
	    }
	    return str2;
	  }

	  public String getWebLrc(String s, int i)
	  {
		String s1;
		InputStream inputstream;
		String s2 = "";
		try {
			s1 = getLrcPath((new StringBuilder("lrc_")).append(i).toString());
			inputstream = getContent(s);
			FileOutputStream fileoutputstream = null;
			byte abyte0[] = null;
			fileoutputstream = new FileOutputStream(s1);
			abyte0 = InputStreamToByte(inputstream);
			if (abyte0 != null) {
				fileoutputstream.write(abyte0);
				fileoutputstream.close();
				s2 = new String(abyte0);
				if (inputstream != null)
					inputstream.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s2;
	  }

	  public byte[] readFile(String s)
	    throws IOException
	  {
		byte abyte0[] = null;
		FileInputStream fileinputstream1 = null;
		File file = new File(s);
		fileinputstream1 = new FileInputStream(file);
		abyte0 = new byte[fileinputstream1.available()];
		int i = fileinputstream1.read(abyte0);
		if (fileinputstream1 != null)
			fileinputstream1.close();
		return abyte0;
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
	  public void writeDataToFile(byte[] abyte0, String s)
	    throws IOException
	  {
		FileOutputStream fileoutputstream1 = new FileOutputStream(s);
		fileoutputstream1.write(abyte0);
		fileoutputstream1.flush();
		if (fileoutputstream1 != null)
			fileoutputstream1.close();
	        
	  }
}
