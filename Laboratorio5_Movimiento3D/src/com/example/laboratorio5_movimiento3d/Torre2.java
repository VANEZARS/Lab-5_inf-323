package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class Torre2 {

	private Cubo cubo;
	private DoblePiramide dPiramide;
	
	public Torre2(){
		cubo = new Cubo();
		dPiramide = new DoblePiramide();
	}
	
	public void dibuja(GL10 gl){
		gl.glPushMatrix();
		gl.glColor4f(239/255f, 228/255f, 156/255f, 1);
		gl.glScalef(0.5f, 2, 0.5f);
		cubo.dibuja(gl);
		gl.glColor4f(219/255f, 208/255f, 136/255f, 1);
		gl.glTranslatef(0, 1f, 0);
		gl.glScalef(1.2f, 0.1f, 1.2f);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		gl.glColor4f(48/255f, 167/255f, 167/255f, 1);
		gl.glTranslatef(0, 3.5f, 0);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		dPiramide.dibuja(gl);
	}
}
