package com.rent.activitiy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rent.HouseFilter;
import com.rent.PreferenceUtils;
import com.rent.R;

public class SearchWithoutMapActivity extends Activity {

	protected Context mContext;
	  protected int mDistance;
	  private TextView mFiterShow;
	  private ImageView mGotoOtherView;
	  private RelativeLayout mGotoSiftView;
	  protected MainActivity mMainActivity;
	  private Context mSearchContext;
	  private TextView mTopNotifyBar;

	  private void initView()
	  {
	    TextView localTextView1 = (TextView)findViewById(R.id.recent_view_topnotifybar);
	    this.mTopNotifyBar = localTextView1;
	    this.mTopNotifyBar.setVisibility(8);
	    TextView localTextView2 = (TextView)findViewById(R.id.fiter_show);
	    this.mFiterShow = localTextView2;
	    updateFilterBar();
	    ImageView localImageView1 = (ImageView)findViewById(R.id.goto_other_imageview);
	    this.mGotoOtherView = localImageView1;
	    ImageView localImageView2 = this.mGotoOtherView;
	    localImageView2.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    //MobclickAgent.onEvent(this.this$0, "search_viewtype", "list");
//	    	    ((SearchActivity)SearchWithoutMapActivity.access$000(this.this$0)).changeActivtiy(1);
	    	  }
		});
	    RelativeLayout localRelativeLayout1 = (RelativeLayout)findViewById(R.id.go_filter);
	    this.mGotoSiftView = localRelativeLayout1;
	    RelativeLayout localRelativeLayout2 = this.mGotoSiftView;
	    localRelativeLayout2.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*MobclickAgent.onEvent(this.this$0, "filterclick");
	    	    SearchWithoutMapActivity localSearchWithoutMapActivity = this.this$0;
	    	    Intent localIntent = new Intent(localSearchWithoutMapActivity, FilterActivity.class);
	    	    this.this$0.startActivity(localIntent);*/
	    	  }
		});
	  }

	  private void updateFilterBar()
	  {
	    HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this);
	    TextView localTextView = this.mFiterShow;
	    Context localContext = this.mContext;
	    String str = localHouseFilter.toDisplayedString(localContext);
	    localTextView.setText(str);
	    int i = localHouseFilter.getmDistanceLength();
	    this.mDistance = i;
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(R.layout.no_mapsearch_view);
	    initView();
	  }

	  public void onPause()
	  {
	    super.onPause();
//	    MobclickAgent.onPause(this);
	  }

	  public void onResume()
	  {
	    super.onResume();
//	    MobclickAgent.onResume(this);
	  }

	  public void setSearchContext(Context paramContext)
	  {
	    this.mSearchContext = paramContext;
	  }
}
