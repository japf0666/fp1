package simulador.views;

import java.awt.*;

import tipos.IUbicable;

public class RendererSensor implements IEntityRenderer {

    @Override
    public void render(Graphics2D g, IUbicable entity, int x, int y, int size) {

        int r = size/4;
        int cx = x + size/2 - r/2;
        int cy = y + size/2 - r/2;

        g.setColor(new Color(40, 120, 255));
        g.fillRect(cx, cy, r, r);

        g.setColor(Color.WHITE);
        g.drawRect(cx, cy, r, r);

        g.fillRect(cx + r/3,  cy + r/3,  r/4, r/4);
    }
}
