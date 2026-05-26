package practica_3;

import java.util.Scanner;

public class UsoSensorAmbiental {

	public static void main(String[] args) {

		// Crear un sensor
		SensorAmbiental s1 = new SensorAmbiental();

		// Registro de datos para el sensor 1
		Scanner sc = new Scanner(System.in);
		System.out.println("\n🔧 Registro del sensor 1");

		System.out.print("ID: ");
		s1.setIdentificador(sc.nextInt());
		sc.nextLine(); // limpiar buffer, para eliminar el enter pendiente
		// nextInt() lee sólo el número

		System.out.print("Tipo: ");
		s1.setTipo(sc.nextLine());

		System.out.print("Ubicación: ");
		s1.setUbicacion(sc.nextInt(), sc.nextInt());

		// Mostrar pantalla de control
		System.out.println("=== 🏙️ Pantalla de Control  ===");
		s1.mostrarInfo();

		// Mostrar descripciones rápidas
		System.out.println("📋 Resumen:");
		System.out.println(s1);

		sc.close();
	}
}
