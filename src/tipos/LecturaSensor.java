package tipos;

import java.util.Date;

/**
 * Modela la captura de un dato de la forma más completa posible.
 * Ubicación, sensor que ha tomado la medida, marca temporal de la medida ...
 */
public class LecturaSensor {
	
	double value;
	int x;
	int y;
	Date time;
	int sensorId;
	String parameter;
	
	public LecturaSensor(int x, int y, String param, double valor, Date time) {
		this.x = x;
		this.y = y;
		this.parameter = param;
		this.value = valor;
		this.time = time;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}


}
