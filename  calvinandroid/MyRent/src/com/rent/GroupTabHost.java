package com.rent;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GroupTabHost extends ImageView {

	private final ArrayList<GroupTab> mTabs;
	private final Paint mPaint;
	private int mActiveTab = 0;
	private final Paint mActiveTextPaint;
	private final Paint mInactiveTextPaint;
	private GroupListener mViewChangeListener;

	public GroupTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTabs = new ArrayList<GroupTab>();
		mPaint = new Paint();
		
		mActiveTextPaint = new Paint();
		Paint.Align localAlign1 = Paint.Align.CENTER;
		float f1 = UIUtils.dip2Px(getContext(), 12.0F);
		mActiveTextPaint.setTextAlign(localAlign1);
		mActiveTextPaint.setTextSize(f1);
		this.mActiveTextPaint.setColor(-1);
		this.mActiveTextPaint.setAntiAlias(true);
		
		mInactiveTextPaint = new Paint();
		Paint.Align localAlign2 = Paint.Align.CENTER;
		float f2 = UIUtils.dip2Px(getContext(), 12.0F);
		this.mInactiveTextPaint.setColor(-6710887);
	    this.mInactiveTextPaint.setAntiAlias(true);
	    this.mInactiveTextPaint.setTextSize(f2);
	    this.mInactiveTextPaint.setTextAlign(localAlign2);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		try {
			Rect localRect = new Rect();
			getDrawingRect(localRect);
			int i = localRect.right;
			int k = 0;
			k = i / this.mTabs.size();
			for (int j = this.mTabs.size(); j >= 1; j--) {
				Canvas localCanvas1 = paramCanvas;
				localCanvas1.drawColor(-16777216);
				this.mPaint.setColor(-12369085);
				this.mPaint.setAntiAlias(true);
				float f2 = localRect.left;
				float f3 = localRect.top + 1;
				float f4 = localRect.right;
				float f5 = localRect.top + 1;
				Paint localPaint1 = this.mPaint;
				paramCanvas.drawLine(f2, f3, f4, f5, localPaint1);
				int n = 0;
				int i2;
				for (int i1 = 46; n < 24; i1 = i2) {
					this.mPaint.setARGB(255, i1, i1, i1);
					float f6 = localRect.left;
					float f7 = localRect.top + n + 1;
					float f8 = localRect.right;
					float f9 = localRect.top + n + 2;
					Paint localPaint2 = this.mPaint;
					paramCanvas.drawRect(f6, f7, f8, f9, localPaint2);
					i2 = i1 + -1;
					n += 1;
				}
			}

			for (int j = 0; j < this.mTabs.size(); j++) {
				GroupTab localGroupTab = (GroupTab) this.mTabs.get(j);
				Resources localResources1 = getResources();
				int i8 = localGroupTab.getActiveIconId();
				Bitmap localBitmap1 = BitmapFactory.decodeResource(localResources1,
						i8);
				Resources localResources2 = getResources();
				int i9 = localGroupTab.getInactiveIconId();
				Bitmap localBitmap2 = BitmapFactory.decodeResource(localResources2,
						i9);
				int i10 = localBitmap1.getWidth();
				int i11 = localBitmap1.getHeight();
				Bitmap.Config localConfig1 = Bitmap.Config.ARGB_8888;
				Bitmap localBitmap3 = Bitmap.createBitmap(i10, i11, localConfig1);
				Paint localPaint3 = new Paint(1);
				localPaint3.setAntiAlias(true);
				Canvas localCanvas2 = new Canvas();
				localCanvas2.setBitmap(localBitmap3);
				String str1;
				float f1;
				float f24;
				int i18 = k * j;
				int i19 = k / 2;
				int i20 = i10 / 2;
				int i21 = i19 - i20;
				i = i18 + i21;

				float f20 = i;
				float f21 = localRect.top + 5;
				Paint localPaint10 = null;
				
				str1 = localGroupTab.getText();
				int i30 = k * j;
				int i31 = k / 2;
				f1 = i30 + i31;
				f24 = localRect.bottom - 2;
				
				if (j == this.mActiveTab) {
					/*Shader.TileMode localTileMode1 = Shader.TileMode.CLAMP;
					LinearGradient localLinearGradient1 = new LinearGradient(0.0F,
							0.0F, i10, i11, -1, -11221023, localTileMode1);
					Shader localShader1 = localPaint3
							.setShader(localLinearGradient1);*/
					localCanvas2.drawRect(0.0F, 0.0F, i10, i11, localPaint3);

					this.mPaint.setARGB(37, 255, 255, 255);
					this.mPaint.setAntiAlias(true);
					int i24 = localRect.left;
					int i25 = k * j;
					float f14 = i24 + i25 + 4;
					float f15 = localRect.top + 4;
					int i26 = localRect.left;
					int i27 = (j + 1) * k;
					float f16 = i26 + i27 - 2;
					float f17 = localRect.bottom - 2;
					RectF localRectF1 = new RectF(f14, f15, f16, f17);
					float f18 = 5.0F;
					float f19 = 5.0F;
					paramCanvas.drawRoundRect(localRectF1, f18, f19, this.mPaint);
					paramCanvas.drawBitmap(localBitmap1, f20, f21, localPaint10);
					paramCanvas.drawText(str1, f1, f24, this.mActiveTextPaint);
				} else {
					paramCanvas.drawBitmap(localBitmap2, f20, f21, localPaint10);
					paramCanvas.drawText(str1, f1, f24, this.mInactiveTextPaint);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		Rect localRect = new Rect();
		getDrawingRect(localRect);
		int i = localRect.right;
		if (this.mTabs.size() != 0) {
			for (int j = this.mTabs.size();;) {
				float f1 = i / j;
				float f2 = paramMotionEvent.getX() / f1;
				float f3 = paramMotionEvent.getX() / f1 % 1.0F;
				i = (int) (f2 - f3);
				this.mActiveTab = i;
				if (this.mViewChangeListener != null)
					this.mViewChangeListener.sendVisableId(i);
				invalidate();
			}
		}
		return super.onTouchEvent(paramMotionEvent);
	}

	public void setActiveTab(int paramInt) {
		this.mActiveTab = paramInt;
		invalidate();
	}

	public void addTab(GroupTab paramGroupTab) {
		this.mTabs.add(paramGroupTab);
	}

	public void setListener(GroupListener paramGroupListener) {
		this.mViewChangeListener = paramGroupListener;
	}

	public void setTabName(String paramString, int paramInt) {
		int i = this.mTabs.size();
		if (paramInt < i) {
			((GroupTab) this.mTabs.get(paramInt)).mText = paramString;
			invalidate();
		}
	}

	public void fireTabChanged() {
		if (this.mViewChangeListener != null) {
			GroupListener localGroupListener = this.mViewChangeListener;
			int i = this.mActiveTab;
			localGroupListener.sendVisableId(i);
		}
	}
}
