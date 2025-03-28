import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void testInscribirParticipanteEnConcurso() {
        //set up (inicializacion de variables)
        var fechaConcurso = LocalDateTime.now().plusDays(10);
        var fechaInicioInscripcion = LocalDateTime.now().minusDays(1);
        var fechaFinInscripcion = LocalDateTime.now().plusDays(5);

        var c1 = new Concurso(
                "Concurso de Programación",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion
        );
        var p1 = new Participante("Jose Azulado",
                "44444444");

        //exercise (Ejercitacion: Que un participante se inscriba en un concurso)
        c1.agregarParticipante(p1);

        //verify (Verificacion)
        assertTrue(p1.estaInscritoEN(c1));
        assertEquals(1, c1.cantidadInscriptos());
    }

    @Test
    public void testInscripcionPrimerDia() {
        //set up
        var fechaInicioInscripcion = LocalDateTime.now(); // Hoy es el primer día
        var fechaFinInscripcion = LocalDateTime.now().plusDays(5);
        var fechaConcurso = LocalDateTime.now().plusDays(10);

        var c2 = new Concurso(
                "Concurso de Cocina",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion
        );
        var p2 = new Participante("Rosa Millones",
                "22222222");

        //exercise
        c2.agregarParticipante(p2);

        //verify
        assertEquals(10, p2.obtenerPuntos());
        assertTrue(p2.estaInscritoEN(c2));
    }

    @Test
    public void testInscripcionPreviaAlPeriodoInscripcion() {
        //set up
        var ahora = LocalDateTime.now();
        var fechaInicioInscripcion = ahora.plusDays(1); // La inscripcion comienza mañana
        var fechaFinInscripcion = ahora.plusDays(5);
        var fechaConcurso = ahora.plusDays(10);

        var c3 = new Concurso(
                "Concurso de Fotografía",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion
        );
        var p3 = new Participante("Elton Tito", "33333333");

        //exercise
        //assertThrows

        // verificar que la inscripción no se realizó
        assertEquals(0, c3.cantidadInscriptos());
        assertFalse(p3.estaInscritoEN(c3));
    }

    @Test
    public void testInscripcionPosteriorAlPeriodoInscripcion() {
        //set up
        var ahora = LocalDateTime.now();
        var fechaInicioInscripcion = ahora.minusDays(10);
        var fechaFinInscripcion = ahora.minusDays(1); // Inscripción terminó ayer
        var fechaConcurso = ahora.plusDays(5);

        var c4 = new Concurso(
                "Concurso de Cine",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion
        );
        var p4 = new Participante("Mario Neta", "24242424");

        //exercise
        //assertThrows

        // verificar que la inscripción no se realizó
        assertEquals(0, c4.cantidadInscriptos());
        assertFalse(p4.estaInscritoEN(c4));
    }
}
