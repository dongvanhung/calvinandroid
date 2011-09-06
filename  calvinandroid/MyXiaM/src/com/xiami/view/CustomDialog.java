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

	public CustomDialog(Context paramContext)
	  {
	    super(paramContext);
	  }

	  public CustomDialog(Context paramContext, int paramInt)
	  {
	    super(paramContext, paramInt);
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

	    /*public CustomDialog create()
	    {
	      LayoutInflater localLayoutInflater = (LayoutInflater)CustomDialog.this.getSystemService("layout_inflater");
	      Context localContext = CustomDialog.this;
	      CustomDialog localCustomDialog = new CustomDialog(CustomDialog, 2131361820);
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
	          CustomDialog.Builder.1 local1 = new CustomDialog.Builder.1(this, localCustomDialog);
	          localButton2.setOnClickListener(local1);
	        }
	        if (this.negativeButtonText == null)
	          break label277;
	        Button localButton3 = (Button)localView1.findViewById(2131165210);
	        String str3 = this.negativeButtonText;
	        localButton3.setText(str3);
	        if (this.negativeButtonClickListener != null)
	        {
	          Button localButton4 = (Button)localView1.findViewById(2131165210);
	          CustomDialog.Builder.2 local2 = new CustomDialog.Builder.2(this, localCustomDialog);
	          localButton4.setOnClickListener(local2);
	        }
	        label222: if (this.message == null)
	          break label292;
	        TextView localTextView2 = (TextView)localView1.findViewById(2131165208);
	        String str4 = this.message;
	        localTextView2.setText(str4);
	      }
	      while (true)
	      {
	        localCustomDialog.setContentView(localView1);
	        return localCustomDialog;
	        localView1.findViewById(2131165209).setVisibility(8);
	        break;
	        label277: localView1.findViewById(2131165210).setVisibility(8);
	        break label222;
	        label292: if (this.contentView != null)
	        {
	          FrameLayout localFrameLayout = (FrameLayout)localView1.findViewById(2131165207);
	          localFrameLayout.removeAllViews();
	          View localView2 = this.contentView;
	          ViewGroup.LayoutParams localLayoutParams2 = new ViewGroup.LayoutParams(-1, -1);
	          localFrameLayout.addView(localView2, localLayoutParams2);
	          continue;
	        }
	        ((LinearLayout)localView1.findViewById(2131165207)).removeAllViews();
	      }
	    }

	    public Builder setContentView(View paramView)
	    {
	      this.contentView = paramView;
	      return this;
	    }

	    public Builder setMessage(int paramInt)
	    {
	      String str = (String)CustomDialog.this.getText(paramInt);
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
	      String str = (String)CustomDialog.this.getText(paramInt);
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
	      String str = (String)CustomDialog.this.getText(paramInt);
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
	      String str = (String)CustomDialog.this.getText(paramInt);
	      this.title = str;
	      return this;
	    }

	    public Builder setTitle(String paramString)
	    {
	      this.title = paramString;
	      return this;
	    }*/
	  }
}
