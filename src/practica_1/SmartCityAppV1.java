package practica_1;
import java.util.Scanner;

public class SmartCityAppV1 {

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


		System.out.println("\nHola "+nombre+". Bienvenido/a "+ ciudad +". Acceso concedido al sistema SmartCity ("+ año +")");

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

		entrada.close(); // Se cierra cuando se termina de usar la entrada por teclado
	}
} 
