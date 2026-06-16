package tipos;

/**
 * Se asume que la ciudad está organizada según una cuadrícula urbana.
 * donde cada elemento puede ser colocado en una celda específica de la 
 * cuadrícula, definida por coordenadas (columna, fila) = (x, y).
 * 
 * La interfaz IUbicable define los métodos necesarios para que un objeto 
 * pueda ser ubicado en la ciudad, identificado de forma unívoca y proporcione
 * una descripción de sí mismo.
 * 
 * Esta interfaz es fundamental para la gestión de la ciudad, ya que permite
 * colocar y localizar objetos como sensores, edificios, parques, etc., en 
 * la cuadrícula urbana y gestionarlos a estos efectos de forma regular.
 */

public interface IUbicable {
	
    /**
     * devuelve el identificador único del objeto ubicable
     * @return identificador
     */
	int getIdentificador();
	
	/**
	 * devuelve la coordenada x (columna de la cuadricula)
	 * @return coordenadaX
	 */
	int getCoordenadaX(); 
	
	/** 
	 * devuelve la coordenada y (fila de la cuadricula)
	 * @return coordenadaY
	 */
	int getCoordenadaY();  
	
	/**
	 * permite actualizar la coordenada X
	 * @param cooordenadaX
	 */
	void setCoordenadaX(int col);
	
	/**
	 * permite actualizar la coordenada Y
	 * @param coordenadaY
	 */
	void setCoordenadaY(int fila);
	
	/**
	 * permite establecer nueva ubicación
	 * @param coordenadaX
	 * @param coordenadaY
	 */	
	public void setUbicacion(int coordenadaX, int coordenadaY);
}