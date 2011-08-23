package com.rent.activitiy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.rent.R;

public class SplashActivity extends Activity {

	protected boolean _active = true;
	protected int _splashTime = 5000;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		boolean bool = requestWindowFeature(1);
		setContentView(R.layout.splash);
		getWindow().setFlags(1024, 1024);
		// thread for displaying the SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					finish();
					startActivity(new Intent(
							"com.rent.activitiy.MainActivity"));
					stop();
				}
			}
		};
		splashTread.start();
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
	
}