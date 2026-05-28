package tipos;

import java.util.Random;
import java.util.UUID;

/**
 *  Implementación de ISensor que:
 * - Calcula internamente el identificar del Sensor (lo sacamos del constructor).
 * - Aporta un constructor sin argumentos para un sensor por defecto devuelve medidas
 *   adimensionales.
 * - Aporta un constructor con todos los parámetros ajustables.
 * - Aporta un constructor donde se fija el parámetro que mide el sensor.
 * - Aporta un constructor sin argumentos.
 * 
 * - Incluye un método no definido en las interfaces para fijar la ciudad de la que toma
 *   sus datos.
 */
public class SensorSimple implements ISensorSimple {
	
	private static int id = 1;
	public final int identificador;
	
	protected String parametro = "ratio";
	protected int col;
	protected int fila;
		
	protected ICuadriculaUrbanaSimple ciudad;
	
	public SensorSimple(int x, int y, String parametro) {
		
		id++;
		identificador = id;
		
		col = x;
		fila = y;
		this.parametro = parametro;
	}
	
	public SensorSimple(String parametro) {
		this(0, 0, parametro);
	}
	
	public SensorSimple() {
		this(0, 0, "ratio");
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public int getCoordenadaX() {
		return col;
	}

	@Override
	public int getCoordenadaY() {
		return fila;
	}

	@Override
	public void setCoordenadaX(int col) {
		this.col = col;
	}

	@Override
	public void setCoordenadaY(int fila) {
		this.fila = fila;
	}

	@Override
	public String getParametro() {
		return parametro;
	}

	@Override
	public double getValor() {
		if (ciudad == null) {
			return new Random().nextDouble();
		}
		else {
			return ciudad.getValor(col, fila, parametro);
		}
	}

	@Override
	public String getUbicacion() {
		return "[" + col + ", " + fila + "]";
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
		fila = coordenadaY;
		col = coordenadaX;
	}

	@Override
	public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getParametro());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor());
        System.out.println("========================================");

	}
	
	public void setCiudad(ICuadriculaUrbanaSimple ciudad) {
		this.ciudad = ciudad;
	}

}
