package com.xiami.activity.handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiami.XiamiApp;
import com.xiami.activity.MainActivity;

public abstract class ViewHandler {

	protected static final String TAG = "ViewHandler";
	  protected XiamiApp app;
	  protected Context mContext;
//	  protected ExchangeViewManager mEvm;
	  protected ContentSwitcher mSwitcher;
	  private TextView mTitle;
	  protected ViewGroup mView;
	  protected boolean needToload;

	  public ViewHandler(View paramView, Context paramContext, ContentSwitcher paramContentSwitcher)
	  {
/*	    ExchangeViewManager localExchangeViewManager = new ExchangeViewManager();
	    this.mEvm = localExchangeViewManager;
*/	    this.needToload = true;
	    ViewGroup localViewGroup = (ViewGroup)paramView;
	    this.mView = localViewGroup;
	    this.mContext = paramContext;
	    XiamiApp localXiamiApp = (XiamiApp)paramContext.getApplicationContext();
	    this.app = localXiamiApp;
	    this.mSwitcher = paramContentSwitcher;
	    TextView localTextView = (TextView)findViewById(2131165195);
	    this.mTitle = localTextView;
	    View localView = findViewById(2131165194);
	    if (localView != null)
	    {
	      localView.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    mSwitcher.switchback();
	    	  }
		});
	    }
	  }

	  protected void back()
	  {
	    /*if (this.mEvm.isOpen())
	      this.mEvm.toggle();*/
	      this.mSwitcher.switchback();
	  }

	  protected boolean bindService(Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
	  {
	    return this.mContext.bindService(paramIntent, paramServiceConnection, paramInt);
	  }

	  protected View findViewById(int paramInt)
	  {
	    return this.mView.findViewById(paramInt);
	  }

	  protected LayoutInflater getLayoutInflater()
	  {
	    return (LayoutInflater)this.mContext.getSystemService("layout_inflater");
	  }

	  protected Resources getResources()
	  {
	    return this.mContext.getResources();
	  }

	  protected String getString(int paramInt)
	  {
	    return this.mContext.getString(paramInt);
	  }

	  protected Object getSystemService(String paramString)
	  {
	    return this.mContext.getSystemService(paramString);
	  }

	  public void load(Object paramObject)
	  {
	    if (this.needToload)
	      loadData();
	  }

	  protected void loadData()
	  {
	    this.needToload = false;
	  }

	  public void onBackKey()
	  {
	    back();
	  }

	  public boolean onContextItemSelected(MenuItem paramMenuItem)
	  {
	    return false;
	  }

	  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
	  {
	  }

	  public void onDestroy()
	  {
	  }

	  protected void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
	  {
	    Intent localIntent = this.mContext.registerReceiver(paramBroadcastReceiver, paramIntentFilter);
	  }

	  protected void sendBroadcast(Intent paramIntent)
	  {
	    this.mContext.sendBroadcast(paramIntent);
	  }

	  public void setTitle(String paramString)
	  {
	    this.mTitle.setText(paramString);
	  }

	  public void startActivity(Intent paramIntent)
	  {
	    this.mContext.startActivity(paramIntent);
	  }

	  protected void unbindService(ServiceConnection paramServiceConnection)
	  {
	    this.mContext.unbindService(paramServiceConnection);
	  }

	  public void unload()
	  {
	  }

	  protected void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
	  {
	    this.mContext.unregisterReceiver(paramBroadcastReceiver);
	  }

	  public abstract interface ContentSwitcher
	  {
	    public abstract void showSearchDialog();

	    public abstract void switchView(MainActivity.VIEWS paramVIEWS, Object paramObject);

	    public abstract void switchback();
	  }

	  public abstract interface ContextMenuRegister
	  {
	    public abstract void register(View paramView);
	  }
}
