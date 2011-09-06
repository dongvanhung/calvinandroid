package com.xiami.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class CustomPopupWindow {

		protected View anchor;
	  private Drawable background = null;
	  private View root;
	  protected PopupWindow window;
	  protected WindowManager windowManager;

	  public CustomPopupWindow(View paramView)
	  {
	    /*this.anchor = paramView;
	    Context localContext = paramView.getContext();
	    PopupWindow localPopupWindow1 = new PopupWindow(localContext);
	    this.window = localPopupWindow1;
	    PopupWindow localPopupWindow2 = this.window;
	    CustomPopupWindow.1 local1 = new CustomPopupWindow.1(this);
	    localPopupWindow2.setTouchInterceptor(local1);
	    WindowManager localWindowManager = (WindowManager)paramView.getContext().getSystemService("window");
	    this.windowManager = localWindowManager;
	    onCreate();*/
	  }

	  public void dismiss()
	  {
	    this.window.dismiss();
	  }

	  protected void onCreate()
	  {
	  }

	  protected void onShow()
	  {
	  }

	  protected void preShow()
	  {
	    if (this.root == null)
	      throw new IllegalStateException("setContentView was not called with a view to display.");
	    onShow();
	    if (this.background == null)
	    {
	      PopupWindow localPopupWindow1 = this.window;
	      BitmapDrawable localBitmapDrawable = new BitmapDrawable();
	      localPopupWindow1.setBackgroundDrawable(localBitmapDrawable);
	    }
	    while (true)
	    {
	      this.window.setWidth(-1);
	      this.window.setHeight(-1);
	      this.window.setTouchable(true);
	      this.window.setFocusable(true);
	      this.window.setOutsideTouchable(true);
	      PopupWindow localPopupWindow2 = this.window;
	      View localView = this.root;
	      localPopupWindow2.setContentView(localView);
	      PopupWindow localPopupWindow3 = this.window;
	      Drawable localDrawable = this.background;
	      localPopupWindow3.setBackgroundDrawable(localDrawable);
	    }
	  }

	  public void setBackgroundDrawable(Drawable paramDrawable)
	  {
	    this.background = paramDrawable;
	  }

	  public void setContentView(int paramInt)
	  {
	    View localView = ((LayoutInflater)this.anchor.getContext().getSystemService("layout_inflater")).inflate(paramInt, null);
	    setContentView(localView);
	  }

	  public void setContentView(View paramView)
	  {
	    this.root = paramView;
	    this.window.setContentView(paramView);
	  }

	  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
	  {
	    this.window.setOnDismissListener(paramOnDismissListener);
	  }

	  public void showDropDown()
	  {
	    showDropDown(0, 0);
	  }

	  public void showDropDown(int paramInt1, int paramInt2)
	  {
	    preShow();
	    this.window.setAnimationStyle(2131361824);
	    PopupWindow localPopupWindow = this.window;
	    View localView = this.anchor;
	    localPopupWindow.showAsDropDown(localView, paramInt1, paramInt2);
	  }

	  public void showLikeQuickAction()
	  {
	    showLikeQuickAction(0, 0);
	  }

	  public void showLikeQuickAction(int paramInt1, int paramInt2)
	  {
	    /*preShow();
	    this.window.setAnimationStyle(2131361830);
	    int[] arrayOfInt = new int[2];
	    this.anchor.getLocationOnScreen(arrayOfInt);
	    int i = arrayOfInt[0];
	    int j = arrayOfInt[1];
	    int k = arrayOfInt[0];
	    int m = this.anchor.getWidth();
	    int n = k + m;
	    int i1 = arrayOfInt[1];
	    int i2 = this.anchor.getHeight();
	    int i3 = i1 + i2;
	    Rect localRect = new Rect(i, j, n, i3);
	    View localView1 = this.root;
	    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
	    localView1.setLayoutParams(localLayoutParams);
	    this.root.measure(-1, -1);
	    int i4 = this.root.getMeasuredWidth();
	    int i5 = this.root.getMeasuredHeight();
	    int i6 = (this.windowManager.getDefaultDisplay().getWidth() - i4) / 2 + paramInt1;
	    int i7 = localRect.top - i5 + paramInt2;
	    int i8 = localRect.top;
	    if (i5 > i8)
	    {
	      i7 = localRect.bottom + paramInt2;
	      this.window.setAnimationStyle(2131361825);
	    }
	    PopupWindow localPopupWindow = this.window;
	    View localView2 = this.anchor;
	    localPopupWindow.showAtLocation(localView2, 0, i6, i7);*/
	  }
}
