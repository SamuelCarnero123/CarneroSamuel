package App;

import javax.swing.SwingUtilities;

import Controlador.Controlador;
import Vista.Vista;

public class App {

	public static Vista vista;
	public static Controlador controlador = new Controlador();

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new Vista(controlador).setVisible(true));

	}

}
