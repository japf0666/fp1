package tipos;

public abstract class ElementoUrbanoAbstracto implements IElementoUrbano {
	
	protected String nombre;
	protected int col;
	protected int fila;
	
	
	public ElementoUrbanoAbstracto(String nombre, int c, int f) {
		this.nombre = nombre;
		this.col = c;
		this.fila = f;	
	}

	@Override
	public String getDescripcion() {
		return nombre;
	}

	@Override
	public int getCoordenadaX() {
		return col;
	}

	@Override
	public int getCoordenadaY() {
		return fila;
	}
	
	@Override
	public void setCoordenadaX(int col) {
		this.col = col;
	}
	
	@Override
	public void setCoordenadaY(int fila) {
		this.fila = fila;
	}
}
