import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class No_Hacer_Caso_A_Este_Archivo {
    public static void main(String[] args) {
        // Definición de la URL de conexión, usuario y contraseña
        String jdbcURL = "jdbc:mysql://localhost:3306/videojuegos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String usuario = "root";    // Reemplaza con tu usuario de MySQL
        String contraseña = "Mantismarina2"; // Reemplaza con tu contraseña de MySQL

        try {
            // Cargar el controlador JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            Connection con = DriverManager.getConnection(jdbcURL, usuario, contraseña);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Juegos");

            // Mostrar los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String fechaLanzamiento = rs.getString("fecha_lanzamiento");
                double calificacion = rs.getDouble("calificacion");
                String plataforma = rs.getString("plataforma");
                
                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Género: " + genero);
                System.out.println("Fecha de Lanzamiento: " + fechaLanzamiento);
                System.out.println("Calificación: " + calificacion);
                System.out.println("Plataforma: " + plataforma);
                System.out.println("--------------------------------");
            }
            
            // Cerrar la conexión
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}