package com.rent.exchange;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.rent.exchange.model.AdvertiserConfig;

public class ReportThread extends Thread{

	int action;
	  int mActionIndex;
	  Context mContext;
	  int mPageLevel;
	  List<AdvertiserConfig> mReportList;
	  int mType;

	  public ReportThread(int paramInt1, Context paramContext, AdvertiserConfig paramAdvertiserConfig, int paramInt2, int paramInt3, int paramInt4)
	  {
	    this.mActionIndex = paramInt4;
	    this.mPageLevel = paramInt3;
	    this.action = paramInt1;
	    this.mContext = paramContext;
	    ArrayList localArrayList = new ArrayList();
	    this.mReportList = localArrayList;
	    boolean bool = this.mReportList.add(paramAdvertiserConfig);
	    this.mType = paramInt2;
	  }

	  public ReportThread(int paramInt1, Context paramContext, List<AdvertiserConfig> paramList, int paramInt2, int paramInt3, int paramInt4)
	  {
	    this.mActionIndex = paramInt4;
	    this.mPageLevel = paramInt3;
	    this.action = paramInt1;
	    this.mContext = paramContext;
	    this.mReportList = paramList;
	    this.mType = paramInt2;
	  }

	  public void run()
	  {
	    int i = 0;
	    try
	    {
	      while (true)
	      {
	        int j = this.mReportList.size();
	        if (i >= j)
	        {
	          int k = this.mActionIndex;
	          int m = this.mPageLevel;
	          int n = this.mType;
	          List localList = this.mReportList;
	          int i1 = ExchangeDataService.report(k, m, n, localList);
	          return;
	        }
	        AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)this.mReportList.get(i);
	        int i2 = this.action;
	        localAdvertiserConfig.action = i2;
	        i += 1;
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        localException.printStackTrace();
	    }
	  }
	  
}
