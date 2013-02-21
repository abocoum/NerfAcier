package com.hubuu.nerfacier;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;

public class BeforePlayActivity extends Activity implements SensorEventListener {

	private Handler oHandler;
	Cercle cercleExt;
	Cercle cercleInt;
	private int rayonExt;
	private CustomCircleView custumcercle;

	private SensorManager mSensorManager;

	private Sensor mAccelerometer;

	private final float NOISE = (float) 2.0;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//on récupère le layout
		RelativeLayout lay= (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);

		lay.setBackgroundColor(Color.BLUE);
		setContentView(R.layout.activity_main);

		cercleInt = new  Cercle(300,300,100);             
		custumcercle =(CustomCircleView)findViewById(R.id.Canvas01);       
		custumcercle.setCercleInt(cercleInt); 

		rayonExt=250;
		cercleExt = new Cercle(300,300,rayonExt);   
		cercleExt.setRayon(rayonExt);
		custumcercle.setCercleExt(cercleExt);

		oHandler = new Handler();
		oHandler.postDelayed(mUpdateTimeTask, 10);
		//        custumcercle.setOnTouchListener(this);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.    TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


		Log.v("man", "end MAin");



	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() { 

			if(rayonExt>100){
				cercleExt.setRayon(rayonExt);
				custumcercle.setCercleExt(cercleExt);
				oHandler.postDelayed(this, 10);
				rayonExt--;
			}    	        	    
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

 

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub


		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			// the values you were calculating originally here were over 10000!
			int  x = (int) Math.pow(event.values[1], 2); 
			int y = (int) Math.pow(event.values[2], 2);
			if(  y<0){
				cercleInt.setY(y);
				cercleExt.setY(y);
			}

			if (x<0){
				cercleInt.setX(x);						
				cercleExt.setX(x);
			}

			custumcercle.setCercleInt(cercleInt);
			custumcercle.setCercleExt(cercleExt);


			Log.v("Sensor", "onchagesensor");
		}
	}

}
