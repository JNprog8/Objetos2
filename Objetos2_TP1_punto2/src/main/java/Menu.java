public class Menu {
    public static final String PRECIO_INVALIDO = "El precio no puede ser negativo";
    public static final String DESCRIPCION_VACIA = "La descripción no puede ser vacía";
    public static final String DESCRIPCION_LARGA = "La descripción no puede tener más de 150 caracteres";
    private String producto;
    private float precio;

    public Menu(String unProducto, float unPrecio) {
        validarProducto(unProducto);
        validarPrecio(unPrecio);
        this.producto = unProducto;
        this.precio = unPrecio;
    }

    public String obtenerNombreProducto() {
        return producto;
    }

    public float obtenerPrecio() {
        return precio;
    }

    private void validarProducto(String unProducto) {
        if (unProducto.isBlank()) {
            throw new RuntimeException(DESCRIPCION_VACIA);
        }

        if (unProducto.length() > 150) {
            throw new RuntimeException(DESCRIPCION_LARGA);
        }
    }

    private void validarPrecio(float precio) {
        if (precio < 0) {
            throw new RuntimeException(PRECIO_INVALIDO);
        }
    }
}