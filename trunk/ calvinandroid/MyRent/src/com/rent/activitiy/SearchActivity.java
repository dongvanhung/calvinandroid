package com.rent.activitiy;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.rent.R;
import com.rent.Rent;

public class SearchActivity extends ActivityGroup
{
  public static final String[] MAIN_VIEW_NAME;
  private LinearLayout mContainView;
  private View mListView;
  private View mMapView;
  private View mView;

  static
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "map_view";
    arrayOfString[1] = "list_view";
    arrayOfString[2] = "filter_view";
    arrayOfString[3] = "address_view";
    MAIN_VIEW_NAME = arrayOfString;
  }

  private void initView()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(R.id.search_view);
    this.mContainView = localLinearLayout1;
    int i = Rent.getCurrentNetwork(this);
    if ((Rent.isAvailableGoogleMap()) && (i == 1))
    {
      LocalActivityManager localLocalActivityManager1 = getLocalActivityManager();
      String str1 = Rent.MAIN_VIEW_NAME[0];
      Intent localIntent1 = new Intent(this, SearchOnMapActivity.class).addFlags(131072);
      View localView1 = localLocalActivityManager1.startActivity(str1, localIntent1).getDecorView();
      this.mMapView = localView1;
      LocalActivityManager localLocalActivityManager2 = getLocalActivityManager();
      String str2 = Rent.MAIN_VIEW_NAME[0];
      ((SearchOnMapActivity)(SearchOnMapActivity)localLocalActivityManager2.getActivity(str2)).setSearchContext(this);
      LinearLayout localLinearLayout2 = this.mContainView;
      View localView2 = this.mMapView;
      localLinearLayout2.addView(localView2);
    } else {
      LocalActivityManager localLocalActivityManager3 = getLocalActivityManager();
      String str3 = Rent.MAIN_VIEW_NAME[1];
      Intent localIntent2 = new Intent(this, SearchInListActivity.class).addFlags(131072);
      View localView3 = localLocalActivityManager3.startActivity(str3, localIntent2).getDecorView();
      this.mListView = localView3;
      LocalActivityManager localLocalActivityManager4 = getLocalActivityManager();
      String str4 = Rent.MAIN_VIEW_NAME[1];
      ((SearchInListActivity)(SearchInListActivity)localLocalActivityManager4.getActivity(str4)).setSearchContext(this);
      LinearLayout localLinearLayout3 = this.mContainView;
      View localView4 = this.mListView;
      localLinearLayout3.addView(localView4);
    }
  }

  public void changeActivtiy(int paramInt)
  {
    this.mContainView.removeAllViews();
    switch (paramInt)
    {
    default:
    case 0:
    	if (Rent.isAvailableGoogleMap())
        {
          LocalActivityManager localLocalActivityManager1 = getLocalActivityManager();
          String str1 = MAIN_VIEW_NAME[0];
          Intent localIntent1 = new Intent(this, SearchOnMapActivity.class).addFlags(131072);
          View localView1 = localLocalActivityManager1.startActivity(str1, localIntent1).getDecorView();
          this.mView = localView1;
          LocalActivityManager localLocalActivityManager2 = getLocalActivityManager();
          String str2 = MAIN_VIEW_NAME[0];
          ((SearchOnMapActivity)(SearchOnMapActivity)localLocalActivityManager2.getActivity(str2)).setSearchContext(this);
        } else {
          LocalActivityManager localLocalActivityManager3 = getLocalActivityManager();
          String str3 = MAIN_VIEW_NAME[0];
          Intent localIntent2 = new Intent(this, SearchWithoutMapActivity.class).addFlags(131072);
          View localView3 = localLocalActivityManager3.startActivity(str3, localIntent2).getDecorView();
          this.mView = localView3;
          LocalActivityManager localLocalActivityManager4 = getLocalActivityManager();
          String str4 = MAIN_VIEW_NAME[0];
          ((SearchWithoutMapActivity)(SearchWithoutMapActivity)localLocalActivityManager4.getActivity(str4)).setSearchContext(this);
        }
        LinearLayout localLinearLayout1 = this.mContainView;
        View localView2 = this.mView;
        localLinearLayout1.addView(localView2);
    case 1:
    	 LocalActivityManager localLocalActivityManager5 = getLocalActivityManager();
         String str5 = MAIN_VIEW_NAME[1];
         Intent localIntent3 = new Intent(this, SearchInListActivity.class).addFlags(131072);
         View localView4 = localLocalActivityManager5.startActivity(str5, localIntent3).getDecorView();
         this.mView = localView4;
         LocalActivityManager localLocalActivityManager6 = getLocalActivityManager();
         String str6 = MAIN_VIEW_NAME[1];
         ((SearchInListActivity)(SearchInListActivity)localLocalActivityManager6.getActivity(str6)).setSearchContext(this);
         LinearLayout localLinearLayout2 = this.mContainView;
         View localView5 = this.mView;
         localLinearLayout2.addView(localView5);
    case 2:
    	 /*LocalActivityManager localLocalActivityManager7 = getLocalActivityManager();
         String str7 = MAIN_VIEW_NAME[2];
         Intent localIntent4 = new Intent(this, FilterActivity.class).addFlags(131072);
         View localView6 = localLocalActivityManager7.startActivity(str7, localIntent4).getDecorView();
         this.mView = localView6;
         LinearLayout localLinearLayout3 = this.mContainView;
         View localView7 = this.mView;
         localLinearLayout3.addView(localView7);*/
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    //MobclickAgent.onError(this);
    boolean bool = requestWindowFeature(1);
    setContentView(R.layout.search_activity);
    initView();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void onPause()
  {
    super.onPause();
   // MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    super.onResume();
    //MobclickAgent.onResume(this);
  }
}