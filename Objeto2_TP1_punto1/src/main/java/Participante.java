import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante {
    private List<Concurso> misConcursos;
    private int puntos;
    private String nombre;
    private String dni;

    public Participante(String nombre, String dni) {
        // validacionesa
        Validar.camposParticipante(nombre, dni);

        this.nombre = nombre;
        this.dni = dni;
        this.puntos = 0;
        this.misConcursos = new ArrayList<>();
    }

//    public void verPuntos() {
//        System.out.println("Puntos: " + this.puntos);
//    }

    public int obtenerPuntos() {
        return this.puntos;
    }

    public void sumarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntos += puntos;
        }
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public void inscribirEnConcurso(Concurso concurso) {
        if (concurso != null &&
                this.misConcursos.stream().noneMatch(c -> c.equals(concurso))) {
            this.misConcursos.add(concurso);
        }
    }

    public boolean estaInscritoEN(Concurso concurso) {
        return concurso != null &&
                misConcursos.stream()
                        .anyMatch(c -> c.equals(concurso));
    }

//    public String obtenerDNI() {
//        return this.dni;
//    }

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
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}