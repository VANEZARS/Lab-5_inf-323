package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class DobleCruz {
	
	private Cubo cubo;
	
	public DobleCruz(){
		cubo = new Cubo();
	}
	public void dibuja(GL10 gl){
		gl.glColor4f(1, 1, 1, 1);
		gl.glPushMatrix();
		gl.glScalef(0.5f, 3, 0.5f);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, 1, 0);
		gl.glRotatef(90, 1, 0, 0);
		gl.glScalef(0.5f, 3, 0.5f);
		
		cubo.dibuja(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, 1, 0);
		gl.glRotatef(90, 0, 0, 1);
		gl.glScalef(0.5f, 3, 0.5f);
		cubo.dibuja(gl);
		gl.glPopMatrix();;
	}
}
