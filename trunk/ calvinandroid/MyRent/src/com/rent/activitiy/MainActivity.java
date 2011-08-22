package com.rent.activitiy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.rent.GroupStub;
import com.rent.GroupTabHost;
import com.rent.R;

public class MainActivity extends Activity {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		boolean bool = requestWindowFeature(1);
		setContentView(R.layout.main);
		initTab();
		//InnerHandler localInnerHandler = new InnerHandler();
		//new InnerThread(localInnerHandler).start();
	}
	
	private void initTab() {
		LinearLayout localLinearLayout = (LinearLayout)findViewById(R.id.container);
	    GroupStub[] arrayOfGroupStub = new GroupStub[4];
	    String str1 = getString(R.string.search_view_tab);
	    GroupStub localGroupStub1 = new GroupStub(str1, R.drawable.search_tab_icon_on, R.drawable.search_tab_icon_off, SearchActivity.class);
	    arrayOfGroupStub[0] = localGroupStub1;
	    String str2 = getString(2131361795);
	    GroupStub localGroupStub2 = new GroupStub(str2, R.drawable.history_tab_icon_on, R.drawable.history_tab_icon_off, HistoryActivity.class);
	    arrayOfGroupStub[1] = localGroupStub2;
	    String str3 = getString(2131361796);
	    GroupStub localGroupStub3 = new GroupStub(str3, R.drawable.inventory_tab_icon_on, R.drawable.inventory_tab_icon_off, InventoryActivity.class);
	    arrayOfGroupStub[2] = localGroupStub3;
	    String str4 = getString(2131361797);
	    GroupStub localGroupStub4 = new GroupStub(str4, R.drawable.more_icon_on, R.drawable.more_icon_off, MoreActivity.class);
	    arrayOfGroupStub[3] = localGroupStub4;
	    GroupTabHost localGroupTabHost = (GroupTabHost)findViewById(R.id.tab_host);
	    //localGroupTabHost.setNewMessageIcon(2130837657);
	    /*GroupHelper.init(this, localLinearLayout, localGroupTabHost, arrayOfGroupStub, this);
	    localGroupTabHost.setActiveTab(0);
	    localGroupTabHost.fireTabChanged();*/
	}
}
