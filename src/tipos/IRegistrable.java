package tipos;

import java.util.Date;

/**
 * Modela métodos para obtener las últimas medidas (directivas) tomadas (realizadas) 
 * por los sensores (actuadores)
 * 
 */
public interface IRegistrable {
	
	public LecturaSensor [] getLecturas(int nLecturas);	
	public double getMedia(int nLecturas);
	public double getVarianza(int nLecturas);
	public double getMaximo(int nLecturas);
	public double getMinimo(int nLecturas);

}
