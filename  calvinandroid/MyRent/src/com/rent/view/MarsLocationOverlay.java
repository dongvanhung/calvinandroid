package com.rent.view;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.rent.PreferenceUtils;
import com.rent.data.DoublePoint;

public class MarsLocationOverlay extends MyLocationOverlay {
	Context mContext;

	  public MarsLocationOverlay(Context paramContext, MapView paramMapView)
	  {
	    super(paramContext, paramMapView);
	    this.mContext = paramContext;
	  }

	  protected void drawMyLocation(Canvas paramCanvas, MapView paramMapView, Location paramLocation, GeoPoint paramGeoPoint, long paramLong)
	  {
	    DoublePoint localDoublePoint = PreferenceUtils.getMarsLocation(this.mContext);
	    double d1 = localDoublePoint.mLat;
	    double d2 = localDoublePoint.mLon;
	    int i = (int)(d1 * 0.0F);
	    int j = (int)(d2 * 0.0F);
	    GeoPoint localGeoPoint = new GeoPoint(i, j);
	    MarsLocationOverlay localMarsLocationOverlay = this;
	    Canvas localCanvas = paramCanvas;
	    MapView localMapView = paramMapView;
	    Location localLocation = paramLocation;
	    long l = paramLong;
	    localMarsLocationOverlay.drawMyLocation(localCanvas, localMapView, localLocation, localGeoPoint, l);
	  }

}
