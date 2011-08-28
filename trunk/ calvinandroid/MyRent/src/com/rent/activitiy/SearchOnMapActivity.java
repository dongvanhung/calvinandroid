package com.rent.activitiy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.rent.HouseFilter;
import com.rent.PreferenceUtils;
import com.rent.R;
import com.rent.Rent;
import com.rent.SearchHandlerListener;
import com.rent.data.Community;
import com.rent.handler.AddressFromLocationHandler;
import com.rent.handler.AddressToLocationHandler;
import com.rent.handler.GetMarLocationHandler;
import com.rent.handler.SearchHandler;
import com.rent.lib.AppAlert;
import com.rent.listener.AddressFromLocationListener;
import com.rent.listener.AddressToLocationListener;
import com.rent.listener.GetMarsLocationListener;
import com.rent.listener.MyLocationListener;
import com.rent.location.MyLocationManager;
import com.rent.thread.AddressFromLocationThread;
import com.rent.thread.AddressToLocationThread;
import com.rent.thread.GetMarsLocationThread;
import com.rent.thread.SearchThread;
import com.rent.view.MarsLocationOverlay;
import com.rent.view.RentMapOverlayItem;
import com.rent.view.RentMapView;

public class SearchOnMapActivity extends MapActivity implements
		MyLocationListener, SearchHandlerListener, RentMapView.MapMoveListener {

	protected String mAddressName;
	protected ArrayList<Community> mCommList;
	protected Context mContext;
	protected int mDistance;
	private TextView mFiterShow;
	private RelativeLayout mGoFilter;
	protected ImageView mGotoOtherView;
	private boolean mHasLocated = true;
	protected EditText mInputLocation;
	private boolean mIsCanRefresh = false;
	private boolean mIsInit = true;
	private boolean mIsInitLoad = true;
	private boolean mIsMyLocation = false;
	private ImageView mLocate;
	private MapController mMapControl;
	private RentMapView mMapView;
	private MarsLocationOverlay mMyLocation;
	protected SharedPreferences mMyShare;
	private boolean mNeedRefreshOtherView = false;
	private boolean mNotifyIsVisible = true;
	private View mOverlay;
	private List<Overlay> mOverlayList;
	protected String mQ;
	private ImageView mRecordCondition;
	protected Context mSearchContext;
	protected SearchHandler mSearchHandler;
	protected int mSumCommNumber;
	protected TextView mTopNotifyBar;

	private void hideTheNotify() {
		this.mNotifyIsVisible = false;
		new SearchOnMapActivity7().start();
	}

	final class SearchOnMapActivity7 extends Thread {
		private int time = 3;

		public void run() {
			if (this.time > 0) {
				long l = 1000L;
				try {
					sleep(l);
					int i = this.time - 1;
					this.time = i;
				} catch (Exception localException) {
					localException.printStackTrace();
				}
			}
			SearchOnMapActivity.this.runOnUiThread(new Runnable() {
				public void run() {
//					if ((!SearchOnMapActivity.access$500(this.this$1.this$0)) && (SearchOnMapActivity.access$300(this.this$1.this$0)))
						SearchOnMapActivity.this.mTopNotifyBar.setVisibility(8);
//					    boolean bool = SearchOnMapActivity.access$502(this.this$1.this$0, 1);
				}
			});
		}
	}

	private void refreshByAddress(String paramString) {
		if (!Rent.isAvailableNetwork(this)) {
			SearchHandler localSearchHandler1 = this.mSearchHandler;
			updateUI(5, localSearchHandler1);
		} else {
			SearchHandler localSearchHandler2 = this.mSearchHandler;
			updateUI(7, localSearchHandler2);
			SearchOnMapActivity8 local8 = new SearchOnMapActivity8();
			AddressToLocationHandler localAddressToLocationHandler = new AddressToLocationHandler(
					local8);
			new AddressToLocationThread(localAddressToLocationHandler,
					paramString).start();
		}
	}

	final class SearchOnMapActivity8 implements AddressToLocationListener {
		public void locationObtained(double paramDouble1, double paramDouble2,
				boolean paramBoolean) {
			if (paramBoolean != false)
				SearchOnMapActivity.this.refreshByLocation(paramDouble1,
						paramDouble2);
		}
	}

	private void refreshByLocation(double paramDouble1, double paramDouble2) {
		SharedPreferences localSharedPreferences = getSharedPreferences(
				"location_point", 0);
		SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
		String str1 = String.valueOf(paramDouble1);
		boolean bool1 = localEditor1.putString("lat", str1).commit();
		SharedPreferences.Editor localEditor2 = localSharedPreferences.edit();
		String str2 = String.valueOf(paramDouble2);
		boolean bool2 = localEditor2.putString("lon", str2).commit();
		Context localContext1 = this.mContext;
		RentMapView localRentMapView = this.mMapView;
		Rent.resetLocationRange(localContext1, localRentMapView);
		if (!Rent.isAvailableNetwork(this)) {
			SearchHandler localSearchHandler = this.mSearchHandler;
			updateUI(5, localSearchHandler);
		} else {
			SearchOnMapActivity9 local9 = new SearchOnMapActivity9();
			Context localContext2 = this.mContext;
			AddressFromLocationHandler localAddressFromLocationHandler = new AddressFromLocationHandler(
					local9, localContext2);
			Context localContext3 = this.mContext;
			new AddressFromLocationThread(localAddressFromLocationHandler,
					localContext3).start();
		}
	}

	final class SearchOnMapActivity9 implements AddressFromLocationListener {
		public void addressObtained(String paramString) {
			if ((paramString == null) || (paramString.trim().length() == 0))
				SearchOnMapActivity.this.mHasLocated = false;
			SearchHandler localSearchHandler = SearchOnMapActivity.this.mSearchHandler;
			SearchOnMapActivity.this.updateUI(1, localSearchHandler);
			startSearchThread(65535L);
		}
	}

	private void showHouseOverlay() {
		int i = this.mCommList.size();
		this.mOverlayList.clear();
		this.mOverlay.setVisibility(8);
		List localList = this.mMapView.getOverlays();
		MarsLocationOverlay localMarsLocationOverlay = this.mMyLocation;
		boolean bool1 = localList.add(localMarsLocationOverlay);
		Drawable localDrawable = getResources().getDrawable(2130837662);
		Context localContext = this.mContext;
		View localView = this.mOverlay;
		RentMapView localRentMapView = this.mMapView;
		RentMapOverlayItem localRentMapOverlayItem = new RentMapOverlayItem(
				localDrawable, localContext, localView, localRentMapView);
		int j = 0;
		while (j < i) {
			int k = (int) (((Community) this.mCommList.get(j)).mLat * 1000000.0D);
			int m = (int) (((Community) this.mCommList.get(j)).mLon * 1000000.0D);
			GeoPoint localGeoPoint = new GeoPoint(k, m);
			OverlayItem localOverlayItem = new OverlayItem(localGeoPoint, null,
					null);
			((Community) this.mCommList.get(j)).mFlag = 0;
			Community localCommunity = (Community) this.mCommList.get(j);
			localRentMapOverlayItem.addItem(localOverlayItem, localCommunity,
					true, 0);
			j += 1;
		}
		if ((this.mQ != null) && (i > 0)) {
			String str1 = this.mQ;
			String str2 = ((Community) this.mCommList.get(0)).mName;
			if (str1.equals(str2))
				localRentMapOverlayItem.showPopup(0);
		}
		if (i != 0)
			this.mOverlayList.add(localRentMapOverlayItem);
		this.mMapView.invalidate();
	}

	public MapView getMapView() {
		return this.mMapView;
	}

	public void hideOverlay() {
		if (Rent.isAvailableMap())
			this.mOverlay.setVisibility(8);
	}

	public void init() {
		setContentView(R.layout.mapsearch_view);
		RentMapView localRentMapView1 = (RentMapView) findViewById(R.id.recent_map);
		this.mMapView = localRentMapView1;
		this.mMapView.setMoveListener(this);
		this.mContext = this;
		SharedPreferences localSharedPreferences = this.mContext
				.getSharedPreferences("rent_setting", 0);
		if (localSharedPreferences.getBoolean("show_map_zoom_button", true)) {
			this.mMapView.setBuiltInZoomControls(true);
		} else {
			this.mMapView.setBuiltInZoomControls(false);
		}
		
		List localList = this.mMapView.getOverlays();
		this.mOverlayList = localList;
		MapController localMapController1 = this.mMapView.getController();
		this.mMapControl = localMapController1;
		MapController localMapController2 = this.mMapControl;
		int i = localSharedPreferences.getInt("zoom_value", 16);
		int j = localMapController2.setZoom(i);
		View localView1 = LayoutInflater.from(this.mContext).inflate(R.layout.overlay_row, null);
		this.mOverlay = localView1;
		this.mOverlay.setVisibility(8);
		RentMapView localRentMapView2 = this.mMapView;
		View localView2 = this.mOverlay;
		localRentMapView2.addView(localView2);
		TextView localTextView1 = (TextView) findViewById(R.id.recent_view_topnotifybar);
		this.mTopNotifyBar = localTextView1;
		this.mTopNotifyBar.setVisibility(8);
		TextView localTextView2 = this.mTopNotifyBar;
		localTextView2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				// if (SearchOnMapActivity.access$000(this.this$0))
				// {
				TextView localTextView = SearchOnMapActivity.this.mTopNotifyBar;
				String str = SearchOnMapActivity.this.mContext
						.getString(2131361906);
				localTextView.setText(str);
				startSearchThread(65535L);
				// boolean bool =
				// SearchOnMapActivity.access$002(this.this$0, 0);
				// }
			}
		});
		TextView localTextView3 = (TextView) findViewById(R.id.fiter_show);
		this.mFiterShow = localTextView3;
		this.mFiterShow.setTextSize(15.0F);
		ImageView localImageView1 = (ImageView) findViewById(R.id.locate_my);
		this.mLocate = localImageView1;
		ImageView localImageView2 = this.mLocate;
		localImageView2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				SearchOnMapActivity.this.mInputLocation.setText("");
				PreferenceUtils.setShowKeyword(SearchOnMapActivity.this,
						false);
				SearchOnMapActivity.this.mQ = null;
				// boolean bool =
				// SearchOnMapActivity.access$102(this.this$0, 1);
				SearchOnMapActivity.this.refreshByLocation(true);
				// MobclickAgent.onEvent(this.this$0, "locateself");
			}
		});
		ImageView localImageView3 = (ImageView) findViewById(R.id.record_cordition);
		this.mRecordCondition = localImageView3;
		ImageView localImageView4 = this.mRecordCondition;
		localImageView4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				int i = 17;
				if (Rent.isAvailableMap())
					i = SearchOnMapActivity.this.getMapView()
							.getZoomLevel();
				// MobclickAgent.onEvent(this.this$0, "addsearchhistory");
				Context localContext = SearchOnMapActivity.this.mContext;
				String str = SearchOnMapActivity.this.mInputLocation
						.getText().toString();
				Rent.addSearchHistory(localContext, i, str);
			}
		});
		RelativeLayout localRelativeLayout1 = (RelativeLayout) findViewById(R.id.go_filter);
		this.mGoFilter = localRelativeLayout1;
		RelativeLayout localRelativeLayout2 = this.mGoFilter;
		localRelativeLayout2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				// MobclickAgent.onEvent(this.this$0.mContext,
				// "filterclick");
				Intent localIntent = new Intent(SearchOnMapActivity.this,
						FilterActivity.class);
				SearchOnMapActivity.this.startActivity(localIntent);
			}
		});
		ImageView localImageView5 = (ImageView) findViewById(R.id.goto_other_imageview);
		this.mGotoOtherView = localImageView5;
		ImageView localImageView6 = this.mGotoOtherView;
		localImageView6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				SharedPreferences.Editor localEditor = SearchOnMapActivity.this.mContext
						.getSharedPreferences("rent_setting", 0).edit();
				int i = SearchOnMapActivity.this.mMapView.getZoomLevel();
				boolean bool1 = localEditor.putInt("zoom_value", i)
						.commit();
				((SearchActivity)SearchOnMapActivity.this.mSearchContext).changeActivtiy(1);
				/*
				 * if (!SearchOnMapActivity.access$300(this.this$0))
				 * PreferenceUtils
				 * .setRefreshStatus(SearchOnMapActivity.this, 0); else {
				 */
				// boolean bool2 =
				// SearchOnMapActivity.access$102(this.this$0, 0);
				// boolean bool3 =
				// SearchOnMapActivity.access$302(this.this$0, 1);
				// MobclickAgent.onEvent(this.this$0, "viewtype", "map");
				// ((SearchActivity)SearchOnMapActivity.this.mSearchContext).changeActivtiy(1);
				/*
				 * if ((!SearchOnMapActivity.access$400(this.this$0)) &&
				 * (SearchOnMapActivity.access$100(this.this$0))) {
				 * PreferenceUtils.setRefreshStatus(this.this$0, 4);
				 * continue; } if
				 * ((SearchOnMapActivity.access$400(this.this$0)) &&
				 * (SearchOnMapActivity.access$100(this.this$0))) {
				 * PreferenceUtils.setRefreshStatus(this.this$0, 5);
				 * continue; } PreferenceUtils.setRefreshStatus(this.this$0,
				 * 2);
				 */
			}
			// }
		});
		EditText localEditText1 = (EditText) findViewById(R.id.search_edittext);
		this.mInputLocation = localEditText1;
		this.mInputLocation.setCursorVisible(false);
		this.mInputLocation.setInputType(0);
		EditText localEditText2 = this.mInputLocation;
		localEditText2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * if (SearchOnMapActivity.access$200(this.this$0) != null)
				 * { SharedPreferences.Editor localEditor =
				 * SearchOnMapActivity
				 * .this.mContext.getSharedPreferences("rent_setting",
				 * 0).edit(); int i =
				 * SearchOnMapActivity.this.mMapView.getZoomLevel(); boolean
				 * bool = localEditor.putInt("zoom_value", i).commit(); }
				 */
				Context localContext = SearchOnMapActivity.this.mContext;
				Intent localIntent = new Intent(localContext,
						SelectActivity.class);
				SearchOnMapActivity.this.startActivity(localIntent);
			}
		});
		RentMapView localRentMapView3 = this.mMapView;
		MarsLocationOverlay localMarsLocationOverlay = new MarsLocationOverlay(
				this, localRentMapView3);
		this.mMyLocation = localMarsLocationOverlay;
		boolean bool = this.mMyLocation.enableMyLocation();
		SearchHandler localSearchHandler = new SearchHandler(this);
		this.mSearchHandler = localSearchHandler;
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

	public void mapMoved() {
		this.mInputLocation.setText("");
		PreferenceUtils.setShowKeyword(this, false);
		this.mQ = null;
		this.mTopNotifyBar.setVisibility(0);
		TextView localTextView = this.mTopNotifyBar;
		String str1 = this.mContext.getString(R.string.info_is_gethouse);
		localTextView.setText(str1);
		this.mNotifyIsVisible = true;
		this.mNeedRefreshOtherView = true;
		Projection localProjection = this.mMapView.getProjection();
		GeoPoint localGeoPoint1 = localProjection.fromPixels(0, 0);
		double d1 = localGeoPoint1.getLatitudeE6() / 1000000.0D * 1.0D;
		double d2 = localGeoPoint1.getLongitudeE6() / 1000000.0D * 1.0D;
		int i = this.mMapView.getWidth();
		int j = this.mMapView.getHeight();
		GeoPoint localGeoPoint2 = localProjection.fromPixels(i, j);
		double d3 = localGeoPoint2.getLatitudeE6() / 1000000.0D * 1.0D;
		double d4 = localGeoPoint2.getLongitudeE6() / 1000000.0D * 1.0D;
		String str2 = d1 + "";
		int k = Log.e("ltlat1=", str2);
		String str3 = d2 + "";
		int m = Log.e("ltlon1=", str3);
		String str4 = d3 + "";
		int n = Log.e("rblat1=", str4);
		String str5 = d4 + "";
		int i1 = Log.e("rblon1=", str5);
		double d5 = (d1 + d3) / 2.0D;
		double d6 = (d4 + d2) / 2.0D;
		SharedPreferences localSharedPreferences = this.mContext
				.getSharedPreferences("location_point", 0);
		SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
		String str6 = String.valueOf(d5);
		boolean bool1 = localEditor1.putString("lat", str6).commit();
		SharedPreferences.Editor localEditor2 = localSharedPreferences.edit();
		String str7 = String.valueOf(d6);
		boolean bool2 = localEditor2.putString("lon", str7).commit();
		refreshByLocation(d5, d6);
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		// MobclickAgent.onError(this);
		init();
		refreshByLocation(false);
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)
				&& (this.mMapView != null)) {
			SharedPreferences.Editor localEditor = this.mContext
					.getSharedPreferences("rent_setting", 0).edit();
			int i = this.mMapView.getZoomLevel();
			boolean bool = localEditor.putInt("zoom_value", i).commit();
		}
		return false;
	}

	public void onLocationChanged(double paramDouble1, double paramDouble2,
			boolean paramBoolean) {
		this.mHasLocated = true;
		this.mIsMyLocation = true;
		Context localContext = getApplicationContext();
		SearchOnMapActivity10 local10 = new SearchOnMapActivity10();
		GetMarLocationHandler localGetMarLocationHandler = new GetMarLocationHandler(
				local10);
		String str = Rent.getVersionName(getApplicationContext());
		double d1 = paramDouble1;
		double d2 = paramDouble2;
		new GetMarsLocationThread(localContext, localGetMarLocationHandler, d1,
				d2, str, "rent").start();
	}

	final class SearchOnMapActivity101 implements Runnable {
		private double d1;
		private double d2;

		public SearchOnMapActivity101(double d1, double d2) {
			this.d1 = d1;
			this.d2 = d2;
		}

		public void run() {
			PreferenceUtils.saveMarsLocation(SearchOnMapActivity.this, d1, d2);
			SearchOnMapActivity.this.refreshByLocation(d1, d2);
			boolean bool = SearchOnMapActivity.this.mMyLocation
					.enableMyLocation();
		}
	}

	final class SearchOnMapActivity10 implements GetMarsLocationListener {
		public void marsLocationObtained(double paramDouble1,
				double paramDouble2, boolean paramBoolean) {
			if (paramBoolean != false) {
				double d1 = paramDouble2;
				double d2 = paramDouble1;
				SearchOnMapActivity101 local1 = new SearchOnMapActivity101(d1,
						d2);
				SearchOnMapActivity.this.runOnUiThread(local1);
			}
		}
	}

	protected void onPause() {
		super.onPause();
		MyLocationManager.getInstance(getApplicationContext())
				.stopLocationManager();
		// MobclickAgent.onPause(this);
	}

	protected void onResume() {
		super.onResume();
		SharedPreferences sharedpreferences = null;
		MapController mapcontroller = null;
		int i = 0;
		int j = 0;
		if (PreferenceUtils.isShowZoom(this)) {
			this.mMapView.setBuiltInZoomControls(false);
		} else {
			this.mMapView.setBuiltInZoomControls(true);
		}
		if (this.mIsInit) {
			this.mInputLocation.setText("");
			this.mQ = null;
			mIsInit = false;
			// MobclickAgent.onResume(this);
		} else {
			updateFilterBar();
			this.mIsMyLocation = false;
			switch (PreferenceUtils.getRefreshStatus(this)) {
			case 2:
				if (mMapControl != null) {
					sharedpreferences = mContext.getSharedPreferences(
							"rent_setting", 0);
					mapcontroller = mMapControl;
					i = sharedpreferences.getInt("zoom_value", 16);
					j = mapcontroller.setZoom(i);
				}
			default:
			case 0:
				refreshByLocation(true);
			case 1:
				refreshByLocation(false);
			case 3:
				if (!mHasLocated) {
					PreferenceUtils.setInit(this);
					mHasLocated = true;
					mIsMyLocation = false;
					MyLocationManager.getInstance(getApplicationContext())
							.stopLocation();
				}
				String s2 = PreferenceUtils.getCurrentKeyWord(this);
				mQ = s2;
				EditText edittext1 = mInputLocation;
				String s3 = PreferenceUtils.getCurrentKeyWord(this);
				edittext1.setText(s3);
				StringBuilder stringbuilder = new StringBuilder();
				String s4 = PreferenceUtils.getCurrentCityName(this);
				StringBuilder stringbuilder1 = stringbuilder.append(s4);
				String s5 = PreferenceUtils.getCurrentKeyWord(this);
				String s6 = stringbuilder1.append(s5).toString();
				refreshByAddress(s6);
				mNeedRefreshOtherView = true;
			case 4:
				refreshByLocation(false);
			case 5:
				mIsMyLocation = true;
				refreshByLocation(false);
			}
		}
		if (!PreferenceUtils.getShowKeyword(this)) {
			this.mInputLocation.setText("");
			this.mQ = null;
		} else {
			EditText edittext = mInputLocation;
			String s = PreferenceUtils.getCurrentKeyWord(this);
			edittext.setText(s);
			String s1 = PreferenceUtils.getCurrentKeyWord(this);
			mQ = s1;
		}
	}

	public void postSearch(boolean paramBoolean,
			SearchHandler paramSearchHandler) {
		this.mSearchHandler = paramSearchHandler;
		if (this.mSearchHandler == null)
			return;
		if (true == paramBoolean) {
			SearchHandler localSearchHandler1 = this.mSearchHandler;
			updateUI(2, localSearchHandler1);
		} else {
			SearchHandler localSearchHandler2 = this.mSearchHandler;
			updateUI(3, localSearchHandler2);
		}
		String str1 = getString(R.string.confirm);
		String str2 = getString(R.string.cancel);
		AppAlert.actionAlerts(this, str1, str2);
	}

	public void refreshByLocation(boolean paramBoolean) {
		int i = 0;
		if (!Rent.isAvailableNetwork(this)) {
			SearchHandler localSearchHandler1 = this.mSearchHandler;
			updateUI(5, localSearchHandler1);
		} else {
			try {
				this.mIsMyLocation = false;
				if (Rent.isAvailableMap()) {
					int k = getSharedPreferences("rent_setting", 0).getInt(
							"zoom_value", 16);
					int m = getMapView().getController().setZoom(k);
				}
				if (!paramBoolean) {
					String str1 = PreferenceUtils.getCurrentCityName(this);
					str1 = "shanghai";
					if ((str1 == null) || (str1.length() == 0)) {
						i = 1;
					}
				}
				if (i == 0) {
					SearchHandler localSearchHandler4 = this.mSearchHandler;
					updateUI(1, localSearchHandler4);
					String str3 = getSharedPreferences("location_point", 0)
							.getString("name", "");
					this.mAddressName = str3;
					startSearchThread(65535L);
				}
				Rent.checkNetProvider(getParent());
				SearchHandler localSearchHandler2 = this.mSearchHandler;
				updateUI(0, localSearchHandler2);
				this.mHasLocated = false;
				MyLocationManager.getInstance(getApplicationContext())
						.registerListener(this);
			} catch (Exception localException) {
				String str2 = localException.toString();
				int n = Log.e("MainActivity.getCurrentGeoPoint()", str2);
			}
		}
	}

	public void setSearchContext(Context paramContext) {
		this.mSearchContext = paramContext;
	}

	public void startSearchThread(long paramLong)
	  {
	    updateFilterBar();
	    if (!Rent.canGetConnect(this))
	    {
	      SearchHandler localSearchHandler1 = this.mSearchHandler;
	      SearchOnMapActivity localSearchOnMapActivity1 = this;
	      int i = 5;
	      SearchHandler localSearchHandler2 = localSearchHandler1;
	      localSearchOnMapActivity1.updateUI(i, localSearchHandler2);
	    } else {
	      SearchOnMapActivity localSearchOnMapActivity2 = this;
	      String str1 = "location_point";
	      int j = 0;
	      SharedPreferences localSharedPreferences = localSearchOnMapActivity2.getSharedPreferences(str1, j);
	      double d1 = Double.parseDouble(localSharedPreferences.getString("latlt", "39.920591"));
	      double d2 = Double.parseDouble(localSharedPreferences.getString("lonlt", "116.432791"));
	      double d3 = Double.parseDouble(localSharedPreferences.getString("latrb", "39.920591"));
	      double d4 = Double.parseDouble(localSharedPreferences.getString("lonrb", "116.432791"));
	      String str2 = PreferenceUtils.getCurrentCityName(this);
	      PreferenceUtils.setRefreshStatus(this.mContext, 2);
	      SearchHandler localSearchHandler3 = new SearchHandler(SearchOnMapActivity.this);
	      SearchHandler localSearchHandler4 = localSearchHandler3;
	      SearchOnMapActivity localSearchOnMapActivity3 = this;
	      HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this);
	      String str3 = this.mQ;
	      long l = paramLong;
	      SearchOnMapActivity localSearchOnMapActivity4 = this;
	      new SearchThread(localSearchHandler3, d3, d4, d1, d2, str2, 0, localHouseFilter, l, localSearchOnMapActivity4, false, str3).start();
	    }
	  }

	public void updateFilterBar() {
		HouseFilter localHouseFilter = PreferenceUtils
				.getCurrentHouseFilter(this);
		int i = localHouseFilter.getmDistanceLength();
		this.mDistance = i;
		TextView localTextView = this.mFiterShow;
		Context localContext = this.mContext;
		String str = localHouseFilter.toDisplayedString(localContext);
		localTextView.setText(str);
	}

	public void updateUI(int paramInt, SearchHandler paramSearchHandler) {
		this.mIsCanRefresh = false;
		this.mTopNotifyBar.setVisibility(0);
		if (this.mIsInitLoad)
			this.mIsInitLoad = false;
		switch (paramInt) {
		default:
			return;
		case 0:
			TextView localTextView1 = this.mTopNotifyBar;
			String str1 = this.mContext.getString(R.string.info_is_location);
			localTextView1.setText(str1);
		case 1:
			TextView localTextView2 = this.mTopNotifyBar;
			String str2 = this.mContext.getString(R.string.info_is_gethouse);
			localTextView2.setText(str2);
			SharedPreferences localSharedPreferences = this.mContext
					.getSharedPreferences("location_point", 0);
			double d1 = Double.parseDouble(localSharedPreferences.getString(
					"lat", "39.920591"));
			double d2 = Double.parseDouble(localSharedPreferences.getString(
					"lon", "116.432791"));
			int i = (int) (d1 * 1000000.0D);
			int j = (int) (d2 * 0.0F);
			GeoPoint localGeoPoint = new GeoPoint(i, j);
			this.mMapControl.setCenter(localGeoPoint);
		case 2:
			if (paramSearchHandler == null) {
				TextView localTextView3 = this.mTopNotifyBar;
				String str3 = this.mContext.getString(R.string.info_success_location_fail_house);
				localTextView3.setText(str3);
			} else {
				String str4 = this.mContext.getSharedPreferences("location_point",
						0).getString("name", "");
				ArrayList localArrayList = paramSearchHandler.getCommList();
				this.mCommList = localArrayList;
				int k = paramSearchHandler.mTotalNumber;
				this.mSumCommNumber = k;
				this.mSearchHandler = paramSearchHandler;
				TextView localTextView4 = this.mTopNotifyBar;
				String str5 = this.mContext.getString(R.string.topnotifybar_info_search_view2);
				Object[] arrayOfObject = new Object[2];
				arrayOfObject[0] = str4;
				Integer localInteger = Integer.valueOf(this.mSumCommNumber);
				arrayOfObject[1] = localInteger;
				String str6 = String.format(str5, arrayOfObject);
				localTextView4.setText(str6);
				showHouseOverlay();
				hideTheNotify();
			}
		case 3:
			TextView localTextView5 = this.mTopNotifyBar;
			String str7 = this.mContext.getString(R.string.no_cell_in_the_location);
			localTextView5.setText(str7);
			this.mIsCanRefresh = true;
		case 4:
			TextView localTextView6 = this.mTopNotifyBar;
			String str8 = this.mContext.getString(R.string.cannot_get_location);
			localTextView6.setText(str8);
		case 5:
			TextView localTextView7 = this.mTopNotifyBar;
			String str9 = this.mContext.getString(R.string.unvaliable_network);
			localTextView7.setText(str9);
			hideTheNotify();
		case 6:
			TextView localTextView8 = this.mTopNotifyBar;
			String str10 = this.mContext.getString(R.string.error_can_not_find_location);
			localTextView8.setText(str10);
			hideTheNotify();
		case 7:
			TextView localTextView9 = this.mTopNotifyBar;
			String str11 = this.mContext.getString(R.string.info_is_gethouse);
			localTextView9.setText(str11);
		}
	}
}
