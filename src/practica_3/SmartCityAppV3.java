package practica_3;
import java.util.Scanner;
public class SmartCityAppV3 {

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
       
        Scanner entrada = new Scanner(System.in);

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
        
        int opcion = entrada.nextInt();
        System.out.println("Has elegido la opción " + opcion);
        System.out.println();
        
        // ======== Lectura de datos del primer sensor ========
        System.out.println("\n🔧 Registro del sensor 1");
        
        System.out.print("ID: ");
        int identificador = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextInt() lee sólo el número
        
        System.out.print("Tipo: ");
        String tipo = entrada.nextLine();
        
        System.out.print(" Ubicación (CoordenadaX): ");
        int coordenadaX = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextDouble() lee sólo el número
        
        System.out.print("Ubicación (CoordenadaY): ");
        int coordenadaY = entrada.nextInt();
        entrada.nextLine();
        
     // ======== Lectura de datos del segundo sensor ========
        System.out.println("\n🔧 Registro del sensor 2");
        
        System.out.print("ID: ");
        int identificador2 = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextInt() lee sólo el número
        
        System.out.print("Tipo: ");
        String tipo2 = entrada.nextLine();
        
        System.out.print("Ubicación (CoordenadaX): ");
        int coordenadaX2 = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextDouble() lee sólo el número
        
        System.out.print("Ubicación (CoordenadaY): ");
        int coordenadaY2 = entrada.nextInt();
        entrada.nextLine();
        
        // ======== Lectura de datos del tercer sensor ========
        System.out.println("\n🔧 Registro del sensor 3");
        
        System.out.print("ID: ");
        int identificador3 = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextInt() lee sólo el número
        
        System.out.print("Tipo: ");
        String tipo3 = entrada.nextLine();
        
        System.out.print("Ubicación (CoordenadaX): ");
        int coordenadaX3 = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer, para eliminar el enter pendiente
                       // nextDouble() lee sólo el número
        
        System.out.print("Ubicación (CoordenadaY): ");
        int coordenadaY3 = entrada.nextInt();
        entrada.nextLine();
        
  

		// Crear sensores 
        SensorAmbiental31 sensor1 = new SensorAmbiental31(coordenadaX, coordenadaY, tipo, "CO2");
        SensorAmbiental31 sensor2 = new SensorAmbiental31(coordenadaX2, coordenadaY2, tipo2, "");
        SensorAmbiental31 sensor3 = new SensorAmbiental31(coordenadaX3, coordenadaY3, tipo3, "");

        // Mostrar panel de control inicial
        System.out.println("🌾 SmartCity – Panel de Control de Sensores 🌾");
        System.out.println("-------------------------------------------");

        sensor1.mostrarInfo();
        System.out.println("-------------------------------------------");

        sensor2.mostrarInfo();
        System.out.println("-------------------------------------------");

        sensor3.mostrarInfo();
        System.out.println("-------------------------------------------");

        // Actualización de ubicación
        System.out.println("Introduzca nuevas coordenadas X e Y para el sensor 1: ");
        sensor1.setUbicacion(entrada.nextInt(), entrada.nextInt()); 
        
        System.out.println("Introduzca nuevas coordenadas X e Y para el sensor 2: ");
        sensor2.setUbicacion(entrada.nextInt(), entrada.nextInt()); 
        
        System.out.println("Introduzca nuevas coordenadas X e Y para el sensor 3: ");
        sensor3.setUbicacion(entrada.nextInt(), entrada.nextInt());  

        // Mostrar valores actualizados
        System.out.println("\n🔄 Actualización de Sensores:");
        System.out.println("---------------- Valor Sensor 1 ------------------");
        System.out.println(sensor1.getUbicacion());
        System.out.println("---------------- Valor Sensor 2 -----------------");
        System.out.println(sensor2.getUbicacion());
        System.out.println("---------------  Valor Sensor 3 -----------------");
        System.out.println(sensor3.getUbicacion());
        System.out.println("-------------------------------------------");

        // Mostrar resumen final con toString()
        System.out.println("\n📋 Resumen Final de Sensores:");
        System.out.println(sensor1);
        System.out.println(sensor2);
        System.out.println(sensor3);
    }
}

	


