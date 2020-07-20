package com.example.laboratorio5_movimiento3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.view.MotionEvent;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 02/04/2014
 *
 */
public class Renderiza extends GLSurfaceView implements Renderer {

	/* Objeto */
	private Cubo cubo;
	private Piso piso;
	private Plano plano;
	private Circulo circulo;
	
	private Camino camino;
	private ArbolPiramides arbolP;
	private ArbolDoblePiramide arbolDP;
	private Sol sol;
	private Casa casa;

	private Torre torre;
	private Torre2 torre2;
	
	private RelojArena reloj;
	private DobleCruz cruz;
	
	/* Inicializa ubicación de la vista del observador */
	private final float[] vectorEntrada = { 0, 0, -1, 1 };
	private static float posicion[] = { 0, 0, 0 };
	private final float[] direccion = new float[4];

	/* Tamaño de la ventana en pixeles */
	private int alto;
	private int ancho;
	
	/* Para la rotación y traslación */
	private float rotY;
	private	float antX;
	
	final float[] matriz = new float[16];
	
	/* Contexto */
	Context contexto;
	
	public Renderiza(Context contexto) {
		super(contexto);
		this.contexto = contexto;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		
		cubo = new Cubo();
		piso = new Piso();
		plano = new Plano();
		arbolP = new ArbolPiramides();
		arbolDP = new ArbolDoblePiramide();
		circulo = new Circulo(0.5f, 300, true);
        sol = new Sol();
        casa = new Casa(gl);
        camino = new Camino();
        torre = new Torre();
        torre2 = new Torre2();
        reloj = new RelojArena();
        cruz = new DobleCruz();
        
        gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearColor(176/255f, 196/255f, 222/256f, 0);

	}
	
	@Override
	public void onDrawFrame(GL10 gl) {

		/* Borra el buffer de la ventana y del z-buffer */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		/* Botones de las opciones */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-4, 4, -6, 6, 1, 100);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		// Botón avanza
		gl.glPushMatrix();
		gl.glTranslatef(0, -4, 0);
		gl.glColor4f(0, 0, 1, 1);
		circulo.dibuja(gl);
		gl.glPopMatrix();
		
		// Botón retrocede
		gl.glPushMatrix();
		gl.glTranslatef(0, -5.5f, 0);
		gl.glColor4f(0, 0, 1, 1);
		circulo.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 67, ancho / (float)alto, 1f, 100f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotatef(-rotY, 0, 1, 0);
		gl.glTranslatef(-posicion[0], -posicion[1], -posicion[2]);
		
//		// Piso
//		gl.glPushMatrix();
//		gl.glTranslatef(0, 0, -6);
//		piso.dibuja(gl);
//		gl.glPopMatrix();
		
		// Plano
		gl.glPushMatrix();
		gl.glColor4f(0, 1, 64/255f, 1);
		gl.glTranslatef(0, -1f, -6);
//		gl.glRotatef(10, 1, 0, 0);
		gl.glScalef(50, 1, 50);
		plano.dibuja(gl);
		gl.glPopMatrix();
		
		//Camino
		gl.glPushMatrix();
		gl.glTranslatef(0, -0.9f, -6);
//		gl.glRotatef(10, 1, 0, 0);
		gl.glScalef(0.3f, 1, 1);
		camino.dibuja(gl);
		gl.glPopMatrix();
		//Camino
		gl.glPushMatrix();
		gl.glTranslatef(0, -0.9f, -10);
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.3f, 1, 1);
		camino.dibuja(gl);
		gl.glPopMatrix();
		
		dibujaArboles(gl);
		dibujaCasas(gl);
		
		//Torre
		gl.glPushMatrix();
		gl.glTranslatef(1.5f, 1f, -12);
		gl.glColor4f(1, 0, 0, 1);
		torre.dibuja(gl);
		gl.glPopMatrix();
		
		//Torre
		gl.glPushMatrix();
		gl.glTranslatef(-7.5f, 1f, -8);
		gl.glColor4f(1, 0, 0, 1);
		torre.dibuja(gl);
		gl.glPopMatrix();

		//Torre2
		gl.glPushMatrix();
		gl.glTranslatef(-1.5f, 1f, -12);
		gl.glColor4f(1, 0, 0, 1);
		torre2.dibuja(gl);
		gl.glPopMatrix();

		//Torre2
		gl.glPushMatrix();
		gl.glTranslatef(1.5f, 1f, 3);
		gl.glColor4f(1, 0, 0, 1);
		torre2.dibuja(gl);
		gl.glPopMatrix();

		dibujaCruces(gl);
		
		//RelojArena
		gl.glPushMatrix();
		gl.glTranslatef(4.5f, 0.1f, -12);
		reloj.dibuja(gl);
		gl.glPopMatrix();

		//RelojArena
		gl.glPushMatrix();
		gl.glTranslatef(-2f, 0.1f, 3);
		reloj.dibuja(gl);
		gl.glPopMatrix();

		//RelojArena
		gl.glPushMatrix();
		gl.glTranslatef(2f, 0.1f, -18);
		reloj.dibuja(gl);
		gl.glPopMatrix();
		
		//Sol
		gl.glPushMatrix();
		gl.glTranslatef(0, 30, -70);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		sol.dibuja(gl);
		gl.glPopMatrix();

		gl.glFlush();

	}
	public void dibujaArboles(GL10 gl){

		//Arbol
		gl.glPushMatrix();
		gl.glTranslatef(-1.5f, 0.5f, -8);
		gl.glScalef(0.5f, 1, 0.5f);
		gl.glColor4f(1, 1, 0, 1);
		arbolP.dibuja(gl);
		gl.glPopMatrix();
		
		//Arbol
		gl.glPushMatrix();
		gl.glTranslatef(1.5f, 0.5f, -21);
		gl.glScalef(0.5f, 1, 0.5f);
		arbolP.dibuja(gl);
		gl.glPopMatrix();

		//Arbol
		gl.glPushMatrix();
		gl.glTranslatef(-1.5f, 0.5f, -18);
		gl.glScalef(0.5f, 1, 0.5f);
		arbolDP.dibuja(gl);
		gl.glPopMatrix();

		//Arbol
		gl.glPushMatrix();
		gl.glTranslatef(-4.5f, 0.5f, -8);
		gl.glScalef(0.5f, 1, 0.5f);
		arbolDP.dibuja(gl);
		gl.glPopMatrix();
		
		//Arbol
		gl.glPushMatrix();
		gl.glTranslatef(1.5f, 0.5f, -3);
		gl.glScalef(0.5f, 1, 0.5f);
		arbolP.dibuja(gl);
		gl.glPopMatrix();
		
		//Arbol2
		gl.glPushMatrix();
		gl.glTranslatef(4.5f, 0.5f, -8);
		gl.glScalef(0.5f, 1, 0.5f);
		arbolDP.dibuja(gl);
		gl.glPopMatrix();
		
		for(float i = -29f; i<23; i += 6){
			gl.glPushMatrix();
			gl.glTranslatef(22.5f, 0.5f, i);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolDP.dibuja(gl);
			gl.glPopMatrix();
			
			//Arbol
			gl.glPushMatrix();
			gl.glTranslatef(22.5f, 0.5f, i+3f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolP.dibuja(gl);
			gl.glPopMatrix();
			
		}
		for(float i = -29f; i<23; i += 6){
			gl.glPushMatrix();
			gl.glTranslatef(-22.5f, 0.5f, i);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolDP.dibuja(gl);
			gl.glPopMatrix();
			
			//Arbol
			gl.glPushMatrix();
			gl.glTranslatef(-22.5f, 0.5f, i+3f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolP.dibuja(gl);
			gl.glPopMatrix();
			
		}
		for(float i = -22.5f; i<23; i += 6){
			gl.glPushMatrix();
			gl.glTranslatef(i, 0.5f, 22.5f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolDP.dibuja(gl);
			gl.glPopMatrix();
			
			//Arbol
			gl.glPushMatrix();
			gl.glTranslatef(i+3, 0.5f, 22.5f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolP.dibuja(gl);
			gl.glPopMatrix();
			
		}
		for(float i = -22.5f; i<23; i += 6){
			gl.glPushMatrix();
			gl.glTranslatef(i, 0.5f, -31.5f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolDP.dibuja(gl);
			gl.glPopMatrix();
			
			//Arbol
			gl.glPushMatrix();
			gl.glTranslatef(i+3, 0.5f, -31.5f);
			gl.glScalef(0.5f, 1, 0.5f);
			arbolP.dibuja(gl);
			gl.glPopMatrix();
			
		}
	}
	public void dibujaCasas(GL10 gl){
		//Casa
		gl.glPushMatrix();
		gl.glTranslatef(1.5f, -0.5f, -6);
		gl.glScalef(0.5f, 0.5f, 1f);
		gl.glColor4f(1, 0, 0, 1);
		casa.dibuja(gl);
		gl.glPopMatrix();
		
		//Casa
		gl.glPushMatrix();
		gl.glTranslatef(-1.5f, -0.5f, -3);
		gl.glScalef(0.5f, 0.5f, 1f);
		gl.glColor4f(166/255f, 0/255f, 4/255f, 1);
		casa.dibuja(gl);
		gl.glPopMatrix();
		
		//Casa
		gl.glPushMatrix();
		gl.glTranslatef(-4.5f, -0.5f, -12);
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.5f, 0.5f, 1f);
		gl.glColor4f(205/255f, 100/255f, 50/255f, 1);
		casa.dibuja(gl);
		gl.glPopMatrix();
		
		//Casa
		gl.glPushMatrix();
		gl.glTranslatef(7.5f, -0.5f, -12);
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.5f, 0.5f, 1f);
		gl.glColor4f(102/255f, 45/255f, 153/255f, 1);
		casa.dibuja(gl);
		gl.glPopMatrix();
		
		//Casa
		gl.glPushMatrix();
		gl.glTranslatef(-1.5f, -0.5f, -15);
		gl.glScalef(0.5f, 0.5f, 1f);
		gl.glColor4f(102/255f, 45/255f, 153/255f, 1);
		casa.dibuja(gl);
		gl.glPopMatrix();
	}
	public void dibujaCruces(GL10 gl){
		//Cruz
		gl.glPushMatrix();
		gl.glTranslatef(-10.5f, 2f, -18);
		cruz.dibuja(gl);
		gl.glPopMatrix();
		//Cruz
		gl.glPushMatrix();
		gl.glTranslatef(10.5f, 2f, -18);
		cruz.dibuja(gl);
		gl.glPopMatrix();
		//Cruz
		gl.glPushMatrix();
		gl.glTranslatef(-10.5f, 2f, 0);
		cruz.dibuja(gl);
		gl.glPopMatrix();
		//Cruz
		gl.glPushMatrix();
		gl.glTranslatef(-10.5f, 2f, 0);
		cruz.dibuja(gl);
		gl.glPopMatrix();
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		
		ancho = w;
		alto = h;
		
		gl.glViewport(0, 0, ancho, alto);
		
		GLU.gluLookAt(gl, 0, 0, 0, 0, 0, -1, 0, 1, 0);

	}
	
	/**
	 * Maneja los eventos del movimiento en la pantalla táctil. 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		
		/* Obtiene la coordenada de la pantalla */
		float posx = e.getX();
		float posy = e.getY();
		
		/* Se considera cuando se levanta el dedo de la pantalla. */ 
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			
			/* En coordenadas del OpenGL */
			posx = ((posx / (float) ancho) * 8) - 4;
			posy = ((1 - posy / (float) alto) * 12) - 6;

			/* Verifica área elegida */
			if (puntoEstaDentroDelCirculo(posx, posy, 0, -4f, 0.5f)) { // Avanza
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);
				
				posicion[0] = posicion[0] + direccion[0] * 1f;
				posicion[1] = posicion[1] + direccion[1] * 1f;
				posicion[2] = posicion[2] + direccion[2] * 1f;
				
			} else if (puntoEstaDentroDelCirculo(posx, posy, 0, -5.5f, 0.5f)) { // Retrocede
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);
				
				posicion[0] = posicion[0] - direccion[0] * 1f;
				posicion[1] = posicion[1] - direccion[1] * 1f;
				posicion[2] = posicion[2] - direccion[2] * 1f;
			}
			requestRender();
		} else if (e.getAction() == MotionEvent.ACTION_MOVE) {
			if(antX == -1) {
				antX = posx;
			} else {							
				rotY = rotY + (posx - antX) / 10;
				antX = posx;
			}
			
			requestRender();
		} else { 
			antX = -1;
		}	
		return true;
	}
	
	private boolean puntoEstaDentroDelCirculo(float posx, float posy, float x,
			float y, float radio) {
		return (distancia2(posx, posy, x, y) < radio * radio);
	}

	public float distancia2(float x1, float y1, float x2, float y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}
