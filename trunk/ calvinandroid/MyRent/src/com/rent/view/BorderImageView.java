package com.rent.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderImageView extends ImageView {

	 public BorderImageView(Context paramContext)
	  {
	    super(paramContext);
	  }

	  public BorderImageView(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	  }

	  public BorderImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
	  {
	    super(paramContext, paramAttributeSet, paramInt);
	  }

	  protected void onDraw(Canvas paramCanvas)
	  {
	    super.onDraw(paramCanvas);
	    Rect localRect1 = paramCanvas.getClipBounds();
	    Rect localRect2 = new Rect();
	    int i = localRect1.left;
	    int j = localRect1.top + 1;
	    int k = localRect1.right - 1;
	    int m = localRect1.bottom - 1 - 1;
	    localRect2.set(i, j, k, m);
	    Paint localPaint = new Paint();
	    localPaint.setARGB(255, 255, 255, 255);
	    Paint.Style localStyle = Paint.Style.STROKE;
	    localPaint.setStyle(localStyle);
	    paramCanvas.drawRect(localRect2, localPaint);
	  }
}
