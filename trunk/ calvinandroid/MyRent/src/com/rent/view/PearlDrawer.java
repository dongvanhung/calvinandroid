package com.rent.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.rent.exchange.EasingType;
import com.rent.exchange.ElasticInterpolator;
import com.rent.exchange.ExchangeDataService;
import com.rent.exchange.GetDataThread;
import com.rent.exchange.ReportThread;
import com.rent.exchange.SwitchAdListener;
import com.rent.exchange.SwithAdThread;
import com.rent.exchange.common.AnimMapper;
import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeAnimation;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.common.RetriveImageThread;
import com.rent.exchange.model.AdvertiserConfig;
import com.rent.listener.ExchangeDataRequestListener;

public class PearlDrawer implements BasePanel.OnPanelListener,
		ExchangeDataRequestListener, SwitchAdListener {

	public static int blurLayoutId = 100;
	public ImageView bannerBg;
	public int contentHeight;
	public View mBottomContent;
	Context mContext;
	public View mHandleContent;
	public boolean mIsBanner;
	public int mPosition;
	ViewGroup mRootLayout;
	int mType;
	public BasePanel panel = null;

	public PearlDrawer(Context paramContext, ViewGroup paramViewGroup,
			int paramInt) {
		this.mContext = paramContext;
		this.mType = paramInt;
		this.mRootLayout = paramViewGroup;
		getLandingPageSwitcher();
	}

	private void bindData(int paramInt) {
		AdvertiserConfig localAdvertiserConfig = ExchangeDataService.getCurAd();
		BasePanel localBasePanel1 = this.panel;
		int i = IdMapper.name0();
		TextView localTextView1 = (TextView) localBasePanel1.findViewById(i);
		String str1 = localAdvertiserConfig.adName;
		localTextView1.setText(str1);
		BasePanel localBasePanel2 = this.panel;
		int j = IdMapper.size0();
		TextView localTextView2 = (TextView) localBasePanel2.findViewById(j);
		if (ExchangeConstants.show_size) {
			String str2 = ExchangeConstants
					.getFileSizeDescription(localAdvertiserConfig.fileSize);
			localTextView2.setText(str2);
			BasePanel localBasePanel3 = this.panel;
			int k = IdMapper.content0();
			TextView localTextView3 = (TextView) localBasePanel3
					.findViewById(k);
			String str3 = localAdvertiserConfig.adDescription;
			localTextView3.setText(str3);
			BasePanel localBasePanel4 = this.panel;
			int m = IdMapper.appIcon0();
			ImageView localImageView = (ImageView) localBasePanel4
					.findViewById(m);
			Context localContext = this.mContext;
			String str4 = localAdvertiserConfig.adIconUrl;
			new RetriveImageThread(localContext, localImageView, str4).start();
			BasePanel localBasePanel5 = this.panel;
			int n = IdMapper.dev();
			TextView localTextView4 = (TextView) localBasePanel5
					.findViewById(n);
			StringBuilder localStringBuilder1 = new StringBuilder("Test");
			String str5 = localAdvertiserConfig.provider;
			String str6 = str5;
			localTextView4.setText(str6);
			BasePanel localBasePanel6 = this.panel;
			int i1 = IdMapper.appname();
			TextView localTextView5 = (TextView) localBasePanel6
					.findViewById(i1);
			StringBuilder localStringBuilder2 = new StringBuilder("Test");
			String str7 = localAdvertiserConfig.adName;
			String str8 = str7;
			localTextView5.setText(str8);
			BasePanel localBasePanel7 = this.panel;
			int i2 = IdMapper.des0();
			TextView localTextView6 = (TextView) localBasePanel7
					.findViewById(i2);
			String str9 = localAdvertiserConfig.adDetail;
			localTextView6.setText(str9);
		} else {
			localTextView2.setVisibility(8);
		}
	}

	private BasePanel getLandingPage(Context paramContext, View paramView,
			RelativeLayout.LayoutParams paramLayoutParams,
			ViewGroup paramViewGroup, int paramInt1, int paramInt2) {
		int i = paramInt2;
		this.mPosition = i;
		int j;
		RelativeLayout.LayoutParams localLayoutParams3 = null;
		Button localButton1;
		RelativeLayout.LayoutParams localLayoutParams5 = null;
		Button localButton10 = null;
		if (paramInt2 == 0) {
			j = 10;
			boolean bool1 = this.mIsBanner;
			Context localContext1 = paramContext;
			int k = paramInt2;
			boolean bool2 = bool1;
			BasePanel localBasePanel1 = new BasePanel(localContext1, k, bool2);
			BasePanel localBasePanel2 = localBasePanel1;
			BasePanel localBasePanel3 = localBasePanel1;
			this.panel = localBasePanel3;
			this.panel.setId(0);
			int m = 65535;
			int n = 65534;
			RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(
					m, n);
			RelativeLayout.LayoutParams localLayoutParams2 = localLayoutParams1;
			localLayoutParams1.addRule(j);
			BasePanel localBasePanel4 = this.panel;
			PearlDrawer localPearlDrawer1 = this;
			localBasePanel4.setOnPanelListener(localPearlDrawer1);
			BasePanel localBasePanel5 = this.panel;
			EasingType.Type localType = EasingType.Type.OUT;
			ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(
					localType, 1.0F, 1.0F);
			localBasePanel5.setInterpolator(localElasticInterpolator);
			Context localContext2 = paramContext;
			int i1 = paramInt1;
			ViewGroup localViewGroup1 = null;
			View localView1 = View.inflate(localContext2, i1, localViewGroup1);
			this.mBottomContent = localView1;
			this.mBottomContent.setVisibility(8);
			BasePanel localBasePanel6 = this.panel;
			View localView2 = this.mBottomContent;
			localBasePanel6.mContent = localView2;
			int i2 = getH();
			int i3 = 65535;
			int i4 = i2;
			localLayoutParams3 = new RelativeLayout.LayoutParams(i3, i4);
			RelativeLayout.LayoutParams localLayoutParams4 = localLayoutParams3;
			localLayoutParams3.addRule(j);
			if (paramView != null) {}
			localButton1 = new Button(paramContext);
			Button localButton2 = localButton1;
			int i5 = paramInt2;
			int i6 = 1;
			if (i5 != i6){}
			Resources localResources1 = paramContext.getResources();
			int i7 = DrawableMapper
					.exchange_bottom_switcher_collapsed_background();
			Drawable localDrawable1 = localResources1.getDrawable(i7);
			Button localButton3 = localButton1;
			Drawable localDrawable2 = localDrawable1;
			localButton3.setBackgroundDrawable(localDrawable2);
			int i8 = -1;
			if (!ExchangeConstants.handler_auto_expand) {
				int i9 = ExchangeConstants.TAG_WIDTH;
				Context localContext4 = paramContext;
				i8 = DeviceManager.dpToPix(i9, localContext4);
			}
			int i10 = ExchangeConstants.TAG_HEIGHT;
			int i11 = DeviceManager.dpToPix(i10, paramContext);
			RelativeLayout.LayoutParams localLayoutParams6 = localLayoutParams5;
			int i12 = i8;
			int i13 = i11;
			localLayoutParams5 = new RelativeLayout.LayoutParams(i2, i3);
			Context localContext5 = paramContext;
			if (!ExchangeConstants.handler_auto_expand) {
				if (!ExchangeConstants.handler_left){}
				Button localButton4 = localButton1;
				int i14 = 5;
				localButton4.setGravity(i14);
			}
			BasePanel localBasePanel7 = this.panel;
			Button localButton5 = localButton1;
			localBasePanel7.mHandle = localButton5;
			Button localButton6 = localButton1;
			this.mHandleContent = localButton6;
			if (paramInt2 != 0){}
			BasePanel localBasePanel8 = this.panel;
			View localView3 = this.mBottomContent;
			BasePanel localBasePanel9 = localBasePanel8;
			View localView4 = localView3;
			RelativeLayout.LayoutParams localLayoutParams7 = localLayoutParams3;
			localBasePanel9.addView(localView4, localLayoutParams7);
			BasePanel localBasePanel10 = this.panel;
			Button localButton7 = localButton1;
			RelativeLayout.LayoutParams localLayoutParams8 = localLayoutParams5;
			localBasePanel10.addView(localButton7, localLayoutParams8);
			this.panel.setListener();
			RelativeLayout localRelativeLayout1 = new RelativeLayout(
					paramContext);
			RelativeLayout localRelativeLayout2 = localRelativeLayout1;
			int i15 = 65535;
			int i16 = 65534;
			LinearLayout.LayoutParams localLayoutParams9 = new LinearLayout.LayoutParams(
					i15, i16);
			LinearLayout.LayoutParams localLayoutParams10 = localLayoutParams9;
			int i17 = Color.alpha(0);
			RelativeLayout localRelativeLayout3 = localRelativeLayout1;
			int i18 = i17;
			localRelativeLayout3.setBackgroundColor(i18);
			ViewGroup localViewGroup2 = paramViewGroup;
			RelativeLayout localRelativeLayout4 = localRelativeLayout1;
			LinearLayout.LayoutParams localLayoutParams11 = localLayoutParams9;
			localViewGroup2.addView(localRelativeLayout4, localLayoutParams11);
			if (ExchangeConstants.blur_switcher) {
				PearlDrawer localPearlDrawer2 = this;
				RelativeLayout localRelativeLayout5 = localRelativeLayout1;
				RelativeLayout localRelativeLayout6 = localPearlDrawer2
						.getBlur(localRelativeLayout5);
				BasePanel localBasePanel11 = this.panel;
				RelativeLayout localRelativeLayout7 = localRelativeLayout6;
				localBasePanel11.blurLayout = localRelativeLayout7;
			}
			BasePanel localBasePanel12 = this.panel;
			RelativeLayout localRelativeLayout8 = localRelativeLayout1;
			BasePanel localBasePanel13 = localBasePanel12;
			RelativeLayout.LayoutParams localLayoutParams12 = localLayoutParams1;
			localRelativeLayout8.addView(localBasePanel13, localLayoutParams12);
			ExchangeAnimation.showSlowly(this.panel);
			View localView5 = this.mBottomContent;
			int i19 = IdMapper.back();
			Button localButton8 = (Button) localView5.findViewById(i19);
			PearlDrawer localPearlDrawer3 = this;
			Button localButton9 = localButton8;
			localButton9.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramView) {
					/*
					 * BasePanel localBasePanel; if
					 * (!this.this$0.panel.mAnimating) { localBasePanel =
					 * this.this$0.panel; if (!this.this$0.panel.isOpen()) break
					 * label45; } label45: int j; for (int i = 0; ; j = 1) {
					 * localBasePanel.setOpen(i, 1); return; }
					 */
				}
			});
			View localView6 = this.mBottomContent;
			int i20 = IdMapper.more();
			localButton10 = (Button) localView6.findViewById(i20);
			if (!ExchangeDataService.hasData()){}
			int i21 = ExchangeDataService.mAdvertisers.size();
			int i22 = 1;
			if (i21 != i22){}
			int i23 = 8;
			localButton10.setVisibility(i23);
			View localView7 = this.mBottomContent;
			int i24 = IdMapper.download();
			Button localButton12 = (Button) localView7.findViewById(i24);
			PearlDrawer localPearlDrawer4 = this;
			Context localContext7 = paramContext;
			Button localButton13 = localButton12;
			localButton13.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramView) {
					/*
					 * Context localContext1 = this.this$0.mContext;
					 * AdvertiserConfig localAdvertiserConfig1 =
					 * ExchangeDataService.getCurAd(); int i =
					 * this.this$0.mType; new ReportThread(3, localContext1,
					 * localAdvertiserConfig1, i, 2, 0).start();
					 * AdvertiserConfig localAdvertiserConfig2 =
					 * ExchangeDataService.getCurAd(); Context localContext2 =
					 * this.val$context; String str1 =
					 * localAdvertiserConfig2.apk; StringBuilder
					 * localStringBuilder = new StringBuilder("Test"); String
					 * str2 = localAdvertiserConfig2.adName; String str3 = str2;
					 * int j = this.this$0.mType; DownloadAgent
					 * localDownloadAgent = new DownloadAgent(localContext2,
					 * str1, "Test", str3, "", localAdvertiserConfig2, j, 0);
					 */
				}
			});
			this.mHandleContent.setVisibility(8);
			GetDataThread localGetDataThread1 = new GetDataThread(
					this.mContext, this);
			Context localContext8 = this.mContext;
			GetDataThread localGetDataThread2 = localGetDataThread1;
			Context localContext9 = localContext8;
			PearlDrawer localPearlDrawer5 = this;
			localGetDataThread1.start();
			View localView8 = this.mHandleContent;
			int i25 = IdMapper.banner_bg();
			ImageView localImageView = (ImageView) localView8.findViewById(i25);
			this.bannerBg = localImageView;
			return this.panel;
		} else {
			Resources localResources2 = paramContext.getResources();
			int i26 = DrawableMapper
					.exchange_top_switcher_collapsed_background();
			Drawable localDrawable3 = localResources2.getDrawable(i26);
			Button localButton14 = localButton10;
			Drawable localDrawable4 = localDrawable3;
			localButton14.setBackgroundDrawable(localDrawable4);
			Button localButton15 = localButton10;
			int i27 = 5;
			localButton15.setGravity(i27);
			BasePanel localBasePanel14 = this.panel;
			Button localButton16 = localButton10;
			RelativeLayout.LayoutParams localLayoutParams13 = localLayoutParams5;
			localBasePanel14.addView(localButton16, localLayoutParams13);
			BasePanel localBasePanel15 = this.panel;
			View localView9 = this.mBottomContent;
			BasePanel localBasePanel16 = localBasePanel15;
			View localView10 = localView9;
			RelativeLayout.LayoutParams localLayoutParams14 = localLayoutParams3;
			localBasePanel16.addView(localView10, localLayoutParams14);
			View localView11 = paramView;
			this.mHandleContent = localView11;
			BasePanel localBasePanel17 = this.panel;
			View localView12 = paramView;
			localBasePanel17.mHandle = localView12;
			if (paramInt2 == 0) {
				BasePanel localBasePanel18 = this.panel;
				View localView13 = this.mBottomContent;
				BasePanel localBasePanel19 = localBasePanel18;
				View localView14 = localView13;
				RelativeLayout.LayoutParams localLayoutParams15 = localLayoutParams3;
				localBasePanel19.addView(localView14, localLayoutParams15);
				BasePanel localBasePanel20 = this.panel;
				View localView15 = paramView;
				RelativeLayout.LayoutParams localLayoutParams16 = paramLayoutParams;
				localBasePanel20.addView(localView15, localLayoutParams16);
			}
			BasePanel localBasePanel21 = this.panel;
			View localView16 = paramView;
			RelativeLayout.LayoutParams localLayoutParams17 = paramLayoutParams;
			localBasePanel21.addView(localView16, localLayoutParams17);
			BasePanel localBasePanel22 = this.panel;
			View localView17 = this.mBottomContent;
			BasePanel localBasePanel23 = localBasePanel22;
			View localView18 = localView17;
			RelativeLayout.LayoutParams localLayoutParams18 = localLayoutParams3;
			localBasePanel23.addView(localView18, localLayoutParams18);
			Button localButton17 = localButton10;
			localButton17.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramView) {
					/*
					 * try { Context localContext = this.this$0.mContext; Class
					 * localClass =
					 * Class.forName("com.exchange.View.ListCurtainActivity");
					 * Intent localIntent = new Intent(localContext,
					 * localClass);
					 * this.this$0.mContext.startActivity(localIntent); return;
					 * } catch (ClassNotFoundException
					 * localClassNotFoundException) { while (true)
					 * localClassNotFoundException.printStackTrace(); }
					 */
				}
			});
		}
		return null;
	}

	public void dataReceived(int paramInt) {
		if (paramInt == 0)
			Log.i(ExchangeConstants.LOG_TAG, "failed to get request data");
		else {
			this.mHandleContent.setVisibility(0);
			int j = Log.i(ExchangeConstants.LOG_TAG, "get data");
			new SwithAdThread(this).start();
			if (this.mIsBanner) {
				Context localContext = this.mContext;
				AdvertiserConfig localAdvertiserConfig = ExchangeDataService
						.getCurAd();
				int k = this.mType;
				int m = 0;
				new ReportThread(0, localContext, localAdvertiserConfig, k, 1,
						m).start();
			}
			bindData(0);
		}
	}

	public RelativeLayout getBlur(ViewGroup paramViewGroup) {
		Context localContext = this.mContext;
		RelativeLayout localRelativeLayout = new RelativeLayout(localContext);
		int i = blurLayoutId;
		localRelativeLayout.setId(i);
		localRelativeLayout.setVisibility(8);
		RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(
				-1, -1);
		localRelativeLayout.setLayoutParams(localLayoutParams);
		int j = Color.argb(200, 0, 0, 0);
		localRelativeLayout.setBackgroundColor(j);
		paramViewGroup.addView(localRelativeLayout);
		return localRelativeLayout;
	}

	int getH() {
		if (this.mIsBanner)
			;
		for (int i = 270;; i = 325) {
			if (!DeviceManager.isScreenPortrait(this.mContext))
				i += -100;
			Context localContext = this.mContext;
			return DeviceManager.dpToPix(i, localContext);
		}
	}

	public void getLandingPageSwitcher() {
		Context localContext1 = this.mContext;
		int i = DeviceManager.dpToPix(55, localContext1);
		RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(
				-1, i);
		View localView1;
		if (this.mType == 0) {
			this.mIsBanner = true;
			Context localContext2 = this.mContext;
			int j = LayoutMapper.exchange_big_handler_bottom_handler();
			localView1 = View.inflate(localContext2, j, null);
			Context localContext3 = this.mContext;
			ViewGroup localViewGroup1 = this.mRootLayout;
			int k = LayoutMapper.exchange_big_handler_bottom_content();
			BasePanel localBasePanel1 = getLandingPage(localContext3,
					localView1, localLayoutParams, localViewGroup1, k, 1);
			if (localView1 != null) {
				int m = IdMapper.flipper();
				ViewFlipper localViewFlipper = (ViewFlipper) localView1
						.findViewById(m);
				localViewFlipper.setFlipInterval(10000);
				Context localContext4 = this.mContext;
				int n = AnimMapper.exchange_push_up_in();
				Animation localAnimation1 = AnimationUtils.loadAnimation(
						localContext4, n);
				localViewFlipper.setInAnimation(localAnimation1);
				Context localContext5 = this.mContext;
				int i1 = AnimMapper.exchange_push_up_out();
				Animation localAnimation2 = AnimationUtils.loadAnimation(
						localContext5, i1);
				localViewFlipper.setOutAnimation(localAnimation2);
				Context localContext6 = this.mContext;
				int i2 = LayoutMapper.exchange_big_handler_flipper_content();
				View localView2 = View.inflate(localContext6, i2, null);
				localViewFlipper.addView(localView2);
			}
		} else {
			if (this.mType == 1) {
				this.mIsBanner = true;
				Context localContext7 = this.mContext;
				int i3 = LayoutMapper.exchange_big_handler_top_handler();
				localView1 = View.inflate(localContext7, i3, null);
				Context localContext8 = this.mContext;
				ViewGroup localViewGroup2 = this.mRootLayout;
				int i4 = LayoutMapper.exchange_big_handler_top_content();
				BasePanel localBasePanel2 = getLandingPage(localContext8,
						localView1, localLayoutParams, localViewGroup2, i4, 0);
			} else if (this.mType == 2) {
				this.mIsBanner = false;
				Context localContext9 = this.mContext;
				ViewGroup localViewGroup3 = this.mRootLayout;
				int i5 = LayoutMapper.exchange_small_handler_bottom_focus();
				BasePanel localBasePanel3 = getLandingPage(localContext9, null,
						null, localViewGroup3, i5, 1);
			} else if (this.mType != 3) {
			} else {
				this.mIsBanner = false;
				Context localContext10 = this.mContext;
				ViewGroup localViewGroup4 = this.mRootLayout;
				int i6 = LayoutMapper.exchange_small_handler_top_focus();
				BasePanel localBasePanel4 = getLandingPage(localContext10,
						null, null, localViewGroup4, i6, 0);
			}
		}
	}

	public void onPanelClosed(BasePanel paramBasePanel) {
		if (this.mPosition == 1) {
			EasingType.Type localType1 = EasingType.Type.OUT;
			ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(
					localType1, 1.0F, 1.0F);
			paramBasePanel.setInterpolator(localElasticInterpolator);
			if (this.bannerBg != null) {
				ImageView localImageView1 = this.bannerBg;
				int i = DrawableMapper.exchange_ban();
				localImageView1.setImageResource(i);
			}
			paramBasePanel.mHandle.setVisibility(0);
		} else {
			EasingType.Type localType2 = EasingType.Type.OUT;
			BounceInterpolator localBounceInterpolator = new BounceInterpolator();
			paramBasePanel.setInterpolator(localBounceInterpolator);
			if (this.bannerBg == null) {
			} else {
				ImageView localImageView2 = this.bannerBg;
				int j = DrawableMapper.exchange_ban_top();
				localImageView2.setImageResource(j);
			}
		}
	}

	public void onPanelOpened(BasePanel paramBasePanel, View paramView) {
		if (this.mPosition == 1) {
			EasingType.Type localType1 = EasingType.Type.OUT;
			BounceInterpolator localBounceInterpolator = new BounceInterpolator();
			paramBasePanel.setInterpolator(localBounceInterpolator);
			if (this.bannerBg != null) {
				ImageView localImageView1 = this.bannerBg;
				int i = DrawableMapper.exchange_ban_bottom_reverse();
				localImageView1.setImageResource(i);
			}
			paramBasePanel.mHandle.setVisibility(0);
			paramBasePanel.showBlur();
			if (this.mIsBanner) {
				Context localContext1 = this.mContext;
				AdvertiserConfig localAdvertiserConfig1 = ExchangeDataService
						.getCurAd();
				int j = this.mType;
				int k = 0;
				new ReportThread(0, localContext1, localAdvertiserConfig1, j,
						2, k).start();
			}
		} else {
			EasingType.Type localType2 = EasingType.Type.OUT;
			ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(
					localType2, 1.0F, 1.0F);
			paramBasePanel.setInterpolator(localElasticInterpolator);
			if (this.bannerBg == null) {
			} else {
				ImageView localImageView2 = this.bannerBg;
				int m = DrawableMapper.exchange_ban_top_reverse();
				localImageView2.setImageResource(m);
				Context localContext2 = this.mContext;
				AdvertiserConfig localAdvertiserConfig2 = ExchangeDataService
						.getCurAd();
				int n = this.mType;
				int i1 = 0;
				new ReportThread(2, localContext2, localAdvertiserConfig2, n,
						1, i1).start();
			}
		}
	}

	public void timeup()
	  {
		AdvertiserConfig localAdvertiserConfig = null;
	    ViewFlipper localViewFlipper1 = null;
	    View localView2 = null;
	    TextView localTextView2 = null;
	    if (this.mHandleContent.getWindowVisibility() == 8) {
	      Log.i(ExchangeConstants.LOG_TAG, "current window gone");
	    }
	      if (this.mBottomContent.getVisibility() != 8){}
	      int j = Log.i(ExchangeConstants.LOG_TAG, "switch ad");
	      if (!ExchangeDataService.rotate()) {} else {
	    	  localAdvertiserConfig = ExchangeDataService.getCurAd();
		      if (this.mIsBanner)
		      {
		        Context localContext1 = this.mContext;
		        int k = this.mType;
		        new ReportThread(0, localContext1, localAdvertiserConfig, k, 1, 0).start();
		      }
		      View localView1 = this.mHandleContent;
		      int m = IdMapper.flipper();
		      localViewFlipper1 = (ViewFlipper)localView1.findViewById(m);
		      if (localViewFlipper1 == null)return;
		      localViewFlipper1.setFlipInterval(5000);
		      Context localContext2 = this.mContext;
		      int n = AnimMapper.exchange_push_up_in();
		      Animation localAnimation1 = AnimationUtils.loadAnimation(localContext2, n);
		      localViewFlipper1.setInAnimation(localAnimation1);
		      Context localContext3 = this.mContext;
		      int i1 = AnimMapper.exchange_push_up_out();
		      Animation localAnimation2 = AnimationUtils.loadAnimation(localContext3, i1);
		      localViewFlipper1.setOutAnimation(localAnimation2);
		      Context localContext4 = this.mContext;
		      int i2 = LayoutMapper.exchange_big_handler_flipper_content();
		      localView2 = View.inflate(localContext4, i2, null);
		      ViewFlipper localViewFlipper2 = localViewFlipper1;
		      View localView3 = localView2;
		      localViewFlipper2.addView(localView3);
		      int i3 = IdMapper.name0();
		      View localView4 = localView2;
		      int i4 = i3;
		      TextView localTextView1 = (TextView)localView4.findViewById(i4);
		      String str1 = localAdvertiserConfig.adName;
		      localTextView1.setText(str1);
		      int i5 = IdMapper.size0();
		      View localView5 = localView2;
		      int i6 = i5;
		      localTextView2 = (TextView)localView5.findViewById(i6);
		      if (!ExchangeConstants.show_size){}
		      String str2 = ExchangeConstants.getFileSizeDescription(localAdvertiserConfig.fileSize);
		      TextView localTextView3 = localTextView2;
		      String str3 = str2;
		      localTextView3.setText(str3);
	      }
	      int i7 = IdMapper.content0();
	      View localView6 = localView2;
	      int i8 = i7;
	      TextView localTextView4 = (TextView)localView6.findViewById(i8);
	      String str4 = localAdvertiserConfig.adDescription;
	      localTextView4.setText(str4);
	      BasePanel localBasePanel1 = this.panel;
	      int i9 = IdMapper.appIcon0();
	      ImageView localImageView = (ImageView)localBasePanel1.findViewById(i9);
	      Context localContext5 = this.mContext;
	      String str5 = localAdvertiserConfig.adIconUrl;
	      new RetriveImageThread(localContext5, localImageView, str5).start();
	      BasePanel localBasePanel2 = this.panel;
	      int i10 = IdMapper.dev();
	      TextView localTextView5 = (TextView)localBasePanel2.findViewById(i10);
	      StringBuilder localStringBuilder1 = new StringBuilder("Test");
	      String str6 = localAdvertiserConfig.provider;
	      String str7 = str6;
	      localTextView5.setText(str7);
	      BasePanel localBasePanel3 = this.panel;
	      int i11 = IdMapper.appname();
	      TextView localTextView6 = (TextView)localBasePanel3.findViewById(i11);
	      StringBuilder localStringBuilder2 = new StringBuilder("Test");
	      String str8 = localAdvertiserConfig.adName;
	      String str9 = str8;
	      localTextView6.setText(str9);
	      BasePanel localBasePanel4 = this.panel;
	      int i12 = IdMapper.des0();
	      TextView localTextView7 = (TextView)localBasePanel4.findViewById(i12);
	      String str10 = localAdvertiserConfig.adDetail;
	      localTextView7.setText(str10);
	      if (localViewFlipper1 != null)
	      {
	        localViewFlipper1.showNext();
	        if (localViewFlipper1.getChildCount() == 3)
	          localViewFlipper1.removeViewAt(0);
	      }
	      SwithAdThread localSwithAdThread1 = new SwithAdThread(this);
	      SwithAdThread localSwithAdThread2 = localSwithAdThread1;
	      localSwithAdThread1.start();
	      TextView localTextView8 = localTextView2;
	      int i13 = 8;
	      localTextView8.setVisibility(i13);
	    BasePanel localBasePanel5 = this.panel;
	    int i14 = IdMapper.name0();
	    TextView localTextView9 = (TextView)localBasePanel5.findViewById(i14);
	    String str11 = localAdvertiserConfig.adName;
	    localTextView9.setText(str11);
	    BasePanel localBasePanel6 = this.panel;
	    int i15 = IdMapper.size0();
	    localTextView2 = (TextView)localBasePanel6.findViewById(i15);
	    if (ExchangeConstants.show_size)
	    {
	      String str12 = ExchangeConstants.getFileSizeDescription(localAdvertiserConfig.fileSize);
	      TextView localTextView10 = localTextView2;
	      String str13 = str12;
	      localTextView10.setText(str13);
	    } else {
	      BasePanel localBasePanel7 = this.panel;
	      int i16 = IdMapper.content0();
	      TextView localTextView11 = (TextView)localBasePanel7.findViewById(i16);
	      String str14 = localAdvertiserConfig.adDescription;
	      localTextView11.setText(str14);
	      TextView localTextView12 = localTextView2;
	      int i17 = 8;
	      localTextView12.setVisibility(i17);
	    }
	  }
}
