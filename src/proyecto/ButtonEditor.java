package proyecto;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private List<Videojuegos> juegos;
    private List<Registro> usuarios; // Lista de usuarios (de la base de datos)
    private JTable table; // Agregar tabla como variable de instancia

    public ButtonEditor(JCheckBox checkBox, JTable table, List<Videojuegos> juegos) {
        super(checkBox);
        this.table = table; // Asignar la tabla al constructor
        this.juegos = juegos; // Pasar la lista de juegos
        this.usuarios = Registro.obtenerUsuarios(); // Obtener la lista de usuarios
    
        button = new JButton();
        button.setOpaque(true);
    
        // Evento del botón
        button.addActionListener(e -> {
            int row = table.getSelectedRow(); // Obtener la fila seleccionada de la tabla
            if (row >= 0 && row < juegos.size() && usuarios.size() >= 0) {
                // Obtener el segundo usuario
                Registro usuarioSeleccionado = usuarios.get(0);  // Selecciona el segundo registro
    
                if (usuarioSeleccionado != null) {
                    // Obtener el juego de la fila seleccionada
                    Videojuegos juegoSeleccionado = juegos.get(row);
                    // Llamar al método de confirmación de compra con el juego y el objeto Registro del usuario
                    apartadoPrincipal.mostrarConfirmacionCompra(juegoSeleccionado, usuarioSeleccionado);
                }
            }
            clicked = true;
            fireEditingStopped();
        });
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        label = (value == null) ? "Comprar" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return clicked ? label : null;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}