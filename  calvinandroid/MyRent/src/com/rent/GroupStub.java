package com.rent;

import android.app.Activity;

public class GroupStub extends GroupTab {

	protected Class<? extends Activity> mActivityClass;

	public GroupStub(String paramString, int iconOn, int iconOff,
			Class<? extends Activity> paramClass) {
		super(paramString, iconOn, iconOff);
		this.mActivityClass = paramClass;
	}

	public Class<? extends Activity> getActivityClass() {
		return this.mActivityClass;
	}
}
