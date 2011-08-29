package com.rent.activitiy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.HouseFilter;
import com.rent.PreferenceUtils;
import com.rent.R;
import com.rent.Rent;
import com.rent.SearchHandlerListener;
import com.rent.adapter.ListSearchAdapter;
import com.rent.data.Community;
import com.rent.handler.AddressFromLocationHandler;
import com.rent.handler.AddressToLocationHandler;
import com.rent.handler.GetMarLocationHandler;
import com.rent.handler.SearchHandler;
import com.rent.listener.AddressFromLocationListener;
import com.rent.listener.AddressToLocationListener;
import com.rent.listener.GetMarsLocationListener;
import com.rent.listener.MyLocationListener;
import com.rent.location.MyLocationManager;
import com.rent.thread.AddressFromLocationThread;
import com.rent.thread.AddressToLocationThread;
import com.rent.thread.GetMarsLocationThread;
import com.rent.thread.SearchThread;

public class SearchInListActivity extends Activity implements
		SearchHandlerListener, MyLocationListener {

	protected String mAddressName;
	protected ArrayList<Community> mCommList;
	protected int mCommunityNumber;
	protected Context mContext = this;
	private TextView mFiterShow;
	private RelativeLayout mGoFilter;
	protected ImageView mGotoOtherView;
	private boolean mHasLocated = true;
	protected EditText mInputLocation;
	private boolean mIsCanRefresh = false;
	private boolean mIsEnd = false;
	private boolean mIsInit = true;
	private boolean mIsInitLoad = false;
	private boolean mIsLoading = true;
	private boolean mIsMyLocation = false;
	private SearchHandler mListSearchHandler;
	private ListView mListView;
	private boolean mLoadFailed = false;
	private ImageView mLocate;
	private boolean mNeedRefreshOtherView = false;
	private int mOffset = 0;
	protected String mQ;
	private ImageView mRecordCondition;
	private ListSearchAdapter mSearchAdapter;
	protected Context mSearchContext;
	protected TextView mTopNotifyBar;

	private void clearList() {
		this.mListSearchHandler.mOffset = 0;
		this.mListSearchHandler.mTotalNumber = 0;
		this.mListSearchHandler.getCommList().clear();
		this.mSearchAdapter.setIsEnd(true);
		ListSearchAdapter localListSearchAdapter = this.mSearchAdapter;
		ArrayList localArrayList = this.mListSearchHandler.getCommList();
		localListSearchAdapter.refresh(localArrayList);
		this.mSearchAdapter.notifyDataSetChanged();
		this.mOffset = 0;
	}

	private void refreshByAddress(String paramString) {
		if (!Rent.isAvailableNetwork(this)) {
			updateUI(5);
		} else {
			updateUI(1);
			SearchInListActivity9 local9 = new SearchInListActivity9();
			AddressToLocationHandler localAddressToLocationHandler = new AddressToLocationHandler(
					local9);
			new AddressToLocationThread(localAddressToLocationHandler,
					paramString).start();
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
		Rent.resetLocationRange(this.mContext, null);
		if (!Rent.isAvailableNetwork(this)) {
			updateUI(5);
		} else {
			SearchInListActivity10 local10 = new SearchInListActivity10();
			Context localContext1 = this.mContext;
			AddressFromLocationHandler localAddressFromLocationHandler = new AddressFromLocationHandler(
					local10, localContext1);
			Context localContext2 = this.mContext;
			new AddressFromLocationThread(localAddressFromLocationHandler,
					localContext2).start();
		}
	}

	final class SearchInListActivity10 implements AddressFromLocationListener {
		public void addressObtained(String paramString) {
			mAddressName = paramString;
			updateUI(1);
			
//			 SearchInListActivity.access$1000(this.this$0);
			 SearchInListActivity.this.mListView.setSelection(0);
//			 * SearchInListActivity.access$400(this.this$0);
			 
		}
	}

	private void startSearchThread() {
		int i = 1;
		this.mIsLoading = true;
		updateFilterBar();
		SharedPreferences localSharedPreferences = this.mContext
				.getSharedPreferences("location_point", 0);
		double d1 = Double.parseDouble(localSharedPreferences.getString(
				"latlt", "39.920591"));
		double d2 = Double.parseDouble(localSharedPreferences.getString(
				"lonlt", "116.432791"));
		double d3 = Double.parseDouble(localSharedPreferences.getString(
				"latrb", "39.920591"));
		double d4 = Double.parseDouble(localSharedPreferences.getString(
				"lonrb", "116.432791"));
		String str1 = PreferenceUtils.getCurrentCityName(this);
		PreferenceUtils.setRefreshStatus(this.mContext, 2);
		HouseFilter localHouseFilter = PreferenceUtils
				.getCurrentHouseFilter(this);
		SearchHandler localSearchHandler = this.mListSearchHandler;
		int j = this.mListSearchHandler.mOffset;
		String str2 = this.mQ;
		new SearchThread(localSearchHandler, d3, d4, d1, d2, str1, j,
				localHouseFilter, 65535L, null, false, str2).start();
	}

	public void init() {
		setContentView(R.layout.listsearch_view);
		Context localContext = this.mContext;
		ListSearchAdapter localListSearchAdapter1 = new ListSearchAdapter(
				localContext, this);
		this.mSearchAdapter = localListSearchAdapter1;
		ListView localListView1 = (ListView) findViewById(R.id.listsearch_list);
		this.mListView = localListView1;
		ListView localListView2 = this.mListView;
		ListSearchAdapter localListSearchAdapter2 = this.mSearchAdapter;
		localListView2.setAdapter(localListSearchAdapter2);
		ListView localListView3 = this.mListView;
		localListView3.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> paramAdapterView,
					View paramView, int paramInt, long paramLong) {
				if (mCommList != null && mCommList.size() > paramInt) {
					Intent localIntent1 = new Intent(mContext,
							ResultActivity.class);
					Bundle localBundle = new Bundle();
					localBundle.putInt("searchformat", 0);
					String str1 = ((Community) mCommList.get(paramInt)).mName;
					localBundle.putString("keyword", str1);
					long l = ((Community) mCommList.get(paramInt)).mGroupId;
					localBundle.putLong("group_id", l);
					String str2 = ((Community) mCommList.get(paramInt)).mCity;
					localBundle.putString("city", str2);
					int j = ((Community) mCommList.get(paramInt)).mPrice;
					localBundle.putInt("price", j);
					int k = ((Community) mCommList.get(paramInt)).mSourceCount;
					localBundle.putInt("sourcecoount", k);
					double d1 = ((Community) mCommList.get(paramInt)).mLat;
					localBundle.putDouble("latitude", d1);
					double d2 = ((Community) mCommList.get(paramInt)).mLon;
					localBundle.putDouble("longitude", d2);
					Intent localIntent2 = localIntent1.putExtras(localBundle);
					mContext.startActivity(localIntent1);
				}
			}
		});
		ListView localListView4 = this.mListView;
		localListView4.setOnScrollListener(new OnScrollListener() {
			public void onScroll(AbsListView paramAbsListView, int paramInt1,
					int paramInt2, int paramInt3) {
				/*if (!SearchInListActivity.access$100(this.this$0))
				      boolean bool1 = SearchInListActivity.access$202(this.this$0, 0);
				    if ((!SearchInListActivity.access$300(this.this$0)) && (paramInt3 > 0) && (!SearchInListActivity.access$200(this.this$0)))
				    {*/
				if(paramInt3 > 0) {
					Log.i("scrollItem-param1", String.valueOf(paramInt1));
					Log.i("scrollItem-param2", String.valueOf(paramInt2));
					Log.i("scrollItem-param3", String.valueOf(paramInt3));
				      int i = paramInt1 + paramInt2;
				      if (paramInt3 == i)
				      {
				        SearchInListActivity.this.mTopNotifyBar.setText(R.string.is_loading);
//				        SearchInListActivity.access$400(this.this$0);
				      }
				    }/* else {
				      boolean bool2 = SearchInListActivity.access$102(this.this$0, 1);
				    }*/
			}

			public void onScrollStateChanged(AbsListView paramAbsListView,
					int paramInt) {
			}
		});
		// SearchHandlerListener
		SearchHandler localSearchHandler = new SearchHandler(this);
		this.mListSearchHandler = localSearchHandler;
		TextView localTextView1 = (TextView) findViewById(R.id.recent_view_topnotifybar);
		this.mTopNotifyBar = localTextView1;
		this.mTopNotifyBar.setVisibility(8);
		TextView localTextView2 = this.mTopNotifyBar;
		localTextView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * if (SearchInListActivity.access$500(this.this$0)) {
				 */
				TextView localTextView = mTopNotifyBar;
				String str = mContext.getString(R.string.info_is_gethouse);
				localTextView.setText(str);
				// SearchInListActivity.access$400(this.this$0);
				// boolean bool = SearchInListActivity.access$502(this.this$0,
				// 0);
				// }
			}
		});
		TextView localTextView3 = (TextView) findViewById(R.id.fiter_show);
		this.mFiterShow = localTextView3;
		this.mFiterShow.setTextSize(15.0F);
		ImageView localImageView1 = (ImageView) findViewById(R.id.locate_my);
		this.mLocate = localImageView1;
		ImageView localImageView2 = this.mLocate;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				mInputLocation.setText("");
				mQ = null;
				// boolean bool = SearchInListActivity.access$602(this.this$0,
				// 1);
				refreshByLocation(true);
				// MobclickAgent.onEvent(this.this$0, "locateself");
			}
		});
		ImageView localImageView3 = (ImageView) findViewById(R.id.record_cordition);
		this.mRecordCondition = localImageView3;
		ImageView localImageView4 = this.mRecordCondition;
		localImageView4.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				// MobclickAgent.onEvent(this.this$0, "addsearchhistory");
				SharedPreferences localSharedPreferences = mContext
						.getSharedPreferences("rent_setting", 0);
				int i = localSharedPreferences.getInt("zoom_value", 16);
				String str = mInputLocation.getText().toString();
				Rent.addSearchHistory(mContext, i, str);
			}
		});
		RelativeLayout localRelativeLayout1 = (RelativeLayout) findViewById(R.id.go_filter);
		this.mGoFilter = localRelativeLayout1;
		RelativeLayout localRelativeLayout2 = this.mGoFilter;
		localRelativeLayout2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				// MobclickAgent.onEvent(this.this$0.mContext, "filterclick");
				Intent localIntent = new Intent(mContext, FilterActivity.class);
				startActivity(localIntent);
			}
		});
		ImageView localImageView5 = (ImageView) findViewById(R.id.goto_other_imageview);
		this.mGotoOtherView = localImageView5;
		if (Rent.isAvailableGoogleMap()) {
			this.mGotoOtherView.setImageResource(R.drawable.map_icon);
			ImageView localImageView6 = this.mGotoOtherView;
			localImageView6.setOnClickListener(new OnClickListener() {
				public void onClick(View paramView) {
					if (mContext != null) {
						PreferenceUtils.setRefreshStatus(mContext, 0);
						((SearchActivity) mSearchContext).changeActivtiy(0);
					} else {
						// boolean bool1 =
						// SearchInListActivity.access$602(this.this$0, 0);
						// boolean bool2 =
						// SearchInListActivity.access$702(this.this$0, 1);
						// MobclickAgent.onEvent(this.this$0, "viewtype",
						// "map");
						
						
//						 if ((!SearchInListActivity.access$800(this.this$0))
//						 && (SearchInListActivity.access$600(this.this$0))) {
//						 PreferenceUtils.setRefreshStatus(SearchInListActivity.this, 4);
//						 * continue; } if
//						 * ((SearchInListActivity.access$800(this.this$0)) &&
//						 * (SearchInListActivity.access$600(this.this$0))) {
//						 PreferenceUtils.setRefreshStatus(SearchInListActivity.this, 5);
//						 * continue; }
						 
						//TODO: IF Elseif ELSE
						PreferenceUtils.setRefreshStatus(mContext, 2);
					}
				}
			});
			EditText localEditText1 = (EditText) findViewById(R.id.search_edittext);
			this.mInputLocation = localEditText1;
			this.mInputLocation.setCursorVisible(false);
			this.mInputLocation.setInputType(0);
			EditText localEditText2 = this.mInputLocation;
			localEditText2.setOnClickListener(new OnClickListener() {
				public void onClick(View paramView) {
					Intent localIntent = new Intent(mContext,
							SelectActivity.class);
					startActivity(localIntent);
				}
			});
			if (Rent.isAvailableNetwork(this))
				updateUI(5);
		} else {
			this.mGotoOtherView.setImageResource(R.drawable.go_list);
		}
		updateUI(1);
		String str = getSharedPreferences("location_point", 0).getString(
				"name", "");
		this.mAddressName = str;
		startSearchThread();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		// MobclickAgent.onError(this);
		init();
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		return false;
	}

	public void onLocationChanged(double paramDouble1, double paramDouble2,
			boolean paramBoolean) {
		if (!this.mHasLocated) {
			this.mHasLocated = true;
			this.mIsMyLocation = true;
			this.mInputLocation.setText("");
			this.mQ = null;
			Context localContext = getApplicationContext();
			SearchInListActivity11 local11 = new SearchInListActivity11();
			GetMarLocationHandler localGetMarLocationHandler = new GetMarLocationHandler(
					local11);
			String str = Rent.getVersionName(getApplicationContext());
			double d1 = paramDouble1;
			double d2 = paramDouble2;
			new GetMarsLocationThread(localContext, localGetMarLocationHandler,
					d1, d2, str, "rent").start();
		}
	}

	final class SearchInListActivity11 implements GetMarsLocationListener {
		public void marsLocationObtained(double paramDouble1,
				double paramDouble2, boolean paramBoolean) {
			if (paramBoolean != false) {
				final double d1 = paramDouble1;
				final double d2 = paramDouble2;
				SearchInListActivity.this.runOnUiThread(new Runnable() {
					public void run() {
						SearchInListActivity.this.refreshByLocation(d1, d2);
					}
				});
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
		int i;
		if (!this.mIsInit) {
			if (PreferenceUtils.getShowKeyword(this)) {
				EditText localEditText1 = this.mInputLocation;
				String str1 = PreferenceUtils.getCurrentKeyWord(this);
				localEditText1.setText(str1);
				String str2 = PreferenceUtils.getCurrentKeyWord(this);
				this.mQ = str2;
				i = this.mOffset;
				this.mOffset = 0;
				if (!this.mIsInit) {
					updateFilterBar();
					this.mIsMyLocation = false;
					switch (PreferenceUtils.getRefreshStatus(this)) {
					default:
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					}
				}
			} else {
				this.mIsInit = false;
				super.onResume();
				// MobclickAgent.onResume(this);
				this.mInputLocation.setText("");
				this.mQ = null;
				this.mInputLocation.setText("");
				this.mQ = null;
				refreshByLocation(true);
				this.mNeedRefreshOtherView = true;
				if (!this.mHasLocated) {
					PreferenceUtils.setInit(this);
					this.mHasLocated = true;
					this.mIsMyLocation = false;
					MyLocationManager.getInstance(getApplicationContext())
							.stopLocation();
				}
				String str3 = PreferenceUtils.getCurrentKeyWord(this);
				this.mQ = str3;
				EditText localEditText2 = this.mInputLocation;
				String str4 = PreferenceUtils.getCurrentKeyWord(this);
				localEditText2.setText(str4);
				StringBuilder localStringBuilder1 = new StringBuilder();
				String str5 = PreferenceUtils.getCurrentCityName(this);
				StringBuilder localStringBuilder2 = localStringBuilder1
						.append(str5);
				String str6 = PreferenceUtils.getCurrentKeyWord(this);
				String str7 = str6;
				refreshByAddress(str7);
				this.mNeedRefreshOtherView = true;
				refreshByLocation(false);
				this.mIsMyLocation = true;
				refreshByLocation(false);
			}
		}
	}

	public void postSearch(boolean paramBoolean,
			SearchHandler paramSearchHandler) {
		if (true == paramBoolean) {
			int i = this.mListSearchHandler.mTotalNumber;
			this.mCommunityNumber = i;
			int j = paramSearchHandler.mOffset;
			int k = this.mListSearchHandler.getCommList().size();
//			if (j == k) {
				this.mSearchAdapter.setIsEnd(true);
				this.mIsEnd = true;
				this.mIsLoading = false;
				TextView localTextView = this.mTopNotifyBar;
				String str1 = this.mContext
						.getString(R.string.topnotifybar_info_search_view2);
				Object[] arrayOfObject = new Object[2];
				String str2 = this.mAddressName;
				arrayOfObject[0] = str2;
				Integer localInteger = Integer.valueOf(this.mCommunityNumber);
				arrayOfObject[1] = localInteger;
				String str3 = String.format(str1, arrayOfObject);
				localTextView.setText(str3);
				ListSearchAdapter localListSearchAdapter = this.mSearchAdapter;
				ArrayList localArrayList = this.mListSearchHandler.getCommList();
				localListSearchAdapter.refresh(localArrayList);
				this.mSearchAdapter.notifyDataSetChanged();
//			}
		} else {
			int m = this.mListSearchHandler.getCommList().size();
			this.mOffset = m;
			this.mSearchAdapter.setIsEnd(false);
			this.mIsEnd = false;
			this.mSearchAdapter.setIsEnd(false);
			this.mIsEnd = false;
			this.mTopNotifyBar.setText(R.string.server_busy);
			this.mLoadFailed = true;
		}
	}

	public void refreshByLocation(boolean paramBoolean) {
		if (!Rent.isAvailableNetwork(this)) {
			updateUI(5);
		} else {
			try {
				if (!paramBoolean) {
					String str1 = PreferenceUtils.getCurrentCityName(this);
					if ((str1 == null) || (str1.length() == 0)) {
						
					} else {
						updateUI(1);
						int j = 0;
					}
				} else {
					if(!this.mIsMyLocation) {
						updateUI(1);
						String str3 = getSharedPreferences("location_point", 0).getString(
								"name", "");
						this.mAddressName = str3;
						clearList();
					} else {
						Rent.checkNetProvider(getParent());
						updateUI(0);
						MyLocationManager.getInstance(getApplicationContext())
								.registerListener(this);
						this.mHasLocated = false;
						clearList();
					}
					
				}
			} catch (Exception localException) {
				String str2 = localException.toString();
				int k = Log.e("MainActivity.getCurrentGeoPoint()", str2);
			}
			this.mListView.setSelection(0);
			startSearchThread();
		}
	}

	public void setSearchContext(Context paramContext) {
		this.mSearchContext = paramContext;
	}

	public void updateFilterBar() {
		HouseFilter localHouseFilter = PreferenceUtils
				.getCurrentHouseFilter(this);
		TextView localTextView = this.mFiterShow;
		Context localContext = this.mContext;
		String str = localHouseFilter.toDisplayedString(localContext);
		localTextView.setText(str);
	}

	public void updateUI(int paramInt) {
		this.mIsCanRefresh = false;
		this.mTopNotifyBar.setVisibility(0);
		if (this.mIsInitLoad)
			this.mIsInitLoad = false;
		switch (paramInt) {
		case 2:
		default:
		case 0:
			TextView localTextView = this.mTopNotifyBar;
			String str = this.mContext.getString(R.string.info_is_location);
			localTextView.setText(str);
		case 1:
			TextView localTextView2 = this.mTopNotifyBar;
			String str2 = this.mContext.getString(R.string.info_is_gethouse);
			localTextView2.setText(str2);
		case 3:
			TextView localTextView3 = this.mTopNotifyBar;
			String str3 = this.mContext
					.getString(R.string.no_cell_in_the_location);
			localTextView3.setText(str3);
			this.mIsCanRefresh = true;
		case 4:
			TextView localTextView4 = this.mTopNotifyBar;
			String str4 = this.mContext.getString(R.string.cannot_get_location);
			localTextView4.setText(str4);
		case 5:
			TextView localTextView5 = this.mTopNotifyBar;
			String str5 = this.mContext.getString(R.string.unvaliable_network);
			localTextView5.setText(str5);
		case 6:
			TextView localTextView6 = this.mTopNotifyBar;
			String str6 = this.mContext
					.getString(R.string.error_can_not_find_location);
			localTextView6.setText(str6);
		}
	}

	final class SearchInListActivity9 implements AddressToLocationListener {
		public void locationObtained(double paramDouble1, double paramDouble2,
				boolean paramBoolean) {
			if (paramBoolean != false) {
				SearchInListActivity.this.refreshByLocation(paramDouble1, paramDouble2);
			}
		}
	}
}
