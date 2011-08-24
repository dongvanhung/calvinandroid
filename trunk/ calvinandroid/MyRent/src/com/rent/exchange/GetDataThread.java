package com.rent.exchange;

import java.util.List;
import java.util.Set;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.common.R;
import com.rent.exchange.model.AdvertiserConfig;
import com.rent.listener.ExchangeDataRequestListener;

public class GetDataThread extends Thread {

	final Handler getDataHandler;
	  Context mContext;
	  public ExchangeDataRequestListener mDataReceiverListener;

	  public GetDataThread(Context paramContext, ExchangeDataRequestListener paramExchangeDataRequestListener)
	  {
	    GetDataThread1 local1 = new GetDataThread1(this);
	    this.getDataHandler = local1;
	    this.mContext = paramContext;
	    this.mDataReceiverListener = paramExchangeDataRequestListener;
	    init(paramContext);
	  }
	  
	  class GetDataThread1 extends Handler
	  {	
		  private GetDataThread gd; 
		  public GetDataThread1(GetDataThread gd) {
			  this.gd = gd;
		  }
	    public void handleMessage(Message paramMessage)
	    {
	      ExchangeDataRequestListener localExchangeDataRequestListener = gd.mDataReceiverListener;
	      int i = paramMessage.what;
	      localExchangeDataRequestListener.dataReceived(i);
	    }
	  }

	  public static void init(Context paramContext)
	  {
	    R.init(paramContext);
	    int[] arrayOfInt = new int[8];
	    int i = R.drawable("exchange_zhanwei");
	    arrayOfInt[0] = i;
	    int j = R.drawable("exchange_zhanwei");
	    arrayOfInt[1] = j;
	    int k = R.drawable("exchange_zhanwei");
	    arrayOfInt[2] = k;
	    int m = R.drawable("exchange_zhanwei");
	    arrayOfInt[3] = m;
	    int n = R.drawable("exchange_zhanwei");
	    arrayOfInt[4] = n;
	    int i1 = R.drawable("exchange_zhanwei");
	    arrayOfInt[5] = i1;
	    int i2 = R.drawable("exchange_zhanwei");
	    arrayOfInt[6] = i2;
	    int i3 = R.drawable("exchange_zhanwei");
	    arrayOfInt[7] = i3;
	    ExchangeConstants.appIcon = arrayOfInt;
	  }

	  public void run()
	  {
	    synchronized (ExchangeConstants.getDataMutex)
	    {
	      try
	      {
	        ExchangeDataService.context = this.mContext;
	        if (!ExchangeDataService.hasData())
	        {
	          if (ExchangeConstants.isTestMode){} else {
	          ExchangeDataService.request();
	          }
	        }
	        while (!ExchangeDataService.hasData())
	        {
	          boolean bool1 = this.getDataHandler.sendEmptyMessage(0);
	          int i = 0;
	          List localList = ExchangeDataService.getExampleAds(i);
	          int j = ExchangeConstants.REQUEST_NUMBER;
	          ExchangeDataService.mAdvertisers = localList.subList(0, j);
	        }
	      }
	      catch (Exception localException)
	      {
	        while (true)
	          localException.printStackTrace();
	      }
	    }
	    int k = ExchangeDataService.mAdvertisers.size();
	    int m = ExchangeConstants.CURTAIN_PEARL_COUNT_VERTICAL;
	    if (k < m)
	      ExchangeConstants.CURTAIN_PEARL_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
	    int n = ExchangeDataService.mAdvertisers.size();
	    int i1 = ExchangeConstants.CURTAIN_LIST_COUNT_VERTICAL;
	    if (n < i1)
	      ExchangeConstants.CURTAIN_LIST_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
	    int i2 = ExchangeDataService.mAdvertisers.size();
	    int i3 = ExchangeConstants.DRAWER_LIST_COUNT_VERTICAL;
	    if (i2 < i3)
	      ExchangeConstants.DRAWER_LIST_COUNT_VERTICAL = ExchangeDataService.mAdvertisers.size();
	    int i4 = ExchangeDataService.mAdvertisers.size();
	    int i5 = ExchangeConstants.CONTAINER_LIST_COUNT;
	    if (i4 < i5)
	      ExchangeConstants.CONTAINER_LIST_COUNT = ExchangeDataService.mAdvertisers.size();
	    Set localSet1 = DeviceManager.getInstalledPackages(this.mContext);
	    int i6 = 0;
	    if (DeviceManager.installedApps != null) {
	      i6 = 0;
	    } else {
	      int i7 = ExchangeDataService.mAdvertisers.size();
	      if (i6 >= i7)
	      {
	        boolean bool2 = this.getDataHandler.sendEmptyMessage(1);
	      }
	      AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)ExchangeDataService.mAdvertisers.get(i6);
	      Set localSet2 = DeviceManager.installedApps;
	      String str = localAdvertiserConfig.packageName;
	      if (localSet2.contains(str))
	        localAdvertiserConfig.installed = true;
	      i6 += 1;
	    }
	  }
}
