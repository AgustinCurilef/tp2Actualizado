package persistenciaEjercicio1;

import ejercicio1.Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class EnBaseDatosRegistroRestaurante implements Registro {
    private final String url;
    private final String usuario;
    private final String contrasena;

    public EnBaseDatosRegistroRestaurante(String url, String usuario, String contrasena) {
        this.url = (String) Objects.requireNonNull(url);
        this.usuario = (String) Objects.requireNonNull(usuario);
        this.contrasena = contrasena;
    }

    public void registrar(String registro) {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.usuario, this.contrasena);

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO restaurante (fecha,factura ) VALUES (?, ?)");

                try {
                    String[] partes = registro.split(",");
                    statement.setString(1, partes[0].trim());
                    statement.setString(2, partes[1].trim());
                    statement.executeUpdate();
                } catch (Throwable var8) {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (Throwable var9) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }
}
