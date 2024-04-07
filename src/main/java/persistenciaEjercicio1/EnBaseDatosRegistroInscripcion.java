package persistenciaEjercicio1;

import ejercicio1.Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class EnBaseDatosRegistroInscripcion implements Registro {
    private final String url;
    private final String usuario;
    private final String contrasena;

    public EnBaseDatosRegistroInscripcion(String url, String usuario, String contrasena) {
        this.url = (String) Objects.requireNonNull(url);
        this.usuario = (String) Objects.requireNonNull(usuario);
        this.contrasena = contrasena;
    }

    public void registrar(String registro) {
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO concurso (fechaInscripcion,idParticipante,idConcurso) VALUES (?, ?, ?)")) {
            String[] partes = registro.split(",");
            statement.setString(1, partes[0].trim());
            statement.setString(2, partes[1].trim());
            statement.setString(3, partes[2].trim());

            // Ejecutar la consulta de inserción
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
