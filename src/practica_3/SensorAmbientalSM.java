package practica_3;

import tipos.ISensor;

import simulador.Ciudad0;

public class SensorAmbientalSM implements ISensor {
	
	// Debería ser inmutable.
    private int identificador;
    
    // CO2, ruido, humedad, temperatura, ...
    // Podría ser inmutable.
    private String parametro;
    
    // Unidad de medida escogida (ppm, C, m/s ...)
    private String unidades;
    
    // celda en la que se ubica.
    private int coordenadaX, coordenadaY;
    
    // valor. se asumen parámetros escalalares.
    private double valor;
    
    // Ciudad en la que se va a agregar
    Ciudad0 ciudad = null;
    
    public SensorAmbientalSM(int id, String parametro, String unidades) {
    	identificador = id;
    	this.parametro = parametro;
    	this.unidades = unidades;
    }

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public String getDescripcion() {
		return toString();
	}

	@Override
	public int getCoordenadaX() {
		return coordenadaX;
	}

	@Override
	public int getCoordenadaY() {
		return coordenadaY;
	}

	@Override
	public void setCoordenadaX(int col) {
		coordenadaX = col;
	}

	@Override
	public void setCoordenadaY(int fila) {
		coordenadaY = fila;
	}

	@Override
	public String getParametro() {
		return parametro;
	}

	@Override
	public double getValor() {
		if (ciudad == null) {
			return valor;
		}
		else {
			return ciudad.getValor(coordenadaX, coordenadaY, parametro);
		}
	}
	
	public void setCiudad(Ciudad0 ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String getUnidades() {
		return unidades;
	}

	@Override
	public String getUbicacion() {
        return  "[" + coordenadaX + "," + coordenadaY + "]";
	}

	@Override
	public void setUbicacion(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
	}

	@Override
	public void mostrarInfo() {
        System.out.println("📡 Sensor ID: " + getIdentificador());
        System.out.println("🔎 Tipo: " + getParametro());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("📊 Valor actual: " + getValor());
        System.out.println("========================================");
	}
	
	@Override
    public String toString() {
        return "Sensor " + getIdentificador() + " (" + getParametro() + "): " + getValor() + " " + "Ubicación: " +
           getUbicacion();
    }

}
