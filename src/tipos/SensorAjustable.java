package tipos;

import java.util.Date;

public class SensorAjustable extends SensorSimple implements ICalibrable, IRegulable {
	
	protected double offset;
	protected double minValue = 0;
	protected double maxValue = 100;
	
	public SensorAjustable(int col, int fila, String parametro) {
		super(col, fila, parametro);
	}
	
	public SensorAjustable(String parametro) {
		super(parametro);
	}
	
	public SensorAjustable() {
		super();
	}

	// Métodos de ICalibrable.
	@Override
	public int calibrar(double offset) {
		this.offset = offset;
		return 0;
	}
	
	@Override
	public double getOffset() {
		return offset;
	}


	// Métodos de IRegulable.
	@Override
	public double getValorMaximo() {
		return maxValue;
	}

	@Override
	public double getValorMinimo() {
		return minValue;
	}
	
	@Override
	public int setValorMinMax(double min, double max) {
		if (min < max) {
			minValue = min; maxValue = max;
			return 0;
		}
		return 1;
	}
	
	@Override
	public double getValor() {
		double valor = super.getValor() + offset;
		valor = valor < maxValue? valor: maxValue;
		valor = valor > minValue? valor: minValue;
		return valor;
	}

	@Override 
	public String toString() {
		return super.toString() + ", offset = " + this.getOffset() + ", [Min, Max] = [" + this.getValorMinimo() + ", " + this.getValorMaximo() + "]";
	}

}
