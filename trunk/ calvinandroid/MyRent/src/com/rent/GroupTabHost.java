package com.rent;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GroupTabHost extends ImageView {

	private final ArrayList<GroupTab> mTabs;
	
	public GroupTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTabs = new ArrayList<GroupTab>();
	}

	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mTabs.size(); i++) {
			GroupTab groupTab = mTabs.get(i);
			
		}
	}

	public void addTab(GroupTab paramGroupTab) {
		this.mTabs.add(paramGroupTab);
	}

}
