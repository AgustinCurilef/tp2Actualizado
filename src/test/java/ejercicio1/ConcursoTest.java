package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConcursoTest {
    FakeRegistro registroFake = new FakeRegistro();
    FakeNotificacion notificacionFake = new FakeNotificacion();
    ProveedorDeFechas fechaFake = new ProveedorDeFechas() {
        @Override
        public LocalDate fecha() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.of(2022, 12, 18);
        }
    };

    public ConcursoTest() {
    }

    @Test
    public void test01() {
        Participante jose = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        Inscripcion.inscribirAEn(jose, unConcurso, fechaFake);
        Assertions.assertTrue(unConcurso.participanteInscripto(jose));
        Assertions.assertEquals(1, unConcurso.cantidadInscriptos());
        Assertions.assertEquals(10, jose.obtenerPuntos());
    }

    @Test
    public void test02() {
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");

        Assertions.assertEquals(0, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test03() {
        Participante jose = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");

        Assertions.assertFalse(unConcurso.participanteInscripto(jose));
    }

    @Test
    public void test04() {
        Participante jose1 = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        Participante jose2 = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        Inscripcion.inscribirAEn(jose1, unConcurso, fechaFake);
        Assertions.assertTrue(unConcurso.participanteInscripto(jose2));
        Assertions.assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test05() {
        Participante jose = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        Participante jorge = new Participante("698712", "Jorge Saldivar", "Jprge@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        Inscripcion.inscribirAEn(jose, unConcurso, fechaFake);
        Inscripcion.inscribirAEn(jorge, unConcurso, fechaFake);
        Assertions.assertTrue(unConcurso.participanteInscripto(jorge));
        Assertions.assertEquals(2, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test06() {
        Participante jose = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.of(2020, 1, 1);
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        try {
            Inscripcion.inscribirAEn(jose, unConcurso, fechaFake);
            Assertions.fail("Se esperaba una RuntimeException pero no se lanzó ninguna.");
        } catch (RuntimeException e) {
            Assertions.assertEquals("Lo sentimos, el período de inscripción ha expirado. Ya no se pueden aceptar inscripciones.", e.getMessage());
        }
        Assertions.assertFalse(unConcurso.participanteInscripto(jose));
        Assertions.assertEquals(0, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test07() {
        Participante jose1 = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        Concurso dosConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso2@gmail.com", "Concurso2", "Se ha registrado con exito!!");
        Inscripcion.inscribirAEn(jose1, unConcurso, fechaFake);
        Inscripcion.inscribirAEn(jose1, dosConcurso, fechaFake);
        Assertions.assertTrue(unConcurso.participanteInscripto(jose1));
        Assertions.assertTrue(dosConcurso.participanteInscripto(jose1));
        Assertions.assertEquals(20, jose1.obtenerPuntos());
    }

    @Test
    public void TestFakes() {
        Participante jose1 = new Participante("234566", "Jose Perez", "Jose@gmail.com");
        LocalDate fechaLimite = LocalDate.now();
        Concurso unConcurso = new Concurso("Un Ejercicio1.Concurso", fechaLimite.plusDays(3L)
                , registroFake, notificacionFake, "Concurso1@gmail.com", "Concurso1", "Se ha registrado con exito!!");
        Inscripcion.inscribirAEn(jose1, unConcurso, fechaFake);
        String registro = "18/12/2022" + "," + jose1.obtenerDNI() + "," + unConcurso.obtenerIdConcurso();
        Assertions.assertTrue(registroFake.StartsWich(registro));
        Assertions.assertTrue(notificacionFake.startsWich("Jose@gmail.com", "Concurso1@gmail.com"
                , "Concurso1", "Se ha registrado con exito!!"));
    }
}