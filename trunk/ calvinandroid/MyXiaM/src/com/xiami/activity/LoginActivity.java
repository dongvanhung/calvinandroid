package com.xiami.activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiami.XiamiApp;
import com.xiami.lib.data.Member;
import com.xiami.localdb.DbHelper;
import com.xiami.util.NotificationsUtil;
import com.xiami.util.User;

public class LoginActivity extends Activity {

	private static final String LOGIN_KEY = "last_login_name";
	  private XiamiApp app;
	  private Button button_reg;
	  private Button button_submit;
	  private Context context = this;
	  private EditText email_text;
	  private View.OnClickListener joinClick;
	  private View.OnClickListener loginClick;
	  private Member member;
	  private EditText password_text;

	  public LoginActivity()
	  {
	    this.joinClick = new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    /*Intent localIntent1 = new Intent();
	    	    Intent localIntent2 = localIntent1.setClass(LoginActivity.this, JoinActivity.class);
	    	    startActivity(localIntent1);*/
	    	  }
		};
	    this.loginClick = new View.OnClickListener() {
	    	  public void onClick(View paramView)
	    	  {
	    	    String str1 = LoginActivity.this.email_text.getText().toString().replaceAll("\\s+", "");
	    	    String str2 = LoginActivity.this.password_text.getText().toString().replaceAll("\\s+", "");
	    	    LoginActivity.LoginTask localLoginTask = new LoginActivity.LoginTask( str1, str2);
	    	    Void[] arrayOfVoid = new Void[0];
	    	    AsyncTask localAsyncTask = localLoginTask.execute(arrayOfVoid);
	    	  }
		};
	  }

	  public void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    boolean bool = requestWindowFeature(1);
	    setContentView(2130903095);
	    XiamiApp localXiamiApp1 = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp1;
//	    MobclickAgent.update(this);
	    Button localButton1 = (Button)findViewById(2131165322);
	    this.button_submit = localButton1;
	    Button localButton2 = (Button)findViewById(2131165321);
	    this.button_reg = localButton2;
	    EditText localEditText1 = (EditText)findViewById(2131165319);
	    this.email_text = localEditText1;
	    EditText localEditText2 = (EditText)findViewById(2131165309);
	    this.password_text = localEditText2;
	    Button localButton3 = this.button_submit;
	    View.OnClickListener localOnClickListener1 = this.loginClick;
	    localButton3.setOnClickListener(localOnClickListener1);
	    Button localButton4 = this.button_reg;
	    View.OnClickListener localOnClickListener2 = this.joinClick;
	    localButton4.setOnClickListener(localOnClickListener2);
	    XiamiApp localXiamiApp2 = (XiamiApp)getApplicationContext();
	    this.app = localXiamiApp2;
	  }

	  protected void onResume()
	  {
	    SharedPreferences localSharedPreferences = getPreferences(0);
	    EditText localEditText = this.email_text;
	    String str = localSharedPreferences.getString("last_login_name", "");
	    localEditText.setText(str);
	    super.onResume();
	  }

	  class LoginTask extends AsyncTask<Void, Integer, Boolean>
	  {
	    String email;
	    ProgressDialog loadingDialog;
	    String msg;
	    String pwd;

	    public LoginTask(String paramString1, String arg3)
	    {
	      this.email = paramString1;
	      this.pwd = arg3;
	      ProgressDialog localProgressDialog = new ProgressDialog(LoginActivity.this);
	      localProgressDialog.setMessage("Test");
	      localProgressDialog.setIndeterminate(true);
	      localProgressDialog.setCancelable(true);
	      this.loadingDialog = localProgressDialog;
	    }

	    protected Boolean doInBackground(Void[] paramArrayOfVoid)
	    {
	      try
	      {
	        User localUser = new User();
	        String str1 = this.email;
	        String str2 = this.pwd;
	        JSONObject localJSONObject = localUser.login(str1, str2);
	        if (localJSONObject != null)
	        {
	          if (localJSONObject.getString("status").equals("ok"))
	          {
	            int i = localJSONObject.getInt("user_id");
	            String str3 = localJSONObject.getString("nick_name");
	            LoginActivity localLoginActivity = LoginActivity.this;
	            Member localMember1 = LoginActivity.this.app.getMember();
	            localLoginActivity.member = localMember1;
	            LoginActivity.this.member.setUser_id(i);
	            LoginActivity.this.member.setNick_name(str3);
	            Member localMember2 = LoginActivity.this.member;
	            String str4 = this.pwd;
	            localMember2.setPassword(str4);
	            Member localMember3 = LoginActivity.this.member;
	            String str5 = this.email;
	            localMember3.setEmail(str5);
	            XiamiApp localXiamiApp = LoginActivity.this.app;
	            Member localMember4 = LoginActivity.this.member;
	            localXiamiApp.setMember(localMember4);
	            DbHelper localDbHelper = DbHelper.getInstance(LoginActivity.this.context);
	            Member localMember5 = LoginActivity.this.member;
	            localDbHelper.setLoginMmeber(localMember5);
	            Intent localIntent1 = new Intent();
	            Intent localIntent2 = localIntent1.setAction("com.xiami.login");
	            Intent localIntent3 = localIntent1.putExtra("username", str3);
	            LoginActivity.this.sendBroadcast(localIntent1);
	          }
	          return true;
	          
	            /*String str6 = localJSONObject.getString("msg");
	            this.msg = str6;
	            localBoolean = Boolean.valueOf(0);*/
	          }
	      }
	      catch (ClientProtocolException localClientProtocolException)
	      {
	        
	      }
	      catch (JSONException localJSONException)
	      {
	       
	      }
	      catch (IOException localIOException)
	      {
	        
	      }
	      return false;
	    }

	    protected void onPostExecute(Boolean paramBoolean)
	    {
	      this.loadingDialog.dismiss();
	      if (paramBoolean.booleanValue())
	      {
	        SharedPreferences.Editor localEditor1 = LoginActivity.this.getPreferences(0).edit();
	        String str1 = this.email;
	        SharedPreferences.Editor localEditor2 = localEditor1.putString("last_login_name", str1);
	        boolean bool = localEditor1.commit();
	        Intent localIntent1 = new Intent();
	        LoginActivity localLoginActivity1 = LoginActivity.this;
	        Intent localIntent2 = localIntent1.setClass(localLoginActivity1, MainActivity.class);
	        LoginActivity.this.startActivity(localIntent1);
	      }
	      else
	      {
	        if (this.msg != null)
	        {
	          LoginActivity localLoginActivity2 = LoginActivity.this;
	          String str2 = this.msg;
	          NotificationsUtil.ToastShort(localLoginActivity2, str2);
	        }
	      }
	    }

	    protected void onPreExecute()
	    {
	      this.loadingDialog.show();
	    }

	    protected void onProgressUpdate(Integer[] paramArrayOfInteger)
	    {
	      if (paramArrayOfInteger[0].intValue() == 0)
	        NotificationsUtil.ToastShort(LoginActivity.this, "Test");
	      else
	      {
	        if (paramArrayOfInteger[0].intValue() == 1)
	        {
	          NotificationsUtil.ToastShort(LoginActivity.this, "Test");
	          
	        } else if (paramArrayOfInteger[0].intValue() != 2)
	        NotificationsUtil.ToastShort(LoginActivity.this, "TEst");
	      }
	    }
	  }
}
