package tipos;

import java.util.Date;

public class SensorAjustable extends SensorSimple implements ICalibrable, IRegulable {
	
	protected double offset;
	protected double minValue;
	protected double maxValue;
	
	public SensorAjustable(int col, int fila, String parametro) {
		super(col, fila, parametro);
	}
	
	public SensorAjustable(String parametro) {
		super(parametro);
	}
	
	public SensorAjustable() {
		super();
	}

	@Override
	public int calibrar(double offset) {
		this.offset = offset;
		return 0;
	}

	@Override
	public double getValorMaximo() {
		return maxValue;
	}

	@Override
	public double getValorMinimo() {
		return minValue;
	}
	
	@Override
	public double getValor() {
		double valor = super.getValor() + offset;
		valor = valor < maxValue? valor: maxValue;
		valor = valor > minValue? valor: minValue;
		return valor;
	}

	@Override
	public int setValorMinMax(double min, double max) {
		if (min < max) {
			minValue = min; maxValue = max;
			return 0;
		}
		return 1;
	}

}
