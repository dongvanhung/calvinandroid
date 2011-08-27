package com.rent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class GroupImageManager {

	public static String FILE_FOLDER_PATH;

	static {
		FILE_FOLDER_PATH = Rent.APP_SDCARD_FOLDER + "/group/pic/";
	}

	public boolean saveBitmap(Bitmap paramBitmap, String paramString) {
		String s1 = Environment.getExternalStorageState();
		FileOutputStream fileoutputstream = null;
        try {
			if(!"mounted".equals(s1)) {
				Log.e("img", paramString);
			} else {
			    String s2 = FILE_FOLDER_PATH;
			    File file = new File(s2);
			    if(!file.exists())
			        file.mkdirs();
			    String s3 = (new StringBuilder()).append(file).append("/").append(paramString).toString();
			    File obj1 = new File(s3);
			    if(!obj1.exists()) {
			        boolean flag1 = obj1.createNewFile();
			        fileoutputstream = new FileOutputStream(obj1);
			        CompressFormat compressformat = android.graphics.Bitmap.CompressFormat.PNG;
			        boolean flag2 = paramBitmap.compress(compressformat, 100, fileoutputstream);
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			if(fileoutputstream != null) {
				 try {
					fileoutputstream.flush();
					 fileoutputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public Bitmap getBitmap(String paramString) throws Exception {
		String s1 = Environment.getExternalStorageState();
		FileInputStream obj1 = null;
		Bitmap bitmap = null;
		try {
	        if(!"mounted".equals(s1)) {
	        	Log.e("img", paramString);
	        } else {
	             StringBuilder stringbuilder = new StringBuilder();
	             String s2 = FILE_FOLDER_PATH;
	             String s3 = stringbuilder.append(s2).append(paramString).toString() + ".png";
	             File obj = new File(s3);
	             if(obj.exists()) {
	            	 obj1 = new FileInputStream(obj);
	                 bitmap = BitmapFactory.decodeStream(((java.io.InputStream) (obj1)));
	             } else {
	            	 return null;
	             }
	        }
		} finally {
			if(obj1 != null)
				obj1.close();
		}
        return bitmap;
	}
}
