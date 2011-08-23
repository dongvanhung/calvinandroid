package com.rent.lib;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AppAlert {

	private static final String STR_ALERT = "alert";
	private static final String STR_CONTENT = "content";
	private static final String STR_PARAM = "param";
	private static final String STR_TYPE = "type";
	private static final int TYPE_DIALOG = 0;
	private static final int TYPE_OPEN_APP = 1;
	private static AppAlert alert = null;
	private static ArrayList<JSONObject> alertList = new ArrayList();

	public static void actionAlerts(Context paramContext, String paramString1,
			String paramString2) {
		String str = null;
		for (int i = 0; i < alertList.size(); i++) {
			Object localObject1 = (JSONObject) alertList.get(i);
			Object localObject2 = alertList.remove(0);
			try {
				i = ((JSONObject) localObject1).getInt("type");
				str = ((JSONObject) localObject1).getString("content");
				localObject1 = ((JSONObject) localObject1).getString("param");
				AlertDialog localAlertDialog1 = new AlertDialog.Builder(
						paramContext).setMessage(str)
						.setPositiveButton(paramString1, null).show();
			} catch (Exception localException) {
			}
			
			AlertDialog.Builder localBuilder = new AlertDialog.Builder(
					paramContext).setMessage(str);
			AlertDialog localAlertDialog2 = localBuilder
					.setPositiveButton(paramString1,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// Context localContext = this.val$context;
									// String str = this.val$param;
									// ToolUtils.runApplication(localContext,
									// str, null);
								}
							}).setNegativeButton(paramString2, null).show();
		}

	}

	public static int alertCount() {
		return alertList.size();
	}

	public static void checkData(JSONObject paramJSONObject) {
		Iterator localIterator = paramJSONObject.keys();
		while (localIterator.hasNext()) {
			String str = (String) localIterator.next();
			if (!"alert".equals(str))
				continue;
			try {
				JSONObject localJSONObject = paramJSONObject.getJSONObject(str);
				boolean bool = alertList.add(localJSONObject);
			} catch (Exception localException) {
			}
		}
	}

	public AppAlert getInstance() {
		if (alert == null)
			alert = new AppAlert();
		return alert;
	}
}
