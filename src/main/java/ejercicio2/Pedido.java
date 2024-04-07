package ejercicio2;

import ejercicio1.Notificacion;
import ejercicio1.ProveedorDeFechas;
import ejercicio1.Registro;
import excepcionesEjercicio2.MesaIncorrectaException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Pedido {
    private static int contadorPedido = 0;
    private final int numero;
    private final Mesa mesaCliente;
    private final ArrayList<Bebida> bebidas;
    private final ArrayList<PlatoPrincipal> platos;
    private final Registro registro;
    private final Notificacion miCuenta;
    private final String remitente;
    private final String asunto;
    private final String mensaje;
    private Double factura;
    private Double propina;
    private ProveedorDeFechas proveedor;

    public Pedido(Mesa mesaCliente, Registro registro, Notificacion miCuenta, String remitente, String asunto, String mensaje, ProveedorDeFechas proveedor) {
        this.mesaCliente = mesaCliente;
        this.bebidas = new ArrayList();
        this.platos = new ArrayList();
        ++contadorPedido;
        this.numero = contadorPedido;
        this.factura = 0.0;
        this.propina = 0.0;
        this.registro = registro;
        this.miCuenta = miCuenta;
        this.remitente = remitente;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.proveedor = proveedor;
    }

    void tomarPedido(PlatoPrincipal unPlato, Mesa mesaPedido) throws MesaIncorrectaException {
        if (!this.chequearMesa(mesaPedido)) {
            throw new MesaIncorrectaException("Esta mesa no coincide con numero de mesa del pedido.");
        } else {
            this.platos.add(unPlato);
            this.factura = this.factura + unPlato.precio();
        }
    }

    void tomarPedido(Bebida unaBebida, Mesa mesaPedido) throws MesaIncorrectaException {
        if (!this.chequearMesa(mesaPedido)) {
            throw new MesaIncorrectaException("Esta mesa no coincide con numero de mesa del pedido.");
        } else {
            this.bebidas.add(unaBebida);
            this.factura = this.factura + unaBebida.precio();
        }
    }

    public Double calcularTotal(Tarjeta tarjeta) {
        double descuento = tarjeta.aplicarDescuento(this);
        return this.factura - descuento;
    }

    private void registar(double facturaFinal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String var10000 = this.proveedor.fecha().format(formatter);
        String fechaYtotal = var10000 + "," + facturaFinal + "\n";
        this.registro.registrar(fechaYtotal);
        this.miCuenta.enviarNotificacion(this.mesaCliente.obtenerCorreo(), this.remitente, this.asunto, this.mensaje);
    }

    Double calcularBebidas() {
        double totalBebidas = 0.0;

        Bebida bebida;
        for (Iterator<Bebida> var2 = this.bebidas.iterator(); var2.hasNext(); totalBebidas += bebida.precio()) {
            bebida = (Bebida) var2.next();
        }

        return totalBebidas;
    }

    Double calcularPlatos() {
        double totalPlatos = 0.0;

        PlatoPrincipal plato;
        for (Iterator<PlatoPrincipal> var2 = this.platos.iterator(); var2.hasNext(); totalPlatos += plato.precio()) {
            plato = (PlatoPrincipal) var2.next();
        }

        return totalPlatos;
    }

    void cancelarCuenta(Double pago, Double propina) {
        this.registar(pago + propina);
        this.factura = this.factura - pago;
        this.propina = propina;
    }

    public int numeroPedido() {
        return this.numero;
    }

    public int cantidadPlatosPedidos() {
        return this.platos.size();
    }

    public int cantidadBebidasPedidas() {
        return this.bebidas.size();
    }

    private boolean chequearMesa(Mesa mesaPedido) {
        return mesaPedido.equals(this.mesaCliente);
    }

    public Double obtenerFactura() {
        return this.factura;
    }

    public Double obtenerPropina() {
        return this.propina;
    }
}