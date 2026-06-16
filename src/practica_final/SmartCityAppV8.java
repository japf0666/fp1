package practica_final;
import java.util.Scanner;

import tipos.ICalibrable;
import tipos.IRegulable;
import tipos.ISensorSimple;
import tipos.LecturaSensor;
import tipos.IRegistrable;
import tipos.SensorAjustable;
import tipos.SensorConHistorial;
import tipos.SensorSimple;

// ..........................................................................
import simulador.Ciudad0;
// ..........................................................................

public class SmartCityAppV8 {

    private static Scanner entrada = new Scanner(System.in);
    private static ISensorSimple [] sensores = new ISensorSimple[2];
    private static int totalSensores = 0;
    
    // .........................................................
    private static Ciudad0 ciudad0;
    //..........................................................

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
        
        if (ciudad0 != null) {
			System.out.println("⚠️ Se ha detectado un entorno simulado (Ciudad0). " +
					"Los sensores registrados podrán interactuar con el simulador.");
			ciudad0.setCabecera(ciudad, nombre, " " + año);
		}
    }

    // MENÚ
    public static void mostrarMenu() {
        System.out.println("\n📡 --- SMART CITY --- 📡");
        System.out.println("1. Registrar sensor");
        System.out.println("2. Eliminar sensor");
        System.out.println("3. Mostrar valor del sensor");
        System.out.println("4. Verificar estado");
        System.out.println("5. Calibrar sensor (solo calibrable)");
        System.out.println("6. Configurar límites de escala (solo regulable)");
        System.out.println("7. Mostrar historial de un sensor (solo registrable)");
        System.out.println("8. Listar sensores");
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
            System.out.println("\n¿Qué parámetro mide:  ?");
            System.out.print("\"CO2\", \"temperatura\", \"humedad\", \"ruido\"");
            String nombreTipo = entrada.nextLine();
            
            boolean calibrable = false, regulable = false, historial = false;
            
            System.out.println("\n¿Es calibrable:  ?(s/n");
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
          
          //.......................................................................................
          // Si la ciudad no es null, se registra el sensor en el simulador
          // y se fija la ciudad del sensor a Ciudad0 para que pueda interactuar 
          // con el entorno simulado.
          if (ciudad0 != null) {
        	  // sensor conoce a ciudad.
        	  ((SensorSimple)  sensores[posicionLibre]).setCiudad(ciudad0);
        	  
        	  // ciudad conoce a sensor.
			  ciudad0.agregarSensor(sensores[posicionLibre].getCoordenadaX(), 
					                sensores[posicionLibre].getCoordenadaY(), 
					                sensores[posicionLibre]);	  
          }
          //.......................................................................................
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
    			
    			// Si la ciudad no es null, se elimina el sensor en el simulador.
    	    	if (ciudad0 != null) {
    	    		ciudad0.quitarSensor(sensores[i].getCoordenadaX(), sensores[i].getCoordenadaY(), sensores[i]);  
    	    	}    			
    			
    			// Eliminar sensor.
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
        if (sensor instanceof SensorConHistorial) {
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
    
    // CONFIGURAR LÍMITES DE ESCALA (FONDOS DE ESCALA AJUSTABLES)
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
            int limiteMax = entrada.nextInt();

            System.out.print("Nuevo límite mínimo: ");
            int limiteMin = entrada.nextInt();
            
            s.setValorMinMax(limiteMin, limiteMax);    
            System.out.println("✅ Límites actualizados a " + s.getValorMinimo() + "," + s.getValorMaximo());

        } else {
            System.out.println("❌ Este sensor no es regulable (fondos de escala fijos");
        }
    }

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
    	
    	System.out.print("Introduce tamaño del historial: ");
    	int tamaño = entrada.nextInt();
    	LecturaSensor [] lecturas = s.getLecturas(tamaño); //mostrarHistorial(); 
    	for (int i=0; i<lecturas.length; i++) {
    	  if (lecturas[i]!=null)	
    		System.out.print(lecturas[i]);
    	}
    	
    	System.out.printf(" Valor máximo: %.2f%n",
                s.getMaximo(tamaño));

        System.out.printf(" Valor mínimo: %.2f%n",
                s.getMinimo(tamaño));

        System.out.printf(" Media: %.2f%n",
                s.getMedia(tamaño));
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
                    eliminarSensor();
                    break;
                
                case 3:
                    mostrarValor();
                    break;

                case 4:
                    verificarEstado();
                    break;
                    
                case 5:
                    calibrarSensor();
                    break;

                case 6:
                    configurarLimite();
                    break;  
            
                case 7:
                	mostrarHistorico();
                	break;
                case 8:
                	listarSensores();
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