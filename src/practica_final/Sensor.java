package Practica8Estudiante;

/**
 * @author Bárbara Álvarez Torres
 * @version 2.0 curso2026-2027
 * @see "Para más información ver anexo Libro Allende"
 */
public class Sensor {
    
	protected int identificador;
    protected String tipo;
    protected double valor;
    protected int coordenadaX, coordenadaY;
    Historial historial;
    
    /**
     * Constructor para crear un nuevo sensor.
     * @param identificador ID único del sensor.
     * @param tipo Tipo de sensor (Temperatura, Humedad, CO2, etc.).
     * @param valor Valor inicial del sensor.
     * @param unidad Unidad de medida del sensor.
     * */
    public Sensor(int identificador, String tipo, int coordenadaX, int coordenadaY, int tamañoHistorial) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        historial = new Historial(tamañoHistorial);
    }
    
    /**
     * @param identificador representa el identificador del sensor
     */
    public void setIdentificador(int identificador) {
    	this.identificador = identificador;
    }
    /**
     * 
     * @return Devuelve el identificador del sensor
     */
    public int getIdentificador() {
        return identificador;
    }
    /**
     * 
     * @param tipo Representa el tipo de sensor
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * 
     * @return Devuelve el tipo de sensor
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * 
     * @return Devuelve el valor del sensor
     */
    public double getValor() {
    	valor = Math.random()*50; //valor entre 0 y 50
    	historial.registrar(valor);
        
        return Math.round(valor*100)/100; 
    }
    /**
     * 
     * @param coordenadaX Representa la coordenada X de un punto
     * @param coordenadaY Representa la coordenada Y de un punto
     */
    public void setUbicacion(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    /**
     * 
     * @return Devuelve la ubicación del sensor
     */
    public String getUbicacion() {
        return "[" + coordenadaX + "," + coordenadaY + "]";
    }
    /**
     * Este método no toma parámetros ni devuelve nada
     * Muestra la información del sensor a modo de tabla
     */
    public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getTipo());
        System.out.println("📊 Valor actual: " + getValor());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("========================================");
    }
    /**
     * @return Devuelve una cadena con la información del sensor
     */
    public String toString() {
        return "Sensor " + getIdentificador() + " (" + getTipo() + "): " + getValor() +  " Ubicación:" + getUbicacion();
    }

    // Método estático para comparar dos sensores
    public static boolean compararSensores(Sensor s1, Sensor s2) {
        return s1.getTipo().equals(s2.getTipo());
    }
    // Método de instancia para comparar con otro sensor
    public boolean compararSensores(Sensor otro) {
        return tipo.equals(otro.getTipo());
    }
    
    class Historial{
   	 double [] valores;
   	 int indice = 0;
   	 
   	 public Historial(int tamaño) {
   		 valores = new double[tamaño];
   	 }
     
   	 public void registrar(double valor) {
           valores[indice] = valor;
           indice=(indice+1)%valores.length; 
      }
       
      public void mostrarHistorial() {
           System.out.println("📚 Historial de valores:");
           for (int i = 0; i < valores.length; i++) {
               System.out.printf("  Valor %d: %.2f%n", (i + 1), valores[i]);
           }
       }
      
       public double obtenerMaximo() {
       	double maximo = valores[0];
       	for (int i=1; i<valores.length;i++) {
       		if (valores[i]>maximo) {
       			maximo = valores[i];
       		}
       	}
       	return maximo;
       }
       
       public double obtenerMinimo() {
       	double minimo = valores[0];
       	for (int i=1; i<valores.length;i++) {
       		if (valores[i]<minimo) {
       			minimo = valores[i];
       		}
       	}
       	return minimo;
       }
       
       public double calcularMedia() {
       	double suma = 0;
       	for (int i=0; i<valores.length;i++) {
       		suma+=valores[i];
       	}
       	return suma/valores.length;
       } 
   }
 }
    
