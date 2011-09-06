package com.xiami;

import java.util.List;

public interface Musicable {

	 public abstract void deleteAllDownloadSong();

	  public abstract void deleteDownloadSong(Song paramSong);

	  public abstract void download(Song paramSong);

	  public abstract void download(List<Song> paramList);

	  public abstract int getDownloadingIndex();

	  public abstract int getDownloadingProgress();

	  public abstract int getLoadedProgress();

	  public abstract int getMode();

	  public abstract List<Song> getOfflineSongs();

	  public abstract Song getPlayingSong();

	  public abstract String getRadioName();

	  public abstract int getTimePlay();

	  public abstract int getTimeTotal();

	  public abstract boolean isPlay();

	  public abstract boolean isRadio();

	  public abstract void play(List<Song> paramList);

	  public abstract void play(List<Song> paramList, int paramInt);

	  public abstract void playAlbum(int paramInt);

	  public abstract void playArtist(int paramInt, String paramString);

	  public abstract void playCollect(int paramInt);

	  public abstract void playMyRadio();

	  public abstract void playNext();

	  public abstract void playPause();

	  public abstract void playPrev();

	  public abstract void playRadio(int paramInt, String paramString);

	  public abstract void playRndSongs();

	  public abstract void shufflePlay(List<Song> paramList);

	  public abstract void toggleMode();
}
