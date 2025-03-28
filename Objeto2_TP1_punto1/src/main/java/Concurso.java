import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private List<Participante> inscriptos;
    private String nombre;
    private LocalDateTime fechaConcurso;
    private LocalDateTime fechaInicioInscripcion;
    private LocalDateTime fechaFinInscripcion;

    public Concurso(String nombreConcurso,
                    LocalDateTime fechaConcurso,
                    LocalDateTime fechaInicioInscripcion,
                    LocalDateTime fechaFinInscripcion) {
        // Valida los campos del constructor de concurso
        Validar.camposConcurso(nombreConcurso, fechaConcurso, fechaInicioInscripcion, fechaFinInscripcion);

        this.nombre = nombreConcurso;
        this.fechaConcurso = fechaConcurso;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.inscriptos = new ArrayList<>();
    }

    public void agregarParticipante(Participante participante) {
        // un participante se inscribe en el momento, esto es: 'hoy'
        LocalDateTime hoy = LocalDateTime.now();

        // validar periodo de inscripcion
        if (hoy.isBefore(fechaInicioInscripcion)) {
            throw new IllegalStateException("La inscripción aún no ha comenzado.");
        }
        if (hoy.isAfter(fechaFinInscripcion)) {
            throw new IllegalStateException("La inscripción ya ha finalizado.");
        }

        // validar que no este ya inscrito
        if (!estaParticipante(participante)) {
            this.inscriptos.add(participante);
            participante.inscribirEnConcurso(this);
            // dar 10 puntos por inscripción en el primer dia
            if (hoy.toLocalDate().isEqual(fechaInicioInscripcion.toLocalDate())) {
                participante.sumarPuntos(10);
                System.out.println("Felicidades " + participante.obtenerNombre() + ", has ganado 10 puntos.");
            }

            System.out.println("Inscripción exitosa.");
        } else {
            throw new IllegalStateException("El participante ya está inscrito.");
        }
    }

//    public void mostrarInscriptos() {
//        inscriptos.forEach(System.out::println);
//    }

    public int cantidadInscriptos() {
        return inscriptos.size();
    }

    public boolean estaParticipante(Participante participante) {
        return inscriptos.contains(participante);
    }
}