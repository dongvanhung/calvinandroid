package com.xiami.activity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import org.json.JSONException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.xiami.Musicable;
import com.xiami.Song;
import com.xiami.XPlayer;
import com.xiami.XiamiApp;
import com.xiami.service.MusicPlayService;
import com.xiami.util.API;
import com.xiami.util.NotificationsUtil;

public abstract class MusicableActivity extends Activity implements Musicable {

	static final int PAUSE = 1;
	  static final int PLAY_RADIO = 2;
//	  static final int PLAY_SONG;
	  private API api;
	  private ServiceConnection mConnection;
	  private Handler mHandler;
//	  private IMusicPlayService mIMusicPlayService;
	  private XPlayer player;

	  public MusicableActivity()
	  {
	    /*MusicableActivity.1 local1 = new MusicableActivity.1(this);
	    this.mConnection = local1;
	    MusicableActivity.2 local2 = new MusicableActivity.2(this);
	    this.mHandler = local2;
	    this.player = null;*/
	  }

	  private void pause()
	  {
	    Handler localHandler = this.mHandler;
	    Message localMessage = this.mHandler.obtainMessage(1);
	    boolean bool = localHandler.sendMessage(localMessage);
	  }

	  public void deleteAllDownloadSong()
	  {
	    /*if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.deleteAllDownloadSong();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }*/
	  }

	  public void deleteDownloadSong(Song paramSong)
	  {
	    /*if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.deleteDownloadSong(paramSong);
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }*/
	  }
/*
	  public void download(Song paramSong)
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      i = this.mIMusicPlayService.downloadSong(paramSong);
	      if (i == 0)
	        NotificationsUtil.ToastShort(this, "Test");
	      while (true)
	      {
	        return;
	        if (i != 1)
	          break;
	        NotificationsUtil.ToastShort(this, "Test");
	      }
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	      {
	        int i;
	        localRemoteException.printStackTrace();
	        continue;
	        if (i == 2)
	        {
	          NotificationsUtil.ToastShort(this, "Test");
	          continue;
	        }
	        NotificationsUtil.ToastShort(this, "TEst");
	      }
	    }
	  }

	  public void download(List<Song> paramList)
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      int i = this.mIMusicPlayService.downloadSongs(paramList);
	      if (i == 0)
	        NotificationsUtil.ToastShort(this, "鎴愬姛鍔犲叆鍒颁笅杞藉垪琛�);
	      while (true)
	      {
	        return;
	        if (i != 2)
	          break;
	        NotificationsUtil.ToastShort(this, "涓嬭浇瀹归噺鍓╀綑涓嶈冻,涓嶈兘涓嬭浇鏁翠釜鍒楄〃");
	      }
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	      {
	        localRemoteException.printStackTrace();
	        continue;
	        NotificationsUtil.ToastShort(this, "鏈煡閿欒锛屼笅杞藉け璐�);
	      }
	    }
	  }

	  public int getDownloadingIndex()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getDownloadingIndex();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = -1;
	    }
	  }

	  public int getDownloadingProgress()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getDownloadingProgress();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = -1;
	    }
	  }

	  public int getLoadedProgress()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getLoadedProgress();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = 0;
	    }
	  }

	  public int getMode()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getMode();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = 0;
	    }
	  }

	  public List<Song> getOfflineSongs()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        List localList1 = this.mIMusicPlayService.getOfflineSongs();
	        localList2 = localList1;
	        return localList2;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      List localList2 = null;
	    }
	  }

	  public Song getPlayingSong()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        Song localSong1 = this.mIMusicPlayService.getPlayingSong();
	        localSong2 = localSong1;
	        return localSong2;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      Song localSong2 = null;
	    }
	  }

	  public String getRadioName()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        String str1 = this.mIMusicPlayService.getRadioName();
	        str2 = str1;
	        return str2;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      String str2 = null;
	    }
	  }

	  public int getTimePlay()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getTimePlay();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = 0;
	    }
	  }

	  public int getTimeTotal()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getTimeTotal();
	        j = i;
	        return j;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      int j = 0;
	    }
	  }

	  public boolean isPlay()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        boolean bool1 = this.mIMusicPlayService.isPlay();
	        bool2 = bool1;
	        return bool2;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      boolean bool2 = false;
	    }
	  }

	  public boolean isRadio()
	  {
	    if (this.mIMusicPlayService != null);
	    while (true)
	    {
	      try
	      {
	        boolean bool1 = this.mIMusicPlayService.isRadio();
	        bool2 = bool1;
	        return bool2;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	      boolean bool2 = false;
	    }
	  }

	  public boolean onBackKey()
	  {
	    finish();
	    return true;
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    Intent localIntent1 = new Intent(this, MusicPlayService.class);
	    ComponentName localComponentName = startService(localIntent1);
	    Intent localIntent2 = new Intent(this, MusicPlayService.class);
	    ServiceConnection localServiceConnection = this.mConnection;
	    boolean bool = bindService(localIntent2, localServiceConnection, 1);
	    super.onCreate(paramBundle);
	  }

	  protected void onDestroy()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      int i = Log.d("unbindservice from activity");
	      ServiceConnection localServiceConnection = this.mConnection;
	      unbindService(localServiceConnection);
	    }
	    super.onDestroy();
	  }

	  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	  {
	    if (paramInt == 4);
	    for (boolean bool = onBackKey(); ; bool = false)
	      return bool;
	  }

	  protected void onResume()
	  {
	    if (this.player != null)
	    {
	      this.player.registerMetaReceiver();
	      this.player.checkUpdateTask();
	    }
	    super.onResume();
	  }

	  protected void onServiceConnected()
	  {
	  }

	  protected void onStart()
	  {
	    XiamiApp localXiamiApp = (XiamiApp)getApplication();
	    API localAPI = new API(localXiamiApp);
	    this.api = localAPI;
	    super.onStart();
	  }

	  protected void onStop()
	  {
	    int i = Log.d("musicable::onstop");
	    if (this.player != null)
	      this.player.release();
	    super.onStop();
	  }

	  public void play(List<Song> paramList)
	  {
	    play(paramList, 0);
	  }

	  public void play(List<Song> paramList, int paramInt)
	  {
	    if ((paramList != null) && (paramList.size() > 0))
	      if (this.mIMusicPlayService == null)
	        NotificationsUtil.ToastShort(this, "鎾斁鍣ㄩ敊璇紝璇风◢鍊欓噸璇�);
	    while (true)
	    {
	      return;
	      Bundle localBundle = new Bundle();
	      ArrayList localArrayList = (ArrayList)paramList;
	      localBundle.putParcelableArrayList("songs", localArrayList);
	      localBundle.putInt("track", paramInt);
	      Message localMessage = this.mHandler.obtainMessage(0);
	      localMessage.setData(localBundle);
	      boolean bool = this.mHandler.sendMessage(localMessage);
	      continue;
	      NotificationsUtil.ToastShort(this, "娌℃湁姝屾洸鍙互鎾斁锛岃閲嶈瘯");
	    }
	  }

	  public void playAlbum(int paramInt)
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      SONGTYPES localSONGTYPES = SONGTYPES.ALBUM;
	      LoadSongListTask localLoadSongListTask = new LoadSongListTask(paramInt, localSONGTYPES);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadSongListTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  public void playArtist(int paramInt, String paramString)
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      LoadArtistRadioSongTask localLoadArtistRadioSongTask = new LoadArtistRadioSongTask(paramInt, paramString);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadArtistRadioSongTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  public void playCollect(int paramInt)
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      SONGTYPES localSONGTYPES = SONGTYPES.COLLECT;
	      LoadSongListTask localLoadSongListTask = new LoadSongListTask(paramInt, localSONGTYPES);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadSongListTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  public void playMyRadio()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      LoadMyRadioTask localLoadMyRadioTask = new LoadMyRadioTask(null);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadMyRadioTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  public void playNext()
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.playNext();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void playPause()
	  {
	    int i = Log.d("musicableactivity:playpause");
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.playPause();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void playPrev()
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.playPrev();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void playRadio(int paramInt, String paramString)
	  {
	    playRadio(paramInt, paramString, null);
	  }

	  public void playRadio(int paramInt, String paramString, OnLoadListener paramOnLoadListener)
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      MobclickAgent.onEvent(this, "song play", "radio");
	      LoadRadioSongTask localLoadRadioSongTask = new LoadRadioSongTask(paramInt, paramString, paramOnLoadListener);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadRadioSongTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  public void playRadio(List<Song> paramList, String paramString)
	  {
	    if ((paramList != null) && (paramList.size() > 0))
	      if (this.mIMusicPlayService == null)
	        NotificationsUtil.ToastShort(this, "鎾斁鍣ㄩ敊璇紝璇风◢鍊欓噸璇�);
	    while (true)
	    {
	      return;
	      Bundle localBundle = new Bundle();
	      ArrayList localArrayList = (ArrayList)paramList;
	      localBundle.putParcelableArrayList("songs", localArrayList);
	      localBundle.putString("name", paramString);
	      Message localMessage = this.mHandler.obtainMessage(2);
	      localMessage.setData(localBundle);
	      boolean bool = this.mHandler.sendMessage(localMessage);
	      continue;
	      NotificationsUtil.ToastShort(this, "娌℃湁姝屾洸鍙互鎾斁锛岃閲嶈瘯");
	    }
	  }

	  public void playRndSongs()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      LoadRndSongsTask localLoadRndSongsTask = new LoadRndSongsTask(null);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadRndSongsTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "鍔犺浇姝屾洸涓�..");
	    }
	  }

	  protected void resumePlay()
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.resumePlay();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void setPlayer(XPlayer paramXPlayer)
	  {
	    this.player = paramXPlayer;
	  }

	  public void setTitle(CharSequence paramCharSequence)
	  {
	    ((TextView)findViewById(2131165195)).setText(paramCharSequence);
	  }

	  public void shufflePlay(List<Song> paramList)
	  {
	    if ((paramList == null) || (paramList.size() < 1))
	      NotificationsUtil.ToastShort(this, "娌℃湁姝屾洸鍙互鎾斁锛岃閲嶈瘯");
	    while (true)
	    {
	      return;
	      if (this.mIMusicPlayService == null)
	        continue;
	      try
	      {
	        this.mIMusicPlayService.setMode(1);
	        Random localRandom = new Random();
	        int i = paramList.size();
	        int j = localRandom.nextInt(i);
	        play(paramList, j);
	      }
	      catch (RemoteException localRemoteException)
	      {
	        while (true)
	          localRemoteException.printStackTrace();
	      }
	    }
	  }

	  protected void stopService()
	  {
	    SharedPreferences.Editor localEditor1 = PreferenceManager.getDefaultSharedPreferences(this).edit();
	    SharedPreferences.Editor localEditor2 = localEditor1.remove("minleft");
	    SharedPreferences.Editor localEditor3 = localEditor1.remove("timetoclose");
	    boolean bool1 = localEditor1.commit();
	    int i = Log.d("Service", "Player::closeService");
	    Intent localIntent = new Intent(this, MusicPlayService.class);
	    boolean bool2 = stopService(localIntent);
	  }

	  public void toggleMode()
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.toggleMode();
	      return;
	    }
	    catch (RemoteException localRemoteException)
	    {
	      while (true)
	        localRemoteException.printStackTrace();
	    }
	  }

	  class LoadArtistRadioSongTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private int id;
	    private String name;

	    public LoadArtistRadioSongTask(int paramString, String arg3)
	    {
	      this.id = paramString;
	      Object localObject;
	      this.name = localObject;
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      try
	      {
	        API localAPI = MusicableActivity.this.api;
	        int i = this.id;
	        List localList1 = localAPI.getArtistRadioSongs(i);
	        localList2 = localList1;
	        return localList2;
	      }
	      catch (UnsupportedEncodingException localUnsupportedEncodingException)
	      {
	        while (true)
	        {
	          localUnsupportedEncodingException.printStackTrace();
	          List localList2 = null;
	        }
	      }
	      catch (URLArgNotFoundException localURLArgNotFoundException)
	      {
	        while (true)
	          localURLArgNotFoundException.printStackTrace();
	      }
	      catch (JSONException localJSONException)
	      {
	        while (true)
	          localJSONException.printStackTrace();
	      }
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	      {
	        MusicableActivity localMusicableActivity = MusicableActivity.this;
	        String str = this.name;
	        localMusicableActivity.playRadio(paramList, str);
	      }
	      while (true)
	      {
	        return;
	        NotificationsUtil.ToastShort(MusicableActivity.this, "鍔犺浇鐢靛彴鏁版嵁澶辫触!");
	      }
	    }
	  }

	  class LoadMyRadioTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private LoadMyRadioTask()
	    {
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      return MusicableActivity.this.api.getMyRadioSongs();
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	        MusicableActivity.this.play(paramList, 0);
	      while (true)
	      {
	        return;
	        NotificationsUtil.ToastShort(MusicableActivity.this, "鍔犺浇姝屾洸澶辫触!");
	      }
	    }
	  }

	  class LoadRadioSongTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private int id;
	    private MusicableActivity.OnLoadListener listener;
	    private String name;

	    public LoadRadioSongTask(int paramString, String paramOnLoadListener, MusicableActivity.OnLoadListener arg4)
	    {
	      this.id = paramString;
	      this.name = paramOnLoadListener;
	      Object localObject;
	      this.listener = localObject;
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      API localAPI = MusicableActivity.this.api;
	      int i = this.id;
	      Radio localRadio = localAPI.getRadio(i);
	      if (localRadio == null);
	      for (List localList = null; ; localList = localRadio.getSongs())
	        return localList;
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	      {
	        MusicableActivity localMusicableActivity = MusicableActivity.this;
	        String str = this.name;
	        localMusicableActivity.playRadio(paramList, str);
	        if (this.listener != null)
	          this.listener.onLoad();
	      }
	      while (true)
	      {
	        return;
	        NotificationsUtil.ToastShort(MusicableActivity.this, "鍔犺浇鐢靛彴鏁版嵁澶辫触!");
	      }
	    }
	  }

	  class LoadRndSongsTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private LoadRndSongsTask()
	    {
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      return MusicableActivity.this.api.getRndSongs();
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if ((paramList == null) || (paramList.size() < 1))
	        NotificationsUtil.ToastShort(MusicableActivity.this, "鍔犺浇姝屾洸澶辫触!");
	      while (true)
	      {
	        return;
	        MusicableActivity.this.play(paramList, 0);
	      }
	    }
	  }

	  class LoadSongListTask extends AsyncTask<Void, Void, List<Song>>
	  {
	    private int id;
	    private MusicableActivity.SONGTYPES type;

	    public LoadSongListTask(int paramSONGTYPES, MusicableActivity.SONGTYPES arg3)
	    {
	      this.id = paramSONGTYPES;
	      Object localObject;
	      this.type = localObject;
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	      try
	      {
	        int[] arrayOfInt = $SWITCH_TABLE$com$xiami$activity$MusicableActivity$SONGTYPES();
	        int i = this.type.ordinal();
	        Object localObject;
	        switch (arrayOfInt[i])
	        {
	        default:
	          localObject = null;
	        case 3:
	        case 2:
	        case 1:
	        }
	        while (true)
	        {
	          return localObject;
	          API localAPI1 = MusicableActivity.this.api;
	          int j = this.id;
	          Radio localRadio = localAPI1.getRadio(j);
	          if (localRadio == null)
	          {
	            localObject = null;
	            continue;
	          }
	          localObject = localRadio.getSongs();
	          continue;
	          API localAPI2 = MusicableActivity.this.api;
	          int k = this.id;
	          Collect localCollect = localAPI2.getCollect(k);
	          if (localCollect == null)
	          {
	            localObject = null;
	            continue;
	          }
	          localObject = localCollect.getSongs();
	          continue;
	          API localAPI3 = MusicableActivity.this.api;
	          int m = this.id;
	          Album localAlbum = localAPI3.getAlbum(m);
	          if (localAlbum == null)
	          {
	            localObject = null;
	            continue;
	          }
	          List localList = localAlbum.getSongs();
	          localObject = localList;
	        }
	      }
	      catch (JSONException localJSONException)
	      {
	        while (true)
	          localJSONException.printStackTrace();
	      }
	      catch (UnsupportedEncodingException localUnsupportedEncodingException)
	      {
	        while (true)
	          localUnsupportedEncodingException.printStackTrace();
	      }
	      catch (URLArgNotFoundException localURLArgNotFoundException)
	      {
	        while (true)
	          localURLArgNotFoundException.printStackTrace();
	      }
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	        MusicableActivity.this.play(paramList, 0);
	      while (true)
	      {
	        return;
	        NotificationsUtil.ToastShort(MusicableActivity.this, "鍔犺浇姝屾洸澶辫触!");
	      }
	    }
	  }

	  abstract interface OnLoadListener
	  {
	    public abstract void onLoad();
	  }

	  enum SONGTYPES
	  {
	    static
	    {
	      SONGTYPES[] arrayOfSONGTYPES = new SONGTYPES[3];
	      SONGTYPES localSONGTYPES1 = ALBUM;
	      arrayOfSONGTYPES[0] = localSONGTYPES1;
	      SONGTYPES localSONGTYPES2 = COLLECT;
	      arrayOfSONGTYPES[1] = localSONGTYPES2;
	      SONGTYPES localSONGTYPES3 = RADIO;
	      arrayOfSONGTYPES[2] = localSONGTYPES3;
	      ENUM$VALUES = arrayOfSONGTYPES;
	    }
	  }*/
}
