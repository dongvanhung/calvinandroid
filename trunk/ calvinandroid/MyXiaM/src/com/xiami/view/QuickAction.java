package com.xiami.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public class QuickAction extends CustomPopupWindow {
	
	protected static final int ANIM_AUTO = 5;
	  protected static final int ANIM_GROW_FROM_CENTER = 3;
	  protected static final int ANIM_GROW_FROM_LEFT = 1;
	  protected static final int ANIM_GROW_FROM_RIGHT = 2;
	  protected static final int ANIM_REFLECT = 4;
	  private final Context context;
	  private final LayoutInflater inflater;
	  private final View root;

	  public QuickAction(View paramView)
	  {
	    super(paramView);
	    Context localContext = paramView.getContext();
	    this.context = localContext;
	    LayoutInflater localLayoutInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
	    this.inflater = localLayoutInflater;
	    View localView1 = this.inflater.inflate(2130903105, null);
	    this.root = localView1;
	    View localView2 = this.root;
	    setContentView(localView2);
	  }
	  /*
	  public void setAction(int paramInt, View.OnClickListener paramOnClickListener)
	  {
	    this.root.findViewById(paramInt).setOnClickListener(paramOnClickListener);
	  }

	  public void show()
	  {
	    preShow();
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
	    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
	    localView1.setLayoutParams(localLayoutParams);
	    this.root.measure(-1, -1);
	    int i4 = this.root.getMeasuredHeight();
	    int i5 = this.root.getMeasuredWidth();
	    int i6 = this.windowManager.getDefaultDisplay().getWidth();
	    int i7 = this.windowManager.getDefaultDisplay().getHeight();
	    int i8 = localRect.left;
	    int i9 = i5 / 2;
	    int i10 = i8 - i9;
	    int i11 = this.anchor.getWidth() / 2;
	    int i12 = i10 + i11;
	    int i13 = localRect.top - i4 + 10;
	    int i14 = localRect.top;
	    int i15 = localRect.bottom;
	    int i16 = i7 - i15;
	    if (i14 > i16)
	      int i17 = 1;
	    while (true)
	    {
	      this.window.setAnimationStyle(2131361832);
	      PopupWindow localPopupWindow = this.window;
	      View localView2 = this.anchor;
	      localPopupWindow.showAtLocation(localView2, 0, i12, i13);
	      return;
	      int i18 = 0;
	    }
	  }*/
}
