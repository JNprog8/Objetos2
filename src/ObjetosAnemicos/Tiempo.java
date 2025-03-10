package ObjetosAnemicos;

import java.time.LocalDate;

public class Tiempo {
    private LocalDate tiempo;

    // Constructor
    public Tiempo(){
        this.tiempo = LocalDate.now();
    }

    // Obtener tiempo
    public LocalDate getTiempo() {
        return tiempo;
    }
}
