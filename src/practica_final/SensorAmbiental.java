package Practica8Estudiante;

public class SensorAmbiental extends Sensor implements ISensor {
	

    public SensorAmbiental(int identificador, String tipo, int coordenadaX, int coordenadaY, int tamañoHistorial) {
        super(identificador,tipo, coordenadaX, coordenadaY, tamañoHistorial);
    }

    /**
     *  Verificar si el valor está dentro del rango seguro (ejemplo según tipo de sensor)
     * @return Devuelve true si valor en rango y false en caso contrario
     */
   
     public boolean estaEnRangoSeguro() {
        if (tipo.equals("Temperatura")) {
                return valor >= 0 && valor <= 40; // °C seguro
        } else if (tipo.equals("Humedad")){
                return valor >= 10 && valor<=90; // % recomendado
        } else if (tipo.equals("CO2")) {
                return valor <= 1000; // ppm recomendado
        } else    
                return true; // Para otros sensores, asumimos seguro
     }
    
    // Clase interna 
    public  class Calibrador {
    	
    	private double ajuste;
    	
    	public Calibrador(double ajuste) {
    		this.ajuste = ajuste;
    	}
        public double calibrar() {
            return getValor() + ajuste; // simula pequeña corrección
        }
        
    }
    
    //IMPLEMENTA LA INTERFAZ
    public String reportarEstado() {
        if (estaEnRangoSeguro()) {
            return "✅ Sensor " + tipo + " (" + identificador + ") en rango seguro.";
        } else {
            return "⚠️ Sensor " + tipo + " (" + identificador + ") fuera de rango.";
        }
    }
       
}
