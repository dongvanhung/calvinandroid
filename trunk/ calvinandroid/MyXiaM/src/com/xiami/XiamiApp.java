package com.xiami;


import android.app.Application;
import android.content.Context;

import com.xiami.file.FileUtil;
import com.xiami.lib.data.Member;
import com.xiami.localdb.DbHelper;
import com.xiami.util.API;
import com.xiami.util.FmSetting;

public class XiamiApp extends Application {
	
	private DbHelper db;
	  private FileUtil fileUtil;
	  FmSetting fmSetting;
	  private Context mContext;
	  Member member;
	  private API webUtil;

	  public boolean checkNetwork()
	  {
	    FmSetting localFmSetting = getFmSetting();
	    Context localContext = this.mContext;
	    return localFmSetting.checkNetwork(localContext, localFmSetting, false);
	  }

	  public boolean checkNetworkWithNotify()
	  {
	    FmSetting localFmSetting = getFmSetting();
	    Context localContext = this.mContext;
	    return localFmSetting.checkNetwork(localContext, localFmSetting, true);
	  }

	  public DbHelper getDb()
	  {
	    return this.db;
	  }

	  public FileUtil getFileUtil()
	  {
	    return this.fileUtil;
	  }

	  public FmSetting getFmSetting()
	  {
	    if (this.fmSetting == null)
	    {
	      FmSetting localFmSetting1 = this.db.getFmSetting();
	      this.fmSetting = localFmSetting1;
	      if (this.fmSetting == null)
	      {
	        FmSetting localFmSetting2 = new FmSetting();
	        this.fmSetting = localFmSetting2;
	        this.fmSetting.setWifi(1);
	      }
	    }
	    return this.fmSetting;
	  }

	  public Member getMember()
	  {
	    return this.member;
	  }

	  public API getWebUtil()
	  {
	    return this.webUtil;
	  }

	  public void onCreate()
	  {
	    super.onCreate();
	    Context localContext1 = getApplicationContext();
	    this.mContext = localContext1;
	    Member localMember = new Member();
	    this.member = localMember;
	    DbHelper localDbHelper = DbHelper.getInstance(this.mContext);
	    this.db = localDbHelper;
	    API localAPI = new API(this);
	    this.webUtil = localAPI;
	    Context localContext2 = this.mContext;
	    FileUtil localFileUtil = new FileUtil(localContext2);
	    this.fileUtil = localFileUtil;
	  }

	  public void setFmSetting(FmSetting paramFmSetting)
	  {
	    this.fmSetting = paramFmSetting;
	    this.db.setFmSetting(paramFmSetting);
	  }

	  public void setMember(Member paramMember)
	  {
	    this.member = paramMember;
	  }

	  public void setWebUtil(API paramAPI)
	  {
	    this.webUtil = paramAPI;
	  }

}
