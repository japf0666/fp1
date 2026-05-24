package simulador.views;

import java.awt.Graphics2D;

import tipos.CeldaUrbana;

public interface ICeldaRenderer {

    void render(
            Graphics2D g,
            CeldaUrbana celda,
            int x,
            int y,
            int cellSize,
            EnvironmentalOverlayManager overlayManager
    );
}