package com.xiami.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xiami.XiamiApp;
import com.xiami.lib.data.Member;
import com.xiami.localdb.DbHelper;
import com.xiami.service.MusicPlayService;

public class StartActivity extends Activity {

	private static final int GO_LOGIN = 1;
	  private static final int GO_MAIN = 2;
	  private XiamiApp app;
	  private Handler mHandler;

	  public StartActivity()
	  {
		  StartActivity1 local1 = new StartActivity1();
	    this.mHandler = local1;
	  }
	  
	  class StartActivity1 extends Handler
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      /*Intent localIntent1 = new Intent();
	      Intent localIntent2 = localIntent1.addFlags(67108864);
	      switch (paramMessage.what)
	      {
	      default:
	    	  super.handleMessage(paramMessage);
	      case 1:
	    	  	StartActivity localStartActivity1 = this.this$0;
		        Intent localIntent3 = localIntent1.setClass(localStartActivity1, LoginActivity.class);
		        startActivity(localIntent1);
		        finish();
	      case 2:
	    	  StartActivity localStartActivity2 = this.this$0;
		        Intent localIntent4 = localIntent1.setClass(localStartActivity2, MainActivity.class);
		        startActivity(localIntent1);
		        finish();
	      }*/
	    }
	  }

	  public void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    Intent localIntent = new Intent(this, MusicPlayService.class);
	    ComponentName localComponentName = startService(localIntent);
//	    MobclickAgent.onError(this);
	    setContentView(2130903128);
	    getWindow().setFlags(1024, 1024);
	    XiamiApp localXiamiApp = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp;
	  }

	  protected void onResume()
	  {
		  super.onResume();
	    /*Member localMember = DbHelper.getInstance(this).getLoginMember();
	    if ((localMember != null) && (localMember.getUser_id() > 0))
	    {
	      this.app.setMember(localMember);
	      boolean bool1 = this.mHandler.sendEmptyMessageDelayed(2, 500L);
	      
	    }
	    else 
	    {
	      boolean bool2 = this.mHandler.sendEmptyMessageDelayed(1, 3000L);
	    }*/
	  }

	  protected void onStart()
	  {
	    super.onStart();
	  }
}
