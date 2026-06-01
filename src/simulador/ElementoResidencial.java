package simulador;

import tipos.ElementoUrbanoAbstracto;
import tipos.EstadoAmbiental;
import tipos.ParametroAmbiental;

public class ElementoResidencial extends ElementoUrbanoAbstracto {
	
	public static int id = 1;
	
	private final int identificador;
	
	public ElementoResidencial(String nombre, int col, int fila) {
		super(nombre, col, fila);
		this.identificador = id;
		id++;
	}

	@Override
	public EstadoAmbiental getEfectoAmbiental() {
		
		EstadoAmbiental e1 = new EstadoAmbiental();
		e1.agregarParametro(new ParametroAmbiental("CO2", "ppm", 4)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 4)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", 4));
		return e1;
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
		col = coordenadaX;
		fila = coordenadaY;		
	}
	
}