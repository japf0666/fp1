package simulador.views;

import java.awt.*;
import java.util.Random;

import tipos.CeldaUrbana;

public class RendererPlaza extends AbstractCeldaRenderer {

    private Random random = new Random(0);

    @Override
    public void pintarTextura(Graphics2D g, CeldaUrbana celda,
                       int x, int y, int size) {

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 1; i < size; i+= 15) {
            g.fillRect(x + i, y + i, 5, 5);
        }
        
        g.drawRect(x, y, size, size);
    }
}
