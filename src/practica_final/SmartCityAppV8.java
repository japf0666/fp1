package practica_final;
import java.util.Scanner;

import tipos.ICalibrable;
import tipos.IRegulable;
import tipos.ISensorSimple;
import tipos.IRegistrable;
import tipos.SensorAjustable;
import tipos.SensorConHistorial;
import tipos.SensorSimple;

import simulador.Ciudad0;

public class SmartCityAppV8 {

    private static Scanner entrada = new Scanner(System.in);
    private static ISensorSimple [] sensores = new ISensorSimple[2];
    private static int totalSensores = 0;

    public static ISensorSimple buscarSensorPorId(int id) {

        for (int i = 0; i < sensores.length; i++) {      
            if (sensores[i]!=null && sensores[i].getIdentificador() == id) {
                return sensores[i];
            }
        }
        return null;
    }
    // BIENVENIDA
    public static void darBienvenida() {

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

        System.out.print("Introduce tu nombre: ");
        String nombre = entrada.nextLine();

        System.out.print("Introduce tu ciudad: ");
        String ciudad = entrada.nextLine();

        System.out.print("Introduce el año actual: ");
        int año = entrada.nextInt();
        entrada.nextLine();

        System.out.println("\n👋 Hola " + nombre +
                ", bienvenido/a a " + ciudad +
                " (SmartCity " + año + ")");
    }

    // MENÚ
    public static void mostrarMenu() {
        System.out.println("\n📡 --- SMART CITY --- 📡");
        System.out.println("1. Registrar sensor (hasta 16 sensores)");
        System.out.println("2. Mostrar valor del sensor");
        System.out.println("3. Verificar estado");
        System.out.println("4. Calibrar sensor (solo ambiental)");
        System.out.println("5. Configurar límite velocidad (solo tráfico)");
        System.out.println("6. Comprobar si requiere intervención (solo ruido)");
        System.out.println("7. Mostrar historial de un sensor");
        System.out.println("8. Listar sensores");
        System.out.println("9. Eliminar sensor");
        System.out.println("0. Salir");
    }

    // REGISTRAR SENSOR (GENÉRICO)
    public static void registrarSensor() {
      int posicionLibre=-1;
      for (int i=0; i<sensores.length;i++) {
    	   if (sensores[i] == null) {
    		 posicionLibre = i;
    	     i=sensores.length; // en lugar de hacer un break;
    	   }
      }
      if (posicionLibre == -1) {
    		System.out.println("No queda espacio para más sensores. Elimine uno");
      }
      else {
          System.out.println("\n¿Qué parámtro mide:  ?");
          String[] parametros = {"CO2", "temperatura", "humedad", "ruido"};
          System.out.print("Tipo (texto): ");
          String nombreTipo = entrada.nextLine();
          
          boolean calibrable = false, regulable = false, historial = false;
          
          System.out.println("\n¿Es calibrabler:  ?(s/n");
          char respuesta = entrada.nextLine().charAt(0);
          calibrable = (respuesta == 's' || respuesta == 'S')? true : false;
          
          System.out.println("\n¿Es regulable (fondos de escala):  ?(s/n");
          respuesta = entrada.nextLine().charAt(0);
          regulable = (respuesta == 's' || respuesta == 'S')? true : false;

          System.out.println("\n¿Almacena historial de medidas:  ?(s/n");
          respuesta = entrada.nextLine().charAt(0);
          historial = (respuesta == 's' || respuesta == 'S')? true : false;
          
          if (calibrable && regulable && historial) {
        	  sensores[posicionLibre] = new SensorConHistorial(nombreTipo);
          }
          else if(calibrable && regulable) {
        	  sensores[posicionLibre] = new SensorAjustable(nombreTipo);        	  
          }
          else {
			  sensores[posicionLibre] = new SensorSimple(nombreTipo);
		  }

          System.out.print("Coordenada X: ");
          int x = entrada.nextInt();

          System.out.print("Coordenada Y: ");
          int y = entrada.nextInt();
          
          sensores[posicionLibre].setCoordenadaX(x);
          sensores[posicionLibre].setCoordenadaY(y);
          
          entrada.nextLine();
          totalSensores++;
          
          System.out.println("Nuevo sensor ----> " + sensores[posicionLibre]);

       }
    }

    public static void eliminarSensor() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
    	if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }
		 
		 for (int i=0; i<sensores.length; i++) {
			 if (sensores[i]==sensor) {
				 sensores[i]=null;
			 }
		 }
		 totalSensores-=1;
	 }
    // MOSTRAR VALOR (POLIMORFISMO)
    public static void mostrarValor() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
    	if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }

    	double valor = sensor.getValor();
    	    
        System.out.printf("\n📊 Valor del sensor: %.2f%n", valor);
        for (int i = 0; i < (int) valor; i++) {
            System.out.print("█");
        }
        System.out.println();
    }

    // VERIFICAR RANGO 
    public static void verificarEstado() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
    	
        if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }
       
        System.out.println("\n🔍 Estado del sensor:" +
				"\nID: " + sensor.getIdentificador() +
				"\nTipo: " + sensor.getTipo() +
				"\nUbicación: [" + sensor.getCoordenadaX() + ", " + sensor.getCoordenadaY() + "]" +
				"\nValor actual: " + sensor.getValor());
        
        if (sensor instanceof SensorAjustable) {
			SensorAjustable s = (SensorAjustable) sensor;
			System.out.println("Offset = " + ((SensorAjustable) sensor).getOffset()); 
			System.out.println("Fondeos de escal = [" + ((SensorAjustable) sensor).getValorMinimo() + ", " + ((SensorAjustable) sensor).getValorMaximo() + "]"); 
			
		}
        else if (sensor instanceof SensorConHistorial) {
            System.out.println(((SensorConHistorial) sensor).getCapacidad()); 
        }
    }

    // CALIBRAR AMBIENTAL
    public static void calibrarSensor() {

    	if (totalSensores== 0) {
    		System.out.println("❌ No hay sensores registrados");
    		return;
    	}

    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
    	if (sensor == null) {
    		System.out.println("⚠️ Sensor no registrado");
    		return;
    	}

    	if (sensor instanceof ICalibrable) {

    		ICalibrable s = (ICalibrable) sensor;

    		System.out.print("Nuevo offset de calibración: ");
    		double ajuste = entrada.nextDouble();

    		s.calibrar(ajuste);

    		System.out.println("✅ Valor actualizado a " + s.getOffset());

    	} else {
    		System.out.println("❌ Este sensor no es calibrable");
    	}
    }
    
    // CONFIGURAR TRÁFICO
    public static void configurarLimite() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
        if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }

        if (sensor instanceof IRegulable) {

        	IRegulable s = (IRegulable) sensor;

            System.out.print("Nuevo límite máximo: ");
            int limite = entrada.nextInt();

            s.setValorMinMax(s.getValorMinimo(), limite);    
            System.out.println("✅ Límite actualizado a " + s.getValorMaximo());

        } else {
            System.out.println("❌ Este sensor no es regulable (fondos de escala fijos");
        }
    }

    /*
    // COMPROBAR INTERVENCIÓN 
    public static void comprobarIntervencion() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	Sensor sensor = buscarSensorPorId(id);
        if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }

        if (sensor instanceof SensorRuido) {

            SensorRuido s = (SensorRuido) sensor;
            boolean atencion = s.requiereAtencion();
               if (atencion) {
                  System.out.println("✅ El sensor requiere intervención");
               }
               else {
            	  System.out.println("✅ El sensor no requiere intervención");
               }

        } else {
            System.out.println("❌ Este sensor no es de ruido");
        }
    }
    */
    
    
    public static void mostrarHistorico() {

    	if (totalSensores == 0) {
    	    System.out.println("❌ No hay sensores registrados");
    	    return;
    	}
    	System.out.print("Introduce ID del sensor: ");
    	int id = entrada.nextInt();

    	ISensorSimple sensor = buscarSensorPorId(id);
    	if (sensor == null) {
            System.out.println("⚠️ Sensor no registrado");
            return;
        }
    	
    	if (!(sensor instanceof SensorConHistorial)) {
			System.out.println("❌ Este sensor no almacena historial de medidas");
			return;
		}
    	
    	IRegistrable s = (SensorConHistorial) sensor;
    	
    	/*
    	sensor.historial.mostrarHistorial(); 
    	System.out.printf(" Valor máximo: %.2f%n",
                sensor.historial.obtenerMaximo());

        System.out.printf(" Valor mínimo: %.2f%n",
                sensor.historial.obtenerMinimo());

        System.out.printf(" Media: %.2f%n",
                sensor.historial.calcularMedia());
                */
    }
    
    public static void listarSensores() {

        if (totalSensores == 0) {
            System.out.println("⚠️ No hay sensores registrados");
            return;
        }

        for (int i = 0; i < sensores.length; i++) {
        	if (sensores[i]!=null)
              System.out.println(sensores[i]);
        }
    }
    
    
    public static void main(String[] args) {
    	ciudad0 = Ciudad0.getCiudad();
        darBienvenida();
        int opcion;

        do {
            mostrarMenu();
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    registrarSensor();
                    break;

                case 2:
                    mostrarValor();
                    break;

                case 3:
                    verificarEstado();
                    break;

                case 4:
                    calibrarSensor();
                    break;
                    
                case 5:
                    configurarLimite();
                    break;

                case 6:
                    comprobarIntervencion();
                    break;  
                
                case 7:
                	mostrarHistorico();
                	break;
                case 8:
                	listarSensores();
                	break;
                case 9:
                	 eliminarSensor();
                	 break;
                case 0:
                    System.out.println("👋 Saliendo del programa...");
                    break;

                default:
                    System.out.println("❌ Opción inválida");
            }
            
        } while (opcion != 0);

        entrada.close();
    }
}