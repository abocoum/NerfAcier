package com.hubuu.nerfacier;

import java.text.DecimalFormat;

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
	private double[] m_dOrentationValue = new double[3];
	
	private double[] m_dOldOrentationValues = new double[20];
	private int currentIndex=0;
	
	private float depart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jouer_activitity);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor. TYPE_ORIENTATION );
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		
		init ();
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
			
//			m_dOrentationValue[0] = event.values[0];
//			m_dOrentationValue[1] = event.values[1];
//			m_dOrentationValue[2] = event.values[2];
			 
			
			doCalculate(event.values[1]);	 
			Log.v("man", "end MAin");
		}
	}
	
	 void init () {
		 // initialize old value to zero
		 for(int i = 0; i<m_dOldOrentationValues.length; i++ ) {
			 m_dOldOrentationValues[i]=0;
		 }
	 }
	
	void doCalculate(  float y){
		double moyenne =0;
		double aroundY = Math.round( y*100.0)/100.0;
		m_dOldOrentationValues[currentIndex] = Math.abs(aroundY);
		currentIndex = (currentIndex+1)%20;
		
		for(int i = 0; i<m_dOldOrentationValues.length; i++ ) {
			moyenne+=m_dOldOrentationValues[i];
		 }
		
		moyenne /=20;
		moyenne = Math.round( moyenne*100.0)/100.0;
		if (moyenne>1.0){
			TextView tv = (TextView)findViewById(R.id.textview);
			tv.setText( "mouv moy: "+" "+moyenne + tv.getText());
		}
		
	}
	

}
