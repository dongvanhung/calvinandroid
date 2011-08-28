package com.rent.view;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.rent.GroupImageManager;
import com.rent.R;
import com.rent.Rent;
import com.rent.UIUtils;
import com.rent.activitiy.ResultActivity;
import com.rent.activitiy.SearchOnMapActivity;
import com.rent.data.Community;

public class RentMapOverlayItem extends ItemizedOverlay<OverlayItem> {

	private boolean mCanClick = true;
	private final ArrayList<Community> mCellList;
	private int mClickOnIndex = 0;
	private final Context mContext;
	private int mFlag = 0;
	private final GroupImageManager mGroupPicSDCardManager;
	private boolean mHasClickCellView = false;
	private final ArrayList<OverlayItem> mItemList;
	private final MapView mMapView;
	private int mOffsetX = 0;
	private float mOffsetY = 0.0F;
	private final View mOverlayView;

	public RentMapOverlayItem(Drawable paramDrawable, Context paramContext,
			View paramView, MapView paramMapView) {
		super(paramDrawable);
		ArrayList localArrayList1 = new ArrayList();
		this.mItemList = localArrayList1;
		ArrayList localArrayList2 = new ArrayList();
		this.mCellList = localArrayList2;
		GroupImageManager localGroupImageManager = new GroupImageManager();
		this.mGroupPicSDCardManager = localGroupImageManager;
		this.mContext = paramContext;
		this.mOverlayView = paramView;
		this.mMapView = paramMapView;
	}

	private void initTapClick() {
		OverlayView localOverlayView = (OverlayView) this.mOverlayView
				.findViewById(R.id.overlay_view);
		localOverlayView.setVisibility(8);
		localOverlayView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramView) {
				RentMapOverlayItem.this.mCanClick = true;
				RentMapOverlayItem.this.mHasClickCellView = true;
				// if (RentMapOverlayItem.access$700(this.this$0))
				// {
				Context localContext = RentMapOverlayItem.this.mContext;
				Intent localIntent1 = new Intent(localContext,
						ResultActivity.class);
				Bundle localBundle = new Bundle();
				localBundle.putInt("searchformat", 0);
				ArrayList localArrayList1 = RentMapOverlayItem.this.mCellList;
				int i = RentMapOverlayItem.this.mClickOnIndex;
				String str1 = ((Community) localArrayList1.get(i)).mName;
				localBundle.putString("keyword", str1);
				long l = ((Community) localArrayList1.get(i)).mGroupId;
				localBundle.putLong("group_id", l);
				String str2 = ((Community) localArrayList1.get(i)).mCity;
				localBundle.putString("city", str2);
				int n = ((Community) localArrayList1.get(i)).mPrice;
				localBundle.putInt("price", n);
				int i2 = ((Community) localArrayList1.get(i)).mSourceCount;
				localBundle.putInt("sourcecoount", i2);
				double d1 = ((Community) localArrayList1.get(i)).mLat;
				localBundle.putDouble("latitude", d1);
				double d2 = ((Community) localArrayList1.get(i)).mLon;
				localBundle.putDouble("longitude", d2);
				Intent localIntent2 = localIntent1.putExtras(localBundle);
				localContext.startActivity(localIntent1);
				// }
			}
		});
	}

	public void addItem(OverlayItem paramOverlayItem, Community paramCommunity,
			boolean paramBoolean, int paramInt) {
		boolean bool1 = this.mItemList.add(paramOverlayItem);
		boolean bool2 = this.mCellList.add(paramCommunity);
		this.mCanClick = paramBoolean;
		this.mFlag = paramInt;
		populate();
	}

	protected OverlayItem createItem(int paramInt) {
		return (OverlayItem) this.mItemList.get(paramInt);
	}

	public void draw(Canvas paramCanvas, MapView paramMapView,
			boolean paramBoolean) {
		int i = 0;
		try {
			int j = size();
			int k = 0;
			if (k < j) {
				Projection localProjection = paramMapView.getProjection();
				Point localPoint1 = new Point();
				int m = ((OverlayItem) this.mItemList.get(k)).getPoint()
						.getLatitudeE6();
				int n = ((OverlayItem) this.mItemList.get(k)).getPoint()
						.getLongitudeE6();
				GeoPoint localGeoPoint = new GeoPoint(m, n);
				Point localPoint2 = localProjection.toPixels(localGeoPoint,
						localPoint1);
				i = localPoint1.x;
				int i1 = localPoint1.y;
				try {
					Paint localPaint = new Paint();
					Bitmap localBitmap = BitmapFactory.decodeResource(
							this.mContext.getResources(), R.drawable.pin_icon);
					int i2 = localBitmap.getWidth();
					int i3 = localBitmap.getHeight();
					Rect localRect = new Rect(0, 0, i2, i3);
					float f1 = i;
					float f2 = i1;
					int i4 = localBitmap.getWidth();
					float f3 = i + i4;
					int i5 = localBitmap.getHeight();
					float f4 = i1 + i5;
					RectF localRectF = new RectF(f1, f2, f3, f4);
					paramCanvas.drawBitmap(localBitmap, localRect, localRectF,
							localPaint);
					k += 1;
				} catch (Exception localException1) {
					while (true)
						localException1.printStackTrace();
				}
			} else {
				super.draw(paramCanvas, paramMapView, paramBoolean);
			}
		} catch (Exception localException2) {
		}
	}

	final class RentMapOverlayItem2 implements Callable<String> {
		private int paramInt;

		public RentMapOverlayItem2(int paramInt) {
			this.paramInt = paramInt;
		}

		public String call() throws Exception {
			try {
				ArrayList localArrayList = RentMapOverlayItem.this.mCellList;
				byte[] arrayOfByte = Rent
						.downLoadImage(((Community) localArrayList
								.get(paramInt)).mImage);
				int j = arrayOfByte.length;
				Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte,
						0, j);
				SearchOnMapActivity localSearchOnMapActivity = (SearchOnMapActivity) RentMapOverlayItem.this.mContext;
				RentMapOverlayItem21 local1 = new RentMapOverlayItem21(
						localBitmap, localSearchOnMapActivity);
				localSearchOnMapActivity.runOnUiThread(local1);
			} catch (Exception localException) {
				localException.printStackTrace();
				return "fail";
			}
			return "success";
		}
	}

	final class RentMapOverlayItem21 implements Runnable {
		private Bitmap bm;
		private SearchOnMapActivity localSearchOnMapActivity;

		public RentMapOverlayItem21(Bitmap bm,
				SearchOnMapActivity localSearchOnMapActivity) {
			this.bm = bm;
			this.localSearchOnMapActivity = localSearchOnMapActivity;
		}

		public void run() {
			ImageView localImageView = (ImageView) localSearchOnMapActivity
					.findViewById(R.id.overlay_pic);
			localImageView.setImageBitmap(bm);
			localImageView.setVisibility(0);
			try {
				GroupImageManager localGroupImageManager = RentMapOverlayItem.this.mGroupPicSDCardManager;
				StringBuilder localStringBuilder = new StringBuilder();
				ArrayList localArrayList = RentMapOverlayItem.this.mCellList;
				int i = RentMapOverlayItem.this.mClickOnIndex;
				long l = ((Community) localArrayList.get(i)).mGroupId;
				String str = l + ".png";
				boolean bool = localGroupImageManager.saveBitmap(bm, str);
			} catch (Exception localException) {
			}
		}
	}

	protected boolean onTap(int paramInt) {
		MapView.LayoutParams localLayoutParams = null;
		View localView1 = null;
		try {
			if ((size() > paramInt)
					&& (((Community) this.mCellList.get(paramInt)).mFlag == 0)) {
				RentMapOverlayItem2 local2 = new RentMapOverlayItem2(paramInt);
				FutureTask<String> task = new FutureTask<String>(local2);
				new Thread(task).start();

				if (task.get().equals("success")) {

					GroupImageManager localGroupImageManager1 = this.mGroupPicSDCardManager;
					StringBuilder localStringBuilder1 = new StringBuilder();
					long l1 = ((Community) this.mCellList.get(paramInt)).mGroupId;
					String str1 = l1 + ".png";
					if (localGroupImageManager1.getBitmap(str1) != null) {
						GroupImageManager localGroupImageManager2 = this.mGroupPicSDCardManager;
						StringBuilder localStringBuilder2 = new StringBuilder();
						long l2 = ((Community) this.mCellList.get(paramInt)).mGroupId;
						String str2 = l2 + ".png";
						Bitmap localBitmap1 = localGroupImageManager2
								.getBitmap(str2);
						((ImageView) this.mOverlayView.findViewById(R.id.overlay_pic))
								.setImageBitmap(localBitmap1);
					} else {
						this.mClickOnIndex = paramInt;
						TextView localTextView1 = (TextView) this.mOverlayView
								.findViewById(R.id.overlay_title);
						String str3 = ((Community) this.mCellList.get(paramInt)).mName;
						localTextView1.setText(str3);
						int j = this.mFlag;
						if (2 != j) {
							TextView localObject1 = (TextView) this.mOverlayView
									.findViewById(R.id.overlay_price);
							StringBuffer localObject2 = new StringBuffer();
							StringBuffer localStringBuffer1 = ((StringBuffer) localObject2)
									.append("<font color=\"#65de53\">");
							if (((Community) this.mCellList.get(paramInt)).mPrice > 0){} //TODO:
							String str4 = this.mContext.getString(R.string.unavailable2);
							StringBuffer localStringBuffer2 = ((StringBuffer) localObject2)
									.append(str4);
							StringBuffer localStringBuffer3 = ((StringBuffer) localObject2)
									.append("<font>");
							Spanned localSpanned1 = Html
									.fromHtml(((StringBuffer) localObject2)
											.toString());
							((TextView) localObject1).setText(localSpanned1);
						}
						int k = this.mFlag;
						if (2 == k) {
							TextView localTextView3 = (TextView) this.mOverlayView
									.findViewById(R.id.overlay_fit);
							String str8 = ((Community) this.mCellList
									.get(paramInt)).mAddress;
							localTextView3.setText(str8);
						} else {
							TextView localTextView2 = (TextView) this.mOverlayView
									.findViewById(R.id.overlay_fit);
							String str5 = this.mContext.getString(R.string.have_some_cells_for_condition);
							Object[] arrayOfObject1 = new Object[3];
							arrayOfObject1[0] = "<font color=\"#f27a04\">";
							Integer localInteger1 = Integer
									.valueOf(((Community) this.mCellList
											.get(paramInt)).mSourceCount);
							arrayOfObject1[1] = localInteger1;
							arrayOfObject1[2] = "</font>";
							Spanned localSpanned2 = Html.fromHtml(String.format(
									str5, arrayOfObject1));
							localTextView2.setText(localSpanned2);
						}
						int m = (int) (UIUtils.dip2Px(this.mContext, 21.0F) * -1.0F);
						localLayoutParams = (MapView.LayoutParams) this.mOverlayView
								.getLayoutParams();
						localLayoutParams.x = 0;
						localLayoutParams.y = m;
						int n = ((OverlayItem) this.mItemList.get(paramInt))
								.getPoint().getLatitudeE6();
						int i1 = ((OverlayItem) this.mItemList.get(paramInt))
								.getPoint().getLongitudeE6();
						GeoPoint localGeoPoint1 = new GeoPoint(n, i1);
						localLayoutParams.point = localGeoPoint1;
						Projection localProjection = this.mMapView
								.getProjection();
						Point localPoint1 = new Point();
						int i2 = ((OverlayItem) this.mItemList.get(paramInt))
								.getPoint().getLatitudeE6();
						int i3 = ((OverlayItem) this.mItemList.get(paramInt))
								.getPoint().getLongitudeE6();
						GeoPoint localGeoPoint2 = new GeoPoint(i2, i3);
						Point localPoint2 = localProjection.toPixels(
								localGeoPoint2, localPoint1);
						OverlayView localObject1 = (OverlayView) this.mOverlayView
								.findViewById(R.id.overlay_view);
						int i4 = (int) UIUtils.dip2Px(this.mContext, 250.0F);
						int i = (int) UIUtils.dip2Px(this.mContext, 100.0F);
						if (localPoint1.x >= 0) {
							int i36 = localPoint1.x;
							int i37 = this.mMapView.getWidth();
							if (i36 > i37) {
								int i38 = -i4 / 2;
								int i39 = localPoint1.x;
								int i40 = this.mMapView.getWidth();
								int i41 = i39 - i40;
								int i42 = i38 - i41;
								this.mOffsetX = i42;
							}
							int i43 = localPoint1.x;
							int i44 = i4 / 2;
							if (i43 - i44 < 0) {
								int i45 = i4 / 2;
								int i46 = localPoint1.x;
								int i47 = i45 - i46;
								this.mOffsetX = i47;
							}
							int i48 = localPoint1.x;
							int i49 = i4 / 2;
							int i50 = i48 + i49;
							int i51 = this.mMapView.getWidth();
							if (i50 > i51) {
								int i52 = this.mMapView.getWidth();
								int i53 = localPoint1.x;
								int i54 = i52 - i53;
								int i55 = i4 / 2;
								int i56 = i54 - i55;
								this.mOffsetX = i56;
							}
							this.mOffsetX = 0;
						}
						int i5 = i4 / 2;
						int i6 = localPoint1.x;
						int i7 = i5 - i6;
						this.mOffsetX = i7;
						int i8 = localPoint1.y;
						int i9 = i / 2;
						if (i8 - i9 >= 0) {
							this.mOffsetY = 0.0F;
						}
						float f1 = UIUtils.dip2Px(this.mContext, 72.0F);
						this.mOffsetY = f1;
						if (this.mOffsetY != 0.0F) {
							int i11 = 0;
						}
						int i10 = 1;
						localView1 = this.mOverlayView.findViewById(R.id.content_view);
						RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams) localView1
								.getLayoutParams();
						RelativeLayout.LayoutParams localObject2 = (RelativeLayout.LayoutParams) ((OverlayView) localObject1)
								.getLayoutParams();
						if (i10 == 0) {
							int i57 = localLayoutParams1.width;
							int i58 = localLayoutParams1.height;
							RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(
									i57, i58);
							localLayoutParams4.addRule(12);
							localView1.setLayoutParams(localLayoutParams4);
							int i59 = (int) UIUtils.dip2Px(this.mContext, 3.0F);
							int i60 = (int) UIUtils
									.dip2Px(this.mContext, 10.0F);
							localView1.setPadding(i59, 0, 0, i60);
							int i61 = ((RelativeLayout.LayoutParams) localObject2).width;
							int i62 = ((RelativeLayout.LayoutParams) localObject2).height;
							RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(
									i61, i62);
							localLayoutParams5.addRule(12);
							((OverlayView) localObject1)
									.setLayoutParams(localLayoutParams5);
						}
						int i12 = localLayoutParams1.width;
						int i13 = localLayoutParams1.height;
						RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(
								i12, i13);
						localLayoutParams2.addRule(10);
						localView1.setLayoutParams(localLayoutParams2);
						int i14 = (int) UIUtils.dip2Px(this.mContext, 3.0F);
						int i15 = (int) UIUtils.dip2Px(this.mContext, 3.0F);
						int i16 = (int) UIUtils.dip2Px(this.mContext, 9.0F);
						localView1.setPadding(i14, i15, 0, i16);
						int i17 = ((RelativeLayout.LayoutParams) localObject2).width;
						int i18 = ((RelativeLayout.LayoutParams) localObject2).height;
						RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(
								i17, i18);
						localLayoutParams3.addRule(10);
						((OverlayView) localObject1)
								.setLayoutParams(localLayoutParams3);
						int i19 = localLayoutParams.x;
						int i20 = this.mOffsetX;
						int i21 = i19 + i20;
						localLayoutParams.x = i21;
						float f2 = localLayoutParams.y;
						float f3 = this.mOffsetY;
						int i22 = (int) (f2 + f3);
						localLayoutParams.y = i22;
						int i23 = localPoint1.x;
						int i24 = i4 / 2;
						int i25 = i23 - i24;
						int i26 = this.mOffsetX;
						int i27 = i25 + i26;
						int i28 = localPoint1.x;
						int i29 = i4 / 2;
						int i30 = i28 + i29;
						int i31 = this.mOffsetX;
						int i32 = i30 + i31;
						int i33 = localPoint1.x;
						int i34 = localView1.getWidth();
						int i35 = localView1.getHeight();
						((OverlayView) localObject1).refreshView(i27, i32, i33,
								true, i34, i35);
						MapView localMapView = this.mMapView;
						View localView2 = this.mOverlayView;
						localMapView.updateViewLayout(localView2,
								localLayoutParams);
						((OverlayView) localObject1).setVisibility(0);
						this.mOverlayView.setVisibility(0);
						Bitmap localBitmap2 = BitmapFactory.decodeResource(
								this.mContext.getResources(), R.drawable.defaut_cell_pic);
						((ImageView) this.mOverlayView.findViewById(R.id.overlay_pic))
								.setImageBitmap(localBitmap2);
					}
				}
			}
		}

		catch (Exception localException) {
			Object localObject1;
			Object localObject2;
			Point localPoint1;
			int i4;
			RelativeLayout.LayoutParams localLayoutParams1;
			Bitmap localBitmap3 = BitmapFactory.decodeResource(
					this.mContext.getResources(), R.drawable.defaut_cell_pic);
			((ImageView) this.mOverlayView.findViewById(R.id.overlay_pic))
					.setImageBitmap(localBitmap3);
			String str6 = this.mContext.getString(R.string.rent_average_price);
			Object[] arrayOfObject2 = new Object[1];
			Integer localInteger2 = Integer.valueOf(((Community) this.mCellList
					.get(paramInt)).mPrice);
			arrayOfObject2[0] = localInteger2;
			String str7 = String.format(str6, arrayOfObject2);
			/*StringBuffer localStringBuffer4 = ((StringBuffer) localObject2)
					.append(str7);*/
		}
		return super.onTap(paramInt);
	}

	public boolean onTap(GeoPoint paramGeoPoint, MapView paramMapView)
	  {
	    this.mHasClickCellView = false;
	    this.mOverlayView.setVisibility(8);
	    OverlayView localOverlayView = (OverlayView)this.mOverlayView.findViewById(R.id.overlay_view);
	    localOverlayView.setVisibility(8);
	    localOverlayView.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View paramView)
	    	  {
	    	    SharedPreferences.Editor localEditor = RentMapOverlayItem.this.mContext.getSharedPreferences("rent_setting", 0).edit();
	    	    int i = RentMapOverlayItem.this.mMapView.getZoomLevel();
	    	    boolean bool1 = localEditor.putInt("zoom_value", i).commit();
	    	    RentMapOverlayItem.this.mOverlayView.setVisibility(8);
//	    	    boolean bool2 = RentMapOverlayItem.access$302(this.this$0, 1);
	    	    Context localContext = RentMapOverlayItem.this.mContext;
	    	    Intent localIntent1 = new Intent(localContext, ResultActivity.class);
	    	    Bundle localBundle = new Bundle();
	    	    localBundle.putInt("searchformat", 0);
	    	    ArrayList localArrayList1 = RentMapOverlayItem.this.mCellList;
	    	    int j = RentMapOverlayItem.this.mClickOnIndex;
	    	    String str1 = ((Community)localArrayList1.get(j)).mName;
	    	    localBundle.putString("keyword", str1);
	    	    long l = ((Community)localArrayList1.get(j)).mGroupId;
	    	    localBundle.putLong("group_id", l);
	    	    String str2 = ((Community)localArrayList1.get(j)).mCity;
	    	    localBundle.putString("city", str2);
	    	    int i1 = ((Community)localArrayList1.get(j)).mPrice;
	    	    localBundle.putInt("price", i1);
	    	    int i3 = ((Community)localArrayList1.get(j)).mSourceCount;
	    	    localBundle.putInt("sourcecoount", i3);
	    	    double d1 = ((Community)localArrayList1.get(j)).mLat;
	    	    localBundle.putDouble("latitude", d1);
	    	    double d2 = ((Community)localArrayList1.get(j)).mLon;
	    	    localBundle.putDouble("longitude", d2);
	    	    Intent localIntent2 = localIntent1.putExtras(localBundle);
	    	    RentMapOverlayItem.this.mContext.startActivity(localIntent1);
	    	  }
		});
	    if (!this.mHasClickCellView);
	    for (boolean bool = super.onTap(paramGeoPoint, paramMapView); ; bool = true)
	      return bool;
	  }

	public void showPopup(int paramInt) {
		initTapClick();
		boolean bool = onTap(paramInt);
	}

	public int size() {
		return this.mItemList.size();
	}

}
