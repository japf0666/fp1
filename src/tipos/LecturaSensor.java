package tipos;

import java.util.Date;
import java.util.Random;

/**
 * Modela la captura de un dato de la forma más completa posible.
 * Ubicación, sensor que ha tomado la medida, marca temporal de la medida ...
 */
public class LecturaSensor {
	
	private double value;
	private int x;
	private int y;
	private Date time;
	private int sensorId;
	private String parameter;
	
	public LecturaSensor(int x, int y, String param, double valor, Date time) {
		this.x = x;
		this.y = y;
		this.parameter = param;
		this.value = valor;
		this.time = time;
	}
	
	/**
	 * Método estático para generar lecturas aleatorias (para tests).
	 * @param numLecturas número de lecturas a generar
	 * @param parametro nombre del parámetro medido (ej. CO2, temperatura, ruido...)
	 * @param media valor medio de las lecturas a generar
	 * @param varianza varianza de las lecturas a generar (si es menor que media/2, se usará media/10)
	 * @return un array de lecturas generadas aleatoriamente con los parámetros indicados
	 */
	public static LecturaSensor[] generarLecturas(
			int numLecturas,
			String parametro,
			double media,
			double varianza
			) {
		
		int nLecturas = numLecturas > 0 ? numLecturas: 10;
		LecturaSensor[] lecturas = new LecturaSensor[nLecturas];
		
		double var = varianza < (media/2.0) && varianza > 0.01  ? varianza : media/10;
		double desviacionTipica = Math.sqrt(var);
		long tiempoInicial = System.currentTimeMillis();
		
		Random rnd = new Random();
		
		for (int i = 0; i < nLecturas; i++) {
			double valor = media + desviacionTipica * rnd.nextGaussian();
			LecturaSensor lectura = new LecturaSensor(0, 0, parametro, valor, new Date(tiempoInicial + i*1000) );
			lectura.setSensorId(i);
			lecturas[i] = lectura;
		}
		
		return lecturas;
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
	
	@Override
	public String toString() {
		return "lectura  ---> sensor: " + sensorId + " at[" + x + ", " + y +"], value = " + value + ", time: " + time;
	}
	
	public static void main(String [] args) {
		
		LecturaSensor [] lecturas = generarLecturas(10, "CO2", 500, 50);
		
		for (int i = 0; i < lecturas.length; i++) {
			System.out.println(lecturas[i]);
		}
		
	}


}
