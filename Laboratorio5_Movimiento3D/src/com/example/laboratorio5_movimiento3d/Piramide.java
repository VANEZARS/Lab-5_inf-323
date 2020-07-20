package com.example.laboratorio5_movimiento3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;
public class Piramide {
	/**
	 * 	  3 --------- 2
	 *   /| 		 /|
	 *  / | 		/ |
	 * 7 --------- 6  |
	 * |  |        |  |
	 * |  0 ------ |--1
	 * | /         | /
	 * |/ 		   |/
	 * 4 --------- 5
	 */
	/* Las coordenadas cartesianas (x, y, z) */
	private float vertices[] = new float[] {
			//Izquierda
			-1, -1, -1, //0
			1, -1, -1,	//1
			0,  1,  0,	//2
			//Derecha
			1, -1, -1, // 3
			1, -1, 1,  // 4 
			0,  1,  0, // 5
			//Atras
			1, -1, 1,  // 6 
			-1, -1, 1, // 7 
			0,  1,  0, // 8	
			//Adelante
			-1, -1, -1,// 9
			-1, -1, 1, // 10
			0,  1,  0, // 11	
			// Abajo
			-1, -1, -1, // 12 
			1, -1, -1,  // 13 
			1, -1, 1,   // 14 
			-1, -1, 1,  // 15 
			
	};
	/* Los colores x c/vértice (r,g,b,a) */
	byte maxColor = (byte)255;
	private byte colores[] = new byte[] {
			// Frente - lila
			maxColor, 0, maxColor, maxColor, // 4 0
			maxColor, 0, maxColor, maxColor, // 5 1
			maxColor, 0, maxColor, maxColor, // 6 2
			// Atrás - amarillo
			maxColor, maxColor, 0, maxColor, // 3 4
			maxColor, maxColor, 0, maxColor, // 2 5
			maxColor, maxColor, 0, maxColor, // 1 6
			// Izquierda - celeste
			0, maxColor, maxColor, maxColor, // 0 8
			0, maxColor, maxColor, maxColor, // 4 9
			0, maxColor, maxColor, maxColor, // 7 10
			// Derecha - rojo
			maxColor, 0, 0, maxColor, // 5 12
			maxColor, 0, 0, maxColor, // 1 13
			maxColor, 0, 0, maxColor, // 2 14
			// Abajo - azul
			0, 0, maxColor, maxColor, // 0 16
			0, 0, maxColor, maxColor, // 1 17
			0, 0, maxColor, maxColor, // 5 18
			0, 0, maxColor, maxColor, // 4 19
	};
	/* Indices */
	private short indices[] = new short [] {
			12, 13, 14, 12, 14, 15, //Abajo
			0, 1, 2,
			3, 4, 5,
			6, 7, 8,
			9, 10, 11
	};
	private FloatBuffer bufVertices;
	private ByteBuffer bufColores;
	private ShortBuffer bufIndices;
	public Piramide() {
		/* Lee los vértices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer
		/* Lee los colores */
		bufColores = ByteBuffer.allocateDirect(colores.length);
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer
		/* Lee los indices */
		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short
		bufIndices.put(indices);
		bufIndices.rewind(); // puntero al principio del buffer
	}
	public void dibuja(GL10 gl) {
		/* Se habilita el acceso al arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		/* Se habilita el acceso al arreglo de colores */
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);
		/* Se especifica los datos del arreglo= de colores */
//		gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, bufColores);
//		gl.glColor4f(34/255f, 177/255f, 76/255f, 1);
		/* Se dibuja el cubo */
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
				GL10.GL_UNSIGNED_SHORT, bufIndices);
		gl.glColor4f(0,0,0,1);
		gl.glLineWidth(2);
		gl.glDrawArrays(GL10.GL_LINES, 0, 16);
		/* Se deshabilita el acceso a los arreglos */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}