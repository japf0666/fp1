package tipos;

import java.util.Random;
import java.util.UUID;

import simulador.Ciudad0;

/**
 *  Implementación de ISensorSimple que:
 * - Calcula internamente el identificar del Sensor (así lo sacamos del constructor).
 * - Aporta un constructor sin argumentos para un sensor por defecto que devuelve lecturas
 *   para un parámetro no determinado.
 * - Aporta un constructor con todos los parámetros ajustables.
 * - Aporta un constructor donde se fija el parámetro que mide el sensor.
 * - Aporta un constructor sin argumentos.
 * 
 * - Incluye un método no definido en las interfaces para fijar la ciudad de la que toma
 *   sus datos (se puedeconsiderar incluirlo en la interfaz).
 */
public class SensorSimple implements ISensorSimple {
	
	private static int id = 1;
	public final int identificador;
	
	protected String parametro = "no determinado";
	protected int col;
	protected int fila;
	
	// Definición más general de una cuadrícula urbana, no ligada a ninguna implementación.
	//protected ICuadriculaUrbanaSimple ciudad;
	Ciudad0 ciudad;
	
	private Random rnd = new Random();
	
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
		this(0, 0, "no determinado");
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
	public String getTipo() {
		return parametro;
	}
	

	@Override
	public double getValor() {
		if (ciudad == null) {
			double v = 10 * rnd.nextDouble();
			return v;
		}
		else {
			return ciudad.getValor(col, fila, parametro);
		}
	}

	
	public String getUbicacion() {
		return "[" + col + ", " + fila + "]";
	}


	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
		fila = coordenadaY;
		col = coordenadaX;
	}

	
	public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getTipo());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor());
        System.out.println("========================================");

	}
	
	public String toString() {
		return /*this.getClass().getSimpleName()  + */ " Sensor, id = " + this.getIdentificador() + ", param = " + this.getTipo() + ", at [" +  this.getCoordenadaX() + ", " + this.getCoordenadaY() + "]";
	}
	
	public void setCiudad(Ciudad0 ciudad) {
		this.ciudad = ciudad;
	}

}
