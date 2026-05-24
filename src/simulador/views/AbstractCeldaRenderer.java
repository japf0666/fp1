package simulador.views;

import java.awt.*;

import tipos.CeldaUrbana;
import tipos.ParametroAmbiental;

public abstract class AbstractCeldaRenderer
        implements ICeldaRenderer {

    @Override
    public void render(
            Graphics2D g, CeldaUrbana celda,
            int x, int y, int size,
            EnvironmentalOverlayManager overlayManager) {

        pintarOverlayAmbiental(g, celda, x, y, size, overlayManager);
        pintarTextura(g, celda, x, y, size);
    }

    protected abstract void pintarTextura(
            Graphics2D g, CeldaUrbana celda,
            int x, int y, int size
    );

    protected void pintarOverlayAmbiental(
            Graphics2D g, CeldaUrbana celda,
            int x, int y, int size,
            EnvironmentalOverlayManager overlayManager){
    	
        // Se usa solo el primer parámetro activo.
        for (String parameter : overlayManager.getActiveParameters()) {
            ParametroAmbiental p =
                    celda.getEstadoAmbiental().getParametro(parameter);
            if (p == null) {continue;}

            ColorScale scale =  overlayManager.getScale(parameter);
            if (scale == null) {continue;}
            Color color =  scale.getColor(p.getValor());
            g.setColor(color);
            g.fillRect(x, y, size, size);
            return;
        }
    }
}