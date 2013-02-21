package com.hubuu.nerfacier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomCircleView extends View  {

	 
	Paint paintInt;
	private Cercle cercleInt ;
	private float maxrange=1;
	

	 

	 

	public CustomCircleView(Context context, AttributeSet attr) {
		super(context, attr);
		 Init();
		 Log.v("init", "in init");
		
	}


	private void Init (){		 		 		  
		
		 
		cercleInt = new Cercle();
		
		paintInt = new Paint();
		paintInt.setColor(Color.GREEN);
		paintInt.setStyle(Paint.Style.FILL);
		Log.v("init", "in init");
		}
	
	protected void onDraw(Canvas canvas) {		
		super.onDraw(canvas);
		Log.v("CV", "in onDraw");
		
		
		int xCenter = this.getWidth() / 2, yCenter =  getHeight() / 2;
		
		
		
		float xToDisp = (cercleInt.getX() * 100) / maxrange;
		float yToDisp = (cercleInt.getY() * 100) / maxrange;

		// then draw the point using 3 circles (and translate the coordonates according to the
		// center of the screen
		canvas.drawCircle(xCenter - xToDisp, yToDisp + yCenter, cercleInt.getRayon(), paintInt);
//		canvas.drawCircle(cercleInt.getX(),cercleInt.getY(),cercleInt.getRayon(),paintInt);			
	}
		
	public Cercle getCercleInt() {
		return cercleInt;

	}


	public void setCercleInt(Cercle cercle, float maxrange) {
		this.cercleInt = cercle;
		this.maxrange= maxrange;
		this.invalidate();
		requestLayout();
	}

 


}
