package com.rent.view;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.rent.exchange.ExchangeDataService;
import com.rent.exchange.GetDataThread;
import com.rent.exchange.ReportThread;
import com.rent.exchange.SwitchAdListener;
import com.rent.exchange.SwithAdThread;
import com.rent.exchange.common.AnimMapper;
import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.common.RetriveImageThread;
import com.rent.exchange.model.AdvertiserConfig;
import com.rent.listener.ExchangeDataRequestListener;

public class StandaloneBanner implements ExchangeDataRequestListener, SwitchAdListener {

	 Context mContext;
	  ViewGroup mFatherLayout;
	  View mHandleContent;
	  int mType;

	  public StandaloneBanner(Context paramContext, ViewGroup paramViewGroup, int paramInt)
	  {
	    this.mContext = paramContext;
	    this.mFatherLayout = paramViewGroup;
	    this.mType = paramInt;
	    Context localContext = this.mContext;
	    new GetDataThread(localContext, this).start();
	  }

	  public void dataReceived(int paramInt)
	  {
	    if (paramInt == 0) {
	    	Log.i(ExchangeConstants.LOG_TAG, "failed to get request data");
	  } else 
	    {
	      getHandler();
	      new SwithAdThread(this).start();
	      Context localContext = this.mContext;
	      AdvertiserConfig localAdvertiserConfig = ExchangeDataService.getCurAd();
	      int j = this.mType;
	      int k = 0;
	      new ReportThread(0, localContext, localAdvertiserConfig, j, 1, k).start();
	    }
	  }

	  void getHandler()
	  {
	    Context localContext1 = this.mContext;
	    int i = LayoutMapper.exchange_big_handler_bottom_handler();
	    View localView1 = View.inflate(localContext1, i, null);
	    int j = IdMapper.banner_bg();
	    ImageView localImageView1 = (ImageView)localView1.findViewById(j);
	    int k = ExchangeConstants.banner_alpha;
	    localImageView1.setAlpha(k);
	    int m = IdMapper.flipper();
	    ViewFlipper localViewFlipper = (ViewFlipper)localView1.findViewById(m);
	    localViewFlipper.setFlipInterval(10000);
	    Context localContext2 = this.mContext;
	    int n = AnimMapper.exchange_push_up_in();
	    Animation localAnimation1 = AnimationUtils.loadAnimation(localContext2, n);
	    localViewFlipper.setInAnimation(localAnimation1);
	    Context localContext3 = this.mContext;
	    int i1 = AnimMapper.exchange_push_up_out();
	    Animation localAnimation2 = AnimationUtils.loadAnimation(localContext3, i1);
	    localViewFlipper.setOutAnimation(localAnimation2);
	    Context localContext4 = this.mContext;
	    int i2 = LayoutMapper.exchange_big_handler_flipper_content();
	    View localView2 = View.inflate(localContext4, i2, null);
	    localViewFlipper.addView(localView2);
	    AdvertiserConfig localAdvertiserConfig = ExchangeDataService.getCurAd();
	    int i3 = IdMapper.name0();
	    TextView localTextView1 = (TextView)localView1.findViewById(i3);
	    String str1 = localAdvertiserConfig.adName;
	    localTextView1.setText(str1);
	    int i4 = IdMapper.size0();
	    TextView localTextView2 = (TextView)localView1.findViewById(i4);
	    if (ExchangeConstants.show_size)
	    {
	      String str2 = ExchangeConstants.getFileSizeDescription(localAdvertiserConfig.fileSize);
	      localTextView2.setText(str2);
	    }
	    while (true)
	    {
	      int i5 = IdMapper.content0();
	      TextView localTextView3 = (TextView)localView1.findViewById(i5);
	      String str3 = localAdvertiserConfig.adDescription;
	      localTextView3.setText(str3);
	      int i6 = IdMapper.appIcon0();
	      ImageView localImageView2 = (ImageView)localView1.findViewById(i6);
	      Context localContext5 = this.mContext;
	      String str4 = localAdvertiserConfig.adIconUrl;
	      new RetriveImageThread(localContext5, localImageView2, str4).start();
	      localView1.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*Context localContext1 = this.this$0.mContext;
	    	    AdvertiserConfig localAdvertiserConfig = ExchangeDataService.getCurAd();
	    	    int i = this.this$0.mType;
	    	    new ReportThread(2, localContext1, localAdvertiserConfig, i, 1, 0).start();
	    	    try
	    	    {
	    	      Context localContext2 = this.this$0.mContext;
	    	      Class localClass = Class.forName("com.exchange.View.PearlCurtainActivity");
	    	      Intent localIntent1 = new Intent(localContext2, localClass);
	    	      Bundle localBundle = new Bundle();
	    	      int j = ExchangeDataService.curIndex;
	    	      localBundle.putInt("position", j);
	    	      Intent localIntent2 = localIntent1.putExtras(localBundle);
	    	      this.this$0.mContext.startActivity(localIntent1);
	    	      return;
	    	    }
	    	    catch (ClassNotFoundException localClassNotFoundException)
	    	    {
	    	      while (true)
	    	        localClassNotFoundException.printStackTrace();
	    	    }*/
	    	  }
		});
	      this.mHandleContent = localView1;
	      Context localContext6 = this.mContext;
	      int i7 = DeviceManager.dpToPix(55, localContext6);
	      Context localContext7 = this.mContext;
	      RelativeLayout localRelativeLayout = new RelativeLayout(localContext7);
	      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, i7);
	      localRelativeLayout.setLayoutParams(localLayoutParams1);
	      int i8 = Color.alpha(0);
	      localRelativeLayout.setBackgroundColor(i8);
	      this.mFatherLayout.addView(localRelativeLayout);
	      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, i7);
	      localLayoutParams2.addRule(12);
	      View localView3 = this.mHandleContent;
	      localRelativeLayout.addView(localView3, localLayoutParams2);
	      localTextView2.setVisibility(8);
	    }
	  }

	  public void timeup()
	  {
	    AdvertiserConfig localAdvertiserConfig = null;
	    ViewFlipper localViewFlipper = null;
	    View localView2 = null;
	    TextView localTextView2 = null;
	    if (this.mHandleContent.getWindowVisibility() == 0)
	    {
	      int i = Log.i(ExchangeConstants.LOG_TAG, "switch ad");
	      boolean bool = ExchangeDataService.rotate();
	      List localList = ExchangeDataService.mAdvertisers;
	      int j = ExchangeDataService.curIndex;
	      localAdvertiserConfig = (AdvertiserConfig)localList.get(j);
	      Context localContext1 = this.mContext;
	      int k = this.mType;
	      int m = 0;
	      new ReportThread(0, localContext1, localAdvertiserConfig, k, 1, m).start();
	      View localView1 = this.mHandleContent;
	      int n = IdMapper.flipper();
	      localViewFlipper = (ViewFlipper)localView1.findViewById(n);
	      localViewFlipper.setFlipInterval(5000);
	      Context localContext2 = this.mContext;
	      int i1 = AnimMapper.exchange_push_up_in();
	      Animation localAnimation1 = AnimationUtils.loadAnimation(localContext2, i1);
	      localViewFlipper.setInAnimation(localAnimation1);
	      Context localContext3 = this.mContext;
	      int i2 = AnimMapper.exchange_push_up_out();
	      Animation localAnimation2 = AnimationUtils.loadAnimation(localContext3, i2);
	      localViewFlipper.setOutAnimation(localAnimation2);
	      Context localContext4 = this.mContext;
	      int i3 = LayoutMapper.exchange_big_handler_flipper_content();
	      localView2 = View.inflate(localContext4, i3, null);
	      localViewFlipper.addView(localView2);
	      int i4 = IdMapper.name0();
	      TextView localTextView1 = (TextView)localView2.findViewById(i4);
	      String str1 = localAdvertiserConfig.adName;
	      localTextView1.setText(str1);
	      int i5 = IdMapper.size0();
	      localTextView2 = (TextView)localView2.findViewById(i5);
	      if (!ExchangeConstants.show_size){}
	      String str2 = ExchangeConstants.getFileSizeDescription(localAdvertiserConfig.fileSize);
	      localTextView2.setText(str2);
	    } else 
	    {
	      int i6 = IdMapper.content0();
	      TextView localTextView3 = (TextView)localView2.findViewById(i6);
	      String str3 = localAdvertiserConfig.adDescription;
	      localTextView3.setText(str3);
	      View localView3 = this.mHandleContent;
	      int i7 = IdMapper.appIcon0();
	      ImageView localImageView = (ImageView)localView3.findViewById(i7);
	      Context localContext5 = this.mContext;
	      String str4 = localAdvertiserConfig.adIconUrl;
	      new RetriveImageThread(localContext5, localImageView, str4).start();
	      localViewFlipper.showNext();
	      if (localViewFlipper.getChildCount() == 3) {
	        localViewFlipper.removeViewAt(0);
	      new SwithAdThread(this).start();
	      } else 
	      {
	        Log.i(ExchangeConstants.LOG_TAG, "current window gone");
	      }
	      localTextView2.setVisibility(8);
	    }
	  }
}
