package simulador.views;

import java.awt.Color;
import java.util.*;

public class EnvironmentalOverlayManager {

    // Parámetro -> escala de color
    private Map<String, ColorScale> scales;

    
    //Parámetros visibles
    private Set<String> activeParameters;

    public EnvironmentalOverlayManager() {
        scales = new HashMap<>();
        activeParameters = new LinkedHashSet<>();
        inicializarEscalas();
    }

    private void inicializarEscalas() {

        // CO2 -> grises
        scales.put("CO2", new ColorScale(new Color(60, 60, 60), 1000));

        // temperatura -> rojos
        scales.put("temperatura", new ColorScale(new Color(220, 60, 60), 50));

        // ruido -> amarillos/naranja
        scales.put("ruido", new ColorScale(new Color(255, 170, 0), 120));
    }

    public void activateParameter(String name) {
        activeParameters.add(name);
    }

    public void deactivateParameter(String name) {
        activeParameters.remove(name);
    }

    public void clearParameters() {
        activeParameters.clear();
    }

    public boolean isActive(String parameter) {
        return activeParameters.contains(parameter);
    }

    public ColorScale getScale(String parameter) {
        return scales.get(parameter);
    }

    public Set<String> getActiveParameters() {
        return activeParameters;
    }
}
