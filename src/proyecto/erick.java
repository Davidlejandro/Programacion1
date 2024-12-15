package proyecto;  // Define el paquete del proyecto
// Importa las clases necesarias para los componentes de la interfaz gráfica
import javax.swing.*;
import java.awt.*;
//Definicion de la clase llamada "erick"
public class erick { 
    // Método que configura y muestra la ventana con la información del creador "Erick"
    public static void VentanaCreadorTres() {  
        
        // Configuración de la ventana principal
        // Crea una nueva ventana con título "GameStore"
        JFrame ventana = new JFrame("GameStore");  
        // Establece que la ventana se cierre al pulsar el botón de cierre
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        // Establece el tamaño de la ventana a 1000x700 píxeles
        ventana.setSize(1000, 700);  

        // Configurar el ícono personalizado de la ventana
        // Carga una imagen como icono
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");  
        // Establece el icono de la ventana
        ventana.setIconImage(icono);  

        // Crear un JPanel con un fondo personalizado
         // Carga una imagen de fondo para la ventana
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg"); 
        // Crea un JPanel personalizado
        JPanel fondo = new JPanel() { 
            @Override
            // Sobrescribe el método para pintar el fondo
            protected void paintComponent(Graphics g) {  
                // Llama al método de la clase base para asegurarse de que se dibuje correctamente
                super.paintComponent(g);  
                // Se dibuja la imagen de fondo en el panel. El fondo se ajusta para cubrir todo el tamaño del panel.
                g.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);  
            }
        };
        // Establece el layout del JPanel como null para permitir posiciones absolutas de los componentes
        fondo.setLayout(null);  

        // Crear y configurar los labels 
        // Crea una etiqueta con el nombre del creador, centrada
        JLabel titulo = new JLabel("Erick Mejia", SwingConstants.CENTER);  
        // Establece la posición y tamaño del título
        titulo.setBounds(345, 40, 350, 40);  
         // Establece el color de la fuente a blanco
        titulo.setForeground(Color.WHITE); 
         // Establece la fuente, el estilo y el tamaño del texto
        titulo.setFont(new Font("Arial", Font.BOLD, 35)); 
        // Añade la etiqueta al JPanel de fondo
        fondo.add(titulo);
        // Crea una etiqueta con la descripción del creador, centrada
        JLabel descripcion = new JLabel("<html>Colaborador de desarrollo de diagramas de base de datos, y codigo java, <br> colaborador en formato del proyecto.</html>", SwingConstants.CENTER);  
                // Establece la posición y tamaño de la descripción
        descripcion.setBounds(30, 200, 940, 60); 
         // Establece el color del texto a blanco
        descripcion.setForeground(Color.WHITE); 
        // Establece la fuente, el estilo y el tamaño de la descripción
        descripcion.setFont(new Font("Arial", Font.BOLD, 20));  
        // Añade la descripción al JPanel de fondo
        fondo.add(descripcion);  

        // Cargar y mostrar una foto del creador
        // Carga la imagen del creador
        ImageIcon Foto = new ImageIcon("img/Foto de erick.jpg");  
        // Crea un JLabel con la foto del creador
        JLabel etiquetaImagen = new JLabel(Foto);  
        // Establece la posición y tamaño de la imagen
        etiquetaImagen.setBounds(355, 320, 300, 300);  
        // Añade la imagen al JPanel de fondo
        fondo.add(etiquetaImagen);  
        
        // Escalar la imagen proporcionalmente al tamaño del JLabel
        // Carga la imagen original
        ImageIcon originalIcon = new ImageIcon("img/Foto de erick.jpg");  
        // Obtiene el objeto Image de la imagen original
        Image originalImage = originalIcon.getImage();  

        // Escalar la imagen al tamaño del JLabel
        // Obtiene el ancho del JLabel
        int labelWidth = etiquetaImagen.getWidth();  
        // Obtiene la altura del JLabel
        int labelHeight = etiquetaImagen.getHeight();  
        // Escala la imagen proporcionalmente
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH); 

        // Establecer la nueva imagen escalada en el JLabel
        etiquetaImagen.setIcon(new ImageIcon(scaledImage));

        // Escucha los cambios en el tamaño de la ventana para reajustar los componentes
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Obtiene el ancho de la ventana
                int anchoDeVentana = ventana.getWidth();  
                // Centra el título horizontalmente
                titulo.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40);  
                // Centra la descripción horizontalmente
                descripcion.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60);  
            }
        });

        // Crear un menú con la opción de cerrar la ventana
        // Crea una barra de menú
        JMenuBar menuBar = new JMenuBar(); 
         // Crea un menú "Salir"
        JMenu salir = new JMenu("Salir");
        // Crea un ítem de menú para cerrar la ventana
        JMenuItem cerrarSesion = new JMenuItem("Cerrar ventana"); 
        // Define la acción de cerrar la ventana
        cerrarSesion.addActionListener(e -> ventana.dispose());  
        // Añade el menú "Salir" a la barra de menú
        menuBar.add(salir); 
         // Añade el ítem "Cerrar ventana" al menú "Salir"
        salir.add(cerrarSesion); 

        // Establecer la barra de menú en la ventana
        ventana.setJMenuBar(menuBar);

        // Hacer visible la ventana
        ventana.setVisible(true);

        // Configurar el panel con fondo como contenido de la ventana
        ventana.setContentPane(fondo);

        // Añadir la etiqueta de la imagen al JFrame (no necesario ya que ya se ha añadido al JPanel)
        ventana.add(etiquetaImagen);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}