package com.rent.adapter;

import java.util.ArrayList;

import android.app.Activity;
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

import com.rent.GroupImageManager;
import com.rent.R;
import com.rent.Rent;
import com.rent.data.Community;

public class ListSearchAdapter extends BaseAdapter {

	private final ArrayList<Community> mCommList;
	private final Context mContext;
	private int mCount = 0;
	private GroupImageManager mGroupSDCardManager;
	private boolean mIsEnd = true;
	private String mNoPricePrompt;
	private String mPriceUnitSrt;
	private Activity activity;

	public ListSearchAdapter(Context paramContext, Activity activity) {
		ArrayList localArrayList = new ArrayList();
		this.mCommList = localArrayList;
		GroupImageManager localGroupImageManager = new GroupImageManager();
		this.mGroupSDCardManager = localGroupImageManager;
		this.mContext = paramContext;
		String str1 = this.mContext.getString(2131361823);
		this.mPriceUnitSrt = str1;
		String str2 = this.mContext.getString(2131361825);
		this.mNoPricePrompt = str2;
		this.activity = activity;
	}

	public int getCount() {
		if (this.mIsEnd)
			;
		for (int i = this.mCount;; i = this.mCount + 1)
			return i;
	}

	public Object getItem(int paramInt) {
		return Integer.valueOf(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		View localView = LayoutInflater.from(this.mContext).inflate(
				R.layout.listsearch_row, null);
		View localObject1 = localView.findViewById(R.id.list_layout);
		View localObject2 = localView.findViewById(R.id.result_load_more_text);
		long l = 0;
		if (this.mCount > paramInt) {
			localObject1.setVisibility(0);
			localObject2.setVisibility(8);
			l = ((Community) this.mCommList.get(paramInt)).mGroupId;
			localObject1 = (ImageView) localView
					.findViewById(R.id.search_list_image);
			new ListSearchAdapterThread(
					this, paramInt, (ImageView) localObject1).start();
		}
		try {
			GroupImageManager localGroupImageManager1 = this.mGroupSDCardManager;
			String str1 = l + ".png";
			if (localGroupImageManager1.getBitmap(str1) == null)
				return null;
			GroupImageManager localGroupImageManager2 = this.mGroupSDCardManager;
			String str2 = l + ".png";
			Bitmap localBitmap = localGroupImageManager2.getBitmap(str2);
			((ImageView) localObject1).setImageBitmap(localBitmap);
			TextView localTextView1 = (TextView) localView
					.findViewById(R.id.cell_title);
			String str3 = ((Community) this.mCommList.get(paramInt)).mName;
			localTextView1.setText(str3);
			TextView localTextView2 = (TextView) localView
					.findViewById(R.id.cell_sourcecount);
			StringBuilder localStringBuilder1 = new StringBuilder().append("(");
			int i = ((Community) this.mCommList.get(paramInt)).mSourceCount;
			String str4 = i + ")";
			localTextView2.setText(str4);
			localObject1 = (TextView) localView.findViewById(R.id.cell_price);
			/*if (((Community) this.mCommList.get(paramInt)).mPrice > 0) //TODO:
				return null;*/
			String str5 = this.mNoPricePrompt;
			((TextView) localObject1).setText(str5);
			TextView localTextView3 = (TextView) localView
					.findViewById(R.id.cell_address);
			String str6 = ((Community) this.mCommList.get(paramInt)).mAddress;
			localTextView3.setText(str6);
		} catch (Exception localException) {
			StringBuilder localStringBuilder2 = new StringBuilder();
			int j = ((Community) this.mCommList.get(paramInt)).mPrice;
			StringBuilder localStringBuilder3 = localStringBuilder2.append(j);
			String str7 = this.mPriceUnitSrt;
			((TextView) localObject1).setText(str7);
		}
		((View) localObject1).setVisibility(8);
		((View) localObject2).setVisibility(0);
		return localView;
	}

	public void refresh(ArrayList<Community> paramArrayList) {
		int i = paramArrayList.size();
		this.mCount = i;
		this.mCommList.clear();
		int j = 0;
		while (true) {
			int k = this.mCount;
			if (j >= k)
				break;
			ArrayList localArrayList = this.mCommList;
			Object localObject = paramArrayList.get(j);
			boolean bool = localArrayList.add(localObject);
			j += 1;
		}
	}

	public void setIsEnd(boolean paramBoolean) {
		this.mIsEnd = paramBoolean;
	}

	final class ListSearchAdapterThread extends Thread {
		private int paramInt;
		private ImageView imageView;
		private ListSearchAdapter adapter;
		private Bitmap localBitmap2;

		public ListSearchAdapterThread(ListSearchAdapter adapter, int paramInt,
				ImageView imageView) {
			this.adapter = adapter;
			this.paramInt = paramInt;
			this.imageView = imageView;
		}

		public void run() {
			Looper.prepare();// TODO
			try {
				ArrayList localArrayList1 = adapter.mCommList;
				for (int i = 0; i < localArrayList1.size(); i++) {
					byte[] arrayOfByte = Rent
							.downLoadImage(((Community) localArrayList1.get(i)).mImage);
					Bitmap localBitmap1 = BitmapFactory.decodeByteArray(
							arrayOfByte, 0, arrayOfByte.length);
					int k = imageView.getWidth();
					int m = imageView.getHeight();
					localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, k,
							m, true);
					activity.runOnUiThread(new Runnable() {
						public void run() {
							ImageView localImageView = imageView;
							localImageView.setImageBitmap(localBitmap2);
						}
					});
					StringBuilder localStringBuilder = new StringBuilder();
					long l = ((Community) localArrayList1.get(i)).mGroupId;
					String str = l + ".png";
					boolean bool = mGroupSDCardManager.saveBitmap(localBitmap1,
							str);
				}
			} catch (Exception localException) {
				localException.printStackTrace();
			} finally {
				Looper.myLooper().quit();
			}
		}
	}

	final class ListSearchAdapterRunnable implements Runnable {
		private Bitmap image;
		private ImageView imageView;

		public ListSearchAdapterRunnable(Bitmap image, ImageView imageView) {
			this.image = image;
			this.imageView = imageView;
		}

		public void run() {
			ImageView localImageView = imageView;
			Bitmap localBitmap = image;
			localImageView.setImageBitmap(localBitmap);
		}
	}

}
