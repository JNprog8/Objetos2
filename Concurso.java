package Punto1;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Concurso {
    private String nombre;
    private ArrayList<Participante> inscriptos;
    private LocalDateTime fechaConcurso;
    private LocalDateTime fechaInicioInscripcion;
    private LocalDateTime fechaFinInscripcion;

    public Concurso(String nombreConcurso,
                    LocalDateTime fechaRealizacionConcurso,
                    LocalDateTime fechaInicioInscripciones,
                    LocalDateTime fechaFinalInscripciones) {

        if (nombreConcurso == null || nombreConcurso.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del concurso no puede estar vacío");
        }
        if (fechaInicioInscripciones.isAfter(fechaFinalInscripciones)) {
            throw new IllegalArgumentException("La fecha de inicio de inscripción debe ser anterior a la fecha final");
        }
        if (fechaFinalInscripciones.isAfter(fechaRealizacionConcurso)) {
            throw new IllegalArgumentException("La fecha final de inscripción debe ser anterior a la fecha del concurso");
        }

        this.nombre = nombreConcurso;
        this.fechaConcurso = fechaRealizacionConcurso;
        this.fechaInicioInscripcion = fechaInicioInscripciones;
        this.fechaFinInscripcion = fechaFinalInscripciones;
        this.inscriptos = new ArrayList<>();
    }

    public void inscribirParticipante(Participante participante) {
        try {
            // Verifica que el participante no sea nulo
            if (participante == null) {
                throw new IllegalArgumentException("El participante no puede ser nulo");
            }

            // Verificar que el participante no esté ya inscrito
            if (this.inscriptos.contains(participante)) {
                throw new RuntimeException("El participante ya está inscrito en este concurso");
            }

            LocalDateTime ahora = LocalDateTime.now();

            // Verificar que la inscripción esté dentro del período válido
            if (ahora.isBefore(this.fechaInicioInscripcion)) {
                throw new RuntimeException("La inscripción está fuera del período válido: aún no ha comenzado el período de inscripción");
            }

            if (ahora.isAfter(this.fechaFinInscripcion)) {
                throw new RuntimeException("La inscripción está fuera del período válido: ya ha finalizado el período de inscripción");
            }

            // Sumar puntos si se inscribe el primer día
            if (ahora.toLocalDate().equals(this.fechaInicioInscripcion.toLocalDate())) {
                participante.sumarPuntos(10);
                System.out.println("¡Felicidades " + participante.getNombre() + "! Has ganado 10 puntos por inscribirte el primer día");
            }

            // Agregar participante al concurso
            this.inscriptos.add(participante);
            // y concurso al participante
            participante.agregarConcurso(this);

            System.out.println("Inscripción realizada con éxito");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public LocalDateTime getFechaInicio() {
        return this.fechaInicioInscripcion;
    }

    public LocalDateTime getFechaFinal() {
        return this.fechaFinInscripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCantidadInscriptos() {
        return this.inscriptos.size();
    }

    public ArrayList<Participante> getInscriptos() {
        return new ArrayList<>(this.inscriptos);
    }

    @Override
    public String toString() {
        return "Concurso{" +
                "nombre='" + nombre + '\'' +
                ", fechaConcurso=" + fechaConcurso +
                ", participantes=" + inscriptos.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Concurso otro = (Concurso) obj;
        return nombre.equals(otro.nombre) && fechaConcurso.equals(otro.fechaConcurso);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode() + fechaConcurso.hashCode();
    }
}