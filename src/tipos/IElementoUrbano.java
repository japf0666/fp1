package tipos;

/**
 * Interfaz que representa un elemento urbano que puede ubicarse en una celda urbana. 
 * Un elemento urbano tiene un efecto ambiental asociado, que puede influir en el estado 
 * ambiental de la celda donde se ubica.
 * 
 * Los elementos urbanos pueden ser de diferentes tipos, como edificios, parques, 
 * carreteras, etc., cada uno con su propio efecto ambiental.
 * 
 * Los elementos úrbanos se definen para poder modelar cómo diferentes tipos de 
 * infraestructura y uso del suelo que afectan el ambiente urbano y que pueden cambiarse
 * a lo largo del tiempo, por ejemplo, a través de la construcción o demolición de edificios.
 */

public interface IElementoUrbano extends IUbicable {

	// Efectos
	EstadoAmbiental getEfectoAmbiental();
}
