package tipos;

/**
 * Modela un actuador
 */
public interface IActuador extends IUbicable{

	void actuar(double consigna);
	double getConsignaActual();

}
