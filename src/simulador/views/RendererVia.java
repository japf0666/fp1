package simulador.views;

import java.awt.*;
import java.util.Random;

import tipos.CeldaUrbana;

public class RendererVia extends AbstractCeldaRenderer {

    private Random random = new Random(0);

    @Override
    public void pintarTextura(Graphics2D g, CeldaUrbana celda,
                       int x, int y, int size) {

        g.setColor(Color.white);
        g.drawRect(x, y, size, size);
    	
        g.setColor(Color.GRAY);

        for (int i = 0; i < 10; i++) {
            int px = x + random.nextInt(size);
            int py = y + random.nextInt(size);
            g.fillOval(px, py, 6, 6);
        }
    }
}
