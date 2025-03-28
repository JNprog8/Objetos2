import java.time.LocalDateTime;

public class Validar {
    // Método de validación genérico para objetos nulos
    public static void noNulo(Object objeto, String mensaje) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    // Método de validación para cadenas no vacías
    public static void strNoVacio(String cadena, String mensaje) {
        noNulo(cadena, mensaje);
        if (cadena.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    // Método de validación de fechas para concursos
    public static void fechas(LocalDateTime inicio, LocalDateTime fin, LocalDateTime concurso) {
        noNulo(inicio, "La fecha de inicio no puede ser nula.");
        noNulo(fin, "La fecha de fin no puede ser nula.");
        noNulo(concurso, "La fecha del concurso no puede ser nula.");

        if (inicio.isAfter(fin) || fin.isAfter(concurso)) {
            throw new IllegalArgumentException("Las fechas no cumplen con la secuencia requerida.");
        }
    }

    // Método de validación específico para Concurso
    public static void camposConcurso(String nombre,
                                      LocalDateTime fechaConcurso,
                                      LocalDateTime fechaInicio,
                                      LocalDateTime fechaFin) {
        strNoVacio(nombre, "El nombre del concurso no puede ser nulo o vacío.");
        fechas(fechaInicio, fechaFin, fechaConcurso);
    }

    // Método de validación específico para Participante
    public static void camposParticipante(String nombre, String dni) {
        strNoVacio(nombre, "El nombre del participante no puede ser nulo o vacío.");
        strNoVacio(dni, "El DNI del participante no puede ser nulo o vacío.");
    }
}