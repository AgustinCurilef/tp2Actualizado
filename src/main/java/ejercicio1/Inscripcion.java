package ejercicio1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inscripcion {
    private static final int contadorInscriptos = 0;
    private static int puntosAsumar = 10;
    private final Participante participante;
    private final Concurso concurso;
    private final LocalDate fechaInscripcion;

    private Inscripcion(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public static void inscribirAEn(Participante unParticipante, Concurso unConcurso, ProveedorDeFechas proveedor) throws RuntimeException {
        if (!unConcurso.puedeInscribirse(LocalDate.now())) {
            throw new RuntimeException("Lo sentimos, el período de inscripción ha expirado. Ya no se pueden aceptar inscripciones.");
        }
        Inscripcion nuevaInscripcion = new Inscripcion(unParticipante, unConcurso, proveedor.fecha());
        unConcurso.nuevaInscripcion(nuevaInscripcion);
        if (unConcurso.primerDiaInscripcion(LocalDate.now())) {
            unParticipante.sumarPuntos(puntosAsumar);
        }

    }

    public boolean estaInscripto(Participante participante) {
        return this.participante.equals(participante);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = this.fechaInscripcion.format(formatter);
        return fechaFormateada + "," + this.participante.obtenerDNI() + "," + this.concurso.obtenerIdConcurso() + "\n";
    }

    public String obtenerCorreo() {
        return this.participante.obtenerCorreoElectronico();
    }
}
