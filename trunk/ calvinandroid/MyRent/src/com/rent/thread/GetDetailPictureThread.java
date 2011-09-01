package com.rent.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.rent.Rent;
import com.rent.UIUtils;

public class GetDetailPictureThread extends Thread {

	private Bundle mBundle;
	  private Context mContext;
	  private Handler mHandler;
	  private Message mMsg;
	  private Map<String, String> mNeedDownloadMap;
	  private ArrayList<String> mPicNameAL;

	  public GetDetailPictureThread(Context paramContext, Handler paramHandler, Map<String, String> paramMap)
	  {
	    this.mHandler = paramHandler;
	    this.mNeedDownloadMap = paramMap;
	    ArrayList localArrayList = new ArrayList();
	    this.mPicNameAL = localArrayList;
	    Message localMessage = new Message();
	    this.mMsg = localMessage;
	    this.mContext = paramContext;
	    Bundle localBundle = new Bundle();
	    this.mBundle = localBundle;
	  }

	  private void downPicFromUrlString()
	    throws Exception
	  {
	    Iterator localIterator = this.mNeedDownloadMap.keySet().iterator();
	    while (localIterator.hasNext())
	    {
	      String str = (String)localIterator.next();
	      byte[] arrayOfByte = Rent.downLoadImage((String)this.mNeedDownloadMap.get(str));
	      boolean bool = this.mPicNameAL.add(str);
	      this.mBundle.putByteArray(str, arrayOfByte);
	      Message localMessage1 = this.mMsg;
	      Bundle localBundle1 = this.mBundle;
	      localMessage1.setData(localBundle1);
	    }
	    Collections.sort(this.mPicNameAL);
	    Bundle localBundle2 = this.mBundle;
	    ArrayList localArrayList = this.mPicNameAL;
	    localBundle2.putStringArrayList("pic_name", localArrayList);
	    Message localMessage2 = this.mMsg;
	    Bundle localBundle3 = this.mBundle;
	    localMessage2.setData(localBundle3);
	  }

	  public void run()
	  {
	    super.run();
	    Looper.prepare();
	    try
	    {
	      downPicFromUrlString();
	      Handler localHandler = this.mHandler;
	      Message localMessage = this.mMsg;
	      boolean bool = localHandler.sendMessage(localMessage);
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        UIUtils.displayToast(this.mContext, 2131361891);
	        Looper.myLooper().quit();
	      }
	    }
	    finally
	    {
	      Looper.myLooper().quit();
	    }
	  }
	  
}
