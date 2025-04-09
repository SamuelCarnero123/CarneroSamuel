package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controlador.Controlador;
import Modelo.Alumno;

public class Vista extends JFrame {
	private JComboBox<Alumno> comboAlumnos;
	private JComboBox<String> comboAsignaturas;
	private JTextField campoNota;
	private JTextArea areaResumen;
	private JButton btnGuardar;
	private Controlador controlador;

	private List<Alumno> listaAlumnos;

	public Vista() throws HeadlessException {
		super();
	}



	public Vista(Controlador controlador) {
		this.controlador = controlador;
		setTitle("Gestor de Notas");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		listaAlumnos = new ArrayList<>(List.of(new Alumno("Bartolito Clases"), new Alumno("María la de Java"),
				new Alumno("NullPointer González"), new Alumno("Lola Bytecode")));

		comboAlumnos = new JComboBox<>(listaAlumnos.toArray(new Alumno[0]));
		comboAsignaturas = new JComboBox<>(new String[] { "Lenguajes de Marcas", "Entornos de Desarrollo" });
		campoNota = new JTextField(5);
		btnGuardar = new JButton("Guardar Nota");
		areaResumen = new JTextArea(10, 40);
		areaResumen.setEditable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 5, 5));
		panel.add(new JLabel("Alumno:"));
		panel.add(comboAlumnos);
		panel.add(new JLabel("Asignatura:"));
		panel.add(comboAsignaturas);
		panel.add(new JLabel("Nota:"));
		panel.add(campoNota);
		panel.add(new JLabel(""));
		panel.add(btnGuardar);

		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(areaResumen), BorderLayout.CENTER);
	
	// EVENTOS

    // 1. Al cambiar de alumno, mostramos sus notas
    comboAlumnos.addActionListener(e -> controlador.actualizarResumen());
    //comboAlumnos.addActionListener(controlador.actionPerformed(2));
    

    // 2. Botón guardar
    btnGuardar.addActionListener(e -> controlador.guardarNota());

    // 3. Al cambiar de asignatura, podríamos pre-cargar la nota si ya existe
    comboAsignaturas.addActionListener(e -> controlador.cargarNotaExistente());

    // 4. Validación de nota (al perder foco)
    campoNota.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent e) {
            controlador.validarNota();
        }
    });
    
}


	public JComboBox<Alumno> getComboAlumnos() {
		return comboAlumnos;
	}

	public JComboBox<String> getComboAsignaturas() {
		return comboAsignaturas;
	}

	public JTextField getCampoNota() {
		return campoNota;
	}

	public JTextArea getAreaResumen() {
		return areaResumen;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	
	
	
}
