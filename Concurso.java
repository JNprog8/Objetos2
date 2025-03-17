import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Concurso {
    private String nombre;
    private ArrayList<Inscripcion> inscripciones;
    private LocalDateTime fechaConcurso;
    private LocalDateTime fechaInicioInscripcion;
    private LocalDateTime fechaFinInscripcion;

    public Concurso(String nombre,
                    LocalDateTime fechaConcurso,
                    LocalDateTime fechaInicioInscripcion,
                    LocalDateTime fechaFinInscripcion) {

        // Valido que los parametros no sean nulos
        validarParametros(nombre, fechaConcurso, fechaInicioInscripcion, fechaFinInscripcion);

        // Valido la logica en las fechas
        validarFechas(fechaInicioInscripcion, fechaFinInscripcion, fechaConcurso);

        // Si pasa las validaciones, se crea un concurso
        this.nombre = nombre;
        this.fechaConcurso = fechaConcurso;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.inscripciones = new ArrayList<>();
    }

    // Registra la inscripción de un participante en este concurso
    public void registrarInscripcion(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("El participante no puede ser nulo");
        }

        LocalDateTime ahora = LocalDateTime.now();

        // Validar que la inscripción se realice dentro del período válido
        if (ahora.isBefore(fechaInicioInscripcion) || ahora.isAfter(fechaFinInscripcion)) {
            throw new IllegalStateException("La inscripción solo está permitida entre " +
                    fechaInicioInscripcion + " y " + fechaFinInscripcion + ".");
        }

        // Verificar si el participante ya está inscrito
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getParticipante().equals(participante)) {
                throw new IllegalStateException("El participante ya está inscrito en este concurso");
            }
        }

        // Crear nueva inscripción
        Inscripcion nuevaInscripcion = new Inscripcion(participante, this, ahora);
        inscripciones.add(nuevaInscripcion);

        // Dar bonificación si se inscribe el primer día
        if (ahora.toLocalDate().isEqual(fechaInicioInscripcion.toLocalDate())) {
            participante.sumarPuntos(10);
        }
    }

    // Metodo para validar que los parámetros ingresados en constructor no sean nulos
    private void validarParametros(String nombre,
                                   LocalDateTime fechaConcurso,
                                   LocalDateTime fechaInicioInscripcion,
                                   LocalDateTime fechaFinInscripcion) {
        if (nombre == null || fechaConcurso == null ||
                fechaInicioInscripcion == null || fechaFinInscripcion == null) {
            throw new IllegalArgumentException("Ningún parámetro puede ser nulo");
        }
    }

    private void validarFechas(LocalDateTime fechaInicioInscripcion,
                               LocalDateTime fechaFinInscripcion,
                               LocalDateTime fechaConcurso) {

        if (fechaInicioInscripcion.isAfter(fechaFinInscripcion) || fechaFinInscripcion.isAfter(fechaConcurso)) {
            throw new IllegalArgumentException("La fecha del concurso se realiza después del periodo de inscripción" +
                    "\n inicio inscripción <= fin inscripción <= fecha concurso");
        }
    }

    public int cantidadInscriptos() {
        return this.inscripciones.size();
    }

    public ArrayList<Participante> getParticipantesInscritos() {
        ArrayList<Participante> participantes = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            participantes.add(inscripcion.getParticipante());
        }
        return participantes;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public LocalDateTime getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public LocalDateTime getFechaConcurso() {
        return fechaConcurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concurso concurso = (Concurso) o;
        return Objects.equals(nombre, concurso.nombre) &&
                Objects.equals(fechaConcurso, concurso.fechaConcurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaConcurso);
    }

    @Override
    public String toString() {
        return "Concurso{" +
                "nombre='" + nombre + '\'' +
                ", fechaConcurso=" + fechaConcurso +
                ", fechaInicioInscripcion=" + fechaInicioInscripcion +
                ", fechaFinInscripcion=" + fechaFinInscripcion +
                ", cantidadInscriptos=" + inscripciones.size() +
                '}';
    }
}