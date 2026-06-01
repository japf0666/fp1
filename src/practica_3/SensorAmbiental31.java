package practica_3;

import simulador.Ciudad0;
import tipos.ICuadriculaUrbanaSimple;
import tipos.ISensorSimple;

/**
 * Implementación de ISensor que:
 * - Calcula internamente el identificar del Sensor (lo sacamos del constructor).
 * - Aporta un constructor sin argumentos para un sensor por defecto de CO2 medido en ppm
 * - Aporta un constructor con todos los parámetros ajustables.
 * - Aporta un constructor donde se fija el parámetro que mide el sensor y sus unidades.
 * 
 * También proporciona una solución (no una buena solución) para evitar la creación de sensores
 * mal formados.
 */
public class SensorAmbiental31 implements ISensorSimple {
	
	public static int id = 1;

	private final int identificador;
	
	private String parametro;
	private String unidades;

	private int col;
	private int fila;
	
	private double valor; // valor actual de la medición
	

	Ciudad0 ciudad = null;

	
	
	// Mejor empezar por el constructor más completo y después reutilizarlo.
	public SensorAmbiental31(int x, int y, String parametro, String unidades) {
		
		// Evitamos la creación de un sensor absurdo, pero introducimos un problema
		// que puede ser mayor: abortamos el programa.
		if (parametro == null || unidades == null) {
			System.out.println("Parámetros null, abortamos programa");
			System.exit(1);
		}
		
		col = x;
		fila = y;
		this.parametro = parametro;
		this.unidades = unidades;
		
		this.identificador = id;
		id++;
	}
	
	// Constructor con tipo de parámetro y unidades.
	public SensorAmbiental31(String parametro, String unidades) {
		this(0, 0, parametro, unidades);
	}
	
	
	// Constructor sin argumentos.
	public SensorAmbiental31() {
		this(0, 0, "CO2", "ppm"); 
		
		// También valdría: this("CO2", "ppm");
	}
	
	
	
	@Override
	public int getIdentificador() {
		return identificador;
	}

	//@Override
	public String getDescripcion() {
		return toString();
	}

	@Override
	public int getCoordenadaX() {
		return col;
	}

	@Override
	public int getCoordenadaY() {
		return fila;
	}

	@Override
	public void setCoordenadaX(int col) {
		this.col = col;
	}

	@Override
	public void setCoordenadaY(int fila) {
		this.fila = fila;
	}

	@Override
	public String getTipo() {
		return parametro;
	}

	@Override
	public double getValor() {
		if (ciudad == null) {
			return valor;
		}
		else {
			return ciudad.getValor(getCoordenadaX(), getCoordenadaY(), parametro);
		}
	}
	
	public void setCiudad(Ciudad0 ciudad) {
		this.ciudad = ciudad;
	}

	//@Override
	public String getUnidades() {
		return unidades;
	}

	//@Override
	public String getUbicacion() {
		return "[" + col + ", " + fila + "]";
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
		col = coordenadaX;
		fila = coordenadaY;	
	}
	

	//@Override
	public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getTipo());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor() + " " + getUnidades());
        System.out.println("========================================");
	}
	
	@Override
    public String toString() {
        return "Sensor(" + getIdentificador() + ") de " + getTipo() + ", valor = " + 
                           getValor() + " " + getUnidades() + 
                           ", " + "ubicado en: " + getUbicacion();
    }
	
	
	private static void testConstructores() {

		System.out.println("Clase SensorAmbiental31 \n\n" +
				"EJECUTANDO TEST DE CONSTRUCTORES.\n");

		// Probamos la construcción del objeto con parámetros válidos y "lógicos":
		System.out.println("Creando sensor con parámetros 'coherentes': 10, 20, C02, ppm");
		SensorAmbiental31 s1 = new SensorAmbiental31(10, 20, "CO2", "ppm");

		// Llamamos implícitamente a toString que a su vez llama al resto de métodos.
		System.out.println(" Sensor creado ----> " + s1);
		System.out.println();

		// Llamamos a mostrar info que a su vez llama al resto de métodos.
		s1.mostrarInfo();

		System.out.println("------------------------------------------------------------");
		System.out.println("Creando sensor con parámetros 'coherentes': temperatura, ºC");
		SensorAmbiental31 s2 = new SensorAmbiental31("temperatura", "ºC");
		System.out.println(" Sensor creado ----> " + s2);
		System.out.println();
		s2.mostrarInfo();

		System.out.println("------------------------------------------------------------");
		System.out.println("Creando sensor con parámetros 'coherentes': CO2, ppm, constructor sin argumentos");
		SensorAmbiental31 s3 = new SensorAmbiental31();
		System.out.println(" Sensor creado ----> " + s3);
		System.out.println();
		s3.mostrarInfo();

		System.out.println("------------------------------------------------------------");
		System.out.println("Creando sensor con parámetros absurdos");
		SensorAmbiental31 s4 = new SensorAmbiental31(-45666, 238999, "verdín", "gallifantes");
		System.out.println(" Sensor creado ----> " + s4);
		System.out.println();
		s4.mostrarInfo();

		System.out.println("------------------------------------------------------------");
		System.out.println("Intentando crear sensor con parámetros no válidos (unidades = null");
		System.out.println("El programa abortará su ejecución.");
		SensorAmbiental31 s5 = new SensorAmbiental31("Jose Manuel", null);
		System.out.println(" Sensor creado ----> " + s5);
		System.out.println();
	}

	
	
	public static void main(String args []) {
		testConstructores();
	
	}	

}
