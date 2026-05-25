package simulador;

import java.util.ArrayList;

import tipos.CeldaUrbana;
import tipos.EstadoAmbiental;
import tipos.IActuador;
import tipos.ICuadriculaUrbanaSimple;
import tipos.IElementoUrbano;
import tipos.ISensor;
import tipos.ParametroAmbiental;

/**
 * Implementa una cuadricula urbana que no se actualiza a sí misma de forma dinámica.
 * 
 * El estado ambiental de cada celda es la suma de:
 * - El efecto ambiental que produce el elemento urbano que contiene
 * - El efecto ambiental que producen los actuadores que contiene.
 * 
 * No se tienen en cuenta los estados de las celdas adyacentes.
 * No hay fenómenos de desplazamiento de los efectos ambientales (p.e. viento).
 */
public class CuadriculaUrbanaSimple implements ICuadriculaUrbanaSimple {
	
	int numCols;
	int numFilas;
	CeldaUrbana[][] cuadricula;
	
	public CuadriculaUrbanaSimple(int nCols, int nFilas) {
		this.numCols = nCols;
		this.numFilas = nFilas;
		cuadricula = new CeldaUrbana[nFilas][nCols];
		for (int i = 0; i < nFilas; i++) {
			for (int j = 0; j < nCols; j++) {
				cuadricula[i][j] = new CeldaUrbana(j, i); 
			}
		}
	}
	
	public CuadriculaUrbanaSimple() {
		new CuadriculaUrbanaSimple(10,10);
	}
	
	@Override
	public CeldaUrbana getCeldaUrbana(int col, int fila) {
		
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> getCeldaUrbana en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return null;
		}	
		/*
		CeldaUrbana c = new CeldaUrbana(cuadricula[fila][col]);
		//System.out.println(c);
		return c;
		*/
		return cuadricula[fila][col];
		
	}


	@Override
	public int getNumColumnas() {
		return numCols;
	}

	@Override
	public int getNumFilas() {
		return numFilas;
	}
	
	@Override
	public EstadoAmbiental getEstadoAmbiental(int col, int fila) {
		if (!validarCoordenadas(col, fila)) {
			return null;
		}
		return cuadricula[fila][col].getEstadoAmbiental();
	}

	@Override
	public double getValor(int col, int fila, String param) {
		if (!validarCoordenadas(col, fila)) {
			return Double.NaN;
		}
		ParametroAmbiental p = cuadricula[fila][col].getEstadoAmbiental().getParametro(param);
		if (p == null) {
			return Double.NaN;
		}
		return p.getValor();
	}
	
	@Override
	public int setEstadoAmbiental(int col, int fila, EstadoAmbiental efecto) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> setEstadoAmbiental en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		if (efecto == null) {
			System.out.println("Error ---> setEstadoAmbiental: efecto = null");	
			return 2;			
		}
		cuadricula[fila][col].setEstadoAmbiental(efecto);
		return 0;
	}

	@Override
	public int addEstadoAmbiental(int col, int fila, EstadoAmbiental efecto) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> addEstadoAmbiental en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		if (efecto == null) {
			System.out.println("Error ---> addEstadoAmbiental: efecto = null");	
			return 2;			
		}
		cuadricula[fila][col].sumarEfectoAmbiental(efecto);
		return 0;
	}
	
	@Override
	public int setParametroAmbiental(int col, int fila, String nombre, double valor) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> setParametroAmbiental en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		cuadricula[fila][col].setParametroAmbiental(nombre, valor);
		return 0;
	}

	// Devuelve 0 si no se producen errores de ejecución.
	// Si la celda contiene un elemento lo sobreescribe.
	@Override
	public int agregarElementoUrbano(int col, int fila, IElementoUrbano elemento) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> agregarElementoUrbano en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		if (elemento == null) {
			System.out.println("Error ---> agregarElementoUrbano: elemento = null");	
			return 2;
		}
		elemento.setCoordenadaX(col);
		elemento.setCoordenadaY(fila);
		cuadricula[fila][col].setElemento(elemento);
		return 0;
	}

	@Override
	public int agregarSensor(int col, int fila, ISensor sensor) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> agregarSensor en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		if (sensor == null) {
			System.out.println("Error ---> agregarSensor: elemento = null");	
			return 2;
		}
		if (cuadricula[fila][col].contieneSensor(sensor)) {
			System.out.println("Error ---> agregarSensor: la celda ya contiene al sensor");
			System.out.println(sensor);
			return 3;
		}
		sensor.setCoordenadaX(col);
		sensor.setCoordenadaY(fila);
		cuadricula[fila][col].addSensor(sensor);;
		return 0;
	}

	@Override
	public int agregarActuador(int col, int fila, IActuador actuador) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> agregarActuador en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return 1;
		}
		if (actuador == null) {
			System.out.println("Error ---> agregarActuador: elemento = null");	
			return 2;
		}
		if (cuadricula[fila][col].contieneActuador(actuador)) {
			System.out.println("Error ---> agregarActuador: la celda ya contiene al actuador");
			System.out.println(actuador);
			return 3;
		}
		
		actuador.setCoordenadaX(col);
		actuador.setCoordenadaY(fila);
		cuadricula[fila][col].addActuador(actuador);
		return 0;
	}

	@Override
	public IElementoUrbano quitarElementoUrbano(int col, int fila) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> quitarElementoUrbano en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return null;
		}
		
		IElementoUrbano item = cuadricula[fila][col].getElemento();
		if ( item == null) {
			System.out.println("Error ---> quitarElementoUrbano: la celda no tiene ningún elemento urbano");
			return null;
		}
		cuadricula[fila][col].setElemento(null);
		return item;
	}

	@Override
	public ISensor quitarSensor(int col, int fila, ISensor sensor) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> quitarSensor en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return null;
		}
		if (sensor == null) {
			System.out.println("Error ---> quitarSensor, sensor es null");
            return null;			
		}
		if (!cuadricula[fila][col].contieneSensor(sensor)) {
			System.out.println("Error ---> quitarSensor, la celda no contiene al sensor especifcado");
            return null;			
		}
		cuadricula[fila][col].removeSensor(sensor);
		return sensor;
	}


	@Override
	public IActuador quitarActuador(int col, int fila, IActuador actuador) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> quitarActuador en (" + col + ", " + fila +
					            "), columna o fila fuera de rango");
			return null;
		}
		if (actuador == null) {
			System.out.println("Error ---> quitarActuador, actuador es null");
            return null;			
		}
		if (!cuadricula[fila][col].contieneActuador(actuador)) {
			System.out.println("Error ---> quitarActuador, la celda no contiene al actuador especifcado");
            return null;			
		}
		cuadricula[fila][col].removeActuador(actuador);
		return actuador;
	}

	@Override
	public int desplazarElementoUrbano(int colOrigen, int filaOrigen, int colDestino, int filaDestino) {

		if (!validarCoordenadas(colOrigen, filaOrigen)) {
			System.out.println("Error ---> desplazarElementoUrbano en (" + colOrigen + ", " + filaOrigen +
		            "), columnaOrigen o filaOrigen fuera de rango");
			return 1;			
		}		
		if (!validarCoordenadas(colDestino, filaDestino)) {
			System.out.println("Error ---> desplazarElementoUrbano en (" + colDestino + ", " + filaDestino +
		            "), columnaDestino o filaDestino fuera de rango");
			return 1;			
		}
		
		IElementoUrbano elemento = cuadricula[filaOrigen][colOrigen].getElemento();
		if (elemento == null) {
			System.out.println("Error ---> desplazarElementoUrbano. No hay elemento en origen (" 
		                       + colOrigen + ", " + filaOrigen + ")");
			return 2;			
		}
		
		if (cuadricula[filaDestino][colDestino].getElemento() != null)  {
			System.out.println("Error ---> desplazarElementoUrbano. Destino (" 
                    + colDestino + ", " + filaDestino + ") ya contiene un elemento urbano");
	        return 3;			
		}
		cuadricula[filaOrigen][colOrigen].setElemento(null);
		cuadricula[filaDestino][colDestino].setElemento(elemento);		
		return 0;
	}

	@Override
	public int desplazarSensor(int colOrigen, int filaOrigen, int colDestino, int filaDestino, ISensor sensor) {

		if (!validarCoordenadas(colOrigen, filaOrigen)) {
			System.out.println("Error ---> desplazarSensor en (" + colOrigen + ", " + filaOrigen +
		            "), columnaOrigen o filaOrigen fuera de rango");
			return 1;			
		}		
		if (!validarCoordenadas(colDestino, filaDestino)) {
			System.out.println("Error ---> desplazarSensor en (" + colDestino + ", " + filaDestino +
		            "), columnaDestino o filaDestino fuera de rango");
			return 1;			
		}
		
		if (!cuadricula[filaOrigen][colOrigen].contieneSensor(sensor)) {
			System.out.println("Error ---> desplazarSensor. El sensor especificado no está en origen (" 
		                       + colOrigen + ", " + filaOrigen + ")");
			return 2;			
		}
		
		if (cuadricula[filaDestino][colDestino].contieneSensor(sensor))  {
			System.out.println("Error ---> desplazarSensor. Destino (" 
                    + colDestino + ", " + filaDestino + ") ya contiene al sensor");
	        return 3;			
		}
		cuadricula[filaOrigen][colOrigen].removeSensor(sensor);
		cuadricula[filaDestino][colDestino].addSensor(sensor);	
		return 0;
	}

	@Override
	public int desplazarActuador(int colOrigen, int filaOrigen, int colDestino, int filaDestino, IActuador actuador) {

		if (!validarCoordenadas(colOrigen, filaOrigen)) {
			System.out.println("Error ---> desplazarActuador en (" + colOrigen + ", " + filaOrigen +
		            "), columnaOrigen o filaOrigen fuera de rango");
			return 1;			
		}		
		if (!validarCoordenadas(colDestino, filaDestino)) {
			System.out.println("Error ---> desplazarActuador en (" + colDestino + ", " + filaDestino +
		            "), columnaDestino o filaDestino fuera de rango");
			return 1;			
		}
		
		if (!cuadricula[filaOrigen][colOrigen].contieneActuador(actuador)) {
			System.out.println("Error ---> desplazarActuador. El actuador especificado no está en origen (" 
		                       + colOrigen + ", " + filaOrigen + ")");
			return 2;			
		}
		
		if (cuadricula[filaDestino][colDestino].contieneActuador(actuador))  {
			System.out.println("Error ---> desplazarActuador. Destino (" 
                    + colDestino + ", " + filaDestino + ") ya contiene al actuador");
	        return 3;			
		}
		cuadricula[filaOrigen][colOrigen].removeActuador(actuador);
		cuadricula[filaDestino][colDestino].addActuador(actuador);	
		return 0;
	}

	@Override
	public IElementoUrbano getElementoUrbano(int col, int fila) {
		if (!validarCoordenadas(col, fila)) {
			System.out.println("Error ---> getElementoUrbano en (" + col + ", " + fila +
		            "), columna o fila fuera de rango");
			return null;			
		}
		return cuadricula[fila][col].getElemento();
	}

	@Override
	public ISensor[] getSensores(int col, int fila) {
		ArrayList<ISensor> listaSensores = cuadricula[fila][col].getSensores();
		ISensor[] sensores = new ISensor[listaSensores.size()];
		for (int i = 0; i < sensores.length; i++) {
			sensores[i] = listaSensores.get(i);
		}
		return sensores;
	}

	@Override
	public IActuador[] getActuadores(int col, int fila) {
		ArrayList<IActuador> listaActuadores = cuadricula[fila][col].getActuadores();
		IActuador[] actuadores = new IActuador[listaActuadores.size()];
		for (int i = 0; i < actuadores.length; i++) {
			actuadores[i] = listaActuadores.get(i);
		}
		return actuadores;
	}

	public boolean validarCoordenadas(int col, int fila) {
		if (col < 0 || fila < 0 || col >= numCols || fila >= numFilas) {
			return false;
		}
		return true;
	}

}
