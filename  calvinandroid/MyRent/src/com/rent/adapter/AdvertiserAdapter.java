package com.rent.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.common.RetriveImageThread;
import com.rent.exchange.model.AdvertiserConfig;
import com.rent.view.IdMapper;
import com.rent.view.LayoutMapper;

public class AdvertiserAdapter extends ArrayAdapter<AdvertiserConfig> {

	Context mContext;
	  int resourceId;
	  boolean showHeader;

	  public AdvertiserAdapter(Context paramContext, int paramInt1, List<AdvertiserConfig> paramList, int paramInt2, boolean paramBoolean)
	  {
	    super(paramContext, paramInt1, paramList);
	    this.mContext = paramContext;
	    this.resourceId = paramInt2;
	    this.showHeader = paramBoolean;
	  }

	  public long getItemId(int paramInt)
	  {
	    return ((AdvertiserConfig)getItem(paramInt)).getId();
	  }

	  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	  {
	    AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)getItem(paramInt);
	    LayoutInflater localLayoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
	    int i = this.resourceId;
	    paramView = localLayoutInflater.inflate(i, null);
	    View localView1;
	    if ((this.showHeader) && (paramInt == 0))
	    {
	      int j = Log.i(ExchangeConstants.LOG_TAG, "show header");
	      int k = LayoutMapper.exchange_recom_header();
	      localView1 = localLayoutInflater.inflate(k, null);
	      return localView1;
	    }
	    int m = IdMapper.icon();
	    ImageView localImageView = (ImageView)paramView.findViewById(m);
	    if (localImageView != null)
	    {
	      Context localContext = this.mContext;
	      String str1 = localAdvertiserConfig.adIconUrl;
	      new RetriveImageThread(localContext, localImageView, str1).start();
	      if (localAdvertiserConfig.adName == null)
	      {
	        int n = IdMapper.exchange_icon_container();
	        View localView2 = paramView.findViewById(n);
	        if (localView2 != null)
	          localView2.setVisibility(8);
	      }
	    }
	    int i1 = IdMapper.open_type();
	    TextView localTextView1 = (TextView)paramView.findViewById(i1);
	    String str2;
	    TextView localTextView4 = null;
	    if (localTextView1 != null)
	    {
	      if (localAdvertiserConfig.installed)
	      {
	        str2 = "Test";
	        localTextView1.setText(str2);
	      }
	    }
	    else
	    {
	      int i2 = IdMapper.name();
	      TextView localTextView2 = (TextView)paramView.findViewById(i2);
	      if (localTextView2 != null)
	      {
	        int i3 = Color.parseColor(ExchangeConstants.text_color);
	        localTextView2.setTextColor(i3);
	        String str3 = localAdvertiserConfig.adName;
	        localTextView2.setText(str3);
	      }
	      int i4 = IdMapper.des();
	      TextView localTextView3 = (TextView)paramView.findViewById(i4);
	      if (localTextView3 != null)
	      {
	        String str4 = localAdvertiserConfig.adDescription;
	        localTextView3.setText(str4);
	      }
	      int i5 = IdMapper.size0();
	      localTextView4 = (TextView)paramView.findViewById(i5);
	      if (!ExchangeConstants.show_size){}
	      if (localTextView4 != null)
	      {
	        String str5 = ExchangeConstants.getFileSizeDescription(localAdvertiserConfig.fileSize);
	        localTextView4.setText(str5);
	      }
	    }
	    while (true)
	    {
	      localView1 = paramView;
	      str2 = "Test";
	      if (localTextView4 == null){} else {
	    	  localTextView4.setVisibility(8);
	      }
	    }
	  }
}
