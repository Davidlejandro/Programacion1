package gamestore.proyecto;
import javax.swing.*;
import java.awt.*;
public class david {
    public static void VentanaCreadorDos() {
        // Configuración de la ventana
        JFrame ventana = new JFrame("GameStore");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(1000, 700);

        // Configurar el ícono personalizado
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Crear un JPanel con un fondo personalizado
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg");
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setLayout(null);

        // Crear y configurar los labels
        JLabel titulo = new JLabel("David Ruiz", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        fondo.add(titulo);

        JLabel descripcion = new JLabel(
                "<html>Creador de las categorias de la aplicacion, y representacion<br> de tabla en la realizacion de una compra con manejos de las GUI y ayuda clases.</html>",
                SwingConstants.CENTER);
        descripcion.setBounds(30, 200, 940, 60);
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(descripcion);
        ImageIcon Foto = new ImageIcon("img/david.jpg");
        JLabel etiquetaImagen = new JLabel(Foto);
        etiquetaImagen.setBounds(355, 320, 300, 300); // Ajusta la posición y tamaño
        fondo.add(etiquetaImagen);
        ImageIcon originalIcon = new ImageIcon("img/david.jpg");
        Image originalImage = originalIcon.getImage();

        // Escalar la imagen proporcionalmente
        int labelWidth = etiquetaImagen.getWidth();
        int labelHeight = etiquetaImagen.getHeight();
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        // Establecer la nueva imagen escalada
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));

        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int anchoDeVentana = ventana.getWidth();
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título
               
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

        
        ventana.setJMenuBar(menuBar);
        ventana.setVisible(true);

        // Configurar el panel como contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}


