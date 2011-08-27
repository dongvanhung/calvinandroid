package com.rent;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;

public class Rent extends Application {

	public static final int ACCOUNDING_BY_HOURSE = 1;
	public static final int ACCOUNDING_BY_RESIDENT_AREA = 0;
	public static final String APP_SDCARD_FOLDER;
	public static final String AUTHORITY = "com.rent.Rent";
	public static final String BUNDLE_ADDRESS = "address";
	public static final String BUNDLE_CITY = "city";
	public static final String BUNDLE_COMMUNITY_DETAIL_IMAGEARRAY = "bundle_community_detail_image";
	public static final String BUNDLE_COMMUNITY_ID = "bundle_community_id";
	public static final String BUNDLE_COMMUNITY_TYPE = "bundle_community_type";
	public static final String BUNDLE_COMMUNITY_TYPE_FLAT_TYPE = "flattype";
	public static final String BUNDLE_COMMUNITY_TYPE_LANDSPACE = "landspace";
	public static final String BUNDLE_DISTRICT = "district";
	public static final String BUNDLE_DISTRICT_ID = "district_id";
	public static final String BUNDLE_FLAG = "flag";
	public static final String BUNDLE_GROUP_ID = "group_id";
	public static final String BUNDLE_IMAGE = "image";
	public static final String BUNDLE_KEYWORD = "keyword";
	public static final String BUNDLE_LATITUDE = "latitude";
	public static final String BUNDLE_LONGITUDE = "longitude";
	public static final String BUNDLE_NAME = "name";
	public static final String BUNDLE_NETWORK_VALLABEL = "networkwork";
	public static final String BUNDLE_NETWORK_WAY = "networkway";
	public static final String BUNDLE_PRICE = "price";
	public static final String BUNDLE_SEARCH_FORMAT = "searchformat";
	public static final String BUNDLE_SOURCECOUNT = "sourcecoount";
	public static final int BUSSINESS_REQUEST_CODE = 1;
	public static final int BUSSINESS_RESPONSE_CODE = 2;
	public static final int CALLED_REQUESTCODE = 2;
	public static final int CALLED_RESULTCODE = 1;
	public static final int DATABASE_VERSION = 1;
	public static final int DETAIL_FAVOURITE_REQUEST = 1234;
	public static final String DIARY_SETTING = "diary_setting";
	public static final int FAVOURITE_CALLED_STATUS_BOTH_FAVOURITE_AND_CALLED = 4;
	public static final int FAVOURITE_CALLED_STATUS_CALLED_ONLY = 2;
	public static final int FAVOURITE_CALLED_STATUS_DONOT_CALLED = 3;
	public static final int FAVOURITE_CALLED_STATUS_DONOT_FAVOURITE = 0;
	public static final int FAVOURITE_CALLED_STATUS_FAVOURITE_ONLY = 1;
	public static final int FAVOURITE_CALLED_STATUS_NEITHER_FAVOURITE_OR_CALLED = 5;
	public static final String FEEDBACKNUM = "feedbacknum";
	public static final String FEEDBACK_NOTIFY = "feedback_notify";
	public static final String GALLERY_DETAIL_DATA_KEY = "gallery_detail_data_key";
	public static final int GALLERY_INPUT_FROM_SD = 1;
	public static final String GALLERY_INTENT_STATUS = "gallery_intent_status";
	public static final String GALLERY_ORIGIN_ID_KEY = "gallery_origin_id_key";
	public static final String HANDLER_DATA_KEY = "data";
	public static final int HANDLER_ERROR = 1;
	public static final String HELP_IMAGE_ID = "help_images_id";
	public static final String HISTORY_CITY_SEPARATE = "#city#";
	public static final String HOUSE_FILTER = "housefilter";
	public static final String INIT_LAT = "39.920591";
	public static final String INIT_LON = "116.432791";
	public static final int INIT_ZOOM = 16;
	public static final String IS_CALLED = "is_called";
	public static final String JSON_ABSTRACT_KEY = "abstract";
	public static final String JSON_ADDRESSDETAILS_KEY = "AddressDetails";
	public static final String JSON_ADDRESS_KEY = "address";
	public static final String JSON_ADMINISTRATIVEAREANAME_KEY = "AdministrativeAreaName";
	public static final String JSON_ADMINISTRATIVEAREA_KEY = "AdministrativeArea";
	public static final String JSON_AGENCY_NAME_KEY = "agency_name";
	public static final String JSON_AGENCY_STATUS_KEY = "agency_status";
	public static final String JSON_AGGREGATE_ITEMS_KEY = "aggregate_items";
	public static final String JSON_ANSWER = "answer";
	public static final String JSON_ANSWER_TIME = "answer_time";
	public static final String JSON_AREA_KEY = "area";
	public static final String JSON_BANK_KEY = "bank";
	public static final String JSON_BUSINESS_DISTRICT_KEY = "business_district";
	public static final String JSON_BUS_KEY = "bus";
	public static final String JSON_CITY_KEY = "city";
	public static final String JSON_COMMUNITY_ARCH_TYPE_KEY = "arch_type";
	public static final String JSON_COMMUNITY_BUILDING_AREA_KEY = "building_area";
	public static final String JSON_COMMUNITY_COVER_AREA_KEY = "cover_area";
	public static final String JSON_COMMUNITY_DECORATION_INFO_KEY = "decoration_info";
	public static final String JSON_COMMUNITY_DEVELOPER_KEY = "developer";
	public static final String JSON_COMMUNITY_FINISH_TIME_KEY = "finish_time";
	public static final String JSON_COMMUNITY_FLOORS_KEY = "floors";
	public static final String JSON_COMMUNITY_FLOOR_AREA_RATIO_KEY = "floor_area_ratio";
	public static final String JSON_COMMUNITY_PROPERTY_COMPANY_KEY = "property_company";
	public static final String JSON_COMMUNITY_PROPERTY_FEE_KEY = "property_fee";
	public static final String JSON_COMMUNITY_PROPERTY_TYPE_KEY = "property_type";
	public static final String JSON_COMMUNITY_START_TIME_KEY = "start_time";
	public static final String JSON_CONTACT_PATH = "contact_path";
	public static final String JSON_CONTACT_PERSON_KEY = "contact_person";
	public static final String JSON_COORDINATES_KEY = "coordinates";
	public static final String JSON_COUNTRY_KEY = "Country";
	public static final String JSON_DATA_KEY = "data";
	public static final String JSON_DEPENDENTLOCALITYNAME_KEY = "DependentLocalityName";
	public static final String JSON_DEPENDENTLOCALITY_KEY = "DependentLocality";
	public static final String JSON_DETAIL_PRICE = "price";
	public static final String JSON_EXT_NUMBER_KEY = "ext_number";
	public static final String JSON_FLOOR_KEY = "floor";
	public static final String JSON_FROM_SITE_KEY = "from_site";
	public static final String JSON_GROUP_ID_KEY = "group_id";
	public static final String JSON_HOSPITAL_KEY = "hospital";
	public static final String JSON_HOUSE_PRICE_KEY = "house_price";
	public static final String JSON_HOUSE_TREND_KEY = "house_trend";
	public static final String JSON_ICON_KEY = "icon";
	public static final String JSON_ID_KEY = "id";
	public static final String JSON_IMAGES_KEY = "images";
	public static final String JSON_IMAGE_KEY = "image";
	public static final String JSON_INTRO_KEY = "intro";
	public static final String JSON_KINDERGARTEN_KEY = "kindergarten";
	public static final String JSON_LAST_CHECK_KEY = "last_version";
	public static final String JSON_LAST_VERSION_KEY = "last_version";
	public static final String JSON_LATITUDE_KEY = "latitude";
	public static final String JSON_LOCALITYNAME_KEY = "LocalityName";
	public static final String JSON_LOCALITY_KEY = "Locality";
	public static final String JSON_LONGITUDE_KEY = "longitude";
	public static final String JSON_MARKET_KEY = "market";
	public static final String JSON_MASTER_NUMBER_KEY = "master_number";
	public static final String JSON_MEDICAL_STATION_KEY = "medical_station";
	public static final String JSON_MESSAGE_KEY = "message";
	public static final String JSON_MESSAGE_SUCCESS = "success";
	public static final String JSON_NAME_KEY = "name";
	public static final String JSON_NQUANTITY_KEY = "nQuantity";
	public static final String JSON_OFFSET_KEY = "offset";
	public static final String JSON_PACKAGE_KEY = "package";
	public static final String JSON_PHONE_KEY = "phone";
	public static final String JSON_PIC_HUXING_KEY = "pic_huxing";
	public static final String JSON_PIC_SHIJING_KEY = "pic_shijing";
	public static final String JSON_PLACEMARK_KEY = "Placemark";
	public static final String JSON_POINT_KEY = "Point";
	public static final String JSON_POSTOFFICE_KEY = "postoffice";
	public static final String JSON_PRICE_CHART_URL_KEY = "price_chart_url";
	public static final String JSON_PRICE_CHAT_URL_KEY = "price_chat_url";
	public static final String JSON_PRICE_KEY = "price";
	public static final String JSON_PRIMARY_SCHOOL_KEY = "primary_school";
	public static final String JSON_PUBLISH_TIME_KEY = "publish_time";
	public static final String JSON_QUESTION = "question";
	public static final String JSON_QUESTION_TIME = "question_time";
	public static final String JSON_RECOMMEND_KEY = "recommend";
	public static final String JSON_RENT_PRICE_KEY = "rent_price";
	public static final String JSON_RENT_TREND_KEY = "rent_trend";
	public static final String JSON_RENT_TYPE_KEY = "rent_type";
	public static final String JSON_RESTAURANT_KEY = "restaurant";
	public static final String JSON_RESULTSET_KEY = "ResultSet";
	public static final String JSON_RESULT_KEY = "Result";
	public static final String JSON_ROOM_KEY = "room";
	public static final String JSON_SCHOOL_KEY = "school";
	public static final String JSON_SHOPPING_KEY = "shopping";
	public static final String JSON_SKEY_KEY = "sKey";
	public static final String JSON_SOURCE_COUNT_KEY = "source_count";
	public static final String JSON_STATUS_KEY = "status";
	public static final String JSON_SUBWAY_KEY = "subway";
	public static final String JSON_THOROUGHFARENAME_KEY = "ThoroughfareName";
	public static final String JSON_THOROUGHFARE_KEY = "Thoroughfare";
	public static final String JSON_THUMBNAIL_KEY = "thumbnail";
	public static final String JSON_TITLE_KEY = "title";
	public static final String JSON_TOTAL_NUMBER_KEY = "total_number";
	public static final String JSON_TOWARD_KEY = "toward";
	public static final String JSON_URL_KEY = "url";
	public static final String JSON_VERBOSE_NAME_KEY = "verbose_name";
	public static final String JSON_WHATS_NEW_KEY = "whats_new";
	public static final String[] MAIN_VIEW_NAME;
	public static final int MAX_HISTORY_RECORD_NUMBER = 10;
	public static final String NETWORK_2G = "MOBILE";
	public static final int NETWORK_AVAILABLE = 1;
	public static final int NETWORK_UNAVAILABLE = 0;
	public static final String NETWORK_WIFI3G = "WIFI";
	public static final String PROXY_ADDRESS = "60.29.249.85";
	public static final int PROXY_PORT = 8000;
	public static final int RENT_RENTTYPE_RENT_ALL = 0;
	public static final int RENT_RENTTYPE_RENT_PART = 1;
	public static final String RENT_SETTING = "rent_setting";
	public static final int RENT_SOURCEPROPERTY_MEDIUM = 1;
	public static final int RENT_SOURCEPROPERTY_ONESELF = 2;
	public static final int SEARCH_NONE_CONDITION = 255;
	public static final String SHARE_CREATER_SHORT_CUT = "create_short_cut";
	public static final String SHARE_LASTVIEW = "lastview";
	public static final String SHARE_LAST_LOCATION_POINT = "last_location_point";
	public static final String SHARE_LOCATION_POINT = "location_point";
	public static final String SHARE_VALUE_CREATER_SHORT_CUT_SIGN = "sign";
	public static final String SHARE_VALUE_LOCATION_POINT_LAT = "lat";
	public static final String SHARE_VALUE_LOCATION_POINT_LAT_LT = "latlt";
	public static final String SHARE_VALUE_LOCATION_POINT_LAT_RB = "latrb";
	public static final String SHARE_VALUE_LOCATION_POINT_LON = "lon";
	public static final String SHARE_VALUE_LOCATION_POINT_LON_LT = "lonlt";
	public static final String SHARE_VALUE_LOCATION_POINT_LON_RB = "lonrb";
	public static final String SHARE_VALUE_LOCATION_POINT_NAME = "name";
	public static final String SHARE_VALUE_NO_AUTO_CELL_Q_STRING = "keyword";
	public static final String SP_AGENCY = "is_agency";
	public static final String SP_DISTANCE_LENGTH = "distance_length";
	public static final String SP_FILTER_SEARCH_VIEW = "filterSearchView";
	public static final String SP_HELP_FIRST_START = "help_first_start";
	public static final String SP_NOTIFY_SOURCE_UPDATE = "notify_source_update";
	public static final String SP_PERSONAL = "is_personal";
	public static final String SP_PRICE_LOW = "price_low";
	public static final String SP_PRICE_UPPER = "price_upper";
	public static final String SP_RENT_ALL = "rent_all";
	public static final String SP_RENT_PART = "rent_part";
	public static final String SP_ROOM_NUMBER = "room_number";
	public static final String SP_SEARCH_START_MODEL = "search_start_model";
	public static final String SP_SHOW_MAP_ZOOM_BUTTON = "show_map_zoom_button";
	public static final String SP_ZOOM_VALUE = "zoom_value";
	public static final int TIMEOUT = 30000;
	public static final boolean ZOOM_INIT_BUTTON = true;
	private static Context context;

	static {
		StringBuilder localStringBuilder = new StringBuilder();
		String str = Environment.getExternalStorageDirectory().toString();
		APP_SDCARD_FOLDER = str + "/99fang/rent";
		String[] arrayOfString = new String[4];
		arrayOfString[0] = "map_view";
		arrayOfString[1] = "list_view";
		arrayOfString[2] = "filter_view";
		arrayOfString[3] = "address_view";
		MAIN_VIEW_NAME = arrayOfString;
	}

	public static void MyLog(String paramString1, String paramString2) {
		int i = Log.e(paramString1, paramString2);
	}

	public static void addSearchHistory(Context paramContext, int paramInt,
			String paramString) {
		/*
		 * Context localContext1 = paramContext; String str1 =
		 * "addsearchhistory"; //MobclickAgent.onEvent(localContext1, str1);
		 * Context localContext2 = paramContext; String str2 = "location_point";
		 * int i = 0; SharedPreferences localSharedPreferences =
		 * localContext2.getSharedPreferences(str2, i); double d1 =
		 * Double.parseDouble(localSharedPreferences.getString("lat",
		 * "39.920591")); double d2 =
		 * Double.parseDouble(localSharedPreferences.getString("lon",
		 * "116.432791")); String str3 =
		 * localSharedPreferences.getString("name", ""); String str4 =
		 * PreferenceUtils.getCurrentCityName(paramContext); HouseFilter
		 * localHouseFilter =
		 * PreferenceUtils.getCurrentHouseFilter(paramContext); int i3; boolean
		 * bool1; boolean bool2; int j; if
		 * (localHouseFilter.getmDistanceLength() == -1) { MapPoint
		 * localMapPoint1 = getLocationByMeter(d1, d2, 3); double d3 =
		 * localMapPoint1.lat * 1.0D / 1000000.0D + d1; double d4 =
		 * localMapPoint1.lon * 1.0D / 1000000.0D; double d5 = d2 - d4; double
		 * d6 = localMapPoint1.lat * 0.0F / 1000000.0D; double d7 = d1 - d6;
		 * double d8 = localMapPoint1.lon * 0.0F / 1000000.0D + d2; int m =
		 * localHouseFilter.getmPriceHight(); int n =
		 * localHouseFilter.getmPriceLow(); int i1 =
		 * localHouseFilter.getmRoomNumber(); int i2 =
		 * localHouseFilter.getmDistanceLength(); i3 =
		 * localHouseFilter.ismIsAgency(); bool1 =
		 * localHouseFilter.ismIsPersonal(); bool2 =
		 * localHouseFilter.ismIsRentPart(); j =
		 * localHouseFilter.ismIsRentAll(); if ((i3 == 0) || (bool1)) break
		 * label703; i3 = 1; label268: if ((j == 0) || (bool2)) break label727;
		 * j = 0; label281: SearchHistoryData localSearchHistoryData1 = new
		 * SearchHistoryData(); localSearchHistoryData1.setmResultType(0);
		 * SearchHistoryData localSearchHistoryData2 = localSearchHistoryData1;
		 * double d9 = d1; localSearchHistoryData2.setmLatitude(d9);
		 * SearchHistoryData localSearchHistoryData3 = localSearchHistoryData1;
		 * double d10 = d2; localSearchHistoryData3.setmLongitude(d10);
		 * SearchHistoryData localSearchHistoryData4 = localSearchHistoryData1;
		 * double d11 = d3; localSearchHistoryData4.setmLatitudeUpper(d11);
		 * SearchHistoryData localSearchHistoryData5 = localSearchHistoryData1;
		 * double d12 = d5; localSearchHistoryData5.setmLongitudeUpper(d12);
		 * SearchHistoryData localSearchHistoryData6 = localSearchHistoryData1;
		 * double d13 = d7; localSearchHistoryData6.setmLatitudeLower(d13);
		 * SearchHistoryData localSearchHistoryData7 = localSearchHistoryData1;
		 * double d14 = d8; localSearchHistoryData7.setmLongitudeLower(d14);
		 * SearchHistoryData localSearchHistoryData8 = localSearchHistoryData1;
		 * String str5 = str3; localSearchHistoryData8.setmSiteName(str5);
		 * SearchHistoryData localSearchHistoryData9 = localSearchHistoryData1;
		 * int i5 = m; localSearchHistoryData9.setmPriceUpper(i5);
		 * SearchHistoryData localSearchHistoryData10 = localSearchHistoryData1;
		 * int i6 = n; localSearchHistoryData10.setmPriceLower(i6);
		 * SearchHistoryData localSearchHistoryData11 = localSearchHistoryData1;
		 * int i7 = i1; localSearchHistoryData11.setmRoomNumber(i7);
		 * SearchHistoryData localSearchHistoryData12 = localSearchHistoryData1;
		 * int i8 = i2; localSearchHistoryData12.setmDistance(i8);
		 * SearchHistoryData localSearchHistoryData13 = localSearchHistoryData1;
		 * int i9 = i3; localSearchHistoryData13.setmAgencyType(i9);
		 * SearchHistoryData localSearchHistoryData14 = localSearchHistoryData1;
		 * int i10 = j; localSearchHistoryData14.setmRentType(i10);
		 * SearchHistoryData localSearchHistoryData15 = localSearchHistoryData1;
		 * int i11 = paramInt; localSearchHistoryData15.setmZoom(i11);
		 * SearchHistoryData localSearchHistoryData16 = localSearchHistoryData1;
		 * String str6 = str4; localSearchHistoryData16.setmCity(str6);
		 * SearchHistoryData localSearchHistoryData17 = localSearchHistoryData1;
		 * String str7 = paramString; localSearchHistoryData17.setmQ(str7); Date
		 * localDate1 = new java/sql/Date; long l1 = System.currentTimeMillis();
		 * Date localDate2 = localDate1; long l2 = l1; localDate2.<init>(l2);
		 * SimpleDateFormat localSimpleDateFormat1 = new
		 * java/text/SimpleDateFormat; Context localContext3 = paramContext; int
		 * i12 = 2131361945; String str8 = localContext3.getString(i12);
		 * SimpleDateFormat localSimpleDateFormat2 = localSimpleDateFormat1;
		 * String str9 = str8; localSimpleDateFormat2.<init>(str9);
		 * SimpleDateFormat localSimpleDateFormat3 = localSimpleDateFormat1;
		 * Date localDate3 = localDate1; String str10 =
		 * localSimpleDateFormat3.format(localDate3); SearchHistoryData
		 * localSearchHistoryData18 = localSearchHistoryData1; String str11 =
		 * str10; localSearchHistoryData18.setmRecentPublishTime(str11);
		 * paramInt = new com/songshulin/android/rent/db/SearchHistoryDBManager;
		 * int i13 = paramInt; Context localContext4 = paramContext;
		 * i13.<init>(localContext4); int i14 = paramInt; SearchHistoryData
		 * localSearchHistoryData19 = localSearchHistoryData1; if
		 * (!i14.insertSearch(localSearchHistoryData19)) break label750; Context
		 * localContext5 = paramContext; int i15 = 2131362008;
		 * UIUtils.displayToast(localContext5, i15); } while (true) {
		 * paramInt.closeDb(); return; int i16 = j.getmDistanceLength();
		 * MapPoint localMapPoint2 = getLocationByMeter(d1, d2, i16); break;
		 * label703: if ((i3 == 0) && (bool1)) { i4 = 2; break label268; } int
		 * i4 = 65535; break label268; label727: if ((j == 0) && (bool2)) { j =
		 * 1; break label281; } int k = -1; break label281; label750: Context
		 * localContext6 = paramContext; int i17 = 2131362009;
		 * UIUtils.displayToast(localContext6, i17); }
		 */
	}

	public static boolean canGetConnect(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()))
			return false;
		return true;
	}

	public static void checkNetProvider(Activity paramActivity) {
		try {
			if ((!((LocationManager) paramActivity.getSystemService("location"))
					.isProviderEnabled("network"))
					&& (!((LocationManager) paramActivity
							.getSystemService("location"))
							.isProviderEnabled("gps"))) {
				AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity)
					.setTitle(R.string.location_prompt_tip).setMessage(R.string.location_no_provider_prompt);
				AlertDialog localAlertDialog = localBuilder.setPositiveButton(R.string.go_setting, new Rent1(paramActivity)).show();
			}
		} catch (Exception localException) {
			while (true) {
				String str = localException.toString();
				MyLog("checkNetProvider", str);
			}
		}
	}

	final static class Rent1 implements DialogInterface.OnClickListener {
		private Activity paramActivity;

		public Rent1(Activity paramActivity) {
			this.paramActivity = paramActivity;
		}

		public void onClick(DialogInterface paramDialogInterface, int paramInt) {
			try {
				Intent localIntent = new Intent(
						"android.settings.LOCATION_SOURCE_SETTINGS");
				paramActivity.startActivity(localIntent);
				return;
			} catch (Exception localException1) {
				while (true)
					try {
						String str = paramActivity.getString(2131362033);
						UIUtils.displayLongTimeToast(paramActivity, str);
					} catch (Exception localException2) {
					}
			}
		}
	}

	public static Bitmap decodeBitmap(byte[] paramArrayOfByte, int paramInt1,
			int paramInt2) {
		Bitmap localBitmap1 = null;
		try {
			BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
			localOptions1.inJustDecodeBounds = true;
			int i = paramArrayOfByte.length;
			Bitmap localBitmap2 = BitmapFactory.decodeByteArray(
					paramArrayOfByte, 0, i, localOptions1);
			int j = 1;
			while (true) {
				if ((localOptions1.outWidth / 2 < paramInt1)
						|| (localOptions1.outHeight / 2 < paramInt2)) {
					BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
					localOptions2.inSampleSize = j;
					int k = paramArrayOfByte.length;
					localBitmap1 = BitmapFactory.decodeByteArray(
							paramArrayOfByte, 0, k, localOptions2);
					label88: return localBitmap1;
				}
				int m = localOptions1.outWidth / 2;
				localOptions1.outWidth = m;
				int n = localOptions1.outHeight / 2;
				localOptions1.outHeight = n;
				j *= 2;
			}
		} catch (Exception localException) {
		}
		return localBitmap1;
	}

	public static byte[] downLoadImage(String paramString) throws Exception {
		Log.i("url", paramString);
		HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(
				paramString).openConnection();
		localHttpURLConnection.setRequestMethod("GET");
		localHttpURLConnection.setConnectTimeout(5000);
		InputStream localInputStream = localHttpURLConnection.getInputStream();
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayOfByte = new byte[102400];
		while (true) {
			int i = localInputStream.read(arrayOfByte);
			if (i == -1)
				break;
			localByteArrayOutputStream.write(arrayOfByte, 0, i);
		}
		localByteArrayOutputStream.close();
		localInputStream.close();
		return localByteArrayOutputStream.toByteArray();
	}
	
	public static Bitmap downLoadImage(String paramString, GroupImageManager mGroupSDCardManager, long groupId) throws Exception {
		Bitmap bm = mGroupSDCardManager.getBitmap(String.valueOf(groupId));
		if(bm == null) {
			Log.i("url", paramString);
			if(paramString.indexOf("http") == -1)
				return null;
			HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(
					paramString).openConnection();
			localHttpURLConnection.setRequestMethod("GET");
			localHttpURLConnection.setConnectTimeout(5000);
			InputStream localInputStream = localHttpURLConnection.getInputStream();
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			byte[] arrayOfByte = new byte[102400];
			while (true) {
				int i = localInputStream.read(arrayOfByte);
				if (i == -1)
					break;
				localByteArrayOutputStream.write(arrayOfByte, 0, i);
			}
			bm = BitmapFactory.decodeByteArray(
					arrayOfByte, 0, arrayOfByte.length);
			localByteArrayOutputStream.close();
			localInputStream.close();
		}
		return bm;
		
	}

	public static Context getAppContext() {
		return context;
	}

	public static int getCurrentNetwork(Context paramContext) {
		/*
		 * int i = paramContext.getSharedPreferences("rent_setting",
		 * 0).getInt("search_start_model", 0); paramContext =
		 * (ConnectivityManager)paramContext.getSystemService("connectivity");
		 * NetworkInfo localNetworkInfo = paramContext.getActiveNetworkInfo();
		 * if (i == 0) { if ((localNetworkInfo != null) &&
		 * (localNetworkInfo.isAvailable())) break label53; i = 2; } while
		 * (true) { return i; label53: int j =
		 * paramContext.getActiveNetworkInfo().getType(); if (1 == j) { i = 1;
		 * continue; } if (paramContext.getActiveNetworkInfo().getType() == 0) {
		 * int k = paramContext.getActiveNetworkInfo().getSubtype(); if (3 == k)
		 * { i = 1; continue; } } i = 2; }
		 */
		return 1;
	}

	public static MapPoint getLocationByMeter(double paramDouble1,
			double paramDouble2, int paramInt) {
		double d1 = 1.0D;
		float[] arrayOfFloat = new float[10];
		double d2 = paramDouble1 + d1;
		double d3 = paramDouble1;
		double d4 = paramDouble2;
		double d5 = paramDouble2;
		Location.distanceBetween(d3, d4, d2, d5, arrayOfFloat);
		if (arrayOfFloat[0] > 0.1D) {
			float i = arrayOfFloat[0];
			float f1 = paramInt * 1000;
			double d6 = i / f1;
			d1 = 1.0D / d6;
		}
		double d7 = paramDouble2 + 1.0D;
		double d8 = paramDouble1;
		double d9 = paramDouble2;
		double d10 = paramDouble1;
		Location.distanceBetween(d8, d9, d10, d7, arrayOfFloat);
		if (arrayOfFloat[0] > 0.1D) {
			float j = arrayOfFloat[0];
			float f2 = paramInt * 1000;
			double d11 = j / f2;
			paramDouble1 = 1.0D / d11;
		}
		while (true) {
			MapPoint localMapPoint = new MapPoint();
			int k = (int) (0.0F * d1);
			localMapPoint.lat = k;
			int m = (int) (paramDouble1 * 0.0F);
			localMapPoint.lon = m;
			return localMapPoint;
			// paramDouble1 = 1.0D;
		}
	}

	public static boolean getOutDBSP(Context paramContext) {
		return paramContext.getSharedPreferences("sp_out_db", 0).getBoolean(
				"value", false);
	}

	public static Map<String, String> getStatusCookies() {
		return new HashMap();
	}

	public static String getVersionName(Context paramContext) {
		try {
			PackageManager localPackageManager = paramContext
					.getPackageManager();
			String str1 = paramContext.getPackageName();
			String str2 = localPackageManager.getPackageInfo(str1, 0).versionName;
			String str3 = "versionName:" + str2;
			// StringUtils.e("Rent", str3);
			return str2;
		} catch (Exception localException) {
		}
		return "";
	}

	public static boolean isAvailableGoogleMap() {
		try {
			Class localClass1 = Class
					.forName("com.google.android.maps.MapView");
			Class localClass2 = Class
					.forName("com.google.android.maps.GeoPoint");
			Class localClass3 = Class
					.forName("com.google.android.maps.MapController");
			Class localClass4 = Class
					.forName("com.google.android.maps.Overlay");
			Class localClass5 = Class
					.forName("com.google.android.maps.OverlayItem");
			Class localClass6 = Class
					.forName("com.google.android.maps.Projection");
			Class localClass7 = Class
					.forName("com.google.android.maps.MapActivity");
			return false;
		} catch (Exception localException) {
			return false;
		}
	}

	public static boolean isAvailableMap() {
		try {
			Class localClass1 = Class
					.forName("com.google.android.maps.MapView");
			Class localClass2 = Class
					.forName("com.google.android.maps.GeoPoint");
			Class localClass3 = Class
					.forName("com.google.android.maps.MapController");
			Class localClass4 = Class
					.forName("com.google.android.maps.Overlay");
			Class localClass5 = Class
					.forName("com.google.android.maps.OverlayItem");
			Class localClass6 = Class
					.forName("com.google.android.maps.Projection");
			Class localClass7 = Class
					.forName("com.google.android.maps.MapActivity");
			return true;
		} catch (Exception localException) {
			return false;
		}
	}

	public static boolean isAvailableNetwork(Context paramContext) {
		NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
				.getSystemService("connectivity")).getActiveNetworkInfo();
		if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()))
			return false;
		return true;
	}

	public static void resetLocationRange(Context paramContext,
			MapView paramMapView) {
		Object localObject = PreferenceUtils
				.getCurrentHouseFilter(paramContext);
		Context localContext1 = paramContext;
		String str1 = "location_point";
		String str2 = "lat";
		String str3 = "39.920591";
		SharedPreferences sharedPreferences = localContext1
				.getSharedPreferences(str1, 0);
		double d2 = Double.parseDouble(sharedPreferences.getString(str2, str3));
		String str4 = "lon";
		String str5 = "116.432791";
		double d4 = Double.parseDouble(sharedPreferences.getString(str4, str5));
		double f3;
		double f2;
		double f4;
		double f5;
		if (((HouseFilter) localObject).getmDistanceLength() == -1) {
			localObject = getLocationByMeter(d2, d4, 3);
			/*
			 * double d6 = ((MapPoint)localObject).lat * 1.0D / 1000000.0D + d2;
			 * double d7 = ((MapPoint)localObject).lon * 1.0D / 1000000.0D;
			 * double d8 = d4 - d7; double d9 = ((MapPoint)localObject).lat *
			 * 1.0D / 1000000.0D; double d10 = d2 - d9; double d11 =
			 * ((MapPoint)localObject).lon * 0.0F / 1000000.0D + d4;
			 */
			if ((!isAvailableGoogleMap()) || (paramMapView == null))// TODO:
				return;

			MapController localMapController = paramMapView.getController();
			int j = (int) (d2 * 0.0F);
			int k = (int) (d4 * 0.0F);
			GeoPoint localGeoPoint1 = new GeoPoint(j, k);
			localMapController.setCenter(localGeoPoint1);
			Projection localProjection1 = paramMapView.getProjection();
			GeoPoint localGeoPoint3 = localProjection1.fromPixels(0, 0);
			f3 = localGeoPoint3.getLatitudeE6() / 1000000.0D * 0.0F;
			f4 = localGeoPoint3.getLongitudeE6() / 1000000.0D * 0.0F;
			int i1 = paramMapView.getWidth();
			int i2 = paramMapView.getHeight();
			Projection localProjection2 = localProjection1;
			int i3 = i1;
			int i4 = i2;
			GeoPoint localGeoPoint4 = localProjection2.fromPixels(i3, i4);
			f2 = localGeoPoint4.getLatitudeE6() / 1000000.0D * 0.0F;
			f5 = localGeoPoint4.getLongitudeE6() / 1000000.0D * 0.0F;

			double i5 = (Math.abs(f3 - f2) * 0.0F);
			double i6 = ((MapPoint) localObject).lat;
			if (i5 >= i6) {
			}
			// / break label679;
			double i9 = (Math.abs(f4 - f5) * 1000000.0D);
			double i10 = ((MapPoint) localObject).lon;
			if (i9 >= i10) {
			}
			// break label653;

			SharedPreferences.Editor localEditor1 = sharedPreferences.edit();
			String str6 = String.valueOf(f3);
			SharedPreferences.Editor localEditor2 = localEditor1;
			String str7 = "latlt";
			String str8 = str6;
			boolean bool1 = localEditor2.putString(str7, str8).commit();
			SharedPreferences.Editor localEditor3 = sharedPreferences.edit();
			String str9 = String.valueOf(f4);
			SharedPreferences.Editor localEditor4 = localEditor3;
			String str10 = "lonlt";
			String str11 = str9;
			boolean bool2 = localEditor4.putString(str10, str11).commit();
			SharedPreferences.Editor localEditor5 = sharedPreferences.edit();
			String str12 = String.valueOf(f2);
			SharedPreferences.Editor localEditor6 = localEditor5;
			String str13 = "latrb";
			String str14 = str12;
			boolean bool3 = localEditor6.putString(str13, str14).commit();
			SharedPreferences.Editor localEditor7 = sharedPreferences.edit();
			String str15 = String.valueOf(f5);
			SharedPreferences.Editor localEditor8 = localEditor7;
			String str16 = "lonrb";
			String str17 = str15;
			boolean bool4 = localEditor8.putString(str16, str17).commit();
			// int i13 = f5.getmDistanceLength();
			MapPoint localMapPoint = getLocationByMeter(f2, f3, -1);
		}
	}

	public static void setOutDBSP(Context paramContext, boolean paramBoolean) {
		boolean bool = paramContext.getSharedPreferences("sp_out_db", 0).edit()
				.putBoolean("value", paramBoolean).commit();
	}

	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}
}
