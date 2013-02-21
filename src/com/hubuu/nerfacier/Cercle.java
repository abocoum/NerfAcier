package com.hubuu.nerfacier;

public class Cercle {

	private float rayon;
	private float x; 
	private float y; 


	public Cercle(){
		rayon=50;
		x=50; 
		y=50;
	}

	public Cercle(int x, int y,int rayon){
		this.rayon = rayon;
		this.x=x;
		this.y=y;
	}

	public Cercle(float rayon ){
		this.rayon = rayon;		 
	}


	public float getRayon() {
		return rayon;
	}

	public void setRayon(float rayon) {
		this.rayon = rayon;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

}
