package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class DoblePiramide {

	public Piramide piramide;
	
	public DoblePiramide(){
		piramide = new Piramide();
	}
	public void dibuja(GL10 gl){
		piramide.dibuja(gl);
		gl.glTranslatef(0, -2, 0);
		gl.glRotatef(180, 1, 0, 0);
		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		piramide.dibuja(gl);
	}
}
