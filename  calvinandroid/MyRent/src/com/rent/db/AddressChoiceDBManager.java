package com.rent.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.rent.R;
import com.rent.Rent;
import com.rent.data.PCDZAddress;

public class AddressChoiceDBManager {

	public static final String DB_NAME = "pcdz";
	private static final String DB_PATH;
	private static final String PACKAGE_NAME = "com.rent";
	private final int BUFFER_SIZE = 204800;
	private final String TABLE_CITY = "city";
	private final String TABLE_DISTRICT = "district";
	private final String TABLE_PRICE = "rent_price";
	private final String TABLE_PROVINCE = "province";
	private final String TABLE_ZONE = "zone";
	private Context mContext;
	private SQLiteDatabase mDatabase;

	static {
		StringBuilder localStringBuilder = new StringBuilder().append("/data");
		String str = Environment.getDataDirectory().getAbsolutePath();
		DB_PATH = str + "/" + "com.rent" + "/databases";
	}

	public AddressChoiceDBManager(Context paramContext) {
		this.mContext = paramContext;
	}

	public void closeDatabase() {
		if (this.mDatabase != null)
			this.mDatabase.close();
	}

	public ArrayList<PCDZAddress> getCityByProvince(int paramInt) {
		ArrayList localArrayList1 = new ArrayList();
		String[] arrayOfString1 = new String[2];
		arrayOfString1[0] = "id";
		arrayOfString1[1] = "name";
		try {
			SQLiteDatabase localSQLiteDatabase = this.mDatabase;
			String[] arrayOfString2 = new String[1];
			String str1 = paramInt + "";
			arrayOfString2[0] = str1;
			Cursor localCursor = localSQLiteDatabase.query("city",
					arrayOfString1, "province_id=?", arrayOfString2, null,
					null, "id desc");
			while (localCursor.moveToNext()) {
				PCDZAddress localPCDZAddress = new PCDZAddress();
				int i = localCursor.getInt(0);
				localPCDZAddress.mId = i;
				String str2 = localCursor.getString(1);
				localPCDZAddress.mName = str2;
				boolean bool = localArrayList1.add(localPCDZAddress);
			}
		} catch (Exception localArrayList2) {
			localArrayList2.printStackTrace();
		} finally {
		}
		return localArrayList1;
	}

	// ERROR //
	public int getCityFirstDistrictId(String paramString1, String paramString2) {
		return 0;
	}

	// ERROR //
	public String getCityFirstDistrictName(String paramString) {
		return "";
	}

	// ERROR //
	public int getCityIdByCityName(String paramString) {
		return 0;
	}

	public ArrayList<PCDZAddress> getDistrictByCity(int paramInt) {
		ArrayList localArrayList1 = new ArrayList();
		String[] arrayOfString1 = new String[2];
		arrayOfString1[0] = "id";
		arrayOfString1[1] = "name";
		try {
			SQLiteDatabase localSQLiteDatabase = this.mDatabase;
			String[] arrayOfString2 = new String[1];
			String str1 = paramInt + "";
			arrayOfString2[0] = str1;
			Cursor localCursor = localSQLiteDatabase.query("district",
					arrayOfString1, "city_id=?", arrayOfString2, null, null,
					"id asc");
			while (localCursor.moveToNext()) {
				PCDZAddress localPCDZAddress = new PCDZAddress();
				int i = localCursor.getInt(0);
				localPCDZAddress.mId = i;
				String str2 = localCursor.getString(1);
				localPCDZAddress.mName = str2;
				boolean bool = localArrayList1.add(localPCDZAddress);
			}
		} catch (Exception localArrayList2) {
			localArrayList2.printStackTrace();
		} finally {
		}
		return localArrayList1;
	}

	// ERROR //
	public String getDistrictName(int paramInt1, int paramInt2) {
		return "";
	}

	public String getDistrictName(int paramInt, String paramString) {
		int i = getCityIdByCityName(paramString);
		return getDistrictName(paramInt, i);
	}

	// ERROR //
	public String getDistrictNameById(int paramInt) {
		return "";
	}

	// ERROR //
	public String getPriceByCity(int paramInt) {
		return "";
	}

	public ArrayList<PCDZAddress> getProvince() {
		ArrayList localArrayList1 = new ArrayList();
		String[] arrayOfString = new String[2];
		arrayOfString[0] = "id";
		arrayOfString[1] = "name";
		try {
			Cursor localCursor = this.mDatabase.query("province",
					arrayOfString, null, null, null, null, "id desc", null);
			while (localCursor.moveToNext()) {
				PCDZAddress localPCDZAddress = new PCDZAddress();
				int i = localCursor.getInt(0);
				localPCDZAddress.mId = i;
				String str = localCursor.getString(1);
				localPCDZAddress.mName = str;
				boolean bool = localArrayList1.add(localPCDZAddress);
			}
		} catch (Exception localArrayList2) {
			localArrayList2.printStackTrace();
		} finally {
		}
		return localArrayList1;
	}

	public ArrayList<PCDZAddress> getZoneByDistrict(int paramInt) {
		ArrayList localArrayList1 = new ArrayList();
		String[] arrayOfString1 = new String[2];
		arrayOfString1[0] = "id";
		arrayOfString1[1] = "name";
		try {
			SQLiteDatabase localSQLiteDatabase = this.mDatabase;
			String[] arrayOfString2 = new String[1];
			String str1 = paramInt + "";
			arrayOfString2[0] = str1;
			Cursor localCursor = localSQLiteDatabase.query("zone",
					arrayOfString1, "district_id=?", arrayOfString2, null,
					null, "id asc");
			while (localCursor.moveToNext()) {
				PCDZAddress localPCDZAddress = new PCDZAddress();
				int i = localCursor.getInt(0);
				localPCDZAddress.mId = i;
				String str2 = localCursor.getString(1);
				localPCDZAddress.mName = str2;
				boolean bool = localArrayList1.add(localPCDZAddress);
			}
		} catch (Exception localArrayList2) {
			localArrayList2.printStackTrace();
		} finally {
		}
		return localArrayList1;
	}

	public int getZoneNumberByDistrictId(int paramInt) {
		int i = 0;
		try {
			SQLiteDatabase localSQLiteDatabase = this.mDatabase;
			String[] arrayOfString = new String[1];
			String str = paramInt + "";
			arrayOfString[0] = str;
			Cursor localCursor = localSQLiteDatabase.query("zone", null,
					"district_id=?", arrayOfString, null, null, "id asc");
			i = localCursor.getCount();
			localCursor.close();

		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return i;
	}

	public boolean isDistrictInCity(int paramInt1, int paramInt2) {
		if ((getDistrictName(paramInt2, paramInt1) != null)
				&& (getDistrictName(paramInt2, paramInt1).length() > 0))
			return true;
		return false;

	}

	public boolean isDistrictInCity(String paramString, int paramInt) {
		if ((getDistrictName(paramInt, paramString) != null)
				&& (getDistrictName(paramInt, paramString).length() > 0))
			return true;
		return false;
	}

	public void openDatabase() {
		String localObject1 = DB_PATH;
		String str1 = "pcdz";
		InputStream localObject2 = null;
		FileOutputStream localObject3 = null;
		try {
			File newFile = new File(localObject1);
			String str2 = (String) localObject1 + "/" + "pcdz";
			File file = new File(str2);
			boolean result1 = false;
			if (!newFile.exists())
				result1 = newFile.mkdirs();
			if ((!Rent.getOutDBSP(this.mContext)) && file.exists()) {
				boolean bool2 = file.delete();
				int j = Log.e("AddressChoiceDBManager.openDatabase", "delete");
			}
			if (!file.exists()) {
				file.createNewFile();
				Rent.setOutDBSP(this.mContext, true);
				int k = Log.e("AddressChoiceDBManager.openDatabase", "mikfile");
				localObject2 = this.mContext.getResources().openRawResource(
						R.raw.pcdz);
				String str3 = (String) localObject1 + "/" + "pcdz";
				localObject3 = new FileOutputStream(str3);
				byte[] newbyte = new byte[204800];
				while (true) {
					int i = ((InputStream) localObject2).read(newbyte);
					if (i <= 0)
						break;
					localObject3.write(newbyte, 0, i);
				}
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			try {
				if (localObject3 != null)
					localObject3.close();
				if (localObject2 != null)
					localObject2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SQLiteDatabase localSQLiteDatabase = this.mContext
					.openOrCreateDatabase("pcdz", 0, null);
			this.mDatabase = localSQLiteDatabase;
		}
	}

}
