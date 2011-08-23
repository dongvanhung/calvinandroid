package com.rent.activitiy;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rent.HouseFilter;
import com.rent.MobclickAgent;
import com.rent.PreferenceUtils;
import com.rent.SearchHandlerListener;
import com.rent.adapter.ResultAdapter;
import com.rent.data.HouseSource;
import com.rent.handler.SearchHandler;

public class ResultActivity extends Activity implements SearchHandlerListener{

	private String mAddress;
	  int[] mCategoryCount;
	  int[] mCategoryIndice;
	  private String mCellName;
	  private String mCity;
	  private int mCount = 15;
	  private ImageView mGotoBackView;
	  private Long mGroupId;
	  private ArrayList<HouseSource> mHoustList;
	  private String mImage;
	  private boolean mIsEnd = false;
	  private boolean mIsLoading = false;
	  private int mLastSumItem = 0;
	  private double mLatitude;
	  private ListView mListView;
	  private double mLongitude;
	  private int mOffset = 0;
	  private int mPrice;
	  private ResultAdapter mResultAdapter;
	  private SearchHandler mSearchHandler;
	  private int mSort = 0;
	  private Button mSortButton;
	  private int mSourceCount;

	  public ResultActivity()
	  {
	    int[] arrayOfInt1 = { -1, -1, -1 };
	    this.mCategoryIndice = arrayOfInt1;
	    int[] arrayOfInt2 = { 0, 0, 0 };
	    this.mCategoryCount = arrayOfInt2;
	  }

	  private void loadHouseList()
	  {
	    this.mIsLoading = true;
	    HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this);
	    SearchHandler localSearchHandler = this.mSearchHandler;
	    long l = this.mGroupId.longValue();
	    int i = this.mSearchHandler.mOffset;
	    int j = this.mCount;
	    String str = this.mCity;
	    int k = this.mSort;
	    new SearchThread(localSearchHandler, l, i, j, localHouseFilter, str, k).start();
	  }

	  private void sortCategory()
	  {
	    Arrays.fill(this.mCategoryIndice, -1);
	    if (this.mSort != 0);
	    int m;
	    int i4;
	    do
	    {
	      return;
	      Arrays.fill(this.mCategoryCount, 0);
	      long l1 = System.currentTimeMillis();
	      int j = 0;
	      int k = this.mHoustList.size();
	      if (j < k)
	      {
	        HouseSource localHouseSource = (HouseSource)this.mHoustList.get(j);
	        if ((localHouseSource.mPublishTime == null) || (localHouseSource.mPublishTime.trim().length() == 0))
	        {
	          int[] arrayOfInt1 = this.mCategoryCount;
	          int n = arrayOfInt1[2] + 1;
	          arrayOfInt1[2] = n;
	        }
	        while (true)
	        {
	          j += 1;
	          break;
	          StringBuilder localStringBuilder = new StringBuilder();
	          String str = localHouseSource.mPublishTime;
	          long l2 = Long.valueOf(str + "000").longValue();
	          long l3 = l1 - l2;
	          if (l3 <= 259200000L)
	          {
	            int[] arrayOfInt2 = this.mCategoryCount;
	            int i1 = arrayOfInt2[0] + 1;
	            arrayOfInt2[0] = i1;
	            continue;
	          }
	          if (l3 <= 604800000L)
	          {
	            int[] arrayOfInt3 = this.mCategoryCount;
	            int i2 = arrayOfInt3[1] + 1;
	            arrayOfInt3[1] = i2;
	            continue;
	          }
	          int[] arrayOfInt4 = this.mCategoryCount;
	          int i3 = arrayOfInt4[2] + 1;
	          arrayOfInt4[2] = i3;
	        }
	      }
	      m = 0;
	      i4 = this.mCategoryIndice.length;
	    }
	    while (m >= i4);
	    if (this.mCategoryCount[m] == 0)
	      this.mCategoryIndice[m] = -1;
	    while (true)
	    {
	      m += 1;
	      break;
	      int i = 0;
	      int i5 = 1;
	      while (i < m)
	      {
	        if (this.mCategoryCount[i] > 0)
	        {
	          int i6 = this.mCategoryCount[i] + 1;
	          i5 += i6;
	        }
	        i += 1;
	      }
	      this.mCategoryIndice[m] = i5;
	    }
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903108);
	    Object localObject = getIntent().getExtras();
	    int i = ((Bundle)localObject).getInt("searchformat", 0);
	    if (i == 0)
	    {
	      String str1 = ((Bundle)localObject).getString("keyword");
	      this.mCellName = str1;
	      Long localLong = Long.valueOf(((Bundle)localObject).getLong("group_id"));
	      this.mGroupId = localLong;
	      String str2 = ((Bundle)localObject).getString("city");
	      this.mCity = str2;
	      int j = ((Bundle)localObject).getInt("price");
	      this.mPrice = j;
	      int k = ((Bundle)localObject).getInt("sourcecoount");
	      this.mSourceCount = k;
	      double d1 = ((Bundle)localObject).getDouble("latitude");
	      this.mLatitude = d1;
	      double d2 = ((Bundle)localObject).getDouble("longitude");
	      this.mLongitude = d2;
	      String str3 = ((Bundle)localObject).getString("image");
	      this.mImage = str3;
	      String str4 = ((Bundle)localObject).getString("address");
	      this.mAddress = str4;
	      TextView localTextView = (TextView)findViewById(2131493149);
	      String str5 = this.mCellName;
	      localTextView.setText(str5);
	    }
	    SearchHandler localSearchHandler = new SearchHandler(this);
	    this.mSearchHandler = localSearchHandler;
	    ListView localListView1 = (ListView)findViewById(2131493152);
	    this.mListView = localListView1;
	    String str6 = this.mCellName;
	    int m = this.mPrice;
	    int n = this.mSourceCount;
	    ResultAdapter localResultAdapter1 = new ResultAdapter(this, str6, m, n);
	    this.mResultAdapter = localResultAdapter1;
	    ListView localListView2 = this.mListView;
	    ResultAdapter localResultAdapter2 = this.mResultAdapter;
	    localListView2.setAdapter(localResultAdapter2);
	    ListView localListView3 = this.mListView;
	    ResultActivity.1 local1 = new ResultActivity.1(this);
	    localListView3.setOnItemClickListener(local1);
	    ListView localListView4 = this.mListView;
	    ResultActivity.2 local2 = new ResultActivity.2(this);
	    localListView4.setOnScrollListener(local2);
	    ImageView localImageView1 = (ImageView)findViewById(2131493148);
	    this.mGotoBackView = localImageView1;
	    ImageView localImageView2 = this.mGotoBackView;
	    ResultActivity.3 local3 = new ResultActivity.3(this);
	    localImageView2.setOnClickListener(local3);
	    Button localButton1 = (Button)findViewById(2131493150);
	    this.mSortButton = localButton1;
	    Button localButton2 = this.mSortButton;
	    ResultActivity.4 local4 = new ResultActivity.4(this);
	    localButton2.setOnClickListener(local4);
	    if (i == 0)
	      loadHouseList();
	    localObject = ManifestData.getString(this, "UMENG_CHANNEL");
	    if ((NetworkUtils.isWifi(this)) && (((String)localObject).equalsIgnoreCase("umeng")))
	    {
	      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(2131493151);
	      ResultActivity.5 local5 = new ResultActivity.5(this, localRelativeLayout);
	      ExchangeDataService.requestDataAsyn(this, local5);
	    }
	  }

	  protected void onPause()
	  {
	    super.onPause();
	    MobclickAgent.onPause(this);
	  }

	  protected void onResume()
	  {
	    super.onResume();
	    MobclickAgent.onResume(this);
	  }

	  public void postSearch(boolean paramBoolean, SearchHandler paramSearchHandler)
	  {
	    int k;
	    if (true == paramBoolean)
	    {
	      ArrayList localArrayList1 = this.mSearchHandler.getHouseList();
	      this.mHoustList = localArrayList1;
	      int i = this.mHoustList.size();
	      int j = this.mLastSumItem;
	      k = i - j;
	      int m = this.mHoustList.size();
	      this.mLastSumItem = m;
	      if ((k == 0) && (this.mOffset == 0))
	      {
	        String str1 = getString(2131361904);
	        Toast.makeText(this, str1, 1).show();
	        this.mResultAdapter.setLoadMore(0);
	        this.mIsEnd = 1;
	        sortCategory();
	        ResultAdapter localResultAdapter1 = this.mResultAdapter;
	        ArrayList localArrayList2 = this.mHoustList;
	        localResultAdapter1.setHouseList(localArrayList2);
	        ResultAdapter localResultAdapter2 = this.mResultAdapter;
	        int[] arrayOfInt = this.mCategoryIndice;
	        localResultAdapter2.setmCategoryIndice(arrayOfInt);
	        ResultAdapter localResultAdapter3 = this.mResultAdapter;
	        int n = this.mSearchHandler.getTotalNumber();
	        localResultAdapter3.setmSourceCount(n);
	        this.mResultAdapter.notifyDataSetChanged();
	      }
	    }
	    while (true)
	    {
	      this.mIsLoading = 0;
	      return;
	      if (k == 0)
	      {
	        this.mResultAdapter.setLoadMore(0);
	        this.mIsEnd = 1;
	        break;
	      }
	      this.mResultAdapter.setLoadMore(1);
	      int i1 = this.mOffset;
	      int i2 = k + i1;
	      this.mOffset = i2;
	      break;
	      this.mResultAdapter.showIsLoading(0);
	      String str2 = getString(2131361904);
	      Toast.makeText(this, str2, 1).show();
	      this.mIsEnd = 1;
	      this.mResultAdapter.setLoadMore(0);
	    }
	  }
	
}
