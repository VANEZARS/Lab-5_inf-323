package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.opengles.GL10;

public class Sol {
	Circulo esfera;
	Circulo esfera2;
	public Sol(){
		esfera = new Circulo(5, 20, true);
		esfera2 =  new Circulo(5, 20, false);
	}
	public void dibuja(GL10 gl){
		gl.glColor4f(1f, 0.8f, 0, 1);
		esfera.dibuja(gl);
		gl.glColor4f(1, 0, 0, 1);
		esfera2.dibuja(gl);
	}
}
