package simulador.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import tipos.CeldaUrbana;

/**
 * Renderer para zonas residenciales.
 * 
 * Características visuales:
 * .- Trama diagonal regular.
 * .- Baja agresividad visual.
 * .- Tonos grises suaves.
 * .- Sensación de orden urbano.
 * 
 * La textura está basada en líneas diagonales
 * con separación moderada para evitar ruido visual.
 */
public class RendererZonaResidencial extends AbstractCeldaRenderer {

    
    // Separación entre líneas diagonales.
    private static final int STEP = 8;

    // Color principal de la trama. Gris claro ligeramente cálido.
    private static final Color COLOR_TRAMA = Color.cyan.darker();


    @Override
    public void pintarTextura(Graphics2D g,  CeldaUrbana celda, int x, int y, int size) {
 
        //Configuración de línea fina.
        g.setStroke(new BasicStroke(1f));
        g.setColor(COLOR_TRAMA);

        // Dibujado de diagonales.
        for (int i = 0; i <= size; i += STEP) {
            g.drawLine(x, y + i, x + i, y);
            g.drawLine(x + i, y + size, x + size, y + i);
        }
    }
}
