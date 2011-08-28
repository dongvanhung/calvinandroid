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
			int i = BitmapFactory.decodeResource(getResources(), R.drawable.left)
					.getWidth();
			this.mSideLength = i;
			int j = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_up)
					.getWidth();
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
			return;
		} catch (Exception localException) {
			Log.e("error", localException.getCause().getMessage());
		}
	}

	public void refreshView(int paramInt1, int paramInt2, int paramInt3,
			boolean paramBoolean, int paramInt4, int paramInt5) {
		int i = 0;
		int j = 0;
		if (paramBoolean) {
			this.mArrowView.setImageResource(R.drawable.arrow_down);
			setGravity(49);
			if (paramInt3 >= paramInt1) {
				i = this.mSideLength + paramInt1;
				j = this.mArrowLength / 2;
			} else {
				int k = paramInt3;
					if (k > paramInt2) {
						int m = this.mSideLength;
						int n = paramInt2 - m;
						int i1 = this.mArrowLength / 2;
						k = n - i1;
					}
					int i2 = k - paramInt1;
					int i3 = this.mSideLength;
					int i4 = i2 - i3;
					int i5 = this.mArrowLength / 2;
					int i6 = i4 - i5;
					int i7 = paramInt2 - k;
					int i8 = this.mSideLength;
					int i9 = i7 - i8;
					int i10 = this.mArrowLength / 2;
					int i11 = i9 - i10;
					int i12 = (int) UIUtils.dip2Px(this.mContext, 16.0F);
					int i13 = i6 + i12;
					k = i11 - i12;
					if (i13 <= 0)
						i13 = 0;
					if (k <= 0)
						k = 0;
					ImageView localImageView1 = this.mLeftView;
					LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(
							i13, -1, 0.0F);
					localImageView1.setLayoutParams(localLayoutParams1);
					ImageView localImageView2 = this.mRightView;
					LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(
							k, -1, 0.0F);
					localImageView2.setLayoutParams(localLayoutParams2);
					//
			}
			this.mArrowView.setImageResource(R.drawable.arrow_up);
			setGravity(81);
		}
	}
}
