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
	
	public SensorConHistorial(int col, int fila, String parametro, int capacidad) {
		super(col, fila, parametro);
		historial = new HistorialLecturas(capacidad);
	}
	
	public SensorConHistorial(String parametro, int capacidad) {
		super(parametro);
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
		         col, fila, getParametro(), getValor(), new Date());
		
		// La agregamos al historial.
		historial.agregarLectura(lectura);		
		return v;
	}
	
	/**
	 * Almacena un número finito de lecturas en un buffer circular.
	 */
	private class HistorialLecturas {
		
		private LecturaSensor [] lecturas;
		
		int ultimoIndice = 0;
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
			int indice = (ultimoIndice + 1) % lecturas.length;
            lecturas[indice] = lectura;
            if (disponibles < lecturas.length) {
            	disponibles++;
            }
		}
		
		public LecturaSensor[] getLecturas(int nLecturas) {
			
			int offset = nLecturas <= 0 || nLecturas > disponibles? disponibles: nLecturas;
			int primerIndice = (ultimoIndice - offset + lecturas.length) % lecturas.length;
			
			LecturaSensor lecturas[] = new LecturaSensor[offset];
			
			for (int i = primerIndice; i < lecturas.length; i++) {
				lecturas[i] = this.lecturas[i];
			}
			return lecturas;
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

}
