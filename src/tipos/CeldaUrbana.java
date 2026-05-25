package tipos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que representa una celda urbana en la cuadrícula urbana. 
 * 
 * Cada celda tiene:
 * .- coordenadas (columna, fila) que determinan su posición en el entorno urbano.
 * .- un estado ambiental
 * .- opcionalmente, un elemento urbano que puede influir sobre su estado ambiental.
 * .- opcionalmente, una lista de sensores ubicados en ella que pueden "medir" su estado ambiental.
 * .- opcionalmante, una lista de actuadores que pueden modificar su estado ambiental.
 * 
 * El estado ambiental de la celda se puede modificar por otro objeto invocando: 
 * .- setEfectoAmbiental establece el estado ambiental de la celda a un nuevo valor.
 * .- sumarEfectoAmbiental incrementa el estado ambiental actual de la celda otro efecto ambiental, 
 *    sumando los valores de cada parámetro ambiental.
 */

public class CeldaUrbana  {
	
	EstadoAmbiental estadoAmbiental = new EstadoAmbiental(); // Estado ambiental inicial vacío sin parámetros.
	int col, fila;
	ArrayList<ISensor> sensores = new ArrayList<>();
	ArrayList<IActuador> actuadores = new ArrayList<>();
	IElementoUrbano elemento;
	
	// límites superiores para los parámetros ambientales, por ejemplo, el límite de CO2 en ppm.
	HashMap<String, Double> limites = new HashMap<>();
	
	/**
	 * Crea una celda urbana con coordenadas (col, fila) y un estado ambiental inicial vacío sin parámetros.
	 * @param col	
	 * @param fila
	 */
	public CeldaUrbana(int col, int fila) {
		this.col = col;
		this.fila = fila;
	}
	
	/**
	 * Crea una celda urbana con coordenadas (col, fila) y un estado ambiental inicial 
	 * @param col	
	 * @param fila
	 */
	public CeldaUrbana(int col, int fila, EstadoAmbiental estado) {
		this.col = col;
		this.fila = fila;
		
		// Crear una copia profunda del estado ambiental.
		this.estadoAmbiental = new EstadoAmbiental(estado); 
		this.sensores = new ArrayList<>();
		this.actuadores = new ArrayList<>();
		this.elemento = null;
	}
	
	/**
	 * Constructor de copia
	 * @param parametro
	 * @param valor
	 */
	public CeldaUrbana(CeldaUrbana celda) {
		
		if (celda == null) {
			throw new NullPointerException();
		}
		
		this.col = celda.col;
		this.fila = celda.fila;
		this.estadoAmbiental = new EstadoAmbiental(celda.getEstadoAmbiental());
	}
	
	public void setLimite(String parametro, double valor) {
		limites.put(parametro, valor);
	}
	
	
	public EstadoAmbiental getEstadoAmbiental() {
		return estadoAmbiental;
	}
	
	/**
	 * Actualiza el estado ambiental de la celda aplicando las siguientes reglas:
	 * - Si algún parámetro ambiental tiene un valor negativo, se ajusta a 0.
	 * - Si algún parámetro ambiental tiene un límite superior se acota a ese valor .
	 * 
	 * @param efectoAmbiental El nuevo estado ambiental de la celda.
	 */
	public CeldaUrbana setEstadoAmbiental(EstadoAmbiental efecto) {
		
		// Crear una copia profunda del estado ambiental.
		this.estadoAmbiental = new EstadoAmbiental(efecto); 
		
		// Aplicar las reglas de ajuste a los parámetros ambientales.
		for (String key : estadoAmbiental.getNombresParametros()) {
			ParametroAmbiental p = estadoAmbiental.getParametro(key);
			
			// Ajustar a 0 si el valor es negativo.
			if (p.getValor() < 0) {
				p.setValor(0);
			}
			
			// Acotar al límite superior si existe.
			if (limites.containsKey(key) && p.getValor() > limites.get(key)) {
				p.setValor(limites.get(key));
			}
		}
		return this;
	}
	
	/**
	 * Fija el valor de un parámetro ambiental específico en el estado ambiental de la celda, 
	 * aplicando las mismas reglas de ajuste que el método setEstadoAmbiental(EstadoAmbiental).
	 * 
	 * Si el parámetro no existe en el estado ambiental de la celda, no produce ningún cambio.
	 * @param nombre
	 * @param valor
	 */
	public CeldaUrbana setParametroAmbiental(String nombre, double valor) {
		ParametroAmbiental p = estadoAmbiental.getParametro(nombre);
		if (p != null) {
			
			// Ajustar a 0 si el valor es negativo.
			double v = valor >= 0 ? valor : 0; 
			// Acotar al límite superior si existe.
			valor = limites.containsKey(nombre) && v > limites.get(nombre) ? limites.get(nombre) : v; 
			p.setValor(valor);
		}
		return this;
	}
	
	public CeldaUrbana sumarEfectoAmbiental(EstadoAmbiental efecto) {
		estadoAmbiental.sumar(efecto);
		return this;
	}

	public IElementoUrbano getElemento() {
		return elemento;
	}
	
	public void setElemento(IElementoUrbano elemento) {
		this.elemento = elemento;
		this.setEstadoAmbiental(elemento.getEfectoAmbiental());
	}
	
	public ArrayList<ISensor> getSensores() {
        return sensores; //getByType(ISensorSimulado.class);
    }
	
	public boolean contieneSensor(ISensor sensor) {
		if (sensor == null) {
			return false;
		}
		for (ISensor s: sensores) {
			if (s.getIdentificador() == sensor.getIdentificador()) {
				return true;
			}
		}
		return false;
	}
	
	public void addSensor(ISensor sensor) {
		//System.out.println("add sensor 1");
		if (sensor != null && !contieneSensor(sensor)) {
			//System.out.println("add sensor 2");
			this.sensores.add(sensor);
		}
	}
	
	public void removeSensor(ISensor sensor) {
		if (contieneSensor(sensor)) {
			this.sensores.remove(sensor);
		}
	}
    
    public ArrayList<IActuador> getActuadores() {
        return actuadores; //getByType(IActuador.class);
    }

	public boolean contieneActuador(IActuador actuador) {
		if (actuador == null) {
			return false;
		}
		/*
		for (IActuador a: actuadores) {
			if (a.getIdentificador() == actuador.getIdentificador()) {
				return true;
			}
		}
		*/
		return false;
	}
	
	public void addActuador(IActuador actuador) {
		if (actuador != null && !contieneActuador(actuador)) {
			System.out.println("celda, addactuador. " + actuador);
			this.actuadores.add(actuador);
		}
	}
	
	public void removeActuador(IActuador actuador) {
		if (contieneActuador(actuador)) {
			this.actuadores.remove(actuador);
		}
	}
    

	public void setColumna(int c) {
		this.col = c;
	}

	public void setFila(int f) {
		this.fila = f;
	}

	public int getColumna() {
		return col;
	}

	public int getFila() {
		return fila;
	}
	
	@Override
	public String toString() {
		
		String s1 = "Celda at (col, fila): " + col + ", " + fila + "\n";
		
		String s2 = "Sensores ---> \n";
		for (ISensor s : sensores) {
			s2 += s.toString();
			s2 += "\n";
		}
		
		String s3 = "Actuadores ---> \n";
		for (IActuador s : actuadores) {
			s3 += s.toString();
			s3 += "\n";
		}
		return s1 + "\n" + s2 + "\n" + s3 + "\n-----------------------------\n";
	}
	
	public static void main(String[] args) {
		
		// Creamos una celada urbana con coordenadas (0, 0) y 
		// estado ambiental inicial vacío sin parámetros.
		CeldaUrbana celda = new CeldaUrbana(0, 0);
		System.out.println("Efecto ambiental inicial: " + celda.getEstadoAmbiental().getParametro("CO2"));
		
		// Creamos dos estados ambientales con varios parámetros.
		EstadoAmbiental e1 = new EstadoAmbiental();
		e1.agregarParametro(new ParametroAmbiental("CO2", "ppm", 400)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 50)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", 25));
		System.out.println("Estado Ambiental 1:\n" + e1);
		
		// Establecemos un límite superior para el parámetro CO2.
		System.out.println("Establecemos un límite superior de 1000 ppm para el parámetro CO2...");
		celda.setLimite("CO2", 1000); 
		
		// Establecemos el estado ambiental de la celda al estado ambiental e1
		celda.setEstadoAmbiental(e1);
		System.out.println("Estado ambiental de la celda después de aplicar e1:\n" + celda.getEstadoAmbiental());
		
		EstadoAmbiental e2 = new EstadoAmbiental();
		e2.agregarParametro(new ParametroAmbiental("CO2", "ppm", 700)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 30)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", 20));
		System.out.println("Estado Ambiental 2:\n" + e2);
		System.out.println("sumamos el efecto ambiental e2 al estado ambiental de la celda...");
		
		celda.sumarEfectoAmbiental(e2);
		System.out.println("Estado ambiental de la celda después de sumar e2:\n" + celda.getEstadoAmbiental());
		
		// creamos nuevo efecto ambiental con valores negativos para el ppm y la temperatura.
		EstadoAmbiental e3 = new EstadoAmbiental();
		e3.agregarParametro(new ParametroAmbiental("CO2", "ppm", -1100)).
		   agregarParametro(new ParametroAmbiental("ruido", "dB", 30)).
		   agregarParametro(new ParametroAmbiental("temperatura", "°C", -40));
		System.out.println("Estado Ambiental 3:\n" + e3);
		System.out.println("sumamos el efecto ambiental e3 al estado ambiental de la celda...");
		celda.sumarEfectoAmbiental(e3);
		System.out.println("Estado ambiental de la celda después de sumar e3:\n" + celda.getEstadoAmbiental());		
		
	}
	
}