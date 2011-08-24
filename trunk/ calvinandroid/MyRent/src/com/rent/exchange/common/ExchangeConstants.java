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
		arrayOfString1[0] = "生命播放器";
		arrayOfString1[1] = "经典物理创意游戏 ";
		arrayOfString1[2] = "漂流瓶";
		arrayOfString1[3] = "蘑菇微博";
		arrayOfString1[4] = "赶集生活";
		arrayOfString1[5] = "吉他人人都是音乐家";
		arrayOfString1[6] = "365日历";
		arrayOfString1[7] = "3D重力滚球";
		title = arrayOfString1;
		String[] arrayOfString2 = new String[8];
		arrayOfString2[0] = "支持目前绝大多数格式的视频播放器";
		arrayOfString2[1] = "一款极具创意的益智游戏";
		arrayOfString2[2] = "每个人都有属于他自己的漂流瓶";
		arrayOfString2[3] = "一款简单实用的微博客户端";
		arrayOfString2[4] = "丰富娱乐生活找到心仪的另一半";
		arrayOfString2[5] = "一款可以在手机上模拟吉他的软件";
		arrayOfString2[6] = "最符合国人使用习惯的日历";
		arrayOfString2[7] = "全3D制作的重力滚球游戏，画面精美";
		des = arrayOfString2;
		String[] arrayOfString3 = new String[8];
		arrayOfString3[0] = "一款能够支持目前绝大多数格式的视频播放器，包括xvid, h.264, avi, mov, mpg, mpeg, wmf, wav, swf, wmv, mkv, ogg, mp3, mp2, cda, flac, aac, ac3, 3gp, m4v, mp4。软件的编解码器能够提供平稳的视频质量，支持灰度和彩色屏幕和ARMv6的平台！";
		arrayOfString3[1] = "一款极具创意的益智游戏，游戏的物理效果从基本的碰撞效果到重力作用，再到磁力相互作用，都有真实而有趣的表现手法。而且每一个小关卡都有着自己独特的物理定律，最少的时间最少的弹药完成关卡才是玩家要琢磨的事情。更新到90个关卡，够玩儿的了。";
		arrayOfString3[2] = "每个用户都可以获得属于他自己的漂流瓶，他可以知道现在自己的瓶子漂流到了什么地方，他可以知道有哪些人在他的瓶子里留了言。阅读发现的漂流瓶。用户每到一个地点去寻找到新的漂流瓶，都可以先对漂流瓶中的内容进行阅读。可以挑选自己感兴趣的瓶子进行回复和关注。";
		arrayOfString3[3] = "蘑菇微博是一款简单实用的客户端。支持图片和链接自动展开。列表页自动延展。适合在Wifi环境下使用！";
		arrayOfString3[4] = "租房？买火车票？淘二手？找服务？觅工作？……赶集生活能帮你，赶集生活有海量生活信息！房子要出租？闲置宝贝想转让？……赶集生活也能帮你，你可以使用赶集生活进行发布!同城交友？发布征婚信息？老乡会？……赶集生活一样可以帮你，你可以丰富娱乐生活找到心仪的另一半！";
		arrayOfString3[5] = "Solo吉他是一款可以在手机上模拟吉他的软件。网络上火爆的的Android女就是玩着Solo边弹边唱的。";
		arrayOfString3[6] = "本程序是365日历网的手机版，您手机上的数据将会与您电脑和网络上的数据同步，永不丢失。PC版与手机版结合使用会让您的时间管理更高效。每天节约2小时 = 在世上多活10年。后续精彩功能，敬请期待。。。";
		arrayOfString3[7] = "全3D制作的重力滚球游戏，同时发布在iphone平台。 画面精美，故事情节有趣，在这款游戏面前，同类滚球游戏可以永久删除掉了！ 你需要用重力控制这颗生物球通过各种机关：大炮、刺球、地震、浮动地板、加速减速、电灯开关等等。软件对机能要求较高，可能造成部分机型无法正常运行。";
		detailDes = arrayOfString3;
		banben = "版本：";
		yes = "是";
		no = "否";
	}

	public static String getFileSizeDescription(long paramLong) {
		String str2;
		if (paramLong < 1000L) {
			String str1 = String.valueOf((int) paramLong);
			str2 = str1 + "B";
			return "大小：" + str2;
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
