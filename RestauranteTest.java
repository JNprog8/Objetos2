package Punto2_Test;

import Punto2.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestauranteTest {

    private Restaurante restaurante;
    private Mesa mesa;
    private Bebida aguaMineral;
    private Bebida gaseosa;
    private Comida milanesaPapas;
    private Comida pasta;

    public void setUp() {
        // Inicializar el restaurante
        restaurante = new Restaurante();

        // Crear mesas
        mesa = new Mesa(1, 4);
        restaurante.agregarMesa(mesa);

        // Crear menú
        aguaMineral = new Bebida("Agua Mineral", 2000);
        gaseosa = new Bebida("Gaseosa", 4000);
        milanesaPapas = new Comida("Milanesa con papas", 12000);
        pasta = new Comida("Pasta", 10000);

        // Agregar productos al menú del restaurante
        restaurante.agregarMenu(aguaMineral);
        restaurante.agregarMenu(gaseosa);
        restaurante.agregarMenu(milanesaPapas);
        restaurante.agregarMenu(pasta);
    }

    // Metodo auxiliar para configurar un pedido
    private void configurarPedido(TarjetaCredito tarjeta, Propina propina) {
        mesa.ocupar();
        mesa.tomarPedido();
        mesa.agregarAlPedido(aguaMineral);
        mesa.agregarAlPedido(gaseosa);
        mesa.agregarAlPedido(milanesaPapas);
        mesa.agregarAlPedido(pasta);
        mesa.confirmarPedido(tarjeta, propina);
    }

    @Test
    public void testCalculoCostoTarjetaVisa() {
        // Caso 1: Cálculo de costo con tarjeta Visa
        // Visa: 3% de descuento sobre bebidas

        TarjetaCredito visa = new Visa();
        configurarPedido(visa, Propina.TRES_PORCIENTO);

        Pedido pedido = mesa.obtenerPedido();

        // Cálculos esperados:
        // Subtotal: Agua (200) + Gaseosa (300) + Milanesa (1200) + Pasta (1000) = 2700
        // Descuento Visa (3% sobre bebidas): (200 + 300) * 0.03 = 15
        // Subtotal con descuento: 2700 - 15 = 2685
        // Propina (3%): 2685 * 0.03 = 80.55
        // Total: 2685 + 80.55 = 2765.55

        double costoTotal = pedido.calcularCostoTotal();
        assertEquals(2765.55, costoTotal, 0.01);

        // Liberar la mesa para el siguiente test
        mesa.liberar();
    }

    @Test
    public void testCalculoCostoTarjetaMastercard() {
        // Caso 2: Cálculo de costo con tarjeta Mastercard
        // Mastercard: 2% de descuento sobre platos principales

        TarjetaCredito mastercard = new Mastercard();
        configurarPedido(mastercard, Propina.DOS_PORCIENTO);

        Pedido pedido = mesa.obtenerPedido();

        // Cálculos esperados:
        // Subtotal: Agua (200) + Gaseosa (300) + Milanesa (1200) + Pasta (1000) = 2700
        // Descuento Mastercard (2% sobre comidas): (1200 + 1000) * 0.02 = 44
        // Subtotal con descuento: 2700 - 44 = 2656
        // Propina (2%): 2656 * 0.02 = 53.12
        // Total: 2656 + 53.12 = 2709.12

        double costoTotal = pedido.calcularCostoTotal();
        assertEquals(2709.12, costoTotal, 0.01);

        // Liberar la mesa para el siguiente test
        mesa.liberar();
    }

    @Test
    public void testCalculoCostoTarjetaComarcaPlus() {
        // Caso 3: Cálculo de costo con tarjeta Comarca Plus
        // Comarca Plus: 2% de descuento sobre el total (bebidas + platos)

        TarjetaCredito comarcaPlus = new ComarcaPlus();
        configurarPedido(comarcaPlus, Propina.CINCO_PORCIENTO);

        Pedido pedido = mesa.obtenerPedido();

        // Cálculos esperados:
        // Subtotal: Agua (200) + Gaseosa (300) + Milanesa (1200) + Pasta (1000) = 2700
        // Descuento Comarca Plus (2% sobre total): 2700 * 0.02 = 54
        // Subtotal con descuento: 2700 - 54 = 2646
        // Propina (5%): 2646 * 0.05 = 132.3
        // Total: 2646 + 132.3 = 2778.3

        double costoTotal = pedido.calcularCostoTotal();
        assertEquals(2778.3, costoTotal, 0.01);

        // Liberar la mesa para el siguiente test
        mesa.liberar();
    }

    @Test
    public void testCalculoCostoTarjetaViedma() {
        // Caso 4: Cálculo de costo con tarjeta Viedma
        // Otras tarjetas: Sin descuento

        TarjetaCredito viedma = new OtraTarjeta("Viedma");
        configurarPedido(viedma, Propina.DOS_PORCIENTO);

        Pedido pedido = mesa.obtenerPedido();

        // Cálculos esperados:
        // Subtotal: Agua (200) + Gaseosa (300) + Milanesa (1200) + Pasta (1000) = 2700
        // Descuento Viedma (0%): 0
        // Subtotal con descuento: 2700 - 0 = 2700
        // Propina (2%): 2700 * 0.02 = 54
        // Total: 2700 + 54 = 2754

        double costoTotal = pedido.calcularCostoTotal();
        assertEquals(2754.0, costoTotal, 0.01);

        // Liberar la mesa
        mesa.liberar();
    }
}
