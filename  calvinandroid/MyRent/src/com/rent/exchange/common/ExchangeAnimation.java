package com.rent.exchange.common;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rent.exchange.EasingType;
import com.rent.exchange.ElasticInterpolator;

public class ExchangeAnimation {

	 public static void disappearDown(Context paramContext, View paramView)
	  {
	    int i = AnimMapper.exchange_push_down_out();
	    Animation localAnimation = AnimationUtils.loadAnimation(paramContext, i);
	    EasingType.Type localType = EasingType.Type.OUT;
	    ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(localType, 1.0F, 1.0F);
	    localAnimation.setInterpolator(localElasticInterpolator);
	    localAnimation.setDuration(1000L);
	    localAnimation.setAnimationListener(new Animation.AnimationListener(){
	    	public void onAnimationEnd(Animation paramAnimation)
	    	  {
	    	    //this.val$view.setVisibility(8);
	    	  }

	    	  public void onAnimationRepeat(Animation paramAnimation)
	    	  {
	    	  }

	    	  public void onAnimationStart(Animation paramAnimation)
	    	  {
	    	  }
	    });
	    paramView.startAnimation(localAnimation);
	  }

	  public static void disappearSlowly(View paramView)
	  {
	    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
	    localAlphaAnimation.setDuration(1000L);
	    localAlphaAnimation.setStartTime(200L);
	    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(){
	    	public void onAnimationEnd(Animation paramAnimation)
	    	  {
//	    	    this.val$view.setVisibility(8);
	    	  }

	    	  public void onAnimationRepeat(Animation paramAnimation)
	    	  {
	    	  }

	    	  public void onAnimationStart(Animation paramAnimation)
	    	  {
	    	  }
	    });
	    paramView.startAnimation(localAlphaAnimation);
	  }

	  public static void showSlowly(View paramView)
	  {
	    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
	    localAlphaAnimation.setDuration(1000L);
	    localAlphaAnimation.setStartTime(200L);
	    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(){
	    	public void onAnimationEnd(Animation paramAnimation)
	    	  {
//	    	    this.val$view.setVisibility(0);
	    	  }

	    	  public void onAnimationRepeat(Animation paramAnimation)
	    	  {
	    	  }

	    	  public void onAnimationStart(Animation paramAnimation)
	    	  {
	    	  }
	    });
	    paramView.startAnimation(localAlphaAnimation);
	  }

	  public static void showUp(Context paramContext, View paramView)
	  {
	    int i = AnimMapper.exchange_push_up_in();
	    Animation localAnimation = AnimationUtils.loadAnimation(paramContext, i);
	    EasingType.Type localType = EasingType.Type.OUT;
	    ElasticInterpolator localElasticInterpolator = new ElasticInterpolator(localType, 1.0F, 1.0F);
	    localAnimation.setInterpolator(localElasticInterpolator);
	    localAnimation.setDuration(1000L);
	    paramView.setVisibility(0);
	    localAnimation.setAnimationListener(new Animation.AnimationListener(){
	    	public void onAnimationEnd(Animation paramAnimation)
	    	  {
	    	  }

	    	  public void onAnimationRepeat(Animation paramAnimation)
	    	  {
	    	  }

	    	  public void onAnimationStart(Animation paramAnimation)
	    	  {
	    	  }
	    });
	    paramView.startAnimation(localAnimation);
	  }
}
