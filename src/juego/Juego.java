package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import juego.controles.Teclado;
import juego.graficos.Pantalla;

public class Juego extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;

	private static final String NAME = "Juego de rol 2D";

	public static final short WIDTH = 768;
	public static final short HEIGHT = 512;

	private static int aps;
	private static int dps;
	
	private static int desplazamientoJugadorX = 0;
	private static int desplazamientoJugadorY = 0;

	private static volatile boolean juegoEstaEnFuncionamiento = false;

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;
	
	
	private static BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private static DataBufferInt dataBufferInt = (DataBufferInt) img.getRaster().getDataBuffer();
	
	private static int[] pixeles = dataBufferInt.getData();

	public Juego() {
		
		Dimension dimension = new Dimension(WIDTH, HEIGHT);

		setPreferredSize(dimension);
		
		pantalla = new Pantalla(WIDTH, HEIGHT);

		teclado = new Teclado();
		
		addKeyListener(teclado);

		ventana = new JFrame(NAME);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);

		BorderLayout borderLayout = new BorderLayout();

		ventana.setLayout(borderLayout);
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		requestFocus();
		
	}

	public static void main(String[] args) {
		
		Juego juego = new Juego();

		juego.iniciar();
		
	}

	private synchronized void iniciar() {
		
		juegoEstaEnFuncionamiento = true;

		thread = new Thread(this, "graficos");
		thread.start();
		
	}

	private synchronized void detener() {
		
		juegoEstaEnFuncionamiento = false;

		try {
			
			thread.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void run() {
		
		final int nanoSegundosQueHayEnUnSegundo = 1000000000;
		
		final byte actualizacionesAEfectuarEnCadaSegundo_APS = 60;

		final long tiempoATranscurrirEntreActualizaciones = nanoSegundosQueHayEnUnSegundo / actualizacionesAEfectuarEnCadaSegundo_APS;

		long referenciaTemporalFueraDelBucleWhile = System.nanoTime();
		long referenciaParaElIndicador = System.nanoTime();

		float tiempoTranscurridoEntreAsignacionesReferenciasTemporales;

		float acumuladorNuevosRepartosTiempoCalculados_delta = 0;

		while (juegoEstaEnFuncionamiento) {
			
			final long referenciaAlInicioDelBucleWhile = System.nanoTime();

			tiempoTranscurridoEntreAsignacionesReferenciasTemporales = referenciaAlInicioDelBucleWhile - referenciaTemporalFueraDelBucleWhile;

			referenciaTemporalFueraDelBucleWhile = referenciaAlInicioDelBucleWhile;

			float repartoTiempoTranscurrido = tiempoTranscurridoEntreAsignacionesReferenciasTemporales / tiempoATranscurrirEntreActualizaciones;

			acumuladorNuevosRepartosTiempoCalculados_delta = acumuladorNuevosRepartosTiempoCalculados_delta + repartoTiempoTranscurrido;

			while (acumuladorNuevosRepartosTiempoCalculados_delta >= 1) {
				
				actualizar();

				acumuladorNuevosRepartosTiempoCalculados_delta = acumuladorNuevosRepartosTiempoCalculados_delta - 1;
				
			}

			dibujar();

			long referenciaPreSubtraccion = System.nanoTime();

			long tiempoTranscurridoDesdeReferenciaPreSubtraccionHastaReferenciaParaElIndicador = referenciaPreSubtraccion - referenciaParaElIndicador;

			if (tiempoTranscurridoDesdeReferenciaPreSubtraccionHastaReferenciaParaElIndicador > nanoSegundosQueHayEnUnSegundo) {
				
				ventana.setTitle(NAME + " || Actualizaciones por segundo: " + aps + " || Dibujos por segundo: " + dps);

				aps = 0;
				dps = 0;

				referenciaParaElIndicador = System.nanoTime();
				
			}
			
		}
		
	}

	private void actualizar() {
		
		teclado.actualizar();

		if (teclado.arriba) {
			
			desplazamientoJugadorY--;
			
		}

		if (teclado.abajo) {
			
			desplazamientoJugadorY++;
			
		}

		if (teclado.izquierda) {
			
			desplazamientoJugadorX++;
			
		}

		if (teclado.derecha) {
			
			desplazamientoJugadorX--;
			
		}

		if (teclado.salir) {
			
			System.exit(0);
			
		}

		aps = aps + 1;
		
	}

	private void dibujar() {
		
		BufferStrategy bufferStrategy = getBufferStrategy();
		
		if (bufferStrategy == null) {
			
			createBufferStrategy(3);
			
			return;
			
		}
		
		pantalla.pintarDeNegro();
		
		pantalla.traerPixelesDelSprite(desplazamientoJugadorX, desplazamientoJugadorY);
		
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		graphics.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		
		graphics.dispose();
		
		bufferStrategy.show();
		
		dps = dps + 1;
		
	}

}
