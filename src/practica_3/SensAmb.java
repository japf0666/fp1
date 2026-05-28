package practica_3;

import java.util.Scanner;

public class SensAmb {
	
    private int identificador;
    private String tipo;
    private int coordenadaX, coordenadaY;
    private double valor;
    
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setUbicacion(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public String getUbicacion() {
        return  "[" + coordenadaX + "," + coordenadaY + "]";
    }

    public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getTipo());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor());
        System.out.println("========================================");
    }

    public String toString() {
        return "Sensor " + getIdentificador() + " (" + getTipo() + "): " + getValor() + " " + "Ubicación: " +
           getUbicacion();
    }
    
    
    public static void main(String [] args) {
		// Crear un sensor
		SensAmb s1 = new SensAmb();

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
