package tipos;

import java.util.Date;

/**
 * Implementa interfaces ICalibrable, IRegulable e IRegistrable y reutiliza las 
 * implementación de SensorAjustable, añadiendo los métodos de IRegistrable.
 * 
 * La implementación utiliza una clase interna para gestionar el historial de
 * lecturas.
 */
public class SensorConHistorial extends SensorAjustable implements IRegistrable {
	
	private HistorialLecturas historial;
	private int capacidad = 10;
	
	
	// Constructores.
	public SensorConHistorial(int col, int fila, String parametro, int capacidad) {
		super(col, fila, parametro);
		this.capacidad = capacidad;
		historial = new HistorialLecturas(capacidad);
	}
	
	public SensorConHistorial(String parametro, int capacidad) {
		super(parametro);
		this.capacidad = capacidad;
		historial = new HistorialLecturas(capacidad);
	}
	
	public SensorConHistorial(String parametro) {
		super(parametro);
		historial = new HistorialLecturas();
	}
	
	public SensorConHistorial() {
		super();
		historial = new HistorialLecturas();
	}
	
	@Override
	public LecturaSensor[] getLecturas(int nLecturas) {
		return historial.getLecturas(nLecturas);
	}

	@Override
	public double getMedia(int nLecturas) {
		return historial.getMedia(nLecturas);
	}

	@Override
	public double getVarianza(int nLecturas) {
		return historial.getVarianza(nLecturas);
	}

	@Override
	public double getMaximo(int nLecturas) {
		return historial.getMaximo(nLecturas);
	}

	@Override
	public double getMinimo(int nLecturas) {
		return historial.getMinimo(nLecturas);
	}
	
	@Override
	public double getValor() {
		
		// Obtenemos valor.
		double v = super.getValor();

		// Creamos lectura.
		LecturaSensor lectura = new LecturaSensor(
		         col, fila, getParametro(), v, new Date());
		
		// La agregamos al historial.
		historial.agregarLectura(lectura);		
		return v;
	}
	
	/**
	 * Almacena un número finito de lecturas en un buffer circular.
	 */
	private class HistorialLecturas {
		
		private LecturaSensor [] lecturas;
		
		int indice = 0;
		int disponibles = 0;
		
		public HistorialLecturas(int capacidad) {
			int tamanio = capacidad >= 1? capacidad:100;
			lecturas = new LecturaSensor[capacidad];
		}
		
		public HistorialLecturas() {
			lecturas = new LecturaSensor[100];
		}
		
		public void agregarLectura(LecturaSensor lectura) {
			if (lectura == null) {
				return;
			}
			lecturas[indice] = lectura;
			
			System.out.println("Agregando lectura en [" + indice + "] ---> " + lectura);
			
			indice = (indice + 1) % lecturas.length;
            
            if (disponibles < lecturas.length) {
            	disponibles++;
            }
		}
		
		public LecturaSensor[] getLecturas(int nLecturas) {
			
			int offset = (nLecturas <= 0 || nLecturas > disponibles)? disponibles: nLecturas;
			int primerIndice = (indice - offset + lecturas.length) % lecturas.length;
			
			//System.out.println("nLecturas = " + nLecturas + ", offset = " + offset + ", " + "primer indice = " + primerIndice);
			
			LecturaSensor resultado[] = new LecturaSensor[offset];
			
			for (int i = 0; i < resultado.length; i++) {
				if (lecturas[(primerIndice + i) % lecturas.length] != null) {
					resultado[i] = lecturas[(primerIndice + i) % lecturas.length];
				}
			}
			return resultado;
		}
		
		public double getMedia(int nLecturas) {
			LecturaSensor lecturas[] = getLecturas(nLecturas);
			double media = 0;
			for (int i = 0; i < lecturas.length; i++) {
				media += lecturas[i].getValue();
			}
			return media/lecturas.length;
		}

		public double getVarianza(int nLecturas) {
			LecturaSensor lecturas[] = getLecturas(nLecturas);
			double media = getMedia(nLecturas);
			double varianza = 0;
			for (int i = 0; i < lecturas.length; i++) {
				varianza += Math.pow(media - lecturas[i].getValue(), 2);
			}
			return varianza/lecturas.length;
		}

		public double getMaximo(int nLecturas) {
			LecturaSensor lecturas[] = getLecturas(nLecturas);
			double max = lecturas[0].getValue();
			for (int i = 0; i < lecturas.length; i++) {
				if (lecturas[i].getValue() > max) {
					max = lecturas[i].getValue();
				}
			}
			return max;
		}

		public double getMinimo(int nLecturas) {
			LecturaSensor lecturas[] = getLecturas(nLecturas);
			double min = lecturas[0].getValue();
			for (int i = 0; i < lecturas.length; i++) {
				if (lecturas[i].getValue() < min) {
					min = lecturas[i].getValue();
				}
			}
			return min;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + ", capacidad = " + this.capacidad; 
	}
	
	
	public static void main(String [] args) {
		SensorConHistorial sensor = new SensorConHistorial(7, 10, "CO2", 6);
		sensor.calibrar(111);
		sensor.setValorMinMax(0, 1200);
			
		
		for (int i = 0; i < 10; i++) {
			double v = sensor.getValor();
			// System.out.println(v);
		}
		
		
		for (int i = 1; i < 10; i++) {
			System.out.println("\nObteniendo " + i + " últimas lecturas");
			LecturaSensor[] lecturas = sensor.getLecturas(i);
			for (int j = 0; (j < i && j < lecturas.length); j++) {
				System.out.println(lecturas[j]);
			}
			System.out.println("--------------------------------------------------------------------");
		}
		
		
		for (int i = 1; i < 10; i++) {
			System.out.println("Obteniendo media y varianza de las " + i + " últimas lecturas");
			System.out.println("media --> " + sensor.getMedia(i));
			System.out.println("varianza --> " + sensor.getVarianza(i));			
			System.out.println("--------------------------------------------------------------------");
		}
		
		
	}
	

}
