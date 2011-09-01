package com.rent.activitiy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rent.DetailImageManager;
import com.rent.Rent;
import com.rent.UIUtils;
import com.rent.data.DetailData;
import com.rent.db.DetailDBManager;
import com.rent.handler.DetailDataHandler;
import com.rent.listener.HandlerListener;
import com.rent.thread.DetailThread;
import com.rent.thread.GetDetailPictureThread;

public class SourceDetailActivity extends Activity
implements HandlerListener {

	public static final String DETAIL_DOWN_PIC_ERROR = "error";
	  public static final String DETAIL_DOWN_PIC_NAME_MSG_KEY = "pic_name";
	  public static final String DETAIL_DOWN_PIC_SUCCESS = "success";
	  public static final String DETAIL_IMAGES = "detail_images";
	  public static final String DETAIL_THUMBNAIL = "detail_thumbnail";
	  private Handler contactPahtImageDownload;
	  private ImageView mBackImageView;
	  private ImageView mCallPicImage;
	  private ImageView mChangeFavouriteStatusIV;
	  private String mCommunityAddress;
	  private ScrollView mContentScrollView;
	  private ProgressBar mContentScrollViewProgressBar;
	  private DetailData mDetailData;
	  private DetailDataHandler mDetailDataHandler;
	  private ImageView mDetailImagesView;
	  private long mOriginId;
	  private ArrayList<byte[]> mPhoneImageByteList;
	  private LinearLayout mPopLayout;
	  private ImageView mReturnMapIV;
	  private TextView mRourcePhoneNum;
	  private DetailImageManager mSDCardManager;

	  public SourceDetailActivity()
	  {
		  SourceDetailActivity5 local5 = new SourceDetailActivity5();
	    this.contactPahtImageDownload = local5;
	  }
	  
	  final class SourceDetailActivity5 extends Handler
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      super.handleMessage(paramMessage);
	      Object localObject;
	      /*try
	      {
	        ArrayList localArrayList1 = paramMessage.getData().getStringArrayList("pic_name");
	        SourceDetailActivity localSourceDetailActivity = this.this$0;
	        ArrayList localArrayList2 = new ArrayList();
	        ArrayList localArrayList3 = SourceDetailActivity.access$702(localSourceDetailActivity, localArrayList2);
	        localObject = localArrayList1.iterator();
	        while (((Iterator)localObject).hasNext())
	        {
	          String str = (String)((Iterator)localObject).next();
	          byte[] arrayOfByte1 = paramMessage.getData().getByteArray(str);
	          boolean bool = SourceDetailActivity.access$700(this.this$0).add(arrayOfByte1);
	        }
	      }
	      catch (Exception localException1)
	      {
	      }
	      while (true)
	      {
	        return;
	        byte[] arrayOfByte2 = (byte[])SourceDetailActivity.access$700(this.this$0).get(0);
	        SourceDetailActivity.access$800(this.this$0).setText("");
	        SourceDetailActivity.access$800(this.this$0).setVisibility(8);
	        localObject = (ImageView)this.this$0.findViewById(2131493186);
	        try
	        {
	          int i = arrayOfByte2.length;
	          Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte2, 0, i);
	          BitmapDrawable localBitmapDrawable = new BitmapDrawable(localBitmap);
	          ((ImageView)localObject).setImageDrawable(localBitmapDrawable);
	          ((ImageView)localObject).setVisibility(0);
	          label189: ImageView localImageView = SourceDetailActivity.access$1000(this.this$0);
	          SourceDetailActivity.5.1 local1 = new SourceDetailActivity.5.1(this, arrayOfByte2);
	          localImageView.setOnClickListener(local1);
	        }
	        catch (Exception localException2)
	        {
	          break label189;
	        }
	      }*/
	    }
	  }
	  
	  private void addDetailImageViewClickListener()
	  {
	    ImageView localImageView = this.mDetailImagesView;
	    localImageView.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
/*//	    	    MobclickAgent.onEvent(this.this$0, "clickroomimages");
	    	    SourceDetailActivity localSourceDetailActivity = this.this$0;
	    	    Intent localIntent1 = new Intent(localSourceDetailActivity, DetailGalleryActivity.class);
	    	    Bundle localBundle = new Bundle();
	    	    DetailData localDetailData = SourceDetailActivity.access$300(this.this$0);
	    	    localBundle.putSerializable("gallery_detail_data_key", localDetailData);
	    	    Intent localIntent2 = localIntent1.putExtras(localBundle);
	    	    this.this$0.startActivity(localIntent1);*/
	    	  }
		});
	  }

	  private void dealOriginId()
	  {
	    Intent localIntent = getIntent();
	    String str = localIntent.getStringExtra("address");
	    this.mCommunityAddress = str;
	    long l1 = localIntent.getLongExtra("origin_id", 0L);
	    this.mOriginId = l1;
	    if (this.mOriginId == 0L)
	      UIUtils.displayToast(this, 2131362002);
	    else {
	      if (this.mOriginId > 0L)
	      {
	        DetailDBManager localDetailDBManager = new DetailDBManager(this);
	        long l2 = this.mOriginId;
	        DetailData localDetailData1 = localDetailDBManager.getDetailDataByOriginId(l2);
	        this.mDetailData = localDetailData1;
	        localDetailDBManager.closeDb();
	        if (this.mDetailData != null)
	        {
	          DetailData localDetailData2 = this.mDetailData;
	          showRentDetail(localDetailData2);
	        } else if (!UIUtils.isNetworkAvailable(this))
	        {
	          UIUtils.displayToast(this, 2131361836);
	        } else {
	        	long l3 = this.mOriginId;
	        	downloadRentDetail(l3);
	        }
	      }
	    }
	  }

	  private void downPhoneNumPicture(String paramString)
	  {
	    if ((paramString != null) && (paramString.length() > 0))
	    {
	      HashMap localHashMap = new HashMap();
	      Object localObject = localHashMap.put("contact_path", paramString);
	      Handler localHandler = this.contactPahtImageDownload;
	      new GetDetailPictureThread(this, localHandler, localHashMap).start();
	    } else {
	      this.mCallPicImage.setVisibility(8);
	      this.mRourcePhoneNum.setVisibility(8);
	    }
	  }

	  private void downThumbnailAndShow()
	  {
	    ProgressBar localProgressBar = (ProgressBar)findViewById(2131493166);
	    localProgressBar.setVisibility(0);
	    String str = this.mDetailData.getmThumbnail();
	    new SourceDetailActivity3(str, localProgressBar).start();
	  }
	  
	  final class SourceDetailActivity3 extends Thread
	  {
		  private String str;
		  private ProgressBar localProgressBar;
		  public SourceDetailActivity3(String str, ProgressBar localProgressBar) {
			  this.str = str;
			  this.localProgressBar = localProgressBar;
		  }
	    public void run()
	    {
	      try
	      {
	        byte[] arrayOfByte = Rent.downLoadImage(str);
	        int i = arrayOfByte.length;
	        Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, i);
	        SourceDetailActivity31 local1 = new SourceDetailActivity31(localBitmap);
	        SourceDetailActivity.this.runOnUiThread(local1);
	      }
	      catch (Exception localException)
	      {
	          localException.printStackTrace();
	      }
	    }
	  }
	  
	  final class SourceDetailActivity31 implements Runnable
	{
		  private Bitmap localBitmap;
		  public SourceDetailActivity31(Bitmap localBitmap) {
			  this.localBitmap = localBitmap;
		  }
	  public void run()
	  {
	    /*SourceDetailActivity localSourceDetailActivity = this.this$1.this$0;
	    SourceDetailActivity.3.1.1 local1 = new SourceDetailActivity.3.1.1(this);
	    localSourceDetailActivity.runOnUiThread(local1);
	    DetailImageManager localDetailImageManager = SourceDetailActivity.access$600(this.this$1.this$0);
	    Bitmap localBitmap = this.val$thumbBmp;
	    StringBuilder localStringBuilder = new StringBuilder();
	    long l = SourceDetailActivity.access$000(this.this$1.this$0);
	    String str = l + "";
	    boolean bool = localDetailImageManager.saveThumbnailBitmap(localBitmap, "thumbnail.png", str);*/
	  }
	}
	  
	  private void downloadRentDetail(long paramLong)
	  {
	    DetailDataHandler localDetailDataHandler1 = new DetailDataHandler(this);
	    this.mDetailDataHandler = localDetailDataHandler1;
	    DetailDataHandler localDetailDataHandler2 = this.mDetailDataHandler;
	    new DetailThread(localDetailDataHandler2, paramLong).start();
	  }

	  private void fillPhoneNumber(DetailData paramDetailData)
	  {
	    TextView localTextView = (TextView)findViewById(2131493185);
	    this.mRourcePhoneNum = localTextView;
	    ImageView localImageView1 = (ImageView)findViewById(2131493187);
	    this.mCallPicImage = localImageView1;
	    Object localObject;
	    String str7;
	    if ((paramDetailData.mMasterNumber != null) && (paramDetailData.mMasterNumber.length() > 0) && (paramDetailData.mExtNumber != null) && (paramDetailData.mExtNumber.length() > 0))
	    {
	      StringBuilder localStringBuilder1 = new StringBuilder();
	      String str1 = paramDetailData.mMasterNumber;
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append("-");
	      String str2 = paramDetailData.mExtNumber;
	      String str3 = str2;
	      StringBuilder localStringBuilder3 = new StringBuilder();
	      String str4 = paramDetailData.mMasterNumber;
	      StringBuilder localStringBuilder4 = localStringBuilder3.append(str4).append(",");
	      String str5 = paramDetailData.mExtNumber;
	      String str6 = str5 + "#";
	      localObject = str3;
	      str7 = str6;
	      str7 = Uri.encode(str7);
	      if ((localObject == null) || (((String)localObject).length() <= 0)) {
	    	  str7 = paramDetailData.getmContactPath();
		      if (UIUtils.isNetworkAvailable(this))
		      {
		        this.mRourcePhoneNum.setText(2131362003);
		        downPhoneNumPicture(str7);
		      } else 
		    	  UIUtils.displayToast(this, 2131361836);
	      }
	      this.mRourcePhoneNum.setText((CharSequence)localObject);
	      ImageView localImageView2 = this.mCallPicImage;
	      localImageView2.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* if (this.val$dd.getmCalledTimes() > 0)
	    	    {
	    	      SourceDetailActivity localSourceDetailActivity1 = this.this$0;
	    	      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(localSourceDetailActivity1).setTitle(2131361821).setMessage(2131361819);
	    	      SourceDetailActivity.2.1 local1 = new SourceDetailActivity.2.1(this);
	    	      AlertDialog localAlertDialog1 = localBuilder1.setPositiveButton(2131361826, local1).setNegativeButton(2131361827, null).show();
	    	    }
	    	    while (true)
	    	    {
	    	      return;
	    	      SourceDetailActivity localSourceDetailActivity2 = this.this$0;
	    	      AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(localSourceDetailActivity2).setTitle(2131361821).setMessage(2131361820);
	    	      SourceDetailActivity.2.2 local2 = new SourceDetailActivity.2.2(this);
	    	      AlertDialog localAlertDialog2 = localBuilder2.setPositiveButton(2131361826, local2).setNegativeButton(2131361827, null).show();
	    	    }*/
	    	  }
		});
	    } else {
	      str7 = paramDetailData.getmPhone();
	      localObject = str7;
//	      label243: MobclickAgent.onEvent(this, "dialnumber", "picture");
	      
	    }
	  }

	  private void mChangeFavouriteStatusIVListener()
	  {
	    ImageView localImageView = this.mChangeFavouriteStatusIV;
	    localImageView.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    /*MobclickAgent.onEvent(this.this$0, "addfavoriteroom");
	    	    SourceDetailActivity.access$900(this.this$0);
	    	    SourceDetailActivity localSourceDetailActivity1 = this.this$0;
	    	    DetailDBManager localDetailDBManager = new DetailDBManager(localSourceDetailActivity1);
	    	    long l = SourceDetailActivity.access$000(this.this$0);
	    	    int i = localDetailDBManager.changeFavouriteStatusByOriginId(l);
	    	    String[] arrayOfString = this.this$0.getResources().getStringArray(2131165189);
	    	    if ((i == 4) || (i == 1))
	    	    {
	    	      SourceDetailActivity localSourceDetailActivity2 = this.this$0;
	    	      String str1 = arrayOfString[1];
	    	      UIUtils.displayToast(localSourceDetailActivity2, str1);
	    	      SourceDetailActivity.access$1100(this.this$0).setImageResource(2130837614);
	    	    }
	    	    while (true)
	    	    {
	    	      localDetailDBManager.closeDb();
	    	      return;
	    	      SourceDetailActivity localSourceDetailActivity3 = this.this$0;
	    	      String str2 = arrayOfString[0];
	    	      UIUtils.displayToast(localSourceDetailActivity3, str2);
	    	      SourceDetailActivity.access$1100(this.this$0).setImageResource(2130837698);
	    	    }*/
	    	  }
		});
	  }

	  private void returnMapButtonListener()
	  {
	    ImageView localImageView = this.mReturnMapIV;
	    localImageView.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
			}
		});
	  }

	  private void saveDetailCalledStatus(long paramLong)
	  {
	    saveToDB();
	    DetailDBManager localDetailDBManager = new DetailDBManager(this);
	    int i = localDetailDBManager.addCalledStatusByOriginId(paramLong);
	    localDetailDBManager.closeDb();
	  }

	  private void saveToDB()
	  {
	    try
	    {
	      DetailDBManager localDetailDBManager = new DetailDBManager(this);
	      long l = this.mOriginId;
	      if (localDetailDBManager.getDetailDataByOriginId(l) == null)
	      {
	        DetailData localDetailData = this.mDetailData;
	        boolean bool = localDetailDBManager.insertDetailData(localDetailData);
	      }
	      localDetailDBManager.closeDb();
	      label40: return;
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	    }
	  }

	  private boolean setFavouritePic()
	  {
	    DetailDBManager localDetailDBManager = new DetailDBManager(this);
	    long l = this.mOriginId;
	    int j = localDetailDBManager.queryFavouriteStatusByOriginId(l);
	    try {
		    if ((j == 4) || (j == 1))
		    {
		      this.mChangeFavouriteStatusIV.setImageResource(2130837614);
		    } else {
		        this.mChangeFavouriteStatusIV.setImageResource(2130837698);
		    }
	    }
	    finally
	    {
	      localDetailDBManager.closeDb();
	    }
	    return true;
	  }

	  private void showHasPicContent(DetailData paramDetailData)
	  {
	    ImageView localImageView = (ImageView)findViewById(2131493164);
	    this.mDetailImagesView = localImageView;
	    Object localObject = (TextView)findViewById(2131493167);
	    int i = paramDetailData.getmPrice();
	    if (i != 0)
	    {
	      String str2 = getResources().getString(2131361992);
	      Object[] arrayOfObject1 = new Object[1];
	      String str3 = "<font color=\"#aa651b\"><b>" + i + "</b></font>" + "";
	      arrayOfObject1[0] = str3;
	      Spanned localSpanned1 = Html.fromHtml(String.format(str2, arrayOfObject1));
	      ((TextView)localObject).setText(localSpanned1);
	    }
	    localObject = (TextView)findViewById(2131493168);
	    String str1 = paramDetailData.getmRoom();
	    if (str1 != null)
	    {
	      String str4 = getResources().getString(2131361993);
	      Object[] arrayOfObject2 = new Object[1];
	      arrayOfObject2[0] = str1;
	      String str5 = String.format(str4, arrayOfObject2);
	      ((TextView)localObject).setText(str5);
	    }
	    localObject = (TextView)findViewById(2131493169);
	    int j = paramDetailData.getmArea();
	    if (j != 0)
	    {
	      String str6 = getResources().getString(2131361994);
	      Object[] arrayOfObject3 = new Object[1];
	      String str7 = j + "";
	      arrayOfObject3[0] = str7;
	      String str8 = String.format(str6, arrayOfObject3);
	      Spanned localSpanned2 = Html.fromHtml(str8 + "m<sup><small>2</small></sup>");
	      ((TextView)localObject).setText(localSpanned2);
	    }
	    localObject = (TextView)findViewById(2131493170);
	    j = paramDetailData.getmRentType();
	    String str9 = getResources().getString(2131361995);
	    if (j == 0)
	    {
	      String str10 = getResources().getString(2131361936);
	      Object[] arrayOfObject4 = new Object[1];
	      arrayOfObject4[0] = str10;
	      String str11 = String.format(str9, arrayOfObject4);
	      ((TextView)localObject).setText(str11);
	    }
	    else
	    {
	      TextView localTextView = (TextView)findViewById(2131493171);
	      String str12 = paramDetailData.getmAgencyStatus();
	      String str13 = getResources().getString(2131361996);
	      Object[] arrayOfObject5 = new Object[1];
	      arrayOfObject5[0] = str12;
	      String str14 = String.format(str13, arrayOfObject5);
	      localTextView.setText(str14);
	      try
	      {
	        DetailImageManager localDetailImageManager = this.mSDCardManager;
	        StringBuilder localStringBuilder = new StringBuilder();
	        long l = this.mOriginId;
	        String str15 = l + "";
	        localObject = localDetailImageManager.getThumbnailBitmaps(str15);
	        if (((List)localObject).size() < 0)
	          downThumbnailAndShow();
	        else
	        {
	          if (1 != j) {
	          String str16 = getResources().getString(2131361937);
	          Object[] arrayOfObject6 = new Object[1];
	          arrayOfObject6[0] = str16;
	          String str17 = String.format(str9, arrayOfObject6);
	          ((TextView)localObject).setText(str17);
	          } else {
//	          break;
	          Bitmap localBitmap = (Bitmap)((List)localObject).get(j);
	          ProgressBar localProgressBar = (ProgressBar)findViewById(2131493166);
	          this.mDetailImagesView.setImageBitmap(localBitmap);
	          localProgressBar.setVisibility(8);
	          this.mDetailImagesView.setVisibility(0);
	          int k = this.mDetailData.getmImages().length;
	          showPopPromptImage(k);
	          addDetailImageViewClickListener();
	          }
	        }
	      }
	      catch (Exception localException)
	      {
	        while (true)
	          downThumbnailAndShow();
	      }
	    }
	  }

	  private void showNoPicContent(DetailData paramDetailData)
	  {
	    TextView localTextView1 = (TextView)findViewById(2131493173);
	    int i = paramDetailData.getmPrice();
	    if (i != 0)
	    {
	      String str2 = getResources().getString(2131361992);
	      Object[] arrayOfObject1 = new Object[1];
	      String str3 = "<font color=\"#aa651b\"><b>" + i + "</b></font>" + "";
	      arrayOfObject1[0] = str3;
	      Spanned localSpanned1 = Html.fromHtml(String.format(str2, arrayOfObject1));
	      localTextView1.setText(localSpanned1);
	    }
	    localTextView1 = (TextView)findViewById(2131493174);
	    String str1 = paramDetailData.getmRoom();
	    if (str1 != null)
	    {
	      String str4 = getResources().getString(2131361993);
	      Object[] arrayOfObject2 = new Object[1];
	      arrayOfObject2[0] = str1;
	      String str5 = String.format(str4, arrayOfObject2);
	      localTextView1.setText(str5);
	    }
	    localTextView1 = (TextView)findViewById(2131493175);
	    int j = paramDetailData.getmArea();
	    if (j != 0)
	    {
	      String str6 = getResources().getString(2131361994);
	      Object[] arrayOfObject3 = new Object[1];
	      String str7 = j + "";
	      arrayOfObject3[0] = str7;
	      String str8 = String.format(str6, arrayOfObject3);
	      Spanned localSpanned2 = Html.fromHtml(str8 + "m<sup><small>2</small></sup>");
	      localTextView1.setText(localSpanned2);
	    }
	    localTextView1 = (TextView)findViewById(2131493176);
	    j = paramDetailData.getmRentType();
	    String str9 = getResources().getString(2131361995);
	    if (j == 0)
	    {
	      String str10 = getResources().getString(2131361936);
	      Object[] arrayOfObject4 = new Object[1];
	      arrayOfObject4[0] = str10;
	      String str11 = String.format(str9, arrayOfObject4);
	      localTextView1.setText(str11);
	      
	      TextView localTextView2 = (TextView)findViewById(2131493177);
	      String str12 = paramDetailData.getmAgencyStatus();
	      String str13 = getResources().getString(2131361996);
	      Object[] arrayOfObject5 = new Object[1];
	      arrayOfObject5[0] = str12;
	      String str14 = String.format(str13, arrayOfObject5);
	      localTextView2.setText(str14);
	    }
	    else
	    {
	      if (1 != j) {
	      String str15 = getResources().getString(2131361937);
	      Object[] arrayOfObject6 = new Object[1];
	      arrayOfObject6[0] = str15;
	      String str16 = String.format(str9, arrayOfObject6);
	      localTextView1.setText(str16);
	      }
	    }
	  }

	  private void showPopPromptImage(int paramInt)
	  {
	    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131493165);
	    this.mPopLayout = localLinearLayout;
	    int i = 0;
	    while (i < paramInt)
	    {
	      ImageView localImageView = new ImageView(this);
	      ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
	      localImageView.setLayoutParams(localLayoutParams);
	      localImageView.setImageResource(2130837697);
	      localImageView.setPadding(5, 0, 5, 0);
	      this.mPopLayout.addView(localImageView);
	      i += 1;
	    }
	  }

	  private void showRentDetail(DetailData paramDetailData)
	  {
	    ProgressBar localProgressBar = (ProgressBar)findViewById(2131493182);
	    this.mContentScrollViewProgressBar = localProgressBar;
	    this.mContentScrollViewProgressBar.setVisibility(0);
	    ScrollView localScrollView = (ScrollView)findViewById(2131492906);
	    this.mContentScrollView = localScrollView;
	    this.mContentScrollView.setVisibility(8);
	    ImageView localImageView1 = (ImageView)findViewById(2131493159);
	    this.mChangeFavouriteStatusIV = localImageView1;
	    boolean bool = setFavouritePic();
	    mChangeFavouriteStatusIVListener();
	    ImageView localImageView2 = (ImageView)findViewById(2131493157);
	    this.mBackImageView = localImageView2;
	    ImageView localImageView3 = this.mBackImageView;
	    localImageView3.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    finish();
	    	  }
		});
	    ImageView localImageView4 = (ImageView)findViewById(2131493179);
	    this.mReturnMapIV = localImageView4;
	    returnMapButtonListener();
	    TextView localTextView1 = (TextView)findViewById(2131493160);
	    String str1 = paramDetailData.getmTitle();
	    if (str1 != null)
	      localTextView1.setText(str1);
	    localTextView1 = (TextView)findViewById(2131493178);
	    str1 = paramDetailData.getmAddress();
	    if (str1 != null)
	    {
	      String str2 = getResources().getString(2131361997);
	      Object[] arrayOfObject1 = new Object[1];
	      arrayOfObject1[0] = str1;
	      String str3 = String.format(str2, arrayOfObject1);
	      localTextView1.setText(str3);
	    }
	    localTextView1 = (TextView)findViewById(2131493180);
	    str1 = paramDetailData.getmAbstract();
	    if ((str1 != null) && (str1.length() > 0))
	    {
	      localTextView1.setText(str1);
	      localTextView1 = (TextView)findViewById(2131493161);
	      str1 = paramDetailData.getmPublishTime();
	      if (str1 != null)
	      {
	        String str4 = getResources().getString(2131361998);
	        long l = Long.valueOf(str1 + "000").longValue();
	        Date localDate = new Date(l);
	        String str5 = new SimpleDateFormat("MM/dd HH:mm").format(localDate);
	        Object[] arrayOfObject2 = new Object[1];
	        arrayOfObject2[0] = str5;
	        String str6 = String.format(str4, arrayOfObject2);
	        localTextView1.setText(str6);
	      }
	      localTextView1 = (TextView)findViewById(2131493162);
	      str1 = paramDetailData.getmFromSite();
	      if (str1 != null)
	      {
	        String str7 = getResources().getString(2131361999);
	        Object[] arrayOfObject3 = new Object[1];
	        arrayOfObject3[0] = str1;
	        String str8 = String.format(str7, arrayOfObject3);
	        localTextView1.setText(str8);
	      }
	      TextView localTextView2 = (TextView)findViewById(2131493181);
	      int i = paramDetailData.getmCalledTimes();
	      String str9 = getResources().getString(2131362000);
	      Object[] arrayOfObject4 = new Object[1];
	      String str10 = i + "";
	      arrayOfObject4[0] = str10;
	      String str11 = String.format(str9, arrayOfObject4);
	      localTextView2.setText(str11);
	      TextView localTextView3 = (TextView)findViewById(2131493184);
	      String str12 = paramDetailData.getmContactPerson();
	      localTextView3.setText(str12);
	      fillPhoneNumber(paramDetailData);
	      if ((paramDetailData.getmImages() == null) || (paramDetailData.getmImages().length <= 0) || (paramDetailData.getmImages()[0].length() <= 0))
	      {((LinearLayout)findViewById(2131493172)).setVisibility(0);
	      showNoPicContent(paramDetailData);
	      } else {
	      ((LinearLayout)findViewById(2131493163)).setVisibility(0);
	      showHasPicContent(paramDetailData);
	      }
	      this.mContentScrollViewProgressBar.setVisibility(8);
	      this.mContentScrollView.setVisibility(0);
	    }
	    else 
	    {
	      localTextView1.setText(2131362004);
	    }
	  }

	  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
	  {
	    super.onActivityResult(paramInt1, paramInt2, paramIntent);
	    if ((paramInt1 == 2) && (paramInt2 == 1) && (paramIntent.getBooleanExtra("is_called", false)))
	    {
	      DetailDBManager localDetailDBManager = new DetailDBManager(this);
	      long l = this.mOriginId;
	      int i = localDetailDBManager.addCalledStatusByOriginId(l);
	      localDetailDBManager.closeDb();
	    }
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903112);
	    DetailImageManager localDetailImageManager = new DetailImageManager();
	    this.mSDCardManager = localDetailImageManager;
	    dealOriginId();
//	    MobclickAgent.onEvent(this, "viewroomdetail");
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

	  public void statusChanged(boolean paramBoolean)
	  {
	    if (true == paramBoolean);
	    while (true)
	    {
	      try
	      {
	        DetailData localDetailData1 = (DetailData)this.mDetailDataHandler.getDetailDataList().get(0);
	        this.mDetailData = localDetailData1;
	        if ((this.mDetailData.getmAddress() != null) && (this.mDetailData.getmAddress().trim().length() != 0)){}
	        DetailData localDetailData2 = this.mDetailData;
	        String str = this.mCommunityAddress;
	        localDetailData2.setmAddress(str);
	        DetailData localDetailData3 = this.mDetailData;
	        showRentDetail(localDetailData3);
	      }
	      catch (Exception localException)
	      {
	        localException.printStackTrace();
	        continue;
	      }
	      UIUtils.displayLongTimeToast(this, 2131361891);
	    }
	  }
}
