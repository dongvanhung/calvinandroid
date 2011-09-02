package com.rent.activitiy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.rent.HouseFilter;
import com.rent.PreferenceUtils;
import com.rent.R;
import com.rent.db.AddressChoiceDBManager;

public class FilterActivity extends Activity{

	private static final int DITANCE_LENGTH = 4;
	  private static final int ROOM_TYPE_NUMBER = 5;
	  private RadioButton mAgencyNoneConditionRadio;
	  private RadioButton mAgencyRadio;
	  private RadioGroup mAgencyRadioGroup;
	  private SeekBar mCellSeekBar;
	  private TextView mCellTextView;
	  private Context mContext = this;
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
	    String localObject2 = null;
	    int j = this.mCurHightPirce;
	    String localObject1 = "";
	    if (-1 == j)
	    {
	      int k = this.mCurLowPrice;
	      if (-1 == k)
	      {
	        this.mPriceSeekBar.setProgress(0);
	        localObject1 = this.mContext.getString(R.string.none_condition);
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
	        String str1 = this.mContext.getString(R.string.none_condition);
	        localTextView1.setText(str1);
	        int n = this.mDistanceLength;
	        if (-1 != n)
	          break;
	        this.mDistanceSeekBar.setProgress(0);
	        TextView localTextView2 = this.mDistanceTextView;
	        String str2 = this.mContext.getResources().getString(R.string.none_condition);
	        localTextView2.setText(str2);
	        if ((!this.mIsAgency) || (this.mIsPersonal))
	          break;
	        this.mAgencyRadio.setChecked(true);
	        if ((!this.mIsRentAll) || (this.mIsRentPart))
	          break;
	        this.mRentAllRadio.setChecked(true);
	        int i1 = this.mCurHightPirce;
	        if (-1 == i1)
	        {
	          int i2 = this.mCurLowPrice;
	          if (-1 != i2)
	          {
	            try
	            {
	              this.mPriceSeekBar.setProgress(this.mCurLowPrice);
	              StringBuilder localStringBuilder1 = new StringBuilder();
	              int i3 = this.mCurLowPrice;
	              StringBuilder localStringBuilder2 = localStringBuilder1.append(i3);
	              String str3 = this.mContext.getString(R.string.siftsearch_price_section_max_price);
	            }
	            catch (Exception localException3)
	            {
	              localException3.printStackTrace();
	            }
	          }
	        }
	      }
	      String str4;
	      try
	      {
	            int i8 = 0;
	              StringBuilder localStringBuilder3 = new StringBuilder();
	              int i4 = this.mCurLowPrice;
	              StringBuilder localStringBuilder4 = localStringBuilder3.append(i4);
	              String str5 = this.mContext.getString(R.string.siftsearch_price_section_separator);
	              StringBuilder localStringBuilder5 = localStringBuilder4.append(str5);
	              int i5 = this.mCurHightPirce;
	              StringBuilder localStringBuilder6 = localStringBuilder5.append(i5);
	              String str6 = this.mContext.getString(R.string.unit_yuan);
	              str4 = str6;
	              localObject1 = str4;
	              try
	              {
	                int i6 = getCurCityPriceArray().length;
	                int i7 = 0;
	                while (true)
	                {
	                  int i9 = getCurCityPriceArray().length;
	                  if (i7 >= i9)
	                    break;
	                  int i10 = this.mCurLowPrice;
	                  int i11 = getCurCityPriceArray()[i7];
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
	                  break;
	                SeekBar localSeekBar2 = this.mPriceSeekBar;
	                int i13 = i8 + 1;
	                localSeekBar2.setProgress(i13);
	              }
	              catch (Exception localException2)
	              {
	              }
	            localObject1 = localObject2;
	            SeekBar localSeekBar3 = this.mPriceSeekBar;
	            int i14 = i8 + 2;
	            localSeekBar3.setProgress(i14);
	            try
	            {
	              SeekBar localSeekBar4 = this.mCellSeekBar;
	              int i15 = this.mRoomNumber;
	              localSeekBar4.setProgress(i15);
	              TextView localTextView3 = this.mCellTextView;
	              StringBuilder localStringBuilder7 = new StringBuilder();
	              int i16 = this.mRoomNumber;
	              StringBuilder localStringBuilder8 = localStringBuilder7.append(i16);
	              String str7 = this.mContext.getString(R.string.string_room);
	              String str8 = str7;
	              localTextView3.setText(str8);
	            }
	            catch (Exception localException4)
	            {
	              localException4.printStackTrace();
	            }
	          try
	          {
	            SeekBar localSeekBar5 = this.mDistanceSeekBar;
	            int i17 = this.mDistanceLength - 1;
	            localSeekBar5.setProgress(i17);
	            String str9 = getResources().getString(R.string.siftsearch_distance_length_units);
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
	        if ((!this.mIsAgency) && (this.mIsPersonal))
	        {
	          this.mPersonalRadio.setChecked(true);
	        }
	        this.mAgencyNoneConditionRadio.setChecked(true);
	        if ((!this.mIsRentAll) && (this.mIsRentPart))
	        {
	          this.mRentPartRadio.setChecked(true);
	        }
	        this.mRentNoneConditionRadio.setChecked(true);
	      }
	      catch (Exception localException1)
	      {
	      }
	    }
	  }

	  private int[] getCurCityPriceArray()
	  {
	    int[] localObject = { 600, 1100, 1800, 2600, 3500, 5000, 9000 };
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
	    FilterActivity8 local8 = new FilterActivity8();
	    localSeekBar.setOnSeekBarChangeListener(local8);
	  }
	  
	  final class FilterActivity8 implements OnSeekBarChangeListener
	{
	  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
	  {
	    /*if (paramInt == 0);
	    try
	    {
	      int i = FilterActivity.access$1102(this.this$0, -1);
	      int j = FilterActivity.access$1202(this.this$0, -1);
	      while (true)
	      {
	        FilterActivity.access$2100(this.this$0, paramInt);
	        return;
	        if (paramInt != 1)
	          break;
	        int k = FilterActivity.access$1102(this.this$0, 0);
	        FilterActivity localFilterActivity1 = this.this$0;
	        int[] arrayOfInt1 = FilterActivity.access$1700(this.this$0);
	        int m = paramInt - 1;
	        int n = arrayOfInt1[m];
	        int i1 = FilterActivity.access$1202(localFilterActivity1, n);
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        continue;
	        int i2 = FilterActivity.access$1900(this.this$0) + 1;
	        if (paramInt == i2)
	        {
	          FilterActivity localFilterActivity2 = this.this$0;
	          int[] arrayOfInt2 = FilterActivity.access$1700(this.this$0);
	          int i3 = paramInt - 2;
	          int i4 = arrayOfInt2[i3];
	          int i5 = FilterActivity.access$1102(localFilterActivity2, i4);
	          int i6 = FilterActivity.access$1202(this.this$0, -1);
	          continue;
	        }
	        FilterActivity localFilterActivity3 = this.this$0;
	        int[] arrayOfInt3 = FilterActivity.access$1700(this.this$0);
	        int i7 = paramInt - 2;
	        int i8 = arrayOfInt3[i7];
	        int i9 = FilterActivity.access$1102(localFilterActivity3, i8);
	        FilterActivity localFilterActivity4 = this.this$0;
	        int[] arrayOfInt4 = FilterActivity.access$1700(this.this$0);
	        int i10 = paramInt - 1;
	        int i11 = arrayOfInt4[i10];
	        int i12 = FilterActivity.access$1202(localFilterActivity4, i11);
	      }
	    }*/
	  }

	  public void onStartTrackingTouch(SeekBar paramSeekBar)
	  {
	    /*try
	    {
	      FilterActivity localFilterActivity1 = this.this$0;
	      int[] arrayOfInt1 = FilterActivity.access$1800(this.this$0);
	      int[] arrayOfInt2 = FilterActivity.access$1702(localFilterActivity1, arrayOfInt1);
	      FilterActivity localFilterActivity2 = this.this$0;
	      int i = FilterActivity.access$1700(this.this$0).length;
	      int j = FilterActivity.access$1902(localFilterActivity2, i);
	      SeekBar localSeekBar = FilterActivity.access$2000(this.this$0);
	      int k = FilterActivity.access$1900(this.this$0) + 1;
	      localSeekBar.setMax(k);
	      label72: return;
	    }
	    catch (Exception localException)
	    {
	      break label72;
	    }*/
	  }

	  public void onStopTrackingTouch(SeekBar paramSeekBar)
	  {
	  }
	}
	  
	  private void setDistanceTextView(int paramInt)
	  {
	    String str1 = getResources().getString(R.string.siftsearch_distance_length_units);
	    int i = paramInt + 1;
	    this.mDistanceLength = i;
	    TextView localTextView = this.mDistanceTextView;
	    StringBuilder localStringBuilder1 = new StringBuilder();
	    int j = this.mDistanceLength;
	    String str2 = j + str1;
	    localTextView.setText(str2);
	    StringBuilder localStringBuilder2 = new StringBuilder().append("");
	    int k = this.mDistanceLength;
//	    String str3 = k;
	   // MobclickAgent.onEvent(this, str3);
	  }

	  private void setPriceTextView(int paramInt)
	  {
	    int i = this.mCurHightPirce;
	    String str3 = "";
	    if (-1 == i)
	    {
	      int j = this.mCurLowPrice;
	      if (-1 != j)
	      {
	        StringBuilder localStringBuilder1 = new StringBuilder();
	        int k = this.mCurLowPrice;
	        StringBuilder localStringBuilder2 = localStringBuilder1.append(k);
	        String str1 = this.mContext.getString(R.string.unit_yuan);
	        StringBuilder localStringBuilder3 = localStringBuilder2.append(str1);
	        String str2 = this.mContext.getString(R.string.siftsearch_price_section_max_price);
	        str3 = str2;
	        this.mPriceSectionTextView.setText(str3);
	      }
	    } else {
	      int m = this.mCurHightPirce;
	      if (-1 == m)
	      {
	        int n = this.mCurLowPrice;
	        if (-1 == n)
	        {
	          str3 = this.mContext.getString(R.string.none_condition);
	        }
	      }
	      StringBuilder localStringBuilder4 = new StringBuilder();
	      int i1 = this.mCurLowPrice;
	      StringBuilder localStringBuilder5 = localStringBuilder4.append(i1);
	      String str4 = this.mContext.getString(R.string.siftsearch_price_section_separator);
	      StringBuilder localStringBuilder6 = localStringBuilder5.append(str4);
	      int i2 = this.mCurHightPirce;
	      StringBuilder localStringBuilder7 = localStringBuilder6.append(i2);
	      String str5 = this.mContext.getString(R.string.unit_yuan);
	      str3 = str5;
	    }
	  }

	  private void setRoomNumTextView(int paramInt)
	  {
	    if (paramInt == 0)
	    {
	      this.mRoomNumber = -1;
	      TextView localTextView1 = this.mCellTextView;
	      String str1 = this.mContext.getString(R.string.none_condition);
	      localTextView1.setText(str1);
	    } else {
	      this.mRoomNumber = paramInt;
	      TextView localTextView2 = this.mCellTextView;
	      StringBuilder localStringBuilder1 = new StringBuilder();
	      int i = this.mRoomNumber;
	      StringBuilder localStringBuilder2 = localStringBuilder1.append(i);
	      String str2 = this.mContext.getString(R.string.string_room);
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
	      String[] localObject = paramString.split(",");
	      int i = localObject.length;
	      arrayOfInt = new int[i];
	      int j = 0;
	      while (j < i)
	      {
	        int k = Integer.valueOf(localObject[j]).intValue();
	        arrayOfInt[j] = k;
	        j += 1;
	      }
	      return arrayOfInt;
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
	    setContentView(R.layout.filtersearch_view);
	    RadioGroup localRadioGroup1 = (RadioGroup)findViewById(R.id.rent_type_group);
	    this.mRentRadioGroup = localRadioGroup1;
	    RadioButton localRadioButton1 = (RadioButton)findViewById(R.id.rent_part);
	    this.mRentPartRadio = localRadioButton1;
	    RadioButton localRadioButton2 = (RadioButton)findViewById(R.id.rent_all);
	    this.mRentAllRadio = localRadioButton2;
	    RadioButton localRadioButton3 = (RadioButton)findViewById(R.id.rent_none_condition);
	    this.mRentNoneConditionRadio = localRadioButton3;
	    RadioGroup localRadioGroup2 = this.mRentRadioGroup;
	    localRadioGroup2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
	    	public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
	    	  {
	    	    /*int i = FilterActivity.access$000(this.this$0).getId();
	    	    if (paramInt == i)
	    	    {
	    	      boolean bool1 = FilterActivity.access$102(this.this$0, 0);
	    	      boolean bool2 = FilterActivity.access$202(this.this$0, 1);
	    	    }
	    	    while (true)
	    	    {
	    	      return;
	    	      int j = FilterActivity.access$300(this.this$0).getId();
	    	      if (paramInt == j)
	    	      {
	    	        boolean bool3 = FilterActivity.access$102(this.this$0, 1);
	    	        boolean bool4 = FilterActivity.access$202(this.this$0, 0);
	    	        continue;
	    	      }
	    	      int k = FilterActivity.access$400(this.this$0).getId();
	    	      if (paramInt != k)
	    	        continue;
	    	      boolean bool5 = FilterActivity.access$102(this.this$0, 0);
	    	      boolean bool6 = FilterActivity.access$202(this.this$0, 0);
	    	    }*/
	    	  }
	    });
	    RadioGroup localRadioGroup3 = (RadioGroup)findViewById(R.id.agency_type_group);
	    this.mAgencyRadioGroup = localRadioGroup3;
	    RadioButton localRadioButton4 = (RadioButton)findViewById(R.id.agency);
	    this.mAgencyRadio = localRadioButton4;
	    RadioButton localRadioButton5 = (RadioButton)findViewById(R.id.personal);
	    this.mPersonalRadio = localRadioButton5;
	    RadioButton localRadioButton6 = (RadioButton)findViewById(R.id.agency_none_condition);
	    this.mAgencyNoneConditionRadio = localRadioButton6;
	    RadioGroup localRadioGroup4 = this.mAgencyRadioGroup;
	    localRadioGroup4.setOnCheckedChangeListener(new OnCheckedChangeListener(){
	    	public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
	    	  {
	    	    /*int i = FilterActivity.access$500(this.this$0).getId();
	    	    if (paramInt == i)
	    	    {
	    	      boolean bool1 = FilterActivity.access$602(this.this$0, 1);
	    	      boolean bool2 = FilterActivity.access$702(this.this$0, 0);
	    	    }
	    	    while (true)
	    	    {
	    	      return;
	    	      int j = FilterActivity.access$800(this.this$0).getId();
	    	      if (paramInt == j)
	    	      {
	    	        boolean bool3 = FilterActivity.access$702(this.this$0, 1);
	    	        boolean bool4 = FilterActivity.access$602(this.this$0, 0);
	    	        continue;
	    	      }
	    	      int k = FilterActivity.access$900(this.this$0).getId();
	    	      if (paramInt != k)
	    	        continue;
	    	      boolean bool5 = FilterActivity.access$702(this.this$0, 0);
	    	      boolean bool6 = FilterActivity.access$602(this.this$0, 0);
	    	    }*/
	    	  }
	    });
	    ImageView localImageView1 = (ImageView)findViewById(R.id.siftsearch_goto_back_imageview);
	    this.mGotoBackView = localImageView1;
	    ImageView localImageView2 = this.mGotoBackView;
	    localImageView2.setOnClickListener(new OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
//	    	    PreferenceUtils.setRefreshStatus(this.this$0, 2);
	    	    finish();
	    	  }
		});
	    Button localButton1 = (Button)findViewById(R.id.price_custom_but);
	    this.mCustomButton = localButton1;
	    Button localButton2 = this.mCustomButton;
	    localButton2.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* FilterActivity localFilterActivity = this.this$0;
	    	    Context localContext = FilterActivity.access$1000(this.this$0);
	    	    new FilterActivity.CustomInputDialog(localFilterActivity, localContext).show();*/
	    	  }
		});
	    Button localButton3 = (Button)findViewById(R.id.ok_button);
	    this.mOkButton = localButton3;
	    Button localButton4 = this.mOkButton;
	    localButton4.setOnClickListener(new OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	   /* Context localContext1 = FilterActivity.access$1000(this.this$0);
	    	    StringBuilder localStringBuilder1 = new StringBuilder();
	    	    int i = FilterActivity.access$1100(this.this$0);
	    	    StringBuilder localStringBuilder2 = localStringBuilder1.append(i).append("-");
	    	    int j = FilterActivity.access$1200(this.this$0);
	    	    String str1 = j;
	    	    MobclickAgent.onEvent(localContext1, "priceselect", str1);
	    	    Context localContext2 = FilterActivity.access$1000(this.this$0);
	    	    int k = FilterActivity.access$1300(this.this$0);
	    	    MobclickAgent.onEvent(localContext2, "roomtypeselect", k);
	    	    int m = FilterActivity.access$1400(this.this$0);
	    	    String str2;
	    	    if (-1 == m)
	    	    {
	    	      str2 = "unlimited";
	    	      MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "distencefilter", str2);
	    	      if ((!FilterActivity.access$600(this.this$0)) || (FilterActivity.access$700(this.this$0)))
	    	        break label257;
	    	      MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "agencytype", "agency");
	    	      label162: if ((!FilterActivity.access$100(this.this$0)) || (FilterActivity.access$200(this.this$0)))
	    	        break label311;
	    	      MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "renttype", "all");
	    	    }
	    	    while (true)
	    	    {
	    	      this.this$0.saveSettingToSharePreference();
	    	      PreferenceUtils.setRefreshStatus(this.this$0, 1);
	    	      this.this$0.finish();
	    	      return;
	    	      StringBuilder localStringBuilder3 = new StringBuilder();
	    	      int n = FilterActivity.access$1400(this.this$0);
	    	      str2 = n + "";
	    	      break;
	    	      label257: if ((!FilterActivity.access$600(this.this$0)) && (FilterActivity.access$700(this.this$0)))
	    	      {
	    	        MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "agencytype", "personal");
	    	        break label162;
	    	      }
	    	      MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "agencytype", "unlimit");
	    	      break label162;
	    	      label311: if ((!FilterActivity.access$100(this.this$0)) && (FilterActivity.access$200(this.this$0)))
	    	      {
	    	        MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "renttype", "one");
	    	        continue;
	    	      }
	    	      MobclickAgent.onEvent(FilterActivity.access$1000(this.this$0), "renttype", "unlimit");
	    	    }*/
	    	  }
		});
	    TextView localTextView1 = (TextView)findViewById(R.id.price_range);
	    this.mPriceSectionTextView = localTextView1;
	    TextView localTextView2 = (TextView)findViewById(R.id.room_type_text);
	    this.mCellTextView = localTextView2;
	    TextView localTextView3 = (TextView)findViewById(R.id.distance_text);
	    this.mDistanceTextView = localTextView3;
	    SeekBar localSeekBar1 = (SeekBar)findViewById(R.id.price_type_bar);
	    this.mPriceSeekBar = localSeekBar1;
	    SeekBar localSeekBar2 = (SeekBar)findViewById(R.id.room_type_bar);
	    this.mCellSeekBar = localSeekBar2;
	    this.mCellSeekBar.setMax(5);
	    SeekBar localSeekBar3 = this.mCellSeekBar;
	    localSeekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
	    	public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
	    	  {
//	    	    FilterActivity.access$1500(this.this$0, paramInt);
	    	  }

	    	  public void onStartTrackingTouch(SeekBar paramSeekBar)
	    	  {
	    	  }

	    	  public void onStopTrackingTouch(SeekBar paramSeekBar)
	    	  {
	    	  }
	    });
	    SeekBar localSeekBar4 = (SeekBar)findViewById(R.id.distance_bar);
	    this.mDistanceSeekBar = localSeekBar4;
	    this.mDistanceSeekBar.setMax(4);
	    SeekBar localSeekBar5 = this.mDistanceSeekBar;
	    localSeekBar5.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
	    	public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
	    	  {
//	    	    FilterActivity.access$1600(this.this$0, paramInt);
	    	  }

	    	  public void onStartTrackingTouch(SeekBar paramSeekBar)
	    	  {
	    	  }

	    	  public void onStopTrackingTouch(SeekBar paramSeekBar)
	    	  {
	    	  }
	    });
	    setFileValues();
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.onError(this);
	    boolean bool = requestWindowFeature(1);
	    init();
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
	      super(arg2);
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
	      setContentView(R.layout.custom_dialog);
	      String str = FilterActivity.this.mContext.getString(R.string.custom_dialog_title);
	      setTitle(str);
	      EditText localEditText1 = (EditText)findViewById(R.id.low_input_edittext);
	      this.lowEditText = localEditText1;
	      EditText localEditText2 = (EditText)findViewById(R.id.hight_input_edittext);
	      this.hightEditText = localEditText2;
	      Button localButton1 = (Button)findViewById(R.id.custom_dialog_ok);
	      this.okButton = localButton1;
	      Button localButton2 = (Button)findViewById(R.id.custom_dialog_cancle);
	      this.canclebutton = localButton2;
	      Button localButton3 = this.canclebutton;
	      localButton3.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
//	    	    this.this$1.dismiss();
	    	  }
	      });
	      Button localButton4 = this.okButton;
	      localButton4.setOnClickListener(new View.OnClickListener() {
			
	    	  public void onClick(View paramView)
	    	  {
	    	   /* String str1 = FilterActivity.CustomInputDialog.access$2200(this.this$1).getText().toString();
	    	    String str2 = FilterActivity.CustomInputDialog.access$2300(this.this$1).getText().toString();
	    	    if ((str1.length() == 0) && (str2.length() == 0))
	    	    {
	    	      Toast.makeText(FilterActivity.access$1000(this.this$1.this$0), 2131362018, 0).show();
	    	      FilterActivity.CustomInputDialog.access$2400(this.this$1);
	    	    }
	    	    try
	    	    {
	    	      int i = Integer.valueOf(str1).intValue();
	    	      int j = Integer.valueOf(str2).intValue();
	    	      if (i >= j)
	    	      {
	    	        Toast.makeText(FilterActivity.access$1000(this.this$1.this$0), 2131362020, 0).show();
	    	        FilterActivity.CustomInputDialog.access$2400(this.this$1);
	    	      }
	    	      while (true)
	    	      {
	    	        return;
	    	        int k = FilterActivity.access$1102(this.this$1.this$0, i);
	    	        int m = FilterActivity.access$1202(this.this$1.this$0, j);
	    	        StringBuilder localStringBuilder1 = new StringBuilder();
	    	        int n = FilterActivity.access$1100(this.this$1.this$0);
	    	        StringBuilder localStringBuilder2 = localStringBuilder1.append(n);
	    	        String str3 = FilterActivity.access$1000(this.this$1.this$0).getString(2131362014);
	    	        StringBuilder localStringBuilder3 = localStringBuilder2.append(str3);
	    	        int i1 = FilterActivity.access$1200(this.this$1.this$0);
	    	        StringBuilder localStringBuilder4 = localStringBuilder3.append(i1);
	    	        String str4 = FilterActivity.access$1000(this.this$1.this$0).getString(2131361843);
	    	        String str5 = str4;
	    	        FilterActivity.access$2500(this.this$1.this$0).setText(str5);
	    	        this.this$1.dismiss();
	    	      }
	    	    }
	    	    catch (Exception localException)
	    	    {
	    	      while (true)
	    	      {
	    	        Toast.makeText(FilterActivity.access$1000(this.this$1.this$0), 2131362019, 0).show();
	    	        FilterActivity.CustomInputDialog.access$2400(this.this$1);
	    	      }
	    	    }*/
	    	  }
		});
	    }
	  }
	  
}
