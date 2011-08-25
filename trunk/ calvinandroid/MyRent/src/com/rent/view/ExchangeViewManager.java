package com.rent.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rent.exchange.ExchangeDataService;
import com.rent.exchange.common.DeviceManager;
import com.rent.exchange.common.ExchangeAnimation;
import com.rent.exchange.common.ExchangeConstants;

public class ExchangeViewManager {

	ListDrawer listDrawer = null;
	Context mContext;
	int mType;
	PearlDrawer pearlDrawer = null;
	StandaloneBanner standaloneBanner = null;

	public void addView(Context paramContext, int paramInt) {
		try {
			this.mContext = paramContext;
			this.mType = paramInt;
			if (paramInt != 7)
				Log.e(ExchangeConstants.LOG_TAG,
						"cannot invoke in-page promotion with our father layout");
			else {
				try {
					Class localClass = Class
							.forName("com.exchange.View.ListCurtainActivity");
					Intent localIntent = new Intent(paramContext, localClass);
					paramContext.startActivity(localIntent);
				} catch (ClassNotFoundException localClassNotFoundException) {
					localClassNotFoundException.printStackTrace();
				}
			}
		} catch (Exception localException) {
			while (true)
				localException.printStackTrace();
		}
	}

	public void addView(Context paramContext, ViewGroup paramViewGroup,
			int paramInt) {
		try {
			this.mContext = paramContext;
			if ((ExchangeConstants.ONLY_CHINESE)
					&& (!DeviceManager.isChinese(this.mContext)))
				Log.e(ExchangeConstants.LOG_TAG, "English os can not show ads");
			else {
				if ((ExchangeDataService.hasData())
						&& (ExchangeDataService.mAdvertisers.size() == 1)) {
					if (paramInt != 4) {
					}
					paramInt = 2;
				}
				this.mType = paramInt;
				switch (paramInt) {
				default:
					int j = Log.e(ExchangeConstants.LOG_TAG,
							"paramter type cannot be handled");
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				}
			}
		} catch (Exception localException) {
			String str1 = ExchangeConstants.LOG_TAG;
			StringBuilder localStringBuilder = new StringBuilder(
					"add view error");
			String str2 = localException.getMessage();
			String str3 = str2;
			int k = Log.e(str1, str3);
			if (paramInt != 5) {
			} else {
				paramInt = 3;
				PearlDrawer localPearlDrawer = new PearlDrawer(paramContext,
						paramViewGroup, paramInt);
				this.pearlDrawer = localPearlDrawer;
				ListDrawer localListDrawer = new ListDrawer(paramContext,
						paramViewGroup, paramInt);
				this.listDrawer = localListDrawer;
				StandaloneBanner localStandaloneBanner = new StandaloneBanner(
						paramContext, paramViewGroup, paramInt);
				this.standaloneBanner = localStandaloneBanner;
			}
		}	
	}

	public void hideBanner() {
		try {
			switch (this.mType) {
			default:
				int i = Log.e(ExchangeConstants.LOG_TAG,
						"paramter type cannot be handled");
			case 0:
			case 1:
			case 2:
			case 3:
				if (this.pearlDrawer == null) {
				} else {
					Context localContext1 = this.mContext;
					View localView1 = this.pearlDrawer.mHandleContent;
					ExchangeAnimation.disappearDown(localContext1, localView1);
				}
			case 4:
			case 5:
			case 6:
			}
		} catch (Exception localException) {
			int j = Log.e(ExchangeConstants.LOG_TAG, "hideBanner error");
			if (this.listDrawer == null) {
			} else {
				Context localContext2 = this.mContext;
				View localView2 = this.listDrawer.mHandleContent;
				ExchangeAnimation.disappearDown(localContext2, localView2);
			}
			if (this.standaloneBanner == null) {
			} else
				ExchangeAnimation
						.disappearSlowly(this.standaloneBanner.mHandleContent);
		}
	}

	public boolean isFling() {
		boolean bool1 = false;
		try {
			switch (this.mType) {
			default:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				if (this.pearlDrawer == null) {
					bool1 = false;
					return bool1;
				}
				bool1 = this.pearlDrawer.panel.isFling();
				if (this.listDrawer == null) {
					bool1 = false;
					return bool1;
				}
				boolean bool2 = this.listDrawer.panel.isFling();
				bool1 = bool2;
			}
		} catch (Exception localException) {
			int i = Log.e(ExchangeConstants.LOG_TAG, "isFling error");
			bool1 = false;
		}
		return bool1;
	}

	public boolean isOpen() {
		boolean bool1 = false;
		try {
			switch (this.mType) {
			default:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				if (this.pearlDrawer == null) {
					bool1 = false;
					return bool1;
				}
				bool1 = this.pearlDrawer.panel.isOpen();
				if (this.listDrawer == null) {
					bool1 = false;
					return bool1;
				}
				boolean bool2 = this.listDrawer.panel.isOpen();
				bool1 = bool2;
			}
		} catch (Exception localException) {
			int i = Log.e(ExchangeConstants.LOG_TAG, "isOpen error");
		}
		return bool1;
	}

	public void showBanner() {
		try {
			switch (this.mType) {
			default:
				int i = Log.e(ExchangeConstants.LOG_TAG,
						"paramter type cannot be handled");
			case 0:
			case 1:
			case 2:
			case 3:
				if (this.pearlDrawer == null) {
				} else {
					Context localContext1 = this.mContext;
					View localView1 = this.pearlDrawer.mHandleContent;
					ExchangeAnimation.showUp(localContext1, localView1);
				}
			case 4:
			case 5:
			case 6:
			}
		} catch (Exception localException) {
			int j = Log.e(ExchangeConstants.LOG_TAG, "showBanner error");
			if (this.listDrawer == null) {
			} else {
				Context localContext2 = this.mContext;
				View localView2 = this.listDrawer.mHandleContent;
				ExchangeAnimation.showUp(localContext2, localView2);
			}
			if (this.standaloneBanner == null) {
			} else
				ExchangeAnimation
						.showSlowly(this.standaloneBanner.mHandleContent);
		}
	}

	public void toggle() {
		try {
			switch (this.mType) {
			default:
				int i = Log.e(ExchangeConstants.LOG_TAG,
						"paramter type cannot be handled");
			case 0:
			case 1:
			case 2:
			case 3:
				if (!this.pearlDrawer.panel.isOpen()) {
				} else {
					int j = 0;
					if (this.pearlDrawer == null) {
					} else
						this.pearlDrawer.panel.setOpen(false, true);
				}
			case 4:
			case 5:
			}
		} catch (Exception localException) {
			int n = Log.e(ExchangeConstants.LOG_TAG, "toggle error");
		}
		if (!this.listDrawer.panel.isOpen()) {
		}
		int m = 0;
		for (int k = 0; this.listDrawer != null; m = 1) {
			this.listDrawer.panel.setOpen(false, true);
			break;
		}
	}

}
