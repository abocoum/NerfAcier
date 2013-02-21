package com.hubuu.nerfacier;

 

 
 

 
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;

public class BeforePlayActivity extends Activity   implements   SensorEventListener   {

	
	Cercle cercleInt;
	private int rayon;
	private CustomCircleView custumcercle;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	
	public float xPosition, xAcceleration,xVelocity = 0.0f;
	public float yPosition,zPosition, yAcceleration,yVelocity = 0.0f;
	public float xmax,ymax, zmax;
	public float xmin,ymin, zmin;
	
	
	
	public float frameTime = 0.666f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("setContentView", "setContentView");
		setContentView(R.layout.activity_before_play);
		
		cercleInt = new  Cercle(300,300,100);             
		custumcercle =(CustomCircleView)findViewById(R.id.Canvas01);       
		
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		custumcercle.setCercleInt(cercleInt, mAccelerometer.getMaximumRange());
		Display display = getWindowManager().getDefaultDisplay();
		 
	    xmax = (float)display.getWidth() ;
	    ymax = (float)display.getHeight()  ;
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_before_play, menu);
		return true;
	}

	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Display display=  getWindowManager().getDefaultDisplay();  ;
		if (event.sensor.getType() == Sensor. TYPE_ACCELEROMETER) {
		switch (display.getRotation()) {
		case Surface.ROTATION_0:
			xPosition = event.values[0];
			yPosition = event.values[1];
			break;
		case Surface.ROTATION_90:
			xPosition = -event.values[1];
			yPosition = event.values[0];
			break;
		case Surface.ROTATION_180:
			xPosition = -event.values[0];
			yPosition = -event.values[1];
			break;
		case Surface.ROTATION_270:
			xPosition = event.values[1];
			yPosition = -event.values[0];
			break;
		}
		
		zPosition = event.values[2];

		
            //Set sensor values as acceleration
//            yAcceleration = event.values[1]; 
//            xAcceleration = event.values[2];
//            updateMinAndMax();
        }
    }
	
	
	private void updateBall() {


	    //Calculate new speed
	    xVelocity += (xAcceleration * frameTime);
	    yVelocity += (yAcceleration * frameTime);

	    //Calc distance travelled in that time
	    float xS = (xVelocity/2)*frameTime;
	    float yS = (yVelocity/2)*frameTime;

	    //Add to position negative due to sensor 
	    //readings being opposite to what we want!
	    xPosition -= xS; 
	    yPosition -= yS;

	    if (xPosition > xmax) {
	        xPosition = xmax;
	    } else if (xPosition < 0) {
	        xPosition = 0;
	    }
	    if (yPosition > ymax) { 
	        yPosition = ymax;
	    } else if (yPosition < 0) {
	        yPosition = 0;
	    }
	    updateMinAndMax();
	   
	}
	
	
	private void updateMinAndMax() {
		// set the min and max
		if (xPosition < xmin) {
			xmin = xPosition;
		} else if (xPosition > xmax) {
			xmax = xPosition;
		}
		if (yPosition < ymin) {
			ymin = yPosition;
		} else if (yPosition > ymax) {
			ymax = yPosition;
		}
		if (zPosition < zmin) {
			zmin = zPosition;
		} else if (zPosition > zmax) {
			zmax = zPosition;
		}
		
		 cercleInt.setX(xPosition);
		    cercleInt.setY(yPosition);
		    custumcercle.setCercleInt(cercleInt, mAccelerometer.getMaximumRange());
	}
	
	 
	
	 

}
