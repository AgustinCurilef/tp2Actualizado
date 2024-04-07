package ejercicio1;

import java.util.Objects;

public class Participante {
    private final String dni;
    private final String nombre;
    private String miCorreo;
    private int puntos;

    public Participante(String dni, String nombre, String correoElectronico) {
        this.dni = dni;
        this.nombre = nombre;
        this.miCorreo = correoElectronico;
        this.puntos = 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Participante) {
            Participante that = (Participante) o;
            return Objects.equals(this.dni, that.dni);
        } else {
            return false;
        }
    }

    public void sumarPuntos(int puntosAsumar) {
        this.puntos += puntosAsumar;
    }

    public int obtenerPuntos() {
        return this.puntos;
    }

    public String obtenerDNI() {
        return this.dni;
    }

    public String obtenerCorreoElectronico() {
        return this.miCorreo;
    }
}
