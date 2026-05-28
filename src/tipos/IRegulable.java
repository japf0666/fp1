package tipos;

/**
 * Define un método para ajustar los fondos de escala de sensores y actuadores.
 * 
 * Esta versión asume que aun no se ha impartido el tema de excepciones.
 */

public interface IRegulable {
	
	public double getValorMaximo();
	public double getValorMinimo();
	public int setValorMinMax(double min, double max);
}
