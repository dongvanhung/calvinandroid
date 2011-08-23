package com.rent.handler;

import android.os.Handler;

public class ThreadHandler extends Handler {

	public static final int CODE_DATA_FAIL = 1;
	public static final int CODE_OTHER_ERROR = 3;
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_TIME_OUT = 2;
	public static final String KEY_CITY_TYPE = "city";
	public static final String KEY_DATA = "handler_data";
	public static final String KEY_ERROR_CODE = "error_code";
	public static final String KEY_ID = "mId";
	public static final String KEY_LOW_TIME_TYPE = "low_time";
	public static final String KEY_Q = "q";
	public static final String KEY_RESULT_TYPE = "result";
}
