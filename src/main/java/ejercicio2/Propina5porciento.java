package ejercicio2;

public class Propina5porciento extends Propina {
    public Propina5porciento() {
    }

    public Double calcularPropina(Double totalPedido) {
        return totalPedido * 0.05;
    }
}
