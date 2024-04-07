package ejercicio2;

public class TarjetaMastercad extends Tarjeta {
    private Double descuento = 0.02;

    public TarjetaMastercad(String titular, int codigo) {
        super("Mastercad", titular, codigo);
    }

    public double aplicarDescuento(Pedido pedido) {
        return pedido.calcularPlatos() * this.descuento;
    }
}