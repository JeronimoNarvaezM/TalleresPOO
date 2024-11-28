/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistroProductosJSON;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class RegistroProductosInt extends JFrame {
    
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtCodigo, txtPrecio, txtCategoria;
    private RegistroProductosGson registro;
    
    public RegistroProductosInt() {
        registro = new RegistroProductosGson();

        // Configuración de la ventana
        setTitle("Gestor de Productos con JSON - Gson");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(null);

        // Campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 150, 25);
        add(txtNombre);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 60, 100, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 60, 150, 25);
        add(txtCodigo);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 100, 100, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 100, 150, 25);
        add(txtPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 140, 100, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(120, 140, 150, 25);
        add(txtCategoria);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 200, 100, 25);
        btnAgregar.addActionListener(this::agregarProducto);
        add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(140, 200, 100, 25);
        btnEliminar.addActionListener(this::eliminarProducto);
        add(btnEliminar);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(20, 260, 100, 25);
        btnModificar.addActionListener(this::modificarProducto);
        add(btnModificar);
        
        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Código", "Precio", "Categoría"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBounds(300, 20, 460, 300);
        tablaProductos.getSelectionModel().addListSelectionListener(e -> cargarCamposDesdeTabla());
        add(scrollPane);

        cargarTabla();
    }

    private void agregarProducto(ActionEvent e) {
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String categoria = txtCategoria.getText();

        Producto producto = new Producto(nombre, codigo, precio, categoria);
        registro.agregarProductos(producto);
        cargarTabla();
        limpiarCampos();
    }

    private void eliminarProducto(ActionEvent e) {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String codigo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            registro.eliminarProductos(codigo);
            cargarTabla();
        }
    }
    
        private void modificarProducto(ActionEvent e) {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtener el código original de la fila seleccionada
            String codigoOriginal = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

            // Obtener los datos modificados desde los campos de texto
            String nombreModificado = txtNombre.getText();
            String codigoModificado = txtCodigo.getText();
            double precioModificado = Double.parseDouble(txtPrecio.getText());
            String categoriaModificada = txtCategoria.getText();

            // Crear un producto modificado
            Producto productoModificado = new Producto(nombreModificado, codigoModificado, precioModificado, categoriaModificada);

            // Llamar al método para modificar el producto
            registro.modificarProducto(codigoOriginal, productoModificado);

            // Actualizar la tabla
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void cargarTabla() {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        for (Producto producto : registro.getProductos()) {
            modeloTabla.addRow(new Object[]{producto.getNombre(), producto.getCodigo(), producto.getPrecio(), producto.getCategoria()});
        }
    }

    private void cargarCamposDesdeTabla() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtNombre.setText((String) modeloTabla.getValueAt(filaSeleccionada, 0));
            txtCodigo.setText((String) modeloTabla.getValueAt(filaSeleccionada, 1));
            txtPrecio.setText(String.valueOf(modeloTabla.getValueAt(filaSeleccionada, 2)));
            txtCategoria.setText((String) modeloTabla.getValueAt(filaSeleccionada, 3));
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtCategoria.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroProductosInt().setVisible(true));
    }
}
