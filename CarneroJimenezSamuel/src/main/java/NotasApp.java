import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class NotasApp extends JFrame {

    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<String> comboAsignaturas;
    private JTextField campoNota;
    private JTextArea areaResumen;
    private JButton btnGuardar;

    private List<Alumno> listaAlumnos;

    public NotasApp() {
        setTitle("Gestor de Notas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listaAlumnos = new ArrayList<>(List.of(
            new Alumno("Bartolito Clases"),
            new Alumno("María la de Java"),
            new Alumno("NullPointer González"),
            new Alumno("Lola Bytecode")
        ));

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
        comboAlumnos.addActionListener(e -> actualizarResumen());

        // 2. Botón guardar
        btnGuardar.addActionListener(e -> guardarNota());

        // 3. Al cambiar de asignatura, podríamos pre-cargar la nota si ya existe
        comboAsignaturas.addActionListener(e -> cargarNotaExistente());

        // 4. Validación de nota (al perder foco)
        campoNota.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validarNota();
            }
        });

        actualizarResumen();
    }

    private void guardarNota() {
        Alumno alumno = (Alumno) comboAlumnos.getSelectedItem();
        String asignatura = (String) comboAsignaturas.getSelectedItem();
        try {
            double nota = Double.parseDouble(campoNota.getText());
            if (nota < 0 || nota > 10) throw new NumberFormatException();
            alumno.setNota(asignatura, nota);
            actualizarResumen();
            JOptionPane.showMessageDialog(this, "Nota guardada correctamente");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduce una nota válida (0-10)");
        }
    }

    private void cargarNotaExistente() {
        Alumno alumno = (Alumno) comboAlumnos.getSelectedItem();
        String asignatura = (String) comboAsignaturas.getSelectedItem();
        Double nota = alumno.getNota(asignatura);
        campoNota.setText(nota != null ? nota.toString() : "");
    }

    private void validarNota() {
        String texto = campoNota.getText();
        try {
            double nota = Double.parseDouble(texto);
            if (nota < 0 || nota > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            campoNota.setText("");
            JOptionPane.showMessageDialog(this, "La nota debe estar entre 0 y 10.");
        }
    }

    private void actualizarResumen() {
        Alumno alumno = (Alumno) comboAlumnos.getSelectedItem();
        areaResumen.setText(alumno != null ? alumno.resumenNotas() : "");
    }

    // MODELO: Alumno
    public static class Alumno {
        private String nombre;
        private Map<String, Double> notas;

        public Alumno(String nombre) {
            this.nombre = nombre;
            this.notas = new HashMap<>();
        }

        public void setNota(String asignatura, double nota) {
            notas.put(asignatura, nota);
        }

        public Double getNota(String asignatura) {
            return notas.get(asignatura);
        }

        public String resumenNotas() {
            StringBuilder sb = new StringBuilder("Notas de " + nombre + ":\n");
            if (notas.isEmpty()) {
                sb.append("  (Sin notas todavía)\n");
            } else {
                for (var entry : notas.entrySet()) {
                    sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
            return sb.toString();
        }

        public String toString() {
            return nombre;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotasApp().setVisible(true));
    }
}
