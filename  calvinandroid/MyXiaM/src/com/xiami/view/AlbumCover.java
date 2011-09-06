package com.xiami.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.xiami.XiamiApp;
import com.xiami.file.FileUtil;

public class AlbumCover extends ImageView {

	 private static int DEFAULT_COVER = 2130837529;
	  private XiamiApp app;
	  boolean isDraw = false;
//	  private LoadAlbumLogoTask loadingTask;
	  private int mAlbumId;
	  private String mPath;
	  private int vHeight;
	  private int vWidth;

	  public AlbumCover(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	    XiamiApp localXiamiApp = (XiamiApp)paramContext.getApplicationContext();
	    this.app = localXiamiApp;
	    new BitmapFactory.Options().inDensity = 160;
	  }

	  public static String getCoverFileName(int paramInt)
	  {
	    return "6_album_" + paramInt;
	  }

	  /*public void checkLoad(int paramInt, String paramString)
	  {
	    if (this.mAlbumId == paramInt);
	    while (true)
	    {
	      return;
	      load(paramInt, paramString);
	    }
	  }

	  public Bitmap createImage(Bitmap paramBitmap)
	  {
	    Object localObject;
	    if (paramBitmap == null)
	      localObject = null;
	    while (true)
	    {
	      return localObject;
	      int i = paramBitmap.getWidth();
	      int j = paramBitmap.getHeight();
	      if (this.vWidth == 0)
	      {
	        localObject = paramBitmap;
	        continue;
	      }
	      int k = this.vWidth * j;
	      int m = this.vHeight * i;
	      if (k > m)
	      {
	        int n = this.vHeight * i;
	        int i1 = this.vWidth;
	        int i2 = n / i1;
	        localObject = Bitmap.createBitmap(paramBitmap, 0, 0, i, i2, null, 0);
	        continue;
	      }
	      float f1 = i;
	      float f2 = j;
	      if (f1 / f2 > 2.0F)
	      {
	        int i3 = (int)(j * 1.5D);
	        paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, i3, j, null, 0);
	        i = paramBitmap.getWidth();
	        j = paramBitmap.getHeight();
	      }
	      Matrix localMatrix = new Matrix();
	      boolean bool = localMatrix.preScale(1.0F, -1.0F);
	      Bitmap localBitmap1 = paramBitmap;
	      int i4 = j;
	      Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, i, i4, localMatrix, 0);
	      int i5 = this.vHeight * i;
	      int i6 = this.vWidth;
	      int i7 = i5 / i6;
	      Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
	      Bitmap localBitmap3 = Bitmap.createBitmap(i, i7, localConfig);
	      Canvas localCanvas1 = new android/graphics/Canvas;
	      Canvas localCanvas2 = localCanvas1;
	      Bitmap localBitmap4 = localBitmap3;
	      localCanvas2.<init>(localBitmap4);
	      Canvas localCanvas3 = localCanvas1;
	      Bitmap localBitmap5 = paramBitmap;
	      float f3 = 0.0F;
	      float f4 = 0.0F;
	      Paint localPaint1 = null;
	      localCanvas3.drawBitmap(localBitmap5, f3, f4, localPaint1);
	      float f5 = j;
	      Canvas localCanvas4 = localCanvas1;
	      Bitmap localBitmap6 = localBitmap2;
	      float f6 = 0.0F;
	      float f7 = f5;
	      Paint localPaint2 = null;
	      localCanvas4.drawBitmap(localBitmap6, f6, f7, localPaint2);
	      Paint localPaint3 = new Paint();
	      float f8 = paramBitmap.getHeight();
	      float f9 = localBitmap3.getHeight();
	      Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
	      LinearGradient localLinearGradient1 = new LinearGradient(0.0F, f8, 0.0F, f9, 1895825407, 16777215, localTileMode);
	      Paint localPaint4 = localPaint3;
	      LinearGradient localLinearGradient2 = localLinearGradient1;
	      Shader localShader = localPaint4.setShader(localLinearGradient2);
	      PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
	      PorterDuffXfermode localPorterDuffXfermode1 = new PorterDuffXfermode(localMode);
	      Paint localPaint5 = localPaint3;
	      PorterDuffXfermode localPorterDuffXfermode2 = localPorterDuffXfermode1;
	      Xfermode localXfermode = localPaint5.setXfermode(localPorterDuffXfermode2);
	      float f10 = j;
	      float f11 = i;
	      float f12 = localBitmap3.getHeight();
	      Canvas localCanvas5 = localCanvas1;
	      Paint localPaint6 = localPaint3;
	      localCanvas5.drawRect(0.0F, f10, f11, f12, localPaint6);
	      paramBitmap.recycle();
	      localBitmap2.recycle();
	      localObject = localBitmap3;
	    }
	  }

	  public void load(int paramInt, String paramString)
	  {
	    if (this.mAlbumId == paramInt);
	    while (true)
	    {
	      return;
	      if (this.loadingTask != null)
	      {
	        AsyncTask.Status localStatus1 = this.loadingTask.getStatus();
	        AsyncTask.Status localStatus2 = AsyncTask.Status.RUNNING;
	        if ((localStatus1 == localStatus2) && (!this.loadingTask.isCancelled()))
	          boolean bool = this.loadingTask.cancel(1);
	      }
	      this.mAlbumId = paramInt;
	      this.mPath = paramString;
	      String str = getCoverFileName(this.mAlbumId);
	      if (this.app.getFileUtil().checkImg(str))
	      {
	        Bitmap localBitmap1 = this.app.getFileUtil().getLocalImage(str);
	        if (localBitmap1 != null)
	        {
	          Bitmap localBitmap2 = createImage(localBitmap1);
	          setImageBitmap(localBitmap2);
	          continue;
	        }
	        int i = DEFAULT_COVER;
	        setImageResource(i);
	        if (!this.app.checkNetwork())
	          continue;
	        LoadAlbumLogoTask localLoadAlbumLogoTask1 = new LoadAlbumLogoTask(paramInt, paramString);
	        this.loadingTask = localLoadAlbumLogoTask1;
	        LoadAlbumLogoTask localLoadAlbumLogoTask2 = this.loadingTask;
	        Void[] arrayOfVoid1 = new Void[0];
	        AsyncTask localAsyncTask1 = localLoadAlbumLogoTask2.execute(arrayOfVoid1);
	        continue;
	      }
	      int j = DEFAULT_COVER;
	      setImageResource(j);
	      if (!this.app.checkNetwork())
	        continue;
	      LoadAlbumLogoTask localLoadAlbumLogoTask3 = new LoadAlbumLogoTask(paramInt, paramString);
	      this.loadingTask = localLoadAlbumLogoTask3;
	      LoadAlbumLogoTask localLoadAlbumLogoTask4 = this.loadingTask;
	      Void[] arrayOfVoid2 = new Void[0];
	      AsyncTask localAsyncTask2 = localLoadAlbumLogoTask4.execute(arrayOfVoid2);
	    }
	  }

	  protected void onDraw(Canvas paramCanvas)
	  {
	    if (!this.isDraw)
	    {
	      int i = getWidth();
	      this.vWidth = i;
	      int j = getHeight();
	      this.vHeight = j;
	      reload();
	      this.isDraw = 1;
	    }
	    super.onDraw(paramCanvas);
	  }

	  public void reload()
	  {
	    if (this.loadingTask != null)
	    {
	      boolean bool = this.loadingTask.cancel(1);
	      this.loadingTask = null;
	    }
	    String str1 = getCoverFileName(this.mAlbumId);
	    if (this.app.getFileUtil().checkImg(str1))
	    {
	      Bitmap localBitmap1 = this.app.getFileUtil().getLocalImage(str1);
	      if (localBitmap1 != null)
	      {
	        Bitmap localBitmap2 = createImage(localBitmap1);
	        setImageBitmap(localBitmap2);
	      }
	    }
	    while (true)
	    {
	      return;
	      int i = DEFAULT_COVER;
	      setImageResource(i);
	      if (!this.app.checkNetwork())
	        continue;
	      int j = this.mAlbumId;
	      String str2 = this.mPath;
	      LoadAlbumLogoTask localLoadAlbumLogoTask1 = new LoadAlbumLogoTask(j, str2);
	      this.loadingTask = localLoadAlbumLogoTask1;
	      LoadAlbumLogoTask localLoadAlbumLogoTask2 = this.loadingTask;
	      Void[] arrayOfVoid1 = new Void[0];
	      AsyncTask localAsyncTask1 = localLoadAlbumLogoTask2.execute(arrayOfVoid1);
	      continue;
	      int k = DEFAULT_COVER;
	      setImageResource(k);
	      if (!this.app.checkNetwork())
	        continue;
	      int m = this.mAlbumId;
	      String str3 = this.mPath;
	      LoadAlbumLogoTask localLoadAlbumLogoTask3 = new LoadAlbumLogoTask(m, str3);
	      this.loadingTask = localLoadAlbumLogoTask3;
	      LoadAlbumLogoTask localLoadAlbumLogoTask4 = this.loadingTask;
	      Void[] arrayOfVoid2 = new Void[0];
	      AsyncTask localAsyncTask2 = localLoadAlbumLogoTask4.execute(arrayOfVoid2);
	    }
	  }

	  class LoadAlbumLogoTask extends AsyncTask<Void, Integer, Bitmap>
	  {
	    private int mAlbumId;
	    private String mCover;

	    public LoadAlbumLogoTask(int paramString, String arg3)
	    {
	      CharSequence localCharSequence;
	      if (localCharSequence == null)
	        boolean bool1 = cancel(1);
	      while (true)
	      {
	        return;
	        this.mAlbumId = paramString;
	        Matcher localMatcher = Pattern.compile(".*(_\\d{1})\\.[a-zA-Z]{1,5}").matcher(localCharSequence);
	        if ((!localMatcher.find()) || (localMatcher.groupCount() != 1))
	          boolean bool2 = cancel(0);
	        String str1 = localMatcher.group(1);
	        String str2 = localCharSequence.replaceFirst(str1, "_4");
	        this.mCover = str2;
	      }
	    }

	    protected Bitmap doInBackground(Void[] paramArrayOfVoid)
	    {
	      String str1 = AlbumCover.getCoverFileName(this.mAlbumId);
	      StringBuilder localStringBuilder = new StringBuilder("album cover loading:");
	      String str2 = this.mCover;
	      int i = Log.d(str2);
	      FileUtil localFileUtil = AlbumCover.this.app.getFileUtil();
	      String str3 = this.mCover;
	      return localFileUtil.getImg(str3, str1);
	    }

	    protected void onPostExecute(Bitmap paramBitmap)
	    {
	      if (paramBitmap != null)
	      {
	        Bitmap localBitmap = AlbumCover.this.createImage(paramBitmap);
	        AlbumCover.this.setImageBitmap(localBitmap);
	      }
	    }
	  }*/
}
