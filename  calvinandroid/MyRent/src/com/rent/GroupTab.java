package com.rent;

public class GroupTab {

	protected int mActiveIconId;
	protected int mInactiveIconId;
	protected String mMessage;
	protected String mText;

	public GroupTab(String paramString, int paramInt1, int paramInt2) {
		this.mText = paramString;
		this.mActiveIconId = paramInt1;
		this.mInactiveIconId = paramInt2;
	}

	public int getActiveIconId() {
		return this.mActiveIconId;
	}

	public int getInactiveIconId() {
		return this.mInactiveIconId;
	}

	public String getMessage() {
		return this.mMessage;
	}

	public String getText() {
		return this.mText;
	}

	public void setMessage(String paramString) {
		this.mMessage = paramString;
	}
}
