package practica_1;

import java.util.Scanner;

public class ProfesorPractica1 {

	public static void main(String[] args) {
		
		// Este programa será un sistema SmartCity
		
		// Entrada/salida con la clase Scanner
		Scanner entrada = new Scanner(System.in);
		
		// Entrada/salida con la clase Teclado
		System.out.println("Introduce tu nombre:");
		String nombre1 = entrada.nextLine();
		System.out.println("Hola, " + nombre1 + ". Iniciando sistema SmartCity...");
		
        System.out.println("Introduce tu nombre:");
        String nombre2 = entrada.nextLine();

        // El operador + está sobrecargado y para números suma
        // pero para strings concatena o une
        System.out.println("Hola, " + nombre1 + " " + nombre2 + ". Iniciando sistema SmartCity...");
        
        entrada.close();
	}
}
