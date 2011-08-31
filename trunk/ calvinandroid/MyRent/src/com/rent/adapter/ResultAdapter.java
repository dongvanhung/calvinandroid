package com.rent.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rent.R;
import com.rent.Rent;
import com.rent.activitiy.ResultActivity;
import com.rent.data.HouseSource;

public class ResultAdapter extends BaseAdapter {

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

	public ResultAdapter(Context paramContext, String paramString,
			int paramInt1, int paramInt2) {
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

	public static int getVirtualViewItemIndex(int paramInt1, int paramInt2,
			int[] paramArrayOfInt) {
		int i = 0;
		int j = paramInt2;
		while (true) {
			int k = paramArrayOfInt.length;
			if (i >= k)
				break;
			if (paramArrayOfInt[i] >= 0) {
				int m = paramArrayOfInt[i];
				if (paramInt1 > m)
					j += 1;
			}
			i += 1;
		}
		if(paramInt1 - j < 0)
			return 0;
		return paramInt1 - j;
	}

	public int getCount() {
		int i = 1;
		if ((this.mLoadMore) && (this.mItemCount > 0))
			i = 1 + 1;
		int j = 0;
		int k = i;
		i = j;
		while (true) {
			int m = this.mCategoryIndice.length;
			if (i >= m)
				break;
			if (this.mCategoryIndice[i] >= 0)
				k += 1;
			i += 1;
		}
		return this.mItemCount + k;
	}

	public Object getItem(int paramInt) {
		return this.mHouseList.get(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public int getSumCount() {
		return this.mItemCount;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		View localView1 = null;
		StringBuffer localStringBuffer1 = null;
		if (paramInt == 0) {
			View localView2 = LayoutInflater.from(this.mContext).inflate(
					com.rent.R.layout.result_row_community, null);
			TextView localTextView1 = (TextView) localView2
					.findViewById(com.rent.R.id.name);
			String str1 = this.mName;
			localTextView1.setText(str1);
			TextView localObject2 = (TextView) localView2
					.findViewById(R.id.price);
			if (this.mPrice == 0) {
				String str2 = this.mContext.getString(R.string.unavailable2);
				localObject2.setText(str2);
			} else {
				String str5 = this.mContext
						.getString(R.string.rent_average_price_mount);
				Object[] arrayOfObject2 = new Object[1];
				Integer localInteger2 = Integer.valueOf(this.mPrice);
				arrayOfObject2[0] = localInteger2;
				String str6 = String.format(str5, arrayOfObject2);
				((TextView) localObject2).setText(str6);
			}
			TextView localTextView2 = (TextView) localView2
					.findViewById(R.id.sourcecount);
			this.mSourceNumberTextView = localTextView2;
			if (this.init) {
				showIsLoading(true);
				this.init = false;
			}
			if ((this.mSourceNumberTextView.getText() == null)
					|| (this.mSourceNumberTextView.getText().toString()
							.length() == 0)) {
				TextView localTextView3 = this.mSourceNumberTextView;
				String str3 = this.mContext
						.getString(R.string.community_detail_sourcecount);
				Object[] arrayOfObject1 = new Object[1];
				Integer localInteger1 = Integer.valueOf(this.mSourceCount);
				arrayOfObject1[0] = localInteger1;
				String str4 = String.format(str3, arrayOfObject1);
				localTextView3.setText(str4);
			}

		}
		if (paramInt == 1) {
			showIsLoading(false);
			int i = 0;
			while (true) {
				int j = this.mCategoryIndice.length;
				if (i >= j)
					break;
				int k = this.mCategoryIndice[i];
				if (paramInt == k) {
					View localView3 = LayoutInflater.from(this.mContext)
							.inflate(R.layout.result_row_category, null);
					TextView localObject2 = (TextView) localView3
							.findViewById(R.id.category);
					switch (i) {
					default:
						localObject2 = (TextView) localView3;
					case 0:
						String str7 = this.mContext
								.getString(R.string.housesource_category_0);
						((TextView) localObject2).setText(str7);
					case 1:
						String str8 = this.mContext
								.getString(R.string.housesource_category_1);
						((TextView) localObject2).setText(str8);
					case 2:
						String str9 = this.mContext
								.getString(R.string.housesource_category_2);
						((TextView) localObject2).setText(str9);
					}
					i += 1;
				}
			}
		}
		localView1 = LayoutInflater.from(this.mContext).inflate(
				R.layout.result_row, null);
		View localObject1 = localView1.findViewById(R.id.result_layout_view);
		Object localObject2 = (TextView) localView1
				.findViewById(R.id.result_load_more_text);
		int[] arrayOfInt = this.mCategoryIndice;
		int m = getVirtualViewItemIndex(paramInt, 1, arrayOfInt);
		int n = this.mItemCount;
		m = paramInt;
		if (m < n && n != 0) {
			((View) localObject1).setVisibility(0);
			((TextView) localObject2).setVisibility(8);
			localObject2 = (TextView) localView1
					.findViewById(R.id.result_list_title);
			String str10 = ((HouseSource) this.mHouseList.get(m)).mTitle;
			((TextView) localObject2).setText(str10);
			TextView textView = (TextView) localView1
					.findViewById(R.id.result_detail_left);
			localStringBuffer1 = new StringBuffer();
			if (((HouseSource) this.mHouseList.get(m)).mRentType == 0) {
				String str11 = this.mContext
						.getString(R.string.rent_favourite_rent_type_lease_all);
				StringBuffer localStringBuffer2 = localStringBuffer1
						.append(str11);
			} else {
				String str18 = this.mContext
						.getString(R.string.rent_favourite_rent_type_lease_part);
				StringBuffer localStringBuffer6 = localStringBuffer1
						.append(str18);
			}
			StringBuffer localStringBuffer3 = localStringBuffer1.append(":");
			String str12 = ((HouseSource) this.mHouseList.get(m)).mRoom;
			StringBuffer localStringBuffer4 = localStringBuffer1.append(str12);
			int i1 = ((HouseSource) this.mHouseList.get(m)).mArea;
			StringBuffer localStringBuffer5 = localStringBuffer1.append(i1);
			StringBuilder localStringBuilder1 = new StringBuilder();
			String str13 = localStringBuffer1.toString();
			StringBuilder localStringBuilder2 = localStringBuilder1
					.append(str13);
			String str14 = this.mContext.getString(R.string.sq_meter);
			String str15 = str14;
			textView.setText(str15);
			TextView localTextView4 = (TextView) localView1
					.findViewById(R.id.result_detail_right);
			StringBuilder localStringBuilder3 = new StringBuilder();
			int i2 = ((HouseSource) this.mHouseList.get(m)).mPrice;
			String str16 = i2 + "";
			localTextView4.setText(str16);
			if ((((HouseSource) this.mHouseList.get(m)).mAgency != null)
					&& (((HouseSource) this.mHouseList.get(m)).mAgency.length() > 0)) {
				TextView localTextView5 = (TextView) localView1
						.findViewById(R.id.agency_status);
				String str17 = ((HouseSource) this.mHouseList.get(m)).mAgency;
				localTextView5.setText(str17);
			}
			ImageView imageView = (ImageView) localView1.findViewById(R.id.result_pic);
			if ((((HouseSource) this.mHouseList.get(m)).mImage != null)
					&& (((HouseSource) this.mHouseList.get(m)).mImage.length() != 0)) {
				((TextView) localObject2).setWidth(400);
				imageView.setVisibility(8);
			} else {
				imageView.setVisibility(0);
				new ResultAdapter1(this, m, imageView).start();
			}
		} else {
			((View) localObject1).setVisibility(8);
			((TextView) localObject2).setVisibility(0);
		}
		return localView1;
	}

	final class ResultAdapter1 extends Thread {
		private ResultAdapter adapter;
		private int m;
		private ImageView imageView;

		public ResultAdapter1(ResultAdapter adapter, int m, ImageView imageView) {
			this.adapter = adapter;
			this.m = m;
			this.imageView = imageView;
		}

		public void run() {
			Looper.prepare();
			try {

				ArrayList localArrayList = ResultAdapter.this.mHouseList;
				byte[] arrayOfByte = Rent
						.downLoadImage(((HouseSource) localArrayList.get(m)).mImage);
				int j = arrayOfByte.length;
				Bitmap localBitmap1 = BitmapFactory.decodeByteArray(
						arrayOfByte, 0, j);
				int k = imageView.getWidth();
				int m = imageView.getHeight();
				Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1,
						k, m, true);
				ResultAdapter11 adapter11 = new ResultAdapter11(localBitmap2,
						imageView);
				((ResultActivity) adapter.mContext).runOnUiThread(adapter11);

			} catch (Exception localException) {
				localException.printStackTrace();
			} finally {
				Looper.myLooper().quit();
			}
		}
	}

	final class ResultAdapter11 implements Runnable {
		private Bitmap bm;
		private ImageView imageView;

		public ResultAdapter11(Bitmap bm, ImageView imageView) {
			this.bm = bm;
			this.imageView = imageView;
		}

		public void run() {
			ImageView localImageView = imageView;
			Bitmap localBitmap = bm;
			localImageView.setImageBitmap(localBitmap);
		}
	}

	public void setHouseList(ArrayList<HouseSource> paramArrayList) {
		int i = paramArrayList.size();
		this.mItemCount = i;
		this.mHouseList.clear();
		for (int j2 = 0; j2 < i; j2++) {
			Object localObject = paramArrayList.get(j2);
			this.mHouseList.add((HouseSource)localObject);
		}
	}

	public void setLoadMore(boolean paramBoolean) {
		this.mLoadMore = paramBoolean;
	}

	public void setmCategoryIndice(int[] paramArrayOfInt) {
		int i = 0;
		while (true) {
			int j = paramArrayOfInt.length;
			if (i >= j)
				break;
			int[] arrayOfInt = this.mCategoryIndice;
			int k = paramArrayOfInt[i];
			arrayOfInt[i] = k;
			i += 1;
		}
	}

	public void setmSourceCount(int paramInt) {
		this.mSourceCount = paramInt;
		int i = this.mSourceCount;
		int j = this.mHouseList.size();
		if (i < j) {
			int k = this.mHouseList.size();
			this.mSourceCount = k;
		}
	}

	public void showIsLoading(boolean paramBoolean) {
		if (this.mSourceNumberTextView == null) {
		} else {
			if (paramBoolean) {
				TextView localTextView1 = this.mSourceNumberTextView;
				String str1 = this.mContext.getString(R.string.on_loading);
				localTextView1.setText(str1);
			} else {
				TextView localTextView2 = this.mSourceNumberTextView;
				String str2 = this.mContext
						.getString(R.string.community_detail_sourcecount);
				Object[] arrayOfObject = new Object[1];
				Integer localInteger = Integer.valueOf(this.mSourceCount);
				arrayOfObject[0] = localInteger;
				String str3 = String.format(str2, arrayOfObject);
				localTextView2.setText(str3);
			}
		}
	}
}
