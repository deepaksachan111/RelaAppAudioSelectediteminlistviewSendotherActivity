package com.server18.relaxationapp;

import android.app.Activity;
import android.os.Bundle;

public class ZoomActivity extends Activity {
    Zoom imgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
/*
        Zoom mImageView = (Zoom)findViewById(R.id.customImageVIew1);
        mImageView.setImageResource(R.drawable.image7);*/


        imgDisplay = (Zoom) findViewById(R.id.customImageVIew1);
        imgDisplay.setMaxZoom(2f);
        imgDisplay.setImageResource(R.drawable.ileana);
      //SingleFingerView singleFingerView = (SingleFingerView)findViewById(R.id.tiv);



    }


}


