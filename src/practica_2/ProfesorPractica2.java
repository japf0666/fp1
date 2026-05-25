package practica_2;

import java.util.Scanner;

import simulador.Ciudad0;

public class ProfesorPractica2 {
	
	 public static void main(String[] args) {
		 
	        Scanner scanner = new Scanner(System.in);
	        
	        // Introduce los datos medidos para zona 1
	        System.out.println("Introduce el nivel de CO₂ (ppm) para zona 1:");
	        int co2_1 = scanner.nextInt();

	        System.out.println("Introduce la temperatura (ºC) para zona 1:");
	        double temperatura_1 = scanner.nextDouble();

	        System.out.println("Introduce el nivel de humedad (%) para zona 1:");
	        double humedad_1 = scanner.nextDouble();

	        System.out.println("\nLecturas de los sensores en zona 1:");
	        System.out.println("CO₂: " + co2_1 + " ppm");
	        System.out.println("Temperatura: " + temperatura_1 + " ºC");
	        System.out.println("Humedad: " + humedad_1 + " %");
	        System.out.println();

	        //Introduce los datos medidos para zona 2
	        System.out.println("Introduce el nivel de CO₂ (ppm) para zona 2:");
	        int co2_2 = scanner.nextInt();
	        
	        System.out.println("Introduce la temperatura (ºC) para zona 2:");
	        double temperatura_2 = scanner.nextDouble();
	        
	        System.out.println("Introduce el nivel de humedad (%) para zona 2:");
	        double humedad_2 = scanner.nextDouble();
	        
	        System.out.println("\nLecturas de los sensores en zona 2:");
	        System.out.println("CO₂: " + co2_2 + " ppm");
	        System.out.println("Temperatura: " + temperatura_2 + " ºC");
	        System.out.println("Humedad: " + humedad_2 + " %");
	        System.out.println();
	        
	        // Operaciones simples (sin condicionales)
	        double media1 = (co2_1 + co2_2)/ 2;
	        double media2 = (temperatura_1 + temperatura_2)/2;
	        double media3 = (humedad_1 + humedad_2)/2;
	
	        System.out.printf("Media aritmética de co2: %.2f%n", media1);
	        System.out.printf("Media aritmética de temperatura: %.2f%n", media2);
	        System.out.printf("Media aritmética de humedad: %.2f%n", media3);
	        
	        scanner.close();
	        
	        System.out.println("Pulse enter para arrancar simulador");
	        // Vamos a obtener ahora los valores de un simulador de smartcity:
	        Ciudad0 ciudad = Ciudad0.getCiudad();
	        double v1 = ciudad.getValor(2, 2, "CO2");
	        System.out.println("nivel de CO2 en [2,2] = " + v1);
	        double v2 = ciudad.getValor(4, 2, "CO2");
	        System.out.println("nivel de CO2 en [4,2] = " + v2);
	        double v3 = ciudad.getValor(4, 4, "CO2");
	        System.out.println("nivel de CO2 en [4,4] = " + v3);
	        
	        System.out.println("Media valores C02 = " + ((v1+v2+v3)/3));
	        
	        
	    }
}
