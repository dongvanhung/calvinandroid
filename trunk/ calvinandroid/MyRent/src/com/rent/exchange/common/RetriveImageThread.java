package com.rent.exchange.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.rent.view.DrawableMapper;

public class RetriveImageThread extends Thread{

	Context mContext;
	  ImageView mIcon;
	  String mUrl;
	  Handler retriveImageHandler;

	  public RetriveImageThread(Context paramContext, ImageView paramImageView, String paramString)
	  {
	    RetriveImageThread1 local1 = new RetriveImageThread1();
	    this.retriveImageHandler = local1;
	    this.mContext = paramContext;
	    this.mIcon = paramImageView;
	    this.mUrl = paramString;
	  }
	  
	  class RetriveImageThread1 extends Handler
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      RetriveImageThread.Pair localPair = (RetriveImageThread.Pair)paramMessage.obj;
	      if (localPair != null)
	      {
	        ImageView localImageView = localPair.mIcon;
	        Drawable localDrawable = localPair.mDrawable;
	        localImageView.setBackgroundDrawable(localDrawable);
	      }
	    }
	  }
	  
	  public void run()
	  {
	    Drawable localDrawable1 = Network.fetchImage(this.mUrl);
	    Message localMessage = new Message();
	    Pair localPair1 = null;
	    if (localDrawable1 == null)
	    {
	      ImageView localImageView1 = this.mIcon;
	      Resources localResources = this.mContext.getResources();
	      int i = DrawableMapper.exchange_zhanwei();
	      Drawable localDrawable2 = localResources.getDrawable(i);
	      localPair1 = new Pair(localImageView1, localDrawable2);
	    }
	    Pair localPair2;
	    for (localMessage.obj = localPair1; ; localMessage.obj = localPair2)
	    {
	      boolean bool = this.retriveImageHandler.sendMessage(localMessage);
	      ImageView localImageView2 = this.mIcon;
	      localPair2 = new Pair(localImageView2, localDrawable1);
	    }
	  }

	  public class Pair
	  {
	    Drawable mDrawable;
	    ImageView mIcon;

	    public Pair(ImageView paramDrawable, Drawable arg3)
	    {
	      this.mIcon = paramDrawable;
	      this.mDrawable = arg3;
	    }
	  }
}
