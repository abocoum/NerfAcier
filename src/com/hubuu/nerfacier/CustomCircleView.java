package com.hubuu.nerfacier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CustomCircleView extends View  {

	private Paint paintExt;
	Paint paintInt;
	private ShapeDrawable mDrawable ;
	private ShapeDrawable mDrawableForBIg ;
	private Cercle cercleInt ;
	private Cercle cercleExt;
	

	public CustomCircleView(Context context) {
		super(context);
		Log.v("Custumview", "Version1");
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);

	}

	public CustomCircleView(Cercle cercle, Context context) {

		super(context);
		Log.v("Custumview", "Version2");
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);

	}

	public CustomCircleView(Context context, AttributeSet attr) {
		super(context, attr);
		 Init();
		
	}


	private void Init (){		 		 		  
//		mDrawable = new ShapeDrawable(new OvalShape()); // ici on affiche un oval...
//		mDrawable.getPaint().setColor(Color.  GREEN);
//		mDrawable.getPaint().setStyle(Paint.Style.FILL);
//		//mDrawable.setBounds(100, 100, 300, 300); 
//		
//		//
//		mDrawableForBIg = new ShapeDrawable(new OvalShape()); // ici on affiche un oval...
//		mDrawableForBIg.getPaint().setColor(Color. BLUE);
//		mDrawableForBIg.getPaint().setStyle(Paint.Style. STROKE);
//		mDrawableForBIg.getPaint().setStrokeWidth(2);
//		//mDrawableForBIg.setBounds(100, 100, 500, 500); 
		
		cercleExt= new Cercle();
		cercleInt = new Cercle();
		
		paintInt = new Paint();
		paintInt.setColor(Color.  GREEN);
		paintInt.setStyle(Paint.Style.FILL);
		
		paintExt  = new Paint();
		paintExt.setColor(Color.BLUE);
		paintExt.setStyle(Paint.Style.STROKE);		
		paintExt.setStrokeWidth(2);
		
		
		}
	
	protected void onDraw(Canvas canvas) {		
		super.onDraw(canvas);
		Log.v("CV", "in onDraw");
		canvas.drawCircle(cercleExt.getX(),cercleExt.getY(),cercleExt.getRayon(),paintExt);
		canvas.drawCircle(cercleInt.getX(),cercleInt.getY(),cercleInt.getRayon(),paintInt);			
	}
		

	


	public Cercle getCercleExt() {
		return cercleExt;

	}


	public void setCercleExt(Cercle cercle) {
		this.cercleExt = cercle;
		this.invalidate();
		requestLayout();
	}

	
	public Cercle getCercleInt() {
		return cercleInt;

	}


	public void setCercleInt(Cercle cercle) {
		this.cercleInt = cercle;
	}

	public boolean onTouchEvent( MotionEvent arg1) {
//		switch (arg1.getAction()) {
//		case MotionEvent.ACTION_MOVE :
//		{
//			paintInt.setColor(Color.GRAY);                
//			invalidate(); // pour invalider l'image et forcer un rappel à la methode onDraw de la classe.
//		}
//		}
		paintInt.setColor(Color. MAGENTA);
		Log.v("ontouch", "paitn");
		
		this.invalidate();

		return false;
	}


}
