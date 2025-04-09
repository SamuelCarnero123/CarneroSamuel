package Modelo;

import java.util.HashMap;
import java.util.Map;

// MODELO: Alumno
public class Alumno {
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
