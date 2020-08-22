package juego.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {

	private final int ancho;
	private final int alto;

	private String ruta;

	public final int[] pixeles;

	private BufferedImage img;
	
//	public static final HojaSprites hojaCoches = new HojaSprites(320, 320, "/img/4.png");
//	public static final HojaSprites hojaCoches = new HojaSprites(4096, 4096, "/img/coches.jpg");
//	public static final HojaSprites hojaCoches = new HojaSprites(4096, 4096, "/img/coches.png");
	public static final HojaSprites hojaCoches = new HojaSprites(4096, 4096, "/img/tablero 4096 x 4096px 16 unidades.png");
	

	public HojaSprites(final int ancho, final int alto, final String ruta) {

		this.ancho = ancho;
		this.alto = alto;
		
		this.ruta = ruta;
		
		this.pixeles = new int[ancho * alto];

		try {

			img = ImageIO.read(HojaSprites.class.getResource(ruta));
			
			img.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ArrayIndexOutOfBoundsException e) {

			e.printStackTrace();

		}

	}
	
	public String getRuta() {
		
		return ruta;
		
	}

	public void setRuta(String ruta) {
		
		this.ruta = ruta;
		
	}

	public BufferedImage getImg() {
		
		return img;
		
	}

	public void setImg(BufferedImage img) {
		
		this.img = img;
		
	}

	public int getAncho() {
		
		return ancho;
		
	}

	public int getAlto() {
		
		return alto;
		
	}

	public int[] getPixeles() {
		
		return pixeles;
		
	}
	
	public void mostrar() {
		
		for (int i = 0; i < pixeles.length; i++) {
			
			System.out.println(pixeles[i]);
		
		}
		
	}

}