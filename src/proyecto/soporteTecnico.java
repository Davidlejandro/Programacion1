package proyecto;

import javax.swing.*;
import java.awt.*;

public class soporteTecnico {

    public static void VentanaSoporte() {
        // Configuración de la ventana
        JFrame ventana = new JFrame("GameStore");
        // Crea una ventana de tipo JFrame con el título "GameStore"

        // Establece el comportamiento cuando se cierra la ventana. En este caso, la
        // ventana se cierra sin terminar la aplicación
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define el tamaño de la ventana en píxeles (1000 de ancho y 700 de alto)
        ventana.setSize(1000, 700);

        // Configurar el ícono personalizado para la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        // Obtiene una imagen del archivo "icono.png" en la carpeta "img" para usarla
        // como ícono de la ventana
        ventana.setIconImage(icono);
        // Establece la imagen obtenida como ícono de la ventana

        // Crear un JPanel con un fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        // Crea un objeto ImageIcon con la imagen "principal.jpg" ubicada en la carpeta
        // "img", que servirá como fondo

        // Crear un JPanel personalizado donde se pintará el fondo
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Llama al método de pintura del JPanel para realizar el dibujo
                // Dibuja la imagen del fondo, ajustándola al tamaño del JPanel (getWidth() y
                // getHeight())
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Establece el layout del JPanel a forma de null.
        fondo.setLayout(null);

        // Crear y configurar los labels
        // Crear un JLabel para el título "Soporte Técnico", con alineación centrada
        JLabel titulo = new JLabel("Soporte Técnico", SwingConstants.CENTER);
        // Establece las coordenadas (345, 40) y tamaño (350, 40) del JLabel en la
        // ventana
        titulo.setBounds(345, 40, 350, 40);
        // Establece el color del texto como blanco
        titulo.setForeground(Color.WHITE);
        // Establece la fuente del texto a "Arial", negrita, tamaño 35
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        // Agrega el JLabel 'titulo' al panel de fondo
        fondo.add(titulo);

        // Crear un JLabel para la descripción, con formato HTML para saltos de línea
        JLabel descripcion = new JLabel("<html>Si necesitas ayuda, estamos disponibles para ayudarte en lo que necesites. <br>" +"Contáctanos a través de nuestros medios:</html>",SwingConstants.CENTER);
        // Establece las coordenadas (30, 200) y tamaño (940, 60) del JLabel en la
        // ventana
        descripcion.setBounds(30, 200, 940, 60);
        // Establece el color del texto como blanco
        descripcion.setForeground(Color.WHITE);
        // Establece la fuente del texto a "Arial", negrita, tamaño 20
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        // Agrega el JLabel 'descripcion' al panel de fondo
        fondo.add(descripcion);

        // Crear un JLabel para el correo electrónico, con alineación centrada
        JLabel email = new JLabel("Correo: GameStore@gmail.com", SwingConstants.CENTER);
        // Establece las coordenadas (300, 400) y tamaño (400, 30) del JLabel en la
        // ventana
        email.setBounds(300, 400, 400, 30);
        // Establece el color del texto como blanco
        email.setForeground(Color.WHITE);
        // Establece la fuente del texto a "Arial", negrita, tamaño 20
        email.setFont(new Font("Arial", Font.BOLD, 20));
        // Agrega el JLabel 'email' al panel de fondo
        fondo.add(email);

        // Crear un JLabel para el número de teléfono, con alineación centrada
        JLabel telefono = new JLabel("Teléfono: 0995538246", SwingConstants.CENTER);
        // Establece las coordenadas (300, 450) y tamaño (400, 30) del JLabel en la
        // ventana
        telefono.setBounds(300, 450, 400, 30);
        // Establece el color del texto como blanco
        telefono.setForeground(Color.WHITE);
        // Establece la fuente del texto a "Arial", negrita, tamaño 20
        telefono.setFont(new Font("Arial", Font.BOLD, 20));
        // Agrega el JLabel 'telefono' al panel de fondo
        fondo.add(telefono);

        // Agrega un escucha para detectar cambios en el tamaño de la ventana
ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
    // Método que se ejecuta cuando la ventana es redimensionada
    public void componentResized(java.awt.event.ComponentEvent evt) {
        // Obtiene el ancho actual de la ventana
        int anchoDeVentana = ventana.getWidth();

        // Ajustamos la posición del JLabel 'titulo' para que esté centrado horizontalmente en la ventana
        // Calcula la posición X (800 / 2) para centrar el título en base al ancho de la ventana
        titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
        
        // Ajustamos la posición del JLabel 'descripcion' para que esté justo debajo del título
        // Calcula la posición X (800 / 2) para centrar la descripción en base al ancho de la ventana
        descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60);
        
        // Ajustamos la posición del JLabel 'email' para que esté justo debajo de la descripción
        // Calcula la posición X (800 / 2) para centrar el correo en base al ancho de la ventana
        email.setBounds((anchoDeVentana - 800) / 2, 400, 800, 60); 
        
        // Ajustamos la posición del JLabel 'telefono' para que esté justo debajo del correo
        // Calcula la posición X (800 / 2) para centrar el teléfono en base al ancho de la ventana
        telefono.setBounds((anchoDeVentana - 800) / 2, 440, 800, 60); 
    }
});

        JMenuBar menuBar = new JMenuBar();
        // Crear el menú "Salir"
        JMenu salir = new JMenu("Salir");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");
        // Se realiza una acción para cerra el sistema inmobiliario
        // Crear el menú "Salir"
        cerrarSesion.addActionListener(e -> ventana.dispose());
        menuBar.add(salir);
        salir.add(cerrarSesion);
        //hacemos que el menuBar de salir se muestre en la ventana
        ventana.setJMenuBar(menuBar);

        // Configurar el panel como contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}