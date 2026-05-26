package practica_2;

import java.util.Scanner;

public class SmartCityAppV2 {
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
        System.out.println("\nHola "+nombre+". Bienvenida/o a "+ ciudad +". Acceso concedido al sistema SmartCity ("+ año +")");
       
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
        
        
        // ======== Lectura de datos de la primera zona ========
        System.out.println("===== 🔍 ZONA 1 =====");
        System.out.print("📍 Zona (Norte, Sur, Este, Oeste): ");
        String zona1 = entrada.nextLine();
        
        System.out.print("🌬 Nivel de CO₂ (ppm): ");
        int co2_1 = entrada.nextInt();

        System.out.print("🌡 Temperatura (ºC): ");
        double temp1 = entrada.nextDouble();

        System.out.print("💧 Humedad (%): ");
        double hum1 = entrada.nextDouble();
       
        entrada.nextLine(); // Limpieza del buffer
        
        System.out.print("💨 ¿Hay viento? (sí/no): ");
        String viento1 = entrada.nextLine();

        // ======== Lectura de datos de la segunda zona ========
        System.out.println("\n===== 🔍 Zona 2 =====");
        System.out.print("📍 Zona (Norte, Sur, Este, Oeste): ");
        String zona2 = entrada.nextLine();

        System.out.print("🌬 Nivel de CO₂ (ppm): ");
        int co2_2 = entrada.nextInt();

        System.out.print("🌡 Temperatura (ºC): ");
        double temp2 = entrada.nextDouble();

        System.out.print("💧 Humedad (%): ");
        double hum2 = entrada.nextDouble();

        entrada.nextLine(); // Limpieza del buffer
        
        System.out.print("💨 ¿Hay viento? (sí/no): ");
        String viento2 = entrada.nextLine();

        // ======== Cálculos requeridos ========
        double mediaHumedad = (hum1 + hum2)/2;
        double difTemperatura = Math.abs(temp1 - temp2);
        int difCO2 = Math.abs(co2_1 - co2_2);

        // ======== Pantalla de control visual ========
        System.out.println("\n\n==============================================");
        System.out.println("🖥️  SMARTCITY - PANTALLA DE CONTROL DE SENSORES");
        System.out.println("👷 Operador: " + nombre);
        System.out.println("================================================");

        System.out.println("\n📡 Zona 1 - " + zona1);
        System.out.println("🌬 CO₂: " + co2_1 + " ppm");
        System.out.println("🌡 Temperatura: " + temp1 + " ºC");
        System.out.println("💧 Humedad: " + hum1 + " %");
        System.out.println("💨 Viento: " + viento1);

        System.out.println("\n📡 Zona 2 - " + zona2);
        System.out.println("🌬 CO₂: " + co2_2 + " ppm");
        System.out.println("🌡 Temperatura: " + temp2 + " ºC");
        System.out.println("💧 Humedad: " + hum2 + " %");
        System.out.println("💨 Viento: " + viento2);

        System.out.print("\n📈 MEDIA HUMEDAD ENTRE SENSORES: ");
        System.out.printf("%.2f\n", mediaHumedad);
        System.out.print("🌡 DIFERENCIA DE TEMPERATURA EN ºC: ");
        System.out.printf("%.2f\n", difTemperatura); 
        System.out.print("🌬 DIFERENCIA DE CO₂ (ppm): " + difCO2);
      
        System.out.println("\n✅ Fin del monitoreo. Gracias, " + nombre + ".");
        System.out.println("==============================================");

        entrada.close(); 
    }
}
