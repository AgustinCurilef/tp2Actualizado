package persistenciaEjercicio1;

import ejercicio1.Registro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class EnDiscoRegistro implements Registro {
    private final String ruta;

    public EnDiscoRegistro(String ruta) {
        this.ruta = (String) Objects.requireNonNull(ruta);
    }

    public void registrar(String registro) {
        try {
            Files.write(Paths.get(this.ruta), registro.getBytes(), new OpenOption[]{StandardOpenOption.APPEND});
        } catch (IOException var3) {
            throw new RuntimeException("No se puedo Inscribir en disco", var3);
        }
    }
}
