package gamestore.proyecto;

import javax.swing.JFrame;

public class usuario {
    private String nombre;
    private String fechaNacimiento;
    private String telefono;
    private String correo;
    private String contraseña;

    public usuario(String nombre, String fechaNacimiento, String telefono, String correo, String contraseña) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
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

    public static usuario realizarRegistro(JFrame ventana) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarRegistro'");
    }
}
