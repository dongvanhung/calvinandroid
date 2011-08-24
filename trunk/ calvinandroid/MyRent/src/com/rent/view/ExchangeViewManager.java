package com.rent.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class ExchangeViewManager {

	ListDrawer listDrawer = null;
	  Context mContext;
	  int mType;
	  PearlDrawer pearlDrawer = null;
	  StandaloneBanner standaloneBanner = null;

	  public void addView(Context paramContext, int paramInt)
	  {
	    try
	    {
	      this.mContext = paramContext;
	      this.mType = paramInt;
	      if (paramInt != 7)
	        int i = Log.e(ExchangeConstants.LOG_TAG, "cannot invoke in-page promotion with our father layout");
	      while (true)
	      {
	        return;
	        try
	        {
	          Class localClass = Class.forName("com.exchange.View.ListCurtainActivity");
	          Intent localIntent = new Intent(paramContext, localClass);
	          paramContext.startActivity(localIntent);
	        }
	        catch (ClassNotFoundException localClassNotFoundException)
	        {
	          localClassNotFoundException.printStackTrace();
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        localException.printStackTrace();
	    }
	  }

	  public void addView(Context paramContext, ViewGroup paramViewGroup, int paramInt)
	  {
	    try
	    {
	      this.mContext = paramContext;
	      if ((ExchangeConstants.ONLY_CHINESE) && (!DeviceManager.isChinese(this.mContext)))
	        int i = Log.e(ExchangeConstants.LOG_TAG, "English os can not show ads");
	      while (true)
	      {
	        return;
	        if ((ExchangeDataService.hasData()) && (ExchangeDataService.mAdvertisers.size() == 1))
	        {
	          if (paramInt != 4)
	            break;
	          paramInt = 2;
	        }
	        this.mType = paramInt;
	        switch (paramInt)
	        {
	        default:
	          int j = Log.e(ExchangeConstants.LOG_TAG, "paramter type cannot be handled");
	        case 0:
	        case 1:
	        case 2:
	        case 3:
	        case 4:
	        case 5:
	        case 6:
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        String str1 = ExchangeConstants.LOG_TAG;
	        StringBuilder localStringBuilder = new StringBuilder("add view error");
	        String str2 = localException.getMessage();
	        String str3 = str2;
	        int k = Log.e(str1, str3);
	        continue;
	        if (paramInt != 5)
	          continue;
	        paramInt = 3;
	        continue;
	        PearlDrawer localPearlDrawer = new PearlDrawer(paramContext, paramViewGroup, paramInt);
	        this.pearlDrawer = localPearlDrawer;
	        continue;
	        ListDrawer localListDrawer = new ListDrawer(paramContext, paramViewGroup, paramInt);
	        this.listDrawer = localListDrawer;
	        continue;
	        StandaloneBanner localStandaloneBanner = new StandaloneBanner(paramContext, paramViewGroup, paramInt);
	        this.standaloneBanner = localStandaloneBanner;
	      }
	    }
	  }

	  public void hideBanner()
	  {
	    try
	    {
	      switch (this.mType)
	      {
	      default:
	        int i = Log.e(ExchangeConstants.LOG_TAG, "paramter type cannot be handled");
	      case 0:
	      case 1:
	      case 2:
	      case 3:
	        while (true)
	        {
	          return;
	          if (this.pearlDrawer == null)
	            continue;
	          Context localContext1 = this.mContext;
	          View localView1 = this.pearlDrawer.mHandleContent;
	          ExchangeAnimation.disappearDown(localContext1, localView1);
	        }
	      case 4:
	      case 5:
	      case 6:
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int j = Log.e(ExchangeConstants.LOG_TAG, "hideBanner error");
	        continue;
	        if (this.listDrawer == null)
	          continue;
	        Context localContext2 = this.mContext;
	        View localView2 = this.listDrawer.mHandleContent;
	        ExchangeAnimation.disappearDown(localContext2, localView2);
	        continue;
	        if (this.standaloneBanner == null)
	          continue;
	        ExchangeAnimation.disappearSlowly(this.standaloneBanner.mHandleContent);
	      }
	    }
	  }

	  public boolean isFling()
	  {
	    try
	    {
	      switch (this.mType)
	      {
	      default:
	        bool1 = false;
	      case 0:
	      case 1:
	      case 2:
	      case 3:
	      case 4:
	      case 5:
	      }
	      while (true)
	      {
	        return bool1;
	        if (this.pearlDrawer == null)
	        {
	          bool1 = false;
	          continue;
	        }
	        bool1 = this.pearlDrawer.panel.isFling();
	        continue;
	        if (this.listDrawer == null)
	        {
	          bool1 = false;
	          continue;
	        }
	        boolean bool2 = this.listDrawer.panel.isFling();
	        bool1 = bool2;
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int i = Log.e(ExchangeConstants.LOG_TAG, "isFling error");
	        boolean bool1 = false;
	      }
	    }
	  }

	  public boolean isOpen()
	  {
	    try
	    {
	      switch (this.mType)
	      {
	      default:
	        bool1 = false;
	      case 0:
	      case 1:
	      case 2:
	      case 3:
	      case 4:
	      case 5:
	      }
	      while (true)
	      {
	        return bool1;
	        if (this.pearlDrawer == null)
	        {
	          bool1 = false;
	          continue;
	        }
	        bool1 = this.pearlDrawer.panel.isOpen();
	        continue;
	        if (this.listDrawer == null)
	        {
	          bool1 = false;
	          continue;
	        }
	        boolean bool2 = this.listDrawer.panel.isOpen();
	        bool1 = bool2;
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int i = Log.e(ExchangeConstants.LOG_TAG, "isOpen error");
	        boolean bool1 = false;
	      }
	    }
	  }

	  public void showBanner()
	  {
	    try
	    {
	      switch (this.mType)
	      {
	      default:
	        int i = Log.e(ExchangeConstants.LOG_TAG, "paramter type cannot be handled");
	      case 0:
	      case 1:
	      case 2:
	      case 3:
	        while (true)
	        {
	          return;
	          if (this.pearlDrawer == null)
	            continue;
	          Context localContext1 = this.mContext;
	          View localView1 = this.pearlDrawer.mHandleContent;
	          ExchangeAnimation.showUp(localContext1, localView1);
	        }
	      case 4:
	      case 5:
	      case 6:
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int j = Log.e(ExchangeConstants.LOG_TAG, "showBanner error");
	        continue;
	        if (this.listDrawer == null)
	          continue;
	        Context localContext2 = this.mContext;
	        View localView2 = this.listDrawer.mHandleContent;
	        ExchangeAnimation.showUp(localContext2, localView2);
	        continue;
	        if (this.standaloneBanner == null)
	          continue;
	        ExchangeAnimation.showSlowly(this.standaloneBanner.mHandleContent);
	      }
	    }
	  }

	  public void toggle()
	  {
	    try
	    {
	      switch (this.mType)
	      {
	      default:
	        int i = Log.e(ExchangeConstants.LOG_TAG, "paramter type cannot be handled");
	      case 0:
	      case 1:
	      case 2:
	      case 3:
	        while (true)
	        {
	          return;
	          if (!this.pearlDrawer.panel.isOpen())
	            break;
	          int j = 0;
	          if (this.pearlDrawer == null)
	            continue;
	          this.pearlDrawer.panel.setOpen(j, 1);
	        }
	      case 4:
	      case 5:
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int n = Log.e(ExchangeConstants.LOG_TAG, "toggle error");
	        continue;
	        k = 1;
	      }
	      if (!this.listDrawer.panel.isOpen());
	    }
	    int m;
	    for (int k = 0; this.listDrawer != null; m = 1)
	    {
	      this.listDrawer.panel.setOpen(k, 1);
	      break;
	    }
	  }
	  
}
