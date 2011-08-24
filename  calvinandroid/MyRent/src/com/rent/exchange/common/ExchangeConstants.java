package com.rent.exchange.common;

import java.text.DecimalFormat;

public class ExchangeConstants {

	public static String APPKEY;
	public static String BASE_URL;
	public static boolean CONTAINER_AUTOEXPANDED = false;
	public static int CONTAINER_HEIGHT = 0;
	public static int CONTAINER_LIST_COUNT = 0;
	public static int CURTAIN_LIST_COUNT_HORIZEN = 0;
	public static int CURTAIN_LIST_COUNT_VERTICAL = 0;
	public static int CURTAIN_PEARL_COUNT_HORIZEN = 0;
	public static int CURTAIN_PEARL_COUNT_VERTICAL = 0;
	public static int DOWNLOAD_DIALOG_HEIGHT_HORIZEN = 0;
	public static int DOWNLOAD_DIALOG_HEIGHT_VERTICAL = 0;
	public static int DRAWER_LIST_COUNT_HORIZEN = 0;
	public static int DRAWER_LIST_COUNT_VERTICAL = 0;
	static String IMAGE_ROOT;
	public static String LOG_TAG;
	public static boolean ONLY_CHINESE = false;
	public static int REFRESH_INTERVAL = 0;
	public static String REPORT_URL;
	public static int REQUEST_NUMBER = 0;
	public static String REQUEST_URL;
	public static int TAG_HEIGHT = 0;
	public static int TAG_WIDTH = 0;
	public static int[] appIcon;
	public static String banben;
	public static int banner_alpha = 0;
	public static boolean blur_switcher = false;
	public static String[] des;
	public static String[] detailDes;
	public static final Object downloadMutex;
	public static boolean filterInstalledApp = false;
	public static boolean full_screen = false;
	public static final Object getDataMutex;
	public static boolean handler_auto_expand = false;
	public static boolean handler_left = false;
	public static boolean isTestMode = false;
	public static String keywords;
	public static String no;
	public static final int promoter_category_exchange = 0;
	public static final int promoter_category_house = 1;
	public static final int promoter_category_thirdparty = 2;
	public static final int report_click_to_launch = 4;
	public static final int report_click_to_promote = 2;
	public static final int report_download = 1;
	public static final int report_download_click = 3;
	public static final int report_impression = 0;
	public static int sdk_version = 0;
	public static boolean show_at_least_seven = false;
	public static boolean show_size = false;
	public static String text_color;
	public static String[] title;
	public static final int type_big_handler_bottom = 0;
	public static final int type_big_handler_top = 1;
	public static final int type_container = 8;
	public static final int type_list_curtain = 7;
	public static final int type_pearl_curtain = 9;
	public static final int type_small_handler_list_bottom = 4;
	public static final int type_small_handler_list_top = 5;
	public static final int type_small_handler_pearl_bottom = 2;
	public static final int type_small_handler_pearl_top = 3;
	public static final int type_standalone_handler = 6;
	public static String yes;

	static {
		getDataMutex = new Object();
		downloadMutex = new Object();
		BASE_URL = "http://ex.puata.info";
		String str1 = String.valueOf(BASE_URL);
		REQUEST_URL = str1 + "/api/promotion_contents";
		String str2 = String.valueOf(BASE_URL);
		REPORT_URL = str2 + "/api/promotion_logs";
		CONTAINER_AUTOEXPANDED = true;
		APPKEY = "";
		blur_switcher = true;
		show_size = false;
		keywords = "";
		show_at_least_seven = false;
		full_screen = false;
		banner_alpha = 255;
		IMAGE_ROOT = "/sdcard/download/promotion_cache/";
		LOG_TAG = "exchage";
		REFRESH_INTERVAL = 10000;
		CONTAINER_HEIGHT = 55;
		ONLY_CHINESE = true;
		REQUEST_NUMBER = 7;
		CURTAIN_PEARL_COUNT_VERTICAL = 3;
		CURTAIN_PEARL_COUNT_HORIZEN = 2;
		CURTAIN_LIST_COUNT_VERTICAL = 7;
		CURTAIN_LIST_COUNT_HORIZEN = 4;
		CONTAINER_LIST_COUNT = 7;
		DRAWER_LIST_COUNT_VERTICAL = 4;
		DRAWER_LIST_COUNT_HORIZEN = 2;
		DOWNLOAD_DIALOG_HEIGHT_VERTICAL = 200;
		DOWNLOAD_DIALOG_HEIGHT_HORIZEN = 100;
		isTestMode = false;
		filterInstalledApp = true;
		TAG_HEIGHT = 38;
		TAG_WIDTH = 38;
		handler_auto_expand = true;
		handler_left = false;
		appIcon = null;
		text_color = "#000000";
		String[] arrayOfString1 = new String[8];
		arrayOfString1[0] = "����������";
		arrayOfString1[1] = "������������Ϸ ";
		arrayOfString1[2] = "Ư��ƿ";
		arrayOfString1[3] = "Ģ��΢��";
		arrayOfString1[4] = "�ϼ�����";
		arrayOfString1[5] = "�������˶������ּ�";
		arrayOfString1[6] = "365����";
		arrayOfString1[7] = "3D��������";
		title = arrayOfString1;
		String[] arrayOfString2 = new String[8];
		arrayOfString2[0] = "֧��Ŀǰ���������ʽ����Ƶ������";
		arrayOfString2[1] = "һ��ߴ����������Ϸ";
		arrayOfString2[2] = "ÿ���˶����������Լ���Ư��ƿ";
		arrayOfString2[3] = "һ���ʵ�õ�΢���ͻ���";
		arrayOfString2[4] = "�ḻ���������ҵ����ǵ���һ��";
		arrayOfString2[5] = "һ��������ֻ���ģ�⼪�������";
		arrayOfString2[6] = "����Ϲ���ʹ��ϰ�ߵ�����";
		arrayOfString2[7] = "ȫ3D����������������Ϸ�����澫��";
		des = arrayOfString2;
		String[] arrayOfString3 = new String[8];
		arrayOfString3[0] = "һ���ܹ�֧��Ŀǰ���������ʽ����Ƶ������������xvid, h.264, avi, mov, mpg, mpeg, wmf, wav, swf, wmv, mkv, ogg, mp3, mp2, cda, flac, aac, ac3, 3gp, m4v, mp4������ı�������ܹ��ṩƽ�ȵ���Ƶ������֧�ֻҶȺͲ�ɫ��Ļ��ARMv6��ƽ̨��";
		arrayOfString3[1] = "һ��ߴ����������Ϸ����Ϸ������Ч���ӻ�������ײЧ�����������ã��ٵ������໥���ã�������ʵ����Ȥ�ı����ַ�������ÿһ��С�ؿ��������Լ����ص������ɣ����ٵ�ʱ�����ٵĵ�ҩ��ɹؿ��������Ҫ��ĥ�����顣���µ�90���ؿ�����������ˡ�";
		arrayOfString3[2] = "ÿ���û������Ի���������Լ���Ư��ƿ��������֪�������Լ���ƿ��Ư������ʲô�ط���������֪������Щ��������ƿ���������ԡ��Ķ����ֵ�Ư��ƿ���û�ÿ��һ���ص�ȥѰ�ҵ��µ�Ư��ƿ���������ȶ�Ư��ƿ�е����ݽ����Ķ���������ѡ�Լ�����Ȥ��ƿ�ӽ��лظ��͹�ע��";
		arrayOfString3[3] = "Ģ��΢����һ���ʵ�õĿͻ��ˡ�֧��ͼƬ�������Զ�չ�����б�ҳ�Զ���չ���ʺ���Wifi������ʹ�ã�";
		arrayOfString3[4] = "�ⷿ�����Ʊ���Զ��֣��ҷ����ٹ����������ϼ������ܰ��㣬�ϼ������к���������Ϣ������Ҫ���⣿���ñ�����ת�ã������ϼ�����Ҳ�ܰ��㣬�����ʹ�øϼ�������з���!ͬ�ǽ��ѣ�����������Ϣ������᣿�����ϼ�����һ�����԰��㣬����Էḻ���������ҵ����ǵ���һ�룡";
		arrayOfString3[5] = "Solo������һ��������ֻ���ģ�⼪��������������ϻ𱬵ĵ�AndroidŮ��������Solo�ߵ��߳��ġ�";
		arrayOfString3[6] = "��������365���������ֻ��棬���ֻ��ϵ����ݽ����������Ժ������ϵ�����ͬ����������ʧ��PC�����ֻ�����ʹ�û�������ʱ��������Ч��ÿ���Լ2Сʱ = �����϶��10�ꡣ�������ʹ��ܣ������ڴ�������";
		arrayOfString3[7] = "ȫ3D����������������Ϸ��ͬʱ������iphoneƽ̨�� ���澫�������������Ȥ���������Ϸ��ǰ��ͬ�������Ϸ��������ɾ�����ˣ� ����Ҫ�������������������ͨ�����ֻ��أ����ڡ����򡢵��𡢸����ذ塢���ټ��١���ƿ��صȵȡ�����Ի���Ҫ��ϸߣ�������ɲ��ֻ����޷��������С�";
		detailDes = arrayOfString3;
		banben = "�汾��";
		yes = "��";
		no = "��";
	}

	public static String getFileSizeDescription(long paramLong) {
		String str2;
		if (paramLong < 1000L) {
			String str1 = String.valueOf((int) paramLong);
			str2 = str1 + "B";
			return "��С��" + str2;
		} else {
			if (paramLong < 1000000L) {
				String str3 = String.valueOf(Math
						.round((float) paramLong / 1000.0D));
				str2 = str3 + "K";
			} else if (paramLong < 1000000000L) {
				DecimalFormat localDecimalFormat1 = new DecimalFormat("#0.0");
				double d1 = (float) paramLong / 1000000.0D;
				String str4 = String.valueOf(localDecimalFormat1.format(d1));
				str2 = str4 + "M";
			} else {
				DecimalFormat localDecimalFormat2 = new DecimalFormat("#0.00");
				double d2 = (float) paramLong / 1000000000.0D;
				String str5 = String.valueOf(localDecimalFormat2.format(d2));
				str2 = str5 + "G";
			}
		}
		return str2;
	}
}
