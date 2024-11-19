/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistroProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 57320
 */
public class registroProductos extends JFrame{
    private JTextField txtCodigo, txtNombre, txtPrecio, txtCategoria;
    private JButton btnAgregar;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private ArchivoProducto archivoProducto;

    public registroProductos() {
        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        archivoProducto = new ArchivoProducto();

        // Campos de texto
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 20, 150, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 50, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 50, 150, 25);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 80, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 80, 150, 25);
        add(txtPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 110, 80, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(100, 110, 150, 25);
        add(txtCategoria);

        // Botón para agregar producto
        btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(20, 150, 230, 25);
        add(btnAgregar);

        // Tabla para mostrar productos
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Categoría");

        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBounds(20, 200, 550, 150);
        add(scrollPane);

        // Cargar productos desde el archivo
        cargarProductos();

        // Acción para el botón de agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String categoria = txtCategoria.getText();

                Producto producto = new Producto(codigo, nombre, precio, categoria);
                archivoProducto.escribirProducto(producto);

                modeloTabla.addRow(new Object[]{codigo, nombre, precio, categoria});
                limpiarCampos();
            }
        });
    }

    private void cargarProductos() {
        List<Producto> productos = archivoProducto.leerProductos();
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCategoria()});
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCategoria.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new registroProductos().setVisible(true);
        });
    }
}
