package com.rent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rent.data.HouseSource;

public class ResultAdapter extends BaseAdapter{

	public static final int TOP_OFFSET = 1;
	  private boolean init;
	  int[] mCategoryIndice;
	  private final Context mContext;
	  private final ArrayList<HouseSource> mHouseList;
	  private int mItemCount = 0;
	  private boolean mLoadMore = false;
	  private final String mName;
	  private final int mPrice;
	  private int mSourceCount = 0;
	  private TextView mSourceNumberTextView;

	  public ResultAdapter(Context paramContext, String paramString, int paramInt1, int paramInt2)
	  {
	    ArrayList localArrayList = new ArrayList();
	    this.mHouseList = localArrayList;
	    this.init = true;
	    int[] arrayOfInt = { -1, -1, -1 };
	    this.mCategoryIndice = arrayOfInt;
	    this.mContext = paramContext;
	    this.mName = paramString;
	    this.mPrice = paramInt1;
	    this.mSourceCount = paramInt2;
	  }

	  public static int getVirtualViewItemIndex(int paramInt1, int paramInt2, int[] paramArrayOfInt)
	  {
	    int i = 0;
	    int j = paramInt2;
	    while (true)
	    {
	      int k = paramArrayOfInt.length;
	      if (i >= k)
	        break;
	      if (paramArrayOfInt[i] >= 0)
	      {
	        int m = paramArrayOfInt[i];
	        if (paramInt1 > m)
	          j += 1;
	      }
	      i += 1;
	    }
	    return paramInt1 - j;
	  }

	  public int getCount()
	  {
	    int i = 1;
	    if ((this.mLoadMore) && (this.mItemCount > 0))
	      i = 1 + 1;
	    int j = 0;
	    int k = i;
	    i = j;
	    while (true)
	    {
	      int m = this.mCategoryIndice.length;
	      if (i >= m)
	        break;
	      if (this.mCategoryIndice[i] >= 0)
	        k += 1;
	      i += 1;
	    }
	    return this.mItemCount + k;
	  }

	  public Object getItem(int paramInt)
	  {
	    return this.mHouseList.get(paramInt);
	  }

	  public long getItemId(int paramInt)
	  {
	    return paramInt;
	  }

	  public int getSumCount()
	  {
	    return this.mItemCount;
	  }

	  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	  {
	    View localView1 = null;
	    StringBuffer localStringBuffer1 = null;
	    if (paramInt == 0)
	    {
	      View localView2 = LayoutInflater.from(this.mContext).inflate(2130903107, null);
	      TextView localTextView1 = (TextView)localView2.findViewById(2131492930);
	      String str1 = this.mName;
	      localTextView1.setText(str1);
	      localObject2 = (TextView)localView2.findViewById(2131492931);
	      if (this.mPrice == 0)
	      {
	        String str2 = this.mContext.getString(2131361825);
	        ((TextView)localObject2).setText(str2);
	      }
	      while (true)
	      {
	        TextView localTextView2 = (TextView)localView2.findViewById(2131493145);
	        this.mSourceNumberTextView = localTextView2;
	        if (this.init)
	        {
	          showIsLoading(1);
	          this.init = 0;
	        }
	        if ((this.mSourceNumberTextView.getText() == null) || (this.mSourceNumberTextView.getText().toString().length() == 0))
	        {
	          TextView localTextView3 = this.mSourceNumberTextView;
	          String str3 = this.mContext.getString(2131361946);
	          Object[] arrayOfObject1 = new Object[1];
	          Integer localInteger1 = Integer.valueOf(this.mSourceCount);
	          arrayOfObject1[0] = localInteger1;
	          String str4 = String.format(str3, arrayOfObject1);
	          localTextView3.setText(str4);
	        }
	        localObject2 = localView2;
	        return localObject2;
	        String str5 = this.mContext.getString(2131361952);
	        Object[] arrayOfObject2 = new Object[1];
	        Integer localInteger2 = Integer.valueOf(this.mPrice);
	        arrayOfObject2[0] = localInteger2;
	        String str6 = String.format(str5, arrayOfObject2);
	        ((TextView)localObject2).setText(str6);
	      }
	    }
	    if (paramInt == 1)
	      showIsLoading(0);
	    int i = 0;
	    while (true)
	    {
	      int j = this.mCategoryIndice.length;
	      if (i >= j)
	        break label439;
	      int k = this.mCategoryIndice[i];
	      if (paramInt == k)
	      {
	        View localView3 = LayoutInflater.from(this.mContext).inflate(2130903106, null);
	        localObject2 = (TextView)localView3.findViewById(2131493144);
	        switch (i)
	        {
	        default:
	        case 0:
	        case 1:
	        case 2:
	        }
	        while (true)
	        {
	          localObject2 = localView3;
	          break;
	          String str7 = this.mContext.getString(2131362046);
	          ((TextView)localObject2).setText(str7);
	          continue;
	          String str8 = this.mContext.getString(2131362047);
	          ((TextView)localObject2).setText(str8);
	          continue;
	          String str9 = this.mContext.getString(2131362048);
	          ((TextView)localObject2).setText(str9);
	        }
	      }
	      i += 1;
	    }
	    label439: localView1 = LayoutInflater.from(this.mContext).inflate(2130903105, null);
	    Object localObject1 = localView1.findViewById(2131493136);
	    Object localObject2 = (TextView)localView1.findViewById(2131493092);
	    int[] arrayOfInt = this.mCategoryIndice;
	    int m = getVirtualViewItemIndex(paramInt, 1, arrayOfInt);
	    int n = this.mItemCount;
	    if (m < n)
	    {
	      ((View)localObject1).setVisibility(0);
	      ((TextView)localObject2).setVisibility(8);
	      localObject2 = (TextView)localView1.findViewById(2131493140);
	      String str10 = ((HouseSource)this.mHouseList.get(m)).mTitle;
	      ((TextView)localObject2).setText(str10);
	      localObject1 = (TextView)localView1.findViewById(2131493141);
	      localStringBuffer1 = new StringBuffer();
	      if (((HouseSource)this.mHouseList.get(m)).mRentType == 0)
	      {
	        String str11 = this.mContext.getString(2131361936);
	        StringBuffer localStringBuffer2 = localStringBuffer1.append(str11);
	        label611: StringBuffer localStringBuffer3 = localStringBuffer1.append(":");
	        String str12 = ((HouseSource)this.mHouseList.get(m)).mRoom;
	        StringBuffer localStringBuffer4 = localStringBuffer1.append(str12);
	        int i1 = ((HouseSource)this.mHouseList.get(m)).mArea;
	        StringBuffer localStringBuffer5 = localStringBuffer1.append(i1);
	        StringBuilder localStringBuilder1 = new StringBuilder();
	        String str13 = localStringBuffer1.toString();
	        StringBuilder localStringBuilder2 = localStringBuilder1.append(str13);
	        String str14 = this.mContext.getString(2131361865);
	        String str15 = str14;
	        ((TextView)localObject1).setText(str15);
	        TextView localTextView4 = (TextView)localView1.findViewById(2131493137);
	        StringBuilder localStringBuilder3 = new StringBuilder();
	        int i2 = ((HouseSource)this.mHouseList.get(m)).mPrice;
	        String str16 = i2 + "";
	        localTextView4.setText(str16);
	        if ((((HouseSource)this.mHouseList.get(m)).mAgency != null) && (((HouseSource)this.mHouseList.get(m)).mAgency.length() > 0))
	        {
	          TextView localTextView5 = (TextView)localView1.findViewById(2131493139);
	          String str17 = ((HouseSource)this.mHouseList.get(m)).mAgency;
	          localTextView5.setText(str17);
	        }
	        localObject1 = (ImageView)localView1.findViewById(2131493142);
	        if ((((HouseSource)this.mHouseList.get(m)).mImage != null) && (((HouseSource)this.mHouseList.get(m)).mImage.length() != 0))
	          break label960;
	        ((TextView)localObject2).setWidth(400);
	        ((ImageView)localObject1).setVisibility(8);
	      }
	    }
	    while (true)
	    {
	      localObject2 = localView1;
	      break;
	      String str18 = this.mContext.getString(2131361937);
	      StringBuffer localStringBuffer6 = localStringBuffer1.append(str18);
	      break label611;
	      label960: ((ImageView)localObject1).setVisibility(0);
	      new ResultAdapter.1(this, m, (ImageView)localObject1).start();
	      continue;
	      ((View)localObject1).setVisibility(8);
	      ((TextView)localObject2).setVisibility(0);
	    }
	  }

	  public void setHouseList(ArrayList<HouseSource> paramArrayList)
	  {
	    int i = paramArrayList.size();
	    this.mItemCount = i;
	    this.mHouseList.clear();
	    int j = 0;
	    while (true)
	    {
	      int k = this.mItemCount;
	      if (j >= k)
	        break;
	      ArrayList localArrayList = this.mHouseList;
	      Object localObject = paramArrayList.get(j);
	      boolean bool = localArrayList.add(localObject);
	      j += 1;
	    }
	  }

	  public void setLoadMore(boolean paramBoolean)
	  {
	    this.mLoadMore = paramBoolean;
	  }

	  public void setmCategoryIndice(int[] paramArrayOfInt)
	  {
	    int i = 0;
	    while (true)
	    {
	      int j = paramArrayOfInt.length;
	      if (i >= j)
	        break;
	      int[] arrayOfInt = this.mCategoryIndice;
	      int k = paramArrayOfInt[i];
	      arrayOfInt[i] = k;
	      i += 1;
	    }
	  }

	  public void setmSourceCount(int paramInt)
	  {
	    this.mSourceCount = paramInt;
	    int i = this.mSourceCount;
	    int j = this.mHouseList.size();
	    if (i < j)
	    {
	      int k = this.mHouseList.size();
	      this.mSourceCount = k;
	    }
	  }

	  public void showIsLoading(boolean paramBoolean)
	  {
	    if (this.mSourceNumberTextView == null);
	    while (true)
	    {
	      return;
	      if (paramBoolean)
	      {
	        TextView localTextView1 = this.mSourceNumberTextView;
	        String str1 = this.mContext.getString(2131361897);
	        localTextView1.setText(str1);
	        continue;
	      }
	      TextView localTextView2 = this.mSourceNumberTextView;
	      String str2 = this.mContext.getString(2131361946);
	      Object[] arrayOfObject = new Object[1];
	      Integer localInteger = Integer.valueOf(this.mSourceCount);
	      arrayOfObject[0] = localInteger;
	      String str3 = String.format(str2, arrayOfObject);
	      localTextView2.setText(str3);
	    }
	  }
}
