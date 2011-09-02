package com.rent.activitiy;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rent.Rent;
import com.rent.UIUtils;

public class DialActivity extends Activity {

	private EditText mDialEditText;
	  private GridView mDialNumGV;
	  private LinearLayout mFinishLL;
	  private boolean mIsCalled = false;
	  private long mOriginId;
	  private ImageView mPhoneNumPic;
	  private PhoneVoteThread mPhoneVoteThread;

	  private void dialEditAppendChar(String paramString)
	  {
	    EditText localEditText = this.mDialEditText;
	    Editable localEditable = this.mDialEditText.getText().append(paramString);
	    localEditText.setText(localEditable);
	  }

	  private void dialEditDeleteChar()
	  {
	    if (this.mDialEditText.length() > 0)
	    {
	      EditText localEditText = this.mDialEditText;
	      Editable localEditable = this.mDialEditText.getText();
	      int i = this.mDialEditText.getText().length() - 1;
	      CharSequence localCharSequence = localEditable.subSequence(0, i);
	      localEditText.setText(localCharSequence);
	    }
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903058);
	    Intent localIntent = getIntent();
	    long l = localIntent.getLongExtra("id", 0L);
	    this.mOriginId = l;
	    byte[] arrayOfByte = localIntent.getByteArrayExtra("contact_path");
	    int i = 2131492987;
	    try
	    {
	      ImageView localImageView1 = (ImageView)findViewById(2131492987);
	      this.mPhoneNumPic = localImageView1;
	    }
	    catch (Exception localException1)
	    {
	      try
	      {
	        ImageView localImageView2 = this.mPhoneNumPic;
	        int j = arrayOfByte.length;
	        Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, j);
	        BitmapDrawable localBitmapDrawable = new BitmapDrawable(localBitmap);
	        localImageView2.setImageDrawable(localBitmapDrawable);
	        label111: i = 2131492983;
	        GridView localGridView1 = (GridView)findViewById(i);
	        this.mDialNumGV = localGridView1;
	        GridView localGridView2 = this.mDialNumGV;
	        DialAdapter localDialAdapter = new DialAdapter(this);
	        localGridView2.setAdapter(localDialAdapter);
	        EditText localEditText = (EditText)findViewById(2131492984);
	        this.mDialEditText = localEditText;
	        LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131492988);
	        this.mFinishLL = localLinearLayout1;
	        LinearLayout localLinearLayout2 = this.mFinishLL;
	        localLinearLayout2.setOnClickListener(new View.OnClickListener() {
	        	  public void onClick(View paramView)
	        	  {
	        		  finish();
	        	  }
			});
	      }
	      catch (Exception localException2)
	      {
	    	  localException2.printStackTrace();
	      }
	    }
	  }

	  protected void onPause()
	  {
	    super.onPause();
//	    MobclickAgent.onPause(this);
	  }

	  protected void onResume()
	  {
	    super.onResume();
//	    MobclickAgent.onResume(this);
	  }

	  class PhoneVoteThread extends Thread
	  {
	    private Context mContext;
	    private long mItemId;
	    private String mPhoneNumber;
	    private String mUrl;
	    private String mUuid;
	    private String voteURL = "http://api.99fang.com/core/1/phone_vote?channel=rent&item_id=%1d&uuid=%1s&phone=%2s";

	    public PhoneVoteThread(Context paramLong, long arg3, String arg5)
	    {
	      String str1 = ((TelephonyManager)DialActivity.this.getSystemService("phone")).getDeviceId();
	      this.mUuid = str1;
	      this.mContext = paramLong;
	      this.mItemId = 0;
	      this.mPhoneNumber = arg5;
	      String str2 = this.voteURL;
	      Object[] arrayOfObject = new Object[3];
	      Long localLong = Long.valueOf(this.mItemId);
	      arrayOfObject[0] = localLong;
	      String str3 = this.mUuid;
	      arrayOfObject[1] = str3;
	      String str4 = this.mPhoneNumber;
	      arrayOfObject[2] = str4;
	      String str5 = String.format(str2, arrayOfObject);
	      this.mUrl = str5;
	    }

	    private void sendURL(String paramString)
	      throws Exception
	    {
	      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
	      localHttpURLConnection.setRequestMethod("GET");
	      localHttpURLConnection.setConnectTimeout(5000);
	      InputStream localInputStream = localHttpURLConnection.getInputStream();
	      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
	      byte[] arrayOfByte = new byte[102400];
	      while (true)
	      {
	        int i = localInputStream.read(arrayOfByte);
	        if (i == -1)
	          break;
	        localByteArrayOutputStream.write(arrayOfByte, 0, i);
	      }
	      localByteArrayOutputStream.close();
	      localInputStream.close();
	    }

	    public void run()
	    {
	      if (Rent.canGetConnect(this.mContext));
	      try
	      {
	        String str = this.mUrl;
	        sendURL(str);
	        super.run();
	        return;
	      }
	      catch (Exception localException)
	      {
	        while (true)
	          localException.printStackTrace();
	      }
	    }
	  }

	  class DialAdapter extends BaseAdapter
	  {
	    private final char[] chars;
	    private Context mContext;

	    public DialAdapter(Context arg2)
	    {
	      char[] arrayOfChar = { };
	      this.chars = arrayOfChar;
	      Object localObject;
	      this.mContext = arg2;
	    }

	    public int getCount()
	    {
	      return this.chars.length;
	    }

	    public Object getItem(int paramInt)
	    {
	      return Character.valueOf(this.chars[paramInt]);
	    }

	    public long getItemId(int paramInt)
	    {
	      return paramInt;
	    }

	    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	    {
	      if (paramView == null)
	    	  return null;
	      View localView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130903098, null);
	        Button localButton = (Button)localView.findViewById(2131493121);
	        ImageButton localImageButton = (ImageButton)localView.findViewById(2131493122);
	        char c = this.chars[paramInt];
	        if ('b' == c)
	        {
	          localImageButton.setImageResource(2130837659);
	          localImageButton.setVisibility(0);
	          localImageButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
//					DialActivity.access$000(this.this$1.this$0);
					
				}
			});
	          localImageButton.setOnLongClickListener(new View.OnLongClickListener() {
				
				public boolean onLongClick(View v) {
					DialActivity.this.mDialEditText.setText("");
				    return false;
				}
			});
	          return localView;
	        }
	        else
	        {
	          
	          if ('d' == c)
	          {
	            localImageButton.setImageResource(2130837548);
	            localImageButton.setVisibility(0);
	            localImageButton.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						String str = DialActivity.this.mDialEditText.getText().toString();
					    if (PhoneNumberUtils.isGlobalPhoneNumber(str))
					    {
//					      MobclickAgent.onEvent(this.this$1.this$0, "dialnumber", "picture");
					      boolean bool1 = DialActivity.this.mIsCalled = true;
					      Uri localUri = Uri.parse("tel:" + str);
					      Intent localIntent1 = new Intent("android.intent.action.DIAL", localUri);
					      Intent localIntent2 = new Intent();
					      boolean bool2 = DialActivity.this.mIsCalled = false;
					      Intent localIntent3 = localIntent2.putExtra("is_called", bool2);
					      DialActivity.this.setResult(1, localIntent2);
					      long l = DialActivity.this.mOriginId;
					      DialActivity.PhoneVoteThread localPhoneVoteThread1 = new DialActivity.PhoneVoteThread(DialActivity.this, l, str);
//					      DialActivity.PhoneVoteThread localPhoneVoteThread2 = DialActivity.access$302(localDialActivity1, localPhoneVoteThread1);
					      localPhoneVoteThread1.start();
					      DialActivity.this.startActivity(localIntent1);
					      finish();
					    }
					    else 
					    {
					      UIUtils.displayToast(DialActivity.this, 2131362026);
					    }
						
					}
				});
	          } else {
		          String str1 = c + "";
		          localButton.setText(str1);
		          localButton.setVisibility(0);
		          String str2 = c + "";
		          localButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						/*DialActivity localDialActivity = this.this$1.this$0;
					    String str = this.val$p;
					    DialActivity.access$500(localDialActivity, str);*/
						
					}
				});
	          }
	        }
	        return localView;
	    }
	  }
	  
}
