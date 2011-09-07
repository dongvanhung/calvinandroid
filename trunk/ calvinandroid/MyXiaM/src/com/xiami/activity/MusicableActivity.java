package com.xiami.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import android.os.IBinder;
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
import com.xiami.exception.URLArgNotFoundException;
import com.xiami.lib.data.Album;
import com.xiami.lib.data.Collect;
import com.xiami.lib.data.Radio;
import com.xiami.service.IMusicPlayService;
import com.xiami.service.MusicPlayService;
import com.xiami.util.API;
import com.xiami.util.NotificationsUtil;

public abstract class MusicableActivity extends Activity implements Musicable {

	static final int PAUSE = 1;
	  static final int PLAY_RADIO = 2;
	  static final int PLAY_SONG = 0;
	  private API api;
	  private ServiceConnection mConnection;
	  private Handler mHandler;
	  private IMusicPlayService mIMusicPlayService;
	  private XPlayer player;

	  public MusicableActivity()
	  {
		  MusicableActivity1 local1 = new MusicableActivity1();
	    this.mConnection = local1;
	    MusicableActivity2 local2 = new MusicableActivity2();
	    this.mHandler = local2;
	    this.player = null;
	  }
	  
	  class MusicableActivity2 extends Handler
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      /*int i = paramMessage.what;
	      if (i == 1)
	        if (MusicableActivity.access$1(this.this$0) == null);
	        else
	      {
	        try
	        {
	          MusicableActivity.access$1(this.this$0).pause();
	          removeMessages(i);
	          return;
	        }
	        catch (RemoteException localRemoteException1)
	        {
	          localRemoteException1.printStackTrace();
	          continue;
	        }
	        if (i == 0)
	          try
	          {
	            Bundle localBundle1 = paramMessage.getData();
	            ArrayList localArrayList1 = localBundle1.getParcelableArrayList("songs");
	            IMusicPlayService localIMusicPlayService = MusicableActivity.access$1(this.this$0);
	            int j = localBundle1.getInt("track");
	            localIMusicPlayService.play(localArrayList1, j);
	            if (MusicableActivity.access$2(this.this$0) != null)
	              MusicableActivity.access$2(this.this$0).open();
	            removeMessages(i);
	          }
	          catch (RemoteException localRemoteException2)
	          {
	            while (true)
	              localRemoteException2.printStackTrace();
	          }
	        if (i != 2)
	          continue;
	        try
	        {
	          Bundle localBundle2 = paramMessage.getData();
	          ArrayList localArrayList2 = localBundle2.getParcelableArrayList("songs");
	          String str = localBundle2.getString("name");
	          MusicableActivity.access$1(this.this$0).playRadio(localArrayList2, str);
	          if (MusicableActivity.access$2(this.this$0) != null)
	            MusicableActivity.access$2(this.this$0).open();
	          removeMessages(i);
	        }
	        catch (RemoteException localRemoteException3)
	        {
	          while (true)
	            localRemoteException3.printStackTrace();
	        }
	      }*/
	    }
	  }
	  
	  class MusicableActivity1 implements ServiceConnection
	{
	  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
	  {
//	    MusicableActivity localMusicableActivity = this.this$0;
	    IMusicPlayService localIMusicPlayService = IMusicPlayService.Stub.asInterface(paramIBinder);
//	    MusicableActivity.access$0(localMusicableActivity, localIMusicPlayService);
//	    this.this$0.onServiceConnected();
	  }

	  public void onServiceDisconnected(ComponentName paramComponentName)
	  {
//	    MusicableActivity.access$0(this.this$0, null);
	  }
	}

	  private void pause()
	  {
	    Handler localHandler = this.mHandler;
	    Message localMessage = this.mHandler.obtainMessage(1);
	    boolean bool = localHandler.sendMessage(localMessage);
	  }

	  public void deleteAllDownloadSong()
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.deleteAllDownloadSong();
	    }
	    catch (RemoteException localRemoteException)
	    {
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void deleteDownloadSong(Song paramSong)
	  {
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      this.mIMusicPlayService.deleteDownloadSong(paramSong);
	    }
	    catch (RemoteException localRemoteException)
	    {
	        localRemoteException.printStackTrace();
	    }
	  }

	  public void download(Song paramSong)
	  {
		  int i = 0;
	    if (this.mIMusicPlayService != null);
	    try
	    {
	      i = this.mIMusicPlayService.downloadSong(paramSong);
	      if (i == 0)
	        NotificationsUtil.ToastShort(this, "Test");
	      else
	      {
	        if (i != 1){}
	        NotificationsUtil.ToastShort(this, "Test");
	      }
	    }
	    catch (RemoteException localRemoteException)
	    {
	        localRemoteException.printStackTrace();
	        if (i == 2)
	        {
	          NotificationsUtil.ToastShort(this, "Test");
	        } else {
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
	        NotificationsUtil.ToastShort(this, "Test");
	      else
	      {
	        if (i != 2){}
	        NotificationsUtil.ToastShort(this, "Test");
	      }
	    }
	    catch (RemoteException localRemoteException)
	    {
	        localRemoteException.printStackTrace();
	        NotificationsUtil.ToastShort(this, "Test");
	    }
	  }

	  public int getDownloadingIndex()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getDownloadingIndex();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return -1;
	  }

	  public int getDownloadingProgress()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getDownloadingProgress();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return -1;
	  }

	  public int getLoadedProgress()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getLoadedProgress();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return 0;
	  }

	  public int getMode()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getMode();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return 0;
	  }

	  public List<Song> getOfflineSongs()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        List localList1 = this.mIMusicPlayService.getOfflineSongs();
	        return localList1;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return null;
	  }

	  public Song getPlayingSong()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        Song localSong1 = this.mIMusicPlayService.getPlayingSong();
	        return localSong1;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return null;
	  }

	  public String getRadioName()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        String str1 = this.mIMusicPlayService.getRadioName();
	        return str1;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return "";
	  }

	  public int getTimePlay()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getTimePlay();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return 0;
	  }

	  public int getTimeTotal()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        int i = this.mIMusicPlayService.getTimeTotal();
	        return i;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return 0;
	  }

	  public boolean isPlay()
	  {
	    if (this.mIMusicPlayService != null) {
	      try
	      {
	        boolean bool1 = this.mIMusicPlayService.isPlay();
	        return bool1;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return false;
	  }

	  public boolean isRadio()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      try
	      {
	        boolean bool1 = this.mIMusicPlayService.isRadio();
	        return bool1;
	      }
	      catch (RemoteException localRemoteException)
	      {
	        localRemoteException.printStackTrace();
	      }
	    }
	    return false;
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
	        NotificationsUtil.ToastShort(this, "Test");
	      else
	    {
	      Bundle localBundle = new Bundle();
	      ArrayList localArrayList = (ArrayList)paramList;
	      localBundle.putParcelableArrayList("songs", localArrayList);
	      localBundle.putInt("track", paramInt);
	      Message localMessage = this.mHandler.obtainMessage(0);
	      localMessage.setData(localBundle);
	      boolean bool = this.mHandler.sendMessage(localMessage);
	      NotificationsUtil.ToastShort(this, "Test");
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
	      NotificationsUtil.ToastShort(this, "Test..");
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
	      NotificationsUtil.ToastShort(this, "Test..");
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
	      NotificationsUtil.ToastShort(this, "Test..");
	    }
	  }

	  public void playMyRadio()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      LoadMyRadioTask localLoadMyRadioTask = new LoadMyRadioTask();
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadMyRadioTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "Test..");
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
	      LoadRadioSongTask localLoadRadioSongTask = new LoadRadioSongTask(paramInt, paramString, paramOnLoadListener);
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadRadioSongTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "Test..");
	    }
	  }

	  public void playRadio(List<Song> paramList, String paramString)
	  {
	    if ((paramList != null) && (paramList.size() > 0))
	      if (this.mIMusicPlayService == null)
	        NotificationsUtil.ToastShort(this, "Test");
	      else
	    {
	      Bundle localBundle = new Bundle();
	      ArrayList localArrayList = (ArrayList)paramList;
	      localBundle.putParcelableArrayList("songs", localArrayList);
	      localBundle.putString("name", paramString);
	      Message localMessage = this.mHandler.obtainMessage(2);
	      localMessage.setData(localBundle);
	      boolean bool = this.mHandler.sendMessage(localMessage);
	      NotificationsUtil.ToastShort(this, "Test");
	    }
	  }

	  public void playRndSongs()
	  {
	    if (this.mIMusicPlayService != null)
	    {
	      pause();
	      LoadRndSongsTask localLoadRndSongsTask = new LoadRndSongsTask();
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadRndSongsTask.execute(arrayOfVoid);
	      NotificationsUtil.ToastShort(this, "Test..");
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
	      NotificationsUtil.ToastShort(this, "Test");
	    else
	    {
	      if (this.mIMusicPlayService != null) {
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
		          localRemoteException.printStackTrace();
		      }
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
	      this.name = arg3;
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	    	 List localList1 = null;
	      try
	      {
	        API localAPI = MusicableActivity.this.api;
	        int i = this.id;
	        localList1 = localAPI.getArtistRadioSongs(i);
	      }
	      catch (UnsupportedEncodingException localUnsupportedEncodingException)
	      {
	          localUnsupportedEncodingException.printStackTrace();
	      }
	      catch (URLArgNotFoundException localURLArgNotFoundException)
	      {
	          localURLArgNotFoundException.printStackTrace();
	      }
	      catch (JSONException localJSONException)
	      {
	          localJSONException.printStackTrace();
	      }
	      return localList1;
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	      {
	        MusicableActivity localMusicableActivity = MusicableActivity.this;
	        String str = this.name;
	        localMusicableActivity.playRadio(paramList, str);
	      }
	      else
	      {
	        NotificationsUtil.ToastShort(MusicableActivity.this, "Test!");
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
	      else
	      {
	        NotificationsUtil.ToastShort(MusicableActivity.this, "Test");
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
	      this.listener = arg4;
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
	      else
	      {
	        NotificationsUtil.ToastShort(MusicableActivity.this, "Test!");
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
	        NotificationsUtil.ToastShort(MusicableActivity.this, "Test");
	      else
	      {
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
	      this.type = arg3;
	    }

	    protected List<Song> doInBackground(Void[] paramArrayOfVoid)
	    {
	    	 List localObject = new ArrayList();
	      try
	      {
	        int i = this.type.ordinal();
	        switch (i + 1)
	        {
	        default:
	          localObject = null;
	          break;
	        case 3:
	        	API localAPI1 = MusicableActivity.this.api;
		          int j = this.id;
		          Radio localRadio = localAPI1.getRadio(j);
		          if (localRadio != null) {
		        	  localObject = localRadio.getSongs();
		          }
		          break;
	        case 2:
	        	API localAPI2 = MusicableActivity.this.api;
		          int k = this.id;
		          Collect localCollect = localAPI2.getCollect(k);
		          if (localCollect != null) {
		        	  localObject = localCollect.getSongs();
		          }
		          break;
	        case 1:
	        	 API localAPI3 = MusicableActivity.this.api;
		          int m = this.id;
		          Album localAlbum = localAPI3.getAlbum(m);
		          if (localAlbum != null) {
		        	  List localList = localAlbum.getSongs();
		        	  localObject = localList;
		          }
		          break;
	        }
	      }
	      catch (JSONException localJSONException)
	      {
	          localJSONException.printStackTrace();
	      }
	      catch (UnsupportedEncodingException localUnsupportedEncodingException)
	      {
	          localUnsupportedEncodingException.printStackTrace();
	      }
	      catch (URLArgNotFoundException localURLArgNotFoundException)
	      {
	          localURLArgNotFoundException.printStackTrace();
	      }
	      return localObject;
	    }

	    protected void onPostExecute(List<Song> paramList)
	    {
	      if (paramList != null)
	        MusicableActivity.this.play(paramList, 0);
	      else
	      {
	        NotificationsUtil.ToastShort(MusicableActivity.this, "Test");
	      }
	    }
	  }

	  abstract interface OnLoadListener
	  {
	    public abstract void onLoad();
	  }

	  static enum SONGTYPES
	  {
		  ALBUM,
		  COLLECT,
		  RADIO;
	  }
}
