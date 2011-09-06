package com.xiami.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.xiami.XiamiApp;
import com.xiami.file.FileUtil;
import com.xiami.util.API;
import com.xiami.util.FmSetting;

public class MusicPlayService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*public static final int ASYNC_OPEN_COMPLETE = 4;
	  public static final int Action_Continue = 6;
	  public static final int Action_Pause = 2;
	  public static final int Action_Play = 1;
	  public static final int Action_PlayNext = 4;
	  public static final int Action_PlayPrev = 5;
	  public static final int Action_Stop = 3;
	  public static final int Action_StopService = 99;
	  public static final String DOWNLOAD_LIST_REFRESH = "com.xiami.download_list_refresh";
	  public static final String META_CHANGED = "com.xiami.meta_changed";
	  public static final int PLAYMODE_RND = 1;
	  public static final int PLAYMODE_SEQ = 0;
	  public static final int PLAYMODE_SINGLE = 2;
	  public static final String PLAY_MODE_CHANGED = "com.xiami.play_mode_changed";
	  public static final String PLAY_STATE_CHANGED = "com.xiami.play_state_changed";
	  public static final String RADIO_MODE_CHANGEED = "com.xiami.radio_mode_changed";
	  public static final String REPEAT_MODE_CHANGED = "com.xiami.repeat_mode_changed";
	  public static final int SERVER_DIED = 3;
	  private String WIFILOCK_KEY;
	  private XiamiApp app;
	  private ConnectionChangeReceiver conReceiver;
	  int currVolume = 5;
	  private int currentTrack = 0;
	  private Handler handler = null;
	  private BroadcastReceiver headsetListener;
	  private String lastConnectTypeName;
	  public boolean lastPlayStatus;
	  private final IMusicPlayService.Stub mBinder;
	  private int mDownloadProgress;
	  private FileUtil mFileUtil;
	  private FmSetting mFmSetting;
	  private int mNetworkConfirm;
	  private List<Song> mOfflineSongs;
	  private DownloadFilesTask mOfflineTask;
	  private OfflineUtil mOfflineUtil;
	  private PhoneStateListener mPhoneListener;
	  public MultiPlayer mPlayer;
	  private int mStartId;
	  WifiManager.WifiLock mWifiLock;
	  private List<Integer> rndlist;
	  private List<Song> songs;
	  TelephonyManager tm;
	  boolean volumeUped;

	  public MusicPlayService()
	  {
	    MusicPlayService.1 local1 = new MusicPlayService.1(this);
	    this.headsetListener = local1;
	    MusicPlayService.2 local2 = new MusicPlayService.2(this);
	    this.mBinder = local2;
	    this.mDownloadProgress = 0;
	    this.mNetworkConfirm = 0;
	    DownloadFilesTask localDownloadFilesTask = new DownloadFilesTask(null);
	    this.mOfflineTask = localDownloadFilesTask;
	    MusicPlayService.3 local3 = new MusicPlayService.3(this);
	    this.mPhoneListener = local3;
	    this.mWifiLock = null;
	    ArrayList localArrayList1 = new ArrayList();
	    this.rndlist = localArrayList1;
	    ArrayList localArrayList2 = new ArrayList();
	    this.songs = localArrayList2;
	    this.volumeUped = 0;
	    this.WIFILOCK_KEY = "xiamiplay";
	  }

	  private void acquireWifiNetwork()
	  {
	    WifiManager localWifiManager = (WifiManager)getSystemService("wifi");
	    if (this.mWifiLock == null)
	    {
	      String str = this.WIFILOCK_KEY;
	      WifiManager.WifiLock localWifiLock = localWifiManager.createWifiLock(str);
	      this.mWifiLock = localWifiLock;
	      this.mWifiLock.setReferenceCounted(1);
	    }
	    this.mWifiLock.acquire();
	  }

	  private void hideNotify()
	  {
	    NotificationsUtil.cancalNotify(this, 1);
	  }

	  private void releaseWifiNetwork()
	  {
	    if (this.mWifiLock != null)
	      this.mWifiLock.release();
	  }

	  private void showNotify()
	  {
	    Song localSong = this.mPlayer.playingSong;
	    if (localSong == null);
	    while (true)
	    {
	      return;
	      String str1 = localSong.getName();
	      String str2 = localSong.getArtistName();
	      NotificationsUtil.notifyOnGoing(this, str1, str2, 1, "open_player");
	    }
	  }

	  public int getNextTrack()
	  {
	    int i = this.currentTrack;
	    switch (this.mPlayer.mode)
	    {
	    default:
	    case 0:
	      while (true)
	      {
	        return i;
	        i += 1;
	        int j = this.songs.size() - 1;
	        if (i <= j)
	          continue;
	        i = 0;
	      }
	    case 1:
	    }
	    int k = 0;
	    int m = 0;
	    while (true)
	    {
	      int n = this.rndlist.size() - 1;
	      if (m >= n)
	      {
	        int i1 = this.rndlist.size() - 1;
	        if (k >= i1)
	          break;
	        i = Integer.valueOf(((Integer)this.rndlist.get(k)).toString()).intValue();
	        break;
	      }
	      int i2 = Integer.valueOf(((Integer)this.rndlist.get(m)).toString()).intValue();
	      if (i == i2)
	      {
	        int i3 = m + 1;
	        int i4 = this.rndlist.size() - 1;
	        if (i3 <= i4);
	      }
	      m += 1;
	    }
	  }

	  public int getPosition()
	  {
	    if (this.mPlayer == null);
	    for (int i = 0; ; i = this.mPlayer.position())
	      return i;
	  }

	  public int getPrevTrack()
	  {
	    int i = this.currentTrack;
	    switch (this.mPlayer.mode)
	    {
	    default:
	    case 0:
	      while (true)
	      {
	        return i;
	        i += -1;
	        if (i >= 0)
	          continue;
	        i = this.songs.size() - 1;
	      }
	    case 1:
	    }
	    int j = 0;
	    int k = 0;
	    while (true)
	    {
	      int m = this.rndlist.size() - 1;
	      if (k >= m)
	      {
	        int n = this.rndlist.size() - 1;
	        if (j >= n)
	          break;
	        i = Integer.valueOf(((Integer)this.rndlist.get(j)).toString()).intValue();
	        break;
	      }
	      int i1 = Integer.valueOf(((Integer)this.rndlist.get(k)).toString()).intValue();
	      if ((i == i1) && (k - 1 < 0))
	        int i2 = this.rndlist.size() - 1;
	      k += 1;
	    }
	  }

	  public Song getSongByTrack(int paramInt)
	  {
	    if (this.songs.size() > 0);
	    for (Song localSong = (Song)this.songs.get(paramInt); ; localSong = null)
	      return localSong;
	  }

	  public List<Song> getSongs()
	  {
	    return this.songs;
	  }

	  public IBinder onBind(Intent paramIntent)
	  {
	    return this.mBinder;
	  }

	  public void onCreate()
	  {
	    super.onCreate();
	    XiamiApp localXiamiApp = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp;
	    MultiPlayer localMultiPlayer = new MultiPlayer(this);
	    this.mPlayer = localMultiPlayer;
	    TelephonyManager localTelephonyManager1 = (TelephonyManager)getSystemService("phone");
	    this.tm = localTelephonyManager1;
	    TelephonyManager localTelephonyManager2 = this.tm;
	    PhoneStateListener localPhoneStateListener = this.mPhoneListener;
	    localTelephonyManager2.listen(localPhoneStateListener, 32);
	    if (this.handler == null)
	    {
	      Handler localHandler1 = new Handler();
	      this.handler = localHandler1;
	      Handler localHandler2 = this.handler;
	      MusicPlayService.4 local4 = new MusicPlayService.4(this);
	      boolean bool = localHandler2.postDelayed(local4, 100L);
	    }
	    acquireWifiNetwork();
	    FileUtil localFileUtil = this.app.getFileUtil();
	    this.mFileUtil = localFileUtil;
	    FmSetting localFmSetting = this.app.getFmSetting();
	    this.mFmSetting = localFmSetting;
	    OfflineUtil localOfflineUtil = new OfflineUtil(this);
	    this.mOfflineUtil = localOfflineUtil;
	    List localList = this.mOfflineUtil.getOfflineSongs();
	    this.mOfflineSongs = localList;
	    startDownloadSong();
	    LoadDefaultPlaylistTask localLoadDefaultPlaylistTask = new LoadDefaultPlaylistTask(null);
	    Void[] arrayOfVoid = new Void[0];
	    AsyncTask localAsyncTask = localLoadDefaultPlaylistTask.execute(arrayOfVoid);
	    IntentFilter localIntentFilter1 = new IntentFilter();
	    localIntentFilter1.addAction("android.intent.action.ANY_DATA_STATE");
	    localIntentFilter1.addAction("android.intent.action.SERVICE_STATE");
	    localIntentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
	    localIntentFilter1.addAction("android.net.wifi.STATE_CHANGE");
	    ConnectionChangeReceiver localConnectionChangeReceiver1 = new ConnectionChangeReceiver(null);
	    this.conReceiver = localConnectionChangeReceiver1;
	    ConnectionChangeReceiver localConnectionChangeReceiver2 = this.conReceiver;
	    Intent localIntent1 = registerReceiver(localConnectionChangeReceiver2, localIntentFilter1);
	    IntentFilter localIntentFilter2 = new IntentFilter();
	    localIntentFilter2.addAction("android.intent.action.HEADSET_PLUG");
	    BroadcastReceiver localBroadcastReceiver = this.headsetListener;
	    IntentFilter localIntentFilter3 = new IntentFilter(localIntentFilter2);
	    Intent localIntent2 = registerReceiver(localBroadcastReceiver, localIntentFilter3);
	    int i = Log.d("MusicService::onCreate");
	  }

	  public void onDestroy()
	  {
	    super.onDestroy();
	    if (this.mPlayer != null)
	    {
	      this.mPlayer.release();
	      this.mPlayer = null;
	    }
	    ConnectionChangeReceiver localConnectionChangeReceiver = this.conReceiver;
	    unregisterReceiver(localConnectionChangeReceiver);
	    BroadcastReceiver localBroadcastReceiver = this.headsetListener;
	    unregisterReceiver(localBroadcastReceiver);
	    TelephonyManager localTelephonyManager = this.tm;
	    PhoneStateListener localPhoneStateListener = this.mPhoneListener;
	    localTelephonyManager.listen(localPhoneStateListener, 0);
	    hideNotify();
	    releaseWifiNetwork();
	    int i = Log.d("MusicService::onDestroy");
	    System.gc();
	    super.onDestroy();
	  }

	  public void onStart(Intent paramIntent, int paramInt)
	  {
	    this.mStartId = paramInt;
	    super.onStart(paramIntent, paramInt);
	  }

	  public boolean onUnbind(Intent paramIntent)
	  {
	    int i = Log.d("unbind");
	    if (!this.mPlayer.isPlaying())
	    {
	      int j = Log.d("stop service");
	      setForeground(0);
	      int k = this.mStartId;
	      stopSelf(k);
	    }
	    return super.onUnbind(paramIntent);
	  }

	  public void pause()
	  {
	    int i = Log.d("service call meidaplayer pause");
	    if ((this.mPlayer != null) && (this.mPlayer.isPlaying()))
	    {
	      this.mPlayer.pause();
	      this.mPlayer.isPlay = 0;
	      Intent localIntent = new Intent("com.xiami.play_state_changed");
	      sendBroadcast(localIntent);
	      hideNotify();
	    }
	  }

	  public void play(boolean paramBoolean)
	  {
	    NotificationsUtil.cancalNotify(this, 4);
	    if ((paramBoolean) && (this.mPlayer.isInitialized()))
	    {
	      this.mPlayer.start();
	      showNotify();
	    }
	    Song localSong;
	    String str3;
	    while (true)
	    {
	      return;
	      if ((this.songs != null) && (this.songs.size() >= 1))
	      {
	        this.mPlayer.isPlay = 1;
	        Intent localIntent1 = new Intent("com.xiami.play_state_changed");
	        sendBroadcast(localIntent1);
	        showNotify();
	        MobclickAgent.onEvent(this, "track play");
	        localSong = this.mPlayer.playingSong;
	        if (localSong.isLocal())
	          break label291;
	        String str1 = localSong.getFileUrl().replace(" ", "%20");
	        int i = localSong.getSongId();
	        String str2 = new Integer(i).toString();
	        str3 = this.app.getFileUtil().getMp3(str1, str2);
	        if (localSong.getDownloadStatus() == 1)
	        {
	          if (!new File(str3).exists())
	          {
	            NotificationsUtil.ToastLong(this, "Test");
	            continue;
	          }
	        }
	        else if (!this.app.checkNetworkWithNotify())
	        {
	          this.mPlayer.isPlay = 0;
	          Intent localIntent2 = new Intent("com.xiami.play_state_changed");
	          sendBroadcast(localIntent2);
	          this.mPlayer.stop();
	          NotificationsUtil.ToastLong(this, "Test");
	          continue;
	        }
	        if (!this.app.checkNetwork())
	          break;
	        PlaylogTask localPlaylogTask = new PlaylogTask(null);
	        Song[] arrayOfSong = new Song[1];
	        arrayOfSong[0] = localSong;
	        AsyncTask localAsyncTask = localPlaylogTask.execute(arrayOfSong);
	      }
	    }
	    while (true)
	    {
	      this.mPlayer.setDataSourceAsync(str3);
	      break;
	      break;
	      label291: StringBuilder localStringBuilder1 = new StringBuilder();
	      Uri localUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(localUri).append("/");
	      String str4 = localSong.getLocalId();
	      str3 = str4;
	    }
	  }

	  public void playNext()
	  {
	    if (this.songs.size() < 1)
	      NotificationsUtil.ToastShort(this, "Test");
	    while (true)
	    {
	      return;
	      MusicPlayService.5 local5 = new MusicPlayService.5(this);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = local5.execute(arrayOfVoid);
	    }
	  }

	  public void playOrPause()
	  {
	    if (this.songs.size() < 1)
	      NotificationsUtil.ToastShort(this, "Test");
	    while (true)
	    {
	      return;
	      int i = Log.d("play or pause");
	      if (this.mPlayer.isPlay)
	      {
	        pause();
	        continue;
	      }
	      play(1);
	    }
	  }

	  public void playPrev()
	  {
	    if (this.songs.size() < 1)
	      NotificationsUtil.ToastShort(this, "Test");
	    while (true)
	    {
	      return;
	      MusicPlayService.6 local6 = new MusicPlayService.6(this);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = local6.execute(arrayOfVoid);
	    }
	  }

	  public void setRndlist()
	  {
	    this.rndlist.clear();
	    int i = 0;
	    while (true)
	    {
	      int j = this.songs.size() - 1;
	      if (i >= j)
	      {
	        Collections.shuffle(this.rndlist);
	        return;
	      }
	      List localList = this.rndlist;
	      Integer localInteger = Integer.valueOf(i);
	      boolean bool = localList.add(localInteger);
	      i += 1;
	    }
	  }

	  public void setSongs(List<Song> paramList, int paramInt, boolean paramBoolean)
	  {
	    if ((paramList == null) || (paramList.size() == 0));
	    while (true)
	    {
	      return;
	      this.songs.clear();
	      boolean bool = this.songs.addAll(paramList);
	      setRndlist();
	      this.currentTrack = paramInt;
	      MultiPlayer localMultiPlayer = this.mPlayer;
	      Song localSong = (Song)this.songs.get(paramInt);
	      localMultiPlayer.playingSong = localSong;
	      if (paramBoolean)
	        play(0);
	      Intent localIntent = new Intent("com.xiami.meta_changed");
	      sendBroadcast(localIntent);
	    }
	  }

	  public void startDownloadSong()
	  {
	    switch (this.mFmSetting.isAvaliableNetwork(this))
	    {
	    default:
	    case 1:
	    case 2:
	    case 4:
	    case 3:
	    }
	    while (true)
	    {
	      return;
	      AsyncTask.Status localStatus1 = this.mOfflineTask.getStatus();
	      AsyncTask.Status localStatus2 = AsyncTask.Status.PENDING;
	      if (localStatus1.equals(localStatus2))
	      {
	        DownloadFilesTask localDownloadFilesTask1 = this.mOfflineTask;
	        Void[] arrayOfVoid1 = new Void[0];
	        AsyncTask localAsyncTask1 = localDownloadFilesTask1.execute(arrayOfVoid1);
	        continue;
	      }
	      AsyncTask.Status localStatus3 = this.mOfflineTask.getStatus();
	      AsyncTask.Status localStatus4 = AsyncTask.Status.FINISHED;
	      if ((!localStatus3.equals(localStatus4)) && (!this.mOfflineTask.isCancelled()))
	        continue;
	      DownloadFilesTask localDownloadFilesTask2 = new DownloadFilesTask(null);
	      this.mOfflineTask = localDownloadFilesTask2;
	      DownloadFilesTask localDownloadFilesTask3 = this.mOfflineTask;
	      Void[] arrayOfVoid2 = new Void[0];
	      AsyncTask localAsyncTask2 = localDownloadFilesTask3.execute(arrayOfVoid2);
	      continue;
	      AsyncTask.Status localStatus5 = this.mOfflineTask.getStatus();
	      AsyncTask.Status localStatus6 = AsyncTask.Status.RUNNING;
	      if (localStatus5.equals(localStatus6))
	        boolean bool = this.mOfflineTask.cancel(1);
	      NotificationsUtil.ToastShort(this, "褰撳墠缃戠粶璁剧疆涓哄己鍒朵笅杞�);
	      continue;
	      NotificationsUtil.ToastShort(this, "褰撳墠娌IFI杩炴帴");
	      continue;
	      NotificationsUtil.ToastShort(this, "褰撳墠娌″彲鐢ㄧ殑缃戠粶杩炴帴");
	    }
	  }

	  class ConnectionChangeReceiver extends BroadcastReceiver
	  {
	    private ConnectionChangeReceiver()
	    {
	    }

	    public void onReceive(Context paramContext, Intent paramIntent)
	    {
	      if (MusicPlayService.this.mNetworkConfirm == 2);
	      while (true)
	      {
	        return;
	        if ((MusicPlayService.this.mPlayer.isPlaying()) && (MusicPlayService.MultiPlayer.access$3(MusicPlayService.this.mPlayer) != null) && (!MusicPlayService.MultiPlayer.access$3(MusicPlayService.this.mPlayer).isLocal()))
	        {
	          ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
	          NetworkInfo localNetworkInfo1 = localConnectivityManager.getActiveNetworkInfo();
	          NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(1);
	          NetworkInfo localNetworkInfo3 = localConnectivityManager.getNetworkInfo(0);
	          FmSetting localFmSetting = MusicPlayService.this.app.getFmSetting();
	          if ((localFmSetting.getShowMessageWhenNoWifi() != 1) || (localFmSetting.isForceOffline() == 1) || (localNetworkInfo1 == null))
	            continue;
	          StringBuilder localStringBuilder1 = new StringBuilder("network:");
	          String str1 = localNetworkInfo1.getTypeName();
	          StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(" ");
	          String str2 = paramIntent.getAction();
	          int i = Log.d(str2);
	          NetworkInfo.State localState1 = localNetworkInfo2.getState();
	          NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
	          if (localState1 != localState2)
	          {
	            NetworkInfo.State localState3 = localNetworkInfo3.getState();
	            NetworkInfo.State localState4 = NetworkInfo.State.CONNECTED;
	            if (localState3 != localState4)
	            {
	              NotificationsUtil.notifyNow(MusicPlayService.this, "澶卞幓缃戠粶閾炬帴锛屽凡鏆傚仠鎾斁銆�, "鐐瑰嚮鎭㈠鎾斁", 4, "resume_play");
	              int j = Log.d("lost wifi connected ,pause");
	              MusicPlayService.this.pause();
	              continue;
	            }
	          }
	          if (localNetworkInfo1.getType() != 1)
	          {
	            if (MusicPlayService.this.tm.getDataState() != 2)
	              continue;
	            if ((MusicPlayService.this.lastConnectTypeName == null) || (MusicPlayService.this.lastConnectTypeName.equals("WIFI")))
	            {
	              boolean bool = MusicPlayService.this.mOfflineTask.cancel(1);
	              MusicPlayService.this.lastPlayStatus = 1;
	              MusicPlayService.this.mNetworkConfirm = 1;
	              int k = Log.d("not wifi ,pause");
	              MusicPlayService.this.pause();
	              NotificationsUtil.notifyNow(MusicPlayService.this, "绯荤粺褰撳墠涓洪潪Wifi缃戠粶锛屼负閬垮厤鎰忓鐨勬祦閲忔秷鑰楋紝宸叉殏鍋滄挱鏀俱�", "鐐瑰嚮鎭㈠鎾斁", 4, "resume_play");
	            }
	          }
	          MusicPlayService localMusicPlayService = MusicPlayService.this;
	          String str3 = localNetworkInfo1.getTypeName();
	          localMusicPlayService.lastConnectTypeName = str3;
	          continue;
	        }
	      }
	    }
	  }

	  class DownloadFilesTask extends AsyncTask<Void, Integer, Void>
	  {
	    private int downloadingIndex = 0;

	    private DownloadFilesTask()
	    {
	    }

	    protected Void doInBackground(Void[] paramArrayOfVoid)
	    {
	      int i = 0;
	      while (true)
	      {
	        Song localSong = getSongToDownload();
	        if (localSong == null)
	        {
	          if (i != 0)
	            NotificationsUtil.notifyNow(MusicPlayService.this, "姝屾洸涓嬭浇瀹屾垚锛�, "鐐瑰嚮鏌ョ湅", 3, "switch_offline");
	          return null;
	        }
	        FmSetting localFmSetting1 = MusicPlayService.this.mFmSetting;
	        MusicPlayService localMusicPlayService1 = MusicPlayService.this;
	        FmSetting localFmSetting2 = MusicPlayService.this.mFmSetting;
	        if (!localFmSetting1.checkNetwork(localMusicPlayService1, localFmSetting2, 0))
	          continue;
	        MusicPlayService.this.mDownloadProgress = 0;
	        String str1 = FileUtil.getMp3Path(String.valueOf(localSong.getSongId()));
	        if (new File(str1).exists())
	          boolean bool = new File(str1).delete();
	        String str2 = localSong.getFileUrl();
	        download(str2, str1);
	        String str3 = AlbumCover.getCoverFileName(localSong.getAlbumId());
	        if ((!new File(str3).exists()) && (localSong.getCover() != null))
	        {
	          FileUtil localFileUtil1 = MusicPlayService.this.mFileUtil;
	          String str4 = localSong.getCover().replaceAll("_[123]\\.", "_4\\.");
	          Bitmap localBitmap = localFileUtil1.getImg(str4, str3);
	        }
	        if (localSong.getLrcFile() != null)
	        {
	          FileUtil localFileUtil2 = MusicPlayService.this.mFileUtil;
	          String str5 = localSong.getLrcFile();
	          int j = localSong.getSongId();
	          String str6 = localFileUtil2.getWebLrc(str5, j);
	        }
	        localSong.setDownloadStatus(1);
	        MusicPlayService.this.mOfflineUtil.updateOfflineSong(localSong);
	        MusicPlayService localMusicPlayService2 = MusicPlayService.this;
	        Intent localIntent = new Intent("com.xiami.download_list_refresh");
	        localMusicPlayService2.sendBroadcast(localIntent);
	        i = 1;
	      }
	    }

	    public void download(String paramString1, String paramString2)
	    {
	      try
	      {
	        String str1 = paramString1;
	        String str2 = " ";
	        String str3 = "%20";
	        String str4 = str1.replace(str2, str3);
	        URL localURL1 = new java/net/URL;
	        URL localURL2 = localURL1;
	        String str5 = str4;
	        localURL2.<init>(str5);
	        String str6 = paramString2;
	        File localFile = new File(str6);
	        long l1 = System.currentTimeMillis();
	        int i = Log.d("download begining");
	        StringBuilder localStringBuilder1 = new StringBuilder("download url:");
	        URL localURL3 = localURL1;
	        int j = Log.d(localURL3);
	        StringBuilder localStringBuilder2 = new StringBuilder("downloaded file name:");
	        String str7 = paramString2;
	        int k = Log.d(str7);
	        InputStream localInputStream1 = localURL1.openConnection().getInputStream();
	        BufferedInputStream localBufferedInputStream1 = new java/io/BufferedInputStream;
	        BufferedInputStream localBufferedInputStream2 = localBufferedInputStream1;
	        InputStream localInputStream2 = localInputStream1;
	        int m = 4096;
	        localBufferedInputStream2.<init>(localInputStream2, m);
	        ByteArrayBuffer localByteArrayBuffer1 = new org/apache/http/util/ByteArrayBuffer;
	        ByteArrayBuffer localByteArrayBuffer2 = localByteArrayBuffer1;
	        int n = 512;
	        localByteArrayBuffer2.<init>(n);
	        FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile);
	        int i1 = 1;
	        while (true)
	        {
	          int i2 = localBufferedInputStream1.read();
	          int i3 = i2;
	          int i4 = 65535;
	          if (i3 == i4)
	          {
	            if (localByteArrayBuffer1.length() > 0)
	            {
	              byte[] arrayOfByte1 = localByteArrayBuffer1.toByteArray();
	              FileOutputStream localFileOutputStream2 = localFileOutputStream1;
	              byte[] arrayOfByte2 = arrayOfByte1;
	              localFileOutputStream2.write(arrayOfByte2);
	            }
	            MusicPlayService.this.mDownloadProgress = 99;
	            localFileOutputStream1.close();
	            StringBuilder localStringBuilder3 = new StringBuilder("download ready in");
	            long l2 = (System.currentTimeMillis() - l1) / 1000L;
	            int i5 = Log.d(l2 + " sec");
	            return;
	          }
	          if (localByteArrayBuffer1.isFull())
	          {
	            byte[] arrayOfByte3 = localByteArrayBuffer1.toByteArray();
	            FileOutputStream localFileOutputStream3 = localFileOutputStream1;
	            byte[] arrayOfByte4 = arrayOfByte3;
	            localFileOutputStream3.write(arrayOfByte4);
	            localByteArrayBuffer1.clear();
	          }
	          i1 += 1;
	          int i6 = i2;
	          int i7 = 250;
	          if (i6 == i7)
	          {
	            int i8 = i1 % 120;
	            int i9 = 3;
	            if (i8 == i9)
	            {
	              MusicPlayService localMusicPlayService = MusicPlayService.this;
	              int i10 = MusicPlayService.this.mDownloadProgress + 1;
	              int i11 = Math.min(99, i10);
	              localMusicPlayService.mDownloadProgress = i11;
	            }
	          }
	          int i12 = (byte)i2;
	          ByteArrayBuffer localByteArrayBuffer3 = localByteArrayBuffer1;
	          int i13 = i12;
	          localByteArrayBuffer3.append(i13);
	        }
	      }
	      catch (IOException localIOException1)
	      {
	        while (true)
	        {
	          localIOException1.printStackTrace();
	          StringBuilder localStringBuilder4 = new StringBuilder("Error: ");
	          IOException localIOException2 = localIOException1;
	          int i14 = Log.d(localIOException2);
	        }
	      }
	    }

	    public Song getSongToDownload()
	    {
	      int i = MusicPlayService.this.mOfflineSongs.size();
	      int j = this.downloadingIndex;
	      while (true)
	      {
	        if (j >= i)
	          this.downloadingIndex = -1;
	        Song localSong;
	        for (Object localObject = null; ; localObject = localSong)
	        {
	          return localObject;
	          localSong = (Song)MusicPlayService.this.mOfflineSongs.get(j);
	          if (localSong.getDownloadStatus() != 0)
	            break;
	          localSong.setDownloadStatus(2);
	          this.downloadingIndex = j;
	        }
	        j += 1;
	      }
	    }
	  }

	  class LoadDefaultPlaylistTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private LoadDefaultPlaylistTask()
	    {
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      return MusicPlayService.this.app.getWebUtil().getMyPlaylist();
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if ((paramList == null) || (paramList.size() < 1));
	      while (true)
	      {
	        return;
	        int i = Log.d("default list loaded");
	        if ((MusicPlayService.this.mPlayer == null) || (MusicPlayService.MultiPlayer.access$3(MusicPlayService.this.mPlayer) != null))
	          continue;
	        MusicPlayService.this.setSongs(paramList, 0, 0);
	      }
	    }
	  }

	  public class MultiPlayer
	  {
	    private boolean isPlay = 0;
	    private boolean isRadio = 0;
	    private int loadedProgress = 0;
	    private Context mContext;
	    private boolean mIsInitialized = 0;
	    private MediaPlayer mMediaPlayer;
	    private String mPath;
	    private int mode;
	    private Song playingSong;
	    private String radioName;

	    public MultiPlayer(Context arg2)
	    {
	      MediaPlayer localMediaPlayer = new MediaPlayer();
	      this.mMediaPlayer = localMediaPlayer;
	      this.mode = 0;
	      this.playingSong = null;
	      this.radioName = "";
	      Object localObject;
	      this.mContext = localObject;
	    }

	    public int duration()
	    {
	      if (this.mIsInitialized);
	      for (int i = this.mMediaPlayer.getDuration(); ; i = 0)
	        return i;
	    }

	    public boolean isInitialized()
	    {
	      return this.mIsInitialized;
	    }

	    public boolean isPlaying()
	    {
	      return this.mMediaPlayer.isPlaying();
	    }

	    public void pause()
	    {
	      int i = Log.d("mediaplayer pause");
	      this.mMediaPlayer.pause();
	    }

	    public int position()
	    {
	      if (this.mIsInitialized);
	      for (int i = this.mMediaPlayer.getCurrentPosition(); ; i = 0)
	        return i;
	    }

	    public void release()
	    {
	      stop();
	      this.mMediaPlayer.release();
	    }

	    public long seek(long paramLong)
	    {
	      try
	      {
	        MediaPlayer localMediaPlayer = this.mMediaPlayer;
	        int i = (int)paramLong;
	        localMediaPlayer.seekTo(i);
	        return paramLong;
	      }
	      catch (IllegalStateException localIllegalStateException)
	      {
	        while (true)
	          localIllegalStateException.printStackTrace();
	      }
	    }

	    // ERROR //
	    public void setDataSourceAsync(String paramString)
	    {
	      // Byte code:
	      //   0: aload_0
	      //   1: aload_1
	      //   2: putfield 71	com/xiami/service/MusicPlayService$MultiPlayer:mPath	Ljava/lang/String;
	      //   5: aload_0
	      //   6: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   9: invokevirtual 142	android/media/MediaPlayer:reset	()V
	      //   12: aload_1
	      //   13: ldc 144
	      //   15: invokevirtual 150	java/lang/String:startsWith	(Ljava/lang/String;)Z
	      //   18: ifeq +134 -> 152
	      //   21: aload_0
	      //   22: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   25: astore_2
	      //   26: aload_0
	      //   27: getfield 30	com/xiami/service/MusicPlayService$MultiPlayer:this$0	Lcom/xiami/service/MusicPlayService;
	      //   30: astore_3
	      //   31: aload_1
	      //   32: invokestatic 156	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
	      //   35: astore 4
	      //   37: aload_2
	      //   38: aload_3
	      //   39: aload 4
	      //   41: invokevirtual 160	android/media/MediaPlayer:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
	      //   44: aload_0
	      //   45: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   48: iconst_3
	      //   49: invokevirtual 163	android/media/MediaPlayer:setAudioStreamType	(I)V
	      //   52: aload_0
	      //   53: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   56: astore 5
	      //   58: new 165	com/xiami/service/MusicPlayService$MultiPlayer$1
	      //   61: dup
	      //   62: aload_0
	      //   63: invokespecial 168	com/xiami/service/MusicPlayService$MultiPlayer$1:<init>	(Lcom/xiami/service/MusicPlayService$MultiPlayer;)V
	      //   66: astore 6
	      //   68: aload 5
	      //   70: aload 6
	      //   72: invokevirtual 172	android/media/MediaPlayer:setOnPreparedListener	(Landroid/media/MediaPlayer$OnPreparedListener;)V
	      //   75: aload_0
	      //   76: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   79: astore 7
	      //   81: new 174	com/xiami/service/MusicPlayService$MultiPlayer$2
	      //   84: dup
	      //   85: aload_0
	      //   86: invokespecial 175	com/xiami/service/MusicPlayService$MultiPlayer$2:<init>	(Lcom/xiami/service/MusicPlayService$MultiPlayer;)V
	      //   89: astore 8
	      //   91: aload 7
	      //   93: aload 8
	      //   95: invokevirtual 179	android/media/MediaPlayer:setOnCompletionListener	(Landroid/media/MediaPlayer$OnCompletionListener;)V
	      //   98: aload_0
	      //   99: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   102: astore 9
	      //   104: new 181	com/xiami/service/MusicPlayService$MultiPlayer$3
	      //   107: dup
	      //   108: aload_0
	      //   109: invokespecial 182	com/xiami/service/MusicPlayService$MultiPlayer$3:<init>	(Lcom/xiami/service/MusicPlayService$MultiPlayer;)V
	      //   112: astore 10
	      //   114: aload 9
	      //   116: aload 10
	      //   118: invokevirtual 186	android/media/MediaPlayer:setOnErrorListener	(Landroid/media/MediaPlayer$OnErrorListener;)V
	      //   121: aload_0
	      //   122: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   125: astore 11
	      //   127: new 188	com/xiami/service/MusicPlayService$MultiPlayer$4
	      //   130: dup
	      //   131: aload_0
	      //   132: invokespecial 189	com/xiami/service/MusicPlayService$MultiPlayer$4:<init>	(Lcom/xiami/service/MusicPlayService$MultiPlayer;)V
	      //   135: astore 12
	      //   137: aload 11
	      //   139: aload 12
	      //   141: invokevirtual 193	android/media/MediaPlayer:setOnBufferingUpdateListener	(Landroid/media/MediaPlayer$OnBufferingUpdateListener;)V
	      //   144: aload_0
	      //   145: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   148: invokevirtual 196	android/media/MediaPlayer:prepareAsync	()V
	      //   151: return
	      //   152: aload_0
	      //   153: getfield 47	com/xiami/service/MusicPlayService$MultiPlayer:mMediaPlayer	Landroid/media/MediaPlayer;
	      //   156: aload_1
	      //   157: invokevirtual 198	android/media/MediaPlayer:setDataSource	(Ljava/lang/String;)V
	      //   160: goto -116 -> 44
	      //   163: astore 13
	      //   165: aload 13
	      //   167: invokevirtual 131	java/lang/IllegalStateException:printStackTrace	()V
	      //   170: aload_0
	      //   171: getfield 30	com/xiami/service/MusicPlayService$MultiPlayer:this$0	Lcom/xiami/service/MusicPlayService;
	      //   174: invokevirtual 201	com/xiami/service/MusicPlayService:playNext	()V
	      //   177: goto -133 -> 44
	      //   180: astore 14
	      //   182: aload_0
	      //   183: ldc 34
	      //   185: putfield 42	com/xiami/service/MusicPlayService$MultiPlayer:mIsInitialized	Z
	      //   188: goto -37 -> 151
	      //   191: astore 15
	      //   193: aload_0
	      //   194: ldc 34
	      //   196: putfield 42	com/xiami/service/MusicPlayService$MultiPlayer:mIsInitialized	Z
	      //   199: goto -48 -> 151
	      //   202: astore 16
	      //   204: goto -53 -> 151
	      //
	      // Exception table:
	      //   from	to	target	type
	      //   152	160	163	java/lang/IllegalStateException
	      //   0	144	180	java/io/IOException
	      //   144	151	180	java/io/IOException
	      //   152	160	180	java/io/IOException
	      //   165	177	180	java/io/IOException
	      //   0	144	191	java/lang/IllegalArgumentException
	      //   144	151	191	java/lang/IllegalArgumentException
	      //   152	160	191	java/lang/IllegalArgumentException
	      //   165	177	191	java/lang/IllegalArgumentException
	      //   144	151	202	java/lang/Exception
	    }

	    public void setVolume(float paramFloat)
	    {
	      try
	      {
	        this.mMediaPlayer.setVolume(paramFloat, paramFloat);
	        return;
	      }
	      catch (IllegalStateException localIllegalStateException)
	      {
	        while (true)
	          localIllegalStateException.printStackTrace();
	      }
	    }

	    public void start()
	    {
	      MusicPlayService.this.mPlayer.isPlay = 1;
	      MusicPlayService localMusicPlayService = MusicPlayService.this;
	      Intent localIntent = new Intent("com.xiami.play_state_changed");
	      localMusicPlayService.sendBroadcast(localIntent);
	      this.mMediaPlayer.start();
	    }

	    public void stop()
	    {
	      this.mMediaPlayer.reset();
	      this.mIsInitialized = 0;
	    }
	  }

	  class PlaylogTask extends AsyncTask<Song, Void, Void>
	  {
	    private PlaylogTask()
	    {
	    }

	    protected Void doInBackground(Song[] paramArrayOfSong)
	    {
	      if ((paramArrayOfSong == null) || (paramArrayOfSong.length == 0) || (paramArrayOfSong[0] == 0));
	      for (Object localObject = null; ; localObject = null)
	      {
	        return localObject;
	        API localAPI = MusicPlayService.this.app.getWebUtil();
	        int i = paramArrayOfSong[0].getSongId();
	        boolean bool = localAPI.playlog(i);
	      }
	    }
	  }*/
}
