/**
 * Version preliminar del Bingo por Frank Martinez 1AMT B. Falta mucho. Sentiros
 * libres de tocar cosas, lo que querais.
 * 
 * Generador de numeros aleatorios controlando las repeticiones para por ejemplo
 * generar tableros o sacar bolas
 * 
 * @author fmm
 *
 */

public class GeneradorAleatorio {
	private int maximo;
	private int repeticiones;
	private int numeroSacado; // no getters ni setters
	private boolean[] haSalido;
	private int[] lista;

	/**
	 * @param maximo
	 * @param repeticiones
	 */
	public GeneradorAleatorio(int maximo, int repeticiones) {
		super();
		this.maximo = maximo;
		this.repeticiones = repeticiones; // cada numero repetido por ejemplo 1 o 2 veces
		this.lista = new int[maximo * repeticiones + 1];
		this.haSalido = new boolean[maximo * repeticiones + 1];
		for (int i = 0; i < repeticiones; i++) {
			for (int j = 0; j < maximo; j++) { // rellenar con enteros consecutivos
				this.lista[i * j + j] = j;
				this.haSalido[i * j + j] = false;
			}
		}

	}

	/**
	 * Tira y devuelve numero sacado
	 * 
	 * @return
	 */

	public int tirar() {
		int m = maximo + 1;
		int num;
		do {
			num = (int) (Math.random() * m);
		} while (haSalido[num]);
		haSalido[num] = true;
		this.numeroSacado = lista[num];
		return this.numeroSacado;
	}
	public void resetear() {
		for (int i = 0; i < repeticiones; i++) {
			for (int j = 0; j < maximo; j++) { // rellenar con enteros consecutivos
				this.lista[i * j + j] = j;
				this.haSalido[i * j + j] = false;
			}
		}
	}

	/**
	 * @return the maximo
	 */
	public int getMaximo() {
		return maximo;
	}

	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	/**
	 * @return the repeticiones
	 */
	public int getRepeticiones() {
		return repeticiones;
	}

	/**
	 * @param repeticiones the repeticiones to set
	 */
	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}
}
