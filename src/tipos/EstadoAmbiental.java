package tipos;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Clase que representa el estado ambiental de una celda urbana, compuesto por varios parámetros 
 * ambientales que se pueden añadir dinámicamente.
 * 
 * Los estados ambientales se pueden escalar y sumar entre sí, lo que permite modelar 
 * cómo cambian las condiciones ambientales en la ciudad a lo largo del tiempo o en respuesta 
 * a diferentes eventos.
 */
public class EstadoAmbiental {
	
	private HashMap<String, ParametroAmbiental> parametros;
	
	/**
	 * El constructor por defecto crea un estado ambiental vacío sin parámetros.
	 */
	public EstadoAmbiental() {
		this.parametros = new HashMap<>();
	}
	
	/**
	 * Crea un estado ambiental a partir de una lista de parámetros ambientales. 
	 * Cada parámetro se almacena en un HashMap, cuya clave es el nombre del parámetro.
	 * @param parametros
	 */
	 public EstadoAmbiental(ArrayList<ParametroAmbiental> parametros) {
		this.parametros = new HashMap<>();
		if (parametros != null) {
			for (ParametroAmbiental p : parametros) {
				this.parametros.put(p.getNombre(), p);
			}
		}
	}
	 
	/**
	 * Copia profunda de otro estado ambiental. 
	 * Se crea un nuevo HashMap y se copian los parámetros uno a uno.
	 * @param otro
	 */
	 public EstadoAmbiental(EstadoAmbiental otro) {
		 this.parametros = new HashMap<>();
		 for (String key : otro.parametros.keySet()) {
			 ParametroAmbiental p = otro.parametros.get(key);
			 this.parametros.put(key, new ParametroAmbiental(p.getNombre(), p.getUnits(), p.getValor()));
		 }
	 }
	
	
	/**
	 * 
	 * @param nombre
	 * @return El parámetro ambiental asociado al nombre proporcionado, o null si no existe.
	 */
	public ParametroAmbiental getParametro(String nombre) {
		return parametros.get(nombre);
	}
	
	/**
	 * Se añade al HashMap utilizando su nombre como clave.
	 * @param p El parámetro ambiental a agregar al estado ambiental. 
	 * @return Referencia a sí mismo (encadenamiento de operaciones).
	 */
	public EstadoAmbiental agregarParametro(ParametroAmbiental p) {
		parametros.put(p.getNombre(), p);
		return this;
	}
	
	/**
	 * 
	 * @return Un array de strings con los nombres de los parámetros ambientales presentes en el estado ambiental.
	 */
	public String[] getNombresParametros() {
		return parametros.keySet().toArray(new String[0]);
	}
	
	
	/**
	 * Modifica el valor del parámetro ambiental identificado por el nombre proporcionado.
	 * @param nombre
	 * @param valor
	 */
	public void setValorParametro(String nombre, double valor) {
		ParametroAmbiental p = parametros.get(nombre);
		if (p != null) {
			p.setValor(valor);
		}
	}
	
	public double getValorParametro(String nombre) {
		ParametroAmbiental p = parametros.get(nombre);
		if (p != null) {
			return p.getValor();
		}
		throw new IllegalArgumentException("Parámetro no encontrado: " + nombre);
	}
	
	/**
	 * escala el valor del parámetro identificado por el nombre escalado por 
	 * el factor proporcionado. 
	 * @param nombre
	 * @param factor
	 * @return Referencia a sí mismo (encadenamiento de operaciones).
	 */
	public EstadoAmbiental escalar(String nombre, double factor) {
		double valor = getParametro(nombre).getValor() * factor;
		getParametro(nombre).setValor(valor);
		return this;
	}
	
	/**
	 * Escala todos los parámetros por el factor proporcionado.
	 * @param factor
	 * @return Referencia a sí mismo.
	 */
	public EstadoAmbiental escalar(double factor) {
		for (String key : parametros.keySet()) {
			double valor = getParametro(key).getValor() * factor;
			getParametro(key).setValor(valor);
		}
		return this;
	}
	
	/**
	 * Actualiza el estado ambiental sumando los valores de los parámetros 
	 * del estado ambiental proporcionado. 
	 * Si un parámetro no existe en el estado ambiental actual, simplemente se agrega.
	 * @param otro
	 * @return Referencia a sí mismo.
	 */
	public EstadoAmbiental sumar(EstadoAmbiental otro) {
		for (String key : otro.parametros.keySet()) {
			if (!parametros.containsKey(key)) {
				parametros.put(key, new ParametroAmbiental(otro.parametros.get(key)));
			}
			else {
				double valor = getParametro(key).getValor() + otro.getParametro(key).getValor();
				getParametro(key).setValor(valor);
			}
		}
		return this;	
	}
	
	/**
	 * Devuelve una representación en forma de string del estado ambiental, 
	 * mostrando cada parámetro con su nombre, valor y unidades.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : parametros.keySet()) {
			ParametroAmbiental p = parametros.get(key);
			sb.append(p.getNombre()).append(": ").append(p.getValor()).append(" ").append(p.getUnits()).append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		//EstadoAmbiental e1 = new EstadoAmbiental(400, 50, 25);
		EstadoAmbiental e1 = new EstadoAmbiental();
		e1.agregarParametro(new ParametroAmbiental("CO2", "ppm", 400)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 50)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", 25));
		System.out.println("Estado Ambiental 1:\n" + e1);
		
		EstadoAmbiental e2 = new EstadoAmbiental();
		e2.agregarParametro(new ParametroAmbiental("CO2", "ppm", 200)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 30)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", 20));
		System.out.println("Estado Ambiental 2:\n" + e2);
		
		EstadoAmbiental e3 = e1.sumar(e2);
		System.out.println("Suma de E1 y E2:\n" + e3);
		
		EstadoAmbiental e4 = e1.escalar(0.5);
		System.out.println("E1 escalado a la mitad:\n" + e4);
		
		EstadoAmbiental e5 = e1.escalar("CO2", 2);
		System.out.println("E1 con CO2 duplicado:\n" + e5);
		
		EstadoAmbiental e6 = e1.escalar("CO2", 3);
		System.out.println("E1 con CO2 duplicado:\n" + e6);
		
		//EstadoAmbiental e10 = new EstadoAmbiental(-1200, -100, -100);
		EstadoAmbiental e10 = new EstadoAmbiental();
		e10.agregarParametro(new ParametroAmbiental("CO2", "ppm", -1200)).
		    agregarParametro(new ParametroAmbiental("ruido", "dB", -100)).
		    agregarParametro(new ParametroAmbiental("temperatura", "°C", -100));
		System.out.println("Estado Ambiental 10:\n" + e10);
		
		EstadoAmbiental e11 = e1.sumar(e10);
		System.out.println("Suma de E1 y E10:\n" + e11);

	}
}
