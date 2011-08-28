package com.rent.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.maps.MapView;
import com.rent.UIUtils;

public class RentMapView extends MapView {

	private float mLastX;
	private float mLastY;
	private MapMoveListener mListener = null;
	private boolean mNeedRefresh = false;

	public RentMapView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public RentMapView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	public RentMapView(Context paramContext, String paramString) {
		super(paramContext, paramString);
	}

	public void draw(Canvas paramCanvas) {
		try {
			super.draw(paramCanvas);
			return;
		} catch (Exception localException) {
			while (true) {
				String str = localException.toString();
				int i = Log.e("RentMapView", str);
			}
		}
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		int i = (int) UIUtils.dip2Px(getContext(), 20.0F);
		if (paramMotionEvent.getAction() == 0) {
			float f1 = paramMotionEvent.getX();
			this.mLastX = f1;
			float f2 = paramMotionEvent.getY();
			this.mLastY = f2;
		}
		if (paramMotionEvent.getAction() == 2) {
			this.mNeedRefresh = true;
			this.mListener.hideOverlay();
		}
		if ((this.mNeedRefresh) && (paramMotionEvent.getAction() == 1)) {
			float f3 = this.mLastX;
			float f4 = paramMotionEvent.getX();
			float f5 = Math.abs(f3 - f4);
			float f6 = i;
			if (f5 <= f6) {
				float f7 = this.mLastY;
				float f8 = paramMotionEvent.getY();
				float f9 = Math.abs(f7 - f8);
				float f10 = i;
				if (f9 <= f10)
					;
			} else {
				this.mNeedRefresh = false;
				this.mListener.mapMoved();
			}
		}
		return super.onTouchEvent(paramMotionEvent);
	}

	public void setMoveListener(MapMoveListener paramMapMoveListener) {
		this.mListener = paramMapMoveListener;
	}

	public abstract interface MapMoveListener {
		public abstract void hideOverlay();

		public abstract void mapMoved();
	}
}
