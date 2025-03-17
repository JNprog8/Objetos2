import java.util.ArrayList;
import java.util.Objects;

public class Participante {
    private String dni;
    private String nombre;
    private int puntos;
    private ArrayList<Concurso> concursosInscritos;

    public Participante(String dni, String nombre) {
        // Validar dni
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o estar vacío");
        }
        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacío");
        }

        this.dni = dni;
        this.nombre = nombre;
        this.puntos = 0;
        this.concursosInscritos = new ArrayList<>();
    }

    public void inscribirseEnConcurso(Concurso concurso) {
        if (concurso == null) {
            throw new IllegalArgumentException("El concurso no puede ser nulo");
        }

        if (estaInscritoEnConcurso(concurso)) {
            throw new IllegalStateException("Ya estás inscrito en este concurso");
        }

        concurso.registrarInscripcion(this);
        concursosInscritos.add(concurso);
    }

    public boolean estaInscritoEnConcurso(Concurso concurso) {
        return concursosInscritos.contains(concurso);
    }

    public void sumarPuntos(int puntos) {
        if (puntos <= 0) {
            throw new IllegalArgumentException("No se pudo sumar puntos. Ingrese un número mayor que cero");
        }
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public ArrayList<Concurso> getConcursosInscritos() {
        return new ArrayList<>(concursosInscritos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Participante{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}