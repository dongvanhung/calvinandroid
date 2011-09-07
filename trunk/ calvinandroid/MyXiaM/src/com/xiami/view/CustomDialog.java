package com.xiami.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDialog extends Dialog {
	
	private Context mContext;
	
	public CustomDialog(Context paramContext)
	  {
	    super(paramContext);
	    this.mContext = paramContext;
	  }

	  public CustomDialog(Context paramContext, int paramInt)
	  {
	    super(paramContext, paramInt);
	    this.mContext = paramContext;
	  }

	  public class Builder
	  {
	    private View contentView;
	    private String message;
	    private DialogInterface.OnClickListener negativeButtonClickListener;
	    private String negativeButtonText;
	    private DialogInterface.OnClickListener positiveButtonClickListener;
	    private String positiveButtonText;
	    private String title;

	    public Builder()
	    {
	    }

	    public CustomDialog create()
	    {
	      LayoutInflater localLayoutInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
	      CustomDialog localCustomDialog = new CustomDialog(mContext, 2131361820);
	      View localView1 = localLayoutInflater.inflate(2130903044, null);
	      ViewGroup.LayoutParams localLayoutParams1 = new ViewGroup.LayoutParams(-1, -1);
	      localCustomDialog.addContentView(localView1, localLayoutParams1);
	      TextView localTextView1 = (TextView)localView1.findViewById(2131165224);
	      String str1 = this.title;
	      localTextView1.setText(str1);
	      if (this.positiveButtonText != null)
	      {
	        Button localButton1 = (Button)localView1.findViewById(2131165209);
	        String str2 = this.positiveButtonText;
	        localButton1.setText(str2);
	        if (this.positiveButtonClickListener != null)
	        {
	          Button localButton2 = (Button)localView1.findViewById(2131165209);
	          localButton2.setOnClickListener(new View.OnClickListener() {
	        	  public void onClick(View paramView)
	        	  {
	        		  positiveButtonClickListener.onClick(CustomDialog.this, -1);
	        	    cancel();
	        	  }
			});
	        }
	        if (this.negativeButtonText == null) {
	        	localView1.findViewById(2131165210).setVisibility(8);
	        } else {
		        Button localButton3 = (Button)localView1.findViewById(2131165210);
		        String str3 = this.negativeButtonText;
		        localButton3.setText(str3);
		        if (this.negativeButtonClickListener != null)
		        {
		          Button localButton4 = (Button)localView1.findViewById(2131165210);
		          localButton4.setOnClickListener(new View.OnClickListener() {
		        	  public void onClick(View paramView)
		        	  {
		        	    negativeButtonClickListener.onClick(CustomDialog.this, -1);
		        	  }
				});
		        }
	        }
	        if (this.message == null) {
	        	if (this.contentView != null)
		        {
		          FrameLayout localFrameLayout = (FrameLayout)localView1.findViewById(2131165207);
		          localFrameLayout.removeAllViews();
		          View localView2 = this.contentView;
		          ViewGroup.LayoutParams localLayoutParams2 = new ViewGroup.LayoutParams(-1, -1);
		          localFrameLayout.addView(localView2, localLayoutParams2);
		        } else {
		        	((LinearLayout)localView1.findViewById(2131165207)).removeAllViews();
		        }
	        }
	        TextView localTextView2 = (TextView)localView1.findViewById(2131165208);
	        String str4 = this.message;
	        localTextView2.setText(str4);
	        localCustomDialog.setContentView(localView1);
	      }
	      else
	      {
	        localView1.findViewById(2131165209).setVisibility(8);
	      }
	      return localCustomDialog;
	    }

	    public Builder setContentView(View paramView)
	    {
	      this.contentView = paramView;
	      return this;
	    }

	    public Builder setMessage(int paramInt)
	    {
	      String str = (String)mContext.getText(paramInt);
	      this.message = str;
	      return this;
	    }

	    public Builder setMessage(String paramString)
	    {
	      this.message = paramString;
	      return this;
	    }

	    public Builder setNegativeButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
	    {
	      String str = (String)mContext.getText(paramInt);
	      this.negativeButtonText = str;
	      this.negativeButtonClickListener = paramOnClickListener;
	      return this;
	    }

	    public Builder setNegativeButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
	    {
	      this.negativeButtonText = paramString;
	      this.negativeButtonClickListener = paramOnClickListener;
	      return this;
	    }

	    public Builder setPositiveButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
	    {
	      String str = (String)mContext.getText(paramInt);
	      this.positiveButtonText = str;
	      this.positiveButtonClickListener = paramOnClickListener;
	      return this;
	    }

	    public Builder setPositiveButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
	    {
	      this.positiveButtonText = paramString;
	      this.positiveButtonClickListener = paramOnClickListener;
	      return this;
	    }

	    public Builder setTitle(int paramInt)
	    {
	      String str = (String)mContext.getText(paramInt);
	      this.title = str;
	      return this;
	    }

	    public Builder setTitle(String paramString)
	    {
	      this.title = paramString;
	      return this;
	    }
	  }
}
