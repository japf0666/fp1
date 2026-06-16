package tipos;

/**
 * Define un método para ajustar los fondos de escala de sensores y actuadores.
 * es decir, valor mínimo y valor máximo, y dos métodos para obtenerlos
 */

public interface IRegulable {
	
	public double getValorMaximo();
	public double getValorMinimo();
	public int setValorMinMax(double min, double max);
}
