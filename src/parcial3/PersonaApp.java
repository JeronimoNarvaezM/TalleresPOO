/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtNombre, txtCorreo;
    private List<Persona> personas;

    public PersonaApp() {
        setTitle("Gestión de Personas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        personas = new ArrayList<>();

        // Panel izquierdo: Formulario
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Formulario de Personas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        leftPanel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        leftPanel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        txtCorreo = new JTextField(15);
        leftPanel.add(txtCorreo, gbc);

        JButton btnAgregar = new JButton("Agregar a Tabla");
        btnAgregar.addActionListener(e -> agregarPersonaATabla());
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        leftPanel.add(btnAgregar, gbc);

        add(leftPanel, BorderLayout.WEST);

        // Panel central: Tabla
        tableModel = new DefaultTableModel(new String[]{"Identificación", "Nombre", "Correo"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior: Botones para archivos
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Acciones con Archivos"));

        JButton btnGuardarPlano = new JButton("Guardar Plano");
        btnGuardarPlano.addActionListener(e -> guardarArchivoPlano());

        JButton btnLeerPlano = new JButton("Cargar Plano");
        btnLeerPlano.addActionListener(e -> leerArchivoPlano());

        JButton btnGuardarXML = new JButton("Guardar XML");
        btnGuardarXML.addActionListener(e -> guardarArchivoXML());

        JButton btnLeerXML = new JButton("Cargar XML");
        btnLeerXML.addActionListener(e -> leerArchivoXML());

        JButton btnGuardarJSON = new JButton("Guardar JSON");
        btnGuardarJSON.addActionListener(e -> guardarArchivoJSON());

        JButton btnLeerJSON = new JButton("Cargar JSON");
        btnLeerJSON.addActionListener(e -> leerArchivoJSON());

        buttonPanel.add(btnGuardarPlano);
        buttonPanel.add(btnLeerPlano);
        buttonPanel.add(btnGuardarXML);
        buttonPanel.add(btnLeerXML);
        buttonPanel.add(btnGuardarJSON);
        buttonPanel.add(btnLeerJSON);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void agregarPersonaATabla() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();

        // Validar el correo
        try {
            validarCorreo(correo); 

            if (!id.isEmpty() && !nombre.isEmpty() && !correo.isEmpty()) {
                Persona persona = new Persona(id, nombre, correo);
                personas.add(persona);
                tableModel.addRow(new Object[]{id, nombre, correo});
                txtId.setText("");
                txtNombre.setText("");
                txtCorreo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
        } catch (ExcepcionCorreosMalEscritos e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarCorreo(String correo) throws ExcepcionCorreosMalEscritos {
        if (!correo.contains("@") || !correo.endsWith(".com")) {
            throw new ExcepcionCorreosMalEscritos("El correo debe contener un '@' y terminar con '.com'.");
        }
    }

    private void guardarArchivoPlano() {
        try {
            GestorArchivos.guardarPlano(personas, "C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.txt");
            JOptionPane.showMessageDialog(this, "Archivo plano guardado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo plano: " + e.getMessage());
        }
    }

    private void leerArchivoPlano() {
        try {
            personas = GestorArchivos.leerPlano("C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.txt");
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Archivo plano cargado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo plano: " + e.getMessage());
        }
    }

    private void guardarArchivoXML() {
        try {
            GestorArchivos.guardarXML(personas, "C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.xml");
            JOptionPane.showMessageDialog(this, "Archivo XML guardado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo XML: " + e.getMessage());
        }
    }

    private void leerArchivoXML() {
        try {
            personas = GestorArchivos.leerXML("C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.xml");
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Archivo XML cargado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo XML: " + e.getMessage());
        }
    }

    private void guardarArchivoJSON() {
        try {
            GestorArchivos.guardarJSON(personas, "C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.json");
            JOptionPane.showMessageDialog(this, "Archivo JSON guardado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo JSON: " + e.getMessage());
        }
    }

    private void leerArchivoJSON() {
        try {
            personas = GestorArchivos.leerJSON("C:\\Users\\57320\\Desktop\\Programación\\TalleresPOO\\src\\parcial3\\personas.json");
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Archivo JSON cargado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo JSON: " + e.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Persona persona : personas) {
            tableModel.addRow(new Object[]{persona.getIdentificacion(), persona.getNombre(), persona.getCorreo()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PersonaApp app = new PersonaApp();
            app.setVisible(true);
        });
    }
}





