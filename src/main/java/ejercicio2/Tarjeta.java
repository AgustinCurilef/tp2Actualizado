package ejercicio2;

public class Tarjeta {
    private final String nombreTarjeta;
    private final String titular;
    private final int codigo;
    private Double descuento;

    public Tarjeta(String nombreTarjeta, String titular, int codigo) {
        this.nombreTarjeta = nombreTarjeta;
        this.titular = titular;
        this.codigo = codigo;
        this.descuento = 0.0;
    }

    public void realizarPago(Pedido pedido, Propina tipoPropina) {
        pedido.cancelarCuenta(pedido.calcularTotal(this), tipoPropina.calcularPropina(pedido.calcularTotal(this)));
    }

    public double aplicarDescuento(Pedido pedido) {
        return this.descuento;
    }
}