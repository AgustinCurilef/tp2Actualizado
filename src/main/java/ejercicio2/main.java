package ejercicio2;

import ejercicio1.CorreoElectronico;
import ejercicio1.ProveedorDeFechas;
import persistenciaEjercicio1.EnBaseDatosRegistroRestaurante;
import persistenciaEjercicio1.EnDiscoRegistro;

import java.time.LocalDate;

public class main {
    public main() {
    }

    public static void main(String[] args) {
        PlatoPrincipal Milanesa = new PlatoPrincipal("Milanesa", 30.0);
        Bebida gaseosa = new Bebida("Pepsi", 15.0);
        Mesa mesa1 = new Mesa(1, 5, "cliente1@gmail.com");
        Mesa mesa2 = new Mesa(1, 5, "cliente2@gmail.com");
        CorreoElectronico micuenta = new CorreoElectronico("fd5c2b624ae696", "78b2ddb2667cc9", "sandbox.smtp.mailtrap.io");
        Pedido pedido1 = new Pedido(mesa1, new EnBaseDatosRegistroRestaurante("jdbc:mysql://localhost:3306/tp1_objetos2", "root", ""), micuenta, "Restaurante1@gmail.com", "Un Ejercicio1.Restaurante", "Gracias por su compra, vuelva pronto!", new ProveedorDeFechas() {
            public LocalDate fecha() {
                return LocalDate.now();
            }
        });
        Pedido pedido2 = new Pedido(mesa2, new EnDiscoRegistro("C:\\Users\\MAK\\Documents\\Orientacion a Objetos 2(2024)\\Todos los TP\\tp2Actualizado\\TotalPedidos.txt"), micuenta, "Restaurante1@gmail.com", "Un Ejercicio1.Restaurante", "Gracias por su compra, vuelva pronto!", new ProveedorDeFechas() {
            public LocalDate fecha() {
                return LocalDate.now();
            }
        });
        mesa1.realizarPedido(pedido1, gaseosa);
        mesa1.realizarPedido(pedido1, Milanesa);
        mesa2.realizarPedido(pedido2, Milanesa);
        Tarjeta miTarjeta = new Tarjeta("Viedma", "Agustin", 123456);
        Propina miPropina = new Propina5porciento();
        mesa1.pagarCuenta(pedido1, miTarjeta, miPropina);
        mesa2.pagarCuenta(pedido2, miTarjeta, miPropina);
    }
}
