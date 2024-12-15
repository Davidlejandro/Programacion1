package proyecto;
// Importa las clases necesarias para los componentes de la interfaz gráfica
import javax.swing.*;
import java.awt.*;

public class david {
    public static void VentanaCreadorDos() {
        // Configuración de la ventana
        // Crear una nueva ventana (JFrame) con el título "GameStore"
        JFrame ventana = new JFrame("GameStore");

        // Configurar la operación de cierre de la ventana: al cerrar, se descarta la
        // ventana sin terminar el programa
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Establecer el tamaño de la ventana (ancho 1000px, alto 700px)
        ventana.setSize(1000, 700);

        // Configurar el ícono personalizado para la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Crear un JPanel con un fondo personalizado que se dibuja sobre el panel
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Llamada al método paintComponent de JPanel para realizar el dibujo
                // Se dibuja la imagen de fondo en el panel. El fondo se ajusta para cubrir todo el tamaño del panel.
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);                                                                      
            }
        };

        // Establecer el layout del panel como "null", lo que permite colocar
        // componentes con posiciones específicas
        fondo.setLayout(null);

        // Crear el JLabel para el título con el texto "David Ruiz", alineado al centro
        JLabel titulo = new JLabel("David Ruiz", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40); // Establecer la posición y tamaño del título
        titulo.setForeground(Color.WHITE); // Establecer el color del texto como blanco
        titulo.setFont(new Font("Arial", Font.BOLD, 35)); // Establecer la fuente con estilo negrita y tamaño 35
        fondo.add(titulo); // Añadir el JLabel del título al panel

        // Crear un JLabel para la descripción del creador con texto HTML con salto de linea.
        JLabel descripcion = new JLabel(
                "<html>Creador de las categorias de la aplicacion, y desarrollador de base de datos MySQL,<br> Vinculacion BDD con el proyecto y ayudante de dasarrollo de clases de codigo java.</html>",
                SwingConstants.CENTER); // Alinear el texto al centro
        descripcion.setBounds(30, 200, 940, 80); // Establecer la posición y tamaño de la descripción
        descripcion.setForeground(Color.WHITE); // Establecer el color del texto como blanco
        descripcion.setFont(new Font("Arial", Font.BOLD, 20)); // Establecer la fuente con estilo negrita y tamaño 20
        fondo.add(descripcion); // Añadir el JLabel de descripción al panel

        // Crear un JLabel para mostrar la imagen de "David"
        ImageIcon Foto = new ImageIcon("img/david.jpg");
        JLabel etiquetaImagen = new JLabel(Foto);
        etiquetaImagen.setBounds(355, 320, 300, 300); // Establecer la posición y tamaño de la imagen
        fondo.add(etiquetaImagen); // Añadir la imagen al panel

        // Crear un ImageIcon con la imagen original para escalarla
        ImageIcon originalIcon = new ImageIcon("img/david.jpg");
        Image originalImage = originalIcon.getImage();

        // Escalar la imagen proporcionalmente al tamaño del JLabel
        int labelWidth = etiquetaImagen.getWidth();
        int labelHeight = etiquetaImagen.getHeight();
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        // Establecer la imagen escalada en el JLabel
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));

        // Añadir un listener para ajustar las posiciones de los componentes cuando la
        // ventana cambie de tamaño
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Obtener el ancho de la ventana
                int anchoDeVentana = ventana.getWidth(); 
                // Centrar el título horizontalmente
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40);
                // Centrar la descripción horizontalmente debajo del título
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60);
            }
        });

        // Crear una barra de menú para la ventana
        JMenuBar menuBar = new JMenuBar();

        // Crear un menú "Salir" en la barra de menús
        JMenu salir = new JMenu("Salir");

        // Crear un ítem de menú "Cerrar ventana" en el menú "Salir"
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana");

        // Añadir una acción al ítem "Cerrar ventana" para cerrar la ventana al hacer
        // clic
        cerrarSesion.addActionListener(e -> ventana.dispose());

        // Añadir el menú "Salir" a la barra de menús
        menuBar.add(salir);

        // Añadir el ítem "Cerrar ventana" al menú "Salir"
        salir.add(cerrarSesion);

        // Establecer la barra de menús en la ventana
        ventana.setJMenuBar(menuBar);

        // Hacer que la ventana sea visible
        ventana.setVisible(true);

        // Configurar el panel de fondo como el contenido principal de la ventana
        ventana.setContentPane(fondo);
    }
}