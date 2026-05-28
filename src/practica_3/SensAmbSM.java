package practica_3;

import tipos.ISensor;

import java.util.Scanner;

import simulador.Ciudad0;

/**
 * Implementación de ISensor que toma los valores de sus parámetros inmutables 
 * en el constructor. 
 * 
 * Añadimos un método main para probar sus métodos.
 * Una clase no está terminada hasta que no se ha probado razonablemente su funcionamiento.
 */
public class SensAmbSM implements ISensor {
	
	// Debería ser inmutable.
    private int identificador;
    
    // CO2, ruido, humedad, temperatura, ...
    // Podría ser inmutable.
    private String parametro;
    
    // Unidad de medida escogida (ppm, C, m/s ...)
    private String unidades;
    
    // celda en la que se ubica.
    private int coordenadaX, coordenadaY;
    
    // valor. se asumen parámetros escalalares.
    private double valor;
    
    // Ciudad en la que se va a agregar
    Ciudad0 ciudad = null;
    
    public SensAmbSM(int id, String parametro, String unidades) {
    	identificador = id;
    	this.parametro = parametro;
    	this.unidades = unidades;
    }

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public String getDescripcion() {
		return toString();
	}

	@Override
	public int getCoordenadaX() {
		return coordenadaX;
	}

	@Override
	public int getCoordenadaY() {
		return coordenadaY;
	}

	@Override
	public void setCoordenadaX(int col) {
		coordenadaX = col;
	}

	@Override
	public void setCoordenadaY(int fila) {
		coordenadaY = fila;
	}

	@Override
	public String getParametro() {
		return parametro;
	}

	@Override
	public double getValor() {
		if (ciudad == null) {
			return valor;
		}
		else {
			return ciudad.getValor(coordenadaX, coordenadaY, parametro);
		}
	}
	
	public void setCiudad(Ciudad0 ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String getUnidades() {
		return unidades;
	}

	@Override
	public String getUbicacion() {
        return  "[" + coordenadaX + "," + coordenadaY + "]";
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
	}

	@Override
	public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getParametro());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor() + " " + getUnidades());
        System.out.println("========================================");
	}
	
	@Override
    public String toString() {
        return "Sensor " + getIdentificador() + " (" + getParametro() + "): " + 
                           getValor() + " " + getUnidades() + 
                           " " + "Ubicación: " + getUbicacion();
    }
	
	private static void testConstructores() {
		
		System.out.println("Clase SensorAmbSM \n\n" +
		                    "EJECUTANDO TEST DE CONSTRUCTORES.\n");
		
		// Probamos la construcción del objeto con parámetros válidos y "lógicos":
		System.out.println("Creando sensor con parámetros 'coherentes': 1, C02, ppm");
		SensAmbSM s1 = new SensAmbSM(1, "CO2", "ppm");
		
		// Llamamos implícitamente a toString que a su vez llama al resto de métodos.
		System.out.println(" Sensor creado ----> " + s1);
		System.out.println();

		// Llamamos a mostrar info que a su vez llama al resto de métodos.
		s1.mostrarInfo();
		
		System.out.println("------------------------------------------------------------");
		// Probamos la construcción del objeto con parámetros válidos pero absurdos:
		System.out.println("Creando sensor con parámetros 'absurdos': 1, CO2, patatas");
		SensAmbSM s2 = new SensAmbSM(1, "CO2", "patatas");
		System.out.println(" Sensor creado ----> " + s2);
		System.out.println();	
		s2.mostrarInfo();
		System.out.println();	
		System.out.println("¿tiene sentido?");
		System.out.println();
		
		System.out.println("------------------------------------------------------------");
		// Probamos la construcción del objeto con parámetros inválidos y absurdos:
		System.out.println("Creando sensor con parámetros 'absurdos': -1, Jose Manuel, null");
		SensAmbSM s3 = new SensAmbSM(-1, "Jose Manuel", null);
		System.out.println(" Sensor creado ----> " + s3);
		System.out.println();	
		s3.mostrarInfo();
		System.out.println();	
		System.out.println("¿tiene sentido?");
		System.out.println();	
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Problemas:");
		System.out.println("Incluso si el código no produce errores de compilación ni de ejecución ...\n" +
				           "Podemos crear sensores con atributos incoherentes.\n" +
				           "Podemos crear distintos sensores con el mismo identificador.\n\n" + 
				           "Estos problemas deben resolverse:\n" +
				           "- Si es posible en la propia clase.\n " +
				           "- Si no pueden resolverse en la clase deben tratarse en el código que usa la clase.\n\n" +
				           "Iremos añadiendo formas de solucionar estos problemas a lo largo del curso ... \n" 
				          );	
	}
	
	public static void testSensorEnSimulador() {

		
		System.out.println("Clase SensorAmbSM \n\n" +
				"PROBANDO INTEGRACIÓN CON SIMULADOR.\n");

		// Creamos ciudad simulada.
		Ciudad0 ciudad = Ciudad0.getCiudad();

		// Pedimos datos del sensor
		Scanner sc = new Scanner(System.in);

		System.out.println("\n--------------------------------------------------------");
		System.out.println("Introduzca los datos del sensor: ");

		System.out.print("ID: ");
		int id = sc.nextInt();

		// limpiar buffer, para eliminar el enter pendiente
		// nextInt() lee sólo el número
		sc.nextLine(); 

		System.out.print("Tipo de parámetro: ");
		String parametro = sc.nextLine();

		System.out.print("Unidades: ");
		String unidades = sc.nextLine();

		System.out.print("Ubicación - columna: ");
		int col = sc.nextInt();

		System.out.print("Ubicación - fila: ");
		int fila = sc.nextInt();

		// Creamos el sensor y le fijamos su ubicación
		SensAmbSM s1 = new SensAmbSM(id, parametro, unidades);
		s1.setUbicacion(col, fila);

		// Le decimos en qué ciudad está.
		s1.setCiudad(ciudad);

		// Leemos el parámetro correspondiente a su posición.
		System.out.println("Pulse enter para ver valor del sensor");
		sc.nextLine();
		System.out.println("valor leido = " + s1.getValor());

		// Agregamos el sensor explíciamente a la ciudad para que pueda 
		// gestionarlo.
		System.out.println("Pulse enter para agregar el sensor al simulador");
		sc.nextLine();
		ciudad.agregarSensor(s1.getCoordenadaX(),
				s1.getCoordenadaY(), s1);

		// Leemos el parámetro correspondiente a su posición.
		System.out.println("Pulse enter para ver valor del sensor");
		sc.nextLine();
		System.out.println("valor leido = " + s1.getValor());


		// Mostrar pantalla de control
		System.out.println("=== 🏙️ Pantalla de Control  ===");
		s1.mostrarInfo();

		// Mostrar descripciones rápidas
		System.out.println("📋 Resumen:");
		System.out.println(s1);

		sc.close();	
	}
	
	public static void main(String args []) {		
		testConstructores();
		testSensorEnSimulador();	
	}
}
