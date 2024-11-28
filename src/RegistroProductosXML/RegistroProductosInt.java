package RegistroProductosXML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import RegistroProductos.Producto;

public class RegistroProductosInt extends JFrame {
    private JTextField txtNombre, txtCodigo, txtPrecio, txtCategoria;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private ArchivoXML archivoXML;

    public RegistroProductosInt() {
        archivoXML = new ArchivoXML();
        initUI();
        cargarDatosDesdeXML();
        agregarEventoTabla();
    }

    private void initUI() {
        setTitle("Registro de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos de texto y etiquetas
        panelIzquierdo.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelIzquierdo.add(txtNombre);

        panelIzquierdo.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelIzquierdo.add(txtCodigo);

        panelIzquierdo.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelIzquierdo.add(txtPrecio);

        panelIzquierdo.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panelIzquierdo.add(txtCategoria);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarProducto());
        panelIzquierdo.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarProducto());
        panelIzquierdo.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarProducto());
        panelIzquierdo.add(btnEliminar);

        add(panelIzquierdo, BorderLayout.WEST);

        // Configuración de la tabla
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Código", "Precio", "Categoría"}, 0);
        tablaProductos = new JTable(modeloTabla);
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        setVisible(true);
    }
    
    private void agregarEventoTabla() {
    // Obtener el modelo de selección de la tabla
    tablaProductos.getSelectionModel().addListSelectionListener(e -> {
        // Asegurarse de que no se dispare el evento cuando no hay una selección activa
        if (!e.getValueIsAdjusting() && tablaProductos.getSelectedRow() != -1) {
            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = tablaProductos.getSelectedRow();

            // Verificar si hay una fila seleccionada
            if (filaSeleccionada != -1) {
                // Actualizar los campos de texto con los valores de la fila seleccionada
                txtNombre.setText((String) modeloTabla.getValueAt(filaSeleccionada, 0)); // Columna "Nombre"
                txtCodigo.setText((String) modeloTabla.getValueAt(filaSeleccionada, 1)); // Columna "Código"
                txtPrecio.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString()); // Columna "Precio"
                txtCategoria.setText((String) modeloTabla.getValueAt(filaSeleccionada, 3)); // Columna "Categoría"
            }
        }
    });
}


    // Cargar datos desde el archivo XML a la tabla
    private void cargarDatosDesdeXML() {
        try {
            List<RegistroProductos.Producto> productos = archivoXML.leerProductos();
            for (RegistroProductos.Producto producto : productos) {
                modeloTabla.addRow(new Object[]{
                        producto.getNombre(),
                        producto.getCodigo(),
                        producto.getPrecio(),
                        producto.getCategoria()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar un producto
    private void agregarProducto() {
        // Validar que todos los campos estén llenos
        if (txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty() ||
                txtPrecio.getText().isEmpty() || txtCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener datos de los campos de texto
            String nombre = txtNombre.getText();
            String codigo = txtCodigo.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            String categoria = txtCategoria.getText();

            // Crear un objeto Producto
            RegistroProductos.Producto producto = new RegistroProductos.Producto(codigo, nombre, precio, categoria);

            // Agregar el producto al archivo XML
            archivoXML.agregarProducto(producto);

            // Agregar el producto al modelo de la tabla
            modeloTabla.addRow(new Object[]{nombre, codigo, precio, categoria});

            // Limpiar los campos de texto
            limpiarCampos();

            // Confirmación
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el producto en el archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para modificar un producto
    private void modificarProducto() {
         // Verificar si hay una fila seleccionada en la tabla
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto en la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener los datos actuales de la fila seleccionada
            String codigoActual = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            
            // Validar que los campos del formulario no estén vacíos
            if (txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty() ||
                    txtPrecio.getText().isEmpty() || txtCategoria.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener los nuevos valores desde los campos del formulario
            String nombreNuevo = txtNombre.getText();
            String codigoNuevo = txtCodigo.getText();
            double precioNuevo = Double.parseDouble(txtPrecio.getText());
            String categoriaNueva = txtCategoria.getText();

            // Crear un objeto Producto con los nuevos datos
            Producto productoModificado = new Producto(codigoNuevo, nombreNuevo, precioNuevo, categoriaNueva);

            // Modificar el producto en el archivo XML
            archivoXML.modificarProductoPorCodigo(codigoActual, productoModificado);

            // Actualizar los valores en el modelo de la tabla
            modeloTabla.setValueAt(nombreNuevo, filaSeleccionada, 0);
            modeloTabla.setValueAt(codigoNuevo, filaSeleccionada, 1);
            modeloTabla.setValueAt(precioNuevo, filaSeleccionada, 2);
            modeloTabla.setValueAt(categoriaNueva, filaSeleccionada, 3);

            // Limpiar los campos del formulario
            limpiarCampos();

            JOptionPane.showMessageDialog(this, "Producto modificado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto en el archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    // Método para eliminar un producto
    private void eliminarProducto() {
    // Verificar si hay una fila seleccionada en la tabla
        int filaSeleccionada = tablaProductos.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto en la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Confirmar la eliminación
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    // Obtener el código del producto desde la fila seleccionada
                    String codigo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

                    // Eliminar el producto del archivo XML
                    archivoXML.eliminarProductoPorCodigo(codigo);

                    // Eliminar la fila del modelo de la tabla
                    modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto del archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtCategoria.setText("");
    }

    public static void main(String[] args) {
        new RegistroProductosInt();
    }
}


