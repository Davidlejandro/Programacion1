package proyecto;
// Importación de la clase JFrame para crear ventanas en la interfaz gráfica
import javax.swing.*;

// Importación de la interfaz TableCellRenderer para personalizar cómo se renderizan las celdas en una tabla (JTable)
import javax.swing.table.TableCellRenderer;

// Importación de las clases de java.awt para gestionar gráficos y componentes visuales, como Color, Font, etc.
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
     // Constructor de la clase ButtonRenderer
    public ButtonRenderer() {
         // Establece que el botón será opaco (tendrá un fondo visible)
        setOpaque(true);
    }
     // Sobrescribe el método getTableCellRendererComponent de la interfaz TableCellRenderer
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //Establece el texto del botón. Si el valor es null, muestra "Comprar", de lo contrario muestra el valor proporcionado.
        setText((value == null) ? "Comprar" : value.toString());
         // Devuelve el propio botón como el componente a renderizar en la celda de la tabla
        return this;
    }
}