package com.xiami.lib.data;

import java.io.Serializable;

public class Member implements Serializable {

	private static final long serialVersionUID = -2889327954575338009L;
	  String avatar;
	  String email;
	  String nick_name;
	  String password;
	  int user_id;

	  public String getAvatar()
	  {
	    return this.avatar;
	  }

	  public String getEmail()
	  {
	    return this.email;
	  }

	  public String getNick_name()
	  {
	    return this.nick_name;
	  }

	  public String getPassword()
	  {
	    return this.password;
	  }

	  public int getUser_id()
	  {
	    return this.user_id;
	  }

	  public void setAvatar(String paramString)
	  {
	    this.avatar = paramString;
	  }

	  public void setEmail(String paramString)
	  {
	    this.email = paramString;
	  }

	  public void setNick_name(String paramString)
	  {
	    this.nick_name = paramString;
	  }

	  public void setPassword(String paramString)
	  {
	    this.password = paramString;
	  }

	  public void setUser_id(int paramInt)
	  {
	    this.user_id = paramInt;
	  }
}
