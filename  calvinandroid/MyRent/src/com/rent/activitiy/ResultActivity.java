package com.rent.activitiy;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rent.HouseFilter;
import com.rent.PreferenceUtils;
import com.rent.R;
import com.rent.SearchHandlerListener;
import com.rent.adapter.ResultAdapter;
import com.rent.data.HouseSource;
import com.rent.handler.SearchHandler;
import com.rent.listener.ExchangeDataRequestListener;
import com.rent.thread.SearchThread;
import com.rent.util.ManifestData;
import com.rent.util.NetworkUtils;
import com.rent.view.ExchangeViewManager;

public class ResultActivity extends Activity implements SearchHandlerListener {

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

	public ResultActivity() {
		int[] arrayOfInt1 = { -1, -1, -1 };
		this.mCategoryIndice = arrayOfInt1;
		int[] arrayOfInt2 = { 0, 0, 0 };
		this.mCategoryCount = arrayOfInt2;
	}

	private void loadHouseList() {
		this.mIsLoading = true;
		HouseFilter localHouseFilter = PreferenceUtils
				.getCurrentHouseFilter(this);
		SearchHandler localSearchHandler = this.mSearchHandler;
		long l = this.mGroupId.longValue();
		int i = this.mSearchHandler.mOffset;
		int j = this.mCount;
		String str = this.mCity;
		int k = this.mSort;
		new SearchThread(localSearchHandler, l, i, j, localHouseFilter, str, k)
				.start();
	}

	private void sortCategory() {
		Arrays.fill(this.mCategoryIndice, -1);
		int m;
		int i4;
			Arrays.fill(this.mCategoryCount, 0);
			long l1 = System.currentTimeMillis();
			int k = this.mHoustList.size();
			for (int i = 0; i < this.mHoustList.size(); i++) {
				HouseSource localHouseSource = (HouseSource) this.mHoustList.get(i);
				if ((localHouseSource.mPublishTime == null)
						|| (localHouseSource.mPublishTime.trim().length() == 0)) {
					int[] arrayOfInt1 = this.mCategoryCount;
					int n = arrayOfInt1[2] + 1;
					arrayOfInt1[2] = n;
				}
				else {
					StringBuilder localStringBuilder = new StringBuilder();
					String str = localHouseSource.mPublishTime;
					long l2 = Long.valueOf(str + "000").longValue();
					long l3 = l1 - l2;
					if (l3 <= 259200000L) {
						int[] arrayOfInt2 = this.mCategoryCount;
						int i1 = arrayOfInt2[0] + 1;
						arrayOfInt2[0] = i1;
					} else if (l3 <= 604800000L) {
						int[] arrayOfInt3 = this.mCategoryCount;
						int i2 = arrayOfInt3[1] + 1;
						arrayOfInt3[1] = i2;
					} else {
						int[] arrayOfInt4 = this.mCategoryCount;
						int i3 = arrayOfInt4[2] + 1;
						arrayOfInt4[2] = i3;
					}
				}
			}
			for (int i = 0; i < mCategoryCount.length; i++) {
				this.mCategoryIndice[i] = mCategoryCount[i];
			}
	}

	protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    //MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(R.layout.result_view);
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
	      TextView localTextView = (TextView)findViewById(R.id.result_title);
	      String str5 = this.mCellName;
	      localTextView.setText(str5);
	    }
	    SearchHandler localSearchHandler = new SearchHandler(this);
	    this.mSearchHandler = localSearchHandler;
	    ListView localListView1 = (ListView)findViewById(R.id.result_view_list);
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
	    localListView3.setOnItemClickListener(new OnItemClickListener(){
	    	public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
	    	  {
	    	    if (paramInt == 0)
	    	    {
	    	      Bundle localBundle = new Bundle();
	    	      localBundle.putString("city", mCity);
	    	      localBundle.putString("name", mCellName);
	    	      localBundle.putInt("sourcecoount", mSourceCount);
	    	      localBundle.putDouble("latitude", mLatitude);
	    	      localBundle.putDouble("longitude", mLongitude);
	    	      localBundle.putInt("price", mPrice);
	    	      localBundle.putLong("group_id", mGroupId);
	    	      localBundle.putString("image", mImage);
	    	      Intent localIntent1 = new Intent(ResultActivity.this, CommunityDetailActivity.class);
	    	      Intent localIntent2 = localIntent1.putExtras(localBundle);
	    	      startActivity(localIntent1);
	    	    } else {
	    	      /*int k = 0;
	    	      while (true)
	    	      {
	    	        int m = mCategoryIndice.length;
	    	        if (k >= m)
	    	          break label245;
	    	        int n = mCategoryIndice[k];
	    	        if (paramInt == n)
	    	          break;
	    	        k += 1;
	    	      }
	    	      int[] arrayOfInt = mCategoryIndice;
	    	      k = ResultAdapter.getVirtualViewItemIndex(paramInt, 1, arrayOfInt);
	    	      int i1 = ResultActivity.this.getSumCount();
	    	      if (k >= i1)
	    	        continue;
	    	      HouseSource localHouseSource = (HouseSource)ResultActivity.access$800(this.this$0).getItem(k);
	    	      ResultActivity localResultActivity2 = this.this$0;
	    	      Intent localIntent3 = new Intent(localResultActivity2, SourceDetailActivity.class);
	    	      long l2 = localHouseSource.mOriginId;
	    	      Intent localIntent4 = localIntent3.putExtra("origin_id", l2);
	    	      String str4 = ResultActivity.access$900(this.this$0);
	    	      Intent localIntent5 = localIntent3.putExtra("address", str4);
	    	      this.this$0.startActivity(localIntent3);*/
	    	    }
	    	  }
	    });
	    ListView localListView4 = this.mListView;
	    /*localListView4.setOnScrollListener(new OnScrollListener(){
	    	 public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
	    	  {
	    	    if ((!ResultActivity.access$1000(this.this$0)) && (!ResultActivity.access$1100(this.this$0)) && (paramInt3 > 0) && (paramInt1 + paramInt2 == paramInt3))
	    	      ResultActivity.access$1200(this.this$0);
	    	  }

	    	  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
	    	  {
	    	  }
	    });*/
	    ImageView localImageView1 = (ImageView)findViewById(R.id.result_goto_back_imageview);
	    this.mGotoBackView = localImageView1;
	    ImageView localImageView2 = this.mGotoBackView;
	    localImageView2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View paramView)
	    	  {
	    	    ResultActivity.this.finish();
	    	  }
		});
	    Button localButton1 = (Button)findViewById(R.id.result_sort);
	    this.mSortButton = localButton1;
	    Button localButton2 = this.mSortButton;
	    /*localButton2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View paramView)
	    	  {
	    	    MobclickAgent.onEvent(this.this$0, "sortbuttonclicked");
	    	    if (ResultActivity.access$1300(this.this$0) == 1)
	    	    {
	    	      int i = ResultActivity.access$1302(this.this$0, 0);
	    	      Button localButton1 = ResultActivity.access$1400(this.this$0);
	    	      String str1 = this.this$0.getString(2131362045);
	    	      localButton1.setText(str1);
	    	    }
	    	    while (true)
	    	    {
	    	      ResultActivity.access$1500(this.this$0).clearAllList();
	    	      ResultActivity.access$1500(this.this$0).mOffset = 0;
	    	      int j = ResultActivity.access$1602(this.this$0, 0);
	    	      boolean bool = ResultActivity.access$1102(this.this$0, 0);
	    	      ResultActivity.access$1700(this.this$0).setSelection(0);
	    	      int k = ResultActivity.access$1802(this.this$0, 0);
	    	      ResultActivity.access$1200(this.this$0);
	    	      return;
	    	      int m = ResultActivity.access$1302(this.this$0, 1);
	    	      Button localButton2 = ResultActivity.access$1400(this.this$0);
	    	      String str2 = this.this$0.getString(2131362044);
	    	      localButton2.setText(str2);
	    	    }
	    	  }
		});*/
	    if (i == 0)
	      loadHouseList();
	    localObject = ManifestData.getString(this, "UMENG_CHANNEL");
	    if ((NetworkUtils.isWifi(this)) && (((String)localObject).equalsIgnoreCase("umeng"))) //TODO:
	    {
	      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(R.id.result_list_layout);
	      ResultActivity5 local5 = new ResultActivity5(this, localRelativeLayout);
	     // ExchangeDataService.requestDataAsyn(this, local5);  //TODO:
	    }
	  }

	final class ResultActivity5 implements ExchangeDataRequestListener {
		private RelativeLayout localRelativeLayout;
		private ResultActivity activity;
		public ResultActivity5(ResultActivity activity, RelativeLayout localRelativeLayout) {
			this.activity = activity;
			this.localRelativeLayout = localRelativeLayout;
		}
		public void dataReceived(int paramInt) {
			if (paramInt == 0) {
				Log.e("exchange", "failed to get request data");
				return;
			}
			int j = Log.e("exchange", "sucuss to get request data");
			ExchangeViewManager localExchangeViewManager = new ExchangeViewManager();
			localExchangeViewManager.addView(activity, localRelativeLayout.getId());
		}
	}

	protected void onPause() {
		super.onPause();
		//MobclickAgent.onPause(this);
	}

	protected void onResume() {
		super.onResume();
		//MobclickAgent.onResume(this);
	}

	public void postSearch(boolean paramBoolean,
			SearchHandler paramSearchHandler) {
		int k = 0;
		if (true == paramBoolean) {
			ArrayList localArrayList1 = this.mSearchHandler.getHouseList();
			this.mHoustList = localArrayList1;
			int i = this.mHoustList.size();
			int m = this.mHoustList.size();
			this.mLastSumItem = m;
			k = i - this.mLastSumItem;
			if ((k == 0) && (this.mOffset == 0)) {
				String str1 = getString(R.string.info_no_result);
				Toast.makeText(this, str1, 1).show();
				this.mResultAdapter.setLoadMore(false);
				this.mIsEnd = true;
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
			this.mIsLoading = false;
		} else {
			if (k == 0) {
				this.mResultAdapter.setLoadMore(false);
				this.mIsEnd = true;
			}
			this.mResultAdapter.setLoadMore(true);
			int i1 = this.mOffset;
			int i2 = k + i1;
			this.mOffset = i2;
			this.mResultAdapter.showIsLoading(false);
			String str2 = getString(R.string.info_no_result);
			Toast.makeText(this, str2, 1).show();
			this.mIsEnd = true;
			this.mResultAdapter.setLoadMore(false);
		}
	}

}
