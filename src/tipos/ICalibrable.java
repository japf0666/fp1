package tipos;

/**
 * Define un método para ajustar sensores y actuadores.
 * 
 * Esta versión asume que aun no se ha impartido el tema de excepciones.
 */

public interface ICalibrable {
	
	/**
	 * Desplaza el cero de la medida segú el offset indicado
	 * @param offset
	 * @return código de éxito o error definido por la implementación.
	 */
	public int calibrar(double offset);
}
