package com.rent.thread;

import java.io.File;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.rent.Rent;

public class CommunityDetailThread extends AbstractThread{

	private String mUrl = "http://api.99fang.com/core/1/comm_detail?channel=rent&city=%s&name=%s";

	  public CommunityDetailThread(Handler paramHandler, String paramString1, String paramString2)
	  {
	    super(paramHandler);
	    String str1 = this.mUrl;
	    Object[] arrayOfObject = new Object[2];
	    arrayOfObject[0] = paramString1;
	    arrayOfObject[1] = paramString2;
	    String str2 = String.format(str1, arrayOfObject);
	    this.mUrl = str2;
	  }

	  protected Message getMessageByContent(String paramString)
	  {
	    Bundle localBundle = new Bundle();
	    localBundle.putString("data", paramString);
	    Message localMessage = new Message();
	    localMessage.setData(localBundle);
	    return localMessage;
	  }

	  protected boolean isSuccessful(String paramString)
	    throws JSONException
	  {
	    if (paramString != null)
	    {
	      String str = new JSONObject(paramString).getString("message");
	      if (!"success".equals(str))
	    	  return false;
	      return true;
	    }
	    return false;
	  }

	  public void run()
	  {
	    String str1 = this.mUrl;
	    super.setUrl(str1);
	    StringBuilder localStringBuilder = new StringBuilder().append("Url=");
	    String str2 = this.mUrl;
	    String str3 = str2;
	    Rent.MyLog("CommunityDetailThread.run()", str3);
	    try
	    {
//	      String str4 = executeGet();
	      String str4 = "{\"message\": \"success\", \"data\": {\"parking_info\": \"\u5145\u8db3\", \"price_chart_url\": \"http://chart.apis.google.com/chart?chxl=0:|05\u6708|06\u6708|07\u6708|08\u6708&chxr=0,0,3|1,3300,3900&chxt=x,y&&cht=lc&chds=3300,3900&chd=t:3575,3783,3766,3566&chg=33.333,20&chls=2&chma=0,0,10,0&chm=N,FF4303,0,-1,11|o,FF4302,0,-1,5\", \"house_price\": 23853, \"others\": \"\u6e38\u6cf3\u6c60 \u9910\u5385 \u5065\u8eab\u623f \u505c\u8f66\u573a \u6d17\u8863\u5e97 \u5496\u5561\u5385 \u68cb\u724c\u5ba4\", \"business_district\": \"\u63a7\u6c5f / \u6768\u6d66\u5927\u6865\", \"area\": \"\u4e0a\u6d77-\u6768\u6d66-\u5468\u5bb6\u5634\u8def\", \"intro\": \"\u5b66\u6821\uff1a \u7acb\u4fe1\u4f1a\u8ba1\u5b66\u6821(\u6768\u6d66\u5206\u6821),\u4e0a\u6d77\u5e02\u6768\u6d66\u533a\u63a7\u6c5f\u4e8c\u6751\u5c0f\u5b66\u5206\u6821,\u6768\u6d66\u5c0f\u5b66(\u4e1c\u533a),\u9f50\u9f50\u54c8\u5c14\u8def\u7b2c\u4e00\u5c0f\u5b66,\u4e0a\u6d77\u5e02\u6559\u80b2\u79d1\u5b66\u7814\u7a76\u9662\u9644\u5c5e\u4e2d\u5b66,\u4e0a\u6d77\u5e02\u5e02\u4e1c\u521d\u7ea7\u4e2d\u5b66,\u5efa\u8bbe\u521d\u7ea7\u4e2d\u5b66,\u4e0a\u6d77\u5e02\u6768\u6d66\u533a\u970d\u5170\u6258\u513f\u6240\u9f50\u6768\u4e2d\u5b66;\u4e0a\u6d77\u5e02\u6811\u4eba\u4e2d\u5b66;\u4e1c\u832d\u767d\u56ed\u5e7c\u513f\u56ed;\u5927\u6865\u4e2d\u5b66;\u6c11\u529e\u9633\u6d66\u5c0f\u5b66;\u4e0a\u6d77\u5e02\u6559\u79d1\u9662\u9644\u5c5e\u4e2d\u5b66;\u5411\u9633\u5e7c\u513f\u56ed;\u6768\u6d66\u5c0f\u5b66\u5206\u6821;\u63a7\u6c5f\u4e8c\u6751\u5c0f\u5b66\u5206\u6821;\u4e0a\u6d77\u5e02\u5e02\u4e1c\u521d\u7ea7\u4e2d\u5b66;\u6559\u5e08\u8fdb\u4fee\u5b66\u9662\u5b9e\u9a8c\u5c0f\u5b66;\u6768\u6d66\u5c0f\u5b66(\u4e1c\u533a);\u957f\u9633\u5e7c\u513f\u56ed;\", \"images\": {\"pic_huxing\": [\"http://client.fstat.net/community/community_pic/92/118/447701.jpg\", \"http://client.fstat.net/community/community_pic/574/760/447702.jpg\", \"http://client.fstat.net/community/community_pic/30/802/684067.jpg\", \"http://client.fstat.net/community/community_pic/66/119/684068.jpg\", \"http://client.fstat.net/community/community_pic/512/275/684069.jpg\", \"http://client.fstat.net/community/community_pic/11/240/684070.jpg\", \"http://client.fstat.net/community/community_pic/412/756/684071.jpg\", \"http://client.fstat.net/community/community_pic/204/974/684072.jpg\", \"http://client.fstat.net/community/community_pic/938/959/684073.jpg\", \"http://client.fstat.net/community/community_pic/787/973/684074.jpg\", \"http://client.fstat.net/community/community_pic/706/443/684075.jpg\", \"http://client.fstat.net/community/community_pic/438/354/684076.jpg\", \"http://client.fstat.net/community/community_pic/125/90/684077.jpg\", \"http://client.fstat.net/community/community_pic/255/494/684078.jpg\", \"http://client.fstat.net/community/community_pic/563/697/684079.jpg\", \"http://client.fstat.net/community/community_pic/40/644/684080.jpg\", \"http://client.fstat.net/community/community_pic/979/227/684081.jpg\", \"http://client.fstat.net/community/community_pic/658/587/684082.jpg\", \"http://client.fstat.net/community/community_pic/213/646/684083.jpg\", \"http://client.fstat.net/community/community_pic/285/914/684084.jpg\", \"http://client.fstat.net/community/community_pic/693/509/684085.jpg\", \"http://client.fstat.net/community/community_pic/523/888/684086.jpg\", \"http://client.fstat.net/community/community_pic/33/467/684087.jpg\", \"http://client.fstat.net/community/community_pic/46/707/5323223.jpg\", \"http://client.fstat.net/community/community_pic/885/550/5323224.jpg\", \"http://client.fstat.net/community/community_pic/443/378/5323225.jpg\", \"http://client.fstat.net/community/community_pic/118/252/5323226.jpg\", \"http://client.fstat.net/community/community_pic/380/624/301523250434284418.jpg\", \"http://client.fstat.net/community/community_pic/650/932/15219056165545055644.jpg\", \"http://client.fstat.net/community/community_pic/699/965/14287496798056661271.jpg\", \"http://client.fstat.net/community/community_pic/351/509/6285993771249721476.jpg\", \"http://client.fstat.net/community/community_pic/309/589/10983727868771944340.jpg\", \"http://client.fstat.net/community/community_pic/191/393/9420921083139484816.jpg\", \"http://client.fstat.net/community/community_pic/183/651/7360126368964547303.jpg\"], \"pic_shijing\": [\"http://client.fstat.net/community/community_pic/318/17/178151.jpg\", \"http://client.fstat.net/community/community_pic/573/300/684066.jpg\", \"http://client.fstat.net/community/community_pic/381/218/5323191.jpg\", \"http://client.fstat.net/community/community_pic/416/87/5323192.jpg\", \"http://client.fstat.net/community/community_pic/528/913/5323193.jpg\", \"http://client.fstat.net/community/community_pic/17/654/5323194.jpg\", \"http://client.fstat.net/community/community_pic/965/897/5323195.jpg\", \"http://client.fstat.net/community/community_pic/268/559/5323196.jpg\", \"http://client.fstat.net/community/community_pic/709/845/5323197.jpg\", \"http://client.fstat.net/community/community_pic/645/64/5323198.jpg\", \"http://client.fstat.net/community/community_pic/179/699/5323199.jpg\", \"http://client.fstat.net/community/community_pic/535/0/5323200.jpg\", \"http://client.fstat.net/community/community_pic/370/297/5323201.jpg\", \"http://client.fstat.net/community/community_pic/671/574/5323202.jpg\", \"http://client.fstat.net/community/community_pic/187/508/5323203.jpg\", \"http://client.fstat.net/community/community_pic/584/183/5323204.jpg\", \"http://client.fstat.net/community/community_pic/462/989/5323205.jpg\", \"http://client.fstat.net/community/community_pic/170/994/5323206.jpg\", \"http://client.fstat.net/community/community_pic/544/2/5323207.jpg\", \"http://client.fstat.net/community/community_pic/581/322/5323208.jpg\", \"http://client.fstat.net/community/community_pic/158/546/5323209.jpg\", \"http://client.fstat.net/community/community_pic/636/200/5323210.jpg\", \"http://client.fstat.net/community/community_pic/503/854/5323211.jpg\", \"http://client.fstat.net/community/community_pic/462/889/5323212.jpg\", \"http://client.fstat.net/community/community_pic/750/870/5323213.jpg\", \"http://client.fstat.net/community/community_pic/789/582/5323214.jpg\", \"http://client.fstat.net/community/community_pic/20/453/5323215.jpg\", \"http://client.fstat.net/community/community_pic/63/472/5323216.jpg\", \"http://client.fstat.net/community/community_pic/905/390/5323217.jpg\", \"http://client.fstat.net/community/community_pic/291/260/5323218.jpg\", \"http://client.fstat.net/community/community_pic/849/321/5323219.jpg\", \"http://client.fstat.net/community/community_pic/708/662/5323220.jpg\", \"http://client.fstat.net/community/community_pic/311/868/5323221.jpg\", \"http://client.fstat.net/community/community_pic/228/963/5323222.jpg\", \"http://client.fstat.net/community/community_pic/354/521/3655106891513329619.jpg\", \"http://client.fstat.net/community/community_pic/472/359/6631108416023133952.jpg\", \"http://client.fstat.net/community/community_pic/398/200/17345243318331124606.jpg\", \"http://client.fstat.net/community/community_pic/331/445/14697018735358794842.jpg\", \"http://client.fstat.net/community/community_pic/178/212/5139820220948224651.jpg\", \"http://client.fstat.net/community/community_pic/162/562/9301665591172232649.jpg\", \"http://client.fstat.net/community/community_pic/791/343/17422693026567230308.jpg\", \"http://client.fstat.net/community/community_pic/782/957/1696005613006865853.jpg\", \"http://client.fstat.net/community/community_pic/460/146/2858154555977139641.jpg\", \"http://client.fstat.net/community/community_pic/923/56/12878112352154428871.jpg\", \"http://client.fstat.net/community/community_pic/287/524/7783529567727515477.jpg\", \"http://client.fstat.net/community/community_pic/403/756/3520801519367473622.jpg\"], \"pic_yangban\": []}, \"property_type\": \"\u516c\u5bd3\", \"id\": 70120, \"developer\": \"\u4e0a\u6d77\u51ef\u4e1c\u623f\u5730\u4ea7\u516c\u53f8\", \"city\": \"\u4e0a\u6d77\", \"alp_index\": \"D\", \"house_trend\": -1, \"hospital\": \"\u4e0a\u6d77\u6d77\u6ee8\u95e8\u8bca\u90e8,\u56fd\u5927\u836f\u623f\u4e1c\u76db\u5e97,\u5b89\u5b8f\u4fdd\u5065,\u4e0a\u6d77\u7eba\u7ec7\u7b2c\u4e8c\u533b\u9662,\u6768\u6d66\u533a\u7259\u75c5\u9632\u6cbb\u6240,\u4e0a\u6d77\u5e02\u6768\u6d66\u533a\u80bf\u7624\u8bca\u6cbb\u4e2d\u5fc3\", \"kindergarten\": \"\u5411\u9633\u5e7c\u513f\u56ed\uff08\u6768\u6d66\u533a\uff09\u3001\u51e4\u5357\u65b0\u6751\u5e7c\u513f\u56ed\u3001\u970d\u5170\u6258\u513f\u6240\", \"primary_school\": \"\u5e73\u51c9\u4e8c\u4e2d,\u6768\u6d66\u5c0f\u5b66,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u521d\u7ea7\u4e2d\u5b66\u4e1c\u90e8,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u521d\u7ea7\u4e2d\u5b66,\u6c11\u529e\u9633\u6d66\u5c0f\u5b66,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u5c0f\u5b66,\u5e94\u7528\u6280\u672f\u8fdb\u4fee\u5b66\u6821,\u6c6a\u9f50\u98ce\u82ad\u857e\u821e\u4e13\u79d1\u5b66\u6821,\u590f\u6069\u82f1\u8bed\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u5e02\u804c\u5de5\u9000\u4f11\u5927\u5b66\u6caa\u4e1c\u5206\u6821,\u674e\u658c\u7535\u6c14\u6280\u5e08\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u5de5\u7a0b\u6280\u672f\u5927\u5b66\u827a\u672f\u8bbe\u8ba1\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u4e0a\u6d77\u65b0\u4e9a\u804c\u4e1a\u6280\u672f\u5b66\u6821\", \"postoffice\": \"\u6caa\u4e1c\u5de5\u4eba\u6587\u5316\u5bab\u90ae\u653f\u6240\u3001\u4e0a\u6d77\u5e02\u90ae\u653f\u5c40\u5e02\u5317\u533a\u5c40\u6768\u6d66\u90ae\u7535\u652f\u5c40\", \"latitude\": 3127059, \"thumbnail\": \"http://client.fstat.net/community/thumbnail/318/17/178151.jpg\", \"accuracy\": 9.5, \"property_fee\": \"0.50\u5143/\u5e73\u65b9\u7c73/\u6708\", \"shopping\": \"\u534e\u8054\u8d85\u5e02NO.2801,\u8fea\u4e9a\u5929\u5929\u6298\u6263\u8d85\u5e02(\u5e73\u51c9\u8def),\u5bb6\u5f97\u5229\u8d85\u5e02(no.60),\u5bb6\u5f97\u5229\u8d85\u5e02NO.060,\u5bb6\u5f97\u5229\u8d85\u5e02\u5e73\u51c9\u5e97,\u8389\u5b9d\u7f8e\u5bb9\u7f8e\u53d1\u7528\u54c1\u4e13\u5356\u5e97,\u4e0a\u6d77\u70df\u8349\u96c6\u56e2\u70df\u9152\u4e13\u5356\u5e97\u6377\u5f3a275,\u4e5d\u73af\u7535\u52a8\u8f66\u84c4\u7535\u6c60\u4e0a\u6d77\u4e13\u5356\u5e97,\u6d77\u5c14\u5e73\u51c9\u4e13\u5356\u5e97,\u6210\u4eba\u5065\u5eb7\u7528\u54c1\u4e13\u5356\u5e97,\u91d1\u661f\u5f69\u7535\u4e16\u7eaa\u98ce(\u5e73\u51c9\u4e13\u5356\u5e97),\u5c0f\u6c64\u8fde\u9501\u6c34\u679c\u5927\u5356\u573a,\u50bb\u5947\u7092\u8d27\u5e73\u4ef7\u5927\u5356\u573aNO.158,\u7eff\u8272\u597d\u679c\u591a\u5927\u5356\u573a,\u8212\u60e0\u5bb6\u5177\u5927\u5356\u573a,\u5927\u5e86\u7cbe\u54c1\u603b\u6c47,\u6b23\u987a\u5546\u53a6\", \"arch_type\": \"\u666e\u901a\u4f4f\u5b85\", \"bus\": \"25\u8def,22\u8def,934\u8def,137\u8def,538\u8def,8\u8def,868\u8def,842\u8def\", \"green_rate\": 0.34999999999999998, \"address\": \"\u6768\u6d66\u533a\u4e0a\u6d77\u6768\u6d66\u7709\u5dde\u8def888\u5f041\u53f7\", \"rent_trend\": -4, \"bank\": \"\u4e0a\u6d77\u6d66\u4e1c\u53d1\u5c55\u94f6\u884c(\u81ea\u52a9\u94f6\u884c),\u4e2d\u56fd\u5de5\u5546\u94f6\u884c,\u5408\u4f5c\u94f6\u884c\u5b9a\u6d77\u652f\u884c\u8425\u4e1a\u6240,\u4e0a\u6d77\u5e02\u90ae\u653f\u5c40\u5e02\u5317\u533a\u5c40\u4e1c\u5bab\u90ae\u653f\u50a8\u84c4\u6240,\u4e0a\u6d77\u94f6\u884c\u4e34\u9752\u8def\u652f\u884c,\u5de5\u884c\u5e73\u51c9\u652f\u884c\", \"addr\": \"\u6768\u6d66\u533a\u4e0a\u6d77\u6768\u6d66\u7709\u5dde\u8def888\u5f041\u53f7\", \"school\": \"\u5e73\u51c9\u4e8c\u4e2d,\u6768\u6d66\u5c0f\u5b66,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u521d\u7ea7\u4e2d\u5b66\u4e1c\u90e8,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u521d\u7ea7\u4e2d\u5b66,\u6c11\u529e\u9633\u6d66\u5c0f\u5b66,\u4e0a\u6d77\u5e02\u5efa\u8bbe\u5c0f\u5b66,\u5e94\u7528\u6280\u672f\u8fdb\u4fee\u5b66\u6821,\u6c6a\u9f50\u98ce\u82ad\u857e\u821e\u4e13\u79d1\u5b66\u6821,\u590f\u6069\u82f1\u8bed\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u5e02\u804c\u5de5\u9000\u4f11\u5927\u5b66\u6caa\u4e1c\u5206\u6821,\u674e\u658c\u7535\u6c14\u6280\u5e08\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u5de5\u7a0b\u6280\u672f\u5927\u5b66\u827a\u672f\u8bbe\u8ba1\u5b66\u9662\u4e1c\u5bab\u5206\u9662,\u4e0a\u6d77\u65b0\u4e9a\u804c\u4e1a\u6280\u672f\u5b66\u6821\", \"debug2\": \"http://sh.ganji.com/xiaoqu/dongtiangongyu/\", \"name\": \"\u4e1c\u7530\u516c\u5bd3\", \"restaurant\": \"\u4e0a\u6d77\u4e00\u5fc3\u658b,\u4e0a\u6d77\u4e1c\u5bab\u82b1\u56ed\u9152\u697c\u6709\u9650\u516c\u53f8,\u90b5\u7a3c\u697c,\u6cf0\u6664\u58eb\u897f\u9910\u6709\u9650\u516c\u53f8\u68a7\u6850\u6811\u5496\u5561\u5c4b,\u539f\u9053\u79c1\u623f\u83dc,\u4e0a\u6d77\u9f0e\u54c1\u9c7c\u7fc5\u9910\u996e\u7ecf\u8425\u7ba1\u7406\u6709\u9650\u516c\u53f8\", \"finish_time\": \"1999\u5e74\", \"community_sign\": 4231787560, \"entertainment\": \"\u71d5\u59ff\u5f62\u50cf\u8bbe\u8ba1\u4e2d\u5fc3,\u5eb7\u7984\u6d74\u5ba4,\u68cb\u724c\u5ba4(\u5e73\u51c9\u8def),\u4e1c\u5bab\u5f71\u5267\u9662,\u5929\u97f5,\u4e1c\u5bab\u68cb\u724c\u5ba4\", \"longitude\": 12152960, \"rent_price\": 3366, \"property_company\": \"\u4e0a\u6d77\u51ef\u4e1c\u623f\u5730\u4ea7\u516c\u53f8\", \"subway\": \"\u5730\u94c18\u53f7\u7ebf\", \"debug\": \"http://shanghai.anjuke.com/v2/community/view/4005\", \"floor_area_ratio\": 2.5}}";
	      if (isSuccessful(str4))
	      {
	        Handler localHandler = this.handler;
	        Message localMessage = getMessageByContent(str4);
	        boolean bool1 = localHandler.sendMessage(localMessage);
	      } else {
	        boolean bool2 = this.handler.sendEmptyMessage(1);
	      }
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	        boolean bool3 = this.handler.sendEmptyMessage(1);
	    }
	  }
}
