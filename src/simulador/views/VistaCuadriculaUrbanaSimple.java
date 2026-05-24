package simulador.views;

import java.awt.*;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.SwingUtilities;

import tipos.CeldaUrbana;
import tipos.ICuadriculaUrbanaSimple;
import tipos.IActuador;
import tipos.IElementoUrbano;
import tipos.ISensor;
import simulador.ElementoIndustrial;
import simulador.ElementoPeatonal;
import simulador.ElementoResidencial;
import simulador.ElementoVia;
import simulador.ElementoVerde;
import simulador.ElementoSumidero;
import simulador.views.EnvironmentalOverlayManager;


/**
 * Representa una cuadrícula con valores ajustables desde su interfaz, 
 * en la que se pueden integrar sensores y actuadores.
 * 
 * Actúa como un decorador de la cuadricula urbana, permitiendo
 * interactuar con ella y mostrando información detallada de cada celda al hacer clic derecho
 * con el ratón sobre cada celda. 
 * 
 * Además, permite superponer información ambiental relevante a través del EnvironmentalOverlayManager.
 */

public class VistaCuadriculaUrbanaSimple extends JPanel {

	private static final long serialVersionUID = 1L;
	private ICuadriculaUrbanaSimple cuadricula;
    private int cellSize = 40;
    
    // Mapa que asocia cada clase de elemento urbano con su renderer correspondiente.
    private Map<Class<?>, ICeldaRenderer> renderers = new HashMap<>();

    IEntityRenderer rendererSensor = new RendererSensor();
    IEntityRenderer rendererActuador = new RendererActuador();

    // Manager para superposiciones ambientales 
    private EnvironmentalOverlayManager overlayManager;


    /**
     * Constructor que inicializa el panel con la cuadricula urbana y configura 
     * los renderers para cada tipo de elemento.
     */
    public VistaCuadriculaUrbanaSimple(ICuadriculaUrbanaSimple cuadricula) {
        this.cuadricula = cuadricula;
        this.overlayManager = new EnvironmentalOverlayManager();
        setBackground(Color.WHITE);
        
        inicializarRenderers();
        
        
        // Mouse handler to show cell data on right-click / popup trigger
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePopupIfNeeded(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                handlePopupIfNeeded(e);
            }
            private void handlePopupIfNeeded(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showCellPopup(e);
                }
            }
        });               
    }
    
    private void inicializarRenderers() {
        renderers.put(ElementoIndustrial.class, new RendererFactoria());
        renderers.put(ElementoVerde.class, new RendererZonaVerde());
        renderers.put(ElementoVia.class, new RendererVia());
        renderers.put(ElementoPeatonal.class, new RendererPlaza());
        renderers.put(ElementoResidencial.class, new RendererZonaResidencial());
        renderers.put(ElementoSumidero.class, new RendererSumidero());
        renderers.put(null, new RendererVacio()); // Renderer para celdas sin elemento

    }    
    
    // Fija parámetro a mostrar en la superposición ambiental, 
    // ocultando cualquier otro parámetro previamente mostrado
    public void mostrarParametro(String nombre) {
        overlayManager.clearParameters();
        overlayManager.activateParameter(nombre);
        System.out.println("mostrar parametro: " + nombre);
        repaint();
    }   
    
    // Oculta cualquier parámetro ambiental mostrado en la superposición
    public void ocultarParametros() {
		overlayManager.clearParameters();
		System.out.println("ocultar parametros");
		repaint();
	}
    

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (int fila = 0; fila < cuadricula.getNumFilas(); fila++) {
            for (int col = 0; col < cuadricula.getNumColumnas(); col++) {
            	
                CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);
                
                if (celda != null) {
                	
                	//System.out.println("paint: dibujando celda");
                
                	int px = col * cellSize;
                	int py = fila * cellSize;
                
                	// Trama (vista asociado a elemnto urbano).
                	dibujarCelda(g2, celda, px, py);
                
                	// Sensores y actuadores superpuestos a vistas de elementos urbanos.
                	dibujarEntidades(g2, celda, px, py);
                }
            }
        }
        
        drawGrid(g2); // Dibuja la cuadrícula de fondo
    }
    
    
    private void drawGrid(Graphics2D g2) {
		g2.setColor(Color.LIGHT_GRAY);
		for (int x = 0; x <= cuadricula.getNumColumnas() * cellSize; x += cellSize) {
			g2.drawLine(x, 0, x, cuadricula.getNumFilas() * cellSize);
		}
		for (int y = 0; y <= cuadricula.getNumFilas() * cellSize; y += cellSize) {
			g2.drawLine(0, y, cuadricula.getNumColumnas() * cellSize, y);
		}
	}

    private void dibujarCelda(Graphics2D g2, CeldaUrbana celda, int px, int py) {
        
    	g2.setColor(Color.white);
        g2.drawRect(px, py, cellSize, cellSize);

        if (celda.getElemento() == null) {
            return;
        }

        ICeldaRenderer renderer =
                renderers.get(celda.getElemento().getClass());

        if (renderer != null) {
        	System.out.println("renderer != null");
            renderer.render(g2, celda, px, py, cellSize, overlayManager);
        }
    }
    
    
    private void dibujarEntidades(Graphics2D g2, CeldaUrbana celda, int px, int py) {
    	
    	if (celda == null) {
    		return;
    	}

        for (ISensor s : celda.getSensores()) {
            if (s != null) {
            	System.out.println("pintando sensor");
            	rendererSensor.render(g2, s, px, py, cellSize);
            }
        }    
        for (IActuador a : celda.getActuadores()) {
            if (a != null) { 
            	System.out.println("pintando actuador");
            	rendererActuador.render(g2, a, px, py, cellSize);
            }
        } 
    }    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                cuadricula.getNumColumnas() * cellSize,
                cuadricula.getNumFilas() * cellSize
        );
    }
    
    
    private void showCellPopup(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int col = x / cellSize;
        int fila = y / cellSize;

        if (col < 0 || fila < 0 || col >= cuadricula.getNumColumnas() || fila >= cuadricula.getNumFilas()) {
            return;
        }

        CeldaUrbana celda = cuadricula.getCeldaUrbana(col, fila);

        StringBuilder sb = new StringBuilder();
        sb.append("Cell: (").append(col).append(", ").append(fila).append(")\n\n");

        Object elemento = celda.getElemento();
        sb.append("Elemento: ").append(elemento == null ? "none" : elemento.toString()).append("\n\n");

        sb.append("Sensors (").append(celda.getSensores().size()).append("):\n");
        for (ISensor s : celda.getSensores()) {
            sb.append(" - ").append(String.valueOf(s)).append("\n");
        }
        sb.append("\nActuators (").append(celda.getActuadores().size()).append("):\n");
        for (IActuador a : celda.getActuadores()) {
            sb.append(" - ").append(String.valueOf(a)).append("\n");
        }

        // Try to append environmental overlay information if available via a descriptive method.
        // If no suitable method exists, this block fails silently and environmental info is omitted.
        try {
            Method m = overlayManager.getClass().getMethod("describeCell", CeldaUrbana.class);
            Object desc = m.invoke(overlayManager, celda);
            if (desc != null) {
                sb.append("\nEnvironmental:\n").append(String.valueOf(desc)).append("\n");
            }
        } catch (Exception ignored) {
            // no describeCell method -> skip environmental description
        }

        JTextArea text = new JTextArea(sb.toString());
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setColumns(40);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setPreferredSize(new Dimension(360, 220));

        JPopupMenu popup = new JPopupMenu();
        popup.add(scroll);
        popup.show(this, x, y);
    }
}