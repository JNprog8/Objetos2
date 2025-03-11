Joaquin Neguelua
TP 0 - Objetos Anémicos

<< Las diferentes implementaciones (Anemica y no anémica) están segmentadas en su Packages ("ObjetosAnemicos" y "ObjetosFuertes") >>

Características de implementacion de un objeto anémico (ObjetosAnemicos):

i) La clase Tiempo carece de comportamiento. contiene un metodo getter y su constructor. No encapsula ninguna lógica de formateo dentro de su clase.
ii) La clase Main se encarga de toda la lógica de presentación, lo que genera un alto acoplamiento entre 'Main' y 'Tiempo'.
iii) Implementaciones anémicas implican un alto acoplamiento, que va contra los principios del Encapsulamiento.
iv) En consecuencia, si se quiere cambiar la forma de formatear 'Tiempo', habría que modificar todas las partes del código que lo usan en lugar de centralizar esa lógica en la clase 'Tiempo'.

Características de implementacion de un objeto no anémico (ObjetosFuertes):

i) El encapsulamiento de la lógica en la clase 'Tiempo' junto a la lógica de formateo incorporada a la clase, permite que la clase 'Tiempo' no se vea expuesta al resto de clases.
ii) La clase 'Main' solo se encarga de interactuar con 'Tiempo', sin preocuparse por su lógica interna (Por lo tanto hay menos acoplamiento). 
iii) Si se quiere cambiar la lógica de formateo, solo se modifica la clase 'Tiempo'. Esto favorece la reutilización y la escalabilidad del código (Mantenible) .
