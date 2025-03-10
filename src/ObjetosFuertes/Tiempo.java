package ObjetosFuertes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Tiempo {
    private LocalDate tiempo;

    // Constructor
    public Tiempo(){
        this.tiempo = LocalDate.now();
    }

    public void formatoLargo(){
        System.out.println("Fecha en formato largo: " + this.tiempo.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }

    public void formatoCorto(){
        System.out.println("Fecha en formato corto: " +this.tiempo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
