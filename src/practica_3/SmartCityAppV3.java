package practica_3;

import tipos.ISensorSimple;

import java.util.Scanner;

import simulador.Ciudad0;

public class SmartCityAppV3 {
	
    static Scanner entrada = new Scanner(System.in);
	static Ciudad0 city = Ciudad0.getCiudad();

	public static void main(String[] args) {
		
		 // ASCII Art de un edificio con antena/sensor
        System.out.println("\n      [|||]       ");
        System.out.println("         | |        ");
        System.out.println("     ____|_|____    ");
        System.out.println("    |  SMARTCITY |  ");
        System.out.println("    |   SENSOR   |  ");
        System.out.println("    |  NETWORK   |  ");
        System.out.println("    |____________|  ");
        System.out.println("       ||    ||     ");
        System.out.println("       ||    ||     ");
        System.out.println("     __||____||__   ");
        System.out.println();
       

        System.out.println("Introduce tu nombre:");
        String nombre = entrada.nextLine();
        System.out.println("Introduce tu ciudad:");
        String ciudad = entrada.nextLine();
        System.out.println("Introduce el año actual:");
        int año = entrada.nextInt();
        entrada.nextLine();
     
        System.out.println("\n👋 ¡Hola, " +nombre+". Bienvenida/o a "+ciudad+". Acceso concedido al sistema SmartCity ("+año+")");
       
	    // Menú simulado
        System.out.println("=======================================");
        System.out.println("     MENÚ PRINCIPAL - SmartCity");
        System.out.println("=======================================");
        System.out.println("1. Registrar un sensor en el sistema");
        System.out.println("2. Mostrar el valor de un sensor");
        System.out.println("3. Consultar el estado de un sensor");
        System.out.println("4. Salir del programa");
        System.out.println("=======================================");
        System.out.println("Por favor, selecciona una opción (sin efecto todavía):");
        
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Vamos a registrar dos sensores y a cambiarlos de posición");
        
        
        // ======== Lectura de datos del primer sensor ========
        System.out.println("\n🔧 Registro del sensor 1");       
        ISensorSimple s1 = registrarSensor();
        ((SensorAmbiental31) s1).mostrarInfo();
        
        // ======== Lectura de datos del segundo sensor ========
        System.out.println("\n🔧 Registro del sensor 2");
        ISensorSimple s2 = registrarSensor();
        ((SensorAmbiental31) s2).mostrarInfo();        

        // Incluimos sensores en la smartcity
        System.out.println("Pulse <ENTER> para añadir sensor 1 a la smartcity");
        entrada.nextLine();
        city.agregarSensor(s1.getCoordenadaX(), s1.getCoordenadaY(), s1);
        ((SensorAmbiental31) s1).setCiudad(city);
        ((SensorAmbiental31) s1).mostrarInfo();
        
        System.out.println("Pulse <ENTER> para añadir sensor 2 a la smartcity");
        entrada.nextLine();
        city.agregarSensor(s2.getCoordenadaX(), s2.getCoordenadaY(), s2);
        ((SensorAmbiental31) s2).mostrarInfo();
        
        System.out.println("Cambio de coordenadas de los sensores en la smartcity");
        System.out.println("los desplazamientos en la smartcity se propagan a los sensores");
        System.out.println("Al revés no es cierto");
        
        System.out.println("introduzca nuevas coordenadas para sensor 1");
        int x2 = entrada.nextInt();
        int y2 = entrada.nextInt();
        city.desplazarSensor(s1.getCoordenadaX(), s1.getCoordenadaY(), x2, y2, s1);
        System.out.println(s1);

        // Mostrar resumen final con toString()
        System.out.println("\n📋 Resumen Final de Sensores:");
        System.out.println(s1);
        System.out.println(s2);
    }
	
	private static ISensorSimple registrarSensor() {
        System.out.println("\n🔧 Registrando sensor. Introduzca datos: ");       
        System.out.print("Tipo: ");
        String tipo = entrada.nextLine();
        System.out.print(" Ubicación (CoordenadaX): ");
        int coordenadaX = entrada.nextInt();
        entrada.nextLine(); 
        System.out.print("Ubicación (CoordenadaY): ");
        int coordenadaY = entrada.nextInt();
        entrada.nextLine();	
        return new SensorAmbiental31(coordenadaX, coordenadaY, tipo, "units");     
	}
}

	


