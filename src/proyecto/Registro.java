package proyecto;
// Define que esta clase pertenece al paquete "proyecto".

import javax.swing.*;
// Importa las clases de Swing para crear interfaces gráficas, como JOptionPane y JFrame.

import java.sql.*;
// Importa las clases necesarias para trabajar con bases de datos en Java.

import java.text.SimpleDateFormat;
// Importa la clase para formatear y analizar fechas.
import java.util.ArrayList;
import java.util.List;

public class Registro {
    // Define la clase pública "Registro".

    // Atributos privados de la clase
    // Identificador único del registro.
    private int id_usuario;
    // Nombre del usuario.
    private String nombre;
    // Fecha de nacimiento del usuario.
    private String apellido;
    private Date fechaNacimiento;
    // Número de teléfono del usuario.
    private String telefono;
    // Correo electrónico del usuario.
    private String correo;
    // Contraseña del usuario.
    private String password;

    // Constructor que inicializa los atributos de la clase.
    public Registro(int id_usuario, String nombre, String apellido, Date fechaNacimiento, String telefono,
            String correo, String password) {
        // Instanciamos los valores de la clase Registro
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.apellido = apellido;
    }

    // Constructor vacío para la clase Registro, que puede ser utilizado cuando no
    // se tienen los valores iniciales.
    public Registro() {
        // No realiza ninguna acción, solo crea un objeto vacío de tipo Registro.
    }

    // Métodos "getter" que permiten acceder a los valores de los atributos privados
    // de la clase.

    public int getID() {
        // Devuelve el identificador único del usuario.
        return id_usuario;
    }

    public String getNombre() {
        // Devuelve el nombre del usuario.
        return nombre;
    }

    public Date getFechaNacimiento() {
        // Devuelve la fecha de nacimiento del usuario.
        return fechaNacimiento;
    }

    public String getTelefono() {
        // Devuelve el número de teléfono del usuario.
        return telefono;
    }

    public String getCorreo() {
        // Devuelve el correo electrónico del usuario.
        return correo;
    }

    public String getContraseña() {
        // Devuelve la contraseña del usuario.
        return password;
    }

    public String getApellido() {
        // Devuelve el apellido del usuario.
        return apellido;
    }

    // El Métodos "setter" que permiten modificar los valores de los atributos
    // privados de la clase.

    public void setNombre(String nombre) {
        // Establece un nuevo nombre para el usuario.
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        // Establece una nueva contraseña para el usuario.
        this.password = contraseña;
    }

    // Método estático que permite realizar el registro del usuario. Toma un objeto
    // JFrame como argumento (una ventana).
    public static Registro realizarRegistro(JFrame ventana) {
        // Mensaje a mostrar si el usuario cancela el registro.
        String cancelMessage = "El registro ha sido cancelado.";

        // Variable para almacenar el nombre de usuario ingresado.
        String nombre;
        // Bucle infinito para permitir múltiples intentos de ingresar un nombre de
        // usuario.
        while (true) {
            // Solicitar al usuario que ingrese un nombre de usuario.
            nombre = JOptionPane.showInputDialog(ventana, "Ingrese un Nombre de Usuario:");

            // Verificar si el usuario canceló la entrada (hizo clic en "Cancelar").
            if (nombre == null) {
                // Mostrar mensaje de cancelación si el usuario cancela el registro.
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                // Terminar el proceso y devolver null si se cancela el registro.
                return null;
            }

            // Verificar si el nombre ingresado está vacío.
            if (nombre.trim().isEmpty()) {
                // Mostrar mensaje de error si el nombre está vacío.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar un Nombre de Usuario.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle para que el usuario intente de nuevo.
                continue;
            }

            // Verificar si el nombre contiene caracteres no válidos (números).
            if (!esNombreValido(nombre)) {
                // Mostrar mensaje de error si el nombre contiene números.
                JOptionPane.showMessageDialog(ventana, "El nombre de usuario no puede contener números.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle si el nombre no es válido.
                continue;
            }
            // Salir del bucle si el nombre es válido.
            break;
        }

        // Variable para almacenar el apellido ingresado.
        String apellido;

        // Bucle infinito para permitir múltiples intentos de ingresar el apellido.
        while (true) {
            // Solicitar al usuario que ingrese su apellido.
            apellido = JOptionPane.showInputDialog(ventana, "Ingrese su apellido:");

            // Verificar si el usuario canceló la entrada (hizo clic en "Cancelar").
            if (apellido == null) {
                // Mostrar mensaje de cancelación si el usuario cancela el registro.
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                // Terminar el proceso y devolver null si se cancela el registro.
                return null;
            }

            // Verificar si el apellido ingresado está vacío.
            if (apellido.trim().isEmpty()) {
                // Mostrar mensaje de error si el apellido está vacío.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar un apellido.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle para que el usuario intente de nuevo.
                continue;
            }

            // Verificar si el apellido contiene caracteres no válidos (números).
            if (!esNombreValido(apellido)) {
                // Mostrar mensaje de error si el apellido contiene números.
                JOptionPane.showMessageDialog(ventana, "El apellido del usuario no puede contener números.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                // Reiniciar el bucle si el apellido no es válido.
                continue;
            }
            // Salir del bucle si el apellido es válido.
            break;
        }

        // Solicitar y validar la fecha de nacimiento.
        // Variable para almacenar la fecha de nacimiento en formato de cadena.
        String fechaNacimientoStr;
        // Variable para almacenar la fecha de nacimiento en formato Date
        Date fechaNacimiento = null;

        // Bucle infinito para permitir múltiples intentos de ingresar una fecha válida.
        while (true) {
            // Solicitar al usuario que ingrese su fecha de nacimiento en formato
            // "DD/MM/YYYY".
            fechaNacimientoStr = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DD/MM/YYYY):");

            // Verificar si el usuario ha cancelado la entrada.
            if (fechaNacimientoStr == null)
                return null; // Si el usuario cancela, salir y retornar null.

            // Verificar si la cadena ingresada tiene el formato correcto (8 dígitos).
            if (fechaNacimientoStr.matches("\\d{8}")) {
                try {
                    // Extraer el día, mes y año de la cadena ingresada.
                    String dia = fechaNacimientoStr.substring(0, 2);
                    String mes = fechaNacimientoStr.substring(2, 4);
                    String anio = fechaNacimientoStr.substring(4, 8);

                    // Crear una cadena con el formato "DD/MM/YYYY" para facilitar la conversión.
                    String fechaConFormato = dia + "/" + mes + "/" + anio;

                    // Crear un objeto SimpleDateFormat con el formato "dd/MM/yyyy".
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    // Desactivar la validación flexible para evitar fechas no válidas.
                    sdf.setLenient(false);

                    // Intentar convertir la cadena en un objeto java.util.Date.
                    java.util.Date utilDate = sdf.parse(fechaConFormato);

                    // Convertir la fecha de tipo util.Date a java.sql.Date.
                    fechaNacimiento = new Date(utilDate.getTime());

                    // Si todo es exitoso sale del bucle con break.
                    break;
                } catch (Exception e) {
                    // Si ocurre un error (por ejemplo, una fecha no válida), mostrar un mensaje de
                    // error.
                    JOptionPane.showMessageDialog(ventana, "Error al Ingresar la fecha. Inténtelo nuevamente.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si la cadena ingresada no tiene el formato correcto, mostrar un mensaje de
                // error.
                JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DDMMYYYY).",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Variable para almacenar el número de teléfono ingresado.
        String telefono;

        // Bucle infinito para permitir múltiples intentos de ingresar un número de
        // teléfono válido.
        while (true) {
            // Solicitar al usuario que ingrese su número de teléfono.
            telefono = JOptionPane.showInputDialog(ventana, "Ingrese su número de teléfono:");

            // Verificar si el usuario ha cancelado la entrada.
            if (telefono == null) {
                // Si el usuario cancela, mostrar un mensaje y salir del método retornando null.
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

            // Verificar si el número de teléfono está vacío o no tiene exactamente 10
            // caracteres.
            if (telefono.trim().isEmpty() || telefono.length() != 10) {
                // Si el número no tiene 10 dígitos, mostrar un mensaje de error.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar 10 dígitos. Intente nuevamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Variable para verificar si el número contiene solo dígitos.
                boolean valido = true;

                // Recorrer cada caracter del número de teléfono para asegurarse de que todos
                // sean dígitos.
                for (int numerico = 0; numerico < telefono.length(); numerico++) {
                    if (!Character.isDigit(telefono.charAt(numerico))) {
                        // Si algún caracter no es un dígito, marcar como no válido y salir del bucle.
                        valido = false;
                        break;
                    }
                }

                // Si el número de teléfono es válido, salir del bucle.
                if (valido) {
                    break;
                } else {
                    // Si el número contiene caracteres no numéricos, mostrar un mensaje de error.
                    JOptionPane.showMessageDialog(ventana, "El número debe contener solo dígitos. Intente nuevamente.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // Variable para almacenar el correo electrónico ingresado.
        String correo;

        // Bucle infinito para permitir múltiples intentos de ingresar un correo
        // electrónico válido.
        while (true) {
            // Solicitar al usuario que ingrese su correo electrónico.
            correo = JOptionPane.showInputDialog(ventana, "Ingrese su Correo Electrónico:");

            // Verificar si el usuario ha cancelado la entrada.
            if (correo == null) {
                // Si el usuario cancela, mostrar un mensaje y salir del método retornando null.
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }

            // Verificamos si el correo electrónico ingresado es válido.
            if (!esCorreoValido(correo)) {
                // Si el correo no es válido, mostrar un mensaje de error y continuar
                // solicitando el correo.
                JOptionPane.showMessageDialog(ventana, "Ingrese su correo correctamente.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Si el correo es válido, salir del bucle.
                break;
            }
        }

        // Solicitar la contraseña.
        // Solicitar la contraseña al usuario mediante un campo de texto de tipo
        // contraseña.
        JPasswordField passwordField = new JPasswordField();

        // Mostrar una ventana para ingresar la contraseña utilizando un cuadro de
        // confirmación.
        int option = JOptionPane.showConfirmDialog(ventana, passwordField, "Ingrese una Contraseña:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Verificar si el usuario presionó el botón "OK" para ingresar la contraseña.
        if (option == JOptionPane.OK_OPTION) {
            // Obtener la contraseña ingresada como un arreglo de caracteres.
            char[] password = passwordField.getPassword();

            // Verificar si el usuario no ingresó ninguna contraseña (longitud del arreglo
            // es 0).
            if (password.length == 0) {
                // Mostrar un mensaje de error si la contraseña está vacía.
                JOptionPane.showMessageDialog(ventana, "Debe ingresar una contraseña.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
                return null; // Salir del método si no se ingresa contraseña.
            }

            // Convertir el arreglo de caracteres a un string.
            String contraseñaString = new String(password);

            // Crear un nuevo objeto de tipo "Registro" con los datos obtenidos.
            Registro nuevoRegistro = new Registro(0, nombre, apellido, fechaNacimiento, telefono, correo,
                    contraseñaString);

            // Intentar insertar el nuevo registro en la base de datos.
            if (insertarEnBaseDeDatos(nuevoRegistro)) {
                // Si la inserción es exitosa, mostrar un mensaje de éxito y retornar el nuevo
                // registro.
                JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return nuevoRegistro;
            } else {
                // Si ocurre un error al guardar en la base de datos, mostrar un mensaje de
                // error.
                JOptionPane.showMessageDialog(ventana, "Error al guardar en la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                // Retornar null si no se pudo guardar en la base de datos.
                return null;
            }
        } else {
            // Si el usuario cancela la acción (presiona "Cancelar"), mostrar un mensaje y
            // salir del método.
            JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }

    // Método para validar que el nombre no contenga números
    public static boolean esNombreValido(String nombre) {
        for (char c : nombre.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Método para validar el correo sin expresión regular compleja
    public static boolean esCorreoValido(String correo) {
        // Verifica si el correo es nulo o está vacío
        if (correo == null || correo.isEmpty()) {
            // Si es nulo o vacío, el correo no es válido
            return false;
        }
        // Encuentra el índice del primer carácter '@' en el correo
        int atIndex = correo.indexOf('@');
        // Encuentra el índice del último carácter '.' en el correo
        int dotIndex = correo.lastIndexOf('.');
        // Verifica si el '@' está en una posición válida (después del primer carácter)
        // y si el '.' está después del '@' y antes del final del correo.
        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < correo.length() - 1;
    }

    // Método para insertar un registro en la base de datos.
    private static boolean insertarEnBaseDeDatos(Registro registro) {
        // Declara la variable para la conexión a la base de datos, inicialmente nula
        Connection conn = null;
        // Declara la variable para el PreparedStatement, inicialmente nula
        PreparedStatement stmt = null;

        try {
            // Establece una conexión con la base de datos MySQL usando las credenciales
            // proporcionadas
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juego", "root", "Mantismarina2");

            // Define la consulta SQL para insertar un registro en la tabla 'Registro'
            String sql = "INSERT INTO Registro (nombre, apellido, fechaNacimiento, telefono, correo, password) VALUES (?, ?, ?, ?, ?, ?)";

            // Prepara la declaración SQL usando la conexión establecida
            stmt = conn.prepareStatement(sql);

            // Establece el valor del primer parámetro (nombre) en la consulta SQL
            stmt.setString(1, registro.getNombre());

            // Establece el valor del segundo parámetro (apellido) en la consulta SQL
            stmt.setString(2, registro.getApellido());

            // Establece el valor del tercer parámetro (fechaNacimiento) en la consulta SQL
            stmt.setDate(3, registro.getFechaNacimiento());

            // Establece el valor del cuarto parámetro (telefono) en la consulta SQL
            stmt.setString(4, registro.getTelefono());

            // Establece el valor del quinto parámetro (correo) en la consulta SQL
            stmt.setString(5, registro.getCorreo());

            // Establece el valor del sexto parámetro (contraseña) en la consulta SQL
            stmt.setString(6, registro.getContraseña());

            // Ejecuta la actualización SQL, que retorna el número de filas afectadas
            int filasAfectadas = stmt.executeUpdate();

            // Si se ha afectado al menos una fila (es decir, la inserción fue exitosa),
            // retorna 'true'
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción durante la ejecución, imprime el stack trace
            e.printStackTrace();
            // Retorna 'false' si ocurre un error en la base de datos
            return false;
        } finally {
            try {
                // Cierra el PreparedStatement si no es nulo
                if (stmt != null)
                    stmt.close();

                // Cierra la conexión si no es nula
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // Si ocurre un error al cerrar los recursos, imprime el stack trace
                e.printStackTrace();
            }
        }

    }

    // Método para obtener todos los usuarios registrados en la base de datos
    public static List<Registro> obtenerUsuarios() {
        // Crea una lista para almacenar los registros de usuarios obtenidos de la base
        // de datos
        List<Registro> listaDeUsuarios = new ArrayList<>();

        // Declara las variables para la conexión a la base de datos, el
        // PreparedStatement y el ResultSet
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establece la conexión con la base de datos MySQL utilizando las credenciales
            // proporcionadas
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juego", "root", "Mantismarina2");

            // Define la consulta SQL para obtener todos los registros de la tabla
            // 'registro'
            String sql = "SELECT * FROM registro";

            // Prepara la declaración SQL para su ejecución
            stmt = conn.prepareStatement(sql);

            // Ejecuta la consulta SQL y obtiene los resultados en un ResultSet
            rs = stmt.executeQuery();

            // Itera a través de los resultados obtenidos en el ResultSet
            while (rs.next()) {
                // Obtiene los valores de cada columna del registro en la base de datos
                int id_usuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("Apellido");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String password = rs.getString("password");

                // Crea un nuevo objeto Registro con los datos obtenidos del ResultSet
                Registro registro = new Registro(id_usuario, nombre, apellido, fechaNacimiento, telefono, correo,
                        password);

                // Agrega el objeto Registro a la lista de usuarios
                listaDeUsuarios.add(registro);
            }
        } catch (SQLException e) {
            // Si ocurre una excepción de SQL, imprime el error
            e.printStackTrace();
        } finally {
            try {
                // Asegura que el ResultSet, PreparedStatement y Connection sean cerrados para
                // liberar recursos
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // Si ocurre un error al cerrar los recursos, imprime el error
                e.printStackTrace();
            }
        }

        // Retorna la lista de usuarios obtenidos de la base de datos
        return listaDeUsuarios;
    }
}