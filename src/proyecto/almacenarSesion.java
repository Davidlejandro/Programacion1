package proyecto;

public class almacenarSesion {
    private static Registro usuarioActivo; // Objeto para el usuario activo

    // Método para establecer el usuario activo
    public static void setUsuarioActivo(Registro usuario) {
        usuarioActivo = usuario;
    }

    // Método para obtener el usuario activo
    public static Registro getUsuarioActivo() {
        return usuarioActivo;
    }
}