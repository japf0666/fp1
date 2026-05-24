package simulador.views;

import java.awt.*;

import tipos.IUbicable;

public class RendererActuador implements IEntityRenderer {

    @Override
    public void render(Graphics2D g, IUbicable entity, int x, int y, int size) {

        int w = size / 3;
        int px = x + size / 2 - w / 2;
        int py = y + size / 2 - w / 2;

        g.setColor(new Color(220, 90, 40));
        g.fillRect(px, py, w, w);
        g.setColor(Color.WHITE);
        g.drawRect(px, py, w, w);

        // pequeña cruz interna
        g.drawLine(px + 3, py + w / 2, px + w - 3, py + w / 2);
        g.drawLine(px + w / 2, py + 3, px + w / 2, py + w - 3);
    }
}