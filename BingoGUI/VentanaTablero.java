import java.awt.Font;

import javax.swing.*;

public class VentanaTablero {
	private String n;
	Tablero tablero;
	JFrame fr = new JFrame();
	JLabel lb = new JLabel();
	Font fuente1= new Font("Monospaced",Font.BOLD,16);

	/**
	 * @param n
	 */
	public VentanaTablero(String n) {
		super();
		this.n = n;
		fr.setTitle("Tablero " + n);
	}

	
	public void actualizar(String representacion) {
		lb.setText(convertir(representacion));
	}

	public void iniciar() {
		fr.setSize(400, 400);
		lb.setBounds(5, 5, 390, 390);
		lb.setFont(fuente1);
		fr.setLayout(null);
		fr.add(lb);
		fr.setVisible(true);
	}
	
	public void eliminar() {
		fr.setVisible(false);
		fr.dispose();
	}

	private String convertir(String original) {
		return "<html>" + original.replaceAll("\n","<br>");
	}
}
