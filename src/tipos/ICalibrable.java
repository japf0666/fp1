package tipos;

/**
 * Define un método para ajustar sensores y actuadores y 
 * otro método para obtener el ajuste aplicado.
 */

public interface ICalibrable {
	
	/**
	 * Desplaza el cero de la medida segú el offset indicado
	 * @param offset
	 * @return código de éxito o error definido por la implementación.
	 */
	public int calibrar(double offset);
	
	/** 
	 * devuelve el ajuste aplicado
	 * @return offset
	 */
	public double getOffset();
}
