package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class RelojArena {

	private Piramide piramide;
	private Cubo cubo;
	
	public RelojArena(){
		piramide = new Piramide();
		cubo = new Cubo();
	}
	public void dibuja(GL10 gl){
		gl.glPushMatrix();
		gl.glColor4f(64/255f, 0, 0, 1);
		gl.glTranslatef(0, -1, 0);
		gl.glScalef(1.2f, 0.2f, 1.2f);
		cubo.dibuja(gl);
		gl.glScalef(1, 5, 1);
		gl.glTranslatef(0, 1, 0);
		gl.glTranslatef(0, 3, 0);
		gl.glScalef(1, 0.2f, 1);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glColor4f(1, 1, 128/255f, 1);
		
		piramide.dibuja(gl);
		gl.glTranslatef(0, 2, 0);
		gl.glRotatef(180, 1, 0, 0);
		gl.glColor4f(1, 1, 128/255f, 1);
		piramide.dibuja(gl);
		gl.glPopMatrix();
	}
}
