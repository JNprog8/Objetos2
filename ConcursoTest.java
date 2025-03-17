import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConcursoTest {
    private Participante participante;
    private Concurso concurso;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime fechaConcurso;

    @BeforeEach
    void setUp() {
        fechaInicio = LocalDateTime.now().minusDays(1);
        fechaFin = LocalDateTime.now().plusDays(5);
        fechaConcurso = LocalDateTime.now().plusDays(10);
        concurso = new Concurso("Torneo de Matemática", fechaConcurso, fechaInicio, fechaFin);
        participante = new Participante("12345678", "Juan Pérez");
    }

    @Test
    void participanteSeInscribeEnConcurso() {
        participante.inscribirseEnConcurso(concurso);
        assertTrue(participante.estaInscritoEnConcurso(concurso));
        assertEquals(1, concurso.cantidadInscriptos());
    }

    @Test
    void participanteSeInscribePrimerDiaYGanaPuntos() {
        LocalDateTime fechaExactaInicio = LocalDateTime.now();
        Concurso concursoEspecial = new Concurso("Torneo de Física", fechaConcurso, fechaExactaInicio, fechaFin);
        participante.inscribirseEnConcurso(concursoEspecial);
        assertEquals(10, participante.getPuntos());
    }

    @Test
    void participanteIntentaInscribirseFueraDeFecha() {
        LocalDateTime fechaInicioPasada = LocalDateTime.now().minusDays(10);
        LocalDateTime fechaFinPasada = LocalDateTime.now().minusDays(5);
        Concurso concursoCerrado = new Concurso("Torneo Cerrado", fechaConcurso, fechaInicioPasada, fechaFinPasada);

        IllegalStateException excepcion = assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() {
                participante.inscribirseEnConcurso(concursoCerrado);
            }
        });

        assertEquals("La inscripción solo está permitida entre " + fechaInicioPasada + " y " + fechaFinPasada + ".", excepcion.getMessage());
    }

    @Test
    void noSePuedeInscribirDosVecesAlMismoConcurso() {
        participante.inscribirseEnConcurso(concurso);
        IllegalStateException excepcion = assertThrows(IllegalStateException.class, () -> participante.inscribirseEnConcurso(concurso));
        assertEquals("Ya estás inscrito en este concurso", excepcion.getMessage());
    }
}
