import java.awt.event.*;
import javax.swing.*;

public class InterfazBingo1 {
	public int contadorTableros = -1;
	public final int MAX_TAB = 10;
	public final int MAX_BOLA = 90;
	public VentanaTablero[] ventanas = new VentanaTablero[MAX_TAB];
	public Tablero[] tableros = new Tablero[MAX_TAB];
	public boolean haGanado = false;
	private GeneradorAleatorio aleBolas = new GeneradorAleatorio(MAX_BOLA, 1);
	private int ganador = -1;

	public void iniciarInterfaz() {
		JFrame fr = new JFrame("Aplicacion del Bingo");
		JLabel lb1 = new JLabel("");
		JButton b1 = new JButton("Nuevo tablero");
		JButton b2 = new JButton("Jugar");
		JButton b3 = new JButton("Resetear");
		JLabel lb2 = new JLabel("Nuevo usuario: ");
		JLabel lb3 = new JLabel("Cantidad a apostar: ");
		JTextField tb1 = new JTextField();
		JTextField tb2 = new JTextField("30");
		b2.setBounds(5, 5, 190, 190);
		b1.setBounds(205, 5, 190, 90);
		b3.setBounds(205, 105, 190, 90);
		lb1.setBounds(405, 5, 190, 100);
		lb2.setBounds(405, 5, 190, 30);
		lb2.setLabelFor(tb2);
		tb1.setBounds(405, 35, 190, 30);
		lb3.setBounds(405, 105, 190, 30);
		lb3.setLabelFor(tb2);
		tb2.setBounds(405, 135, 190, 30);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario;
				int cantidadApostada;
				nombreUsuario = tb1.getText();
				cantidadApostada = Integer.parseInt(tb2.getText());
				contadorTableros++;
				nuevoTablero(contadorTableros, nombreUsuario, cantidadApostada);
			}

		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}

		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearTodo();
			}
		});
		fr.add(lb1);
		fr.add(b1);
		fr.add(b2);
		fr.add(b3);
		fr.add(tb1);
		fr.add(tb2);
		fr.add(lb2);
		fr.add(lb3);
		fr.setSize(600, 250);
		fr.setLayout(null);
		fr.setVisible(true);
	}

	public void nuevoTablero(int n, String nombreUsuario, int cantidadApostada) {
		VentanaTablero ventana;
		Tablero tablero;
		if (nombreUsuario.isEmpty()) {
			nombreUsuario = "" + contadorTableros;
		}
		ventana = new VentanaTablero(nombreUsuario);
		ventanas[contadorTableros] = ventana;
		ventanas[contadorTableros].iniciar();
		tablero = new Tablero("" + contadorTableros, 200, cantidadApostada, MAX_BOLA, 1);
		tableros[contadorTableros] = tablero;
		tableros[contadorTableros].rellenarAleatorio();
	}

	private void actualizar() {
		int bola;
		bola = aleBolas.tirar();
		for (int i = 0; i <= contadorTableros; i++) {
			String s = "";
			s += tableros[i].mostrarSaldo();
			s += tableros[i].actualizar(bola);
			s += tableros[i].comprobarTodo();
			s += tableros[i].mostrar();

			ventanas[i].actualizar(s);
		}
		resolverApuesta();
	}

	private void resolverApuesta() {
		for (int i = 0; i <= contadorTableros; i++) {
			if (ganador==-1 && tableros[i].comprobarBingo()) {
				ganador = i;
				resetearPartida();
			}
		}
		if (ganador > -1) {
			
			tableros[ganador]
					.setSaldo(tableros[ganador].getSaldo() + contadorTableros * tableros[ganador].getApuesta());
			for (int i = 0; i <= contadorTableros; i++) {
				if (i != ganador) {
					tableros[i].setSaldo(tableros[i].getSaldo() - tableros[i].getApuesta());
				}
			}
		}
		ganador = -1;
	}

	private void resetearPartida() {
		aleBolas.resetear();
		for (int i = 0; i <= contadorTableros; i++) {
			tableros[i].resetear();
			tableros[i].rellenarAleatorio();
		}
	}

	private void resetearTodo() {
		for (int i = 0; i <= contadorTableros; i++) {
			ventanas[i].eliminar();
			tableros[i].resetear();
			tableros[i] = null;
			ventanas[i] = null;
		}
		contadorTableros = -1;
	}

}
