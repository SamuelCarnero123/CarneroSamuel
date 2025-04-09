package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Alumno;
import Vista.Vista;

public class Controlador implements ActionListener {
	public Vista vista;

	public Controlador() {
		super();
		this.vista = new Vista(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void actualizarResumen() {
		Alumno alumno = (Alumno) vista.getComboAlumnos().getSelectedItem();
		vista.getAreaResumen().setText(alumno != null ? alumno.resumenNotas() : "");
	}

	public void guardarNota() {
		Alumno alumno = (Alumno) vista.getComboAlumnos().getSelectedItem();
		String asignatura = (String) vista.getComboAsignaturas().getSelectedItem();
		System.out.println(alumno+"---"+asignatura);
		try {
			double nota = Double.parseDouble(vista.getCampoNota().getText());
			System.out.println(nota);
			if (nota < 0 || nota > 10)
				throw new NumberFormatException();
			alumno.setNota(asignatura, nota);
			actualizarResumen();
			JOptionPane.showMessageDialog(vista, "Nota guardada correctamente");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "Introduce una nota válida (0-10)");
		}
	}

	public void cargarNotaExistente() {
		Alumno alumno = (Alumno) vista.getComboAlumnos().getSelectedItem();
		String asignatura = (String) vista.getComboAsignaturas().getSelectedItem();
		Double nota = alumno.getNota(asignatura);
		vista.getCampoNota().setText(nota != null ? nota.toString() : "");
	}

	public void validarNota() {
		String texto = vista.getCampoNota().getText();
		try {
			double nota = Double.parseDouble(texto);
			if (nota < 0 || nota > 10) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			vista.getCampoNota().setText("");
			JOptionPane.showMessageDialog(vista, "La nota debe estar entre 0 y 10.");
		}
	}

}
