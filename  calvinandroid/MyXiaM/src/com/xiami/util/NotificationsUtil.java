package com.xiami.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.xiami.activity.MainActivity;

public class NotificationsUtil {

	public static final int NOTIFY_DOWNLOAD_COMPLETE = 3;
	  public static final int NOTIFY_PAUSE = 4;
	  public static final int NOTIFY_PLAYING = 1;
	  public static final int NOTIFY_UPDATE = 2;

	  public static void ToastLoadFail(Context paramContext)
	  {
	    ToastShort(paramContext, "Test");
	  }

	  public static void ToastLong(Context paramContext, String paramString)
	  {
	    Toast localToast = Toast.makeText(paramContext, paramString, 1);
	    localToast.setGravity(17, 0, 65526);
	    localToast.show();
	  }

	  public static void ToastShort(Context paramContext, String paramString)
	  {
	    Toast localToast = Toast.makeText(paramContext, paramString, 0);
	    localToast.setGravity(17, 0, 65526);
	    localToast.show();
	  }

	  public static Toast buildLoadingToast(Context paramContext)
	  {
	    Toast localToast = new Toast(paramContext);
	    TextView localTextView = new TextView(paramContext);
	    localTextView.setText("Test");
	    localTextView.setTextSize(25.0F);
	    localTextView.setTextColor(-1);
	    localTextView.setShadowLayer(1.0F, 2.0F, 2.0F, -16777216);
	    localToast.setView(localTextView);
	    localToast.setGravity(17, 0, 0);
	    localToast.setDuration(10);
	    return localToast;
	  }

	  public static void cancalNotify(Context paramContext, int paramInt)
	  {
	    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
	  }

	  public static void notifyNow(Context paramContext, String paramString1, CharSequence paramCharSequence, int paramInt, String paramString2)
	  {
	    long l = System.currentTimeMillis();
	    Notification localNotification = new Notification(2130837772, paramString1, l);
	    Intent localIntent1 = new Intent(paramContext, MainActivity.class);
	    Intent localIntent2 = localIntent1.setFlags(536870912);
	    Intent localIntent3 = localIntent1.setAction(paramString2);
	    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, localIntent1, 0);
	    localNotification.setLatestEventInfo(paramContext, paramString1, paramCharSequence, localPendingIntent);
	    ((NotificationManager)paramContext.getSystemService("notification")).notify(paramInt, localNotification);
	  }

	  public static void notifyOnGoing(Context paramContext, String paramString1, CharSequence paramCharSequence, int paramInt, String paramString2)
	  {
	    long l = System.currentTimeMillis();
	    Notification localNotification = new Notification(2130837772, paramString1, l);
	    int i = localNotification.flags | 0x22;
	    localNotification.flags = i;
	    Intent localIntent1 = new Intent(paramContext, MainActivity.class);
	    Intent localIntent2 = localIntent1.setFlags(536870912);
	    Intent localIntent3 = localIntent1.setAction(paramString2);
	    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, localIntent1, 0);
	    localNotification.setLatestEventInfo(paramContext, paramString1, paramCharSequence, localPendingIntent);
	    ((NotificationManager)paramContext.getSystemService("notification")).notify(paramInt, localNotification);
	  }
}
