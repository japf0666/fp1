package tipos;

/**
 * Interfaz que modela un elemento urbano que puede ubicarse en una celda urbana. 
 * Un elemento urbano tiene un efecto ambiental asociado, que puede influir en el estado 
 * ambiental de la celda donde se ubica.
 * 
 * Los elementos urbanos pueden ser de diferentes tipos, como edificios, parques, 
 * carreteras, etc., cada uno con su propio efecto ambiental.
 * 
 * Los elementos úrbanos se definen para poder modelar la forma en que diferentes tipos de 
 * infraestructura (o usos del suelo) afectan al estado ambiental de la celda que
 * los contiene.
 * 
 */

public interface IElementoUrbano extends IUbicable {

	// Efectos
	EstadoAmbiental getEfectoAmbiental();
}
