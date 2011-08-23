package com.rent.activitiy;

import java.util.List;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.rent.GroupHelper;
import com.rent.GroupStub;
import com.rent.GroupTabHost;
import com.rent.PreferenceUtils;
import com.rent.R;

public class MainActivity extends ActivityGroup implements
		GroupHelper.TabListener {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		boolean bool = requestWindowFeature(1);
		setContentView(R.layout.main);
		initTab();
		// InnerHandler localInnerHandler = new InnerHandler();
		// new InnerThread(localInnerHandler).start();
	}

	private void initTab() {
		LinearLayout localLinearLayout = (LinearLayout) findViewById(R.id.container);
		GroupStub[] arrayOfGroupStub = new GroupStub[4];
		String str1 = getString(R.string.search_view_tab);
		GroupStub localGroupStub1 = new GroupStub(str1,
				R.drawable.search_tab_icon_on, R.drawable.search_tab_icon_off,
				SearchActivity.class);
		arrayOfGroupStub[0] = localGroupStub1;
		String str2 = getString(R.string.history_view_tab);
		GroupStub localGroupStub2 = new GroupStub(str2,
				R.drawable.history_tab_icon_on,
				R.drawable.history_tab_icon_off, HistoryActivity.class);
		arrayOfGroupStub[1] = localGroupStub2;
		String str3 = getString(R.string.inventory_view_tab);
		GroupStub localGroupStub3 = new GroupStub(str3,
				R.drawable.inventory_tab_icon_on,
				R.drawable.inventory_tab_icon_off, InventoryActivity.class);
		arrayOfGroupStub[2] = localGroupStub3;
		String str4 = getString(R.string.more_view_tab);
		GroupStub localGroupStub4 = new GroupStub(str4,
				R.drawable.more_icon_on, R.drawable.more_icon_off,
				MoreActivity.class);
		arrayOfGroupStub[3] = localGroupStub4;
		GroupTabHost localGroupTabHost = (GroupTabHost) findViewById(R.id.tab_host);
		// localGroupTabHost.setNewMessageIcon(2130837657);
		GroupHelper groupHelper = new GroupHelper();
		groupHelper.init(this, localLinearLayout, localGroupTabHost,
				arrayOfGroupStub, this);
		localGroupTabHost.setActiveTab(0);
		localGroupTabHost.fireTabChanged();
	}

	public void setCurrentTabId(int paramInt) {
		PreferenceUtils.setRefreshStatus(this, 2);
		Activity localActivity;
		if (1 == paramInt) {
			localActivity = GroupHelper.getActivityById(this, 1);
			if ((localActivity != null)
					&& ((localActivity instanceof HistoryActivity))) {
				// ((HistoryActivity)localActivity).setMainContext(this);
			}
		}
		if (3 == paramInt) {
			localActivity = GroupHelper.getActivityById(this, 3);
			if ((localActivity == null)
					|| (!(localActivity instanceof MoreActivity))) {
			}
			/*
			 * ((MoreActivity)localActivity).setMainContext(this); continue;
			 */
		}
	}

	private void clearCache() {
		/*GroupImageManager.clearCache();
		DetailDBManager localDetailDBManager = new DetailDBManager(this);
		List localList = localDetailDBManager.getCacheList();
		localDetailDBManager.closeDb();
		DetailImageManager.clearCache(localList);
		GroupImageManager.clearCache();*/
	}
}
