package proyecto;
import javax.swing.*;

// Asegúrate de importar java.sql.Date
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class tablas {
    private int id;
    private String titulo;
    private String genero;
    private Date fecha_lanzamiento;
    private int calificacion;
    private String plataforma;

    // Constructor
    public tablas(int id, String titulo, String genero, Date fecha_lanzamiento, int calificacion, String plataforma) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.calificacion = calificacion;
        this.plataforma = plataforma;
    }

    // Método para obtener los juegos de acuerdo con la categoría desde la base de datos
    public static List<Videojuegos> obtenerJuegosPorCategoria(String categoria) {
        List<Videojuegos> juegos = new ArrayList<>();
        String jdbcURL = "jdbc:mysql://localhost:3306/Juegos"; // URL de tu base de datos
        String usuario = "root"; // Usuario de MySQL
        String contraseña = "Mantismarina2"; // Contraseña de MySQL

        // Consulta SQL para obtener los juegos por categoría
        String query = "SELECT * FROM videojuegos WHERE genero = ?";

        try {
        
            Connection con = DriverManager.getConnection(jdbcURL, usuario, contraseña);
            PreparedStatement pst = con.prepareStatement(query); {
            pst.setString(1, categoria);

            // Ejecutar la consulta
            ResultSet rs = pst.executeQuery();

            // Procesar los resultados de la consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                Date fechaLanzamiento = rs.getDate("fecha_lanzamiento");
                int calificacion = rs.getInt("calificacion");
                String plataforma = rs.getString("plataforma");

                // Crear un objeto Videojuegos y agregarlo a la lista
                Videojuegos juego = new Videojuegos(id, titulo, genero, fechaLanzamiento, calificacion, plataforma);
                juegos.add(juego);
            }

        }
    } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.",
                                          "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }

        return juegos;
    }
}

