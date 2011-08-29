package com.rent.adapter;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;
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
		String str1 = this.mContext.getString(R.string.yuan_two_unit);
		this.mPriceUnitSrt = str1;
		String str2 = this.mContext.getString(R.string.unavailable2);
		this.mNoPricePrompt = str2;
		this.activity = activity;
	}

	public int getCount() {
		if (this.mIsEnd) {
			return this.mCount;
		}
		return 0;
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
		try {
			if (this.mCount > paramInt) {
				localObject1.setVisibility(0);
				localObject2.setVisibility(8);
				l = ((Community) this.mCommList.get(paramInt)).mGroupId;
				ImageView img = (ImageView) localView
						.findViewById(R.id.search_list_image);
				ListSearchAdapterThread adapterThread = new ListSearchAdapterThread(
						this, paramInt, img);
				FutureTask<String> task = new FutureTask<String>(adapterThread);
				new Thread(task).start();
				
				if(task.get().equals("success")) {
					GroupImageManager localGroupImageManager1 = this.mGroupSDCardManager;
					String str1 = l + ".png";
					if (localGroupImageManager1.getBitmap(str1) == null){}
					GroupImageManager localGroupImageManager2 = this.mGroupSDCardManager;
					String str2 = l + ".png";
					Bitmap localBitmap = localGroupImageManager2.getBitmap(str2);
					img.setImageBitmap(localBitmap);
					TextView localTextView1 = (TextView) localView
							.findViewById(R.id.cell_title);
					String str3 = ((Community) this.mCommList.get(paramInt)).mName;
					localTextView1.setText(str3);
					TextView localTextView2 = (TextView) localView
							.findViewById(R.id.cell_sourcecount);
					StringBuilder localStringBuilder1 = new StringBuilder().append("(");
					int i = ((Community) this.mCommList.get(paramInt)).mSourceCount;
					localTextView2.setText(localStringBuilder1.append(i + ")"));
					TextView text = (TextView) localView.findViewById(R.id.cell_price);
					/*
					 * if (((Community) this.mCommList.get(paramInt)).mPrice > 0)
					 * //TODO: return null;
					 */
					text.setText(this.mNoPricePrompt);
					TextView localTextView3 = (TextView) localView
							.findViewById(R.id.cell_address);
					String str6 = ((Community) this.mCommList.get(paramInt)).mAddress;
					localTextView3.setText(str6);
				}
			} else {
				((View) localObject1).setVisibility(8);
				((View) localObject2).setVisibility(0);
			}
		} catch (Exception localException) {
			/*StringBuilder localStringBuilder2 = new StringBuilder();
			int j = ((Community) this.mCommList.get(paramInt)).mPrice;
			StringBuilder localStringBuilder3 = localStringBuilder2.append(j);
			String str7 = this.mPriceUnitSrt;
			((TextView) localObject1).setText(str7);*/
		}
		return localView;
	}

	public void refresh(ArrayList<Community> paramArrayList) {
		int i = paramArrayList.size();
		this.mCount = i;
		this.mCommList.clear();
		for (int k = 0; k < paramArrayList.size(); k++) {
			this.mCommList.add(paramArrayList.get(k));
		}
	}

	public void setIsEnd(boolean paramBoolean) {
		this.mIsEnd = paramBoolean;
	}

	final class ListSearchAdapterThread implements Callable<String> {
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

		public String call() throws Exception {
			Looper.prepare();// TODO
			try {
				ArrayList localArrayList1 = adapter.mCommList;
				/*for (int i = 0; i < localArrayList1.size(); i++) {
					if (((Community) localArrayList1.get(i)).mImage
							.indexOf("http") == -1)
						continue;*/

					Bitmap localBitmap1 = Rent
							.downLoadImage(((Community) localArrayList1.get(paramInt)).mImage, mGroupSDCardManager, ((Community) localArrayList1.get(paramInt)).mGroupId);
					int k = imageView.getWidth();
					int m = imageView.getHeight(); // TODO: here can not get the
													// width and height
					if(localBitmap1 != null) {
						int k1 = localBitmap1.getWidth();
						int m1 = localBitmap1.getHeight();
						localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, k1,
								m1, true);
						activity.runOnUiThread(new Runnable() {
							public void run() {
								ImageView localImageView = imageView;
								localImageView.setImageBitmap(localBitmap2);
							}
						});
						StringBuilder localStringBuilder = new StringBuilder();
						long l = ((Community) localArrayList1.get(paramInt)).mGroupId;
						String str = l + ".png";
						boolean bool = mGroupSDCardManager.saveBitmap(localBitmap1,
								str);
					}
//				}
			} catch (Exception localException) {
				Log.e("error", localException.getCause().getMessage());
				return "fail";
			} finally {
				Looper.myLooper().quit();
			}
			return "success";
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
