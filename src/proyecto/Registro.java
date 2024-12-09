package proyecto;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Registro {
    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private String telefono; // Cambié esto de int a String
    private String correo;
    private String contraseña;

    public Registro(int id, String nombre, Date fechaNacimiento, String telefono, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Métodos getter
    public int getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Método para realizar el registro
    public static Registro realizarRegistro(JFrame ventana) {
        String cancelMessage = "El registro ha sido cancelado.";

        // Validación para el nombre
        String nombre;
        while (true) {
            nombre = JOptionPane.showInputDialog(ventana, "Ingrese un Nombre de Usuario:");
            if (nombre == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar un Nombre de Usuario.", "Error de registro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        // Validación para la fecha de nacimiento
        String fechaNacimientoStr;
        Date fechaNacimiento = null;
        while (true) {
            fechaNacimientoStr = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DD/MM/AAAA):");
            if (fechaNacimientoStr == null) return null;
            if (fechaNacimientoStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                try {
                    // Convertir la fecha de nacimiento de String a Date
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date utilDate = sdf.parse(fechaNacimientoStr);
                    fechaNacimiento = new Date(utilDate.getTime()); // Convertir a java.sql.Date
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventana, "Error al convertir la fecha. Inténtelo nuevamente.",
                            "Error de registro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DD/MM/AAAA).",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Validación para el teléfono
        String telefono;
        while (true) {
            telefono = JOptionPane.showInputDialog(ventana, "Ingrese su número de teléfono:");
            if (telefono == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            if (telefono.trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "El campo no puede estar vacío.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else if (!telefono.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar 10 dígitos. Intente nuevamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else {
                break; // Teléfono válido
            }
        }

        // Validación para el correo
        String correo;
        while (true) {
            correo = JOptionPane.showInputDialog(ventana, "Ingrese su Correo Electrónico:");
            if (correo == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(ventana, "Ingrese su correo correctamente.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }

        // Capturar la contraseña
        JPasswordField contraseñaField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(
                ventana,
                contraseñaField,
                "Ingrese una Contraseña:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            char[] contraseña = contraseñaField.getPassword();
            if (contraseña.length == 0) {
                JOptionPane.showMessageDialog(ventana, "Debe ingresar una contraseña.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            String contraseñaString = new String(contraseña);

            // Crear el objeto Registro con los valores ingresados
            Registro nuevoRegistro = new Registro(0, nombre, fechaNacimiento, telefono, correo, contraseñaString);

            // Intentar insertar los datos en la base de datos
            if (insertarEnBaseDeDatos(nuevoRegistro)) {
                JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return nuevoRegistro;
            } else {
                JOptionPane.showMessageDialog(ventana, "Error al guardar en la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } else {
            JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    // Método para insertar los datos en la base de datos
    private static boolean insertarEnBaseDeDatos(Registro Registro) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establecer la conexión a la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");

            // Crear la consulta SQL para insertar los datos
            String sql = "INSERT INTO Registro (nombre, fechaNacimiento, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Registro.getNombre());
            stmt.setDate(2, Registro.getFechaNacimiento());
            stmt.setString(3, Registro.getTelefono());
            stmt.setString(4, Registro.getCorreo());
            stmt.setString(5, Registro.getContraseña());

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
