package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class Torre {

	private Cubo cubo;
	private Esfera esfera;
	
	public Torre(){
		cubo = new Cubo();
		esfera = new Esfera(1, 5, 5);
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
		gl.glColor4f(0.5f, 0.5f, 0.5f, 1);
		gl.glTranslatef(0, 3f, 0);
		gl.glScalef(1f, 1f, 1f);
		esfera.dibuja(gl);
	}
}
