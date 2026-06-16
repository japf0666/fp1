package tipos;

import java.util.Date;

/**
 * Modela métodos para obtener las últimas medidas (directivas) tomadas (realizadas) 
 * por los sensores (actuadores), así como estadísticos sobre los mismos.
 * 
 */
public interface IRegistrable {
	
	/**
	 * 
	 * @param nLecturas
	 * @return un array con las últimas nLecturas realizadas por el sensor o actuador. 
	 * Si no se han realizado nLecturas, se devolverán las realizadas hasta el momento.
	 */
	public LecturaSensor [] getLecturas(int nLecturas);	
	
	public int getCapacidad();
	
	public double getMedia(int nLecturas);
	
	public double getVarianza(int nLecturas);
	
	public double getMaximo(int nLecturas);
	
	public double getMinimo(int nLecturas);

}
