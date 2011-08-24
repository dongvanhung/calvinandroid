package com.rent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AlwaysMarqueeTextView extends TextView{

	 public AlwaysMarqueeTextView(Context paramContext)
	  {
	    super(paramContext);
	  }

	  public AlwaysMarqueeTextView(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	  }

	  public AlwaysMarqueeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
	  {
	    super(paramContext, paramAttributeSet, paramInt);
	  }

	  public boolean isFocused()
	  {
	    return true;
	  }
}
