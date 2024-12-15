package proyecto;
//Definicion de clase "almacenarSesion"
public class almacenarSesion {
    // Objeto para el usuario activo
    private static Registro usuarioActivo; 

    // Método para establecer el usuario activo
    public static void setUsuarioActivo(Registro usuario) {
        usuarioActivo = usuario;
    }

    // Método para obtener el usuario activo
    public static Registro getUsuarioActivo() {
        return usuarioActivo;
    }
}