package ObjetosAnemicos;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {
    public static void main(String[] args) {
        Tiempo tiempo = new Tiempo();

        System.out.println(tiempo.getTiempo().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println(tiempo.getTiempo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}