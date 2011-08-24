package com.rent.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class BasePanel extends LinearLayout {

	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	private static final String TAG = "Panel2";
	public static final int TOP = 0;
	TranslateAnimation animation;
	private Animation.AnimationListener animationListener;
	public View blurLayout;
	public boolean isBanner = false;
	public boolean mAnimating = false;
	public Drawable mClosedHandle;
	public View mContent;
	private int mContentHeight;
	private int mContentWidth;
	private int mDuration;
	private GestureDetector mGestureDetector;
	private PanelOnGestureListener mGestureListener;
	public View mHandle;
	private Interpolator mInterpolator;
	public boolean mIsShrinking;
	private boolean mLinearFlying;
	public Drawable mOpenedHandle;
	private int mOrientation;
	private int mPosition;
	private State mState;
	private float mTrackX;
	private float mTrackY;
	private float mVelocity;
	public boolean opened = false;
	private OnPanelListener panelListener;
	Runnable startAnimation;
	public int startX = -1;
	public int startY = -1;
	View.OnTouchListener touchListener;

	public BasePanel(Context paramContext, int paramInt, boolean paramBoolean) {
		super(paramContext);
		this.touchListener = new OnTouchListener() {
			int initX;
			int initY;
			boolean setInitialPosition;

			public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
				return true;
				/*
				 * StringBuilder localStringBuilder1 = new
				 * StringBuilder("state: "); BasePanel.State localState =
				 * BasePanel.access$0(this.this$0); StringBuilder
				 * localStringBuilder2 =
				 * localStringBuilder1.append(localState).append(" x: "); float
				 * f1 = paramMotionEvent.getX(); StringBuilder
				 * localStringBuilder3 =
				 * localStringBuilder2.append(f1).append(" y: "); float f2 =
				 * paramMotionEvent.getY(); String str = f2; int i =
				 * Log.d("Panel2", str); int j = paramMotionEvent.getAction();
				 * int k; if (j == 0) { this.initX = 0; this.initY = 0; if
				 * (!this.this$0.opened) { if (BasePanel.access$1(this.this$0)
				 * != 1) break label199; if (BasePanel.access$2(this.this$0) ==
				 * 0) { k = -1; this.initY = k; } } else {
				 * this.setInitialPosition = 1; } } while (true) { if
				 * ((!BasePanel
				 * .access$6(this.this$0).onTouchEvent(paramMotionEvent)) && (j
				 * == 1)) { BasePanel localBasePanel = this.this$0; Runnable
				 * localRunnable = this.this$0.startAnimation; boolean bool =
				 * localBasePanel.post(localRunnable); } return false; k = 1;
				 * break; label199: if (BasePanel.access$2(this.this$0) == 2);
				 * for (k = -1; ; k = 1) { this.initX = k; break; } if
				 * (this.setInitialPosition) { this.this$0.hideBlur(); int m =
				 * this.initX; int n = BasePanel.access$3(this.this$0); int i1 =
				 * m * n; this.initX = i1; int i2 = this.initY; int i3 =
				 * BasePanel.access$4(this.this$0); int i4 = i2 * i3; this.initY
				 * = i4; BasePanel.PanelOnGestureListener
				 * localPanelOnGestureListener =
				 * BasePanel.access$5(this.this$0); int i5 = this.initX; int i6
				 * = this.initY; localPanelOnGestureListener.setScroll(i5, i6);
				 * this.setInitialPosition = 0; int i7 = -this.initX; this.initX
				 * = i7; int i8 = -this.initY; this.initY = i8; } float f3 =
				 * this.initX; float f4 = this.initY;
				 * paramMotionEvent.offsetLocation(f3, f4); }
				 */
			}
		};
		this.startAnimation = new Runnable() {
			public void run() {
				/*
				 * if (this.this$0.opened) this.this$0.hideBlur();
				 * this.this$0.mHandle.setVisibility(0); this.this$0.mAnimating
				 * = 1; int i = 0; int j = 0; int k = 0; int m = 0;
				 * BasePanel.State localState1 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState2
				 * = BasePanel.State.FLYING; BasePanel localBasePanel1; int n;
				 * int i1; label113: int i2; label171: int i3; if (localState1
				 * == localState2) { localBasePanel1 = this.this$0; if
				 * ((BasePanel.access$2(this.this$0) != 0) &&
				 * (BasePanel.access$2(this.this$0) != 2)) { n = 0; if
				 * (BasePanel.access$7(this.this$0) <= 0.0F) break label433; i1
				 * = 1; boolean bool = n ^ i1; localBasePanel1.mIsShrinking =
				 * bool; } } else { if (BasePanel.access$1(this.this$0) != 1)
				 * break label518; i2 = BasePanel.access$4(this.this$0); if
				 * (this.this$0.mIsShrinking) break label445; if
				 * (BasePanel.access$2(this.this$0) != 0) break label439; k =
				 * -i2; BasePanel.State localState3 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState4
				 * = BasePanel.State.TRACKING; if (localState3 != localState4)
				 * break label477; float f1 = BasePanel.access$8(this.this$0);
				 * float f2 = k; float f3 = Math.abs(f1 - f2); float f4 =
				 * BasePanel.access$8(this.this$0); float f5 = m; float f6 =
				 * Math.abs(f4 - f5); if (f3 < f6) { localBasePanel1 =
				 * this.this$0; if (!this.this$0.mIsShrinking) break label470; n
				 * = 0; label267: localBasePanel1.mIsShrinking = n; m = k; } k =
				 * (int)BasePanel.access$8(this.this$0); label286:
				 * BasePanel.State localState5 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState6
				 * = BasePanel.State.FLYING; if ((localState5 != localState6) ||
				 * (!BasePanel.access$9(this.this$0))) break label510; float f7
				 * = m - k; float f8 = BasePanel.access$7(this.this$0); i3 =
				 * Math.max((int)(Math.abs(f7 / f8) * 1000.0F), 20); } while
				 * (true) { BasePanel localBasePanel2 = this.this$0;
				 * BasePanel.access$12(this.this$0, 0.0F);
				 * BasePanel.access$13(localBasePanel2, 0.0F); if (i3 != 0)
				 * break label846; BasePanel localBasePanel3 = this.this$0;
				 * BasePanel.State localState7 = BasePanel.State.READY;
				 * BasePanel.access$14(localBasePanel3, localState7); if
				 * (this.this$0.mIsShrinking)
				 * this.this$0.mContent.setVisibility(8);
				 * BasePanel.access$15(this.this$0); return; n = 1; break;
				 * label433: i1 = 0; break label113; label439: k = i2; break
				 * label171; label445: if (BasePanel.access$2(this.this$0) ==
				 * 0); for (m = -i2; ; m = i2) break; label470: n = 1; break
				 * label267; label477: BasePanel.State localState8 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState9
				 * = BasePanel.State.FLYING; if (localState8 != localState9)
				 * break label286; k = (int)BasePanel.access$8(this.this$0);
				 * break label286; label510: i3 = 1000; continue; label518: int
				 * i4 = BasePanel.access$3(this.this$0); if
				 * (!this.this$0.mIsShrinking) if
				 * (BasePanel.access$2(this.this$0) == 2) { i = -i4; label552:
				 * BasePanel.State localState10 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState11
				 * = BasePanel.State.TRACKING; if (localState10 != localState11)
				 * break label770; float f9 = BasePanel.access$10(this.this$0);
				 * float f10 = i; float f11 = Math.abs(f9 - f10); float f12 =
				 * BasePanel.access$10(this.this$0); float f13 = j; float f14 =
				 * Math.abs(f12 - f13); if (f11 < f14) { localBasePanel1 =
				 * this.this$0; if (!this.this$0.mIsShrinking) break label763; n
				 * = 0; label647: localBasePanel1.mIsShrinking = n; j = i; } i =
				 * (int)BasePanel.access$10(this.this$0); } while (true) {
				 * BasePanel.State localState12 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState13
				 * = BasePanel.State.FLYING; if ((localState12 != localState13)
				 * || (!BasePanel.access$9(this.this$0))) break label803; float
				 * f15 = j - i; float f16 = BasePanel.access$7(this.this$0); i3
				 * = Math.max((int)(Math.abs(f15 / f16) * 1000.0F), 20); break;
				 * i = i4; break label552; if (BasePanel.access$2(this.this$0)
				 * == 2); for (j = -i4; ; j = i4) break; label763: n = 1; break
				 * label647; label770: BasePanel.State localState14 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState15
				 * = BasePanel.State.FLYING; if (localState14 != localState15)
				 * continue; i = (int)BasePanel.access$10(this.this$0); }
				 * label803: int i5 = BasePanel.access$11(this.this$0); int i6 =
				 * Math.abs(j - i); int i7 = i5 * i6; int i8 =
				 * BasePanel.access$3(this.this$0); i3 = i7 / i8; } label846:
				 * BasePanel localBasePanel4 = this.this$0; float f17 = i; float
				 * f18 = j; float f19 = k; float f20 = m; TranslateAnimation
				 * localTranslateAnimation1 = new TranslateAnimation(f17, f18,
				 * f19, f20); localBasePanel4.animation =
				 * localTranslateAnimation1; TranslateAnimation
				 * localTranslateAnimation2 = this.this$0.animation; long l =
				 * i3; localTranslateAnimation2.setDuration(l);
				 * TranslateAnimation localTranslateAnimation3 =
				 * this.this$0.animation; Animation.AnimationListener
				 * localAnimationListener = BasePanel.access$16(this.this$0);
				 * localTranslateAnimation3
				 * .setAnimationListener(localAnimationListener);
				 * BasePanel.State localState16 =
				 * BasePanel.access$0(this.this$0); BasePanel.State localState17
				 * = BasePanel.State.FLYING; if ((localState16 == localState17)
				 * && (BasePanel.access$9(this.this$0))) { TranslateAnimation
				 * localTranslateAnimation4 = this.this$0.animation;
				 * LinearInterpolator localLinearInterpolator = new
				 * LinearInterpolator();
				 * localTranslateAnimation4.setInterpolator
				 * (localLinearInterpolator); } while (true) { BasePanel
				 * localBasePanel5 = this.this$0; TranslateAnimation
				 * localTranslateAnimation5 = this.this$0.animation;
				 * localBasePanel5.startAnimation(localTranslateAnimation5);
				 * break; if (BasePanel.access$17(this.this$0) == null)
				 * continue; TranslateAnimation localTranslateAnimation6 =
				 * this.this$0.animation; Interpolator localInterpolator =
				 * BasePanel.access$17(this.this$0);
				 * localTranslateAnimation6.setInterpolator(localInterpolator);
				 * }
				 */
			}
		};
		this.animationListener = new AnimationListener() {

			public void onAnimationEnd(Animation paramAnimation) {
				/*
				 * BasePanel localBasePanel = this.this$0; BasePanel.State
				 * localState = BasePanel.State.READY;
				 * BasePanel.access$14(localBasePanel, localState); if
				 * (this.this$0.mIsShrinking)
				 * this.this$0.mContent.setVisibility(8); for
				 * (this.this$0.opened = 0; ; this.this$0.opened = 1) {
				 * BasePanel.access$15(this.this$0); return; }
				 */
			}

			public void onAnimationRepeat(Animation paramAnimation) {
			}

			public void onAnimationStart(Animation paramAnimation) {
				/*
				 * BasePanel localBasePanel = this.this$0; BasePanel.State
				 * localState = BasePanel.State.ANIMATING;
				 * BasePanel.access$14(localBasePanel, localState);
				 */
			}
		};
		this.isBanner = paramBoolean;
		this.mDuration = 1000;
		setPosition(paramInt, paramContext);
		this.mLinearFlying = false;
		State localState = State.READY;
		this.mState = localState;
		PanelOnGestureListener localPanelOnGestureListener1 = new PanelOnGestureListener();
		this.mGestureListener = localPanelOnGestureListener1;
		PanelOnGestureListener localPanelOnGestureListener2 = this.mGestureListener;
		GestureDetector localGestureDetector = new GestureDetector(
				localPanelOnGestureListener2);
		this.mGestureDetector = localGestureDetector;
		this.mGestureDetector.setIsLongpressEnabled(false);
	}

	private float ensureRange(float paramFloat, int paramInt1, int paramInt2) {
		float f1 = paramInt1;
		float f2 = Math.max(paramFloat, f1);
		float f3 = paramInt2;
		return Math.min(f2, f3);
	}

	private void postProcess() {
		if (!this.isBanner) {
			if ((!this.mIsShrinking) || (this.mClosedHandle == null)) {
			}
			View localView1 = this.mHandle;
			Drawable localDrawable1 = this.mClosedHandle;
			localView1.setBackgroundDrawable(localDrawable1);
			this.mAnimating = false;
			setListener();
		} else {

			if ((this.mIsShrinking) || (this.mOpenedHandle == null)) {
			} else {
				View localView2 = this.mHandle;
				Drawable localDrawable2 = this.mOpenedHandle;
				localView2.setBackgroundDrawable(localDrawable2);
			}
		}
	}

	protected void dispatchDraw(Canvas paramCanvas) {
		State localState1 = this.mState;
		State localState2 = State.ABOUT_TO_ANIMATE;
		int i;
		if ((localState1 == localState2) && (!this.mIsShrinking)) {
			if (this.mOrientation != 1) {
			}
			i = this.mContentHeight;
			if ((this.mPosition == 2) || (this.mPosition == 0))
				i = -i;
			if (this.mOrientation != 1) {
			}
			float f1 = i;
			paramCanvas.translate(0.0F, f1);
		}
		while (true) {
			State localState3 = this.mState;
			State localState4 = State.TRACKING;
			if (localState3 != localState4) {
				State localState5 = this.mState;
				State localState6 = State.FLYING;
				if (localState5 != localState6)
					;
			} else {
				float f2 = this.mTrackX;
				float f3 = this.mTrackY;
				paramCanvas.translate(f2, f3);
			}
			super.dispatchDraw(paramCanvas);
			i = this.mContentWidth;
			float f4 = i;
			paramCanvas.translate(f4, 0.0F);
		}
	}

	public View getContent() {
		return this.mContent;
	}

	public View getHandle() {
		return this.mHandle;
	}

	public void hideBlur() {
		if (this.blurLayout != null)
			this.blurLayout.setVisibility(8);
	}

	public boolean isFling() {
		State localState1 = this.mState;
		State localState2 = State.FLYING;
		if (localState1 == localState2)
			return true;
		return false;
	}

	public boolean isOpen() {
		if (this.mContent.getVisibility() == 0)
			return true;
		return false;
	}

	public void onFinishInflate() {
		super.onFinishInflate();
		View localView1 = this.mHandle;
		removeView(localView1);
		View localView2 = this.mContent;
		removeView(localView2);
		if ((this.mPosition == 0) || (this.mPosition == 2)) {
			View localView3 = this.mContent;
			addView(localView3);
			View localView4 = this.mHandle;
			addView(localView4);

			if (this.mClosedHandle != null) {
				View localView5 = this.mHandle;
				Drawable localDrawable = this.mClosedHandle;
				localView5.setBackgroundDrawable(localDrawable);
			}
			if (!isOpen())
				this.mContent.setVisibility(8);
		} else {
			View localView6 = this.mHandle;
			addView(localView6);
			View localView7 = this.mContent;
			addView(localView7);
		}
	}

	protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4) {
		super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
		int i = this.mContent.getWidth();
		this.mContentWidth = i;
		int j = this.mContent.getHeight();
		this.mContentHeight = j;
	}

	public void setInterpolator(Interpolator paramInterpolator) {
		this.mInterpolator = paramInterpolator;
	}

	public void setListener() {
		if (!this.isBanner) {
			View localView1 = this.mHandle;
			View.OnTouchListener localOnTouchListener = this.touchListener;
			localView1.setOnTouchListener(localOnTouchListener);
			if (this.panelListener != null) {
				if (!isOpen()) {
				}
				this.panelListener.onPanelOpened(this, null);
			} else {
				this.panelListener.onPanelClosed(this);
			}
		}
		if (isOpen()) {
			if (this.panelListener != null) {
				OnPanelListener localOnPanelListener = this.panelListener;
				View localView2 = this.mHandle;
				localOnPanelListener.onPanelOpened(this, localView2);
			}
		} else {
			View localView3 = this.mHandle;
			localView3.setOnClickListener(new OnClickListener() {
				public void onClick(View paramView) {
					/*
					 * BasePanel localBasePanel1 = this.this$0; BasePanel.State
					 * localState = BasePanel.State.ABOUT_TO_ANIMATE;
					 * BasePanel.access$14(localBasePanel1, localState);
					 * BasePanel localBasePanel2 = this.this$0; if
					 * (this.this$0.mContent.getVisibility() == 0); for (int i =
					 * 1; ; i = 0) { localBasePanel2.mIsShrinking = i; if
					 * (!this.this$0.mIsShrinking)
					 * this.this$0.mContent.setVisibility(0); BasePanel
					 * localBasePanel3 = this.this$0; Runnable localRunnable =
					 * this.this$0.startAnimation; boolean bool =
					 * localBasePanel3.post(localRunnable); return; }
					 */
				}
			});
			if (this.panelListener == null) {
			} else {
				this.panelListener.onPanelClosed(this);
			}
		}
	}

	public void setOnPanelListener(OnPanelListener paramOnPanelListener) {
		this.panelListener = paramOnPanelListener;
	}

	public void setOpen(boolean paramBoolean1, boolean paramBoolean2) {
		int i = 0;
		if (paramBoolean1)
			;
		for (int j = 0;; j = 1) {
			this.mIsShrinking = false;
			if (!paramBoolean2)
				break;
			State localState = State.ABOUT_TO_ANIMATE;
			this.mState = localState;
			if (!this.mIsShrinking)
				this.mContent.setVisibility(0);
			Runnable localRunnable = this.startAnimation;
			boolean bool = post(localRunnable);
			return;
		}
		View localView = this.mContent;
		if (paramBoolean1)
			;
		while (true) {
			localView.setVisibility(i);
			postProcess();
			i = 8;
		}
	}

	public void setPosition(int paramInt, Context paramContext) {
		this.mPosition = paramInt;
		int i;
		if ((this.mPosition == 0) || (this.mPosition == 1)) {
			i = 1;
			this.mOrientation = i;
			int j = this.mOrientation;
			setOrientation(j);
			if (!this.isBanner)
				;
		} else {
			i = 0;
			if (this.mPosition == 1) {
				Resources localResources1 = paramContext.getResources();
				int k = DrawableMapper
						.exchange_bottom_switcher_expanded_background();
				Drawable localDrawable1 = localResources1.getDrawable(k);
				this.mOpenedHandle = localDrawable1;
				Resources localResources2 = paramContext.getResources();
				int m = DrawableMapper
						.exchange_bottom_switcher_collapsed_background();
				Drawable localDrawable2 = localResources2.getDrawable(m);
				this.mClosedHandle = localDrawable2;
			} else {
				Resources localResources3 = paramContext.getResources();
				int n = DrawableMapper
						.exchange_top_switcher_expanded_background();
				Drawable localDrawable3 = localResources3.getDrawable(n);
				this.mOpenedHandle = localDrawable3;
				Resources localResources4 = paramContext.getResources();
				int i1 = DrawableMapper
						.exchange_top_switcher_collapsed_background();
				Drawable localDrawable4 = localResources4.getDrawable(i1);
				this.mClosedHandle = localDrawable4;
			}
		}
	}

	public void showBlur() {
		if (this.blurLayout != null) {
			this.blurLayout.setVisibility(0);
			AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
			localAlphaAnimation.setDuration(300L);
			localAlphaAnimation.setRepeatCount(0);
			this.blurLayout.setAnimation(localAlphaAnimation);
			localAlphaAnimation.startNow();
			View localView = this.blurLayout;
			localView.setOnClickListener(new OnClickListener() {
				public void onClick(View paramView) {
					// this.this$0.setOpen(0, 1);
				}
			});
		}
	}

	public abstract interface OnPanelListener {
		public abstract void onPanelClosed(BasePanel paramBasePanel);

		public abstract void onPanelOpened(BasePanel paramBasePanel,
				View paramView);
	}

	class PanelOnGestureListener implements GestureDetector.OnGestureListener {
		float scrollX;
		float scrollY;

		PanelOnGestureListener() {
		}

		public boolean onDown(MotionEvent paramMotionEvent) {
			this.scrollY = 0.0F;
			this.scrollX = 0.0F;
			BasePanel.State localState1 = BasePanel.this.mState;
			BasePanel.State localState2 = BasePanel.State.READY;
			if (localState1 != localState2) {
				return false;
			}
			BasePanel localBasePanel2 = BasePanel.this;
			BasePanel.State localState3 = BasePanel.State.ABOUT_TO_ANIMATE;
			localBasePanel2.mState = localState3;
			BasePanel localBasePanel1 = BasePanel.this;
			if (BasePanel.this.mContent.getVisibility() == 0)
				;
			localBasePanel1.mIsShrinking = true;
			if (!BasePanel.this.mIsShrinking)
				BasePanel.this.mContent.setVisibility(0);
			return true;
		}

		public boolean onFling(MotionEvent paramMotionEvent1,
				MotionEvent paramMotionEvent2, float paramFloat1,
				float paramFloat2) {
			BasePanel localBasePanel1 = BasePanel.this;
			BasePanel.State localState = BasePanel.State.FLYING;
			localBasePanel1.mState = localState;
			BasePanel localBasePanel2 = BasePanel.this;
			float f = 0;
			if (BasePanel.this.mOrientation == 1)
				f = paramFloat2;
			else {
				localBasePanel2.mVelocity = f;
				BasePanel localBasePanel3 = BasePanel.this;
				Runnable localRunnable = BasePanel.this.startAnimation;
				boolean bool = localBasePanel3.post(localRunnable);
				f = paramFloat1;
			}
			return true;
		}

		public void onLongPress(MotionEvent paramMotionEvent) {
		}

		public boolean onScroll(MotionEvent paramMotionEvent1,
				MotionEvent paramMotionEvent2, float paramFloat1,
				float paramFloat2) {
			BasePanel localBasePanel1 = BasePanel.this;
			BasePanel.State localState = BasePanel.State.TRACKING;
			localBasePanel1.mState = localState;
			float f1 = 0.0F;
			float f2 = 0.0F;
			if (BasePanel.this.mOrientation == 1) {
				float f3 = this.scrollY - paramFloat2;
				this.scrollY = f3;
				if (BasePanel.this.mPosition == 0) {
					BasePanel localBasePanel2 = BasePanel.this;
					float f4 = this.scrollY;
					int i = -BasePanel.this.mContentHeight;
					f1 = localBasePanel2.ensureRange(f4, i, 0);
				}

				float f5 = BasePanel.this.mTrackX;
				if (f2 == f5) {
					float f6 = BasePanel.this.mTrackY;
					if (f1 == f6)
						;
				} else {
					BasePanel.this.mTrackX = f2;
					BasePanel.this.mTrackY = f1;
					BasePanel.this.invalidate();
				}
			} else {
				BasePanel localBasePanel3 = BasePanel.this;
				float f7 = this.scrollY;
				int j = BasePanel.this.mContentHeight;
				f1 = localBasePanel3.ensureRange(f7, 0, j);
				float f8 = this.scrollX - paramFloat1;
				this.scrollX = f8;
				if (BasePanel.this.mPosition == 2) {
					BasePanel localBasePanel4 = BasePanel.this;
					float f9 = this.scrollX;
					int k = -BasePanel.this.mContentWidth;
					f2 = localBasePanel4.ensureRange(f9, k, 0);
				} else {
					BasePanel localBasePanel5 = BasePanel.this;
					float f10 = this.scrollX;
					int m = BasePanel.this.mContentWidth;
					f2 = localBasePanel5.ensureRange(f10, 0, m);
				}
			}
			return true;
		}

		public void onShowPress(MotionEvent paramMotionEvent) {
		}

		public boolean onSingleTapUp(MotionEvent paramMotionEvent) {
			BasePanel localBasePanel = BasePanel.this;
			Runnable localRunnable = BasePanel.this.startAnimation;
			boolean bool = localBasePanel.post(localRunnable);
			return true;
		}

		public void setScroll(int paramInt1, int paramInt2) {
			float f1 = paramInt1;
			this.scrollX = f1;
			float f2 = paramInt2;
			this.scrollY = f2;
		}
	}

	public static enum State {
		FLYING, ABOUT_TO_ANIMATE, ANIMATING, READY, TRACKING
		/*
		 * State FLYING = new State("FLYING", 4); State[] arrayOfState = new
		 * State[5]; State localState1 = ABOUT_TO_ANIMATE; arrayOfState[0] =
		 * localState1; State localState2 = ANIMATING; arrayOfState[1] =
		 * localState2; State localState3 = READY; arrayOfState[2] =
		 * localState3; State localState4 = TRACKING; arrayOfState[3] =
		 * localState4; State localState5 = FLYING; arrayOfState[4] =
		 * localState5; ENUM$VALUES = arrayOfState;
		 */
	}
}
