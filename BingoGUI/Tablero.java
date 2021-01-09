/**
 * Version preliminar del Bingo por Frank Martinez 1AMT B. Falta mucho. Sentiros
 * libres de tocar cosas, lo que querais.
 * 
 * Tablero, esto es un carton del bingo, con sus propiedades, logica y
 * operaciones como rellenar
 * 
 * @author fmm
 *
 */

public class Tablero {
	public static final int DIMENX = 5;// DIMENSIONES TABLERO
	public static final int DIMENY = 5;// DIMENSIONES TABLERO
	private int[][] tablero = new int[DIMENX][DIMENY]; // array de los numeros del tablero
	private boolean[][] aciertos = new boolean[DIMENX][DIMENY]; // array de los aciertos sobre el tablero
	private int numeroBolasDeCadaNumero;
	private int maximoBola;
	private int contadorTiradas;
	private GeneradorAleatorio aleTablero;
	private int saldo;
	private int apuesta;
	private String nombre;

	/**
	 * Constructor
	 * 
	 * @param tablero
	 */
	public Tablero(String nombre, int saldo, int apuesta, int maximoBola, int numeroBolasDeCadaNumero) {
		super();
		for (int i = 0; i < DIMENX; i++) { // rellenar inicialmente tablero vacio
			for (int j = 0; j < DIMENY; j++) {
				this.tablero[i][j] = 0;
				this.aciertos[i][j] = false;
			}
		}
		this.numeroBolasDeCadaNumero = numeroBolasDeCadaNumero;
		this.maximoBola = maximoBola;
		this.saldo = saldo;
		this.apuesta = apuesta;
		this.nombre = nombre;
		this.aleTablero = new GeneradorAleatorio(maximoBola, 1);
	}

	/**
	 * Rellena el tablero con numeros aleatorios
	 */

	public void rellenarAleatorio() {
		for (int i = 0; i < DIMENX; i++) {
			for (int j = 0; j < DIMENY; j++) {
				this.tablero[i][j] = aleTablero.tirar();
			}
		}
	}
	
	public void resetear() {
		for (int i = 0; i < DIMENX; i++) { // rellenar vacio
			for (int j = 0; j < DIMENY; j++) {
				this.tablero[i][j] = 0;
				this.aciertos[i][j] = false;
			}
			contadorTiradas=0;
		}
	}

	/**
	 * Muestra el tablero por consola
	 */

	public String mostrar() {
		char ch1 = '*'; // caracter separador
		String s = "";
		for (int i = 0; i < DIMENX; i++) {
			s += "" + ch1 + ch1 + ch1 + ch1;
		}
		s += ch1 + "\n" + ch1;
		for (int i = 0; i < DIMENX; i++) {
			for (int j = 0; j < DIMENY; j++) {
				if (!aciertos[i][j]) { // numeros restantes por acertar
					s += "" + ch1 + this.tablero[i][j] + ch1;
					if (this.tablero[i][j] < 10) { // numeros de una cifra no descuadran
						s += "" + ch1;
					}
				} else { // numeros ya acertados tachados
					s += "" + ch1 + "xx" + ch1;
				}

			}
			s += "\n" + ch1;
		}
		for (int i = 0; i < DIMENX; i++) {
			s += "" + ch1 + ch1 + ch1 + ch1;
		}
		s += "\n";
		return s;
	}

	/**
	 * Actualiza bola tirada para el tablero y realiza comprobaciones
	 */

	public String actualizar(int bola) {
		int numero;
		String s = "";
		boolean haAcertado = false;
		numero = bola; // tirar bola
		s += "\nHa salido el numero: " + numero; // informar de la tirada
		for (int i = 0; i < DIMENX; i++) {
			for (int j = 0; j < DIMENY; j++) {
				if (numero == tablero[i][j]) {
					aciertos[i][j] = true; // actualizar matriz de aciertos
					haAcertado = true;
				}
			}
		}
		if (haAcertado) { // informar del acierto
			s += "\nFelicidades, ha acertado el numero";
			s += "\nHa sido tachado con xx";
		}

		comprobarTodo(); // comprobar lineas o bingo

		contadorTiradas++;
		s += "\ntirada n" + contadorTiradas + "\n";
		return s;
	}
	
	public String mostrarSaldo() {
		String s="";
		s += "Su saldo es: " + this.getSaldo() + "\n";
		return s;
	}

	/**
	 * @return the numeroBolasDeCadaNumero
	 */
	public int getNumeroBolasDeCadaNumero() {
		return numeroBolasDeCadaNumero;
	}

	/**
	 * @param numeroBolasDeCadaNumero the numeroBolasDeCadaNumero to set
	 */
	public void setNumeroBolasDeCadaNumero(int numeroBolasDeCadaNumero) {
		this.numeroBolasDeCadaNumero = numeroBolasDeCadaNumero;
	}

	/**
	 * Comprobar linea en las 2 dimensiones y bingo, informar en su caso
	 */

	public String comprobarTodo() {
		boolean lineaX, lineaY, bingo;
		String s = "";
		lineaX = comprobarLineaX();
		lineaY = comprobarLineaY();
		bingo = comprobarBingo();
		if (lineaX) {
			s += "Ha sacado linea en horizontal! Felicidades!\n";
		}
		if (lineaY) {
			s += "Ha sacado linea en vertical! Felicidades!\n";
		}
		if (bingo) {
			s += "Ha sacado BINGO!! Ha ganado\n";
		}
		return s;
	}

	/**
	 * Comprueba el ganador de una partida y devuelve su nombre, si no, devuelve no
	 * 
	 * @return
	 */

	public String comprobarGanador() {
		boolean esBingo;
		String ganador = "no";
		esBingo = comprobarBingo();
		if (esBingo) {
			ganador = this.nombre;
		}
		return ganador;
	}

	public String resolverApuestaGanador(int nJugadores) {
		this.saldo += this.apuesta * nJugadores;
		return this.nombre;
	}

	public void resolverApuestaPerdedor() {
		this.saldo -= this.apuesta;
	}

	/**
	 * Comprobar linea en la dimension X
	 * 
	 * @return
	 */

	private boolean comprobarLineaX() {
		boolean esLinea = false;
		int contador = 0;
		for (int i = 0; i < DIMENX; i++) {
			contador = 0;
			for (int j = 0; j < DIMENY; j++) {
				if (aciertos[i][j]) {
					contador++;
				}
			}
			if (contador >= DIMENX) {
				esLinea = true;
			}
		}
		return esLinea;
	}

	/**
	 * Comprobar linea en la dimension Y
	 * 
	 * @return
	 */

	private boolean comprobarLineaY() {
		boolean esLinea = false;
		int contador = 0;
		for (int j = 0; j < DIMENY; j++) {
			contador = 0;
			for (int i = 0; i < DIMENX; i++) {
				if (aciertos[i][j]) {
					contador++;
				}
			}
			if (contador >= DIMENY) {
				esLinea = true;
			}
		}
		return esLinea;
	}

	/**
	 * Comprobar bingo. No se sale si hay bingo, se tiene que salir el usuario.
	 * 
	 * @return
	 */

	public boolean comprobarBingo() {
		boolean esBingo = false;
		int contador = 0;
		for (int i = 0; i < DIMENX; i++) {
			for (int j = 0; j < DIMENY; j++) {
				if (aciertos[i][j]) {
					contador++;
				}
			}
		}
		if (contador >= DIMENX * DIMENY) {
			esBingo = true;
		}
		return esBingo;
	}

	/**
	 * @return the saldo
	 */
	public int getSaldo() {
		return saldo;
	}
	
	public int getApuesta() {
		return apuesta;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

}
