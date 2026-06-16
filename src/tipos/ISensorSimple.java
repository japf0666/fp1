package tipos;

/**
 * La interfaz ISensor representa un sensor ubicado en la ciudad que mide un 
 * parámetro específico (como calidad del aire, ruido o temperatura) y proporciona
 * información sobre su ubicación, valor actual y unidades de medida.
 * 
 * El parámetro específico que mide el sensor se especifica con un atributo (en principio
 * inmutable) para evitar hacer una subclase por cada tipo de parámetro.
 * 
 * Esta interfaz modela al sensor más simple posible.
 * La implementaciones pueden extenderse y especializarse implementando otras interfaces especificas con 
 * funcionalidad adicional (calibración, valores de fondo, historial de lecturas, etc.)
 * aplicando el principio de segregación de interfaces.
 * 
 * Se asume que la ciudad está organizada según una cuadrícula urbana.
 * donde cada elemento puede ser colocado en una celda específica de la 
 * cuadrícula, definida por coordenadas (columna, fila) = (x, y).
 * 
 * También se asume que:
 * - cada sensor sólo mide un parámetro escalar en unas unidades específicas.
 * - el identificador del sensor y su parárámetro asociado son en principio inmutables.
 * - el sensor obtiene su valor a través de la celda urbana en la que se encuentra ubicado,
 *   por ello no hay un método para establecer el valor del sensor.
 * - La ubicación del sensor puede cambiarse.
 * 
 * No obstante, la interfaz puede extenderse para incluir nuevo comportamiento. Igualmente,
 * puden definirse nuevas interfaces que definan nuevo comportamiento aplicable a cierto
 * tipo de sensores.
 *
 */

public interface ISensorSimple extends IUbicable {
	
	
    /**
	 * Devuelve el nombre del parámetro que lee el sensor
	 * @return parametro que mide el sensor (e.g., calidad del aire, ruido, temperatura)
	 */
    public String getTipo();

    /**
     * Valor actual del sensor, que puede variar dependiendo del tipo de sensor 
     * (e.g., nivel de contaminación para un sensor de calidad del aire, decibelios para un sensor de ruido, 
     * grados Celsius para un sensor de temperatura).
     * 
     * Se asume que el valor del sensor se obtiene a través de la celda urbana en la que se encuentra ubicado,
     * o bien  se genera según una lógica interna.
     * 
     * No tiene sentido un método setValor() para poder establecerlo.
     * 
     * @return valor actual del sensor (e.g., nivel de contaminación, decibelios, grados Celsius)
     */
    public double getValor();
       
    /**
	 * Devuelve la ubicación del sensor en formato de coordenadas (x, y).
	 * @return ubicación del sensor en formato de coordenadas (e.g., "[3,5]")
	 */
    //public String getUbicacion();

    /**
     * Establece la ubicación del sensor en la ciudad utilizando coordenadas (x, y).
     * @param coordenadaX	columna en la que se encuentra el sensor
     * @param coordenadaY 	fila en la que se encuentra el sensor
     */
    //public void setUbicacion(int coordenadaX, int coordenadaY);
    
    /**
     * Muestra toda la información relevante del sensor, incluyendo su identificador, tipo, 
     * ubicación y valor actual.
     */
    //public void mostrarInfo();

}
