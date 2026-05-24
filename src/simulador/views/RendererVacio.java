package simulador.views;

import java.awt.Color;
import java.awt.Graphics2D;

import tipos.CeldaUrbana;

public class RendererVacio extends AbstractCeldaRenderer {

	@Override
	protected void pintarTextura(Graphics2D g, CeldaUrbana celda, int x, int y, int size) {
		
        g.setColor(Color.DARK_GRAY);

        for (int i = 0; i < size; i += 10) {
        	for (int j = 0; j < size; j += 10) {
        		g.fillOval(x + i, y + j, 5, 5);
        	}
        }
        
        g.setColor(new Color(200, 200, 200, 100));
        g.drawRect(x + 2, y + 2, size - 4, size - 4);
	}

}
