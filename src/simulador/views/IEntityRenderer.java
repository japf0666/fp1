package simulador.views;

import java.awt.Graphics2D;

import tipos.IUbicable;

public interface IEntityRenderer {
    void render(Graphics2D g, IUbicable entity, int x, int y, int cellSize);
}