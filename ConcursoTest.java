package Punto1_Test;

import Punto1.Concurso;
import Punto1.Participante;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class ConcursoTest {

    @Test
    public void testInscribirParticipanteEnConcurso() {
        //set up (inicializacion de variables)
        LocalDateTime fechaConcurso = LocalDateTime.now().plusDays(10);
        LocalDateTime fechaInicioInscripcion = LocalDateTime.now().minusDays(1);
        LocalDateTime fechaFinInscripcion = LocalDateTime.now().plusDays(5);

        Concurso c1 = new Concurso("Concurso de Programacion", fechaConcurso, fechaInicioInscripcion, fechaFinInscripcion);

        Participante p1 = new Participante("Jose Azulado", "44444444");

        //exercise (Ejercitacion: Que Un participante se inscribe en un concurso)
        c1.inscribirParticipante(p1);

        //verify (Verificacion: si un participante esta inscripto al concurso entonces se cumplen los siguientes assert )
        assertTrue(p1.estaInscriptoEn(c1)); // si un participante esta en un concurso la salida debe ser True -> el assert debe dar true
        assertEquals(1, c1.getCantidadInscriptos()); // La cantidad de inscriptos en el concurso deberia ser de 1 -> el assert debe dar true
        assertTrue(c1.getInscriptos().contains(p1)); // el concurso contiene al participante que se acaba de inscribir -> el assert debe dar true
    }

    @Test
    public void testInscripcionPrimerDia() {
        //set up
        // tambien funcionaria con 'LocalDateTime hoy = LocalDateTime.now();' y 'fechaInicioInscripcion = hoy'
        LocalDateTime fechaInicioInscripcion = LocalDateTime.now(); // Hoy es el primer día
        LocalDateTime fechaFinInscripcion = LocalDateTime.now().plusDays(5);
        LocalDateTime fechaConcurso = LocalDateTime.now().plusDays(10);

        Concurso c2 = new Concurso("Concurso de Cocina",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion);

        Participante p2 = new Participante("Rosa Millones", "22222222");

        //exercise (Ejercitacion:Un participante se inscribe en un concurso el primer día de abierta la inscripcion)
        c2.inscribirParticipante(p2);

        //verify (Verificación: Se deben verificar los siguientes assert)
        assertEquals(10, p2.getPuntos());
        assertTrue(p2.estaInscriptoEn(c2));
    }

    @Test
    public void testInscripcionPreviaAlPeriodoInscripcion() {
        //set up
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaInicioInscripcion = ahora.plusDays(1); // La inscripcion comienza mañana
        LocalDateTime fechaFinInscripcion = ahora.plusDays(5);
        LocalDateTime fechaConcurso = ahora.plusDays(10);

        Concurso c3 = new Concurso("Concurso de Fotografia",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion);

        Participante p3 = new Participante("Elton Tito", "33333333");
        
        //exercise (Ejercitacion: Un participante intenta inscribirse ANTES del rango de inscripción)
        c3.inscribirParticipante(p3);

        //verify (Verificacion: Se deben verificar los siguientes assert)
        assertEquals(0, c3.getCantidadInscriptos());
        assertFalse(p3.estaInscriptoEn(c3));
    }

    @Test
    public void testInscripcionPosteriorAlPeriodoInscripcion() {
        //set up
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaInicioInscripcion = ahora.minusDays(10);
        LocalDateTime fechaFinInscripcion = ahora.minusDays(1); // Inscripción terminó ayer
        LocalDateTime fechaConcurso = ahora.plusDays(5);

        Concurso c4 = new Concurso("Concurso de Cine",
                fechaConcurso,
                fechaInicioInscripcion,
                fechaFinInscripcion);

        Participante p4 = new Participante("Mario Neta", "24242424");

        //exercise (Ejercitacion: Un participante intenta inscribirse DESPUES del rango de inscripción)
        c4.inscribirParticipante(p4);

        //verify (Verificacion: Se deben verificar los siguientes assert)
        assertEquals(0, c4.getCantidadInscriptos());
        assertFalse(p4.estaInscriptoEn(c4));
    }
}