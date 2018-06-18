package com.android.ggallery.museodellemarionette;

import android.app.Application;

import java.util.Map;

public class Global extends Application {

    Beacon NearestBeacon;

    public Beacon getNearestBeacon(){

        return NearestBeacon;
    }

    public void setNearestBeacon(Beacon nearestbeacon){

        NearestBeacon=nearestbeacon;
    }

    Boolean inStaticActivity;

    public Boolean getInStaticActivity(){

        return inStaticActivity;
    }

    public void setInStaticActivity(Boolean _inStaticActivity){

        inStaticActivity=_inStaticActivity;
    }

}