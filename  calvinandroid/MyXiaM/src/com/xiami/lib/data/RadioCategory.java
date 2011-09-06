package com.xiami.lib.data;

import java.util.ArrayList;
import java.util.List;

public class RadioCategory {

	String name;
	  List<Radio> radios;

	  public static RadioCategory getTestCategory()
	  {
	    RadioCategory localRadioCategory = new RadioCategory();
	    String str1 = String.valueOf(System.currentTimeMillis() % 1000L);
	    localRadioCategory.setName(str1);
	    ArrayList localArrayList = new ArrayList();
	    int i = 0;
	    while (true)
	    {
	      if (i >= 10)
	      {
	        localRadioCategory.setRadios(localArrayList);
	        return localRadioCategory;
	      }
	      Radio localRadio = new Radio();
	      Object[] arrayOfObject = new Object[2];
	      Integer localInteger = Integer.valueOf(i);
	      arrayOfObject[0] = localInteger;
	      String str2 = localRadioCategory.getName();
	      arrayOfObject[1] = str2;
	      String str3 = String.format("%d - %s", arrayOfObject);
	      localRadio.setTitle(str3);
	      boolean bool = localArrayList.add(localRadio);
	      i += 1;
	    }
	  }

	  public String getName()
	  {
	    return this.name;
	  }

	  public List<Radio> getRadios()
	  {
	    return this.radios;
	  }

	  public void setName(String paramString)
	  {
	    this.name = paramString;
	  }

	  public void setRadios(List<Radio> paramList)
	  {
	    this.radios = paramList;
	  }
}
