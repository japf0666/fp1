package simulador.views;

import java.awt.*;

import tipos.CeldaUrbana;

public class RendererSumidero extends AbstractCeldaRenderer{
	
    @Override
    public void pintarTextura(Graphics2D g, CeldaUrbana celda,
                       int x, int y, int size) {

        g.setColor(Color.BLUE);
        g.fillRect(x+2, y+2, size-2, size-2);
    }  

}