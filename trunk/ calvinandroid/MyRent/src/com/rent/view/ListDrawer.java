package com.rent.view;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rent.adapter.AdvertiserAdapter;
import com.rent.exchange.AdvertiserList;
import com.rent.exchange.EasingType;
import com.rent.exchange.ElasticInterpolator;
import com.rent.exchange.ExchangeDataService;
import com.rent.exchange.GetDataThread;
import com.rent.exchange.ReportThread;
import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.listener.ExchangeDataRequestListener;

public class ListDrawer implements BasePanel.OnPanelListener,
		ExchangeDataRequestListener {

	ListView listView;
	AdvertiserAdapter mAdapter;
	public View mBottomContent;
	public int mContentId;
	Context mContext;
	public ViewGroup mFatherLayout;
	public View mHandleContent;
	public int mItemCount;
	public int mPosition;
	public int mType;
	public BasePanel panel;

	public ListDrawer(Context paramContext, ViewGroup paramViewGroup,
			int paramInt) {
		this.mContext = paramContext;
		this.mFatherLayout = paramViewGroup;
		this.mType = paramInt;
		int i = 0;
		if (DeviceManager.isScreenPortrait(this.mContext))
			i = ExchangeConstants.DRAWER_LIST_COUNT_VERTICAL;
		int j;
		for (this.mItemCount = i;; this.mItemCount = j) {
			getLandingPageSwitcher();
			Context localContext = this.mContext;
			new GetDataThread(localContext, this).start();
			j = ExchangeConstants.DRAWER_LIST_COUNT_HORIZEN;
		}
	}

	public void dataReceived(int paramInt) {
		if (paramInt == 0) {
			int i = Log.i(ExchangeConstants.LOG_TAG,
					"failed to get request data");
			return;
		}
		int j = ExchangeDataService.mAdvertisers.size();
		if (this.mItemCount > j)
			;
		for (int k = j;; k = this.mItemCount) {
			this.mItemCount = k;
			ViewGroup localViewGroup = this.mFatherLayout;
			int m = this.mContentId;
			int n = this.mPosition;
			setInteraction(localViewGroup, m, n);
			break;
		}
	}

	public RelativeLayout getBlur(ViewGroup paramViewGroup) {
		Context localContext = this.mContext;
		RelativeLayout localRelativeLayout = new RelativeLayout(localContext);
		localRelativeLayout.setId(100);
		localRelativeLayout.setVisibility(8);
		RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(
				-1, -1);
		localRelativeLayout.setLayoutParams(localLayoutParams);
		int i = Color.argb(200, 0, 0, 0);
		localRelativeLayout.setBackgroundColor(i);
		paramViewGroup.addView(localRelativeLayout);
		return localRelativeLayout;
	}

	int getH() {
		int i = this.mItemCount;
		int j = 52 * i;
		int k = 50 + j;
		Context localContext = this.mContext;
		return DeviceManager.dpToPix(k, localContext);
	}

	public void getLandingPageSwitcher() {
		switch (this.mType) {
		default:
		case 4:
			this.mPosition = 1;
			int i = LayoutMapper.exchange_small_handler_bottom_list();
			this.mContentId = i;
		case 5:
			this.mPosition = 0;
			int j = LayoutMapper.exchange_small_handler_top_list();
			this.mContentId = j;
		}
	}

	public void onPanelClosed(BasePanel paramBasePanel) {
		if (paramBasePanel.getId() == 0) {
			EasingType.Type localType1 = EasingType.Type.OUT;
			ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(
					localType1, 1.0F, 1.0F);
			paramBasePanel.setInterpolator(localElasticInterpolator);
		} else {
			paramBasePanel.mHandle.setVisibility(0);
			EasingType.Type localType2 = EasingType.Type.OUT;
			BounceInterpolator localBounceInterpolator = new BounceInterpolator();
			paramBasePanel.setInterpolator(localBounceInterpolator);
		}
	}

	public void onPanelOpened(BasePanel paramBasePanel, View paramView) {
		if (paramBasePanel.getId() == 0) {
			EasingType.Type localType1 = EasingType.Type.OUT;
			BounceInterpolator localBounceInterpolator = new BounceInterpolator();
			paramBasePanel.setInterpolator(localBounceInterpolator);
		} else {
			paramBasePanel.mHandle.setVisibility(0);
			paramBasePanel.showBlur();
			Context localContext = this.mContext;
			List localList1 = ExchangeDataService.mAdvertisers;
			int i = this.mItemCount;
			List localList2 = localList1.subList(0, i);
			int j = this.mType;
			int k = 0;
			new ReportThread(0, localContext, localList2, j, 2, k).start();
			EasingType.Type localType2 = EasingType.Type.OUT;
			ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(
					localType2, 1.0F, 1.0F);
			paramBasePanel.setInterpolator(localElasticInterpolator);
		}
	}

	void setInteraction(ViewGroup paramViewGroup, int paramInt1, int paramInt2)
	  {
	    int i;
	    RelativeLayout.LayoutParams localLayoutParams1 = null;
	    Button localButton1 = null;
	    int n;
	    RelativeLayout.LayoutParams localLayoutParams3 = null;
	    RelativeLayout.LayoutParams localLayoutParams4 = null;
	    if (paramInt2 == 0)
	    {
	      i = 10;
	      BasePanel localBasePanel1 = new BasePanel(this.mContext,paramInt2, false);
	      Context localContext1 = this.mContext;
	      BasePanel localBasePanel2 = localBasePanel1;
	      Context localContext2 = localContext1;
	      BasePanel localBasePanel3 = localBasePanel1;
	      this.panel = localBasePanel3;
	      this.panel.setId(0);
	      localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
	      RelativeLayout.LayoutParams localLayoutParams2 = localLayoutParams1;
	      int m = i;
	      localLayoutParams2.addRule(m);
	      BasePanel localBasePanel4 = this.panel;
	      ListDrawer localListDrawer1 = this;
	      localBasePanel4.setOnPanelListener(localListDrawer1);
	      BasePanel localBasePanel5 = this.panel;
	      EasingType.Type localType = EasingType.Type.OUT;
	      ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(localType, 1.0F, 1.0F);
	      localBasePanel5.setInterpolator(localElasticInterpolator);
	      localButton1 = new Button(this.mContext);
	      Button localButton2 = localButton1;
	      Resources localResources = this.mContext.getResources();
	      if (paramInt2 != 0){}
	      n = DrawableMapper.exchange_top_switcher_collapsed_background();
	      Drawable localDrawable1 = localResources.getDrawable(n);
	      Button localButton3 = localButton1;
	      Drawable localDrawable2 = localDrawable1;
	      localButton3.setBackgroundDrawable(localDrawable2);
	      int i1 = ExchangeConstants.TAG_HEIGHT;
	      Context localContext5 = this.mContext;
	      int i2 = DeviceManager.dpToPix(i1, localContext5);
	      localLayoutParams3 = new RelativeLayout.LayoutParams(-1, i2);
	      BasePanel localBasePanel6 = this.panel;
	      Button localButton4 = localButton1;
	      localBasePanel6.mHandle = localButton4;
	      Button localButton5 = localButton1;
	      this.mHandleContent = localButton5;
	      Context localContext6 = this.mContext;
	      int i3 = paramInt1;
	      ViewGroup localViewGroup1 = null;
	      View localView1 = View.inflate(localContext6, i3, localViewGroup1);
	      this.mBottomContent = localView1;
	      View localView2 = this.mBottomContent;
	      int i4 = IdMapper.list();
	      ListView localListView1 = (ListView)localView2.findViewById(i4);
	      this.listView = localListView1;
	      ListView localListView2 = this.listView;
	      Context localContext7 = this.mContext;
	      int i5 = LayoutMapper.exchange_normal_banner();
	      int i6 = this.mItemCount;
	      int i7 = this.mType;
	      AdvertiserList localAdvertiserList = new AdvertiserList(localListView2, localContext7, i5, false, i6, i7);
	      this.mBottomContent.setVisibility(8);
	      BasePanel localBasePanel7 = this.panel;
	      View localView3 = this.mBottomContent;
	      localBasePanel7.mContent = localView3;
	      int i8 = getH();
	      localLayoutParams4 = new RelativeLayout.LayoutParams(-1, i8);
	      RelativeLayout.LayoutParams localLayoutParams5 = localLayoutParams4;
	      int i9 = i;
	      localLayoutParams5.addRule(i9);
	      if (paramInt2 != 0){}
	      BasePanel localBasePanel8 = this.panel;
	      View localView4 = this.mBottomContent;
	      localBasePanel8.addView(localView4, localLayoutParams4);
	      BasePanel localBasePanel9 = this.panel;
	      Button localButton6 = localButton1;
	      RelativeLayout.LayoutParams localLayoutParams6 = localLayoutParams3;
	      localBasePanel9.addView(localButton6, localLayoutParams6);
	    }
	    while (true)
	    {
	      this.panel.setListener();
	      RelativeLayout localRelativeLayout1 = new RelativeLayout(this.mContext);
	      RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(-1, -1);
	      RelativeLayout localRelativeLayout3 = localRelativeLayout1;
	      RelativeLayout.LayoutParams localLayoutParams8 = localLayoutParams7;
	      localRelativeLayout3.setLayoutParams(localLayoutParams8);
	      int i10 = Color.alpha(0);
	      RelativeLayout localRelativeLayout4 = localRelativeLayout1;
	      int i11 = i10;
	      localRelativeLayout4.setBackgroundColor(i11);
	      ViewGroup localViewGroup2 = paramViewGroup;
	      RelativeLayout localRelativeLayout5 = localRelativeLayout1;
	      localViewGroup2.addView(localRelativeLayout5);
	      if (ExchangeConstants.blur_switcher)
	      {
	        ListDrawer localListDrawer2 = this;
	        RelativeLayout localRelativeLayout6 = localRelativeLayout1;
	        RelativeLayout localRelativeLayout7 = localListDrawer2.getBlur(localRelativeLayout6);
	        this.panel.blurLayout = localRelativeLayout7;
	      }
	      BasePanel localBasePanel10 = this.panel;
	      RelativeLayout localRelativeLayout8 = localRelativeLayout1;
	      BasePanel localBasePanel11 = localBasePanel10;
	      RelativeLayout.LayoutParams localLayoutParams9 = localLayoutParams1;
	      localRelativeLayout8.addView(localBasePanel11, localLayoutParams9);
	      View localView5 = this.mBottomContent;
	      int i12 = IdMapper.back();
	      Button localButton7 = (Button)localView5.findViewById(i12);
	      localButton7.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* BasePanel localBasePanel;
	    	    if (!this.this$0.panel.mAnimating)
	    	    {
	    	      localBasePanel = this.this$0.panel;
	    	      if (!this.this$0.panel.isOpen())
	    	        break label45;
	    	    }
	    	    int j;
	    	      localBasePanel.setOpen(false, true);
	    	      return;*/
	    	  }
			
		});
	      View localView6 = this.mBottomContent;
	      int i13 = IdMapper.more();
	      Button localButton8 = (Button)localView6.findViewById(i13);
	      Button localButton9 = localButton8;
	      localButton9.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* try
	    	    {
	    	      Context localContext = this.this$0.mContext;
	    	      Class localClass = Class.forName("com.exchange.View.ListCurtainActivity");
	    	      Intent localIntent = new Intent(localContext, localClass);
	    	      this.this$0.mContext.startActivity(localIntent);
	    	      return;
	    	    }
	    	    catch (ClassNotFoundException localClassNotFoundException)
	    	    {
	    	      while (true)
	    	        localClassNotFoundException.printStackTrace();
	    	    }*/
	    	  }
		});
	      i = 12;
	      n = DrawableMapper.exchange_bottom_switcher_collapsed_background();
	      BasePanel localBasePanel12 = this.panel;
	      Button localButton10 = localButton1;
	      RelativeLayout.LayoutParams localLayoutParams10 = localLayoutParams3;
	      localBasePanel12.addView(localButton10, localLayoutParams10);
	      BasePanel localBasePanel13 = this.panel;
	      View localView7 = this.mBottomContent;
	      localBasePanel13.addView(localView7, localLayoutParams4);
	    }
	  }
}
