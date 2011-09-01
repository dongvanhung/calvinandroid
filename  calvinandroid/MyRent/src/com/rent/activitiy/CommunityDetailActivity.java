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
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rent.R;
import com.rent.Rent;
import com.rent.UIUtils;
import com.rent.data.CommunityDetail;
import com.rent.handler.CommunityDetailHandler;
import com.rent.listener.CommunityDetailListener;
import com.rent.thread.CommunityDetailThread;

public class CommunityDetailActivity extends Activity {

	public static final int ABSTRICT_MIN_LENGTH = 50;
	private static int CONTROL_ARROUND = 0;
	private static int CONTROL_INTOR = 0;
	private static int CONTROL_TRAFFIC = 0;
	static final int TAB_FLAT_TYPE = 1;
	static final int TAB_LANDSCAPE = 1;
	private String[] flatTypes = null;
	private boolean hasFlat_type = false;
	private boolean hasLandscape = false;
	private boolean introExpandable = true;
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
	private boolean sourroundingsVisible = true;
	private boolean trafficVisible = true;

	static {
		CONTROL_ARROUND = 2;
		CONTROL_TRAFFIC = 3;
	}

	private void closeOtherLayout(int paramInt) {
		int i = CONTROL_INTOR;
		if (paramInt == i) {
			setIntroLayoutVisible(true);
			setArroundLayoutVisible(false);
			setTrafficLayoutVisible(false);
		} else {
			int j = CONTROL_ARROUND;
			if (paramInt == j) {
				setIntroLayoutVisible(false);
				setArroundLayoutVisible(true);
				setTrafficLayoutVisible(false);
			}
			int k = CONTROL_TRAFFIC;
			if (paramInt != k) {
			}
			setIntroLayoutVisible(false);
			setArroundLayoutVisible(false);
			setTrafficLayoutVisible(true);
		}
	}

	private void downBmp(String paramString, int paramInt) {
		new CommunityDetailActivity6(this, paramString, paramInt).start();
	}

	final class CommunityDetailActivity6 extends Thread {
		private CommunityDetailActivity activity;
		private String paramS;
		private int paramInt;

		public CommunityDetailActivity6(CommunityDetailActivity activity,
				String paramS, int paramInt) {
			this.activity = activity;
			this.paramS = paramS;
			this.paramInt = paramInt;
		}

		public void run() {
			
			int i = 3;
//		    while (i > 0)
		      try
		      {
		        byte[] arrayOfByte = Rent.downLoadImage(paramS);
		        int j = CommunityDetailActivity.this.mLandBitmap.getHeight();
		        int k = CommunityDetailActivity.this.mLandBitmap.getWidth();
		        if (paramInt == 1)
		        {
		          Bitmap localBitmap1 = Rent.decodeBitmap(arrayOfByte, k, j);
//		          Bitmap localBitmap2 = CommunityDetailActivity.access$1502(localCommunityDetailActivity1, localBitmap1);
		        }
		        else
		        {
		          CommunityDetailActivity61 local1 = new CommunityDetailActivity61();
		          CommunityDetailActivity.this.runOnUiThread(local1);
		        }
		      }
		      catch (Exception localException)
		      {
		        localException.printStackTrace();
		      }
//		  }
			 
		}
	}

	final class CommunityDetailActivity61 implements Runnable
	{
		
	public void run()
	{
	  /*CommunityDetailActivity localCommunityDetailActivity = this.this$1.this$0;
	  int i = CommunityDetailActivity.access$1000(this.this$1.this$0);
	  CommunityDetailActivity.access$1100(localCommunityDetailActivity, i);*/
	}
	}

	private void fillIntro(String paramString) {
		this.mIntro = paramString;
		if (paramString.length() > 50) {
			String str1 = paramString.substring(0, 49);
			this.mShortIntro = str1;
			TextView localTextView1 = this.mIntroTextView;
			String str2 = this.mShortIntro;
			localTextView1.setText(str2);
		} else {
			TextView localTextView2 = this.mIntroTextView;
			String str3 = this.mIntro;
			localTextView2.setText(str3);
			this.introExpandable = false;
			this.mCommunityButAdd.setVisibility(8);
			this.mCommunityButReduce.setVisibility(8);
		}
	}

	private void initAlbum() {
		TabHost localTabHost1 = (TabHost) findViewById(R.id.tabhost);
		this.mTab = localTabHost1;
		LocalActivityManager localLocalActivityManager = new LocalActivityManager(
				this, true);
		this.mTab.setup(localLocalActivityManager);
		RelativeLayout localRelativeLayout1 = (RelativeLayout) LayoutInflater
				.from(this).inflate(R.layout.minitab_left, null);
		TextView localTextView1 = (TextView) localRelativeLayout1
				.findViewById(R.id.minitab_label);
		String str1 = getString(R.string.community_landscape);
		localTextView1.setText(str1);
		RelativeLayout localRelativeLayout2 = (RelativeLayout) LayoutInflater
				.from(this).inflate(R.layout.minitab_right, null);
		TextView localTextView2 = (TextView) localRelativeLayout2
				.findViewById(R.id.minitab_label);
		String str2 = getString(R.string.community_flat_type);
		localTextView2.setText(str2);
		TabHost localTabHost2 = this.mTab;
		TabHost localTabHost3 = this.mTab;
		String str3 = getString(R.string.community_landscape);
		TabHost.TabSpec localTabSpec1 = localTabHost3.newTabSpec(str3)
				.setIndicator(localRelativeLayout1).setContent(R.id.pic_container);
		localTabHost2.addTab(localTabSpec1);
		TabHost localTabHost4 = this.mTab;
		TabHost localTabHost5 = this.mTab;
		String str4 = getString(R.string.community_flat_type);
		TabHost.TabSpec localTabSpec2 = localTabHost5.newTabSpec(str4)
				.setIndicator(localRelativeLayout2).setContent(R.id.pic_container);
		localTabHost4.addTab(localTabSpec2);
		Bitmap localBitmap1 = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.testbig)).getBitmap();
		this.mSampleBitmap = localBitmap1;
		ImageView localImageView1 = (ImageView) findViewById(R.id.flat_landscape_preview);
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

	private void initAroundControlLayout() {
		this.mRoundLayout.setVisibility(8);
		ImageView localImageView1 = this.mRoundButAdd;
		localImageView1.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * CommunityDetailActivity.access$2000(this.this$0, 1);
				 * CommunityDetailActivity localCommunityDetailActivity =
				 * this.this$0; int i = CommunityDetailActivity.access$2100();
				 * CommunityDetailActivity
				 * .access$1900(localCommunityDetailActivity, i);
				 * MobclickAgent.onEvent(this.this$0, "community_meta",
				 * "sourroundings");
				 */
			}
		});
		ImageView localImageView2 = this.mRoundButReduce;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				// CommunityDetailActivity.access$2000(this.this$0, 0);
			}
		});
	}

	private void initBaseView() {
		View localView = LayoutInflater.from(this).inflate(R.layout.community_detail_baseinfo, null);
		StringBuffer localStringBuffer1 = new StringBuffer();
		TextView localTextView1 = (TextView) localView.findViewById(R.id.name);
		String str1 = this.mDetail.name;
		localTextView1.setText(str1);
		TextView localTextView2 = (TextView) localView.findViewById(R.id.source);
		int m = localStringBuffer1.length();
		StringBuffer localStringBuffer2 = localStringBuffer1.delete(0, m);
		StringBuffer localStringBuffer3 = localStringBuffer1.append("<font>");
		String str2 = getString(R.string.house_source);
		StringBuffer localStringBuffer4 = localStringBuffer1.append(str2);
		StringBuffer localStringBuffer5 = localStringBuffer1.append(" ");
		StringBuffer localStringBuffer6 = localStringBuffer1.append("</font>");
		StringBuffer localStringBuffer7 = localStringBuffer1
				.append("<font color=\"#842c01\">");
		int n = this.mSourceCount;
		StringBuffer localStringBuffer8 = localStringBuffer1.append(n);
		StringBuffer localStringBuffer9 = localStringBuffer1.append("</font>");
		String str3 = getString(R.string.house_source_flat);
		StringBuffer localStringBuffer10 = localStringBuffer1.append(str3);
		Spanned localSpanned1 = Html.fromHtml(localStringBuffer1.toString());
		localTextView2.setText(localSpanned1);
		TextView localTextView3 = (TextView) localView.findViewById(R.id.price);
		int i2 = localStringBuffer1.length();
		StringBuffer localStringBuffer11 = localStringBuffer1.delete(0, i2);
		String str4 = getString(R.string.rent_average_phrase);
		StringBuffer localStringBuffer12 = localStringBuffer1.append(str4);
		StringBuffer localStringBuffer13 = localStringBuffer1.append(" ");
		int i = 0;
		if (this.mPrice == 0) {
			String str5 = getString(R.string.unavailable);
			StringBuffer localStringBuffer14 = localStringBuffer1.append(str5);
			String str6 = localStringBuffer1.toString();
			localTextView3.setText(str6);
			localTextView3 = (TextView) localView.findViewById(R.id.build_time);
			int i3 = localStringBuffer1.length();
			StringBuffer localStringBuffer15 = localStringBuffer1.delete(0, i3);
			String str7 = getString(R.string.build_time);
			StringBuffer localStringBuffer16 = localStringBuffer1.append(str7);
			StringBuffer localStringBuffer17 = localStringBuffer1.append(" ");
			if (this.mDetail.finish_time == null){}
			String str8 = this.mDetail.finish_time;
			StringBuffer localStringBuffer18 = localStringBuffer1.append(str8);
			String str9 = localStringBuffer1.toString();
			localTextView3.setText(str9);
			localTextView3 = (TextView) localView.findViewById(R.id.decoration_info);
			int i4 = localStringBuffer1.length();
			StringBuffer localStringBuffer19 = localStringBuffer1.delete(0, i4);
			String str10 = getString(R.string.decoration_info);
			StringBuffer localStringBuffer20 = localStringBuffer1.append(str10);
			StringBuffer localStringBuffer21 = localStringBuffer1.append(" ");
			if (this.mDetail.decoration_info == null){}
			String str11 = this.mDetail.decoration_info;
			StringBuffer localStringBuffer22 = localStringBuffer1.append(str11);
			String str12 = localStringBuffer1.toString();
			localTextView3.setText(str12);
			localTextView3 = (TextView) localView.findViewById(R.id.floors);
			int i5 = localStringBuffer1.length();
			StringBuffer localStringBuffer23 = localStringBuffer1.delete(0, i5);
			String str13 = getString(R.string.property_type);
			StringBuffer localStringBuffer24 = localStringBuffer1.append(str13);
			StringBuffer localStringBuffer25 = localStringBuffer1.append(" ");
			if (this.mDetail.property_type == null){}
			String str14 = this.mDetail.property_type;
			StringBuffer localStringBuffer26 = localStringBuffer1.append(str14);
			String str15 = localStringBuffer1.toString();
			localTextView3.setText(str15);
			localTextView3 = (TextView) localView.findViewById(R.id.floor_area_ratio);
			int i6 = localStringBuffer1.length();
			StringBuffer localStringBuffer27 = localStringBuffer1.delete(0, i6);
			String str16 = getString(R.string.floor_area_ratio);
			StringBuffer localStringBuffer28 = localStringBuffer1.append(str16);
			StringBuffer localStringBuffer29 = localStringBuffer1.append(" ");
			if (this.mDetail.floor_area_ratio == null){}
			String str17 = this.mDetail.floor_area_ratio;
			StringBuffer localStringBuffer30 = localStringBuffer1.append(str17);
			String str18 = localStringBuffer1.toString();
			localTextView3.setText(str18);
			localTextView3 = (TextView) localView.findViewById(R.id.property_company);
			int i7 = localStringBuffer1.length();
			StringBuffer localStringBuffer31 = localStringBuffer1.delete(0, i7);
			String str19 = getString(R.string.developer);
			StringBuffer localStringBuffer32 = localStringBuffer1.append(str19);
			StringBuffer localStringBuffer33 = localStringBuffer1.append(" ");
			if (this.mDetail.developer == null){}
			String str20 = this.mDetail.developer;
			StringBuffer localStringBuffer34 = localStringBuffer1.append(str20);
			String str21 = localStringBuffer1.toString();
			localTextView3.setText(str21);
			this.mLinearLayout.addView(localView);
			
			if ((this.mDetail.intro != null)
					&& (this.mDetail.intro.trim().length() != 0)){}
			this.introExpandable = false;
			TextView localTextView4 = this.mIntroTextView;
			String str22 = getString(R.string.community_detail_intro_unavailable);
			localTextView4.setText(str22);
			this.mCommunityButAdd.setVisibility(8);
			this.mCommunityButReduce.setVisibility(8);
			label817: localTextView3 = (TextView) findViewById(R.id.community_detail_primarySchool_text);
			String str23 = this.mDetail.primarySchool;
			localTextView3.setText(str23);
			if ((this.mDetail.primarySchool != null)
					&& (this.mDetail.primarySchool.trim().length() != 0)){}
			i = 0 + 1;
			localTextView3.setVisibility(8);
			((TextView) findViewById(R.id.community_detail_primarySchool_label)).setVisibility(8);
		} else {
			localTextView3 = (TextView) findViewById(R.id.community_detail_kindergarten_text);
			String str24 = this.mDetail.kindergarten;
			localTextView3.setText(str24);
			if ((this.mDetail.kindergarten == null)
					|| (this.mDetail.kindergarten.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_kindergarten_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_postOffice_text);
			String str25 = this.mDetail.postOffice;
			localTextView3.setText(str25);
			if ((this.mDetail.postOffice == null)
					|| (this.mDetail.postOffice.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_postOffice_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_bank_text);
			String str26 = this.mDetail.bank;
			localTextView3.setText(str26);
			if ((this.mDetail.bank == null)
					|| (this.mDetail.bank.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_bank_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_medicalStation_text);
			String str27 = this.mDetail.medicalStation;
			localTextView3.setText(str27);
			if ((this.mDetail.medicalStation == null)
					|| (this.mDetail.medicalStation.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_medicalStation_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_school_text);
			String str28 = this.mDetail.school;
			localTextView3.setText(str28);
			if ((this.mDetail.school == null)
					|| (this.mDetail.school.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_school_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_businessDistrict_text);
			String str29 = this.mDetail.businessDistrict;
			localTextView3.setText(str29);
			if ((this.mDetail.businessDistrict == null)
					|| (this.mDetail.businessDistrict.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_businessDistrict_label)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_hospital_text);
			String str30 = this.mDetail.hospital;
			localTextView3.setText(str30);
			if ((this.mDetail.hospital == null)
					|| (this.mDetail.hospital.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_hospital_label)).setVisibility(8);
			}
			if (i == 8) {
				this.sourroundingsVisible = false;
				((LinearLayout) findViewById(R.id.sourroundings_total_layout)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(R.id.community_detail_bus_text);
			String str31 = this.mDetail.bus;
			localTextView3.setText(str31);
			if ((this.mDetail.bus == null)
					|| (this.mDetail.bus.trim().length() == 0)) {
				i = 0 + 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(R.id.community_detail_bus_label)).setVisibility(8);
			} else {
				localTextView3 = (TextView) findViewById(R.id.community_detail_subway_text);
				String str32 = this.mDetail.subway;
				localTextView3.setText(str32);
				if ((this.mDetail.subway == null)
						|| (this.mDetail.subway.trim().length() == 0)) {
					i += 1;
					localTextView3.setVisibility(8);
					((TextView) findViewById(R.id.community_detail_subway_label)).setVisibility(8);
				}
				if (i == 2) {
					this.trafficVisible = false;
					((LinearLayout) findViewById(R.id.traffic_total_layout)).setVisibility(8);
				}
				localTextView3 = (TextView) findViewById(R.id.price_chart);
				String str33 = getString(R.string.price_chart_trim);
				localTextView3.setText(str33);
				Display localDisplay = getWindowManager().getDefaultDisplay();
				StringBuilder localStringBuilder1 = new StringBuilder()
						.append("width=");
				int i8 = localDisplay.getWidth();
				String str34 = String.valueOf(i8);
				Rent.MyLog("", str34);
				float f = localDisplay.getWidth() - 60;
				int i9 = UIUtils.px2dip(this, f);
				int k = (int) UIUtils.dip2Px(this, 130.0F);
				int i10 = localDisplay.getWidth() - 60;
				WebView localWebView = (WebView) findViewById(R.id.chart);
				localWebView.setInitialScale(100);
				int i1;
				if ((this.mDetail.priceChaturl == null)
						|| (this.mDetail.housePrice == 0)) {
					((ImageView) findViewById(R.id.chart_split_line)).setVisibility(8);
					LinearLayout localLinearLayout = (LinearLayout) findViewById(R.id.chart_container);
					localWebView.setVisibility(8);
					localTextView3.setVisibility(8);
					localLinearLayout.setVisibility(8);
					if (this.mDetail.picShijing.size() == 0){}
					this.hasLandscape = true;
					if (this.mDetail.picHuxing.size() == 0){}
					this.hasFlat_type = true;
					if ((this.hasLandscape) || (this.hasFlat_type)){}
					this.mTab.setVisibility(8);
					this.mTabImageView.setVisibility(8);
					((ScrollView) findViewById(R.id.content_scrollview)).setVisibility(0);
				} else {
					StringBuffer kS = new StringBuffer("");
					StringBuffer localStringBuffer35 = kS
							.append("<font color=\"#469405\">");
					int i11 = this.mPrice;
					StringBuffer localStringBuffer36 = kS.append(i11);
					String str35 = getString(R.string.yuan);
					StringBuffer localStringBuffer37 = kS.append(str35);
					StringBuffer localStringBuffer38 = kS.append("</font>");
					String str36 = getString(R.string.rent_average_unit);
					StringBuffer localStringBuffer39 = kS.append(str36);
					Spanned localSpanned2 = Html.fromHtml(kS.toString());
					localTextView3.setText(localSpanned2);
					String str37 = getString(R.string.unknown);
					StringBuffer localStringBuffer40 = kS.append(str37);
					String str38 = getString(R.string.unknown);
					StringBuffer localStringBuffer41 = kS.append(str38);
					String str39 = getString(R.string.unknown);
					StringBuffer localStringBuffer42 = kS.append(str39);
					String str40 = getString(R.string.unknown);
					StringBuffer localStringBuffer43 = kS.append(str40);
					String str41 = getString(R.string.unknown);
					StringBuffer localStringBuffer44 = kS.append(str41);
					String str42 = this.mDetail.intro;
					fillIntro(str42);
					StringBuilder localStringBuilder2 = new StringBuilder();
					String str43 = this.mDetail.priceChaturl;
					String str44 = str43 + "&chs=" + i10 + "x" + k;
					localWebView.loadUrl(str44);
					i1 = 0;
					String[] arrayOfString1 = new String[this.mDetail.picShijing
							.size()];
					this.landscapes = arrayOfString1;
					ArrayList localArrayList1 = this.mDetail.picShijing;
					String[] arrayOfString2 = this.landscapes;
					Object[] arrayOfObject1 = localArrayList1
							.toArray(arrayOfString2);
					String[] arrayOfString3 = new String[this.mDetail.picHuxing
							.size()];
					this.flatTypes = arrayOfString3;
					ArrayList localArrayList2 = this.mDetail.picHuxing;
					String[] arrayOfString4 = this.flatTypes;
					Object[] arrayOfObject2 = localArrayList2
							.toArray(arrayOfString4);
					if (this.hasLandscape) {
						String str45 = this.landscapes[0];
//						downBmp(str45, 0);
					}
					if (this.hasFlat_type) {
						String str46 = this.flatTypes[0];
//						downBmp(str46, 1);
					}
					TabHost localTabHost = this.mTab;
					localTabHost
							.setOnTabChangedListener(new OnTabChangeListener() {
								public void onTabChanged(String paramString) {
									/*
									 * String str =
									 * this.this$0.getString(2131361817); if
									 * (paramString.equalsIgnoreCase(str)); for
									 * (int i = 0; ; i = 1) { int j =
									 * CommunityDetailActivity
									 * .access$1002(this.this$0, i);
									 * CommunityDetailActivity
									 * .access$1100(this.this$0, i); return; }
									 */
								}
							});
					ImageView localImageView = this.mTabImageView;
					localImageView.setOnClickListener(new OnClickListener() {
						public void onClick(View paramView) {
							/*
							 * int i = 1; if
							 * ((CommunityDetailActivity.access$1000
							 * (this.this$0) == i) &&
							 * ((CommunityDetailActivity.access$1200
							 * (this.this$0) == null) ||
							 * (CommunityDetailActivity
							 * .access$1200(this.this$0).length <= 0)))
							 * Toast.makeText(this.this$0, 2131361863,
							 * 1).show(); while (true) { return; if
							 * ((CommunityDetailActivity
							 * .access$1000(this.this$0) != 0) ||
							 * ((CommunityDetailActivity
							 * .access$1300(this.this$0) != null) &&
							 * (CommunityDetailActivity
							 * .access$1300(this.this$0).length > 0))) break;
							 * Toast.makeText(this.this$0, 2131361864,
							 * 1).show(); } CommunityDetailActivity
							 * localCommunityDetailActivity = this.this$0;
							 * Intent localIntent1 = new
							 * Intent(localCommunityDetailActivity,
							 * CommunityGalleryActivity.class); Bundle
							 * localBundle = new Bundle(); Object localObject1;
							 * Object localObject2; if
							 * (CommunityDetailActivity.access$1000(this.this$0)
							 * == 1) { String[] arrayOfString1 =
							 * CommunityDetailActivity.access$1200(this.this$0);
							 * String str1 = "flattype"; localObject1 =
							 * arrayOfString1; localObject2 = str1; } while
							 * (true) { localBundle.putStringArray(
							 * "bundle_community_detail_image", localObject1);
							 * StringBuilder localStringBuilder = new
							 * StringBuilder(); long l =
							 * CommunityDetailActivity.access$600(this.this$0);
							 * String str2 = l + "";
							 * localBundle.putString("bundle_community_id",
							 * str2);
							 * localBundle.putString("bundle_community_type",
							 * (String)localObject2); Intent localIntent2 =
							 * localIntent1.putExtras(localBundle);
							 * this.this$0.startActivity(localIntent1); break;
							 * if
							 * (CommunityDetailActivity.access$1000(this.this$0)
							 * == 0) { String[] arrayOfString2 =
							 * CommunityDetailActivity.access$1300(this.this$0);
							 * String str3 = "landspace"; localObject1 =
							 * arrayOfString2; localObject2 = str3; continue; }
							 * localObject2 = null; localObject1 = null; }
							 */
						}
					});
				}
			}
		}
		this.setIntroLayoutVisible(false);
		this.setTrafficLayoutVisible(false);
		this.setArroundLayoutVisible(false);
	}

	private void initControlView() {
		ImageView localImageView1 = (ImageView) findViewById(R.id.community_introduce_add_icon);
		this.mCommunityButAdd = localImageView1;
		ImageView localImageView2 = (ImageView) findViewById(R.id.community_introduce_reduce_icon);
		this.mCommunityButReduce = localImageView2;
		TextView localTextView = (TextView) findViewById(R.id.intro);
		this.mIntroTextView = localTextView;
		TableLayout localTableLayout = (TableLayout) findViewById(R.id.community_arround_condition);
		this.mRoundLayout = localTableLayout;
		ImageView localImageView3 = (ImageView) findViewById(R.id.around_condition_add_icon);
		this.mRoundButAdd = localImageView3;
		ImageView localImageView4 = (ImageView) findViewById(R.id.around_condition_reduce_icon);
		this.mRoundButReduce = localImageView4;
		ImageView localImageView5 = (ImageView) findViewById(R.id.around_condition_lines);
		this.mRoundLine = localImageView5;
		LinearLayout localLinearLayout = (LinearLayout) findViewById(R.id.traffic_layout);
		this.mTrafficLayout = localLinearLayout;
		ImageView localImageView6 = (ImageView) findViewById(R.id.traffic_add_icon);
		this.mTrafficButAdd = localImageView6;
		ImageView localImageView7 = (ImageView) findViewById(R.id.traffic_reduce_icon);
		this.mTrafficButReduce = localImageView7;
		ImageView localImageView8 = (ImageView) findViewById(R.id.traffic_lines);
		this.mTrafficLine = localImageView8;
		initIntroControlLayout();
		initAroundControlLayout();
		initTrafficLayout();
	}

	private void initIntroControlLayout() {
		ImageView localImageView1 = this.mCommunityButAdd;
		localImageView1.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * CommunityDetailActivity.access$1700(this.this$0, 1);
				 * CommunityDetailActivity localCommunityDetailActivity =
				 * this.this$0; int i = CommunityDetailActivity.access$1800();
				 * CommunityDetailActivity
				 * .access$1900(localCommunityDetailActivity, i);
				 * MobclickAgent.onEvent(this.this$0, "community_meta",
				 * "intro");
				 */
			}
		});
		ImageView localImageView2 = this.mCommunityButReduce;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				// CommunityDetailActivity.access$1700(this.this$0, 0);
			}
		});
	}

	private void initTabImage(int paramInt) {
		if (paramInt == 1) {
			ImageView localImageView1 = this.mTabImageView;
			Bitmap localBitmap1 = this.mFlatBitmap;
			localImageView1.setImageBitmap(localBitmap1);
		} else {
			if (paramInt == 0) {
				ImageView localImageView2 = this.mTabImageView;
				Bitmap localBitmap2 = this.mLandBitmap;
				localImageView2.setImageBitmap(localBitmap2);
			}
		}
	}

	private void initTrafficLayout() {
		this.mTrafficLayout.setVisibility(8);
		ImageView localImageView1 = this.mTrafficButAdd;
		localImageView1.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * CommunityDetailActivity.access$2200(this.this$0, 1);
				 * CommunityDetailActivity localCommunityDetailActivity =
				 * this.this$0; int i = CommunityDetailActivity.access$2300();
				 * CommunityDetailActivity
				 * .access$1900(localCommunityDetailActivity, i);
				 * MobclickAgent.onEvent(this.this$0, "community_meta",
				 * "traffic");
				 */
			}
		});
		ImageView localImageView2 = this.mTrafficButReduce;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				// CommunityDetailActivity.access$2200(this.this$0, 0);
			}
		});
	}

	private void initView() {
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
		ProgressBar localProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		this.mProgressbar = localProgressBar;
		ImageView localImageView1 = (ImageView) findViewById(R.id.back);
		this.mBack = localImageView1;
		ImageView localImageView2 = this.mBack;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				finish();
			}
		});
		LinearLayout localLinearLayout = (LinearLayout) findViewById(R.id.base_info);
		this.mLinearLayout = localLinearLayout;
		Button localButton1 = (Button) findViewById(R.id.map_navigate);
		this.mMapNav = localButton1;
		if (!Rent.isAvailableGoogleMap())
			this.mMapNav.setEnabled(false);
		Button localButton2 = this.mMapNav;
		localButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				/*
				 * MobclickAgent.onEvent(this.this$0, "community_navi"); if
				 * ((CommunityDetailActivity.access$000(this.this$0) == 0.0D) ||
				 * (CommunityDetailActivity.access$100(this.this$0) == 0.0D) ||
				 * (CommunityDetailActivity.access$000(this.this$0) == -1.0D) ||
				 * (CommunityDetailActivity.access$100(this.this$0) == -1.0D))
				 * Toast.makeText(this.this$0, 2131361988, 0).show(); while
				 * (true) { return; Bundle localBundle = new Bundle();
				 * localBundle.putInt("flag", 1); double d1 =
				 * CommunityDetailActivity.access$000(this.this$0);
				 * localBundle.putDouble("latitude", d1); double d2 =
				 * CommunityDetailActivity.access$100(this.this$0);
				 * localBundle.putDouble("longitude", d2); String str1 =
				 * CommunityDetailActivity.access$200(this.this$0);
				 * localBundle.putString("name", str1); int i =
				 * CommunityDetailActivity.access$300(this.this$0);
				 * localBundle.putInt("price", i); int j =
				 * CommunityDetailActivity.access$400(this.this$0);
				 * localBundle.putInt("sourcecoount", j); String str2 =
				 * CommunityDetailActivity.access$500(this.this$0);
				 * localBundle.putString("image", str2); long l =
				 * CommunityDetailActivity.access$600(this.this$0);
				 * localBundle.putLong("group_id", l); CommunityDetailActivity
				 * localCommunityDetailActivity = this.this$0; Intent
				 * localIntent1 = new Intent(localCommunityDetailActivity,
				 * ShowOnMyMap.class); Intent localIntent2 =
				 * localIntent1.putExtras(localBundle);
				 * this.this$0.startActivity(localIntent1); }
				 */
			}
		});
		initAlbum();
		CommunityDetailActivity3 local3 = new CommunityDetailActivity3();
		CommunityDetailHandler localCommunityDetailHandler = new CommunityDetailHandler(
				local3);
		String str4 = this.mCity;
		String str5 = this.mName;
		str4 = "上海";
		new CommunityDetailThread(localCommunityDetailHandler, str4, str5)
				.start();
	}

	final class CommunityDetailActivity3 implements CommunityDetailListener {
		public void handleCommunityDetail(boolean paramBoolean,
				CommunityDetail paramCommunityDetail) {
			
			if (true == paramBoolean)
		    {
				CommunityDetailActivity.this.mDetail = paramCommunityDetail;
				CommunityDetailActivity.this.initBaseView();
				CommunityDetailActivity.this.mProgressbar.setVisibility(8);
		    } else
		    {
		      String str = CommunityDetailActivity.this.getString(R.string.load_failed);
		      Toast.makeText(CommunityDetailActivity.this, str, 1).show();
		    }
		}
	}

	private void setArroundLayoutVisible(boolean paramBoolean) {
		if (!this.sourroundingsVisible) {
		} else {
			if (paramBoolean) {
				this.mRoundButAdd.setVisibility(8);
				this.mRoundButReduce.setVisibility(0);
				this.mRoundLayout.setVisibility(0);
				this.mRoundLine.setVisibility(0);
			} else {
				this.mRoundButReduce.setVisibility(8);
				this.mRoundButAdd.setVisibility(0);
				this.mRoundLayout.setVisibility(8);
				this.mRoundLine.setVisibility(8);
			}
		}
	}

	private void setIntroLayoutVisible(boolean paramBoolean) {
		if (!this.introExpandable) {
		} else {
			if (paramBoolean) {
				TextView localTextView1 = this.mIntroTextView;
				String str1 = this.mIntro;
				localTextView1.setText(str1);
				this.mCommunityButAdd.setVisibility(8);
				this.mCommunityButReduce.setVisibility(0);
			} else {
				TextView localTextView2 = this.mIntroTextView;
				String str2 = this.mShortIntro;
				localTextView2.setText(str2);
				this.mCommunityButAdd.setVisibility(0);
				this.mCommunityButReduce.setVisibility(8);
			}
		}
	}

	private void setTrafficLayoutVisible(boolean paramBoolean) {
		if (!this.trafficVisible) {
		} else {
			if (paramBoolean) {
				this.mTrafficButAdd.setVisibility(8);
				this.mTrafficButReduce.setVisibility(0);
				this.mTrafficLayout.setVisibility(0);
				this.mTrafficLine.setVisibility(0);
			} else {
				this.mTrafficButReduce.setVisibility(8);
				this.mTrafficButAdd.setVisibility(0);
				this.mTrafficLayout.setVisibility(8);
				this.mTrafficLine.setVisibility(8);
			}
		}
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
//		MobclickAgent.onError(this);
		boolean bool = requestWindowFeature(1);
		setContentView(R.layout.community_detail_activity);
		initControlView();
		initView();
//		MobclickAgent.onEvent(this, "viewcommunitydetail");
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
			finish();
		return false;
	}

	public void onPause() {
		super.onPause();
//		MobclickAgent.onPause(this);
	}

	public void onResume() {
		super.onResume();
//		MobclickAgent.onResume(this);
	}

}
