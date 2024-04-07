package ejercicio2;

public class Propina3porciento extends Propina {
    public Propina3porciento() {
    }

    public Double calcularPropina(Double totalPedido) {
        return totalPedido * 0.03;
    }
}
