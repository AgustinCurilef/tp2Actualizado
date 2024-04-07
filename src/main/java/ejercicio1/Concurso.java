package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private static int contadorConcursos = 0;
    private final String nombre;
    private final LocalDate fechaLimite;
    private final String idConcurso;
    private final List<Inscripcion> inscriptos;
    private final LocalDate fechaInicio;
    private final Registro registro;
    private String listaInscriptos;
    private Notificacion miCuenta;
    private String remitente;
    private String asunto;
    private String mensaje;

    public Concurso(String nombre, LocalDate fechaLimite, Registro registro, Notificacion miCuenta, String remitente, String asunto, String mensaje) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.fechaInicio = LocalDate.now();
        this.inscriptos = new ArrayList();
        ++contadorConcursos;
        this.idConcurso = "" + contadorConcursos;
        this.registro = registro;
        this.miCuenta = miCuenta;
        this.remitente = remitente;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.stream().anyMatch((inscripcion) -> {
            return inscripcion.estaInscripto(participante);
        });
    }

    public void nuevaInscripcion(Inscripcion inscripcion) {
        this.inscriptos.add(inscripcion);
        this.registro.registrar(inscripcion.toString());
        this.miCuenta.enviarNotificacion(inscripcion.obtenerCorreo(), this.remitente, this.asunto, this.mensaje);
    }

    public boolean puedeInscribirse(LocalDate fechaInscripcion) {
        return (this.fechaInicio.isBefore(fechaInscripcion) || this.fechaInicio.isEqual(fechaInscripcion)) && (this.fechaLimite.isAfter(fechaInscripcion) || this.fechaLimite.isEqual(fechaInscripcion));
    }

    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }

    public boolean primerDiaInscripcion(LocalDate fechaIncripcion) {
        return fechaIncripcion.isEqual(this.fechaInicio);
    }

    public String obtenerIdConcurso() {
        return this.idConcurso;
    }
}

