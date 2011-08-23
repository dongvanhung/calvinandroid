package com.rent.activitiy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.HouseFilter;
import com.rent.MobclickAgent;
import com.rent.PreferenceUtils;
import com.rent.Rent;
import com.rent.data.Community;

public class SearchInListActivity extends Activity{

	protected String mAddressName;
	  protected ArrayList<Community> mCommList;
	  protected int mCommunityNumber;
	  protected Context mContext = this;
	  private TextView mFiterShow;
	  private RelativeLayout mGoFilter;
	  protected ImageView mGotoOtherView;
	  private boolean mHasLocated = 1;
	  protected EditText mInputLocation;
	  private boolean mIsCanRefresh = 0;
	  private boolean mIsEnd = 0;
	  private boolean mIsInit = 1;
	  private boolean mIsInitLoad = 1;
	  private boolean mIsLoading = 0;
	  private boolean mIsMyLocation = 0;
	  private SearchHandler mListSearchHandler;
	  private ListView mListView;
	  private boolean mLoadFailed = 0;
	  private ImageView mLocate;
	  private boolean mNeedRefreshOtherView = 0;
	  private int mOffset = 0;
	  protected String mQ;
	  private ImageView mRecordCondition;
	  private ListSearchAdapter mSearchAdapter;
	  protected Context mSearchContext;
	  protected TextView mTopNotifyBar;

	  private void clearList()
	  {
	    this.mListSearchHandler.mOffset = 0;
	    this.mListSearchHandler.mTotalNumber = 0;
	    this.mListSearchHandler.getCommList().clear();
	    this.mSearchAdapter.setIsEnd(1);
	    ListSearchAdapter localListSearchAdapter = this.mSearchAdapter;
	    ArrayList localArrayList = this.mListSearchHandler.getCommList();
	    localListSearchAdapter.refresh(localArrayList);
	    this.mSearchAdapter.notifyDataSetChanged();
	    this.mOffset = 0;
	  }

	  private void refreshByAddress(String paramString)
	  {
	    if (!Rent.isAvailableNetwork(this))
	      updateUI(5);
	    while (true)
	    {
	      return;
	      updateUI(1);
	      SearchInListActivity.9 local9 = new SearchInListActivity.9(this);
	      AddressToLocationHandler localAddressToLocationHandler = new AddressToLocationHandler(local9);
	      new AddressToLocationThread(localAddressToLocationHandler, paramString).start();
	    }
	  }

	  private void refreshByLocation(double paramDouble1, double paramDouble2)
	  {
	    SharedPreferences localSharedPreferences = getSharedPreferences("location_point", 0);
	    SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
	    String str1 = String.valueOf(paramDouble1);
	    boolean bool1 = localEditor1.putString("lat", str1).commit();
	    SharedPreferences.Editor localEditor2 = localSharedPreferences.edit();
	    String str2 = String.valueOf(paramDouble2);
	    boolean bool2 = localEditor2.putString("lon", str2).commit();
	    Rent.resetLocationRange(this.mContext, null);
	    if (!Rent.isAvailableNetwork(this))
	      updateUI(5);
	    while (true)
	    {
	      return;
	      SearchInListActivity.10 local10 = new SearchInListActivity.10(this);
	      Context localContext1 = this.mContext;
	      AddressFromLocationHandler localAddressFromLocationHandler = new AddressFromLocationHandler(local10, localContext1);
	      Context localContext2 = this.mContext;
	      new AddressFromLocationThread(localAddressFromLocationHandler, localContext2).start();
	    }
	  }

	  private void startSearchThread()
	  {
	    int i = 1;
	    this.mIsLoading = i;
	    updateFilterBar();
	    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("location_point", 0);
	    double d1 = Double.parseDouble(localSharedPreferences.getString("latlt", "39.920591"));
	    double d2 = Double.parseDouble(localSharedPreferences.getString("lonlt", "116.432791"));
	    double d3 = Double.parseDouble(localSharedPreferences.getString("latrb", "39.920591"));
	    double d4 = Double.parseDouble(localSharedPreferences.getString("lonrb", "116.432791"));
	    String str1 = PreferenceUtils.getCurrentCityName(this);
	    PreferenceUtils.setRefreshStatus(this.mContext, 2);
	    HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this);
	    SearchHandler localSearchHandler = this.mListSearchHandler;
	    int j = this.mListSearchHandler.mOffset;
	    String str2 = this.mQ;
	    new SearchThread(localSearchHandler, d3, d4, d1, d2, str1, j, localHouseFilter, 65535L, null, 0, str2).start();
	  }

	  public void init()
	  {
	    setContentView(2130903091);
	    Context localContext = this.mContext;
	    ListSearchAdapter localListSearchAdapter1 = new ListSearchAdapter(localContext);
	    this.mSearchAdapter = localListSearchAdapter1;
	    ListView localListView1 = (ListView)findViewById(2131493097);
	    this.mListView = localListView1;
	    ListView localListView2 = this.mListView;
	    ListSearchAdapter localListSearchAdapter2 = this.mSearchAdapter;
	    localListView2.setAdapter(localListSearchAdapter2);
	    ListView localListView3 = this.mListView;
	    SearchInListActivity.1 local1 = new SearchInListActivity.1(this);
	    localListView3.setOnItemClickListener(local1);
	    ListView localListView4 = this.mListView;
	    SearchInListActivity.2 local2 = new SearchInListActivity.2(this);
	    localListView4.setOnScrollListener(local2);
	    SearchHandler localSearchHandler = new SearchHandler(this);
	    this.mListSearchHandler = localSearchHandler;
	    TextView localTextView1 = (TextView)findViewById(2131493096);
	    this.mTopNotifyBar = localTextView1;
	    this.mTopNotifyBar.setVisibility(8);
	    TextView localTextView2 = this.mTopNotifyBar;
	    SearchInListActivity.3 local3 = new SearchInListActivity.3(this);
	    localTextView2.setOnClickListener(local3);
	    TextView localTextView3 = (TextView)findViewById(2131493102);
	    this.mFiterShow = localTextView3;
	    this.mFiterShow.setTextSize(15.0F);
	    ImageView localImageView1 = (ImageView)findViewById(2131493094);
	    this.mLocate = localImageView1;
	    ImageView localImageView2 = this.mLocate;
	    SearchInListActivity.4 local4 = new SearchInListActivity.4(this);
	    localImageView2.setOnClickListener(local4);
	    ImageView localImageView3 = (ImageView)findViewById(2131493095);
	    this.mRecordCondition = localImageView3;
	    ImageView localImageView4 = this.mRecordCondition;
	    SearchInListActivity.5 local5 = new SearchInListActivity.5(this);
	    localImageView4.setOnClickListener(local5);
	    RelativeLayout localRelativeLayout1 = (RelativeLayout)findViewById(2131493100);
	    this.mGoFilter = localRelativeLayout1;
	    RelativeLayout localRelativeLayout2 = this.mGoFilter;
	    SearchInListActivity.6 local6 = new SearchInListActivity.6(this);
	    localRelativeLayout2.setOnClickListener(local6);
	    ImageView localImageView5 = (ImageView)findViewById(2131493093);
	    this.mGotoOtherView = localImageView5;
	    if (Rent.isAvailableGoogleMap())
	    {
	      this.mGotoOtherView.setImageResource(2130837651);
	      ImageView localImageView6 = this.mGotoOtherView;
	      SearchInListActivity.7 local7 = new SearchInListActivity.7(this);
	      localImageView6.setOnClickListener(local7);
	      EditText localEditText1 = (EditText)findViewById(2131492877);
	      this.mInputLocation = localEditText1;
	      this.mInputLocation.setCursorVisible(0);
	      this.mInputLocation.setInputType(0);
	      EditText localEditText2 = this.mInputLocation;
	      SearchInListActivity.8 local8 = new SearchInListActivity.8(this);
	      localEditText2.setOnClickListener(local8);
	      if (Rent.isAvailableNetwork(this))
	        break label465;
	      updateUI(5);
	    }
	    while (true)
	    {
	      return;
	      this.mGotoOtherView.setImageResource(2130837621);
	      break;
	      label465: updateUI(1);
	      String str = getSharedPreferences("location_point", 0).getString("name", "");
	      this.mAddressName = str;
	      startSearchThread();
	    }
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    MobclickAgent.onError(this);
	    init();
	  }

	  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	  {
	    return false;
	  }

	  public void onLocationChanged(double paramDouble1, double paramDouble2, boolean paramBoolean)
	  {
	    if (!this.mHasLocated)
	    {
	      this.mHasLocated = 1;
	      this.mIsMyLocation = 1;
	      this.mInputLocation.setText("");
	      this.mQ = null;
	      Context localContext = getApplicationContext();
	      SearchInListActivity.11 local11 = new SearchInListActivity.11(this);
	      GetMarLocationHandler localGetMarLocationHandler = new GetMarLocationHandler(local11);
	      String str = Rent.getVersionName(getApplicationContext());
	      double d1 = paramDouble1;
	      double d2 = paramDouble2;
	      new GetMarsLocationThread(localContext, localGetMarLocationHandler, d1, d2, str, "rent").start();
	    }
	  }

	  protected void onPause()
	  {
	    super.onPause();
	    MyLocationManager.getInstance(getApplicationContext()).stopLocationManager();
	    MobclickAgent.onPause(this);
	  }

	  protected void onResume()
	  {
	    int i;
	    if (!this.mIsInit)
	      if (PreferenceUtils.getShowKeyword(this))
	      {
	        EditText localEditText1 = this.mInputLocation;
	        String str1 = PreferenceUtils.getCurrentKeyWord(this);
	        localEditText1.setText(str1);
	        String str2 = PreferenceUtils.getCurrentKeyWord(this);
	        this.mQ = str2;
	        i = this.mOffset;
	        this.mOffset = 0;
	        if (!this.mIsInit)
	        {
	          updateFilterBar();
	          this.mIsMyLocation = 0;
	          switch (PreferenceUtils.getRefreshStatus(this))
	          {
	          default:
	          case 0:
	          case 1:
	          case 2:
	          case 3:
	          case 4:
	          case 5:
	          }
	        }
	      }
	    while (true)
	    {
	      this.mIsInit = 0;
	      super.onResume();
	      MobclickAgent.onResume(this);
	      return;
	      this.mInputLocation.setText("");
	      this.mQ = null;
	      break;
	      this.mInputLocation.setText("");
	      this.mQ = null;
	      break;
	      refreshByLocation(1);
	      continue;
	      refreshByLocation(0);
	      this.mNeedRefreshOtherView = 1;
	      this.mOffset = i;
	      continue;
	      if (!this.mHasLocated)
	      {
	        PreferenceUtils.setInit(this);
	        this.mHasLocated = 1;
	        this.mIsMyLocation = 0;
	        MyLocationManager.getInstance(getApplicationContext()).stopLocation();
	      }
	      String str3 = PreferenceUtils.getCurrentKeyWord(this);
	      this.mQ = str3;
	      EditText localEditText2 = this.mInputLocation;
	      String str4 = PreferenceUtils.getCurrentKeyWord(this);
	      localEditText2.setText(str4);
	      StringBuilder localStringBuilder1 = new StringBuilder();
	      String str5 = PreferenceUtils.getCurrentCityName(this);
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(str5);
	      String str6 = PreferenceUtils.getCurrentKeyWord(this);
	      String str7 = str6;
	      refreshByAddress(str7);
	      this.mNeedRefreshOtherView = 1;
	      continue;
	      refreshByLocation(0);
	      continue;
	      this.mIsMyLocation = 1;
	      refreshByLocation(0);
	    }
	  }

	  public void postSearch(boolean paramBoolean, SearchHandler paramSearchHandler)
	  {
	    if (true == paramBoolean)
	    {
	      int i = this.mListSearchHandler.mTotalNumber;
	      this.mCommunityNumber = i;
	      int j = this.mOffset;
	      int k = this.mListSearchHandler.getCommList().size();
	      if (j == k)
	      {
	        this.mSearchAdapter.setIsEnd(1);
	        this.mIsEnd = 1;
	        this.mIsLoading = 0;
	        TextView localTextView = this.mTopNotifyBar;
	        String str1 = this.mContext.getString(2131361871);
	        Object[] arrayOfObject = new Object[2];
	        String str2 = this.mAddressName;
	        arrayOfObject[0] = str2;
	        Integer localInteger = Integer.valueOf(this.mCommunityNumber);
	        arrayOfObject[1] = localInteger;
	        String str3 = String.format(str1, arrayOfObject);
	        localTextView.setText(str3);
	      }
	    }
	    while (true)
	    {
	      ListSearchAdapter localListSearchAdapter = this.mSearchAdapter;
	      ArrayList localArrayList = this.mListSearchHandler.getCommList();
	      localListSearchAdapter.refresh(localArrayList);
	      this.mSearchAdapter.notifyDataSetChanged();
	      return;
	      int m = this.mListSearchHandler.getCommList().size();
	      this.mOffset = m;
	      this.mSearchAdapter.setIsEnd(0);
	      this.mIsEnd = 0;
	      break;
	      this.mSearchAdapter.setIsEnd(0);
	      this.mIsEnd = 0;
	      this.mTopNotifyBar.setText(2131361816);
	      this.mLoadFailed = 1;
	    }
	  }

	  public void refreshByLocation(boolean paramBoolean)
	  {
	    if (!Rent.isAvailableNetwork(this))
	      updateUI(5);
	    while (true)
	    {
	      return;
	      int i = 0;
	      while (true)
	      {
	        try
	        {
	          this.mIsMyLocation = i;
	          if (!paramBoolean)
	            break label84;
	          i = 1;
	          if (i == 0)
	            break label117;
	          Rent.checkNetProvider(getParent());
	          updateUI(0);
	          MyLocationManager.getInstance(getApplicationContext()).registerListener(this);
	          this.mHasLocated = 0;
	          clearList();
	        }
	        catch (Exception localException)
	        {
	          String str2 = localException.toString();
	          int k = Log.e("MainActivity.getCurrentGeoPoint()", str2);
	        }
	        break;
	        label84: String str1 = PreferenceUtils.getCurrentCityName(this);
	        if ((str1 == null) || (str1.length() == 0))
	        {
	          j = 1;
	          continue;
	        }
	        updateUI(1);
	        int j = 0;
	      }
	      label117: updateUI(1);
	      String str3 = getSharedPreferences("location_point", 0).getString("name", "");
	      this.mAddressName = str3;
	      clearList();
	      this.mListView.setSelection(0);
	      startSearchThread();
	    }
	  }

	  public void setSearchContext(Context paramContext)
	  {
	    this.mSearchContext = paramContext;
	  }

	  public void updateFilterBar()
	  {
	    HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this);
	    TextView localTextView = this.mFiterShow;
	    Context localContext = this.mContext;
	    String str = localHouseFilter.toDisplayedString(localContext);
	    localTextView.setText(str);
	  }

	  public void updateUI(int paramInt)
	  {
	    this.mIsCanRefresh = 0;
	    this.mTopNotifyBar.setVisibility(0);
	    if (this.mIsInitLoad)
	      this.mIsInitLoad = 0;
	    switch (paramInt)
	    {
	    case 2:
	    default:
	    case 0:
	    case 1:
	    case 3:
	    case 4:
	    case 5:
	    case 6:
	    }
	    while (true)
	    {
	      return;
	      TextView localTextView1 = this.mTopNotifyBar;
	      String str1 = this.mContext.getString(2131361905);
	      localTextView1.setText(str1);
	      continue;
	      TextView localTextView2 = this.mTopNotifyBar;
	      String str2 = this.mContext.getString(2131361906);
	      localTextView2.setText(str2);
	      continue;
	      TextView localTextView3 = this.mTopNotifyBar;
	      String str3 = this.mContext.getString(2131361815);
	      localTextView3.setText(str3);
	      this.mIsCanRefresh = 1;
	      continue;
	      TextView localTextView4 = this.mTopNotifyBar;
	      String str4 = this.mContext.getString(2131361855);
	      localTextView4.setText(str4);
	      continue;
	      TextView localTextView5 = this.mTopNotifyBar;
	      String str5 = this.mContext.getString(2131361836);
	      localTextView5.setText(str5);
	      continue;
	      TextView localTextView6 = this.mTopNotifyBar;
	      String str6 = this.mContext.getString(2131361866);
	      localTextView6.setText(str6);
	    }
	  }
}
