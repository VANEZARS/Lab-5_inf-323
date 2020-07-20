package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class ArbolPiramides {
	
	private Piramide piramide;
	private Cubo cubo;
	public ArbolPiramides() {
		piramide = new Piramide();
		cubo = new Cubo();
	}
	public void dibuja(GL10 gl){
		gl.glPushMatrix();
		gl.glScalef(0.3f, 2, 0.3f);
		gl.glColor4f(100/255f, 60/255f, 40/255f, 1);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScalef(1, 1, 1);
		gl.glTranslatef(0, 0.5f, 0);
		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		piramide.dibuja(gl);
		gl.glTranslatef(0, 0.7f, 0);
		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		piramide.dibuja(gl);
		gl.glTranslatef(0, 0.7f, 0);
		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		piramide.dibuja(gl);
		gl.glPopMatrix();
	}
}
