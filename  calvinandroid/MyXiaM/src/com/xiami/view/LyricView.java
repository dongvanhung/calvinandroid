package com.xiami.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.xiami.Song;
import com.xiami.XiamiApp;
import com.xiami.file.FileUtil;

public class LyricView extends TextView {

	private XiamiApp app;
	  public int index = 0;
	  private List<Sentence> list;
	  private Paint mCurrPaint;
	  private Paint mPaint;
	  private int mX;
	  private int mY;
	  float margin;
	  float movedY = 0.0F;
	  int needMove = 0;
	  private int nextTimeFixLines = 0;
	  private Song song;
	  float startY;
	  private final int steps = 6;
	  private LoadLyricTask task;
	  private float textH;

	  public LyricView(Context paramContext)
	  {
	    super(paramContext);
	    init();
	  }

	  public LyricView(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	    init();
	  }

	  public LyricView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
	  {
	    super(paramContext, paramAttributeSet, paramInt);
	    init();
	  }

	  private void init()
	  {
	    setFocusable(true);
	    XiamiApp localXiamiApp = (XiamiApp)getContext().getApplicationContext();
	    this.app = localXiamiApp;
	    ArrayList localArrayList = new ArrayList();
	    this.list = localArrayList;
	    Paint localPaint1 = new Paint();
	    this.mPaint = localPaint1;
	    this.mPaint.setAntiAlias(true);
	    Paint localPaint2 = this.mPaint;
	    float f1 = 20;
	    localPaint2.setTextSize(f1);
	    Paint localPaint3 = this.mPaint;
	    Typeface localTypeface1 = Typeface.SERIF;
	    Typeface localTypeface2 = localPaint3.setTypeface(localTypeface1);
	    this.mPaint.setColor(-1);
	    Paint localPaint4 = this.mPaint;
	    Paint.Align localAlign1 = Paint.Align.CENTER;
	    localPaint4.setTextAlign(localAlign1);
	    float f2 = this.mPaint.measureText("M");
	    this.textH = f2;
	    float f3 = (float)(this.textH * 1.618D);
	    this.margin = f3;
	    Paint localPaint5 = new Paint();
	    this.mCurrPaint = localPaint5;
	    this.mCurrPaint.setAntiAlias(true);
	    Paint localPaint6 = this.mCurrPaint;
	    float f4 = 20;
	    localPaint6.setTextSize(f4);
	    Paint localPaint7 = this.mCurrPaint;
	    Typeface localTypeface3 = Typeface.DEFAULT_BOLD;
	    Typeface localTypeface4 = localPaint7.setTypeface(localTypeface3);
	    this.mCurrPaint.setColor(-39424);
	    Paint localPaint8 = this.mCurrPaint;
	    Paint.Align localAlign2 = Paint.Align.CENTER;
	    localPaint8.setTextAlign(localAlign2);
	  }

	  private void parseLine(String s)
	  {
		  if(!s.equals("")) {
			  Matcher matcher;
	          ArrayList arraylist = new ArrayList();
	          int i;
	          matcher = Pattern.compile("(?<=\\[).*?(?=\\])").matcher(s);
	          i = 0;
	          String s3 = matcher.group();
	          arraylist.add(s3);
	          int k;
	          k = s3.length() + 2;
	          i += k;
	          if(!matcher.find()) {
	              int j = s.length();
	              int obj;
	              String s1;
	              if(i > j)
	                  obj = s.length();
	              else
	                  obj = i;
	              s1 = s.substring(obj).trim();
	              if(!s1.equals(""))
	              {
	                  Iterator it = arraylist.iterator();
	                  while(it.hasNext()) 
	                  {
	                      String s2 = (String)it.next();
	                      long l = parseTime(s2);
	                      if(l != 65535L)
	                      {
	                          List list1 = list;
	                          Sentence sentence = new Sentence(s1, l);
	                          boolean flag = list1.add(sentence);
	                      }
	                  }
	              }
	          } else {
	        	  
	          }
		  }
	  }

	  private long parseTime(String s)
	  {
		  int l = 0;
		  long result = 0;
		  String as[] = s.split("\\:|\\.");
	        if(as.length >= 2) {
	        	if(as.length == 2)
		        {
		            l = 0;
		            int i = 0;
		            int k = 0;
		            try
		            {
		                i = Integer.parseInt(as[l]);
		                k = Integer.parseInt(as[1]);
		                if(i < 0 || k < 0 || k >= 60) {
		                	throw new RuntimeException("\u6570\u5B57\u4E0D\u5408\u6CD5!");
		                }
		            }
		            catch(Exception exception)
		            {
		            	exception.printStackTrace();
		            }
		            result = (long)(i * 60 + k) * 1000L;
		        } else if(as.length == 3)
		        {
		            l = 0;
		            int j = 0;
		            int i1 = 0;
		            int j1 = 0;
		            try
		            {
		                j = Integer.parseInt(as[l]);
		                i1 = Integer.parseInt(as[1]);
		                j1 = Integer.parseInt(as[2]);
		                if(j < 0 || i1 < 0 || i1 >= 60 || j1 < 0 || j1 > 99)
		                    throw new RuntimeException("\u6570\u5B57\u4E0D\u5408\u6CD5!");
		            }
		            catch(Exception exception1)
		            {
		            	exception1.printStackTrace();
		            }
		            long l1 = (long)(j * 60 + i1) * 1000L;
		            long l2 = j1 * 10;
		            result = l1 + l2;
		        } else
		        {
		            result = 65535L;
		        }
	        }
	        return result;
	  }

	  class LyricView1 implements Comparator<LyricView.Sentence>
	{
	  public int compare(LyricView.Sentence paramSentence1, LyricView.Sentence paramSentence2)
	  {
	    long l1 = paramSentence1.getFromTime();
	    long l2 = paramSentence2.getFromTime();
	    return (int)(l1 - l2);
	  }
	}
	  
	  private void setLyric(String paramString)
	  {
	    this.list.clear();
	    if ((paramString == null) || (paramString.trim().equals("")))
	    {
	      List localList1 = this.list;
	      String str1 = this.song.getName();
	      LyricView localLyricView1 = this;
	      Sentence localSentence1 = new Sentence(str1, -2147483648L, 2147483647L);
	      boolean bool1 = localList1.add(localSentence1);
	      List localList2 = this.list;
	      LyricView localLyricView2 = this;
	      Sentence localSentence2 = new Sentence("Test, -2147483648L, 2147483647L");
	      boolean bool2 = localList2.add(localSentence2);
	    }
	    else
	    {
	      StringReader localStringReader1 = new StringReader(paramString);
	      StringReader localStringReader2 = localStringReader1;
	      BufferedReader localBufferedReader = new BufferedReader(localStringReader1);
	      try
	      {
	    	  String str3 = "";
	        while ((str3 = localBufferedReader.readLine()) != null)
	        {
	            localBufferedReader.close();
	            List localList3 = this.list;
	            LyricView1 local11 = new LyricView1();
	            LyricView localLyricView3 = this;
//	            local12.<init>(localLyricView3);
	            Collections.sort(localList3, local11);
	            if (this.list.size() != 0)
	              break label298;
	            List localList4 = this.list;
	            String str4 = this.song.getName();
	            LyricView localLyricView4 = this;
	            Sentence localSentence3 = new Sentence(str4, 0L, 2147483647L);
	            boolean bool3 = localList4.add(localSentence3);
	            break;
	          }
	          String str5 = str3.trim();
	          LyricView localLyricView5 = this;
	          String str6 = str5;
	          localLyricView5.parseLine(str6);
	      }
	      catch (IOException l4)
	      {
	        while (true)
	          localIOException.printStackTrace();
	        label298: Sentence localSentence4 = (Sentence)this.list.get(0);
	        List localList5 = this.list;
	        String str7 = this.song.getName();
	        long l1 = localSentence4.getFromTime();
	        LyricView localLyricView6 = this;
	        Sentence localSentence5 = new Sentence(str7, 0L, l1);
	        localList5.add(0, localSentence5);
	        int k = this.list.size();
	        int m = 0;
	        while (true)
	        {
	          int n = m;
	          int i1 = k;
	          if (n >= i1)
	          {
	            if (this.list.size() != 1)
	              break label540;
	            Sentence localSentence6 = (Sentence)this.list.get(0);
	            long l2 = 2147483647L;
	            localSentence6.setToTime(l2);
	            break;
	          }
	          Sentence localSentence7 = null;
	          int i2 = m + 1;
	          int i3 = k;
	          if (i2 < i3)
	          {
	            List localList6 = this.list;
	            int i4 = m + 1;
	            localSentence7 = (Sentence)localList6.get(i4);
	          }
	          Sentence localSentence8 = (Sentence)this.list.get(m);
	          if (localSentence7 != null)
	          {
	            long l3 = localSentence7.getFromTime() - 1L;
	            Sentence localSentence9 = localSentence8;
	            long l4 = l3;
	            localSentence9.setToTime(l4);
	          }
	          m += 1;
	        }
	        label540: List localList7 = this.list;
	        int i5 = this.list.size() - 1;
	        ((Sentence)localList7.get(i5)).setToTime(2147483647L);
	      }
	    }
	  }

	  public void getLyric(Song paramSong)
	  {
	    int i = Log.d("going to get lyric");
	    this.list.clear();
	    if (paramSong == null);
	    while (true)
	    {
	      return;
	      this.song = paramSong;
	      float f = this.mY / 2;
	      this.startY = f;
	      if (this.task != null)
	      {
	        AsyncTask.Status localStatus1 = this.task.getStatus();
	        AsyncTask.Status localStatus2 = AsyncTask.Status.RUNNING;
	        if (localStatus1.equals(localStatus2))
	        {
	          int j = Log.d("task running");
	          continue;
	        }
	      }
	      List localList1 = this.list;
	      String str = paramSong.getName();
	      LyricView localLyricView1 = this;
	      Sentence localSentence1 = new Sentence(str, -2147483648L, 2147483647L);
	      boolean bool1 = localList1.add(localSentence1);
	      List localList2 = this.list;
	      LyricView localLyricView2 = this;
	      Sentence localSentence2 = new Sentence("å§�ï½…æ¹ªéŽ¼æ»…å‚¨å§�å²ƒç˜�...", -2147483648L, 2147483647L);
	      boolean bool2 = localList2.add(localSentence2);
	      LoadLyricTask localLoadLyricTask1 = new LoadLyricTask();
	      this.task = localLoadLyricTask1;
	      LoadLyricTask localLoadLyricTask2 = this.task;
	      Void[] arrayOfVoid = new Void[0];
	      AsyncTask localAsyncTask = localLoadLyricTask2.execute(arrayOfVoid);
	    }
	  }

	  protected void onDraw(Canvas paramCanvas)
	  {
	    super.onDraw(paramCanvas);
	    int i = this.index;
	    Paint localPaint1 = this.mPaint;
	    Paint localPaint2 = this.mCurrPaint;
	    float f1 = this.mX / 2;
	    float f2 = this.startY;
	    int j = this.nextTimeFixLines;
	    this.nextTimeFixLines = 0;
	    if (this.needMove > 0)
	    {
	      int k = this.needMove - 1;
	      this.needMove = k;
	      float f3 = this.startY;
	      float f4 = this.margin / 6.0F;
	      float f5 = f3 - f4;
	      this.startY = f5;
	    }
	    int m = this.list.size();
	    if ((i >= m) || (i < 0));
	    int n;
	    float f8;
	    do
	    {
	      return;
	      while (true)
	      {
	        n = 0;
	        if (n < m)
	          break;
	        if (this.needMove <= 0)
	          continue;
	        invalidate();
	      }
	      float f6 = this.mY;
	      float f7 = this.margin;
	      f8 = f6 - f7;
	    }
	    while (f2 > f8);
	    if (f2 < 0.0F)
	    {
	      float f9 = this.margin;
	      f2 += f9;
	    }
	    String[] arrayOfString;
	    while (true)
	    {
	      n += 1;
	      break;
	      arrayOfString = ((Sentence)this.list.get(n)).getSplits();
	      if (n <= i)
	      {
	        int i1 = this.nextTimeFixLines;
	        int i2 = arrayOfString.length - 1;
	        int i3 = Math.max(0, i2);
	        int i4 = i1 + i3;
	        this.nextTimeFixLines = i4;
	      }
	      if (n != i)
	        break label322;
	      i5 = arrayOfString.length;
	      i6 = 0;
	      while (i6 < i5)
	      {
	        String str1 = arrayOfString[i6];
	        paramCanvas.drawText(str1, f1, f2, localPaint2);
	        float f10 = this.margin;
	        f2 += f10;
	        i6 += 1;
	      }
	    }
	    label322: int i5 = arrayOfString.length;
	    int i6 = 0;
	    label330: String str2;
	    if (i6 < i5)
	    {
	      str2 = arrayOfString[i6];
	      if (j <= 0)
	        break label365;
	      j += -1;
	    }
	    while (true)
	    {
	      i6 += 1;
	      break label330;
	      break;
	      label365: paramCanvas.drawText(str2, f1, f2, localPaint1);
	      float f11 = this.margin;
	      f2 += f11;
	    }
	  }

	  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	  {
	    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
	    this.mX = paramInt1;
	    this.mY = paramInt2;
	    float f = this.mY / 2;
	    this.startY = f;
	  }

	  protected String[] splitText(String paramString)
	  {
	    int i = (int)(this.mX * 0.9D);
	    Paint localPaint = this.mCurrPaint;
	    ArrayList localArrayList = new ArrayList();
	    int j = paramString.length();
	    int k = 0;
	    while (true)
	    {
	      if (k >= j);
	      int m;
	      do
	      {
	        String[] arrayOfString = new String[localArrayList.size()];
	        return (String[])localArrayList.toArray(arrayOfString);
	        float f = i;
	        String str1 = paramString;
	        m = localPaint.breakText(str1, k, j, 1, f, null);
	      }
	      while (m == 0);
	      int n = k + m;
	      String str2 = paramString.substring(k, n);
	      boolean bool = localArrayList.add(str2);
	      k += m;
	    }
	  }

	  public void update(long paramLong)
	  {
	    int i = 0;
	    int j = this.list.size();
	    if (i >= j)
	    {
	      if (-1 <= -1)
	        break label191;
	      if (this.index != -1)
	      {
	        int k = this.needMove + 6;
	        this.needMove = k;
	        float f1 = this.startY;
	        float f2 = this.mY / 2;
	        float f3 = f1 - f2;
	        float f4 = -1;
	        float f5 = this.margin;
	        float f6 = f4 * f5;
	        if (Math.abs(f3 + f6) > 100.0F)
	        {
	          this.needMove = 0;
	          float f7 = this.mY / 2;
	          float f8 = -1;
	          float f9 = this.margin;
	          float f10 = f8 * f9;
	          float f11 = f7 - f10;
	          this.startY = f11;
	        }
	      }
	    }
	    label191: for (this.index = -1; ; this.index = -1)
	    {
	      invalidate();
	      return;
	      if (((Sentence)this.list.get(i)).isInTime(paramLong))
	        int m = i;
	      i += 1;
	      break;
	    }
	  }

	  public class LoadLyricTask extends AsyncTask<Void, Void, String>
	  {
	    public LoadLyricTask()
	    {
	    }

	    protected String doInBackground(Void[] paramArrayOfVoid)
	    {
	      FileUtil localFileUtil1 = LyricView.this.app.getFileUtil();
	      int i = LyricView.this.song.getSongId();
	      String str1 = localFileUtil1.getFileLrc(i);
	      if ((str1 == null) && (LyricView.this.song.getLrcFile() != null))
	      {
	        FileUtil localFileUtil2 = LyricView.this.app.getFileUtil();
	        String str2 = LyricView.this.song.getLrcFile();
	        int j = LyricView.this.song.getSongId();
	        str1 = localFileUtil2.getWebLrc(str2, j);
	      }
	      return str1;
	    }

	    protected void onPostExecute(String paramString)
	    {
	      int i = Log.d("onPostExecute");
	      LyricView.this.setLyric(paramString);
	    }
	  }

	  public class Sentence
	    implements Serializable
	  {
	    private static final long serialVersionUID = 20071125L;
	    private String content;
	    private long fromTime;
	    private String[] splits;
	    private long toTime;

	    public Sentence(String arg2)
	    {
	    }

	    public Sentence(String paramLong, long arg3)
	    {
	    }

	    public Sentence(String paramLong1, long arg3, long arg5)
	    {
	      String[] arrayOfString = LyricView.this.splitText(paramLong1);
	      this.splits = arrayOfString;
	      this.content = paramLong1;
	      this.fromTime = ???;
	      Object localObject;
	      this.toTime = localObject;
	    }

	    public String getContent()
	    {
	      return this.content;
	    }

	    public int getContentWidth(Paint paramPaint)
	    {
	      String str = this.content;
	      return Math.round(paramPaint.measureText(str));
	    }

	    public long getDuring()
	    {
	      long l1 = this.toTime;
	      long l2 = this.fromTime;
	      return l1 - l2;
	    }

	    public long getFromTime()
	    {
	      return this.fromTime;
	    }

	    public String[] getSplits()
	    {
	      return this.splits;
	    }

	    public int getSplitsLines()
	    {
	      return this.splits.length;
	    }

	    public long getToTime()
	    {
	      return this.toTime;
	    }

	    public boolean isInTime(long paramLong)
	    {
	      long l1 = this.fromTime;
	      if (paramLong >= l1)
	      {
	        long l2 = this.toTime;
	        if (paramLong > l2);
	      }
	      for (int i = 1; ; i = 0)
	        return i;
	    }

	    public void setFromTime(long paramLong)
	    {
	      this.fromTime = paramLong;
	    }

	    public void setToTime(long paramLong)
	    {
	      this.toTime = paramLong;
	    }

	    public String toString()
	    {
	      StringBuilder localStringBuilder1 = new StringBuilder("{");
	      long l1 = this.fromTime;
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(l1).append("(");
	      String str = this.content;
	      StringBuilder localStringBuilder3 = localStringBuilder2.append(str).append(")");
	      long l2 = this.toTime;
	      return l2 + "}";
	    }
	  }
}
