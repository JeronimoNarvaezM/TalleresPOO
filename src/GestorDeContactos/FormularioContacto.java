package GestorDeContactos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioContacto extends JDialog {
    private JTextField campoNombre, campoApellido, campoTelefono, campoEmail, campoDireccion;
    private JRadioButton rbSoltero, rbCasado, rbUnionLibre, rbDivorciado;
    private GestorDeContactos gestor;
    private InterfazContactos interfaz;
    private Contacto contactoEditado;

    public FormularioContacto(InterfazContactos interfaz, GestorDeContactos gestor) {
        this(interfaz, gestor, null);
    }

    public FormularioContacto(InterfazContactos interfaz, GestorDeContactos gestor, Contacto contacto) {
        this.gestor = gestor;
        this.interfaz = interfaz;
        this.contactoEditado = contacto;
        inicializarComponentes();
        setModal(true); // Bloquea la ventana principal hasta cerrar este formulario
        setLocationRelativeTo(interfaz); // Centra el formulario en la interfaz principal
    }

    private void inicializarComponentes() {
        setTitle(contactoEditado == null ? "Nuevo Contacto" : "Editar Contacto");
        setSize(400, 350);
        setLayout(new GridLayout(7, 2));

        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoTelefono = new JTextField();
        campoEmail = new JTextField();
        campoDireccion = new JTextField();

        rbSoltero = new JRadioButton("Soltero");
        rbCasado = new JRadioButton("Casado");
        rbUnionLibre = new JRadioButton("Unión Libre");
        rbDivorciado = new JRadioButton("Divorciado");
        
        ButtonGroup grupoEstadoCivil = new ButtonGroup();
        grupoEstadoCivil.add(rbSoltero);
        grupoEstadoCivil.add(rbCasado);
        grupoEstadoCivil.add(rbUnionLibre);
        grupoEstadoCivil.add(rbDivorciado);

        add(new JLabel("Nombre:"));
        add(campoNombre);
        add(new JLabel("Apellido:"));
        add(campoApellido);
        add(new JLabel("Teléfono:"));
        add(campoTelefono);
        add(new JLabel("Email:"));
        add(campoEmail);
        add(new JLabel("Dirección:"));
        add(campoDireccion);
        add(new JLabel("Estado Civil:"));
        
        JPanel panelEstadoCivil = new JPanel();
        panelEstadoCivil.add(rbSoltero);
        panelEstadoCivil.add(rbCasado);
        panelEstadoCivil.add(rbUnionLibre);
        panelEstadoCivil.add(rbDivorciado);
        add(panelEstadoCivil);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        add(btnGuardar);
        add(btnCancelar);

        if (contactoEditado != null) {
            cargarDatosContacto();
        }

        // Evento del botón Guardar
        btnGuardar.addActionListener(e -> guardarContacto());

        // Evento del botón Cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Evento de la tecla Enter para guardar
        getRootPane().setDefaultButton(btnGuardar);
    }

    private void cargarDatosContacto() {
        campoNombre.setText(contactoEditado.getNombre());
        campoApellido.setText(contactoEditado.getApellido());
        campoTelefono.setText(contactoEditado.getTelefono());
        campoEmail.setText(contactoEditado.getEmail());
        campoDireccion.setText(contactoEditado.getDireccion());
        
        // Establecer estado civil
        if (contactoEditado.getEstadoCivil() != null) {
            switch (contactoEditado.getEstadoCivil()) {
                case "Soltero":
                    rbSoltero.setSelected(true);
                    break;
                case "Casado":
                    rbCasado.setSelected(true);
                    break;
                case "Unión Libre":
                    rbUnionLibre.setSelected(true);
                    break;
                case "Divorciado":
                    rbDivorciado.setSelected(true);
                    break;
            }
        }
    }

    private void guardarContacto() {
        String nombre = campoNombre.getText().trim();
        String apellido = campoApellido.getText().trim();
        String telefono = campoTelefono.getText().trim();
        String email = campoEmail.getText().trim();
        String direccion = campoDireccion.getText().trim();
        String estadoCivil = "";

        if (rbSoltero.isSelected()) {
            estadoCivil = "Soltero";
        } else if (rbCasado.isSelected()) {
            estadoCivil = "Casado";
        } else if (rbUnionLibre.isSelected()) {
            estadoCivil = "Unión Libre";
        } else if (rbDivorciado.isSelected()) {
            estadoCivil = "Divorciado";
        }

        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre y el teléfono son obligatorios.");
            return;
        }

        // Crear o actualizar contacto
        if (contactoEditado == null) {
            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono, email, direccion, estadoCivil);
            gestor.agregarContacto(nuevoContacto);
        } else {
            contactoEditado.setNombre(nombre);
            contactoEditado.setApellido(apellido);
            contactoEditado.setTelefono(telefono);
            contactoEditado.setEmail(email);
            contactoEditado.setDireccion(direccion);
            contactoEditado.setEstadoCivil(estadoCivil);
        }

        interfaz.cargarContactosEnTabla();
        dispose(); // Cerrar el formulario
    }

    public void setDatosContacto(Contacto contacto) {
        this.contactoEditado = contacto;
        cargarDatosContacto();
    }
}