package com.rent.activitiy;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
		InnerHandler localInnerHandler = new InnerHandler();
		new InnerThread(localInnerHandler).start();
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
		if(0 == paramInt) {
			localActivity = GroupHelper.getActivityById(this, 0);
//			((SearchActivity)localActivity).setMainContext();
		}
	}

	private void createShortcut() {
		try {
			int i = getSharedPreferences("create_short_cut", 0).getInt("sign",
					0);
			if ((!isInstallShortcut()) && (i == 0)) {
				AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(
						this);
				AlertDialog.Builder localBuilder2 = localBuilder1
						.setMessage(2131361894);
				AlertDialog.Builder localBuilder3 = localBuilder1
						.setTitle(2131361829);
				AlertDialog.Builder localBuilder4 = localBuilder1
						.setPositiveButton(2131361826,
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface paramDialogInterface,
											int paramInt) {
										paramDialogInterface.dismiss();
										/*
										 * if
										 * (!MainActivity.access$300(this.this$0
										 * ))
										 * MainActivity.access$400(this.this$0);
										 */
									}
								});
				AlertDialog.Builder localBuilder5 = localBuilder1
						.setNegativeButton(2131361827,
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface paramDialogInterface,
											int paramInt) {
										paramDialogInterface.dismiss();
										// MainActivity.access$500(this.this$0);
									}
								});
				localBuilder1.create().show();
				SharedPreferences.Editor localEditor1 = getSharedPreferences(
						"create_short_cut", 1).edit();
				SharedPreferences.Editor localEditor2 = localEditor1.putInt(
						"sign", 1);
				boolean bool = localEditor1.commit();
			} else {
				// getHelp();
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private boolean isInstallShortcut() {
		Uri localUri = Uri.parse("content://" + "com.android.launcher.settings"
				+ "/favorites?notify=true");
		ContentResolver localContentResolver = getContentResolver();
		String[] arrayOfString1 = new String[1];
		arrayOfString1[0] = "title";
		String[] arrayOfString2 = new String[1];
		String str = getString(2131361793);
		arrayOfString2[0] = str;
		Cursor localCursor = localContentResolver.query(localUri,
				arrayOfString1, "title=?", arrayOfString2, null);
		if ((localCursor != null) && (localCursor.getCount() > 0)) {
			localCursor.close();
			return true;
		}
		return false;
	}
	
	class InnerThread extends Thread
	  {
	    MainActivity.InnerHandler mHandler;

	    public InnerThread(MainActivity.InnerHandler arg2)
	    {
	      this.mHandler = arg2;
	    }

	    public void run()
	    {
	      
	    }
	  }
	
	class InnerHandler extends Handler {

		public void handleMessage(Message paramMessage) {
			super.handleMessage(paramMessage);
			MainActivity.this.createShortcut();
			/*
			 * if (FeedbackActivity.hasFeedbackNotify()) {
			 * MainActivity.this.promptLookFeedback();
			 * MainActivity.this.feedbackTip();
			 * MoreActivity.checkNotify(MainActivity.this); } else {
			 * FeedbackActivity.getFeedBackPrompt(MainActivity.this); }
			 */
		}
	}

	private void clearCache() {
		/*
		 * GroupImageManager.clearCache(); DetailDBManager localDetailDBManager
		 * = new DetailDBManager(this); List localList =
		 * localDetailDBManager.getCacheList(); localDetailDBManager.closeDb();
		 * DetailImageManager.clearCache(localList);
		 * GroupImageManager.clearCache();
		 */
	}
}
