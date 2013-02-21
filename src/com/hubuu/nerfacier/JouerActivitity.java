package com.hubuu.nerfacier;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class JouerActivitity extends Activity implements SensorEventListener {


	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private float[] m_dOrentationValues = new float[3];
	
	private float[] m_dOldOrentationValues = new float[20];
	private int currentIndex=0;
	
	private float depart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jouer_activitity);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor. TYPE_ORIENTATION );
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jouer_activitity, menu);
		return true;
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION ) {
			TextView tv = (TextView)findViewById(R.id.textview);

			tv.setText("X: "+event.values[0]+"\n "+"Y: "+event.values[1]+"\n "+"Z: "+event.values[2]+"\n" );
			
			m_dOrentationValues[0] = event.values[0];
			m_dOrentationValues[1] = event.values[1];
			m_dOrentationValues[2] = event.values[2];
			
			if(m_dOrentationValues[1] <0 ){
//				sm_dOldOrentationValues[];
			}
			Log.v("man", "end MAin");
		}
	}

}
