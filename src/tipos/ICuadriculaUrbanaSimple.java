package tipos;

/**
 * Interfaz que modela una cuadrícula urbana compuesta por celdas urbanas. 
 * 
 * La cuadrícula urbana es el entorno en el que se desarrollan las actividades urbanas y 
 * donde se ubican los elementos urbanos, sensores y actuadores. 
 * 
 * Proporciona métodos para acceder indirectamnte a las celdas urbanas, 
 * modificar su estado ambiental y gestionar los elementos urbanos, 
 * sensores y actuadores ubicados en ellas.
 * 
 * Nota Docente: Esta interfaz esta pensada para prácticas en las que los estudiantes
 * aún no han llegado al tema de excepciones. Los métodos tienen un comportamiento por
 * defecto si los parámetros no son válidos o bien devuelven un código de error.
 */
public interface ICuadriculaUrbanaSimple {
	
	/**
	 * 
	 * @return El número de columnas de la cuadrícula urbana.
	 */
	public int getNumColumnas();
	
	/**
	 * 
	 * @return El número de filas de la cuadrícula urbana.
	 */
	public int getNumFilas();
	
	/**
	 * Devuelve una referencia a la celda urbana ubicada en la fila y columna especificadas.
	 * @param col
	 * @param fila
	 * @return celda urbana ubicada en la fila y columna especificadas.
	 */
	public CeldaUrbana getCeldaUrbana(int col, int fila);
	
	/**
	 * Devuelve el estado ambiental completo de la celda.
	 * @param col
	 * @param fila
	 * @return estado ambiental completo de la celda
	 */
	public EstadoAmbiental getEstadoAmbiental(int col, int fila);
	
	/**
	 * Devuelve el valor asociado a un parámetro. 
	 * @param col
	 * @param fila
	 * @param Param
	 * @return valor del parámetro o NaN si el parámetro no está definido en la celda o
	 * si fila/columna están fuera de rango.
	 */
	public double getValor(int col, int fila, String Param);
	
	/**
	 * Fija el estado ambiental de la celda ubicada en la fila y columna especificadas
	 * @param col
	 * @param fila
	 * @param efecto
	 * @return código de éxito o de error
	 */
	public int setEstadoAmbiental(int col, int fila, EstadoAmbiental efecto);
	
	/**
	 * Añade un efecto ambiental a la celda ubicada en la fila y columna especificadas
	 * @param col
	 * @param fila
	 * @param efecto
	 * @return código de éxito o de error
	 */
	public int addEstadoAmbiental(int col, int fila, EstadoAmbiental efecto);
	
	/**
	 * Fija el valor del parámetro ambiental específicado de la celda ubicada en la fila y columna
	 * especificadas
	 * @param col
	 * @param fila
	 * @param nombre nombre del parámetro.
	 * @param valor
	 * @return
	 */
	public int setParametroAmbiental(int col, int fila, String nombre, double valor);
	
	
	/**
	 * Añade un elemento urbano a la celda urbana especificada por sus coordenadas (col, fila).
	 * Modifica las coordenadas del elemento urbano para que coincidan con las coordenadas 
	 * de la celda urbana.
	 * @param col
	 * @param fila
	 * @param elemento
	 * @return codigo de éxito o de error definido por la implementación.
	 */
	public int agregarElementoUrbano(int col, int fila, IElementoUrbano elemento);
	
	/**
	 * Añade un sensor a la celda urbana especificada por sus coordenadas (col, fila).
	 * Modifica las coordenadas del sensor para que coincidan con las coordenadas de la celda urbana.
	 * @param col
	 * @param fila
	 * @param sensor
	 * @return codigo de éxito o de error definido por la implementación.
	 */
	public int agregarSensor(int col, int fila, ISensor sensor);
	
	/**
	 * Añade un actuador a la celda urbana especificada por sus coordenadas (col, fila).
	 * Modifica las coordenadas del actuador para que coincidan con las coordenadas de la celda urbana.
	 * @param col
	 * @param fila
	 * @param actuador
	 * @return codigo de éxito o de error definido por la implementación.
	 */
	public int agregarActuador(int col, int fila, IActuador actuador);
	
    /**
     * Elimina el elemento urbano ubicado en la celda urbana especificada por sus coordenadas (col, fila).
     * Si la celda está vacía (no contiene ningún elemento urbano), no se realiza ninguna acción.
     * @param col
     * @param fila
     * @return elemento eliminado o null si no se ha quitado ningún elmento.
     */
	public IElementoUrbano quitarElementoUrbano(int col, int fila);
	
	/**
	 * Elimina el sensor especificado ubicado en la celda urbana especificada por sus coordenadas (col, fila).
	 * Si la celda no contiene el sensor especificado, no se realiza ninguna acción.
	 * @param col
	 * @param fila
	 * @param sensor
	 * @return sensor eliminado o null si no se ha quitado ningún elmento.
	 */
	public ISensor quitarSensor(int col, int fila, ISensor sensor);
	
	/**
	 * Elimina el actuador especificado ubicado en la celda urbana especificada por sus coordenadas (col, fila).
	 * Si la celda no contiene el actuador especificado, no se realiza ninguna acción.
	 * @param col
	 * @param fila
	 * @param actuador
	 * @return actuador eliminado o null si no se ha quitado ningún elmento.
	 */
	public IActuador quitarActuador(int col, int fila, IActuador actuador);
	
	/**
	 * Desplaza el elemento urbano ubicado en la celda urbana de origen especificada por sus coordenadas
	 * a la celda especificada como destino.
	 * Debe mantener la consistencia de coordenadas (coordenadas elmento = coordenadas celda).
	 * @param colOrigen
	 * @param filaOrigen
	 * @param colDestino
	 * @param filaDestino
	 * @return código de éxito o de error
	 */
	public int desplazarElementoUrbano(int colOrigen, int filaOrigen, 
			                            int colDestino, int filaDestino);
	
	/**
	 * Desplaza el sensor ubicado en la celda urbana de origen especificada por sus coordenadas
	 * a la celda especificada como destino.
	 * Debe mantener la consistencia de coordenadas (coordenadas sensor = coordenadas celda).
	 * @param colOrigen
	 * @param filaOrigen
	 * @param colDestino
	 * @param filaDestino
	 * @param sensor
	 * @return código de éxito o de error
	 */
	public int desplazarSensor(int colOrigen, int filaOrigen, int colDestino, int filaDestino, ISensor sensor);
	
	/**
	 * Desplaza el actuador ubicado en la celda urbana de origen especificada por sus coordenadas
	 * a la celda especificada como destino.
	 * Debe mantener la consistencia de coordenadas (coordenadas actuador = coordenadas celda).
	 * @param colOrigen
	 * @param filaOrigen
	 * @param colDestino
	 * @param filaDestino
	 * @param sensor
	 * @return código de éxito o de error
	 */	
	public int desplazarActuador(int colOrigen, int filaOrigen, int colDestino, int filaDestino, IActuador actuador);
	
	/**
	 * Devuelve el elemento urbano contenido en la celda especificada por sus coordenadas
	 * @param col
	 * @param fila
	 * @return elemento contenido en la celda.
	 */
	public IElementoUrbano getElementoUrbano(int col, int fila);
	
	/**
	 * Devuelve la lista de sensores contenidos en la celda especificada por sus coordenadas
	 * @param col
	 * @param fila
	 * @return lista de sensores contenidos en la celda.
	 */
	public ISensor[] getSensores(int col, int fila);
	
	/**
	 * Devuelve la lista de actuadores contenidos en la celda especificada por sus coordenadas
	 * @param col
	 * @param fila
	 * @return lista de actuadores contenidos en la celda.
	 */	
	public IActuador[] getActuadores(int col, int fila);
	
}
