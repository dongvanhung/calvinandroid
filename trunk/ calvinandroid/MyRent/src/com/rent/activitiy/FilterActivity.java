package com.rent.activitiy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rent.HouseFilter;
import com.rent.MobclickAgent;
import com.rent.PreferenceUtils;

public class FilterActivity extends Activity{

	private static final int DITANCE_LENGTH = 4;
	  private static final int ROOM_TYPE_NUMBER = 5;
	  private RadioButton mAgencyNoneConditionRadio;
	  private RadioButton mAgencyRadio;
	  private RadioGroup mAgencyRadioGroup;
	  private SeekBar mCellSeekBar;
	  private TextView mCellTextView;
	  private Context mContext;
	  private int[] mCurCitySeekBarPriceArray;
	  private int mCurHightPirce;
	  private int mCurLowPrice;
	  private Button mCustomButton;
	  private int mDistanceLength;
	  private SeekBar mDistanceSeekBar;
	  private TextView mDistanceTextView;
	  private ImageView mGotoBackView;
	  private boolean mIsAgency;
	  private boolean mIsPersonal;
	  private boolean mIsRentAll;
	  private boolean mIsRentPart;
	  private Button mOkButton;
	  private RadioButton mPersonalRadio;
	  private TextView mPriceSectionTextView;
	  private SeekBar mPriceSeekBar;
	  private int mPriceSeekBarLength;
	  private RadioButton mRentAllRadio;
	  private RadioButton mRentNoneConditionRadio;
	  private RadioButton mRentPartRadio;
	  private RadioGroup mRentRadioGroup;
	  private int mRoomNumber;

	  private void changeWidgetStatus()
	  {
	    int i = this.mPriceSeekBar.getMax();
	    Object localObject2 = null;
	    int j = this.mCurHightPirce;
	    Object localObject1;
	    if (-1 == j)
	    {
	      int k = this.mCurLowPrice;
	      if (-1 == k)
	      {
	        this.mPriceSeekBar.setProgress(0);
	        localObject1 = this.mContext.getString(2131361853);
	      }
	    }
	    while (true)
	    {
	      this.mPriceSectionTextView.setText((CharSequence)localObject1);
	      int m = this.mRoomNumber;
	      if (-1 == m)
	      {
	        this.mCellSeekBar.setProgress(0);
	        TextView localTextView1 = this.mCellTextView;
	        String str1 = this.mContext.getString(2131361853);
	        localTextView1.setText(str1);
	        int n = this.mDistanceLength;
	        if (-1 != n)
	          break label622;
	        this.mDistanceSeekBar.setProgress(0);
	        TextView localTextView2 = this.mDistanceTextView;
	        String str2 = this.mContext.getResources().getString(2131361853);
	        localTextView2.setText(str2);
	        if ((!this.mIsAgency) || (this.mIsPersonal))
	          break label708;
	        this.mAgencyRadio.setChecked(true);
	        if ((!this.mIsRentAll) || (this.mIsRentPart))
	          break label746;
	        this.mRentAllRadio.setChecked(true);
	        return;
	        int i1 = this.mCurHightPirce;
	        if (-1 == i1)
	        {
	          int i2 = this.mCurLowPrice;
	          if (-1 != i2)
	          {
	            try
	            {
	              this.mPriceSeekBar.setProgress(localObject1);
	              StringBuilder localStringBuilder1 = new StringBuilder();
	              int i3 = this.mCurLowPrice;
	              StringBuilder localStringBuilder2 = localStringBuilder1.append(i3);
	              String str3 = this.mContext.getString(2131362015);
	              str4 = str3;
	              localObject1 = str4;
	            }
	            catch (Exception localException3)
	            {
	              localException3.printStackTrace();
	              localObject1 = null;
	            }
	            continue;
	          }
	        }
	      }
	      label153: label176: label199: String str4;
	      try
	      {
	        while (true)
	        {
	          while (true)
	          {
	            int i8;
	            while (true)
	            {
	              StringBuilder localStringBuilder3 = new StringBuilder();
	              int i4 = this.mCurLowPrice;
	              StringBuilder localStringBuilder4 = localStringBuilder3.append(i4);
	              String str5 = this.mContext.getString(2131362014);
	              StringBuilder localStringBuilder5 = localStringBuilder4.append(str5);
	              int i5 = this.mCurHightPirce;
	              StringBuilder localStringBuilder6 = localStringBuilder5.append(i5);
	              String str6 = this.mContext.getString(2131361843);
	              str4 = str6;
	              localObject1 = str4;
	              try
	              {
	                localObject2 = getCurCityPriceArray();
	                int i6 = getCurCityPriceArray().length;
	                int i7 = 0;
	                i8 = 0;
	                while (true)
	                {
	                  int i9 = localObject2.length;
	                  if (i7 >= i9)
	                    break;
	                  int i10 = this.mCurLowPrice;
	                  int i11 = localObject2[i7];
	                  if (i10 == i11)
	                    i8 = i7;
	                  i7 += 1;
	                }
	                if (i8 >= i6)
	                  break;
	                SeekBar localSeekBar1 = this.mPriceSeekBar;
	                int i12 = i6 + 1;
	                localSeekBar1.setMax(i12);
	                if (this.mCurLowPrice != 0)
	                  break label512;
	                SeekBar localSeekBar2 = this.mPriceSeekBar;
	                int i13 = i8 + 1;
	                localSeekBar2.setProgress(i13);
	              }
	              catch (Exception localException2)
	              {
	                localObject2 = localObject1;
	                localObject1 = localException2;
	              }
	            }
	            label503: ((Exception)localObject1).printStackTrace();
	            localObject1 = localObject2;
	            break;
	            label512: SeekBar localSeekBar3 = this.mPriceSeekBar;
	            int i14 = i8 + 2;
	            localSeekBar3.setProgress(i14);
	            break;
	            try
	            {
	              SeekBar localSeekBar4 = this.mCellSeekBar;
	              int i15 = this.mRoomNumber;
	              localSeekBar4.setProgress(i15);
	              TextView localTextView3 = this.mCellTextView;
	              StringBuilder localStringBuilder7 = new StringBuilder();
	              int i16 = this.mRoomNumber;
	              StringBuilder localStringBuilder8 = localStringBuilder7.append(i16);
	              String str7 = this.mContext.getString(2131361868);
	              String str8 = str7;
	              localTextView3.setText(str8);
	            }
	            catch (Exception localException4)
	            {
	              localException4.printStackTrace();
	            }
	          }
	          break label105;
	          try
	          {
	            label622: SeekBar localSeekBar5 = this.mDistanceSeekBar;
	            int i17 = this.mDistanceLength - 1;
	            localSeekBar5.setProgress(i17);
	            String str9 = getResources().getString(2131362016);
	            TextView localTextView4 = this.mDistanceTextView;
	            StringBuilder localStringBuilder9 = new StringBuilder();
	            int i18 = this.mDistanceLength;
	            String str10 = i18 + str9;
	            localTextView4.setText(str10);
	          }
	          catch (Exception localException5)
	          {
	            localException5.printStackTrace();
	          }
	        }
	        break label153;
	        label708: if ((!this.mIsAgency) && (this.mIsPersonal))
	        {
	          this.mPersonalRadio.setChecked(1);
	          break label176;
	        }
	        this.mAgencyNoneConditionRadio.setChecked(1);
	        break label176;
	        label746: if ((!this.mIsRentAll) && (this.mIsRentPart))
	        {
	          this.mRentPartRadio.setChecked(1);
	          break label199;
	        }
	        this.mRentNoneConditionRadio.setChecked(1);
	      }
	      catch (Exception localException1)
	      {
	        break label503;
	      }
	    }
	  }

	  private int[] getCurCityPriceArray()
	  {
	    Object localObject = { 600, 1100, 1800, 2600, 3500, 5000, 9000 };
	    try
	    {
	      String str1 = PreferenceUtils.getCurrentCityName(this);
	      if ((str1 == null) || (str1.length() == 0))
	        str1 = this.mContext.getString(2131361852);
	      Context localContext = this.mContext;
	      AddressChoiceDBManager localAddressChoiceDBManager = new AddressChoiceDBManager(localContext);
	      localAddressChoiceDBManager.openDatabase();
	      int i = localAddressChoiceDBManager.getCityIdByCityName(str1);
	      String str2 = localAddressChoiceDBManager.getPriceByCity(i);
	      localAddressChoiceDBManager.closeDatabase();
	      int[] arrayOfInt = splitStrToIntArray(str2);
	      localObject = arrayOfInt;
	      return localObject;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        localException.printStackTrace();
	    }
	  }

	  private void setCityPriceSection()
	  {
	    SeekBar localSeekBar = this.mPriceSeekBar;
	    FilterActivity.8 local8 = new FilterActivity.8(this);
	    localSeekBar.setOnSeekBarChangeListener(local8);
	  }

	  private void setDistanceTextView(int paramInt)
	  {
	    String str1 = getResources().getString(2131362016);
	    int i = paramInt + 1;
	    this.mDistanceLength = i;
	    TextView localTextView = this.mDistanceTextView;
	    StringBuilder localStringBuilder1 = new StringBuilder();
	    int j = this.mDistanceLength;
	    String str2 = j + str1;
	    localTextView.setText(str2);
	    StringBuilder localStringBuilder2 = new StringBuilder().append("");
	    int k = this.mDistanceLength;
	    String str3 = k;
	    MobclickAgent.onEvent(this, str3);
	  }

	  private void setPriceTextView(int paramInt)
	  {
	    int i = this.mCurHightPirce;
	    String str3;
	    if (-1 == i)
	    {
	      int j = this.mCurLowPrice;
	      if (-1 != j)
	      {
	        StringBuilder localStringBuilder1 = new StringBuilder();
	        int k = this.mCurLowPrice;
	        StringBuilder localStringBuilder2 = localStringBuilder1.append(k);
	        String str1 = this.mContext.getString(2131361843);
	        StringBuilder localStringBuilder3 = localStringBuilder2.append(str1);
	        String str2 = this.mContext.getString(2131362015);
	        str3 = str2;
	      }
	    }
	    while (true)
	    {
	      this.mPriceSectionTextView.setText(str3);
	      return;
	      int m = this.mCurHightPirce;
	      if (-1 == m)
	      {
	        int n = this.mCurLowPrice;
	        if (-1 == n)
	        {
	          str3 = this.mContext.getString(2131361853);
	          continue;
	        }
	      }
	      StringBuilder localStringBuilder4 = new StringBuilder();
	      int i1 = this.mCurLowPrice;
	      StringBuilder localStringBuilder5 = localStringBuilder4.append(i1);
	      String str4 = this.mContext.getString(2131362014);
	      StringBuilder localStringBuilder6 = localStringBuilder5.append(str4);
	      int i2 = this.mCurHightPirce;
	      StringBuilder localStringBuilder7 = localStringBuilder6.append(i2);
	      String str5 = this.mContext.getString(2131361843);
	      str3 = str5;
	    }
	  }

	  private void setRoomNumTextView(int paramInt)
	  {
	    if (paramInt == 0)
	    {
	      this.mRoomNumber = -1;
	      TextView localTextView1 = this.mCellTextView;
	      String str1 = this.mContext.getString(2131361853);
	      localTextView1.setText(str1);
	    }
	    while (true)
	    {
	      return;
	      this.mRoomNumber = paramInt;
	      TextView localTextView2 = this.mCellTextView;
	      StringBuilder localStringBuilder1 = new StringBuilder();
	      int i = this.mRoomNumber;
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(i);
	      String str2 = this.mContext.getString(2131361868);
	      String str3 = str2;
	      localTextView2.setText(str3);
	    }
	  }

	  private void setValues(HouseFilter paramHouseFilter)
	  {
	    boolean bool1 = paramHouseFilter.ismIsAgency();
	    this.mIsAgency = bool1;
	    boolean bool2 = paramHouseFilter.ismIsPersonal();
	    this.mIsPersonal = bool2;
	    boolean bool3 = paramHouseFilter.ismIsRentAll();
	    this.mIsRentAll = bool3;
	    boolean bool4 = paramHouseFilter.ismIsRentPart();
	    this.mIsRentPart = bool4;
	    int i = paramHouseFilter.getmPriceHight();
	    this.mCurHightPirce = i;
	    int j = paramHouseFilter.getmPriceLow();
	    this.mCurLowPrice = j;
	    int k = paramHouseFilter.getmDistanceLength();
	    this.mDistanceLength = k;
	    if (this.mDistanceLength == -1)
	      this.mDistanceLength = 3;
	    int m = paramHouseFilter.getmRoomNumber();
	    this.mRoomNumber = m;
	  }

	  public static int[] splitStrToIntArray(String paramString)
	    throws Exception
	  {
	    if (paramString == null);
	    int[] arrayOfInt;
	    for (Object localObject = null; ; localObject = arrayOfInt)
	    {
	      return localObject;
	      localObject = paramString.split(",");
	      int i = localObject.length;
	      arrayOfInt = new int[i];
	      int j = 0;
	      while (j < i)
	      {
	        int k = Integer.valueOf(localObject[j]).intValue();
	        arrayOfInt[j] = k;
	        j += 1;
	      }
	    }
	  }

	  public HouseFilter getHouseFilter()
	  {
	    HouseFilter localHouseFilter = new HouseFilter();
	    int i = this.mDistanceLength;
	    localHouseFilter.setmDistanceLength(i);
	    boolean bool1 = this.mIsAgency;
	    localHouseFilter.setmIsAgency(bool1);
	    boolean bool2 = this.mIsPersonal;
	    localHouseFilter.setmIsPersonal(bool2);
	    boolean bool3 = this.mIsRentPart;
	    localHouseFilter.setmIsRentPart(bool3);
	    boolean bool4 = this.mIsRentAll;
	    localHouseFilter.setmIsRentAll(bool4);
	    int j = this.mCurHightPirce;
	    localHouseFilter.setmPriceHight(j);
	    int k = this.mCurLowPrice;
	    localHouseFilter.setmPriceLow(k);
	    int m = this.mRoomNumber;
	    localHouseFilter.setmRoomNumber(m);
	    return localHouseFilter;
	  }

	  public void init()
	  {
	    setContentView(2130903083);
	    RadioGroup localRadioGroup1 = (RadioGroup)findViewById(2131493062);
	    this.mRentRadioGroup = localRadioGroup1;
	    RadioButton localRadioButton1 = (RadioButton)findViewById(2131493065);
	    this.mRentPartRadio = localRadioButton1;
	    RadioButton localRadioButton2 = (RadioButton)findViewById(2131493064);
	    this.mRentAllRadio = localRadioButton2;
	    RadioButton localRadioButton3 = (RadioButton)findViewById(2131493063);
	    this.mRentNoneConditionRadio = localRadioButton3;
	    RadioGroup localRadioGroup2 = this.mRentRadioGroup;
	    FilterActivity.1 local1 = new FilterActivity.1(this);
	    localRadioGroup2.setOnCheckedChangeListener(local1);
	    RadioGroup localRadioGroup3 = (RadioGroup)findViewById(2131493058);
	    this.mAgencyRadioGroup = localRadioGroup3;
	    RadioButton localRadioButton4 = (RadioButton)findViewById(2131493060);
	    this.mAgencyRadio = localRadioButton4;
	    RadioButton localRadioButton5 = (RadioButton)findViewById(2131493061);
	    this.mPersonalRadio = localRadioButton5;
	    RadioButton localRadioButton6 = (RadioButton)findViewById(2131493059);
	    this.mAgencyNoneConditionRadio = localRadioButton6;
	    RadioGroup localRadioGroup4 = this.mAgencyRadioGroup;
	    FilterActivity.2 local2 = new FilterActivity.2(this);
	    localRadioGroup4.setOnCheckedChangeListener(local2);
	    ImageView localImageView1 = (ImageView)findViewById(2131493049);
	    this.mGotoBackView = localImageView1;
	    ImageView localImageView2 = this.mGotoBackView;
	    FilterActivity.3 local3 = new FilterActivity.3(this);
	    localImageView2.setOnClickListener(local3);
	    Button localButton1 = (Button)findViewById(2131493053);
	    this.mCustomButton = localButton1;
	    Button localButton2 = this.mCustomButton;
	    FilterActivity.4 local4 = new FilterActivity.4(this);
	    localButton2.setOnClickListener(local4);
	    Button localButton3 = (Button)findViewById(2131492879);
	    this.mOkButton = localButton3;
	    Button localButton4 = this.mOkButton;
	    FilterActivity.5 local5 = new FilterActivity.5(this);
	    localButton4.setOnClickListener(local5);
	    TextView localTextView1 = (TextView)findViewById(2131493051);
	    this.mPriceSectionTextView = localTextView1;
	    TextView localTextView2 = (TextView)findViewById(2131493054);
	    this.mCellTextView = localTextView2;
	    TextView localTextView3 = (TextView)findViewById(2131493056);
	    this.mDistanceTextView = localTextView3;
	    SeekBar localSeekBar1 = (SeekBar)findViewById(2131493052);
	    this.mPriceSeekBar = localSeekBar1;
	    SeekBar localSeekBar2 = (SeekBar)findViewById(2131493055);
	    this.mCellSeekBar = localSeekBar2;
	    this.mCellSeekBar.setMax(5);
	    SeekBar localSeekBar3 = this.mCellSeekBar;
	    FilterActivity.6 local6 = new FilterActivity.6(this);
	    localSeekBar3.setOnSeekBarChangeListener(local6);
	    SeekBar localSeekBar4 = (SeekBar)findViewById(2131493057);
	    this.mDistanceSeekBar = localSeekBar4;
	    this.mDistanceSeekBar.setMax(4);
	    SeekBar localSeekBar5 = this.mDistanceSeekBar;
	    FilterActivity.7 local7 = new FilterActivity.7(this);
	    localSeekBar5.setOnSeekBarChangeListener(local7);
	    setFileValues();
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    init();
	  }

	  protected void onPause()
	  {
	    super.onPause();
	    MobclickAgent.onPause(this);
	  }

	  protected void onResume()
	  {
	    super.onResume();
	    MobclickAgent.onResume(this);
	  }

	  public void readSharePreference()
	  {
	    HouseFilter localHouseFilter = PreferenceUtils.getCurrentHouseFilter(this.mContext);
	    setValues(localHouseFilter);
	    changeWidgetStatus();
	  }

	  public void saveSettingToSharePreference()
	  {
	    SharedPreferences.Editor localEditor1 = this.mContext.getSharedPreferences("filterSearchView", 0).edit();
	    int i = this.mCurHightPirce;
	    SharedPreferences.Editor localEditor2 = localEditor1.putInt("price_upper", i);
	    int j = this.mCurLowPrice;
	    SharedPreferences.Editor localEditor3 = localEditor1.putInt("price_low", j);
	    int k = this.mRoomNumber;
	    SharedPreferences.Editor localEditor4 = localEditor1.putInt("room_number", k);
	    int m = this.mDistanceLength;
	    SharedPreferences.Editor localEditor5 = localEditor1.putInt("distance_length", m);
	    boolean bool1 = this.mIsAgency;
	    SharedPreferences.Editor localEditor6 = localEditor1.putBoolean("is_agency", bool1);
	    boolean bool2 = this.mIsPersonal;
	    SharedPreferences.Editor localEditor7 = localEditor1.putBoolean("is_personal", bool2);
	    boolean bool3 = this.mIsRentAll;
	    SharedPreferences.Editor localEditor8 = localEditor1.putBoolean("rent_all", bool3);
	    boolean bool4 = this.mIsRentPart;
	    SharedPreferences.Editor localEditor9 = localEditor1.putBoolean("rent_part", bool4);
	    boolean bool5 = localEditor1.commit();
	  }

	  public void setFileValues()
	  {
	    setCityPriceSection();
	    readSharePreference();
	  }

	  class CustomInputDialog extends Dialog
	  {
	    private Button canclebutton;
	    private EditText hightEditText;
	    private EditText lowEditText;
	    private Button okButton;

	    public CustomInputDialog(Context arg2)
	    {
	      super();
	    }

	    private void clearEditText()
	    {
	      if ((this.hightEditText != null) && (this.lowEditText != null))
	      {
	        this.hightEditText.setText("");
	        this.lowEditText.setText("");
	      }
	    }

	    protected void onCreate(Bundle paramBundle)
	    {
	      super.onCreate(paramBundle);
	      setContentView(2130903055);
	      String str = FilterActivity.this.mContext.getString(2131362017);
	      setTitle(str);
	      EditText localEditText1 = (EditText)findViewById(2131492968);
	      this.lowEditText = localEditText1;
	      EditText localEditText2 = (EditText)findViewById(2131492969);
	      this.hightEditText = localEditText2;
	      Button localButton1 = (Button)findViewById(2131492970);
	      this.okButton = localButton1;
	      Button localButton2 = (Button)findViewById(2131492971);
	      this.canclebutton = localButton2;
	      Button localButton3 = this.canclebutton;
	      FilterActivity.CustomInputDialog.1 local1 = new FilterActivity.CustomInputDialog.1(this);
	      localButton3.setOnClickListener(local1);
	      Button localButton4 = this.okButton;
	      FilterActivity.CustomInputDialog.2 local2 = new FilterActivity.CustomInputDialog.2(this);
	      localButton4.setOnClickListener(local2);
	    }
	  }
	  
}
