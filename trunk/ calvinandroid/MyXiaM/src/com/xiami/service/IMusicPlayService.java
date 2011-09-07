package com.xiami.service;

import java.util.ArrayList;
import java.util.List;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import com.xiami.Song;

public interface IMusicPlayService extends IInterface {

	public abstract void deleteAllDownloadSong()
    throws RemoteException;

  public abstract void deleteDownloadSong(Song paramSong)
    throws RemoteException;

  public abstract int downloadSong(Song paramSong)
    throws RemoteException;

  public abstract int downloadSongs(List<Song> paramList)
    throws RemoteException;

  public abstract int getDownloadingIndex()
    throws RemoteException;

  public abstract int getDownloadingProgress()
    throws RemoteException;

  public abstract int getLoadedProgress()
    throws RemoteException;

  public abstract int getMode()
    throws RemoteException;

  public abstract List<Song> getOfflineSongs()
    throws RemoteException;

  public abstract Song getPlayingSong()
    throws RemoteException;

  public abstract String getRadioName()
    throws RemoteException;

  public abstract int getTimePlay()
    throws RemoteException;

  public abstract int getTimeTotal()
    throws RemoteException;

  public abstract boolean isDownloading()
    throws RemoteException;

  public abstract boolean isPlay()
    throws RemoteException;

  public abstract boolean isRadio()
    throws RemoteException;

  public abstract void pause()
    throws RemoteException;

  public abstract void play(List<Song> paramList, int paramInt)
    throws RemoteException;

  public abstract void playNext()
    throws RemoteException;

  public abstract void playPause()
    throws RemoteException;

  public abstract void playPrev()
    throws RemoteException;

  public abstract void playRadio(List<Song> paramList, String paramString)
    throws RemoteException;

  public abstract void resumePlay()
    throws RemoteException;

  public abstract void setMode(int paramInt)
    throws RemoteException;

  public abstract void toggleMode()
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IMusicPlayService
  {
    private static final String DESCRIPTOR = "com.xiami.service.IMusicPlayService";
    static final int TRANSACTION_deleteAllDownloadSong = 24;
    static final int TRANSACTION_deleteDownloadSong = 23;
    static final int TRANSACTION_downloadSong = 21;
    static final int TRANSACTION_downloadSongs = 22;
    static final int TRANSACTION_getDownloadingIndex = 19;
    static final int TRANSACTION_getDownloadingProgress = 20;
    static final int TRANSACTION_getLoadedProgress = 14;
    static final int TRANSACTION_getMode = 15;
    static final int TRANSACTION_getOfflineSongs = 18;
    static final int TRANSACTION_getPlayingSong = 16;
    static final int TRANSACTION_getRadioName = 4;
    static final int TRANSACTION_getTimePlay = 17;
    static final int TRANSACTION_getTimeTotal = 13;
    static final int TRANSACTION_isDownloading = 25;
    static final int TRANSACTION_isPlay = 12;
    static final int TRANSACTION_isRadio = 3;
    static final int TRANSACTION_pause = 10;
    static final int TRANSACTION_play = 1;
    static final int TRANSACTION_playNext = 5;
    static final int TRANSACTION_playPause = 9;
    static final int TRANSACTION_playPrev = 6;
    static final int TRANSACTION_playRadio = 2;
    static final int TRANSACTION_resumePlay = 11;
    static final int TRANSACTION_setMode = 8;
    static final int TRANSACTION_toggleMode = 7;

    public Stub()
    {
      attachInterface(this, "com.xiami.service.IMusicPlayService");
    }

    public static IMusicPlayService asInterface(IBinder paramIBinder)
    {
    	IMusicPlayService localObject = null;
      if (paramIBinder == null)
        localObject = null;
      else
      {
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.xiami.service.IMusicPlayService");
        if ((localIInterface != null) && ((localIInterface instanceof IMusicPlayService)))
        {
          localObject = (IMusicPlayService)localIInterface;
          
        } else {
//        	localObject = new asBinder().Proxy();
        }
      }
      return localObject;
    }

    public IBinder asBinder()
    {
      return this;
    }
    
    public Proxy asProxy() {
    	return new Proxy();
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 1;
      switch (paramInt1)
      {
      default:
        super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
    	  paramParcel2.writeString("com.xiami.service.IMusicPlayService");
    	  break;
      case 1:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
	      Parcelable.Creator localCreator1 = Song.CREATOR;
	      ArrayList localArrayList1 = paramParcel1.createTypedArrayList(localCreator1);
	      int k = paramParcel1.readInt();
	      play(localArrayList1, k);
	      paramParcel2.writeNoException();
	      break;
      case 2:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          Parcelable.Creator localCreator2 = Song.CREATOR;
          ArrayList localArrayList2 = paramParcel1.createTypedArrayList(localCreator2);
          String str1 = paramParcel1.readString();
          playRadio(localArrayList2, str1);
          paramParcel2.writeNoException();
          break;
      case 3:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          boolean bool1 = isRadio();
          paramParcel2.writeNoException();
          if (bool1) {
        	  paramParcel2.writeInt(i);
          }
          break;
      case 4:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          String str2 = getRadioName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          break;
      case 5:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          playNext();
          paramParcel2.writeNoException();
          break;
      case 6:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          playPrev();
          paramParcel2.writeNoException();
          break;
      case 7:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          toggleMode();
          paramParcel2.writeNoException();
          break;
      case 8:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int m = paramParcel1.readInt();
          setMode(m);
          paramParcel2.writeNoException();
          break;
      case 9:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          playPause();
          paramParcel2.writeNoException();
          break;
      case 10:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          pause();
          paramParcel2.writeNoException();
          break;
      case 11:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          resumePlay();
          paramParcel2.writeNoException();
          break;
      case 12:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          boolean bool2 = isPlay();
          paramParcel2.writeNoException();
          if (bool2) {
            paramParcel2.writeInt(i);
          }
          break;
      case 13:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int n = getTimeTotal();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(n);
          break;
      case 14:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int i1 = getLoadedProgress();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          break;
      case 15:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int i2 = getMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          break;
      case 16:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          Song localSong1 = getPlayingSong();
          paramParcel2.writeNoException();
          if (localSong1 != null)
          {
            paramParcel2.writeInt(1);
            localSong1.writeToParcel(paramParcel2, 1);
          }
          else
          {
            paramParcel2.writeInt(0);
          }
          break;
      case 17:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int i3 = getTimePlay();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          break;
      case 18:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          List localList = getOfflineSongs();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(localList);
          break;
      case 19:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int i4 = getDownloadingIndex();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i4);
          break;
      case 20:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          int i5 = getDownloadingProgress();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          break;
      case 21:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          if (paramParcel1.readInt() != 0);
          Song localSong2 = (Song)Song.CREATOR.createFromParcel(paramParcel1);
        int i6 = downloadSong(localSong2);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i6);
        break;
      case 22:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          Parcelable.Creator localCreator3 = Song.CREATOR;
          ArrayList localArrayList3 = paramParcel1.createTypedArrayList(localCreator3);
          int i7 = downloadSongs(localArrayList3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          break;
      case 23:
    	  paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          if (paramParcel1.readInt() != 0);
          localSong2 = (Song)Song.CREATOR.createFromParcel(paramParcel1);
            deleteDownloadSong(localSong2);
            paramParcel2.writeNoException();
            break;
      case 24:
          paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
          deleteAllDownloadSong();
          paramParcel2.writeNoException();
          break;
      case 25:
	      paramParcel1.enforceInterface("com.xiami.service.IMusicPlayService");
	      boolean bool3 = isDownloading();
	      paramParcel2.writeNoException();
	      if (bool3) {
	        paramParcel2.writeInt(i);
	      }
	      break;
    }
      return true;
    }

    class Proxy
      implements IMusicPlayService
    {
      Proxy()
      {
      }

      public IBinder asBinder()
      {
        return IMusicPlayService.Stub.this;
      }

      public void deleteAllDownloadSong()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void deleteDownloadSong(Song paramSong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          if (paramSong != null)
          {
            localParcel1.writeInt(1);
            paramSong.writeToParcel(localParcel1, 0);
            boolean bool = IMusicPlayService.Stub.this.transact(23, localParcel1, localParcel2, 0);
            localParcel2.readException();
          }
          else
          {
            int i = 0;
            localParcel1.writeInt(i);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int downloadSong(Song paramSong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          if (paramSong != null)
          {
            localParcel1.writeInt(1);
            paramSong.writeToParcel(localParcel1, 0);
            boolean bool = IMusicPlayService.Stub.this.transact(21, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            int j = i;
            return j;
          }
          else
          {
            int k = 0;
            localParcel1.writeInt(k);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        return 0;
      }

      public int downloadSongs(List<Song> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          localParcel1.writeTypedList(paramList);
          boolean bool = IMusicPlayService.Stub.this.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getDownloadingIndex()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getDownloadingProgress()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String getInterfaceDescriptor()
      {
        return "com.xiami.service.IMusicPlayService";
      }

      public int getLoadedProgress()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public List<Song> getOfflineSongs()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = Song.CREATOR;
          ArrayList localArrayList1 = localParcel2.createTypedArrayList(localCreator);
          ArrayList localArrayList2 = localArrayList1;
          return localArrayList2;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public Song getPlayingSong()
        throws RemoteException
      {
    	  Song localSong = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
        	  localSong = (Song)Song.CREATOR.createFromParcel(localParcel2);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        return localSong;
      }

      public String getRadioName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str1 = localParcel2.readString();
          String str2 = str1;
          return str2;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getTimePlay()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getTimeTotal()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          int j = i;
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean isDownloading()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
           	return true;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        return false;
      }

      public boolean isPlay()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            return true;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        return false;
      }

      public boolean isRadio()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
        	  return true;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        return false;
      }

      public void pause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void play(List<Song> paramList, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          localParcel1.writeTypedList(paramList);
          localParcel1.writeInt(paramInt);
          boolean bool = IMusicPlayService.Stub.this.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void playNext()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void playPause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void playPrev()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void playRadio(List<Song> paramList, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          localParcel1.writeTypedList(paramList);
          localParcel1.writeString(paramString);
          boolean bool = IMusicPlayService.Stub.this.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void resumePlay()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void setMode(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          localParcel1.writeInt(paramInt);
          boolean bool = IMusicPlayService.Stub.this.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void toggleMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.xiami.service.IMusicPlayService");
          boolean bool = IMusicPlayService.Stub.this.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
    }
  }
    }
  }
