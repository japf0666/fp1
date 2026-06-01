package simulador;

import tipos.ISensor;

public class SensorSimpleOld implements ISensor {
	
	public static int id = 1;

	private final int identificador;
	
	private String parametro;
	private String unidades;

	private int col;
	private int fila;
	
	private volatile double valor; // valor actual de la medición
	
	
	public SensorSimpleOld(int x, int y, String parametro, String unidades) {
		col = x;
		fila = y;
		this.parametro = parametro;
		this.unidades = unidades;
		
		this.identificador = id;
		id++;
	}
	
	
	@Override
	public int getIdentificador() {
		return identificador;
	}

	//@Override
	public String getDescripcion() {
		return toString();
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
		return 0;
	}

	@Override
	public String getUnidades() {
		return unidades;
	}

	@Override
	public String getUbicacion() {
		return "Sensor at [" + col + ", " + fila + "]";
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
		col = coordenadaX;
		fila = coordenadaY;	
	}

	@Override
	public void mostrarInfo() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "SensorSimple [id=" + id + ", parametro=" + 
	            parametro + ", unidades=" + unidades + 
				", col=" + col + ", fila=" + fila + "]";
	}
}