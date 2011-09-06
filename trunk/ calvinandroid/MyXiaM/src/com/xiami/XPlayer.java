package com.xiami;

import java.text.SimpleDateFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.xiami.util.API;
import com.xiami.util.NotificationsUtil;
import com.xiami.view.AlbumCover;
import com.xiami.view.LyricView;
import com.xiami.view.QuickAction;

public class XPlayer extends ContextWrapper
implements SlidingDrawer.OnDrawerOpenListener, SlidingDrawer.OnDrawerCloseListener {

	static final int CLOSE_PLAYER = 2;
	  static final int META_CHANGE = 3;
	  static final int MODE_CHANGE = 5;
	  static final int OPEN_PLAYER = 1;
	  static final int RADIO_CHANGE = 6;
	  static final int REFLEASH_PROGRESS = 0;
	  static final int STATE_CHANGE = 4;
	  private XiamiApp app;
	  private Context context;
	  private onPlayerCloseListener mCloseListener;
	  private SlidingDrawer mDrawer;
	  private Musicable mMusic;
	  private onPlayerOpenListener mOpenListener;
	  Handler mPlayerHandler;
	  private BroadcastReceiver playerNotifyReceiver;
	  public boolean progressNeedUpdate;
	  private SimpleDateFormat timeFormater;
	  private ViewHolder view;

	  public XPlayer(Context paramContext, View paramView1, View paramView2, Musicable paramMusicable, SlidingDrawer paramSlidingDrawer)
	  {
	    super(paramContext);
	    XPlayer1 local1 = new XPlayer1();
	    this.mPlayerHandler = local1;
	    XPlayer2 local2 = new XPlayer2();
	    this.playerNotifyReceiver = local2;
	    this.progressNeedUpdate = true;
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("mm:ss");
	    this.timeFormater = localSimpleDateFormat;
	    this.context = paramContext;
	    XiamiApp localXiamiApp = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp;
	    ViewHolder localViewHolder = new ViewHolder(paramView1, paramView2);
	    this.view = localViewHolder;
	    this.mDrawer = paramSlidingDrawer;
	    paramSlidingDrawer.setOnDrawerOpenListener(this);
	    paramSlidingDrawer.setOnDrawerCloseListener(this);
	    this.mMusic = paramMusicable;
	  }
	  
	  class XPlayer2 extends BroadcastReceiver
	  {
	    public void onReceive(Context paramContext, Intent paramIntent)
	    {
	      String str1 = paramIntent.getAction();
	      if ("com.xiami.meta_changed".equals(str1))
	      {
	        Handler localHandler1 = mPlayerHandler;
	        Message localMessage1 = mPlayerHandler.obtainMessage(3);
	        boolean bool1 = localHandler1.sendMessage(localMessage1);
	      }
	      else
	      {
	        String str2 = paramIntent.getAction();
	        if ("com.xiami.play_mode_changed".equals(str2))
	        {
	          Handler localHandler2 = mPlayerHandler;
	          Message localMessage2 = mPlayerHandler.obtainMessage(5);
	          boolean bool2 = localHandler2.sendMessage(localMessage2);
	        }
	        String str3 = paramIntent.getAction();
	        if ("com.xiami.play_state_changed".equals(str3))
	        {
	          Handler localHandler3 = mPlayerHandler;
	          Message localMessage3 = mPlayerHandler.obtainMessage(4);
	          boolean bool3 = localHandler3.sendMessage(localMessage3);
	        }
	        String str4 = paramIntent.getAction();
	        if ("com.xiami.radio_mode_changed".equals(str4)) {
	        	boolean bool4 = mPlayerHandler.sendEmptyMessage(6);
	        }
	      }
	    }
	  }
	  
	  class XPlayer1 extends Handler
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      int i = paramMessage.what;
	      if (i == 0)
	      {
	        XPlayer.ViewHolder localViewHolder = view;
	        int j = mMusic.getTimePlay();
	        int k = mMusic.getTimeTotal();
	        int m = mMusic.getLoadedProgress();
	        localViewHolder.updateProgress(j, k, m);
//	        XPlayer.access$2(this.this$0);
	        boolean bool = sendEmptyMessageDelayed(0, 1000L);
	      } else {
	        if (i == 3)
	        {
	          updateMetaInfo();
	          removeMessages(i);
	        }
	        if (i == 4)
	        {
//	          XPlayer.access$3(this.this$0);
	          removeMessages(i);
	        }
	        if (i == 5)
	        {
	          updatePlayMode(true);
	          removeMessages(i);
	        }
	        if (i == 1)
	        {
	          if (!mDrawer.isOpened())
	        	  mDrawer.open();
	          removeMessages(i);
	        }
	        if (i == 2)
	        {
	          if (mDrawer.isOpened())
	        	  mDrawer.close();
	          removeMessages(i);
	        }
	        if (i != 6){}
//	        XPlayer.access$5(this.this$0);
	      }
	    }
	  }

	  private void updatePlayerProgress()
	  {
	    ViewHolder localViewHolder = this.view;
	    int i = this.mMusic.getTimePlay();
	    int j = this.mMusic.getTimeTotal();
	    int k = this.mMusic.getLoadedProgress();
	    localViewHolder.updateProgress(i, j, k);
	  }

	  private void updatePlayerState()
	  {
	    if (this.mMusic.isPlay())
	      this.view.btnPlay.setImageResource(2130837518);
	    else{
	      this.view.btnPlay.setImageResource(2130837519);
	    }
	  }

	  private void updateRadioState()
	  {
	    if (this.mMusic.isRadio())
	      this.view.radioSwitcher.setDisplayedChild(1);
	    else{
	      this.view.radioSwitcher.setDisplayedChild(0);
	    }
	  }

	  public void checkUpdateTask()
	  {
	    if (this.mDrawer.isOpened())
	      startUpdatePlayerInfo();
	    else{
	      stopUpdatePlayerInfo();
	    }
	  }

	  public void close()
	  {
	    boolean bool = this.mPlayerHandler.sendEmptyMessage(2);
	  }

	  public boolean isOpened()
	  {
	    return this.mDrawer.isOpened();
	  }

	  public void onDrawerClosed()
	  {
	    stopUpdatePlayerInfo();
	    this.mDrawer.getHandle().setSelected(false);
	    if (this.mCloseListener != null)
	      this.mCloseListener.onPlayerClose();
	  }

	  public void onDrawerOpened()
	  {
	    startUpdatePlayerInfo();
	    updateRadioState();
	    this.mDrawer.getHandle().setSelected(true);
	    if (this.mOpenListener != null)
	      this.mOpenListener.onPlayerOpen();
	  }

	  public void open()
	  {
	    boolean bool = this.mPlayerHandler.sendEmptyMessage(1);
	  }

	  public void registerMetaReceiver()
	  {
	    IntentFilter localIntentFilter = new IntentFilter();
	    localIntentFilter.addAction("com.xiami.meta_changed");
	    localIntentFilter.addAction("com.xiami.play_mode_changed");
	    localIntentFilter.addAction("com.xiami.play_state_changed");
	    localIntentFilter.addAction("com.xiami.radio_mode_changed");
	    BroadcastReceiver localBroadcastReceiver = this.playerNotifyReceiver;
	    Intent localIntent = registerReceiver(localBroadcastReceiver, localIntentFilter);
	    updateMetaInfo();
	    updatePlayMode(false);
	  }

	  public void release()
	  {
	    int i = Log.d("test", "xplayer release");
	    stopUpdatePlayerInfo();
	    BroadcastReceiver localBroadcastReceiver = this.playerNotifyReceiver;
	    unregisterReceiver(localBroadcastReceiver);
	  }

	  public void setOnPlayerCloseListener(onPlayerCloseListener paramonPlayerCloseListener)
	  {
	    this.mCloseListener = paramonPlayerCloseListener;
	  }

	  public void setOnPlayerOpenListener(onPlayerOpenListener paramonPlayerOpenListener)
	  {
	    this.mOpenListener = paramonPlayerOpenListener;
	  }

	  public void startUpdatePlayerInfo()
	  {
	    stopUpdatePlayerInfo();
	    int i = Log.d("test", "start update player info");
	    Handler localHandler = this.mPlayerHandler;
	    Message localMessage = this.mPlayerHandler.obtainMessage(0);
	    boolean bool = localHandler.sendMessage(localMessage);
	    updatePlayerState();
	  }

	  public void stopUpdatePlayerInfo()
	  {
	    int i = Log.d("test", "stop update player info");
	    this.mPlayerHandler.removeMessages(0);
	  }

	  public void updateMetaInfo()
	  {
	    ViewHolder localViewHolder = this.view;
	    Song localSong = this.mMusic.getPlayingSong();
	    localViewHolder.loadInfo(localSong);
	  }

	  public void updatePlayMode(boolean paramBoolean)
	  {
	    ViewHolder localViewHolder = this.view;
	    int i = this.mMusic.getMode();
	    localViewHolder.updatePlayMode(i, paramBoolean);
	  }

	  class SongFavTask extends AsyncTask<Void, Void, Boolean>
	  {
	    private Song s;

	    public SongFavTask(Song arg2)
	    {
	      this.s = arg2;
	      this.s.setGrade(4);
	    }

	    protected Boolean doInBackground(Void[] paramArrayOfVoid)
	    {
	      API localAPI = XPlayer.this.app.getWebUtil();
	      int i = this.s.getSongId();
	      int j = this.s.getGrade();
	      return Boolean.valueOf(localAPI.updateGrade(i, j));
	    }

	    protected void onPostExecute(Boolean paramBoolean)
	    {
	      if (paramBoolean.booleanValue())
	      {
	        NotificationsUtil.ToastShort(XPlayer.this.view.viewObj.getContext(), "Test!");
	        Intent localIntent1 = new Intent("com.xiami.activity.MylibSongsActivity.Refresh");
	        int i = this.s.getSongId();
	        Intent localIntent2 = localIntent1.putExtra("songId", i);
	        String str1 = this.s.getSingers();
	        Intent localIntent3 = localIntent1.putExtra("singers", str1);
	        String str2 = this.s.getName();
	        Intent localIntent4 = localIntent1.putExtra("name", str2);
	        int j = this.s.getGrade();
	        Intent localIntent5 = localIntent1.putExtra("grade", j);
	        String str3 = this.s.getCover();
	        Intent localIntent6 = localIntent1.putExtra("cover", str3);
	        int k = this.s.getAlbumId();
	        Intent localIntent7 = localIntent1.putExtra("albumId", k);
	        String str4 = this.s.getAlbumName();
	        Intent localIntent8 = localIntent1.putExtra("albumName", str4);
	        String str5 = this.s.getArtistName();
	        Intent localIntent9 = localIntent1.putExtra("artistName", str5);
	        XPlayer.this.sendBroadcast(localIntent1);
//	        XPlayer.ViewHolder(view).setSelected(1); //TODO;
	      }
	    }
	  }

	  class SongUnfavTask extends AsyncTask<Void, Void, Boolean>
	  {
	    private Song s;

	    public SongUnfavTask(Song arg2)
	    {
	      if (arg2 == null)
	        cancel(false);
	      else
	      {
	        this.s = arg2;
	        this.s.setGrade(0);
	      }
	    }

	    protected Boolean doInBackground(Void[] paramArrayOfVoid)
	    {
	      API localAPI = XPlayer.this.app.getWebUtil();
	      int i = this.s.getSongId();
	      int j = this.s.getGrade();
	      return Boolean.valueOf(localAPI.updateGrade(i, j));
	    }

	    protected void onPostExecute(Boolean paramBoolean)
	    {
	      if (paramBoolean.booleanValue())
	      {
	        Intent localIntent1 = new Intent("com.xiami.activity.MylibSongsActivity.Refresh");
	        int i = this.s.getSongId();
	        Intent localIntent2 = localIntent1.putExtra("songId", i);
	        String str1 = this.s.getSingers();
	        Intent localIntent3 = localIntent1.putExtra("singers", str1);
	        String str2 = this.s.getName();
	        Intent localIntent4 = localIntent1.putExtra("name", str2);
	        int j = this.s.getGrade();
	        Intent localIntent5 = localIntent1.putExtra("grade", j);
	        String str3 = this.s.getCover();
	        Intent localIntent6 = localIntent1.putExtra("cover", str3);
	        int k = this.s.getAlbumId();
	        Intent localIntent7 = localIntent1.putExtra("albumId", k);
	        String str4 = this.s.getAlbumName();
	        Intent localIntent8 = localIntent1.putExtra("albumName", str4);
	        String str5 = this.s.getArtistName();
	        Intent localIntent9 = localIntent1.putExtra("artistName", str5);
	        XPlayer.this.sendBroadcast(localIntent1);
	      }
	    }
	  }
	  
	  class XPlayerViewHolder2 implements GestureDetector.OnGestureListener {
		  private ViewHolder viewHolder;
		  public XPlayerViewHolder2(ViewHolder viewHolder) {
			  this.viewHolder = viewHolder;
		  }
	  public boolean onDown(MotionEvent paramMotionEvent)
	  {
	    return false;
	  }

	  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
	  {
	    float f1 = paramMotionEvent2.getY();
	    float f2 = paramMotionEvent1.getY();
	    if ((f1 - f2 > 100.0F) && (Math.abs(paramFloat2) > 200.0F)) {
//	      XPlayer.ViewHolder.access$4(this.this$1).close();
	    	return true;
	    }
	    return false;
	    	
	  }

	  public void onLongPress(MotionEvent paramMotionEvent)
	  {
	  }

	  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
	  {
	    return false;
	  }

	  public void onShowPress(MotionEvent paramMotionEvent)
	  {
	  }

	  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
	  {
	    LyricView localLyricView = viewHolder.lyric;
	    if (localLyricView.getVisibility() == 0) {
	    	localLyricView.setVisibility(4);
	    	return true;
	    } 
	    return false;
	  }
	}
	  
	  public class ViewHolder
	  {
	    public AlbumCover albumCover;
	    private ImageView btnFav;
	    private ImageView btnLrc;
	    private ImageView btnMode;
	    private ImageView btnMore;
	    public ImageView btnNext;
	    public ImageView btnPlay;
	    public ImageView btnPrev;
	    private ImageView btnRadio;
	    private ImageView btnUnfav;
	    private TextView drawerTitle;
	    private int id;
	    public LyricView lyric;
	    private GestureDetector mGestureDetector;
	    private int perPlay;
	    private int prevPercent;
	    private int prevTotal;
	    private ViewSwitcher radioSwitcher;
	    public SeekBar seekBar;
	    private String shareContent;
	    public TextView txtDuration;
	    public TextView txtPlay;
	    public View viewObj;

	    public ViewHolder(View paramView1, View arg3)
	    {
	      this.viewObj = paramView1;
	      TextView localTextView1 = (TextView)this.viewObj.findViewById(2131165355);
	      this.txtPlay = localTextView1;
	      TextView localTextView2 = (TextView)this.viewObj.findViewById(2131165356);
	      this.txtDuration = localTextView2;
	      TextView localTextView3 = (TextView)this.viewObj.findViewById(2131165232);
	      this.drawerTitle = localTextView3;
	      this.drawerTitle.setSelected(true);
	      LyricView localLyricView1 = (LyricView)this.viewObj.findViewById(2131165346);
	      this.lyric = localLyricView1;
	      LyricView localLyricView2 = this.lyric;
	      localLyricView2.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    lyric.setVisibility(4);
	    	  }
		});
	      AlbumCover localAlbumCover1 = (AlbumCover)this.viewObj.findViewById(2131165197);
	      this.albumCover = localAlbumCover1;
	      XPlayerViewHolder2 local2 = new XPlayerViewHolder2(this);
	      GestureDetector localGestureDetector = new GestureDetector(local2);
	      this.mGestureDetector = localGestureDetector;
	      this.albumCover.setLongClickable(true);
	      AlbumCover localAlbumCover2 = this.albumCover;
	      localAlbumCover2.setOnTouchListener(new View.OnTouchListener(){
	    	  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
	    	  {
	    	    return viewObj.onTouchEvent(paramMotionEvent);
	    	  }
	      });
	      SeekBar localSeekBar = (SeekBar)this.viewObj.findViewById(2131165357);
	      this.seekBar = localSeekBar;
	      ImageView localImageView1 = (ImageView)this.viewObj.findViewById(2131165353);
	      this.btnLrc = localImageView1;
	      ImageView localImageView2 = this.btnLrc;
	      localImageView2.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
//	    	    NotificationsUtil.ToastShort(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "Test");
	    	    LyricView localLyricView = lyric;
	    	    if (localLyricView.getVisibility() == 0) {
		    	      localLyricView.setVisibility(4);
	    	    }
	    	  }
		});
	      ImageView localImageView3 = (ImageView)this.viewObj.findViewById(2131165351);
	      this.btnFav = localImageView3;
	      ImageView localImageView4 = this.btnFav;
	      localImageView4.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
//	    	    MobclickAgent.onEvent(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "song_fav");
	    	    Song localSong = mMusic.getPlayingSong();
	    	    if (localSong.getGrade() > 3) {
//	    	      NotificationsUtil.ToastShort(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "Test");
	    	    }
	    	    else
	    	    {
	    	      XPlayer.SongFavTask localSongFavTask = new XPlayer.SongFavTask(localSong);
	    	      Void[] arrayOfVoid = new Void[0];
	    	      AsyncTask localAsyncTask = localSongFavTask.execute(arrayOfVoid);
	    	    }
	    	  }
		});
	      ImageView localImageView5 = (ImageView)this.viewObj.findViewById(2131165352);
	      this.btnUnfav = localImageView5;
	      ImageView localImageView6 = this.btnUnfav;
	      localImageView6.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
//	    	    MobclickAgent.onEvent(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "song_unfav");
	    	    Song localSong = mMusic.getPlayingSong();
	    	    XPlayer.SongUnfavTask localSongUnfavTask = new XPlayer.SongUnfavTask(localSong);
	    	    Void[] arrayOfVoid = new Void[0];
	    	    AsyncTask localAsyncTask = localSongUnfavTask.execute(arrayOfVoid);
	    	    mMusic.playNext();
//	    	    XPlayer.access$2(XPlayer.ViewHolder.access$4(this.this$1));
	    	  }
		});
	      ImageView localImageView7 = (ImageView)this.viewObj.findViewById(2131165344);
	      this.btnPlay = localImageView7;
	      ImageView localImageView8 = (ImageView)this.viewObj.findViewById(2131165343);
	      this.btnPrev = localImageView8;
	      ImageView localImageView9 = (ImageView)this.viewObj.findViewById(2131165345);
	      this.btnNext = localImageView9;
	      ImageView localImageView10 = (ImageView)this.viewObj.findViewById(2131165349);
	      this.btnMode = localImageView10;
	      ImageView localImageView11 = (ImageView)this.viewObj.findViewById(2131165354);
	      this.btnMore = localImageView11;
	      ImageView localImageView12 = (ImageView)this.viewObj.findViewById(2131165350);
	      this.btnRadio = localImageView12;
	      ViewSwitcher localViewSwitcher = (ViewSwitcher)this.viewObj.findViewById(2131165348);
	      this.radioSwitcher = localViewSwitcher;
	      ((View)this.btnPlay.getParent()).setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    mMusic.playPause();
//	    	    XPlayer.access$2(XPlayer.ViewHolder.access$4(this.this$1));
	    	  }
		});
	      ((View)this.btnPrev.getParent()).setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    mMusic.playPrev();
//	    	    XPlayer.access$2(XPlayer.ViewHolder.access$4(this.this$1));
	    	  }
		});
	      ((View)this.btnNext.getParent()).setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    		  mMusic.playNext();
//	    	    XPlayer.access$2(XPlayer.ViewHolder.access$4(this.this$1));
	    	  }
		});
	      ImageView localImageView13 = this.btnMode;
	      localImageView13.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    mMusic.toggleMode();
	    	  }
		});
	      ImageView localImageView14 = this.btnRadio;
	      localImageView14.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*Intent localIntent = new Intent(XPlayer.this, RadioPickrActivity.class);
	    	    startActivity(localIntent);*/
	    	  }
		});
	      ImageView localImageView15 = this.btnMore;
	      QuickAction localQuickAction = new QuickAction(localImageView15);
	      localQuickAction.setAction(2131165358, new XPlayerViewHolder12(localQuickAction));
	      localQuickAction.setAction(2131165359, new XPlayerViewHolder13(localQuickAction));
	      localQuickAction.setAction(2131165360, new XPlayerViewHolder14(localQuickAction));
	      localQuickAction.setAction(2131165361, new XPlayerViewHolder15(localQuickAction, this));
	      ImageView localImageView16 = this.btnMore;
	      localImageView16.setOnClickListener(new XPlayerViewHolder16(localQuickAction));
	    }
	   
	    class XPlayerViewHolder16 implements View.OnClickListener
	  {
	    	private QuickAction localQuickAction;
	    	public XPlayerViewHolder16(QuickAction localQuickAction) {
	    		this.localQuickAction = localQuickAction;
	    	}
	    public void onClick(View paramView)
	    {
	    	localQuickAction.show();
	    }
	  }
	    
	    class XPlayerViewHolder14 implements View.OnClickListener
	  {
	    	private QuickAction localQuickAction;
	    	public XPlayerViewHolder14(QuickAction localQuickAction) {
	    		localQuickAction = localQuickAction;
	    	}
	    public void onClick(View paramView)
	    {
	      Song localSong = mMusic.getPlayingSong();
	      if (localSong == null)
	      {
//	        NotificationsUtil.ToastShort(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "褰撳墠娌℃湁姝屾洸姝ｅ湪鎾斁!");
	    	  localQuickAction.dismiss();
	      }
	      else
	      {
	        mMusic.download(localSong);
//	        MobclickAgent.onEvent(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "download", "player");
	        localQuickAction.dismiss();
	      }
	    }
	  }
	    
	    class XPlayerViewHolder15 implements View.OnClickListener
	  {
	    	private QuickAction localQuickAction;
	    	private ViewHolder viewHolder;
	    	public XPlayerViewHolder15(QuickAction localQuickAction, ViewHolder viewHolder) {
	    		localQuickAction = localQuickAction;
	    		this.viewHolder = viewHolder;
	    	}
	    public void onClick(View paramView)
	    {
	      /*Intent localIntent1 = new Intent(XPlayer.this, PopShareActivity.class);
	      String str = viewHolder.shareContent;
	      Intent localIntent2 = localIntent1.putExtra("sharecontent", str);
	      startActivity(localIntent1);
	      localQuickAction.dismiss();*/
	    }
	  }
	    
	    
	    class XPlayerViewHolder13 implements View.OnClickListener {
	    	private QuickAction localQuickAction;
	    	public XPlayerViewHolder13(QuickAction localQuickAction) {
	    		localQuickAction = localQuickAction;
	    	}
	    public void onClick(View paramView)
	    {
	      if (!app.checkNetworkWithNotify())
	    	  localQuickAction.dismiss();
	      else
	      {
	        Song localSong = mMusic.getPlayingSong();
	        if (localSong == null)
	        {
//	          NotificationsUtil.ToastShort(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "褰撳墠娌℃湁姝屾洸姝ｅ湪鎾斁!");
	        localQuickAction.dismiss();
	        } else {
	        /*Intent localIntent1 = new Intent(XPlayer.this, ArtistDetailActivity.class);
	        Artist localArtist = new Artist();
	        int i = localSong.getArtistId();
	        localArtist.setId(i);
	        String str = localSong.getArtistName();
	        localArtist.setName(str);
	        Intent localIntent2 = localIntent1.putExtra("data", localArtist);
	        startActivity(localIntent1);
	        localQuickAction.dismiss();*/
	      }
	    }
	  }
	    }
	    
	    class XPlayerViewHolder12 implements View.OnClickListener {
	    	private QuickAction localQuickAction;
	    	public XPlayerViewHolder12(QuickAction localQuickAction) {
	    		localQuickAction = localQuickAction;
	    	}
	    	  public void onClick(View paramView)
	    	  {
	    	    if (!app.checkNetworkWithNotify()) {
	    	    	localQuickAction.dismiss();
	    	    }
	    	    else
	    	    {
	    	      Song localSong = mMusic.getPlayingSong();
	    	      if (localSong == null)
	    	      {
//	    	        NotificationsUtil.ToastShort(XPlayer.access$7(XPlayer.ViewHolder.access$4(this.this$1)), "Test");
	    	    	  localQuickAction.dismiss();
	    	      } else {
		    	     /* Intent localIntent1 = new Intent(XPlayer.this, AlbumDetailActivity.class);
		    	      Album localAlbum = new Album();
		    	      int i = localSong.getAlbumId();
		    	      localAlbum.setId(i);
		    	      String str = localSong.getAlbumName();
		    	      localAlbum.setTitle(str);
		    	      Intent localIntent2 = localIntent1.putExtra("data", localAlbum);
		    	      startActivity(localIntent1);
		    	      localQuickAction.dismiss();*/
	    	      }
	    	    }
	    	  }
		}

	    public void loadInfo(Song paramSong)
	    {
	      /*if (paramSong == null);
	      int j;
	      int k;
	      do
	      {
	        while (true)
	        {
	          return;
	          if (!paramSong.isLocal())
	            break;
	          TextView localTextView1 = this.drawerTitle;
	          Object[] arrayOfObject1 = new Object[2];
	          String str1 = paramSong.getName();
	          arrayOfObject1[0] = str1;
	          String str2 = paramSong.getArtistName();
	          arrayOfObject1[1] = str2;
	          String str3 = String.format("Test: %s - %s", arrayOfObject1);
	          localTextView1.setText(str3);
	          AlbumCover localAlbumCover1 = this.albumCover;
	          int i = paramSong.getAlbumId();
	          String str4 = paramSong.getCover();
	          localAlbumCover1.load(i, str4);
	          Object[] arrayOfObject2 = new Object[2];
	          String str5 = paramSong.getName();
	          arrayOfObject2[0] = str5;
	          String str6 = paramSong.getArtistName();
	          arrayOfObject2[1] = str6;
	          String str7 = String.format("Test %s - %s", arrayOfObject2);
	          this.shareContent = str7;
	          this.btnFav.setEnabled(false);
	          this.btnUnfav.setEnabled(false);
	          this.btnMore.setEnabled(false);
	          this.btnLrc.setEnabled(false);
	        }
	        j = paramSong.getSongId();
	        k = this.id;
	      }
	      while (j == k);
	      int m = paramSong.getSongId();
	      this.id = m;
	      if (XPlayer.this.mMusic.isRadio())
	      {
	        TextView localTextView2 = this.drawerTitle;
	        Object[] arrayOfObject3 = new Object[3];
	        String str8 = XPlayer.this.mMusic.getRadioName();
	        arrayOfObject3[0] = str8;
	        String str9 = paramSong.getName();
	        arrayOfObject3[1] = str9;
	        String str10 = paramSong.getSingers();
	        arrayOfObject3[2] = str10;
	        String str11 = String.format("Test/ %s - %s", arrayOfObject3);
	        localTextView2.setText(str11);
	        StringBuilder localStringBuilder1 = new StringBuilder("Test");
	        String str12 = paramSong.getSingers();
	        StringBuilder localStringBuilder2 = localStringBuilder1.append(str12).append("Test");
	        String str13 = paramSong.getName();
	        StringBuilder localStringBuilder3 = localStringBuilder2.append(str13).append("http://www.xiami.com/song/");
	        String str14 = String.valueOf(this.id);
	        this.shareContent = str14;
	        AlbumCover localAlbumCover2 = this.albumCover;
	        int i1 = paramSong.getAlbumId();
	        String str15 = paramSong.getCover();
	        localAlbumCover2.load(i1, str15);
	        if (paramSong.getGrade() <= 3)
	          break label524;
	        this.btnFav.setSelected(true);
	      }
	      while (true)
	      {
	        XPlayer.this.view.lyric.getLyric(paramSong);
	        this.btnFav.setEnabled(true);
	        this.btnUnfav.setEnabled(true);
	        this.btnMore.setEnabled(true);
	        this.btnLrc.setEnabled(true);
	        break;
	        TextView localTextView3 = this.drawerTitle;
	        Object[] arrayOfObject4 = new Object[2];
	        String str16 = paramSong.getName();
	        arrayOfObject4[0] = str16;
	        String str17 = paramSong.getSingers();
	        arrayOfObject4[1] = str17;
	        String str18 = String.format("TEst:%s - %s", arrayOfObject4);
	        localTextView3.setText(str18);
	        break label293;
	        label524: this.btnFav.setSelected(false);
	      }*/
	    }

	    public void updatePlayMode(int paramInt, boolean paramBoolean)
	    {
	      if (paramInt == 1)
	      {
	        if (paramBoolean)
	          NotificationsUtil.ToastShort(XPlayer.this.context, "Test");
	        this.btnMode.setImageLevel(2);
	      }
	      else
	      {
	        if (paramInt == 0)
	        {
	          if (paramBoolean)
	            NotificationsUtil.ToastShort(XPlayer.this.context, "Test");
	          this.btnMode.setImageLevel(0);
	        } else {
		        if (paramBoolean)
		          NotificationsUtil.ToastShort(XPlayer.this.context, "Test");
		        this.btnMode.setImageLevel(1);
	        }
	      }
	    }

	    public void updateProgress(int paramInt1, int paramInt2, int paramInt3)
	    {
	      if (this.prevTotal != paramInt2)
	      {
	        this.prevTotal = paramInt2;
	        this.seekBar.setMax(paramInt2);
	        SimpleDateFormat localSimpleDateFormat1 = XPlayer.this.timeFormater;
	        Integer localInteger1 = Integer.valueOf(paramInt2);
	        String str1 = localSimpleDateFormat1.format(localInteger1);
	        this.txtDuration.setText(str1);
	      }
	      if (this.prevPercent != paramInt3)
	      {
	        this.prevPercent = paramInt3;
	        if (paramInt3 <= 99){}
	        SeekBar localSeekBar = this.seekBar;
	        int i = XPlayer.this.view.seekBar.getMax();
	        localSeekBar.setSecondaryProgress(i);
	        
	        if (this.perPlay != paramInt1)
	        {
	          this.perPlay = paramInt1;
	          SimpleDateFormat localSimpleDateFormat2 = XPlayer.this.timeFormater;
	          Integer localInteger2 = Integer.valueOf(paramInt1);
	          String str2 = localSimpleDateFormat2.format(localInteger2);
	          this.txtPlay.setText(str2);
	          this.seekBar.setProgress(paramInt1);
	          LyricView localLyricView = this.lyric;
	          long l = paramInt1;
	          localLyricView.update(l);
	        }
	      }
	      else
	      {
	        int j = paramInt2 * paramInt3 / 100;
	        this.seekBar.setSecondaryProgress(j);
	      }
	    }
	  }

	  public abstract interface onPlayerCloseListener
	  {
	    public abstract void onPlayerClose();
	  }

	  public abstract interface onPlayerOpenListener
	  {
	    public abstract void onPlayerOpen();
	  }
}
