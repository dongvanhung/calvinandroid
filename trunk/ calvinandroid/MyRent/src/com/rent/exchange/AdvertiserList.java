package com.rent.exchange;

import java.util.List;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.rent.adapter.AdvertiserAdapter;
import com.rent.exchange.common.ExchangeConstants;
import com.rent.exchange.model.AdvertiserConfig;

public class AdvertiserList {

	public ListClickListener advertiserListener = null;
	View curItem;
	int curY;
	int duration = 150;
	private boolean firstDown = true;
	int lastY;
	View.OnTouchListener listViewOnTouchListener;
	int mBannerId;
	Context mContext;
	ListView mListview;
	boolean mShowHeader;
	int mType;
	private int totalMove = 0;
	int touchPosition;

	public AdvertiserList(ListView paramListView, Context paramContext,
			int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3) {
		this.listViewOnTouchListener = new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				return true;
				/*
				 * int i; switch (paramMotionEvent.getAction()) { default: i =
				 * 0; case 0: case 2: case 1: } int i16; AdvertiserConfig
				 * localAdvertiserConfig1; while (true) { return i;
				 * AdvertiserList localAdvertiserList1 = this.this$0; int j =
				 * (int)paramMotionEvent.getY(); localAdvertiserList1.lastY = j;
				 * AdvertiserList.access$0(this.this$0, 0);
				 * AdvertiserList.access$1(this.this$0, 0); AdvertiserList
				 * localAdvertiserList2 = this.this$0; ListView localListView1 =
				 * this.this$0.mListview; int k = (int)paramMotionEvent.getX();
				 * int m = (int)paramMotionEvent.getY(); int n =
				 * localListView1.pointToPosition(k, m);
				 * localAdvertiserList2.touchPosition = n; AdvertiserList
				 * localAdvertiserList3 = this.this$0; int i1 =
				 * localAdvertiserList3.touchPosition; int i2 =
				 * this.this$0.mListview.getFirstVisiblePosition(); int i3 = i1
				 * - i2; localAdvertiserList3.touchPosition = i3; if
				 * (this.this$0.touchPosition < 0) { i = 0; continue; } if
				 * ((this.this$0.mShowHeader) && (this.this$0.touchPosition ==
				 * 0)) { i = 0; continue; } AdvertiserList localAdvertiserList4
				 * = this.this$0; ListView localListView2 =
				 * this.this$0.mListview; int i4 = this.this$0.touchPosition;
				 * View localView1 = localListView2.getChildAt(i4);
				 * localAdvertiserList4.curItem = localView1; View localView2 =
				 * this.this$0.curItem; int i5 = IdMapper.background(); View
				 * localView3 = localView2.findViewById(i5); if (localView3 ==
				 * null) { this.this$0.curItem = null; i = 0; continue; } View
				 * localView4 = localView3; int i6 = 0;
				 * localView4.setVisibility(i6); i = 0; continue; if
				 * ((this.this$0.mShowHeader) && (this.this$0.touchPosition ==
				 * 0)) { i = 0; continue; } if
				 * (AdvertiserList.access$2(this.this$0)) { AdvertiserList
				 * localAdvertiserList5 = this.this$0; int i7 =
				 * (int)paramMotionEvent.getY(); localAdvertiserList5.curY = i7;
				 * AdvertiserList.access$0(this.this$0, 0);
				 * AdvertiserList.access$1(this.this$0, 0); } AdvertiserList
				 * localAdvertiserList6 = this.this$0; int i8 =
				 * (int)paramMotionEvent.getY(); localAdvertiserList6.curY = i8;
				 * int i9 = this.this$0.curY; int i10 = this.this$0.lastY; int
				 * i11 = i9 - i10; AdvertiserList localAdvertiserList7 =
				 * this.this$0; int i12 =
				 * AdvertiserList.access$3(localAdvertiserList7) + i11;
				 * AdvertiserList.access$0(localAdvertiserList7, i12);
				 * AdvertiserList localAdvertiserList8 = this.this$0; int i13 =
				 * this.this$0.curY; localAdvertiserList8.lastY = i13; i = 0;
				 * continue; if (this.this$0.touchPosition >= 0) { int i14 =
				 * this.this$0.touchPosition; int i15 =
				 * this.this$0.mListview.getCount(); if (i14 < i15); } else { i
				 * = 0; continue; } if ((this.this$0.mShowHeader) &&
				 * (this.this$0.touchPosition == 0)) { i = 0; continue; } i16 =
				 * 0; if (this.this$0.curItem == null) { i = 0; continue; } View
				 * localView5 = this.this$0.curItem; int i17 =
				 * IdMapper.background(); localView3 =
				 * localView5.findViewById(i17); if (localView3 == null) { i =
				 * 0; continue; } View localView6 = localView3; int i18 = 8;
				 * localView6.setVisibility(i18); if
				 * (Math.abs(AdvertiserList.access$3(this.this$0)) >= 20) break;
				 * ListAdapter localListAdapter =
				 * this.this$0.mListview.getAdapter(); int i19 =
				 * this.this$0.mListview.getFirstVisiblePosition(); int i20 =
				 * this.this$0.touchPosition; int i21 = i19 + i20;
				 * localAdvertiserConfig1 =
				 * (AdvertiserConfig)localListAdapter.getItem(i21); if
				 * ((localAdvertiserConfig1 == null) ||
				 * (localAdvertiserConfig1.adName == null)) { i = i16; continue;
				 * } int i22 = 3; switch (this.this$0.mType) { default: case 0:
				 * case 1: case 8: case 7: case 9: case 2: case 3: case 4: case
				 * 5: case 6: } while (true) { if (DeviceManager.installedApps
				 * == null) break label971; Set localSet =
				 * DeviceManager.installedApps; String str1 =
				 * localAdvertiserConfig1.packageName; if
				 * (!localSet.contains(str1)) break label971; String str2 =
				 * ExchangeConstants.LOG_TAG; StringBuilder localStringBuilder1
				 * = new StringBuilder("already installed:"); String str3 =
				 * localAdvertiserConfig1.packageName; String str4 = str3; int
				 * i23 = Log.i(str2, str4); Context localContext1 =
				 * this.this$0.mContext; int i24 = this.this$0.mType; int i25 =
				 * this.this$0.touchPosition; new ReportThread(4, localContext1,
				 * localAdvertiserConfig1, i24, i22, i25).start();
				 * PackageManager localPackageManager1 =
				 * this.this$0.mContext.getPackageManager(); String str5 =
				 * localAdvertiserConfig1.packageName; PackageManager
				 * localPackageManager2 = localPackageManager1; String str6 =
				 * str5; Intent localIntent1 =
				 * localPackageManager2.getLaunchIntentForPackage(str6); Context
				 * localContext2 = this.this$0.mContext; Intent localIntent2 =
				 * localIntent1; localContext2.startActivity(localIntent2); i =
				 * 1; break; i22 = 3; continue; i22 = 1; continue; i22 = 2;
				 * continue; i22 = 2; continue; i22 = 3; continue; i22 = 3; }
				 * label971: Context localContext3 = this.this$0.mContext; int
				 * i26 = this.this$0.mType; int i27 = this.this$0.touchPosition;
				 * new ReportThread(2, localContext3, localAdvertiserConfig1,
				 * i26, i22, i27).start(); if
				 * (localAdvertiserConfig1.landingType == 3) { Uri localUri1 =
				 * Uri.parse(localAdvertiserConfig1.apk); Intent localIntent3 =
				 * new android/content/Intent; Intent localIntent4 =
				 * localIntent3; String str7 = "android.intent.action.VIEW"; Uri
				 * localUri2 = localUri1; localIntent4.<init>(str7, localUri2);
				 * Context localContext4 = this.this$0.mContext; Intent
				 * localIntent5 = localIntent3;
				 * localContext4.startActivity(localIntent5); i = 1; continue; }
				 * if (localAdvertiserConfig1.landingType == 2) { Uri localUri3
				 * = Uri.parse(localAdvertiserConfig1.apk); Intent localIntent6
				 * = new android/content/Intent; Intent localIntent7 =
				 * localIntent6; String str8 = "android.intent.action.VIEW"; Uri
				 * localUri4 = localUri3; localIntent7.<init>(str8, localUri4);
				 * Context localContext5 = this.this$0.mContext; Intent
				 * localIntent8 = localIntent6;
				 * localContext5.startActivity(localIntent8); i = 1; continue; }
				 * if (localAdvertiserConfig1.landingType == 1) { Context
				 * localContext6 = this.this$0.mContext; String str9 =
				 * localAdvertiserConfig1.apk; StringBuilder localStringBuilder2
				 * = new StringBuilder("正在下载应用"); String str10 =
				 * localAdvertiserConfig1.adName; String str11 = str10; int i28
				 * = this.this$0.mType; int i29 = this.this$0.touchPosition;
				 * AdvertiserConfig localAdvertiserConfig2 =
				 * localAdvertiserConfig1; DownloadAgent localDownloadAgent =
				 * new DownloadAgent(localContext6, str9, "正在下载应用",
				 * str11, "", localAdvertiserConfig2, i28, i29); i = 1;
				 * continue; } if (this.this$0.advertiserListener != null) break
				 * label1334; Context localContext7 = this.this$0.mContext; int
				 * i30 = this.this$0.mType; int i31 = this.this$0.touchPosition;
				 * DownloadDialog.getCustomeDialog(localContext7,
				 * localAdvertiserConfig1, i30, i31).show(); } while (true) {
				 * i16 = 1; AdvertiserList.access$0(this.this$0, 0); i = i16;
				 * break; label1334:
				 * this.this$0.advertiserListener.click(localAdvertiserConfig1);
				 * }
				 */
			}
		};
		this.mListview = paramListView;
		this.mBannerId = paramInt1;
		this.mContext = paramContext;
		this.mShowHeader = paramBoolean;
		this.mType = paramInt3;
		List localList1 = ExchangeDataService.mAdvertisers;
		int i = ExchangeDataService.mAdvertisers.size();
		int j;
		List localList2;
		int k = 0;
		if (paramInt2 > i) {
			j = ExchangeDataService.mAdvertisers.size();
			localList2 = localList1.subList(0, j);
			if ((paramInt3 == 8) && (ExchangeConstants.show_at_least_seven))
				k = 0;
			int m = 7 - paramInt2;
			if (k >= m) {
				Context localContext = paramContext;
				int n = paramInt1;
				boolean bool1 = paramBoolean;
				AdvertiserAdapter localAdvertiserAdapter = new AdvertiserAdapter(
						localContext, 17367045, localList2, n, bool1);
				paramListView.setAdapter(localAdvertiserAdapter);
				paramListView.setItemsCanFocus(true);
				View.OnTouchListener localOnTouchListener = this.listViewOnTouchListener;
				paramListView.setOnTouchListener(localOnTouchListener);
			} else {
				j = paramInt2;
			}
			AdvertiserConfig localAdvertiserConfig = new AdvertiserConfig();
			boolean bool2 = localList2.add(localAdvertiserConfig);
			k += 1;
		}
	}

	public AdvertiserList(ListView paramListView, Context paramContext,
			int paramInt1, boolean paramBoolean,
			List<AdvertiserConfig> paramList, int paramInt2) {
		this.listViewOnTouchListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		};
		this.mListview = paramListView;
		this.mBannerId = paramInt1;
		this.mContext = paramContext;
		this.mShowHeader = paramBoolean;
		this.mType = paramInt2;
		Context localContext = paramContext;
		List<AdvertiserConfig> localList = paramList;
		int i = paramInt1;
		boolean bool = paramBoolean;
		AdvertiserAdapter localAdvertiserAdapter = new AdvertiserAdapter(
				localContext, 17367045, localList, i, bool);
		paramListView.setAdapter(localAdvertiserAdapter);
		paramListView.setItemsCanFocus(true);
		View.OnTouchListener localOnTouchListener = this.listViewOnTouchListener;
		paramListView.setOnTouchListener(localOnTouchListener);
	}
}
