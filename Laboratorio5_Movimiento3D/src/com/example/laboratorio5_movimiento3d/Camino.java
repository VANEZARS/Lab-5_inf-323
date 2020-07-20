package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class Camino {

	Plano plano;
	
	public Camino (){
		plano = new Plano();
	}
	public void dibuja(GL10 gl){
		gl.glPushMatrix();
		gl.glScalef(3, 1, 200);
		gl.glColor4f(60/255f, 60/255f, 60/255f, 1);
		plano.dibuja(gl);
		gl.glPopMatrix();
		
	}
}
