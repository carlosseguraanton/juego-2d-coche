package juego.graficos;

public final class Pantalla {
	
	private final short ancho;
	private final short alto;

	public final int[] pixeles;

	public Pantalla(final short WIDTH, final short HEIGHT) {
		
		this.ancho = WIDTH;
		this.alto = HEIGHT;

		pixeles = new int[ancho * alto];
		
	}
	
	/**
	 * Pinta la Pantalla de negro sobrescribiendo los valores de los pixeles del dibujo anterior
	 */
	public void pintarDeNegro() {
		
		for (int i = 0; i < pixeles.length; i++) {
			
			pixeles[i] = 0;
			
		}
		
	}

	/**
	 * Carga los pixels del sprite en el array pixeles
	 * @param desplazamientoJugadorX
	 * @param desplazamientoJugadorY
	 */
	public void traerPixelesDelSprite(final int desplazamientoJugadorX, final int desplazamientoJugadorY) {
		
		int mascaraSprite = Sprite.mascara;
		
		int lado = Sprite.lado;
		
		for (int x = 0; x < ancho; x++) {
			
			int posicionX = x + desplazamientoJugadorX;
			
			if (posicionX >= 0 && posicionX < ancho) {
			
				for (int y = 0; y < alto; y++) {

					int posicionY = y + desplazamientoJugadorY;

					if (posicionY >= 0 && posicionY < alto) {
						
						int indexDelPixelAOcupar = posicionX + posicionY * ancho;
						
						int posicionFinalX = x & mascaraSprite;
						int posicionFinalY = y & mascaraSprite;
						
						int indexDelPixelATransferir = posicionFinalX + posicionFinalY * lado;

						int valorDelPixelATransferir = Sprite.asfalto.pixeles[indexDelPixelATransferir];
							
						pixeles[indexDelPixelAOcupar] = valorDelPixelATransferir;

					}

				}
			
			}
			
		}
		
	}

	public int obtenAncho() {
		
		return ancho;
		
	}

	public int obtenAlto() {
		
		return alto;
		
	}

}
