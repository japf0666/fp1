package practica_3;

public class SensorAmbiental {
	
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
}
