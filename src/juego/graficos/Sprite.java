package juego.graficos;

import java.awt.Point;

public final class Sprite {

	private static int x;
	private static int y;
	
	public static int lado;
	public static int mascara;
	
	public static int columna;
	public static int fila;
	
	private static Point coordenadasSprite = new Point(fila, columna);

	public int[] pixeles;
	
	private HojaSprites hojaSprites;
	
	public static final Sprite asfalto = new Sprite(32, 1, 1, HojaSprites.hojaCoches);
	
//	public static final Sprite coche00 = new Sprite(1024, 0, 0, HojaSprites.hojaCoches);
//	public static final Sprite coche01 = new Sprite(1024, 0, 1, HojaSprites.hojaCoches);
//	public static final Sprite coche02 = new Sprite(1024, 0, 2, HojaSprites.hojaCoches);
//	public static final Sprite coche03 = new Sprite(1024, 0, 3, HojaSprites.hojaCoches);
//	
//	public static final Sprite coche04 = new Sprite(1024, 1, 0, HojaSprites.hojaCoches);
//	public static final Sprite coche05 = new Sprite(1024, 1, 1, HojaSprites.hojaCoches);
//	public static final Sprite coche06 = new Sprite(1024, 1, 2, HojaSprites.hojaCoches);
//	public static final Sprite coche07 = new Sprite(1024, 1, 3, HojaSprites.hojaCoches);
//	
//	public static final Sprite coche08 = new Sprite(1024, 2, 0, HojaSprites.hojaCoches);
//	public static final Sprite coche09 = new Sprite(1024, 2, 1, HojaSprites.hojaCoches);
//	public static final Sprite coche10 = new Sprite(1024, 2, 2, HojaSprites.hojaCoches);
//	public static final Sprite coche11 = new Sprite(1024, 2, 3, HojaSprites.hojaCoches);
//	
//	public static final Sprite coche12 = new Sprite(1024, 3, 0, HojaSprites.hojaCoches);
//	public static final Sprite coche13 = new Sprite(1024, 3, 1, HojaSprites.hojaCoches);
//	public static final Sprite coche14 = new Sprite(1024, 3, 2, HojaSprites.hojaCoches);
//	public static final Sprite coche15 = new Sprite(1024, 3, 3, HojaSprites.hojaCoches);

	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hojaSprites) {
		
		Sprite.lado = lado;
		
		Sprite.mascara = lado - 1;
		
		Sprite.columna = columna;
		Sprite.fila = columna;

		this.setX(Sprite.columna * Sprite.lado);
		this.setY(Sprite.fila * Sprite.lado);
		
		pixeles = new int[this.getX() * this.getY()];
		
		this.hojaSprites = hojaSprites;
		
		for (int x = 0; x < x; x++) {
			
			for (int y = 0; y < y; y++) {
				
				int indexDelPixelAOcupar = x + y * lado;
				
				int posicionFinalX = x + this.getX();
				int posicionFinalY = y + this.getY();
				
				int indexDelPixelATransferir = posicionFinalX + posicionFinalY * this.hojaSprites.getAncho();

				int valorDelPixelATransferir = this.hojaSprites.pixeles[indexDelPixelATransferir];
				
				pixeles[indexDelPixelAOcupar] = valorDelPixelATransferir;
				
			}
			
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		Sprite.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		Sprite.y = y;
	}

	public static int getLado() {
		return lado;
	}

	public static void setLado(int lado) {
		Sprite.lado = lado;
	}

	public static int getMascara() {
		return mascara;
	}

	public static void setMascara(int mascara) {
		Sprite.mascara = mascara;
	}

	public static int getColumna() {
		return columna;
	}

	public static void setColumna(int columna) {
		Sprite.columna = columna;
	}

	public static int getFila() {
		return fila;
	}

	public static void setFila(int fila) {
		Sprite.fila = fila;
	}

	public int[] getPixeles() {
		return pixeles;
	}

	public void setPixeles(int[] pixeles) {
		this.pixeles = pixeles;
	}

	public HojaSprites getHojaSprites() {
		return hojaSprites;
	}

	public void setHojaSprites(HojaSprites hojaSprites) {
		this.hojaSprites = hojaSprites;
	}

	public void mostrar() {
		
		for (int i = 0; i < pixeles.length; i++) {
			
			System.out.println(pixeles[i]);
		
		}
		
	}

	public static Point getCoordenadasSprite() {
		return coordenadasSprite;
	}

	public static void setCoordenadasSprite(Point coordenadasSprite) {
		Sprite.coordenadasSprite = coordenadasSprite;
	}
	
}
