package com.rent.data;

import java.util.ArrayList;

public class CommunityDetail {

	public String address;
	  public String arch_type;
	  public String bank;
	  public String building_area;
	  public String bus;
	  public String businessDistrict;
	  public String cover_area;
	  public String decoration_info;
	  public String developer;
	  public String finish_time;
	  public String floor_area_ratio;
	  public String floors;
	  public String hospital;
	  public int housePrice;
	  public double houseTrend;
	  public long id;
	  public String intro;
	  public String kindergarten;
	  public double lat;
	  public double lon;
	  public String medicalStation;
	  public String name;
	  public ArrayList<String> picHuxing;
	  public ArrayList<String> picShijing;
	  public String postOffice;
	  public String priceChaturl;
	  public String primarySchool;
	  public String property_company;
	  public String property_fee;
	  public String property_type;
	  public int rentPrice;
	  public double rentTrend;
	  public String restaurant;
	  public String school;
	  public String shopping;
	  public String start_time;
	  public String subway;
	  public String thumbnail;

	  public CommunityDetail()
	  {
	    ArrayList localArrayList1 = new ArrayList();
	    this.picHuxing = localArrayList1;
	    ArrayList localArrayList2 = new ArrayList();
	    this.picShijing = localArrayList2;
	  }
}
