package simulador.views;

import java.awt.*;
import java.util.Random;

import tipos.CeldaUrbana;

public class RendererZonaVerde extends AbstractCeldaRenderer {

    private Random random = new Random(0);
    
    private final int numTrees = 15;
    private final int treeSize = 5;
    private int xTrees[];
    private int yTrees[];
    
    public RendererZonaVerde() {
		xTrees = new int[numTrees];
		yTrees = new int[numTrees];
		for (int i = 0; i < numTrees; i++) {
			xTrees[i] = random.nextInt(40);
			yTrees[i] = random.nextInt(40);
		}
	}

    @Override
    public void pintarTextura(Graphics2D g, CeldaUrbana celda,
                       int x, int y, int size) {

        g.setColor(Color.GREEN);

        for (int i = 0; i < numTrees; i++) {
            //int px = x + random.nextInt(size);
            //int py = y + random.nextInt(size);
            g.fillOval(x + xTrees[i], y + yTrees[i], treeSize, treeSize);
        }
    }
}
