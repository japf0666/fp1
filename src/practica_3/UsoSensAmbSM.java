package practica_3;

import java.util.Scanner;

import simulador.Ciudad0;

public class UsoSensAmbSM {
	
	
	public static void main(String[] args) {
		
		// Creamos ciudad simulada.
		Ciudad0 ciudad = Ciudad0.getCiudad();

		// Pedimos datos del sensor
		Scanner sc = new Scanner(System.in);
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
}
