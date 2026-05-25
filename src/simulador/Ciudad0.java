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
import tipos.EstadoAmbiental;
import tipos.IActuador;
import tipos.ICuadriculaUrbanaSimple;
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
public class Ciudad0 extends JFrame implements ICuadriculaUrbanaSimple {

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
    
    ////////////////////////////////////////////////////////////////////////////
    /// MÉTODOS PARA GUI.
        
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
	
	/////////////////////////////////////////////////////////////////////////
	/// MÉTODOS DE ICuadriculaUrbanaDecorados
	
	@Override
	public int getNumColumnas() {
		return cuadricula.getNumColumnas();
	}
	
	@Override
	public int getNumFilas() {
		return cuadricula.getNumFilas();
	}
    
    // Adición de un elemento a la celda, con actualización visual inmediata.
	@Override
    public int agregarElementoUrbano(int col, int fila, IElementoUrbano elemento) {
    	int result = cuadricula.agregarElementoUrbano(col, fila, elemento);
    	if (result == 0) {
    		appendMensaje("Agregando elemento '" + elemento.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Agregando elemento ---> Error code = " + result);    		
    	}
    	return result;
	}
    
	@Override
	public IElementoUrbano quitarElementoUrbano(int col, int fila) {
		IElementoUrbano result = cuadricula.quitarElementoUrbano(col, fila);
    	if (result != null) {
    		appendMensaje("Quitando actuador '" + result.getDescripcion() + "' de celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Quitando elemento urbano ---> Error code = " + result);    		
    	}		
		
		return result;
	}

    
    // Adición de un sensor a la celda, con actualización visual inmediata
	@Override
    public int agregarSensor(int col, int fila, ISensor sensor) {
    	
    	int result = cuadricula.agregarSensor(col, fila, sensor);
    	if (result == 0) {
    		appendMensaje("Agregando sensor '" + sensor.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Agregando sensor ---> Error code = " + result);    		
    	}
    	return result;
    }
    
	@Override
	public ISensor quitarSensor(int col, int fila, ISensor sensor) {
		
		ISensor result = cuadricula.quitarSensor(col, fila, sensor);
    	if (result != null) {
    		appendMensaje("Quitando sensor '" + result.getDescripcion() + "' de celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Quitando sensor ---> Error code = " + result);    		
    	}		
		
		return result;	
	}

    
    // Adición de un actuador a la celda, con actualización visual inmediata
	@Override
    public int agregarActuador(int col, int fila, IActuador actuador) {

    	int result = cuadricula.agregarActuador(col, fila, actuador);
    	if (result == 0) {
    		appendMensaje("Agregando actuador '" + actuador.getDescripcion() + "' en celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Agregando actuador ---> Error code = " + result);    		
    	}
    	return result;
	}
    
	@Override
	public IActuador quitarActuador(int col, int fila, IActuador actuador) {
		IActuador result = cuadricula.quitarActuador(col, fila, actuador);
    	if (result != null) {
    		appendMensaje("Quitando actuador '" + actuador.getDescripcion() + "' de celda (" + col + ", " + fila + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Quitando actuador ---> Error code = " + result);    		
    	}		
		
		return result;
	}    
    
    
    
    // Permite actualizar el estado ambiental de una celda dada.
	@Override
    public int setParametroAmbiental(int col, int fila, String nombreParametro, double valor) {

    	int result = cuadricula.setParametroAmbiental(col, fila, nombreParametro, valor);
    	if (result == 0) {
    		appendMensaje("Actualizando valor en celda (" + col + ", " + fila + ")" + " - " + nombreParametro + ": " + formatDouble(valor));
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.setParametroAmbiental ---> Error code = " + result);    		
    	}
    	return result;
    }
    
	@Override
	public int setEstadoAmbiental(int col, int fila, EstadoAmbiental efecto) {
		
		int result = cuadricula.setEstadoAmbiental(col, fila, efecto);
    	if (result == 0) {
    		appendMensaje("Actualizando valor en celda (" + col + ", " + fila + ")" + " - " + efecto);
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.setEstadoAmbiental ---> Error code = " + result);    		
    	}		
		
		return result;
	}    
    
	@Override
	public int addEstadoAmbiental(int col, int fila, EstadoAmbiental efecto) {
		
		int result = cuadricula.addEstadoAmbiental(col, fila, efecto);
    	if (result == 0) {
    		appendMensaje("Actualizando estado ambiental en celda (" + col + ", " + fila + ")" + efecto);
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.setParametroAmbiental ---> Error code = " + result);    		
    	}				
		return result;
	}
	
	@Override
	public double getValor(int col, int fila, String param) {
		double v = cuadricula.getValor(col, fila, param);
		
		appendMensaje("ciudad0.getValor en (" + col + ", " + fila + ") --> " + v);
		
		return v;
	}
	
    @Override
	public int desplazarActuador(int colOrigen, int filaOrigen, int colDestino, int filaDestino, IActuador actuador) {

		int result = cuadricula.desplazarActuador(colOrigen, filaOrigen, colDestino, filaDestino, actuador);
    	if (result == 0) {
    		appendMensaje("Desplazando actuador " + actuador + "(" + colOrigen +  ", " + filaOrigen + ") --> (" +
    				                                                 colDestino + ", " + filaDestino + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.desplazarActuador ---> Error code = " + result);    		
    	}				
		return result;
	}
	
    @Override
	public int desplazarElementoUrbano(int colOrigen, int filaOrigen, int colDestino, int filaDestino) {

		int result = cuadricula.desplazarElementoUrbano(colOrigen, filaOrigen, colDestino, filaDestino);
    	if (result == 0) {
    		appendMensaje("Desplazando elemento urbano " + "(" + colOrigen +  ", " + filaOrigen + ") --> (" +
    				                                             colDestino + ", " + filaDestino + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.desplazarElementoUrbano ---> Error code = " + result);    		
    	}				
		return result;
	}
	
    @Override
	public int desplazarSensor(int colOrigen, int filaOrigen, int colDestino, int filaDestino, ISensor sensor) {

		int result = cuadricula.desplazarSensor(colOrigen, filaOrigen, colDestino, filaDestino, sensor);
    	if (result == 0) {
    		appendMensaje("Desplazando sensor " + sensor + "(" + colOrigen +  ", " + filaOrigen + ") --> (" +
    				                                             colDestino + ", " + filaDestino + ")");
    		vistaCuadricula.repaint();
    	}
    	else {
    		appendMensaje("Ciudad0.desplazarSensor ---> Error code = " + result);    		
    	}				
		return result;
	}
   
    @Override
	public IActuador[] getActuadores(int col, int fila) {
		return cuadricula.getActuadores(col, fila);
	}
	
    @Override
	public ISensor[] getSensores(int col, int fila) {
		return cuadricula.getSensores(col, fila);
	}

    @Override
	public CeldaUrbana getCeldaUrbana(int col, int fila) {
		return cuadricula.getCeldaUrbana(col, fila);
	}
    
    @Override
	public IElementoUrbano getElementoUrbano(int col, int fila) {
		return cuadricula.getElementoUrbano(col, fila);
	}
  
    @Override
	public EstadoAmbiental getEstadoAmbiental(int col, int fila) {
		return cuadricula.getEstadoAmbiental(col, fila);
	}
    
    public static Ciudad0 getCiudad() {
		CuadriculaUrbanaSimple cuadricula = new CuadriculaUrbanaSimple(10, 10);
		Ciudad0 ciudad = new Ciudad0(cuadricula);
		
        // Por defecto, todo está vacío:
        for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
			for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
				ciudad.agregarElementoUrbano(col, fila, new ElementoVacio("vacio", col, fila));
			}
		}
        
        // Agregar sumideros en los bordes de la ciudad:
        for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
        	for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
				if (col == 0 || col == cuadricula.getNumColumnas() - 1 || fila == 0 || fila == cuadricula.getNumFilas() - 1) {
					ciudad.agregarElementoUrbano(col, fila, new ElementoSumidero("S", col, fila));
				}
			}
        }
        
        // Agregamos 4 factorias en el centro de la ciudad:
        ciudad.agregarElementoUrbano(4, 4, new ElementoIndustrial("I", 4, 4));
        ciudad.agregarElementoUrbano(4, 5, new ElementoIndustrial("I", 4, 5));
        ciudad.agregarElementoUrbano(5, 4, new ElementoIndustrial("I", 5, 4));
        ciudad.agregarElementoUrbano(5, 5, new ElementoIndustrial("I", 5, 5));
        
        // Agregamos Elementos Residenciales
        ciudad.agregarElementoUrbano(4, 2, new ElementoResidencial("R", 4, 2));
        ciudad.agregarElementoUrbano(5, 2, new ElementoResidencial("R", 5, 2));
        ciudad.agregarElementoUrbano(4, 7, new ElementoResidencial("R", 4, 7));
        ciudad.agregarElementoUrbano(5, 7, new ElementoResidencial("R", 5, 7));
        
        // Agregamos Carreteras
        ciudad.agregarElementoUrbano(2, 2, new ElementoVia("C", 2, 2));
        ciudad.agregarElementoUrbano(2, 2, new ElementoVia("C", 2, 2));
        ciudad.agregarElementoUrbano(2, 7, new ElementoVia("C", 2, 7));
        ciudad.agregarElementoUrbano(2, 7, new ElementoVia("C", 2, 7));
        
        ciudad.agregarElementoUrbano(7, 2, new ElementoVia("C", 7, 2));
        ciudad.agregarElementoUrbano(7, 2, new ElementoVia("C", 7, 2));
        ciudad.agregarElementoUrbano(7, 7, new ElementoVia("C", 7, 7));
        ciudad.agregarElementoUrbano(7, 7, new ElementoVia("C", 7, 7));
                
        return ciudad;
    }
    
    
    public static void main(String[] args) {
		CuadriculaUrbanaSimple cuadricula = new CuadriculaUrbanaSimple(10, 10);
		Ciudad0 ciudad = new Ciudad0(cuadricula);
		
        // Por defecto, todo está vacío:
        for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
			for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
				ciudad.agregarElementoUrbano(col, fila, new ElementoVacio("vacio", col, fila));
			}
		}
        
        // Agregar sumideros en los bordes de la ciudad:
        for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
        	for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
				if (col == 0 || col == cuadricula.getNumColumnas() - 1 || fila == 0 || fila == cuadricula.getNumFilas() - 1) {
					ciudad.agregarElementoUrbano(col, fila, new ElementoSumidero("S", col, fila));
				}
			}
        }
        
        // Agregamos 4 factorias en el centro de la ciudad:
        ciudad.agregarElementoUrbano(4, 4, new ElementoIndustrial("I", 4, 4));
        ciudad.agregarElementoUrbano(4, 5, new ElementoIndustrial("I", 4, 5));
        ciudad.agregarElementoUrbano(5, 4, new ElementoIndustrial("I", 5, 4));
        ciudad.agregarElementoUrbano(5, 5, new ElementoIndustrial("I", 5, 5));
        
        // Agregamos zonas verdes
        ciudad.agregarElementoUrbano(4, 2, new ElementoVerde("V", 4, 2));
        ciudad.agregarElementoUrbano(5, 2, new ElementoVerde("V", 5, 2));
        ciudad.agregarElementoUrbano(4, 7, new ElementoVerde("V", 4, 7));
        ciudad.agregarElementoUrbano(5, 7, new ElementoVerde("V", 5, 7));
        
        ciudad.setParametroAmbiental(4, 4, "CO2", 500.0);
        
        ciudad.mostrarParametro("CO2");
        
        // Agregar un sensor de CO2 en la celda (4,4)
        ciudad.agregarSensor(4, 4, new SensorSimple(4, 4, "CO2", "ppm"));
        
        // Agregar un actuador de purificación de aire en la celda (5,5)
        ciudad.agregarActuador(5, 5, new ActuadorSimple(5, 5, "CO2", "ppm"));
        
        // Agregar actuador en celda fuera de rango.
        ciudad.agregarActuador(12, 12, new ActuadorSimple(12, 12, "CO2", "ppm"));

        
    }
}