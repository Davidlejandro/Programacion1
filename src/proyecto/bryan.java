package proyecto;
// Importa las clases necesarias para los componentes de la interfaz gráfica
import javax.swing.*;
import java.awt.*;

public class bryan {
    public static void VentanaCreadorUno() {
        // Configuración de la ventana
        JFrame ventana = new JFrame("GameStore");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(1000, 700);

        // Se obtiene la imagen del ícono utilizando el Toolkit de Java. La imagen se
        // carga desde la ruta especificada.
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Crear un JPanel con un fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        JPanel fondo = new JPanel() {
            // Se sobrescribe el método paintComponent para dibujar un fondo personalizado
            // en el panel
            @Override
            protected void paintComponent(Graphics g) {
                // Se llama al método paintComponent de la clase JPanel para asegurar que se
                // dibujen otros componentes correctamente
                super.paintComponent(g);
                // Se dibuja la imagen de fondo en el panel. El fondo se ajusta para cubrir todo el tamaño del panel.
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Establecer el diseño del panel
        fondo.setLayout(null);

        // Crear y configurar los labels
        JLabel titulo = new JLabel("Bryan Antamba", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        fondo.add(titulo);

        JLabel descripcion = new JLabel(
                "<html>Creador de seccion registro de usuario, Soporte tecnico, Inico sesion, y apartado principal,<br> con manejos de las GUI y creacion de clases.</html>",
                SwingConstants.CENTER);
        descripcion.setBounds(30, 200, 940, 80);
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(descripcion);

        // Crea una nueva imagen desde el archivo especificado
        ImageIcon Foto = new ImageIcon("img/bryan.jpg");

        // Crea una etiqueta (JLabel) para mostrar la imagen
        JLabel etiquetaImagen = new JLabel(Foto);

        // Establece la posición y tamaño de la etiqueta de la imagen
        etiquetaImagen.setBounds(355, 320, 300, 300); // Ajusta la posición y tamaño

        // Añade la etiqueta con la imagen al panel de fondo
        fondo.add(etiquetaImagen);

        // Carga de nuevo la imagen original en un objeto ImageIcon
        ImageIcon originalIcon = new ImageIcon("img/bryan.jpg");

        // Obtiene la imagen de tipo Image desde el objeto ImageIcon
        Image originalImage = originalIcon.getImage();

        // Escalar la imagen proporcionalmente según el tamaño de la etiqueta
        int labelWidth = etiquetaImagen.getWidth(); // Obtiene el ancho de la etiqueta
        int labelHeight = etiquetaImagen.getHeight(); // Obtiene el alto de la etiqueta

        // Escala la imagen utilizando el método getScaledInstance para ajustarse al
        // tamaño de la etiqueta
        // ---------------------------------------------------------------------------
        // Escala la imagen proporcionalmente
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        // Establecer la nueva imagen escalada
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));

        // Añade un componente listener a la ventana para detectar cambios en el tamaño
        // de la ventana
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {

            // Este método se ejecuta cuando la ventana es redimensionada
            public void componentResized(java.awt.event.ComponentEvent evt) {

                // Obtiene el ancho actual de la ventana
                int anchoDeVentana = ventana.getWidth();

                // Ajusta la posición del título de la ventana (label) para centrarlo
                // horizontalmente
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado

                // Ajusta la posición de la descripción para que esté justo debajo del título,
                // también centrada
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título

            }
        });

        // Crea una barra de menús que se asociará a la ventana
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Salir" en la barra de menús
        JMenu salir = new JMenu("Salir");

        // Crear un elemento de menú "Cerrar ventana" dentro del menú "Salir"
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");

        // Añadir un "ActionListener" al elemento "Cerrar ventana" para cerrar la
        // ventana cuando se haga clic
        cerrarSesion.addActionListener(e -> ventana.dispose());

        // Añadir el menú "Salir" a la barra de menús
        menuBar.add(salir);

        // Añadir el elemento "Cerrar ventana" al menú "Salir"
        salir.add(cerrarSesion);

        // Asocia la barra de menús a la ventana
        ventana.setJMenuBar(menuBar);

        // Hace que la ventana sea visible
        ventana.setVisible(true);

        // Configura el panel de fondo como el contenido principal de la ventana
        ventana.setContentPane(fondo);

    }
}