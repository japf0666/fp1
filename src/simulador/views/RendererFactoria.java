package simulador.views;

import java.awt.*;
import java.util.Random;

import tipos.CeldaUrbana;

public class RendererFactoria extends AbstractCeldaRenderer {

    private Random random = new Random(0);

    @Override
    public void pintarTextura(Graphics2D g, CeldaUrbana celda,
                       int x, int y, int size) {

        g.setColor(Color.DARK_GRAY);

        for (int i = 0; i < size; i += 6) {
            g.drawLine(x + i, y, x + i, y + size);
            g.drawLine(x, y + i, x + size, y + i);
        }
        
        g.setColor(new Color(200, 200, 200, 100));
        g.fillRect(x + 2, y + 2, size - 4, size - 4);
    }  
}