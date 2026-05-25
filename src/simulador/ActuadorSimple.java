package simulador;

import tipos.IActuador;

/**
 * 
 */
public class ActuadorSimple implements IActuador {
	
	public static int id = 1;

	private final int identificador;
	
	private String parametro;
	private String unidades;

	private int col;
	private int fila;
	
	public ActuadorSimple(int x, int y, String parametro, String unidades) {
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

	@Override
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
	public void actuar() {
		System.out.println("Actuador [" + identificador + "] actuando :p");
	}
	
	public String toString() {
		return "ActuadorSimple [id=" + id + ", parametro=" + 
	            parametro + ", unidades=" + unidades + 
				", col=" + col + ", fila=" + fila + "]";
	}

}
