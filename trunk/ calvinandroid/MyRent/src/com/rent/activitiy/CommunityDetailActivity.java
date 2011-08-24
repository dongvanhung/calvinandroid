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

import com.rent.MobclickAgent;
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
			/*
			 * int i = 3; while (i > 0) try { byte[] arrayOfByte =
			 * Rent.downLoadImage(this.val$url); int j =
			 * CommunityDetailActivity.access$1400(this.this$0).getHeight(); int
			 * k = CommunityDetailActivity.access$1400(this.this$0).getWidth();
			 * if (this.val$whitTab == 1) { CommunityDetailActivity
			 * localCommunityDetailActivity1 = this.this$0; Bitmap localBitmap1
			 * = Rent.decodeBitmap(arrayOfByte, k, j); Bitmap localBitmap2 =
			 * CommunityDetailActivity
			 * .access$1502(localCommunityDetailActivity1, localBitmap1); }
			 * while (true) { CommunityDetailActivity
			 * localCommunityDetailActivity2 = this.this$0;
			 * CommunityDetailActivity.6.1 local1 = new
			 * CommunityDetailActivity.6.1(this);
			 * localCommunityDetailActivity2.runOnUiThread(local1); i = 0;
			 * break; if (this.val$whitTab != 0) continue;
			 * CommunityDetailActivity localCommunityDetailActivity3 =
			 * this.this$0; Bitmap localBitmap3 = Rent.decodeBitmap(arrayOfByte,
			 * k, j); Bitmap localBitmap4 =
			 * CommunityDetailActivity.access$1602(localCommunityDetailActivity3
			 * , localBitmap3); } } catch (Exception localException) { i += -1;
			 * localException.printStackTrace(); }
			 */
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
		TabHost localTabHost1 = (TabHost) findViewById(2131492912);
		this.mTab = localTabHost1;
		LocalActivityManager localLocalActivityManager = new LocalActivityManager(
				this, true);
		this.mTab.setup(localLocalActivityManager);
		RelativeLayout localRelativeLayout1 = (RelativeLayout) LayoutInflater
				.from(this).inflate(2130903094, null);
		TextView localTextView1 = (TextView) localRelativeLayout1
				.findViewById(2131493108);
		String str1 = getString(2131361817);
		localTextView1.setText(str1);
		RelativeLayout localRelativeLayout2 = (RelativeLayout) LayoutInflater
				.from(this).inflate(2130903095, null);
		TextView localTextView2 = (TextView) localRelativeLayout2
				.findViewById(2131493108);
		String str2 = getString(2131361818);
		localTextView2.setText(str2);
		TabHost localTabHost2 = this.mTab;
		TabHost localTabHost3 = this.mTab;
		String str3 = getString(2131361817);
		TabHost.TabSpec localTabSpec1 = localTabHost3.newTabSpec(str3)
				.setIndicator(localRelativeLayout1).setContent(2131492913);
		localTabHost2.addTab(localTabSpec1);
		TabHost localTabHost4 = this.mTab;
		TabHost localTabHost5 = this.mTab;
		String str4 = getString(2131361818);
		TabHost.TabSpec localTabSpec2 = localTabHost5.newTabSpec(str4)
				.setIndicator(localRelativeLayout2).setContent(2131492913);
		localTabHost4.addTab(localTabSpec2);
		Bitmap localBitmap1 = ((BitmapDrawable) getResources().getDrawable(
				2130837692)).getBitmap();
		this.mSampleBitmap = localBitmap1;
		ImageView localImageView1 = (ImageView) findViewById(2131492915);
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
		View localView = LayoutInflater.from(this).inflate(2130903048, null);
		StringBuffer localStringBuffer1 = new StringBuffer();
		TextView localTextView1 = (TextView) localView.findViewById(2131492930);
		String str1 = this.mDetail.name;
		localTextView1.setText(str1);
		TextView localTextView2 = (TextView) localView.findViewById(2131492933);
		int m = localStringBuffer1.length();
		StringBuffer localStringBuffer2 = localStringBuffer1.delete(0, m);
		StringBuffer localStringBuffer3 = localStringBuffer1.append("<font>");
		String str2 = getString(2131361953);
		StringBuffer localStringBuffer4 = localStringBuffer1.append(str2);
		StringBuffer localStringBuffer5 = localStringBuffer1.append(" ");
		StringBuffer localStringBuffer6 = localStringBuffer1.append("</font>");
		StringBuffer localStringBuffer7 = localStringBuffer1
				.append("<font color=\"#842c01\">");
		int n = this.mSourceCount;
		StringBuffer localStringBuffer8 = localStringBuffer1.append(n);
		StringBuffer localStringBuffer9 = localStringBuffer1.append("</font>");
		String str3 = getString(2131361954);
		StringBuffer localStringBuffer10 = localStringBuffer1.append(str3);
		Spanned localSpanned1 = Html.fromHtml(localStringBuffer1.toString());
		localTextView2.setText(localSpanned1);
		TextView localTextView3 = (TextView) localView.findViewById(2131492931);
		int i2 = localStringBuffer1.length();
		StringBuffer localStringBuffer11 = localStringBuffer1.delete(0, i2);
		String str4 = getString(2131361949);
		StringBuffer localStringBuffer12 = localStringBuffer1.append(str4);
		StringBuffer localStringBuffer13 = localStringBuffer1.append(" ");
		int i = 0;
		if (this.mPrice == 0) {
			String str5 = getString(2131361824);
			StringBuffer localStringBuffer14 = localStringBuffer1.append(str5);
			String str6 = localStringBuffer1.toString();
			localTextView3.setText(str6);
			localTextView3 = (TextView) localView.findViewById(2131492932);
			int i3 = localStringBuffer1.length();
			StringBuffer localStringBuffer15 = localStringBuffer1.delete(0, i3);
			String str7 = getString(2131361955);
			StringBuffer localStringBuffer16 = localStringBuffer1.append(str7);
			StringBuffer localStringBuffer17 = localStringBuffer1.append(" ");
			if (this.mDetail.finish_time == null){}
			String str8 = this.mDetail.finish_time;
			StringBuffer localStringBuffer18 = localStringBuffer1.append(str8);
			String str9 = localStringBuffer1.toString();
			localTextView3.setText(str9);
			localTextView3 = (TextView) localView.findViewById(2131492934);
			int i4 = localStringBuffer1.length();
			StringBuffer localStringBuffer19 = localStringBuffer1.delete(0, i4);
			String str10 = getString(2131361956);
			StringBuffer localStringBuffer20 = localStringBuffer1.append(str10);
			StringBuffer localStringBuffer21 = localStringBuffer1.append(" ");
			if (this.mDetail.decoration_info == null){}
			String str11 = this.mDetail.decoration_info;
			StringBuffer localStringBuffer22 = localStringBuffer1.append(str11);
			String str12 = localStringBuffer1.toString();
			localTextView3.setText(str12);
			localTextView3 = (TextView) localView.findViewById(2131492935);
			int i5 = localStringBuffer1.length();
			StringBuffer localStringBuffer23 = localStringBuffer1.delete(0, i5);
			String str13 = getString(2131361961);
			StringBuffer localStringBuffer24 = localStringBuffer1.append(str13);
			StringBuffer localStringBuffer25 = localStringBuffer1.append(" ");
			if (this.mDetail.property_type == null){}
			String str14 = this.mDetail.property_type;
			StringBuffer localStringBuffer26 = localStringBuffer1.append(str14);
			String str15 = localStringBuffer1.toString();
			localTextView3.setText(str15);
			localTextView3 = (TextView) localView.findViewById(2131492936);
			int i6 = localStringBuffer1.length();
			StringBuffer localStringBuffer27 = localStringBuffer1.delete(0, i6);
			String str16 = getString(2131361964);
			StringBuffer localStringBuffer28 = localStringBuffer1.append(str16);
			StringBuffer localStringBuffer29 = localStringBuffer1.append(" ");
			if (this.mDetail.floor_area_ratio == null){}
			String str17 = this.mDetail.floor_area_ratio;
			StringBuffer localStringBuffer30 = localStringBuffer1.append(str17);
			String str18 = localStringBuffer1.toString();
			localTextView3.setText(str18);
			localTextView3 = (TextView) localView.findViewById(2131492937);
			int i7 = localStringBuffer1.length();
			StringBuffer localStringBuffer31 = localStringBuffer1.delete(0, i7);
			String str19 = getString(2131361959);
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
			String str22 = getString(2131361982);
			localTextView4.setText(str22);
			this.mCommunityButAdd.setVisibility(8);
			this.mCommunityButReduce.setVisibility(8);
			label817: localTextView3 = (TextView) findViewById(2131492945);
			String str23 = this.mDetail.primarySchool;
			localTextView3.setText(str23);
			if ((this.mDetail.primarySchool != null)
					&& (this.mDetail.primarySchool.trim().length() != 0)){}
			i = 0 + 1;
			localTextView3.setVisibility(8);
			((TextView) findViewById(2131492944)).setVisibility(8);
		}
		while (true) {
			localTextView3 = (TextView) findViewById(2131492947);
			String str24 = this.mDetail.kindergarten;
			localTextView3.setText(str24);
			if ((this.mDetail.kindergarten == null)
					|| (this.mDetail.kindergarten.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492946)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492949);
			String str25 = this.mDetail.postOffice;
			localTextView3.setText(str25);
			if ((this.mDetail.postOffice == null)
					|| (this.mDetail.postOffice.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492948)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492951);
			String str26 = this.mDetail.bank;
			localTextView3.setText(str26);
			if ((this.mDetail.bank == null)
					|| (this.mDetail.bank.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492950)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492953);
			String str27 = this.mDetail.medicalStation;
			localTextView3.setText(str27);
			if ((this.mDetail.medicalStation == null)
					|| (this.mDetail.medicalStation.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492952)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492955);
			String str28 = this.mDetail.school;
			localTextView3.setText(str28);
			if ((this.mDetail.school == null)
					|| (this.mDetail.school.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492954)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492957);
			String str29 = this.mDetail.businessDistrict;
			localTextView3.setText(str29);
			if ((this.mDetail.businessDistrict == null)
					|| (this.mDetail.businessDistrict.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492956)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492959);
			String str30 = this.mDetail.hospital;
			localTextView3.setText(str30);
			if ((this.mDetail.hospital == null)
					|| (this.mDetail.hospital.trim().length() == 0)) {
				i += 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492958)).setVisibility(8);
			}
			if (i == 8) {
				this.sourroundingsVisible = false;
				((LinearLayout) findViewById(2131492921)).setVisibility(8);
			}
			localTextView3 = (TextView) findViewById(2131492962);
			String str31 = this.mDetail.bus;
			localTextView3.setText(str31);
			if ((this.mDetail.bus == null)
					|| (this.mDetail.bus.trim().length() == 0)) {
				i = 0 + 1;
				localTextView3.setVisibility(8);
				((TextView) findViewById(2131492961)).setVisibility(8);
			}
			while (true) {
				localTextView3 = (TextView) findViewById(2131492964);
				String str32 = this.mDetail.subway;
				localTextView3.setText(str32);
				if ((this.mDetail.subway == null)
						|| (this.mDetail.subway.trim().length() == 0)) {
					i += 1;
					localTextView3.setVisibility(8);
					((TextView) findViewById(2131492963)).setVisibility(8);
				}
				if (i == 2) {
					this.trafficVisible = false;
					((LinearLayout) findViewById(2131492925)).setVisibility(8);
				}
				localTextView3 = (TextView) findViewById(2131492909);
				String str33 = getString(2131361968);
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
				WebView localWebView = (WebView) findViewById(2131492911);
				localWebView.setInitialScale(100);
				int i1;
				if ((this.mDetail.priceChaturl == null)
						|| (this.mDetail.housePrice == 0)) {
					((ImageView) findViewById(2131492905)).setVisibility(8);
					LinearLayout localLinearLayout = (LinearLayout) findViewById(2131492910);
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
					((ScrollView) findViewById(2131492906)).setVisibility(0);
				} else {
					StringBuffer kS = new StringBuffer("");
					StringBuffer localStringBuffer35 = kS
							.append("<font color=\"#469405\">");
					int i11 = this.mPrice;
					StringBuffer localStringBuffer36 = kS.append(i11);
					String str35 = getString(2131361822);
					StringBuffer localStringBuffer37 = kS.append(str35);
					StringBuffer localStringBuffer38 = kS.append("</font>");
					String str36 = getString(2131361950);
					StringBuffer localStringBuffer39 = kS.append(str36);
					Spanned localSpanned2 = Html.fromHtml(kS.toString());
					localTextView3.setText(localSpanned2);
					String str37 = getString(2131361830);
					StringBuffer localStringBuffer40 = kS.append(str37);
					String str38 = getString(2131361830);
					StringBuffer localStringBuffer41 = kS.append(str38);
					String str39 = getString(2131361830);
					StringBuffer localStringBuffer42 = kS.append(str39);
					String str40 = getString(2131361830);
					StringBuffer localStringBuffer43 = kS.append(str40);
					String str41 = getString(2131361830);
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
						downBmp(str45, 0);
					}
					if (this.hasFlat_type) {
						String str46 = this.flatTypes[0];
						downBmp(str46, 1);
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
	}

	private void initControlView() {
		ImageView localImageView1 = (ImageView) findViewById(2131492918);
		this.mCommunityButAdd = localImageView1;
		ImageView localImageView2 = (ImageView) findViewById(2131492919);
		this.mCommunityButReduce = localImageView2;
		TextView localTextView = (TextView) findViewById(2131492920);
		this.mIntroTextView = localTextView;
		TableLayout localTableLayout = (TableLayout) findViewById(2131492943);
		this.mRoundLayout = localTableLayout;
		ImageView localImageView3 = (ImageView) findViewById(2131492922);
		this.mRoundButAdd = localImageView3;
		ImageView localImageView4 = (ImageView) findViewById(2131492923);
		this.mRoundButReduce = localImageView4;
		ImageView localImageView5 = (ImageView) findViewById(2131492924);
		this.mRoundLine = localImageView5;
		LinearLayout localLinearLayout = (LinearLayout) findViewById(2131492960);
		this.mTrafficLayout = localLinearLayout;
		ImageView localImageView6 = (ImageView) findViewById(2131492926);
		this.mTrafficButAdd = localImageView6;
		ImageView localImageView7 = (ImageView) findViewById(2131492927);
		this.mTrafficButReduce = localImageView7;
		ImageView localImageView8 = (ImageView) findViewById(2131492928);
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
		ProgressBar localProgressBar = (ProgressBar) findViewById(2131492929);
		this.mProgressbar = localProgressBar;
		ImageView localImageView1 = (ImageView) findViewById(2131492876);
		this.mBack = localImageView1;
		ImageView localImageView2 = this.mBack;
		localImageView2.setOnClickListener(new OnClickListener() {
			public void onClick(View paramView) {
				finish();
			}
		});
		LinearLayout localLinearLayout = (LinearLayout) findViewById(2131492907);
		this.mLinearLayout = localLinearLayout;
		Button localButton1 = (Button) findViewById(2131492904);
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
		new CommunityDetailThread(localCommunityDetailHandler, str4, str5)
				.start();
	}

	final class CommunityDetailActivity3 implements CommunityDetailListener {
		public void handleCommunityDetail(boolean paramBoolean,
				CommunityDetail paramCommunityDetail) {
			/*
			 * if (true == paramBoolean) { CommunityDetail localCommunityDetail
			 * = CommunityDetailActivity.access$702(this.this$0,
			 * paramCommunityDetail);
			 * CommunityDetailActivity.access$800(this.this$0); } while (true) {
			 * CommunityDetailActivity.access$900(this.this$0).setVisibility(8);
			 * return; CommunityDetailActivity localCommunityDetailActivity =
			 * this.this$0; String str = this.this$0.getString(2131361948);
			 * Toast.makeText(localCommunityDetailActivity, str, 1).show(); }
			 */
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
		setContentView(2130903047);
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
