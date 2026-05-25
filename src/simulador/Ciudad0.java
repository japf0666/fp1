package simulador;

import java.awt.BorderLayout;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import simulador.views.VistaCuadriculaUrbanaSimple;
import tipos.CeldaUrbana;
import tipos.IActuador;
import tipos.IElementoUrbano;
import tipos.ISensor;


/**
 * ElementalCityView es la ventana principal que muestra la cuadricula urbana
 * utilizando VistaCuadriculaElemental. Además, incluye un área de mensajes
 * para mostrar información relevante al usuario.
 * 
 * Esta clase actúa como el contenedor principal de la interfaz gráfica, 
 * integrando la vista de la cuadricula y proporcionando un espacio para mensajes.
 * 
 */
public class Ciudad0 extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CuadriculaUrbanaSimple cuadricula;
    private VistaCuadriculaUrbanaSimple vistaCuadricula;
      
    private final JTextArea mensajeArea;
    private final Set<String> parametrosActivos = new HashSet<>();
    
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    
    
    public Ciudad0() {
		this(new CuadriculaUrbanaSimple(10, 10)); // Tamaño por defecto de 10x10
	}


    public Ciudad0(CuadriculaUrbanaSimple cuadricula) {
        
    	this.cuadricula = cuadricula;
        setLayout(new BorderLayout());

        vistaCuadricula = new VistaCuadriculaUrbanaSimple(this.cuadricula);
        add(vistaCuadricula, BorderLayout.CENTER);

        // --- Área de mensajes en la parte inferior ---
        mensajeArea = new JTextArea(5, 60);
        mensajeArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(mensajeArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        add(scroll, BorderLayout.SOUTH);

        setTitle("Elemental City View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
        
    private String formatDouble(double v) {
        return DF.format(v);
    }

    private void appendMensaje(String msg) {
        mensajeArea.append(msg + "\n");
        mensajeArea.setCaretPosition(mensajeArea.getDocument().getLength());
    }

    // Opcional: método para consultar parámetros activos desde otras partes
    public Set<String> getParametrosActivos() {
        return new HashSet<>(parametrosActivos);
    }
    
    // Mostar superposición ambiental. Se delega a la vista de la cuadricula para actualizar la superposición
    public void mostrarParametro(String nombre) {
    	vistaCuadricula.mostrarParametro(nombre);
		appendMensaje("Mostrando superposición de: " + nombre);	
    }

    // Ocultar superposición ambiental. Se delega a la vista de la cuadricula para ocultar cualquier superposición activa
	public void ocultarParametros() {
		vistaCuadricula.ocultarParametros();
		appendMensaje("Ocultando superposiciones ambientales");
	}
    
    // Adición de un elemento a la celda, con actualización visual inmediata
    public void agregarElemento(int col, int fila, IElementoUrbano elemento) {
		if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
			throw new IllegalArgumentException("Columna o fila fuera de rango");
		}
		CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
		celda.setElemento(elemento);
		appendMensaje("Agregando elemento '" + elemento.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
		vistaCuadricula.repaint();
	}
    
    // Adición de un sensor a la celda, con actualización visual inmediata
    public void agregarSensor(int col, int fila, ISensor sensor) {
    	if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
    		throw new IllegalArgumentException("Columna o fila fuera de rango");
    	}
    	CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
    	celda.addSensor(sensor);
		appendMensaje("Agragando sensor '" + sensor.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
    	vistaCuadricula.repaint();
    }
    
    // Adición de un actuador a la celda, con actualización visual inmediata
    public void agregarActuador(int col, int fila, IActuador actuador) {
		if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
			throw new IllegalArgumentException("Columna o fila fuera de rango");
		}
		CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
		// System.out.println("agregar actuador" + celda);
		celda.addActuador(actuador);
		appendMensaje("Agregando actuador '" + actuador.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
		// System.out.println("agregar actuador" + celda);
		vistaCuadricula.repaint();
	}
    
    // Obtener valor de un parámetro ambiental específico en una celda dada, o null si no existe
    public Double getParametroAmbiental(int col, int fila, String nombreParametro) {
		if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
			throw new IllegalArgumentException("Columna o fila fuera de rango");
		}
		CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
		double valor = celda.getEstadoAmbiental().getParametro(nombreParametro) != null 
				? celda.getEstadoAmbiental().getParametro(nombreParametro).getValor() 
				: Double.NaN;
		appendMensaje("Retornando valor de celda (" + col + ", " + fila + ")" + " - " + nombreParametro + ": " + formatDouble(valor));
		return valor;
	}
    
    // Permite actualizar el estado ambiental de una celda dada.
    public void actualizarEstadoAmbiental(int col, int fila, String nombreParametro, double valor) {
		if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
			throw new IllegalArgumentException("Columna o fila fuera de rango");
		}
		CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
		celda.setParametroAmbiental(nombreParametro, valor);
		
		appendMensaje("Actualizando valor en celda (" + col + ", " + fila + ")" + " - " + nombreParametro + ": " + formatDouble(valor));
		vistaCuadricula.repaint();
    }
    
    
    // Adaptadores para invocación desde código alumnos.
    public void agregarSensor(ISensor s) {
    	agregarSensor(s.getCoordenadaX(), s.getCoordenadaY(), s);
    }
    
    
    public static void main(String[] args) {
		CuadriculaUrbanaSimple cuadricula = new CuadriculaUrbanaSimple(10, 10);
		Ciudad0 ciudad = new Ciudad0(cuadricula);
		
        // Por defecto, todo está vacío:
        for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
			for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
				ciudad.agregarElemento(col, fila, new ElementoVacio("vacio", col, fila));
			}
		}
        
        // Agregar sumideros en los bordes de la ciudad:
        for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
        	for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
				if (col == 0 || col == cuadricula.getNumColumnas() - 1 || fila == 0 || fila == cuadricula.getNumFilas() - 1) {
					ciudad.agregarElemento(col, fila, new ElementoSumidero("S", col, fila));
				}
			}
        }
        
        // Agregamos 4 factorias en el centro de la ciudad:
        ciudad.agregarElemento(4, 4, new ElementoIndustrial("I", 4, 4));
        ciudad.agregarElemento(4, 5, new ElementoIndustrial("I", 4, 5));
        ciudad.agregarElemento(5, 4, new ElementoIndustrial("I", 5, 4));
        ciudad.agregarElemento(5, 5, new ElementoIndustrial("I", 5, 5));
        
        // Agregamos zonas verdes
        ciudad.agregarElemento(4, 2, new ElementoVerde("V", 4, 2));
        ciudad.agregarElemento(5, 2, new ElementoVerde("V", 5, 2));
        ciudad.agregarElemento(4, 7, new ElementoVerde("V", 4, 7));
        ciudad.agregarElemento(5, 7, new ElementoVerde("V", 5, 7));
        
        ciudad.actualizarEstadoAmbiental(4, 4, "CO2", 500.0);
        
        ciudad.mostrarParametro("CO2");
        
        // Agregar un sensor de CO2 en la celda (4,4)
        ciudad.agregarSensor(4, 4, new SensorSimple(4, 4, "CO2", "ppm"));
        
        // Agregar un actuador de purificación de aire en la celda (5,5)
        ciudad.agregarActuador(5, 5, new ActuadorSimple(5, 5, "CO2", "ppm"));
    }
}