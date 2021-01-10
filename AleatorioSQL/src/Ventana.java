import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

/**
 * Interfaz grafica de usuario y escritura del script SQL.
 * @author fmm
 *
 */
public class Ventana {
	private String tituloVentana;
	private int resultado;
	private File fichero;

	/**
	 * @param tituloVentana
	 */
	public Ventana(String tituloVentana) {
		super();
		this.tituloVentana = tituloVentana;
	}
	
	/**
	 * Construccion de la ventana principal
	 */

	public void iniciarInterfaz() {
		JFrame fr = new JFrame(tituloVentana);
		JButton b1 = new JButton("Generar script");
		JButton b2 = new JButton("Crear o abrir archivo...");
		JLabel lb1 = new JLabel("No hay archivo abierto");
		JLabel lb2 = new JLabel("nº de campos");
		JLabel lb3 = new JLabel("nº de tablas");
		JLabel lb4 = new JLabel("nº de de entradas");
		JTextArea ta1 = new JTextArea();
		JScrollPane scroll = new JScrollPane(ta1);
		JTextField tf2 = new JTextField();
		JTextField tf3 = new JTextField();
		JTextField tf4 = new JTextField();
		fr.setSize(1000, 600);
		lb1.setBounds(5, 5, 890, 30);
		b2.setBounds(705, 5, 290, 30);
		lb2.setBounds(205, 40, 90, 30);
		tf2.setBounds(305, 40, 90, 30);
		lb3.setBounds(5, 40, 90, 30);
		tf3.setBounds(105, 40, 90, 30);
		lb4.setBounds(405, 40, 145, 30);
		tf4.setBounds(545, 40, 90, 30);
		scroll.setBounds(5, 80, 990, 490);
		b1.setBounds(805, 40, 190, 30);
		fr.add(b1);
		fr.add(b2);
		fr.add(lb1);
		fr.add(lb2);
		fr.add(lb3);
		fr.add(lb4);
		fr.add(scroll);
		fr.add(tf2);
		fr.add(tf3);
		fr.add(tf4);
		fr.setLayout(null);
		fr.setVisible(true);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nCampos = 0, nTablas = 0, nEntradas = 0;
				String sCampos = "", sTablas = "", sEntradas = "";
				sCampos = tf2.getText();
				sTablas = tf3.getText();
				sEntradas = tf4.getText();
				if (sCampos.isEmpty()) {
					nCampos = 5;
				} else {
					nCampos = Integer.parseInt(sCampos);
				}
				if (sTablas.isEmpty()) {
					nTablas = 1;
				} else {
					nTablas = Integer.parseInt(sTablas);
				}
				if (sEntradas.isEmpty()) {
					nEntradas = 10;
				} else {
					nEntradas = Integer.parseInt(sEntradas);
				}
				ta1.setText(lanzarGenerador(nTablas, nCampos, nEntradas));
			}

		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int seleccion = fileChooser.showSaveDialog(lb1);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					fichero = fileChooser.getSelectedFile();
					lb1.setText(fichero.toString());
				}
			}

		});
	}
	
	/**
	 * Ejecuta la generacion de la base de datos
	 * @param nTablas
	 * @param nCampos
	 * @param nEntradas
	 * @return
	 */

	private String lanzarGenerador(int nTablas, int nCampos, int nEntradas) {
		String script = "";
		FileWriter escribidor;
		Generador generador = new Generador(nTablas, nCampos, nEntradas);
		try {
			escribidor = new FileWriter(fichero);
			generador.leer();
			script = generador.generar();
			if (!fichero.exists()) {
				fichero.createNewFile();
			}
			escribidor.write(script);
			escribidor.close();
			JOptionPane.showMessageDialog(null, "Script correctamente escrito");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return script;
	}

}
