package simulador;

import tipos.ElementoUrbanoAbstracto;
import tipos.EstadoAmbiental;
import tipos.ParametroAmbiental;

public class ElementoVerde extends ElementoUrbanoAbstracto {
	
	public static int id = 1;
	
	private final int identificador;
	
	public ElementoVerde(String nombre, int col, int fila) {
		super(nombre, col, fila);
		this.identificador = id;
		id++;
	}

	@Override
	public EstadoAmbiental getEfectoAmbiental() {
		
		EstadoAmbiental e1 = new EstadoAmbiental();
		e1.agregarParametro(new ParametroAmbiental("CO2", "ppm", -64)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", -8)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", -4));
		return e1;
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}
	
}

