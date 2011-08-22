package com.rent.activitiy;


import android.app.Activity;
import android.os.Bundle;

import com.rent.R;

public class SplashActivity extends Activity{

	 protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    boolean bool = requestWindowFeature(1);
	    setContentView(R.layout.splash);
	    getWindow().setFlags(1024, 1024);
//	    Intent i = new Intent(SplashActivity.class, );
	  }
	
}
