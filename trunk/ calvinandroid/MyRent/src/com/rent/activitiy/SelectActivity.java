package com.rent.activitiy;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rent.PreferenceUtils;
import com.rent.R;
import com.rent.data.AutoCompleteItemData;
import com.rent.handler.AutoCompleteQueryHandler;
import com.rent.listener.AutoCompleteQueryListener;

public class SelectActivity extends Activity implements AutoCompleteQueryListener {

	 ArrayAdapter<String> mAdapter;
	  private ImageView mBackImageView;
	  private View mBussinessChoice;
	  private LinearLayout mCityChoice;
	  private TextView mCityShow;
	  private ImageView mClearTxt;
	  private Context mContext;
	  private LinearLayout mDistrictChoice;
	  private TextView mDistrictShow;
	  private AutoCompleteTextView mEditText;
	  private LinearLayout mHistoryRecordLL;
	  private Button mOkButton;
	  private AutoCompleteQueryHandler mQueryHandler;

	  private void addLocationHistory(int paramInt1, LinearLayout paramLinearLayout, int paramInt2, String[] paramArrayOfString)
	  {
	    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.history_record_item, null);
	    localLinearLayout.setBackgroundResource(paramInt2);
	    String[] arrayOfString = paramArrayOfString[paramInt1].split("#city#");
	    if ((arrayOfString == null) || (arrayOfString.length <= 1)) {
	    	
	    } else {
	      String str1 = arrayOfString[0];
	      String str2 = arrayOfString[1];
	      ((TextView)localLinearLayout.findViewById(R.id.txt_id)).setText(str2);
	      localLinearLayout.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* PreferenceUtils.setRefreshStatus(SelectActivity.this, 3);
	    	    String str1 = tempKeyWord;
	    	    SelectActivity.access$300(this.this$0, str1);
	    	    SelectActivity localSelectActivity = this.this$0;
	    	    String str2 = this.val$cityName;
	    	    PreferenceUtils.saveCityName(localSelectActivity, str2);
	    	    this.this$0.finish();*/
	    	  }
		});
	      this.mHistoryRecordLL.addView(localLinearLayout);
	    }
	  }

	  private void clearTempKeyWorld()
	  {
	    PreferenceUtils.saveTempKeyWord(this, "");
	  }

	  private static String filterSearchContidion(String paramString)
	  {
	    return Pattern.compile("\\(\\d*\\)").matcher(paramString).replaceAll("");
	  }

	  private void finishSelectAndSkipToSearch(String paramString)
	  {
	    PreferenceUtils.setRefreshStatus(this, 3);
	    //MobclickAgent.onEvent(this.mContext, "manuallocate", paramString);
	    PreferenceUtils.saveKeyWord(this, paramString);
	    PreferenceUtils.setShowKeyword(this, true);
	    String str = PreferenceUtils.getCurrentCityName(this);
	    PreferenceUtils.saveHistoryRecord(this, paramString, str);
	  }

	  private void init()
	  {
	    ImageView localImageView1 = (ImageView)findViewById(R.id.clear_txt_iv);
	    this.mClearTxt = localImageView1;
	    Button localButton1 = (Button)findViewById(R.id.ok_button);
	    this.mOkButton = localButton1;
	    AutoCompleteTextView localAutoCompleteTextView1 = (AutoCompleteTextView)findViewById(R.id.search_edittext);
	    this.mEditText = localAutoCompleteTextView1;
	    AutoCompleteTextView localAutoCompleteTextView2 = this.mEditText;
	    SelectActivity1 local1 = new SelectActivity1();
	    localAutoCompleteTextView2.addTextChangedListener(local1);
	    AutoCompleteTextView localAutoCompleteTextView3 = this.mEditText;
	    localAutoCompleteTextView3.setOnItemClickListener(new OnItemClickListener() {
	    	 public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
	    	  {
	    	    /*String str = SelectActivity.access$100((String)this.this$0.mAdapter.getItem(paramInt));
	    	    SelectActivity.access$200(this.this$0).setText(str);
	    	    AutoCompleteTextView localAutoCompleteTextView = SelectActivity.access$200(this.this$0);
	    	    int i = SelectActivity.access$200(this.this$0).getText().length();
	    	    localAutoCompleteTextView.setSelection(i);
	    	    SelectActivity.access$300(this.this$0, str);
	    	    this.this$0.finish();*/
	    	  }
		});
	    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(R.id.history_record_ll);
	    this.mHistoryRecordLL = localLinearLayout1;
	    ImageView localImageView2 = this.mClearTxt;
	    localImageView2.setOnClickListener(new OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    /*if ((SelectActivity.access$200(this.this$0) != null) && (SelectActivity.access$200(this.this$0).getText().length() > 0))
	    	      SelectActivity.access$200(this.this$0).setText("");*/
	    	  }
		});
	    Button localButton2 = this.mOkButton;
	    localButton2.setOnClickListener(new OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    /*if ((SelectActivity.access$200(this.this$0).getText().toString() != null) && (SelectActivity.access$200(this.this$0).getText().toString().length() > 0))
	    	    {
	    	      String str = SelectActivity.access$200(this.this$0).getText().toString().trim();
	    	      SelectActivity.access$300(this.this$0, str);
	    	      this.this$0.finish();
	    	    }*/
	    	  }
		});
	    TextView localTextView1 = (TextView)findViewById(R.id.bussiness_cur_city);
	    this.mCityShow = localTextView1;
	    TextView localTextView2 = (TextView)findViewById(R.id.area_choice_textview);
	    this.mDistrictShow = localTextView2;
	    ImageView localImageView3 = (ImageView)findViewById(R.id.back);
	    this.mBackImageView = localImageView3;
	    ImageView localImageView4 = this.mBackImageView;
	    localImageView4.setOnClickListener(new OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	   /* PreferenceUtils.setRefreshStatus(this.this$0, 2);
	    	    this.this$0.finish();*/
	    	  }
		});
	    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(R.id.city_choice_ll);
	    this.mCityChoice = localLinearLayout2;
	    LinearLayout localLinearLayout3 = this.mCityChoice;
	    localLinearLayout3.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*SelectActivity localSelectActivity = this.this$0;
	    	    Intent localIntent = new Intent(localSelectActivity, SelectCityActivity.class);
	    	    this.this$0.startActivity(localIntent);*/
	    	  }
		});
	    LinearLayout localLinearLayout4 = (LinearLayout)findViewById(R.id.area_choice_ll);
	    this.mDistrictChoice = localLinearLayout4;
	    LinearLayout localLinearLayout5 = this.mDistrictChoice;
	    localLinearLayout5.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*SelectActivity localSelectActivity = this.this$0;
	    	    Intent localIntent = new Intent(localSelectActivity, SelectAreaActivity.class);
	    	    this.this$0.startActivity(localIntent);*/
	    	  }
		});
	    View localView1 = findViewById(R.id.area_bussiness_district_linearlayout);
	    this.mBussinessChoice = localView1;
	    View localView2 = this.mBussinessChoice;
	    localView2.setOnClickListener(new OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	   /* SelectActivity localSelectActivity = this.this$0;
	    	    Intent localIntent = new Intent(localSelectActivity, SelectZoneActivity.class);
	    	    this.this$0.startActivityForResult(localIntent, 1);*/
	    	  }
		});
	    String[] arrayOfString = PreferenceUtils.getHistoryRecord(this);
	    initHistoryRecord(arrayOfString);
	  }
	  
	  final class SelectActivity1 implements TextWatcher
	{
	  public void afterTextChanged(Editable paramEditable)
	  {
	  }

	  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
	  {
	  }

	  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
	  {
	    if ((paramCharSequence == null) || (paramCharSequence.toString().length() == 0)) {
	   
	    } else {
/*	      SelectActivity localSelectActivity = this.this$0;
	      AutoCompleteQueryHandler localAutoCompleteQueryHandler = SelectActivity.access$000(this.this$0);
	      String str = paramCharSequence.toString();
	      new AutoCompleteQueryThread(localSelectActivity, localAutoCompleteQueryHandler, str).start();
*/	    }
	  }
	}

	  private void initHistoryRecord(String[] paramArrayOfString)
	  {
	    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0) || ((paramArrayOfString.length == 1) && (paramArrayOfString[0].length() == 0)))
	      this.mHistoryRecordLL.setVisibility(8);
	    else {
	    /*int i;
	    int j;
	    do
	    {
	        if (paramArrayOfString.length != 1)
	          break;
	        LinearLayout localLinearLayout1 = this.mHistoryRecordLL;
	        addLocationHistory(0, localLinearLayout1, 2130837640, paramArrayOfString);
	      }
	      i = 0;
	      j = paramArrayOfString.length;
	    }
	    while (i >= j);
	    if (i == 0)
	    {
	      LinearLayout localLinearLayout2 = this.mHistoryRecordLL;
	      addLocationHistory(i, localLinearLayout2, 2130837623, paramArrayOfString);
	    }
	    while (true)
	    {
	      i += 1;
	      break;
	      int k = paramArrayOfString.length - 1;
	      if (i == k)
	      {
	        LinearLayout localLinearLayout3 = this.mHistoryRecordLL;
	        addLocationHistory(i, localLinearLayout3, 2130837552, paramArrayOfString);
	        continue;
	      }
	      LinearLayout localLinearLayout4 = this.mHistoryRecordLL;
	      addLocationHistory(i, localLinearLayout4, 2130837533, paramArrayOfString);
	    }*/
	    }
	  }

	  public void autoCompleteQueryObtained(String paramString)
	  {
	    String[] arrayOfString;
	    int i;
	    if (!paramString.equals(""))
	    {
	      List localList = this.mQueryHandler.getItemLists();
	      arrayOfString = new String[localList.size()];
	      i = 0;
	      Iterator localIterator = localList.iterator();
	      if (localIterator.hasNext())
	      {
	        AutoCompleteItemData localAutoCompleteItemData = (AutoCompleteItemData)localIterator.next();
	        if (localAutoCompleteItemData == null)
	          return;
	        StringBuilder localStringBuilder1 = new StringBuilder();
	        String str1 = localAutoCompleteItemData.suggestion;
	        StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append("(");
	        int k = localAutoCompleteItemData.number;
	        String str2 = k + ")";
	        arrayOfString[i] = str2;
	      }
	    }
	    /*label194: for (int j = i + 1; ; j = i)
	    {
	      i = j;
	      break;
	      ArrayAdapter localArrayAdapter1 = new ArrayAdapter(this, 2130903044, arrayOfString);
	      this.mAdapter = localArrayAdapter1;
	      AutoCompleteTextView localAutoCompleteTextView = this.mEditText;
	      ArrayAdapter localArrayAdapter2 = this.mAdapter;
	      localAutoCompleteTextView.setAdapter(localArrayAdapter2);
	      this.mAdapter.notifyDataSetChanged();
	      this.mEditText.showDropDown();
	      return;
	    }*/
	  }

	  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
	  {
	    super.onActivityResult(paramInt1, paramInt2, paramIntent);
	    if ((paramInt1 == 1) && (paramInt2 == 2))
	      finish();
	  }

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
//	    MobclickAgent.onError(this);
	    AutoCompleteQueryHandler localAutoCompleteQueryHandler = new AutoCompleteQueryHandler(this, this);
	    this.mQueryHandler = localAutoCompleteQueryHandler;
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903041);
	    this.mContext = this;
	    clearTempKeyWorld();
	    init();
	  }

	  protected void onPause()
	  {
	    super.onPause();
	   // MobclickAgent.onPause(this);
	  }

	  protected void onResume()
	  {
	    super.onResume();
	   // MobclickAgent.onResume(this);
	    TextView localTextView1 = this.mCityShow;
	    String str1 = PreferenceUtils.getCurrentCityName(this.mContext);
	    localTextView1.setText(str1);
	    TextView localTextView2 = this.mDistrictShow;
	    String str2 = PreferenceUtils.getCurrentDistrictName(this.mContext);
	    localTextView2.setText(str2);
	    AutoCompleteTextView localAutoCompleteTextView = this.mEditText;
	    String str3 = PreferenceUtils.getCurrentTempKeyWord(this.mContext);
	    localAutoCompleteTextView.setText(str3);
	  }
	  
}
