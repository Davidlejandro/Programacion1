package proyecto;

import javax.swing.*;
import java.awt.*;

public class soporteTecnico {

    public static void VentanaSoporte() {
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
        JLabel titulo = new JLabel("Soporte Técnico", SwingConstants.CENTER);
        titulo.setBounds(345, 40, 350, 40);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        fondo.add(titulo);

        JLabel descripcion = new JLabel(
                "<html>Si necesitas ayuda, estamos disponibles para ayudarte en lo que necesites. <br>" +
                        "Contáctanos a través de nuestros medios:</html>",
                SwingConstants.CENTER);
        descripcion.setBounds(30, 200, 940, 60);
        descripcion.setForeground(Color.WHITE);
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(descripcion);

        JLabel email = new JLabel("Correo: GameStore@gmail.com", SwingConstants.CENTER);
        email.setBounds(300, 400, 400, 30);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(email);

        JLabel telefono = new JLabel("Teléfono: 0995538246", SwingConstants.CENTER);
        telefono.setBounds(300, 450, 400, 30);
        telefono.setForeground(Color.WHITE);
        telefono.setFont(new Font("Arial", Font.BOLD, 20));
        fondo.add(telefono);

        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int anchoDeVentana = ventana.getWidth();
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título
                email.setBounds((anchoDeVentana - 800) / 2, 400, 800, 60); // Justo debajo del título
                telefono.setBounds((anchoDeVentana - 800) / 2, 440, 800, 60); // Justo debajo del título
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
