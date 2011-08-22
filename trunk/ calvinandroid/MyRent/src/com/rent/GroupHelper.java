package com.rent;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class GroupHelper {
	
	public void init(ActivityGroup paramActivityGroup,
			LinearLayout paramLinearLayout, GroupTabHost paramGroupTabHost,
			GroupStub[] paramArrayOfGroupStub, TabListener paramTabListener) {
		
		for (int i = 0; i < paramArrayOfGroupStub.length; i++) {
			String str = paramArrayOfGroupStub[i].getText();
			int k = paramArrayOfGroupStub[i].getActiveIconId();
			int m = paramArrayOfGroupStub[i].getInactiveIconId();
			GroupTab localGroupTab = new GroupTab(str, k, m);
			paramGroupTabHost.addTab(localGroupTab);
			paramGroupTabHost.setOnClickListener(new TabClickListener(i, paramActivityGroup, paramLinearLayout, paramArrayOfGroupStub[i]));
		}
	}
	
	final class TabClickListener implements OnClickListener {
		private int paramInt;
		private ActivityGroup paramActivityGroup;
		private LinearLayout paramLinearLayout;
		private GroupStub subGroupStub;
		
		public TabClickListener(int paramInt, ActivityGroup paramActivityGroup, LinearLayout paramLinearLayout, GroupStub subGroupStub) {
			this.paramInt = paramInt;
			this.paramActivityGroup = paramActivityGroup;
			this.paramLinearLayout = paramLinearLayout;
			this.subGroupStub = subGroupStub;
		}
		
		public void onClick(View v) {
			paramLinearLayout.removeAllViews();
		    Class localClass = subGroupStub.getActivityClass();
		    Intent localIntent1 = new Intent(paramActivityGroup, localClass);//131072
		    Intent localIntent2 = localIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    LocalActivityManager localLocalActivityManager = paramActivityGroup.getLocalActivityManager();
		    String str = "subActivity" + paramInt;
		    Window localWindow = localLocalActivityManager.startActivity(str, localIntent1);
		    View localView = localWindow.getDecorView();
		    paramLinearLayout.addView(localView, -1, -1);
		    //this.val$listener.setCurrentTabId(paramInt);
		}		
	}
	
	public abstract interface TabListener {
		public abstract void setCurrentTabId(int paramInt);
	}
}
