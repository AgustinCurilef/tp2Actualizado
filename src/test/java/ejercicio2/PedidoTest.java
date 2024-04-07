package ejercicio2;

import ejercicio1.FakeNotificacion;
import ejercicio1.FakeRegistro;
import ejercicio1.ProveedorDeFechas;
import excepcionesEjercicio2.MesaIncorrectaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PedidoTest {
    FakeRegistro registroFake = new FakeRegistro();
    FakeNotificacion notificacionFake = new FakeNotificacion();
    ProveedorDeFechas fechaFake = new ProveedorDeFechas() {
        @Override
        public LocalDate fecha() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.of(2022, 12, 18);
        }
    };

    public PedidoTest() {
    }


    @Test
    public void TestIncrementaPedido() {
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Mesa mesa2 = new Mesa(2, 8, "Mesa@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        Pedido pedido2 = new Pedido(mesa2, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        Assertions.assertEquals(1, pedido1.numeroPedido());
        Assertions.assertEquals(2, pedido2.numeroPedido());
    }

    @Test
    public void TestTomarPedido1() {
        Bebida agua = new Bebida("Agua", 10.0);
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        PlatoPrincipal papasFritas1 = new PlatoPrincipal("Papas Fritas", 17.0);
        PlatoPrincipal papasFritas2 = new PlatoPrincipal("Papas Fritas", 17.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, agua);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        mesa1.realizarPedido(pedido1, papasFritas1);
        mesa1.realizarPedido(pedido1, papasFritas2);
        Assertions.assertEquals(3, pedido1.cantidadPlatosPedidos());
        Assertions.assertEquals(2, pedido1.cantidadBebidasPedidas());
    }

    @Test
    public void TestTomarPedido2() {
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        Assertions.assertEquals(0, pedido1.cantidadPlatosPedidos());
        Assertions.assertEquals(0, pedido1.cantidadBebidasPedidas());
    }

    @Test
    public void TestTomarPedido3() {
        Bebida agua = new Bebida("Agua", 10.0);
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Mesa mesa2 = new Mesa(2, 8, "Mesa2@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        Pedido pedido2 = new Pedido(mesa2, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, agua);
        mesa1.realizarPedido(pedido1, agua);
        mesa1.realizarPedido(pedido1, Milanesa);
        mesa2.realizarPedido(pedido2, Milanesa);
        mesa2.realizarPedido(pedido2, gaseosa);
        Assertions.assertEquals(1, pedido1.cantidadPlatosPedidos());
        Assertions.assertEquals(2, pedido1.cantidadBebidasPedidas());
        Assertions.assertEquals(1, pedido2.cantidadPlatosPedidos());
        Assertions.assertEquals(1, pedido2.cantidadBebidasPedidas());
    }

    @Test
    public void TestTomarPedido4() {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Mesa mesa2 = new Mesa(2, 5, "Mesa2@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        Assertions.assertThrows(MesaIncorrectaException.class, () -> pedido1.tomarPedido(Milanesa, mesa2));
        Assertions.assertThrows(MesaIncorrectaException.class, () -> {
            mesa2.realizarPedido(pedido1, Milanesa);
        });
    }

    @Test
    public void TestDescuentoVisa() {
        Bebida agua = new Bebida("Agua", 10.0);
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, agua);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        TarjetaVisa miTarjeta = new TarjetaVisa("Agustin", 123456);
        Assertions.assertEquals(55.0, pedido1.obtenerFactura());
        Assertions.assertEquals(0.75, miTarjeta.aplicarDescuento(pedido1));
        Assertions.assertEquals(54.25, pedido1.calcularTotal(miTarjeta));
        Propina miPropina = new Propina2porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        Assertions.assertEquals(0.75, pedido1.obtenerFactura());
        Assertions.assertEquals(1.085, pedido1.obtenerPropina());
    }

    @Test
    public void TestDescuentoMastercard() {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        TarjetaMastercad miTarjeta = new TarjetaMastercad("Agustin", 123456);
        Assertions.assertEquals(45.0, pedido1.obtenerFactura());
        Assertions.assertEquals(0.6, miTarjeta.aplicarDescuento(pedido1));
        Assertions.assertEquals(44.4, pedido1.calcularTotal(miTarjeta));
        Propina miPropina = new Propina3porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        Assertions.assertEquals(0.6000000000000014, pedido1.obtenerFactura());
        Assertions.assertEquals(1.3319999999999999, pedido1.obtenerPropina());
    }

    @Test
    public void TestDescuentoComarcaPlus() {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        Tarjeta miTarjeta = new TarjetaComarcaPlus("Agustin", 123456);
        Assertions.assertEquals(45.0, pedido1.obtenerFactura());
        Assertions.assertEquals(0.9, miTarjeta.aplicarDescuento(pedido1));
        Assertions.assertEquals(44.1, pedido1.calcularTotal(miTarjeta));
        Propina miPropina = new Propina5porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        Assertions.assertEquals(0.8999999999999986, pedido1.obtenerFactura());
        Assertions.assertEquals(2.205, pedido1.obtenerPropina());
    }

    @Test
    public void TestDescuentoViedma() {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        Tarjeta miTarjeta = new Tarjeta("Viedma", "Agustin", 123456);
        Assertions.assertEquals(45.0, pedido1.obtenerFactura());
        Assertions.assertEquals(0.0, miTarjeta.aplicarDescuento(pedido1));
        Assertions.assertEquals(45.0, pedido1.calcularTotal(miTarjeta));
        Propina miPropina = new Propina5porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        Assertions.assertEquals(0.0, pedido1.obtenerFactura());
        Assertions.assertEquals(2.25, pedido1.obtenerPropina());
    }

    @Test
    public void TestFakes() {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "Mesa1@gmail.com");
        Pedido pedido1 = new Pedido(mesa1, registroFake, notificacionFake, "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra", fechaFake);
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        Tarjeta miTarjeta = new Tarjeta("Viedma", "Agustin", 123456);
        Propina miPropina = new Propina5porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        Assertions.assertTrue(registroFake.StartsWich("18/12/2022,47.25"));
        Assertions.assertTrue(notificacionFake.startsWich("Mesa1@gmail.com", "Restaurante1@gmail.com"
                , "Restaurante1", "Gracias Por su Compra"));

    }
}