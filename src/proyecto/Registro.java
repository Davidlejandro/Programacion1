package gamestore.proyecto;

import javax.swing.*;


public class Registro extends usuario {

    public Registro(String nombre, String fechaNacimiento, String telefono, String correo, String contraseña) {
        super(nombre, fechaNacimiento, telefono, correo, contraseña);
    }

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
        String fechaNacimiento;
        while (true) {
            fechaNacimiento = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DD/MM/AAAA):");
            if (fechaNacimiento == null) return null;
            if (fechaNacimiento.matches("\\d{2}/\\d{2}/\\d{4}")) break;
            JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DD/MM/AAAA).",
                    "Error de registro", JOptionPane.ERROR_MESSAGE);
        }

        String telefono;
        while (true) {
            telefono = JOptionPane.showInputDialog(ventana, "Ingrese ssu numero de telefono: ");
            if (telefono == null) {
                JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                return null; // Salir del registro
            }
            if (telefono.trim().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "EEl campo no tiene que estas vacio.",
                        "Error de registro", JOptionPane.ERROR_MESSAGE);
            } else if (!telefono.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(ventana, 
                        "Debe ingresar 10 digitos maximo, Intente lo nuevamente.",
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
            return new Registro(nombre, fechaNacimiento, telefono, correo, contraseñaString);
        } else {
            JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }
}
