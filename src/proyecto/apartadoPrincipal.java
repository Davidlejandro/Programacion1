package proyecto;

// Importa las clases necesarias para crear la interfaz gráfica utilizando Swing.
import javax.swing.*;

// Importa las clases de AWT necesarias para trabajar con gráficos y eventos.
import java.awt.*;

// Importa las clases para manejar los eventos de acción, como los clics en botones.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importa la clase List para trabajar con listas en Java.
import java.util.List;

// Importa las clases necesarias para crear y manejar tablas en Swing.
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class apartadoPrincipal {
    public static void main(String[] args) {
        // Configuración de la ventana
        JFrame ventana = new JFrame("GameStore");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000, 700);
        ventana.setLayout(null); // Layout absoluto

        // Se obtiene la imagen del ícono utilizando el Toolkit de Java. La imagen se carga desde la ruta especificada.
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Cargar la imagen de fondo
        // Ajusta la ruta según tu estructura de carpetas
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg"); 

        // Crear un JPanel con un fondo personalizado
        JPanel fondo = new JPanel() {
            // Se sobrescribe el método paintComponent para dibujar un fondo personalizado en el panel
            @Override
            protected void paintComponent(Graphics dimeciones) {
                // Se llama al método paintComponent de la clase JPanel para asegurar que se dibujen otros componentes correctamente
                super.paintComponent(dimeciones);
                // Se dibuja la imagen de fondo en el panel. El fondo se ajusta para cubrir todo el tamaño del panel.
                //"fondoApp.getImage()" obtiene la imagen a mostrar.
                //"getWidth() y getHeight()" ajusta la imagen al tamaño completo del panel.
                //"this" especifica el componente en el que se dibuja la imagen.
                dimeciones.drawImage(fondoApp.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Establecer el diseño del panel
        fondo.setLayout(null);

        // Crear y configurar la etiqueta principal
        JLabel label = new JLabel("GameStore", SwingConstants.CENTER);
        label.setBounds(345, 40, 300, 40);
        label.setForeground(Color.WHITE); // Texto en color blanco
        label.setFont(new Font("Arial", Font.BOLD, 40)); // Fuente adaptable

        // Crear y configurar el subtítulo
        JLabel label1 = new JLabel(
                "<html>Bienvenido a GameStore donde podrás encontrar los mejores juegos<br>de acción, terror y aventura.</html>",
                SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);

        // Añadir ambos al panel de fondo
        //hacemos visible el label, label1 en el fondo del proyecto para que sea visible
        fondo.add(label);
        fondo.add(label1);

        // Listener para ajustar la posición dinámica al redimensionar
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            //Se describe el método componentResized. El objeto "evt" contiene información sobre el evento
            public void componentResized(java.awt.event.ComponentEvent evt) {
                //Se obtiene el ancho real de la ventana mediante el método getWidth(). Este valor se almacenara en la variable anchoDeVentana
                int anchoDeVentana = ventana.getWidth();
                //Se ajusta la posición del label"GameStore" dentro de la ventana. 
                //calculamos las coordenada X (300 / 2) para colocar el label centrado horizontalmente en la ventana
                //asegurando que siempre esté en el centro.
                label.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40);
                // Ajusta debajo del título
                label1.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60);
            }
        });

        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Salir"
        JMenu salir = new JMenu("Salir");
        JMenuItem cerrarSesion = new JMenuItem("Cerrar Tienda");
        cerrarSesion.addActionListener(e -> System.exit(0));
        menuBar.add(salir);
        salir.add(cerrarSesion);

        // Menú "Categorías"
        JMenu categorias = new JMenu("Categorias");
        JMenuItem productos1 = new JMenuItem("Acción");
        JMenuItem productos2 = new JMenuItem("Terror");
        JMenuItem productos3 = new JMenuItem("Aventura");

        categorias.add(productos1);
        categorias.add(productos2);
        categorias.add(productos3);

        // Acción para mostrar los juegos según la categoría seleccionada
        //El addActionListener detectará cuando el usuario haga clic en el elemento del menú.
        productos1.addActionListener(new ActionListener() {
            @Override
            //Este método se ejecuta cuando el usuario interactúa con el elemento del menú (al hacer clic en el).
            public void actionPerformed(ActionEvent e) {
                //Cuando se selecciona el elemento "Acción", se llama al método mostrarJuegos
                mostrarJuegos("Acción", ventana);
            }
        });
        productos2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuegos("Terror", ventana);
            }
        });
        productos3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJuegos("Aventura", ventana);
            }
        });
        

        menuBar.add(categorias);

        // Crear menú "Ayuda"
        JMenu help = new JMenu("Ayuda");
        JMenuItem soporte = new JMenuItem("Soporte");
        menuBar.add(help);
        help.add(soporte);
        //Asocia un ActionListenerelemento del menú "Soporte"
        //el método VentanaSoporte()de la clase soporteTecnico abre una ventana y proporciona soporte técnico al usuario.
        soporte.addActionListener(e -> soporteTecnico.VentanaSoporte());

        // Menú "Usuario"
        JMenu cliente = new JMenu("Usuario");
        JMenuItem registro = new JMenuItem("Registrarse");
        cliente.add(registro);
        menuBar.add(cliente);   

        JMenu responsables = new JMenu("Creadores");
        JMenuItem creadores = new JMenuItem("Esteban");
        //Accion al hacer clic en el elemento "Esteban", se ejecuta el método VentanaCreadorTresde la clase esteban.
        creadores.addActionListener(e -> esteban.VentanaCreadorCuatro());
        JMenuItem creadores2 = new JMenuItem("Erick");
        //Accion al hacer clic en el elemento "Erick", se ejecuta el método VentanaCreadorTresde la clase erick.
        creadores2.addActionListener(e -> erick.VentanaCreadorTres());
        JMenuItem creadores3 = new JMenuItem("David");
        //Accion al hacer clic en el elemento "David", se ejecuta el método VentanaCreadorTresde la clase david.
        creadores3.addActionListener(e -> david.VentanaCreadorDos());
        JMenuItem creadores4 = new JMenuItem("Bryan");
        //Accion al hacer clic en el elemento "Bryan", se ejecuta el método VentanaCreadorTresde la clase bryan.
        creadores4.addActionListener(e -> bryan.VentanaCreadorUno());

        menuBar.add(responsables);
        responsables.add(creadores);
        responsables.add(creadores2);
        responsables.add(creadores3);
        responsables.add(creadores4);

        //Crea un menú llamado "Inicio", que será añadido a la barra de menús
        JMenu Inicio1 = new JMenu("Inicio");
        JMenuItem IniciarLaSesion = new JMenuItem("Iniciar sesion");
         // Vincular el ítem con el método iniciarSesion
         IniciarLaSesion.addActionListener(e -> {
                    // Creamos una instancia de la clase InicioSesion
                    InicioSesion inicioSesion = new InicioSesion();
                    // Llamamos al método iniciarSesion de la clase "InicioSesion"
                    inicioSesion.iniciarSesion();
                });
        //Añade el elemento "IniciarLaSesion" al menu Inicio1
        menuBar.add(Inicio1);
        Inicio1.add(IniciarLaSesion);
        


        menuBar.add(Inicio1);
        Inicio1.add(IniciarLaSesion);

        // Acción para "Registrarse"
        registro.addActionListener(e -> {
            System.out.println("Opción de registro seleccionada.");
            // Llamamos al método "realizarRegistro" de la clase "Registro" para crear un nuevo usuario.
            Registro nuevoUsuario = Registro.realizarRegistro(ventana);
             // Verificamos si el registro fue exitoso, es decir, si "nuevoUsuario" no es null.
            if (nuevoUsuario != null) {
                // Si el registro fue exitoso, muestra un cuadro de diálogo con los detalles del nuevo usuario
                //los datos del usuario
                JOptionPane.showMessageDialog(ventana,"Registro Exitoso:\n" +"Nombre: " + nuevoUsuario.getNombre() + "\n" +"Fecha de Nacimiento: " + nuevoUsuario.getFechaNacimiento() + "\n" +"Teléfono: " + nuevoUsuario.getTelefono() + "\n" +"Correo: " + nuevoUsuario.getCorreo() + "\n" +"Contraseña: " + nuevoUsuario.getContraseña(),"Registro Completado",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ventana.setJMenuBar(menuBar);
        ventana.setVisible(true);

        // Configurar el panel como contenido de la ventana
        ventana.setContentPane(fondo);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
    // Método para mostrar los juegos
    private static void mostrarJuegos(String categoria, JFrame ventana) {
        // Obtener la lista de juegos desde la base de datos
        List<Videojuegos> juegos = Videojuegos.obtenerJuegosPorCategoria(categoria);
    
        // Crear una nueva ventana para mostrar los juegos
        JFrame ventanaJuegos = new JFrame(categoria + " - Juegos");
        ventanaJuegos.setSize(800, 400); // Tamaño de la ventana
        ventanaJuegos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaJuegos.setLayout(new BorderLayout());
    
        // Crear un modelo de tabla para los datos
        String[] columnas = {"ID", "Título", "Género", "Fecha Lanzamiento", "Calificación", "Plataforma", "Acción"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Solo permitir editar la columna de acción (botón)
            }
        };
    
        // Llenar el modelo con los datos de los videojuegos
        for (Videojuegos juego : juegos) {
            // Itera sobre cada objeto "juego" en la lista "juegos".
            // "juegos" es una lista que contiene varios objetos de la clase "Videojuegos".
            modeloTabla.addRow(new Object[]{
                // Añadimos una nueva fila a la tabla con los datos del objeto "juego".
                // "modeloTabla" es el modelo de datos de la tabla en la interfaz gráfica.
                //usamos "get" nos dara los resultados de cada uno
                juego.getID(),
                juego.getTitulo(),
                juego.getGenero(),
                juego.getFecha_lanzamiento(),
                juego.getCalificacion(),
                juego.getPlataforma(),
                // Añade un texto estático en la séptima columna para representar el botón de compra
                "Comprar"
            });
        }
    
// Supongamos que tienes una lista de juegos y una tabla configurada en tu código
JTable tabla = new JTable(modeloTabla); // Instancia de la tabla

// Configuración del editor de celdas con el botón en la columna 6
tabla.getColumnModel().getColumn(6).setCellEditor(
    new ButtonEditor(new JCheckBox(), tabla, juegos) // Se pasa la tabla y la lista de juegos al ButtonEditor
);
    // Configuración del renderizador para el botón
    tabla.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
    
        // Hacer que la tabla sea desplazable si tiene muchos datos
        JScrollPane scrollPane = new JScrollPane(tabla);
        ventanaJuegos.add(scrollPane, BorderLayout.CENTER);
    
        // Hacer visible la ventana con la tabla
        ventanaJuegos.setVisible(true);
    }
    // Método público estático que se llama para mostrar una ventana de confirmación de compra.
    // Recibe un objeto "juego" de la clase Videojuegos como argumento, el cual contiene la información del juego comprado.
    public static void mostrarConfirmacionCompra(Videojuegos juego) {
        // Obtener el usuario activo desde la sesión
        Registro usuario = almacenarSesion.getUsuarioActivo();

        //Si el usuario no inicio sesion se le mandara un mensaje para que inicie sesion para poder realizar la compra
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "No hay un usuario autenticado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    //Mostramos los datos de la compra en una pequeña ventana cuando el usuario haga clic al boton de "comprar"
        String mensaje = "Compra realizada con éxito\n" +
                         "Juego: " + juego.getTitulo() + "\n" +
                         "Usuario: " + usuario.getNombre() + "\n" +
                         "Plataforma: " + juego.getPlataforma() + "\n" +
                         "Fecha de Lanzamiento: " + juego.getFecha_lanzamiento() + "\n" + 
                         "Calificación: " + juego.getCalificacion() + "\n" +
                         "Precio: " + juego.getPrecio() + "\n";
                         //titulo a la pequeña ventana "confirmacion de Compra"
        JOptionPane.showMessageDialog(null, mensaje, "Confirmación de Compra", JOptionPane.INFORMATION_MESSAGE);
    }

}