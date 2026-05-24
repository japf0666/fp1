package tipos;

/**
 * La interfaz ISensor representa un sensor ubicado en la ciudad que mide un 
 * parámetro específico (como calidad del aire, ruido o temperatura) y proporciona
 * información sobre su ubicación, valor actual y unidades de medida.
 * 
 * Esta interfaz extiende IUbicable, lo que significa que cada sensor tiene una 
 * ubicación específica en la cuadrícula urbana y puede ser identificado de forma
 * unívoca. 
 * 
 * Además, ISensor incluye métodos para obtener el tipo de parámetro que
 * mide el sensor, su valor actual y las unidades de medida correspondientes.
 * 
 * En principio se asume que:
 * - cada sensor sólo mide un parámetro en unas unidades específicas.
 * - el identificador del sensor, el parárámetro que mide y la unidad de medida son inmutables.
 * - el sensor obtiene su valor a través de la celda urbana en la que se encuentra ubicada,
 *   por ello no hay un método para establecer el valor del sensor.
 * - La ubicación del sensor puede cambiarse.
 * 
 * No obstante, la interfaz puede extenderse para incluir sensores que hagan más mediciones,
 * que puedan variar las unidades de medida, etc. o bien las implementaciones concretas de la interfaz 
 * pueden incluir esta funcionalidad adicional.
 */
public interface ISensor extends IUbicable {

    /**
	 * Devuelve el nombre del parámetro que lee el sensor
	 * @return parametro que mide el sensor (e.g., calidad del aire, ruido, temperatura)
	 */
    public String getParametro();

    /**
     * Valor actual del sensor, que puede variar dependiendo del tipo de sensor 
     * (e.g., nivel de contaminación para un sensor de calidad del aire, decibelios para un sensor de ruido, 
     * grados Celsius para un sensor de temperatura).
     * 
     * Se asume que el valor del sensor se obtiene a través de la celda urbana en la que se encuentra ubicado,
     * o bien  se genera según una lógica interna. No hay método setValor().
     * 
     * @return valor actual del sensor (e.g., nivel de contaminación, decibelios, grados Celsius)
     */
    public double getValor();
    
    /**
     * Unidades de medida del valor del sensor, que también varían según 
     * el tipo de sensor
     * @return
     */
    public String getUnidades();
    
    /**
	 * Devuelve la ubicación del sensor en formato de coordenadas (x, y).
	 * @return ubicación del sensor en formato de coordenadas (e.g., "[3,5]")
	 */
    public String getUbicacion();

    /**
     * Establece la ubicación del sensor en la ciudad utilizando coordenadas (x, y).
     * @param coordenadaX	columna en la que se encuentra el sensor
     * @param coordenadaY 	fila en la que se encuentra el sensor
     */
    public void setUbicacion(int coordenadaX, int coordenadaY);
    
    /**
     * Muestra toda la información relevante del sensor, incluyendo su identificador, tipo, ubicación y valor actual.
     */
    public void mostrarInfo();
}
