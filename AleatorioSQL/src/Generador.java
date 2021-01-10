import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Lectura del diccionario y generacion de la base de datos como script SQL
 * @author fmm
 *
 */
public class Generador {
	public final int MAX = 1048575; //numero de lineas del diccionario, fijo
	private int nTablas;
	private int nCampos;
	private int nEntradas;
	private String palabras[] = new String[MAX];

	/**
	 * @param nTablas
	 * @param nCampos
	 * @param nEntradas
	 */
	public Generador(int nTablas, int nCampos, int nEntradas) {
		super();
		this.nTablas = nTablas;
		this.nCampos = nCampos;
		this.nEntradas = nEntradas;
	}

	/**
	 * Leer el diccionario en el array palabras
	 */
	public void leer() {
		File diccionario = new File("src/diccionario.dat");

		try {
			BufferedReader lector = new BufferedReader(new FileReader(diccionario));
			for (int i = 0; i < MAX; i++) {
				palabras[i] = lector.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Sacar una palabra aleatoria del diccionario
	 * @return
	 */

	private String nale() {
		return palabras[(int) (MAX * Math.random())];
	}

	public String generar() {
		String script = "";
		String nombreTabla[] = new String[nTablas];
		String nombreCampos[][] = new String[nTablas][nCampos];
		for (int i = 0; i < nTablas; i++) {
			nombreTabla[i] = nale();
			script += "DROP TABLE " + nombreTabla[i] + " CASCADE CONSTRAINTS;\n";
		}
		script += "\n";

		for (int i = 0; i < nTablas; i++) {
			script += "CREATE TABLE " + nombreTabla[i] + "(\n";
			for (int j = 0; j < nCampos; j++) {
				nombreCampos[i][j] = nale();
				script += nombreCampos[i][j] + " VARCHAR(30)";
				if (j == 0) {
					script += " PRIMARY KEY ";
				}
				if (j < nCampos -1) {
					script += ", ";
				}
			}
			script += ");\n\n";
		}

		for (int i = 0; i < nTablas; i++) {
			for (int j = 0; j < nCampos; j++) {
				String valores = "(";
				script += "INSERT INTO " + nombreTabla[i];
				for (int k = 0; k < nEntradas; k++) {
					valores += nale();
					if (k < nEntradas - 1) {
						valores += ",";
					} else {
						valores += ")";
					}
				}
				script += " VALUES " + valores + ";\n";
			}
		}
		return script;

	}

}
