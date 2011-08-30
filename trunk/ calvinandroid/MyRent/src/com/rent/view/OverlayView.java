package com.rent.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rent.R;
import com.rent.UIUtils;

public class OverlayView extends LinearLayout {

	private ImageView mArrowLeft;
	private int mArrowLength;
	private ImageView mArrowRight;
	private ImageView mArrowView;
	private final Context mContext;
	private ImageView mLeftView;
	private ImageView mRightView;
	private int mSideLength;

	public OverlayView(Context paramContext) {
		super(paramContext);
		this.mContext = paramContext;
		init();
	}

	public OverlayView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.mContext = paramContext;
		init();
	}

	private void init() {
		setOrientation(0);
		Context localContext1 = this.mContext;
		ImageView localImageView1 = new ImageView(localContext1);
		this.mArrowLeft = localImageView1;
		this.mArrowLeft.setImageResource(R.drawable.left);
		Context localContext2 = this.mContext;
		ImageView localImageView2 = new ImageView(localContext2);
		this.mLeftView = localImageView2;
		this.mLeftView.setImageResource(R.drawable.one_bg);
		ImageView localImageView3 = this.mLeftView;
		ImageView.ScaleType localScaleType1 = ImageView.ScaleType.FIT_XY;
		localImageView3.setScaleType(localScaleType1);
		Context localContext3 = this.mContext;
		ImageView localImageView4 = new ImageView(localContext3);
		this.mArrowView = localImageView4;
		Context localContext4 = this.mContext;
		ImageView localImageView5 = new ImageView(localContext4);
		this.mRightView = localImageView5;
		this.mRightView.setImageResource(R.drawable.one_bg);
		ImageView localImageView6 = this.mRightView;
		ImageView.ScaleType localScaleType2 = ImageView.ScaleType.FIT_XY;
		localImageView6.setScaleType(localScaleType2);
		Context localContext5 = this.mContext;
		ImageView localImageView7 = new ImageView(localContext5);
		this.mArrowRight = localImageView7;
		this.mArrowRight.setImageResource(R.drawable.right);
		try {
			int i = BitmapFactory.decodeResource(getResources(),
					R.drawable.left).getWidth();
			this.mSideLength = i;
			int j = BitmapFactory.decodeResource(getResources(),
					R.drawable.arrow_up).getWidth();
			this.mArrowLength = j;
			ImageView localImageView8 = this.mArrowLeft;
			LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(
					-1, -1, 0.0F);
			addView(localImageView8, localLayoutParams1);
			ImageView localImageView9 = this.mLeftView;
			LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(
					-1, -1, 0.0F);
			addView(localImageView9, localLayoutParams2);
			ImageView localImageView10 = this.mArrowView;
			LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(
					-1, -1, 0.0F);
			addView(localImageView10, localLayoutParams3);
			ImageView localImageView11 = this.mRightView;
			LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(
					-1, -1, 0.0F);
			addView(localImageView11, localLayoutParams4);
			ImageView localImageView12 = this.mArrowRight;
			LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(
					-1, -1, 0.0F);
			addView(localImageView12, localLayoutParams5);
		} catch (Exception localException) {
			Log.e("error", localException.getCause().getMessage());
		}
	}

	public void refreshView(int i, int j, int k, boolean flag, int l, int i1) {
		int l1;
		int l2;
		int i3;
		int j3;
		int k3;
		int l3;
		int i4;
		int j4;
		int k4;
		int l4;
		int i5;
		int j5;
		int k5;
		ImageView imageview;
		android.widget.LinearLayout.LayoutParams layoutparams;
		ImageView imageview1;
		android.widget.LinearLayout.LayoutParams layoutparams1;
		if (flag) {
			mArrowView.setImageResource(R.drawable.arrow_down);
			setGravity(49);
		} else {
			mArrowView.setImageResource(R.drawable.arrow_up);
			setGravity(81);
		}
		if (k < i) {
			int j1 = mSideLength + i;
			int k1 = mArrowLength / 2;
			l1 = j1 + k1;
		} else {
			l1 = k;
		}
		if (l1 > j) {
			int i2 = mSideLength;
			int j2 = j - i2;
			int k2 = mArrowLength / 2;
			l1 = j2 - k2;
		}
		l2 = l1 - i;
		i3 = mSideLength;
		j3 = l2 - i3;
		k3 = mArrowLength / 2;
		l3 = j3 - k3;
		i4 = j - l1;
		j4 = mSideLength;
		k4 = i4 - j4;
		l4 = mArrowLength / 2;
		i5 = k4 - l4;
		j5 = (int) UIUtils.dip2Px(mContext, 16F);
		k5 = l3 + j5;
		l1 = i5 - j5;
		if (k5 <= 0)
			k5 = 0;
		if (l1 <= 0)
			l1 = 0;
		/*imageview = mLeftView;
		layoutparams = new android.widget.LinearLayout.LayoutParams(k5, -1, 0F);
		imageview.setLayoutParams(layoutparams);*/
		/*imageview1 = mRightView;
		layoutparams1 = new android.widget.LinearLayout.LayoutParams(l1, -1, 0F);
		imageview1.setLayoutParams(layoutparams1);*/
	}
}
