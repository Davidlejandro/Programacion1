package proyecto;
// Define que esta clase pertenece al paquete "proyecto".

import javax.swing.*;
// Importa las clases de Swing para crear interfaces gráficas, como JOptionPane y JFrame.

import java.sql.*;
// Importa las clases necesarias para trabajar con bases de datos en Java.

import java.text.SimpleDateFormat;
// Importa la clase para formatear y analizar fechas.

public class Registro {
    // Define la clase pública "Registro".

    // Atributos privados de la clase
    // Identificador único del registro.
    private int id;
    // Nombre del usuario.
    private String nombre;
    // Fecha de nacimiento del usuario.
    private Date fechaNacimiento;
    // Número de teléfono del usuario.
    private String telefono;
    // Correo electrónico del usuario.
    private String correo;
    // Contraseña del usuario.
    private String contraseña;

    // Constructor que inicializa los atributos de la clase.
    public Registro(int id, String nombre, Date fechaNacimiento, String telefono, String correo, String contraseña) {
        // Instanciamos los valores de la clase Registro
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Uso del métodos "getter" el cual permiten acceder a los valores de los
    // atributos privados.
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

    // Método estático que permite realizar el registro del usuario. Toma un objeto
    // JFramecomo argumento (una ventana).
    public static Registro realizarRegistro(JFrame ventana) {
        // Mensaje a mostrar si el usuario cancela el registro.
        String cancelMessage = "El registro ha sido cancelado.";

      String nombre;
      while (true) {
          nombre = JOptionPane.showInputDialog(ventana, "Ingrese un Nombre de Usuario:");

          if (nombre == null) {
              JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado", JOptionPane.INFORMATION_MESSAGE);
              return null;
          }

          if (nombre.trim().isEmpty()) {
              JOptionPane.showMessageDialog(ventana, "Debe ingresar un Nombre de Usuario.", "Error de registro", JOptionPane.ERROR_MESSAGE);
              continue;
          }

          if (!esNombreValido(nombre)) {
              JOptionPane.showMessageDialog(ventana, "El nombre de usuario no puede contener números.", "Error de registro", JOptionPane.ERROR_MESSAGE);
              continue;
          }

          break;
      }

      // Solicitar y validar la fecha de nacimiento.
      String fechaNacimientoStr;
      Date fechaNacimiento = null;
      while (true) {
          fechaNacimientoStr = JOptionPane.showInputDialog(ventana, "Ingrese su Fecha de Nacimiento (DD/MM/AAAA):");

          if (fechaNacimientoStr == null) return null;

          if (fechaNacimientoStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
              try {
                  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                  java.util.Date utilDate = sdf.parse(fechaNacimientoStr);
                  fechaNacimiento = new Date(utilDate.getTime());
                  break;
              } catch (Exception e) {
                  JOptionPane.showMessageDialog(ventana, "Error al Ingresar la fecha. Inténtelo nuevamente.", "Error de registro", JOptionPane.ERROR_MESSAGE);
              }
          } else {
              JOptionPane.showMessageDialog(ventana, "Por favor, ingrese una fecha válida (DD/MM/AAAA).", "Error de registro", JOptionPane.ERROR_MESSAGE);
          }
      }

      // Solicitar y validar el número de teléfono.
      String telefono;
      while (true) {
          telefono = JOptionPane.showInputDialog(ventana, "Ingrese su número de teléfono:");

          if (telefono == null) {
              JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado", JOptionPane.INFORMATION_MESSAGE);
              return null;
          }

          if (telefono.trim().isEmpty() || telefono.length() != 10) {
              JOptionPane.showMessageDialog(ventana, "Debe ingresar 10 dígitos. Intente nuevamente.", "Error de registro", JOptionPane.ERROR_MESSAGE);
          } else {
              boolean valido = true;
              for (int numerico = 0; numerico < telefono.length(); numerico++) {
                  if (!Character.isDigit(telefono.charAt(numerico))) {
                      valido = false;
                      break;
                  }
              }

              if (valido) {
                  break;  // El número es válido, salir del bucle
              } else {
                  JOptionPane.showMessageDialog(ventana, "El número debe contener solo dígitos. Intente nuevamente.", "Error de registro", JOptionPane.ERROR_MESSAGE);
              }
          }
      }

      // Solicitar y validar el correo electrónico.
      String correo;
      while (true) {
          correo = JOptionPane.showInputDialog(ventana, "Ingrese su Correo Electrónico:");

          if (correo == null) {
              JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado", JOptionPane.INFORMATION_MESSAGE);
              return null;
          }

          if (!esCorreoValido(correo)) {
              JOptionPane.showMessageDialog(ventana, "Ingrese su correo correctamente.", "Error de registro", JOptionPane.ERROR_MESSAGE);
          } else {
              break; // El correo es válido
          }
      }

      // Solicitar la contraseña.
      JPasswordField contraseñaField = new JPasswordField();
      int option = JOptionPane.showConfirmDialog(ventana, contraseñaField, "Ingrese una Contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (option == JOptionPane.OK_OPTION) {
          char[] contraseña = contraseñaField.getPassword();
          if (contraseña.length == 0) {
              JOptionPane.showMessageDialog(ventana, "Debe ingresar una contraseña.", "Error de registro", JOptionPane.ERROR_MESSAGE);
              return null;
          }
          String contraseñaString = new String(contraseña);

          Registro nuevoRegistro = new Registro(0, nombre, fechaNacimiento, telefono, correo, contraseñaString);

          if (insertarEnBaseDeDatos(nuevoRegistro)) {
              JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
              return nuevoRegistro;
          } else {
              JOptionPane.showMessageDialog(ventana, "Error al guardar en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
              return null;
          }
      } else {
          JOptionPane.showMessageDialog(ventana, cancelMessage, "Registro cancelado", JOptionPane.INFORMATION_MESSAGE);
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
      if (correo == null || correo.isEmpty()) {
          return false;
      }
      int atIndex = correo.indexOf('@');
      int dotIndex = correo.lastIndexOf('.');
      return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < correo.length() - 1;
  }

  // Método para insertar un registro en la base de datos.
  private static boolean insertarEnBaseDeDatos(Registro registro) {
      Connection conn = null;
      PreparedStatement stmt = null;

      try {
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos", "root", "Mantismarina2");
          String sql = "INSERT INTO Registro (nombre, fechaNacimiento, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?)";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, registro.getNombre());
          stmt.setDate(2, registro.getFechaNacimiento());
          stmt.setString(3, registro.getTelefono());
          stmt.setString(4, registro.getCorreo());
          stmt.setString(5, registro.getContraseña());

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