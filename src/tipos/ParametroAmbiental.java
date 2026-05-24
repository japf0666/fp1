package tipos;

/**
 * Clase que representa un parámetro ambiental específico, como CO2, ruido o temperatura.
 * Cada parámetro tiene un nombre, una unidad de medida y un valor numérico.
 */

public class ParametroAmbiental {
	
	private String nombre;
	private String units;
	private double valor;
	
	/**
	 * Crea un nuevo parámetro ambiental con el nombre, unidad y valor especificados.
	 * @param nombre
	 * @param units
	 * @param valor
	 */
	public ParametroAmbiental(String nombre, String units, double valor) {
		this.nombre = nombre;
		this.units = units;
		this.valor = valor;
	}
	
	/** Constructor de copia profunda
	 * @param otro El parámetro ambiental a copiar.
	 */
	public ParametroAmbiental(ParametroAmbiental otro) {
		this.nombre = otro.nombre;
		this.valor = otro.valor;
		this.units = otro.units;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getValor() {
		return valor;
	}
	
	public String getUnits() {
		return units;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return nombre + "[" + valor + " " + units + "]";
	}
	
	public static void main(String[] args) {
		ParametroAmbiental p1 = new ParametroAmbiental("CO2", "ppm", 400);
		System.out.println(p1);
		ParametroAmbiental p2 = new ParametroAmbiental(p1);
		p2.setValor(500);
		System.out.println(p1);
		System.out.println(p2);
	}

}
