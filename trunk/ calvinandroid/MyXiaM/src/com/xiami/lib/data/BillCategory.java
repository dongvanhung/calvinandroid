package com.xiami.lib.data;

import java.util.List;

public class BillCategory {

	private List<Bill> bills;
	  private int id;
	  private String name;

	  public BillCategory(int paramInt, String paramString, List<Bill> paramList)
	  {
	    this.id = paramInt;
	    this.name = paramString;
	    this.bills = paramList;
	  }

	  public List<Bill> getBills()
	  {
	    return this.bills;
	  }

	  public int getId()
	  {
	    return this.id;
	  }

	  public String getName()
	  {
	    return this.name;
	  }

	  public void setBills(List<Bill> paramList)
	  {
	    this.bills = paramList;
	  }

	  public void setId(int paramInt)
	  {
	    this.id = paramInt;
	  }

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }
}
