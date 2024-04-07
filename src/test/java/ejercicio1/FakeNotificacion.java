package ejercicio1;

public class FakeNotificacion implements Notificacion {
    String contentDestinario;
    String contentRemitente;
    String contentAsunto;
    String contentMensaje;

    @Override
    public void enviarNotificacion(String destinario, String remitente, String asunto, String mensaje) {
        contentDestinario = destinario;
        contentRemitente = remitente;
        contentAsunto = asunto;
        contentMensaje = mensaje;
    }

    public boolean startsWich(String destinario, String remitente, String asunto, String mensaje) {
        return contentDestinario.startsWith(destinario) && contentRemitente.startsWith(remitente)
                && contentAsunto.startsWith(asunto) && contentMensaje.startsWith(mensaje);
    }
}
