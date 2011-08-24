package com.rent.activitiy;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import com.rent.MobclickAgent;
import com.rent.Rent;
import com.rent.UIUtils;

public class CommunityDetailActivity extends Activity{

	public static final int ABSTRICT_MIN_LENGTH = 50;
	  private static int CONTROL_ARROUND = 0;
	  private static int CONTROL_INTOR = 0;
	  private static int CONTROL_TRAFFIC = 0;
	  static final int TAB_FLAT_TYPE = 1;
	  static final int TAB_LANDSCAPE;
	  private String[] flatTypes = null;
	  private boolean hasFlat_type = 0;
	  private boolean hasLandscape = 0;
	  private boolean introExpandable = 1;
	  private String[] landscapes = null;
	  private ImageView mBack;
	  private String mCity;
	  private ImageView mCommunityButAdd;
	  private ImageView mCommunityButReduce;
	  private CommunityDetail mDetail;
	  private Bitmap mFlatBitmap;
	  private long mGroupId;
	  private String mImage;
	  private String mIntro;
	  private TextView mIntroTextView;
	  private Bitmap mLandBitmap;
	  private double mLat;
	  private LinearLayout mLinearLayout;
	  private double mLon;
	  private Button mMapNav;
	  private String mName;
	  private int mPrice;
	  private ProgressBar mProgressbar;
	  private ImageView mRoundButAdd;
	  private ImageView mRoundButReduce;
	  private TableLayout mRoundLayout;
	  private ImageView mRoundLine;
	  private Bitmap mSampleBitmap;
	  private int mSelectedTab = 0;
	  private String mShortIntro;
	  private int mSourceCount = 0;
	  private TabHost mTab;
	  private ImageView mTabImageView;
	  private ImageView mTrafficButAdd;
	  private ImageView mTrafficButReduce;
	  private LinearLayout mTrafficLayout;
	  private ImageView mTrafficLine;
	  private boolean sourroundingsVisible = 1;
	  private boolean trafficVisible = 1;

	  static
	  {
	    CONTROL_ARROUND = 2;
	    CONTROL_TRAFFIC = 3;
	  }

	  private void closeOtherLayout(int paramInt)
	  {
	    int i = CONTROL_INTOR;
	    if (paramInt == i)
	    {
	      setIntroLayoutVisible(1);
	      setArroundLayoutVisible(0);
	      setTrafficLayoutVisible(0);
	    }
	    while (true)
	    {
	      return;
	      int j = CONTROL_ARROUND;
	      if (paramInt == j)
	      {
	        setIntroLayoutVisible(0);
	        setArroundLayoutVisible(1);
	        setTrafficLayoutVisible(0);
	        continue;
	      }
	      int k = CONTROL_TRAFFIC;
	      if (paramInt != k)
	        continue;
	      setIntroLayoutVisible(0);
	      setArroundLayoutVisible(0);
	      setTrafficLayoutVisible(1);
	    }
	  }

	  private void downBmp(String paramString, int paramInt)
	  {
	    new CommunityDetailActivity.6(this, paramString, paramInt).start();
	  }

	  private void fillIntro(String paramString)
	  {
	    this.mIntro = paramString;
	    if (paramString.length() > 50)
	    {
	      String str1 = paramString.substring(0, 49);
	      this.mShortIntro = str1;
	      TextView localTextView1 = this.mIntroTextView;
	      String str2 = this.mShortIntro;
	      localTextView1.setText(str2);
	    }
	    while (true)
	    {
	      return;
	      TextView localTextView2 = this.mIntroTextView;
	      String str3 = this.mIntro;
	      localTextView2.setText(str3);
	      this.introExpandable = 0;
	      this.mCommunityButAdd.setVisibility(8);
	      this.mCommunityButReduce.setVisibility(8);
	    }
	  }

	  private void initAlbum()
	  {
	    TabHost localTabHost1 = (TabHost)findViewById(2131492912);
	    this.mTab = localTabHost1;
	    LocalActivityManager localLocalActivityManager = new LocalActivityManager(this, 1);
	    this.mTab.setup(localLocalActivityManager);
	    RelativeLayout localRelativeLayout1 = (RelativeLayout)LayoutInflater.from(this).inflate(2130903094, null);
	    TextView localTextView1 = (TextView)localRelativeLayout1.findViewById(2131493108);
	    String str1 = getString(2131361817);
	    localTextView1.setText(str1);
	    RelativeLayout localRelativeLayout2 = (RelativeLayout)LayoutInflater.from(this).inflate(2130903095, null);
	    TextView localTextView2 = (TextView)localRelativeLayout2.findViewById(2131493108);
	    String str2 = getString(2131361818);
	    localTextView2.setText(str2);
	    TabHost localTabHost2 = this.mTab;
	    TabHost localTabHost3 = this.mTab;
	    String str3 = getString(2131361817);
	    TabHost.TabSpec localTabSpec1 = localTabHost3.newTabSpec(str3).setIndicator(localRelativeLayout1).setContent(2131492913);
	    localTabHost2.addTab(localTabSpec1);
	    TabHost localTabHost4 = this.mTab;
	    TabHost localTabHost5 = this.mTab;
	    String str4 = getString(2131361818);
	    TabHost.TabSpec localTabSpec2 = localTabHost5.newTabSpec(str4).setIndicator(localRelativeLayout2).setContent(2131492913);
	    localTabHost4.addTab(localTabSpec2);
	    Bitmap localBitmap1 = ((BitmapDrawable)getResources().getDrawable(2130837692)).getBitmap();
	    this.mSampleBitmap = localBitmap1;
	    ImageView localImageView1 = (ImageView)findViewById(2131492915);
	    this.mTabImageView = localImageView1;
	    ImageView localImageView2 = this.mTabImageView;
	    Bitmap localBitmap2 = this.mSampleBitmap;
	    localImageView2.setImageBitmap(localBitmap2);
	    Bitmap localBitmap3 = this.mSampleBitmap;
	    this.mLandBitmap = localBitmap3;
	    Bitmap localBitmap4 = this.mSampleBitmap;
	    this.mFlatBitmap = localBitmap4;
	    this.mTab.setCurrentTab(1);
	    this.mTab.setCurrentTab(0);
	  }

	  private void initAroundControlLayout()
	  {
	    this.mRoundLayout.setVisibility(8);
	    ImageView localImageView1 = this.mRoundButAdd;
	    CommunityDetailActivity.9 local9 = new CommunityDetailActivity.9(this);
	    localImageView1.setOnClickListener(local9);
	    ImageView localImageView2 = this.mRoundButReduce;
	    CommunityDetailActivity.10 local10 = new CommunityDetailActivity.10(this);
	    localImageView2.setOnClickListener(local10);
	  }

	  private void initBaseView()
	  {
	    View localView = LayoutInflater.from(this).inflate(2130903048, null);
	    StringBuffer localStringBuffer1 = new StringBuffer();
	    TextView localTextView1 = (TextView)localView.findViewById(2131492930);
	    String str1 = this.mDetail.name;
	    localTextView1.setText(str1);
	    TextView localTextView2 = (TextView)localView.findViewById(2131492933);
	    int m = localStringBuffer1.length();
	    StringBuffer localStringBuffer2 = localStringBuffer1.delete(0, m);
	    StringBuffer localStringBuffer3 = localStringBuffer1.append("<font>");
	    String str2 = getString(2131361953);
	    StringBuffer localStringBuffer4 = localStringBuffer1.append(str2);
	    StringBuffer localStringBuffer5 = localStringBuffer1.append(" ");
	    StringBuffer localStringBuffer6 = localStringBuffer1.append("</font>");
	    StringBuffer localStringBuffer7 = localStringBuffer1.append("<font color=\"#842c01\">");
	    int n = this.mSourceCount;
	    StringBuffer localStringBuffer8 = localStringBuffer1.append(n);
	    StringBuffer localStringBuffer9 = localStringBuffer1.append("</font>");
	    String str3 = getString(2131361954);
	    StringBuffer localStringBuffer10 = localStringBuffer1.append(str3);
	    Spanned localSpanned1 = Html.fromHtml(localStringBuffer1.toString());
	    localTextView2.setText(localSpanned1);
	    TextView localTextView3 = (TextView)localView.findViewById(2131492931);
	    int i2 = localStringBuffer1.length();
	    StringBuffer localStringBuffer11 = localStringBuffer1.delete(0, i2);
	    String str4 = getString(2131361949);
	    StringBuffer localStringBuffer12 = localStringBuffer1.append(str4);
	    StringBuffer localStringBuffer13 = localStringBuffer1.append(" ");
	    label352: label631: int i;
	    if (this.mPrice == 0)
	    {
	      String str5 = getString(2131361824);
	      StringBuffer localStringBuffer14 = localStringBuffer1.append(str5);
	      String str6 = localStringBuffer1.toString();
	      localTextView3.setText(str6);
	      localTextView3 = (TextView)localView.findViewById(2131492932);
	      int i3 = localStringBuffer1.length();
	      StringBuffer localStringBuffer15 = localStringBuffer1.delete(0, i3);
	      String str7 = getString(2131361955);
	      StringBuffer localStringBuffer16 = localStringBuffer1.append(str7);
	      StringBuffer localStringBuffer17 = localStringBuffer1.append(" ");
	      if (this.mDetail.finish_time == null)
	        break label2051;
	      String str8 = this.mDetail.finish_time;
	      StringBuffer localStringBuffer18 = localStringBuffer1.append(str8);
	      String str9 = localStringBuffer1.toString();
	      localTextView3.setText(str9);
	      localTextView3 = (TextView)localView.findViewById(2131492934);
	      int i4 = localStringBuffer1.length();
	      StringBuffer localStringBuffer19 = localStringBuffer1.delete(0, i4);
	      String str10 = getString(2131361956);
	      StringBuffer localStringBuffer20 = localStringBuffer1.append(str10);
	      StringBuffer localStringBuffer21 = localStringBuffer1.append(" ");
	      if (this.mDetail.decoration_info == null)
	        break label2071;
	      String str11 = this.mDetail.decoration_info;
	      StringBuffer localStringBuffer22 = localStringBuffer1.append(str11);
	      label445: String str12 = localStringBuffer1.toString();
	      localTextView3.setText(str12);
	      localTextView3 = (TextView)localView.findViewById(2131492935);
	      int i5 = localStringBuffer1.length();
	      StringBuffer localStringBuffer23 = localStringBuffer1.delete(0, i5);
	      String str13 = getString(2131361961);
	      StringBuffer localStringBuffer24 = localStringBuffer1.append(str13);
	      StringBuffer localStringBuffer25 = localStringBuffer1.append(" ");
	      if (this.mDetail.property_type == null)
	        break label2091;
	      String str14 = this.mDetail.property_type;
	      StringBuffer localStringBuffer26 = localStringBuffer1.append(str14);
	      label538: String str15 = localStringBuffer1.toString();
	      localTextView3.setText(str15);
	      localTextView3 = (TextView)localView.findViewById(2131492936);
	      int i6 = localStringBuffer1.length();
	      StringBuffer localStringBuffer27 = localStringBuffer1.delete(0, i6);
	      String str16 = getString(2131361964);
	      StringBuffer localStringBuffer28 = localStringBuffer1.append(str16);
	      StringBuffer localStringBuffer29 = localStringBuffer1.append(" ");
	      if (this.mDetail.floor_area_ratio == null)
	        break label2111;
	      String str17 = this.mDetail.floor_area_ratio;
	      StringBuffer localStringBuffer30 = localStringBuffer1.append(str17);
	      String str18 = localStringBuffer1.toString();
	      localTextView3.setText(str18);
	      localTextView3 = (TextView)localView.findViewById(2131492937);
	      int i7 = localStringBuffer1.length();
	      StringBuffer localStringBuffer31 = localStringBuffer1.delete(0, i7);
	      String str19 = getString(2131361959);
	      StringBuffer localStringBuffer32 = localStringBuffer1.append(str19);
	      StringBuffer localStringBuffer33 = localStringBuffer1.append(" ");
	      if (this.mDetail.developer == null)
	        break label2131;
	      String str20 = this.mDetail.developer;
	      StringBuffer localStringBuffer34 = localStringBuffer1.append(str20);
	      label724: String str21 = localStringBuffer1.toString();
	      localTextView3.setText(str21);
	      this.mLinearLayout.addView(localView);
	      if ((this.mDetail.intro != null) && (this.mDetail.intro.trim().length() != 0))
	        break label2151;
	      this.introExpandable = 0;
	      TextView localTextView4 = this.mIntroTextView;
	      String str22 = getString(2131361982);
	      localTextView4.setText(str22);
	      this.mCommunityButAdd.setVisibility(8);
	      this.mCommunityButReduce.setVisibility(8);
	      label817: localTextView3 = (TextView)findViewById(2131492945);
	      String str23 = this.mDetail.primarySchool;
	      localTextView3.setText(str23);
	      if ((this.mDetail.primarySchool != null) && (this.mDetail.primarySchool.trim().length() != 0))
	        break label2431;
	      i = 0 + 1;
	      localTextView3.setVisibility(8);
	      ((TextView)findViewById(2131492944)).setVisibility(8);
	    }
	    while (true)
	    {
	      localTextView3 = (TextView)findViewById(2131492947);
	      String str24 = this.mDetail.kindergarten;
	      localTextView3.setText(str24);
	      if ((this.mDetail.kindergarten == null) || (this.mDetail.kindergarten.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492946)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492949);
	      String str25 = this.mDetail.postOffice;
	      localTextView3.setText(str25);
	      if ((this.mDetail.postOffice == null) || (this.mDetail.postOffice.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492948)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492951);
	      String str26 = this.mDetail.bank;
	      localTextView3.setText(str26);
	      if ((this.mDetail.bank == null) || (this.mDetail.bank.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492950)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492953);
	      String str27 = this.mDetail.medicalStation;
	      localTextView3.setText(str27);
	      if ((this.mDetail.medicalStation == null) || (this.mDetail.medicalStation.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492952)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492955);
	      String str28 = this.mDetail.school;
	      localTextView3.setText(str28);
	      if ((this.mDetail.school == null) || (this.mDetail.school.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492954)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492957);
	      String str29 = this.mDetail.businessDistrict;
	      localTextView3.setText(str29);
	      if ((this.mDetail.businessDistrict == null) || (this.mDetail.businessDistrict.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492956)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492959);
	      String str30 = this.mDetail.hospital;
	      localTextView3.setText(str30);
	      if ((this.mDetail.hospital == null) || (this.mDetail.hospital.trim().length() == 0))
	      {
	        i += 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492958)).setVisibility(8);
	      }
	      if (i == 8)
	      {
	        this.sourroundingsVisible = 0;
	        ((LinearLayout)findViewById(2131492921)).setVisibility(8);
	      }
	      localTextView3 = (TextView)findViewById(2131492962);
	      String str31 = this.mDetail.bus;
	      localTextView3.setText(str31);
	      if ((this.mDetail.bus == null) || (this.mDetail.bus.trim().length() == 0))
	      {
	        i = 0 + 1;
	        localTextView3.setVisibility(8);
	        ((TextView)findViewById(2131492961)).setVisibility(8);
	      }
	      while (true)
	      {
	        localTextView3 = (TextView)findViewById(2131492964);
	        String str32 = this.mDetail.subway;
	        localTextView3.setText(str32);
	        if ((this.mDetail.subway == null) || (this.mDetail.subway.trim().length() == 0))
	        {
	          i += 1;
	          localTextView3.setVisibility(8);
	          ((TextView)findViewById(2131492963)).setVisibility(8);
	        }
	        if (i == 2)
	        {
	          this.trafficVisible = 0;
	          ((LinearLayout)findViewById(2131492925)).setVisibility(8);
	        }
	        localTextView3 = (TextView)findViewById(2131492909);
	        String str33 = getString(2131361968);
	        localTextView3.setText(str33);
	        Display localDisplay = getWindowManager().getDefaultDisplay();
	        StringBuilder localStringBuilder1 = new StringBuilder().append("width=");
	        int i8 = localDisplay.getWidth();
	        String str34 = i8;
	        Rent.MyLog("", str34);
	        float f = localDisplay.getWidth() - 60;
	        int i9 = UIUtils.px2dip(this, f);
	        int k = (int)UIUtils.dip2Px(this, 130.0F);
	        int i10 = localDisplay.getWidth() - 60;
	        WebView localWebView = (WebView)findViewById(2131492911);
	        localWebView.setInitialScale(100);
	        label1873: int i1;
	        if ((this.mDetail.priceChaturl == null) || (this.mDetail.housePrice == 0))
	        {
	          ((ImageView)findViewById(2131492905)).setVisibility(8);
	          LinearLayout localLinearLayout = (LinearLayout)findViewById(2131492910);
	          localWebView.setVisibility(8);
	          localTextView3.setVisibility(8);
	          localLinearLayout.setVisibility(8);
	          if (this.mDetail.picShijing.size() == 0)
	            break label2229;
	          i1 = 1;
	          label1890: this.hasLandscape = i1;
	          if (this.mDetail.picHuxing.size() == 0)
	            break label2236;
	          i1 = 1;
	          label1913: this.hasFlat_type = i1;
	          if ((this.hasLandscape) || (this.hasFlat_type))
	            break label2243;
	          this.mTab.setVisibility(8);
	          this.mTabImageView.setVisibility(8);
	        }
	        while (true)
	        {
	          ((ScrollView)findViewById(2131492906)).setVisibility(0);
	          return;
	          StringBuffer localStringBuffer35 = k.append("<font color=\"#469405\">");
	          int i11 = this.mPrice;
	          StringBuffer localStringBuffer36 = k.append(i11);
	          String str35 = getString(2131361822);
	          StringBuffer localStringBuffer37 = k.append(str35);
	          StringBuffer localStringBuffer38 = k.append("</font>");
	          String str36 = getString(2131361950);
	          StringBuffer localStringBuffer39 = k.append(str36);
	          Spanned localSpanned2 = Html.fromHtml(k.toString());
	          i1.setText(localSpanned2);
	          break;
	          label2051: String str37 = getString(2131361830);
	          StringBuffer localStringBuffer40 = k.append(str37);
	          break label352;
	          label2071: String str38 = getString(2131361830);
	          StringBuffer localStringBuffer41 = k.append(str38);
	          break label445;
	          label2091: String str39 = getString(2131361830);
	          StringBuffer localStringBuffer42 = k.append(str39);
	          break label538;
	          label2111: String str40 = getString(2131361830);
	          StringBuffer localStringBuffer43 = k.append(str40);
	          break label631;
	          label2131: String str41 = getString(2131361830);
	          StringBuffer localStringBuffer44 = k.append(str41);
	          break label724;
	          label2151: String str42 = this.mDetail.intro;
	          fillIntro(str42);
	          break label817;
	          StringBuilder localStringBuilder2 = new StringBuilder();
	          String str43 = this.mDetail.priceChaturl;
	          String str44 = str43 + "&chs=" + i10 + "x" + k;
	          localWebView.loadUrl(str44);
	          break label1873;
	          label2229: i1 = 0;
	          break label1890;
	          label2236: i1 = 0;
	          break label1913;
	          label2243: String[] arrayOfString1 = new String[this.mDetail.picShijing.size()];
	          this.landscapes = arrayOfString1;
	          ArrayList localArrayList1 = this.mDetail.picShijing;
	          String[] arrayOfString2 = this.landscapes;
	          Object[] arrayOfObject1 = localArrayList1.toArray(arrayOfString2);
	          String[] arrayOfString3 = new String[this.mDetail.picHuxing.size()];
	          this.flatTypes = arrayOfString3;
	          ArrayList localArrayList2 = this.mDetail.picHuxing;
	          String[] arrayOfString4 = this.flatTypes;
	          Object[] arrayOfObject2 = localArrayList2.toArray(arrayOfString4);
	          if (this.hasLandscape)
	          {
	            String str45 = this.landscapes[0];
	            downBmp(str45, 0);
	          }
	          if (this.hasFlat_type)
	          {
	            String str46 = this.flatTypes[0];
	            downBmp(str46, 1);
	          }
	          TabHost localTabHost = this.mTab;
	          CommunityDetailActivity.4 local4 = new CommunityDetailActivity.4(this);
	          localTabHost.setOnTabChangedListener(local4);
	          ImageView localImageView = this.mTabImageView;
	          CommunityDetailActivity.5 local5 = new CommunityDetailActivity.5(this);
	          localImageView.setOnClickListener(local5);
	        }
	        j = 0;
	      }
	      label2431: int j = 0;
	    }
	  }

	  private void initControlView()
	  {
	    ImageView localImageView1 = (ImageView)findViewById(2131492918);
	    this.mCommunityButAdd = localImageView1;
	    ImageView localImageView2 = (ImageView)findViewById(2131492919);
	    this.mCommunityButReduce = localImageView2;
	    TextView localTextView = (TextView)findViewById(2131492920);
	    this.mIntroTextView = localTextView;
	    TableLayout localTableLayout = (TableLayout)findViewById(2131492943);
	    this.mRoundLayout = localTableLayout;
	    ImageView localImageView3 = (ImageView)findViewById(2131492922);
	    this.mRoundButAdd = localImageView3;
	    ImageView localImageView4 = (ImageView)findViewById(2131492923);
	    this.mRoundButReduce = localImageView4;
	    ImageView localImageView5 = (ImageView)findViewById(2131492924);
	    this.mRoundLine = localImageView5;
	    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131492960);
	    this.mTrafficLayout = localLinearLayout;
	    ImageView localImageView6 = (ImageView)findViewById(2131492926);
	    this.mTrafficButAdd = localImageView6;
	    ImageView localImageView7 = (ImageView)findViewById(2131492927);
	    this.mTrafficButReduce = localImageView7;
	    ImageView localImageView8 = (ImageView)findViewById(2131492928);
	    this.mTrafficLine = localImageView8;
	    initIntroControlLayout();
	    initAroundControlLayout();
	    initTrafficLayout();
	  }

	  private void initIntroControlLayout()
	  {
	    ImageView localImageView1 = this.mCommunityButAdd;
	    CommunityDetailActivity.7 local7 = new CommunityDetailActivity.7(this);
	    localImageView1.setOnClickListener(local7);
	    ImageView localImageView2 = this.mCommunityButReduce;
	    CommunityDetailActivity.8 local8 = new CommunityDetailActivity.8(this);
	    localImageView2.setOnClickListener(local8);
	  }

	  private void initTabImage(int paramInt)
	  {
	    if (paramInt == 1)
	    {
	      ImageView localImageView1 = this.mTabImageView;
	      Bitmap localBitmap1 = this.mFlatBitmap;
	      localImageView1.setImageBitmap(localBitmap1);
	    }
	    while (true)
	    {
	      return;
	      if (paramInt == 0)
	      {
	        ImageView localImageView2 = this.mTabImageView;
	        Bitmap localBitmap2 = this.mLandBitmap;
	        localImageView2.setImageBitmap(localBitmap2);
	        continue;
	      }
	    }
	  }

	  private void initTrafficLayout()
	  {
	    this.mTrafficLayout.setVisibility(8);
	    ImageView localImageView1 = this.mTrafficButAdd;
	    CommunityDetailActivity.11 local11 = new CommunityDetailActivity.11(this);
	    localImageView1.setOnClickListener(local11);
	    ImageView localImageView2 = this.mTrafficButReduce;
	    CommunityDetailActivity.12 local12 = new CommunityDetailActivity.12(this);
	    localImageView2.setOnClickListener(local12);
	  }

	  private void initView()
	  {
	    Bundle localBundle = getIntent().getExtras();
	    String str1 = localBundle.getString("city");
	    this.mCity = str1;
	    String str2 = localBundle.getString("name");
	    this.mName = str2;
	    int i = localBundle.getInt("sourcecoount");
	    this.mSourceCount = i;
	    double d1 = localBundle.getDouble("latitude");
	    this.mLat = d1;
	    double d2 = localBundle.getDouble("longitude");
	    this.mLon = d2;
	    int j = localBundle.getInt("price");
	    this.mPrice = j;
	    long l = localBundle.getLong("group_id");
	    this.mGroupId = l;
	    String str3 = localBundle.getString("image");
	    this.mImage = str3;
	    ProgressBar localProgressBar = (ProgressBar)findViewById(2131492929);
	    this.mProgressbar = localProgressBar;
	    ImageView localImageView1 = (ImageView)findViewById(2131492876);
	    this.mBack = localImageView1;
	    ImageView localImageView2 = this.mBack;
	    CommunityDetailActivity.1 local1 = new CommunityDetailActivity.1(this);
	    localImageView2.setOnClickListener(local1);
	    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131492907);
	    this.mLinearLayout = localLinearLayout;
	    Button localButton1 = (Button)findViewById(2131492904);
	    this.mMapNav = localButton1;
	    if (!Rent.isAvailableGoogleMap())
	      this.mMapNav.setEnabled(0);
	    Button localButton2 = this.mMapNav;
	    CommunityDetailActivity.2 local2 = new CommunityDetailActivity.2(this);
	    localButton2.setOnClickListener(local2);
	    initAlbum();
	    CommunityDetailActivity.3 local3 = new CommunityDetailActivity.3(this);
	    CommunityDetailHandler localCommunityDetailHandler = new CommunityDetailHandler(local3);
	    String str4 = this.mCity;
	    String str5 = this.mName;
	    new CommunityDetailThread(localCommunityDetailHandler, str4, str5).start();
	  }

	  private void setArroundLayoutVisible(boolean paramBoolean)
	  {
	    if (!this.sourroundingsVisible);
	    while (true)
	    {
	      return;
	      if (paramBoolean)
	      {
	        this.mRoundButAdd.setVisibility(8);
	        this.mRoundButReduce.setVisibility(0);
	        this.mRoundLayout.setVisibility(0);
	        this.mRoundLine.setVisibility(0);
	        continue;
	      }
	      this.mRoundButReduce.setVisibility(8);
	      this.mRoundButAdd.setVisibility(0);
	      this.mRoundLayout.setVisibility(8);
	      this.mRoundLine.setVisibility(8);
	    }
	  }

	  private void setIntroLayoutVisible(boolean paramBoolean)
	  {
	    if (!this.introExpandable);
	    while (true)
	    {
	      return;
	      if (paramBoolean)
	      {
	        TextView localTextView1 = this.mIntroTextView;
	        String str1 = this.mIntro;
	        localTextView1.setText(str1);
	        this.mCommunityButAdd.setVisibility(8);
	        this.mCommunityButReduce.setVisibility(0);
	        continue;
	      }
	      TextView localTextView2 = this.mIntroTextView;
	      String str2 = this.mShortIntro;
	      localTextView2.setText(str2);
	      this.mCommunityButAdd.setVisibility(0);
	      this.mCommunityButReduce.setVisibility(8);
	    }
	  }

	  private void setTrafficLayoutVisible(boolean paramBoolean)
	  {
	    if (!this.trafficVisible);
	    while (true)
	    {
	      return;
	      if (paramBoolean)
	      {
	        this.mTrafficButAdd.setVisibility(8);
	        this.mTrafficButReduce.setVisibility(0);
	        this.mTrafficLayout.setVisibility(0);
	        this.mTrafficLine.setVisibility(0);
	        continue;
	      }
	      this.mTrafficButReduce.setVisibility(8);
	      this.mTrafficButAdd.setVisibility(0);
	      this.mTrafficLayout.setVisibility(8);
	      this.mTrafficLine.setVisibility(8);
	    }
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903047);
	    initControlView();
	    initView();
	    MobclickAgent.onEvent(this, "viewcommunitydetail");
	  }

	  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	  {
	    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
	      finish();
	    return false;
	  }

	  public void onPause()
	  {
	    super.onPause();
	    MobclickAgent.onPause(this);
	  }

	  public void onResume()
	  {
	    super.onResume();
	    MobclickAgent.onResume(this);
	  }
	  
}
