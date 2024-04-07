package ejercicio1;

import persistenciaEjercicio1.EnBaseDatosRegistroInscripcion;
import persistenciaEjercicio1.EnDiscoRegistro;

import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        Participante jose1 = new Participante("234566", "Jose Perez", "jose.Perez@gmail.com");
        Participante pedro1 = new Participante("448899", "Pedro Perez", "Pedro.Perez@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        CorreoElectronico micuenta = new CorreoElectronico("fd5c2b624ae696", "78b2ddb2667cc9", "sandbox.smtp.mailtrap.io");
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L), new EnBaseDatosRegistroInscripcion("jdbc:mysql://localhost:3306/tp1_objetos2", "root", ""), micuenta, "concurso1@gmail.com", "Un Ejercicio1.Concurso", "Se ha registrado con exito");
        Concurso dosConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L), new EnDiscoRegistro("C:\\Users\\MAK\\Documents\\Orientacion a Objetos 2(2024)\\Todos los TP\\tp2Actualizado\\InscripcionConcurso.txt"), micuenta, "concurso2@gmail.com", "Un Ejercicio1.Concurso", "Se ha registrado con exito");
        ProveedorDeFechas proveedor = new ProveedorDeFechas() {
            public LocalDate fecha() {
                return LocalDate.now();
            }
        };
        Inscripcion.inscribirAEn(jose1, unConcurso, proveedor);
        Inscripcion.inscribirAEn(pedro1, dosConcurso, proveedor);
    }
}