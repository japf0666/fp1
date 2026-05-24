package simulador.views;

import java.awt.Color;

/**
 * Define una escala de colores para las represntaciones de los parámetros ambientales
 * como fondos de las celdas urbanas.
 */
public class ColorScale {

    private Color baseColor;
    private double maxValue;

    public ColorScale(Color baseColor, double maxValue) {
        this.baseColor = baseColor;
        this.maxValue = maxValue;
    }

    public Color getColor(double value) {
        double normalized = Math.min(value / maxValue, 1.0);

        int r = interpolate(255, baseColor.getRed(), normalized);
        int g = interpolate(255, baseColor.getGreen(), normalized);
        int b = interpolate(255, baseColor.getBlue(), normalized);
        return new Color(r, g, b);
    }

    private int interpolate(int start, int end, double factor) {
        return (int) (start + (end - start) * factor);
    }
}
