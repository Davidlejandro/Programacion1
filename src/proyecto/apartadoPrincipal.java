package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class apartadoPrincipal {
    public static void main(String[] args) {
        // Configuración de la ventana
        JFrame ventana = new JFrame("GameStore");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000, 700);
        ventana.setLayout(null); // Layout absoluto

        // Configurar el ícono personalizado
        Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
        ventana.setIconImage(icono);

        // Cargar la imagen de fondo
        ImageIcon fondoApp = new ImageIcon("img/principal.jpg"); // Ajusta la ruta según tu estructura de carpetas

        // Crear un JPanel con un fondo personalizado
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics dimeciones) {
                super.paintComponent(dimeciones);
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
        fondo.add(label);
        fondo.add(label1);

        // Listener para ajustar la posición dinámica al redimensionar
        ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int anchoDeVentana = ventana.getWidth();
                label.setBounds((anchoDeVentana - 300) / 2, 40, 300, 40); // Centrado
                label1.setBounds((anchoDeVentana - 800) / 2, 250, 800, 60); // Justo debajo del título
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
        productos1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // Menú "Usuario"
        JMenu cliente = new JMenu("Usuario");
        JMenuItem registro = new JMenuItem("Registrarse");
        cliente.add(registro);
        menuBar.add(cliente);   

        soporte.addActionListener(e -> soporteTecnico.VentanaSoporte());

        JMenu responsables = new JMenu("Creadores");
        JMenuItem creadores = new JMenuItem("Esteban");
        creadores.addActionListener(e -> esteban.VentanaCreadorCuatro());
        JMenuItem creadores2 = new JMenuItem("Erick");
        creadores2.addActionListener(e -> erick.VentanaCreadorTres());
        JMenuItem creadores3 = new JMenuItem("David");
        creadores3.addActionListener(e -> david.VentanaCreadorDos());
        JMenuItem creadores4 = new JMenuItem("Bryan");
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
            System.out.println("Opción de registro seleccionada."); // Depuración
            Registro nuevoUsuario = Registro.realizarRegistro(ventana);
            if (nuevoUsuario != null) {
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
            modeloTabla.addRow(new Object[]{
                juego.getID(),
                juego.getTitulo(),
                juego.getGenero(),
                juego.getFecha_lanzamiento(),
                juego.getCalificacion(),
                juego.getPlataforma(),
                "Comprar" // Texto del botón
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

    // Método de confirmación de compra
    public static void mostrarConfirmacionCompra(Videojuegos juego, Registro usuario) {
        String mensaje = "Compra realizada con éxito\n" +
                         "Juego: " + juego.getTitulo() + "\n" +
                         "Usuario: " + usuario.getNombre() + "\n" +
                         "Plataforma: " + juego.getPlataforma() + "\n" +
                         "Fecha de Lanzamiento: " + juego.getFecha_lanzamiento() + "\n" + 
                         "Calificación: " + juego.getCalificacion() + "\n"+ 
                         "Correo del Usuario: " + usuario.getCorreo() + "\n" +
                         "Precio: " + juego.getPrecio() + "\n";
        JOptionPane.showMessageDialog(null, mensaje, "Confirmación de Compra", JOptionPane.INFORMATION_MESSAGE);
    }
    
}