package proyecto;

import javax.swing.*;

// Asegúrate de importar java.sql.Date
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Videojuegos {
    // Definimos las variables para la seccion de las categorias de los juegos de
    // forma privada
    private int id_juego;
    private String titulo;
    private String genero;
    private Date fecha_lanzamiento;
    private int calificacion;
    private String plataforma;
    private Double precio;

    // Desarrollamos un constructor para las variables
    public Videojuegos(int id_juego, String titulo, String genero, Date fecha_lanzamiento, int calificacion,
            String plataforma, Double precio) {
        // Instanciamos las variables que estan en el constructor,
        this.id_juego = id_juego;
        this.titulo = titulo;
        this.genero = genero;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.calificacion = calificacion;
        this.plataforma = plataforma;
        this.precio = precio;
    }

    // Métodos getter que permiten acceder a los atributos de la clase Videojuegos

    // Obtiene el ID del videojuego
    public int getID() {
        return id_juego;
    }

    // Obtiene el título del videojuego
    public String getTitulo() {
        return titulo;
    }

    // Obtiene el género del videojuego
    public String getGenero() {
        return genero;
    }

    // Obtiene la fecha de lanzamiento del videojuego
    public Date getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    // Obtiene la calificación del videojuego
    public int getCalificacion() {
        return calificacion;
    }

    // Obtiene la plataforma en la que está disponible el videojuego
    public String getPlataforma() {
        return plataforma;
    }

    // Obtiene el precio del videojuego
    public double getPrecio() {
        return precio;
    }

    // Método para obtener los juegos de acuerdo con la categoría desde la base de
    // datos
    // Método estático para obtener los videojuegos por categoría (género)
    public static List<Videojuegos> obtenerJuegosPorCategoria(String categoria) {
        // Crea una lista vacía para almacenar los videojuegos que coinciden con la
        // categoría
        List<Videojuegos> juegos = new ArrayList<>();

        // Configuración de la URL de la base de datos, el usuario y la contraseña de
        // MySQL
        String jdbcURL = "jdbc:mysql://localhost:3306/juego"; // URL de la base de datos
        String usuario = "root"; // Usuario de MySQL
        String contraseña = "Mantismarina2"; // Contraseña de MySQL

        // Consulta SQL para obtener los juegos cuya categoría (género) coincida con el
        // parámetro
        String query = "SELECT * FROM videojuegos WHERE genero = ?";

        try {
            // Establece la conexión a la base de datos con los parámetros de conexión
            // proporcionados
            Connection con = DriverManager.getConnection(jdbcURL, usuario, contraseña);

            // Prepara la consulta SQL con la conexión obtenida
            PreparedStatement pst = con.prepareStatement(query);
            {

                // Establece el valor del parámetro en la consulta, usando la categoría
                // proporcionada
                pst.setString(1, categoria);

                // Ejecuta la consulta y obtiene el conjunto de resultados
                ResultSet rs = pst.executeQuery();

                // Procesa los resultados de la consulta
                while (rs.next()) {
                    // Extrae los valores de las columnas del resultado para obtener los valores de cada uno.
                    int id_juego = rs.getInt("id_juego"); 
                    String titulo = rs.getString("titulo"); 
                    String genero = rs.getString("genero"); 
                    Date fechaLanzamiento = rs.getDate("fecha_lanzamiento"); 
                    int calificacion = rs.getInt("calificacion");
                    String plataforma = rs.getString("plataforma"); 
                    Double precio = rs.getDouble("Precio"); 

                    // Crea un objeto Videojuegos con los datos extraídos de la base de datos
                    Videojuegos juego = new Videojuegos(id_juego, titulo, genero, fechaLanzamiento, calificacion,
                            plataforma, precio);

                    // Añade el objeto Videojuegos a la lista de juegos
                    juegos.add(juego);
                }

            } 
        } catch (SQLException e) {
            // Si ocurre un error al conectar con la base de datos o ejecutar la consulta, muestra un mensaje de error
            e.printStackTrace(); // Imprime el seguimiento del error en la consola
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.",
            // Muestra un cuadro de diálogo con el mensaje de error
                    "Error de Base de Datos", JOptionPane.ERROR_MESSAGE); 
        }

        // Devuelve la lista de videojuegos obtenidos de la base de datos
        return juegos;
    }
}