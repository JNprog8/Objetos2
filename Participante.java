package Punto1;

import java.util.ArrayList;

public class Participante {
    private int puntos;
    private String nombre;
    private String dni;
    private ArrayList<Concurso> concursosInscriptos;

    public Participante(String nombre, String dni) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }
        if (dni == null || dni.trim().isEmpty()) {
            throw new RuntimeException("El DNI no puede estar vacío");
        }

        this.nombre = nombre;
        this.dni = dni;
        this.puntos = 0;
        this.concursosInscriptos = new ArrayList<>();
    }

    public void verPuntos() {
        System.out.println("Puntos: " + this.puntos);
    }

    public int getPuntos() {
        return this.puntos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public void sumarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntos += puntos;
        }
    }

    public void agregarConcurso(Concurso concurso) {
        if (!this.concursosInscriptos.contains(concurso)) {
            this.concursosInscriptos.add(concurso);
        }
    }

    public boolean estaInscriptoEn(Concurso concurso) {
        return this.concursosInscriptos.contains(concurso);
    }

    public int getCantidadConcursosInscriptos() {
        return this.concursosInscriptos.size();
    }

    @Override
    public String toString() {
        return "Participante{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Participante otro = (Participante) obj;
        return dni.equals(otro.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }
}