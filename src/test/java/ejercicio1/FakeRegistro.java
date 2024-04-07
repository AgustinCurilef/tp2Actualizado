package ejercicio1;

public class FakeRegistro implements Registro {
    String content;

    @Override
    public void registrar(String var1) {
        content = var1;
    }

    public boolean StartsWich(String registro) {
        return content.startsWith(registro);
    }
}
