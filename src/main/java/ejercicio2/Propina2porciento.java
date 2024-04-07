package ejercicio2;

public class Propina2porciento extends Propina {
    public Propina2porciento() {
    }

    public Double calcularPropina(Double totalPedido) {
        return totalPedido * 0.02;
    }
}
