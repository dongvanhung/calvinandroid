package com.xiami.activity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.xiami.XPlayer;
import com.xiami.XiamiApp;
import com.xiami.activity.handler.ViewHandler;
import com.xiami.lib.data.Member;
import com.xiami.localdb.DbHelper;
import com.xiami.util.FmSetting;
import com.xiami.util.NotificationsUtil;
import com.xiami.view.CustomDialog;

public class MainActivity extends MusicableActivity {

	public static final String ACTION_FINISH = "com.xiami.activity.finish";
	  public static final String ACTION_SWITCH_OFFLINE = "com.xiami.activity.switch.offline";
	  public static final String Action_AutoClose = "com.xiami.autoclose";
	  public static final String Action_AutoClose_1Min = "com.xiami.autoclose1min";
	  public static final String Action_Req_Error = "com.xiami.req.error";
	  private XiamiApp app;
	  private View btnMylib;
	  private View btnOffline;
	  private View btnlisten;
	  private FmSetting fmSetting;
	  private boolean isUpdateChecked = false;
	  private CustomDialog mAboutDialog;
	  private List<List<VIEWS>> mAllHistrory;
	  private CustomDialog mAutoCloseDialog;
	  private Spinner mAutocloseSpinner;
	  private BroadcastReceiver mBroadcastListener;
	  private VIEWS mCurrView;
	  private ViewFlipper mFlipper;
	  private EnumMap<VIEWS, Integer> mHandlerViewIndex;
	  private EnumMap<VIEWS, ViewHandler> mHandlers;
	  private List<VIEWS> mHistroy;
	  private ViewSwitcher mNavFlipper;
	  private CustomDialog mNetworkDialog;
	  private CustomDialog mSignoutAlert;
	  private View navSelected;
	  View.OnClickListener nav_click;
	  private XPlayer xplayer;

	  public MainActivity()
	  {
	    ArrayList localArrayList = new ArrayList(4);
	    this.mAllHistrory = localArrayList;
	    MainActivity1 local1 = new MainActivity1();
	    this.mBroadcastListener = local1;
	    this.mCurrView = null;
	    EnumMap localEnumMap1 = new EnumMap(VIEWS.class);
	    this.mHandlers = localEnumMap1;
	    EnumMap localEnumMap2 = new EnumMap(VIEWS.class);
	    this.mHandlerViewIndex = localEnumMap2;
	    this.nav_click = new View.OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    int i = paramView.getId();
	    	    List localList = (List)mAllHistrory.get(0);
	    	    MainActivity.VIEWS localVIEWS1 = MainActivity.VIEWS.INDEX;
	    	    switch (i)
	    	    {
	    	    default:
	    	      if (localList.size() <= 0)
	    	        break;
	    	      /*MainActivity.VIEWS localVIEWS2 = (MainActivity.VIEWS)localList.get(0);
	    	      MainActivity.access$0(MainActivity.this, localVIEWS2, null, 0);*/
	    	    case 2131165329:
	    	    	localList = (List)mAllHistrory.get(2);
		    	      localVIEWS1 = MainActivity.VIEWS.OFFLINE_SONGS;
//		    	      MainActivity.access$0(this.this$0, localVIEWS1, null, 1);
	    	    case 2131165328:
	    	    	localList = (List)mAllHistrory.get(1);
		    	      localVIEWS1 = MainActivity.VIEWS.MYLIB;
//		    	      MainActivity.access$0(this.this$0, localVIEWS1, null, 1);
	    	    }
	    	  }
		};
	    this.xplayer = null;
	  }
	  
	  class MainActivity1 extends BroadcastReceiver
	  {
	    public void onReceive(Context paramContext, Intent paramIntent)
	    {
	      String str = paramIntent.getAction();
	      if (str.equals("com.xiami.autoclose1min")) {
	        Toast localToast3 = Toast.makeText(paramContext, "Test", 1);
	      }
	      else
	      {
	        if (str.equals("com.xiami.autoclose"))
	        {
	          Toast localToast2 = Toast.makeText(paramContext, "Test", 1);
	          stopService();
	          finish();
	        }
	        if (str.equals("com.xiami.req.error"))
	        {
	        	CustomDialog customDialog = new CustomDialog(MainActivity.this);
	          CustomDialog.Builder localBuilder = customDialog.new Builder();
	          localBuilder.setMessage("Test").setTitle("Test1");
	          localBuilder.setPositiveButton("Tes1", new DialogInterface.OnClickListener(){
	        	  public void onClick(DialogInterface paramDialogInterface, int paramInt)
	        	  {
	        	    paramDialogInterface.dismiss();
	        	  }
	          }).create().show();
	        }
	        if (str.equals("com.xiami.activity.finish"))
	        {
	          finish();
	        }
	        if (str.equals("com.xiami.activity.switch.offline"))
	        {
	          MainActivity.VIEWS localVIEWS = MainActivity.VIEWS.OFFLINE_SONGS;
//	          MainActivity.access$0(localMainActivity2, localVIEWS, null, 1);
	          
	        }
	        if (str.equals("com.xiami.meta_changed")) {
	        	int i = Log.d("1","no meta changed");
//	        	MainActivity.access$1(this.this$0);
	        }
	      }
	    }
	  }

	  private boolean checkNetworkConnected(VIEWS paramVIEWS)
	  {
	    /*int[] arrayOfInt = paramVIEWS.
	    int i = paramVIEWS.ordinal();
	    switch (arrayOfInt[i])
	    {
	    case 3:
	    case 5:
	    default:
	    case 1:
	    case 2:
	    case 4:
	    case 6:
	    case 7:
	    case 8:
	    case 9:
	    }
	    boolean bool;
	    for (int j = 1; ; bool = this.app.checkNetworkWithNotify())
	      return j;*/
		  return true;
	  }

	  private void installShortCut()
	  {
	    Intent localIntent1 = new Intent();
	    String str1 = getPackageName();
	    Intent localIntent2 = localIntent1.setClassName(str1, ".activity.StartActivity");
	    Intent localIntent3 = localIntent1.addCategory("android.intent.category.LAUNCHER");
	    Intent localIntent4 = new Intent();
	    Intent localIntent5 = localIntent4.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
	    String str2 = getString(2131099649);
	    Intent localIntent6 = localIntent4.putExtra("android.intent.extra.shortcut.NAME", str2);
	    Bitmap localBitmap = ((BitmapDrawable)getResources().getDrawable(2130837772)).getBitmap();
	    Intent localIntent7 = localIntent4.putExtra("android.intent.extra.shortcut.ICON", localBitmap);
	    Intent localIntent8 = localIntent4.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
	    sendBroadcast(localIntent4);
	  }

	  private void loadPlayer()
	  {
	      View localView1 = ((ViewStub)findViewById(2131165206)).inflate();
	      TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 100.0F, 0.0F);
	      AccelerateDecelerateInterpolator localAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
	      localTranslateAnimation.setInterpolator(localAccelerateDecelerateInterpolator);
	      localTranslateAnimation.setDuration(500L);
	      localTranslateAnimation.setFillAfter(true);
	      localView1.startAnimation(localTranslateAnimation);
	      View localView2 = findViewById(2131165233);
	      SlidingDrawer localSlidingDrawer = (SlidingDrawer)findViewById(2131165228);
	      MainActivity localMainActivity1 = this;
	      MainActivity localMainActivity2 = this;
	      XPlayer localXPlayer1 = new XPlayer(localMainActivity1, localView1, localView2, localMainActivity2, localSlidingDrawer);
	      this.xplayer = localXPlayer1;
	      this.xplayer.registerMetaReceiver();
	      XPlayer localXPlayer2 = this.xplayer;
	      MainActivity3 local3 = new MainActivity3();
	      localXPlayer2.setOnPlayerCloseListener(local3);
	      XPlayer localXPlayer3 = this.xplayer;
	      localXPlayer3.setOnPlayerOpenListener(new XPlayer.onPlayerOpenListener()
	    		  {
	    	  public void onPlayerOpen()
	    	  {
//	    	    MainActivity.access$4(this.this$0).setDisplayedChild(1);
	    	  }
	    	});
	      XPlayer localXPlayer4 = this.xplayer;
	      setPlayer(localXPlayer4);
	  }
	  
	  class MainActivity3 implements XPlayer.onPlayerCloseListener
	{
	  public void onPlayerClose()
	  {
//	    xplayer.setDisplayedChild(0);
	  }
	}

	  private void setupNavBar()
	  {
	    ViewSwitcher localViewSwitcher = (ViewSwitcher)findViewById(2131165229);
	    this.mNavFlipper = localViewSwitcher;
	    View localView1 = findViewById(2131165327);
	    this.btnlisten = localView1;
	    View localView2 = this.btnlisten;
	    View.OnClickListener localOnClickListener1 = this.nav_click;
	    localView2.setOnClickListener(localOnClickListener1);
	    View localView3 = findViewById(2131165328);
	    this.btnMylib = localView3;
	    View localView4 = this.btnMylib;
	    View.OnClickListener localOnClickListener2 = this.nav_click;
	    localView4.setOnClickListener(localOnClickListener2);
	    View localView5 = findViewById(2131165329);
	    this.btnOffline = localView5;
	    View localView6 = this.btnOffline;
	    View.OnClickListener localOnClickListener3 = this.nav_click;
	    localView6.setOnClickListener(localOnClickListener3);
	    View localView7 = this.btnlisten;
	    this.navSelected = localView7;
	    this.navSelected.setSelected(true);
	  }

	  private void setupViewHandlers()
	  {
	    EnumMap localEnumMap1 = this.mHandlerViewIndex;
	    VIEWS localVIEWS1 = VIEWS.INDEX;
	    Integer localInteger1 = Integer.valueOf(0);
	    Object localObject1 = localEnumMap1.put(localVIEWS1, localInteger1);
	    EnumMap localEnumMap2 = this.mHandlerViewIndex;
	    VIEWS localVIEWS2 = VIEWS.MUSCI_BILLS;
	    Integer localInteger2 = Integer.valueOf(1);
	    Object localObject2 = localEnumMap2.put(localVIEWS2, localInteger2);
	    EnumMap localEnumMap3 = this.mHandlerViewIndex;
	    VIEWS localVIEWS3 = VIEWS.OFFLINE_SONGS;
	    Integer localInteger3 = Integer.valueOf(2);
	    Object localObject3 = localEnumMap3.put(localVIEWS3, localInteger3);
	    EnumMap localEnumMap4 = this.mHandlerViewIndex;
	    VIEWS localVIEWS4 = VIEWS.AM_COLLECT;
	    Integer localInteger4 = Integer.valueOf(3);
	    Object localObject4 = localEnumMap4.put(localVIEWS4, localInteger4);
	    EnumMap localEnumMap5 = this.mHandlerViewIndex;
	    VIEWS localVIEWS5 = VIEWS.MYLIB;
	    Integer localInteger5 = Integer.valueOf(4);
	    Object localObject5 = localEnumMap5.put(localVIEWS5, localInteger5);
	    EnumMap localEnumMap6 = this.mHandlerViewIndex;
	    VIEWS localVIEWS6 = VIEWS.MYLIB_SONGS;
	    Integer localInteger6 = Integer.valueOf(5);
	    Object localObject6 = localEnumMap6.put(localVIEWS6, localInteger6);
	    EnumMap localEnumMap7 = this.mHandlerViewIndex;
	    VIEWS localVIEWS7 = VIEWS.MYLIB_ALBUMS;
	    Integer localInteger7 = Integer.valueOf(6);
	    Object localObject7 = localEnumMap7.put(localVIEWS7, localInteger7);
	    EnumMap localEnumMap8 = this.mHandlerViewIndex;
	    VIEWS localVIEWS8 = VIEWS.MYLIB_ARTISTS;
	    Integer localInteger8 = Integer.valueOf(7);
	    Object localObject8 = localEnumMap8.put(localVIEWS8, localInteger8);
	    EnumMap localEnumMap9 = this.mHandlerViewIndex;
	    VIEWS localVIEWS9 = VIEWS.MYLIB_COLLECTS;
	    Integer localInteger9 = Integer.valueOf(8);
	    Object localObject9 = localEnumMap9.put(localVIEWS9, localInteger9);
	    EnumMap localEnumMap10 = this.mHandlerViewIndex;
	    VIEWS localVIEWS10 = VIEWS.HOT_ALBUMS;
	    Integer localInteger10 = Integer.valueOf(9);
	    Object localObject10 = localEnumMap10.put(localVIEWS10, localInteger10);
	    /*MainActivity.7 local7 = new MainActivity.7(this);
	    MainActivity.8 local8 = new MainActivity.8(this);
	    List localList1 = this.mAllHistrory;
	    ArrayList localArrayList1 = new ArrayList();
	    boolean bool1 = localList1.add(localArrayList1);
	    List localList2 = this.mAllHistrory;
	    ArrayList localArrayList2 = new ArrayList();
	    boolean bool2 = localList2.add(localArrayList2);
	    List localList3 = this.mAllHistrory;
	    ArrayList localArrayList3 = new ArrayList();
	    boolean bool3 = localList3.add(localArrayList3);
	    List localList4 = (List)this.mAllHistrory.get(0);
	    this.mHistroy = localList4;
	    EnumMap localEnumMap11 = this.mHandlers;
	    VIEWS localVIEWS11 = VIEWS.INDEX;
	    View localView1 = this.mFlipper.getChildAt(0);
	    ListenXiamiHandler localListenXiamiHandler = new ListenXiamiHandler(localView1, this, local7, this);
	    Object localObject11 = localEnumMap11.put(localVIEWS11, localListenXiamiHandler);
	    EnumMap localEnumMap12 = this.mHandlers;
	    VIEWS localVIEWS12 = VIEWS.MUSCI_BILLS;
	    View localView2 = this.mFlipper.getChildAt(1);
	    BillHandler localBillHandler = new BillHandler(localView2, this, local7);
	    Object localObject12 = localEnumMap12.put(localVIEWS12, localBillHandler);
	    EnumMap localEnumMap13 = this.mHandlers;
	    VIEWS localVIEWS13 = VIEWS.OFFLINE_SONGS;
	    View localView3 = this.mFlipper.getChildAt(2);
	    MainActivity localMainActivity1 = this;
	    MainActivity localMainActivity2 = this;
	    OfflineHandler localOfflineHandler = new OfflineHandler(localView3, localMainActivity1, local7, local8, localMainActivity2);
	    Object localObject13 = localEnumMap13.put(localVIEWS13, localOfflineHandler);
	    EnumMap localEnumMap14 = this.mHandlers;
	    VIEWS localVIEWS14 = VIEWS.AM_COLLECT;
	    View localView4 = this.mFlipper.getChildAt(3);
	    MainActivity localMainActivity3 = this;
	    MainActivity localMainActivity4 = this;
	    CollectsHandler localCollectsHandler = new CollectsHandler(localView4, localMainActivity3, local7, local8, localMainActivity4);
	    Object localObject14 = localEnumMap14.put(localVIEWS14, localCollectsHandler);
	    EnumMap localEnumMap15 = this.mHandlers;
	    VIEWS localVIEWS15 = VIEWS.MYLIB;
	    View localView5 = this.mFlipper.getChildAt(4);
	    MylibHandler localMylibHandler = new MylibHandler(localView5, this, local7, this);
	    Object localObject15 = localEnumMap15.put(localVIEWS15, localMylibHandler);
	    EnumMap localEnumMap16 = this.mHandlers;
	    VIEWS localVIEWS16 = VIEWS.MYLIB_SONGS;
	    View localView6 = this.mFlipper.getChildAt(5);
	    MainActivity localMainActivity5 = this;
	    MainActivity localMainActivity6 = this;
	    MylibSongsHandler localMylibSongsHandler = new MylibSongsHandler(localView6, localMainActivity5, local7, local8, localMainActivity6);
	    Object localObject16 = localEnumMap16.put(localVIEWS16, localMylibSongsHandler);
	    EnumMap localEnumMap17 = this.mHandlers;
	    VIEWS localVIEWS17 = VIEWS.MYLIB_ALBUMS;
	    View localView7 = this.mFlipper.getChildAt(6);
	    MainActivity localMainActivity7 = this;
	    MainActivity localMainActivity8 = this;
	    MylibAlbumsHandler localMylibAlbumsHandler = new MylibAlbumsHandler(localView7, localMainActivity7, local7, local8, localMainActivity8);
	    Object localObject17 = localEnumMap17.put(localVIEWS17, localMylibAlbumsHandler);
	    EnumMap localEnumMap18 = this.mHandlers;
	    VIEWS localVIEWS18 = VIEWS.MYLIB_ARTISTS;
	    View localView8 = this.mFlipper.getChildAt(7);
	    MainActivity localMainActivity9 = this;
	    MainActivity localMainActivity10 = this;
	    MylibArtistsHandler localMylibArtistsHandler = new MylibArtistsHandler(localView8, localMainActivity9, local7, local8, localMainActivity10);
	    Object localObject18 = localEnumMap18.put(localVIEWS18, localMylibArtistsHandler);
	    EnumMap localEnumMap19 = this.mHandlers;
	    VIEWS localVIEWS19 = VIEWS.MYLIB_COLLECTS;
	    View localView9 = this.mFlipper.getChildAt(8);
	    MainActivity localMainActivity11 = this;
	    MainActivity localMainActivity12 = this;
	    MylibCollectsHandler localMylibCollectsHandler = new MylibCollectsHandler(localView9, localMainActivity11, local7, local8, localMainActivity12);
	    Object localObject19 = localEnumMap19.put(localVIEWS19, localMylibCollectsHandler);
	    EnumMap localEnumMap20 = this.mHandlers;
	    VIEWS localVIEWS20 = VIEWS.HOT_ALBUMS;
	    View localView10 = this.mFlipper.getChildAt(9);
	    MainActivity localMainActivity13 = this;
	    MainActivity localMainActivity14 = this;
	    HotAlbumsHandler localHotAlbumsHandler = new HotAlbumsHandler(localView10, localMainActivity13, local7, local8, localMainActivity14);
	    Object localObject20 = localEnumMap20.put(localVIEWS20, localHotAlbumsHandler);
	    VIEWS localVIEWS21 = VIEWS.INDEX;
	    switchView(localVIEWS21, null, true);*/
	  }

	  private void showAboutDialog()
	  {
		  CustomDialog customDialog = new CustomDialog(MainActivity.this);
	    CustomDialog.Builder localBuilder1 = null;
	    View localView = null;
	    if (this.mAboutDialog == null)
	    {
	      localBuilder1 = customDialog.new Builder();
	      localView = getLayoutInflater().inflate(2130903040, null);
	      ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
	      localView.setLayoutParams(localLayoutParams);
	    }
	    try
	    {
	      StringBuilder localStringBuilder = new StringBuilder("Test");
	      PackageManager localPackageManager = getPackageManager();
	      String str1 = getPackageName();
	      String str2 = localPackageManager.getPackageInfo(str1, 0).versionName;
	      String str3 = str2;
	      ((TextView)localView.findViewById(2131165190)).setText(str3);
	      CustomDialog.Builder localBuilder2 = localBuilder1.setTitle("Test");
	     /* MainActivity.9 local9 = new MainActivity.9(this);
	      CustomDialog.Builder localBuilder3 = localBuilder2.setPositiveButton("Tes1", local9).setContentView(localView);*/
	      CustomDialog localCustomDialog = localBuilder1.create();
	      this.mAboutDialog = localCustomDialog;
	      this.mAboutDialog.show();
	      return;
	    }
	    catch (PackageManager.NameNotFoundException localNameNotFoundException)
	    {
	      while (true)
	        localNameNotFoundException.printStackTrace();
	    }
	  }

	  private void showAutocloseAlert()
	  {
	    if (this.mAutoCloseDialog == null)
	    {
	    	CustomDialog customDialog = new CustomDialog(MainActivity.this);
	      CustomDialog.Builder localBuilder1 = customDialog.new Builder();
	      View localView = getLayoutInflater().inflate(2130903046, null);
	      ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
	      localView.setLayoutParams(localLayoutParams);
	      Spinner localSpinner1 = (Spinner)localView.findViewById(2131165217);
	      this.mAutocloseSpinner = localSpinner1;
	      this.mAutocloseSpinner.setVisibility(0);
	      String[] arrayOfString = new String[6];
	      arrayOfString[0] = "Test";
	      arrayOfString[1] = "10Test";
	      arrayOfString[2] = "20Test";
	      arrayOfString[3] = "30Test";
	      arrayOfString[4] = "60Test";
	      arrayOfString[5] = "90Test";
	      int[] arrayOfInt = new int[6];
	      arrayOfInt[1] = 10;
	      arrayOfInt[2] = 20;
	      arrayOfInt[3] = 30;
	      arrayOfInt[4] = 60;
	      arrayOfInt[5] = 90;
	      ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 2130903121, arrayOfString);
	      localArrayAdapter.setDropDownViewResource(17367049);
	      this.mAutocloseSpinner.setAdapter(localArrayAdapter);
	      Spinner localSpinner2 = this.mAutocloseSpinner;
	      /*MainActivity.10 local10 = new MainActivity.10(this, arrayOfInt);
	      localSpinner2.setOnItemSelectedListener(local10);
	      CustomDialog.Builder localBuilder2 = localBuilder1.setTitle("Test").setContentView(localView);
	      MainActivity.11 local11 = new MainActivity.11(this);
	      CustomDialog.Builder localBuilder3 = localBuilder2.setNegativeButton("Tste", local11);*/
	      CustomDialog localCustomDialog = localBuilder1.create();
	      this.mAutoCloseDialog = localCustomDialog;
	    }
	    switch (PreferenceManager.getDefaultSharedPreferences(this).getInt("minleft", 0))
	    {
	    default:
	    	this.mAutoCloseDialog.show();
	    case 0:
	    	this.mAutocloseSpinner.setSelection(0);
	    case 10:
	    	this.mAutocloseSpinner.setSelection(1);
	    case 20:
	    	this.mAutocloseSpinner.setSelection(2);
	    case 30:
	    	this.mAutocloseSpinner.setSelection(3);
	    case 60:
	    	this.mAutocloseSpinner.setSelection(4);
	    case 90:
	    	this.mAutocloseSpinner.setSelection(5);
	    }
	  }

	  private void showNetworkDialog()
	  {
	    /*CustomDialog.Builder localBuilder1;
	    View localView;
	    CheckBox localCheckBox1;
	    CheckBox localCheckBox2;
	    if (this.mNetworkDialog == null)
	    {
	    	CustomDialog customDialog = new CustomDialog(MainActivity.this);
	      localBuilder1 = customDialog.new Builder();
	      localView = getLayoutInflater().inflate(2130903100, null);
	      ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
	      localView.setLayoutParams(localLayoutParams);
	      localCheckBox1 = (CheckBox)localView.findViewById(2131165334);
	      localCheckBox2 = (CheckBox)localView.findViewById(2131165335);
	      if (this.fmSetting.isForceOffline() != 1)
	        break label184;
	      localCheckBox1.setChecked(true);
	      if (this.fmSetting.getShowMessageWhenNoWifi() != 1)
	        break label194;
	      localCheckBox2.setChecked(true);
	    }
	    while (true)
	    {
	      MainActivity.12 local12 = new MainActivity.12(this);
	      localCheckBox2.setOnCheckedChangeListener(local12);
	      localCheckBox1.setOnCheckedChangeListener(local12);
	      CustomDialog.Builder localBuilder2 = localBuilder1.setTitle("Test").setContentView(localView);
	      MainActivity.13 local13 = new MainActivity.13(this);
	      CustomDialog.Builder localBuilder3 = localBuilder2.setNegativeButton("Test", local13);
	      CustomDialog localCustomDialog = localBuilder1.create();
	      this.mNetworkDialog = localCustomDialog;
	      this.mNetworkDialog.show();
	      return;
	      label184: localCheckBox1.setChecked(0);
	      break;
	      label194: localCheckBox2.setChecked(0);
	    }*/
	  }

	  private void showSignoutAlert()
	  {
	    /*if (this.mSignoutAlert == null)
	    {
	      CustomDialog.Builder localBuilder1 = new CustomDialog.Builder(this);
	      CustomDialog.Builder localBuilder2 = localBuilder1.setMessage("纭畾娉ㄩ攢涔堬紵").setTitle("娉ㄩ攢");
	      MainActivity.14 local14 = new MainActivity.14(this);
	      CustomDialog.Builder localBuilder3 = localBuilder2.setPositiveButton("纭畾", local14);
	      MainActivity.15 local15 = new MainActivity.15(this);
	      CustomDialog.Builder localBuilder4 = localBuilder3.setNegativeButton("鍙栨秷", local15);
	      CustomDialog localCustomDialog = localBuilder1.create();
	      this.mSignoutAlert = localCustomDialog;
	    }
	    this.mSignoutAlert.show();*/
	  }

	  private void switchBack()
	  {
	    if ((this.xplayer != null) && (this.xplayer.isOpened()))
	      this.xplayer.close();
	    else 
	    {
	      if (this.mHistroy.size() > 1)
	      {
	        Object localObject = this.mHistroy.remove(0);
	        VIEWS localVIEWS = (VIEWS)this.mHistroy.remove(0);
	        switchView(localVIEWS, null, true, false);
	      }
	      finish();
	    }
	  }

	  private void switchView(VIEWS paramVIEWS, Object paramObject, boolean paramBoolean)
	  {
	    switchView(paramVIEWS, paramObject, paramBoolean, true);
	  }

	  private void switchView(VIEWS paramVIEWS, Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
	  {
	    /*if (!checkNetworkConnected(paramVIEWS));
	    int i;
	    View localView;
	    ViewHandler localViewHandler;
	    VIEWS localVIEWS1;
	    do
	    {
	      return;
	      if (paramBoolean2)
	      {
	        String str = paramVIEWS.name();
//	        MobclickAgent.onEvent(this, "switch_view", str);
	      }
	      i = 0;
	      localView = this.btnlisten;
	      localViewHandler = (ViewHandler)this.mHandlers.get(paramVIEWS);
	      localVIEWS1 = this.mCurrView;
	    }
	    while (paramVIEWS.equals(localVIEWS1));
	    int[] arrayOfInt = $SWITCH_TABLE$com$xiami$activity$MainActivity$VIEWS();
	    int j = paramVIEWS.ordinal();
	    switch (arrayOfInt[j])
	    {
	    default:
	    case 1:
	    case 2:
	    case 3:
	    case 4:
	    case 10:
	    case 5:
	    case 6:
	    case 7:
	    case 8:
	    case 9:
	    }
	    while (true)
	    {
	      if (this.mCurrView != null)
	      {
	        EnumMap localEnumMap = this.mHandlers;
	        VIEWS localVIEWS2 = this.mCurrView;
	        ((ViewHandler)localEnumMap.get(localVIEWS2)).unload();
	      }
	      this.mCurrView = paramVIEWS;
	      if ((this.navSelected != null) && (this.navSelected.getId() != i))
	        this.navSelected.setSelected(0);
	      localView.setSelected(1);
	      this.navSelected = localView;
	      localViewHandler.load(paramObject);
	      ViewFlipper localViewFlipper = this.mFlipper;
	      int k = ((Integer)this.mHandlerViewIndex.get(paramVIEWS)).intValue();
	      localViewFlipper.setDisplayedChild(k);
	      if (!paramBoolean1)
	        break;
	      this.mHistroy.add(0, paramVIEWS);
	      break;
	      i = 2131165327;
	      List localList1 = (List)this.mAllHistrory.get(0);
	      this.mHistroy = localList1;
	      continue;
	      i = 2131165329;
	      localView = this.btnOffline;
	      List localList2 = (List)this.mAllHistrory.get(2);
	      this.mHistroy = localList2;
	      continue;
	      i = 2131165328;
	      localView = this.btnMylib;
	      List localList3 = (List)this.mAllHistrory.get(1);
	      this.mHistroy = localList3;
	    }*/
	  }

	  public boolean onContextItemSelected(MenuItem paramMenuItem)
	  {
	    EnumMap localEnumMap = this.mHandlers;
	    VIEWS localVIEWS = this.mCurrView;
	    return ((ViewHandler)localEnumMap.get(localVIEWS)).onContextItemSelected(paramMenuItem);
	  }

	  public void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.update(this);
	    boolean bool = requestWindowFeature(1);
	    XiamiApp localXiamiApp = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp;
	    FmSetting localFmSetting = this.app.getFmSetting();
	    this.fmSetting = localFmSetting;
	    StringBuilder localStringBuilder = new StringBuilder("app is null?");
	    int i;
	    if (this.app == null)
	      i = 1;
	    else
	    {
	      setContentView(2130903096);
	      setupNavBar();
	      ViewFlipper localViewFlipper = (ViewFlipper)findViewById(2131165326);
	      this.mFlipper = localViewFlipper;
	      IntentFilter localIntentFilter = new IntentFilter();
	      localIntentFilter.addAction("com.xiami.req.error");
	      localIntentFilter.addAction("com.xiami.autoclose");
	      localIntentFilter.addAction("com.xiami.autoclose1min");
	      localIntentFilter.addAction("com.xiami.activity.finish");
	      localIntentFilter.addAction("com.xiami.activity.switch.offline");
	      localIntentFilter.addAction("com.xiami.meta_changed");
	      BroadcastReceiver localBroadcastReceiver = this.mBroadcastListener;
	      Intent localIntent = registerReceiver(localBroadcastReceiver, localIntentFilter);
	      try
	      {
	        /*MainActivity.5 local5 = new MainActivity.5(this);
	        ExchangeDataService.requestDataAsyn(this, local5);*/
	        setupViewHandlers();
	        int j = 0;
	      }
	      catch (Exception localException)
	      {
	          localException.printStackTrace();
	      }
	    }
	  }

	  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
	  {
	    EnumMap localEnumMap = this.mHandlers;
	    VIEWS localVIEWS = this.mCurrView;
	    ((ViewHandler)localEnumMap.get(localVIEWS)).onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
	  }

	  public boolean onCreateOptionsMenu(Menu paramMenu)
	  {
	    MenuItem localMenuItem1 = paramMenu.add(0, 1, 1, "Test").setIcon(17301578);
	    MenuItem localMenuItem2 = paramMenu.add(0, 2, 2, "Test1").setIcon(17301570);
	    MenuItem localMenuItem3 = paramMenu.add(0, 3, 9, "Test2").setIcon(17301562);
	    MenuItem localMenuItem4 = paramMenu.add(0, 4, 4, "Test3").setIcon(17301560);
	    MenuItem localMenuItem5 = paramMenu.add(0, 5, 11, "Test4").setIcon(17301576);
	    MenuItem localMenuItem6 = paramMenu.add(0, 6, 6, "Test5").setIcon(17301568);
	    MenuItem localMenuItem7 = paramMenu.add(0, 7, 7, "Test6").setIcon(17301591);
	    MenuItem localMenuItem8 = paramMenu.add(0, 8, 8, "test7").setIcon(17301586);
	   /* if (ExchangeDataService.hasData())
	      MenuItem localMenuItem9 = paramMenu.add(0, 9, 3, "Test8").setIcon(17301561);*/
	    MenuItem localMenuItem10 = paramMenu.add(0, 10, 10, "Test9").setIcon(17301578);
	    MenuItem localMenuItem11 = paramMenu.add(0, 11, 5, "Test10").setIcon(17301555);
	    return true;
	  }

	  protected void onDestroy()
	  {
	    VIEWS[] arrayOfVIEWS = VIEWS.values();
	    int i = arrayOfVIEWS.length;
	    int j = 0;
	    {
	      if (j >= i)
	      {
	        this.mHandlers = null;
	        if (this.mBroadcastListener != null)
	        {
	          BroadcastReceiver localBroadcastReceiver = this.mBroadcastListener;
	          unregisterReceiver(localBroadcastReceiver);
	          this.mBroadcastListener = null;
	        }
	        DbHelper.closeInstance();
	        super.onDestroy();
	        return;
	      }
	      VIEWS localVIEWS = arrayOfVIEWS[j];
	      ((ViewHandler)this.mHandlers.get(localVIEWS)).onDestroy();
	    }
	  }

	  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	  {
	    if (paramInt == 4)
	    {
	      EnumMap localEnumMap = this.mHandlers;
	      VIEWS localVIEWS = this.mCurrView;
	      ((ViewHandler)localEnumMap.get(localVIEWS)).onBackKey();
	    }
	    return true;
	  }

	  protected void onNewIntent(Intent paramIntent)
	  {
	    String str = paramIntent.getAction();
	    if ("open_player".equals(str))
	    {
	      loadPlayer();
	      this.xplayer.open();
	      setIntent(paramIntent);
	      super.onNewIntent(paramIntent);
	    }
	    else
	    {
	      if ("switch_offline".equals(str))
	      {
	        VIEWS localVIEWS = VIEWS.OFFLINE_SONGS;
	        switchView(localVIEWS, null, true, false);
	        NotificationsUtil.cancalNotify(this, 3);
	      }
	      if ("resume_play".equals(str))
	      {
	        resumePlay();
	      }
	      if ("check_update".equals(str))
	      {
	        NotificationsUtil.cancalNotify(this, 2);
	      }
	      if (!this.isUpdateChecked) {
	    	  this.isUpdateChecked = true;
	      }
	    }
	  }

	  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
	  {
		  return true;
	    /*int i;
	    switch (paramMenuItem.getItemId())
	    {
	    default:
	    	showAutocloseAlert();
	    case 1:
	    	showNetworkDialog();
	    case 2:
	    	finish();
	    case 3:
	    	 stopService();
	    case 4:
	    	 showSignoutAlert();
	    case 5:
	    	showAboutDialog();
	    case 6:
	    case 7:
	    case 8:
	    case 9:
	    case 10:
	    case 11:
	    }
	    while (true)
	    {
	      return i;
	      
	      i = 1;
	      continue;
	      
	      i = 1;
	      continue;
	      
	      i = 1;
	      continue;
	     
	      finish();
	      i = 1;
	      continue;
	     
	      i = 1;
	      continue;
	      
	      i = 1;
	      continue;
	      continue;
	      MobclickAgent.onEvent(this, "share_app");
	      Intent localIntent1 = new Intent(this, PopShareActivity.class);
	      String str = getString(2131099723);
	      Intent localIntent2 = localIntent1.putExtra("sharecontent", str);
	      startActivity(localIntent1);
	      i = 1;
	      continue;
	      MobclickAgent.onEvent(this, "exchange_show", "main menu");
	      try
	      {
	        new ExchangeViewManager().addView(this, 7);
	        label207: i = 1;
	        continue;
	        MobclickAgent.setUpdateListener(new MainActivity.6(this));
	        MobclickAgent.update(this);
	        i = 1;
	        continue;
	        MobclickAgent.onEvent(this, "install_shortcut");
	        installShortCut();
	        i = 1;
	      }
	      catch (Exception localException)
	      {
	        break label207;
	      }
	    }*/
	  }

	  protected void onPause()
	  {
	    super.onPause();
	  }

	  protected void onResume()
	  {
	    if (this.app.getMember().getNick_name() == null)
	    {
	      Member localMember = DbHelper.getInstance(this).getLoginMember();
	      this.app.setMember(localMember);
	    }
	    NotificationsUtil.cancalNotify(this, 2);
	    super.onResume();
//	    MobclickAgent.onResume(this);
	  }

	  protected void onServiceConnected()
	  {
	    if ((isPlay()) || (getPlayingSong() != null))
	    {
	      int i = Log.d("11", "onserviceconnected");
	      loadPlayer();
	    }
	  }

	  protected void onStop()
	  {
	    super.onStop();
	  }

	  public void startSearch(String paramString, boolean paramBoolean1, Bundle paramBundle, boolean paramBoolean2)
	  {
	    super.startSearch(paramString, paramBoolean1, paramBundle, paramBoolean2);
	  }

	  public static enum VIEWS
	  {
	      AM_COLLECT,
	      HOT_ALBUMS,
	      INDEX,
	      MUSCI_BILLS,
	      MYLIB,
	      MYLIB_ALBUMS,
	      MYLIB_ARTISTS,
	      MYLIB_COLLECTS,
	      MYLIB_SONGS,
	      OFFLINE_SONGS;
	  }
}
