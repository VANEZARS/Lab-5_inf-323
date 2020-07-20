package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class Casa {
	
	private Cubo cubo;
	private Prisma prisma;
	
	public Casa(GL10 gl){
		cubo = new Cubo();
		prisma =  new Prisma();
		
	}
	public void dibuja(GL10 gl){
		//gl.glColor4f(167/255f, 88/255f, 88/255f, 1);
	
		gl.glTranslatef(0, 2, 0);
		prisma.dibuja(gl);
		gl.glTranslatef(0, -2, 0);
		gl.glColor4f(0.9f, 0.9f, 0, 1);
		cubo.dibuja(gl);
	}
}
